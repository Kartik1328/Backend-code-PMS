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
@Table(name = "appraisee_development_goal_setting_draft_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppraiseeDevelopmentGoalSettingDraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "data")
    @SequenceGenerator(name = "data", allocationSize = 1)
    @Column(nullable = true)

    private Long id;
    private Long employeeId;
    private String training;
    private String goal;
    private String description;
    private String selfAssessment;
    private String managerAssessment;

}