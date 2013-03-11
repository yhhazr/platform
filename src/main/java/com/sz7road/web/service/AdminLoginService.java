package com.sz7road.web.service;

public interface AdminLoginService {

	public boolean checkIp(String ip) throws Exception;
	
	public boolean checkLogin(String userName, String password) throws Exception;
}
