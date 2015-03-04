package edu.csust.volunteer.service;

import java.util.List;

import edu.csust.volunteer.vo.InfoModel;

public interface InfoService {

	List<InfoModel> getBrodcastInfoList(int i);

	List<InfoModel> getNewsInfoList(int i);

	List<InfoModel> getHotUserDiary();

}
