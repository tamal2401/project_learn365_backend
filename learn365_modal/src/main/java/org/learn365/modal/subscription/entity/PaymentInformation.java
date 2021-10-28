package org.learn365.modal.subscription.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_Payment_details")
public class PaymentInformation extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_generator")
	@SequenceGenerator(name="payment_generator", sequenceName = "payment_seq")
	private Long id;
	@Column(name = "payment_order_id", nullable = false)
	private String order_id;
	@Column(name = "payment_amount", nullable = false)
	private Double amount;
	@Column(name = "payment_status", nullable = false)
	private String status;
	@Column(name = "user_id", nullable = false)
	private Long user_id;
	@Column(name = "payment_id")
	private String payment_id;
	@Column(name = "payment_mode")
	private String mode;
	@Column(name = "Subscription_std_id")
	private String standardid;
	@Column(name = "Subscription_std_name")
	private String standardname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
