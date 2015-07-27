package edu.csust.volunteer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import edu.csust.volunteer.support.EnableQueryCache;
@Entity
@Table(name = "t_attention")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Attention {
	private int id;
	private String friend;//被关注的对象
	private String active;//主动关注着
	private String friend_head;
	private String friend_name;
	private String friend_sign;
	private String friend_college;
	private String friend_major;
	
	private String active_head;
	private String active_name;
	private String active_sign;
	private String active_college;
	private String active_major;
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "AFRIEND", length = 100, nullable = false)
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	@Column(name = "AACTIVE", length = 100, nullable = false)
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	@Column(name = "AFHEAD", length = 100, nullable = false)
	public String getFriend_head() {
		return friend_head;
	}
	public void setFriend_head(String friend_head) {
		this.friend_head = friend_head;
	}
	@Column(name = "AFNAME", length = 100, nullable = false)
	public String getFriend_name() {
		return friend_name;
	}
	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}
	@Column(name = "ASIGN", length = 100, nullable = false)
	public String getFriend_sign() {
		return friend_sign;
	}
	public void setFriend_sign(String friend_sign) {
		this.friend_sign = friend_sign;
	}
	@Column(name = "ACOLLEGE", length = 100, nullable = false)
	public String getFriend_college() {
		return friend_college;
	}
	public void setFriend_college(String friend_college) {
		this.friend_college = friend_college;
	}
	@Column(name = "VMAJOR", length = 100, nullable = false)
	public String getFriend_major() {
		return friend_major;
	}
	public void setFriend_major(String friend_major) {
		this.friend_major = friend_major;
	}
	@Column(name = "A_HEAD", length = 100, nullable = false)
	public String getActive_head() {
		return active_head;
	}
	public void setActive_head(String active_head) {
		this.active_head = active_head;
	}
	@Column(name = "A_NAME", length = 100, nullable = false)
	public String getActive_name() {
		return active_name;
	}
	public void setActive_name(String active_name) {
		this.active_name = active_name;
	}
	@Column(name = "A_SIGN", length = 100, nullable = false)
	public String getActive_sign() {
		return active_sign;
	}
	public void setActive_sign(String active_sign) {
		this.active_sign = active_sign;
	}
	@Column(name = "A_COLLEGE", length = 100, nullable = false)
	public String getActive_college() {
		return active_college;
	}
	public void setActive_college(String active_college) {
		this.active_college = active_college;
	}
	@Column(name = "A_MAJOR", length = 100, nullable = false)
	public String getActive_major() {
		return active_major;
	}
	public void setActive_major(String active_major) {
		this.active_major = active_major;
	}
	
}
