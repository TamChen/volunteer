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

import org.hibernate.annotations.CacheConcurrencyStrategy;

import edu.csust.volunteer.support.EnableQueryCache;

@Entity
@Table(name = "t_userdiary")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserDiary {
	private int id;
	private String title;
	private String userNo;
	private String userName;
	private Date publishTime;
	private String content;
	private int activityId;//默认下是审核通过的，日记可以与活动关联
	private boolean status;//true 审核通过，false审核未通过，，应该是默认通过，然后管理员可以查看，可以推荐，可以锁定
	private boolean nice;
	private int likeNum;
	private int param; //包含三个状态，0编辑的时候，1预览版、2发布了、
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "APARAM", length = 20, nullable = false)
	public int getParam() {
		return param;
	}

	public void setParam(int param) {
		this.param = param;
	}

	@Column(name = "ALIKENUM", length = 20, nullable = false)
	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	@Column(name = "ALtitle", length = 50, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "ALuserNo", length = 20, nullable = false)
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	
	@Column(name = "ALuserName", length = 100, nullable = false)
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ALpublishTime")
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "ALcontent", length = 5000, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "ALactivityId", length = 20, nullable = true)
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	@Column(name = "ALstatus", length = 1, nullable = false)
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column(name = "ALisNice", length = 1, nullable = false)
	public boolean isNice() {
		return nice;
	}

	public void setNice(boolean nice) {
		this.nice = nice;
	}

	@Override
	public String toString() {
		return "Log :{**id:[" + id + "],title:[" + title + "],userNo:["
				+ userNo + "],userName:[" + userName + "],publishTime:["
				+ publishTime + "],content:[" + content + "],activityId:["
				+ activityId + "],status:[" + status + "],isNice:[" + nice
				+ "]**}";
	}


}
