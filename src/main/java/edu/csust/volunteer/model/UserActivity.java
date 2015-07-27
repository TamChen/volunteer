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
@Table(name = "t_useractivity")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserActivity {
	private int id;
	private int activityId;
	private String pic;//活动图片
	private String userNo;
	private int workHour;//工作时长
	private int flag;//1:报名2、通过0：是感兴趣,3审核未通过
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "ACTIVITYID", length = 20, nullable = false)
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	@Column(name = "USERNO", length = 20, nullable = false)
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	@Column(name = "UPIC", length = 200, nullable = false)
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	@Column(name = "WorkHour", length = 40, nullable = false)
	public int getWorkHour() {
		return workHour;
	}
	public void setWorkHour(int workHour) {
		this.workHour = workHour;
	}
	@Column(name = "FLAG", length = 1, nullable = false)
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "UserActivity:{[id:"+this.id+"],[userNo:"+this.userNo+"],[flag:"+this.flag+"],[workHour:"+this.workHour+"]}";
	}
	
}
