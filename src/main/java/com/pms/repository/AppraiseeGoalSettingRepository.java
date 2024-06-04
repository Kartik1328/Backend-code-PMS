package com.pms.repository;
import com.pms.Model.AppraiseeGoalSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppraiseeGoalSettingRepository  extends JpaRepository<AppraiseeGoalSetting,Long> {
    void deleteByEmpId(Long id);
    List<AppraiseeGoalSetting> findByEmpId(Long empId);
}
