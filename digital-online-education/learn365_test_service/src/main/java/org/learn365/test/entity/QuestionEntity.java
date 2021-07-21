package org.learn365.test.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEST_QUESTIONS")
@Data
public class QuestionEntity extends BaseEntity{
    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "question_order", nullable = false)
    private String question_order;

    @Column(name = "question_mark", nullable = false)
    private Integer marks;

    @Column(name = "question_explanation_text", nullable = false)
    private String explanationText;

    @Column(name = "question_explanation_video_id", nullable = false)
    private Long explanationVideoId;

    @Column(name = "question_explanation_video_path", nullable = false)
    private String explanationVideoPath;

    @Column(name = "question_correct_option_id", nullable = false)
    private Long correctOptionId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.PERSIST)
    private List<OptionsEntity> options;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private TestEntity test;
}
