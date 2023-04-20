package com.ceir.CeirCode.repo.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ceir.CeirCode.model.app.AlertDb;
import com.ceir.CeirCode.model.app.RunningAlertDb;

public interface AlertDbRepo extends JpaRepository<AlertDb, Long>,JpaSpecificationExecutor<AlertDb>{

	
	public AlertDb findById(long id);
}
