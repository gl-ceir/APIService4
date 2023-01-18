package com.ceir.CeirCode.repoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.StatesInterpretationDb;
import com.ceir.CeirCode.repo.StatesInterpretaionRepository;


@Service
public class StateInterupRepoService {

	@Autowired
	StatesInterpretaionRepository stateRepo;
	
	private final static Logger log =LoggerFactory.getLogger(StateInterupRepoService.class);
	
	public StatesInterpretationDb getByFeatureIdAndState(Integer featureId, int state) {
		
		try 
		{
			log.info("going to fetch StatesInterpretationDb data by featureId="+featureId+" and state= "+state+"");
			return stateRepo.findByFeatureIdAndState(featureId, state);
		}
		catch(Exception e) {
			log.info("fail to fetch StatesInterpretationDb data by featureId="+featureId+" and state= "+state+"");
			return new StatesInterpretationDb();
		}
	}
}
