package org.learn365.file.uploader.service;

import org.learn365.file.uploader.model.GeneratedTestEntityDetails;
import org.learn365.file.uploader.model.TestException;
import org.learn365.file.uploader.model.TestNameMetadataRequest;

public interface TestReportParserService {

    GeneratedTestEntityDetails parseExcel(String fileName, TestNameMetadataRequest metadata) throws TestException;
}
