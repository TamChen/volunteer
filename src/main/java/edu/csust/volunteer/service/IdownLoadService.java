package edu.csust.volunteer.service;

import java.util.List;

import edu.csust.remote.entity.Download;

public interface IdownLoadService {

	List<Download> getLastSevenInfo(int typeno,int current, int size);

	boolean deleteInfoById(int id);

	Integer getNumber(int i);

	boolean updateDownloadInfo(int id, String name, int type);

	void savaDownloadInfo(int type, String path, String fileName, String time);

	String getDownLoadInfoById(int no);

}
