package com.ofilm.yk.service;

import com.ofilm.yk.entity.User;

public interface UserService {

	public boolean addUser(User u);
	public User findByUsername(String username);
}
