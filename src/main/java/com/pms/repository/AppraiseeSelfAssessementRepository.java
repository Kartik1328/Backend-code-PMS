package com.pms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pms.Model.AppraiseeSelfAssessement;
import java.util.Optional;

//common used things or genric things that we have to use in our code

public interface AppraiseeSelfAssessementRepository extends JpaRepository<AppraiseeSelfAssessement,Long> {
	Optional<AppraiseeSelfAssessement>findByempId(Long empId);

}
