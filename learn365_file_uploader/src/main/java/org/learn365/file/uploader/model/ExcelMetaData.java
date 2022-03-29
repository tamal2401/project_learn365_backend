package org.learn365.file.uploader.model;

import lombok.Data;

@Data
public class ExcelMetaData {
    private String text;
    private Integer correctAnswer;
    private Integer order;
    private Integer marks;
    private String explanationText;
    private String explanationVideoName;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
}
