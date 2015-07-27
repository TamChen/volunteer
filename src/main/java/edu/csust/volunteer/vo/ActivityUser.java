package edu.csust.volunteer.vo;

import java.util.Date;

public class ActivityUser {
	private int activityId;
	private String adminName;
	private String title;
	private String activityTime;
	private String address;
	private int activityStatu;//是否审核通过y1n2，是否编辑完成y3n4,是否结束y5n6
	private int userWorkTime;//用户时长
	private int userStatu;//用户状态
	private int number;
	private int attend;
	private int interest;
	private String  pic;
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getAttend() {
		return attend;
	}
	public void setAttend(int attend) {
		this.attend = attend;
	}
	public int getInterest() {
		return interest;
	}
	public void setInterest(int interest) {
		this.interest = interest;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getActivityStatu() {
		return activityStatu;
	}
	public void setActivityStatu(int activityStatu) {
		this.activityStatu = activityStatu;
	}
	public int getUserWorkTime() {
		return userWorkTime;
	}
	public void setUserWorkTime(int userWorkTime) {
		this.userWorkTime = userWorkTime;
	}
	public int getUserStatu() {
		return userStatu;
	}
	public void setUserStatu(int userStatu) {
		this.userStatu = userStatu;
	}
	
}
