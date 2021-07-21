package org.learn365.test.repository;

import org.learn365.test.entity.UserTestOptsRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface UserTestOptsRecordRepository extends JpaRepository<UserTestOptsRecordEntity, Long> {

    @Query(value = "select tr from UserTestOptsRecordEntity tr where tr.userId=:userId and tr.testId=:testId")
    List<UserTestOptsRecordEntity> findTestRecordByTestIdAndUserId(@Param("testId") Long testId, @Param("userId") Long usedId);
}
