package edu.csust.volunteer.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;

import edu.csust.volunteer.service.BaseService;

@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction<T> extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected BaseService<T> service;// 业务逻辑
	
	/**
	 * 继承BaseAction的action需要先设置这个方法，使其获得当前action的业务服务
	 * 
	 * @param service
	 */
	public void setService(BaseService<T> service) {
		this.service = service;
	}
	
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	protected void writeJson(Object obj) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(JSON.toJSONStringWithDateFormat(obj,
					"yyyy-MM-dd HH:mm:ss", SerializerFeature.PrettyFormat));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
