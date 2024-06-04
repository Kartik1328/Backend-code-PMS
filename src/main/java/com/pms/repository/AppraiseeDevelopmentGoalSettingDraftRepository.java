package com.pms.repository;
import com.pms.Model.AppraiseeDevelopmentGoalSettingDraft;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AppraiseeDevelopmentGoalSettingDraftRepository extends JpaRepository<AppraiseeDevelopmentGoalSettingDraft, Long> {
    void deleteAll();
   // AppraiseeDevelopmentGoalSettingDraft findByEmployeeId(Long empId);
}
