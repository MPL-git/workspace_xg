package com.jf.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.dao.ActivityAreaModuleMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAreaModule;
import com.jf.entity.ActivityAreaModuleExample;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ActivityGroup;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.IntegralDtl;
import com.jf.entity.IntegralDtlExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponExample;
import com.jf.entity.ProductPropValue;
import com.jf.entity.ProductPropValueExample;
import com.jf.entity.SysParamCfg;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityBrandGroupService;
import com.jf.service.ActivityGroupService;
import com.jf.service.ActivityService;
import com.jf.service.CouponService;
import com.jf.service.FullCutService;
import com.jf.service.FullDiscountService;
import com.jf.service.FullGiveService;
import com.jf.service.IntegralDtlService;
import com.jf.service.MemberAccountService;
import com.jf.service.MemberCouponService;
import com.jf.service.ProductItemService;
import com.jf.service.ProductPropValueService;
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月25日 下午3:32:13 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class ActivityController extends BaseController{
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private ActivityAreaService activityAreaService;
	
	@Resource
	private CouponService couponService;
	
	@Resource
	private FullGiveService fullGiveService;
	
	@Resource
	private FullCutService fullCutService;
	
	@Resource
	private FullDiscountService fullDiscountService;
	
	@Resource
	private ProductItemService productItemService;
	
	@Resource
	private MemberCouponService memberCouponService;
	
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private ProductService productService;
	@Resource
	private ActivityGroupService activityGroupService;
	@Resource
	private ActivityBrandGroupService activityBrandGroupService;
	@Resource
	private ProductPropValueService productPropValueService;
	@Resource
	private IntegralDtlService integralDtlService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private ActivityAreaModuleMapper activityAreaModuleMapper;
	
	/**
	 * 
	 * 方法描述 ：获取会场优惠券列表
	 * @author  chenwf: 
	 * @date 创建时间：2019年05月10日 上午10:16:16 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getActivityAreaCoupons")
	@ResponseBody
	public ResponseMsg getActivityAreaCouponList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			String memberId = "";
			Integer activityAreaId = null;
			Integer activityId = null;
			if(reqDataJson.containsKey("memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			if(reqDataJson.containsKey("activityAreaId") && !StringUtil.isBlank(reqDataJson.getString("activityAreaId"))){
				activityAreaId = reqDataJson.getInt("activityAreaId");
			}else if(reqDataJson.containsKey("activityId") && !StringUtil.isBlank(reqDataJson.getString("activityId"))){
				activityId = reqDataJson.getInt("activityId");
				activityAreaId = activityService.selectByPrimaryKey(activityId).getActivityAreaId();
			}else{
				throw new ArgException("系统出错");
			}
			
			List<Map<String, Object>> couponList = couponService.getActivityAreaCoupons(activityAreaId,memberId);
			Map<String, Object> map = new HashMap<>();
			map.put("couponList", couponList);
			map.put("activityAreaId", activityAreaId);
			map.put("currentDate", new Date().getTime());
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取品牌团商品列表
	 * @author  chenwf: 
	 * @date 创建时间：2019年04月25日 下午2:17:05 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getCategoryBrandGroup")
	@ResponseBody
	public ResponseMsg getCategoryBrandGroup(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Integer pageSize = Const.RETURN_SIZE_10;
			Map<String,Object> map = activityService.getCategoryBrandGroup(reqPRM,reqDataJson,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取会场模板
	 * @author  chenwf: 
	 * @date 创建时间：2018年1月19日 下午2:17:05 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getActivityAreaTemplateByAreaId")
	@ResponseBody
	public ResponseMsg getActivityAreaTemplateByAreaId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"activityAreaId"};
			this.requiredStr(obj,request);
			//品类id
			Integer activityAreaId = reqDataJson.getInt("activityAreaId");
			String areaUrl = "";
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
			if(activityArea != null && !StringUtil.isBlank(activityArea.getTempletSuffix())){
				areaUrl = Config.getProperty("mUrl")+activityArea.getTempletSuffix()+"?activityAreaId="+activityArea.getId()+"&currentPage=0&pageSize=10&memberId=";
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("areaUrl", areaUrl);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：获取今日特惠活动列表
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月22日 下午5:11:23 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getTodayPreferentialActivityList20180822")
	@ResponseBody
	public ResponseMsg getTodayPreferentialActivityList20180822(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			//品类id
			String productTypeId = reqDataJson.getString("productTypeId");
			List<Map<String,Object>> activityList = new ArrayList<>();
			Map<String, Object> brandGroupMap = new HashMap<String, Object>();
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
			String memberId = "";
			String adCatalog = "";
			String adCatalogName = "";
			if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			if(!StringUtil.isBlank(productTypeId) && !productTypeId.equals("1") && !productTypeId.equals("0")){
				SysParamCfg cfg = DataDicUtil.getSysParamCfgModel("APP_HOME_TOP_CATALOG", productTypeId);
				if(cfg == null){
					throw new ArgException("系统异常");
				}
				adCatalog = cfg.getParamOrder();
				adCatalogName = cfg.getParamName();
			}else{
				productTypeId = "";
				adCatalog = "1";
			}
			
			String isNewVersion = "1";
			activityList = activityService.getTodayPreferentialActivityList(productTypeId,adCatalog,pageSize,currentPage,memberId,isNewVersion);
			brandGroupMap = new HashMap<String, Object>();
			if(!StringUtil.isBlank(productTypeId)){
				Integer version = reqPRM.getInt("version");
				String system = reqPRM.getString("system");
				if(system.equals(Const.IOS) && version < 46){
					brandGroupMap = activityBrandGroupService.getTodayPreferentialActivityBrandGroup(productTypeId);
				}else{
					if(currentPage == 0){
						brandGroupMap = activityBrandGroupService.getTodayPreferentialActivityBrandGroup(productTypeId);
					}
				}
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("activityList", activityList);
			map.put("brandGroupMap", brandGroupMap);
			map.put("title", adCatalogName);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：获取今日特惠品牌团活动列表
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月22日 下午5:11:23 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getActivityBrandGroupActivityList")
	@ResponseBody
	public ResponseMsg getActivityBrandGroupActivityList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage","activityBrandGroupId"};
			this.requiredStr(obj,request);
			Integer activityBrandGroupId = reqDataJson.getInt("activityBrandGroupId");
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = reqDataJson.getInt("currentPage");//页码
			Map<String,Object> dataMap = activityService.getActivityBrandGroupActivityList(activityBrandGroupId,currentPage,pageSize);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：获取预告列表
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月12日 上午10:13:00 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getActivityTrailerList")
	@ResponseBody
	public ResponseMsg getActivityTrailerList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			
			String memberId = "";
			if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			String activityTime = reqDataJson.getString("activityTime");//开售时间
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
			if(currentPage == null){
				currentPage = 0;
			}
			if(pageSize == null){
				pageSize = 5;
			}
			if(reqPRM.containsKey("version") && reqPRM.containsKey("system")){
				Integer version = reqPRM.getInt("version");
				String system = reqPRM.getString("system");
				if((version == 18 || version == 19) && system.equals(Const.ANDROID)){
					pageSize = 100;
				}
			}
			String activityBeginTime = "";
			String activityEndTime = "";
			String year = DateUtil.getFormatDate(new Date(), "yyyy");
			activityTime = activityTime.replace("月", "-").replace("日", "");
			//activityTime 11月11日
			if(!StringUtil.isBlank(activityTime)){
				activityBeginTime = year+"-"+activityTime+" 00:00:00";
				activityEndTime = year+"-"+activityTime+" 23:59:59";
			}
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("activityBeginTime", activityBeginTime);
			params.put("activityEndTime", activityEndTime);
			params.put("pageSize", pageSize);
			params.put("currentPage", currentPage*pageSize);
			//当天
			List<ActivityCustom> activityCustoms = activityService.getActivityPreheatList(params);
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			Map<String,Object> dataMap = new HashMap<String,Object>();
			if(activityCustoms !=null && activityCustoms.size() > 0){
				for (ActivityCustom activityCustom : activityCustoms) {
					String areaUrl = "";
					Map<String,Object> data = new HashMap<String,Object>();
					data.put("posterPic", FileUtil.getImageServiceUrl()+activityCustom.getPosterPic());
					//活动id
					data.put("activityId", activityCustom.getId());
					//会场id
					data.put("activityAreaId", activityCustom.getActivityAreaId());
					data.put("benefitPoint", activityCustom.getBenefitPoint() == null ? "" : activityCustom.getBenefitPoint());
					data.put("activityBeginTime", activityCustom.getActivityBeginTime().getTime()/1000);
					//source会场形式(1会场 2品牌特卖)
					data.put("source", activityCustom.getSource());
					if(activityCustom.getSource().equals("1")){
						String code = "";
						String logo = "";
						if(activityCustom.getType().equals("1")){
							code = "APP_ACTIVITY_BRAND";
						}else{
							code = "APP_ACTIVITY_SINGLE_PRODUCT";
						}
						List<SysParamCfg> sysParamCfgs = DataDicUtil.getSysParamCfg(code);
						if(CollectionUtils.isNotEmpty(sysParamCfgs)){
							logo = sysParamCfgs.get(0).getParamValue();
						}
						data.put("logo", FileUtil.getImageServiceUrl()+logo);
						data.put("entryPic", FileUtil.getImageServiceUrl()+activityCustom.getAreaEntryPic());
						if(!StringUtil.isBlank(activityCustom.getTempletSuffix())){
							areaUrl = Config.getProperty("mUrl")+activityCustom.getTempletSuffix()+"?activityAreaId="+activityCustom.getActivityAreaId()+"&currentPage=0&pageSize=10&memberId=";
						}
					}else{
						data.put("entryPic", FileUtil.getImageServiceUrl()+activityCustom.getEntryPic());
						data.put("logo", FileUtil.getImageServiceUrl()+activityCustom.getLogo());
					}
					data.put("name", activityCustom.getActivityAreaName());
					data.put("areaUrl", areaUrl);
					if(StringUtil.isBlank(memberId)){
						//开售提醒始终显示灰色
						data.put("isExist", false);
					}else{
						//判断是否添加开售提醒 true 存在  false 不存在
						boolean isExist = DataDicUtil.getRemindExists(activityCustom.getActivityAreaId(),Integer.valueOf(memberId),"1");
						data.put("isExist", isExist);
					}
					returnData.add(data);
				}
			}
			dataMap.put("Rows", returnData);
			
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：根据活动id获取商品集合
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月17日 下午3:45:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getActivityProductList")
	@ResponseBody
	public ResponseMsg getActivityProductList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage","activityId"};
			this.requiredStr(obj,request);
			Integer version = reqPRM.getInt("version");
			String system = reqPRM.getString("system");
			//会员id
			String memberId = "";
			if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			//活动id
			Integer activityId = reqDataJson.getInt("activityId");
			//适合性别  00 都不选 11 都选  10 选男不选女   01 选女不选男
			String suitSex = reqDataJson.getString("suitSex");
			if(!StringUtil.isBlank(suitSex)){
				if(suitSex.equals("10")){//男
					suitSex = "1%";
				}else if(suitSex.equals("01")){//女
					suitSex = "%1";
				}else{//男女
					suitSex = "";
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
			//售价排序类型 DESC 降序  ASC
			String salePriceSort = reqDataJson.getString("salePriceSort");
			//折扣排序类型
			String discountSort = reqDataJson.getString("discountSort");
			//库存>0 标识  T>0
			String stockMark = reqDataJson.getString("stockMark");
			String alias = "";
			if(reqDataJson.containsKey("alias") && !StringUtil.isBlank(reqDataJson.getString("alias"))){
				alias = reqDataJson.getString("alias");
			}
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
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
			Map<String,Object> params = new HashMap<String,Object>();
			BigDecimal salePriceMaxNew = new BigDecimal(0);
			BigDecimal salePriceMinNew = new BigDecimal(0);
			if(!StringUtil.isBlank(salePriceMax)){
				salePriceMaxNew = new BigDecimal(salePriceMax);
			}
			if(!StringUtil.isBlank(salePriceMin)){
				salePriceMinNew = new BigDecimal(salePriceMin);
			}
			//根据别名查找尺码id
			List<String> propValueIdList = new ArrayList<String>();
			if(!StringUtil.isBlank(alias)){
				ProductPropValueExample productPropValueExample = new ProductPropValueExample();
				productPropValueExample.createCriteria().andAliasEqualTo(alias).andPropIdEqualTo(2).andDelFlagEqualTo("0");
				List<ProductPropValue> productPropValues = productPropValueService.selectByExample(productPropValueExample);
				if(CollectionUtils.isNotEmpty(productPropValues)){
					for (ProductPropValue productPropValue : productPropValues) {
						propValueIdList.add(productPropValue.getId().toString());
					}
				}
			}
			params.put("propValueIdList", propValueIdList);
			params.put("alias", alias);
			params.put("activityId", activityId);
			params.put("suitSex", suitSex);
			params.put("suitGroup", suitGroup);
			params.put("productTypeIdList", productTypeIdList);
			params.put("salePriceMin", salePriceMinNew);
			params.put("salePriceMax", salePriceMaxNew);
			params.put("salePriceSort", salePriceSort);
			params.put("discountSort", discountSort);
			params.put("stockMark", stockMark);
			params.put("pageSize", pageSize);
			params.put("currentPage", currentPage*pageSize);
			Map<String,Object> returnData = new HashMap<String,Object>();
			List<Map<String,Object>> productInfoList = new ArrayList<Map<String,Object>>();
			List<ActivityCustom> activityCustoms = activityService.getProductByActiviTyId(params);
			
			if(activityCustoms != null && activityCustoms.size() > 0){
				for (ActivityCustom activityCustom : activityCustoms) {
					Map<String,Object> productMap = new HashMap<String,Object>();
					String pic = activityCustom.getProductPic();
					BigDecimal salePrice = new BigDecimal(activityCustom.getSalePrice().toString());
					BigDecimal svipDiscount = activityCustom.getSvipDiscount();
					BigDecimal svipSalePrice = productService.getProductSvipSalePrice(salePrice,svipDiscount,Const.PRODUCT_ACTIVITY_TYPE_AREA);
					if(activityCustom.getIsWatermark().equals("1")){
						pic = StringUtil.getActivityMkPic(pic, "M_WM", activityCustom.getActivityAreaId());
					}else{
						pic = StringUtil.getPic(pic, "M");
					}
					BigDecimal arrivalPrice = new BigDecimal("0");
					BigDecimal deductAmount = new BigDecimal("0");
					BigDecimal deposit = new BigDecimal("0");
					if(activityCustom.getActivityEndTime().after(new Date()) && activityCustom.getDeductAmount()!=null && activityCustom.getDeposit()!=null){
						deductAmount = activityCustom.getDeductAmount();
						deposit = activityCustom.getDeposit();
						arrivalPrice = salePrice.subtract(deductAmount).add(deposit);
					}
					if(activityCustom.getMaxSalePrice().compareTo(salePrice) == 0){
						productMap.put("hasDifferentPrice", "");
					}else{
						productMap.put("hasDifferentPrice", "起");
					}
					productMap.put("arrivalPrice", arrivalPrice);
					productMap.put("deductAmount", deductAmount);
					productMap.put("deposit", deposit);
					productMap.put("productName", activityCustom.getProductName());
					productMap.put("salePrice", salePrice);
					productMap.put("tagPrice", activityCustom.getTagPrice());
					productMap.put("discount", activityCustom.getDiscount());
					productMap.put("activityAreaId", activityCustom.getActivityAreaId());
					productMap.put("activityId", activityCustom.getId());
					productMap.put("productId", activityCustom.getProductId());
					productMap.put("manageSelf", activityCustom.getIsManageSelf()); //是否自营
					Integer stockSum = activityCustom.getStockSum();
					if(stockSum<0){
						stockSum = 0;
					}
					productMap.put("stock", stockSum);
					productMap.put("pic", pic);
					productMap.put("svipSalePrice", svipSalePrice);
					productInfoList.add(productMap);
				}
			}
			ActivityCustom activityCustom = activityService.getActivityInfoById(activityId);
			if(activityCustom == null){
				return new ResponseMsg(ResponseMsg.ERROR_9999,"来晚了哦！活动已结束");
			}
			//商品彩标图
			String productWkPicM = "";
			String productWkPosition = "";//商品彩标位置1 左上 2 右上 3 右下 4 左下 
			Integer activityGroupId = activityCustom.getActivityGroupId();
			if("1".equals(activityCustom.getSource())){
				activityGroupId = activityCustom.getAreaGroupId();
			}
			if(activityGroupId != null){
				ActivityGroup activityGroup = activityGroupService.getActivityGroupModelByGroupId(activityGroupId);
				if(activityGroup != null){
					productWkPicM = activityGroup.getProductWkPicM();
					productWkPosition = activityGroup.getProductWkPosition();
					if(StringUtil.isBlank(productWkPosition)){
						productWkPosition = "2";
					}
					
				}
			}
			//开售提醒是否点亮
			boolean isExist = false;
			Integer activityAreaId = activityCustom.getActivityAreaId();
			if(!StringUtil.isBlank(memberId) && activityAreaId != null){
				//开售提醒始终显示灰色
				//判断是否添加开售提醒 true 存在  false 不存在
				isExist = DataDicUtil.getRemindExists(activityAreaId,Integer.valueOf(memberId),"1");
			}
			Date activityBeginDate = activityCustom.getActivityBeginTime();
			Date activityEndDate = activityCustom.getActivityEndTime();
			if(activityBeginDate == null || activityEndDate == null){
				activityBeginDate = DateUtil.addHour(new Date(), -2);
				activityEndDate = DateUtil.addHour(new Date(), -1);
			}
			returnData.put("productWkPicM", StringUtil.getPic(productWkPicM, ""));
			returnData.put("productWkPosition", productWkPosition);
			returnData.put("isExist", isExist);
			returnData.put("productInfoList", productInfoList);
			returnData.put("benefitPoint", activityCustom.getBenefitPoint());
			returnData.put("posterPic", FileUtil.getImageServiceUrl()+activityCustom.getPosterPic());
			returnData.put("activityBeginDate", activityBeginDate.getTime()/1000);
			returnData.put("activityEndDate", activityEndDate.getTime()/1000);
			returnData.put("activityAreaId", activityAreaId);
			returnData.put("currentTime", new Date().getTime()/1000);
			returnData.put("activityName", activityCustom.getName());
			if((system.equals(Const.ANDROID) && version <= 54) || (system.equals(Const.IOS) && version <= 56)){
				List<Map<String,Object>> couponList = new ArrayList<Map<String,Object>>();
				String preferentialType = activityCustom.getPreferentialType();
				String preferentialIdGroup = "";
				//获取优惠券集合
				if(!StringUtil.isBlank(preferentialType) && preferentialType.equals("1")){
					preferentialIdGroup = activityCustom.getPreferentialIdGroup();
				}
				//优惠券整合
				Date date = new Date();
				if(!StringUtil.isBlank(preferentialIdGroup)){
					String[] groups = preferentialIdGroup.split(",");
					List<Integer> groupList = new ArrayList<Integer>();
					for (String str : groups) {
						groupList.add(Integer.valueOf(str));
					}
					CouponExample couponExample = new CouponExample();
					if(preferentialType.equals("1")){
						couponExample.createCriteria().andActivityAreaIdEqualTo(activityCustom.getActivityAreaId())
								.andIdIn(groupList).andDelFlagEqualTo("0").andRecBeginDateLessThanOrEqualTo(date)
								.andRecEndDateGreaterThanOrEqualTo(date).andStatusEqualTo("1").andRecTypeNotEqualTo("3");
						List<Coupon> coupons = couponService.selectByExample(couponExample);
						if(coupons != null && coupons.size() > 0){
							for (Coupon coupon : coupons) {
								//优惠券发行数量
								Integer grantQuantity = coupon.getGrantQuantity() == null ? 0 : coupon.getGrantQuantity();
								//优惠券已领数量
								Integer recQuantity = coupon.getRecQuantity() == null ? 0 : coupon.getRecQuantity();
								//优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
								String recLimitType = coupon.getRecLimitType() == null ? "" : coupon.getRecLimitType();
								//优惠券 限领数量
								Integer recEach = coupon.getRecEach() == null ? 0 : coupon.getRecEach();
								if(grantQuantity > 0 && grantQuantity > recQuantity){
									Map<String,Object> couponMap = new HashMap<String,Object>();
									couponMap.put("couponId", coupon.getId());
									couponMap.put("money", coupon.getMoney());
									couponMap.put("minimum", coupon.getMinimum());
									couponMap.put("recType", coupon.getRecType());
									couponMap.put("needIntegral", coupon.getNeedIntegral() == null ? 0 : coupon.getNeedIntegral());
									couponMap.put("recLimitType", recLimitType);
									couponMap.put("recEach", recEach);
									if(StringUtil.isBlank(memberId)){
										couponMap.put("isHasCoupon", false);
									}else{
										MemberCouponExample memberCouponExample = new MemberCouponExample();
										memberCouponExample.createCriteria().andMemberIdEqualTo(Integer.valueOf(memberId))
										.andCouponIdEqualTo(coupon.getId()).andDelFlagEqualTo("0");
										memberCouponExample.setOrderByClause("rec_date desc");
										List<MemberCoupon> memberCoupons = memberCouponService.selectByExample(memberCouponExample);
										if(CollectionUtils.isNotEmpty(memberCoupons)){
											String recDate = DateUtil.getFormatDate(memberCoupons.get(0).getRecDate(), "yyyyMMdd");
											String currentDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
											couponMap.put("memberCouponCount", memberCoupons.size());
											if(recLimitType.equals("1")){
												if(recDate.equals(currentDate)){
													couponMap.put("isHasCoupon", true);
												}else{
													couponMap.put("isHasCoupon", false);
												}
											}else if(recLimitType.equals("2")){
												if(memberCoupons.size() < recEach){
													couponMap.put("isHasCoupon", false);
												}else{
													couponMap.put("isHasCoupon", true);
												}
											}else if(recLimitType.equals("3")){
												couponMap.put("isHasCoupon", false);
											}else{
												couponMap.put("isHasCoupon", true);
											}
										}else{
											couponMap.put("isHasCoupon", false);
											couponMap.put("memberCouponCount", 0);
										}
									}
									couponList.add(couponMap);
								}
							}
						}
					}
				}
				returnData.put("couponList", couponList);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	
	
	/////////////////////////////////////以下功能均已废弃////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 
	 * 方法描述 ：获取今日特惠活动列表（废弃20180222，做特卖品牌团兼容不了新版本需求）
	 * @author  chenwf: 
	 * @date 创建时间：2017年4月22日 下午5:11:23 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getTodayPreferentialActivityList")
	@ResponseBody
	public ResponseMsg getTodayPreferentialActivityList(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"currentPage"};
			this.requiredStr(obj,request);
			//品类id
			String productTypeId = reqDataJson.getString("productTypeId");
			//广告类目(1 首页 2 运动 3 鞋包 4 服饰 5 生活)
			String adCatalog = "";
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
			String memberId = "";
			if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			if(currentPage == null){
				currentPage = 0;
			}
			if(pageSize == null){
				pageSize = 5;
			}
			if(reqDataJson.containsKey("adCatalog")){
				adCatalog = reqDataJson.getString("adCatalog");
			}
			if(StringUtil.isBlank(adCatalog)){
				SysParamCfg cfg = DataDicUtil.getSysParamCfgModel("APP_HOME_TOP_CATALOG", productTypeId);
				if(cfg != null){
					adCatalog = cfg.getParamOrder();
				}
			}
			String isNewVersion = "0";
			List<Map<String,Object>> returnData = activityService.getTodayPreferentialActivityList(productTypeId,adCatalog,pageSize,currentPage,memberId,isNewVersion);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,pageSize,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：根据会场id获取分享得积分的描述详情//STORY #1708
	 * @author  yjc: 
	 * @date 创建时间：2019年9月27日16:05:19 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getIntegralDescByActivityAreaId")
	@ResponseBody
	public ResponseMsg getIntegralDescByActivityAreaId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"activityAreaId"};
			this.requiredStr(obj,request);
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(reqDataJson.getString("activityAreaId")));
			String shareIntegralTypeDesc = "";
			String getIntegralMethodDesc = "";
			String shareIntegralType = activityArea.getShareIntegralType();
			if("1".equals(shareIntegralType)){//0.无积分1.首次得积分2.每日首次分享得积分
				shareIntegralTypeDesc = "分享得积分";
				getIntegralMethodDesc = "首次分享可获得积分";
			}else if("2".equals(shareIntegralType)){
				shareIntegralTypeDesc = "分享得积分";
				getIntegralMethodDesc = "活动期间每天首次分享可获得积分";
			}
			String areaUrl = "";
			if(activityArea != null && !StringUtil.isBlank(activityArea.getTempletSuffix())){
				areaUrl = Config.getProperty("mUrl")+activityArea.getTempletSuffix()+"?activityAreaId="+activityArea.getId()+"&currentPage=0&pageSize=10&memberId=";
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("shareIntegralType", shareIntegralType);
			dataMap.put("shareIntegralTypeDesc", shareIntegralTypeDesc);
			dataMap.put("getIntegralMethodDesc", getIntegralMethodDesc);
			dataMap.put("areaUrl", areaUrl);
			InputStream stream = ActivityController.class.getResourceAsStream("/config.properties");
	        try {
	        	Properties properties = new Properties();
	        	properties.load(stream);
	        	String originalId = properties.getProperty("originalId");
	        	String xcxShareType = properties.getProperty("xcxShareType");
	        	dataMap.put("originalId", originalId);//小程序原始ID
	        	dataMap.put("xcxShareType", xcxShareType);//分享小程序的版本（0-正式，1-开发，2-体验）
			} catch (Exception e) {
				e.printStackTrace();
			}
	        dataMap.put("wxPath", "tpl/decorate/index?activityAreaId="+activityArea.getId());//分享小程序页面的具体路径
	        dataMap.put("activityShareDesc",  activityArea.getActivityShareDesc());
	        dataMap.put("activitySharePic",  StringUtil.getPic(activityArea.getActivitySharePic(), ""));
	        dataMap.put("name",  activityArea.getName());
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：分享会场送积分//STORY #1708
	 * @author  yjc: 
	 * @date 创建时间：2019年9月27日16:05:19 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/giveIntegralByShareActivityArea")
	@ResponseBody
	public ResponseMsg giveIntegralByShareActivityArea(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"activityAreaId","memberId"};
			this.requiredStr(obj,request);
			Integer memberId = Integer.parseInt(reqDataJson.getString("memberId"));
			Integer activityAreaId = Integer.parseInt(reqDataJson.getString("activityAreaId"));
			IntegralDtl integralDtl = new IntegralDtl();
			MemberAccountExample e = new MemberAccountExample();
			e.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0").andIsEffectEqualTo("1");
			List<MemberAccount> memberAccounts = memberAccountService.selectByExample(e);
			if(memberAccounts!=null && memberAccounts.size()>0){
				int memberAccountId = memberAccounts.get(0).getId();
				IntegralDtlExample example = new IntegralDtlExample();
				example.setOrderByClause("id desc");
				IntegralDtlExample.Criteria criteria = example.createCriteria();
				criteria.andDelFlagEqualTo("0");
				criteria.andAccIdEqualTo(memberAccountId);
				criteria.andTallyModeEqualTo("1");//1.进账
				criteria.andTypeEqualTo(19);//19.分享会场送积分
				criteria.andBizTypeEqualTo("11");
				criteria.andOrderIdEqualTo(activityAreaId);
				criteria.andCreateByEqualTo(memberId);
				List<IntegralDtl> integralDtlList = integralDtlService.selectByExample(example);
				if(integralDtlList!=null && integralDtlList.size()>0){
					integralDtl = integralDtlList.get(0);
				}
			}
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(reqDataJson.getString("activityAreaId")));
			Integer integral = 0;
			if(activityArea.getStatus().equals("1") && activityArea.getActivityEndTime().after(new Date())){
				String shareIntegralType = activityArea.getShareIntegralType();
				Integer minShareIntegral = activityArea.getMinShareIntegral();
				Integer maxShareIntegral = activityArea.getMaxShareIntegral();
				if(shareIntegralType.equals("1")){//0.无积分1.首次得积分2.每日首次分享得积分
					if(integralDtl.getId()==null){//赠送积分
						if(memberAccounts!=null && memberAccounts.size()>0){
							integral = this.giveIntegral(memberAccounts.get(0),memberId,activityArea.getId(),minShareIntegral,maxShareIntegral);
						}
					}
				}else if(shareIntegralType.equals("2")){
					if(integralDtl.getId()!=null){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String now = sdf.format(new Date());
						String shareDate = sdf.format(integralDtl.getCreateDate());
						if(!now.equals(shareDate)){//赠送积分
							if(memberAccounts!=null && memberAccounts.size()>0){
								integral = this.giveIntegral(memberAccounts.get(0),memberId,activityArea.getId(),minShareIntegral,maxShareIntegral);
							}
						}
					}else{//赠送积分
						if(memberAccounts!=null && memberAccounts.size()>0){
							integral = this.giveIntegral(memberAccounts.get(0),memberId,activityArea.getId(),minShareIntegral,maxShareIntegral);
						}
					}
				}
				
			}
			String msg = "";
			if(integral>0){
				msg = "<span style='font-size:16px;'>分享成功获得<font color='#FF5050'>"+integral+"</font>积分</span>";
			} 
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("msg",msg);
			dataMap.put("integral",integral.toString());
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：根据主会场模块id获取分享信息//STORY #1773
	 * @author  yjc: 
	 * @date 创建时间：2019年12月19日10:03:50
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getShareInfoByActivityAreaModuleId")
	@ResponseBody
	public ResponseMsg getShareInfoByActivityAreaModuleId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"activityAreaModuleId"};
			this.requiredStr(obj,request);
			Map<String,Object> dataMap = new HashMap<String,Object>();
			String areaUrl = "";
			String activityShareDesc = "";
			String activitySharePic = "";
			String name = "";
			ActivityAreaModule activityAreaModule = activityAreaModuleMapper.selectByPrimaryKey(Integer.parseInt(reqDataJson.getString("activityAreaModuleId")));
			if(activityAreaModule!=null){
				if("1".equals(activityAreaModule.getLinkType())){//1.会场
					ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(activityAreaModule.getLinkValue()));
					if(activityArea != null && !StringUtil.isBlank(activityArea.getTempletSuffix())){
						areaUrl = Config.getProperty("mUrl")+activityArea.getTempletSuffix()+"?activityAreaId="+activityArea.getId()+"&currentPage=0&pageSize=10&memberId=";
					}
					activityShareDesc = activityArea.getActivityShareDesc();
					activitySharePic = StringUtil.getPic(activityArea.getActivitySharePic(), "");
					name = activityArea.getName();
				}else if("4".equals(activityAreaModule.getLinkType())){//4.自建页面
					ActivityAreaModuleExample e = new ActivityAreaModuleExample();
					e.setOrderByClause("IFNULL(seq_no,99999) asc,id desc");
					e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andLinkTypeEqualTo("1");//1.会场
					List<ActivityAreaModule> activityAreaModuleList = activityAreaModuleMapper.selectByExample(e);
					if(activityAreaModuleList!=null && activityAreaModuleList.size()>0){
						activityAreaModule = activityAreaModuleList.get(0);
						ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(activityAreaModule.getLinkValue()));
						if(activityArea != null && !StringUtil.isBlank(activityArea.getTempletSuffix())){
							areaUrl = Config.getProperty("mUrl")+activityArea.getTempletSuffix()+"?activityAreaId="+activityArea.getId()+"&currentPage=0&pageSize=10&memberId=";
						}
						activityShareDesc = activityArea.getActivityShareDesc();
						activitySharePic = StringUtil.getPic(activityArea.getActivitySharePic(), "");
						name = activityArea.getName();
					}
				}
			}
			InputStream stream = ActivityController.class.getResourceAsStream("/config.properties");
	        try {
	        	Properties properties = new Properties();
	        	properties.load(stream);
	        	String originalId = properties.getProperty("originalId");
	        	String xcxShareType = properties.getProperty("xcxShareType");
	        	dataMap.put("originalId", originalId);//小程序原始ID
	        	dataMap.put("xcxShareType", xcxShareType);//分享小程序的版本（0-正式，1-开发，2-体验）
			} catch (Exception e) {
				e.printStackTrace();
			}
	        dataMap.put("areaUrl", areaUrl);
			dataMap.put("activityShareDesc",  activityShareDesc);
	        dataMap.put("activitySharePic",  activitySharePic);
	        dataMap.put("name",  name);
	        dataMap.put("wxPath", "page/mainVenue/index?activityAreaModuleId="+reqDataJson.getString("activityAreaModuleId"));//分享小程序页面的具体路径
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,dataMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	private Integer giveIntegral(MemberAccount memberAccount, Integer memberId,Integer activityAreaId,Integer minShareIntegral, Integer maxShareIntegral) {
		Integer integral = this.getRandom(minShareIntegral, maxShareIntegral);
		IntegralDtl integralDtl = new IntegralDtl();
		integralDtl.setDelFlag("0");
		integralDtl.setCreateDate(new Date());
		integralDtl.setCreateBy(memberId);
		integralDtl.setAccId(memberAccount.getId());
		integralDtl.setTallyMode("1");
		integralDtl.setType(19);
		integralDtl.setBizType("11");
		integralDtl.setOrderId(activityAreaId);
		integralDtl.setIntegral(integral);
		integralDtl.setBalance(memberAccount.getIntegral()+integral);
		memberAccount.setIntegral(memberAccount.getIntegral()+integral);
		memberAccount.setUpdateDate(new Date());
		memberAccount.setUpdateBy(memberId);
		integralDtlService.save(integralDtl,memberAccount);
		return integral;
	}
	
	private int getRandom(int min, int max) {
		Random random = new Random();
		int i = random.nextInt(max)%(max-min+1)+min;
		return i;
	}
	
}
