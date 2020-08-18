package com.jf.entity;
/**
 * 接口状态码枚举值
 *
 */
public enum StateCode {
	
	SUCCESS("0","成功","查询或调用成功"),
	ERR_APP_CALLAPP("-1","应用调用异常","应用调用异常"),
	ERR_APP_CALLINTF("-1000","接口调用异常","接口调用异常"),
	ERR_DB_INVOKE("-2000","数据库调用异常","数据库调用异常"),
	ERR_PARAM_EMPTY("-3000","入参为空","入参为空"),
	ERR_PARAM_VALID("-3001","入参非法","入参非法"),
	ERR_OTHER("-9999","其他逻辑异常","其他逻辑异常"),
	ERR_TRANSATION_HCYL("-6301","业务办理存在互斥依赖","业务办理存在互斥依赖"),
	ERR_FILE_UPLOAD("-999","失败","文件上传失败"),
	JSON_AJAX_SUCCESS("200","成功","操作成功"),
	JSON_AJAX_ERROR("300","失败","操作异常失败"),
	;
	
	private String stateCode;		//编码
	private String stateName;		//名称
	private String stateMessage;	//详细描述信息
	
	private StateCode(String stateCode,String stateName,String stateMessage){
		this.stateCode=stateCode;
		this.stateName=stateName;
		this.stateMessage=stateMessage;
	}
	
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateMessage() {
		return stateMessage;
	}
	public void setStateMessage(String stateMessage) {
		this.stateMessage = stateMessage;
	}
	
}
