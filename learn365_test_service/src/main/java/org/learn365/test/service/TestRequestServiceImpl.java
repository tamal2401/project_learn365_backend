package org.learn365.test.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.learn365.modal.course.CourseVideoTestStatus;
import org.learn365.modal.course.TestResult;
import org.learn365.modal.course.response.ChapterResponse;
import org.learn365.modal.course.response.ChapterVideoResponse;
import org.learn365.modal.course.response.StandardResponse;
import org.learn365.modal.course.response.SubjectResponse;
import org.learn365.modal.test.*;
import org.learn365.modal.test.entity.*;
import org.learn365.modal.test.entity.BaseEntity;
import org.learn365.modal.test.request.*;
import org.learn365.modal.test.response.*;
import org.learn365.test.channel.Channel;
import org.learn365.test.dao.TestDetailsDao;
import org.learn365.test.exception.TestException;
import org.learn365.test.util.OptionListComparator;
import org.learn365.test.util.QuestionListComparator;
import org.learn365.test.util.TestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.invoke.MethodHandles;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.learn365.test.util.TestUtil.*;
import static org.learn365.test.util.TestConstants.*;

@Component
public class TestRequestServiceImpl implements TestRequestService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Autowired
    TestDetailsDao detailsDao;

    @Autowired
    Channel channel;

    @Autowired
    TestMetadataService testMetadataService;

    @Autowired
    @Qualifier("customTemplate")
    RestTemplate template;

    @Override
    public TestDataResponse getTestDetails(NewTestDataRequest request, String testType) throws TestException {
        TestEntity testEntity = getNewTestData(request, testType);
        assert testEntity != null;
        SubjectDetailsEntity subEntity = testEntity.getSubDetails();
        TestDataResponse response = new TestDataResponse();

        if (testEntity.getIsActive() && !testEntity.getIsDeleted()) {
            List<TestQuestion> testQuestions = testEntity.getQuestions()
                    .stream()
                    .map(this::buildTestQuestion)
                    .collect(Collectors.toList());
            response.setQuestions(testQuestions);
        }

        response.setTestId(testEntity.getId());
        response.setTestCode(testEntity.getTestCode());
        response.setSubId(subEntity.getSubjectId());
        response.setGradeId(subEntity.getGradeLevel());
        response.setChapterName(subEntity.getChapterName());
        response.setChapterPath(subEntity.getChapterPath());

        if (StringUtils.equals(testType, CR)) {
            response.setTestType(CR);
            response.setConceptVideoId(subEntity.getTestTypeKeyFields().get(KEY_VIDEO_ID));
        } else if (StringUtils.equals(testType, CT)) {
            response.setTestType(CT);
            response.setChapterId(subEntity.getTestTypeKeyFields().get(KEY_CHAPTER_ID));
        }
        return response;
    }

    @Override
    public TestDataResponse getTestById(String testId) {
        TestEntity testEntity = detailsDao.findTestDataByTestId(testId);
        optionalExceptionWrapper(testEntity, "Invalid test id requested");

        assert testEntity != null;
        SubjectDetailsEntity subEntity = testEntity.getSubDetails();
        TestDataResponse response = new TestDataResponse();

        if (testEntity.getIsActive() && !testEntity.getIsDeleted()) {
            List<TestQuestion> testQuestions = testEntity.getQuestions()
                    .stream()
                    .map(this::buildTestQuestion)
                    .collect(Collectors.toList());
            response.setQuestions(testQuestions);
        }

        response.setTestId(testEntity.getId());
        response.setTestCode(testEntity.getTestCode());
        response.setSubId(subEntity.getSubjectId());
        response.setGradeId(subEntity.getGradeLevel());
        response.setChapterName(subEntity.getChapterName());
        response.setChapterPath(subEntity.getChapterPath());

        if (StringUtils.equals(subEntity.getTestType(), CR)) {
            response.setTestType(CR);
            response.setConceptVideoId(subEntity.getTestTypeKeyFields().get(KEY_VIDEO_ID));
        } else if (StringUtils.equals(subEntity.getTestType(), CT)) {
            response.setTestType(CT);
            response.setChapterId(subEntity.getTestTypeKeyFields().get(KEY_CHAPTER_ID));
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
            String testId = testScoreData.getTestId();
            String userId = String.valueOf(testScoreData.getUserId());

            TestEntity testData = detailsDao.findTestDataByTestId(testId);
            if (StringUtils.isBlank(testCode)) {
                testCode = testData.getTestCode();
            }

            SubjectDetailsEntity subDetails = testData.getSubDetails();

            UserTestOptsRecordEntity recordEntity = new UserTestOptsRecordEntity();
            recordEntity.setUserId(userId);
            recordEntity.setTestId(testId);
            recordEntity.setSubDetails(subDetails);
            recordEntity.setTestCode(testCode);
            recordEntity.setCreatedAt(LocalDateTime.now());
            recordEntity.setUpdatedAt(LocalDateTime.now());
            List<TestQuestionsOptionsEntity> submittedQuestionDetails = submittedDetailsList.stream()
                    .map(each -> {
                        TestQuestionsOptionsEntity testQuestions = new TestQuestionsOptionsEntity();
                        testQuestions.setQuestionId(each.getQuestionId());
                        testQuestions.setSelectedOptionId(each.getSelectedOptionId());
                        testQuestions.setCorrectOptionId(each.getCorrectOptionId());
                        testQuestions.setMarks(each.getMarks());
                        return testQuestions;
                    })
                    .collect(Collectors.toList());
            recordEntity.setQuestionDetails(submittedQuestionDetails);

            UserTestOptsRecordEntity existingTestOptions = detailsDao.findTestOptionsRecord(userId, testId);
            if (null != existingTestOptions) recordEntity.setId(existingTestOptions.getId());
            detailsDao.saveUserTestOptsRecord(recordEntity);

//            Double marks = calculateMarks(testScoreData.getSubmittedQuestions());
            Double totalMarks = getTotalMarks(testScoreData.getSubmittedQuestions());
            Double obtainedMarks = getObtainedMarks(testScoreData.getSubmittedQuestions());

            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
            Double marksInPercentage = Double.parseDouble(nf.format(obtainedMarks / totalMarks));
            String grades = calculateGrades(marksInPercentage);

            UserTestReportEntity reportEntity = new UserTestReportEntity();
            reportEntity.setUserId(userId);
            reportEntity.setTestId(testId);
            reportEntity.setTestCode(testCode);
            reportEntity.setSubDetails(subDetails);
            reportEntity.setGrades(grades);
            reportEntity.setMarksInPercentage(marksInPercentage);
            reportEntity.setTotalMarks(totalMarks);
            reportEntity.setObtainedMarks(obtainedMarks);
            reportEntity.setCreatedAt(LocalDateTime.now());
            reportEntity.setUpdatedAt(LocalDateTime.now());

            UserTestReportEntity existingTestReport = detailsDao.findTestReportsRecord(userId, testId);
            if (null != existingTestReport) reportEntity.setId(existingTestReport.getId());
            detailsDao.saveUserTestReport(reportEntity);

            // call course service to update
            CourseVideoTestStatus testStatus = new CourseVideoTestStatus();

            if (testData.getSubDetails().getTestType().equals(CR)) {
                if (marksInPercentage >= .5) {
                    testStatus.setTestResult(TestResult.PASSED);
                } else {
                    testStatus.setTestResult(TestResult.FAILED);
                }
                testStatus.setVideoId(Long.parseLong(testData.getSubDetails().getTestTypeKeyFields().get(KEY_VIDEO_ID)));
                testStatus.setUserId(testScoreData.getUserId());
                testStatus.setTestId(testData.getId());
                Message<CourseVideoTestStatus> testMessage = MessageBuilder.withPayload(testStatus).build();
                try{
                    channel.testStatusPublisher().send(testMessage);
                }catch (Throwable ex){
                    log.info("Exception occurred while publishing CR type submitted test data in QUEUE, Details: "+testStatus);
                    log.error("Exception details:: "+ex.getMessage());
                }
            }


            response.setUserId(userId);
            response.setTestId(testId);
            response.setSubjectId(subDetails.getSubjectId());
            response.setGradeLevel(subDetails.getGradeLevel());
            response.setGrades(grades);
            response.setTestCode(testCode);
            response.setMarksInPercentage(marksInPercentage);
        } catch (Exception ex) {
            log.error(EXCEPTION_OCCURRED_PREFIX, ExceptionUtils.getStackTrace(ex));
            log.error(ROLLING_BACK_TRANSACTIONS);
            if (ex instanceof TestException) {
                throw new TestException(ex.getMessage());
            }
            throw new TestException(ERROR_MESSAGE_INTERNAL_ERROR_OCCURRED_ROLLED_BACK_TRANSACTION);

        }
        return response;
    }

    @Transactional
    @Override
    public CreatedTestResponse createTestData(List<TestDetailsMetadataRequest> testDataObjects) {

        List<TestMetadata> entities = new ArrayList<>();
        List<TestError> errorList = new ArrayList<>();
        for (TestDetailsMetadataRequest data : testDataObjects) {
            try {
                TestEntity savedEntity = buildAndSaveTestData(data, true);
                TestMetadata metadata = new TestMetadata(savedEntity.getId(), savedEntity.getTestCode());
                entities.add(metadata);
            } catch (Throwable ex) {
                log.error(EXCEPTION_OCCURRED_PREFIX, ExceptionUtils.getStackTrace(ex));
                log.error(ROLLING_BACK_TRANSACTIONS);
                TestError error = new TestError();
                error.setCode(data.getTestCode());
                error.setErrorMsg(ex.getMessage());
                errorList.add(error);
            }
        }
        CreatedTestResponse response = new CreatedTestResponse();
        response.setTestMetadataList(entities);
        response.setErrorList(errorList);
        return response;
    }

    @Transactional
    @Override
    public TestEntity buildAndSaveTestData(TestDetailsMetadataRequest testDataObject, Boolean updateCourseTable) throws TestException {

        String testCode = testDataObject.getTestCode();
        Boolean testIsActive = testDataObject.getIsActive();

        List<QuestionEntity> questionEntities = new ArrayList<>();
        for (QuestionMetadata question : testDataObject.getTestQuestions()) {
            List<OptionMetadata> optionSet = question.getOptions();
            boolean correctOptionFound = false;

            List<OptionsEntity> optionEntitySet = new ArrayList<>();
            for (OptionMetadata option : optionSet) {
                OptionsEntity optsEntity = buildOptionsEntity(option);

                if (optsEntity.getIsCorrect()) {
                    if (!correctOptionFound) {
                        correctOptionFound = true;
                    } else {
                        throw new TestException(ERROR_MESSAGE_MULTIPLE_CORRECT_OPTION_PROVIDED);
                    }
                }
                optionEntitySet.add(optsEntity);
            }
            optionEntitySet.sort(new OptionListComparator());

            if (!correctOptionFound) {
                throw new TestException(ERROR_MESSAGE_CORRECT_OPTION_ID_IS_NOT_PROVIDED);
            }
            QuestionEntity questionEntity = buildQuestionEntity(question, optionEntitySet, testCode);
            questionEntities.add(questionEntity);
        }
        questionEntities.sort(new QuestionListComparator());

        SubjectDetailsEntity subEntity = buildSubjectEntity(testDataObject);
        TestEntity testEntity = buildTestEntity(testCode, testIsActive, questionEntities);
        testEntity.setSubDetails(subEntity);

        TestEntity savedEntity = detailsDao.saveTestData(testEntity);

        if (!updateCourseTable) {
            return savedEntity;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://course-portfolio-services/v1/chapter/service/")
                .queryParam("testid", savedEntity.getId());

//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9018/v1/chapter/service/")
//                .queryParam("testid", savedEntity.getId());

        ResponseEntity<Boolean> responseEntity = null;
        if (StringUtils.equals(testDataObject.getTestType(), CR)) {
            builder.path("chaptervideo/test").queryParam("chaptervideoid", savedEntity.getSubDetails().getTestTypeKeyFields().get(KEY_VIDEO_ID));
        } else if (StringUtils.equals(testDataObject.getTestType(), CT)) {
            builder.path("test").queryParam("chapterid", savedEntity.getSubDetails().getTestTypeKeyFields().get(KEY_CHAPTER_ID));
        }

        try {
            responseEntity = template.exchange(builder.build().toUri(), HttpMethod.PUT, entity, Boolean.class);
            if (responseEntity.getStatusCodeValue() != 200) {
                throw new TestException("test data " + savedEntity.getId() + " not updated in course table, Failed uri: " + builder.build().toUri());
            }
            boolean isSaved = responseEntity.getBody() != null ? responseEntity.getBody() : false;
            if (!isSaved) {
                throw new TestException("Test Id " + savedEntity.getId() + " not saved in course service. Rolling back transaction.");
            }
        } catch (Exception ex) {
            rollbackTransaction(savedEntity.getId());
            log.error("Saved test entity object removed due to failure, test id: " + savedEntity.getId());
            throw ex;
        }

        return savedEntity;
    }

    @Transactional
    @Override
    public List<String> changeStatusOfExistingTests(List<String> testIds, boolean isActive, boolean isDeleted) {
        Collection<TestEntity> records = detailsDao.findTestRecords(testIds);
        optionalExceptionWrapper(records,
                ERROR_MESSAGE_NO_RECORDS_FOUND_FOR_THE_LIST_OF_TEST_ID);
        List<TestEntity> updatedRecords = records.stream()
                .peek(record -> {
                    record.setIsActive(isActive);
                    record.setIsDeleted(isDeleted);
                }).collect(Collectors.toList());

        detailsDao.saveTestDatas(updatedRecords);
        return updatedRecords.stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

    @Override
    public TestHistoryResponse findTestHistory(ChapterTestRequest request) throws TestException {
        TestEntity testEntity = getEntity(request);
        SubjectDetailsEntity subDetails = testEntity.getSubDetails();

        String userId = request.getUserId();
        String testId = testEntity.getId();
        String subId = subDetails.getSubjectId();
        String gradeLevel = subDetails.getGradeLevel();

        UserTestOptsRecordEntity tempRecord = detailsDao.findTestOptionsRecord(userId, testId);
        optionalExceptionWrapper(tempRecord, ERROR_MESSAGE_NO_TEST_HISTORY_EXISTS);

        UserTestReportEntity tempReportRecord = detailsDao.findTestReportsRecord(userId, testId);
        optionalExceptionWrapper(tempReportRecord, ERROR_MESSAGE_NO_TEST_HISTORY_EXISTS);

        List<TestQuestionsOptionsEntity> submittedQuestions = tempRecord.getQuestionDetails();

        TestHistoryResponse response = new TestHistoryResponse();
        response.setUserId(userId);
        response.setTestId(testEntity.getId());
        response.setTestCode(testEntity.getTestCode());
        response.setSubjectId(subId);
        response.setGradeLevel(gradeLevel);
        response.setMarksPercentage(tempReportRecord.getMarksInPercentage());
        response.setGrades(tempReportRecord.getGrades());
        response.setTestType(tempRecord.getSubDetails().getTestType());

        List<TestQuestionData> mappedHistory = testEntity.getQuestions()
                .stream()
                .map(each -> getTestQuestionsRecord(each, submittedQuestions))
                .collect(Collectors.toList());

        response.setQuestions(mappedHistory);
        return response;
    }

    @Override
    public TestEntity getNewTestData(NewTestDataRequest request, String testType) throws TestException {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put(USER_ID, request.getUserId());
        queryMap.put(GRADE_LEVEL, request.getGradeLevel());
        queryMap.put(SUBJECT_ID, request.getSubjectId());
        queryMap.put(TEST_TYPE, testType);

        switch (testType) {
            case CT:
                queryMap.put(CHAPTER_ID, request.getChapterId());
                break;
            case CR:
                queryMap.put(VIDEO_ID, request.getVideoId());
                break;
            case ALL:
                queryMap.put(CHAPTER_ID, request.getChapterId());
                queryMap.put(VIDEO_ID, request.getVideoId());
                break;
        }
        List<UserTestReportEntity> reports = detailsDao.getReportsList(queryMap);
        List<String> blackListedIds = reports.stream().map(UserTestReportEntity::getId).collect(Collectors.toList());

        // pull all the tests entities which does not contains any of the above id's
        List<TestEntity> filteredTests = detailsDao.findFilteredTestRecords(blackListedIds, request, testType);

        if (filteredTests.isEmpty()) {
            throw new TestException("No valid tests exists for user : " + request.getUserId());
        }

        return filteredTests.stream().findFirst().get();
    }

    @Override
    public TestStatResponse generateStatForTest(TestStatRequest request) throws Exception {
        TestStatResponse response = new TestStatResponse();

        Date formattedDate;
        try {
            formattedDate = TestUtil.formatDate(request.getDateLimit());
        } catch (ParseException parseException) {
            throw new TestException("Incorrect date format provided to filter");
        }

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put(USER_ID, request.getUserId());
        queryMap.put(TEST_TYPE, request.getTestType());
        queryMap.put(CREATED_AT, formattedDate);
        queryMap.put(GRADE_LEVEL, request.getGradeLevel());
        List<UserTestReportEntity> reportList = detailsDao.getReportsList(queryMap);
        optionalExceptionWrapper(reportList, "No test report exists for user " + request.getUserId() + " adn test type " + request.getTestType());
        int attemptsCount = reportList.size();

        int completedTestCount = 0;
        switch (request.getTestType()) {
            case CT:
                completedTestCount = reportList.stream()
                        .map(report -> report.getSubDetails().getTestTypeKeyFields().get(CHAPTER_ID))
                        .collect(Collectors.toSet())
                        .size();
                break;
            case CR:
                completedTestCount = reportList.stream()
                        .map(report -> report.getSubDetails().getTestTypeKeyFields().get(VIDEO_ID))
                        .collect(Collectors.toSet())
                        .size();
                break;
            case ALL:
                int tempChapterCount = reportList.stream()
                        .map(report -> report.getSubDetails().getTestTypeKeyFields().get(CHAPTER_ID))
                        .collect(Collectors.toSet())
                        .size();

                int tempVideoIdCount = reportList.stream()
                        .map(report -> report.getSubDetails().getTestTypeKeyFields().get(VIDEO_ID))
                        .collect(Collectors.toSet())
                        .size();
                completedTestCount = tempChapterCount + tempVideoIdCount;
                break;
        }

        int totalMarks = 0;
        int obtainedMarks = 0;
        for (UserTestReportEntity report : reportList) {
            totalMarks = totalMarks + report.getTotalMarks().intValue();
            obtainedMarks = obtainedMarks + report.getObtainedMarks().intValue();
        }

        Set<String> subjects = testMetadataService.getSubjectSet();
        int totalTestsSize = subjects.stream()
                .map(subject -> testMetadataService.getChapterList(request.getGradeLevel(), subject).size())
                .reduce(0, Integer::sum);

        if (reportList.size() > totalTestsSize) {
            throw new TestException(String.format("Number of total tests is more than tests attempted by user %s and testType %s",
                    request.getUserId(), request.getTestType()));
        }
        int pendingTests = totalTestsSize - completedTestCount;

//      Calculate total marks percentages for each subjects
        if (reportList.isEmpty()) {
            response.setUserId(request.getUserId());
            response.setSubGradeMap(new ArrayList<>());
            response.setTotalTestAttempted(attemptsCount);
            response.setTotalTestAvailable(pendingTests);
            response.setFavouriteSub(new HashSet<>());
            response.setWeakSub(new HashSet<>());
            response.setTotalMarks(totalMarks);
            response.setObtainedMarks(obtainedMarks);
            return response;
        }

        Map<String, Integer> uniqueSubjectsCountMap = reportList.stream()
                .map(each -> each.getSubDetails().getSubjectId())
                .collect(Collectors.toMap(Function.identity(), e -> 1, Math::addExact));

        List<Identifier> subToMarksList = uniqueSubjectsCountMap.entrySet()
                .stream()
                .map(entrySet -> {
                    Optional<Double> subjectSum = reportList.stream()
                            .filter(report -> report.getSubDetails().getSubjectId().equals(entrySet.getKey()))
                            .map(UserTestReportEntity::getMarksInPercentage)
                            .reduce(Double::sum);
                    Double marks = subjectSum.orElse(Double.valueOf("0")) / entrySet.getValue();
                    return new Identifier(entrySet.getKey(), marks);
                })
                .collect(Collectors.toList());

        Set<Double> marksSet = subToMarksList.stream().map(each -> (Double) each.getVal()).collect(Collectors.toSet());

        // take top one as favourite sub and lowest one as weakest sub
        Set<String> fvrtSubs = new HashSet<>();
        Set<String> weakSubs = new HashSet<>();
        Double maxVal = Collections.max(marksSet);
        Double minVal = Collections.min(marksSet);

        for (Identifier entry : subToMarksList) {
            Double mark = (Double) entry.getVal();
            if (maxVal.equals(minVal)) {
                if (mark.equals(maxVal)) {
                    fvrtSubs.add(entry.getKey());
                    continue;
                }
            }
            if (mark.equals(maxVal)) {
                fvrtSubs.add(entry.getKey());
            }
            if (mark.equals(minVal)) {
                weakSubs.add(entry.getKey());
            }
        }

        response.setUserId(request.getUserId());
        response.setSubGradeMap(subToMarksList);
        response.setTotalTestAttempted(attemptsCount);
        response.setTotalTestAvailable(pendingTests);
        response.setFavouriteSub(fvrtSubs);
        response.setWeakSub(weakSubs);
        response.setTotalMarks(totalMarks);
        response.setObtainedMarks(obtainedMarks);
        return response;
    }

    @Override
    public List<SubjectStatusResponse> getSubjectStatus(SubjectStatusRequest request) throws TestException {
        List<String> subjects = request.getSubjects();
        String userId = request.getUserId();
        String gradeLevel = request.getGradeId();
        List<SubjectStatusResponse> responseList = new ArrayList<>();

        List<StandardResponse> standardResponses = testMetadataService.getStandardResponse();

        for (String sub : subjects) {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put(USER_ID, userId);
            queryMap.put(GRADE_LEVEL, gradeLevel);
            queryMap.put(SUBJECT_ID, sub);
            queryMap.put(TEST_TYPE, CT);

            List<UserTestReportEntity> reports = detailsDao.getReportsList(queryMap);

            List<ChapterResponse> chapterList = standardResponses.stream()
                    .filter(response -> response.getName().equals(gradeLevel))
                    .flatMap(response -> response.getSubjects().stream())
                    .filter(subject -> subject.getName().equals(sub))
                    .flatMap(subject -> subject.getChapters().stream())
                    .collect(Collectors.toList());

            List<CompletedChapterTestStatus> chapterStatusList = new ArrayList<>();
            List<Long> tempCompletedChapters = new ArrayList<>();

            for (UserTestReportEntity report : reports) {
                CompletedChapterTestStatus res = new CompletedChapterTestStatus();
                Long completedChapterId = Long.valueOf(report.getSubDetails().getTestTypeKeyFields().get(CHAPTER_ID));
                for (ChapterResponse chapter : chapterList) {
                    if (completedChapterId.equals(chapter.getId())) {
                        tempCompletedChapters.add(chapter.getId());
                        res.setChapterName(chapter.getChapterName());
                        res.setChapterTestDate(report.getCreatedAt().toString());
                        res.setChapterTestScore(report.getGrades());
                        chapterStatusList.add(res);
                    }
                }
            }

            List<PendingChapterTestData> pendingChapters = chapterList.stream()
                    .filter(chapter -> !tempCompletedChapters.contains(chapter.getId()))
                    .map(chapter -> {
                        PendingChapterTestData pendingTestData = new PendingChapterTestData();
                        pendingTestData.setChapterName(chapter.getChapterName());
                        pendingTestData.setChapterId(chapter.getId());
                        pendingTestData.setChapterMainTestId(chapter.getTestid());
                        return pendingTestData;
                    })
                    .collect(Collectors.toList());

            SubjectStatusResponse subRes = new SubjectStatusResponse();
            subRes.setSubject(sub);
            subRes.setTotalTests(chapterList.size());
            subRes.setPendingChapterNames(pendingChapters);
            subRes.setCompletedTests(chapterStatusList.size());
            subRes.setAllChapterTestStatus(chapterStatusList);
            responseList.add(subRes);
        }
        return responseList;
    }

    @Override
    public TestUsageResponse getUserUsage(UserUsageRequest request) throws TestException {
        TestUsageResponse res = new TestUsageResponse();

        List<StandardResponse> standardResponses = testMetadataService.getStandardResponse();
        long totalTests = standardResponses.stream()
                .filter(response -> response.getName().equals(request.getGradeLevel()))
                .flatMap(response -> response.getSubjects().stream())
                .mapToLong(subject -> subject.getChapters().size())
                .sum();

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put(USER_ID, request.getUserId());
        queryMap.put(GRADE_LEVEL, request.getGradeLevel());
        queryMap.put(TEST_TYPE, CT);

        List<UserTestReportEntity> reports = detailsDao.getReportsList(queryMap);
        long completedTestsCount = 0;
        Double maxScore = 0d;
        Double minScore = 1d;

        for (UserTestReportEntity report : reports) {
            if (report.getSubDetails().getTestTypeKeyFields().containsKey(CHAPTER_ID)) {
                completedTestsCount++;
            }
            Double score = report.getMarksInPercentage();
            if (score > maxScore) {
                maxScore = score;
            }

            if (score < minScore) {
                minScore = score;
            }
        }

        Set<String> fvrtSubs = new HashSet<>();
        Set<String> weakSubs = new HashSet<>();
        for (UserTestReportEntity report : reports) {
            Double subMark = report.getMarksInPercentage();
            if (maxScore.equals(minScore)) {
                if (subMark.equals(maxScore)) {
                    fvrtSubs.add(report.getSubDetails().getSubjectId());
                    continue;
                }
            }
            if (maxScore.equals(subMark)) {
                fvrtSubs.add(report.getSubDetails().getSubjectId());
            }
            if (minScore.equals(subMark)) {
                weakSubs.add(report.getSubDetails().getSubjectId());
            }
        }

        long pendingTests = totalTests - completedTestsCount;

        res.setUserId(request.getUserId());
        res.setCompletedTestCount(completedTestsCount);
        res.setPendingTestCount(pendingTests);
        res.setFavouriteSub(fvrtSubs);
        res.setWeakSub(weakSubs);
        return res;
    }

    @Override
    public RecentTestResponse getRecentTestsData(UserUsageRequest request) throws TestException {
        List<StandardResponse> standardResponses = testMetadataService.getStandardResponse();
        long totalTests = standardResponses.stream()
                .filter(response -> response.getName().equals(request.getGradeLevel()))
                .flatMap(response -> response.getSubjects().stream())
                .mapToInt(subject -> subject.getChapters().size())
                .sum();

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put(USER_ID, request.getUserId());
        queryMap.put(GRADE_LEVEL, request.getGradeLevel());
        queryMap.put(TEST_TYPE, CT);
        List<UserTestReportEntity> reports = detailsDao.getReportsList(queryMap);
        long completedTests = reports.stream()
                .filter(report -> report.getSubDetails().getTestTypeKeyFields().containsKey(CHAPTER_ID))
                .count();

        List<UserTestOptsRecordEntity> recentTests = detailsDao.getRecentTests(request.getUserId(), request.getGradeLevel(), CT, 5);
        List<UserTestOptsRecordEntity> recentConceptRefresherTests = detailsDao.getRecentTests(request.getUserId(), request.getGradeLevel(), CR, 5);

        Set<Long> recentVideoIdSet = recentConceptRefresherTests.stream()
                .map(each -> {
                    String id = each.getSubDetails().getTestTypeKeyFields().get(VIDEO_ID);
                    return Long.parseLong(id);
                })
                .collect(Collectors.toSet());
        List<VideoMetadata> recentClassMetadata = buildRecentClassData(request.getGradeLevel(), recentVideoIdSet);

        List<Identifier> chapters = testMetadataService.getAllChapters(request.getGradeLevel());
        List<RecentTestMetadata> metaDataList = recentTests.stream()
                .map(test -> {
                    Integer totalMarks = test.getQuestionDetails()
                            .stream()
                            .map(TestQuestionsOptionsEntity::getMarks)
                            .reduce(0, Integer::sum);
                    Optional<String> opt = chapters.stream()
                            .filter(chap -> chap.getKey().equals(test.getSubDetails().getTestTypeKeyFields().get(CHAPTER_ID)))
                            .map(each -> String.valueOf(each.getVal()))
                            .findFirst();
                    String chapterName = opt.orElse("");

                    RecentTestMetadata metaData = new RecentTestMetadata();
                    metaData.setTestId(test.getTestId());
                    metaData.setSubjectName(test.getSubDetails().getSubjectId());
                    metaData.setTotalNumOfQuestions(test.getQuestionDetails().size());
                    metaData.setTotalMarks(totalMarks);
                    metaData.setChapterName(chapterName);

                    return metaData;
                })
                .collect(Collectors.toList());

        RecentTestResponse response = new RecentTestResponse();
        response.setTotalTestCount(totalTests);
        response.setCompletedTestCount(completedTests);
        response.setRecentTestsData(metaDataList);
        response.setRecentVideoDetails(recentClassMetadata);
        return response;
    }

    private List<VideoMetadata> buildRecentClassData(String gradeLevel, Set<Long> recentVideoIdSet) throws TestException {
        List<VideoMetadata> recentClassMetadata = new ArrayList<>();

        try {
            List<StandardResponse> stndrdRes = testMetadataService.getStandardResponse();
            List<SubjectResponse> gradeMetaData = stndrdRes.stream().filter(each -> each.getName().equals(gradeLevel)).findFirst().map(StandardResponse::getSubjects).get();

            for(SubjectResponse subjectResponse : gradeMetaData){
                for(ChapterResponse chapterRes:subjectResponse.getChapters()){
                    for(ChapterVideoResponse videoRes : chapterRes.getChaptervideo()){
                        for(Long videoId : recentVideoIdSet){
                            if(videoId.equals(videoRes.getId())){
                                VideoMetadata metadata = new VideoMetadata();
                                metadata.setGrade(gradeLevel);
                                metadata.setChaptername(chapterRes.getChapterName());
                                metadata.setChapterurl(chapterRes.getChapterpath());
                                metadata.setSubjectname(subjectResponse.getName());
                                metadata.setVideothumbnail(videoRes.getThumbnail());
                                metadata.setChaptervideoUrl(videoRes.getVideourl());

                                recentClassMetadata.add(metadata);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (TestException e) {
            log.error("Unable to fetch Standardresponse for Recent Class data");
            throw e;
        }

        return recentClassMetadata;
    }

    private TestQuestionData getTestQuestionsRecord(QuestionEntity each,
                                                    List<TestQuestionsOptionsEntity> submittedQuestions) {
        TestQuestionData questionHistory = new TestQuestionData();
        questionHistory.setQuestion(each.getQuestion());
        questionHistory.setQuestionId(each.getQuestionId());
        questionHistory.setMarks(each.getMarks());
        questionHistory.setQuestionOrder(each.getQuestionOrder());
        questionHistory.setExplanationText(each.getExplanationText());
        questionHistory.setExplanationVideoId(each.getExplanationVideoId());
        questionHistory.setExplanationVideoPath(each.getExplanationVideoPath());
        questionHistory.setOptions(each.getOptions()
                .stream()
                .map(this::getOptionsRecord)
                .collect(Collectors.toList()));
        Optional<String> optionalSelectedOption = submittedQuestions.stream()
                .filter(eachSubQues -> eachSubQues.getQuestionId().equals(each.getQuestionId()))
                .map(TestQuestionsOptionsEntity::getSelectedOptionId)
                .findFirst();

        optionalSelectedOption.ifPresent(questionHistory::setSelectedOptionId);

        return questionHistory;
    }

    private TestQuestion buildTestQuestion(QuestionEntity question) {
        TestQuestion testQuestion = new TestQuestion();

        testQuestion.setQuestionId(question.getQuestionId());
        testQuestion.setQuestion(question.getQuestion());
        testQuestion.setQuestionOrder(question.getQuestionOrder());
        testQuestion.setExplanationText(question.getExplanationText());
        testQuestion.setMarks(question.getMarks());
        testQuestion.setExplanationVideoId(question.getExplanationVideoId());
        testQuestion.setExplanationVideoPath(question.getExplanationVideoPath());

        List<OptionsEntity> optionEntities = question.getOptions();
        Set<Option> options = optionEntities
                .stream()
                .map(this::getOptionsRecord).collect(Collectors.toSet());

        testQuestion.setOptions(options);
        return testQuestion;
    }

    private Option getOptionsRecord(OptionsEntity optEntity) {
        Option opt = new Option();
        opt.setOptionId(optEntity.getOptionId());
        opt.setOptionDesc(optEntity.getOptionDescription());
        opt.setOptionSeq(optEntity.getOptionSequence());
        opt.setIsCorrect(optEntity.getIsCorrect());
        return opt;
    }

    private TestEntity getEntity(ChapterTestRequest request) throws TestException {
        TestEntity testEntity;
        if (!StringUtils.isBlank(request.getTestCode())) {
            String testCode = request.getTestCode();
            testEntity = detailsDao.findTestDataByTestCode(testCode);
        } else if (!StringUtils.isBlank(request.getTestId())) {
            String testId = request.getTestId();
            testEntity = detailsDao.findTestDataByTestId(testId);
        } else {
            throw new TestException(ERROR_MESSAGE_INVALID_TEST_CODE);
        }
        optionalExceptionWrapper(testEntity, ERROR_MESSAGE_NO_TEST_RECORD_EXISTS);
        return testEntity;
    }

    private TestEntity buildTestEntity(String testCode, Boolean testIsActive, List<QuestionEntity> questionEntities) {
        TestEntity testEntity = new TestEntity();
        testEntity.setTestCode(testCode);
        testEntity.setIsActive(testIsActive);
        testEntity.setIsDeleted(false);
        testEntity.setQuestions(questionEntities);
        testEntity.setCreatedAt(LocalDateTime.now());
        testEntity.setUpdatedAt(LocalDateTime.now());
        return testEntity;
    }

    private QuestionEntity buildQuestionEntity(QuestionMetadata question, List<OptionsEntity> optionEntitySet, String testCode) {
        QuestionEntity questionEntity = new QuestionEntity();
        String questionId = generateRandomId();
        questionEntity.setQuestionId(questionId);
        questionEntity.setQuestion(question.getQuestion());
        questionEntity.setQuestionOrder(question.getQuestionOrder());
        questionEntity.setMarks(question.getMarks());
        if (StringUtils.isBlank(question.getExplanationText()) || StringUtils.equals(question.getExplanationText(), "null")) {
            log.info("Missing explanation text for testId {}, QuestionId: {}", testCode, questionId);
        }
        questionEntity.setExplanationText(question.getExplanationText());
        if (StringUtils.isBlank(question.getExplanationVideoId()) || StringUtils.equals(question.getExplanationVideoId(), "null")) {
            log.info("Missing explanation video link for testId {}, QuestionId: {}", testCode, questionId);
        }
        questionEntity.setExplanationVideoId(question.getExplanationVideoId());
        questionEntity.setExplanationVideoPath(question.getExplanationVideoPath());
        questionEntity.setOptions(optionEntitySet);
        return questionEntity;
    }

    private SubjectDetailsEntity buildSubjectEntity(TestDetailsMetadataRequest testDataObject) {
        SubjectDetailsEntity subjectMetadata = new SubjectDetailsEntity();
        subjectMetadata.setSubjectId(testDataObject.getSubjectId());
        subjectMetadata.setGradeLevel(testDataObject.getGradeCode());
        subjectMetadata.setTestType(testDataObject.getTestType());
        subjectMetadata.setChapterName(testDataObject.getChapterName());
        subjectMetadata.setChapterPath(testDataObject.getChapterPath());
        subjectMetadata.setTestTypeKeyFields(testDataObject.getTestKeyFields());
        return subjectMetadata;
    }

    private OptionsEntity buildOptionsEntity(OptionMetadata option) {
        OptionsEntity optsEntity = new OptionsEntity();
        optsEntity.setOptionId(generateRandomId());
        optsEntity.setOptionDescription(option.getOptionDesc());
        optsEntity.setIsCorrect(option.getIsCorrect());
        optsEntity.setOptionSequence(option.getOptionSeq());
        return optsEntity;
    }

    private Double calculateMarks(List<SubmittedQuestionRequest> submittedQuestionRequestList) {
        Double obtainedMarks = submittedQuestionRequestList.stream()
                .map(question -> {
                    if (question.getCorrectOptionId().equals(question.getSelectedOptionId())) {
                        return question.getMarks();
                    }
                    return 0;
                })
                .reduce(Integer::sum)
                .orElse(0)
                .doubleValue();

        Double totalMarks = submittedQuestionRequestList.stream()
                .map(SubmittedQuestionRequest::getMarks)
                .reduce(Integer::sum)
                .orElse(0)
                .doubleValue();

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        return Double.parseDouble(nf.format(obtainedMarks / totalMarks));
    }

    private Double getObtainedMarks(List<SubmittedQuestionRequest> submittedQuestionRequestList) {
        return submittedQuestionRequestList.stream()
                .map(question -> {
                    if (question.getCorrectOptionId().equals(question.getSelectedOptionId())) {
                        return question.getMarks();
                    }
                    return 0;
                })
                .reduce(Integer::sum)
                .orElse(0)
                .doubleValue();
    }

    private Double getTotalMarks(List<SubmittedQuestionRequest> submittedQuestionRequestList) {
        return submittedQuestionRequestList.stream()
                .map(SubmittedQuestionRequest::getMarks)
                .reduce(Integer::sum)
                .orElse(0)
                .doubleValue();
    }

    private String calculateGrades(Double marks) {
        GradesEntity val = optionalExceptionWrapper(detailsDao.getGradesFromMarks(marks),
                ERROR_MESSAGE_ERROR_WHILE_GETTING_GRADES_FROM_DB);
        return val.getGrade();
    }

    private void rollbackTransaction(String id) {
        detailsDao.removeEntity(id);
    }

    private <T> T optionalExceptionWrapper(T value, String msg) {
        Optional<T> result = Optional.of(value);
        return result.<RuntimeException>orElseThrow(() -> {
            throw new RuntimeException(msg);
        });
    }
}
