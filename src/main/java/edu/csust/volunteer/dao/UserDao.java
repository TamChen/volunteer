package edu.csust.volunteer.dao;

import java.util.List;

import edu.csust.volunteer.model.User;
import edu.csust.volunteer.vo.UserVO;

public interface UserDao extends BaseDao<User> {

	public boolean isUserNoExists(String userNo);

	public boolean isUserNoBlocked(String userNo);

	public String getPasswordByUserNo(String hql, Object[] params);

	public List<User> listUserOutstanding(String hql1);

	public List<User> findUserPicture(String hql);

	public void updateUser(User user);
//	=============================================================
	public int getUserTotalNumber(String hql, Object[] params);

	public List<Object[]> getUserListBackend(String hql, int current,
			int userListSize);

	public List<User> getUserInfoList(String hql, int current, int pagesize);

}
