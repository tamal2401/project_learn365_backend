package org.learn365.modal.subscription.response;


public class SubscriptionDetailsResponse extends BaseEntity {

	private String subscriptionstdid;
	private String subscriptionstdname;
	private String subscriptionchapterid;
	private String subscriptionchaptername;
	private String subscriptionDesccription;

	public String getSubscriptionstdid() {
		return subscriptionstdid;
	}

	public void setSubscriptionstdid(String subscriptionstdid) {
		this.subscriptionstdid = subscriptionstdid;
	}

	public String getSubscriptionstdname() {
		return subscriptionstdname;
	}

	public void setSubscriptionstdname(String subscriptionstdname) {
		this.subscriptionstdname = subscriptionstdname;
	}

	public String getSubscriptionchapterid() {
		return subscriptionchapterid;
	}

	public void setSubscriptionchapterid(String subscriptionchapterid) {
		this.subscriptionchapterid = subscriptionchapterid;
	}

	public String getSubscriptionchaptername() {
		return subscriptionchaptername;
	}

	public void setSubscriptionchaptername(String subscriptionchaptername) {
		this.subscriptionchaptername = subscriptionchaptername;
	}

	public String getSubscriptionDesccription() {
		return subscriptionDesccription;
	}

	public void setSubscriptionDesccription(String subscriptionDesccription) {
		this.subscriptionDesccription = subscriptionDesccription;
	}
}
