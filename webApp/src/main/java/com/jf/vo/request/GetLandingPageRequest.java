package com.jf.vo.request;

import javax.validation.constraints.NotNull;

/**
 * @author Pengl
 * @create 2020-06-28 上午 10:12
 */
public class GetLandingPageRequest {

	@NotNull(message = "落地页ID不能为空")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
