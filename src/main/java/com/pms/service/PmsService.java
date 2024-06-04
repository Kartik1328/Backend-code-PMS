package com.pms.service;
import java.util.List;

import com.pms.Model.*;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

@Service
public interface PmsService {
    
    public AppraiseeSelfAssessement insert(AppraiseeSelfAssessement assessment) throws MessagingException;
    
    public AppraiseeSelfAssessementDraft insertDraft(AppraiseeSelfAssessementDraft assessment_draft);

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

	public List<MasterTableDummy> getByMgrId(Long mgrId);
//-----------------------------------------------------------------------------------------------------------

//	AMULYAS'
//	---------------------------------------------------------------------------------------------------------
//	public EmployeeStatus insert(EmployeeStatus employee_status);
	
	public List <EmployeeStatus> getemployeePerformanceDates();

//	USING THIS Api kartikey has to display dates in the status table

//	------------------------------------------------------------------------------------

//	public List<EmployeeStatus> getemployeePerformance();

//
//	public List<AppraiseeGoalSetting> getEmployeeKraByEmpId(Long empId);
//
//	public List<AppraiseeGoalSettingDraft> getEmployeeKraDraftByEmpId(Long empId);
//
//	@Transactional
//	public List<AppraiseeGoalSetting> submitAppraiseeGoalSetting(List<AppraiseeGoalSetting> appraiseeGoalSetting,Long empId) throws MessagingException;
//
//	String getmes();
//
//	void delete(Long id);
//
//	public List<AppraiseeGoalSettingDraft> getemployeeKraDraft();
//
//	public List<AppraiseeGoalSettingDraft> submitAppraiseeGoalSettingDraft(List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft);
//
//	void deletedevelopmentdraftbyempid();
//
//	public List<AppraiseeDevelopmentGoalSetting> getDevelopmentGoals();
//
//	AppraiseeDevelopmentGoalSetting insertdev(AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting);
//
//	boolean deleteDevelopmentGoal(Long empId);
//
//	public List<AppraiseeDevelopmentGoalSetting> getEmployeeDevelopmentEmpId(Long empId);
//
//	public List<AppraiseeDevelopmentGoalSettingDraft> getDevelopmentDraftGoals();
//
//	public List<AppraiseeDevelopmentGoalSettingDraft> submitAppraiseeDevelopmentGoalSettingDraft(List<AppraiseeDevelopmentGoalSettingDraft> appraiseeDevelopmentGoalSettingDraft);
//
//	public List<AppraiseeDevelopmentGoalSettingDraft> getEmployeeDevelopmentOrDraftEmpId(Long empId);
//
//	public List<PendingAppraisal> getPendingAppraisalEmpId(Long empId);
//
//	EmployeeStatus postemployeePerformance(EmployeeStatus employeeStatus);
//
//    MasterDatabase postmanagerrevertcomments(MasterDatabase masterDatabase)throws MessagingException;
//
//	public List<TrainingOption> getAllTrainingDropdown();
//
//	public List<DevelopmentOption> getAllDevelopmentGoal();
//
//}

//	public List<AppraiseeGoalSetting> getEmployeeKraByEmpId(Long empId);
//
//	public List<AppraiseeGoalSettingDraft> getEmployeeKraDraftByEmpId(Long empId);
//
//	@Transactional
//	public List<AppraiseeGoalSetting> submitAppraiseeGoalSetting(List<AppraiseeGoalSetting> appraiseeGoalSetting,Long empId) throws MessagingException;
//
//	String getmes();
//
//	void delete(Long id);
//
//	public List<AppraiseeGoalSettingDraft> getemployeeKraDraft();
//
//	public List<AppraiseeGoalSettingDraft> submitAppraiseeGoalSettingDraft(List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft);
//
//	void deletedevelopmentdraftbyempid();
//
//	public List<AppraiseeDevelopmentGoalSetting> getDevelopmentGoals();
//
//	AppraiseeDevelopmentGoalSetting insertdev(AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting);
//
//	boolean deleteDevelopmentGoal(Long Id);
//
//	public List<AppraiseeDevelopmentGoalSetting> getEmployeeDevelopmentEmpId(Long empId);
//
//	public List<AppraiseeDevelopmentGoalSettingDraft> getDevelopmentDraftGoals();
//
//	public List<AppraiseeDevelopmentGoalSettingDraft> submitAppraiseeDevelopmentGoalSettingDraft(List<AppraiseeDevelopmentGoalSettingDraft> appraiseeDevelopmentGoalSettingDraft);
//
//	public List<AppraiseeDevelopmentGoalSettingDraft> getEmployeeDevelopmentOrDraftEmpId(Long empId);
//
//	public List<PendingAppraisal> getPendingAppraisalEmpId(Long empId);
//
//	EmployeeStatus postemployeePerformance(EmployeeStatus employeeStatus);
//
//	MasterDatabase postmanagerrevertcomments(MasterDatabase masterDatabase)throws MessagingException;
//
//	public List<TrainingOption> getAllTrainingDropdown();
//
//	public List<DevelopmentOption> getAllDevelopmentGoal();
//
//}

	public List<AppraiseeGoalSetting> getEmployeeKraByEmpId(Long empId);

	public List<AppraiseeGoalSettingDraft> getEmployeeKraDraftByEmpId(Long empId);

	@Transactional
	public List<AppraiseeGoalSetting> submitAppraiseeGoalSetting(List<AppraiseeGoalSetting> appraiseeGoalSetting,Long empId) throws MessagingException;

	String getmes();

	void delete(Long id);

	public List<AppraiseeGoalSettingDraft> getemployeeKraDraft();

	public List<AppraiseeGoalSettingDraft> submitAppraiseeGoalSettingDraft(List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft);

	List<PendingAppraisal> getPendingAppraisalEmpId(Long empId);

	void deletedevelopmentdraftbyempid();

	public List<AppraiseeDevelopmentGoalSetting> getDevelopmentGoals();

	AppraiseeDevelopmentGoalSetting insertdev(AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting);

	boolean deleteDevelopmentGoal(Long Id);

	public List<AppraiseeDevelopmentGoalSetting> getEmployeeDevelopmentEmpId(Long empId);

	public List<AppraiseeDevelopmentGoalSettingDraft> getDevelopmentDraftGoals();

	public List<AppraiseeDevelopmentGoalSettingDraft> submitAppraiseeDevelopmentGoalSettingDraft(List<AppraiseeDevelopmentGoalSettingDraft> appraiseeDevelopmentGoalSettingDraft);

	public List<AppraiseeDevelopmentGoalSettingDraft> getEmployeeDevelopmentOrDraftEmpId(Long empId);


	List<EmployeeStatus> getEmployeePerformanceByEmpId(Long empId);

	EmployeeStatus postemployeePerformance(EmployeeStatus employeeStatus);

	MasterDatabase postmanagerrevertcomments(MasterDatabase masterDatabase)throws MessagingException;

	public List<TrainingOption> getAllTrainingDropdown();

	public List<DevelopmentOption> getAllDevelopmentGoal();

	//	public List<EmployeeStatus> getDataByYearAndReviewCycle(String year,String reviewCycle);
	public List<EmployeeStatus> getDataByYearAndReviewCycleAndEmpId(String year,String reviewCycle, Long empId);

//	public List<PendingAppraisal> getPendingAppraisalEmpId(Long empId,reviewCycle);

//	List<PendingAppraisal> getPendingAppraisal(Long empId, String reviewCycle);

	EmployeeStatus postSubmittedOn(Long empId);

	EmployeeStatus postReviewedOn(Long empId);

//	AppraiseeDevelopmentGoalSettingDraft getByEmpIdDevGoals(Long empId);

	AppraiseeDevelopmentGoalSetting getByEmpIdDevGoals(Long empId);

	AppraiseeDevelopmentGoalSetting postDevGoalsModified(AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting);

}

