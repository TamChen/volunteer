package edu.csust.volunteer.dao.impl;

import org.springframework.stereotype.Repository;
import edu.csust.volunteer.dao.UserDao;
import edu.csust.volunteer.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public boolean isUserNoExists(String userno) {
		Object params[]={userno};
		Long count=(Long) uniqueQuery("select count(id) from User where userno = ?", params);
		if (count != null && count > 0) { // 用户已经存在
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getPasswordByUserNo(String hql, Object[] params) {
		String password=(String) uniqueQuery(hql, params);
		return password;
	}
	
	@Override
	//判断用户是否被锁，返回true没有被锁
	public boolean isUserNoBlocked(String userNo) {
		// TODO Auto-generated method stub
		return true;
	}
}
