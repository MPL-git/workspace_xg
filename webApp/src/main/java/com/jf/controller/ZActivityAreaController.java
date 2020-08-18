package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.ActivityArea;
import com.jf.entity.DecorateModule;
import com.jf.service.ActivityAreaService;
import com.jf.service.DecorateInfoService;
import com.jf.service.DecorateModuleService;
import com.jf.service.ProductTypeService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月5日 上午11:21:26 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class ZActivityAreaController extends BaseController{
	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private DecorateInfoService decorateInfoService;
	@Resource
	private DecorateModuleService decorateModuleService;
	/**
	 * 
	 * 方法描述 ：品牌活动会场
	 * @author  chenwf: 
	 * @date 创建时间：2017年9月6日 下午5:15:09 
	 * @version
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	@RequestMapping(value = "/api/n/getBrandActivityByAreaId")
	@ResponseBody
	public ResponseMsg getBrandActivityByAreaId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"activityAreaId","pageSize","currentPage"};
			this.requiredStr(obj,request);
			String activityAreaId = reqDataJson.getString("activityAreaId");
			String memberId = reqDataJson.getString("memberId");
			Integer pageSize = reqDataJson.getInt("pageSize");
			Integer currentPage = reqDataJson.getInt("currentPage");
			if(currentPage == null){
				currentPage = 0;
			}
			if(pageSize == null){
				pageSize = 5;
			}
			Map<String,Object> map = activityAreaService.getActivityByAreaId(Integer.valueOf(activityAreaId),memberId,pageSize,currentPage);
			if(currentPage == 0){
				List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("dataMapList");
				if(CollectionUtil.isEmpty(list)){
					return new ResponseMsg(ResponseMsg.ERROR_9999,"暂无数据，会场正在装修中");
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,ResponseMsg.ERROR_MSG,"数据加载失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：单品活动会场
	 * @author  chenwf: 
	 * @date 创建时间：2017年9月6日 下午5:15:09 
	 * @version
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	@RequestMapping(value = "/api/n/getIndividualActivityByAreaId")
	@ResponseBody
	public ResponseMsg getIndividualActivityByAreaId(HttpServletRequest request){
		
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"activityAreaId","pageSize","currentPage"};
			this.requiredStr(obj,request);
			String activityAreaId = reqDataJson.getString("activityAreaId");
			String memberId = this.getMemberIdStr(request);
			Integer pageSize = reqDataJson.getInt("pageSize");
			Integer currentPage = reqDataJson.getInt("currentPage");
			//适合性别  00 都不选 11 都选  10 选男不选女   01 选女不选男
			String suitSex = reqDataJson.getString("suitSex");
			if(!StringUtil.isBlank(suitSex)){
				if(suitSex.equals("10")){//男
					suitSex = "1%";
				}else if(suitSex.equals("01")){//女
					suitSex = "%1";
				}
			}
			//适合人群 100青年人   010儿童幼儿   001中老年人
			String suitGroup = reqDataJson.getString("suitGroup");
			//商品品类id
			String productTypeId = reqDataJson.getString("productTypeId");
			//最低价格
			String salePriceMin = reqDataJson.getString("salePriceMin");
			//最高价格
			String salePriceMax = reqDataJson.getString("salePriceMax");
			//售价排序类型 升序DESC 降序  ASC
			String salePriceSort = reqDataJson.getString("salePriceSort");
			//折扣排序类型
			String discountSort = reqDataJson.getString("discountSort");
			//库存>0 标识  T>0
			String stockMark = reqDataJson.getString("stockMark");
			if(currentPage == null){
				currentPage = 0;
			}
			if(pageSize == null){
				pageSize = 5;
			}
			List<String> productTypeIdList = new ArrayList<String>();
			if(!StringUtil.isBlank(productTypeId)){
				for (String productTypeIdStr : productTypeId.split(",")) {
					productTypeIdList.add(productTypeIdStr);
				}
			}
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.valueOf(activityAreaId));
			Map<String, Object> map = activityAreaService.getIndividualActivityByAreaId(Integer.valueOf(activityAreaId),
					memberId, pageSize, currentPage, suitSex, suitGroup, productTypeId, salePriceMin, salePriceMax, salePriceSort,
					discountSort, stockMark,productTypeIdList,activityArea);
			if(currentPage == 0){
				List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("produtMapList");
				if(CollectionUtil.isEmpty(list)){//虽然没有商品，但是页面的其他数据仍需要返回给H5前端（如：顶部图，筛选的分类等）
//					return new ResponseMsg(ResponseMsg.ERROR_9999,"暂无商品");
				}
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,ResponseMsg.ERROR_MSG,"数据加载失败");
		}
	}
	
	@RequestMapping(value = "/api/n/getH5ProductInfoList")
	@ResponseBody
	public ResponseMsg getH5ProductInfoList(HttpServletRequest request){
		
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			String sportProducts = reqDataJson.getString("sportProducts");
			String clothingProducts = reqDataJson.getString("clothingProducts");
			String shoesProducts = reqDataJson.getString("shoesProducts");
			String lifeProducts = reqDataJson.getString("lifeProducts");
			Map<String, Object> map = activityAreaService.getH5ProductInfoList(sportProducts,clothingProducts,shoesProducts,lifeProducts);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,ResponseMsg.ERROR_MSG,"数据加载失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：装修会场
	 * @author  chenwf: 
	 * @date 创建时间：2018年2月28日 下午4:51:52 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getDecorationMeeting")
	@ResponseBody
	public ResponseMsg getDecorationMeeting(HttpServletRequest request){
		
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"activityAreaId"};
			this.requiredStr(obj,request);
			Integer activityAreaId = reqDataJson.getInt("activityAreaId");
			Integer memberId = reqDataJson.optInt("memberId") != 0 ? reqDataJson.optInt("memberId") : null;
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
			Map<String, Object> map = activityAreaService.getDecorationMeeting(activityAreaId,reqPRM,memberId);
			map.put("activityTimeText", "-- 离活动开始还有 --");
			map.put("activityAreaTopPic", StringUtil.getPic(activityArea.getTopPic(), ""));
			map.put("activityBeginTime", activityArea.getActivityBeginTime());
			map.put("activityEndTime", activityArea.getActivityEndTime());
			map.put("currentTime", new Date());
			map.put("activityAreaName", activityArea.getName());
			map.put("isPreSell", activityArea.getIsPreSell());//是否预售:0.否1.是
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,ResponseMsg.ERROR_MSG,"数据加载失败");
		}
	}
	
	@RequestMapping(value = "/api/n/getProductList")
	@ResponseBody
	public ResponseMsg getProductList(HttpServletRequest request){
		
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"decorateModuleId","currentPage"};
			this.requiredStr(obj,request);
			Integer decorateModuleId = reqDataJson.getInt("decorateModuleId");
			Integer currentPage = reqDataJson.getInt("currentPage");
			DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(decorateModuleId);
			List<Map<String,Object>> productList = decorateInfoService.getProductList(decorateModule,Const.RETURN_SIZE_10,currentPage);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,Const.RETURN_SIZE_10,productList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,ResponseMsg.ERROR_MSG,"数据加载失败");
		}
	}
	
	@RequestMapping(value = "/api/n/getActivityList")
	@ResponseBody
	public ResponseMsg getActivityList(HttpServletRequest request){
		
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"decorateModuleId","currentPage"};
			this.requiredStr(obj,request);
			Integer decorateModuleId = reqDataJson.getInt("decorateModuleId");
			Integer currentPage = reqDataJson.getInt("currentPage");
			DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(decorateModuleId);
			List<Map<String,Object>> productList = decorateInfoService.getActivityList(decorateModule,Const.RETURN_SIZE_10,currentPage);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,Const.RETURN_SIZE_10,productList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,ResponseMsg.ERROR_MSG,"数据加载失败");
		}
	}
}
