package com.pms.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="appraisal_cycle_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PendingAppraisal {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id; // Remove explicit setting of id
    private Long empId;
    private String appraisalQuarter;
    private String status;
    private String periodFrom;
    private String periodTo;
    private String cycleName;

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
}
