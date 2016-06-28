package com.z2w.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tcomp")
public class Z2WTypeBasedComponentController {

	@RequestMapping("infoPage")
	public String getInfoPage(){
		return "tcomp/infoPage";
	}
}
