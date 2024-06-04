package com.pms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pms.Model.AppraiseeSelfAssessementDraft;
import java.util.Optional;

public interface AppraiseeSelfAssessmentDraftRepository extends JpaRepository<AppraiseeSelfAssessementDraft,Long> {
	Optional<AppraiseeSelfAssessementDraft>findByEmpId(Long empId);
	void deleteByEmpId(Long empId);
	boolean existsByEmpId(Long empId);
}
