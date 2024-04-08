package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.Model.AppraiseeManagerAssessment;
import java.util.Optional;

public interface AppraiseeManagerAssessmentRepository extends JpaRepository<AppraiseeManagerAssessment,Long> {
   Optional<AppraiseeManagerAssessment>findBymgrId(Long mgr_id);
}
