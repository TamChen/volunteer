package edu.csust.volunteer.vo;

import java.util.Date;


public class NoticeVo {
	private int id;
	private String userno;
	private String username;
	private String userpic;
	private String friendno;
	private String friendname;
	private String friendpic;
	private String noticetitle;
	private String noticeContent;
	private Date sendTime;
	private int msgCount;
	private boolean statu; 
	private Date send_time;
	private boolean type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	public boolean isStatu() {
		return statu;
	}
	public void setStatu(boolean statu) {
		this.statu = statu;
	}
	public String getUserpic() {
		return userpic;
	}
	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}
	public int getMsgCount() {
		return msgCount;
	}
	
	public String getFriendno() {
		return friendno;
	}
	public void setFriendno(String friendno) {
		this.friendno = friendno;
	}
	public String getFriendname() {
		return friendname;
	}
	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}
	public String getFriendpic() {
		return friendpic;
	}
	public void setFriendpic(String friendpic) {
		this.friendpic = friendpic;
	}
	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNoticetitle() {
		return noticetitle;
	}
	public void setNoticetitle(String noticetitle) {
		this.noticetitle = noticetitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
}
