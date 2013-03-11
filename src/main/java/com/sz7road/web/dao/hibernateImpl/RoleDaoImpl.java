package com.sz7road.web.dao.hibernateImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.buffer.CircularFifoBuffer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sz7road.web.dao.RoleDao;
import com.sz7road.web.model.pagination.PageInfo;
import com.sz7road.web.model.role.Role;
/**
 * 
 * @author hai.yuan
 *
 */
@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public List<Role> getAllRoles(PageInfo pageInfo) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Role.class);
		cri.setFirstResult(pageInfo.getStartRow());
		cri.setMaxResults(pageInfo.getPageSize());
		List<Role> roleList = cri.list();
		return roleList;
	}

	@Override
	public int getAllRolesCount() throws Exception {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Role.class);
		count = cri.list().size();
		return count;
	}

}
