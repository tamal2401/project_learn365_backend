package org.learn365.test.service;

import org.learn365.test.exception.TestException;
import org.learn365.test.model.request.ChapterTestRequest;
import org.learn365.test.model.response.TestDataResponse;
import org.learn365.test.model.request.TestDetailsMetadataRequest;
import org.learn365.test.model.request.TestScoreDataRequest;
import org.learn365.test.model.response.TestScoreDataResponse;

import java.util.List;

public interface TestRequestService {

    TestDataResponse getTestDetails(ChapterTestRequest request) throws TestException;

    TestScoreDataResponse saveSubmittedTestData(TestScoreDataRequest testScoreData) throws TestException;

    String updateQuestionSet(TestDetailsMetadataRequest testDataObject) throws TestException;

    List<Long> deleteTests(List<Long> testIds);

    List<Long> deactivateTests(List<Long> testIds);
}
