package edu.csust.volunteer.dao;

import edu.csust.volunteer.model.User;
import edu.csust.volunteer.vo.UserVO;

public interface UserDao extends BaseDao<User> {

	public boolean isUserNoExists(String userNo);

	public boolean isUserNoBlocked(String userNo);

	public String getPasswordByUserNo(String hql, Object[] params);

}
