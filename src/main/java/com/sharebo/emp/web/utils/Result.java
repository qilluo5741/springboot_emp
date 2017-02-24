package com.sharebo.emp.web.utils;

public class Result {
	private Integer code;
	private String message;
	private Object result;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getresult() {
		return result;
	}
	public void setresult(Object result) {
		this.result = result;
	}
	public Result(Integer code, String message, Object result) {
		super();
		this.code = code;
		this.message = message;
		this.result = result;
	}
	public Result(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public Result(Integer code) {
		super();
		this.code = code;
	}
	public Result(Integer code, Object result) {
		super();
		this.code = code;
		this.result = result;
	}
	
}
