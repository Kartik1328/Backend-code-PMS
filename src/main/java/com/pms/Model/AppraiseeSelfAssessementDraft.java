package com.pms.Model;
import jakarta.persistence.*;
import lombok.*;
//import java.time.LocalDate;
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
@Table(name = "appraisee_self_assessement_draft")
public class AppraiseeSelfAssessementDraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="data",allocationSize=1)

    private Long sad_id;
//	where ID is written that means it is the primary key of the table and that variable is very important
//  here ID is for sad_id(self assessment draft)
//  bigint is used as Long in java
    
//    private LocalDate submittedOn;
    private String overallComments;
    private int overallRating;
    private String comment;
    private String rating;
    private String selfAssessment;
    private Long empId;
}


