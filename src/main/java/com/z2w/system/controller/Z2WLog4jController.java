package com.z2w.system.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.z2w.system.service.Log4jService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/log4j")
public class Z2WLog4jController {

	private static final Logger logger = Logger.getLogger(Z2WLog4jController.class.getName());

	@RequestMapping("manage")
	public String getLogMngPage() {

		return "system/log4jMng";
	}

	@Autowired
	private Log4jService log4jService;

	@RequestMapping(value = "getTarget")
	public void getSystemTargets(HttpServletResponse response, String type) throws IOException, Exception {
		List<String> list = null;
		
		if("system".equals(type)){
			list = log4jService.getSystemLoggerNames();
			
		}else if("customer".equals(type)){
			list = log4jService.getCustomerLoggerNames();
		}
		response.setContentType("text/html;charset=UTF-8");// 处理乱码
		
		if(list == null){
			response.getWriter().write("[{'id':'blank','text':'blank'}]");
		}
		
		JSONArray ja = new JSONArray();
		for (String name : list) {
			JSONObject jo = new JSONObject();
			jo.put("id", name);
			jo.put("text", name);
			ja.add(jo);
		}

		response.getWriter().write(ja.toString());
	}

	@RequestMapping("getLevel")
	public void getTargetLogLevel(@RequestParam(value = "target", required = true) String target,
			HttpServletResponse response) throws Exception {
		String levelStr = log4jService.getLevelStrByTarget(target);

		JSONObject jo = new JSONObject();
		jo.put("success", true);
		jo.put("levelStr", levelStr);

		logger.debug("get level:[" + target + "] -> " + jo);

		response.setContentType("text/html;charset=UTF-8");// 处理乱码
		response.getWriter().write(jo.toString());
	}

	@RequestMapping("setLevel")
	public void setTargetLogLevel(@RequestParam(value = "target", required = true) String target,
			@RequestParam(value = "levelStr", required = true) String levelStr, HttpServletResponse response)
					throws Exception {
		Level level = log4jService.getLevelByLevelStr(levelStr);
		log4jService.setTargetLevel(target, level);

		JSONObject jo = new JSONObject();
		jo.put("success", true);
		jo.put("msg", "set " + levelStr + " ok");

		logger.debug("set level:[" + target + "] -> " + jo);

		response.setContentType("text/html;charset=UTF-8");// 处理乱码
		response.getWriter().write(jo.toString());
	}
}