package org.learn365.modal.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "grades")
@Data
@EqualsAndHashCode(callSuper=false)
public class GradesEntity extends BaseEntity{
    private String grade;
    private Float upperLimit;
    private Float lowerLimit;
    private String examLevel;
}


