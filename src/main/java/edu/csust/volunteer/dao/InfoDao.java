package edu.csust.volunteer.dao;

import java.util.List;

import edu.csust.volunteer.model.Info;

public interface InfoDao {

	List<Info> getBrodcastInfoList(String hql, Object[] params);

}
