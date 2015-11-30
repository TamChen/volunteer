package edu.csust.volunteer.support;

import java.io.File;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.csust.volunteer.model.Album;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.service.PictureService;
import edu.csust.volunteer.service.UserService;

public class StartRunTask extends Observable implements ServletContextListener {
	private static final Logger LOGGER = Logger.getLogger(StartRunTask.class);
	private Timer timer = new Timer();
	private ApplicationContext ctx;

	public void contextDestroyed(ServletContextEvent arg0) {
		// ②该方法在Web容器关闭时执行
		timer.cancel();
		System.out.println("服务器应用程序关闭...");
	}
	public void contextInitialized(ServletContextEvent sce) {
		// ②在Web容器启动时自动执行该方法 //获得实例
		ctx = WebApplicationContextUtils.getWebApplicationContext(sce
				.getServletContext());
		PictureService pictureService = (PictureService) ctx
				.getBean("pictureService");
		UserService userService = (UserService) ctx
				.getBean("userService");
		// 启动服务器中所有的定时服务
		TimerTask task = new SimpleTimerTask();
		timer.schedule(task, 50000,50000); //
		// 初始化数据库,主要是初始化数据库，例如默认相册
		initDefaultAlbum(pictureService,userService);
		String relativelyPath=System.getProperty("user.dir"); 
		String localPath=relativelyPath.substring(0, relativelyPath.length()-4)+"/webapps/volunteer/image";
		String serverPath=relativelyPath.substring(0, relativelyPath.length()-4)+"/image";
		SynchroManager.copy(serverPath, localPath);//从外部同步到本地。
		System.out.println("初始化数据库中的，默认相册");
	}
	// 初始化数据库,主要是初始化数据库，例如默认相册
	public void initDefaultAlbum(PictureService pictureService,UserService userService) {
		//先获得所有的学生学号，然后写入数据库，先判断在album里是否有值，没有则插入
		List<User> userliList=userService.queryUserAll();
		String userThumb="static/style/img/photo_album.png";
		String intro=new Date().toString()+" 更新";
		//这里数据库应该有一些数据，例如用户的数据
		for (User user : userliList) {
			if (pictureService.getUserAlbumByNo(user.getUserno())==null) {
				Album album=new Album("默认相册",new Date(),user.getUserno(),userThumb,intro,0);
				pictureService.saveAlbum(album);
			}
		}
	}
	public void initDefaultCache() {
		EhcacheManager.addCacheByName(EhcacheManager.CACHE_ACTIVITYCACHE);
		EhcacheManager.addCacheByName(EhcacheManager.CACHE_ALBUMCACHE);
		EhcacheManager.addCacheByName(EhcacheManager.CACHE_ATTENTIONCACHE);
		EhcacheManager.addCacheByName(EhcacheManager.CACHE_INFOCACHE);
		EhcacheManager.addCacheByName(EhcacheManager.CACHE_NOTICECACHE);
		EhcacheManager.addCacheByName(EhcacheManager.CACHE_PICTURECACHE);
		EhcacheManager.addCacheByName(EhcacheManager.CACHE_STATISTICSCACHE);
		EhcacheManager.addCacheByName(EhcacheManager.CACHE_USERCACHE);
		EhcacheManager.addCacheByName(EhcacheManager.CACHE_USERACTIVITYCACHE);
		EhcacheManager.addCacheByName(EhcacheManager.CACHE_USERDIARYCACHE);
	}
	
	class SimpleTimerTask extends TimerTask {// ③任务
		public void run() {
			System.out.println("执行定时任务");
			LOGGER.info("执行定时任务，例如定时清理关系等等");//同步图片文件
//			String path=request.getSession().getServletContext().getRealPath("/");
			String relativelyPath=System.getProperty("user.dir"); 
			String localPath=relativelyPath.substring(0, relativelyPath.length()-4)+"/webapps/volunteer/image";
			String serverPath=relativelyPath.substring(0, relativelyPath.length()-4)+"/image";
			SynchroManager.copy(localPath, serverPath);//从本地同步到外部，防止数据丢失
//			String localPath= ServletActionContext.getServletContext().getRealPath("/image");
//			String serverPath= ServletActionContext.getServletContext().getRealPath("/");
//			serverPath=serverPath.substring(0, serverPath.length()-10)+"/image";
//			System.out.print(localPath);
//			System.out.print(serverPath);
			// try {
			// MainServer mainServer=new MainServer();
			// addObserver(serverLisener);
			// mainServer.addObserver(mainServer);
			// mainServer.run();
			// mainServer = new MainServer();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
	}
}
