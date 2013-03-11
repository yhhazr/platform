package com.sz7road.web.action.admin.permission;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.sz7road.web.model.pagination.PageInfo;
import com.sz7road.web.model.pagination.PaginationResult;
import com.sz7road.web.model.permission.Permission;
import com.sz7road.web.service.PermissionService;
/**
 * 
 * @author hai.yuan
 *
 */
public class PermissionAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(PermissionAction.class);
	
	@Resource
	private PermissionService permService;
	
	private String pageSize;
	
	private String startRow;
	
	private String echo;
	
	private String result;
	
	private String permissionId;
	
	private List<Permission> permissionList;
	
	private Permission permission;

	
	public String listPermission(){
		PageInfo pageInfo = new PageInfo(Integer.parseInt(startRow), Integer.parseInt(pageSize));
		try {
			PaginationResult<Permission> pageinaResult = permService.getPermissionList(pageInfo);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("sEcho", echo);
			jsonObject.put("aaData", pageinaResult.getResultList());
			jsonObject.put("iTotalRecords", pageinaResult.getTotal());
			jsonObject.put("iTotalDisplayRecords", pageinaResult.getTotal());
			result = JSONObject.fromObject(jsonObject).toString();
		} catch (Exception e) {
			logger.error("取得权限列表错误：",e);
		}
		return SUCCESS;
	}
	
	public String addPermission(){
		PageInfo pageInfo = new PageInfo(0, 999);
		String result = SUCCESS;
		try {
			PaginationResult<Permission> pageinaResult = permService.getPermissionList(pageInfo);
			permissionList = pageinaResult.getResultList();
		} catch (Exception e) {
			result = ERROR;
			logger.error("取得权限列表错误：",e);
		}
		return result;
	}
	
	public String addPermissionSubmit(){
		result = "true";
		try {
			permService.addPermission(permission);
		} catch (Exception e) {
			logger.error("新增权限错误：",e);
			result = "false";
		}
		return SUCCESS;
	}
	
	public String listChildPermission(){
		PageInfo pageInfo = new PageInfo(Integer.parseInt(startRow), Integer.parseInt(pageSize));
		try {
			if(permissionId != null){
				PaginationResult<Permission> pageinaResult = permService.getChildPermissionList(Integer.parseInt(permissionId), pageInfo);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("sEcho", echo);
				jsonObject.put("aaData", pageinaResult.getResultList());
				jsonObject.put("iTotalRecords", pageinaResult.getTotal());
				jsonObject.put("iTotalDisplayRecords", pageinaResult.getTotal());
				result = JSONObject.fromObject(jsonObject).toString();
			}
		} catch (Exception e) {
			logger.error("取得子权限列表错误：",e);
		}
		return SUCCESS;
	}
	
	public String editPermission(){
		PageInfo pageInfo = new PageInfo(0, 999);
		String result = SUCCESS;
		try {
			PaginationResult<Permission> pageinaResult = permService.getPermissionList(pageInfo);
			permissionList = pageinaResult.getResultList();
			if(permissionId != null){
				permission = permService.getPermissionById(Integer.parseInt(permissionId));
			}
		} catch (Exception e) {
			result = ERROR;
			logger.error("取得权限列表错误：",e);
		}
		return result;
	}
	
	public String editPermissionSubmit(){
		result = "true";
		try {
			permService.updatePermission(permission);
		} catch (Exception e) {
			logger.error("修改权限错误：",e);
			result = "false";
		}
		return SUCCESS;
	}
	
	public String deletePermission(){
		result = "true";
		try {
			if(permissionId != null){
				permService.deletePermission(Integer.parseInt(permissionId));
			}
		} catch (Exception e) {
			logger.error("修改权限错误：",e);
			result = "false";
		}
		return SUCCESS;
	}
	
	public String childPermissionManage(){
		return SUCCESS;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	
}
