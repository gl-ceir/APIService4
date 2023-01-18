package com.ceir.CeirCode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.filtermodel.AlertDbFilter;
import com.ceir.CeirCode.model.AlertDb;
import com.ceir.CeirCode.model.AllRequest;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.service.AlertDbService;
import com.ceir.CeirCode.util.HttpResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/alertDb")
public class AlertDbController {

	@Autowired
	SystemConfigDbListRepository systemConfigRepo;

	@Autowired
	AlertDbService alertDbService;
	
	@ApiOperation(value = "alert db  data.", response = AlertDb.class)
	@PostMapping("/viewAll") 
	public MappingJacksonValue viewRecord(@RequestBody AlertDbFilter filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file,
			@RequestParam(name = "source", defaultValue = "menu", required = false) String source){
		MappingJacksonValue mapping = null;
		if( file == 0) {
			Page<AlertDb> alertDbReponse  = alertDbService.viewAllAlertData(filterRequest, pageNo, pageSize,source);
			mapping = new MappingJacksonValue(alertDbReponse);
			
		}else {
			//hh
			FileDetails fileDetails = alertDbService.getAlertDbInFile(filterRequest,source);
			mapping = new MappingJacksonValue(fileDetails);
		}
		return mapping;
	}
	
	@ApiOperation(value = "alert ids data", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/view") 
	public ResponseEntity<?> getUsertypes(@RequestHeader HttpHeaders headers){
		return alertDbService.getAlertData();
	}

	@ApiOperation(value="view alert d by id")
	@PostMapping("/viewById")
	public ResponseEntity<?> viewById(@RequestBody AllRequest request){
		return alertDbService.findById(request);
	}

	@ApiOperation(value="update alert db")
	@PostMapping("/update")
	public ResponseEntity<?> updateAlertDb(@RequestBody AlertDb alertDb){
		return alertDbService.updateAlertDb(alertDb);
	}
}
