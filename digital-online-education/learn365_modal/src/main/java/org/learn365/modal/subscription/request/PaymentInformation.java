package org.learn365.modal.subscription.request;

public class PaymentInformation extends BaseEntity {

	private String order_id;
	private Double amount;
	private String status;
	private Long user_id;
	private String payment_id;
	private String mode;
	private String standardid;
	private String standardname;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getStandardid() {
		return standardid;
	}

	public void setStandardid(String standardid) {
		this.standardid = standardid;
	}

	public String getStandardname() {
		return standardname;
	}

	public void setStandardname(String standardname) {
		this.standardname = standardname;
	}

}
