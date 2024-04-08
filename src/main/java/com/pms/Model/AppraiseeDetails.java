package com.pms.Model;
import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "appraisee_details")
public class AppraiseeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="data",allocationSize=1)

    private Long id;
//    wherever @Id is written then that is the primary key
//	where ID is written that means it is the primary key of the table and that variable is very important
//  bigint is used as Long in java
    
    private String empName;
    private String designation;
    private String department;
    private String mgrName;
    private String location;
    private String eStatus;    
    private String contactNo;
    private String email;
    private String employeeId;
}


