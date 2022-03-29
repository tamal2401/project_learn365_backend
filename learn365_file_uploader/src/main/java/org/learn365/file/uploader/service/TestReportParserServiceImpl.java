package org.learn365.file.uploader.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.learn365.file.uploader.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;

import static org.learn365.file.uploader.constants.TestConstants.*;
import static org.learn365.file.uploader.util.TestUtil.generateRandomId;

@Service
public class TestReportParserServiceImpl implements TestReportParserService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Value("${cloud.service.test}")
    String testServiceAddress;

    @Autowired
    @Qualifier("customTemplate")
    RestTemplate restTemplate;

    @Autowired
    TestMetadataService testMetadataService;

    @Override
    public GeneratedTestEntityDetails parseExcel(String filePath, TestNameMetadataRequest metadata) throws TestException {
        String randNum = generateRandomId();
        String testName = String.format("%s_%s_%s_%s",
                metadata.getGradeName(),
                metadata.getSubjectName(),
                metadata.getTestType(),
                randNum);

        FileInputStream fileIp = null;
        Workbook workbook = null;
        GeneratedTestEntityDetails response = new GeneratedTestEntityDetails();
        try {
            fileIp = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileIp);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();

            Map<String, Integer> rowNumMap = new TreeMap<>();
            List<ExcelMetaData> excelMetaDataList = new ArrayList<>();

            while (itr.hasNext()) {
                Row currentRow = itr.next();
                if(isRowEmpty(currentRow)){
                   continue;
                }
                if (currentRow.getRowNum() == 0) {
                    for (Cell cell : currentRow) {
                        rowNumMap.put(cell.getStringCellValue(), cell.getColumnIndex());
                    }
                    continue;
                }
                ExcelMetaData excelMetaData = new ExcelMetaData();

                String textAsString = getCellValue(currentRow.getCell(rowNumMap.get(COLUMN_TEXT), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                StringExceptionWrapper(textAsString, "Question can not be null in uploaded excel");
                excelMetaData.setText(textAsString);

                String correctAnswersAsString = getCellValue(currentRow.getCell(rowNumMap.get(COLUMN_CORRECT_ANSWERS), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                StringExceptionWrapper(correctAnswersAsString, "Correct answer can not be null in excel");
                excelMetaData.setCorrectAnswer(parseInteger(correctAnswersAsString));

                String orderAsString = getCellValue(currentRow.getCell(rowNumMap.get(COLUMN_ORDER), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                StringExceptionWrapper(orderAsString, "Question order can not be null");
                excelMetaData.setOrder(parseInteger(orderAsString));

                String marksAsString = getCellValue(currentRow.getCell(rowNumMap.get(COLUMN_MARKS), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                StringExceptionWrapper(marksAsString, "Question marks can not be null in uploaded excel");
                excelMetaData.setMarks(parseInteger(marksAsString));

                String explanationTextAsString = getCellValue(currentRow.getCell(rowNumMap.get(COLUMN_EXPLANATION_TEXT), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                StringExceptionWrapper(explanationTextAsString, "Explanation String can not be null in uploaded excel");
                excelMetaData.setExplanationText(explanationTextAsString);

                String explanationVideoAsString = getCellValue(currentRow.getCell(rowNumMap.get(COLUMN_EXPLANATION_VIDEO), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                excelMetaData.setExplanationVideoName(explanationVideoAsString);

                String answerOption1AsString = getCellValue(currentRow.getCell(rowNumMap.get(COLUMN_ANSWER_OPTION_1), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                StringExceptionWrapper(answerOption1AsString, "Option 1 can not be null in uploaded excel");
                excelMetaData.setOpt1(answerOption1AsString);

                String answerOption2AsString = getCellValue(currentRow.getCell(rowNumMap.get(COLUMN_ANSWER_OPTION_2), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                StringExceptionWrapper(answerOption2AsString, "Option 2 can not be null in uploaded excel");
                excelMetaData.setOpt2(answerOption2AsString);

                String answerOption3AsString = getCellValue(currentRow.getCell(rowNumMap.get(COLUMN_ANSWER_OPTION_3), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                StringExceptionWrapper(answerOption3AsString, "Option 3 can not be null in uploaded excel");
                excelMetaData.setOpt3(answerOption3AsString);

                String answerOption4AsString = getCellValue(currentRow.getCell(rowNumMap.get(COLUMN_ANSWER_OPTION_4), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
                StringExceptionWrapper(answerOption4AsString, "Option 4 can not be null in uploaded excel");
                excelMetaData.setOpt4(answerOption4AsString);

                excelMetaDataList.add(excelMetaData);
            }

            if (excelMetaDataList.isEmpty()) {
                throw new TestException(ERROR_MESSAGE_EMPTY_RECORDS_PROVIDED);
            }

            TestDetailsMetadataRequest detailsMetadataRequest = new TestDetailsMetadataRequest();
            detailsMetadataRequest.setTestCode(testName);
            detailsMetadataRequest.setSubjectId(metadata.getSubjectName());
            detailsMetadataRequest.setIsActive(true);
            detailsMetadataRequest.setGradeCode(metadata.getGradeName());
            detailsMetadataRequest.setTestType(metadata.getTestType());

            HashMap<String, String> map = new HashMap<>();
            if (StringUtils.equals(metadata.getTestType(), CR)) {
                map.put(KEY_VIDEO_ID, metadata.getTestSelectorKey());
            } else if (StringUtils.equals(metadata.getTestType(), CT)) {
                map.put(KEY_CHAPTER_ID, metadata.getTestSelectorKey());
                resolveChapterDetails(detailsMetadataRequest, metadata.getTestSelectorKey());
            }
            detailsMetadataRequest.setTestKeyFields(map);

            List<QuestionMetadata> questionList = excelMetaDataList.stream()
                    .map(each -> {
                        QuestionMetadata questionMetadata = new QuestionMetadata();
                        questionMetadata.setQuestion(each.getText());
                        questionMetadata.setQuestionOrder(each.getOrder());
                        questionMetadata.setMarks(each.getMarks());
                        questionMetadata.setExplanationText(each.getExplanationText());
                        questionMetadata.setExplanationVideoId(each.getExplanationVideoName());

                        Integer correctAns = each.getCorrectAnswer();
                        List<OptionMetadata> optList = new ArrayList<>();
                        optList.add(buildOptionMetadata(correctAns, each.getOpt1(), 1));
                        optList.add(buildOptionMetadata(correctAns, each.getOpt2(), 2));
                        optList.add(buildOptionMetadata(correctAns, each.getOpt3(), 3));
                        optList.add(buildOptionMetadata(correctAns, each.getOpt4(), 4));

                        questionMetadata.setOptions(optList);

                        return questionMetadata;
                    }).collect(Collectors.toList());

            detailsMetadataRequest.setTestQuestions(questionList);

            List<TestDetailsMetadataRequest> requestQuestionsList = Collections.singletonList(detailsMetadataRequest);
            
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(testServiceAddress+"/v1/test/service/create").build();
            HttpEntity<List<TestDetailsMetadataRequest>> request = new HttpEntity<>(requestQuestionsList);
            ResponseEntity<CreatedTestResponse> entity = restTemplate.exchange(builder.toUri(),
                    HttpMethod.PUT,
                    request,
                    new ParameterizedTypeReference<CreatedTestResponse>() {
            });

            if (entity.getStatusCodeValue() != 200) {
                throw new TestException("Test service not responding");
            }

            CreatedTestResponse testRes = entity.getBody();
            if (null == testRes) {
                throw new TestException("Test service returned null response");
            }

            if(!testRes.getErrorList().isEmpty()){
                TestError testError = testRes.getErrorList().stream().findFirst().get();
                throw new TestException("Exception occurred while creating test : "+testError.getCode()+". exception Message: "+testError.getErrorMsg());
            }
            response.setCourseTableUpdatedStatus(true);
            response.setTestId(testRes.getTestMetadataList().stream().findFirst().get().getTestId());
            response.setTestName(testRes.getTestMetadataList().stream().findFirst().get().getTestCode());
        } catch (Throwable ex) {
            log.error(ex.getMessage());
            throw new TestException(ex.getMessage());
        } finally {
            try {
                assert workbook != null;
                workbook.close();
                fileIp.close();
            } catch (IOException e) {
                log.error(ERROR_MESSAGE_ERROR_WHILE_HANDLING_EXCEL + e.getMessage());
            }
            removeUploadedFile(filePath);
        }
        return response;
    }

    private void resolveChapterDetails(TestDetailsMetadataRequest detailsMetadataRequest, String chapterIdInStr) throws TestException {
        List<StandardResponse> standardResponses = testMetadataService.getStandardResponse();

        Long chapterId = Long.parseLong(chapterIdInStr);
        for(StandardResponse standardRes : standardResponses){
            if(!standardRes.getName().equals(detailsMetadataRequest.getGradeCode())){
                continue;
            }
            for(SubjectResponse subRes : standardRes.getSubjects()){
                if(!subRes.getName().equals(detailsMetadataRequest.getSubjectId())){
                    continue;
                }
                for(ChapterResponse chapRes : subRes.getChapters()){
                    if(chapRes.getId().equals(chapterId)){
                        detailsMetadataRequest.setChapterName(chapRes.getChapterName());
                        detailsMetadataRequest.setChapterPath(chapRes.getChapterpath());
                        break;
                    }
                }
            }
        }
    }

    private void removeUploadedFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            boolean isRemoved = file.delete();
            if (isRemoved) {
                log.info(UPLOADED_FILE_HAS_BEEN_REMOVED_AFTER_PROCESSING);
            }
        }
    }

    private Integer parseInteger(String value) {
        return (int) Double.parseDouble(value);
    }

    private OptionMetadata buildOptionMetadata(int correctAns, String option, int i) {
        OptionMetadata opt = new OptionMetadata();
        opt.setOptionDesc(option);
        opt.setOptionSeq(i);
        opt.setIsCorrect(correctAns == i);
        return opt;
    }

    private String getCellValue(Cell cellValue) {
        Object val = null;
        if (cellValue != null) {
            switch (cellValue.getCellType()) {
                case BOOLEAN:
                    val = cellValue.getBooleanCellValue();
                    break;
                case NUMERIC:
                    val = cellValue.getNumericCellValue();
                    break;
                case STRING:
                    val = cellValue.getStringCellValue();
                    break;
                case BLANK:
                    break;
                case ERROR:
                    val = cellValue.getErrorCellValue();
                    break;
                case FORMULA:
                    break;
                default:
                    throw new IllegalStateException(UNEXPECTED_CELL_VALUE_TYPE + cellValue.getCellType());
            }
            return String.valueOf(val);
        }
        throw new NullPointerException();
    }

    private <T> T optionalExceptionWrapper(T value, String msg) {
        Optional<T> result = Optional.of(value);

        return result.<RuntimeException>orElseThrow(() -> {
            throw new RuntimeException(msg);
        });
    }

    private static boolean isRowEmpty(Row row) {
        boolean isEmpty = true;
        DataFormatter dataFormatter = new DataFormatter();

        if (row != null) {
            for (Cell cell : row) {
                if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
                    isEmpty = false;
                    break;
                }
            }
        }

        return isEmpty;
    }

    private <T> T StringExceptionWrapper(T value, String msg) {
        Optional<T> result = Optional.of(value);

        return result.map(val -> StringUtils.isBlank((String) val) ? null : val).<RuntimeException>orElseThrow(() -> {
            throw new RuntimeException(msg);
        });
    }
}
