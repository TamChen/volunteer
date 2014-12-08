package edu.csust.volunteer.dao;

import edu.csust.volunteer.model.User;
import edu.csust.volunteer.vo.UserVO;

public interface UserDao extends BaseDao<User> {
	public boolean isUsernameExists(UserVO user);

}
