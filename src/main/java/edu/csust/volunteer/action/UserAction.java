package edu.csust.volunteer.action;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ModelDriven;

import edu.csust.volunteer.model.DataObject;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.service.UserService;
import edu.csust.volunteer.util.Encrypt;
import edu.csust.volunteer.vo.UserVO;

@Results( { @Result(name = "success", location = "/main.jsp"), 
@Result(name = "error", location = "/error.jsp") }) 
public class UserAction extends BaseAction implements ModelDriven<UserVO> {

	private static final Logger LOGGER = Logger.getLogger(UserAction.class);

	private UserVO user;

	private UserService userService;

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

//	public void test() {
//		LOGGER.info("进入useraction");
//	}

	public void regist() {
		DataObject dataObject = new DataObject();
		if (user != null) {
			if (StringUtils.isBlank(user.getUserName())) {
				dataObject.setMsg("用户名不能为空！");
				writeJson(dataObject);
				return;
			} else if (userService.isUsernameExists(user)) { // 用户名已经存在
				dataObject.setMsg("用户名已经存在！");
				writeJson(dataObject);
				return;
			} else if (StringUtils.isBlank(user.getPassword())) {
				dataObject.setMsg("密码不能为空！");
				writeJson(dataObject);
				return;
			}
//			user.setId(UUID.randomUUID());
//			user.setCreateDate(new Date());
//			user.setModifyDate(user.getCreateDate());
			user.setPassword(Encrypt.e(user.getPassword()));
			User u = new User();
			try {
				BeanUtils.copyProperties(u, user);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				Serializable res = userService.save(u);
				if (res != null) {
					dataObject.setSuccess(true);
					dataObject.setMsg("注册成功！");
				} else {
					dataObject.setMsg("注册失败！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				dataObject.setMsg("注册失败！");
			}
			writeJson(dataObject);
		}
	}
	@Action(value = "test", 
			results = { @Result(name = "success", location = "/login.jsp") })
	public String test() {
		LOGGER.info("进入useraction");
		return "success";
	}
	
	@Action(value = "add", 
			results = { @Result(name = "success", location = "/index.jsp") })  
	public String add() {
		DataObject dataObject = new DataObject();
		if (user != null) {
			if (StringUtils.isBlank(user.getUserName())) {
				dataObject.setMsg("用户名不能为空！");
				writeJson(dataObject);
				return "success";
			} else if (userService.isUsernameExists(user)) { // 用户名已经存在
				dataObject.setMsg("用户名已经存在！");
				writeJson(dataObject);
				return "success";
			} else if (StringUtils.isBlank(user.getPassword())) {
				dataObject.setMsg("密码不能为空！");
				writeJson(dataObject);
				return "success";
			}
//			user.setId(UUID.randomUUID().toString());
//			user.setCreateDate(new Date());
//			user.setModifyDate(user.getCreateDate());
			user.setPassword(Encrypt.e(user.getPassword()));
			User u = new User();
			try {
				BeanUtils.copyProperties(u, user);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				Serializable res = userService.save(u);
				if (res != null) {
					dataObject.setSuccess(true);
					dataObject.setMsg("添加成功！");
					dataObject.setObject(user);
				} else {
					dataObject.setMsg("添加失败！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				dataObject.setMsg("添加失败！");
			}
			writeJson(dataObject);
		}
		return "succcess";
	}
	@Action(value = "userAction")
//	@Results({@Result(name="success",location="/index.jsp")})
	public void login() {
		DataObject dataObject = new DataObject();
		if (user != null) {
			if (StringUtils.isBlank(user.getUserName())) {
				dataObject.setMsg("用户名不能为空！");
				writeJson(dataObject);
				return;
			} else if (StringUtils.isBlank(user.getPassword())) {
				dataObject.setMsg("密码不能为空！");
				writeJson(dataObject);
				return;
			}
			user.setPassword(Encrypt.e(user.getPassword()));
			User tu = userService.login(user);
			if (tu != null) {
				try {
					BeanUtils.copyProperties(user, tu);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dataObject.setSuccess(true);
				dataObject.setMsg("登录成功！");
			} else {
				dataObject.setMsg("用户名或密码不正确！");
			}
			writeJson(dataObject);
		}
	}

	@Override
	public UserVO getModel() {
		if (user == null) {
			user = new UserVO();
		}
		return user;
	}
}
