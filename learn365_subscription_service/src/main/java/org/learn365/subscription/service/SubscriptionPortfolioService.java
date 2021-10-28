package org.learn365.subscription.service;

import java.util.List;

import org.learn365.modal.subscription.request.OfferedGradeRequest;
import org.learn365.modal.subscription.request.SubscriptionPlanRequest;
import org.learn365.modal.subscription.response.OfferedGradeResponse;

public interface SubscriptionPortfolioService {

	 OfferedGradeResponse createSubscriptionPortFolio(
			OfferedGradeRequest gradeRequest);

	 OfferedGradeResponse updateSubscriptionPortFolio(
			OfferedGradeRequest gradeRequest, Long gradId);

	 List<OfferedGradeResponse> getAllSubscriptionPortFolio();

	 OfferedGradeResponse getBygradeIdSubscriptionPortFolio(String gradId);

	 Boolean deleteOfferedGrade(Long gradId);

}
