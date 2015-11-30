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
import javax.transaction.Transactional;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import edu.csust.volunteer.support.EnableQueryCache;

@Entity
@Table(name = "t_user")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String userno;
	private String password;
	private String userHead;
	private boolean userSex;
	private String userMajor;;//专业与年级
	private String userCollege;//学院
	private Date userBir;
	private String userIntro;
	private String userTele;
	private String userMail;
	private String sign;
	private String userAddress;
    private boolean status;//是否为管理员，true是，false
    private String resume;//简历
    private int userRate;//义工排名。可以设在某个时间段对所有人进行排名，，，在管理员中这个字段作为发布活动的多少；
    private int infoNum;//消息条数
    private int workTime;//总时长
    private String outstandingPicture;
    private boolean outstanding;
    private double star;

    @Column(name = "validataCode", length = 100, nullable = true)
    private String validataCode;
    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registerDate")
    private Date registerDate;

	@Column(name = "URESUME", length = 500, nullable = true)
    public String getResume() {
		return resume;
	}
	
	public void setResume(String resume) {
		this.resume = resume;
	}
	
	
	public String getValidataCode() {
		return validataCode;
	}

	public void setValidataCode(String validataCode) {
		this.validataCode = validataCode;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "USTAR", length = 10, nullable = true)
	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

	@Column(name = "USIGN", length = 500, nullable = false)
    public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name = "URATE", length = 10, nullable = true)
    public int getUserRate() {
		return userRate;
	}

	public void setUserRate(int userRate) {
		this.userRate = userRate;
	}

	@Column(name = "UINFONUM", length = 10, nullable = true)
   	public int getInfoNum() {
   		return infoNum;
   	}

   	public void setInfoNum(int infoNo) {
   		this.infoNum = infoNo;
   	}
   	
   	@Column(name = "UMajor", length = 100, nullable = false)
	public String getUserMajor() {
		return userMajor;
	}

	public void setUserMajor(String userMajor) {
		this.userMajor = userMajor;
	}

	@Column(name = "UINTRO", length = 5000, nullable =false)
	public String getUserIntro() {
		return userIntro;
	}

	public void setUserIntro(String userIntro) {
		this.userIntro = userIntro;
	}
	
	@Column(name = "UTELE", length = 5000, nullable = false)
	public String getUserTele() {
		return userTele;
	}

	public void setUserTele(String userTele) {
		this.userTele = userTele;
	}





	@Column(name = "UMAIL", length = 50, nullable = true)
	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	@Column(name = "UADRESS", length = 200, nullable = true)
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Column(name = "UHEAD", length = 200, nullable = true)
	public String getUserHead() {
		return userHead;
	}

	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}
	
	@Column(name = "USEX", length = 100, unique = true, nullable = true)
	public boolean getUserSex() {
		return userSex;
	}

	public void setUserSex(boolean userSex) {
		this.userSex = userSex;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UBIR")
	public Date getUserBir() {
		return userBir;
	}

	

	public void setUserBir(Date userBir) {
		this.userBir = userBir;
	}

	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "UNAME", length = 100, nullable = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "UNO", length = 10, unique = true, nullable = false)
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

	@Column(name = "USTATUS", length = 12, nullable = true)
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Column(name = "OUTSTANDING", length = 12, nullable = false)

	public boolean isOutstanding() {
		return outstanding;
	}
	public void setOutstanding(boolean outstanding) {
		this.outstanding = outstanding;
	}
	@Column(name = "OUTSTANDINGPICTURE", length = 50, nullable = false)
	public String getOutstandingPicture() {
		return outstandingPicture;
	}

	public void setOutstandingPicture(String outstandingPicture) {
		this.outstandingPicture = outstandingPicture;
	}


	@Column(name = "UCOLLEGE", length = 100, nullable = false)
	public String getUserCollege() {
		return userCollege;
	}

	public void setUserCollege(String userCollege) {
		this.userCollege = userCollege;
	}
	
	@Column(name = "UWORKTIME", length = 100, nullable = true)
	public int getWorkTime() {
		return workTime;
	}

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}
	
}
