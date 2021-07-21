package org.learn365.test.dao;

import org.learn365.test.entity.*;
import org.learn365.test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.learn365.test.util.TestUtil.*;

@Component
public class TestDetailsDaoImpl implements TestDetailsDao{

    @Autowired
    TestRepository testRepository;

    @Autowired
    UserTestOptsRecordRepository testOptsRecordRepository;

    @Autowired
    UserTestReportRepository userTestReportRepository;

    @Autowired
    OptionsRepository optionsRepository;

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    GradesRepository gradesRepository;

    @Override
    public TestEntity findTestData(Long testId) {
        Optional<TestEntity> testEntity = testRepository.findById(testId);
        return testEntity.orElseThrow(() -> {
            throw new RuntimeException("Invalid test id");
        });
    }

    @Override
    public TestEntity findTestData(String testCode) {
        Optional<TestEntity> testEntity = testRepository.findByTestName(testCode);
        return testEntity.orElseThrow(() -> {
            throw new RuntimeException("Invalid test code");
        });
    }

    @Override
    public Collection<TestEntity> findTestRecords(List<Long> testIds) {
        return testRepository.findAllById(testIds);
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
    public UserTestOptsRecordEntity saveUserTestRecord(UserTestOptsRecordEntity recordEntity) {
        return optionalExceptionWrapper(testOptsRecordRepository.save(recordEntity),
                "Error occurred while saving updated user test option records");
    }

    @Override
    public List<UserTestOptsRecordEntity> saveUserTestRecords(Collection<UserTestOptsRecordEntity> recordEntity) {
        return optionalExceptionWrapper(testOptsRecordRepository.saveAll(recordEntity),
                "Error occurred while saving updated user test option records");
    }

    @Override
    public List<UserTestOptsRecordEntity> findTestRecord(Long userId, Long testId) {
        return testOptsRecordRepository.findTestRecordByTestIdAndUserId(testId, userId);
    }

    @Override
    public UserTestReportEntity saveUserTestReport(UserTestReportEntity reportEntity) {
        return optionalExceptionWrapper(userTestReportRepository.save(reportEntity),
                "Error occurred while saving user test report");
    }

    @Override
    public List<OptionsEntity> saveOptionsEntities(Collection<OptionsEntity> optionEntitySet) {
        return optionalExceptionWrapper(optionsRepository.saveAll(optionEntitySet),
                "Error occurred while saving set of option entity");
    }

    @Override
    public QuestionEntity saveQuestionEntity(QuestionEntity questionEntity) {
        return optionalExceptionWrapper(questionsRepository.save(questionEntity),
                "Error occurred while saving question entity");
    }

    @Override
    public List<QuestionEntity> saveQuestionEntities(Collection<QuestionEntity> entities) {
        return optionalExceptionWrapper(questionsRepository.saveAll(entities),
                "Error occurred while saving list of questions.");
    }

    @Override
    public String getGradesFromMarks(Float marks) {
        return optionalExceptionWrapper(gradesRepository.getGradesFromMarks(marks),
                "Error occurred while finding grades based on test marks");
    }


}
