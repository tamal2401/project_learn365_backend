package org.learn365.course.repository;

import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.entity.SubscribedChapter;
import org.learn365.modal.course.entity.SubscribedGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface SubscribedChapterRepository
        extends JpaRepository<SubscribedChapter, Long>
{

    @Transactional
    @Modifying
    @Query(value = "update SubscribedChapter s set s.teststatus=:teststatus,s.timespent=:timespent,s.chapterStatus=:videoStatus where s.id=:id and s.userId=:userid")
    void updateStudentProgress(@Param("teststatus") boolean teststatus,
                               @Param("timespent") Long timespent,
                               @Param("videoStatus") StudentProgress videoStatus,
                               @Param("id") Long id,
                               @Param("userid") Long userid);

    @Query(value = "select new org.learn365.course.repository.ProgressingFields(sc.timespent,sc.chapterStatus,sc.teststatus) from SubscribedChapter sc where sc.id=:id and sc.userId=:userid")
    Optional<ProgressingFields> findByIdAndUserId(@Param("id") Long id,
                                                  @Param("userid") Long userId);

    @Transactional
    @Modifying
    @Query(value = "update SubscribedChapter sc set sc.chapterStatus=:chapterstatus where sc.id=:id ")
    void updateChapterProgress(
            @Param("chapterstatus") StudentProgress studentProgress,
            @Param("id") Long id);


}
