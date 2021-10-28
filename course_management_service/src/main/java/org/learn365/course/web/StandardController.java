package org.learn365.course.web;

import org.learn365.course.service.StandardService;
import org.learn365.modal.course.request.StandardRequest;
import org.learn365.modal.course.response.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/grade/")
public class StandardController
{

    private static Logger log = LoggerFactory.getLogger(StandardController.class);

    private StandardService standardService;

    public StandardController(StandardService standardService)
    {
        this.standardService = standardService;
    }

    @GetMapping(
            value = {"getallavailableportfolio", "service/getallavailableportfolio"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StandardResponse> getAllAvailablePortfolio()
    {
        log.info("Request to get all available course portfolio");
        return standardService.getAllAvailableCourses();
    }

    @GetMapping(
            value = {"getportfolioBygrade/{grade}", "service/getportfolioBygrade/{grade}"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StandardResponse getAllAvailablePortfolioByGrade(
            @PathVariable(name = "grade", required = true) String grade)
    {
        log.info("Request to get all available course portfolio by grade : {}",
                grade);
        return standardService.getCourseByGrade(grade);
    }

    @PostMapping(value = {"addportfolio", "service/addportfolio"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StandardResponse addCoursePortfolio(
            @Valid @RequestBody StandardRequest standardrequest)
    {
        log.info("Request to add course for grade : {}",
                standardrequest.getName());
        return standardService.addCourse(standardrequest);
    }

    @GetMapping(value = {"getAllGrade", "service/getAllGrade"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getAllGrade()
    {
        log.info("Request to get all grade");
        return standardService.getAllGrade();
    }

}
