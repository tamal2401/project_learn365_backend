package org.learn365.course.service;

import org.learn365.modal.course.response.ChapterResponse;

import java.util.List;

public interface ChapterService extends ChapterVideoService {

    boolean updateTest(Long id,String testid);
    List<ChapterResponse> getChapterBySubject(Long id);

}
