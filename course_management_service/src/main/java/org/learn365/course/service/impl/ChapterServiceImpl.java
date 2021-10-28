package org.learn365.course.service.impl;

import org.learn365.course.repository.ChapterRepository;
import org.learn365.course.repository.ChapterVideoRepository;
import org.learn365.course.repository.SubjectRepository;
import org.learn365.course.service.ChapterService;
import org.learn365.course.service.CopySubjectToStudentProfile;
import org.learn365.exception.CoursePortFolioNotFoundException;
import org.learn365.modal.course.entity.Chapter;
import org.learn365.modal.course.entity.Subject;
import org.learn365.modal.course.response.ChapterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl
        implements ChapterService, CopySubjectToStudentProfile
{
    private static Logger log = LoggerFactory.getLogger(
            ChapterServiceImpl.class);
    private ChapterRepository chapterrepository;
    private ChapterVideoRepository chapterVideoRepository;
    private SubjectRepository subjectRepository;

    public ChapterServiceImpl(ChapterRepository chapterrepository,
                              ChapterVideoRepository chapterVideoRepository,
                              SubjectRepository subjectRepository)
    {
        this.chapterrepository = chapterrepository;
        this.chapterVideoRepository = chapterVideoRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public boolean updateTest(Long id, String testid)
    {
        try
        {
            chapterrepository.updateChapterTest(id, testid);
        } catch (Exception e)
        {
            log.error("Unable to update test for chapter :{} exception is :{} ",
                    id, e);
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<ChapterResponse> getChapterBySubject(Long id)
    {
        Optional<Subject> hasSubjectAvailable = subjectRepository.findById(id);
        Subject subject = hasSubjectAvailable.orElseThrow(
                () -> new CoursePortFolioNotFoundException(
                        "Subject is not available"));
        List<Chapter> chapters = chapterrepository.findChaptersBySubject(
                subject);
        return null;
    }

    @Override
    public boolean updateConceptTest(Long id, String testid)
    {
        try
        {
            chapterVideoRepository.updateChapterTest(id, testid);
        } catch (Exception e)
        {
            log.error(
                    "Unable to updateConceptTest test for chapter :{} exception is :{} ",
                    id, e);
        }
        return true;
    }
}
