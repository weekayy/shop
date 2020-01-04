package com.ofilm.yk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	@RequestMapping("/wel")
	public String wel() {
		
		return "wel";
	}
	
	@RequestMapping("/errors")
	public String error() {
		
		return "errors";
	}
}
