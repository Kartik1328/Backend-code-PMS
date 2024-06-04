package com.pms.repository;
import com.pms.Model.AppraiseeGoalSettingDraft;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppraiseeGoalSettingDraftRepository extends JpaRepository<AppraiseeGoalSettingDraft,Long> {
//    void deleteByempId(Long empId);
    void deleteByEmpId(Long empId);

//    boolean existsByempId(Long empId);
    boolean existsByEmpId(Long empId);

//    List<AppraiseeGoalSettingDraft> findByempId(Long empId);
    List<AppraiseeGoalSettingDraft> findByEmpId(Long empId);

}
