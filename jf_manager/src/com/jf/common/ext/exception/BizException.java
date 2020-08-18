package com.jf.common.ext.exception;

/**
 * 业务异常
 * 
 * @author hdl
 *
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1;

	private static final int DEFAULT_ERROR_CODE = 1;
	
	/**
	 * 错误码
	 */
	private int errorCode;
	
	public BizException() {
		this.errorCode = DEFAULT_ERROR_CODE;
	}
	
	public BizException(String message) {
		super(message);
		this.errorCode = DEFAULT_ERROR_CODE;
	}
	
    public BizException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = DEFAULT_ERROR_CODE;
    }
    
    public BizException(String message, int errorCode, Throwable cause) {
    	super(message, cause);
    	this.errorCode = errorCode;
    }
    
    public BizException(String message, int errorCode) {
    	this(message);
    	this.errorCode = errorCode;
    }
    
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
    
}
