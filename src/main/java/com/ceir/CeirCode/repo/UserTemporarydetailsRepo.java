package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.UserTemporarydetails;

public interface UserTemporarydetailsRepo extends JpaRepository<UserTemporarydetails, Long>{
	public UserTemporarydetails save(UserTemporarydetails details );
	public UserTemporarydetails findByUserDetails_id(long id);                 
}
