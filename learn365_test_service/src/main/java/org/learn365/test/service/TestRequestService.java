package org.learn365.test.service;

import org.learn365.modal.test.entity.TestEntity;
import org.learn365.modal.test.request.*;
import org.learn365.modal.test.response.*;
import org.learn365.test.exception.TestException;

import java.text.ParseException;
import java.util.List;

public interface TestRequestService {

    TestDataResponse getTestDetails(NewTestDataRequest request, String testType) throws TestException;

    TestDataResponse getTestById(String testId);

    TestScoreDataResponse saveSubmittedTestData(TestScoreDataRequest testScoreData) throws TestException;

    TestEntity buildAndSaveTestData(TestDetailsMetadataRequest testDataObject, Boolean updateCourseTable) throws TestException;

    CreatedTestResponse createTestData(List<TestDetailsMetadataRequest> testDataObjects) throws TestException;

    List<String> changeStatusOfExistingTests(List<String> testIds, boolean isActive, boolean isDeleted);

    TestHistoryResponse findTestHistory(ChapterTestRequest request) throws TestException;

    TestEntity getNewTestData(NewTestDataRequest request, String testType) throws TestException;

    TestStatResponse generateStatForTest(TestStatRequest request) throws Exception;

    List<SubjectStatusResponse> getSubjectStatus(SubjectStatusRequest request) throws TestException;

    TestUsageResponse getUserUsage(UserUsageRequest request) throws TestException;

    RecentTestResponse getRecentTestsData(UserUsageRequest request) throws TestException;
}
