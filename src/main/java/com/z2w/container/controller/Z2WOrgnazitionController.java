package com.z2w.container.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.z2w.action.model.Z2WActionBean;
import com.z2w.action.service.Z2WActionService;
import com.z2w.common.exception.Z2WException;
import com.z2w.container.model.Z2WOrganization;
import com.z2w.container.service.Z2WOrgnazitionService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/organization")
public class Z2WOrgnazitionController {

	private static final Logger logger = Logger.getLogger(Z2WOrgnazitionController.class.getName());
	
	@Autowired
	Z2WOrgnazitionService persistenceService;
	
	@RequestMapping("create")
	public void createOrgnazition(@RequestParam(value = "name", required = true) String name,
			HttpServletResponse response){

		Z2WOrganization exOrg = persistenceService.getOrganizationByName(name);
		
		if(exOrg == null){
			exOrg = new Z2WOrganization();
			exOrg.setName(name);
			persistenceService.createOrUpdateOrganization(exOrg);
		}else {
			
		}
	}
	
	@RequestMapping("modify")
	public void modifyOrgnazition(@RequestParam(value = "name", required = true) String name,
			HttpServletResponse response){

		Z2WOrganization exOrg = persistenceService.getOrganizationByName(name);
		
		logger.info("org: " + exOrg);
		if(exOrg == null){
			
		}else {
			if(name.endsWith("_mod")){
				name = name.substring(0, name.length()-4);
			}else{
				name = name + "_mod";
			}
			exOrg.setName(name);
			persistenceService.createOrUpdateOrganization(exOrg);
		}
	}
	
	@RequestMapping("view")
	public String showAllOrgnazitions(){
		
		return "container/organization/view";
	}
	
	@RequestMapping("getAll")
	@ResponseBody//返回json
	public List<Z2WOrganization> getAllOrgnazitions(HttpServletResponse response){
		List<Z2WOrganization> orgs = persistenceService.getAllOrganization();
		
		return orgs;
	}
	
	@Autowired
	private Z2WActionService z2WActionService;
	
	@RequestMapping("listOrgNavOpt")
	public void listOrganizationNavigatorOpt(HttpServletRequest request, HttpServletResponse response) throws Z2WException, IOException{
		List<Z2WOrganization> orgs = persistenceService.getAllOrganization();
		
		List<Z2WActionBean> actions = z2WActionService.getModelActions("orgnazition_navigator_actions");
		
		RequestContext requestContext = new RequestContext(request);
		Locale locale = requestContext .getLocale();
		
		JSONArray array = new JSONArray();
		for(Z2WOrganization org : orgs){
			JSONObject jo = new JSONObject();
			jo.put("id", org.getId());
			jo.put("text", org.getName());
			jo.put("iconCls", "icon-organization");
			jo.put("children", z2WActionService.constructActionJson(actions, locale));
			
			array.add(jo);
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(array.toString());
	}
}
