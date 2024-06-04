package com.pms.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appraisee_goal_setting_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeGoalSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "data")
    @SequenceGenerator(name = "data", allocationSize = 1)
    @Column(nullable = true)

    private Long id;
    private Long empId;
    private String Kra;
    private String Goals;
    private String Measurement;
    private String Weightage;
    private Long TotalWeightage;
    private String Target;



}