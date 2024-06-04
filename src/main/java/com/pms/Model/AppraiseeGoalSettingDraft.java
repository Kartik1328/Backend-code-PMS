package com.pms.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="appraisee_goal_setting_draft_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeGoalSettingDraft {

    private static long idCounter = 0;
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "data")
    @SequenceGenerator(name = "data", allocationSize = 1)
    @Column(nullable = true)
    @Id
    private Long id;
    private Long empId;
    private String Kra;
    private String Goals;
    private String Weightage;
    private String Measurement;
    private Long TotalWeightage;
    private String Target;

    public static long getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(long idCounter) {
        AppraiseeGoalSettingDraft.idCounter = idCounter;
    }

}