package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ceir.CeirCode.model.RequestHeaders;

public interface ReqHeadersRepo extends JpaRepository<RequestHeaders, Long> , JpaSpecificationExecutor<RequestHeaders> {

}
