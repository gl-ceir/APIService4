package com.ceir.CeirCode.repoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.PeriodValidate;
import com.ceir.CeirCode.model.UserToStakehoderfeatureMapping;
import com.ceir.CeirCode.repo.UserToStakehoderfeatureMappingRepo;
@Service
public class UserFeatureRepoService {

	@Autowired
	UserToStakehoderfeatureMappingRepo userfeatureRepo;
	
	public UserToStakehoderfeatureMapping getByUsertypeIdAndFeatureId(PeriodValidate periodValidate) 
	{
		try 
		{
			return userfeatureRepo.findByUserTypeFeature_IdAndStakeholderFeature_Id(periodValidate.getUsertypeId(),periodValidate.getFeatureId());
		}
		catch(Exception e) {
			return null;
		}
		
	}
}
