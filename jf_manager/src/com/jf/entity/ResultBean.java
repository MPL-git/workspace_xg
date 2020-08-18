package com.jf.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 查询结果通用类
 * 主要用在提供AIP接口的时候使用
 */
public class ResultBean implements Serializable{

	private static final long serialVersionUID = -5142709212827138004L;
     @JsonProperty("RESULT_CODE")
	private String resultCode;
	
     @JsonProperty("RESULT_MESSAGE")
	private String message;
     
     @JsonProperty("DATA")
	private Object result;

	public ResultBean(){
		this.resultCode=StateCode.SUCCESS.getStateCode();
		this.message=StateCode.SUCCESS.getStateMessage();
	}
	
	public ResultBean(String resCode,String resMsg ){
		this.resultCode=resCode;
		this.message=resMsg;
	}
	public String getResultCode() {
		return resultCode; 
	}
	
	public ResultBean setResultBean(String resCode,String resMsg ) {
		this.resultCode=resCode;
		this.message=resMsg;
		return this;
	}
	
	public static ResultBean getResultBean(String resCode,String resMsg ) {
		ResultBean resultBean=new ResultBean();
		resultBean.resultCode=resCode;
		resultBean.message=resMsg;
		return resultBean;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	
}
