package edu.csust.volunteer.action;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;

import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.service.ActivityService;
import edu.csust.volunteer.service.PictureService;
import edu.csust.volunteer.service.UserService;

@Action("ajaxUploderAction")
public class AjaxUploderAction extends BaseAction<Activity>{
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UserAction.class);
	private File uploadFile;
	private String filename;
	private File[] fileUpload;
	private String[] fileUploadFileName;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private PictureService pictureService;
	@Autowired
	private UserService userService;
	public void uploadPic(){
		String uuid=UUID.randomUUID().toString();
/*		活动图片,保存的应该是缩略图*/
		String savePath= ServletActionContext.getServletContext().getRealPath("/image/activity");
		File file = new File(savePath); // 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) file.mkdir();
		JSONObject jsonData=new JSONObject();
		File f[] = this.getFileUpload();
		String pictureSavePath[]=new String[f.length];
		boolean uploadResult=true;
		System.out.println(f.length);
		for (int i = 0; i < f.length; i++) {
			String uploadName=getFileUploadFileName()[i].toString();
			String tmpName = uploadName.substring(uploadName.lastIndexOf(".") + 1,uploadName.length()); //获得扩展名
			try {
				// 以服务器的文件保存地址和原文件名建立上传文件输出流
				FileOutputStream fos = new FileOutputStream(savePath+ "\\"+ uuid+"."+tmpName);
				// 以上传文件建立一个文件上传流
				 BufferedInputStream fis = new BufferedInputStream(new FileInputStream(getFileUpload()[i]));
				// 将上传文件的内容写入服务器
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) 
				fos.write(buffer, 0, len);
				
				fis.close();
				fos.flush();
				fos.close();
				
				pictureSavePath[i] = request.getContextPath() + "/image/activity/"+ uuid+ "."+tmpName;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				uploadResult=false;
			} catch (IOException e) {
				e.printStackTrace();
				uploadResult=false;
			}
		}
		jsonData.put("uploadResult", uploadResult);
		jsonData.put("pictureSavePath", pictureSavePath);
		writeJson(jsonData);
	}
	public void addCropperPic(){
		String savePath= ServletActionContext.getServletContext().getRealPath("/image/thumb-activity");
		File file = new File(savePath); // 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) file.mkdir();
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonData=new JSONObject();
		boolean cropperPicResult=true;
		int dataX=Integer.parseInt(request.getParameter("dataX"));
		int dataY=Integer.parseInt(request.getParameter("dataY"));
		int dataWidth=Integer.parseInt(request.getParameter("dataWidth"));
		int dataHeight=Integer.parseInt(request.getParameter("dataHeight"));
		int activityId=Integer.parseInt(request.getParameter("activityId"));
		int time=Integer.parseInt(request.getParameter("time"));
		String picPath=request.getParameter("picPath");
		LOGGER.info(dataY+"++"+dataX+"++"+dataWidth+"++"+dataHeight+"++"+activityId+"++"+picPath);
		String realpath = ServletActionContext.getServletContext().getRealPath("");
		try {
			Thumbnails.of(realpath+picPath.substring(10)).sourceRegion(dataX,dataY,
				dataWidth, dataHeight).size(dataWidth, dataHeight).keepAspectRatio(false).toFile(
						savePath+ "\\"+picPath.substring(26));
			cropperPicResult=true;
			activityService.saveActivityPic(time,activityId,request.getContextPath()+"/image/thumb-activity/"+picPath.substring(26));
		} catch (IOException e) {
			LOGGER.info("错误");
		}
		jsonData.put("time", time);
		jsonData.put("cropperPicResult", cropperPicResult);
		writeJson(jsonData);
	}
	
	public  synchronized void uploadMutiPic() throws IOException {
		//一次性最多上传10张，应该有限制....不能读取png图片buffimage
		FileOutputStream fos =null;
		BufferedInputStream fis =null;
		String uuid=UUID.randomUUID().toString();
//		FileInputStream fis1=new Fil
		HttpServletRequest request=ServletActionContext.getRequest();
		String savePath= ServletActionContext.getServletContext().getRealPath("/image/userUpload");
		File file = new File(savePath); // 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists())file.mkdir();
		String cropperPath= ServletActionContext.getServletContext().getRealPath("/image/thumb-user");
		File file1 = new File(cropperPath); // 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file1.exists())file1.mkdir();
		int album=Integer.parseInt(request.getParameter("album"));
		String userno=(String)request.getSession().getAttribute("userno");
		JSONObject jsonData=new JSONObject();
		boolean uploadResult=true;
		String name=this.getFilename();
		String pictureSavePath="";
		String pictureCropperPath="";
//		executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			String uploadName=name.toString();
			String tmpName = uploadName.substring(uploadName.lastIndexOf(".") + 1,uploadName.length()); 
			try {
				// 以服务器的文件保存地址和原文件名建立上传文件输出流
				fos = new FileOutputStream(savePath+ "\\"+uuid+"."+tmpName);
				// 以上传文件建立一个文件上传流
				fis = new BufferedInputStream(new FileInputStream(getUploadFile()));
				
				// 将上传文件的内容写入服务器
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) 
				fos.write(buffer, 0, len);
				//重新获得图片流
				fis = new BufferedInputStream(new FileInputStream(savePath+ "\\"+uuid+"."+tmpName));
				BufferedImage image = ImageIO.read(fis);
				int widthX=image.getWidth(),X=0;
				int HeightY=image.getHeight(),Y=0;
				int size=100;
				System.out.println("图片高度"+image.getHeight()+"图片宽度"+image.getWidth());;
				if (widthX<HeightY) {
					X=0;Y=(HeightY-widthX)/2;
					size=widthX;
				}else if(widthX>HeightY){
					X=(widthX-HeightY)/2;Y=0;
					size=HeightY;
				}else {
					X=0;Y=0;size=HeightY;
				}
				//剪切
				Thumbnails.of(savePath+ "\\"+ uuid+"."+tmpName).sourceRegion(X,Y,
						size, size).size(170, 170).keepAspectRatio(false).toFile(
								cropperPath+ "\\"+ uuid+"."+tmpName);
				//降低像素
				Thumbnails.of(savePath+ "\\"+ uuid+"."+tmpName).sourceRegion(X,Y,
						size, size).size(170, 170).keepAspectRatio(false).toFile(
								cropperPath+ "\\"+ uuid+"."+tmpName);
				pictureSavePath = request.getContextPath() +"/image/userUpload"+ "/"+ uuid + "."+tmpName;//大图地址
				pictureCropperPath =request.getContextPath() +"/image/thumb-user"+ "/"+ uuid + "."+tmpName;//小图地址
			} catch (FileNotFoundException e) {
				uploadResult=false;
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				uploadResult=false;
			}finally {
	            if (fos != null)
	                fos.close();
	        }
		//guava判断条件
		boolean edit=true;
		Preconditions.checkArgument(1 > 0, "must be positive: %s", "hello"); 
		int pic=pictureService.savePic(userno,pictureSavePath,pictureCropperPath,album,0,edit);
		jsonData.put("uploadResult", uploadResult);
		jsonData.put("picid", pic);
		jsonData.put("pictureCropperPath", pictureCropperPath);
		writeJson(jsonData);
	}
	public  synchronized void uploadActivityPic() throws IOException {
		//一次性最多上传10张，应该有限制....不能读取png图片buffimage
		FileOutputStream fos =null;
		BufferedInputStream fis =null;
		String uuid=UUID.randomUUID().toString();
//		FileInputStream fis1=new Fil
		HttpServletRequest request=ServletActionContext.getRequest();
		String savePath= ServletActionContext.getServletContext().getRealPath("/image/act_pic");
		File file = new File(savePath); // 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists())file.mkdir();
		String cropperPath= ServletActionContext.getServletContext().getRealPath("/image/act_thumb");
		File file1 = new File(cropperPath); // 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file1.exists())file1.mkdir();
		int activityid=Integer.parseInt(request.getParameter("activityid"));
		String userno=(String)request.getSession().getAttribute("userno");
		JSONObject jsonData=new JSONObject();
		boolean uploadResult=true;
		String name=this.getFilename();
		String pictureSavePath="";
		String pictureCropperPath="";
//		executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			String uploadName=name.toString();
			String tmpName = uploadName.substring(uploadName.lastIndexOf(".") + 1,uploadName.length()); 
			try {
				// 以服务器的文件保存地址和原文件名建立上传文件输出流
				fos = new FileOutputStream(savePath+ "\\"+uuid+"."+tmpName);
				// 以上传文件建立一个文件上传流
				fis = new BufferedInputStream( new FileInputStream(getUploadFile()));
				// 将上传文件的内容写入服务器
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) 
				fos.write(buffer, 0, len);
				//重新获得图片流
				fis = new BufferedInputStream(new FileInputStream(savePath+ "\\"+uuid+"."+tmpName));
				BufferedImage image = ImageIO.read(fis);
				int widthX=image.getWidth(),X=0;
				int HeightY=image.getHeight(),Y=0;
				int size=100;
				System.out.println("图片高度"+image.getHeight()+"图片宽度"+image.getWidth());;
				if (widthX<HeightY) {
					X=0;Y=(HeightY-widthX)/2;size=widthX;
				}else if(widthX>HeightY){
					X=(widthX-HeightY)/2;Y=0;size=HeightY;
				}else {
					X=0;Y=0;size=HeightY;
				}
				//剪切
				Thumbnails.of(savePath+ "\\"+ uuid+"."+tmpName).sourceRegion(X,Y,
						size, size).size(size, size).keepAspectRatio(false).toFile(
								cropperPath+ "\\"+ uuid+"."+tmpName);
				pictureSavePath = request.getContextPath() +"/image/act_pic"+ "/"+ uuid + "."+tmpName;//大图地址
				pictureCropperPath =request.getContextPath() +"/image/act_thumb"+ "/"+ uuid + "."+tmpName;//小图地址
			} catch (FileNotFoundException e) {
				uploadResult=false;
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				uploadResult=false;
			}finally {
	            if (fos != null)
	                fos.close();
	        }
		//guava判断条件
		boolean edit=true;
		Preconditions.checkArgument(1 > 0, "must be positive: %s", "hello"); 
		int pic=pictureService.savePic(userno,pictureSavePath,pictureCropperPath,0,activityid,edit);
		jsonData.put("uploadResult", uploadResult);
		jsonData.put("picid", pic);
		jsonData.put("pictureCropperPath", pictureCropperPath);
		writeJson(jsonData);
	}
	public void uploadUserHead() throws IOException {
		JSONObject jsonObject=new JSONObject();
		FileOutputStream fos =null;
		FileInputStream fis =null;
		String uuid=UUID.randomUUID().toString();
		String uploadName=getFilename().toString();
		String savePath= ServletActionContext.getServletContext().getRealPath("/image/userhead");
		System.out.println("保存路径"+savePath);
		File file = new File(savePath); // 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists())file.mkdir();
		String tmpName = uploadName.substring(uploadName.lastIndexOf(".") + 1,uploadName.length()); 
		String pictureSavePath="";
		try {
			// 以服务器的文件保存地址和原文件名建立上传文件输出流
			fos = new FileOutputStream(savePath+ "\\"+uuid+"."+tmpName);
			// 以上传文件建立一个文件上传流
			fis = new FileInputStream(getUploadFile());
			// 将上传文件的内容写入服务器
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) 
			fos.write(buffer, 0, len);
			//重新获得图片流
			pictureSavePath = request.getContextPath() +"/image/userhead"+ "/"+ uuid + "."+tmpName;//大图地址
			System.out.println(pictureSavePath);
		}
		catch (IOException e) {
			e.printStackTrace();
		}finally {
            if (fos != null)
                fos.close();
        }
		jsonObject.put("path", pictureSavePath);
		writeJson(jsonObject);
	}
	public void addUserHeadCropperPic() {
		JSONObject jsonData=new JSONObject();
		boolean issuccess=true;
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=(String)request.getSession().getAttribute("userno");
		int dataX=Integer.parseInt(request.getParameter("dataX"));
		int dataY=Integer.parseInt(request.getParameter("dataY"));
		int dataWidth=Integer.parseInt(request.getParameter("dataWidth"));
		int dataHeight=Integer.parseInt(request.getParameter("dataHeight"));
		String picPath=request.getParameter("picPath");
		String savepath= ServletActionContext.getServletContext().getRealPath("/image/userhead");
		LOGGER.info(dataY+"++"+dataX+"++"+dataWidth+"++"+dataHeight+"++"+picPath);
		File file = new File(savepath); // 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) file.mkdir();
		String picName=picPath.substring(26);
		System.out.println(savepath+"\\"+picName);
		String showPath=request.getContextPath()+"/image/userhead/"+"1"+picName;
		System.out.println(showPath);
		try {
			
			Thumbnails.of(savepath+"\\"+picName).sourceRegion(dataX,dataY,
				dataWidth, dataHeight).size(200, 200).keepAspectRatio(false).toFile(
						savepath+"\\"+"1"+picName);
			User user=userService.findUserById(userno);
			user.setUserHead(showPath);
			System.out.println("这里是保存的路径"+showPath);
			userService.updateUser(user);
		} catch (IOException e) {
			
			LOGGER.info("错误");
			issuccess=false;
		}
		jsonData.put("issuccess", issuccess);
		writeJson(jsonData);
	}
	      
	        

	public File[] getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(File[] fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String[] getFileUploadFileName() {
		return fileUploadFileName;
	}
	public void setFileUploadFileName(String[] fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
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
