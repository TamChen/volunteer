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

@Entity
@Table(name = "t_Album")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Album {
	private int id;
	private String name;//专辑名
	private Date date;//创建日期
	private String userno;//用户对应多张专辑
	private String thumb;//封面的缩略图
	private String intro;
	private int num;//照片数量
	
	public Album(){}
	public Album(String name, Date date, String userno, String thumb,
			String intro, int num) {
		super();
		this.name = name;
		this.date = date;
		this.userno = userno;
		this.thumb = thumb;
		this.intro = intro;
		this.num = num;
	}
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "UINTRO", length = 100, nullable = true)
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	@Column(name = "UNUM", length = 10, nullable = true)
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Column(name = "UTHUMB", length = 100, nullable = true)
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	@Column(name = "USERNO", length = 20, nullable = true)
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	@Column(name = "PDATE", length = 20, nullable = true)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Column(name = "PNAME", length = 20, nullable = true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
