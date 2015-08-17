package edu.csust.volunteer.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import edu.csust.volunteer.dao.UserDao;
import edu.csust.volunteer.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	@Override
	public boolean isUserNoExists(String userno) {
		Object params[]={userno};
		return true;
	}
	@Override
	public String getPasswordByUserNo(String hql, Object[] params) {
		String password=(String) uniqueQuery(hql, params);
		return password;
	}
	@Override
	//判断用户是否被锁，返回true没有被锁
	public boolean isUserNoBlocked(String userNo) {
		return true;
	}
	@Override
	public List<User> listUserOutstanding(String hql) {
		 return find(hql);
	}
	@Override
	public List<User> findUserPicture(String hql) {
		return find(hql);
	}
	@Override
	public void updateUser(User user) {
		Session session = getSession2();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}

	@Override
	public int getUserTotalNumber(String hql, Object[] params) {
		Long UserTotalNumber=(Long)uniqueQuery(hql, params);
		return UserTotalNumber.intValue();
	}
	@Override
	public List<Object[]> getUserListBackend(String hql, int current,
			int userListSize) {
		List<Object[]> objects=getObjectPageList(hql, current, userListSize);
		return objects;
	}
	@Override
	public List<User> getUserInfoList(String hql, int current, int pagesize) {
		return splitpageList(hql, current, pagesize);
	}
	
/*	Session s = HibernateSessionFactory.getSession();
	Criteria c = s.createCriteria(User.class);
	c.setCacheable(true);//这句必须要有
	System.out.println("第一次读取");
	List<User> users = c.list();
	System.out.println(users.size());
	HibernateSessionFactory.closeSession();
	 
	s = HibernateSessionFactory.getSession();
	c = s.createCriteria(User.class);
	c.setCacheable(true);//这句必须要有
	System.out.println("第二次读取");
	users = c.list();
	System.out.println(users.size());
	HibernateSessionFactory.closeSession();*/

}
