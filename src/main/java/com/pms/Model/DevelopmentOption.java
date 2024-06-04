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
@Table(name="developmentoption_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DevelopmentOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="data")
	private Long id;
    private String deepTechnical;
    private String functional;
    private String na;
    private String softSkills;
    private String productTesting;
}
