package com.jf.vo.response.stockclearance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2020-08-04 下午 3:57
 */
public class StockClearanceProductTypeResponse {

	// 单品活动类目集合
	private List<ProductTypeView> productTypeList = new ArrayList<>();

	public List<ProductTypeView> getProductTypeList() {
		return productTypeList;
	}

	public void setProductTypeList(List<ProductTypeView> productTypeList) {
		this.productTypeList = productTypeList;
	}

}
