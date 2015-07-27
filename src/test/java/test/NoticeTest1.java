package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.csust.volunteer.service.InfoService;
import edu.csust.volunteer.service.NoticeService;

public class NoticeTest1 {
	ApplicationContext ctx = null;
	NoticeService noticeService = null;
	private List<String> lists = new ArrayList<String>();
	@Before
	public void before(){
		ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		noticeService = (NoticeService) ctx.getBean("NoticeService");
	}
	@Test
	public void test() {
		noticeService.getLastNoticeList("201258080119");
	}

}
