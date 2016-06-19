package com.z2w.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class TestController {

	@RequestMapping("test")
	public String getDemoCombo() {
		
		return "test";
	}
}
