package com.pms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pms.Model.EmployeeStatus;

//import java.util.Optional;

public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus,Long> {
//	Optional<EmployeeStatus>findByempId(Long empId);

}
