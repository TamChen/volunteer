package edu.csust.volunteer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import edu.csust.volunteer.support.EnableQueryCache;

/**
 * 
 * @author hrz
 * @date 2015-3-6
 */
@Entity
@Table(name = "t_picture")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Picture {
	private int id;
	private String name;
	private Date date;
	private String path;
	private boolean actOrPer;//活动还是个人的1是活动，0是个人
	private int activityId;
	private String userno;
	private String thumb;
	private int album;//如果是个人图片，说明所属专辑
	private boolean cover;//是否是封面
	private String picIntro;
	private boolean edit;
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "UEDIT", length = 4, nullable = false)
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	@Column(name = "UINTRO", length = 500, nullable = false)
	public String getPicIntro() {
		return picIntro;
	}
	public void setPicIntro(String picIntro) {
		this.picIntro = picIntro;
	}
	@Column(name = "UALBUM", length = 10, nullable = false)
	public int  getAlbum() {
		return album;
	}
	public void setAlbum(int album) {
		this.album = album;
	}
	@Column(name = "UTHUMB", length = 200, nullable = false)
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	@Column(name = "USERNO", length = 20, nullable = false)
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	@Column(name = "PDATE", length = 20, nullable = false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Column(name = "PNAME", length = 20, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "PPATH", length = 1000, nullable = false)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name = "PACTORPER", length = 20, nullable = false)
	public boolean isActOrPer() {
		return actOrPer;
	}
	public void setActOrPer(boolean actOrPer) {
		this.actOrPer = actOrPer;
	}
	@Column(name = "PACTIVITYID", length = 20, nullable = false)
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	@Column(name = "PCOVER", length = 20, nullable = false)
	public boolean isCover() {
		return cover;
	}
	public void setCover(boolean cover) {
		this.cover = cover;
	}
	
	
	
	
}
