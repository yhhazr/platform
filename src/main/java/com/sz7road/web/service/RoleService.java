package com.sz7road.web.service;

import com.sz7road.web.model.pagination.PageInfo;
import com.sz7road.web.model.pagination.PaginationResult;
import com.sz7road.web.model.role.Role;

public interface RoleService {

	public PaginationResult<Role> getAllRoles(PageInfo pageInfo) throws Exception;
	
}
