package com.pms.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="trainings_tbl")
public class TrainingOption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="data")	
	
	private Long id;
    private String trainingName;
}
	
   
