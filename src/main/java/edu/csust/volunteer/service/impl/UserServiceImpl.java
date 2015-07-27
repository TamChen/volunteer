package edu.csust.volunteer.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.csust.volunteer.dao.UserDao;

import edu.csust.volunteer.model.User;
import edu.csust.volunteer.service.UserService;
//import edu.csust.volunteer.vo.DataGrid;
import edu.csust.volunteer.vo.PictureVO;
import edu.csust.volunteer.vo.UserBackend;
import edu.csust.volunteer.vo.UserVO;

@Service("userService")
@Transactional(readOnly = true)
// 声明这个service所有方法需要事务管理。每一个业务方法开始时都会打开一个事务。
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public Serializable save(User user) {
		return userDao.save(user);
	}

	@Override
	public Boolean isUserNoExists(String userNo) {
		return userDao.isUserNoExists(userNo);
	}

	@Override
	public List<User> queryUserAll() {
		String hql="from User";
		return userDao.find(hql);
	}

	@Override
	public UserVO getUserByUserNo(String userno) {
		UserVO user=new UserVO();
		String hql="select password from User where userno = ?";
		Object params[]={userno};
		String password=userDao.getPasswordByUserNo(hql, params);
		user.setUserNo(userno);
		user.setPassword(password);
		return user;
	}

	@Override
	public boolean isUserNoBlocked(String userNo) {
		return userDao.isUserNoBlocked(userNo);
	}

	@Override
	public User findUserById(String userno) {
		//根据学号找到该人的姓名
		String hql="from User where userno = ?";
		Object params[]={userno};
		return userDao.load(hql, params);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void updateUserWorkTime(User user) {
		userDao.updateUser(user);
	}

	@Override
	public int getUserTotalNumber() {
		String hql = "select count(*) from User where status=false";
		Object params[]={};
		return userDao.getUserTotalNumber(hql,params);
	}
	//锁住某个用户的功能
	@Override
	public List<UserBackend> getUserListBackend(int current, int userListSize) {
		String hql="select t.id,t.username,t.userno,t.userCollege,t.userMajor,t.userTele,t.userRate,t.workTime from User t where t.status=false";
		System.out.println(hql);
		List<UserBackend> userList=new ArrayList<UserBackend>();
		List<Object[]> userInfoList=userDao.getUserListBackend(hql,current,userListSize);
		for(Object[] object : userInfoList){      
			UserBackend userBackend=new UserBackend();
			userBackend.setId((Integer)object[0]);
			userBackend.setUsername((String)object[1]);
			userBackend.setUserno((String)object[2]);
			userBackend.setUserCollege((String)object[3]);
			userBackend.setUserGrade((String)object[4]);
			userBackend.setUserTel((String)object[5]);
			userBackend.setUserRate((Integer)object[6]);
			userBackend.setWorkTime((Integer)object[7]);
			userList.add(userBackend);
        }  
		return userList;
	}
	@Override
	public void updateUserInfo(String userno, String textarea, String statu) {
			User user=findUserById(userno);
			if (statu.equals("userintro")) {
				user.setUserIntro(textarea);
			}else if (statu.equals("usersign")) {
				user.setSign(textarea);
			}
			userDao.updateUser(user);
	}

	@Override
	public List<User> getUserRateList(int current, int pagesize) {
		String hql="from User where outstanding=true and status=false order by userRate asc";
		return userDao.getUserInfoList(hql, current,pagesize);
	}

	@Override
	public int getUserRateListNum() {
		String hql="select count(*) from User where outstanding=true and status=false";
		Object params[]={};
		return userDao.getUserTotalNumber(hql,params);
	}
	//获得活跃举办方,,,userrate用作发布活动数
	@Override
	public List<User> getExcelentHost(int current,int pagesize) {
		String hql="from User where status=true order by userRate desc";
		return userDao.getUserInfoList(hql, current,pagesize);
	}

	@Override
	public int getExcelentHostNum() {
		String hql="select count(*) from User where status=true";
		Object params[]={};
		return userDao.getUserTotalNumber(hql,params);
	}

	@Override
	public int loadExcelentPersonalNum() {
		String hql="select count(*) from User where status=false";
		Object params[]={};
		return userDao.getUserTotalNumber(hql,params);
	}

	@Override
	public List<User> loadExcelentPersonal(int current, int pagesize) {
		String hql="from User where status=false order by userRate desc";
		return userDao.getUserInfoList(hql, current,pagesize);
	}
}
