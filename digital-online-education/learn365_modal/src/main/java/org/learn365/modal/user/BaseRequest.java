package org.learn365.modal.user;

public abstract class BaseRequest {

	private String appName;
	private String appUser;
	// private Error error;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppUser() {
		return appUser;
	}

	public void setAppUser(String appUser) {
		this.appUser = appUser;
	}

}
