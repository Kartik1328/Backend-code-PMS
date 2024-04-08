package com.pms.controller;
import java.util.List;

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
import com.pms.Model.AppraiseeSelfAssessement;
import com.pms.Model.AppraiseeSelfAssessementDraft;
import com.pms.Model.EmployeeStatus;
import com.pms.Model.AppraiseeAnnualReview;
import com.pms.Model.AppraiseeKra;
import com.pms.Model.AppraiseeManagerAssessment;
import com.pms.Model.MasterTableDummy;
import com.pms.ServiceImp.PMS_ServiceImpl1;

import jakarta.mail.MessagingException;

import com.pms.Model.AppraiseeDetails;
import com.pms.Model.AppraiseeDevelopmentGoalSetting;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class PMSController1 {
    
    @Autowired
    private PMS_ServiceImpl1 pms_ServiceImpl1;
    
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
    public ResponseEntity<?> insert(@RequestBody AppraiseeSelfAssessementDraft assessment_draft) {
        AppraiseeSelfAssessementDraft savedAssessment = pms_ServiceImpl1.insert(assessment_draft);
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
    
//    HERE I HAVE IMPLEMENTED THE EMAIL TRIGGER IN THE SELF ASSSESSEMNT
    
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
    public List<EmployeeStatus> getemployeePerformance() {
        return pms_ServiceImpl1.getemployeePerformance();
    }
//    since List is used therefore no particular Id is needed to be written in the API
	
	@PostMapping("/postByEmployeeStatus")
    public EmployeeStatus postemployeePerformance(@RequestBody EmployeeStatus employeeStatus)  {
		EmployeeStatus status = pms_ServiceImpl1.postemployeePerformance(employeeStatus);
        return status;
    }
	
	 @PostMapping("/postManagerApproval")
	    public EmployeeStatus postmanagerApproval(@RequestBody EmployeeStatus employeeStatus) throws MessagingException {  
		 EmployeeStatus status = pms_ServiceImpl1.postmanagerApproval(employeeStatus); 
	        return status;
	    }
}
