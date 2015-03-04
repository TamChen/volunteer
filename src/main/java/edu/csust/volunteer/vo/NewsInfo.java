package edu.csust.volunteer.vo;

import java.sql.Timestamp;

public class NewsInfo {
	private int uuid;
	private String title;
	private String content;
	private Timestamp recordTime;
	private String author;
	
	public NewsInfo(){
	}
	
	public NewsInfo(int uuid,String title,String content,Timestamp recordTime,String author) {
		this.uuid = uuid;
		this.title = title;
		this.content = content;
		this.recordTime = recordTime;
		this.author = author;
	}
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}

