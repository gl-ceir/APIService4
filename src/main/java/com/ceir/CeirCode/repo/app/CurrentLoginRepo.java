package com.ceir.CeirCode.repo.app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.app.CurrentLogin;
import com.ceir.CeirCode.model.app.LoginTracking;

public interface CurrentLoginRepo extends JpaRepository<CurrentLogin,Long>{
	public CurrentLogin save(CurrentLogin currentLogin);
	public boolean existsByCurrentUserLogin_Id(long id);
	public List<CurrentLogin> findByCurrentUserLogin_Id(long id);
	public void deleteByCurrentUserLogin_Id(long id);
}