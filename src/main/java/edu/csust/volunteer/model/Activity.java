package edu.csust.volunteer.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.type.YesNoType;

import edu.csust.volunteer.support.EnableQueryCache;

@Entity
@Table(name = "t_activity")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Activity {
	private int id;
	private String adminID;//管理员id发起人
	private String adminName;
	private String pic;
	private String pic2;
	private String title;
	private String intro;
	private String detail;
	private Date beginTime;
	private Date endTime;
//	private String activityTime;
	private String address;
	private int number;//需要的人数
	private int attend;//参加的人数
	private int interest;//感兴趣的人数
	private int registration;//报名人数
	private int unVrify;//是否已审；审核的人数
	private int state;//是否审核通过y1n2，是否编辑完成y3n4,是否结束y5n6
	private boolean special;
	private Date publishDate;
	private int praise;
//	public String getActivityTime() {
//		//时间问题
//		Calendar beginDate = Calendar.getInstance();
//		beginDate.setTime(this.beginTime);
//		Calendar endDate = Calendar.getInstance();
//		endDate.setTime(this.endTime);
//		beginDate.get(Calendar.MONTH);
//		beginDate.get(Calendar.DATE);
//		beginDate.get(Calendar.HOUR_OF_DAY);
//		beginDate.get(Calendar.MINUTE);
//		return activityTime;
//	}
	@Id  //应该根据时间来决定是否结束。目前是通过后台管理结束活动
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "VSPECIAL", length = 5, nullable = true)
	public boolean isSpecial() {
		return special;
	}
	public void setSpecial(boolean special) {
		this.special = special;
	}
	
	@Column(name = "VPIC2", length = 100, nullable = true)
	public String getPic2() {
		return pic2;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	@Column(name = "VUNVIRIFY", length = 50, nullable = false)
	public int getUnVrify() {
		return unVrify;
	}
	public void setUnVrify(int unVrify) {
		this.unVrify = unVrify;
	}
	
	@Column(name = "VADMINNAME", length = 50, nullable = false)
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	@Column(name = "VREGISTRATION", length = 50, nullable = false)
	public int getRegistration() {
		return registration;
	}
	public void setRegistration(int registration) {
		this.registration = registration;
	}
	@Column(name = "VADDRESS", length = 100, nullable = false)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "VADMINID", length = 20, nullable = false)
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	
	@Column(name = "VPIC", length = 200, nullable = false)
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
	
	@Column(name = "VDETAIL", length =8000, nullable = false)
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VPUBLICS")
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	@Column(name = "VPRAISE", length = 10, nullable = false)
	public int getPraise() {
		return praise;
	}
	public void setPraise(int praise) {
		this.praise = praise;
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
	
	@Column(name = "VSTATE", length = 3, nullable = false)
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
