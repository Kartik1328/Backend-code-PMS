package com.pms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pms.Model.MasterDatabase;

public interface MasterDatabaseRepository extends JpaRepository < MasterDatabase,Long>{
}
