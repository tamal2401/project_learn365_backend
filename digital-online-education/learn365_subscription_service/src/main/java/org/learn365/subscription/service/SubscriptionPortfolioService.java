package org.learn365.subscription.service;

import java.util.List;

import org.learn365.modal.subscription.request.OfferedGradeRequest;
import org.learn365.modal.subscription.request.SubscriptionPlanRequest;
import org.learn365.modal.subscription.response.OfferedGradeResponse;

public interface SubscriptionPortfolioService {

	public OfferedGradeResponse createSubscriptionPortFolio(
			OfferedGradeRequest gradeRequest);

	public OfferedGradeResponse updateSubscriptionPortFolio(
			OfferedGradeRequest gradeRequest, Long gradId);

	public List<OfferedGradeResponse> getAllSubscriptionPortFolio();

	public OfferedGradeResponse getBygradeIdSubscriptionPortFolio(Long gradId);

	public Boolean deleteOfferedGrade(Long gradId);

}
