package edu.csust.volunteer.action;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
	 @Action("/test01")  
	  public String execute() {  
		 System.out.println("hello world");
	    return SUCCESS;  
	  }  
}
