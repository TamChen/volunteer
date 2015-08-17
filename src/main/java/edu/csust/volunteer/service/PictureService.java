package edu.csust.volunteer.service;

import java.util.List;

import edu.csust.volunteer.model.Album;
import edu.csust.volunteer.model.Picture;
import edu.csust.volunteer.model.User;
/**
 * 
 * @author hrz
 * @date 2015-3-7
 */
public interface PictureService {
	public List<Picture> listActPicture();
	public List<User> listPerPicture();
	public List<Picture> findActPicture(String text);
	public List<User> findUserPicture(String text);
	//================================================================
	public int getPicListNum(String input, int param);
	public int getActivityPicListNum(int activityid);
	public Picture getPictureById(int activityid);
	public List<Picture> findPictureList(String input, int param, int current, int size);
	public List<Picture> getPicByActivityId(int activityid);
	public List<Picture> getPicByActivityId(int activityid, int current,
			int size);
	//==================================================================
	public List<Album> getUserAlbumByNo(String userno);
	public Album getAlbumById(int albumId);
	public List<Picture> getPicByAlbum(int albumId, int current, int size);
	public List<Picture> getPicByAlbum(int album);
	public int savePic(String userno, String pictureSavePath,
			String pictureCropperPath, int album,int activityid, boolean edit);
	public List<Picture> getUnPostPicByUserno(String userno, int album);
	public boolean deletePictureById(int pic);
	public boolean updatePictureInfo(Picture picture);
	public void saveAlbum(Album album);
	public void updateUserAlbum(Album album);
	public void deleteUserAlbum(int albumId);
	public void deletePicByAlbum(int albumId, String userno);
	public List<Picture> getUnPostPicByActivity(int activityid);
}
