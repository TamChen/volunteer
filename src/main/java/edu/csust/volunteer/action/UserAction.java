package edu.csust.volunteer.action;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

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

import com.alibaba.fastjson.JSONObject;

import edu.csust.volunteer.exception.UserBlockedException;
import edu.csust.volunteer.exception.UserException;
import edu.csust.volunteer.exception.UserNotExistsException;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.service.StatisticService;
import edu.csust.volunteer.service.UserService;
import edu.csust.volunteer.vo.UserBackend;
import edu.csust.volunteer.vo.UserVO;

/**
 * @author tam7
 * 记住我的功能
 */
@Action(value = "userAction", results={
		@Result(name="success",type="redirect",location="/index.jsp"),
	    @Result(name="input",type="redirect",location="/login.jsp"),
	    @Result(name="login",type="redirect",location="/login.jsp"),
	    @Result(name="adminLogin",type="redirect",location="/backend/backend.jsp")
}) 
public class UserAction extends BaseAction<UserVO>{
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UserAction.class);
	@Autowired
	private UserService userService;
	@Autowired
	private StatisticService statisticService;
	//用户和管理员登陆都是这里
    public String login() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userno=request.getParameter("userno");
		String password=request.getParameter("password");
		LOGGER.info(userno+"+++"+password);
			Subject subject=SecurityUtils.getSubject();
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
				User user=userService.findUserById(userno);
				statisticService.increaseLoginInfo();
				session.setAttribute("username", user.getUsername());
				session.setAttribute("infonum", user.getInfoNum());
				session.setAttribute("userno", user.getUserno());
				session.setAttribute("head", user.getUserHead());
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
			if (userno.length()<12) {
				return "adminLogin";
			}
			return SUCCESS;
		
	}
    //用户和管理员登出都是这里
    public String logout(){
    	SecurityUtils.getSubject().logout();
    	LOGGER.info("isAuthenticated:"+SecurityUtils.getSubject().isAuthenticated());
    	LOGGER.info("退出成功");
		return LOGIN;
	}
    //==========================================================================
    //后台方法、获得总人数
	public void getUserTotalNumber() {
		int UserTotalNumber = userService.getUserTotalNumber();
		writeJson(UserTotalNumber);
	}
	public void getAllUserList(){
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int current=Integer.parseInt(request.getParameter("current"));
		int userListSize=Integer.parseInt(request.getParameter("userListSize"));
		List<UserBackend> userList = userService.getUserListBackend(current, userListSize);
		jsonData.put("userList", userList);
		writeJson(jsonData);
	}
	public void getUserDetail() {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		User user=userService.findUserById(userno);
		jsonData.put("user", user);
		writeJson(jsonData);
	}
	//保存用户简介 
	public void saveUserIntro() throws UnsupportedEncodingException {
		String statu="userintro";
		HttpServletRequest request=ServletActionContext.getRequest();
		String textarea = new String(request.getParameter("textarea").getBytes("iso8859-1"),"utf-8");
		String userno = (String)request.getSession().getAttribute("userno");
		userService.updateUserInfo(userno,textarea,statu);
		System.out.println(textarea);
	}
	//保存用户签名
	public void saveUserSign() throws UnsupportedEncodingException {
		String statu="usersign";
		HttpServletRequest request=ServletActionContext.getRequest();
		String userSign = new String(request.getParameter("userSign").getBytes("iso8859-1"),"utf-8");
		String userno = (String)request.getSession().getAttribute("userno");
		userService.updateUserInfo(userno,userSign,statu);
		System.out.println(userSign);
	}

	//获得用户排名
	public void getUserRateList() {
		JSONObject jsonObject=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int current = Integer.parseInt(request.getParameter("current"));
		int pagesize=3;
		List<User> userList=userService.getUserRateList(current,pagesize);
		jsonObject.put("userList", userList);
		writeJson(jsonObject);
	}
	//获得用户排名总数
	public void getUserRateListNum() {
		JSONObject jsonObject=new JSONObject();
		int size=userService.getUserRateListNum();
		jsonObject.put("num", (size/3)+1);
		writeJson(jsonObject);
	}
	//获得优秀举办方
	public void getExcelentHost() {
		JSONObject jsonObj = new JSONObject();
		int current = Integer.parseInt(request.getParameter("current"));
		int pagesize=16;
		List<User> hostList=userService.getExcelentHost(current,pagesize);
		jsonObj.put("hostList", hostList);
		writeJson(jsonObj);
	}
	public void getExcelentHostNum() {
		JSONObject jsonObj = new JSONObject();
		int num=userService.getExcelentHostNum();
		jsonObj.put("num", (num/16)+1);
		writeJson(jsonObj);
	}
	public void loadExcelentPersonalNum(){
		JSONObject jsonObj = new JSONObject();
		int num=userService.loadExcelentPersonalNum();
		jsonObj.put("num", (num/16)+1);
		writeJson(jsonObj);
	}
	public void loadExcelentPersonal(){
		JSONObject jsonObj = new JSONObject();
		int current = Integer.parseInt(request.getParameter("current"));
		int pagesize=16;
		List<User> userList=userService.loadExcelentPersonal(current,pagesize);
		jsonObj.put("userList", userList);
		writeJson(jsonObj);
	}
	public void saveUserInfo() {
		JSONObject jsonObj = new JSONObject();
		int sex= Integer.parseInt(request.getParameter("sex"));
		boolean sex_user=true;
		if (sex==0) 
		sex_user=false;
		String bir = request.getParameter("bir");//日期转换
		String home=request.getParameter("home");
		String mail=request.getParameter("mail");
		String resume=request.getParameter("resume");
		String userno=(String)request.getSession().getAttribute("userno");
		User user=userService.findUserById(userno);
		user.setUserSex(sex_user);user.setUserAddress(home);
		user.setUserBir(new Date());user.setUserMail(mail);
		user.setResume(resume);
		userService.updateUser(user);
	}
}

