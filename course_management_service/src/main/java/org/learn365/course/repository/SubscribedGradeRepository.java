package org.learn365.course.repository;

import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.entity.SubscribedGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface SubscribedGradeRepository extends JpaRepository<SubscribedGrade, Long> {

    @Transactional
    @Modifying
    @Query(value = "update SubscribedGrade s set s.timespent=:timespent,s.gradestatus=:videoStatus where s.id=:id and s.userId=:userid")
    public void updateStudentProgress(@Param("timespent") Long timespent, @Param("videoStatus") StudentProgress videoStatus, @Param("id") Long id, @Param("userid") Long userid);

    @Query(value = "select new org.learn365.course.repository.ProgressingFields(sg.timespent,sg.gradestatus) from SubscribedGrade sg where sg.id=:id and sg.userId=:userid")
    public Optional<ProgressingFields> findByIdAndUserId(@Param("id")Long id, @Param("userid")Long userId);
}
