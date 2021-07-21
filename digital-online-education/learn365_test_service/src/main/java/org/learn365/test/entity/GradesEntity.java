package org.learn365.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "GRADES")
@Data
public class GradesEntity extends BaseEntity{

    @Column(name = "grade", nullable = false)
    private String grade;

    @Column(name = "upper_limit", nullable = false)
    private Float upperLimit;

    @Column(name = "lower_limit", nullable = false)
    private Float lowerLimit;

    @Column(name = "exam_level", nullable = false)
    private String level;
}
