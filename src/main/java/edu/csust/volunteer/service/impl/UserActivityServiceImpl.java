package edu.csust.volunteer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import edu.csust.volunteer.dao.UserActivityDao;
import edu.csust.volunteer.dao.UserDao;
import edu.csust.volunteer.dao.ActivityDao;
import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.model.UserActivity;
import edu.csust.volunteer.service.ActivityService;
import edu.csust.volunteer.service.UserActivityService;
import edu.csust.volunteer.service.UserService;
import edu.csust.volunteer.vo.ActivityUser;
import edu.csust.volunteer.vo.ApplyUser;
import edu.csust.volunteer.vo.PictureVO;
import edu.csust.volunteer.vo.UserBackend;
@Service("userActivityService")
public class UserActivityServiceImpl implements UserActivityService {
	@Autowired
	private UserActivityDao userActivityDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ActivityDao activityDao; 
	@Override
	public int getFlagByActIdAndUserno(int activityId, String userno) {
		String hql="select flag from UserActivity where activityId=? and userNo=?";
		Object params[]={activityId,userno};
		int flag=userActivityDao.getFlagByActIdAndUserno(hql,params);
		System.out.println("返回的标志"+flag);
		return flag;
	}
	@Override
	public void saveUserActivity(UserActivity userActivity) {
		userActivityDao.saveUserActivity(userActivity);
		
	}
	@Override
	public List<ApplyUser> getJoinActivityUserByID(int activityID,int current, int applySize) {
		String hql = "from UserActivity ua where ua.activityId="+activityID;
		List<UserActivity> userList=userActivityDao.getJoinActivityUserByID(hql,current,applySize);
		if (userList==null) {
			return null;
		}else {
			return transFormUserActivityToApplyUser(userList);
		}
	}

	@Override
	public void setUserJoinedActivity(UserActivity ua) {
		//通过审核，user的消息数+1
		String hql="update User t set t.infoNum =t.infoNum+1 where userno = ?";
		Object params[]={ua.getUserNo()};
		userDao.executeByHql(hql, params);
		userActivityDao.setUserJoinedActivity(ua);
	}

	@Override
	public UserActivity getJoinActivityUserByUAID(int userActivityID) {
		String hql = "from UserActivity ua where ua.id="+userActivityID;
		return userActivityDao.getJoinActivityUserByUAID(hql);
	}
	
	@Override
	public int getActivityByActIdAndUserno(int activityId, String userno) {
		String hql="select flag from UserActivity where activityId=? and userNo=?";
		Object params[]={activityId,userno};
		int flag=userActivityDao.getFlagByActIdAndUserno(hql,params);
		return flag;
	}
	@Override					
	public int getApplyTotal(int activityId) {
		String hql="select count(*) from UserActivity where activityId=?";
		Object params[]={activityId};
		return userActivityDao.getApplyTotal(hql,params);
	}
	@Override
	public void deletRecordById(int activityID) {
		String hql="delete from UserActivity acti where acti.activityId=?";
		Object params[]={activityID};
		userActivityDao.deleteUserActivity(hql, params);
	}
	//在用户界面，将用户相关的活动都列出
	@Override
	public List<ActivityUser> getActivityByUserNo(String userno, int current,
			int pageSize) {
		String hql = "from UserActivity ua where ua.userNo='"+userno+"'";
		List<UserActivity> userList=userActivityDao.getJoinActivityUserByID(hql,current,pageSize);
		if (userList==null) {
			return null;
		}else {
			return transFormUserActivity(userList);
		}
	}
	@Override
	public int getUserActivityNum(String userno) {
		String hql="select count(*) from UserActivity where userNo=?";
		Object params[]={userno};
		return userActivityDao.getApplyTotal(hql,params);
	}
	//转换格式传数据到前台//根据关系表中的管理员id找到管理员的信息
	private List<ApplyUser> transFormUserActivityToApplyUser(List<UserActivity> userList) {
		List<ApplyUser> applyUserList=Lists.newArrayList(Collections2.transform(userList, new Function<UserActivity,ApplyUser>() {
            @Override
            public ApplyUser apply(UserActivity input) {
            	ApplyUser applyUser=new ApplyUser();
            	User user=userService.findUserById(input.getUserNo());
            	applyUser.setId(input.getId());
            	applyUser.setUserNo(input.getUserNo());
            	applyUser.setUserName(user.getUsername());
            	applyUser.setCollege(user.getUserCollege());
            	applyUser.setGrade(user.getUserMajor());
            	applyUser.setStatu(input.getFlag());
            	applyUser.setTotaltime(user.getWorkTime());
            	applyUser.setWorktime(input.getWorkHour());
            	applyUser.setUserHead(user.getUserHead());
            	return applyUser;
            }
        }));
		return applyUserList;
	}
	private List<ActivityUser>  transFormUserActivity(List<UserActivity> activityList){
		List<ActivityUser> activityUserList=Lists.newArrayList(Collections2.transform(activityList, new Function<UserActivity,ActivityUser>() {
			@Override
			public ActivityUser apply(UserActivity input) {
				ActivityUser activityUser=new ActivityUser();
				Activity activity=activityService.getActivityByID(input.getActivityId());
				activityUser.setActivityId(activity.getId());
				activityUser.setTitle(activity.getTitle());
				activityUser.setAddress(activity.getAddress());
				activityUser.setAdminName(activity.getAdminName());
				activityUser.setActivityTime("");//这里应该是某种格式的时间
				activityUser.setActivityStatu(activity.getState());
				activityUser.setUserStatu(input.getFlag());
				activityUser.setUserWorkTime(input.getWorkHour());
				activityUser.setNumber(activity.getNumber());
				activityUser.setInterest(activity.getInterest());
				activityUser.setAttend(activity.getAttend());
				activityUser.setPic(activity.getPic());
				return activityUser;
			}
        }));
		return activityUserList;
	}
	@Override
	public List<PictureVO> getUserActivityAll(String userno) {
		String hql="select t.id,t.activityId,t.pic,t.flag from UserActivity t where t.userNo='"+userno+"' order by id desc";
		List<Object[]> objects=userActivityDao.getUserActivityAll(hql);
		List<PictureVO> pictureList=new ArrayList<PictureVO>();
		for (Object[] objects2 : objects) {
			PictureVO VO=new PictureVO();
			VO.setId((Integer)objects2[0]);
			VO.setUrl(((Integer)objects2[1]).toString());
			VO.setPicPath((String)objects2[2]);
			VO.setStatu((Integer)objects2[3]);
			pictureList.add(VO);
		}
		return pictureList;
	}
	@Override
	public int getSizeOfUserActivity(String userno, int param) {
		String hql="select count(*) from UserActivity where userNo=? and flag=?";
		Object params[]={userno,param};
		return userActivityDao.getApplyTotal(hql,params);
	}
	@Override
	public boolean deleteUserActivity(String userno, int activityId) {
		String hql="delete from UserActivity where userNo=? and activityId=?";
		Object params[]={userno,activityId};
		userActivityDao.deleteUserActivity(hql,params);
		return true;
	}
	//根据管理员的id找到所有发布的活动信息；
	@Override
	public List<ActivityUser> getAdminActivityInfo(String adminno, int current, int pageSize){
		String hql = "from Activity where adminID='"+adminno+"'order by id desc";
		List<Activity> activityList = activityDao.getActivityList(hql, current,pageSize);
		if (activityList==null) {
			return null;
		}
		return transFormActivityToActivityUser(activityList);
	}
	//将activity转化为activityuser进行前台展示
	private List<ActivityUser> transFormActivityToActivityUser(List<Activity> activityList){
		List<ActivityUser> activityUserList=Lists.newArrayList(Collections2.transform(activityList, new Function<Activity,ActivityUser>() {
			@Override
			public ActivityUser apply(Activity activity) {
				ActivityUser activityUser=new ActivityUser();
				activityUser.setActivityId(activity.getId());
				activityUser.setTitle(activity.getTitle());
				activityUser.setAddress(activity.getAddress());
				activityUser.setAdminName(activity.getAdminName());
				activityUser.setActivityTime("");//这里应该是某种格式的时间
				activityUser.setActivityStatu(activity.getState());
				activityUser.setUserStatu(0);
				activityUser.setUserWorkTime(0);
				activityUser.setNumber(activity.getNumber());
				activityUser.setInterest(activity.getInterest());
				activityUser.setAttend(activity.getAttend());
				activityUser.setPic(activity.getPic());
				return activityUser;
			}
        }));
		return activityUserList;
	}
	@Override
	public int getAllAdminActivitySize(String userno, int param){
		String hql="select count(*) from Activity where adminID=?";
		Object params[]={userno};
		return activityDao.getActivityTotal(hql,params);
	}
}
