package edu.csust.volunteer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import edu.csust.volunteer.dao.InfoDao;
import edu.csust.volunteer.model.Info;
import edu.csust.volunteer.service.InfoService;
import edu.csust.volunteer.vo.BrodcastInfo;
import edu.csust.volunteer.vo.InfoModel;
import edu.csust.volunteer.vo.NewsInfo;
@Service("InfoService")
public class InfoServiceImpl implements InfoService {

	@Autowired
	private InfoDao infoDao;
	@Override
	public List<InfoModel> getBrodcastInfoList(int i) {
		//只要最新的四条信息的ID，title和时间,后面可能有优化的语句
		String hql="from Info";
		Object params[]={};
		List<Info> brodcastInfo=infoDao.getBrodcastInfoList(hql,params);
		return transFormInfoToInfoModel(brodcastInfo);
	}

	@Override
	public List<InfoModel> getNewsInfoList(int i) {
		//只要最新的四条信息的ID，title和时间,后面可能有优化的语句
		String hql="from Info";
		Object params[]={};
		List<Info> brodcastInfo=infoDao.getBrodcastInfoList(hql,params);
		return transFormInfoToInfoModel(brodcastInfo);
	}
	
	public List<InfoModel> transFormInfoToInfoModel(List<Info> brodcastInfo) {
		List<InfoModel> infoModeList=Lists.newArrayList(Collections2.transform(brodcastInfo, new Function<Info, InfoModel>() {
            @Override
            public InfoModel apply(Info input) {
            	InfoModel infoModel=new InfoModel();
            	infoModel.setUuid(input.getId());
            	infoModel.setTitle(input.getTitle());
            	infoModel.setRecordTime(input.getRecordTime());
                return infoModel;
            }
        }));
		return infoModeList;
	}

	@Override
	public List<InfoModel> getHotUserDiary() {
		String hql="from Info";
		Object params[]={};
		List<Info> brodcastInfo=infoDao.getBrodcastInfoList(hql,params);
		return transFormInfoToInfoModel(brodcastInfo);
	}
}
