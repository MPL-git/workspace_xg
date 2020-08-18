package com.jf.common.ext.core;

import java.io.Serializable;
import java.util.Map;

/**
 * 返回结果
 * 
 * @author hdl
 *
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final int ERROR_CODE_SUCCEED = 0;
	public static final int ERROR_CODE_FAILURE = 1;

	
	public static final String ERROR_MESSAGE_UNKNOWN_EXCEPTION = "未知错误，请联系客服处理";

	private boolean succeed;
	private String errorMessage;
	private int errorCode;

	private Map<String, Object> data;
	
	public Result() {
		this.succeed = true;
		this.errorMessage = "";
		this.errorCode = ERROR_CODE_SUCCEED;
	}

	public Result(Map<String, Object> data) {
		this.succeed = true;
		this.errorMessage = "";
		this.errorCode = ERROR_CODE_SUCCEED;
		this.data = data;
	}

	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DemoResult [succeed=" + succeed + ", errorMessage=" + errorMessage + ", errorCode=" + errorCode
				+ "]";
	}
	
}
