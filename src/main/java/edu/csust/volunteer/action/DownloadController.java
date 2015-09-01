package edu.csust.volunteer.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import edu.csust.remote.base.BaseControllers;
import edu.csust.remote.entity.Download;
import edu.csust.remote.service.IdownLoadService;
import edu.csust.remote.util.FileOperateUtil;

@RestController
@RequestMapping("/static")
public class DownloadController extends BaseControllers{
	
	@Autowired
	private IdownLoadService downloadService;
	
	/*  获取前台info List，总共七条*/
    @RequestMapping(value = "/download/downloadinfo", method = RequestMethod.GET)
    public void getLastInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	JSONObject jsonData=new JSONObject();
    	List<Download> infoList=downloadService.getLastSevenInfo(0,0,7);
		jsonData.put("infoList", infoList);
		writerJson(response, jsonData);
    }

    /*获取总共有多少条，三种下载内容*/
    @RequestMapping(value = "/download/getnumberone", method = RequestMethod.GET)
    public void getNumber1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	JSONObject jsonData=new JSONObject();
		jsonData.put("number",downloadService.getNumber(4));
		writerJson(response, jsonData);
    }
    @RequestMapping(value = "/download/getnumbertwo", method = RequestMethod.GET)
    public void getNumber2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	JSONObject jsonData=new JSONObject();
    	jsonData.put("number",downloadService.getNumber(5));
		writerJson(response, jsonData);
		writerJson(response, jsonData);
    }
    @RequestMapping(value = "/download/getnumberthree", method = RequestMethod.GET)
    public void getNumber3(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	JSONObject jsonData=new JSONObject();
    	jsonData.put("number",downloadService.getNumber(6));
		writerJson(response, jsonData);
    }
//    获取前台info List，类型一，每次多加载7条
    @RequestMapping(value = "/download/getone", method = RequestMethod.GET)
    public void getTypeOneInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	JSONObject jsonData=new JSONObject();
    	String currentString = request.getParameter("current");
    	int typeno=4,current=Integer.parseInt(currentString);
    	String sizeString = request.getParameter("size");
    	int size=Integer.parseInt(sizeString);
    	List<Download> infoList=downloadService.getLastSevenInfo(typeno,current,size);
		jsonData.put("infoList", infoList);
		writerJson(response, jsonData);
    }
    
   /* 获取前台info List，类型二*/
    @RequestMapping(value = "/download/gettwo", method = RequestMethod.GET)
    public void getTypeTwoInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	String currentString = request.getParameter("current");
    	int typeno=5,current=Integer.parseInt(currentString);
    	String sizeString = request.getParameter("size");
    	int size=Integer.parseInt(sizeString);
    	JSONObject jsonData=new JSONObject();
    	List<Download> infoList=downloadService.getLastSevenInfo(typeno,current,size);
		jsonData.put("infoList", infoList);
		writerJson(response, jsonData);
    }
    
    /*获取前台info List，类型三*/
    @RequestMapping(value = "/download/getthree", method = RequestMethod.GET)
    public void getTypeThreeInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	String currentString = request.getParameter("current");
    	int typeno=6,current=Integer.parseInt(currentString);
    	String sizeString = request.getParameter("size");
    	int size=Integer.parseInt(sizeString);
    	JSONObject jsonData=new JSONObject();
    	List<Download> infoList=downloadService.getLastSevenInfo(typeno,current,size);
    	System.out.println("hello"+infoList.size());
		jsonData.put("infoList", infoList);
		writerJson(response, jsonData);
    }
    //========================================================================================================
    
    /*删除文件*/
    @RequestMapping(value = "/download/delete", method = RequestMethod.GET)
    public void delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	String idString = request.getParameter("id");
    	int id=Integer.parseInt(idString);
    	JSONObject jsonData=new JSONObject();
    	String downloadfFileName=downloadService.getDownLoadInfoById(id);
        boolean success=downloadService.deleteInfoById(id);//删除数据库中的数据
        try {
        	FileOperateUtil.deleteFile(downloadfFileName);//删除文件
        	jsonData.put("success",success);
		} catch (Exception e) {
			System.out.println("文件未找到");
			jsonData.put("success",false);
		}
		writerJson(response, jsonData);
    }
    /* 更新文件信息*/
    @RequestMapping(value = "/download/update", method = RequestMethod.GET)
    public void update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	String idString = request.getParameter("id");
    	int id=Integer.parseInt(idString);
    	int type=Integer.parseInt(request.getParameter("type"));
    	JSONObject jsonData=new JSONObject();
    	String downloadfFileName = request.getParameter("name");
    	String name = new String(downloadfFileName.getBytes("iso-8859-1"),"utf-8");
        boolean success=downloadService.updateDownloadInfo(id,name,type);//删除数据库中的数据
        FileOperateUtil.deleteFile(downloadfFileName);//删除文件
        jsonData.put("success",success);
		writerJson(response, jsonData);
    }
    
    /*通过设置响应的头消息来实现文件的下载功能。*/
	@RequestMapping(value="/download")
	public void download(HttpServletRequest request, HttpServletResponse response){
	    init(request);
	    try {
	         int no = Integer.parseInt(request.getParameter("fileno"));
	         String downloadfFileName=downloadService.getDownLoadInfoById(no);
	         String fileName = downloadfFileName.substring(downloadfFileName.indexOf("_")+1);
	         String userAgent = request.getHeader("User-Agent");
	         byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8"); 
	         fileName = new String(bytes, "ISO-8859-1");
	         response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
	         FileOperateUtil.download(downloadfFileName, response.getOutputStream());
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	 }
	 /*上传文件。*/
	@RequestMapping(value="upload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request){
        init(request);
        try {
        	/*上传文件有文件类型*/  /*type*/
        	/*	将HttpServletRequest对象强制转换为MultipartRequest类型，然后通过MultipartRequest.getFileMap()得到Map<String, MultipartFile>，然后分别保存每个提交的文件*/
//        	 File[] files = new File(FileOperateUtil.FILEDIR).listFiles();
        	MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = mRequest.getFileMap();       
            File file = new File(FileOperateUtil.FILEDIR);
            if (!file.exists()) {
                file.mkdir();
            }
            Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, MultipartFile> entry = it.next();
                MultipartFile mFile = entry.getValue();
                if(mFile.getSize() != 0 && !"".equals(mFile.getName())){
                	String orginalFileName=mFile.getOriginalFilename();
                	String tmpName = orginalFileName.substring(orginalFileName.lastIndexOf(".") + 1,orginalFileName.length()); //获得扩展名
                	String fileName=request.getParameter("title");
                	fileName = new String(fileName.getBytes("iso-8859-1"),"utf-8");//当有乱码时fastjson会出现错误
                	String path[]=initFilePath(fileName+"."+tmpName);
                	DateTime now = new DateTime();// 取得当前时间  
                	String time = now.toString("yyyy/MM/dd");
                	int type=Integer.parseInt(request.getParameter("type"));
                	downloadService.savaDownloadInfo(type,path[1],fileName,time);
                    FileOperateUtil.write(mFile.getInputStream(), new FileOutputStream(path[0]));
                    
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	 private void init(HttpServletRequest request) {
	     if(FileOperateUtil.FILEDIR == null){
	    	String path=request.getSession().getServletContext().getRealPath("/");
	        FileOperateUtil.FILEDIR = path.substring(0, path.length()-7) + "file/";
	     }
	 }
	 private static String[] initFilePath(String name) {
	        String dir = getFileDir(name) + "";
	        File file = new File(FileOperateUtil.FILEDIR + dir);
	        if (!file.exists()) {
	            file.mkdir();
	        }
	        Long num = new Date().getTime();
	        Double d = Math.random()*num;
	        String path[]={(file.getPath() + "/" + num + d.longValue() + "_" + name).replaceAll(" ", "-"),
	        		(dir+"/"+num + d.longValue() + "_" + name).replaceAll(" ", "-")};
	        return path;
	    }
	    
	    
	 private static int getFileDir(String name) {
	        return name.hashCode() & 0xf;
	    }
}