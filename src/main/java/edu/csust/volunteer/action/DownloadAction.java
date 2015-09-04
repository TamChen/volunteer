package edu.csust.volunteer.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import edu.csust.volunteer.model.Download;
import edu.csust.volunteer.model.UserDiary;
import edu.csust.volunteer.service.IdownLoadService;
import edu.csust.volunteer.util.FileOperateUtil;

@Action("downloadAction") 
public class DownloadAction extends BaseAction<Download>{
	
	@Autowired
	private IdownLoadService downloadService;
	private File uploadFile;
	private String filename;

	/*获取下载的条数*/
    public void getNumber() throws Exception {
    	JSONObject jsonData=new JSONObject();
		jsonData.put("number",downloadService.getNumber());
		writeJson(jsonData);
		
    }

    /*获取前台前台分页*/
    public void getInfo() throws Exception {
    	String currentString = request.getParameter("current");
    	int current=Integer.parseInt(currentString);
    	String sizeString = request.getParameter("size");
    	int size=Integer.parseInt(sizeString);
    	JSONObject jsonData=new JSONObject();
    	List<Download> infoList=downloadService.getLastSevenInfo(current,size);
    	System.out.println("hello"+infoList.size());
		jsonData.put("infoList", infoList);
		writeJson(jsonData);
    }
    //========================================================================================================
    
    /*删除文件*/
    public void delete() throws Exception {
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
        writeJson(jsonData);
    }
    /* 更新文件信息*/
    public void update() throws Exception {
    	String idString = request.getParameter("id");
    	int id=Integer.parseInt(idString);
    	JSONObject jsonData=new JSONObject();
    	String downloadfFileName = request.getParameter("name");
    	String name = new String(downloadfFileName.getBytes("iso-8859-1"),"utf-8");
        boolean success=downloadService.updateDownloadInfo(id,name);//删除数据库中的数据
        jsonData.put("success",success);
        writeJson(jsonData);
    }
    
    /*通过设置响应的头消息来实现文件的下载功能。*/
	public void download(){
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
    public void upload(){
    	JSONObject jsonData=new JSONObject(); 
    	boolean success=true;
        init(request);
        try {
        	/*上传文件有文件类型*/  /*type*/
        	/*	将HttpServletRequest对象强制转换为MultipartRequest类型，然后通过MultipartRequest.getFileMap()得到Map<String, MultipartFile>，然后分别保存每个提交的文件*/
//        	 File[] files = new File(FileOperateUtil.FILEDIR).listFiles();
            File file = new File(FileOperateUtil.FILEDIR);
            if (!file.exists()) {
                file.mkdir();
            }
    			String uploadName=getFilename().toString();
    			String tmpName = uploadName.substring(uploadName.lastIndexOf(".") + 1,uploadName.length()); //获得扩展名
    			String fileName=request.getParameter("title");
            	fileName = new String(fileName.getBytes("iso-8859-1"),"utf-8");//当有乱码时fastjson会出现错误
            	String path[]=initFilePath(fileName+"."+tmpName);
    			try {
    				// 以上传文件建立一个文件上传流
    				DateTime now = new DateTime();// 取得当前时间  
                	String time = now.toString("yyyy/MM/dd");
                	downloadService.savaDownloadInfo(path[1],fileName,time);
                	//输入流    path[0]为输出路径
                	FileOperateUtil.write(new FileInputStream(getUploadFile()), new FileOutputStream(path[0]));
    			} catch (FileNotFoundException e) {
    				success=false;
    				e.printStackTrace();
    			} catch (IOException e) {
    				success=false;
    				e.printStackTrace();
    			}
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonData.put("success", success);
		writeJson(jsonData);
    }
	 private void init(HttpServletRequest request) {
	     if(FileOperateUtil.FILEDIR == null){
	    	String path=request.getSession().getServletContext().getRealPath("/");
	    	System.out.println(path);
	        FileOperateUtil.FILEDIR = path.substring(0, path.length()-18) + "volunteerfile/";
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
	 
	 public File getUploadFile() {
			return uploadFile;
		}

		public void setUploadFile(File uploadFile) {
			this.uploadFile = uploadFile;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

}