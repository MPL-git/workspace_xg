package com.jf.vo.response.stockclearance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2020-08-04 下午 3:57
 */
public class StockClearanceProductResponse {

	// 广告位集合
	private List<Map<String, Object>> LinkTypeList = new ArrayList<>();
	// 今日推荐商品集合
	private List<Map<String, Object>> productList = new ArrayList<>();

	public List<Map<String, Object>> getLinkTypeList() {
		return LinkTypeList;
	}

	public void setLinkTypeList(List<Map<String, Object>> linkTypeList) {
		LinkTypeList = linkTypeList;
	}

	public List<Map<String, Object>> getProductList() {
		return productList;
	}

	public void setProductList(List<Map<String, Object>> productList) {
		this.productList = productList;
	}
}
