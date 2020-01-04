package com.ofilm.yk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

	@RequestMapping("/wel")
	public String wel() {
		
		return "wel";
	}
	
	@RequestMapping("/errors")
	public String error() {
		
		return "errors";
	}
}
