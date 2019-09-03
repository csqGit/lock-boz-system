package com.app.bzpower.entity;

public class RequestResult <T> {

	private int code;//错误代码
	
	private T result;

	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	
}
