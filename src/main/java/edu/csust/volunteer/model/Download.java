package edu.csust.volunteer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import edu.csust.remote.support.EnableQueryCache;

@Entity
@Table(name = "t_download")
@EnableQueryCache
public class Download implements Serializable{
	@Id  //id自增
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id")
	private int id;
	
	@Column(name = "name", length = 200, nullable = true)
	private String name;
	
	@Column(name = "type", length = 4, nullable = true)
	private int type;
	
	@Column(name = "typename", length = 30, nullable = true)
	private String typename;
	
	@Column(name = "path", length = 200, nullable = true)
	private String path;
	
	@Column(name = "time", length = 30, nullable = true)
	private String time;
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "DownLoadInfo [id=" + id + ", name=" + name+ ", 路径="
				+path+"]";
	}
	
}
