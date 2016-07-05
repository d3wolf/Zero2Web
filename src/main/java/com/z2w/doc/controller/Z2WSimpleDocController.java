package com.z2w.doc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z2w.common.exception.Z2WException;
import com.z2w.common.service.PersistenceService;
import com.z2w.container.model.Z2WContainer;
import com.z2w.doc.model.Z2WSimpleDocument;
import com.z2w.doc.service.Z2WSimpleDocService;

@Controller
@RequestMapping("/doc")
public class Z2WSimpleDocController {

	@Autowired
	private Z2WSimpleDocService simpleDocService;
	
	@Autowired
	private PersistenceService persistenceService;
	
	@RequestMapping("getContainerDoc")
	@ResponseBody
	public List<Z2WSimpleDocument> getSimpleDoc(@RequestParam(value = "containerOid", required = true) String oid){
		List<Z2WSimpleDocument> list = null;
		try {
			Z2WContainer container = (Z2WContainer) persistenceService.getObjectByOid(oid);
			list = simpleDocService.getDocByContainer(container);
		} catch (Z2WException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@RequestMapping("create")
	public String openCreatePage(@RequestParam(value = "containerOid", required = true) String oid){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("containerOid", oid);//将额外参数传递到新的页面
		
		return "doc/create";
	}
	
	@RequestMapping("doCreate")
	public void createSimpleDoc(HttpServletRequest request, HttpServletResponse response) throws Z2WException{
		String containerOid = request.getParameter("containerOid");
		String name = request.getParameter("name");
		String number = request.getParameter("number");
		
		Z2WContainer container = (Z2WContainer) persistenceService.getObjectByOid(containerOid);
		
		Z2WSimpleDocument simpleDoc = new Z2WSimpleDocument();
		simpleDoc.setName(name);
		simpleDoc.setNumber(number);
		simpleDoc.setContainer(container);
		
		simpleDocService.createDoc(simpleDoc);
	}
}
