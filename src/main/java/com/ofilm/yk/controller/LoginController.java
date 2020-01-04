
package com.ofilm.yk.controller;

import java.util.Date;

import com.ofilm.yk.shop.utils.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofilm.yk.entity.User;
import com.ofilm.yk.service.impl.UserServiceImpl;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserServiceImpl userService;

	@ResponseBody
	@RequestMapping("/user/login")
	public AjaxResult userLogin(String username,String password) {

		AjaxResult ajaxResult = AjaxResult.success();
		User user = userService.findByUsername(username);
		if(MD5Utils.encodeByMD5(password).equals(user.getPassword())){
			return ajaxResult;
		}else {
			log.error("密码错误");
			return  AjaxResult.error("密码错误！");
		}

	}
	
	@ResponseBody
	@RequestMapping("/user/regist")
	public AjaxResult userRegist(User user ) {
		
		user.setCreate_time(DateUtil.dateTrans(new Date()));
		user.setPassword(MD5Utils.encodeByMD5(user.getPassword()));
		
		boolean flag = userService.addUser(user);
		AjaxResult ajaxResult = AjaxResult.success(user);
		if(flag) {
			return ajaxResult;
		}else {
			return AjaxResult.error("注册失败!");
					
		}

	}
}

