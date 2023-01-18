package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.LoginTracking;

public interface LoginTrackingRepo extends JpaRepository<LoginTracking, Integer>{

	public LoginTracking save(LoginTracking loginTracking);
}
