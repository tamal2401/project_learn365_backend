package org.learn365.course.repository;


import org.learn365.modal.course.entity.Chapter;
import org.learn365.modal.course.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ChapterRepository  extends JpaRepository<Chapter,Long> {

    @Transactional
    @Modifying
    @Query(value = "update Chapter ch set ch.testid=:testid where ch.id=:chapterid")
    public void updateChapterTest(@Param("chapterid") Long chapterid, @Param("testid") String testid);

    public List<Chapter> findChaptersBySubject(Subject subject);

}
