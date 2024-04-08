package com.pms.ServiceImp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pms.Model.AppraiseeAnnualReview;
import com.pms.Model.AppraiseeDetails;
import com.pms.Model.AppraiseeDevelopmentGoalSetting;
import com.pms.Model.AppraiseeKra;
import com.pms.Model.AppraiseeManagerAssessment;
import com.pms.Model.AppraiseeSelfAssessement;
import com.pms.Model.AppraiseeSelfAssessementDraft;
import com.pms.Model.EmployeeStatus;
import com.pms.Model.MasterTableDummy;
import com.pms.repository.AppraiseeAnnualReviewRepository;
import com.pms.repository.AppraiseeDetailsRepository;
import com.pms.repository.AppraiseeDevelopmentGoalSettingRepository;
import com.pms.repository.AppraiseeKraRepository;
import com.pms.repository.AppraiseeManagerAssessmentRepository;
import com.pms.repository.AppraiseeSelfAssessementRepository;
import com.pms.repository.AppraiseeSelfAssessmentDraftRepository;
import com.pms.repository.EmployeeStatusRepository;
import com.pms.repository.MasterTableDummyRepository;
import com.pms.service.PMS_Service1;

import jakarta.mail.MessagingException;

@Service
public class PMS_ServiceImpl1 implements PMS_Service1 { 
	
	@Autowired
	private EmployeeStatusRepository employeeStatusRepository;
	
	@Autowired
    private AppraiseeSelfAssessementRepository appraiseeSelfAssessementRepository;
	
	@Autowired
    private MasterTableDummyRepository masterTableDummyRepository;
	
	@Autowired
    private AppraiseeSelfAssessmentDraftRepository appraiseeSelfAssessementDraftRepository;
	
	@Autowired
    private AppraiseeDevelopmentGoalSettingRepository appraiseeDevelopmentGoalSettingRepository;
	
	@Autowired
    private AppraiseeManagerAssessmentRepository appraiseeManagerAssessementRepository;
	
	@Autowired
    private AppraiseeAnnualReviewRepository appraiseeAnnualReviewRepository;
	
	@Autowired
	private AppraiseeKraRepository appraiseeKraRepository;
	
	@Autowired
	private AppraiseeDetailsRepository appraiseeDetailsRepository;
	
	@Autowired
	MailServiceImpl mailServiceImpl;
	
	@Autowired
	private AppraiseeDevelopmentGoalSettingRepository appraisee_development_goal_setting_Repository;
	
	@Override
	public AppraiseeSelfAssessement insert(AppraiseeSelfAssessement assessment) throws MessagingException {

	    // Check if there are any drafts
	    boolean hasDrafts = appraiseeSelfAssessementDraftRepository.count() > 0;

	    if (hasDrafts) {
	        List<AppraiseeSelfAssessementDraft> drafts = appraiseeSelfAssessementDraftRepository.findAll();
	        
	        for (AppraiseeSelfAssessementDraft draft : drafts) {
	            AppraiseeSelfAssessement self = new AppraiseeSelfAssessement();
	            self.setSubmittedOn(LocalDate.now());
	            self.setOverallComments(draft.getOverallComments());
	            self.setOverallRating(draft.getOverallRating());
	            self.setComment(draft.getComment());
	            self.setRating(draft.getRating());
	            self.setEmpId(draft.getEmpId());
	                              
	            // Save to appraiseeSelfAssessementRepository
	            appraiseeSelfAssessementRepository.save(self);
	        }
	    } else {
	        AppraiseeSelfAssessement self = new AppraiseeSelfAssessement();
	        self.setSubmittedOn(LocalDate.now());
	        self.setOverallComments(assessment.getOverallComments());
	        self.setOverallRating(assessment.getOverallRating());
	        self.setComment(assessment.getComment());
	        self.setRating(assessment.getRating());
	        self.setEmpId(assessment.getEmpId());
	        
	        // Save to appraiseeSelfAssessementRepository
	        appraiseeSelfAssessementRepository.save(self);
	    }

	    // Delete data from appraiseeSelfAssessementDraftRepository
	    appraiseeSelfAssessementDraftRepository.deleteAll();

	    // Send mail
	    mailServiceImpl.sendMail();

	    // Return the saved assessment
	    return assessment;
	}


//    @Override
//    public AppraiseeSelfAssessement insert(AppraiseeSelfAssessement assessment) throws MessagingException {
//    	
//    	if (!appraiseeSelfAssessementDraftRepository.isEmpty()) {
//    		appraiseeSelfAssessementDraftRepository.
//    	}
//    	AppraiseeSelfAssessement self=new AppraiseeSelfAssessement();
//    	self.setSubmittedOn(LocalDate.now());
//    	self.setOverallComments(assessment.getOverallComments());
//    	self.setOverallRating(assessment.getOverallRating());
//    	self.setComment(assessment.getComment());
//    	self.setRating(assessment.getRating());
//    	self.setEmpId(assessment.getEmpId());
//    	
//		mailServiceImpl.sendMail();
//    	
//        return appraiseeSelfAssessementRepository.save(self);
////        here we have taken assessement and self
//    }
    
    @Override
    public AppraiseeManagerAssessment insertmng(AppraiseeManagerAssessment review) {
    	AppraiseeManagerAssessment manager=new AppraiseeManagerAssessment();
    	manager.setReviewedOn(LocalDate.now());
    	manager.setOverallMgrComments(review.getOverallMgrComments());
    	manager.setOverallMgrRating(review.getOverallMgrRating());
    	manager.setMgrComment(review.getMgrComment());
    	manager.setMgrRating(review.getMgrRating());
    	manager.setMgrId(review.getMgrId());
        return appraiseeManagerAssessementRepository.save(manager);
//      here we have taken review and manager

    }
    
//    HERE IN THIS FUNCTION WE HAVE TAKEN 1 NAME AND ALL THE THREE FUNCTIONALITIES WILL BE PERFORMED USING THAT
    
    @Override
    public AppraiseeSelfAssessementDraft insert(AppraiseeSelfAssessementDraft draft) {
    	AppraiseeSelfAssessementDraft selfDraft=new AppraiseeSelfAssessementDraft();
//    	selfDraft.setReviewedOn(LocalDate.now());
    	selfDraft.setOverallComments(draft.getOverallComments());
    	selfDraft.setOverallRating(draft.getOverallRating());
    	selfDraft.setComment(draft.getComment());
    	selfDraft.setRating(draft.getRating());
    	selfDraft.setEmpId(draft.getEmpId());
    	selfDraft.setSelfAssessment(draft.getSelfAssessment());
        return appraiseeSelfAssessementDraftRepository.save(selfDraft);
//      here we have taken review and manager

    }
//     this is for the master dummy table
   
    
    @Override
    public List<AppraiseeKra> getByKra(){
    	List<AppraiseeKra> selfAll=appraiseeKraRepository.findAll();
    	return selfAll;
    }
    
//    HERE I HAVE TAKEN ALL BECAUSE THE DATA IS IN THE FORM OF THE ARRAY OR THE LIST.
    
    @Override
    public AppraiseeDevelopmentGoalSetting getByDgId(Long dg_id) {
        return appraiseeDevelopmentGoalSettingRepository.findById(dg_id).orElse(null);
    }
    
    @Override
    public AppraiseeSelfAssessement getById(Long sa_id) {
        return appraiseeSelfAssessementRepository.findByempId(sa_id).orElse(null);
    }
    
    @Override
    public AppraiseeSelfAssessementDraft getByDraftId(Long empId) {
        return appraiseeSelfAssessementDraftRepository.findByempId(empId).orElse(null);
    }
    
    
    
    @Override
    public AppraiseeDetails getByProfileId(Long id) {
        Optional<AppraiseeDetails>app=appraiseeDetailsRepository.findById(id);
        return app.orElse(null);
    }
    

    @Override
    public AppraiseeManagerAssessment getByManagerId(Long ma_id) {
        return appraiseeManagerAssessementRepository.findBymgrId(ma_id).orElse(null);
    }

    @Override
    public AppraiseeAnnualReview getByAnnualId(Long empId) {
        return appraiseeAnnualReviewRepository.findById(empId).orElse(null);
    }
    
    @Override
	public AppraiseeAnnualReview insert(AppraiseeAnnualReview annual_review) {
		
		return appraiseeAnnualReviewRepository.save(annual_review);
	}

	@Override
	public AppraiseeDevelopmentGoalSetting insert(AppraiseeDevelopmentGoalSetting development_goals) {
		
		return appraisee_development_goal_setting_Repository.save(development_goals);
	}
	
	@Override
    public boolean deleteSelfAsmDraft(Long empId) {
        appraiseeSelfAssessementDraftRepository.deleteById(empId);
        return true;
    }


	@Override
	public List<MasterTableDummy> getByMgrId(Long mgrId) {
		
		return masterTableDummyRepository.findByMgrId(mgrId);
	}

 //THIS IS FOR THE EMPLOYEE STATUS TABLE.
	public List<EmployeeStatus> getemployeePerformance() {
		return employeeStatusRepository.findAll();
	}

	public EmployeeStatus postemployeePerformance(EmployeeStatus employeeStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	public EmployeeStatus postmanagerApproval(EmployeeStatus employeeStatus) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public EmployeeStatus insert(EmployeeStatus employee_status) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public EmployeeStatus getemployeePerformance(EmployeeStatus employee_status) {
		// TODO Auto-generated method stub
		return null;
	}
    
   
}
