package org.learn365.file.uploader.model;

import lombok.Data;

import java.util.List;

@Data
public class CreatedTestResponse {
    private List<TestMetadata> testMetadataList;
    private List<TestError> errorList;
}
