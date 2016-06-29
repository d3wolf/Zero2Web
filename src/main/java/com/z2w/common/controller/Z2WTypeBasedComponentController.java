package com.z2w.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z2w.common.exception.Z2WException;
import com.z2w.common.service.PersistenceService;

@Controller
@RequestMapping("/tcomp")
public class Z2WTypeBasedComponentController {

	@RequestMapping("infoPage")
	public String getInfoPage(){
		return "tcomp/infoPage";
	}
	
	@Autowired
	private PersistenceService persistenceService;
	
	@RequestMapping("getObject")
	@ResponseBody
	public Object getObjectByOid(@RequestParam(value = "oid", required = true) String oid) throws Z2WException{
		
		return persistenceService.getObjectByOid(oid);
	}
}
