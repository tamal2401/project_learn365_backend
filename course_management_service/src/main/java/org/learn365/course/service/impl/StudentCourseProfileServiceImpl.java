package org.learn365.course.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.learn365.course.cache.CacheService;
import org.learn365.course.cache.CourseDatastructure;
import org.learn365.course.cache.Node;
import org.learn365.course.listener.CreateUserSubscribedCourse;
import org.learn365.course.repository.StandardRepository;
import org.learn365.course.repository.StudentProfileRepository;
import org.learn365.course.service.CopySubjectToStudentProfile;
import org.learn365.course.service.StudentCourseProfileService;
import org.learn365.course.web.StandardController;
import org.learn365.exception.CoursePortFolioNotFoundException;
import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.entity.*;
import org.learn365.modal.course.response.StandardResponse;
import org.learn365.modal.subscription.request.StudentProfileCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentCourseProfileServiceImpl
        implements StudentCourseProfileService, CopySubjectToStudentProfile
{

    private static Logger log = LoggerFactory.getLogger(
            StudentCourseProfileServiceImpl.class);
    private StandardRepository standardRepository;
    private StudentProfileRepository studentProfileRepository;
    private CacheService cacheService;


    public StudentCourseProfileServiceImpl(
            StandardRepository standardRepository,
            StudentProfileRepository studentProfileRepository,
            CacheService cacheService)
    {
        this.standardRepository = standardRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.cacheService = cacheService;
    }

    @StreamListener(CreateUserSubscribedCourse.UserSubscriptionCourseListner)
    @Override
    public void createStudentProfile(
            StudentProfileCreateRequest studentProfileCreateRequest)
    {
        if (ObjectUtils.isEmpty(studentProfileCreateRequest))
        {
            log.info(
                    "Request to create user profile for course is null please check the DLQ for processing");
            return;
        }
        StudentProfile stuprofile = null;
        List<SubscribedGrade> subscribedGrade = new ArrayList<>();
        Optional<StudentProfile> isstuProfile = studentProfileRepository.findByUserId(
                studentProfileCreateRequest.getUserId());
        if (isstuProfile.isPresent())
        {
            stuprofile = isstuProfile.get();
            log.info(
                    "Student profile is already available to updating the subscribed course for user : {} and for grade : {] and for plan name :{}",
                    stuprofile.getUserId(), stuprofile.getSubscribedGrade(),
                    studentProfileCreateRequest.getSubscribedPlanName());
            subscribedGrade = stuprofile.getSubscribedGrade();
        } else
        {
            stuprofile = new StudentProfile();
            stuprofile.setUserId(studentProfileCreateRequest.getUserId());
            log.info(
                    "Student profile is not available So creating new Profile for user : {} and for grade : {] and for plan : {}",
                    studentProfileCreateRequest.getUserId(),
                    studentProfileCreateRequest.getGradeName(),
                    studentProfileCreateRequest.getSubscribedPlanName());
            stuprofile.setTimespent(0L);
        }

        if (CollectionUtils.isEmpty(subscribedGrade))
        {
            log.info(
                    "going to update profile for  Student : {}  for grade : {] and  plan : {}",
                    studentProfileCreateRequest.getUserId(),
                    studentProfileCreateRequest.getGradeName(),
                    studentProfileCreateRequest.getSubscribedPlanName());
            stuprofile.setSubscribedGrade(
                    copySubscribedGrade(studentProfileCreateRequest,
                            stuprofile));
        } else
        {
            //TODO check If he is already subscribed for this plan IF yes throw error
            if (isAlreadySubscribed(subscribedGrade,
                    studentProfileCreateRequest.getGradeId(),
                    studentProfileCreateRequest.getSubscribedPlanId()))
            {
                log.info(
                        "Student : {}  is already subscribed for grade : {] and  plan : {}",
                        studentProfileCreateRequest.getUserId(),
                        studentProfileCreateRequest.getGradeName(),
                        studentProfileCreateRequest.getSubscribedPlanName());
                return;
            }
            subscribedGrade.addAll(
                    copySubscribedGrade(studentProfileCreateRequest,
                            stuprofile));
            stuprofile.setSubscribedGrade(subscribedGrade);
        }
        if (null != stuprofile)
        {
            try
            {
                studentProfileRepository.save(stuprofile);
            } catch (Exception e)
            {
                log.error(
                        "unable to save subscribed data in student profile for student {} for grade {} for plan {} and exception is {}",
                        studentProfileCreateRequest.getUserId(),
                        studentProfileCreateRequest.getGradeName(),
                        studentProfileCreateRequest.getSubscribedPlanName(), e);
            }
        }
    }

    private List<SubscribedGrade> copySubscribedGrade(
            StudentProfileCreateRequest studentProfileCreateRequest,
            StudentProfile stuprofile)
    {
        /*Optional<Standard> optionalStandard = standardRepository.findByName(
                studentProfileCreateRequest.getGradeName());*/
        Node rootNode = cacheService.getValue(
                studentProfileCreateRequest.getGradeName());
        if (null == rootNode)
        {
            log.info(
                    "Subscribed Grade is not available in cache for user :{} so it is failed ",
                    studentProfileCreateRequest.getUserId());
            throw new CoursePortFolioNotFoundException(
                    "Subscribed grade is not available in cache");
        }
        Optional<Standard> optionalStandard = Optional.of(
                CourseDatastructure.getGradeFromRootNode(rootNode));
        Standard standard = optionalStandard.orElseThrow(
                () -> new CoursePortFolioNotFoundException(
                        "Subscribed course is not available"));
        SubscribedGrade subGrade = new SubscribedGrade.SubscribedGradeBuilder(
                studentProfileCreateRequest.getUserId()
                , studentProfileCreateRequest.getGradeId(),
                studentProfileCreateRequest.getGradeName()).withSubscribedPlanId(
                        studentProfileCreateRequest.getSubscribedPlanId())
                .withSubscribedPlanName(
                        studentProfileCreateRequest.getSubscribedPlanName())
                .withStartDate(studentProfileCreateRequest.getStartDate())
                .withEndDate(studentProfileCreateRequest.getEndDate())
                .withTimeSpent(0L)
                .withStudentCourseStatus(StudentProgress.NOTSTARTED).build();
        subGrade.setSubscribedSubject(
                copySubscribedSubject(studentProfileCreateRequest,
                        CourseDatastructure.getSubjectFromRootNode(rootNode),
                        subGrade));
        subGrade.setStudentprofile(stuprofile);
        return Collections.singletonList(subGrade);

    }

    private List<SubscribedSubject> copySubscribedSubject(
            StudentProfileCreateRequest studentProfileCreateRequest,
            List<Subject> availableSubject, SubscribedGrade subGrade)
    {
        return availableSubject.stream()
                .filter(subject -> !(studentProfileCreateRequest.getSubjectId()
                        .contains(subject.getId()))).map(availablesubject ->
                {
                    SubscribedSubject subscribedSubject = new SubscribedSubject();
                    subscribedSubject.setUserId(
                            studentProfileCreateRequest.getUserId());
                    subscribedSubject.setSubjectid(availablesubject.getId());
                    subscribedSubject.setTeststatus(false);
                    subscribedSubject.setSubscribedGrade(subGrade);
                    subscribedSubject.setSubscribedChapter(
                            copySubscribedChapter(
                                    studentProfileCreateRequest.getUserId(),
                                    availablesubject.getChapters(),
                                    subscribedSubject));
                    return subscribedSubject;
                }).collect(Collectors.toList());

    }

    private List<SubscribedChapter> copySubscribedChapter(Long userId,
                                                          List<Chapter> availableChapter,
                                                          SubscribedSubject subscribedSubject)
    {
        return availableChapter.stream().map(chapter ->
        {
            SubscribedChapter subchapter = new SubscribedChapter();
            subchapter.setUserId(userId);
            subchapter.setChapterid(chapter.getId());
            subchapter.setChapterName(chapter.getChapterName());
            subchapter.setTeststatus(false);
            subchapter.setSubscribedSubject(subscribedSubject);
            if (chapter.getIntialunlock())
            {
                subchapter.setChapterStatus(StudentProgress.INCOMPLETE);
            }
            subchapter.setSubscribedvideos(
                    copySubscribedVideo(userId, chapter.getChaptervideo(),
                            subchapter));
            return subchapter;
        }).collect(Collectors.toList());

    }

    private List<Subscribedvideo> copySubscribedVideo(Long userId,
                                                      List<ChapterVideo> availablevideo,
                                                      SubscribedChapter subscribedChapter)
    {
        return availablevideo.stream().map(video ->
        {
            Subscribedvideo subVideo = new Subscribedvideo();
            subVideo.setVideoId(video.getId());
            subVideo.setUserId(userId);
            subVideo.setTestStatus(false);
            if (video.getIntialunlock() == true)
            {
                subVideo.setVideoStatus(StudentProgress.INCOMPLETE);
            }
            subVideo.setSubscribedChapter(subscribedChapter);
            subVideo.setVideoName(video.getVideoname());
            return subVideo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<StandardResponse> getUserCourseDetails(final Long userId)
    {
        Optional<StudentProfile> isStudentProfileAvailable = studentProfileRepository.findByUserId(
                userId);
        StudentProfile stuProfile = isStudentProfileAvailable.orElseThrow(
                () ->
                {
                    log.info("user {} doesn't have any subscribed course",
                            userId);
                    CoursePortFolioNotFoundException coursenotfoundex = new CoursePortFolioNotFoundException(
                            "No started yet! please subscribe your course");
                    return coursenotfoundex;
                });
        List<StandardResponse> standardResponse = null;
        StandardResponse standardresponse = new StandardResponse();
        standardResponse = stuProfile.getSubscribedGrade().stream()
                .map(profile -> mapTostandardResponse(profile,
                        standardresponse)).collect(Collectors.toList());
        return standardResponse;
    }


    private StandardResponse mapTostandardResponse(
            SubscribedGrade subscribedGrade, StandardResponse standardresponse)
    {
        if (subscribedGrade.getEndDate().isAfter(LocalDate.now()))
        {
            /*Optional<Standard> isGradeAvailable = standardRepository.findByName(
                    subscribedGrade.getGradeName());*/
            Node rootNode = cacheService.getValue(
                    subscribedGrade.getGradeName());
            if (null == rootNode)
            {
                log.info("No grade configured in cache. Please refresh it");
                throw new CoursePortFolioNotFoundException(
                        "No grade configured in cache");
            }
            Optional<Standard> isGradeAvailable = Optional.of(
                    CourseDatastructure.getGradeFromRootNode(rootNode));
            if (isGradeAvailable.isPresent())
            {
                BeanUtils.copyProperties(isGradeAvailable.get(),
                        standardresponse);
                standardresponse.setStudentgradeProfileId(
                        subscribedGrade.getId());
                standardresponse.setStudentProgress(
                        subscribedGrade.getGradestatus());
                standardresponse.setSubjects(
                        mapToSubjectResponse(subscribedGrade,
                                CourseDatastructure.getSubjectFromRootNode(
                                        rootNode)));
            }
        }
        return standardresponse;
    }


    private boolean isAlreadySubscribed(List<SubscribedGrade> subscribedGrade,
                                        Long gradeId, Long planid)
    {
        boolean isSubscribed = false;
        for (SubscribedGrade s : subscribedGrade)
        {
            if (s.getGradeId() == gradeId && s.getSubscribedPlanId() == planid)
            {
                isSubscribed = true;
                break;
            }
        }
        return isSubscribed;
    }
}
