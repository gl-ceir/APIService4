package com.ceir.CeirCode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.SystemConfigurationDb;

public interface SystemConfigDbRepository  extends JpaRepository<SystemConfigurationDb, Long> {

 
    
	public SystemConfigurationDb findByTag(String tag);
}
