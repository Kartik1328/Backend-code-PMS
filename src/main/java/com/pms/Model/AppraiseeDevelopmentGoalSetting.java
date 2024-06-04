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
@Table(name = "appraisee_development_goal_setting")
public class AppraiseeDevelopmentGoalSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="data",allocationSize=1)

    private Long dg_id;
//	where ID is written that means it is the primary key of the table and that variable is very important
//  here ID is for dg_id(development goals)
//  bigint is used as Long in java
    
    
    private Long employeeId;
    private String selfAssessment;
    private String managerAssessment;
    private String training;
    private String description;
    private String goal;
}


