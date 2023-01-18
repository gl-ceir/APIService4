package com.ceir.CeirCode.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.SystemConfigurationDb;

public interface SystemConfigurationDbRepository extends JpaRepository<SystemConfigurationDb, Long> {
	public SystemConfigurationDb getByTag(String tag);
	public SystemConfigurationDb getById(Long id);
}
