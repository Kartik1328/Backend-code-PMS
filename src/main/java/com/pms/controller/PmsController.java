package com.pms.controller;
import java.util.List;
import com.pms.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pms.ServiceImpl.PmsServiceImpl;
import jakarta.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class PmsController {
    @Autowired
    private PmsServiceImpl pms_ServiceImpl1;
    
//    CONTROLLER "GET API" FOR MASTER DUMMY TABLE
    
    @GetMapping("/getByMasterDummy/{mgr_id}")
    public ResponseEntity<?> getByMgrId(@PathVariable Long mgr_id){
        List<MasterTableDummy> data = pms_ServiceImpl1.getByMgrId(mgr_id);
        if(!data.isEmpty()) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
        }
    }
     
    @GetMapping("/getBySelfAsm/{emp_id}")
    public ResponseEntity<?> getById(@PathVariable Long emp_id){
        AppraiseeSelfAssessement assessment = pms_ServiceImpl1.getById(emp_id);
        if(assessment != null) {
            return ResponseEntity.ok(assessment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
        }
    }

    @GetMapping("/getBySelfAsmDraft/{emp_id}")
    public ResponseEntity<?> getBydraftId(@PathVariable Long emp_id){
        AppraiseeSelfAssessementDraft assessment = pms_ServiceImpl1.getByDraftId(emp_id);
        if(assessment != null) {
            return ResponseEntity.ok(assessment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
        }
    }
    
    @GetMapping("/getByProfile/{id}")
    public ResponseEntity<?> getByProfile(@PathVariable Long id){
        AppraiseeDetails details = pms_ServiceImpl1.getByProfileId(id);
        if(details != null) {
            return ResponseEntity.ok(details);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
        }
    }
    
    @GetMapping("/getBySelfAsmAll")
    public ResponseEntity<?> getByKra(){
        List<AppraiseeKra> assessment = pms_ServiceImpl1.getByKra();
        if(!assessment.isEmpty()) {
            return ResponseEntity.ok(assessment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
        }
    }

    @PostMapping("/postBySelfAsm")
    public ResponseEntity<?> insert(@RequestBody AppraiseeSelfAssessement assessment) throws MessagingException {
        AppraiseeSelfAssessement savedAssessment = pms_ServiceImpl1.insert(assessment);
        if(savedAssessment != null) {
            return ResponseEntity.ok("inserted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not inserted");
        }
    }

    @PostMapping("/postBySelfAsmDraft")
    public ResponseEntity<?> insertDraft(@RequestBody AppraiseeSelfAssessementDraft assessment_draft) {
        AppraiseeSelfAssessementDraft savedAssessment = pms_ServiceImpl1.insertDraft(assessment_draft);
        if(savedAssessment != null) {
            return ResponseEntity.ok("inserted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not inserted");
        }
    }
    
    @DeleteMapping("/deleteSelfAsmDraft/{sad_id}")
    public ResponseEntity<String> deleteSelfAsmDraft(@PathVariable Long sad_id) {
        boolean deleted = pms_ServiceImpl1.deleteSelfAsmDraft(sad_id);
        if (deleted) {
            return ResponseEntity.ok("Self Assessement draft data successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Draft data not found");
        }
    }
    
//    HERE I HAVE IMPLEMENTED THE EMAIL TRIGGER IN THE SELF ASSSESSEMNT.
    
    @PostMapping("/postByMng")
    public ResponseEntity<?> insert(@RequestBody AppraiseeManagerAssessment manager_assessement){
    	AppraiseeManagerAssessment savedAssessment = pms_ServiceImpl1.insertmng(manager_assessement);
        if(savedAssessment != null) {
            return ResponseEntity.ok("inserted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not inserted");
        }
    }

    @GetMapping("/getByManagerAsm/{ma_id}")
    public ResponseEntity<?> getByManagerId(@PathVariable Long ma_id){
        AppraiseeManagerAssessment assessment = pms_ServiceImpl1.getByManagerId(ma_id);
        if(assessment != null) {
            return ResponseEntity.ok(assessment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
        }
    }
    
    @GetMapping("/getByAnnual/{ar_id}")
    public ResponseEntity<?> getByAnnualId(@PathVariable Long ar_id){
    	System.out.print(ar_id);
        AppraiseeAnnualReview review = pms_ServiceImpl1.getByAnnualId(ar_id);
        if(review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
        }
    }
    
    @PostMapping("/postByAnnRev")
    public ResponseEntity<?> insert(@RequestBody AppraiseeAnnualReview annual_review){
    	System.out.print("hello");
    	System.out.print(annual_review+"....");

    	AppraiseeAnnualReview savedAnnualReview = pms_ServiceImpl1.insert(annual_review);
        if(savedAnnualReview != null) {
            return ResponseEntity.ok("inserted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not inserted");
        }
    }
    
    @GetMapping("/getByDevGoals/{dg_id}")
    public ResponseEntity<?> getByDg(@PathVariable Long dg_id){
        AppraiseeDevelopmentGoalSetting devgoals = pms_ServiceImpl1.getByDgId(dg_id);
        if(devgoals != null) {
            return ResponseEntity.ok(devgoals);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not fetched");
        }
    }
    
    @PostMapping("/postByDg")
    public ResponseEntity<?> insert(@RequestBody AppraiseeDevelopmentGoalSetting development_goals){
    	
    	System.out.print(development_goals+"....");
    	AppraiseeDevelopmentGoalSetting savedDevelopmentGoals = pms_ServiceImpl1.insert(development_goals);
        if(savedDevelopmentGoals != null) {
            return ResponseEntity.ok("inserted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not inserted");
        }
    }
    
    //NOW THE APIs FOR THE EMPLOYEE STATUS TABLE
	@GetMapping("/getByEmployeeStatus")
	public List<EmployeeStatus> getemployeePerformanceDates() {
		return pms_ServiceImpl1.getemployeePerformanceDates();
	}
//    since List is used therefore no particular id is needed to be written in the API.


	@PostMapping("/postemployeePerformance")
	public EmployeeStatus postemployeePerformance(@RequestBody EmployeeStatus employeeStatus)  {
		EmployeeStatus status = pms_ServiceImpl1.postemployeePerformance(employeeStatus);
		return status;
	}


	@PostMapping("/postByEmployeeStatus")
	public EmployeeStatus postemployeePerformanceDates(@RequestBody EmployeeStatus employeeStatus)  {
		EmployeeStatus status = pms_ServiceImpl1.postemployeePerformanceDates(employeeStatus);
		return status;
	}

//	 Amulya's APIs
// 	 ---------------------------------------------------------------------------------------------------------------------

//	@PostMapping("/postmanagerApproval")
//	    public EmployeeStatus postmanagerApproval(@RequestBody EmployeeStatus employeeStatus) throws MessagingException {
//		System.out.println("hello ++++++++++++++++");
//		 EmployeeStatus status = pms_ServiceImpl1.postmanagerApproval(employeeStatus);
//	        return status;
//	    }
//
//	 @GetMapping("/getdevelopmentGoals")
//	    public List<AppraiseeDevelopmentGoalSetting> getDevelopmentGoals() {
//	        return pms_ServiceImpl1.getDevelopmentGoals();
//	    }
//
//		@GetMapping("/getDevelopmentDraftGoals")
//		 public List<AppraiseeDevelopmentGoalSettingDraft> getDevelopmentDraftGoals() {
//		     return pms_ServiceImpl1.getDevelopmentDraftGoals();
//		 }
//
//		@GetMapping("/getemployeeKraId/{empId}")
//		public List<AppraiseeGoalSetting> getEmployeeKraByEmpId(@PathVariable Long empId) {
//	        return pms_ServiceImpl1.getEmployeeKraByEmpId(empId);
//	    }
//
//		@GetMapping("/getemployeeKraDraft")
//		public List< AppraiseeGoalSettingDraft> getemployeeKraDraft(){
//			return pms_ServiceImpl1.getemployeeKraDraft ();
//		}
//
//		@GetMapping("/getemployeeKraDraft/{empId}")
//		public List<AppraiseeGoalSettingDraft> getEmployeeKraDraftByEmpId(@PathVariable Long empId){
//			return pms_ServiceImpl1.getEmployeeKraDraftByEmpId(empId);
//		}
//
//		@GetMapping("/getemployeeKraOrDraft/{emp_id}")
//		public ResponseEntity<Map<String, Object>> getEmployeeKraOrDraftByEmpId(@PathVariable("emp_id") Long empId) {
//		    Map<String, Object> responseData = new HashMap<>();
//		    boolean isDraft;
//
//		    List<AppraiseeGoalSettingDraft> kraGoalDraftData = pms_ServiceImpl1.getEmployeeKraDraftByEmpId(empId);
//		    System.out.print(kraGoalDraftData);
//		   	    if (kraGoalDraftData != null && !kraGoalDraftData.isEmpty()) {
//		   	     isDraft= true;
//		        responseData.put("data", kraGoalDraftData);
//		        responseData.put("isDraft", isDraft);
//		    } else {
//		        isDraft = false;
//		        List<AppraiseeGoalSetting> kraGoalData = pms_ServiceImpl1.getEmployeeKraByEmpId(empId);
//		        responseData.put("data", kraGoalData);
//		        responseData.put("isDraft", isDraft);
//		    }
//		    return ResponseEntity.ok(responseData);
//		}
//
//		@GetMapping("/getemployeeDevelopmentOrDraft/{emp_id}")
//		public ResponseEntity<Map<String, Object>> getEmployeeDevelopmentOrDraft(@PathVariable("emp_id") Long empId) {
//		    Map<String, Object> responseData = new HashMap<>();
//		    boolean developmentDraft;
//		    List<AppraiseeDevelopmentGoalSettingDraft> developmentDraftData = pms_ServiceImpl1.getEmployeeDevelopmentOrDraftEmpId(empId);
//		    System.out.print(developmentDraftData);
//
//		    if (developmentDraftData != null && !developmentDraftData.isEmpty()) {
//		        developmentDraft = true;
//		        responseData.put("data", developmentDraftData);
//		        responseData.put("developmentDraft", developmentDraft);
//		    } else {
//		        developmentDraft = false;
//		        List<AppraiseeDevelopmentGoalSetting> developmentData = pms_ServiceImpl1.getEmployeeDevelopmentEmpId(empId);
//		        responseData.put("data", developmentData);
//		        responseData.put("developmentDraft", developmentDraft);
//		    }
//		    return ResponseEntity.ok(responseData);
//		}
//
//		@GetMapping("/getmess")
//		public String getmes() {
//			return pms_ServiceImpl1.getmes();
//		}
//
//		@GetMapping("/getemployeePerformance")
//	    public List<EmployeeStatus> getemployeePerformance() {
//	        return pms_ServiceImpl1.getemployeePerformance();
//	    }
//
//		@GetMapping("/getPendingAppraisal/{empId}")
//		public List<PendingAppraisal> getPendingAppraisal(@PathVariable Long empId){
//			System.out.println(empId+"empId");
//			return pms_ServiceImpl1.getPendingAppraisalEmpId(empId);
//		}
//
//	    @GetMapping("/getTrainingOption")
//	    public List<TrainingOption> getAllTrainings(){
//		return pms_ServiceImpl1.getAllTrainingDropdown();
//	    }
//
//		@GetMapping("/getDevelopmentOption")
//	    public List<DevelopmentOption> getAllDevelopmentGoal(){
//		return pms_ServiceImpl1.getAllDevelopmentGoal();
//	    }
//
//		@PostMapping("/postemployeeKra/{empId}")
//		public List<AppraiseeGoalSetting> submitAppraiseeGoalSettingDraft(@RequestBody List<AppraiseeGoalSetting> appraiseeGoalSetting,@PathVariable Long empId) throws MessagingException {
//			return (List<AppraiseeGoalSetting>)pms_ServiceImpl1.submitAppraiseeGoalSetting(appraiseeGoalSetting,empId);
//		}
//		@PostMapping("/postemployeeKraDrafts")
//		public List<AppraiseeGoalSettingDraft> insertDraft(@RequestBody List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft){
//			return (List<AppraiseeGoalSettingDraft>)pms_ServiceImpl1.submitAppraiseeGoalSettingDraft(appraiseeGoalSettingDraft);
//		}
//
//		@PostMapping("/postemployeedevelopment")
//	    public AppraiseeDevelopmentGoalSetting insertdev(@RequestBody AppraiseeDevelopmentGoalSetting  appraiseeDevelopmentGoalSetting)  {
//			System.out.print(appraiseeDevelopmentGoalSetting+"developmentModel");
//	        return pms_ServiceImpl1.insertdev(appraiseeDevelopmentGoalSetting);
//	    }
//
//		@PostMapping("/postemployeedevelopmentDraft")
//	    public List<AppraiseeDevelopmentGoalSettingDraft> insertDraftt(@RequestBody List<AppraiseeDevelopmentGoalSettingDraft> appraiseeDevelopmentGoalSettingDraft) {
//	        return (List<AppraiseeDevelopmentGoalSettingDraft>)pms_ServiceImpl1.submitAppraiseeDevelopmentGoalSettingDraft(appraiseeDevelopmentGoalSettingDraft);
//	    }
//
//		@PostMapping("/postemployeeKraDraft")
//		public List<AppraiseeGoalSettingDraft> submitAppraiseeGoalSettingDraft(@RequestBody List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft){
//			return (List<AppraiseeGoalSettingDraft>)pms_ServiceImpl1.submitAppraiseeGoalSettingDraft(appraiseeGoalSettingDraft);
//		}
//
//		 @PostMapping("/postmanagerrevertcomments")
//			public MasterDatabase postmanagerrevertcomments(@RequestBody MasterDatabase masterDatabase) throws MessagingException {
//
//			    MasterDatabase savedMasterDatabase = pms_ServiceImpl1.postmanagerrevertcomments(masterDatabase);
//			    return savedMasterDatabase;
//			}
//
//
//		 @DeleteMapping("/deletedevelopmentdraftbyempid/{empId}")
//			public void deletedevelopmentdraftbyempid(@PathVariable Long empId ){
//			 pms_ServiceImpl1.deletedevelopmentdraftbyempid();
//			}
//
//			@DeleteMapping("/deleteDevelopmentGoal/{empid}")
//		    public ResponseEntity<String> deleteDevelopmentGoal(@PathVariable Long empid) {
//		        boolean deleted = pms_ServiceImpl1.deleteDevelopmentGoal(empid);
//		       if (deleted) {
//		            return ResponseEntity.ok("Development goal deleted successfully");
//		        } else {
//		            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Development goal not found");
//		        }
//		    }
//
//			@DeleteMapping("/deleteEmployeeKraOrDraft/{id}")
//			public ResponseEntity<String> deleteEmployeeKraOrDraft(@PathVariable Long id) {
//				List<AppraiseeGoalSettingDraft> kraDraftData = pms_ServiceImpl1.getemployeeKraDraft();
//				//pmskradraftService.delete(id);
//			    try {
//			        if (kraDraftData!=null && !kraDraftData.isEmpty()) {
//			        	pms_ServiceImpl1.delete(id);
//			        	pms_ServiceImpl1.delete(id);
//			        } else {
//			        	pms_ServiceImpl1.delete(id);
//			        }
//		         return ResponseEntity.ok("Data deleted successfully");
//			    } catch (Exception e) {
//			        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting data: " + e.getMessage());
//			    }
//			}
//
//
//}

//	@PostMapping("/postmanagerApproval")
//	public EmployeeStatus postmanagerApproval(@RequestBody EmployeeStatus employeeStatus) throws MessagingException {
//		System.out.println("hello ++++++++++++++++");
//		EmployeeStatus status = pms_ServiceImpl1.postmanagerApproval(employeeStatus);
//		return status;
//	}
//
//	@GetMapping("/getdevelopmentGoals")
//	public List<AppraiseeDevelopmentGoalSetting> getDevelopmentGoals() {
//		return pms_ServiceImpl1.getDevelopmentGoals();
//	}
//
//	@GetMapping("/getDevelopmentDraftGoals")
//	public List<AppraiseeDevelopmentGoalSettingDraft> getDevelopmentDraftGoals() {
//		return pms_ServiceImpl1.getDevelopmentDraftGoals();
//	}
//
//	@GetMapping("/getemployeeKraId/{empId}")
//	public List<AppraiseeGoalSetting> getEmployeeKraByEmpId(@PathVariable Long empId) {
//		return pms_ServiceImpl1.getEmployeeKraByEmpId(empId);
//	}
//
//	@GetMapping("/getemployeeKraDraft")
//	public List< AppraiseeGoalSettingDraft> getemployeeKraDraft(){
//		return pms_ServiceImpl1.getemployeeKraDraft ();
//	}
//
//	@GetMapping("/getemployeeKraDraft/{empId}")
//	public List<AppraiseeGoalSettingDraft> getEmployeeKraDraftByEmpId(@PathVariable Long empId){
//		return pms_ServiceImpl1.getEmployeeKraDraftByEmpId(empId);
//	}
//
//	@GetMapping("/getemployeeKraOrDraft/{emp_id}")
//	public ResponseEntity<Map<String, Object>> getEmployeeKraOrDraftByEmpId(@PathVariable("emp_id") Long empId) {
//		Map<String, Object> responseData = new HashMap<>();
//		boolean isDraft;
//
//		List<AppraiseeGoalSettingDraft> kraGoalDraftData = pms_ServiceImpl1.getEmployeeKraDraftByEmpId(empId);
//		System.out.print(kraGoalDraftData);
//		if (kraGoalDraftData != null && !kraGoalDraftData.isEmpty()) {
//			isDraft= true;
//			responseData.put("data", kraGoalDraftData);
//			responseData.put("isDraft", isDraft);
//		} else {
//			isDraft = false;
//			List<AppraiseeGoalSetting> kraGoalData = pms_ServiceImpl1.getEmployeeKraByEmpId(empId);
//			responseData.put("data", kraGoalData);
//			responseData.put("isDraft", isDraft);
//		}
//		return ResponseEntity.ok(responseData);
//	}
//
//	@GetMapping("/getemployeeDevelopmentOrDraft/{emp_id}")
//	public ResponseEntity<Map<String, Object>> getEmployeeDevelopmentOrDraft(@PathVariable("emp_id") Long empId) {
//		Map<String, Object> responseData = new HashMap<>();
//		boolean developmentDraft;
//		List<AppraiseeDevelopmentGoalSettingDraft> developmentDraftData = pms_ServiceImpl1.getEmployeeDevelopmentOrDraftEmpId(empId);
//		System.out.print(developmentDraftData);
//
//		if (developmentDraftData != null && !developmentDraftData.isEmpty()) {
//			developmentDraft = true;
//			responseData.put("data", developmentDraftData);
//			responseData.put("developmentDraft", developmentDraft);
//		} else {
//			developmentDraft = false;
//			List<AppraiseeDevelopmentGoalSetting> developmentData = pms_ServiceImpl1.getEmployeeDevelopmentEmpId(empId);
//			responseData.put("data", developmentData);
//			responseData.put("developmentDraft", developmentDraft);
//		}
//		return ResponseEntity.ok(responseData);
//	}
//
//	@GetMapping("/getmess")
//	public String getmes() {
//		return pms_ServiceImpl1.getmes();
//	}
//
//	@GetMapping("/getemployeePerformance")
//	public List<EmployeeStatus> getemployeePerformance() {
//		return pms_ServiceImpl1.getemployeePerformance();
//	}
//
//	@GetMapping("/getPendingAppraisal/{empId}")
//	public List<PendingAppraisal> getPendingAppraisal(@PathVariable Long empId){
//		System.out.println(empId+"empId");
//		return pms_ServiceImpl1.getPendingAppraisalEmpId(empId);
//	}
//
//	@GetMapping("/getTrainingOption")
//	public List<TrainingOption> getAllTrainings(){
//		return pms_ServiceImpl1.getAllTrainingDropdown();
//	}
//
//	@GetMapping("/getDevelopmentOption")
//	public List<DevelopmentOption> getAllDevelopmentGoal(){
//		return pms_ServiceImpl1.getAllDevelopmentGoal();
//	}
//
//	@PostMapping("/postemployeeKra/{empId}")
//	public List<AppraiseeGoalSetting> submitAppraiseeGoalSettingDraft(@RequestBody List<AppraiseeGoalSetting> appraiseeGoalSetting,@PathVariable Long empId) throws MessagingException {
//		System.out.println(empId+"empId");
//		return (List<AppraiseeGoalSetting>)pms_ServiceImpl1.submitAppraiseeGoalSetting(appraiseeGoalSetting,empId);
//	}
//	@PostMapping("/postemployeeKraDrafts")
//	public List<AppraiseeGoalSettingDraft> insertDraft(@RequestBody List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft){
//		return (List<AppraiseeGoalSettingDraft>)pms_ServiceImpl1.submitAppraiseeGoalSettingDraft(appraiseeGoalSettingDraft);
//	}
//
//
//
//	@PostMapping("/postemployeedevelopment")
//	public AppraiseeDevelopmentGoalSetting insertdev(@RequestBody AppraiseeDevelopmentGoalSetting  appraiseeDevelopmentGoalSetting)  {
//		System.out.print(appraiseeDevelopmentGoalSetting+"developmentModel");
//		return pms_ServiceImpl1.insertdev(appraiseeDevelopmentGoalSetting);
//	}
//
//	@PostMapping("/postemployeedevelopmentDraft")
//	public List<AppraiseeDevelopmentGoalSettingDraft> insertDraftt(@RequestBody List<AppraiseeDevelopmentGoalSettingDraft> appraiseeDevelopmentGoalSettingDraft) {
//		return (List<AppraiseeDevelopmentGoalSettingDraft>)pms_ServiceImpl1.submitAppraiseeDevelopmentGoalSettingDraft(appraiseeDevelopmentGoalSettingDraft);
//	}
//
//	@PostMapping("/postemployeeKraDraft")
//	public List<AppraiseeGoalSettingDraft> submitAppraiseeGoalSettingDraft(@RequestBody List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft){
//		return (List<AppraiseeGoalSettingDraft>)pms_ServiceImpl1.submitAppraiseeGoalSettingDraft(appraiseeGoalSettingDraft);
//	}
//
//	@PostMapping("/postmanagerrevertcomments")
//	public MasterDatabase postmanagerrevertcomments(@RequestBody MasterDatabase masterDatabase) throws MessagingException {
//
//		MasterDatabase savedMasterDatabase = pms_ServiceImpl1.postmanagerrevertcomments(masterDatabase);
//		return savedMasterDatabase;
//	}
//
//
//	@DeleteMapping("/deletedevelopmentdraftbyempid/{empId}")
//	public void deletedevelopmentdraftbyempid(@PathVariable Long empId ){
//		pms_ServiceImpl1.deletedevelopmentdraftbyempid();
//	}
//
//	@DeleteMapping("/deleteDevelopmentGoal/{id}")
//	public ResponseEntity<String> deleteDevelopmentGoal(@PathVariable Long id) {
//		boolean deleted = pms_ServiceImpl1.deleteDevelopmentGoal(id);
//		if (deleted) {
//			return ResponseEntity.ok("Development goal deleted successfully");
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Development goal not found");
//		}
//	}
//
//	@DeleteMapping("/deleteEmployeeKraOrDraft/{id}")
//	public ResponseEntity<String> deleteEmployeeKraOrDraft(@PathVariable Long id) {
//		List<AppraiseeGoalSettingDraft> kraDraftData = pms_ServiceImpl1.getemployeeKraDraft();
//		//pmskradraftService.delete(id);
//		try {
//			if (kraDraftData!=null && !kraDraftData.isEmpty()) {
//				pms_ServiceImpl1.delete(id);
//				// pms_ServiceImpl1.delete(id);
//			} else {
//				pms_ServiceImpl1.delete(id);
//			}
//			return ResponseEntity.ok("Data deleted successfully");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting data: " + e.getMessage());
//		}
//	}
//
//
//}

	@PostMapping("/postmanagerApproval")
	public EmployeeStatus postmanagerApproval(@RequestBody EmployeeStatus employeeStatus) throws MessagingException {
		System.out.println("hello ++++++++++++++++");
		EmployeeStatus status = pms_ServiceImpl1.postmanagerApproval(employeeStatus);
		return status;
	}

	@GetMapping("/getdevelopmentGoals")
	public List<AppraiseeDevelopmentGoalSetting> getDevelopmentGoals() {
		return pms_ServiceImpl1.getDevelopmentGoals();
	}

	@GetMapping("/getDevelopmentDraftGoals")
	public List<AppraiseeDevelopmentGoalSettingDraft> getDevelopmentDraftGoals() {
		return pms_ServiceImpl1.getDevelopmentDraftGoals();
	}

	@GetMapping("/getemployeeKraId/{empId}")
	public List<AppraiseeGoalSetting> getEmployeeKraByEmpId(@PathVariable Long empId) {
		return pms_ServiceImpl1.getEmployeeKraByEmpId(empId);
	}

	@GetMapping("/getemployeeKraDraft")
	public List<AppraiseeGoalSettingDraft> getemployeeKraDraft() {
		return pms_ServiceImpl1.getemployeeKraDraft();
	}

	@GetMapping("/getemployeeKraDraft/{empId}")
	public List<AppraiseeGoalSettingDraft> getEmployeeKraDraftByEmpId(@PathVariable Long empId) {
		return pms_ServiceImpl1.getEmployeeKraDraftByEmpId(empId);
	}

	@GetMapping("/getemployeeKraOrDraft/{emp_id}")
	public ResponseEntity<Map<String, Object>> getEmployeeKraOrDraftByEmpId(@PathVariable("emp_id") Long empId) {
		Map<String, Object> responseData = new HashMap<>();
		boolean isDraft;

		List<AppraiseeGoalSettingDraft> kraGoalDraftData = pms_ServiceImpl1.getEmployeeKraDraftByEmpId(empId);
		System.out.print(kraGoalDraftData);
		if (kraGoalDraftData != null && !kraGoalDraftData.isEmpty()) {
			isDraft = true;
			responseData.put("data", kraGoalDraftData);
			responseData.put("isDraft", isDraft);
		} else {
			isDraft = false;
			List<AppraiseeGoalSetting> kraGoalData = pms_ServiceImpl1.getEmployeeKraByEmpId(empId);
			responseData.put("data", kraGoalData);
			responseData.put("isDraft", isDraft);
		}
		return ResponseEntity.ok(responseData);
	}

	@GetMapping("/getemployeeDevelopmentOrDraft/{emp_id}")
	public ResponseEntity<Map<String, Object>> getEmployeeDevelopmentOrDraft(@PathVariable("emp_id") Long empId) {
		Map<String, Object> responseData = new HashMap<>();
		boolean developmentDraft;
		List<AppraiseeDevelopmentGoalSettingDraft> developmentDraftData = pms_ServiceImpl1.getEmployeeDevelopmentOrDraftEmpId(empId);
		System.out.print(developmentDraftData);

		if (developmentDraftData != null && !developmentDraftData.isEmpty()) {
			developmentDraft = true;
			responseData.put("data", developmentDraftData);
			responseData.put("developmentDraft", developmentDraft);
		} else {
			developmentDraft = false;
			List<AppraiseeDevelopmentGoalSetting> developmentData = pms_ServiceImpl1.getEmployeeDevelopmentEmpId(empId);
			responseData.put("data", developmentData);
			responseData.put("developmentDraft", developmentDraft);
		}
		return ResponseEntity.ok(responseData);
	}

	@GetMapping("/getmess")
	public String getmes() {
		return pms_ServiceImpl1.getmes();
	}


//    @GetMapping("/getemployeePerformance")
//    public List<EmployeeStatus> getemployeePerformance() {
//        return pms_ServiceImpl1.getemployeePerformance();
//    }

	@GetMapping("/getemployeePerformance/{empId}")
	public List<EmployeeStatus> getEmployeeStatusByEmpId(@PathVariable Long empId) {
		return pms_ServiceImpl1.getEmployeePerformanceByEmpId(empId);
	}

	@GetMapping("/getPendingAppraisal/{empId}")
	public List<PendingAppraisal> getPendingAppraisal(@PathVariable Long empId) {
		System.out.println(empId + "empId");
		return pms_ServiceImpl1.getPendingAppraisalEmpId(empId);
	}

	@GetMapping("/getTrainingOption")
	public List<TrainingOption> getAllTrainings() {
		return pms_ServiceImpl1.getAllTrainingDropdown();
	}

	@GetMapping("/getDevelopmentOption")
	public List<DevelopmentOption> getAllDevelopmentGoal() {
		return pms_ServiceImpl1.getAllDevelopmentGoal();
	}

	@PostMapping("/postemployeeKra/{empId}")
	public List<AppraiseeGoalSetting> submitAppraiseeGoalSettingDraft(@RequestBody List<AppraiseeGoalSetting> appraiseeGoalSetting, @PathVariable Long empId) throws MessagingException {
		System.out.println(empId + "empId");
		return (List<AppraiseeGoalSetting>) pms_ServiceImpl1.submitAppraiseeGoalSetting(appraiseeGoalSetting, empId);
	}

	@PostMapping("/postemployeeKraDrafts")
	public List<AppraiseeGoalSettingDraft> insertDraft(@RequestBody List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft) {
		return (List<AppraiseeGoalSettingDraft>) pms_ServiceImpl1.submitAppraiseeGoalSettingDraft(appraiseeGoalSettingDraft);
	}


	@PostMapping("/postemployeedevelopment")
	public AppraiseeDevelopmentGoalSetting insertdev(@RequestBody AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting) {
		System.out.print(appraiseeDevelopmentGoalSetting + "developmentModel");
		return pms_ServiceImpl1.insertdev(appraiseeDevelopmentGoalSetting);
	}

	@PostMapping("/postemployeedevelopmentDraft")
	public List<AppraiseeDevelopmentGoalSettingDraft> insertDraftt(@RequestBody List<AppraiseeDevelopmentGoalSettingDraft> appraiseeDevelopmentGoalSettingDraft) {
		return (List<AppraiseeDevelopmentGoalSettingDraft>) pms_ServiceImpl1.submitAppraiseeDevelopmentGoalSettingDraft(appraiseeDevelopmentGoalSettingDraft);
	}

	@PostMapping("/postemployeeKraDraft")
	public List<AppraiseeGoalSettingDraft> submitAppraiseeGoalSettingDraft(@RequestBody List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft) {
		return (List<AppraiseeGoalSettingDraft>) pms_ServiceImpl1.submitAppraiseeGoalSettingDraft(appraiseeGoalSettingDraft);
	}

	@PostMapping("/postmanagerrevertcomments")
	public MasterDatabase postmanagerrevertcomments(@RequestBody MasterDatabase masterDatabase) throws MessagingException {

		MasterDatabase savedMasterDatabase = pms_ServiceImpl1.postmanagerrevertcomments(masterDatabase);
		return savedMasterDatabase;
	}


	@DeleteMapping("/deletedevelopmentdraftbyempid/{empId}")
	public void deletedevelopmentdraftbyempid(@PathVariable Long empId) {
		pms_ServiceImpl1.deletedevelopmentdraftbyempid();
	}

	@DeleteMapping("/deleteDevelopmentGoal/{id}")
	public ResponseEntity<String> deleteDevelopmentGoal(@PathVariable Long id) {
		boolean deleted = pms_ServiceImpl1.deleteDevelopmentGoal(id);
		if (deleted) {
			return ResponseEntity.ok("Development goal deleted successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Development goal not found");
		}
	}

	@DeleteMapping("/deleteEmployeeKraOrDraft/{id}")
	public ResponseEntity<String> deleteEmployeeKraOrDraft(@PathVariable Long id) {
		List<AppraiseeGoalSettingDraft> kraDraftData = pms_ServiceImpl1.getemployeeKraDraft();
		//pmskradraftService.delete(id);
		try {
			if (kraDraftData != null && !kraDraftData.isEmpty()) {
				pms_ServiceImpl1.delete(id);
				// pms_ServiceImpl1.delete(id);
			} else {
				pms_ServiceImpl1.delete(id);
			}
			return ResponseEntity.ok("Data deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting data: " + e.getMessage());
		}
	}


	@GetMapping("/getByYearAndReviewCycle/{year}/{reviewCycle}/{empId}")
	public ResponseEntity<?> getDataByYearAndReviewCycle(@PathVariable ("year") String year,@PathVariable ("reviewCycle") String reviewCycle, @PathVariable ("empId") Long empId) {
		List<EmployeeStatus> allEmpStatus = pms_ServiceImpl1.getDataByYearAndReviewCycleAndEmpId(year, reviewCycle, empId);
		if (!allEmpStatus.isEmpty()) {
			return ResponseEntity.ok(allEmpStatus);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found based on year and review cycle");
		}
	}



	@GetMapping("/setSubmittedOn/{empId}")
	public ResponseEntity<?>postSubmittedOn(@PathVariable Long empId){
		EmployeeStatus employeeStatus=pms_ServiceImpl1.postSubmittedOn(empId);
		if(employeeStatus!=null){
			return ResponseEntity.ok("submittedOnn Sucessfully");
		}
		else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not submitted");
		}
	}



	@GetMapping("/setReviewedOn/{empId}")
	public ResponseEntity<?>postReviewedOn(@PathVariable Long empId){
		EmployeeStatus employeeStatus=pms_ServiceImpl1.postReviewedOn(empId);
		if(employeeStatus!=null){
			return ResponseEntity.ok("ReviewedOnn Sucessfully");
		}
		else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not submitted");
		}
	}



	@GetMapping("/getByDevGoalByEmpId/{empId}")
	public ResponseEntity<?>getByDevGoalByEmpId(@PathVariable Long empId){
		AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting=pms_ServiceImpl1.getByEmpIdDevGoals(empId);
		if(appraiseeDevelopmentGoalSetting!=null){
			return ResponseEntity.ok(appraiseeDevelopmentGoalSetting);
		}
		else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found devGoals By given EmpId");
		}
	}


	@PostMapping("/postDevGoalByEmpId")
	public ResponseEntity<?>postDevGoalByEmpId(@RequestBody AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting){
		AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSet=pms_ServiceImpl1.postDevGoalsModified(appraiseeDevelopmentGoalSetting);
		if(appraiseeDevelopmentGoalSet!=null){
			return ResponseEntity.ok(appraiseeDevelopmentGoalSet);
		}
		else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not posted by devGoals");
		}
	}

}

