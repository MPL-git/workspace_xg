package com.jf.vo.request.stockclearance;

import javax.validation.constraints.NotNull;

/**
 * @author Pengl
 * @create 2020-08-06 下午 4:37
 */
public class ProductPropRequest {

	@NotNull(message = "类目ID不能为空")
	private Integer singleProductActivityProductTypeId;

	public Integer getSingleProductActivityProductTypeId() {
		return singleProductActivityProductTypeId;
	}

	public void setSingleProductActivityProductTypeId(Integer singleProductActivityProductTypeId) {
		this.singleProductActivityProductTypeId = singleProductActivityProductTypeId;
	}
}
