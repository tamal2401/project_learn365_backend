package org.learn365.course.repository;

import java.util.List;

import org.learn365.modal.course.entity.UserCoursePortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCoursePortfolioRepository extends JpaRepository<UserCoursePortfolio, Long> {

	public List<UserCoursePortfolio> findByUserid(String userId);

}
