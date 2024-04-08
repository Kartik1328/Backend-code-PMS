package com.pms.Model;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.Table;

//We have imported an library for the local date. So that the date can be used as a data type.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "master_table_dummy")
public class MasterTableDummy {
    @Id
   
//    wherever @Id is written then that is the primary key or auto genrating the rows in the table.
//	where ID is written that means it is the primary key of the table and that variable is very important
//  bigint is used as Long in java
    
    
    private Long empId;
//    empID will be the primary key for the master table
    private String empName;
    private String designation;
    private String empCode;
    private String totalWorkPeriod;
//    private String department;
//    private String mgrName;
//    private String location;
//    private String eStatus;    
//    private String contactNo;
//    private String email;
    
//    private Long weightage;
//	private String kra;
//	private String goals;
//	private Long totalWeightage;
//	private String targetOperator;
//	private Long target;
    
    private LocalDate goalsSubmittedOn;
//    private String overallComments;
//    private int overallRating;
//    private String comment;
//    private String rating;
//    private Long empId;
    
    private LocalDate goalsReviewedOn;
//    private String mgrComment;
//    private String overallMgrComments;
//    private int overallMgrRating;
//    private String revertComment;
//    private String mgrRating;
//    private String mgrName;
    private Long mgrId;
    
//    private String selfAssessment;
//    private String managerAssessment;
//    private String training;
//    private String description;
//    private String goal;
    
    private LocalDate assessmentSubmittedOn;
    private LocalDate assessmentReviewedOn;

    

}


