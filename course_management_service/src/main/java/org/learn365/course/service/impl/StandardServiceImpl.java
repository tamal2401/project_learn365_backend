package org.learn365.course.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.learn365.course.repository.StandardRepository;
import org.learn365.course.service.StandardService;
import org.learn365.course.service.Transformer;
import org.learn365.exception.CoursePortFolioNotFoundException;
import org.learn365.modal.course.entity.Chapter;
import org.learn365.modal.course.entity.ChapterVideo;
import org.learn365.modal.course.entity.Standard;
import org.learn365.modal.course.entity.Subject;
import org.learn365.modal.course.request.ChapterRequest;
import org.learn365.modal.course.request.ChapterVideoRequest;
import org.learn365.modal.course.request.StandardRequest;
import org.learn365.modal.course.request.SubjectRequest;
import org.learn365.modal.course.response.ChapterResponse;
import org.learn365.modal.course.response.ChapterVideoResponse;
import org.learn365.modal.course.response.StandardResponse;
import org.learn365.modal.course.response.SubjectResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StandardServiceImpl implements StandardService, Transformer
{
    private static Logger log = LoggerFactory.getLogger(
            StandardServiceImpl.class);

    private StandardRepository standardRepository;

    public StandardServiceImpl(StandardRepository standardRepository)
    {
        this.standardRepository = standardRepository;
    }

    @Override
    public List<StandardResponse> getAllAvailableCourses()
    {
        List<Standard> standard = new ArrayList<>();
        try
        {
            standard = standardRepository.findAll();
        } catch (Exception e)
        {
            log.error(
                    "Not able to fetch course details exception is {}", e);
            throw new CoursePortFolioNotFoundException(
                    "CourseDetails are not configured correctly check in database");
        }
        if (CollectionUtils.isEmpty(standard))
        {
            log.info("No course detail configured in the database");
            throw new CoursePortFolioNotFoundException(
                    "CourseDetails are not configured correctly check in database");
        }
        // Add subscription info
        return transformToStandard(standard);
    }

    @Override
    public StandardResponse getCourseByGrade(String grade)
    {
        Optional<Standard> isstandard = Optional.empty();
        try
        {
            isstandard = standardRepository.findByName(grade);
        } catch (Exception e)
        {
            log.error(
                    "Not able to fetch course details : {} and exception is {}",
                    grade, e);
            throw new CoursePortFolioNotFoundException(
                    "CourseDetails are not configured correctly check in database");
        }
        Standard standard = isstandard.orElseThrow(() ->
        {
            log.info(
                    "CourseDetails are not configured correctly check in database");
            CoursePortFolioNotFoundException coursenotfoundex = new CoursePortFolioNotFoundException(
                    "CourseDetails are not configured correctly check in database");
            return coursenotfoundex;
        });

        // Add subscription info
        return transformToStandard(
                Collections.singletonList(standard)).get(0);
    }

    @Transactional
    @Override
    public StandardResponse addCourse(StandardRequest standardrequest)
    {
        if (ObjectUtils.isEmpty(standardrequest))
        {
            log.info(
                    "Invalid request to add course Please check add valid request");
            throw new IllegalArgumentException(
                    "Standard request should not be null");
        }
        Standard standard = new Standard();
        BeanUtils.copyProperties(standardrequest, standard);
        standard.setSubjects(
                transformRequestSubject(standardrequest.getSubjects(),
                        standard));
        Standard std = null;
        try
        {
            std = standardRepository.save(standard);
        } catch (Exception e)
        {
            log.error("Unable to add course in portfolio exception is : {}", e);
            throw new CoursePortFolioNotFoundException(
                    "Unable to save the record because of exception: {}"+
                    e.getMessage());
        }
        return transformToStandard(Collections.singletonList(std)).get(0);
    }

    @Override
    public List<String> getAllGrade()
    {
        return standardRepository.findAllGrade();
    }

    private List<StandardResponse> transformToStandard(List<Standard> standard)
    {
        return standard.stream().filter(standa -> !ObjectUtils.isEmpty(standa))
                .map(std ->
                {
                    StandardResponse stdResponse = transformResponse(std);
                    stdResponse.setSubjects(
                            transformtoSubject(std.getSubjects()));
                    return stdResponse;
                }).collect(Collectors.toList());

    }

    private List<SubjectResponse> transformtoSubject(List<Subject> subject)
    {
        return subject.stream().filter(subj -> !ObjectUtils.isEmpty(subj))
                .map(sub ->
                {
                    SubjectResponse subjectresponse = transformResponse(sub);
                    subjectresponse.setChapters(
                            transformToChapter(sub.getChapters()));
                    return subjectresponse;
                }).collect(Collectors.toList());

    }

    private List<ChapterResponse> transformToChapter(List<Chapter> chapter)
    {
        return chapter.stream().filter(chap -> !ObjectUtils.isEmpty(chap))
                .map(chptr ->
                {
                    ChapterResponse chapterresponse = transformResponse(chptr);
                    chapterresponse.setChaptervideo(
                            transformToChapterVideo(chptr.getChaptervideo()));
                    return chapterresponse;
                }).collect(Collectors.toList());

    }

    private List<ChapterVideoResponse> transformToChapterVideo(
            List<ChapterVideo> chaptervideo)
    {
        return chaptervideo.stream()
                .filter(chpvideo -> !ObjectUtils.isEmpty(chpvideo))
                .map(chptvideo -> transformResponse(chptvideo))
                .collect(Collectors.toList());

    }

    /**
     * Request mapping to entity object
     */

    private List<Subject> transformRequestSubject(
            List<SubjectRequest> subjectRequest, Standard standard)
    {
        return subjectRequest.stream()
                .filter(subjreq -> !ObjectUtils.isEmpty(subjreq))
                .map(subrequest ->
                {
                    Subject subject = requesttransform(subrequest);
                    subject.setChapters(
                            transformRequestChapter(subrequest.getChapters(),
                                    subject));
                    subject.setStandard(standard);
                    return subject;
                }).collect(Collectors.toList());

    }

    private List<Chapter> transformRequestChapter(
            List<ChapterRequest> chapterrequest, Subject subject)
    {
        return chapterrequest.stream()
                .filter(chap -> !ObjectUtils.isEmpty(chap)).map(chptrReq ->
                {
                    Chapter chapter = requesttransform(chptrReq);
                    chapter.setSubject(subject);
                    chapter.setChaptervideo(transformRequestChapterVideo(
                            chptrReq.getChaptervideo(), chapter));
                    return chapter;
                }).collect(Collectors.toList());

    }

    private List<ChapterVideo> transformRequestChapterVideo(
            List<ChapterVideoRequest> requestchaptervideo,
            Chapter chapter)
    {
        return requestchaptervideo.stream()
                .filter(chpvideo -> !ObjectUtils.isEmpty(chpvideo))
                .map(chptvideo ->
                {
                    ChapterVideo chaptervideo = requesttransform(chptvideo);
                    chaptervideo.setChapter(chapter);
                    return chaptervideo;
                }).collect(Collectors.toList());

    }

}
