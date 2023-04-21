package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.UserTypeUrlMapping;

public interface UserTypeUrlMappingRepository extends JpaRepository<UserTypeUrlMapping, Long>{
	public UserTypeUrlMapping findByUserTypeIdAndUrlPathLike(Long userTypeId, String urlPath);
}
