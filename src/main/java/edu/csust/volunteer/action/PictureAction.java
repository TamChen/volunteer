package edu.csust.volunteer.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import com.alibaba.fastjson.JSONObject;

import edu.csust.volunteer.model.Picture;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.model.Album;
import edu.csust.volunteer.service.PictureService;
/**
 * 
 * @author hrz cyl
 * @date 2015-3-7
 */
@Action("pictureAction")
public class PictureAction extends BaseAction<Picture> {
	private static final long serialVersionUID = 1L;
	private int page;
	private int pageSize;
	private int pageCount;
	private String name;
	private String actOrPer;
	@Autowired
	private PictureService pictureService;

	public void listActPicture(){
		System.out.println("PIcture");
		JSONObject jsonData=new JSONObject();
		List<Picture> Acts = pictureService.listActPicture();
		jsonData.put("Acts",Acts);
		writeJson(jsonData);
	}
	
	public void listPerPicture(){
		System.out.println("listPerPicture");
		JSONObject jsonData=new JSONObject();
		List<User> Pers = pictureService.listPerPicture();
		jsonData.put("Pers",Pers);
		writeJson(jsonData);
	}
	
	public void findPicture(){
		JSONObject jsonData=new JSONObject();
		if(actOrPer.equals("act")){
			List<Picture> pic = pictureService.findActPicture(name);
			System.out.println(pic);
			jsonData.put("pic",pic);
		}
		if(actOrPer.equals("per")){
			List<User> pic = pictureService.findUserPicture(name);
			jsonData.put("pic",pic);
		}
		
		writeJson(jsonData);
	}
	//获得前台的照片墙
	public void getGlimpseList() throws UnsupportedEncodingException {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int  current=Integer.parseInt(request.getParameter("current"));
		int  size=Integer.parseInt(request.getParameter("size"));
		int  param=Integer.parseInt(request.getParameter("param"));
		String input = new String(request.getParameter("input").getBytes("iso8859-1"),"utf-8");
		System.out.println("input"+input);
		List<Picture> pic= pictureService.findPictureList(input,param,current,size);
		jsonData.put("pic",pic);
		writeJson(jsonData);
	}
	public void getPicListNum() throws UnsupportedEncodingException{
		JSONObject jsonData=new JSONObject();
		int  param=Integer.parseInt(request.getParameter("param"));
		int  size=Integer.parseInt(request.getParameter("size"));
		String input = new String(request.getParameter("input").getBytes("iso8859-1"),"utf-8");
		int page = pictureService.getPicListNum(input,param);
		if (page>120) 
		page=120;
		jsonData.put("size", (page/size)+1);
		writeJson(jsonData);
	}
	
	public void getPicNumByActivityId() {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int  activityid=Integer.parseInt(request.getParameter("activityid"));
		int num=pictureService.getActivityPicListNum(activityid);
		jsonData.put("num", num);
		writeJson(jsonData);
	}
	public void getThumbPicByActivityId() {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int  activityid=Integer.parseInt(request.getParameter("activityid"));
		int current=0,size=6;
		List<Picture> pictures=pictureService.getPicByActivityId(activityid,current,size);
		jsonData.put("pic", pictures);
		writeJson(jsonData);
	}
	public void getPicByActivityId() {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int  activityid=Integer.parseInt(request.getParameter("activityid"));
		int  current=Integer.parseInt(request.getParameter("current"));
		int  size=Integer.parseInt(request.getParameter("size"));
		List<Picture> pictures=pictureService.getPicByActivityId(activityid,current,size);
		jsonData.put("pic", pictures);
		writeJson(jsonData);
	}
	public void getPicDetail(){
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int  activityid=Integer.parseInt(request.getParameter("activityid"));
		int  pic=Integer.parseInt(request.getParameter("pic"));
		List<Picture> pictures=pictureService.getPicByActivityId(activityid);
		int num =pictures.size();
		Picture picture=pictureService.getPictureById(pic);
		jsonData.put("num",num);
		System.out.println(activityid+"++"+pic+"++"+num);
		for (int current=0;current<num ; current++) 
		if (pictures.get(current).getId()==pic) {
			jsonData.put("current",current);
			if (current==0) {
				jsonData.put("pre",pictures.get(current).getId());
			}else {
				jsonData.put("pre",pictures.get(current-1).getId());
			}
			if (current==num-1) {
				jsonData.put("next",pictures.get(current).getId());
			}else {
				jsonData.put("next",pictures.get(current+1).getId());
			}
			break;
		}
		jsonData.put("pic",picture);
		writeJson(jsonData);
	}
	public void getUserAlbum() {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		List<Album> albums=pictureService.getUserAlbumByNo(userno);
		jsonData.put("albums", albums);
		if(albums==null)
		jsonData.put("size", 0);
		writeJson(jsonData);
	}
	//根据相册id得到相册的详细信息
	public void getAlbumInfo(){
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int  albumId=Integer.parseInt(request.getParameter("album"));
		Album album=pictureService.getAlbumById(albumId);
		jsonData.put("album", album);
		writeJson(jsonData);
	}
	//根据相册获得该相册的所有照片
	public void getAlbumPicList(){
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int  albumId=Integer.parseInt(request.getParameter("album"));
		int  current=Integer.parseInt(request.getParameter("current"));
		int  size=Integer.parseInt(request.getParameter("size"));
		List<Picture> pictures=pictureService.getPicByAlbum(albumId,current,size);
		jsonData.put("pic", pictures);
		jsonData.put("size", pictures.size());
		writeJson(jsonData);
	}
	//得到用户图片的详细信息
	public void getUserPicDetail(){
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int  album=Integer.parseInt(request.getParameter("album"));
		int  pic=Integer.parseInt(request.getParameter("pic"));
		List<Picture> pictures=pictureService.getPicByAlbum(album);
		int num =pictures.size();
		Picture picture=pictureService.getPictureById(pic);
		jsonData.put("num",num);
		for (int current=0;current<num ; current++) 
		if (pictures.get(current).getId()==pic) {
			jsonData.put("current",current);
			if (current==0) {
				jsonData.put("pre",pictures.get(current).getId());
			}else {
				jsonData.put("pre",pictures.get(current-1).getId());
			}
			if (current==num-1) {
				jsonData.put("next",pictures.get(current).getId());
			}else {
				jsonData.put("next",pictures.get(current+1).getId());
			}
			break;
		}
		jsonData.put("pic",picture);
		writeJson(jsonData);
	}
	public void getUnPostPicture() {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		String userno=(String)request.getSession().getAttribute("userno");
		int  album=Integer.parseInt(request.getParameter("album"));
		List<Picture> pictures=pictureService.getUnPostPicByUserno(userno,album);
		jsonData.put("photo", pictures);
		if (pictures==null) 
		jsonData.put("size", 0);
		else
		jsonData.put("size", pictures.size());
		writeJson(jsonData);
	}
	public void getUnPostActivityPicture() {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int activityid=Integer.parseInt(request.getParameter("activityid"));
		List<Picture> pictures=pictureService.getUnPostPicByActivity(activityid);
		jsonData.put("photo", pictures);
		if (pictures==null) 
		jsonData.put("size", 0);
		else
		jsonData.put("size", pictures.size());
		writeJson(jsonData);
	}
	public void deletPicture() {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int  pic=Integer.parseInt(request.getParameter("pic"));
		boolean deleteResult=pictureService.deletePictureById(pic);
		jsonData.put("deleteResult", deleteResult);
		writeJson(jsonData);
	}
	public void savePictureIntro() throws UnsupportedEncodingException{
		HttpServletRequest request=ServletActionContext.getRequest();
		String intro = new String(request.getParameter("intro").getBytes("iso8859-1"),"utf-8");
		int  param=Integer.parseInt(request.getParameter("param"));
		int  pic=Integer.parseInt(request.getParameter("pic"));
		int  album=Integer.parseInt(request.getParameter("album"));
		Picture picture=pictureService.getPictureById(pic);
		picture.setAlbum(album);
		picture.setCover(judgeIsCover(param,pic));
		picture.setEdit(false);
		picture.setDate(new Date()); picture.setPicIntro(intro);
		boolean issuccess=pictureService.updatePictureInfo(picture);
	}
	private boolean judgeIsCover(int param,int pic) {
		if (param==pic) return true;
		else return false;
	}
	public void saveAlbum() throws UnsupportedEncodingException {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		String userno=(String)request.getSession().getAttribute("userno");
		String name= new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
		String intro= new String(request.getParameter("intro").getBytes("iso8859-1"),"utf-8");
		int  num=Integer.parseInt(request.getParameter("num"));//总共多少图片
		int  pic=Integer.parseInt(request.getParameter("pic"));//封面
		Album album=new Album();
		album.setDate(new Date());album.setIntro(intro);album.setName(name);
		album.setNum(num);
		if(pic!=0)
		album.setThumb(pictureService.getPictureById(pic).getThumb());
		else album.setThumb("static/style/img/photo_album.png");
		album.setUserno(userno);
		pictureService.saveAlbum(album);
		jsonData.put("id", album.getId());
		writeJson(jsonData);
	}
	public void updateAlbum() {
		HttpServletRequest request=ServletActionContext.getRequest();
		int  num=Integer.parseInt(request.getParameter("num"));
		int  pic=Integer.parseInt(request.getParameter("pic"));//封面
		int  albumId=Integer.parseInt(request.getParameter("album"));
		Album album=pictureService.getAlbumById(albumId);
		album.setDate(new Date());//更新日期
		album.setNum(album.getNum()+num);
		if (pic!=0) 
		album.setThumb(pictureService.getPictureById(pic).getThumb());
		pictureService.updateUserAlbum(album);
	}
	public void updateAlbumInfo() throws UnsupportedEncodingException {
		HttpServletRequest request=ServletActionContext.getRequest();
		int  albumId=Integer.parseInt(request.getParameter("album"));
		String name= new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8");
		String intro= new String(request.getParameter("intro").getBytes("iso8859-1"),"utf-8");
		Album album=pictureService.getAlbumById(albumId);
		album.setDate(new Date());//更新日期
		album.setName(name);album.setIntro(intro);
		pictureService.updateUserAlbum(album);
	}
	public void deleteUserAlbum() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String userno=(String)request.getSession().getAttribute("userno");
		int  albumId=Integer.parseInt(request.getParameter("album"));
		pictureService.deleteUserAlbum(albumId);
		pictureService.deletePicByAlbum(albumId,userno);
	}
	//=====================后台方法=============================================
	public void getPicList(){
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int  param=Integer.parseInt(request.getParameter("param"));//0是活动   1是个人
		int  size=Integer.parseInt(request.getParameter("size"));//封面
		int  current=Integer.parseInt(request.getParameter("current"));//封面
		List<Picture> pic= pictureService.findPictureList("",param,current,size);
		jsonData.put("piclist",pic);
		writeJson(jsonData);
		//
	}
	public void getPicListTotalNum(){
		int  param=Integer.parseInt(request.getParameter("param"));
		int page = pictureService.getPicListNum("",param);
		writeJson(page);
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActOrPer() {
		return actOrPer;
	}
	public void setActOrPer(String actOrPer) {
		this.actOrPer = actOrPer;
	}
}
