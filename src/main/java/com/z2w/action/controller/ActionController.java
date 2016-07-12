package com.z2w.action.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContext;

import com.z2w.action.model.Z2WActionBean;
import com.z2w.action.service.Z2WActionService;
import com.z2w.common.exception.Z2WException;
import com.z2w.init.Z2WSytemInit;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/action")
public class ActionController {

	@RequestMapping(value = "demoAction")
	public void getProvince(HttpServletResponse response, String id) throws IOException, Exception {
		JSONArray array = new JSONArray();
		int iid = Integer.parseInt(id);
		for (int i = iid; i < iid + 5; i++) {
			String sid = "" + i;
			String name = "name" + i;
			JSONObject jo = new JSONObject();
			jo.put("id", sid);
			jo.put("text", name);

			JSONArray array2 = new JSONArray();
			for (int j = 1; j < 3; j++) {
				JSONObject jo2 = new JSONObject();
				jo2.put("id", i * 10 + j);
				jo2.put("text", name + (i * 10));

				array2.add(jo2);
			}

			jo.put("children", array2);

			array.add(jo);
		}

		response.setContentType("text/html;charset=UTF-8");// 防止乱码

		response.getWriter().write(array.toString());
	}
	
	@RequestMapping(value = "demo/*")
	public void getDemoAction(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");// 防止乱码

		response.getWriter().write(request.getRequestURL().toString());
	}

	@Autowired
	private Z2WActionService z2WActionService;

	@RequestMapping(value = "modelActions")
	public void getActionTreeByModelName(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String name)
			throws Z2WException, IOException {
		List<Z2WActionBean> actions = z2WActionService.getModelActions(name);

		
		RequestContext requestContext = new RequestContext(request);
		Locale locale = requestContext .getLocale();
		
		Map<String, Object> extParams = new HashMap<String, Object>();
		
		JSONArray array = z2WActionService.constructActionTreeJson(actions, 0, extParams, locale);

		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().write(array.toString());
	}
	
	@RequestMapping(value = "modelActionsMenu")
	public void getActionMenuByModelName(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String name)
			throws Z2WException, IOException {
		List<Z2WActionBean> actions = z2WActionService.getModelActions(name);

		
		RequestContext requestContext = new RequestContext(request);
		Locale locale = requestContext .getLocale();
		
		Map<String, Object> extParams = new HashMap<String, Object>();
		JSONArray array = z2WActionService.constructActionMenuJson(actions, 0, extParams, locale);

		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().write(array.toString());
	}
	
	@Autowired
	private Z2WSytemInit zi;
	
	@RequestMapping(value = "/reload")
	public void reloadActions(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		ServletContext context = request.getSession().getServletContext();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		zi.reloadAction(context);
		
		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().write("{'success':true}");
	}
	
	@RequestMapping(value = "/testGridAction")
	public void testGetGridAction(HttpServletResponse response) throws IOException{
		String result = "[{ text: '增加', iconCls: 'icon-add', url:'/doc/create'},"+
		                "{ text: '修改', iconCls: 'icon-edit', handler: function () {  } }]";
		
		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().write(result);
	}
}
