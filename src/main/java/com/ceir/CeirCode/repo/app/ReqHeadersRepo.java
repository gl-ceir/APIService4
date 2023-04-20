package com.ceir.CeirCode.repo.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ceir.CeirCode.model.app.RequestHeaders;

public interface ReqHeadersRepo extends JpaRepository<RequestHeaders, Long> , JpaSpecificationExecutor<RequestHeaders> {

}
