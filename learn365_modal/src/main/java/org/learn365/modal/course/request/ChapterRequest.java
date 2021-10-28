package org.learn365.modal.course.request;

import org.learn365.modal.course.entity.ChapterVideo;
import org.learn365.modal.course.entity.Subject;

import javax.persistence.*;
import java.util.List;

public class ChapterRequest extends BaseEntity {

	private String chapterName;
	private String chapterpic;
	private String trialVideo;
	private Integer order;
	private List<ChapterVideoRequest> chaptervideo;
	private String testid;
	private String chapterpath;
	private Boolean markedforTrial=false;
	private Boolean intialunlock=false;

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getChapterpic() {
		return chapterpic;
	}

	public void setChapterpic(String chapterpic) {
		this.chapterpic = chapterpic;
	}

	public String getTrialVideo() {
		return trialVideo;
	}

	public void setTrialVideo(String trialVideo) {
		this.trialVideo = trialVideo;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public List<ChapterVideoRequest> getChaptervideo() {
		return chaptervideo;
	}

	public void setChaptervideo(List<ChapterVideoRequest> chaptervideo) {
		this.chaptervideo = chaptervideo;
	}

	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}

	public String getChapterpath() {
		return chapterpath;
	}

	public void setChapterpath(String chapterpath) {
		this.chapterpath = chapterpath;
	}

	public Boolean getMarkedforTrial() {
		return markedforTrial;
	}

	public void setMarkedforTrial(Boolean markedforTrial) {
		this.markedforTrial = markedforTrial;
	}

	public Boolean getIntialunlock() {
		return intialunlock;
	}

	public void setIntialunlock(Boolean intialunlock) {
		this.intialunlock = intialunlock;
	}
}
