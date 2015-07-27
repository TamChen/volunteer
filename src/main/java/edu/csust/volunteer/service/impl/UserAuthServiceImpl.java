package edu.csust.volunteer.service.impl;

import java.util.Set;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

import edu.csust.volunteer.service.UserAuthService;
import edu.csust.volunteer.vo.Role;
import edu.csust.volunteer.vo.UserVO;

/**
 * @author tam7
 *shiro授权的set role和set permissions
 *这里涉及到具体的权限表的设计，多个用户可能对应多个角色，多个角色对应多个permissions
 */
@Service("userAuthService")
public class UserAuthServiceImpl implements UserAuthService {

	@Override
	public Set<String> findStringRoles(UserVO user) {
		//面向切面编程？？
		Set<Role> roles = ((UserAuthService) AopContext.currentProxy()).findRoles(user);
        return Sets.newHashSet(Collections2.transform(roles, new Function<Role, String>() {
            @Override
            public String apply(Role input) {
                return input.getRole();
            }
        }));
	}

	
	@Override
	public Set<String> findStringPermissions(UserVO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Role> findRoles(UserVO user) {
        Set<Role> roles =null;
        return roles;
    }


	
	
}
