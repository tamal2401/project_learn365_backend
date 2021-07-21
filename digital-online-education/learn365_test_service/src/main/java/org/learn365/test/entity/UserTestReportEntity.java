package org.learn365.test.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TEST_REPORT")
@Data
public class UserTestReportEntity extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "marks", nullable = false)
    private Float marks;

    @Column(name = "grades", nullable = false)
    private String grades;

    @Column(name = "test_id", nullable = false)
    private Long testId;

    @Column(name = "test_code", nullable = false)
    private String testCode;
}
