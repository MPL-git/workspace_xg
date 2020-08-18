package com.jf.common.base;

/**
 * @ClassName: ArgException
 * @Description: TODO
 * @author: zhangjingcheng_91
 * @date: 2015年7月28日 下午5:24:05
 * @version: V1.0
 */
public class ArgException extends RuntimeException {
	private String message;
	private String exceptionType;
	/**
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	public ArgException(String message) {
		this.message=message;
	}
	
	public ArgException(String message,String exceptionType){
		this.message=message;
		this.exceptionType=exceptionType;
	}

	/**  
	 * message  
	 * @return message message  
	 */
	public String getMessage() {
		return message;
	}
	

	/**  
	 * message  
	 * @param message message  
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public String getExceptionType() {
		return exceptionType;
	}
	

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	

	
}
