package org.learn365.test.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TEST_RECORD")
@Data
public class UserTestOptsRecordEntity extends BaseEntity {
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "test_id", nullable = false)
    private Long testId;

    @Column(name = "test_code", nullable = false)
    private String testCode;

    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(name = "selected_option", nullable = false)
    private Long selectedOptionId;

    @Column(name = "correct_option_id", nullable = false)
    private Long correctOptionId;
}
