package com.jf.controller;
/**
 * 商品库存处理控制层
 * @author chenwf
 *
 */

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.controller.base.BaseController;
import com.jf.service.CutPriceOrderService;

import net.sf.json.JSONObject;
@Controller
public class ProductItemController extends BaseController{
	@Resource
	private CutPriceOrderService cutPriceOrderService;
	
	/**
	 * 
	 * 方法描述 ：冻结商品sku库存
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月16日 上午11:38:05 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/updateProdutStock")
	@ResponseBody
	public ResponseMsg updateProdutStock(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"id","activityType"};
			this.requiredStr(obj,request);
			Integer id = reqDataJson.getInt("id");
			String activityType = reqDataJson.getString("activityType");
			if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){
				cutPriceOrderService.updateActivationCutOrder(id,activityType);
			}
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
