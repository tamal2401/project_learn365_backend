package org.learn365.modal.subscription.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "learn365_offered_plan")
public class SubscriptionPlan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriptionPlan_generator")
    @SequenceGenerator(name="subscriptionPlan_generator", sequenceName = "subscriptionPlan_seq")
    private Long id;
    @Column(name = "offered_planname", nullable = false, unique = true)
    private String subscriptionPlanName;
    @OneToOne
    private Currency currency;
    @Column(name = "isDiscountFlag",nullable = false)
    private Boolean discounted=false;
    @Column(name = "offered_cost", nullable = false)
    private Double cost;
    @Column(name = "offered_discount_cost", nullable = false)
    private Double discountPrice;
    @Column(name = "offered_validity", nullable = false)
    private String validity;
    @Column(name = "isActive", nullable = false)
    private Boolean active;
    @OneToMany(mappedBy = "subscriptionPlan", cascade = CascadeType.ALL)
    private List<OfferedSubject> courseSubscriptionDetail = new ArrayList();
    @Column(name = "created_by")
    private String createdBy;
    @ManyToOne
    @JoinColumn(name="offerGradeId")
    private OfferedGrade offerGrade;

    public Boolean getDiscounted() {
        return discounted;
    }

    public void setDiscounted(Boolean discounted) {
        this.discounted = discounted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubscriptionPlanName() {
        return subscriptionPlanName;
    }

    public void setSubscriptionPlanName(String subscriptionPlanName) {
        this.subscriptionPlanName = subscriptionPlanName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<OfferedSubject> getCourseSubscriptionDetail() {
        return courseSubscriptionDetail;
    }

    public void setCourseSubscriptionDetail(List<OfferedSubject> courseSubscriptionDetail) {
        this.courseSubscriptionDetail = courseSubscriptionDetail;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public OfferedGrade getOfferGrade() {
        return offerGrade;
    }

    public void setOfferGrade(OfferedGrade offerGrade) {
        this.offerGrade = offerGrade;
    }
}
