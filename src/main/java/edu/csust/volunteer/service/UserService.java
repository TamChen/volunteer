package edu.csust.volunteer.service;

import java.io.Serializable;

import edu.csust.volunteer.model.User;
import edu.csust.volunteer.vo.UserVO;

public interface UserService {
	public Serializable save(User user);

	public boolean isUsernameExists(UserVO user);

	public User login(UserVO user);

}
