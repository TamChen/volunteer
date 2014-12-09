package edu.csust.volunteer.model;

import java.io.Serializable;
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
@Table(name = "t_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String userno;
	private String password;
	private String userhead;
	private int usersex;
	private int usergrade;
	private Date userbir;
	private String userentity;
	private String usertel;
	private String usermail;
	private String useraddress;

	@Column(name = "UGRADE", length = 100, nullable = false)
	public int getUsergrade() {
		return usergrade;
	}

	public void setUsergrade(int usergrade) {
		this.usergrade = usergrade;
	}

	@Column(name = "UIDENTITY", length = 100, nullable = false)
	public String getUserentity() {
		return userentity;
	}

	public void setUserentity(String userentity) {
		this.userentity = userentity;
	}

	@Column(name = "UTEL", length = 50, nullable = false)
	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	@Column(name = "UMAIL", length = 50, nullable = false)
	public String getUsermail() {
		return usermail;
	}

	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}

	@Column(name = "UADRESS", length = 200, nullable = false)
	public String getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	@Column(name = "UHEAD", length = 100, unique = true, nullable = false)
	public String getUserhead() {
		return userhead;
	}

	public void setUserhead(String userhead) {
		this.userhead = userhead;
	}
	
	@Column(name = "USEX", length = 100, unique = true, nullable = false)
	public int getUsersex() {
		return usersex;
	}

	public void setUsersex(int usersex) {
		this.usersex = usersex;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UBIR")
	public Date getUserbir() {
		return userbir;
	}

	public void setUserbir(Date userbir) {
		this.userbir = userbir;
	}

	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "UNAME", length = 100, unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "UNO", length = 100, unique = true, nullable = false)
	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}
	
	@Column(name = "UPASSWORD", length = 32, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
