package com.ceir.CeirCode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceir.CeirCode.model.Securityquestion;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserSecurityquestion;

public interface UserSecurityQuestionRepo extends JpaRepository<UserSecurityquestion, Integer>{

	public UserSecurityquestion save(UserSecurityquestion userSecurityquestion); 
	public UserSecurityquestion findByUser_IdAndSecurityQuestion_IdAndAnswer(long userId,long questionId,String answer);
	    
}
