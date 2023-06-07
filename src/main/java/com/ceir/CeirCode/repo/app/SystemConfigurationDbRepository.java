package com.ceir.CeirCode.repo.app;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.app.SystemConfigurationDb;

public interface SystemConfigurationDbRepository extends JpaRepository<SystemConfigurationDb, Long> {
	public SystemConfigurationDb getByTag(String tag);
	public SystemConfigurationDb getById(Long id);
	public List<SystemConfigurationDb> findByActiveAndTag(Integer active, String tag);
}
