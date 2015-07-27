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
import edu.csust.volunteer.vo.InfoModel;
@Service("InfoService")
public class InfoServiceImpl implements InfoService {

	@Autowired
	private InfoDao infoDao;
	@Override
	public List<InfoModel> getBrodcastInfoList(int i) {
		//isnew 0代表新闻1代表公告
		String hql="from Info where isnew=1";
		Object params[]={};
		List<Info> brodcastInfo=infoDao.getBrodcastInfoList(hql,params);
		if (brodcastInfo==null) {
			return null;
		}
		return transFormInfoToInfoModel(brodcastInfo);
	}

	@Override
	public List<InfoModel> getNewsInfoList(int i) {
		//isnew 0代表新闻1代表公告
		String hql="from Info where isnew=1";
		Object params[]={};
		List<Info> brodcastInfo=infoDao.getBrodcastInfoList(hql,params);
		if (brodcastInfo==null) {
			return null;
		}
		return transFormInfoToInfoModel(brodcastInfo);
	}
	//============================================================
	//这里可以使用分组来进行分类
	@Override
	public List<Info> getInfoByYear(int year,int param) {
		String hql="from Info where YEAR(recordTime)=? and isnew=?";
		Object params[]={year,param==1?true:false};
		List<Info> yearInfo=infoDao.getInfoByYear(hql,params);
		return yearInfo;
	}

	@Override
	public List<InfoModel> getHotUserDiary() {
		String hql="from Info";
		Object params[]={};
		List<Info> brodcastInfo=infoDao.getBrodcastInfoList(hql,params);
		return transFormInfoToInfoModel(brodcastInfo);
	}

	@Override
	public List<Info> getDetailInfoById(int infoId) {
		String hql="from Info where id=?";
		Object params[]={infoId};
		List<Info> detailInfo=infoDao.getDetailInfo(hql,params);
		return detailInfo;
	}

	@Override
	public List<InfoModel> getInfo_F(boolean isnew,int pageNumber,String userno, int maxNumber) {
		//isnew为真则为新闻，false则为公告
		//应该有个超级管理员的角色，可以查看所有的新闻和公告
		//基本的设定为相应的管理员查看相应的新闻与公告
		String hql="from Info where authorNumber = " +userno+" and IISNEW ="+isnew+" order by id desc";
		Object params[]={};
		List<Info> infoList=infoDao.getNews_F(pageNumber,hql,params,maxNumber);
		if(infoList==null){
			return null;
		}
		return transFormInfoToInfoModel(infoList);
	}
	
	@Override
	public int getInfoNumber_F(boolean isnew,String userno) {
		String hql = "select count(i) from Info i where authorNumber = " +userno+" and IISNEW ="+isnew;
		Object params[]={};
		return infoDao.getNewsNumber_F(hql,params);
	}

	@Override
	public Info getNewsByID(int newsID) {
		return infoDao.getNewsByID(newsID);
	}

	private List<InfoModel> transFormInfoToInfoModel(List<Info> info) {
		List<InfoModel> InfoModelList=Lists.newArrayList(Collections2.transform(info, new Function<Info, InfoModel>() {
            @Override
            public InfoModel apply(Info input) {
            	InfoModel infoModel=new InfoModel();
            	infoModel.setUuid(input.getId());
            	infoModel.setTitle(input.getTitle());
            	infoModel.setRecordTime(input.getRecordTime());
            	infoModel.setAuthor(input.getAuthor());
                return infoModel;
            }
        }));
		return InfoModelList;
	}

	@Override
	public void addOrUpdateInfo(Info info) {
			infoDao.addInfo(info);
	}

	@Override
	public Info updateInfoTitleAndContent(int infoid,String title, String content) {
		String hql="update Info t set t.title ='"+title+"' , t.content='"+content+"',t.recordTime=CURRENT_DATE() where id=?";
		Object params[]={infoid};
		infoDao.updateByInfoId(hql,params);
		return null;
	}

	@Override
	public void deleteInfo(int infoid) {
		String hql="delete Info i where i.id=?";
		Object params[]={infoid};
		infoDao.deleteInfoById(hql,params);
	}
}
