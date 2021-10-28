package org.learn365.test.dao;

import org.apache.commons.lang3.StringUtils;
import org.learn365.modal.test.entity.GradesEntity;
import org.learn365.modal.test.entity.TestEntity;
import org.learn365.modal.test.entity.UserTestOptsRecordEntity;
import org.learn365.modal.test.entity.UserTestReportEntity;
import org.learn365.modal.test.request.NewTestDataRequest;
import org.learn365.test.repository.GradesRepository;
import org.learn365.test.repository.TestRepository;
import org.learn365.test.repository.UserTestOptsRecordRepository;
import org.learn365.test.repository.UserTestReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.learn365.test.util.TestConstants.*;

@Component
public class TestDetailsDaoImpl implements TestDetailsDao {

    @Autowired
    TestRepository testRepository;

    @Autowired
    UserTestOptsRecordRepository testOptsRecordRepository;

    @Autowired
    UserTestReportRepository userTestReportRepository;

    @Autowired
    GradesRepository gradesRepository;

    @Autowired
    MongoTemplate template;

    @Override
    public TestEntity findTestDataByTestId(String testId) {
        Optional<TestEntity> testEntity = testRepository.findById(testId);
        return testEntity.<RuntimeException>orElseThrow(() -> {
            throw new RuntimeException(ERROR_MESSAGE_INVALID_TEST_ID);
        });
    }

    @Override
    public TestEntity findTestDataByTestCode(String testCode) {
        return testRepository.findByTestCode(testCode);
    }

    @Override
    public Collection<TestEntity> findTestRecords(List<String> testIds) {
        return (Collection<TestEntity>) testRepository.findAllById(testIds);
    }

    @Override
    public TestEntity saveTestData(TestEntity testEntity) {
        return optionalExceptionWrapper(testRepository.save(testEntity),
                "Error occurred while saving updated test record");
    }

    @Override
    public List<TestEntity> saveTestDatas(List<TestEntity> updatedRecords) {
        return optionalExceptionWrapper(testRepository.saveAll(updatedRecords),
                "Error occurred while saving updated test records");
    }

    @Override
    public GradesEntity getGradesFromMarks(Double marks) {
        return gradesRepository.getGradesFromMarks(marks);
    }

    @Override
    public UserTestOptsRecordEntity findTestOptionsRecord(String userId, String testId) {
        return testOptsRecordRepository.findTestRecordByUserIdAndTestId(userId, testId);
    }

    @Override
    public UserTestOptsRecordEntity saveUserTestOptsRecord(UserTestOptsRecordEntity recordEntity) {
        return optionalExceptionWrapper(testOptsRecordRepository.save(recordEntity),
                "Error occurred while saving updated user test option records");
    }

    @Override
    public UserTestReportEntity findTestReportsRecord(String userId, String testId) {
        return userTestReportRepository.findTestReportByUserIdAndTestId(userId, testId);
    }

    @Override
    public UserTestReportEntity saveUserTestReport(UserTestReportEntity reportEntity) {
        return optionalExceptionWrapper(userTestReportRepository.save(reportEntity),
                "Error occurred while saving user test report");
    }

    @Override
    public List<TestEntity> findFilteredTestRecords(List<String> blackListedTestCodes, NewTestDataRequest request, String testType) {
        Query query = new Query();
        List<Criteria> crtList = new ArrayList<>();

        if (!blackListedTestCodes.isEmpty()) {
            crtList.add(Criteria.where("testCode").nin(blackListedTestCodes));
        }
        if (!StringUtils.isBlank(request.getGradeLevel())) {
            crtList.add(Criteria.where("subDetails.gradeLevel").is(request.getGradeLevel()));
        }
        if (!StringUtils.isBlank(request.getSubjectId())) {
            crtList.add(Criteria.where("subDetails.subjectId").is(request.getSubjectId()));
        }
        if (!StringUtils.isBlank(testType)) {
            List<String> testTypes = new ArrayList<>();
            if (testType.equalsIgnoreCase(ALL)) {
                testTypes = Arrays.asList(CR, CT);
            } else {
                testTypes.add(testType);
            }
            crtList.add(Criteria.where("subDetails.testType").in(testTypes));
            if (testType.equals(CT)) {
                crtList.add(Criteria.where("subDetails.testTypeKeyFields.chapterId").is(request.getChapterId()));
            } else if (testType.equals(CR)) {
                crtList.add(Criteria.where("subDetails.testTypeKeyFields.conceptVideoId").is(request.getVideoId()));
            }
        }

        Criteria[] crtArr = crtList.toArray(new Criteria[0]);
        query.addCriteria(new Criteria().andOperator(crtArr));
        return template.find(query, TestEntity.class, COLLECTION_TESTS);
    }

    @Override
    public List<UserTestReportEntity> getReportsList(Map<String, Object> queryMap) {
        Set<String> keys = queryMap.keySet();

        Query query = new Query();
        List<Criteria> crtList = new ArrayList<>();

        for (String key : keys) {
            switch (key) {
                case USER_ID:
                    crtList.add(Criteria.where(USER_ID).is(queryMap.get(USER_ID)));
                    break;
                case SUBJECT_ID:
                    crtList.add(Criteria.where("subDetails.subjectId").is(queryMap.get(SUBJECT_ID)));
                    break;
                case GRADE_LEVEL:
                    crtList.add(Criteria.where("subDetails.gradeLevel").is(queryMap.get(GRADE_LEVEL)));
                    break;
                case CREATED_AT:
                    crtList.add(Criteria.where(CREATED_AT).lte(queryMap.get(CREATED_AT)));
                    break;
                case TEST_TYPE:
                    List<String> testTypes = new ArrayList<>();
                    String testType = (String) queryMap.get(TEST_TYPE);
                    if (testType.equalsIgnoreCase(ALL)) {
                        testTypes = Arrays.asList(CR, CT);
                    } else {
                        testTypes.add(testType);
                    }
                    crtList.add(Criteria.where("subDetails.testType").in(testTypes));
                    break;
                case CHAPTER_ID:
                    crtList.add(Criteria.where("subDetails.testTypeKeyFields.chapterId").is(queryMap.get(CHAPTER_ID)));
                    break;
                case VIDEO_ID:
                    crtList.add(Criteria.where("subDetails.testTypeKeyFields.conceptVideoId").is(queryMap.get(VIDEO_ID)));
                    break;
            }
        }
        Criteria[] crtArr = crtList.toArray(new Criteria[0]);
        query.addCriteria(new Criteria().andOperator(crtArr));
        List<UserTestReportEntity> reportEntities = template.find(query, UserTestReportEntity.class, COLLECTION_USER_TEST_REPORT_ENTITY);
        return reportEntities;
    }

    @Override
    public List<UserTestOptsRecordEntity> getTestOptionsRecord(Map<String, Object> queryMap) {
        Set<String> keys = queryMap.keySet();

        Query query = new Query();
        List<Criteria> crtList = new ArrayList<>();

        for (String key : keys) {
            switch (key) {
                case USER_ID:
                    crtList.add(Criteria.where(USER_ID).is(queryMap.get(USER_ID)));
                    break;
                case SUBJECT_ID:
                    crtList.add(Criteria.where("subDetails.subjectId").is(queryMap.get(SUBJECT_ID)));
                    break;
                case GRADE_LEVEL:
                    crtList.add(Criteria.where("subDetails.gradeLevel").is(queryMap.get(GRADE_LEVEL)));
                    break;
                case CREATED_AT:
                    crtList.add(Criteria.where(CREATED_AT).lte(queryMap.get(CREATED_AT)));
                    break;
                case TEST_TYPE:
                    List<String> testTypes = new ArrayList<>();
                    String testType = (String) queryMap.get(TEST_TYPE);
                    if (testType.equalsIgnoreCase(ALL)) {
                        testTypes = Arrays.asList(CR, CT);
                    } else {
                        testTypes.add(testType);
                    }
                    crtList.add(Criteria.where("subDetails.testType").in(testTypes));
                    break;
                case CHAPTER_ID:
                    crtList.add(Criteria.where("subDetails.testTypeKeyFields.chapterId").is(queryMap.get(CHAPTER_ID)));
                    break;
                case VIDEO_ID:
                    crtList.add(Criteria.where("subDetails.testTypeKeyFields.conceptVideoId").is(queryMap.get(VIDEO_ID)));
                    break;
            }
        }
        Criteria[] crtArr = crtList.toArray(new Criteria[0]);
        query.addCriteria(new Criteria().andOperator(crtArr));
        List<UserTestOptsRecordEntity> reportEntities = template.find(query, UserTestOptsRecordEntity.class, COLLECTION_USER_TEST_OPTIONS_RECORD_ENTITY);
        return reportEntities;
    }

    @Override
    public List<UserTestOptsRecordEntity> getRecentTests(String userId, String gradeLevel, String testType, int numOfRecentTests) {
        Query query = new Query();
        List<Criteria> crtList = new ArrayList<>();

        crtList.add(Criteria.where("userId").is(userId));
        crtList.add(Criteria.where("subDetails.gradeLevel").is(gradeLevel));
        crtList.add(Criteria.where("subDetails.testType").is(testType));

        Criteria[] crtArr = crtList.toArray(new Criteria[0]);
        query.addCriteria(new Criteria().andOperator(crtArr)).with(Sort.by(Sort.Direction.DESC, CREATED_AT)).limit(numOfRecentTests);
        return template.find(query, UserTestOptsRecordEntity.class, COLLECTION_USER_TEST_OPTIONS_RECORD_ENTITY);
    }

    @Override
    public void removeEntity(String id) {
        testRepository.deleteById(id);
    }


    private <T> T optionalExceptionWrapper(T value, String msg) {
        Optional<T> result = Optional.of(value);
        return result.<RuntimeException>orElseThrow(() -> {
            throw new RuntimeException(msg);
        });
    }
}
