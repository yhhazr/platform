package com.sz7road.web.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessDeniedHandlerImpl implements AccessDeniedHandler {

	private String accessDeniedUrl;

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		boolean isAjax = "XMLHttpRequest".equals(request
				.getHeader("X-Requested-With"));
		if (isAjax) {
			String contentType = "application/html";
			response.setContentType(contentType);
			String jsonObject = "noright";
			PrintWriter out = response.getWriter();
			out.print(jsonObject);
			out.flush();
			out.close();
			return;
		} else {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + accessDeniedUrl);
		}

	}
}
