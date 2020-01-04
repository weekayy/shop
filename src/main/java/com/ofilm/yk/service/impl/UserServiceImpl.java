package com.ofilm.yk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofilm.yk.entity.User;
import com.ofilm.yk.mapper.UserMapper;
import com.ofilm.yk.service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean addUser(User user) {
		
		return userMapper.addUser(user);
	}

	@Override
	public User findByUsername(String username) {

		return userMapper.findByUsername(username);
	}

}
