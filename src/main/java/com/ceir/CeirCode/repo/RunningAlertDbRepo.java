package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ceir.CeirCode.model.AlertDb;
import com.ceir.CeirCode.model.RunningAlertDb;
import com.ceir.CeirCode.model.UserProfile;

public interface RunningAlertDbRepo extends JpaRepository<RunningAlertDb, Long>,JpaSpecificationExecutor<RunningAlertDb>{

	public RunningAlertDb save(RunningAlertDb alertDb);
}
