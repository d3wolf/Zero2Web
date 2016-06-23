package com.z2w.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class Z2WSystemController {

	@RequestMapping(value = "tools")
	public String getSystemToolsPage(){
		
		return "system/tools";
	}
}
