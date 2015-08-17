package edu.csust.volunteer.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.csust.volunteer.dao.AlbumDao;
import edu.csust.volunteer.dao.PictureDao;
import edu.csust.volunteer.dao.UserDao;
import edu.csust.volunteer.model.Album;
import edu.csust.volunteer.model.Picture;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.service.PictureService;
/**
 * 
 * @author hrz
 * @date 2015-3-7
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService {
	@Autowired
	private PictureDao pictureDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AlbumDao albumDao;
	@Override
	public List<Picture> listActPicture() {
		// TODO Auto-generated method stub
		String hql="from Picture where actOrPer=1 and cover=true and edit=false";
		return pictureDao.listActPicture(hql);
	}

	@Override
	public List<User> listPerPicture() {
		// TODO Auto-generated method stub
		String hql1="from User where outstanding = 1";
		List<User> users = userDao.listUserOutstanding(hql1);
		return users;
	}

	@Override
	public List<Picture> findActPicture(String text) {
		// TODO Auto-generated method stub
		System.out.println("Service");
		String hql="from Picture where name like '%"+text+"%' and cover=true and edit=false";
		return pictureDao.findActPicture(hql);
	}

	@Override
	public List<User> findUserPicture(String text) {
		// TODO Auto-generated method stub
		String hql = "from User where username like'%"+text+"%' and outstanding = true";
		return userDao.findUserPicture(hql);
	}

	@Override
	public List<Picture> findPictureList(String input,int param,int current, int pageSize) {
		String hql="";
		if (param==0) 
		hql="from Picture where picIntro like '%"+input+"%' and actOrPer=true and edit=false order by id desc";
		else 
		hql="from Picture where picIntro like '%"+input+"%' and  actOrPer=false and edit=false order by id desc";
		return pictureDao.findPictureList(hql,current,pageSize);
	}

	@Override
	public int getPicListNum(String input,int param) {
		String hql="";
		if (param==0)
		hql="select count(*) from Picture where picIntro like '%"+input+"%' and  actOrPer = true";
		else 
		hql ="select count(*) from Picture where picIntro like '%"+input+"%' and actOrPer = false";
		int num=pictureDao.getPicNumInfo(hql);
		return num;
	}

	@Override
	public int getActivityPicListNum(int activityid) {
		String hql="select count(*) from Picture where actOrPer = true and activityId="+activityid;
		int num=pictureDao.getPicNumInfo(hql);
		return num;
	}

	@Override
	public List<Picture> getPicByActivityId(int activityid, int current,
			int size) {
		String hql="from Picture where actOrPer=true and activityId="+activityid+" and edit=false order by id asc";
		return pictureDao.findPictureList(hql,current,size);
	}
	public Picture getPictureById(int id){
		String hql="from Picture where id="+id;
		return pictureDao.getPicture(hql);
	}

	@Override
	public List<Picture> getPicByActivityId(int activityid) {
		String hql="from Picture where actOrPer=true and activityId="+activityid+" and edit=false order by id asc";
		return pictureDao.findPictureList(hql);
	}
	@Override
	public int savePic(String userno, String pictureSavePath,
			String pictureCropperPath,int album,int activityid,boolean edit) {
		Picture picture=new Picture();
		picture.setActOrPer(album==0?true:false);
		picture.setActivityId(activityid);
		picture.setAlbum(album);
		picture.setCover(false);
		picture.setName(" ");
		picture.setPicIntro(" ");
		picture.setDate(new Date());
		picture.setPath(pictureSavePath);
		picture.setUserno(userno);
		picture.setEdit(edit);
		picture.setThumb(pictureCropperPath);
		pictureDao.savePic(picture);
		return picture.getId();
	}
//=============================================album===================================
	@Override
	public List<Album> getUserAlbumByNo(String userno) {
		String hql="from Album where userno='"+userno+"' order by date desc";
		return albumDao.getAlbumList(hql);
	}

	@Override
	public Album getAlbumById(int albumId) {
		String hql="from Album where id='"+albumId+"'";
		return albumDao.getAlbum(hql);
	}

	@Override
	public List<Picture> getPicByAlbum(int albumId, int current, int size) {
		String hql="from Picture where album ="+albumId+" and edit=false order by date desc";
		return pictureDao.findPictureList(hql,current,size);
	}

	@Override
	public List<Picture> getPicByAlbum(int album) {
		String hql="from Picture where album ="+album+" and edit=false order by date desc";
		return pictureDao.findPictureList(hql);
	}

	@Override
	public List<Picture> getUnPostPicByUserno(String userno,int album) {
		String hql="from Picture where userno ="+userno+" and album="+album+" and edit=true order by date asc";
		return pictureDao.findPictureList(hql);
	}

	@Override
	public boolean deletePictureById(int pic) {
		String hql="delete from Picture where id ="+pic;
		return pictureDao.excuteHql(hql);
	}

	@Override
	public boolean updatePictureInfo(Picture picture) {
		pictureDao.updatePicture(picture);
		return true;
	}

	@Override
	public void saveAlbum(Album album) {
		albumDao.saveAlbum(album);
	}

	@Override
	public void updateUserAlbum(Album album) {
		albumDao.updateAlbum(album);
		
	}

	@Override
	public void deleteUserAlbum(int albumId) {
		String hql="delete from Album where id ="+albumId;
		albumDao.excuteHql(hql);
	}

	@Override
	public void deletePicByAlbum(int albumId,String userno) {
		String hql="delete from Picture where album ="+albumId+" and userno="+userno;
		 pictureDao.excuteHql(hql);
	}

	@Override
	public List<Picture> getUnPostPicByActivity(int activityid) {
		String hql="from Picture where activityId ="+activityid+" and edit=true order by date asc";
		return pictureDao.findPictureList(hql);
	}
	
}
