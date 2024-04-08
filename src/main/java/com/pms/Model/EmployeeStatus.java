package com.pms.Model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

//We have imported an library for the local date. So that the date can be used as a data type.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_status")
public class EmployeeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="data",allocationSize=1)

    private Long Id;

//	where ID is written that means it is the primary key of the table and that variable is very important
//  here ID is for sa_id(self assessment)
//  bigint is used as Long in java
    
    
   	private Long empId;
	private String appraisalCycle;
	private String subForApproval;
	private LocalDate submittedOn;
	private String managerApproval;
	private  LocalDate approvedOn;
	//these two local dates are for goal setting and its approval
	
	private String selfReview;
	private  LocalDate submittedOnn;
	private  String manager;
	private  LocalDate reviewedOnn;
	//these two local dates are for the feedback/assessment and its approval
	
	private String managerReview;
	private  String currentStatus;
	private String status;
	private Long year;
	private LocalDate reviewCycle;
	//these two local dates are for fetching the year and review cycle
	
}


