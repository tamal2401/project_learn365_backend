package org.learn365.course.web;

import java.util.List;

import javax.validation.Valid;

import org.learn365.course.service.StandardService;
import org.learn365.modal.course.request.StandardRequest;
import org.learn365.modal.course.response.StandardResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/grade/")
public class StandardController {

	private StandardService standardService;

	public StandardController(StandardService standardService) {
		this.standardService = standardService;
	}

	@GetMapping(value = {"getallavailableportfolio","service/getallavailableportfolio"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StandardResponse> getAllAvailablePortfolio() {
		return standardService.getAllAvailableCourses();
	}

	@GetMapping(value = {"getportfolioBygrade/{grade}","service/getportfolioBygrade/{grade}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public StandardResponse getAllAvailablePortfolioByGrade(
			@PathVariable(name = "grade", required = true) String grade) {
		return standardService.getCourseByGrade(grade);
	}

	@PostMapping(value = {"addportfolio","service/addportfolio"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public StandardResponse addCoursePortfolio(@Valid @RequestBody StandardRequest standardrequest) {
		return standardService.addCourse(standardrequest);
	}

}
