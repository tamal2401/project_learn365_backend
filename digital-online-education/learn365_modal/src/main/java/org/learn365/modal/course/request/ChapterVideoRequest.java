package org.learn365.modal.course.request;

import javax.validation.constraints.NotBlank;

public class ChapterVideoRequest extends BaseEntity {
	@NotBlank(message = "Video name should not be blank")
	private String videoname;
	@NotBlank(message = "Video url should not be blank")
	private String videourl;
	@NotBlank(message = "Order should not be blank")
	private Integer order;

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

}
