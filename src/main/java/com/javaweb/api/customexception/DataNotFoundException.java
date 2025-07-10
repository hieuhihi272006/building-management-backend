package com.javaweb.api.customexception;

public class DataNotFoundException extends RuntimeException{
	public DataNotFoundException(String message) {
		super(message);
	}
}
