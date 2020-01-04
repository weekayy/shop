package com.ofilm.yk.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	
	 private static final Logger log = LoggerFactory.getLogger(IndexController.class);


	@RequestMapping(value = "/index")
	public String index() {
		
		return "login";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value = "/register")
	public String register() {
		
		return "register";
	}
	
}
