package com.pms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pms.Model.EmployeeStatus;

import java.util.List;
//import java.util.Optional;

public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus,Long> {
//	Optional<EmployeeStatus>findByempId(Long empId);
    List<EmployeeStatus> findByempId(Long empId);
    //	List<EmployeeStatus> findByYearAndReviewCycle(String year,String reviewCycle);
    List<EmployeeStatus> findByYearAndReviewCycleAndEmpId(String year, String reviewCycle, Long empId);

    boolean existsByAppraisalCycle(String appraisalCycle);
}

