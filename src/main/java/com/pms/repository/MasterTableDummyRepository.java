package com.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.Model.MasterTableDummy;

public interface MasterTableDummyRepository extends JpaRepository<MasterTableDummy,Long> {
	
	List<MasterTableDummy>findByMgrId(Long mgr_id);
	



}
