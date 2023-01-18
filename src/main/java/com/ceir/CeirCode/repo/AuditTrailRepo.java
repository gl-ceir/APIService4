package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.AuditTrail;

public interface AuditTrailRepo extends JpaRepository<AuditTrail,Long>{

	
}
