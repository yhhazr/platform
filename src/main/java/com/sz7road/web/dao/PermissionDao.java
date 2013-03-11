package com.sz7road.web.dao;

import java.util.List;

import com.sz7road.web.model.pagination.PageInfo;
import com.sz7road.web.model.pagination.PaginationResult;
import com.sz7road.web.model.permission.Permission;

public interface PermissionDao {

	public List<Permission> getAllParentPermissions(PageInfo pageInfo) throws Exception;
	
	public int getAllParentPermissinsCount() throws Exception;
	
	public void addPermission(Permission permission) throws Exception;
	
	public List<Permission> getChildPermissions(int parentId, PageInfo pageInfo) throws Exception;
	
	public int getChildPermissionsCount(int parentId) throws Exception;
	
	public Permission getPermissionById(int id) throws Exception;
	
	public void updatePermission(Permission permission) throws Exception;
	
	public void deletePermission(Permission permission) throws Exception;
	
	public List<Permission> getPermissionByUrl(String url) throws Exception;
	
}
