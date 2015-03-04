package edu.csust.volunteer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_activity")
public class Activity {
	private int id;
	private int activityId;

	private int adminID;
	private String pic;
	private String title;
	private String intro;
	private String detail;
	private Date beginTime;
	private Date endTime;
	private int number;//需要的人数
	private int attend;//参加的人数
	private int interest;//感兴趣的人数
	private int state;//状态
	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "VACTIVITYID", length = 20, nullable = false)
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	@Column(name = "VAID", length = 20, nullable = false)
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	
	@Column(name = "VPIC", length = 100, nullable = false)
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	@Column(name = "VTITLE", length = 100, nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "VINTRO", length = 200, nullable = false)
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	@Column(name = "VDETAIL", length = 500, nullable = false)
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VTIME")
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VDEADLINE")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name = "VNUMBER", length = 20, nullable = false)
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Column(name = "VATTEND", length = 20, nullable = false)
	public int getAttend() {
		return attend;
	}
	public void setAttend(int attend) {
		this.attend = attend;
	}
	
	@Column(name = "VINTEREST", length = 20, nullable = false)
	public int getInterest() {
		return interest;
	}
	public void setInterest(int interest) {
		this.interest = interest;
	}
	
	@Column(name = "VSTATE", length = 20, nullable = false)
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
