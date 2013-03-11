package com.sz7road.web.dao.hibernateImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sz7road.web.dao.PermissionDao;
import com.sz7road.web.model.pagination.PageInfo;
import com.sz7road.web.model.permission.Permission;
/**
 * 
 * @author hai.yuan
 *
 */
@Repository
@Transactional
public class PermissionDaoImpl implements PermissionDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public List<Permission> getAllParentPermissions(PageInfo pageInfo)
			throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria cir = session.createCriteria(Permission.class);
		cir.add(Restrictions.le("parentId", 2));
		cir.setFirstResult(pageInfo.getStartRow());
		cir.setMaxResults(pageInfo.getPageSize());
		cir.addOrder(Order.asc("orderId"));
		List<Permission> permList = cir.list();
		return permList;
	}

	@Override
	public int getAllParentPermissinsCount() throws Exception {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria  cir = session.createCriteria(Permission.class);
		count = cir.list().size();
		return count;
	}

	@Override
	public void addPermission(Permission permission) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.save(permission);
	}

	@Override
	public List<Permission> getChildPermissions(int parentId,PageInfo pageInfo) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Permission.class);
		cri.add(Restrictions.eq("parentId", parentId));
		cri.setFirstResult(pageInfo.getStartRow());
		cri.setMaxResults(pageInfo.getPageSize());
		cri.addOrder(Order.asc("orderId"));
		List<Permission> permList = cri.list();
		return permList;
	}

	@Override
	public int getChildPermissionsCount(int parentId) throws Exception {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Permission.class);
		cri.add(Restrictions.eq("parentId", parentId));
		count = cri.list().size();
		return count;
	}

	@Override
	public Permission getPermissionById(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Permission permission = (Permission)session.get(Permission.class, id);
		return permission;
	}

	@Override
	public void updatePermission(Permission permission) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.update(permission);
		
	}

	@Override
	public void deletePermission(Permission permission) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.delete(permission);
		
	}

	@Override
	public List<Permission> getPermissionByUrl(String url) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(Permission.class);
		cri.add(Restrictions.eq("permUrl", url));
		return cri.list();
	}

}
