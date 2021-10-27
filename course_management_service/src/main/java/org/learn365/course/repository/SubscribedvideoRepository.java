package org.learn365.course.repository;

import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.TestResult;
import org.learn365.modal.course.entity.SubscribedChapter;
import org.learn365.modal.course.entity.Subscribedvideo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SubscribedvideoRepository
        extends JpaRepository<Subscribedvideo, Long>
{

    @Transactional
    @Modifying
    @Query(value = "update Subscribedvideo s set s.testStatus=:teststatus,s.timespent=:timespent,s.videoStatus=:videoStatus,s.updatedAt=:updateat where s.id=:id and s.userId=:userid")
    public void updateStudentProgress(@Param("teststatus") boolean teststatus,
                                      @Param("timespent") Long timespent,
                                      @Param("videoStatus") StudentProgress videoStatus,
                                      @Param("id") Long id,
                                      @Param("userid") Long userid,
                                      @Param("updateat") LocalDateTime updatetime);

    @Query(value = "select new org.learn365.course.repository.ProgressingFields(sv.timespent,sv.videoStatus,sv.testStatus) from Subscribedvideo sv where sv.id=:id and sv.userId=:userid")
    public Optional<ProgressingFields> findByIdAndUserId(@Param("id") Long id,
                                                         @Param("userid") Long userId);

    @Query(value = "select sv.videoId from Subscribedvideo sv where sv.userId=:userId order by sv.updatedAt DESC")
    public List<Long> findvideoIdForUser(@Param("userId") Long userid,
                                         Pageable pageable);

    @Query(value = "select sv.videoId from Subscribedvideo sv where sv.userId=:userId order by sv.timespent DESC")
    public List<Long> findvideoIdByTimeSpent(@Param("userId") Long userid,
                                             Pageable pageable);

    @Query(value = "select sv.videoId from Subscribedvideo sv where sv.userId=:userId and sv.testResult=:testresult  order by sv.timespent DESC")
    public List<Long> findvideoIdBytestStatus(@Param("userId") Long userid,
                                              @Param("testresult") TestResult testResult,
                                              Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update Subscribedvideo s set s.testResult=:testResult where s.videoId=:videoId and s.userId=:userId")
    void updateTestStatus(@Param("testResult") TestResult testResult,
                          @Param("userId") Long userId,
                          @Param("videoId") Long videoId);

    @Query(value = "select sv.subscribedChapter.id from Subscribedvideo sv where sv.id=:id")
    Long findSubscribedChapterById(@Param("id") Long id);
}
