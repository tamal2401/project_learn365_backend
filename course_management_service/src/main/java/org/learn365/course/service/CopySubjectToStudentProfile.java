package org.learn365.course.service;

import org.learn365.modal.course.entity.*;
import org.learn365.modal.course.response.ChapterResponse;
import org.learn365.modal.course.response.ChapterVideoResponse;
import org.learn365.modal.course.response.SubjectResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

 public interface CopySubjectToStudentProfile {



    default  List<SubjectResponse> mapToSubjectResponse(SubscribedGrade subscribedGrade, List<Subject> subjects) {
        Map<Long, Subject> createInstace=subjects.stream().collect(Collectors.toMap(Subject::getId, Function.identity()));
        List<SubjectResponse> subjectResponse = subscribedGrade.getSubscribedSubject().stream().map(subject -> {
            SubjectResponse subresponse = new SubjectResponse();
            Subject subjectTemplate = createInstace.get(subject.getSubjectid());
            BeanUtils.copyProperties(subjectTemplate, subresponse);
            subresponse.setStudentProgress(subject.getSubjectStatus());
            subresponse.setStudentSubjectProfileId(subject.getId());
            subresponse.setTeststatus(subject.getTeststatus());
            subresponse.setChapters(mapToChapterResponse(subject.getSubscribedChapter(), subjectTemplate.getChapters()));
            return subresponse;
        }).collect(Collectors.toList());
        return subjectResponse;
    }

    default List<ChapterResponse> mapToChapterResponse(List<SubscribedChapter> subscribedGrade, List<Chapter> chapters) {
        Map<Long, Chapter> createInstace=chapters.stream().collect(Collectors.toMap(Chapter::getId, Function.identity()));
        List<ChapterResponse> chapterResponse = subscribedGrade.stream().map(subchapter -> {
            ChapterResponse chapterRes = new ChapterResponse();
            Chapter chapterTemplate = createInstace.get(subchapter.getChapterid());
            BeanUtils.copyProperties(chapterTemplate, chapterRes);
            chapterRes.setStudentProgress(subchapter.getChapterStatus());
            chapterRes.setStudentChapterProfileId(subchapter.getId());
            chapterRes.setTeststaus(subchapter.isTeststatus());
            chapterRes.setChaptervideo(mapToChapterVideoResponse(subchapter.getSubscribedvideos(), chapterTemplate.getChaptervideo()));
            return chapterRes;
        }).collect(Collectors.toList());

        return chapterResponse;
    }

    default List<ChapterVideoResponse> mapToChapterVideoResponse(List<Subscribedvideo> subsVideo, List<ChapterVideo> savedChaptervideo) {
        Map<Long, ChapterVideo> createInstace=savedChaptervideo.stream().collect(Collectors.toMap(ChapterVideo::getId, Function.identity()));
        List<ChapterVideoResponse> chapterVideoResponse = subsVideo.stream().map(video -> {
            ChapterVideoResponse chVideoResponse = new ChapterVideoResponse();
            ChapterVideo chapterVideo = createInstace.get(video.getVideoId());
            BeanUtils.copyProperties(chapterVideo, chVideoResponse);
            chVideoResponse.setStudentProgress(video.getVideoStatus());
            chVideoResponse.setStudentchVideoProfileId(video.getId());
            chVideoResponse.setTestStatus(video.isTestStatus());
            return chVideoResponse;
        }).collect(Collectors.toList());
        return chapterVideoResponse;
    }
}
