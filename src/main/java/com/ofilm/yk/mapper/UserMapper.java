package com.ofilm.yk.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ofilm.yk.entity.User;

@Mapper
public interface UserMapper {

	 boolean addUser(User u);
	 User findByUsername(String username);
}
