package org.learn365.course.service;

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
import org.springframework.beans.BeanUtils;

public interface Transformer {

	default StandardResponse transformResponse(Standard standards) {
		StandardResponse stdresponse = new StandardResponse();
		BeanUtils.copyProperties(standards, stdresponse);
		return stdresponse;
	}

	default SubjectResponse transformResponse(Subject subject) {
		SubjectResponse subjectresponse = new SubjectResponse();
		BeanUtils.copyProperties(subject, subjectresponse);
		return subjectresponse;
	}

	default ChapterResponse transformResponse(Chapter chapter) {
		ChapterResponse chapterresponse = new ChapterResponse();
		BeanUtils.copyProperties(chapter, chapterresponse);
		return chapterresponse;
	}

	default ChapterVideoResponse transformResponse(ChapterVideo chaptervideo) {
		ChapterVideoResponse chaptervideoresponse = new ChapterVideoResponse();
		BeanUtils.copyProperties(chaptervideo, chaptervideoresponse);
		return chaptervideoresponse;
	}

	default Standard requesttransform(StandardRequest stdRequest) {
		Standard std = new Standard();
		BeanUtils.copyProperties(stdRequest, std);
		return std;
	}

	default Subject requesttransform(SubjectRequest subRequest) {
		Subject sub = new Subject();
		BeanUtils.copyProperties(subRequest, sub);
		return sub;
	}

	default Chapter requesttransform(ChapterRequest chptrRequest) {
		Chapter chptr = new Chapter();
		BeanUtils.copyProperties(chptrRequest, chptr);
		return chptr;
	}

	default ChapterVideo requesttransform(ChapterVideoRequest chptrVideoRequest) {
		ChapterVideo chptrvideo = new ChapterVideo();
		BeanUtils.copyProperties(chptrVideoRequest, chptrvideo);
		return chptrvideo;
	}

}
