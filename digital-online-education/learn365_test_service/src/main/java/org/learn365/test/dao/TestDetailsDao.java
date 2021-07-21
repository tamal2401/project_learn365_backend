package org.learn365.test.dao;

import org.learn365.test.entity.*;

import java.util.Collection;
import java.util.List;

public interface TestDetailsDao {

    TestEntity findTestData(Long testId);

    TestEntity findTestData(String testCode);

    Collection<TestEntity> findTestRecords(List<Long> testIds);

    UserTestOptsRecordEntity saveUserTestRecord(UserTestOptsRecordEntity recordEntity);

    List<UserTestOptsRecordEntity> saveUserTestRecords(Collection<UserTestOptsRecordEntity> recordEntity);

    UserTestReportEntity saveUserTestReport(UserTestReportEntity reportEntity);

    List<UserTestOptsRecordEntity> findTestRecord(Long userId, Long testId);

    List<OptionsEntity> saveOptionsEntities(Collection<OptionsEntity> optionEntitySet);

    QuestionEntity saveQuestionEntity(QuestionEntity questionEntity);

    List<QuestionEntity> saveQuestionEntities(Collection<QuestionEntity> entities);

    TestEntity saveTestData(TestEntity testEntity);

    List<TestEntity> saveTestDatas(List<TestEntity> updatedRecords);

    String getGradesFromMarks(Float marks);
}
