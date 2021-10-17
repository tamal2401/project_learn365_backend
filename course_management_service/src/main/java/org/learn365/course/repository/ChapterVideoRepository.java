package org.learn365.course.repository;

import org.learn365.modal.course.entity.ChapterVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.List;

public interface ChapterVideoRepository extends JpaRepository<ChapterVideo, Long> {

    @Transactional
    @Modifying
    @Query(value = "update ChapterVideo chv set chv.testid=:testid where chv.id=:chapterid")
    public void updateChapterTest(@Param("chapterid") Long chapterid, @Param("testid") String testid);

    @Query(value = "SELECT chapter.gradename,chapter.subjectname,chapter.chapter_path_url,chv.video_thumb_nail,chv.chapter_video_url,chapter.chapter_name FROM chapter_video chv " +
            "INNER JOIN (SELECT subje.gradename,subje.subjectname,ch.chapter_name,ch.chapter_path_url,ch.id AS chapterid FROM chapter ch " +
            "INNER JOIN (SELECT grade.grade_name AS gradename,sub.subject_name AS subjectname,sub.id AS subjectid FROM subject sub " +
            "INNER JOIN grade grade ON sub.fk_standard_id=grade.id WHERE sub.is_for_trial=TRUE AND grade.grade_name=:grade) subje ON ch.fk_subject_id=subje.subjectid " +
            "WHERE ch.is_for_trial=TRUE) AS chapter ON chapter.chapterid=chv.fk_chapter_id;", nativeQuery = true)
    public List<Tuple> findTrialVideo(@Param("grade") String gradeName);
}
