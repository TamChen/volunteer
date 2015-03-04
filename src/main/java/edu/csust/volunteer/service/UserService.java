package edu.csust.volunteer.service;

import java.io.Serializable;
import java.util.List;

import edu.csust.volunteer.model.User;
import edu.csust.volunteer.vo.UserVO;

public interface UserService {
	public Serializable save(User user);

	public List<User> queryUserAll();

	public Boolean isUserNoExists(String userNo);

	public UserVO getUserByUserNo(String userNo);

	public boolean isUserNoBlocked(String userNo);


}
