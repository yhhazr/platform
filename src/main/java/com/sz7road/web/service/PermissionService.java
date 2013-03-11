package com.sz7road.web.service;

import com.sz7road.web.model.pagination.PageInfo;
import com.sz7road.web.model.pagination.PaginationResult;
import com.sz7road.web.model.permission.Permission;

public interface PermissionService {
	
	public PaginationResult<Permission> getPermissionList(PageInfo pageInfo) throws Exception ; 

	public void addPermission(Permission permission) throws Exception;
	
	public PaginationResult<Permission> getChildPermissionList(int parentId,PageInfo pageInfo) throws Exception ; 
	
	public Permission getPermissionById(int id) throws Exception;
	
	public void updatePermission(Permission permission) throws Exception;
	
	public void deletePermission(int id) throws Exception;
	
}
