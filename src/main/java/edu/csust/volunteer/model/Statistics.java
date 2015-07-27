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
@Table(name = "t_statistics")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Statistics {
	//每天的访问量,参加活动的人数
	private int id;
	private Date  date;
	private int accessNumDay;
	private int attendNum;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VDATE")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name = "VACCESS", length = 5, nullable = true)
	public int getAccessNumDay() {
		return accessNumDay;
	}
	public void setAccessNumDay(int accessNumDay) {
		this.accessNumDay = accessNumDay;
	}
	
	@Column(name = "VATTEND", length = 5, nullable = true)
	public int getAttendNum() {
		return attendNum;
	}
	public void setAttendNum(int attendNum) {
		this.attendNum = attendNum;
	}
	
}
