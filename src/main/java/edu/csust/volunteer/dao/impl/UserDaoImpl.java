package edu.csust.volunteer.dao.impl;

import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.UserDao;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.vo.UserVO;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public boolean isUsernameExists(UserVO user) {
		Long count = (Long) getSession()
				.createQuery("select count(id) from User where username = ?")
				.setParameter(0, user.getUserName()).uniqueResult();
		if (count != null && count > 0) { // 用户名已经存在
			return true;
		} else {
			return false;
		}
	}

}
