package org.learn365.test.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TEST_OPTIONS")
@Data
public class OptionsEntity extends BaseEntity {

    @Column(name = "option_description", nullable = false)
    private String optionDescription;

    @Column(name = "option_sequence", nullable = false)
    private Integer optionSequence;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

}
