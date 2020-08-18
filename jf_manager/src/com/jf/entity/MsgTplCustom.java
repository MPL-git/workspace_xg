package com.jf.entity;


public class MsgTplCustom extends MsgTpl{
	private String tplTypeDesc;
	private String msgTypeDesc;

	public String getTplTypeDesc() {
		return tplTypeDesc;
	}
	public void setTplTypeDesc(String tplTypeDesc) {
		this.tplTypeDesc = tplTypeDesc;
	}
	public String getMsgTypeDesc() {
		return msgTypeDesc;
	}
	public void setMsgTypeDesc(String msgTypeDesc) {
		this.msgTypeDesc = msgTypeDesc;
	}
}
