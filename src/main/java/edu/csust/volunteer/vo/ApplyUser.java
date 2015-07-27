package edu.csust.volunteer.vo;

public class ApplyUser {
	private int id;//用户活动表的主键id
	private String userNo;
	private String userName;
	private String college;
	private String grade;
	private int statu;
	private String userHead;
	private int worktime;
	private int totaltime;
	
	public String getUserHead() {
		return userHead;
	}
	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatu() {
		return statu;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public int getWorktime() {
		return worktime;
	}
	public void setWorktime(int worktime) {
		this.worktime = worktime;
	}
	public int getTotaltime() {
		return totaltime;
	}
	public void setTotaltime(int totaltime) {
		this.totaltime = totaltime;
	}
}
