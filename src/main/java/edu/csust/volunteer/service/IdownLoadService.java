package edu.csust.volunteer.service;

import java.util.List;

import edu.csust.volunteer.model.Download;

public interface IdownLoadService {

	boolean deleteInfoById(int id);

	Integer getNumber();

	boolean updateDownloadInfo(int id, String name);

	void savaDownloadInfo(String path, String fileName, String time);

	Download getDownLoadInfoById(int no);

	List<Download> getLastSevenInfo(int current, int size);

}
