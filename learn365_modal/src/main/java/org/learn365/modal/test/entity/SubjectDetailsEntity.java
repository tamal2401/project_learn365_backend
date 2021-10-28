package org.learn365.modal.test.entity;

import lombok.Data;

import java.util.Map;

@Data
public class SubjectDetailsEntity {
    private String subjectId;
    private String gradeLevel;
    private String testType;
    private String chapterName;
    private String chapterPath;
    private Map<String, String> testTypeKeyFields;
}
