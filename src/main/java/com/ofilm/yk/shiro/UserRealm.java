package com.ofilm.yk.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ofilm.yk.entity.User;
import com.ofilm.yk.service.impl.UserServiceImpl;

public class UserRealm extends AuthorizingRealm{

	@Autowired
	private UserServiceImpl userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		System.out.println("执行授权逻辑");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermission("user:add");
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		
		System.out.println("执行认证逻辑");
		String username = "";
		String password = "";
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		token.getUsername();
		User user = userService.findByUsername(username);
		if(user == null) {
			throw new UnknownAccountException("用户不存在！");
		}
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password,username);
		
		return info;
		
	}
	public void clearCachedAuthorizationInfo() {
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
}
	
