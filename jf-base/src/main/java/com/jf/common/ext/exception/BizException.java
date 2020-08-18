package com.jf.common.ext.exception;

/**
 * 业务异常
 * 
 * @author hdl
 *
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1;

	public static final String SUCCESS = "0000";
	public static final String ERROR = "4004";

	/**
	 * 错误码
	 */
	private String errorCode;
	
	public BizException() {
		this.errorCode = ERROR;
	}
	
	public BizException(String message) {
		super(message);
		this.errorCode = ERROR;
	}
	
    public BizException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = ERROR;
    }
    
    public BizException(String message, String errorCode, Throwable cause) {
    	super(message, cause);
    	this.errorCode = errorCode;
    }
    
    public BizException(String message, String errorCode) {
    	this(message);
    	this.errorCode = errorCode;
    }
    
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
    
}
