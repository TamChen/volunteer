package edu.csust.volunteer.vo;

import java.util.Date;

public class UserBackend {
	private int id;
	private String username;
	private String userno;
	private String userGrade;//专业与年级
	private String userCollege;//学院
	private String userTel;
    private int userRate;//义工排名。可以设在某个时间段对所有人进行排名
    private int workTime;//总时长
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
	public String getUserCollege() {
		return userCollege;
	}
	public void setUserCollege(String userCollege) {
		this.userCollege = userCollege;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public int getUserRate() {
		return userRate;
	}
	public void setUserRate(int userRate) {
		this.userRate = userRate;
	}
	public int getWorkTime() {
		return workTime;
	}
	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}
    
}
