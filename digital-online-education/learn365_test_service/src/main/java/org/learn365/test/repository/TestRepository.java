package org.learn365.test.repository;

import org.learn365.test.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {

    @Query(value = "select te from TestEntity te where te.testCode=:testName")
    Optional<TestEntity> findByTestName(@Param("testName") String testName);
}
