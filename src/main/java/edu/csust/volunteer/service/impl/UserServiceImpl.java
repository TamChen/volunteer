package edu.csust.volunteer.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.csust.volunteer.dao.UserDao;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.service.UserService;
//import edu.csust.volunteer.vo.DataGrid;
import edu.csust.volunteer.vo.UserVO;

@Service("userService")
@Transactional(readOnly = true)
// 声明这个service所有方法需要事务管理。每一个业务方法开始时都会打开一个事务。
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Serializable save(User user) {
		return userDao.save(user);
	}

	@Override
	public boolean isUsernameExists(UserVO user) {
		return userDao.isUsernameExists(user);
	}

	@Override
	public User login(UserVO user) {
		String hql = "from User where username = :username and password = :password";
		// Object[] params = {user.getUsername(), user.getPassword()};
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("username", user.getUsername());
//		params.put("password", user.getPassword());
		return userDao.load(hql, params);
	}

	private String initOrder(UserVO user, String hql) {
//		String sort = user.getSort();
//		String order = user.getOrder();
//		if (user.getSort() != null) {
//			if (order == null) {
//				order = "asc";
//			}
//			hql += " order by " + sort + " " + order;
//		}
		return hql;
	}

	private String initWhere(UserVO user, String hql, Map<String, Object> params) {
		String username = user.getUserName();
		if (StringUtils.isNotBlank(username)) {
			hql += " where u.username like :username";
			params.put("username", "%" + username.trim() + "%");
		}
		return hql;
	}

}
