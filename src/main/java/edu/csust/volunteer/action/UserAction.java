package edu.csust.volunteer.action;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.csust.volunteer.exception.UserBlockedException;
import edu.csust.volunteer.exception.UserException;
import edu.csust.volunteer.exception.UserNotExistsException;
import edu.csust.volunteer.service.UserService;
import edu.csust.volunteer.vo.UserVO;

/**
 * @author tam7
 *用户相关操作中心，登陆，退出
 *1、log4j或者是slf4j，选一个作为日志记录
 *2、验证码，利用Kaptcha形成
 *3、用AJAX和session设值
 *4、用户试错密码十次就自动锁住十分钟
 *5、安全，授权部分代码
 */
@Action(value = "userAction", results={
		@Result(name="success",type="redirect",location="/index.jsp"),
	    @Result(name="input",type="redirect",location="/login.jsp"),
	    @Result(name="login",type="redirect",location="/login.jsp")
}) 
public class UserAction extends BaseAction<UserVO>{
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UserAction.class);
	private String userno;
	private String password;
	private Subject subject;
	@Autowired
	private UserService userService;
	public String login() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		userno=request.getParameter("userno");
		password=request.getParameter("password");
		session.setAttribute("userno", userno);
		LOGGER.info(userno+"+++"+password);
		subject=SecurityUtils.getSubject();
		try {
			if (!userService.isUserNoExists(userno)) {
				throw new UserNotExistsException();
			}
			if (!userService.isUserNoBlocked(userno)) {
				throw new UserBlockedException();
			}
			UsernamePasswordToken token = new UsernamePasswordToken(userno,password);
			//false登录成功用户信息才会保存到session中
			token.setRememberMe(false);
			subject.login(token);
			LOGGER.info("isAuthenticated:"+subject.isAuthenticated());
			LOGGER.info("login success");
		}catch (UserNotExistsException e) {
			LOGGER.info("User is not exists");
			session.setAttribute("message", "用户不存在");
			return INPUT;
		}catch (UserBlockedException e) {
			LOGGER.info("User is blocked");
			session.setAttribute("message", "用户被锁");
			return INPUT;
		}catch (IncorrectCredentialsException e) {
			LOGGER.info("UserNo or password is incorrect");
			session.setAttribute("message", "账号或密码错误");
			return INPUT;
		}catch (UserException e) {
			LOGGER.info("Unknow mistake");
			session.setAttribute("message", "未知错误");
			return INPUT;
		}
		return SUCCESS;
	}
	public String logout(){
    	SecurityUtils.getSubject().logout();
    	LOGGER.info("isAuthenticated:"+SecurityUtils.getSubject().isAuthenticated());
    	LOGGER.info("退出成功");
		return LOGIN;
	}
}
