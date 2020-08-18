package com.jf.vo.response.stockclearance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pengl
 * @create 2020-08-06 下午 2:41
 */
public class ProductPropResponse {

	// 尺码集合
	private List<ProductPropView> productPropList = new ArrayList<>();

	public List<ProductPropView> getProductPropList() {
		return productPropList;
	}

	public void setProductPropList(List<ProductPropView> productPropList) {
		this.productPropList = productPropList;
	}
}
