package com.sz7road.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sz7road.web.dao.RoleDao;
import com.sz7road.web.model.pagination.PageInfo;
import com.sz7road.web.model.pagination.PaginationResult;
import com.sz7road.web.model.role.Role;
import com.sz7road.web.service.RoleService;
/**
 * 
 * @author hai.yuan
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	@Override
	public PaginationResult<Role> getAllRoles(PageInfo pageInfo)
			throws Exception {
		PaginationResult<Role> paginationResult = new PaginationResult<Role>(roleDao.getAllRolesCount(), roleDao.getAllRoles(pageInfo));
		return paginationResult;
	}

}
