package edu.csust.volunteer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_admin")
public class Admin {
	private int id;
	private String adminName;
	private String adminPassword;
	private String adminHead;
	private String adminTel;
	private String adminIntro;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "ANAME", length = 20, nullable = false)
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	@Column(name = "APASSWORD", length = 20, nullable = false)
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	@Column(name = "AHEAD", length = 20, nullable = false)
	public String getAdminHead() {
		return adminHead;
	}
	public void setAdminHead(String adminHead) {
		this.adminHead = adminHead;
	}
	
	@Column(name = "ATEL", length = 20, nullable = false)
	public String getAdminTel() {
		return adminTel;
	}
	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}
	
	@Column(name = "AINTRO", length = 20, nullable = false)
	public String getAdminIntro() {
		return adminIntro;
	}
	public void setAdminIntro(String adminIntro) {
		this.adminIntro = adminIntro;
	}
}
