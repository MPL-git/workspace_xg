package com.jf.vo.response.stockclearance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2020-08-06 下午 5:58
 */
public class SelectSingleProductActivityProductResponse {

	// 今日推荐商品集合
	private List<Map<String, Object>> productList = new ArrayList<>();

	public List<Map<String, Object>> getProductList() {
		return productList;
	}

	public void setProductList(List<Map<String, Object>> productList) {
		this.productList = productList;
	}
}
