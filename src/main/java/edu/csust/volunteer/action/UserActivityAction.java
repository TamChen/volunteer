package edu.csust.volunteer.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.Notice;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.model.UserActivity;
import edu.csust.volunteer.service.ActivityService;
import edu.csust.volunteer.service.NoticeService;
import edu.csust.volunteer.service.StatisticService;
import edu.csust.volunteer.service.UserActivityService;
import edu.csust.volunteer.service.UserService;
import edu.csust.volunteer.vo.ActivityUser;
import edu.csust.volunteer.vo.ApplyUser;
import edu.csust.volunteer.vo.PictureVO;

@Action("userActivityAction")
public class UserActivityAction extends BaseAction<UserActivity>{
	private static final long serialVersionUID = 1L;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserActivityService userActivityService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private UserService userService;
	@Autowired
	private StatisticService statisticService;
	//前台方法
	/*用户活动表的flag字段总共有三种状态，0、感兴趣1、报名了，2、通过,3表示在useractivity中没有找到信息4表示已结束
	在审核时，应该有审核不通过或者审核通过*/
	//所有的操作在插入后应该都是更新，
	public void getActivityStatu(){
		Date now=new Date();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String activityId=request.getParameter("activityid");
		int flag=userActivityService.getFlagByActIdAndUserno(Integer.parseInt(activityId),(String)session.getAttribute("userno"));
		Activity activity=activityService.getActivityByID(Integer.parseInt(activityId));
		System.out.println("活动状态"+activity.getState());
		/*if (activity.getState()==135||activity.getEndTime().before(now)) {
			flag=4;//状态为4表示活动结束，活动超过时间会自动结束
		}*/
		if (activity.getState()==135) {
		flag=4;//状态为4表示活动结束，活动超过时间会自动结束
		}
		System.out.println("flag++"+flag);
		JSONObject jsonData=new JSONObject();
		jsonData.put("flag",flag);
		writeJson(jsonData);
	}
	//点击报名
	public void applyAttendActivity(){
		statisticService.increaseAttendActivityNum();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String activityId=request.getParameter("activityid");
		System.out.println(activityId);
		System.out.println(session.getAttribute("userno"));
		UserActivity userActivity=new UserActivity();
		Activity activity=activityService.getActivityByID(Integer.parseInt(activityId));
		userActivity.setActivityId(Integer.parseInt(activityId));
		userActivity.setUserNo((String)session.getAttribute("userno"));
		userActivity.setFlag(1);//1报名2通过0感兴趣
		userActivity.setWorkHour(0);
		userActivity.setPic(activity.getPic());
		userActivityService.saveUserActivity(userActivity);
		//将activity表中的报名人数增加1，参加人数为审核通过的人数；,还剩下多少人未审核
		activity.setUnVrify(activity.getUnVrify()+1);
		activity.setRegistration(activity.getRegistration()+1);
		activityService.updateActivity(activity);
		//将报名人数传回前台
		int number[] =new int[2];
		number[0]=activity.getRegistration()+1;
		number[1]=activity.getInterest();
		JSONObject jsonData=new JSONObject();
		jsonData.put("number",number);
		writeJson(jsonData);
	}
	
	//==============================================================================================
	//后台方法
	/*获取工作时长，也会进行通知*/
	public void setUserActivityWorkHourActivity(){
		JSONObject jsonObj = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean setUserActivityWorkHourActivityResult=false;
		try {
			UserActivity ua = userActivityService.getJoinActivityUserByUAID(Integer.parseInt(request.getParameter("userActivityID")));
			int worktime=Integer.parseInt(request.getParameter("userActivityWorkHour"));
			ua.setWorkHour(ua.getWorkHour()+worktime);
			User user=userService.findUserById(ua.getUserNo());
			user.setWorkTime(user.getWorkTime()+worktime);
			userService.updateUserWorkTime(user);
			userActivityService.setUserJoinedActivity(ua);
			setUserActivityWorkHourActivityResult=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObj.put("setUserActivityWorkHourActivityResult", setUserActivityWorkHourActivityResult);
		writeJson(jsonObj);
	}
	/*通过审核
	通过审核，notice表进行相应的通知*/
	public void setUserJoinedActivity(){
		JSONObject jsonObj = new JSONObject();
		boolean setUserJoinedResult=false;
		HttpSession session = ServletActionContext.getRequest().getSession();
		String frind=(String)session.getAttribute("userno");
		try {
			UserActivity ua = userActivityService.getJoinActivityUserByUAID(Integer.parseInt(request.getParameter("userActivityID")));
			//activity表中参加的人数加1;
			Activity activity=activityService.getActivityByID(ua.getActivityId());
			activity.setAttend(activity.getAttend()+1);
			if (!(ua.getFlag()==3)) {
				activity.setUnVrify(activity.getUnVrify()-1);
			}
			activityService.updateActivity(activity);
			ua.setFlag(2);//通过
			userActivityService.setUserJoinedActivity(ua);
			setUserJoinedResult=true;
			//私信信息
			Notice notice1=new Notice(ua.getUserNo(),frind,ua.getUserNo(),frind,true,
					"审核通过的通知","恭喜你通过了XXX活动的审核，请于活动规定时间按时到场，谢谢。",new Date(),true);
			Notice notice2=new Notice(frind,ua.getUserNo(),ua.getUserNo(),frind,true,
					"审核通过的通知","恭喜你通过了XXX活动的审核，请于活动规定时间按时到场，谢谢。",new Date(),true);
			noticeService.saveNotice(notice1);
			noticeService.saveNotice(notice2);
			//审核通过会有用户表中的私信数+1;留言表中的信息也+1
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObj.put("setUserJoinedResult", setUserJoinedResult);
		writeJson(jsonObj);
	}
	public void ignoreUser() {
		JSONObject jsonObj = new JSONObject();
		boolean setUserJoinedResult=false;
		HttpSession session = ServletActionContext.getRequest().getSession();
		String friend=(String)session.getAttribute("userno");//这里是发消息方
		try {
			UserActivity ua = userActivityService.getJoinActivityUserByUAID(Integer.parseInt(request.getParameter("userActivityID")));
			Activity activity=activityService.getActivityByID(ua.getActivityId());
//			activity.setAttend(activity.getAttend()+1);
			if (!(ua.getFlag()==2)) {
				activity.setUnVrify(activity.getUnVrify()-1);
			}
			activityService.updateActivity(activity);
			ua.setFlag(3);//忽略
			userActivityService.setUserJoinedActivity(ua);
			setUserJoinedResult=true;
			//私信信息
			Notice notice1=new Notice(ua.getUserNo(),friend,ua.getUserNo(),friend,true,
					"审核不通过通知","未通过审核。",new Date(),true);
			Notice notice2=new Notice(friend,ua.getUserNo(),friend,ua.getUserNo(),true,
					"审核不通过通知","未通过审核。",new Date(),true);
			noticeService.saveNotice(notice1);
			noticeService.saveNotice(notice2);
			//审核通过会有用户表中的私信数+1;留言表中的信息也+1
			//activity表中参加的人数加1;			
			User user=userService.findUserById(ua.getUserNo());
			user.setInfoNum(user.getInfoNum()+1);
			userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObj.put("setUserJoinedResult", setUserJoinedResult);
		writeJson(jsonObj);
	}
	/**
	 * 获取申请参加此活动的用户
	 * @return
	 */
	public void getJoinActivityUser(){
		JSONObject jsonObj = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		int activityId=Integer.parseInt(request.getParameter("activityid"));
		int current=Integer.parseInt(request.getParameter("current"));
		int applySize=Integer.parseInt(request.getParameter("applySize"));
		List<ApplyUser> listUserActivity = userActivityService.getJoinActivityUserByID(activityId,current,applySize);
		jsonObj.put("listUserActivity", listUserActivity);
		writeJson(jsonObj);
	}
/*	public void  getExcelentHostNum(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int activityId=Integer.parseInt(request.getParameter("activityid"));
		int ApplyTotal = userActivityService.getApplyTotal(activityId);
		writeJson(ApplyTotal);
	}*/
	public void getApplyTotal() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int activityId=Integer.parseInt(request.getParameter("activityid"));
		int ApplyTotal = userActivityService.getApplyTotal(activityId);
		writeJson(ApplyTotal);
	}
	
	public void getUserActivity() {
		JSONObject jsonObj = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		int current=Integer.parseInt(request.getParameter("current"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		List<ActivityUser> listUserActivity = userActivityService.getActivityByUserNo(userno,current,pageSize);
		jsonObj.put("listUserActivity", listUserActivity);
		writeJson(jsonObj);
	}
	//获得所有的用户相关的活动图片，在前台进行分类
	public void getUserActivityAll(){
		JSONObject jsonObj = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=request.getParameter("userno");//只要两项activityid、pic、statu userActivityList
		System.out.println("userno"+userno);
		List<PictureVO> userActivityList=userActivityService.getUserActivityAll(userno);
		jsonObj.put("userActivityList", userActivityList);
		writeJson(jsonObj);
		//返回所有的信息，
	}
	
	//获得所有信息并分页
	public void getAllUserActivityInfo(){
		JSONObject jsonObj = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		//0感兴趣++++1报名++++++2参加（审核通过）+++++3（审核未通过）
		int param=Integer.parseInt(request.getParameter("param"));
		int current=Integer.parseInt(request.getParameter("current"));
		int size=Integer.parseInt(request.getParameter("size"));
		List<ActivityUser> ActivityList = userActivityService.getActivityByUserNo(userno, current, size);
		List<ActivityUser> tempList=new ArrayList<ActivityUser>();
		for (ActivityUser activityUser : ActivityList) 
		if (activityUser.getUserStatu()!=param) 
		tempList.add(activityUser);
		ActivityList.removeAll(tempList);
		jsonObj.put("size", ActivityList.size());
		jsonObj.put("ActivityList", ActivityList);
		writeJson(jsonObj);
	}
	public void getUserActivityTotal(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		int ActivityNumber = userActivityService.getUserActivityNum(userno);
		writeJson(ActivityNumber);
	}
	public void getSizeOfUserActivity() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		int param=Integer.parseInt(request.getParameter("param"));
		int num=userActivityService.getSizeOfUserActivity(userno,param);
		writeJson(num);
	}
	public void deleteUserActivity() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=(String)request.getSession().getAttribute("userno");
		int activityId=Integer.parseInt(request.getParameter("activityid"));
		boolean issuccess=userActivityService.deleteUserActivity(userno,activityId);
		writeJson(issuccess);
	}
	//获得管理员发布的所有活动
	public void getAllAdminActivityInfo(){
		JSONObject jsonObj = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		//0感兴趣++++2所有的管理员活动
		int param=Integer.parseInt(request.getParameter("param"));
		int current=Integer.parseInt(request.getParameter("current"));
		int size=Integer.parseInt(request.getParameter("size"));
		List<ActivityUser> tempList=new ArrayList<ActivityUser>();
		if(param==0){
			List<ActivityUser> ActivityList = userActivityService.getActivityByUserNo(userno, current, size);
			for (ActivityUser activityUser : ActivityList) 
			if (activityUser.getUserStatu()==0) 
			tempList.add(activityUser);
		}else{
			tempList=userActivityService.getAdminActivityInfo(userno,current,size);
		}
		jsonObj.put("size", tempList.size());
		jsonObj.put("ActivityList", tempList);
		writeJson(jsonObj);
	}
	//获得管理员发布的活动和感兴趣活动的大小
	public void getAdminActivityInfoSize(){
		int num=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		int param=Integer.parseInt(request.getParameter("param"));
		if (param==2) {
			num=userActivityService.getAllAdminActivitySize(userno,param);
		}else{
			num=userActivityService.getSizeOfUserActivity(userno,param);
		}
		writeJson(num);
	}
	//一个活动可以由多个学院一起办，查找该活动的所有主办方
//	public void getExcelentHost() {
//		JSONObject jsonObj = new JSONObject();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		int activityId=Integer.parseInt(request.getParameter("activityid"));
//	}
	public void cancelAttendActivity(){
		JSONObject jsonObj = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		int activityId=Integer.parseInt(request.getParameter("activityid"));
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userno=(String)session.getAttribute("userno");//这里是发消息方
		Activity activity=activityService.getActivityByID(activityId);
		boolean issuccess=userActivityService.deleteUserActivity(userno, activityId);
		int number[] =new int[2];
		number[0]=activity.getRegistration()-1;
		number[1]=activity.getInterest();
		activity.setRegistration(activity.getRegistration()-1);
		activityService.updateActivity(activity);
		jsonObj.put("issuccess", issuccess);
		jsonObj.put("number", number);
		writeJson(jsonObj);
	}
}
