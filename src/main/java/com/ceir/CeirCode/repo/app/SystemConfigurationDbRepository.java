package com.ceir.CeirCode.repo.app;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.app.SystemConfigurationDb;

public interface SystemConfigurationDbRepository extends JpaRepository<SystemConfigurationDb, Long> {
	public SystemConfigurationDb getByTag(String tag);
	public SystemConfigurationDb getById(Long id);
}
