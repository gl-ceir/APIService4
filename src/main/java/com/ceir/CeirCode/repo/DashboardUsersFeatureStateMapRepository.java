package com.ceir.CeirCode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.DashboardUsersFeatureStateMap;

public interface DashboardUsersFeatureStateMapRepository extends JpaRepository< DashboardUsersFeatureStateMap, Long>{
	public List<DashboardUsersFeatureStateMap> findByUserTypeIdAndFeatureId( Integer userTypeId, Integer featureId );
}
