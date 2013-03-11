package com.sz7road.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sz7road.web.dao.UserDao;
import com.sz7road.web.model.user.User;
/**
 * 主要实现登陆认证 
 *
 */
public class MyUserDetailsService implements UserDetailsService{

	@Resource
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		User user = new User();
		try {
			user = userDao.getUserByName(username);
			List<String> authStr= userDao.loadUserAuthoritiesByName(username);
			for (String authName : authStr) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(authName);
				auths.add(authority);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new User(user.getId(), user.getUserName(), user.getPassword(), user.getEmail(), user.getCreateDate(), user.getUserName(), auths, true, true, true);
	}
	
}
