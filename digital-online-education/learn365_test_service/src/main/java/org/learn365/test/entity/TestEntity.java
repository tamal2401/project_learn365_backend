package org.learn365.test.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEST")
@Data
public class TestEntity extends BaseEntity {
    @Column(name = "test_code", nullable = false, unique = true)
    private String testCode;

    @Column(name = "test_isActive", nullable = false)
    private String isActive;

    @Column(name = "test_isDeleted", nullable = false)
    private String isDeleted;

    @OneToMany(mappedBy = "test", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<QuestionEntity> testQuestions;
}
