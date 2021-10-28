package org.learn365.modal.test.response;

import lombok.Data;
import org.learn365.modal.test.TestError;
import org.learn365.modal.test.TestMetadata;

import java.util.List;

@Data
public class CreatedTestResponse {
    private List<TestMetadata> testMetadataList;
    private List<TestError> errorList;
}
