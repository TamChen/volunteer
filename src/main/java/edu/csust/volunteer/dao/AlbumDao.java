package edu.csust.volunteer.dao;

import java.util.List;

import edu.csust.volunteer.model.Album;
import edu.csust.volunteer.model.Picture;
import edu.csust.volunteer.model.User;

public interface AlbumDao {
	//============================================================
	public List<Album> getAlbumList(String hql);

	public Album getAlbum(String hql);

	public void saveAlbum(Album album);

	public void updateAlbum(Album album);

	public Object excuteHql(String hql);
}
