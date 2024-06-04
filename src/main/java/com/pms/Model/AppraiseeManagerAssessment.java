package com.pms.Model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

//We have imported a library for the local date. So that the date can be used as a data type.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appraisee_manager_assessment")
public class AppraiseeManagerAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="data",allocationSize=1)

    private Long id;
//	 where ID is written that means it is the primary key of the table and that variable is very important
//   here ID is for ma_id(manager assessment)
//   bigint is used as Long in java
    
    private LocalDate reviewedOn;
    private String mgrComment;
    private String overallMgrComments;
    private int overallMgrRating;
    private String revertComment;
    private String mgrRating;
    private String mgrName;
    private Long mgrId;
//    A FOREIGN KEY IS THERE IN THIS TABLE
}
