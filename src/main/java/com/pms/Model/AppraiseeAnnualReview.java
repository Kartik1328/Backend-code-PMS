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
@Table(name = "appraisee_annual_review")
public class AppraiseeAnnualReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="data",allocationSize=1)

    private Long id;
//	where ID is written that means it is the primary key of the table and that variable is very important
//  here ID is for ar_id(annual review)
//  bigint is used as Long in java
    
    private String appraiseComment;
    private String managerComment;
    private String appraiseeStrength;
    private String areaOfImprovement;
    private int ratings;
    


}
