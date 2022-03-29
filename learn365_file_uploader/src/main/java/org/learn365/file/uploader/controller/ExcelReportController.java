package org.learn365.file.uploader.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.learn365.file.uploader.model.GeneratedTestEntityDetails;
import org.learn365.file.uploader.model.TestException;
import org.learn365.file.uploader.model.TestNameMetadataRequest;
import org.learn365.file.uploader.service.TestMetadataService;
import org.learn365.file.uploader.service.TestReportParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.File;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;

import static org.learn365.file.uploader.constants.TestConstants.*;

@RestController
@RequestMapping("/v1/excel/")
public class ExcelReportController {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Value("${file.local.path}")
    String UPLOADED_FOLDER;

    @Autowired
    TestReportParserService reportParserService;

    @Autowired
    Validator validator;

    @Autowired
    TestMetadataService testMetadataService;

    /**
     * Return PreIndex page to provide Subject and Grade details
     *
     * @param model
     * @return
     */
    @GetMapping(path = "/report")
    public ModelAndView home(Model model) throws MultipartException {
        try{
            initializeModelForIndexPage(model);
        }catch (Exception e){
            log.error(EXCEPTION_OCCURRED_PREFIX, ExceptionUtils.getStackTrace(e));
            String exMsg = String.format("Exception Type: %s, Exception Message: %s", e.getClass().getName(), e.getMessage());
            throw new MultipartException(exMsg);
        }
        model.addAttribute("testTypeList", Arrays.asList(CONCEPT_REFRESHER, CHAPTER_MAIN_TEST));
        model.addAttribute(METADATA, new TestNameMetadataRequest());
        return new ModelAndView(PREINDEX_PAGE);
    }

    /**
     * Return Index page for processing page to provide Chapter and Module details
     * against provided subject and grade details in PreIndex page
     *
     * @param model
     * @param metadata
     * @param redirectAttributes
     * @return
     */
    @PostMapping(path = "/processing")
    public ModelAndView process(Model model,
                                @ModelAttribute(METADATA) TestNameMetadataRequest metadata,
                                RedirectAttributes redirectAttributes) {

        if (StringUtils.isBlank(metadata.getGradeName()) || StringUtils.isBlank(metadata.getSubjectName()) || StringUtils.isBlank(metadata.getTestType())) {
            return new ModelAndView(REDIRECT_REPORT_URI);
        }
        // TODO get chapter and module information from course-service API
        switch (metadata.getTestType()) {
            case CONCEPT_REFRESHER:
                model.addAttribute("videoList", testMetadataService.getVideoList(metadata.getGradeName(), metadata.getSubjectName()));
                break;
            case CHAPTER_MAIN_TEST:
                model.addAttribute("chapterList", testMetadataService.getChapterList(metadata.getGradeName(), metadata.getSubjectName()));
                break;
        }
        model.addAttribute("testType", metadata.getTestType());
        model.addAttribute(METADATA, metadata);
        return new ModelAndView(INDEX_PAGE);
    }

    /**
     * Get Test Data excel file as Multipart adn get Metadata for the test file.
     * Parse the excel file and create test entoty to save in MongoDb
     *
     * @param file
     * @param metadata
     * @param redirectAttributes
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping(path = "/upload")
    public ModelAndView upload(@RequestParam(CUSTOM_FILE) MultipartFile file,
                               @ModelAttribute(METADATA) TestNameMetadataRequest metadata,
                               RedirectAttributes redirectAttributes,
                               BindingResult bindingResult,
                               Model model) throws MultipartException {

        if (file.isEmpty()) {
            model.addAttribute(MESSAGE, ERROR_MESSAGE_NO_FILE_SELECTED);
            return new ModelAndView(UPLOAD_STATUS_PAGE);
        } else {
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
                model.addAttribute(MESSAGE, ERROR_MESSAGE_WRONG_TYPE_OF_FILE_UPLOADED);
                return new ModelAndView(UPLOAD_STATUS_PAGE);
            }
        }

        try {

            Set<ConstraintViolation<TestNameMetadataRequest>> violations = validator.validate(metadata);

            if (!violations.isEmpty()) {
                violations.forEach(each -> bindingResult.addError(new FieldError(each.getRootBean().getClass().getName(), each.getPropertyPath().iterator().next().getName(), each.getMessage())));
                initializeModelForIndexPage(model);
                return new ModelAndView(INDEX_PAGE);
            }

            byte[] bytes = file.getBytes();
            File tmpFile = new File(UPLOADED_FOLDER);
            if (!tmpFile.exists()) {
                if (!tmpFile.mkdirs()) {
                    throw new TestException("Not able to create path in the system: " + UPLOADED_FOLDER);
                }
            }
            String filePath = UPLOADED_FOLDER + SEPARATOR + file.getOriginalFilename();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            GeneratedTestEntityDetails testData = reportParserService.parseExcel(filePath, metadata);

            String responseMsg = String.format(FILE_UPLOAD_SUCCESSFUL_MESSAGE,
                    file.getOriginalFilename(), testData.getTestId(), testData.getTestName(), testData.getTestId());
            model.addAttribute(MESSAGE, responseMsg);

        } catch (Exception e) {
            log.error(EXCEPTION_OCCURRED_PREFIX, ExceptionUtils.getStackTrace(e));
            String exMsg = String.format("Exception Type: %s, Exception Message: %s", e.getClass().getName(), e.getMessage());
            throw new MultipartException(exMsg);
        }
        return new ModelAndView(UPLOAD_STATUS_PAGE);
    }

    private void initializeModelForIndexPage(Model model) throws Exception {
        // TODO Read data from either config or from course-service to get the GRADE and SUBJECT list
        model.addAttribute("subList", testMetadataService.getSubjectSet());
        model.addAttribute("gradeList", testMetadataService.gradeSet());
    }
}
