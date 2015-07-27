package edu.csust.volunteer.dao;

import java.util.List;

import edu.csust.volunteer.model.Info;

public interface InfoDao {

	List<Info> getBrodcastInfoList(String hql, Object[] params);

	List<Info> getDetailInfo(String hql, Object[] params);
	

	List<edu.csust.volunteer.model.Info> getNews_F(int newsPage, String hql,
			Object[] params, int number);

	Info getNewsByID(int newsID);

	int getNewsNumber_F(String hql, Object[] params);

	List<Info> getInfoByYear(String hql, Object[] params);

	void addInfo(Info info);

	void updateByInfoId(String hql, Object[] params);

	void deleteInfoById(String hql, Object[] params);

}
