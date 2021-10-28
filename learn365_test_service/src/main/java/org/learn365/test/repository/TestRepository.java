package org.learn365.test.repository;

import org.learn365.modal.test.entity.TestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends MongoRepository<TestEntity, String> {

    @Query(value = "{ 'testCode' : ?0 }")
    TestEntity findByTestCode(String testName);

    @Query(value = "{ 'subDetails.testType' : ?0 }")
    List<TestEntity> findNumberOfTestByType(String testType);
}
