package edu.csust.volunteer.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.management.CacheManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.csust.volunteer.dao.ActivityDao;
import edu.csust.volunteer.dao.UserDao;
import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.model.UserActivity;
import edu.csust.volunteer.service.ActivityService;
import edu.csust.volunteer.service.UserActivityService;
import edu.csust.volunteer.service.UserService;
import edu.csust.volunteer.support.EhcacheManager;
import edu.csust.volunteer.vo.ActivityVo;
import edu.csust.volunteer.vo.PictureVO;
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
	private static final Logger LOGGER = Logger.getLogger(ActivityServiceImpl.class);
	@Autowired
	private ActivityDao activityDao;
	@Autowired
	private UserActivityService userActivityService;
	@Autowired 
	private UserService userService;
	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityVo> getIndexPageActivityInfo() {
		//传了两条最新的到前台了
		String hql="select title,adminID,pic2,number,attend,interest,id,publishDate,praise from Activity where state=136 and pic2!=''";
		Object params[]={};
		List<Object[]> indexPageActivityInfo;
		List<ActivityVo> activityVoList=new ArrayList<>();
		indexPageActivityInfo=(List<Object[]>)EhcacheManager.getElementValueFromCache(EhcacheManager.CACHE_ACTIVITYCACHE,"act_index");
		if (indexPageActivityInfo==null) {
			indexPageActivityInfo=activityDao.getIndexPageActivityInfo(hql,params);
			EhcacheManager.putElementToCache(EhcacheManager.CACHE_ACTIVITYCACHE,"act_index",indexPageActivityInfo);  
			LOGGER.info("首页活动信息未命中，从数据库调入缓存");
		}else {
			LOGGER.info("首页活动信息命中，从缓存取数据");
		}
		
		for(Object[] object : indexPageActivityInfo){     
			User user=null;
			ActivityVo activityVo=new ActivityVo();
			user=(User)EhcacheManager.getElementValueFromCache(EhcacheManager.CACHE_USERCACHE,(String)object[1]);
			if (user==null) {
				user=userService.findUserById((String)object[1]);
				EhcacheManager.putElementToCache(EhcacheManager.CACHE_USERCACHE,(String)object[1],user);  
				LOGGER.info("用户信息未命中，从数据库调入缓存");
			}else {
				LOGGER.info("用户信息命中，从缓存取数据");
			}
			activityVo.setTitle((String)object[0]);
			activityVo.setAdminphoto(user.getUserHead());//根据adminID找到发布员的头像;
			activityVo.setAdminName(user.getUsername());//根据adminID找到发布员的姓名;
			activityVo.setPic((String)object[2]);
			activityVo.setNumber((Integer)object[3]);
			activityVo.setAttend((Integer)object[4]);
			activityVo.setInterest((Integer)object[5]);
			activityVo.setActivityId((Integer)object[6]);
			activityVo.setPublishDate((Date)object[7]);
			activityVo.setPraise((Integer)object[8]);
			activityVoList.add(activityVo);
        }  
		return activityVoList;
	}
	@Override
	public List<Activity> getActivityListFront(int currentpage,String parm,int perPage) {
		Calendar calendar=Calendar.getInstance();
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println(day);
		int day2=calendar.get(Calendar.DAY_OF_WEEK);
		
		System.out.println(day2);
		String hql="";
		if (parm.equals("0")) {//综合排名
			hql="from Activity where state=135 or state=136 order by interest desc";
		}else if (parm.equals("1")){//最新发布
			System.out.println("hello");
			hql="from Activity where state=135 or state=136 order by publishDate desc";
		}else if (parm.equals("2")){//人气排名
			hql="from Activity where state=135 or state=136 order by registration desc";
		}else if (parm.equals("3")){//今天
			hql="from Activity where DAY(beginTime)="+day+" and state=136 order by id desc";
		}else if (parm.equals("4")){//明天
			hql="from Activity where DAY(beginTime)="+day+1+" and state=136 order by id desc";
		}else if (parm.equals("5")){//周末
			hql="from Activity where (dayofweek(beginTime)=1 or dayofweek(beginTime)=7) and state=136 order by id desc";
		}else if (parm.equals("6")){//最近一周
			Date date=new  Date();//取时间 
		    Calendar   calendar2   = Calendar.getInstance(); 
		    calendar2.setTime(date); 
		    calendar.add(Calendar.DAY_OF_YEAR,7);//把日期往后增加一天.整数往后推,负数往前移动 
		    date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
			hql="from Activity where datediff(beginTime, ?) <= 7";
			Object param[]={date};
			List<Activity> activityList=activityDao.getLastSevenActivityList(hql,param,currentpage,perPage);
			return activityList;
		}else {
			hql="from Activity where title like '%"+parm+"%' and (state=135 or state=136)";
		}
		if (("").equals(parm)||parm==null) {
			hql="from Activity where state=135 or state=136";
			System.out.println("hello2"+"+++++"+parm);
		}
		System.out.println("hql"+hql);
		List<Activity> activityList=activityDao.getActivityList(hql,currentpage,perPage);
		return activityList;
	}
	
	@Override
	public int getSearchTotalActivityNum(String parm) {
		String hql="select count(*) from Activity where title like '%"+parm+"%' and (state=135 or state=136)";
		if (("").equals(parm)||parm==null) {
			hql="select count(*) from Activity where state=135 or state=136";
		}
		Object params[]={};
		int countPage=activityDao.getActivityTotalPageNum(hql,params);
		return countPage;
	}

	//======================================================================================
	@Override//得到后台所有的活动
	public List<Activity> getActivityListBackend(int current, int size) {
		String hql = "from Activity order by id desc";
		List<Activity> activityList = activityDao.getActivityList(hql, current,size);
		return activityList;
	}

	@Override
	public void addOrUpdateActivity(Activity activity) {
		if (activity.getId()==0) {
			activityDao.addActivity(activity);
		}else {
			activityDao.updateActivity(activity);
		}
		
	}

	@Override
	public int getActivityTotal() {
		String hql = "select count(*) from Activity";
		Object params[]={};
		return activityDao.getActivityTotal(hql,params);
	}

	@Override
	public void deleteActivityByID(int activityID) {
		activityDao.deleteActivityByID(activityID);
	}

	@Override
	public Activity getActivityByID(int activityID) {
		String hql = "from Activity where id=?";
		Object params[]={activityID};
		return activityDao.getActivityById(hql,params);
	}

	@Override
	public void updateActivity(Activity activity) {
		activityDao.updateActivity(activity);
	}

	@Override
	public void saveActivityPic(int time,int activityId, String path) {
		String hql="";
		System.out.println("time+++"+time);
		if (time==1) {
			hql="update Activity t set t.pic='"+path+"',t.state=136 where t.id = ?";
		}else {
			hql="update Activity t set t.pic2='"+path+"',t.state=136 where t.id = ?";
		}
		System.out.println(hql);
		Object params[]={activityId};
		activityDao.saveActivityPic(hql,params);
	}
	@Override
	public List<PictureVO> getAdminActivity(String adminid) {
		String hql = "select t.id,t.id,t.pic,t.id from Activity t where t.adminID='"+adminid+"' order by id desc";
		List<Object[]> objects=activityDao.getAdminActivity(hql);
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
	//显示最新和专题活动，
	@Override
	public Activity getSpecialActivity() {
		String hql = "from Activity a where a.special=1 order by id";
		Activity activity=activityDao.getSpecialActivity(hql);
		if (activity==null) {
			hql="from Activity where pic2!='' order by id";
			activity=activityDao.getSpecialActivity(hql);
		}
		return activity;
	}
//获得所有的完成的项目
	@Override
	public List<Activity> getOffActivity() {
		String hql = "from Activity a where a.state=135 order by id";
		List<Activity> activityList=activityDao.getOffActivity(hql);
		return activityList;
	}
}
