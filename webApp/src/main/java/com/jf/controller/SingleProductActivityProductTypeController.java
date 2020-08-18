package com.jf.controller;

import com.jf.common.AppContext;
import com.jf.common.base.ResponseMsg;
import com.jf.service.SingleProductActivityProductTypeService;
import com.jf.vo.request.stockclearance.ProductPropRequest;
import com.jf.vo.request.stockclearance.SelectSingleProductActivityProductRequest;
import com.jf.vo.response.stockclearance.ProductPropResponse;
import com.jf.vo.response.stockclearance.SelectSingleProductActivityProductResponse;
import com.jf.vo.response.stockclearance.StockClearanceProductResponse;
import com.jf.vo.response.stockclearance.StockClearanceProductTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Pengl
 * @create 2020-08-04 下午 3:34
 */
@Controller
public class SingleProductActivityProductTypeController extends BaseController {

	@Autowired
	private SingleProductActivityProductTypeService singleProductActivityProductTypeService;

	@Autowired
	private AppContext appContext;

	/**
	 * 获取断码清仓类目列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/n/getSingleProductActivityProductType")
	public ResponseMsg getSingleProductActivityProductType() {
		StockClearanceProductTypeResponse stockClearanceProductTypeResponse = singleProductActivityProductTypeService.getSingleProductActivityProductType();
		return ResponseMsg.success(stockClearanceProductTypeResponse);
	}

	/**
	 * 获取断码清仓广告列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/n/getSingleProductActivityProduct")
	public ResponseMsg getSingleProductActivityProduct() {
		StockClearanceProductResponse stockClearanceProductResponse = singleProductActivityProductTypeService.getSingleProductActivityProduct();
		return ResponseMsg.success(stockClearanceProductResponse);
	}

	/**
	 * 获取尺码集合列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/n/getProductPropResponse")
	public ResponseMsg getProductPropResponse() {
		ProductPropRequest productPropRequest = appContext.getRequestAndValidate(ProductPropRequest.class);
		ProductPropResponse productPropResponse = singleProductActivityProductTypeService.getProductPropResponse(productPropRequest);
		return ResponseMsg.success(productPropResponse);
	}

	/**
	 * 获取今日推荐活动商品列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/n/getSingleProduct")
	public ResponseMsg getSingleProduct() {
		SelectSingleProductActivityProductRequest selectSingleProductActivityProductRequest = appContext.getRequestAndValidate(SelectSingleProductActivityProductRequest.class);
		SelectSingleProductActivityProductResponse selectSingleProductActivityProductResponse = singleProductActivityProductTypeService.getSingleProduct(selectSingleProductActivityProductRequest);
		return ResponseMsg.success(selectSingleProductActivityProductResponse);
	}

	/**
	 * 获取今日推荐头部信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/n/getSingleProductTopInfo")
	public ResponseMsg getSingleProductTopInfo() {
		Map<String, Object> map = singleProductActivityProductTypeService.getSingleProductTopInfo();
		return ResponseMsg.success(map);
	}

}
