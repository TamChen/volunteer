/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package edu.csust.volunteer.exception;


public class UserException extends BaseException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }

}
