package org.learn365.course.repository;

import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.entity.SubscribedGrade;
import org.learn365.modal.course.entity.SubscribedSubject;
import org.learn365.modal.course.response.SubscribedSubjectReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SubscribedSubjectRepository extends JpaRepository<SubscribedSubject,Long> {

    @Transactional
    @Modifying
    @Query(value = "update SubscribedSubject s set s.teststatus=:teststatus,s.timespent=:timespent,s.subjectStatus=:videoStatus " +
            "where s.id=:id and s.userId=:userid")
    public void updateStudentProgress(@Param("teststatus") boolean teststatus, @Param("timespent") Long timespent, @Param("videoStatus") StudentProgress videoStatus, @Param("id") Long id, @Param("userid") Long userid);

    @Query(value = "select new org.learn365.course.repository.ProgressingFields(ss.timespent,ss.subjectStatus,ss.teststatus) " +
            "from SubscribedSubject ss where ss.id=:id and ss.userId=:userid")
    public Optional<ProgressingFields> findByIdAndUserId(@Param("id") Long id, @Param("userid") Long userId);


    @Query(value = "select new org.learn365.modal.course.response.SubscribedSubjectReport(s.id,s.name,sps.timespent) FROM SubscribedSubject sps " +
            "INNER JOIN Subject s ON sps.subjectid=s.id WHERE sps.userId=:userid AND sps.subscribedGrade.gradeId=(select id from Standard where name=:gradename)")
    public List<SubscribedSubjectReport> findSubscribedSubjectByGradeIdAndUserId(@Param("gradename") String gradename, @Param("userid") Long userId);

    @Query(value = "SELECT g.grade_name,subj.subjectid,subj.subject_name,subj.videoid,subj.chapter_video_url,subj.video_thumb_nail,subj.chapterid,subj.chapter_name,subj.chapter_path_url FROM grade g " +
            "INNER JOIN (SELECT sub.fk_standard_id AS gradeid , sub.id AS subjectid,sub.subject_name,chpter.videoid,chpter.chapter_video_url,chpter.video_thumb_nail,chpter.chapterid ,chpter.chapter_name,chpter.chapter_path_url FROM subject AS sub " +
            "INNER JOIN (SELECT cvid.id AS videoid,cvid.chapter_video_url,cvid.video_thumb_nail,ch.id AS chapterid,ch.chapter_name,ch.chapter_path_url, ch.fk_subject_id AS fk_subjectid FROM chapter ch " +
            "INNER JOIN (SELECT id,chapter_video_url,video_thumb_nail,fk_chapter_id FROM chapter_video WHERE id IN (:recentvideoId)) AS cvid ON cvid.fk_chapter_id=ch.id) AS chpter ON chpter.fk_subjectid=sub.id) AS subj ON subj.gradeid=g.id;",nativeQuery = true)
    public List<Tuple> findRecentVideos(@Param("recentvideoId") List<Long> videoIDs);
}

