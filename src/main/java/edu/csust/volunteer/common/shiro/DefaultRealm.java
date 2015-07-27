package edu.csust.volunteer.common.shiro;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import edu.csust.volunteer.service.UserAuthService;
import edu.csust.volunteer.service.UserService;
import edu.csust.volunteer.vo.UserVO;

public class DefaultRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	@Autowired
    private UserAuthService userAuthService;
	//认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userno = token.getUsername().trim();
        UserVO user = userService.getUserByUserNo(userno);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userno, user.getPassword(), getName());
		return info;
	}

	// 权限验证
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principals);
			SecurityUtils.getSubject().logout();
			return null;
		}
		String userno = (String) principals.getPrimaryPrincipal();
        UserVO user = userService.getUserByUserNo(userno);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userAuthService.findStringRoles(user));
        authorizationInfo.setStringPermissions(userAuthService.findStringPermissions(user));

        return authorizationInfo;
	}

}
