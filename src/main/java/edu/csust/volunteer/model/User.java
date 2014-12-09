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

	private int id;
	private String userName;
	private String userNo;
	private String password;
	private String userHead;
	private int userSex;
	private int userGrade;
	private Date userBir;
	private String userEntity;
	private String userTel;
	private String userMail;
	private String userAddress;

	@Column(name = "UGRADE", length = 100, nullable = false)
	public int getUsergrade() {
		return userGrade;
	}

	public void setUsergrade(int usergrade) {
		this.userGrade = usergrade;
	}

	@Column(name = "UIDENTITY", length = 100, nullable = false)
	public String getUserentity() {
		return userEntity;
	}

	public void setUserentity(String userentity) {
		this.userEntity = userentity;
	}

	@Column(name = "UTEL", length = 50, nullable = false)
	public String getUsertel() {
		return userTel;
	}

	public void setUsertel(String usertel) {
		this.userTel = usertel;
	}

	@Column(name = "UMAIL", length = 50, nullable = false)
	public String getUsermail() {
		return userMail;
	}

	public void setUsermail(String usermail) {
		this.userMail = usermail;
	}

	@Column(name = "UADRESS", length = 200, nullable = false)
	public String getUseraddress() {
		return userAddress;
	}

	public void setUseraddress(String useraddress) {
		this.userAddress = useraddress;
	}

	@Column(name = "UHEAD", length = 100, unique = true, nullable = false)
	public String getUserhead() {
		return userHead;
	}

	public void setUserhead(String userhead) {
		this.userHead = userhead;
	}
	
	@Column(name = "USEX", length = 100, unique = true, nullable = false)
	public int getUsersex() {
		return userSex;
	}

	public void setUsersex(int usersex) {
		this.userSex = usersex;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UBIR")
	public Date getUserbir() {
		return userBir;
	}

	public void setUserbir(Date userbir) {
		this.userBir = userbir;
	}

	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "UNAME", length = 100, nullable = false)
	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}
	
	@Column(name = "UNO", length = 100, unique = true, nullable = false)
	public String getUserno() {
		return userNo;
	}

	public void setUserno(String userno) {
		this.userNo = userno;
	}
	
	@Column(name = "UPASSWORD", length = 32, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
