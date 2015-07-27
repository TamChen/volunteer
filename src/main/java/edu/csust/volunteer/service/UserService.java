package edu.csust.volunteer.service;

import java.io.Serializable;
import java.util.List;

import edu.csust.volunteer.model.Attention;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.vo.PictureVO;
import edu.csust.volunteer.vo.UserBackend;
import edu.csust.volunteer.vo.UserVO;

public interface UserService {
	public Serializable save(User user);

	public List<User> queryUserAll();

	public Boolean isUserNoExists(String userno);

	public UserVO getUserByUserNo(String userno);

	public boolean isUserNoBlocked(String userno);

	public User findUserById(String i);

	public void updateUserWorkTime(User user);
//===============================================================
	//后台方法
	public int getUserTotalNumber();

	public List<UserBackend> getUserListBackend(int current, int userListSize);

	public void updateUserInfo(String userno, String textarea, String statu);

	public void updateUser(User user);

	public List<User> getUserRateList(int current, int pagesize);

	public int getUserRateListNum();

	public List<User> getExcelentHost(int current, int pagesize);

	public int getExcelentHostNum();

	public int loadExcelentPersonalNum();

	public List<User> loadExcelentPersonal(int current, int pagesize);
}
