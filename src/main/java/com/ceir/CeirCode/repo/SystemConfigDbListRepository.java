package com.ceir.CeirCode.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.SystemConfigurationDb;


public interface SystemConfigDbListRepository extends JpaRepository<SystemConfigListDb, Long> {

 
	public ArrayList<SystemConfigListDb> getByTag(String tag);

	public SystemConfigListDb getById(Long id);
    
	

} 
