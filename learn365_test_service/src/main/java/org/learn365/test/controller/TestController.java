package org.learn365.test.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.learn365.modal.test.entity.TestEntity;
import org.learn365.modal.test.entity.UserTestOptsRecordEntity;
import org.learn365.modal.test.entity.UserTestReportEntity;
import org.learn365.modal.test.request.*;
import org.learn365.modal.test.response.*;
import org.learn365.test.exception.TestException;
import org.learn365.test.repository.TestRepository;
import org.learn365.test.repository.UserTestOptsRecordRepository;
import org.learn365.test.repository.UserTestReportRepository;
import org.learn365.test.service.TestMetadataService;
import org.learn365.test.service.TestRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.learn365.test.util.TestConstants.*;

@Api(TEST_CONTROLLER_API_MESSAGE)
@RestController
@RequestMapping("/v1/test/")
public class TestController {

    @Autowired
    TestRequestService testRequestService;

    @Autowired
    Gson gson;

    @ApiOperation(value = API_MESSAGE_FOR_GET_CHAPTER_TEST_BY_ID)
    @GetMapping(value = {"/data", "/service/data"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public TestDataResponse getTest(@RequestParam("testId") String testId) {
        return testRequestService.getTestById(testId);
    }

    @ApiOperation(value = API_MESSAGE_FOR_GET_CHAPTER_TESTS)
    @PostMapping(value = {"/data/{testType}", "/service/data/{testType}"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TestDataResponse getTestByIdentifiers(@Valid @RequestBody NewTestDataRequest request,
                                                 @PathVariable("testType") String testType) throws TestException {
        if (testType.equalsIgnoreCase(CR)) {
            return testRequestService.getTestDetails(request, CR);
        } else if (testType.equalsIgnoreCase(CT)) {
            return testRequestService.getTestDetails(request, CT);
        }
        throw new TestException("Invalid test type requested");
    }

    @ApiOperation(value = API_MESSAGE_TO_POST_TEST_DATA_TO_DB)
    @PutMapping(value = {"/create", "/service/create"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreatedTestResponse createTests(@Valid @RequestBody List<TestDetailsMetadataRequest> testDetailsModels) throws TestException {
        return testRequestService.createTestData(testDetailsModels);
    }

    @ApiOperation(value = API_MESSAGE_FOR_GENERATE_CHAPTER_SCORE_DATA)
    @PostMapping(value = {"/submit", "/service/submit"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TestScoreDataResponse generateTestScoreData(@Valid @RequestBody TestScoreDataRequest testScoreData) throws TestException {
        TestScoreDataResponse response = testRequestService.saveSubmittedTestData(testScoreData);
        // update student profile data
        return response;
    }

    @ApiOperation(value = API_TO_CHANGE_TEST_STATUS)
    @PostMapping(value = {"/change/status", "/service/change/status"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String changeStatus(@Valid @RequestBody List<String> testIds,
                               @RequestParam boolean isActive,
                               @RequestParam boolean isDeleted
    ) {
        List<String> deactivatedIds = testRequestService.changeStatusOfExistingTests(testIds, isActive, isDeleted);
        String idsAsString = gson.toJson(deactivatedIds);
        return String.format(
                "Following %s test id's been modified: %s",
                deactivatedIds.size(),
                idsAsString
        );
    }

    @ApiOperation(value = API_TO_GET_TEST_HISTORY)
    @PostMapping(value = {"/history", "/service/history"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TestHistoryResponse getTestHistory(@Valid @RequestBody ChapterTestRequest request) throws TestException {
        return testRequestService.findTestHistory(request);
    }

    @ApiOperation(value = API_TO_GET_TEST_REPORT)
    @PostMapping(value = {"/report", "/service/report"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TestStatResponse getUserTestStat(@Valid @RequestBody TestStatRequest request) throws Exception {
        return testRequestService.generateStatForTest(request);
    }

    @ApiOperation(value = API_TO_GET_TEST_STATUS)
    @PostMapping(value = {"/subject/status", "/service/subject/status"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubjectStatusResponse> subjectStatus(@Valid @RequestBody SubjectStatusRequest request) throws TestException {
        return testRequestService.getSubjectStatus(request);
    }

    @ApiOperation(value = API_TO_GET_TEST_USAGE)
    @PostMapping(value = {"/usage", "/service/usage"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TestUsageResponse getUserUsage(@Valid @RequestBody UserUsageRequest request) throws TestException {
        return testRequestService.getUserUsage(request);
    }

    @ApiOperation(value = API_TO_GET_RECENT_TEST_DATA)
    @PostMapping(value = {"/recent", "/service/recent"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RecentTestResponse getRecentTests(@Valid @RequestBody UserUsageRequest request) throws TestException {
        return testRequestService.getRecentTestsData(request);
    }



    @Autowired
    TestRepository testRepository;

    @Autowired
    TestMetadataService testMetadataService;

    @Autowired
    UserTestReportRepository userTestReportRepository;

    @Autowired
    UserTestOptsRecordRepository testOptsRecordRepository;

    @GetMapping("/service/get")
    public void get() throws TestException {

        List<UserTestReportEntity> allTestReport = userTestReportRepository.findAll();
        for(UserTestReportEntity report :allTestReport){
            TestEntity test = testRepository.findById(report.getTestId()).get();
            report.setSubDetails(test.getSubDetails());
        }
        userTestReportRepository.saveAll(allTestReport);

        List<UserTestOptsRecordEntity> testOptions = testOptsRecordRepository.findAll();
        for(UserTestOptsRecordEntity option :testOptions){
            TestEntity test = testRepository.findById(option.getTestId()).get();
            option.setSubDetails(test.getSubDetails());
        }
        testOptsRecordRepository.saveAll(testOptions);

//        List<TestEntity> allTests = testRepository.findAll();
//        List<StandardResponse> stnRes = testMetadataService.getStandardResponse();
//        for (TestEntity test : allTests) {
//            SubjectDetailsEntity subDetails = test.getSubDetails();
//            String grade = test.getSubDetails().getGradeLevel();
//            String subject = test.getSubDetails().getSubjectId();
//            if (subDetails.getTestType().equals(CT)) {
//                String chapterIdInStr = test.getSubDetails().getTestTypeKeyFields().get(CHAPTER_ID);
//                Long chapterId = Long.parseLong(chapterIdInStr);
//                for(StandardResponse standardRes : stnRes){
//                    if(!standardRes.getName().equals(grade)){
//                        continue;
//                    }
//                    for(SubjectResponse subRes : standardRes.getSubjects()){
//                        if(!subRes.getName().equals(subject)){
//                            continue;
//                        }
//                        for(ChapterResponse chapRes : subRes.getChapters()){
//                            if(chapRes.getId().equals(chapterId)){
//                                subDetails.setChapterName(chapRes.getChapterName());
//                                subDetails.setChapterPath(chapRes.getChapterpath());
//                                test.setSubDetails(subDetails);
//                                break;
//                            }
//                        }
//                    }
//                }
//            }else if(subDetails.getTestType().equals(CR)){
//                subDetails.setChapterName("");
//                subDetails.setChapterPath("");
//                test.setSubDetails(subDetails);
//            }
//        }
//        testRepository.saveAll(allTests);
    }
}
