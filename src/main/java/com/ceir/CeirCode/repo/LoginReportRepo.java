package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ceir.CeirCode.model.UserLoginReport;

public interface LoginReportRepo extends JpaRepository<UserLoginReport, Long>,JpaSpecificationExecutor<UserLoginReport>{
}
