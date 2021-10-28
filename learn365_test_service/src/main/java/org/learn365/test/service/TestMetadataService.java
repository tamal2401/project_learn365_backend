package org.learn365.test.service;

import org.learn365.modal.course.response.StandardResponse;
import org.learn365.modal.test.Identifier;
import org.learn365.test.exception.TestException;

import java.util.List;
import java.util.Set;

public interface TestMetadataService {

    Set<String> getSubjectSet();

    Set<String> gradeSet();

    List<Identifier> getChapterList(String grade, String subject);

    public List<Identifier> getAllChapters(String grade);

    List<Identifier> getVideoList(String grade, String subject);

    List<StandardResponse> getStandardResponse() throws TestException;
}
