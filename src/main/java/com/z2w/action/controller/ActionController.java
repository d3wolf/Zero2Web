package com.z2w.action.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.z2w.action.model.Z2WActionBean;
import com.z2w.action.service.Z2WActionService;
import com.z2w.common.exception.Z2WException;

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

		response.setContentType("text/html;charset=UTF-8");// 处理乱码

		response.getWriter().write(array.toString());
	}
	
	@RequestMapping(value = "demo/*")
	public void getDemoAction(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");// 处理乱码

		response.getWriter().write(request.getRequestURL().toString());
	}

	@Autowired
	private Z2WActionService z2WActionService;

	@RequestMapping(value = "modelActions")
	public void getActionByModelName(HttpServletResponse response, @RequestParam("id") String name)
			throws Z2WException, IOException {
		List<Z2WActionBean> actions = z2WActionService.getModelActions(name);

		JSONArray array = constructActionJson(actions);

		response.setContentType("text/html;charset=UTF-8");// 处理乱码

		response.getWriter().write(array.toString());
	}
	
	/**
	 * 构造action json字符串，因为要用递归处理sub model，所以单独写一个方法
	 * @param actions
	 * @return
	 * @throws Z2WException
	 */
	public JSONArray constructActionJson(List<Z2WActionBean> actions) throws Z2WException{
		JSONArray array = new JSONArray();

		for (Z2WActionBean action : actions) {
			if (!action.isModel()) {
				JSONObject jo = new JSONObject();
				jo.put("id", action.getName());
				jo.put("text", action.getName());
				jo.put("url", action.getUrl());
				jo.put("type", action.getType());

				array.add(jo);
			}else{
				JSONObject jo = new JSONObject();
				jo.put("id", action.getName());
				jo.put("text", action.getName());
				jo.put("state", "closed");//model默认折叠起来

				List<Z2WActionBean> subActions = z2WActionService.getModelActions(action.getName());
				
				jo.put("children", constructActionJson(subActions));
				
				array.add(jo);
			}
		}
		
		return array;
	}
}
