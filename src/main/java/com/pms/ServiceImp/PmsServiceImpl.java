package com.pms.ServiceImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.pms.Model.*;
import com.pms.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import com.pms.service.PmsService;
import jakarta.mail.MessagingException;

@Service
public class PmsServiceImpl implements PmsService {

	@Autowired
	private  PmsMailServiceImpl pmsMailServiceImpl;
	
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


//	@Autowired
//	private AppraiseeDevelopmentGoalSettingRepository appraisee_development_goal_setting_Repository;

	@Autowired
	private AppraiseeGoalSettingRepository appraiseeGoalSettingRepository;

	@Autowired
	private AppraiseeGoalSettingDraftRepository appraiseeGoalSettingDraftRepository;

	@Autowired
	private AppraiseeDevelopmentGoalSettingDraftRepository appraiseeDevelopmentGoalSettingDraftRepository;

//	@Autowired
//	private EmployeeStatusRepository employeeStatusTableRepository;

	@Autowired
	private PendingAppraisalRepository pendingAppraisalRepository;

	@Autowired
	private MasterDatabaseRepository masterDatabaseRepository;

	@Autowired
	private TrainingOptionRepository trainingOptionRepository;

	@Autowired
	private DevelopmentOptionRepository developmentOptionRepository;

	@Override
	@Transactional
	public AppraiseeSelfAssessement insert(AppraiseeSelfAssessement assessment) throws MessagingException {
		boolean hasDrafts=appraiseeSelfAssessementDraftRepository.existsByEmpId(assessment.getEmpId());
	    if (hasDrafts) {
			appraiseeSelfAssessementDraftRepository.deleteByEmpId(assessment.getEmpId());
	            appraiseeSelfAssessementRepository.save(assessment);

	    } else {
			appraiseeSelfAssessementRepository.save(assessment);
	    }
	    // Send mail
	    pmsMailServiceImpl.sendMail();
	    return assessment;
	}

    
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
//here we have taken review and manager
    }
    
//    HERE IN THIS FUNCTION WE HAVE TAKEN 1 NAME AND ALL THE THREE FUNCTIONALITIES WILL BE PERFORMED USING THAT
    
    @Override
    public AppraiseeSelfAssessementDraft insertDraft(AppraiseeSelfAssessementDraft draft) {
    	AppraiseeSelfAssessementDraft selfDraft=new AppraiseeSelfAssessementDraft();
//    	selfDraft.setReviewedOn(LocalDate.now());
		selfDraft.setSad_id(draft.getSad_id());
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
        return appraiseeSelfAssessementRepository.findByEmpId(sa_id).orElse(null);
    }
    
    @Override
    public AppraiseeSelfAssessementDraft getByDraftId(Long empId) {
        return appraiseeSelfAssessementDraftRepository.findByEmpId(empId).orElse(null);
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
		return appraiseeDevelopmentGoalSettingRepository.save(development_goals);
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

//	@Override
	public List<EmployeeStatus> getemployeePerformanceDates() {
		return employeeStatusRepository.findAll();
	}



	//	USING THIS Api kartikey has to display dates in the status table


	//	@Override

	//	@Override
	public EmployeeStatus postemployeePerformanceDates(EmployeeStatus employeeStatus) {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();
		employeeStatus.setSubmittedOn(today);
		return employeeStatusRepository.save(employeeStatus);
	}

//	----------------------------------------------Amulya's'-----------------------------------------------
//
//	@Override
///**
// * it will List the Goals set by the Employee
// * @param empId-Id of the Employee
// * @return
// */
//public List<AppraiseeGoalSetting> getEmployeeKraByEmpId(Long empId) {
//	return appraiseeGoalSettingRepository.findByempId(empId);
//}
//	/**
//	 * it will List the Goals saved in draft table
//	 * @param empId-Id of the Employee
//	 * @return
//	 */
//
//	@Override
//	public List<AppraiseeGoalSettingDraft> getEmployeeKraDraftByEmpId(Long empId) {
//		return appraiseeGoalSettingDraftRepository.findByempId(empId);
//	}
//
//	/**
//	 * it will List the Goals after submitting
//	 * @param appraiseeGoalSetting - for setting goals of the employee
//	 * @param empId-Id of the Employee
//	 * @return
//	 * @throws MessagingException-it is used for mail sending
//	 */
//
//	@Override
//	@Transactional
//	public List<AppraiseeGoalSetting> submitAppraiseeGoalSetting(List<AppraiseeGoalSetting> appraiseeGoalSetting,
//																 Long empId) throws MessagingException {
//		System.out.print(empId+"hello ......................");
//		boolean result = appraiseeGoalSettingDraftRepository.existsByempId(empId);
//
//		if (result) {
//			appraiseeGoalSettingDraftRepository.deleteByempId(empId);
//		}
//		pmsMailServiceImpl.sendKraSubmittedMail();
//		return appraiseeGoalSettingRepository.saveAll(appraiseeGoalSetting);
//	}
//
//	/**
//	 * it is used to display the message after submission
//	 * @return
//	 */
//
//	@Override
//	public String getmes() {
//		return "hello world";
//	}
//	/**
//	 * use to delete the goals
//	 * @param id-Auto-generated id
//	 */
//	@Override
//	public void delete(Long id) {
//		appraiseeGoalSettingRepository.deleteById(id);
//	}
//
//	/**
//	 * used to List the goals in Draft table
//	 * @return
//	 */
//
//	@Override
//	public List<AppraiseeGoalSettingDraft> getemployeeKraDraft() {
//		return appraiseeGoalSettingDraftRepository.findAll();
//	}
//
//	/**
//	 * it will List the Goals after saving it in draft
//	 * @param appraiseeGoalSettingDraft- for setting goals of the employee
//	 * @return
//	 */
//	@Override
//	public List<AppraiseeGoalSettingDraft> submitAppraiseeGoalSettingDraft(
//			List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft) {
//		return appraiseeGoalSettingDraftRepository.saveAll(appraiseeGoalSettingDraft);
//	}
//
//	/**
//	 * used to List the Development Goals
//	 * @return
//	 */
//	@Override
//	public List<AppraiseeDevelopmentGoalSetting> getDevelopmentGoals() {
//		return appraiseeDevelopmentGoalSettingRepository.findAll();
//	}
//
//	/**
//	 * @param appraiseeDevelopmentGoalSetting-for setting development goals of employee
//	 * @return
//	 */
//	@Override
//	public AppraiseeDevelopmentGoalSetting insertdev(AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting) {
//		AppraiseeDevelopmentGoalSetting developmentGoals = appraiseeDevelopmentGoalSettingRepository
//				.save(appraiseeDevelopmentGoalSetting);
//		return developmentGoals;
//	}
//
//	/**
//	 * used to delete the development goal
//	 * @param empId-Id of the Employee
//	 * @return
//	 */
//
//	@Override
//	public boolean deleteDevelopmentGoal(Long empId) {
//		appraiseeDevelopmentGoalSettingRepository.deleteById(empId);
//		return true;
//	}
//
//	/**
//	 * used to List the Development Goals based on EmpId
//	 * @param empId-Id of the Employee
//	 * @return
//	 */
//	@Override
//	public List<AppraiseeDevelopmentGoalSetting> getEmployeeDevelopmentEmpId(Long empId) {
//		return appraiseeDevelopmentGoalSettingRepository.findAll();
//	}
//
//	/**
//	 * used to List the Development Goals in draft table
//	 * @return
//	 */
//	@Override
//	public List<AppraiseeDevelopmentGoalSettingDraft> getDevelopmentDraftGoals() {
//		return appraiseeDevelopmentGoalSettingDraftRepository.findAll();
//	}
//
//	/**
//	 * @param appraiseeDevelopmentGoalSettingDraft-for setting draft development goals of employee
//	 * @return
//	 */
//	@Override
//	public List<AppraiseeDevelopmentGoalSettingDraft> submitAppraiseeDevelopmentGoalSettingDraft(
//			List<AppraiseeDevelopmentGoalSettingDraft> appraiseeDevelopmentGoalSettingDraft) {
//		return appraiseeDevelopmentGoalSettingDraftRepository.saveAll(appraiseeDevelopmentGoalSettingDraft);
//	}
//
//	/**
//	 * used to List either draft or development table
//	 * @param empId-Id of the Employee
//	 * @return
//	 */
//	@Override
//	public List<AppraiseeDevelopmentGoalSettingDraft> getEmployeeDevelopmentOrDraftEmpId(Long empId) {
//		return appraiseeDevelopmentGoalSettingDraftRepository.findAll();
//	}
//
//	/**
//	 * used to display the Employee status
//	 * @return
//	 */
//
//	@Override
//	public List<EmployeeStatus> getemployeePerformance() {
//		return employeeStatusRepository.findAll();
//	}
//
//	/**
//	 * @param employeeStatus-used to set the Submitted On date
//	 * @return
//	 */
//
//	@Override
//	public EmployeeStatus postemployeePerformance(EmployeeStatus employeeStatus) {
//		LocalDate today = LocalDate.now();
//		employeeStatus.setSubmittedOn(today);
//		return employeeStatusRepository.save(employeeStatus);
//	}
//
//	/**
//	 * @param employeeStatus-used to set Approved on date
//	 * @return
//	 * @throws MessagingException-used to send mail notification
//	 */
//
//	public EmployeeStatus postmanagerApproval(EmployeeStatus employeeStatus) throws MessagingException {
//		System.out.print(employeeStatus.getAppraisalCycle());
//		LocalDate today = LocalDate.now();
//		employeeStatus.setApprovedOn(today);
//		pmsMailServiceImpl.sendApprovesMail();
//		return employeeStatusRepository.save(employeeStatus);
//	}
//
//	/**
//	 * used to List the Quarter status
//	 * @param empId-id of the Employee
//	 * @return
//	 */
////
////	@Override
////	public List<PendingAppraisal> getPendingAppraisalEmpId(Long empId) {
////		LocalDate currentDate = LocalDate.now();
////		int currentYear = currentDate.getYear();
////		List<PendingAppraisal> pendingAppraisalList = new ArrayList<>();
////		List<PendingAppraisal> employeeList = pendingAppraisalRepository.findByempId(empId);
////		String[] quartersStart = { "1 Apr", "1 Jul","1 Oct", "1 Jan" };
////		String[] quartersEnd = { "30 Jun",  "30 Sep","31 Dec", "31 Mar" };
////
////		boolean foundActiveQuarter = false;
////		for (int i = 0; i < 4; i++) {
////			String quarterStart = quartersStart[i] + " " + currentYear;
////			String quarterEnd = quartersEnd[i] + " " + (i == 3 ? currentYear + 1 : currentYear);
////
////			LocalDate startDate = LocalDate.parse(quarterStart, DateTimeFormatter.ofPattern("d MMM u"));
////			LocalDate endDate = LocalDate.parse(quarterEnd, DateTimeFormatter.ofPattern("d MMM u"));
////
////			PendingAppraisal pendingAppraisal = null;
////			if (i < employeeList.size()) {
////				pendingAppraisal = employeeList.get(i);
////			} else {
////				pendingAppraisal = new PendingAppraisal();
////			}
////
////			if (i == 0 && (currentDate.getMonthValue() >= 4 && currentDate.getMonthValue() <= 6)) {
////				System.out.println("Active Quarter: " + (i + 1));
////				pendingAppraisal.setStatus("Active");
////				foundActiveQuarter = true;
////			}
////
////			else {
////				System.out.println("Pending Quarter: " + (i + 1));
////				pendingAppraisal.setStatus("Pending");
////			}
////
////			PendingAppraisal pendingAppraisalObj = pendingAppraisalRepository.save(pendingAppraisal);
////			pendingAppraisalList.add(pendingAppraisalObj);
////		}
////
////		if (!foundActiveQuarter) {
////			for (PendingAppraisal pendingAppraisal : pendingAppraisalList) {
////				pendingAppraisal.setStatus("Pending");
////				pendingAppraisalRepository.save(pendingAppraisal);
////			}
////		}
////
////		List<PendingAppraisal> pendingAppraisalList1 = pendingAppraisalRepository.findAll();
////		for (PendingAppraisal pa : pendingAppraisalList1) {
////			String periodFrom = pa.getPeriodFrom();
////			String periodTo = pa.getPeriodTo();
////			System.out.println("Period From: " + periodFrom);
////			System.out.println("Period To: " + periodTo);
////		}
////		return pendingAppraisalList;
////	}
////
//
//
//	public List<PendingAppraisal> getPendingAppraisalEmpId(Long empId) {
//		String[] quartersStart = { "1 Apr", "1 Jul" , "1 Oct", "1 Jan" };
//		String[] quartersEnd = { "30 Jun",  "31 Aug" ,"31 Dec", "31 Mar" };
//		List<PendingAppraisal> pendingAppraisalList = new ArrayList<>();
//		for (int i = 0; i < quartersStart.length; i++) {
//			LocalDate today = LocalDate.now();
//			int currentYear = today.getYear();
//			String start = quartersStart[i] + " " + currentYear;
//			String end = quartersEnd[i] + " " + currentYear;
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
//			LocalDate startDate = LocalDate.parse(start, formatter);
//			LocalDate endDate = LocalDate.parse(end, formatter);
//			boolean isWithinRange = checkIfWithinRange(startDate, endDate, today);
//			PendingAppraisal pendingAppraisal = new PendingAppraisal();
//			pendingAppraisal.setStatus(isWithinRange ? "Active" : "Pending");
//			pendingAppraisalList.add(pendingAppraisal);
//		}
//		List<PendingAppraisal> pendingAppraisal = pendingAppraisalRepository.findByEmpId(empId);
//		for (int i = 0; i < Math.min(pendingAppraisal.size(), pendingAppraisalList.size()); i++) {
//			pendingAppraisal.get(i).setStatus(pendingAppraisalList.get(i).getStatus());
//		}
//		return pendingAppraisal;
//	}
//
//	private boolean checkIfWithinRange(LocalDate startDate, LocalDate endDate, LocalDate today) {
//		return !today.isBefore(startDate) && !today.isAfter(endDate);
//	}
//
//
//
//
//	/**
//	 * delete the draft data by empId
//	 */
//
//	@Override
//	public void deletedevelopmentdraftbyempid() {
//		appraiseeDevelopmentGoalSettingDraftRepository.deleteAll();
//	}
//
//	/**
//	 *
//	 * @param masterDatabase-used to set the manager revert comments
//	 * @return
//	 * @throws MessagingException-used to send mail notifications
//	 */
//	@Override
//	public MasterDatabase postmanagerrevertcomments(MasterDatabase masterDatabase) throws MessagingException {
//
//		pmsMailServiceImpl.sendRevertBackMail(masterDatabase);
//		return masterDatabaseRepository.save(masterDatabase);
//	}
//
//	/**
//	 * use to List the training options
//	 * @return
//	 */
//	@Override
//	public List<TrainingOption> getAllTrainingDropdown() {
//		return trainingOptionRepository.findAll();
//	}
//
//	/**
//	 * use to list the Development Options
//	 * @return
//	 */
//	@Override
//	public List<DevelopmentOption> getAllDevelopmentGoal() {
//		return developmentOptionRepository.findAll();
//	}
//}
//

//	@Override
///**
// * it will List the Goals set by the Employee
// * @param empId-Id of the Employee
// * @return
// */
//	public List<AppraiseeGoalSetting> getEmployeeKraByEmpId(Long empId) {
//		return appraiseeGoalSettingRepository.findByEmpId(empId);
//	}
//	/**
//	 * it will List the Goals saved in draft table
//	 * @param empId-Id of the Employee
//	 * @return
//	 */
//
//	@Override
//	public List<AppraiseeGoalSettingDraft> getEmployeeKraDraftByEmpId(Long empId) {
//		return appraiseeGoalSettingDraftRepository.findByEmpId(empId);
//	}
//
//	/**
//	 * it will List the Goals after submitting
//	 * @param appraiseeGoalSetting - for setting goals of the employee
//	 * @param empId-Id of the Employee
//	 * @return
//	 * @throws MessagingException-it is used for mail sending
//	 */
//
////	@Override
////	@Transactional
////	public List<AppraiseeGoalSetting> submitAppraiseeGoalSetting(List<AppraiseeGoalSetting> appraiseeGoalSetting,
////																 Long empId) throws MessagingException {
////		System.out.print(empId+"hello ......................");
////		boolean result = appraiseeGoalSettingDraftRepository.existsByEmpId(empId);
////
////		if (result) {
////			appraiseeGoalSettingDraftRepository.deleteByEmpId(empId);
////			List<AppraiseeGoalSettingDraft>getAll= (List<AppraiseeGoalSettingDraft>) appraiseeGoalSettingDraftRepository.findAll();
////			return appraiseeGoalSettingRepository.saveAll(getAll);
////		}
////		else{
////			return appraiseeGoalSettingRepository.saveAll(appraiseeGoalSetting);
////		}
////		pmsMailServiceImpl.sendKraSubmittedMail();
////
////	}
//
//
//
//
//	@Override
//	@Transactional
//	public List<AppraiseeGoalSetting> submitAppraiseeGoalSetting(List<AppraiseeGoalSetting> appraiseeGoalSetting,
//																 Long empId) throws MessagingException {
//
//		boolean result = appraiseeGoalSettingDraftRepository.existsByEmpId(empId);
//		List<AppraiseeGoalSetting> savedAppraiseeGoalSettings;
//		if (result) {
//			appraiseeGoalSettingRepository.deleteByEmpId(empId);
//			List<AppraiseeGoalSettingDraft> getAll = appraiseeGoalSettingDraftRepository.findByEmpId(empId);
//			List<AppraiseeGoalSetting> goalSettingsFromDrafts = convertDraftsToGoalSettings(getAll);
//			savedAppraiseeGoalSettings = appraiseeGoalSettingRepository.saveAll(goalSettingsFromDrafts);
//			appraiseeGoalSettingDraftRepository.deleteByEmpId(empId);
//
//		} else {
//			savedAppraiseeGoalSettings = appraiseeGoalSettingRepository.saveAll(appraiseeGoalSetting);
//		}
//		pmsMailServiceImpl.sendKraSubmittedMail();
//		return savedAppraiseeGoalSettings;
//	}
//
//	private List<AppraiseeGoalSetting> convertDraftsToGoalSettings(List<AppraiseeGoalSettingDraft> drafts) {
//		List<AppraiseeGoalSetting> goalSettings = new ArrayList<>();
//		for (AppraiseeGoalSettingDraft draft : drafts) {
//			AppraiseeGoalSetting goalSetting = new AppraiseeGoalSetting();
//			goalSetting.setEmpId(draft.getEmpId());
//			goalSetting.setTarget(draft.getTarget());
//			goalSetting.setTotalWeightage(draft.getTotalWeightage());
//			goalSetting.setWeightage(draft.getWeightage());
//			goalSetting.setMeasurement(draft.getMeasurement());
//			goalSetting.setKra(draft.getKra());
//			goalSetting.setGoals(draft.getGoals());
//			goalSettings.add(goalSetting);
//		}
//		return goalSettings;
//	}
//
//	/**
//	 * it is used to display the message after submission
//	 * @return
//	 */
//
//	@Override
//	public String getmes() {
//		return "hello world";
//	}
//	/**
//	 * use to delete the goals
//	 * @param id-Auto-generated id
//	 */
//	@Override
//	public void delete(Long id) {
//		appraiseeGoalSettingRepository.deleteById(id);
//	}
//
//	/**
//	 * used to List the goals in Draft table
//	 * @return
//	 */
//
//	@Override
//	public List<AppraiseeGoalSettingDraft> getemployeeKraDraft() {
//		return appraiseeGoalSettingDraftRepository.findAll();
//	}
//
//	/**
//	 * it will List the Goals after saving it in draft
//	 * @param appraiseeGoalSettingDraft- for setting goals of the employee
//	 * @return
//	 */
//	@Override
//	public List<AppraiseeGoalSettingDraft> submitAppraiseeGoalSettingDraft(
//			List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft) {
//		return appraiseeGoalSettingDraftRepository.saveAll(appraiseeGoalSettingDraft);
//	}
//
//	/**
//	 * used to List the Development Goals
//	 * @return
//	 */
//	@Override
//	public List<AppraiseeDevelopmentGoalSetting> getDevelopmentGoals() {
//		return appraiseeDevelopmentGoalSettingRepository.findAll();
//	}
//
//	/**
//	 * @param appraiseeDevelopmentGoalSetting-for setting development goals of employee
//	 * @return
//	 */
//	@Override
//	public AppraiseeDevelopmentGoalSetting insertdev(AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting) {
//		AppraiseeDevelopmentGoalSetting developmentGoals = appraiseeDevelopmentGoalSettingRepository
//				.save(appraiseeDevelopmentGoalSetting);
//		return developmentGoals;
//	}
//
//	/**
//	 * used to delete the development goal
//	 * @param -Id of the Employee
//	 * @return
//	 */
//
//	@Override
//	public boolean deleteDevelopmentGoal(Long Id) {
//		appraiseeDevelopmentGoalSettingRepository.deleteById(Id);
//		return true;
//	}
//
//	/**
//	 * used to List the Development Goals based on EmpId
//	 * @param empId-Id of the Employee
//	 * @return
//	 */
//	@Override
//	public List<AppraiseeDevelopmentGoalSetting> getEmployeeDevelopmentEmpId(Long empId) {
//		return appraiseeDevelopmentGoalSettingRepository.findAll();
//	}
//
//	/**
//	 * used to List the Development Goals in draft table
//	 * @return
//	 */
//	@Override
//	public List<AppraiseeDevelopmentGoalSettingDraft> getDevelopmentDraftGoals() {
//		return appraiseeDevelopmentGoalSettingDraftRepository.findAll();
//	}
//
//	/**
//	 * @param appraiseeDevelopmentGoalSettingDraft-for setting draft development goals of employee
//	 * @return
//	 */
//	@Override
//	public List<AppraiseeDevelopmentGoalSettingDraft> submitAppraiseeDevelopmentGoalSettingDraft(
//			List<AppraiseeDevelopmentGoalSettingDraft> appraiseeDevelopmentGoalSettingDraft) {
//		return appraiseeDevelopmentGoalSettingDraftRepository.saveAll(appraiseeDevelopmentGoalSettingDraft);
//	}
//
//	/**
//	 * used to List either draft or development table
//	 * @param empId-Id of the Employee
//	 * @return
//	 */
//	@Override
//	public List<AppraiseeDevelopmentGoalSettingDraft> getEmployeeDevelopmentOrDraftEmpId(Long empId) {
//		return appraiseeDevelopmentGoalSettingDraftRepository.findAll();
//	}
//
//	/**
//	 * used to display the Employee status
//	 * @return
//	 */
//
//	@Override
//	public List<EmployeeStatus> getemployeePerformance() {
//		LocalDate date=LocalDate.now();
//		List<EmployeeStatus>empStatus=employeeStatusRepository.findAll();
//		for(EmployeeStatus emp:empStatus){
//			emp.setSubmittedOnn(date);
//			emp.setReviewedOnn(date);
//		}
//
//		return employeeStatusRepository.findAll();
//	}
//
//	/**
//	 * @param employeeStatus-used to set the Submitted On date
//	 * @return
//	 */
//
//	@Override
//	public EmployeeStatus postemployeePerformance(EmployeeStatus employeeStatus) {
//		LocalDate today = LocalDate.now();
//		employeeStatus.setSubmittedOn(today);
//		return employeeStatusRepository.save(employeeStatus);
//	}
//
//	/**
//	 * @param employeeStatus-used to set Approved on date
//	 * @return
//	 * @throws MessagingException-used to send mail notification
//	 */
//
//	public EmployeeStatus postmanagerApproval(EmployeeStatus employeeStatus) throws MessagingException {
//		System.out.print(employeeStatus.getAppraisalCycle());
//		LocalDate today = LocalDate.now();
//		employeeStatus.setApprovedOn(today);
//		pmsMailServiceImpl.sendApprovesMail();
//		return employeeStatusRepository.save(employeeStatus);
//	}
//
//
//	public List<PendingAppraisal> getPendingAppraisalEmpId(Long empId) {
//		String[] quartersStart = { "1 Apr", "1 Jul" , "1 Oct", "1 Jan" };
//		String[] quartersEnd = { "30 Jun",  "31 Aug" ,"31 Dec", "31 Mar" };
//		List<PendingAppraisal> pendingAppraisalList = new ArrayList<>();
//		for (int i = 0; i < quartersStart.length; i++) {
//			LocalDate today = LocalDate.now();
//			int currentYear = today.getYear();
//			String start = quartersStart[i] + " " + currentYear;
//			String end = quartersEnd[i] + " " + currentYear;
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
//			LocalDate startDate = LocalDate.parse(start, formatter);
//			LocalDate endDate = LocalDate.parse(end, formatter);
//			boolean isWithinRange = checkIfWithinRange(startDate, endDate, today);
//			PendingAppraisal pendingAppraisal = new PendingAppraisal();
//			pendingAppraisal.setStatus(isWithinRange ? "Active" : "Pending");
//			pendingAppraisalList.add(pendingAppraisal);
//		}
//		List<PendingAppraisal> pendingAppraisal = pendingAppraisalRepository.findByEmpId(empId);
//		for (int i = 0; i < Math.min(pendingAppraisal.size(), pendingAppraisalList.size()); i++) {
//			pendingAppraisal.get(i).setStatus(pendingAppraisalList.get(i).getStatus());
//		}
//		return pendingAppraisal;
//	}
//
//	private boolean checkIfWithinRange(LocalDate startDate, LocalDate endDate, LocalDate today) {
//		return !today.isBefore(startDate) && !today.isAfter(endDate);
//	}
//
//
//
//
//	/**
//	 * delete the draft data by empId
//	 */
//
//	@Override
//	public void deletedevelopmentdraftbyempid() {
//		appraiseeDevelopmentGoalSettingDraftRepository.deleteAll();
//	}
//
//	/**
//	 *
//	 * @param masterDatabase-used to set the manager revert comments
//	 * @return
//	 * @throws MessagingException-used to send mail notifications
//	 */
//	@Override
//	public MasterDatabase postmanagerrevertcomments(MasterDatabase masterDatabase) throws MessagingException {
//
//		pmsMailServiceImpl.sendRevertBackMail(masterDatabase);
//		return masterDatabaseRepository.save(masterDatabase);
//	}
//
//	/**
//	 * use to List the training options
//	 * @return
//	 */
//	@Override
//	public List<TrainingOption> getAllTrainingDropdown() {
//		return trainingOptionRepository.findAll();
//	}
//
//	/**
//	 * use to list the Development Options
//	 * @return
//	 */
//	@Override
//	public List<DevelopmentOption> getAllDevelopmentGoal() {
//		return developmentOptionRepository.findAll();
//	}
//}
//
//
@Override
/**
 * it will List the Goals set by the Employee
 * @param empId-Id of the Employee
 * @return
 */
public List<AppraiseeGoalSetting> getEmployeeKraByEmpId(Long empId) {
	return appraiseeGoalSettingRepository.findByEmpId(empId);
}
	/**
	 * it will List the Goals saved in draft table
	 * @param empId-Id of the Employee
	 * @return
	 */

	@Override
	public List<AppraiseeGoalSettingDraft> getEmployeeKraDraftByEmpId(Long empId) {
		return appraiseeGoalSettingDraftRepository.findByEmpId(empId);
	}

	/**
	 * it will List the Goals after submitting
	 * @param appraiseeGoalSetting - for setting goals of the employee
	 * @param empId-Id of the Employee
	 * @return
	 * @throws MessagingException-it is used for mail sending
	 */

//	@Override
//	@Transactional
//	public List<AppraiseeGoalSetting> submitAppraiseeGoalSetting(List<AppraiseeGoalSetting> appraiseeGoalSetting,
//																 Long empId) throws MessagingException {
//		System.out.print(empId+"hello ......................");
//		boolean result = appraiseeGoalSettingDraftRepository.existsByEmpId(empId);
//
//		if (result) {
//			appraiseeGoalSettingDraftRepository.deleteByEmpId(empId);
//			List<AppraiseeGoalSettingDraft>getAll= (List<AppraiseeGoalSettingDraft>) appraiseeGoalSettingDraftRepository.findAll();
//			return appraiseeGoalSettingRepository.saveAll(getAll);
//		}
//		else{
//			return appraiseeGoalSettingRepository.saveAll(appraiseeGoalSetting);
//		}
//		pmsMailServiceImpl.sendKraSubmittedMail();
//
//	}




	@Override
	@Transactional
	public List<AppraiseeGoalSetting> submitAppraiseeGoalSetting(List<AppraiseeGoalSetting> appraiseeGoalSetting,
																 Long empId) throws MessagingException {

		boolean result = appraiseeGoalSettingDraftRepository.existsByEmpId(empId);
		List<AppraiseeGoalSetting> savedAppraiseeGoalSettings;
		if (result) {
			appraiseeGoalSettingRepository.deleteByEmpId(empId);
			List<AppraiseeGoalSettingDraft> getAll = appraiseeGoalSettingDraftRepository.findByEmpId(empId);
			List<AppraiseeGoalSetting> goalSettingsFromDrafts = convertDraftsToGoalSettings(getAll);
			savedAppraiseeGoalSettings = appraiseeGoalSettingRepository.saveAll(goalSettingsFromDrafts);
			appraiseeGoalSettingDraftRepository.deleteByEmpId(empId);

		} else {
			savedAppraiseeGoalSettings = appraiseeGoalSettingRepository.saveAll(appraiseeGoalSetting);
		}
		pmsMailServiceImpl.sendKraSubmittedMail();
		return savedAppraiseeGoalSettings;
	}

	private List<AppraiseeGoalSetting> convertDraftsToGoalSettings(List<AppraiseeGoalSettingDraft> drafts) {
		List<AppraiseeGoalSetting> goalSettings = new ArrayList<>();
		for (AppraiseeGoalSettingDraft draft : drafts) {
			AppraiseeGoalSetting goalSetting = new AppraiseeGoalSetting();
			goalSetting.setEmpId(draft.getEmpId());
			goalSetting.setTarget(draft.getTarget());
			goalSetting.setTotalWeightage(draft.getTotalWeightage());
			goalSetting.setWeightage(draft.getWeightage());
			goalSetting.setMeasurement(draft.getMeasurement());
			goalSetting.setKra(draft.getKra());
			goalSetting.setGoals(draft.getGoals());
			goalSettings.add(goalSetting);
		}
		return goalSettings;
	}

	/**
	 * it is used to display the message after submission
	 * @return
	 */

	@Override
	public String getmes() {
		return "hello world";
	}
	/**
	 * use to delete the goals
	 * @param id-Auto-generated id
	 */
	@Override
	public void delete(Long id) {
		appraiseeGoalSettingRepository.deleteById(id);
	}

	/**
	 * used to List the goals in Draft table
	 * @return
	 */

	@Override
	public List<AppraiseeGoalSettingDraft> getemployeeKraDraft() {
		return appraiseeGoalSettingDraftRepository.findAll();
	}

	/**
	 * it will List the Goals after saving it in draft
	 * @param appraiseeGoalSettingDraft- for setting goals of the employee
	 * @return
	 */
	@Override
	public List<AppraiseeGoalSettingDraft> submitAppraiseeGoalSettingDraft(
			List<AppraiseeGoalSettingDraft> appraiseeGoalSettingDraft) {
		return appraiseeGoalSettingDraftRepository.saveAll(appraiseeGoalSettingDraft);
	}



	/**
	 * used to List the Development Goals
	 * @return
	 */
	@Override
	public List<AppraiseeDevelopmentGoalSetting> getDevelopmentGoals() {
		return appraiseeDevelopmentGoalSettingRepository.findAll();
	}

	/**
	 * @param appraiseeDevelopmentGoalSetting-for setting development goals of employee
	 * @return
	 */
	@Override
	public AppraiseeDevelopmentGoalSetting insertdev(AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting) {
		AppraiseeDevelopmentGoalSetting developmentGoals = appraiseeDevelopmentGoalSettingRepository
				.save(appraiseeDevelopmentGoalSetting);
		return developmentGoals;
	}

	/**
	 * used to delete the development goal
	 * @param -Id of the Employee
	 * @return
	 */

	@Override
	public boolean deleteDevelopmentGoal(Long Id) {
		appraiseeDevelopmentGoalSettingRepository.deleteById(Id);
		return true;
	}

	/**
	 * used to List the Development Goals based on EmpId
	 * @param empId-Id of the Employee
	 * @return
	 */
	@Override
	public List<AppraiseeDevelopmentGoalSetting> getEmployeeDevelopmentEmpId(Long empId) {
		return appraiseeDevelopmentGoalSettingRepository.findAll();
	}

	/**
	 * used to List the Development Goals in draft table
	 * @return
	 */
	@Override
	public List<AppraiseeDevelopmentGoalSettingDraft> getDevelopmentDraftGoals() {
		return appraiseeDevelopmentGoalSettingDraftRepository.findAll();
	}

	/**
	 * @param appraiseeDevelopmentGoalSettingDraft-for setting draft development goals of employee
	 * @return
	 */
	@Override
	public List<AppraiseeDevelopmentGoalSettingDraft> submitAppraiseeDevelopmentGoalSettingDraft(
			List<AppraiseeDevelopmentGoalSettingDraft> appraiseeDevelopmentGoalSettingDraft) {
		return appraiseeDevelopmentGoalSettingDraftRepository.saveAll(appraiseeDevelopmentGoalSettingDraft);
	}

	/**
	 * used to List either draft or development table
	 * @param empId-Id of the Employee
	 * @return
	 */
	@Override
	public List<AppraiseeDevelopmentGoalSettingDraft> getEmployeeDevelopmentOrDraftEmpId(Long empId) {
		return appraiseeDevelopmentGoalSettingDraftRepository.findAll();
	}


//	@Override
//	public List<EmployeeStatus> getemployeePerformance() {
//		return employeeStatusRepository.findAll();
//	}

	/**
	 * used to display the Employee status
	 * @param empId-Id of the Employee
	 * @return
	 */
	@Override
	public List<EmployeeStatus> getEmployeePerformanceByEmpId(Long empId) {
		return employeeStatusRepository.findByempId(empId);
	}

	/**
	 * @param employeeStatus-used to set the Submitted On date
	 * @return
	 */

	@Override
	public EmployeeStatus postemployeePerformance(EmployeeStatus employeeStatus) {
		LocalDate today = LocalDate.now();
		employeeStatus.setSubmittedOn(today);
		return employeeStatusRepository.save(employeeStatus);
	}

	/**
	 * @param employeeStatus-used to set Approved on date
	 * @return
	 * @throws MessagingException-used to send mail notification
	 */

	public EmployeeStatus postmanagerApproval(EmployeeStatus employeeStatus) throws MessagingException {
		System.out.print(employeeStatus.getAppraisalCycle());
		LocalDate today = LocalDate.now();
		employeeStatus.setApprovedOn(today);
		pmsMailServiceImpl.sendApprovesMail();
		return employeeStatusRepository.save(employeeStatus);
	}

	/**
	 * used to List the Quarter status
	 * @param empId-id of the Employee
	 * @return
	 */
//
//	@Override
//	public List<PendingAppraisal> getPendingAppraisalEmpId(Long empId) {
//		LocalDate currentDate = LocalDate.now();
//		int currentYear = currentDate.getYear();
//		List<PendingAppraisal> pendingAppraisalList = new ArrayList<>();
//		List<PendingAppraisal> employeeList = pendingAppraisalRepository.findByempId(empId);
//		String[] quartersStart = { "1 Apr", "1 Jul","1 Oct", "1 Jan" };
//		String[] quartersEnd = { "30 Jun",  "30 Sep","31 Dec", "31 Mar" };
//
//		boolean foundActiveQuarter = false;
//		for (int i = 0; i < 4; i++) {
//			String quarterStart = quartersStart[i] + " " + currentYear;
//			String quarterEnd = quartersEnd[i] + " " + (i == 3 ? currentYear + 1 : currentYear);
//
//			LocalDate startDate = LocalDate.parse(quarterStart, DateTimeFormatter.ofPattern("d MMM u"));
//			LocalDate endDate = LocalDate.parse(quarterEnd, DateTimeFormatter.ofPattern("d MMM u"));
//
//			PendingAppraisal pendingAppraisal = null;
//			if (i < employeeList.size()) {
//				pendingAppraisal = employeeList.get(i);
//			} else {
//				pendingAppraisal = new PendingAppraisal();
//			}
//
//			if (i == 0 && (currentDate.getMonthValue() >= 4 && currentDate.getMonthValue() <= 6)) {
//				System.out.println("Active Quarter: " + (i + 1));
//				pendingAppraisal.setStatus("Active");
//				foundActiveQuarter = true;
//			}
//
//			else {
//				System.out.println("Pending Quarter: " + (i + 1));
//				pendingAppraisal.setStatus("Pending");
//			}
//
//			PendingAppraisal pendingAppraisalObj = pendingAppraisalRepository.save(pendingAppraisal);
//			pendingAppraisalList.add(pendingAppraisalObj);
//		}
//
//		if (!foundActiveQuarter) {
//			for (PendingAppraisal pendingAppraisal : pendingAppraisalList) {
//				pendingAppraisal.setStatus("Pending");
//				pendingAppraisalRepository.save(pendingAppraisal);
//			}
//		}
//
//		List<PendingAppraisal> pendingAppraisalList1 = pendingAppraisalRepository.findAll();
//		for (PendingAppraisal pa : pendingAppraisalList1) {
//			String periodFrom = pa.getPeriodFrom();
//			String periodTo = pa.getPeriodTo();
//			System.out.println("Period From: " + periodFrom);
//			System.out.println("Period To: " + periodTo);
//		}
//		return pendingAppraisalList;
//	}
//


//	public List<PendingAppraisal> getPendingAppraisalEmpId(Long empId,String reviewCycle) {
//		String[] quartersStart = { "1 Apr", "1 Jul" , "1 Oct", "1 Jan" };
//		String[] quartersEnd = { "30 Jun",  "31 Aug" ,"31 Dec", "31 Mar" };
//		List<PendingAppraisal> pendingAppraisalList = new ArrayList<>();
//		for (int i = 0; i < quartersStart.length; i++) {
//			LocalDate today = LocalDate.now();
//			int currentYear = today.getYear();
//			String start = quartersStart[i] + " " + currentYear;
//			String end = quartersEnd[i] + " " + currentYear;
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
//			LocalDate startDate = LocalDate.parse(start, formatter);
//			LocalDate endDate = LocalDate.parse(end, formatter);
//			boolean isWithinRange = checkIfWithinRange(startDate, endDate, today);
//			PendingAppraisal pendingAppraisal = new PendingAppraisal();
//			pendingAppraisal.setStatus(isWithinRange ? "Active" : "Pending");
//			pendingAppraisalList.add(pendingAppraisal);
//		}
//		List<PendingAppraisal> pendingAppraisal = pendingAppraisalRepository.findByEmpIdAndReviewCycle(year,reviewCycle);
//		for (int i = 0; i < Math.min(pendingAppraisal.size(), pendingAppraisalList.size()); i++) {
//			pendingAppraisal.get(i).setStatus(pendingAppraisalList.get(i).getStatus());
//		}
//		return pendingAppraisal;
//	}
//
//	private boolean checkIfWithinRange(LocalDate startDate, LocalDate endDate, LocalDate today) {
//		return !today.isBefore(startDate) && !today.isAfter(endDate);
//	}
	public List<PendingAppraisal> getPendingAppraisalEmpId(Long empId) {
		String[] quartersStart = { "1 Apr", "1 Jul" , "1 Oct", "1 Jan" };
		String[] quartersEnd = { "30 Jun",  "31 Aug" ,"31 Dec", "31 Mar" };
		List<PendingAppraisal> pendingAppraisalList = new ArrayList<>();
		for (int i = 0; i < quartersStart.length; i++) {
			LocalDate today = LocalDate.now();
			int currentYear = today.getYear();
			String start = quartersStart[i] + " " + currentYear;
			String end = quartersEnd[i] + " " + currentYear;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
			LocalDate startDate = LocalDate.parse(start, formatter);
			LocalDate endDate = LocalDate.parse(end, formatter);
			boolean isWithinRange = checkIfWithinRange(startDate, endDate, today);
			PendingAppraisal pendingAppraisal = new PendingAppraisal();
			pendingAppraisal.setStatus(isWithinRange ? "Active" : "Pending");
			pendingAppraisalList.add(pendingAppraisal);
		}
		List<PendingAppraisal> pendingAppraisal = pendingAppraisalRepository.findByEmpId(empId);
		for (int i = 0; i < Math.min(pendingAppraisal.size(), pendingAppraisalList.size()); i++) {
			pendingAppraisal.get(i).setStatus(pendingAppraisalList.get(i).getStatus());
		}
		return pendingAppraisal;
	}

	private boolean checkIfWithinRange(LocalDate startDate, LocalDate endDate, LocalDate today) {
		return !today.isBefore(startDate) && !today.isAfter(endDate);
	}




	/**
	 * delete the draft data by empId
	 */

	@Override
	public void deletedevelopmentdraftbyempid() {
		appraiseeDevelopmentGoalSettingDraftRepository.deleteAll();
	}

	/**
	 *
	 * @param masterDatabase-used to set the manager revert comments
	 * @return
	 * @throws MessagingException-used to send mail notifications
	 */
	@Override
	public MasterDatabase postmanagerrevertcomments(MasterDatabase masterDatabase) throws MessagingException {

		pmsMailServiceImpl.sendRevertBackMail(masterDatabase);
		return masterDatabaseRepository.save(masterDatabase);
	}

	/**
	 * use to List the training options
	 * @return
	 */
	@Override
	public List<TrainingOption> getAllTrainingDropdown() {
		return trainingOptionRepository.findAll();
	}

	/**
	 * use to list the Development Options
	 * @return
	 */
	@Override
	public List<DevelopmentOption> getAllDevelopmentGoal() {
		return developmentOptionRepository.findAll();
	}




//	@Override
//	public List<EmployeeStatus> getDataByYearAndReviewCycle(String year, String reviewCycle) {
//		return employeeStatusRepository.findByYearAndReviewCycle(year,reviewCycle);
//	}

	@Override
	public List<EmployeeStatus> getDataByYearAndReviewCycleAndEmpId(String year, String reviewCycle, Long empId) {
		return employeeStatusRepository.findByYearAndReviewCycleAndEmpId(year, reviewCycle, empId);
	}

	@Override
	public EmployeeStatus postSubmittedOn(Long empId) {
		LocalDate date= LocalDate.now();
		System.out.print(date+"date");
		String p=date.toString();
		System.out.println(p+"p");
		String newMonth=p.split("-")[1];
		System.out.println(newMonth);


		String[] q1 = {"01", "02", "03"};
		String[] q2 = {"04", "05", "06"};
		String[] q3 = {"07", "08", "09"};
		String[] q4 = {"10", "11", "12"};

		ArrayList<String> quarter1 = new ArrayList<>(Arrays.asList(q1));
		ArrayList<String> quarter2 = new ArrayList<>(Arrays.asList(q2));
		ArrayList<String> quarter3 = new ArrayList<>(Arrays.asList(q3));
		ArrayList<String> quarter4 = new ArrayList<>(Arrays.asList(q4));

		String specMonth="";

		if(quarter1.contains(newMonth)){
			specMonth="	Quarter 1";
		}
		else if(quarter2.contains(newMonth)){
			specMonth="Quarter 2";
		}
		else if(quarter3.contains(newMonth)){
			specMonth="Quarter 3";
		}
		else if(quarter4.contains(newMonth)){
			specMonth="Quarter 4";
		}
		EmployeeStatus updatedStatus = null;
		List<EmployeeStatus>employeeStatuses=employeeStatusRepository.findByempId(empId);
		System.out.println(employeeStatuses+"employeeStatuses");
		System.out.println(specMonth+"specMonth");
		//System.out.println(emp.getAppraisalCycle()+"emp.getAppraisalCycle()");
		for(EmployeeStatus emp:employeeStatuses){
			System.out.println(emp.getAppraisalCycle()+"app....");
			if(emp.getAppraisalCycle().equals(specMonth)){
				System.out.println(emp.getAppraisalCycle());
				System.out.println(specMonth);
				emp.setSubmittedOnn(LocalDate.now());
			}
			employeeStatusRepository.save(emp);
			updatedStatus = emp;

		}
		return updatedStatus;
	}




	@Override
	public EmployeeStatus postReviewedOn(Long empId) {
		LocalDate date= LocalDate.now();
		System.out.print(date+"date");
		String p=date.toString();
		System.out.println(p+"p");
		String newMonth=p.split("-")[1];
		System.out.println(newMonth);


		String[] q1 = {"01", "02", "03"};
		String[] q2 = {"04", "05", "06"};
		String[] q3 = {"07", "08", "09"};
		String[] q4 = {"10", "11", "12"};

		ArrayList<String> quarter1 = new ArrayList<>(Arrays.asList(q1));
		ArrayList<String> quarter2 = new ArrayList<>(Arrays.asList(q2));
		ArrayList<String> quarter3 = new ArrayList<>(Arrays.asList(q3));
		ArrayList<String> quarter4 = new ArrayList<>(Arrays.asList(q4));

		String specMonth="";

		if(quarter1.contains(newMonth)){
			specMonth="	Quarter 1";
		}
		else if(quarter2.contains(newMonth)){
			specMonth="Quarter 2";
		}
		else if(quarter3.contains(newMonth)){
			specMonth="Quarter 3";
		}
		else if(quarter4.contains(newMonth)){
			specMonth="Quarter 4";
		}
		EmployeeStatus updatedStatus = null;
		List<EmployeeStatus>employeeStatuses=employeeStatusRepository.findByempId(empId);
		System.out.println(employeeStatuses+"employeeStatuses");
		System.out.println(specMonth+"specMonth");
		//System.out.println(emp.getAppraisalCycle()+"emp.getAppraisalCycle()");
		for(EmployeeStatus emp:employeeStatuses){
			System.out.println(emp.getAppraisalCycle()+"app....");
			if(emp.getAppraisalCycle().equals(specMonth)){
				System.out.println(emp.getAppraisalCycle());
				System.out.println(specMonth);
				emp.setReviewedOnn(LocalDate.now());
			}
			employeeStatusRepository.save(emp);
			updatedStatus = emp;

		}
		return updatedStatus;
	}

	@Override
	public AppraiseeDevelopmentGoalSetting getByEmpIdDevGoals(Long empId) {
		AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting=appraiseeDevelopmentGoalSettingRepository.findByEmployeeId(empId);
		return appraiseeDevelopmentGoalSetting;
	}

	@Override
	public AppraiseeDevelopmentGoalSetting postDevGoalsModified(AppraiseeDevelopmentGoalSetting appraiseeDevelopmentGoalSetting) {
		return appraiseeDevelopmentGoalSettingRepository.save(appraiseeDevelopmentGoalSetting);
	}


}
