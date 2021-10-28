package org.learn365.modal.course.request;

import javax.validation.constraints.NotBlank;

public class ChapterVideoRequest extends BaseEntity {
	@NotBlank(message = "Video name should not be blank")
	private String videoname;
	@NotBlank(message = "Video url should not be blank")
	private String videourl;
	@NotBlank(message = "Order should not be blank")
	private Integer order;
	private String testid;
	private Boolean intialunlock=false;
	private String thumbnail;

	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}

	public Boolean getIntialunlock() {
		return intialunlock;
	}

	public void setIntialunlock(Boolean intialunlock) {
		this.intialunlock = intialunlock;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
