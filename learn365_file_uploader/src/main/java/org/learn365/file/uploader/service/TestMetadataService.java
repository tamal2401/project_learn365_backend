package org.learn365.file.uploader.service;

import org.learn365.file.uploader.model.Identifier;
import org.learn365.file.uploader.model.StandardResponse;
import org.learn365.file.uploader.model.TestException;

import java.util.List;
import java.util.Set;

public interface TestMetadataService {

    Set<String> getSubjectSet() throws Exception;

    Set<String> gradeSet() throws Exception;

    List<Identifier> getChapterList(String grade, String subject);

    List<Identifier> getVideoList(String grade, String subject);

    List<StandardResponse> getStandardResponse() throws TestException;
}
