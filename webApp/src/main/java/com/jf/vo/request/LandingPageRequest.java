package com.jf.vo.request;

import javax.validation.constraints.NotNull;

/**
 * @author Pengl
 * @create 2020-06-28 上午 10:15
 */
public class LandingPageRequest {

	@NotNull(message = "类型不能为空")
	private String type;
	@NotNull(message = "设备型号不能为空")
	private String reqModel;
	@NotNull(message = "系统版本不能为空")
	private String systemVersion;
	private String androidChnl;
	private Integer iosActivityGroupId;
	private String iosActivityName;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReqModel() {
		return reqModel;
	}

	public void setReqModel(String reqModel) {
		this.reqModel = reqModel;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getAndroidChnl() {
		return androidChnl;
	}

	public void setAndroidChnl(String androidChnl) {
		this.androidChnl = androidChnl;
	}

	public Integer getIosActivityGroupId() {
		return iosActivityGroupId;
	}

	public void setIosActivityGroupId(Integer iosActivityGroupId) {
		this.iosActivityGroupId = iosActivityGroupId;
	}

	public String getIosActivityName() {
		return iosActivityName;
	}

	public void setIosActivityName(String iosActivityName) {
		this.iosActivityName = iosActivityName;
	}
}
