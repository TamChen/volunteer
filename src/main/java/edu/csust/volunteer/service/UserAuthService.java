package edu.csust.volunteer.service;

import java.util.Set;

import edu.csust.volunteer.vo.Role;
import edu.csust.volunteer.vo.UserVO;

public interface UserAuthService {
	
	Set<String> findStringRoles(UserVO user);
	
	Set<String> findStringPermissions(UserVO user);

	Set<Role> findRoles(UserVO user);
}
