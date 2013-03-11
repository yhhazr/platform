package com.sz7road.web.dao;

import java.util.List;

import com.sz7road.web.model.pagination.PageInfo;
import com.sz7road.web.model.role.Role;

public interface RoleDao {

	public List<Role> getAllRoles(PageInfo pageInfo) throws Exception;
	
	public int getAllRolesCount() throws Exception;
	
}
