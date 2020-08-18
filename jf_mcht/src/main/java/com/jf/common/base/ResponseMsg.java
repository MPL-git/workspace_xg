package com.jf.common.base;
/**
 * 用于json格式的返回
 * @author zzb
 *
 */
public class ResponseMsg {
	public static final String SUCCESS = "0000";
	public static final String ERROR = "4004";
	public static final String SUCCESS_MSG = "请求成功";
	public static final String ERROR_MSG = "请求失败";

	private boolean success;
	private String returnCode;
	private String returnMsg;
	private Object returnData = null;

	public ResponseMsg() {
	}

	public ResponseMsg(String returnCode, String returnMsg) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}

	public ResponseMsg(String returnCode, String returnMsg, Object returnData) {
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.returnData = returnData;
	}

	public static ResponseMsg success() {
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		responseMsg.setSuccess(true);
		return responseMsg;
	}

	public static ResponseMsg success(Object data) {
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, data);
		responseMsg.setSuccess(true);
		return responseMsg;
	}

	public static ResponseMsg error(String msg) {
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.ERROR, msg);
		responseMsg.setSuccess(false);
		return responseMsg;
	}

	public static ResponseMsg error(String code, String msg) {
		ResponseMsg responseMsg = new ResponseMsg(code, msg);
		responseMsg.setSuccess(false);
		return responseMsg;
	}

	public static ResponseMsg error(String code, String msg, Object data) {
		ResponseMsg responseMsg = new ResponseMsg(code, msg, data);
		responseMsg.setSuccess(false);
		return responseMsg;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Object getReturnData() {
		return returnData;
	}

	public void setReturnData(Object returnData) {
		this.returnData = returnData;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
