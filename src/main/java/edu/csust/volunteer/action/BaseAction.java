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

@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction implements ServletResponseAware, ServletRequestAware {
	protected HttpServletResponse response;
	protected HttpServletRequest request;

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
