package edu.csust.volunteer.model;

import java.io.Serializable;

public class DataObject implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean success;
	private String msg;
	private Object object;

	public DataObject(boolean success, String msg, Object object) {
		super();
		this.success = success;
		this.msg = msg;
		this.object = object;
	}

	public DataObject() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
