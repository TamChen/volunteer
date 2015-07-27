package edu.csust.volunteer.vo;

import java.util.Date;

public class ActivityVo {
	private int id;
	private int activityId;
	private String adminphoto;
	private String adminName;
	private int adminID;
	private String pic;
	private String title;
	private int number;//需要的人数
	private int attend;//参加的人数
	private int interest;//感兴趣的人数
	private int praise;//赞的人数
	private Date publishDate;
	
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public int getPraise() {
		return praise;
	}
	public void setPraise(int praise) {
		this.praise = praise;
	}
	public String getAdminphoto() {
		return adminphoto;
	}
	public void setAdminphoto(String adminphoto) {
		this.adminphoto = adminphoto;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
}
