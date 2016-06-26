package com.z2w.container.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.z2w.container.model.Z2WOrgnazition;
import com.z2w.container.service.Z2WOrgnazitionService;

@Controller
@RequestMapping("/orgnazition")
public class Z2WOrgnazitionController {

	private static final Logger logger = Logger.getLogger(Z2WOrgnazitionController.class.getName());
	
	@Autowired
	Z2WOrgnazitionService persistenceService;
	
	@RequestMapping("create")
	public void createOrgnazition(@RequestParam(value = "name", required = true) String name,
			HttpServletResponse response){
		System.out.println("hello");
		Z2WOrgnazition exOrg = persistenceService.getOrgnazitionByName(name);
		
		if(exOrg == null){
			exOrg = new Z2WOrgnazition();
			exOrg.setName(name);
			persistenceService.createOrUpdateOrgnazition(exOrg);
		}else {
			
		}
	}
	
	@RequestMapping("modify")
	public void modifyOrgnazition(@RequestParam(value = "name", required = true) String name,
			HttpServletResponse response){
		System.out.println("hello2");
		Z2WOrgnazition exOrg = persistenceService.getOrgnazitionByName(name);
		
		logger.info("org: " + exOrg);
		if(exOrg == null){
			
		}else {
			if(name.endsWith("_mod")){
				name = name.substring(0, name.length()-4);
			}else{
				name = name + "_mod";
			}
			exOrg.setName(name);
		//	exOrg.setModefyTimestamp(new Date());
			persistenceService.createOrUpdateOrgnazition(exOrg);
		}
	}
}
