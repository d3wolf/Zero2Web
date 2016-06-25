package com.z2w.action.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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
	public void getActionByModelName(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String name)
			throws Z2WException, IOException {
		List<Z2WActionBean> actions = z2WActionService.getModelActions(name);

		
		RequestContext requestContext = new RequestContext(request);
		Locale locale = requestContext .getLocale();
		
		JSONArray array = constructActionJson(actions, locale);

		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().write(array.toString());
	}
	
	/**
	 * 采用递归构造action tree的json字符串
	 * @param actions
	 * @return
	 * @throws Z2WException
	 */
	public JSONArray constructActionJson(List<Z2WActionBean> actions, Locale locale) throws Z2WException{
		JSONArray array = new JSONArray();

		for (Z2WActionBean action : actions) {
			if (!action.isModel()) {
				JSONObject jo = new JSONObject();
				jo.put("id", action.getName());
				jo.put("text", z2WActionService.getLocalizedActionName(action,"title", locale));
				jo.put("url", action.getUrl());
				jo.put("type", action.getType());

				array.add(jo);
			}else{
				JSONObject jo = new JSONObject();
				jo.put("id", action.getName());
				jo.put("text", z2WActionService.getLocalizedActionModelName(action.getName(),"title", locale));
				jo.put("state", "closed");//model默认折叠

				List<Z2WActionBean> subActions = z2WActionService.getModelActions(action.getName());
				
				jo.put("children", constructActionJson(subActions, locale));
				
				array.add(jo);
			}
		}
		
		return array;
	}
	
	@Autowired
	private Z2WSytemInit zi;
	
	@RequestMapping(value = "/reload")
	public void reloadActions(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		ServletContext context = request.getSession().getServletContext();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		zi.reloadAction(context);
		
		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().write("{'success':true}");
	}
}
