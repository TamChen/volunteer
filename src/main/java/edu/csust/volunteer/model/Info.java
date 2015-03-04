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

/*包含公告和新闻*/
@Entity
@Table(name = "t_info")
public class Info {
	private int id;
	private String title;
	private String content;
	private Date recordTime;
	private String author;
	//标明是新闻还是公告
	private String isnew;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "ITITLE", length = 100, nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "ICONTENT", length = 100, nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "IRECORDTIME")
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	
	@Column(name = "IAUTHOR", length = 100, nullable = false)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column(name = "IISNEW", length = 10, nullable = false)
	public String getIsnew() {
		return isnew;
	}
	public void setIsnew(String isnew) {
		this.isnew = isnew;
	}
	
	public String toString() {
		return new StringBuffer()
		.append("id[").append(getId()).append("],")
		.append("title[").append(getTitle()).append("],")
		.append("content[").append(getContent()).append("],")
		.append("time[").append(getRecordTime()).append("],")
		.append("author[").append(getAuthor()).append("],")
		.toString();
	}
}
