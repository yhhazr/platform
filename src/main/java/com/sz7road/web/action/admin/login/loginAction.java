package com.sz7road.web.action.admin.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.ActionSupport;
import com.sz7road.web.security.MyUserDetailsService;
import com.sz7road.web.service.AdminLoginService;
/**
 * 
 * @author hai.yuan
 *
 */
/**
 * 
 * @author hai.yuan
 *
 */
public class loginAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(loginAction.class);
	@Resource
	private AdminLoginService loginService;
	
	@Resource
	private MyUserDetailsService userDetailsService;
	
	private String userName;
	
	private String password;
	
	public String adminLoginSubmit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String result = INPUT;
		String ip = getIpAddr(request);
		try {
			if(!loginService.checkIp(ip)){
				this.addActionError("您所在的IP不允许访问本系统！");
			}else{
				if(loginService.checkLogin(userName, password)){
					//spring security 将权限及用户信息存入securityContext
					UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
					Authentication authentication = new UsernamePasswordAuthenticationToken(  
							userDetails, userDetails.getPassword(), userDetails.getAuthorities());
					SecurityContext securityContext = SecurityContextHolder.getContext();
					securityContext.setAuthentication(authentication);
					HttpSession session = request.getSession(true);
				    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
					result = SUCCESS;
				}else{
					this.addActionError("用户名或密码错误！");
				}
			}
		} catch (Exception e) {
			logger.error("登陆提交错误:",e);
		}
		return result;
	}
	
	public void validateAdminLoginSubmit(){
		if (userName == null || "".equals(userName)) {
			this.addFieldError("userName", "用户名不能为空");
		}
		if (password == null || "".equals(password)) {
			this.addFieldError("password", "密码不能为空");
		}
	}
	
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
