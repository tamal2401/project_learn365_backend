package org.learn365.course.service;

import java.util.List;

import org.learn365.modal.course.request.UserCoursePortfolioRequest;
import org.learn365.modal.course.response.UserCoursePortfolioResponse;

public interface UserCoursePortfolioService {

	public UserCoursePortfolioResponse addUserCoursePortfolio(UserCoursePortfolioRequest userCoursePortfolio);

	public List<UserCoursePortfolioResponse> getUserCoursePortfolioByGrade(String grade, String userId);

	public List<UserCoursePortfolioResponse> getUserCoursePortfolioByUserId(String userid);

}
