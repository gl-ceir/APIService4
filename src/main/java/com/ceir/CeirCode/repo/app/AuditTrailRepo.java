package com.ceir.CeirCode.repo.app;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.app.AuditTrail;

public interface AuditTrailRepo extends JpaRepository<AuditTrail,Long>{

	
}
