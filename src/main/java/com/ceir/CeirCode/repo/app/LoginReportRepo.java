package com.ceir.CeirCode.repo.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ceir.CeirCode.model.app.UserLoginReport;

public interface LoginReportRepo extends JpaRepository<UserLoginReport, Long>,JpaSpecificationExecutor<UserLoginReport>{
}
