package com.jf.controller;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.entity.*;
import com.jf.service.*;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年2月28日 下午1:58:23 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class CustomDecorateController extends BaseController{
	
	@Resource
	private DecorateInfoService decorateInfoService;
	@Resource
	private DecorateAreaService decorateAreaService;
	@Resource
	private DecorateModuleService decorateModuleService;
	@Resource
	private ModuleMapService moduleMapService;
	@Resource
	private AdInfoService adInfoService;
	@Resource
	private CustomPageService customPageService;

	/**
	 * 
	 * 方法描述 ：首页主题馆
	 * @author  chenwf: 
	 * @date 创建时间：2018年2月28日 下午4:09:57 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getHomePageDecorateInfo")
	@ResponseBody
	public ResponseMsg getHomePageDecorateInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			String type = "1";
			String position = "";
			if(reqDataJson.containsKey("type")){
				type = reqDataJson.getString("type");
			}
			if(type.equals("2")){
				//首页主题馆
				position = Const.AD_POSITION_HOME_MARKETING;
			}else if(type.equals("3")){
				position = Const.AD_POSITION_HOME_PRDUCT_DETAIL_MARKETING;
			}else{
				position = Const.AD_POSITION_THEME_PAVILIONES;
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("decorateAreaList", new ArrayList<Map<String,Object>>());
			Date date = new Date();
			AdInfoExample adInfoExample = new AdInfoExample();
			adInfoExample.createCriteria().andTypeEqualTo("3").andCatalogEqualTo(Const.AD_CATALOG_HOME)
			.andPositionEqualTo(position).andDelFlagEqualTo("0").andStatusEqualTo("1")
			.andAutoUpDateLessThanOrEqualTo(date).andAutoDownDateGreaterThanOrEqualTo(date);
			adInfoExample.setOrderByClause("auto_up_date desc,id desc");
			adInfoExample.setLimitSize(1);
			List<AdInfo> adInfos = adInfoService.selectByExample(adInfoExample);
			if(CollectionUtils.isNotEmpty(adInfos)){
				AdInfo adInfo = adInfos.get(0);
				map = decorateInfoService.getHomePageDecorateInfo(adInfo.getLinkId(),reqPRM,getMemberId(request));
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：获取装修页面
	 * @author  chenwf: 
	 * @date 创建时间：2018年2月28日 下午4:09:57 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getDecorateInfoPage")
	@ResponseBody
	public ResponseMsg getDecorateInfoPage(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"decorateInfoId"};
			this.requiredStr(obj,request);
			Integer decorateInfoId = reqDataJson.getInt("decorateInfoId");
			Map<String,Object> map = decorateInfoService.getHomePageDecorateInfo(decorateInfoId,reqPRM,getApinMemberId(reqPRM, reqDataJson, request));
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}


	/**
	 * 
	 * 方法描述 ：运营自建页面
	 * @author  chenwf: 
	 * @date 创建时间：2018年2月28日 下午4:09:57 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getCustomerPage")
	@ResponseBody
	public ResponseMsg getCustomerPage(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"customPageId"};
			this.requiredStr(obj,request);
			
			Integer customPageId = reqDataJson.getInt("customPageId");
			Integer decorateInfoId = null;
			String pageName = "";
			CustomPage customPage = customPageService.selectByPrimaryKey(customPageId);
			if(customPage != null){
				if(customPage.getStatus().equals("1")){
					decorateInfoId = customPage.getDecorateInfoId();
				}
				pageName = customPage.getPageName();
			}
			Map<String,Object> map = decorateInfoService.getHomePageDecorateInfo(decorateInfoId,reqPRM,getApinMemberId(reqPRM, reqDataJson, request));
			map.put("pageName", pageName);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/api/n/getSignInCustomerPage")
	@ResponseBody
	public ResponseMsg getSignInCustomerPage(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			Integer decorateInfoId = null;
			CustomPageExample customPageExample = new CustomPageExample();
			customPageExample.createCriteria().andDelFlagEqualTo("0").andPageTypeEqualTo("5").andStatusEqualTo("1");
			customPageExample.setLimitStart(0);
			customPageExample.setLimitSize(1);
			customPageExample.setOrderByClause("id desc");
			List<CustomPage> customPages = customPageService.selectByExample(customPageExample);
			if(CollectionUtils.isNotEmpty(customPages)){
				decorateInfoId = customPages.get(0).getDecorateInfoId();
			}
			Map<String,Object> decorateInfoMap = decorateInfoService.getHomePageDecorateInfo(decorateInfoId,reqPRM,getMemberId(request));
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, decorateInfoMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	/**
	 *
	 * 方法描述 ：根据商品模块类目Id获取商品列表
	 * @author  chenwf:
	 * @date 创建时间：2018年2月28日 下午4:09:57
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getModuleItemListByProductModuleTypeId")
	@ResponseBody
	public ResponseMsg getModuleItemListByProductModuleTypeId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"decorateModuleId","productModuleTypeId"};
			this.requiredStr(obj,request);

			Integer decorateModuleId = reqDataJson.getInt("decorateModuleId");
			Integer productModuleTypeId = reqDataJson.getInt("productModuleTypeId");
			DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(decorateModuleId);
			List<Map<String, Object>> productBlockList = new ArrayList<Map<String, Object>>();
			productBlockList = decorateInfoService.getProductModuleList(productBlockList, decorateModuleId, decorateModule.getShowNum(),productModuleTypeId);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, productBlockList);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
}
