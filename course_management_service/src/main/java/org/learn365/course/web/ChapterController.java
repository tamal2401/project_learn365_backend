package org.learn365.course.web;

import org.learn365.course.service.ChapterService;
import org.learn365.course.service.ChapterVideoService;
import org.learn365.modal.course.response.ChapterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/chapter/")
public class ChapterController
{

    private Logger log = LoggerFactory.getLogger(ChapterController.class);

    private ChapterService chapterservice;
    private ChapterVideoService chapterVideoService;

    public ChapterController(ChapterService chapterservice,
                             ChapterVideoService chapterVideoService)
    {
        this.chapterservice = chapterservice;
        this.chapterVideoService = chapterVideoService;
    }

    @PutMapping(value = {"test", "service/test"})
    public Boolean updateChapterTest(
            @RequestParam(name = "chapterid", required = true) Long chapterid,
            @RequestParam(name = "testid", required = true) String testid)
    {
        log.info("Request to update test details for chapter : {}", chapterid);
        return chapterservice.updateTest(chapterid, testid);

    }

    @PutMapping(value = {"chaptervideo/test", "service/chaptervideo/test"})
    public Boolean updateChapterVideoTest(@RequestParam(name = "chaptervideoid",
            required = true) Long chaptervideoid,
                                          @RequestParam(name = "testid",
                                                  required = true) String testid)
    {
        log.info("Request to update test details for chapter video : {}",
                chaptervideoid);
        return chapterVideoService.updateConceptTest(chaptervideoid, testid);

    }

    @GetMapping(value = {"{subjectid}", "service/{subjectid}"})
    public List<ChapterResponse> findChapterBySubject(
            @PathVariable(name = "subjectid", required = true) Long subjectid)
    {
        log.info("Request to get chapter by Subject : {}", subjectid);
        return chapterservice.getChapterBySubject(subjectid);
    }

}
