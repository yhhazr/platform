package com.sz7road.web.dao;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.sz7road.web.model.user.User;

public interface UserDao {

	public User getUserByName(String name) throws Exception;
	
	public User getUserById(int id) throws Exception;
	
	public void addUser(User user) throws Exception;
	
	public void deleteUser(User user) throws Exception;
	
	public void updateUser(User user) throws Exception;
	
	public List<String> loadUserAuthoritiesByName(String username) throws Exception;
}
