package com.jf.entity;

/**
 * 站内消息扩展
 * @author luoyl
 * 创建时间：2017-3-27 17:32
 */
public class PlatformMsgCustom extends PlatformMsg{
	private String mchtName;//商家名称
	private String mchtCode;//商家编号
	private String msgTypeDesc;
	
	public String getMchtName() {
		return mchtName;
	}

	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}

	public String getMchtCode() {
		return mchtCode;
	}

	public void setMchtCode(String mchtCode) {
		this.mchtCode = mchtCode;
	}
	
	public String getMsgTypeDesc() {
		return msgTypeDesc;
	}
	public void setMsgTypeDesc(String msgTypeDesc) {
		this.msgTypeDesc = msgTypeDesc;
	}
	
}
