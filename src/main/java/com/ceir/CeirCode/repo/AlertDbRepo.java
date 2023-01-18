package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ceir.CeirCode.model.AlertDb;
import com.ceir.CeirCode.model.RunningAlertDb;

public interface AlertDbRepo extends JpaRepository<AlertDb, Long>,JpaSpecificationExecutor<AlertDb>{

	
	public AlertDb findById(long id);
}
