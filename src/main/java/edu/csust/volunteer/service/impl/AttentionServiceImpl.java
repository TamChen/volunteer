package edu.csust.volunteer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.csust.volunteer.dao.AttentionDao;
import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.Attention;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.service.AttentionService;
import edu.csust.volunteer.service.UserService;
import edu.csust.volunteer.vo.PictureVO;
import freemarker.core._RegexBuiltins.replace_reBI;
@Service("attentionService")
public class AttentionServiceImpl implements AttentionService {
	@Autowired 
	private AttentionDao attentionDao;
	@Autowired
	private UserService userService;
	@Override
	public List<Attention> getRelationUsers(String userno, int param,
			int current, int pagesize) {
		String hql="";
		if (param==1) {//说明是获取我关注的人的所有信息
			hql="from Attention where active='"+userno+"' order by id desc";
		}else if (param==2) {
			hql="from Attention where friend='"+userno+"' order by id desc";
		}
		List<Attention> attentions  = attentionDao.getAttentionList(hql, current,pagesize);
		return attentions;
	}
	@Override
	public int getAttentionMeNum(String userno) {
		String hql = "select count(*) from Attention where friend=?";
		Object params[]={userno};
		return attentionDao.getAttentionMeNum(hql,params);
	}
	//获得我关注的朋友
	@Override
	public List<PictureVO> getMyFriend(String userno, int maxSize) {
		String hql="select t.id,t.friend,t.friend_head,t.id from Attention t where t.active='"+userno+"'";
		int current=0;
		List<Object[]> objects=attentionDao.getMyFriend(hql,current,maxSize);
		List<PictureVO> pictureList=new ArrayList<PictureVO>();
		for (Object[] objects2 : objects) {
			PictureVO VO=new PictureVO();
			VO.setId((Integer)objects2[0]);
			VO.setUrl((String)objects2[1]);
			VO.setPicPath((String)objects2[2]);
			VO.setStatu((Integer)objects2[3]);
			pictureList.add(VO);
		}
		return pictureList;
	}
	@Override
	public List<PictureVO> getMyAllFriend(String userno) {
		String hql="select t.id,t.friend,t.friend_head,t.id,t.friend_name from Attention t where t.active='"+userno+"'";
		List<Object[]> objects=attentionDao.getMyAllFriend(hql);
		List<PictureVO> pictureList=new ArrayList<PictureVO>();
		for (Object[] objects2 : objects) {
			PictureVO VO=new PictureVO();
			VO.setId((Integer)objects2[0]);
			VO.setUrl((String)objects2[1]);
			VO.setPicPath((String)objects2[2]);
			VO.setStatu((Integer)objects2[3]);
			VO.setName((String)objects2[4]);
			pictureList.add(VO);
		}
		return pictureList;
	}
	@Override
	public List<PictureVO> getMyAllAttentionMe(String userno) {
		String hql="select t.id,t.active,t.active_head,t.id,t.active_name from Attention t where t.friend='"+userno+"'";
		List<Object[]> objects=attentionDao.getMyAllAttentionMe(hql);
		List<PictureVO> pictureList=new ArrayList<PictureVO>();
		for (Object[] objects2 : objects) {
			PictureVO VO=new PictureVO();
			VO.setId((Integer)objects2[0]);
			VO.setUrl((String)objects2[1]);
			VO.setPicPath((String)objects2[2]);
			VO.setStatu((Integer)objects2[3]);
			VO.setName((String)objects2[4]);
			pictureList.add(VO);
		}
		return pictureList;
		}
	@Override
	public boolean attentionUser(String userno, String currentUser) {
		try {
			User current=userService.findUserById(currentUser);
			User user=userService.findUserById(userno);
			Attention attention=new Attention();
			attention.setActive(currentUser);
			attention.setActive_college(current.getUserCollege());
			attention.setActive_head(current.getUserHead());
			attention.setActive_major(current.getUserMajor());
			attention.setActive_name(current.getUsername());
			attention.setActive_sign(current.getSign());
			attention.setFriend(userno);
			attention.setFriend_college(user.getUserCollege());
			attention.setFriend_head(user.getUserHead());
			attention.setFriend_major(user.getUserMajor());
			attention.setFriend_name(user.getUsername());
			attention.setFriend_sign(user.getSign());
			attentionDao.saveAttentionRelationship(attention);
			} catch (Exception e) {
				return false;
		}
		return true;
	}
	@Override
	public boolean isAttention(String userno, String currentUser) {
		String hql = "select count(*) from Attention where friend=? and active=?";
		Object params[]={userno,currentUser};
		int num= attentionDao.getAttentionMeNum(hql,params);
		System.out.println("num"+num);
		if (num==0) {
			return false;
		}else {
			return true;
		}
	}
	@Override
	public boolean cancelAttention(String userno, String currentUser) {
		String hql="delete from Attention t where t.friend=? and t.active=?";
		Object params[]={userno,currentUser};
		try {
			attentionDao.deleteAttentionRecord(hql,params);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
