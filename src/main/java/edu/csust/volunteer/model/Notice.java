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

import org.hibernate.annotations.CacheConcurrencyStrategy;

import edu.csust.volunteer.support.EnableQueryCache;

/*私信功能，参考于osc*/
@Entity
@Table(name = "t_notice")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//私信表和关注表可以是同一个，没有关注不能私信
public class Notice {
	private int id; // 私信主键字段，自增长;
	private String user; //私信的主人
	private String friend;// : 对方的ID
	private String sender;// : 私信发送者
	private String receiver;// : 私信接收者
	private boolean type;// : 私信类型（普通消息、系统消息）
	private String title;//私信内容标题 有时只有系统通知有
	private String content;// : 私信内容
	private Date send_time;// : 发送时间
	private Date read_time;// : 阅读时间
	private boolean status;// : 私信状态false已读和true未读；
	public Notice(){
		
	}
	public Notice(String user, String friend, String sender, String receiver,
			boolean type, String title, String content, Date send_time,
			boolean status) {
		super();
		this.user = user;
		this.friend = friend;
		this.sender = sender;
		this.receiver = receiver;
		this.type = type;
		this.title = title;
		this.content = content;
		this.send_time = send_time;
		this.status = status;
	}

	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "NUSER", length = 12, nullable = false)
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	@Column(name = "NFRIEND", length = 12,nullable = false)
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	
	@Column(name = "NSENDER", length = 12,  nullable = false)
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	@Column(name = "NRECEIVER", length = 12,  nullable = false)
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	@Column(name = "NTYPE", length = 4, nullable = false)
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	@Column(name = "NTITLE", length = 100, nullable = true)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "NCONTENT", length = 500, nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VSEND_TIME")
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VREAD_TIME")
	public Date getRead_time() {
		return read_time;
	}
	public void setRead_time(Date read_time) {
		this.read_time = read_time;
	}
	
	@Column(name = "NSTATUS", length = 4, nullable = false)
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
