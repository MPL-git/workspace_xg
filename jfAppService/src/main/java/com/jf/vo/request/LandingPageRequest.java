package com.jf.vo.request;

import javax.validation.constraints.NotNull;

/**
 * @author Pengl
 * @create 2020-06-28 下午 2:52
 */
public class LandingPageRequest {

	@NotNull(message = "类型不能为空")
	private String type;
	@NotNull(message = "设备型号不能为空")
	private String reqModel;
	@NotNull(message = "设备ID不能为空")
	private String reqImei;
	@NotNull(message = "系统版本不能为空")
	private String systemVersion;

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

	public String getReqImei() {
		return reqImei;
	}

	public void setReqImei(String reqImei) {
		this.reqImei = reqImei;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}
}
