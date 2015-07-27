package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.csust.volunteer.model.Info;
import edu.csust.volunteer.service.InfoService;
import edu.csust.volunteer.vo.InfoModel;

public class ServiceTest {
	
	ApplicationContext ctx = null;
	InfoService infoService = null;
	private List<String> lists = new ArrayList<String>();
	
	@Before
	public void before(){
		ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		infoService = (InfoService) ctx.getBean("InfoService");
	}
	
	@Test
	public void getNews_F(){
		
		List<InfoModel> newsInfoList = infoService.getInfo_F(true,1,"1",7);
         for(InfoModel n:newsInfoList){
        	 System.out.println(n.getAuthor());
         }
	}
	
	@Test
	public void addNews_F(){
		Info info = new Info();
		info.setTitle("我是新闻");
		info.setContent("新闻内容");
		info.setIsnew(true);
		info.setAuthor("冯大立");
		info.setAuthorNumber("");
		info.setRecordTime(new Date());
		infoService.addOrUpdateInfo(info);
	}
	
	@Test
	public void getNewsByID(){
		System.out.println(infoService.getNewsByID(5));
	}
	
	@Test
	public void getNewsNumber_F(){
	}
	
	@Test
	public void deleteNews(){
		infoService.deleteInfo(1);
	}
}
