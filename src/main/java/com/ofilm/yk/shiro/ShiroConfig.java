package com.ofilm.yk.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean gShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		/**
		 * 	Shiro内置过滤器
		 * 		常用的过滤器:
		 * 			anon :无需认证登录可以访问
		 * 			authc:必须认证才可以访问
		 * 			user:如果使用remeberme可以直接访问
		 * 			perms:该资源必须得到资源权限才可以访问
		 * 			role:改资源必须得到角色权限才可以访问
		 */
			Map<String, String> map = new HashMap<String, String>();
			//登录页无需验证
			map.put("/user/login", "anon");
			//其他所有页面都需要
			map.put("/*/**", "anon");
			shiroFilterFactoryBean.setLoginUrl("/index");
			//设置规则
			shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
			
			return shiroFilterFactoryBean;
		
		
	}
	
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userrealm") UserRealm userrealm) {
		
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRealm(userrealm);
		
		return defaultWebSecurityManager;
		
	}
	
	@Bean(name = "userrealm")
	public UserRealm userRealm() {
		
		return new UserRealm();
	}
	
	@Bean
	public ShiroDialect getShiroDialect() {
		
		return new ShiroDialect();
	}
}
