package com.sz7road.web.action.admin.role;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.sz7road.web.model.pagination.PageInfo;
import com.sz7road.web.model.pagination.PaginationResult;
import com.sz7road.web.model.role.Role;
import com.sz7road.web.service.RoleService;
/**
 * 
 * @author hai.yuan
 *
 */
public class RoleAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(RoleAction.class);
	
	@Resource
	private RoleService roleService;

	private String result;
	
	private String pageSize;
	
	private String startRow;
	
	private String echo;
	
	public String listRole(){
		PageInfo pageInfo = new PageInfo(Integer.parseInt(startRow), Integer.parseInt(pageSize));
		try {
			PaginationResult<Role> pageinaResult = roleService.getAllRoles(pageInfo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("sEcho", echo);
			jsonObject.put("aaData", pageinaResult.getResultList());
			jsonObject.put("iTotalRecords", pageinaResult.getTotal());
			jsonObject.put("iTotalDisplayRecords", pageinaResult.getTotal());
			result = JSONObject.fromObject(jsonObject).toString();
		} catch (Exception e) {
			logger.error("取得角色列表错误：",e);
		}
		return SUCCESS;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartRow() {
		return startRow;
	}

	public void setStartRow(String startRow) {
		this.startRow = startRow;
	}

	public String getEcho() {
		return echo;
	}

	public void setEcho(String echo) {
		this.echo = echo;
	}
	
	
}
