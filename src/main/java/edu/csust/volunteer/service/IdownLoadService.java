package edu.csust.volunteer.service;

import java.util.List;

import edu.csust.volunteer.model.Download;

public interface IdownLoadService {

	boolean deleteInfoById(int id);

	Integer getNumber();

	boolean updateDownloadInfo(int id, String name, int type);

	void savaDownloadInfo(int type, String path, String fileName, String time);

	String getDownLoadInfoById(int no);

	List<Download> getLastSevenInfo(int current, int size);

}
