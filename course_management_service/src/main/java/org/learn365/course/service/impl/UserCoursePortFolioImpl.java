package org.learn365.course.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.learn365.course.repository.UserCoursePortfolioRepository;
import org.learn365.course.service.UserCoursePortfolioService;
import org.learn365.exception.UserCoursePortfolioException;
import org.learn365.modal.course.entity.UserCoursePortfolio;
import org.learn365.modal.course.request.UserCoursePortfolioRequest;
import org.learn365.modal.course.response.UserCoursePortfolioResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class UserCoursePortFolioImpl implements UserCoursePortfolioService {

	private UserCoursePortfolioRepository userCoursePortfolioRepository;

	UserCoursePortFolioImpl(UserCoursePortfolioRepository userCoursePortfolioRepository) {
		this.userCoursePortfolioRepository = userCoursePortfolioRepository;
	}

	@Override
	public UserCoursePortfolioResponse addUserCoursePortfolio(UserCoursePortfolioRequest userCoursePortfolioRequest) {
		if (ObjectUtils.isEmpty(userCoursePortfolioRequest)) {
			throw new IllegalArgumentException("User course portfolio should not be null");
		}
		UserCoursePortfolio userPortfolio = new UserCoursePortfolio();
		BeanUtils.copyProperties(userCoursePortfolioRequest, userPortfolio);
		UserCoursePortfolioResponse UserCoursePortfolioResponse = null;
		try {
			Optional<UserCoursePortfolio> userCoursePortfolio = Optional
					.of(userCoursePortfolioRepository.save(userPortfolio));
			userCoursePortfolio.orElseThrow(() -> new UserCoursePortfolioException("User course doesn't save"));
			UserCoursePortfolioResponse = new UserCoursePortfolioResponse();
			BeanUtils.copyProperties(userCoursePortfolio.get(), UserCoursePortfolioResponse);
		} catch (Exception exception) {
			throw new UserCoursePortfolioException(exception.getMessage());
		}
		return UserCoursePortfolioResponse;
	}

	@Override
	public List<UserCoursePortfolioResponse> getUserCoursePortfolioByGrade(String grade, String userId) {
		return null;
	}

	@Override
	public List<UserCoursePortfolioResponse> getUserCoursePortfolioByUserId(String userid) {
		List<UserCoursePortfolio> userCompletedCoursePortfolio = userCoursePortfolioRepository.findByUserid(userid);
		if (CollectionUtils.isEmpty(userCompletedCoursePortfolio)) {
			throw new UserCoursePortfolioException("Sorry! no course completed till now");
		}

		return null;
	}

}
