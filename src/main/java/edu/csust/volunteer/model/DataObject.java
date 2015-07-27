package edu.csust.volunteer.model;

import java.io.Serializable;

public class DataObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean success;
	private String info;
	private Object object;

	public DataObject(Object object) {
		super();
		this.object =(Serializable)object;
	}

	public DataObject() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
