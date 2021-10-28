package org.learn365.test.repository;

import org.learn365.modal.test.entity.UserTestOptsRecordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestOptsRecordRepository extends MongoRepository<UserTestOptsRecordEntity, String> {

    @Query(value = "{ userId : ?0, testId : ?1}")
    UserTestOptsRecordEntity findTestRecordByUserIdAndTestId(String usedId, String testId);
}
