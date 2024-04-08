package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.Model.AppraiseeDevelopmentGoalSetting;
import java.util.Optional;
public interface AppraiseeDevelopmentGoalSettingRepository extends JpaRepository<AppraiseeDevelopmentGoalSetting,Long> {

	
	
	Optional<AppraiseeDevelopmentGoalSetting>findById(Long dg_id);

}
