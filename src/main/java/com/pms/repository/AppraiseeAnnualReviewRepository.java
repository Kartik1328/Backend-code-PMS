package com.pms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pms.Model.AppraiseeAnnualReview;

@Repository
public interface AppraiseeAnnualReviewRepository extends JpaRepository<AppraiseeAnnualReview, Long> {
}
