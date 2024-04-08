package com.pms.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.*;

@Entity
@Table(name="kra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AppraiseeKra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator="data")
	@SequenceGenerator(name="data",allocationSize=1)
	
	private Long id;
	private Long weightage;
	private String kra;
	private String goals;
	private Long totalWeightage;
	private String targetOperator;
	private Long target;


}