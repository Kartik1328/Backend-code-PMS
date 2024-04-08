package com.pms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.pms.Model.AppraiseeSelfAssessement;
import com.pms.Model.AppraiseeSelfAssessementDraft;
import com.pms.Model.EmployeeStatus;
import com.pms.Model.MasterTableDummy;

import jakarta.mail.MessagingException;

import com.pms.Model.AppraiseeAnnualReview;
import com.pms.Model.AppraiseeDetails;
import com.pms.Model.AppraiseeDevelopmentGoalSetting;
import com.pms.Model.AppraiseeKra;
import com.pms.Model.AppraiseeManagerAssessment;

@Service
public interface PMS_Service1 {
    
    public AppraiseeSelfAssessement insert(AppraiseeSelfAssessement assessment) throws MessagingException;
    
    public AppraiseeSelfAssessementDraft insert(AppraiseeSelfAssessementDraft assessment_draft);

    
    public List<AppraiseeKra> getByKra();
    
    public AppraiseeSelfAssessement getById(Long sa_id);
    
    public AppraiseeDetails getByProfileId(Long id);

    
    public AppraiseeManagerAssessment insertmng(AppraiseeManagerAssessment review);
    
    public AppraiseeManagerAssessment getByManagerId(Long ma_id);
    
    public AppraiseeAnnualReview getByAnnualId(Long empId);
    
    public AppraiseeDevelopmentGoalSetting getByDgId(Long dg_id);

	public AppraiseeDevelopmentGoalSetting insert(AppraiseeDevelopmentGoalSetting development_goals);

	public boolean deleteSelfAsmDraft(Long empid);

	public AppraiseeSelfAssessementDraft getByDraftId(Long emp_id);

	public AppraiseeAnnualReview insert(AppraiseeAnnualReview annual_review);

	public EmployeeStatus insert(EmployeeStatus employee_status);
	
	public EmployeeStatus getemployeePerformance(EmployeeStatus employee_status);


	public List<MasterTableDummy> getByMgrId(Long mgrId);

    
    
    
    
}
