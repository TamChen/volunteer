package edu.csust.volunteer.dao;

import java.util.List;

import edu.csust.volunteer.model.Picture;
import edu.csust.volunteer.model.User;
/**
 * 
 * @author hrz
 * @date 2015-3-7
 */
public interface PictureDao {
	public List<Picture> listActPicture(String hql);
	public List<Picture> listPerPicture(String hql);
	public List<Picture> findActPicture(String hql);
	public List<Picture> findPictureList(String hql, int current,
			int pageSize);
	public int getPicNumInfo(String hql);
	public Picture getPicture(String hql);
	public List<Picture> findPictureList(String hql);
	public void savePic(Picture picture);
	public boolean excuteHql(String hql);
	public void updatePicture(Picture picture);
}
