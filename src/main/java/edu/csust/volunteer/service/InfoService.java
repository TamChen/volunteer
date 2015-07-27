package edu.csust.volunteer.service;

import java.util.List;

import edu.csust.volunteer.model.Info;
import edu.csust.volunteer.vo.InfoModel;

public interface InfoService {

	List<InfoModel> getBrodcastInfoList(int i);

	List<InfoModel> getNewsInfoList(int i);

	List<InfoModel> getHotUserDiary();

	List<Info> getDetailInfoById(int infoId);
	
	List<Info> getInfoByYear(int year, int param);
	//===============================================

	Info getNewsByID(int newsID);

	List<InfoModel> getInfo_F(boolean isnew, int newsPage, String adminno, int j);

	int getInfoNumber_F(boolean isnew, String string);

	void addOrUpdateInfo(Info info);

	Info updateInfoTitleAndContent(int infoid, String title, String string);

	void deleteInfo(int infoid);

	

}
