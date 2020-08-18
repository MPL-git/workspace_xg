package com.jf.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jf.common.AppContext;
import com.jf.vo.request.GetProductRelativeCouponInfoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.service.ProductService;

import net.sf.json.JSONObject;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年6月6日 上午10:47:11 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class ProductInfoController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(ProductInfoController.class);
	@Resource
	private ProductService productService;
	@Autowired
	private AppContext appContext;
	/**
	 * 
	 * 方法描述 ：商品详情基础信息
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月6日 上午11:23:47 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductInfo")
	@ResponseBody
	public ResponseMsg getProductInfo(HttpServletRequest request){
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		try {
			String memberId = getMemberIdStr(request);
			if(StringUtil.isBlank(memberId)){
				if(reqDataJson.containsKey("memberId")){
					memberId = reqDataJson.getString("memberId");
				}
			}
			Map<String,Object> map = productService.getProductBaseInfo(reqPRM,reqDataJson,memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			logger.info("商品详情参数："+reqDataJson.getString("id").toString());
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			logger.info("商品详情参数："+reqDataJson.getString("id").toString());
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 * 商品详情页-优惠详情信息查询
	 */
	@RequestMapping(value = "/api/n/getProductRelativeCouponInfo")
	@ResponseBody
	public ResponseMsg getProductRelativeCouponInfo() {
		GetProductRelativeCouponInfoRequest request = appContext.getRequestAndValidate(GetProductRelativeCouponInfoRequest.class);
		Map<String, Object> data = productService.getProductRelativeCouponInfo(request.getId(), request.getMemberId());
		return ResponseMsg.success(data);
	}
	
	/**
	 * 
	 * 方法描述 ：商品sku信息
	 * @author  chenwf: 
	 * @date 创建时间：2018年6月6日 上午11:23:47 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductSkuInfo")
	@ResponseBody
	public ResponseMsg getProductSkuInfo(HttpServletRequest request){
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		try {
			Map<String,Object> map = productService.getProductSkuInfo(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			logger.info("商品详情参数："+reqDataJson.getString("id").toString());
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			logger.info("商品详情参数："+reqDataJson.getString("id").toString());
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：签到商品的商品详情(没有判断商品状态)
	 * @author  chenwf: 
	 * @date 创建时间：2018年12月13日 上午11:23:47 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getCommonProductBaseInfo")
	@ResponseBody
	public ResponseMsg getCommonProductBaseInfo(HttpServletRequest request){
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		try {
			Map<String,Object> map = productService.getCommonProductBaseInfo(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			logger.info("商品详情参数："+reqDataJson.getString("id").toString());
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			logger.info("商品详情参数："+reqDataJson.getString("id").toString());
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
