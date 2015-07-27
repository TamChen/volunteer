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
@Table(name = "t_userdiary")
public class UserDiary {
	private int diaryId;
	private String authorName;
	private String authorNo;
	private Date recordTime;
	private String diaryContent;
	private String diaryTitle;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}
	
	@Column(name = "AUTHORNAME", length = 20, nullable = false)
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Column(name = "AUTHORNO", length = 20, nullable = false)
	public String getAuthorNo() {
		return authorNo;
	}
	public void setAuthorNo(String authorNo) {
		this.authorNo = authorNo;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RECORDTIME")
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	
	@Column(name = "DIARYCONTENT", length = 200, nullable = false)
	public String getDiaryContent() {
		return diaryContent;
	}
	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}
	
	@Column(name = "DIARYTITLE", length = 20, nullable = false)
	public String getDiaryTitle() {
		return diaryTitle;
	}
	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}
}
