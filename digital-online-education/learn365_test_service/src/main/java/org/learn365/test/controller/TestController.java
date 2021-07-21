package org.learn365.test.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.learn365.test.exception.TestException;
import org.learn365.test.model.request.ChapterTestRequest;
import org.learn365.test.model.request.TestDetailsMetadataRequest;
import org.learn365.test.model.request.TestScoreDataRequest;
import org.learn365.test.model.response.TestDataResponse;
import org.learn365.test.model.response.TestScoreDataResponse;
import org.learn365.test.service.TestRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("Test Controller to main all the test records and test scores for users.")
@RestController
@RequestMapping("v1/test/")
public class TestController {

    @Autowired
    TestRequestService testRequestService;

    @Autowired
    Gson gson;

    @PostMapping(value = "/data", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TestDataResponse getChapterTests(@Valid @RequestBody ChapterTestRequest request) throws TestException {
        return testRequestService.getTestDetails(request);
    }

    @PostMapping(value = "/score", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TestScoreDataResponse generateTestScoreData(@Valid @RequestBody TestScoreDataRequest testScoreData) throws TestException {
        TestScoreDataResponse response = testRequestService.saveSubmittedTestData(testScoreData);
        // update student profile data
        return response;
    }

    @PutMapping(value = "/question/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateQuestionSet(@Valid @RequestBody TestDetailsMetadataRequest testDetailsModel) throws TestException {
        String savedTestDataId = testRequestService.updateQuestionSet(testDetailsModel);
        return String.format("Test details updated successfully, Test_Id: %s", savedTestDataId);
    }

    @PostMapping(value = "/deactivate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String deactivateTests(@Valid @RequestBody List<Long> testIds) {
        List<Long> deactivatedIds = testRequestService.deactivateTests(testIds);
        String idsAsString = gson.toJson(deactivatedIds);
        return String.format(
                "Following test id's been deactivated: %s",
                idsAsString
        );
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String deleteTests(@Valid @RequestBody List<Long> testIds) throws TestException {
        List<Long> deletedIds = testRequestService.deleteTests(testIds);
        String idsAsString = gson.toJson(deletedIds);
        return String.format(
                "Following test id's been deleted: %s",
                idsAsString
        );
    }
}
