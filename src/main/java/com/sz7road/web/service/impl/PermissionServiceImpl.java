package com.sz7road.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sz7road.web.dao.PermissionDao;
import com.sz7road.web.model.pagination.PageInfo;
import com.sz7road.web.model.pagination.PaginationResult;
import com.sz7road.web.model.permission.Permission;
import com.sz7road.web.service.PermissionService;
/**
 * 
 * @author hai.yuan
 *
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionDao permDao;
	
	@Override
	public PaginationResult<Permission> getPermissionList(PageInfo pageInfo)
			throws Exception {
		PaginationResult<Permission> paginationResult = new PaginationResult<Permission>(permDao.getAllParentPermissinsCount(), permDao.getAllParentPermissions(pageInfo));
		return paginationResult;
	}

	@Override
	public void addPermission(Permission permission) throws Exception {
		permDao.addPermission(permission);
	}

	@Override
	public PaginationResult<Permission> getChildPermissionList(int parentId,
			PageInfo pageInfo) throws Exception {
		PaginationResult<Permission> paginationResult = new PaginationResult<Permission>(permDao.getChildPermissionsCount(parentId), permDao.getChildPermissions(parentId, pageInfo));
		return paginationResult;
	}

	@Override
	public Permission getPermissionById(int id) throws Exception {
		return permDao.getPermissionById(id);
	}

	@Override
	public void updatePermission(Permission permission) throws Exception {
		permDao.updatePermission(permission);
	}

	@Override
	public void deletePermission(int id) throws Exception {
		Permission permission = permDao.getPermissionById(id);
		permDao.deletePermission(permission);
		
	}

}
