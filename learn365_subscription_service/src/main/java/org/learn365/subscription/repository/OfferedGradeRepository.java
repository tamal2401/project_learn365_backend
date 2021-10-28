package org.learn365.subscription.repository;

import org.learn365.modal.subscription.entity.OfferedGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OfferedGradeRepository  extends JpaRepository<OfferedGrade,Long> {

    @Query(value = "update OfferedGrade og set og.active=false where og.gradeId=:gradeId")
    public Optional<Boolean> deleteByGradeId(@Param("gradeId") Long gradeId);

    Optional<OfferedGrade> findOfferedGradeByGradeName(String gradename);
}
