package com.jf.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.entity.AdInfo;
import com.jf.entity.AdInfoExample;
import com.jf.entity.IntegralType;
import com.jf.entity.MemberAccount;
import com.jf.entity.ProductPropValue;
import com.jf.entity.ProductPropValueExample;
import com.jf.entity.ProductType;
import com.jf.entity.SingleProductActivityCnf;
import com.jf.entity.SingleProductActivityCustom;
import com.jf.service.AdInfoService;
import com.jf.service.IntegralTypeService;
import com.jf.service.MemberAccountService;
import com.jf.service.OrderService;
import com.jf.service.ProductPropValueService;
import com.jf.service.SingleProductActivityCnfService;
import com.jf.service.SingleProductActivityService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 该控制层主要用来做单品活动接口开发
  * @author  chenwf: 
  * @date 创建时间：2017年9月29日 下午2:48:58 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class SingleProductActivityController extends BaseController{
	@Resource
	private SingleProductActivityService singleProductActivityService;
	@Resource
	private AdInfoService adInfoService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private ProductPropValueService productPropValueService;
	@Resource
	private IntegralTypeService integralTypeService;
	@Resource
	private SingleProductActivityCnfService singleProductActivityCnfService;
	/**
	 * 
	 * 方法描述 ：单品秒杀tab type = 3
	 * @author  chenwf: 
	 * @date 创建时间：2017年10月9日 下午4:53:55 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getSeckillTimeTab")
	@ResponseBody
	public ResponseMsg getSeckillTimeTab(HttpServletRequest request){
		try {
			Date currentDate = new Date();
			List<Map<String,Object>> topPicList = new ArrayList<Map<String,Object>>();
			Map<String,Object> dataMap = new HashMap<>();
			List<Map<String,Object>> list = singleProductActivityService.getSeckillTimeTab();
			//头部图
			QueryObject queryObject = new QueryObject();
	        queryObject.addQuery("status", Const.AD_STATUS_UP);
	        queryObject.addQuery("type", Const.AD_TYPE_1);
	        queryObject.addQuery("catalog", Const.AD_CATALOG_SECKILL);
	        queryObject.addQuery("position", Const.AD_POSITION_TOP);
	        queryObject.addQuery("autoUpDateLessThanOrEqual", currentDate);
	        queryObject.addQuery("sort", "auto_up_date desc");
			List<AdInfo> adInfoList = adInfoService.findListQuery(queryObject);
			if(CollectionUtils.isNotEmpty(adInfoList)){
				for (AdInfo adInfo : adInfoList) {
					if(topPicList.size() <= 5){
						Map<String,Object> adMap = adInfoService.getAdLocation(adInfo);
						topPicList.add(adMap);
					}else{
						break;
					}
				}
			}
			dataMap.put("topPic", topPicList);
			dataMap.put("data", list);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据加载失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：新版本单品秒杀 3
	 * @author  chenwf: 
	 * @date 创建时间：2018年4月18日 下午3:25:58 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getNewSeckillTimeList")
	@ResponseBody
	public ResponseMsg getNewSeckillTimeList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage","beginTime"};
			this.requiredStr(obj,request);
			List<Map<String,Object>> list = singleProductActivityService.getNewSeckillTimeList(reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,2,list);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据加载失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：单品爆款
	 * @author  chenwf: 
	 * @date 创建时间：2017年10月9日 下午4:55:15 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getSingleExplosionActivityList")
	@ResponseBody
	public ResponseMsg getSingleExplosionActivityList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"pageSize","currentPage","type"};
			this.requiredStr(obj,request);
			String type = reqDataJson.getString("type");
			//商品一级品类id
			String productTypeIdOne = reqDataJson.getString("productTypeIdOne");

			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("type", type);
			params.put("productTypeIdOne", productTypeIdOne);
			params.put("pageSize", pageSize);
			params.put("currentPage", currentPage*pageSize);
			List<Map<String,Object>> list = singleProductActivityService.getSingleExplosionActivityList(params);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,list);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据加载失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：单品 新用户专享
	 * @author  chenwf: 
	 * @date 创建时间：2017年10月9日 下午4:55:15 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getSingleNewEnjoyActivityList")
	@ResponseBody
	public ResponseMsg getSingleNewEnjoyActivityList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"pageSize","currentPage","type"};
			this.requiredStr(obj,request);
			String type = reqDataJson.getString("type");
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
			Date currentDate = new Date();
			if(currentPage == null){
				currentPage = 0;
			}
			if(pageSize == null){
				pageSize = 5;
			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("type", type);
			params.put("pageSize", pageSize);
			params.put("currentPage", currentPage*pageSize);
			List<Map<String,Object>> topPicList = new ArrayList<Map<String,Object>>();
			Map<String,Object> dataMap = new HashMap<>();
			List<Map<String,Object>> list = singleProductActivityService.getSingleNewEnjoyActivityList(params);
			//头部图
			QueryObject queryObject = new QueryObject();
	        queryObject.addQuery("status", Const.AD_STATUS_UP);
	        queryObject.addQuery("type", Const.AD_TYPE_1);
	        queryObject.addQuery("catalog", Const.AD_CATALOG_NEW_ENJOY);
	        queryObject.addQuery("position", Const.AD_POSITION_TOP);
	        queryObject.addQuery("autoUpDateLessThanOrEqual", currentDate);
	        queryObject.addQuery("sort", "auto_up_date desc");
			List<AdInfo> adInfoList = adInfoService.findListQuery(queryObject);
			if(CollectionUtils.isNotEmpty(adInfoList)){
				AdInfo adInfo = adInfoList.get(0);
				Map<String,Object> adMap = new HashMap<>();
				adMap.put("id", adInfo.getId());
				adMap.put("pic", StringUtil.getPic(adInfo.getPic(), ""));
				topPicList.add(adMap);
			}
			dataMap.put("adList", topPicList);
			dataMap.put("data", list);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据加载失败");
		}
	}
	
	
	/**
	 * 获取广告
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getAdInfos")
	@ResponseBody
	public ResponseMsg getAdInfos(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"catalog","position"};
			this.requiredStr(obj,request);
			String catalog=reqDataJson.getString("catalog");
			String position=reqDataJson.getString("position");
			
			Map<String, Object> dataMap=new HashMap<String, Object>();
			
			AdInfoExample adInfoExample=new AdInfoExample();
			adInfoExample.createCriteria().andDelFlagEqualTo("0").andCatalogEqualTo(catalog).andStatusEqualTo(Const.AD_STATUS_UP).andAutoUpDateLessThanOrEqualTo(new Date()).andPositionEqualTo(position);
			adInfoExample.setOrderByClause("auto_up_date desc");
			List<Map<String,Object>> topPicList = new ArrayList<Map<String,Object>>();
			List<AdInfo> adInfoList = adInfoService.selectByExample(adInfoExample);
			if(CollectionUtils.isNotEmpty(adInfoList)){
				for (AdInfo adInfo : adInfoList) {
					if(topPicList.size() <= 2){
						Map<String,Object> adMap = adInfoService.getAdLocation(adInfo);
						topPicList.add(adMap);
					}else{
						break;
					}
				}
			}
			dataMap.put("data", topPicList);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据加载失败");
		}
	}
	
	/**
	 * 新用户秒杀列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getNewUserSeckillTimeList")
	@ResponseBody
	public ResponseMsg getNewUserSeckillTimeList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"pageSize","currentPage"};
			this.requiredStr(obj,request);
			
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
			if(currentPage == null){
				currentPage = 0;
			}
			if(pageSize == null){
				pageSize = 5;
			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("limitStart", currentPage*pageSize);
			params.put("limitSize", pageSize);
			List<Map<String,Object>> list = singleProductActivityService.getNewUserSeckillTimeList(params);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,list);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据加载失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：新用户专享，固定内容区
	 * @author  chenwf: 
	 * @date 创建时间：2017年10月9日 下午4:55:15 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getSingleNewEnjoyActivityContent")
	@ResponseBody
	public ResponseMsg getSingleNewEnjoyActivityContent(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Map<String,Object> dataMap = new HashMap<>();
			Date currentDate = new Date();
			//头部图
	        AdInfoExample adInfoExample=new AdInfoExample();
	        adInfoExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo(Const.AD_STATUS_UP).andTypeEqualTo(Const.AD_TYPE_1).andCatalogEqualTo(Const.AD_CATALOG_NEW_ENJOY).andPositionEqualTo(Const.AD_POSITION_TOP).andAutoUpDateLessThanOrEqualTo(currentDate);
	        adInfoExample.setOrderByClause("auto_up_date desc");
			List<AdInfo> adInfoList = adInfoService.selectByExample(adInfoExample);
			List<String> topPicList = new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(adInfoList)){
				AdInfo adInfo = adInfoList.get(0);
				topPicList.add(StringUtil.getPic(adInfo.getPic(), ""));
			}
			dataMap.put("topPic", topPicList);
			dataMap.put("title", "-  新用户专享价  -");
			dataMap.put("summary", "每天20:00更新");
			
			//新用户秒杀专区内容
			JSONObject secKill=new JSONObject();
			adInfoExample=new AdInfoExample();
	        adInfoExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo(Const.AD_STATUS_UP).andTypeEqualTo(Const.AD_TYPE_1).andCatalogEqualTo("8").andPositionEqualTo(Const.AD_POSITION_TOP).andAutoUpDateLessThanOrEqualTo(currentDate);
	        adInfoExample.setOrderByClause("auto_up_date desc");
			List<AdInfo> adInfos=adInfoService.selectByExample(adInfoExample);
			if(adInfos!=null&&adInfos.size()>0){
				secKill.put("backgroundPic", StringUtil.getPic(adInfos.get(0).getPic(), ""));
			}else{
				secKill.put("backgroundPic", "");
			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("limitStart", 0);
			params.put("limitSize", 2);
			List<Map<String,Object>> list = singleProductActivityService.getNewUserSeckillTimeList(params);
			secKill.put("secKillProducts", list);
			dataMap.put("secKill", secKill);
//			System.out.println(dataMap);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据加载失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：积分商城
	 * @author  chenwf: 
	 * @date 创建时间：2017年10月9日 下午4:55:15 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getSingleIntegralMallActivityList")
	@ResponseBody
	public ResponseMsg getSingleIntegralMallActivityList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage","type"};
			this.requiredStr(obj,request);
			String type = "5";
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = reqDataJson.getInt("currentPage");//页码
			String memberId = getMemberIdStr(request);//页码
			Date currentDate = new Date();
			Map<String,Object> dataMap = new HashMap<>();
			//获取用户积分总数
			Integer integral = 0;
			if(!StringUtil.isBlank(memberId)){
				QueryObject memberAccountQuery = new QueryObject();
				memberAccountQuery.addQuery("memberId", Integer.valueOf(memberId));
				List<MemberAccount> memberAccounts = memberAccountService.findListQuery(memberAccountQuery);
				if(CollectionUtils.isNotEmpty(memberAccounts)){
					MemberAccount memberAccount = memberAccounts.get(0);
					integral = memberAccount.getIntegral();
				}else{
					memberId = "";
				}
			}
			if(currentPage == 0){
				//获取积分商城头部图
				List<Map<String,Object>> topPicList = new ArrayList<Map<String,Object>>();
				QueryObject queryObject = new QueryObject();
				queryObject.addQuery("status", Const.AD_STATUS_UP);
				queryObject.addQuery("type", Const.AD_TYPE_1);
				queryObject.addQuery("catalog", Const.AD_CATALOG_INTEGRAL_MALL);
				queryObject.addQuery("position", Const.AD_POSITION_TOP);
				queryObject.addQuery("autoUpDateLessThanOrEqual", currentDate);
				queryObject.addQuery("sort", "auto_up_date desc");
				List<AdInfo> adInfoList = adInfoService.findListQuery(queryObject);
				if(CollectionUtils.isNotEmpty(adInfoList)){
					AdInfo adInfo = adInfoList.get(0);
					Map<String,Object> adMap = adInfoService.getAdLocation(adInfo);
					topPicList.add(adMap);
				}
				dataMap.put("topPic", topPicList);
			}
			
			//获取积分商城商品列表
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("type", type);
			params.put("pageSize", pageSize);
			params.put("currentPage", currentPage*pageSize);
			
			List<Map<String,Object>> list = singleProductActivityService.getSingleIntegralMallActivityList(params,integral,memberId);
			//STORY #1583
			List<ProductType> productTypeList = singleProductActivityService.getProductTypeList();
			JSONArray productTypeJa = new JSONArray();
			if(productTypeList!=null && productTypeList.size()>0){
				JSONObject jo = new JSONObject();
				jo.put("productTypeId", "");
				jo.put("name", "推荐");
				productTypeJa.add(jo);
			}
			for(ProductType productType:productTypeList){
				JSONObject jo = new JSONObject();
				jo.put("productTypeId", productType.getId());
				jo.put("name", productType.getName());
				productTypeJa.add(jo);
			}
			dataMap.put("productTypeList", productTypeJa);
			dataMap.put("data", list);
			dataMap.put("integral", integral);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据加载失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：断码清仓
	 * @author  chenwf: 
	 * @date 创建时间：2018年1月25日 下午3:53:22 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getSingleBrokenCodeClearingActivityList")
	@ResponseBody
	public ResponseMsg getSingleBrokenCodeClearingActivityList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"type","currentPage"};
			this.requiredStr(obj,request);
			String productTypeIdTwo = reqDataJson.containsKey("productTypeIdTwo")?reqDataJson.getString("productTypeIdTwo"):null;
			String type = reqDataJson.getString("type");
			String propValId = reqDataJson.containsKey("propValId")?reqDataJson.getString("propValId"):null;
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
			String saleWeightSorf = reqDataJson.containsKey("saleWeightSorf")?reqDataJson.getString("saleWeightSorf"):null;
			String salePriceSorf = reqDataJson.containsKey("salePriceSorf")?reqDataJson.getString("salePriceSorf"):null;
			List<String> propValIdLists = new ArrayList<>();
			if(!StringUtil.isBlank(propValId)){
				//根据别名找出尺码id集合
				ProductPropValueExample productPropValueExample = new ProductPropValueExample();
				productPropValueExample.createCriteria().andDelFlagEqualTo("0").andAliasEqualTo(propValId);
				List<ProductPropValue> productPropValues = productPropValueService.selectByExample(productPropValueExample);
				if(CollectionUtils.isNotEmpty(productPropValues)){
					for (ProductPropValue productPropValue : productPropValues) {
						propValIdLists.add(productPropValue.getId().toString());
					}
				}
			}
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("propValIdLists", propValIdLists);
			if(!StringUtil.isEmpty(productTypeIdTwo)) {
				List<String> productTypeIdTwos = Arrays.asList(productTypeIdTwo.split(","));
				List<Integer> productTypeIdList = new ArrayList<>();
				for(String productTypeId : productTypeIdTwos) {
					productTypeIdList.add(Integer.parseInt(productTypeId));
				}
				paramsMap.put("productTypeIdList", productTypeIdList);
			}
			paramsMap.put("type", type);
			paramsMap.put("pageSize", pageSize);
			paramsMap.put("currentPage", currentPage*pageSize);
			paramsMap.put("saleWeightSorf", saleWeightSorf);
			paramsMap.put("salePriceSorf", salePriceSorf);
			List<Map<String,Object>> list = singleProductActivityService.getSingleBrokenCodeClearingActivityList(paramsMap);
			Map<String,Object> dataMap = new HashMap<>();
			dataMap.put("list", list);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据加载失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取品牌馆商品集合
	 * @author  chenwf: 
	 * @date 创建时间：2018年4月18日 下午9:14:04 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getBrandGroupProductList")
	@ResponseBody
	public ResponseMsg getBrandGroupProductList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"brandGroupId","currentPage"};
			this.requiredStr(obj,request);
			Map<String,Object> dataMap = singleProductActivityService.getBrandGroupProductList(reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,Const.RETURN_SIZE_10, dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据加载失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取顶级秒杀商品数据列表
	 * @author  chenwf: 
	 * @date 创建时间：2018年7月19日 上午11:14:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getTopSecKillProductList")
	@ResponseBody
	public ResponseMsg getTopSecKillProductList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"beginTime","currentPage"};
			this.requiredStr(obj,request);
			long beginTime = reqDataJson.getLong("beginTime");
			String seckillType = "2";
			if(reqDataJson.get("seckillType") != null) {
				seckillType = reqDataJson.getString("seckillType");
			}
			Integer pageSize = Const.RETURN_SIZE_10;
			Integer currentPage = reqDataJson.getInt("currentPage");
			List<Map<String, Object>> productInfoList = singleProductActivityService.getTopSecKillProductList(beginTime,currentPage,pageSize,seckillType);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("productInfoList", productInfoList);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize, map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据加载失败");
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：积分商城商品列表（根据一级分类id获取）
	 * @author  yjc
	 * @date 创建时间：2019年8月22日15:28:18
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getSingleProductActivityCustomsByProductTypeId")
	@ResponseBody
	public ResponseMsg getSingleProductActivityCustomsByProductTypeId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"currentPage","type"};
			this.requiredStr(obj,request);
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = reqDataJson.getInt("currentPage");//页码
			String memberId = "";
			if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			//获取用户积分总数
			Integer integral = 0;
			if(!StringUtil.isBlank(memberId)){
				QueryObject memberAccountQuery = new QueryObject();
				memberAccountQuery.addQuery("memberId", Integer.valueOf(memberId));
				List<MemberAccount> memberAccounts = memberAccountService.findListQuery(memberAccountQuery);
				if(CollectionUtils.isNotEmpty(memberAccounts)){
					MemberAccount memberAccount = memberAccounts.get(0);
					integral = memberAccount.getIntegral();
				}else{
					memberId = "";
				}
			}
			//获取积分商城商品列表
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("type", reqDataJson.getString("type"));//5
			params.put("productTypeId", reqDataJson.getString("productTypeId"));
			params.put("pageSize", pageSize);
			params.put("currentPage", currentPage*pageSize);
			Map<String,Object> dataMap = new HashMap<>();
			List<SingleProductActivityCustom> singleProductActivityCustoms = singleProductActivityService.getSingleProductActivityCustomsByProductTypeId(params);
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivityCustoms) {
				Map<String,Object> map = new HashMap<String,Object>();
				Integer productId = singleProductActivityCustom.getProductId();
				String productPic = singleProductActivityCustom.getProductPic();
				String productName = singleProductActivityCustom.getProductName();
				Integer stockSum = singleProductActivityCustom.getStockSum();
				BigDecimal salePrice = singleProductActivityCustom.getSalePrice();
				BigDecimal tagPrice = singleProductActivityCustom.getTagPrice();
				String deductibleAmountContext = "";
				Integer deductibleIntegral = 0;//可抵扣积分
				double deductibleAmount = 0.00;//可抵扣金额
				BigDecimal deductibleSalePrice = new BigDecimal("0");//抵后价
				DecimalFormat df = new DecimalFormat();
				df.applyPattern("#.##");
				if(StringUtil.isBlank(memberId)){
					deductibleAmountContext = "请登入查看可抵扣积分额度";
					deductibleSalePrice = new BigDecimal("0.01");
				}else{
					//积分金额换算比率
					QueryObject integralTypeQuery = new QueryObject();
					integralTypeQuery.addQuery("id", Integer.valueOf(Const.INTEGRAL_TYPE_ORDER_PAYMENT));
					integralTypeQuery.addQuery("status", "1");
					List<IntegralType> integralTypes = integralTypeService.findListQuery(integralTypeQuery);
					Integer iCharge = 0;
					if(CollectionUtil.isNotEmpty(integralTypes)){
						IntegralType integralType = integralTypes.get(0);
						if(integralType.getIntType().equals("2")){
							iCharge = integralType.getiCharge() == null ? 0 : integralType.getiCharge();
						}
					}
					//新用户秒杀 限购配置查询
					QueryObject activityCnfQuery = new QueryObject();
					activityCnfQuery.addQuery("activityType", "5");
					List<SingleProductActivityCnf> singleProductActivityCnfs = singleProductActivityCnfService.findListQuery(activityCnfQuery);
					BigDecimal integralExchangeRate = null;//积分兑换比率
					if(CollectionUtils.isNotEmpty(singleProductActivityCnfs)){
						SingleProductActivityCnf singleProductActivityCnf = singleProductActivityCnfs.get(0);
						integralExchangeRate = singleProductActivityCnf.getIntegralExchangeRate();
					}else{
						integralExchangeRate = new BigDecimal(OrderService.INTEGERAL_DISOUNT);
					}
					if(iCharge != 0){
						//计算可以抵扣多少积分  销售价*积分兑换比率
						deductibleIntegral = salePrice.multiply(new BigDecimal(iCharge)).intValue();
						//如果 可抵扣积分 >= 用户本身拥有的积分，就使用所有的积分
						//可使用的积分 = 可用积分 * 最高积分兑换比例
						Integer exchangeIntegral = (int) (deductibleIntegral * integralExchangeRate.doubleValue());
						if(exchangeIntegral > integral){
							exchangeIntegral = integral;
						}
						//可抵扣金额 = 最高积分兑换比例 / 积分兑换比率
						//换算成金额
						deductibleAmount = exchangeIntegral / (double)iCharge;
						if(salePrice.doubleValue() <= deductibleAmount){
							deductibleSalePrice = new BigDecimal("0.01");
							//BUG #11415
							deductibleAmountContext = "需抵扣"+df.format((new BigDecimal(deductibleAmount).subtract(deductibleSalePrice)).multiply(new BigDecimal(100)))+"积分";
						}else{
							deductibleSalePrice = salePrice.subtract(new BigDecimal(deductibleAmount)).setScale(2, BigDecimal.ROUND_HALF_UP);
							if(deductibleAmount < 0){
								deductibleAmountContext = "";
							}else{
								BigDecimal deductionIntegral = new BigDecimal(deductibleAmount).multiply(new BigDecimal(100));
								df = new DecimalFormat();
								df.applyPattern("#");
								deductibleAmountContext = "需抵扣"+df.format(deductionIntegral)+"积分";
							}
						}
					}
				}
				map.put("productId", productId);
				map.put("productPic", StringUtil.getPic(productPic, "M"));
				map.put("productName", productName);
				if(!StringUtil.isBlank(memberId)){
					map.put("salePrice", deductibleSalePrice);//BUG #11415
				}else{
					map.put("salePrice", df.format(salePrice));//BUG #11415
				}
				map.put("tagPrice", df.format(salePrice));//BUG #11500
				map.put("type", "5");
				map.put("stockSum", stockSum);
				map.put("deductibleSalePrice", deductibleSalePrice);
				map.put("deductibleAmountContext", deductibleAmountContext);
				list.add(map);
			}
			dataMap.put("data", list);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据加载失败");
		}
	}
}
