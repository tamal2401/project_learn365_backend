package org.learn365.test.repository;

import org.learn365.modal.test.entity.UserTestReportEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestReportRepository extends MongoRepository<UserTestReportEntity, String> {

    @Query(value = "{ userId : ?0, testId : ?1}")
    UserTestReportEntity findTestReportByUserIdAndTestId(String usedId, String testId);
}
