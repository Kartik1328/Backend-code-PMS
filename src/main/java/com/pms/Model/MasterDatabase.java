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
@Table(name="master_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MasterDatabase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="data")	
	private Long id;
    private Long empId;
    private String mgrRevertComments;
}


