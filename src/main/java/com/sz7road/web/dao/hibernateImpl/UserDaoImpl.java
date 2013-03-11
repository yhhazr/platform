package com.sz7road.web.dao.hibernateImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sz7road.web.dao.UserDao;
import com.sz7road.web.model.role.Role;
import com.sz7road.web.model.rolePermission.RolePermission;
import com.sz7road.web.model.user.User;

/**
 * 
 * @author hai.yuan
 * 
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Resource
	private SessionFactory sessionFactory;

	@Override
	public User getUserByName(String name) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Criteria cri = session.createCriteria(User.class);
		cri.add(Restrictions.eq("userName", name));
		User user = (User) cri.uniqueResult();
		return user;
	}

	@Override
	public User getUserById(int id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		return user;
	}

	@Override
	public void addUser(User user) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	public void deleteUser(User user) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
	}

	@Override
	public void updateUser(User user) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

	@Override
	public List<String> loadUserAuthoritiesByName(String username)
			throws Exception {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select perm.permName from User as user, UserRole as ur, Role as role, Permission as perm, RolePermission as rp where perm.id=rp.permissionId and rp.roleId=role.id and role.id=ur.roleId and ur.userId= user.id and user.userName=:userName";
		Query query = session.createQuery(hql);
		query.setParameter("userName", username);
		List<String> result = query.list();
		for (String grantedAuthority : result) {
			System.out.println(grantedAuthority);
		}
		return result;
	}
}
