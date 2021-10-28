package com.learn365.course;

import com.learn365.model.Chapter;
import com.learn365.model.ChapterVideo;
import com.learn365.model.Grade;
import com.learn365.model.Subject;
import org.learn365.modal.course.request.ChapterRequest;
import org.learn365.modal.course.request.ChapterVideoRequest;
import org.learn365.modal.course.request.StandardRequest;
import org.learn365.modal.course.request.SubjectRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PrepareDataRequest {


    public List<StandardRequest> createCoursePortfolio(Map<Integer, List<Grade>> grademap) {
        List<StandardRequest> standard = new ArrayList<>();
        grademap.forEach((k, v) -> {
            standard.add(mapgradRequest(v.get(0)));
        });
        return standard;
    }

    private StandardRequest mapgradRequest(Grade grade) {
        StandardRequest standardreq = new StandardRequest();
        standardreq.setName(grade.getName());
        standardreq.setActive(true);
        standardreq.setOrder(grade.getOrder());
        standardreq.setPicture(grade.getPicture());
        standardreq.setTestid(grade.getTestId());
        standardreq.setTrialVideo(grade.getTrialVideo());
        standardreq.setSubjects(mapSubject(grade.getSubject()));
        return standardreq;

    }

    public List<SubjectRequest> mapSubject(List<Subject> subjects) {
        List<SubjectRequest> subjRequest=null;
        if(null!=subjects) {
            subjRequest = subjects.stream().filter(s -> null != s).map(subj -> {
                SubjectRequest subreq = new SubjectRequest();
                subreq.setName(subj.getName());
                subreq.setOrder(subj.getOrder());
                subreq.setSubjectPic(subj.getSubjectpic());
                subreq.setTestid(subj.getTestid());
                subreq.setTrialVideo(subj.getTrialVideo());
                subreq.setChapters(mapchapter(subj.getChapter()));
                return subreq;
            }).collect(Collectors.toList());
        }

        return subjRequest;
    }

    public List<ChapterRequest> mapchapter(List<Chapter> chapters) {

        List<ChapterRequest> chapterreq=null;
        if(null!=chapters) {
            chapterreq = chapters.stream().filter(chaptr -> null != chaptr).map(chap -> {
                ChapterRequest chareq = new ChapterRequest();
                chareq.setChapterName(chap.getChapterName());
                chareq.setOrder(chap.getOrder());
                chareq.setChapterpic(chap.getChapterPic());
                chareq.setTestid(chap.getTestid());
                chareq.setTrialVideo(chap.getTrialvideo());
                chareq.setChaptervideo(mapchapterVideo(chap.getChaptervideo()));
                return chareq;
            }).collect(Collectors.toList());
        }

        return chapterreq;
    }

    public List<ChapterVideoRequest> mapchapterVideo(List<ChapterVideo> chaptervideos) {
        List<ChapterVideoRequest> chaptervideoReq=null;
        if(null!=chaptervideos) {
            chaptervideoReq = chaptervideos.stream().filter(cv -> null != cv).map(chvideo -> {
                System.out.println("-------" + chvideo.getVideoname());
                ChapterVideoRequest chaptervi = new ChapterVideoRequest();
                chaptervi.setVideoname(chvideo.getVideoname());
                chaptervi.setVideourl(chvideo.getVideoUrl());
                chaptervi.setOrder(chvideo.getOrder());
                chaptervi.setTestid(chvideo.getTestid());
                chaptervi.setIntialunlock(chvideo.getIntialunlock());
                return chaptervi;
            }).collect(Collectors.toList());
        }
        return chaptervideoReq;
    }
}
