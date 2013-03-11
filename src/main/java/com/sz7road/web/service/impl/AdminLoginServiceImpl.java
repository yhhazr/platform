package com.sz7road.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sz7road.web.common.listener.SystemConfig;
import com.sz7road.web.common.util.MD5Util;
import com.sz7road.web.common.util.Wildcard;
import com.sz7road.web.dao.UserDao;
import com.sz7road.web.model.user.User;
import com.sz7road.web.service.AdminLoginService;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {

	@Resource
	private UserDao userDao;
	
	@Override
	public boolean checkIp(String ip) throws Exception {
		String ipStr = SystemConfig.getProperty("admin.allowedIps");
		return Wildcard.matches(ipStr, ip, "\\|");
	}

	@Override
	public boolean checkLogin(String userName, String password)
			throws Exception {
		boolean result = false;
		User user = userDao.getUserByName(userName);
		if(user != null){
			if(MD5Util.getMD5String(password).equals(user.getPassword())){
				result = true;
			}
		}
		return result;
	}

}
