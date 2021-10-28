package org.learn365.test.util;

public class TestConstants {

    public static final String SEPARATOR = "\\";
    public static final String ERROR_MESSAGE_NO_FILE_SELECTED = "Error Message :: Please select a file to upload";
    public static final String MESSAGE = "message";
    public static final String ERROR_MESSAGE_WRONG_TYPE_OF_FILE_UPLOADED = "Error Message :: Wrong type of file is uploaded. Upload an xlsx file.";
    public static final String REDIRECT_UPLOAD_STATUS_URI = "redirect:uploadStatus";
    public static final String INDEX_PAGE = "index";
    public static final String REDIRECT_REPORT_URI = "redirect:report";
    public static final String FILE_UPLOAD_SUCCESSFUL_MESSAGE = "You successfully uploaded '%s', \n Generated Test Id : %s, Test Name : %s, Course Tabel update status with test id: %s";
    public static final String EXCEPTION_OCCURRED_PREFIX = "Exception occurred: {}";
    public static final String GRADE_NAME_PREFIX = "Grade-";
    public static final String CUSTOM_FILE = "customFile";
    public static final String METADATA = "metadata";
    public static final String PREINDEX_PAGE = "preIndex";
    public static final String UPLOAD_STATUS_PAGE = "uploadStatus";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_CORRECT_ANSWERS = "correctAnswers";
    public static final String COLUMN_ORDER = "order";
    public static final String COLUMN_MARKS = "marks";
    public static final String COLUMN_EXPLANATION_TEXT = "explanationText";
    public static final String COLUMN_EXPLANATION_VIDEO = "explanationVideo";
    public static final String COLUMN_ANSWER_OPTION_1 = "answerOption1";
    public static final String COLUMN_ANSWER_OPTION_2 = "answerOption2";
    public static final String COLUMN_ANSWER_OPTION_3 = "answerOption3";
    public static final String COLUMN_ANSWER_OPTION_4 = "answerOption4";
    public static final String ERROR_MESSAGE_EMPTY_RECORDS_PROVIDED = "Empty records provided in the uploaded excel.";
    public static final String ERROR_MESSAGE_NULL_WORKBOOK = "Excel workbook is null";
    public static final String ERROR_MESSAGE_ERROR_WHILE_HANDLING_EXCEL = "Error occurred while handling excel sheet: ";
    public static final String UNEXPECTED_CELL_VALUE_TYPE = "Unexpected cell value identified: ";
    public static final String ERROR_MESSAGE_PREFIX = "Error Messages :: ";
    public static final String BINDING_VIOLATION_ERROR_MESSAGE = "Binding violation Message";
    public static final String API_MESSAGE_FOR_GET_CHAPTER_TESTS = "This api to get all available chapter tests based on userId and testId";
    public static final String API_MESSAGE_FOR_GET_CHAPTER_TEST_BY_ID = "This api to get test details based on test id";
    public static final String API_MESSAGE_FOR_GENERATE_CHAPTER_SCORE_DATA = "This api to generate test score based on the test metadata send after the test is taken by the user";
    public static final String API_MESSAGE_TO_POST_TEST_DATA_TO_DB = "This api to post test data to mongoDb";
    public static final String API_TO_CHANGE_TEST_STATUS = "This api to change the test's active and delete status based on the testId and isActive and isDeleted flag value";
    public static final String API_TO_GET_TEST_HISTORY = "This api to get test history data based on user id and testId/testCode";
    public static final String API_TO_GET_TEST_REPORT = "This api to get test report data based on user id, test type, grade id and date limit";
    public static final String API_TO_GET_TEST_STATUS = "This api to get test status data based on user id and subject id";
    public static final String API_TO_GET_TEST_USAGE = "This api to get test status data based on user id and subject id";
    public static final String API_TO_GET_RECENT_TEST_DATA = "This api to get recent test data";
    public static final String TEST_CONTROLLER_API_MESSAGE = "Test Controller to main all the test records and test scores for users.";
    public static final String ERROR_MESSAGE_INTERNAL_ERROR_OCCURRED_ROLLED_BACK_TRANSACTION = "Internal Error occurred, RolledBack transaction";
    public static final String ERROR_MESSAGE_TEST_ALREADY_TAKEN_BY_USER = "This test is already take by the user, Test_Id: %s, User_Id: %s";
    public static final String ROLLING_BACK_TRANSACTIONS = "Rolling back transactions";
    public static final String ERROR_MESSAGE_MULTIPLE_CORRECT_OPTION_PROVIDED = "Multiple correct option provided";
    public static final String ERROR_MESSAGE_CORRECT_OPTION_ID_IS_NOT_PROVIDED = "Correct option id is not provided";
    public static final String ERROR_MESSAGE_NO_RECORDS_FOUND_FOR_THE_LIST_OF_TEST_ID = "No records found for the list of testId's";
    public static final String ERROR_MESSAGE_NO_TEST_HISTORY_EXISTS = "No test history exists for given userId and tesId/testCode";
    public static final String ERROR_MESSAGE_INVALID_TEST_CODE = "Please provide a Test Code or Test Id";
    public static final String ERROR_MESSAGE_NO_TEST_RECORD_EXISTS = "No record exists for provided test code or test id";
    public static final String ERROR_MESSAGE_ERROR_WHILE_GETTING_GRADES_FROM_DB = "Error occurred while getting grades from DB";

    /** Test Types */
    public static final String CONCEPT_REFRESHER = "CR";
    public static final String CHAPTER_MAIN_TEST = "CT";

    /** Test types */
    public static final String CR = "CR";
    public static final String CT = "CT";
    public static final String ALL = "ALL";

    /**  Mongo Key's for collections */
    public static final String KEY_VIDEO_ID = "conceptVideoId";
    public static final String KEY_CHAPTER_ID = "chapterId";

    /** Mongo Collection names */
    public static final String ERROR_MESSAGE_INVALID_TEST_ID = "Invalid test id";
    public static final String COLLECTION_TESTS = "tests";
    public static final String COLLECTION_USER_TEST_REPORT_ENTITY = "UserTestReport";
    public static final String COLLECTION_USER_TEST_OPTIONS_RECORD_ENTITY = "UserTestOptionsRecord";
    public static final String COLLECTION_GRADES = "grades";

    /** Key identifiers for mongo query params */
    public static final String USER_ID = "userId";
    public static final String SUBJECT_ID = "subjectId";
    public static final String GRADE_LEVEL = "gradeLevel";
    public static final String CREATED_AT = "createdAt";
    public static final String TEST_TYPE = "testType";
    public static final String CHAPTER_ID = "chapterId";
    public static final String VIDEO_ID = "conceptVideoId";

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String UTC = "UTC";
}
