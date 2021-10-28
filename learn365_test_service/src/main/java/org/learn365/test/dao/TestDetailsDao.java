package org.learn365.test.dao;

import org.learn365.modal.test.entity.*;
import org.learn365.modal.test.request.NewTestDataRequest;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TestDetailsDao {

    TestEntity findTestDataByTestId(String testId);

    TestEntity findTestDataByTestCode(String testCode);

    Collection<TestEntity> findTestRecords(List<String> testIds);

    UserTestOptsRecordEntity saveUserTestOptsRecord(UserTestOptsRecordEntity recordEntity);

    UserTestReportEntity findTestReportsRecord(String userId, String testId);

    UserTestReportEntity saveUserTestReport(UserTestReportEntity reportEntity);

    UserTestOptsRecordEntity findTestOptionsRecord(String userId, String testId);

    TestEntity saveTestData(TestEntity testEntity);

    List<TestEntity> saveTestDatas(List<TestEntity> updatedRecords);

    GradesEntity getGradesFromMarks(Double marks);

    List<TestEntity> findFilteredTestRecords(List<String> blackListedIds, NewTestDataRequest request, String testType);

    void removeEntity(String id);

    List<UserTestReportEntity> getReportsList(Map<String, Object> queryMap);

    List<UserTestOptsRecordEntity> getTestOptionsRecord(Map<String, Object> queryMap);

    List<UserTestOptsRecordEntity> getRecentTests(String userId, String gradeLevel, String testType,  int numOfRecentTests);
}
