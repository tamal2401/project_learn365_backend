package org.learn365.test.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.learn365.test.dao.TestDetailsDao;
import org.learn365.test.entity.*;
import org.learn365.test.exception.TestException;
import org.learn365.test.model.Option;
import org.learn365.test.model.TestQuestion;
import org.learn365.test.model.request.*;
import org.learn365.test.model.response.TestDataResponse;
import org.learn365.test.model.response.TestScoreDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.learn365.test.util.TestUtil.*;

@Component
public class TestRequestServiceImpl implements TestRequestService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Autowired
    TestDetailsDao detailsDao;

    @Override
    public TestDataResponse getTestDetails(ChapterTestRequest request) {
        String testCode = request.getTestCode();
        TestEntity testEntity = detailsDao.findTestData(testCode);

        TestDataResponse response = new TestDataResponse();

        response.setTestId(testEntity.getId());
        response.setTestCode(testEntity.getTestCode());
        response.setUserId(request.getUserId());

        if (testEntity.getIsActive().equals("Y") && testEntity.getIsDeleted().equals("N")) {
            List<TestQuestion> testQuestions = testEntity.getTestQuestions().stream().map(question -> {
                TestQuestion testQuestion = new TestQuestion();

                testQuestion.setQuestionId(question.getId());
                testQuestion.setQuestion(question.getQuestion());
                testQuestion.setQuestionOrder(question.getQuestion_order());
                testQuestion.setExplanationText(question.getExplanationText());
                testQuestion.setMarks(question.getMarks());
                testQuestion.setExplanationVideoId(question.getExplanationVideoId());
                testQuestion.setExplanationVideoPath(question.getExplanationVideoPath());

                Long correctOptionId = question.getCorrectOptionId();
                List<OptionsEntity> optionEntities = question.getOptions();

                Set<Option> options = optionEntities
                        .stream()
                        .map(option -> {
                            Option opt = new Option();

                            opt.setOptionId(option.getId());
                            opt.setOptionSeq(option.getOptionSequence());
                            opt.setOptionDesc(option.getOptionDescription());

                            if (!option.getId().equals(correctOptionId)) {
                                opt.setIsCorrect(Boolean.FALSE);
                            } else {
                                opt.setIsCorrect(Boolean.TRUE);
                            }

                            return opt;
                        }).collect(Collectors.toSet());

                testQuestion.setOptions(options);
                return testQuestion;
            }).collect(Collectors.toList());
            response.setQuestions(testQuestions);
        }
        return response;
    }

    @Transactional
    @Override
    public TestScoreDataResponse saveSubmittedTestData(TestScoreDataRequest testScoreData) throws TestException {
        List<SubmittedQuestionRequest> submittedDetailsList = testScoreData.getSubmittedQuestions();
        TestScoreDataResponse response = new TestScoreDataResponse();
        try {
            // validate test_id exists or not.
            String testCode = testScoreData.getTestCode();
            Long testId = testScoreData.getTestId();
            Long userId = testScoreData.getUserId();

            if (StringUtils.isBlank(testCode)) {
                TestEntity testData = detailsDao.findTestData(testId);
                testCode = testData.getTestCode();
            }

            //validate if test record exists for this test_id and user_id, then it is a repeated submission.
            List<UserTestOptsRecordEntity> tempRecord = detailsDao.findTestRecord(userId, testId);
            optionalExceptionWrapper(tempRecord, String.format("This test is already take by the user, Test_Id: %s, User_Id: %s", testId, userId));

            String finalTestCode = testCode;
            List<UserTestOptsRecordEntity> submittedDetailsEntities = submittedDetailsList.stream()
                    .map(each -> {
                        UserTestOptsRecordEntity recordEntity = new UserTestOptsRecordEntity();
                        recordEntity.setUserId(userId);
                        recordEntity.setTestId(testId);
                        recordEntity.setTestCode(finalTestCode);
                        recordEntity.setQuestionId(each.getQuestionId());
                        recordEntity.setSelectedOptionId(each.getSelectedOptionId());
                        recordEntity.setCorrectOptionId(each.getCorrectOptionId());
                        recordEntity.setCreatedAt(LocalDateTime.now());
                        recordEntity.setUpdatedAt(LocalDateTime.now());
                        return recordEntity;
                    })
                    .collect(Collectors.toList());

            detailsDao.saveUserTestRecords(submittedDetailsEntities);

            Float marks = calculateMarks(testScoreData.getSubmittedQuestions());
            String grades = calculateGrades(marks);

            UserTestReportEntity reportEntity = new UserTestReportEntity();
            reportEntity.setUserId(userId);
            reportEntity.setTestId(testId);
            reportEntity.setTestCode(testCode);
            reportEntity.setGrades(grades);
            reportEntity.setMarks(marks);
            reportEntity.setCreatedAt(LocalDateTime.now());
            reportEntity.setUpdatedAt(LocalDateTime.now());

            detailsDao.saveUserTestReport(reportEntity);

            response.setUserId(userId);
            response.setTestId(testId);
            response.setTestCode(testCode);
            response.setMarks(marks);
        } catch (Exception ex) {
            log.error("Exception occurred: {}", ExceptionUtils.getStackTrace(ex));
            log.error("Rolling back transactions");
            if (ex instanceof TestException) {
                throw new TestException(ExceptionUtils.getMessage(ex));
            }
            throw new TestException("Internal Error occurred, RolledBack transaction");

        }
        return response;
    }

    @Transactional
    @Override
    public String updateQuestionSet(TestDetailsMetadataRequest testDataObject) throws TestException {

        String testCode = testDataObject.getTestCode();
        String testIsActive = testDataObject.getIsActive() ? "Y" : "N";

        List<QuestionEntity> questionEntities = new ArrayList<>();
        TestEntity savedTestEntity;
        try {
            for (QuestionMetadata question : testDataObject.getTestQuestions()) {
                Set<OptionMetadata> optionSet = question.getOptions();
                String correctOptionSeq = "";

                List<OptionsEntity> optionEntitySet = new ArrayList<>();
                for (OptionMetadata option : optionSet) {
                    OptionsEntity optsEntity = buildOptionsEntity(option);

                    if (option.getIsCorrect()) {
                        correctOptionSeq = String.valueOf(option.getOptionSeq());
                    }
                    optionEntitySet.add(optsEntity);
                }

                if (StringUtils.isBlank(correctOptionSeq)) {
                    throw new TestException("Correct option id is not provided");
                }

                List<OptionsEntity> savesOptions = detailsDao.saveOptionsEntities(optionEntitySet);

                String finalCorrectOptionSeq = correctOptionSeq;
                Optional<OptionsEntity> optional = savesOptions.stream()
                        .filter(eachOpt -> eachOpt.getOptionSequence().equals(Integer.valueOf(finalCorrectOptionSeq)))
                        .findFirst();

                if(!optional.isPresent()){
                    throw new TestException(String.format("Error occurred while getting correct option id for test_code: %s", testDataObject.getTestCode()));
                }
                OptionsEntity correctOption = optional.get();

                Long correctOptionId = correctOption.getId();
                QuestionEntity questionEntity = buildQuestionEntity(question, optionEntitySet, correctOptionId);
                QuestionEntity savedQuestionEntity = detailsDao.saveQuestionEntity(questionEntity);
                questionEntities.add(savedQuestionEntity);
            }
            TestEntity testEntity = buildTestEntity(testCode, testIsActive, questionEntities);

            savedTestEntity = detailsDao.saveTestData(testEntity);

            List<QuestionEntity> updatedQuestions = savedTestEntity.getTestQuestions()
                    .stream()
                    .peek(question -> {
                        question.setTest(savedTestEntity);
                        List<OptionsEntity> optionsEntities = question.getOptions()
                                .stream()
                                .peek(option -> option.setQuestion(question))
                                .collect(Collectors.toList());

                        detailsDao.saveOptionsEntities(optionsEntities);
                    }).collect(Collectors.toList());
            detailsDao.saveQuestionEntities(updatedQuestions);
        } catch (Exception ex) {
            log.error("Exception occurred while saving questions master data: {}", ExceptionUtils.getStackTrace(ex));
            log.error("Rolling back transactions");
            if (ex instanceof TestException) {
                throw new TestException(ExceptionUtils.getMessage(ex));
            }
            throw new TestException("Exception occurred while saving questions master data due to contract violation");
        }
        return String.valueOf(savedTestEntity.getId());
    }

    /**
     * To deactivate tests by Id
     * isActive = N
     * isDeleted = Y
     * @param testIds
     * @return testIds
     */
    @Transactional
    @Override
    public List<Long> deleteTests(List<Long> testIds) {
        Collection<TestEntity> records = detailsDao.findTestRecords(testIds);
        optionalExceptionWrapper(records,
                "No records found for the list of testId's");
        List<TestEntity> updatedRecords = records.stream()
                .peek(record -> {
                    record.setIsActive("N");
                    record.setIsDeleted("Y");
                }).collect(Collectors.toList());

        detailsDao.saveTestDatas(updatedRecords);
        return updatedRecords.stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

    /**
     * To deactivate tests by Id
     * isActive = N
     * isDeleted = N
     * @param testIds
     * @return testIds
     */
    @Transactional
    @Override
    public List<Long> deactivateTests(List<Long> testIds) {
        Collection<TestEntity> records = detailsDao.findTestRecords(testIds);
        optionalExceptionWrapper(records,
                "No records found for the list of testId's");
        List<TestEntity> updatedRecords = records.stream()
                .peek(record -> {
                    record.setIsActive("N");
                    record.setIsDeleted("N");
                }).collect(Collectors.toList());

        detailsDao.saveTestDatas(updatedRecords);
        return updatedRecords.stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

    private TestEntity buildTestEntity(String testCode, String testIsActive, List<QuestionEntity> questionEntities) {
        TestEntity testEntity = new TestEntity();
        testEntity.setTestCode(testCode);
        testEntity.setIsActive(testIsActive);
        testEntity.setIsDeleted("N");
        testEntity.setTestQuestions(questionEntities);
        testEntity.setCreatedAt(LocalDateTime.now());
        testEntity.setUpdatedAt(LocalDateTime.now());
        return testEntity;
    }

    private QuestionEntity buildQuestionEntity(QuestionMetadata question, List<OptionsEntity> optionEntitySet, Long correctOptionId) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestion(question.getQuestion());
        questionEntity.setQuestion_order(question.getQuestionOrder());
        questionEntity.setMarks(question.getMarks());
        questionEntity.setExplanationText(question.getExplanationText());
        questionEntity.setExplanationVideoId(question.getExplanationVideoId());
        questionEntity.setExplanationVideoPath(question.getExplanationVideoPath());
        questionEntity.setCorrectOptionId(correctOptionId);
        questionEntity.setOptions(optionEntitySet);
        questionEntity.setCreatedAt(LocalDateTime.now());
        questionEntity.setUpdatedAt(LocalDateTime.now());
        return questionEntity;
    }

    private OptionsEntity buildOptionsEntity(OptionMetadata option) {
        OptionsEntity optsEntity = new OptionsEntity();
        optsEntity.setOptionDescription(option.getOptionDesc());
        optsEntity.setCreatedAt(LocalDateTime.now());
        optsEntity.setUpdatedAt(LocalDateTime.now());
        optsEntity.setOptionSequence(option.getOptionSeq());
        return optsEntity;
    }

    private Float calculateMarks(List<SubmittedQuestionRequest> submittedQuestionRequestList) {
        Integer obtainedMarks = submittedQuestionRequestList.stream()
                .map(question -> {
                    if (question.getCorrectOptionId().equals(question.getSelectedOptionId())) {
                        return question.getMarks();
                    }
                    return 0;
                })
                .reduce(Integer::sum)
                .orElse(0);

        Integer totalMarks = submittedQuestionRequestList.stream()
                .map(SubmittedQuestionRequest::getMarks)
                .reduce(Integer::sum)
                .orElse(0);

        return (Float) (float) (obtainedMarks / totalMarks);
    }


    private String calculateGrades(Float marks) throws TestException {
        String grade = detailsDao.getGradesFromMarks(marks);
        if(!StringUtils.isBlank(grade)){
            return grade;
        }
        throw new TestException("Error occurred while getting grades from DB");
    }
}
