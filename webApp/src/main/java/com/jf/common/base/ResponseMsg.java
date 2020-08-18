package com.jf.common.base;
/**
 * 用于json格式的返回
 * @author zzb
 *
 */
public class ResponseMsg {
	public static final String SUCCESS = "0000";
	public static final String ERROR = "4004";
	public static final String ERROR_4005 = "4005";//购物车返回异常
	public static final String ERROR_9999 = "9999";
	public static final String SUCCESS_MSG = "请求成功";
	public static final String ERROR_MSG = "请求失败";
	private String returnCode;
	private String returnMsg;
	private Integer returnSize;
	private Object returnData=null;

	public ResponseMsg(){
	}
	public ResponseMsg(String returnCode,String returnMsg){
		this.returnCode=returnCode;
		this.returnMsg=returnMsg;
	}
	public ResponseMsg(String returnCode,String returnMsg,Object returnData){
		this.returnCode=returnCode;
		this.returnMsg=returnMsg;
		this.returnData=returnData;
	}
	public ResponseMsg(String returnCode,String returnMsg,Integer returnSize,Object returnData){
		this.returnCode=returnCode;
		this.returnMsg=returnMsg;
		this.returnSize=returnSize;
		this.returnData=returnData;
	}

	public static ResponseMsg success() {
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

    public static ResponseMsg success(Object data, Integer returnSize) {
        return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnSize, data);
    }

	public static ResponseMsg success(Object data) {
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, data);
	}

	public static ResponseMsg error(String msg) {
		return new ResponseMsg(ResponseMsg.ERROR, msg);
	}

	public static ResponseMsg error(String code, String msg) {
		return new ResponseMsg(code, msg);
	}

	public static ResponseMsg error(String code, String msg, Object data) {
		return new ResponseMsg(code, msg, data);
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
	public Integer getReturnSize() {
		return returnSize;
	}
	public void setReturnSize(Integer returnSize) {
		this.returnSize = returnSize;
	}

}
