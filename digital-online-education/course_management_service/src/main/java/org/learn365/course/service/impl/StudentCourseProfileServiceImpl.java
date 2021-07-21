package org.learn365.course.service.impl;

import org.learn365.course.repository.StandardRepository;
import org.learn365.course.repository.StudentProfileRepository;
import org.learn365.course.service.StudentCourseProfileService;
import org.learn365.course.util.PrepareUserCourse;
import org.learn365.exception.CoursePortFolioNotFoundException;
import org.learn365.modal.constants.StudentProgress;
import org.learn365.modal.course.entity.*;
import org.learn365.modal.course.response.ChapterResponse;
import org.learn365.modal.course.response.ChapterVideoResponse;
import org.learn365.modal.course.response.StandardResponse;
import org.learn365.modal.course.response.SubjectResponse;
import org.learn365.modal.subscription.request.StudentProfileCreateRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class StudentCourseProfileServiceImpl implements StudentCourseProfileService {
    private StandardRepository standardRepository;
    private StudentProfileRepository studentProfileRepository;

    public StudentCourseProfileServiceImpl(StandardRepository standardRepository, StudentProfileRepository studentProfileRepository) {
        this.standardRepository = standardRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public void createStudentProfile(StudentProfileCreateRequest studentProfileCreateRequest) {
        StudentProfile stuprofile = null;
        List<SubscribedGrade> subscribedGrade = new ArrayList<>();
        Optional<StudentProfile> isstuProfile = studentProfileRepository.findByUserId(studentProfileCreateRequest.getUserId());
        if (isstuProfile.isPresent()) {
            stuprofile = isstuProfile.get();
            subscribedGrade = stuprofile.getSubscribedGrade();
        } else {
            stuprofile = new StudentProfile();
            stuprofile.setUserId(studentProfileCreateRequest.getUserId());
            stuprofile.setTimespent(0L);
        }

        if (CollectionUtils.isEmpty(subscribedGrade)) {
            stuprofile.setSubscribedGrade(copySubscribedGrade(studentProfileCreateRequest, stuprofile));
        } else {
            subscribedGrade.addAll(copySubscribedGrade(studentProfileCreateRequest, stuprofile));
            stuprofile.setSubscribedGrade(subscribedGrade);
        }
        if (null != stuprofile) {
            studentProfileRepository.save(stuprofile);
        }
    }

    private List<SubscribedGrade> copySubscribedGrade(StudentProfileCreateRequest studentProfileCreateRequest, StudentProfile stuprofile) {
        Optional<Standard> optionalStandard = standardRepository.findById(studentProfileCreateRequest.getGradeId());
        Standard standard = optionalStandard.orElseThrow(() -> new CoursePortFolioNotFoundException("Subscribed course is not available"));
        SubscribedGrade subGrade = new SubscribedGrade.SubscribedGradeBuilder(studentProfileCreateRequest.getUserId()
                , studentProfileCreateRequest.getGradeId()).withSubscribedPlanId(studentProfileCreateRequest.getSubscribedPlanId())
                .withSubscribedPlanName(studentProfileCreateRequest.getSubscribedPlanName())
                .withStartDate(studentProfileCreateRequest.getStartDate()).withEndDate(studentProfileCreateRequest.getEndDate())
                .withTimeSpent(0L).withStudentCourseStatus(StudentProgress.NOTSTARTED).build();
        subGrade.setSubscribedSubject(copySubscribedSubject(studentProfileCreateRequest, standard.getSubjects(), subGrade));
        subGrade.setStudentprofile(stuprofile);
        return Collections.singletonList(subGrade);

    }

    private List<SubscribedSubject> copySubscribedSubject(StudentProfileCreateRequest studentProfileCreateRequest, List<Subject> availableSubject, SubscribedGrade subGrade) {
        return availableSubject.stream().filter(subject -> studentProfileCreateRequest.getSubjectId().contains(subject.getId())).map(availablesubject -> {
            SubscribedSubject subscribedSubject = new SubscribedSubject();
            subscribedSubject.setUserId(studentProfileCreateRequest.getUserId());
            subscribedSubject.setSubjectid(availablesubject.getId());
            subscribedSubject.setTeststatus(false);
            subscribedSubject.setSubscribedGrade(subGrade);
            subscribedSubject.setSubscribedChapter(copySubscribedChapter(studentProfileCreateRequest.getUserId(), availablesubject.getChapters(), subscribedSubject));
            return subscribedSubject;
        }).collect(Collectors.toList());

    }

    private List<SubscribedChapter> copySubscribedChapter(Long userId, List<Chapter> availableChapter, SubscribedSubject subscribedSubject) {
        return availableChapter.stream().map(chapter -> {
            SubscribedChapter subchapter = new SubscribedChapter();
            subchapter.setUserId(userId);
            subchapter.setChapterid(chapter.getId());
            subchapter.setChapterName(chapter.getChapterName());
            subchapter.setTeststatus(false);
            subchapter.setSubscribedSubject(subscribedSubject);
            subchapter.setSubscribedvideos(copySubscribedVideo(userId, chapter.getChaptervideo(), subchapter));
            return subchapter;
        }).collect(Collectors.toList());

    }

    private List<Subscribedvideo> copySubscribedVideo(Long userId, List<ChapterVideo> availablevideo, SubscribedChapter subscribedChapter) {
        return availablevideo.stream().map(video -> {
            Subscribedvideo subVideo = new Subscribedvideo();
            subVideo.setUserId(video.getId());
            subVideo.setUserId(userId);
            subVideo.setTestStatus(false);
            subVideo.setSubscribedChapter(subscribedChapter);
            subVideo.setVideoName(video.getVideoname());
            return subVideo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<StandardResponse> getUserCourseDetails(final Long userId) {
        Optional<StudentProfile> isStudentProfileAvailable = studentProfileRepository.findByUserId(userId);
        // isStudentProfileAvailable.ifPresent(studentProfile->System.out.println("check"));
        StudentProfile stuProfile = isStudentProfileAvailable.orElseThrow(()-> new CoursePortFolioNotFoundException("No started yet! please subscribe your course"));
            List<StandardResponse> standardResponse = null;
            StandardResponse standardresponse = new StandardResponse();
            standardResponse = stuProfile.getSubscribedGrade().stream().map(profile -> mapTostandardResponse(profile, standardresponse)).collect(Collectors.toList());
        return standardResponse;
    }

    private StandardResponse mapTostandardResponse(SubscribedGrade subscribedGrade, StandardResponse standardresponse) {
        Optional<Standard> isGradeAvailable = standardRepository.findById(subscribedGrade.getGradeId());

        if (isGradeAvailable.isPresent()) {
            BeanUtils.copyProperties(isGradeAvailable.get(), standardresponse);
            standardresponse.setStudentProgress(subscribedGrade.getGradestatus());
            standardresponse.setSubjects(mapToSubjectResponse(subscribedGrade, isGradeAvailable.get().getSubjects()));
        }
        return standardresponse;
    }

    private List<SubjectResponse> mapToSubjectResponse(SubscribedGrade subscribedGrade, List<Subject> subjects) {
        AtomicReference<Map<Long, Subject>> createInstace = null;
        subjects.forEach(subject -> {
            PrepareUserCourse<Long, Subject> Storesubject = new PrepareUserCourse<>();
            Storesubject.add(subject.getId(), subject);
            createInstace.set(Storesubject.getMap());
        });


        List<SubjectResponse> subjectResponse = subscribedGrade.getSubscribedSubject().stream().map(subject -> {
            SubjectResponse subresponse = new SubjectResponse();
            Subject subjectTemplate = createInstace.get().get(subject.getSubjectid());
            BeanUtils.copyProperties(subjectTemplate, subresponse);
            subresponse.setStudentProgress(subject.getSubjectStatus());
            subresponse.setTeststatus(subject.getTeststatus());
            subresponse.setChapters(mapToChapterResponse(subject.getSubscribedChapter(), subjectTemplate.getChapters()));
            return subresponse;
        }).collect(Collectors.toList());

        createInstace.get().clear();
        return subjectResponse;
    }

    private List<ChapterResponse> mapToChapterResponse(List<SubscribedChapter> subscribedGrade, List<Chapter> chapters) {
        AtomicReference<Map<Long, Chapter>> createInstace = null;
        chapters.forEach(chapter -> {
            PrepareUserCourse<Long, Chapter> storeChapter = new PrepareUserCourse<>();
            storeChapter.add(chapter.getId(), chapter);
            createInstace.set(storeChapter.getMap());
        });

        List<ChapterResponse> chapterResponse = subscribedGrade.stream().map(subchapter -> {
            ChapterResponse chapterRes = new ChapterResponse();
            Chapter chapterTemplate = createInstace.get().get(subchapter.getChapterid());
            BeanUtils.copyProperties(chapterTemplate, chapterRes);
            chapterRes.setStudentProgress(subchapter.getChapterStatus());
            chapterRes.setTeststaus(subchapter.isTeststatus());
            chapterRes.setChaptervideo(mapToChapterVideoResponse(subchapter.getSubscribedvideos(), chapterTemplate.getChaptervideo()));
            return chapterRes;
        }).collect(Collectors.toList());

        createInstace.get().clear();
        return chapterResponse;
    }

    private List<ChapterVideoResponse> mapToChapterVideoResponse(List<Subscribedvideo> subsVideo, List<ChapterVideo> savedChaptervideo) {
        AtomicReference<Map<Long, ChapterVideo>> createInstace = null;
        savedChaptervideo.forEach(chaptervideo -> {
            PrepareUserCourse<Long, ChapterVideo> storeChapter = new PrepareUserCourse<>();
            storeChapter.add(chaptervideo.getId(), chaptervideo);
            createInstace.set(storeChapter.getMap());
        });

        List<ChapterVideoResponse> chapterVideoResponse = subsVideo.stream().map(video -> {
            ChapterVideoResponse chVideoResponse = new ChapterVideoResponse();
            ChapterVideo chapterVideo = createInstace.get().get(video.getVideoId());
            BeanUtils.copyProperties(chapterVideo, chVideoResponse);
            chVideoResponse.setStudentProgress(video.getVideoStatus());
            chVideoResponse.setTestStatus(video.isTestStatus());
            return chVideoResponse;
        }).collect(Collectors.toList());
        createInstace.get().clear();
        return chapterVideoResponse;
    }
}
