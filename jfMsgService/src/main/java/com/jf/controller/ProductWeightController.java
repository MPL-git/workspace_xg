package com.jf.controller;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.service.IntegralDailyStatisticsService;
import com.jf.service.ProductWeightService;

@Controller
public class ProductWeightController {

	
	@Resource
	private ProductWeightService productWeightService;
	/**
	 * 更新季节权重
	 * @param request
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/productWeight/updateProductSeasonWeights")
	public ResponseMsg updateProductSeasonWeights(HttpServletRequest request) {
		Calendar calendar = Calendar.getInstance();
    	productWeightService.updateProductSeasonWeights(calendar.get(Calendar.MONTH)+1);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
      
	}
	
	/**
	 * 更新商家等级权重
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/productWeight/updateProductMchtGradeWeights")
	public ResponseMsg updateProductMchtGradeWeights(HttpServletRequest request) {
		productWeightService.updateProductMchtGradeWeights();
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	/**
	 * 更新品牌等级权重
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/productWeight/updateProductBrandGradeWeights")
	public ResponseMsg updateProductBrandGradeWeights(HttpServletRequest request) {
		productWeightService.updateProductBrandGradeWeights();
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	/**
	 * 更新商品总权重
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/productWeight/updateProductWeightsTotal")
	public ResponseMsg updateProductWeightsTotal(HttpServletRequest request) {
		productWeightService.updateProductWeightsTotal();
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	/**
	 * 插入商品权重表记录
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/productWeight/insertProductWeights")
	public ResponseMsg insertProductWeights(HttpServletRequest request) {
		productWeightService.insertProductWeights();
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	/**
	 * 更新商品销量权重
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/productWeight/updateProductSaleWeight")
	public ResponseMsg updateProductSaleWeight(HttpServletRequest request) {
		productWeightService.updateProductSaleWeight();
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	/**
	 * 更新商品销售额权重
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/productWeight/updateProductSaleAmountWeight")
	public ResponseMsg updateProductSaleAmountWeight(HttpServletRequest request) {
		productWeightService.updateProductSaleAmountWeight();
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	/**
	 * 更新商品评价权重
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/productWeight/updateProductCommentWeight")
	public ResponseMsg updateProductCommentWeight(HttpServletRequest request) {
		productWeightService.updateProductCommentWeight();
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	
	/**
	 * 更新商品点击量权重
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/productWeight/updateProductPvWeight")
	public ResponseMsg updateProductPvWeight(HttpServletRequest request) {
		productWeightService.updateProductPvWeight();
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	 
}
