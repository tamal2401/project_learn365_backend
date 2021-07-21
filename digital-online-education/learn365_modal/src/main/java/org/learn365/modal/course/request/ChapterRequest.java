package org.learn365.modal.course.request;

import java.util.List;

public class ChapterRequest extends BaseEntity {

	private String chapterName;
	private String chapterpic;
	private String trialVideo;
	private Integer order;
	private List<ChapterVideoRequest> chaptervideo;
	private Long test;

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

	public Long getTest() {
		return test;
	}

	public void setTest(Long test) {
		this.test = test;
	}

}
