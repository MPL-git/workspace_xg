package com.jf.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.jf.common.utils.StringUtil;
import com.jf.dao.ActivityAreaModuleMapper;
import com.jf.dao.ActivityAreaSettingMapper;
import com.jf.dao.CouponModuleTimeMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAreaModule;
import com.jf.entity.ActivityAreaModuleExample;
import com.jf.entity.ActivityAreaSetting;
import com.jf.entity.ActivityAreaSettingExample;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ActivityGroup;
import com.jf.entity.CouponCustom;
import com.jf.entity.CouponModuleTime;
import com.jf.entity.CouponModuleTimeExample;
import com.jf.entity.IntegralDtl;
import com.jf.entity.IntegralDtlExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
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
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;
import com.jf.service.SysParamCfgService;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月23日 上午9:15:54 
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
	private IntegralDtlService integralDtlService;
	@Resource
	private MemberAccountService memberAccountService;
	@Autowired
	private SysParamCfgService sysParamCfgService;
	@Autowired
	private CouponModuleTimeMapper couponModuleTimeMapper;
	@Autowired
	private ActivityAreaModuleMapper activityAreaModuleMapper;
	@Autowired
	private ActivityAreaSettingMapper activityAreaSettingMapper;
	
	
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
			reqDataJson.put("pageSize", pageSize);
			reqDataJson.put("memberId", getMemberId(request));
			Map<String,Object> map = activityService.getCategoryBrandGroup(reqPRM,reqDataJson);
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
			Integer memberId = getApinMemberId(reqPRM, reqDataJson, request);
			Integer activityAreaId = null;
			Integer activityId = null;
			if(reqDataJson.containsKey("activityAreaId") || !StringUtil.isBlank(reqDataJson.getString("activityAreaId"))){
				activityAreaId = reqDataJson.getInt("activityAreaId");
			}else if(reqDataJson.containsKey("activityId") || !StringUtil.isBlank(reqDataJson.getString("activityId"))){
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
	 * 方法描述 ：获取今日特惠活动列表
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
			Map<String, Object> brandGroupMap = new HashMap<String, Object>();
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = reqDataJson.getInt("currentPage");//页码
			String adCatalog = "";
			String adCatalogName = "";
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
			String memberId = getMemberIdStr(request);
			List<Map<String,Object>> activityList = activityService.getTodayPreferentialActivityList(productTypeId,adCatalog,pageSize,currentPage,memberId);
			if(!StringUtil.isBlank(productTypeId) && currentPage == 0){
				brandGroupMap = activityBrandGroupService.getTodayPreferentialActivityBrandGroup(productTypeId);
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
			
			String memberId = getMemberIdStr(request);
			String activityTime = reqDataJson.getString("activityTime");//开售时间
			Integer pageSize = Const.RETURN_SIZE_10;//页数
			Integer currentPage = Integer.valueOf(reqDataJson.getString("currentPage"));//页码
			if(currentPage == null){
				currentPage = 0;
			}
			if(pageSize == null){
				pageSize = 5;
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
			dataMap.put("currentTime", new Date().getTime());
			
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
			//会员id
			String memberId = getMemberIdStr(request);
			//活动id
			Integer activityId = reqDataJson.getInt("activityId");
			//适合性别  00 都不选 11 都选  10 选男不选女   01 选女不选男
			String suitSex = reqDataJson.getString("suitSex");
			if(reqDataJson.containsKey("suitSex") && !StringUtil.isBlank(suitSex)){
				suitSex = reqDataJson.getString("suitSex");
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
			//售价排序类型 DESC 降序  ASC
			String salePriceSort = reqDataJson.getString("salePriceSort");
			//折扣排序类型
			String discountSort = reqDataJson.getString("discountSort");
			//库存>0 标识  T>0
			String stockMark = reqDataJson.getString("stockMark");
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
			params.put("activityId", activityId);
			params.put("suitSex", suitSex);
			params.put("suitGroup", suitGroup);
			params.put("productTypeIdList", productTypeIdList);
			params.put("salePriceMin", salePriceMin);
			params.put("salePriceMax", salePriceMax);
			params.put("salePriceSort", salePriceSort);
			params.put("discountSort", discountSort);
			params.put("stockMark", stockMark);
			params.put("pageSize", pageSize);
			params.put("currentPage", currentPage*pageSize);
			params.put("hiddenTypeList", sysParamCfgService.getSpecialHideTypeList());
			params.put("hiddenMchtCodeList", sysParamCfgService.getSpecialHideMchtCodeList());
			Map<String,Object> returnData = new HashMap<String,Object>();
			List<Map<String,Object>> productInfoList = new ArrayList<Map<String,Object>>();
			List<ActivityCustom> activityCustoms = activityService.getProductByActiviTyId(params);
			
			if(activityCustoms != null && activityCustoms.size() > 0){
				for (ActivityCustom activityCustom : activityCustoms) {
					Map<String,Object> productMap = new HashMap<String,Object>();
					String pic = activityCustom.getProductPic();
					BigDecimal salePrice = new BigDecimal(activityCustom.getSalePrice()+"");
					BigDecimal svipSalePrice = new BigDecimal("0");
					BigDecimal svipDiscount = activityCustom.getSvipDiscount() == null ? new BigDecimal("0") : activityCustom.getSvipDiscount();
					if(activityCustom.getIsWatermark().equals("1")){
						pic = StringUtil.getActivityMkPic(pic, "M_WM", activityCustom.getActivityAreaId());
					}else{
						pic = StringUtil.getPic(pic, "M");
					}
					if(svipDiscount != null && svipDiscount.compareTo(new BigDecimal("0")) > 0){
						svipSalePrice = productService.getProductSvipSalePrice(salePrice, svipDiscount, "0");
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
					productMap.put("svipSalePrice", svipSalePrice);
					productMap.put("productName", activityCustom.getProductName());
					productMap.put("salePrice", salePrice);
					productMap.put("tagPrice", activityCustom.getTagPrice());
					productMap.put("discount", activityCustom.getDiscount());
					productMap.put("activityAreaId", activityCustom.getActivityAreaId());
					productMap.put("activityId", activityCustom.getId());
					productMap.put("productId", activityCustom.getProductId());
					productMap.put("manageSelf", activityCustom.getIsManageSelf()); //是否自营
					productMap.put("stock", activityCustom.getStockSum());
					productMap.put("pic", pic);
					productInfoList.add(productMap);
				}
			}
			ActivityCustom activityCustom = activityService.getActivityInfoById(activityId);
			if(activityCustom == null){
				return new ResponseMsg(ResponseMsg.ERROR_9999,"来晚了哦！活动已结束");
			}
			//开售提醒是否点亮
			boolean isExist = false;
			Integer activityAreaId = activityCustom.getActivityAreaId();
			if(!StringUtil.isBlank(memberId) && activityAreaId != null){
				//开售提醒始终显示灰色
				//判断是否添加开售提醒 true 存在  false 不存在
				isExist = DataDicUtil.getRemindExists(activityAreaId,Integer.valueOf(memberId),"1");
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
				}
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
			returnData.put("activityAreaId", activityAreaId);
			returnData.put("productInfoList", productInfoList);
			returnData.put("tatalPage", "");
			returnData.put("benefitPoint", activityCustom.getBenefitPoint());
			returnData.put("posterPic", FileUtil.getImageServiceUrl()+activityCustom.getPosterPic());
			returnData.put("activityBeginDate", activityBeginDate.getTime()/1000);
			returnData.put("activityEndDate", activityEndDate.getTime()/1000);
			returnData.put("activityName", activityCustom.getName());
			returnData.put("currentTime", new Date().getTime());
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
				msg = "分享成功获得<font color='#FF5050'>"+integral+"</font>积分";
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
	
	/**
	 * 
	 * 方法描述 ：根据领券模块的时间获取优惠券列表
	 * @author  yjc: 
	 * @date 创建时间：2019年04月16日 下午14:17:46 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getCouponListByCouponModuleTime")
	@ResponseBody
	public ResponseMsg getCouponListByCouponModuleTime(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");
			
			Object[] obj = {"recBeginDate","decorateModuleId"};
			this.requiredStr(obj,request);
			String recBeginDate = reqDataJson.getString("recBeginDate");
			String memberId = "";
			Integer decorateModuleId = reqDataJson.getInt("decorateModuleId");
			if(reqDataJson.containsKey("memberId")){
				memberId = reqDataJson.getString("memberId");
			}
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("memberId", memberId==null?0:memberId);
			paramMap.put("recBeginDate", recBeginDate);
			paramMap.put("status", "1");
			paramMap.put("activityType", "2");//2.领券秒杀
			List<CouponCustom> couponCustomList = couponService.getCouponListByCouponModuleTime(paramMap);
			List<Map<String, Object>> couponMapList = new ArrayList<Map<String, Object>>();
			Date currentDate = new Date();
			for(CouponCustom couponCustom:couponCustomList){
				Map<String,Object> couponCustomMap = new HashMap<String,Object>();
				couponCustomMap.put("couponId", couponCustom.getId().toString());
				couponCustomMap.put("name", couponCustom.getName());
				if("4".equals(couponCustom.getRecType())){
					couponCustomMap.put("needSvip", "1");
				}else{
					couponCustomMap.put("needSvip", "0");
				}
				String couponType = couponCustom.getCouponType();
				String couponPreferentialType = couponCustom.getPreferentialType();//1优惠券满减 2优惠券折扣
				String conditionType = couponCustom.getConditionType();
				BigDecimal minimum = couponCustom.getMinimum();
				BigDecimal money = couponCustom.getMoney();
				BigDecimal maxDiscountMoney = couponCustom.getMaxDiscountMoney();
				Integer minicount = couponCustom.getMinicount();//最低几件
				String preferentialInfo = "";
				if(couponType.equals("4")){
					preferentialInfo = "无门槛优惠券";
				}else{
					if("1".equals(couponPreferentialType)){
						if(minimum.subtract(money).compareTo(new BigDecimal("0.01")) == 0){
							preferentialInfo = "无门槛优惠券";
						}else{
							preferentialInfo = "满"+minimum.stripTrailingZeros().toPlainString()+"元可用";
						}
						
					}else if("2".equals(couponPreferentialType)){
						if("1".equals(conditionType)){
							preferentialInfo = "无门槛优惠券";
						}else if("2".equals(conditionType)){
							preferentialInfo = "满"+minimum.stripTrailingZeros().toPlainString()+"元可用";
						}else if("3".equals(conditionType)){
							preferentialInfo = "满"+minicount+"件可用";
						}
						if(maxDiscountMoney != null){
							preferentialInfo += " 最多优惠"+maxDiscountMoney.stripTrailingZeros().toPlainString()+"元";
						}
					}
				}
				couponCustomMap.put("preferentialInfo", preferentialInfo);
				
				String rang = couponCustom.getRang();
				String useDescription = "";
				if(rang.equals("1")){//全平台
					if("2".equals(couponType)){//品类券
						useDescription = "";
						if(!StringUtil.isEmpty(couponCustom.getTypeIds())){
							useDescription = "限"+couponCustom.getProductTypeNames()+"品类可用";
						}else{
							useDescription = "全品类可用";
						}
					}else if("4".equals(couponType)){
						useDescription = "指定商品可用";
					}
				}else if(rang.equals("2")){
					useDescription = "指定会场可用";
				}else if(rang.equals("3")){
					useDescription = "指定会场可用";
				}else if(rang.equals("4")){
					if("4".equals(couponType)){
						useDescription = "指定商品可用";
					}else{
						useDescription = "指定店铺可用";
					}
				}
				couponCustomMap.put("useDescription", useDescription);
				DecimalFormat decimalFormat = new DecimalFormat("#.##");
				if(couponCustom.getDiscount()!=null){
					couponCustomMap.put("discount", decimalFormat.format(couponCustom.getDiscount().multiply(new BigDecimal(10)))+"折");
				}else{
					couponCustomMap.put("discount", "");
				}
				if(couponCustom.getMoney()!=null){
					couponCustomMap.put("money", decimalFormat.format(couponCustom.getMoney())+"元");
				}else{
					couponCustomMap.put("money", "");
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				couponCustomMap.put("recBeginDate", sdf.format(couponCustom.getRecBeginDate()));
				if(couponCustom.getRecBeginDate().after(currentDate)){
					couponCustomMap.put("msgType", "-1");//即将开始
					couponCustomMap.put("msg", "即将开始");
				}else{
					// 优惠券发行数量
					Integer grantQuantity = couponCustom.getGrantQuantity() == null ? 0 : couponCustom.getGrantQuantity();
					// 优惠券已领数量
					Integer recQuantity = couponCustom.getRecQuantity() == null ? 0 : couponCustom.getRecQuantity();
					// 优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
					String recLimitType = couponCustom.getRecLimitType() == null ? "" : couponCustom.getRecLimitType();
					// 优惠券 限领数量
					Integer recEach = couponCustom.getRecEach() == null ? 0 : couponCustom.getRecEach();
					String msgType = "0";//0.可领取 1已领取 2.领光
					String msg = "";
					if (grantQuantity > 0 && grantQuantity > recQuantity) {
						if (couponCustom.getRecDate()!=null) {
							String recDate = DateUtil.getFormatDate(couponCustom.getRecDate(), "yyyyMMdd");
							String currentDateStr = DateUtil.getFormatDate(currentDate, "yyyyMMdd");
							// 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
							if (recLimitType.equals("1")) {
								if (recDate.equals(currentDateStr)) {
									msgType = "1";
									msg = "每人每天限领1张 ";
								}
							} else if (recLimitType.equals("2")) {
								if (couponCustom.getMemberCouponIds().split(",").length >= recEach) {
									msgType = "1";
									msg = "每人限领 " + recEach + "张";
								}
							} else if (recLimitType.equals("3")) {//3.不限
								
							} else {
								msgType = "2";
								msg = "优惠券已抢光";
							}
						}
					} else {
						msgType = "2";
						msg = "优惠券已抢光";
					}
					couponCustomMap.put("msgType", msgType);
					couponCustomMap.put("msg", msg);
				}
				couponMapList.add(couponCustomMap);
			}
			map.put("couponMapList", couponMapList);
			
			List<Map<String, Object>> timeCouponList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> finnalTimeCouponList = new ArrayList<Map<String, Object>>();
			CouponModuleTimeExample example = new CouponModuleTimeExample();
			example.setOrderByClause("start_hours desc,start_min desc");//倒序排
			example.createCriteria().andDelFlagEqualTo("0").andDecorateModuleIdEqualTo(decorateModuleId);
			List<CouponModuleTime> couponModuleTimeList = couponModuleTimeMapper.selectByExample(example);
			if(couponModuleTimeList!=null && couponModuleTimeList.size()>0){
				String minTodayTime = "";
				String maxDayTime = "";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String today = sdf.format(currentDate);
				String tomorrow = sdf.format(DateUtil.addDay(currentDate, 1));
				String afterTomorrow = sdf.format(DateUtil.addDay(currentDate, 2));
				minTodayTime = today+" "+couponModuleTimeList.get(couponModuleTimeList.size()-1).getStartHours()+":"+couponModuleTimeList.get(couponModuleTimeList.size()-1).getStartMin()+":00";
				maxDayTime = afterTomorrow+" "+couponModuleTimeList.get(0).getStartHours()+":"+couponModuleTimeList.get(0).getStartMin()+":00";
				List<String> totalTimeStrList = new ArrayList<String>();
				for(CouponModuleTime couponModuleTime:couponModuleTimeList){
					String eachTodayTime = today+" "+couponModuleTime.getStartHours()+":"+couponModuleTime.getStartMin()+":00";
					String eachTomorrowTime = tomorrow+" "+couponModuleTime.getStartHours()+":"+couponModuleTime.getStartMin()+":00";
					String eachAfterTomorrowTime = afterTomorrow+" "+couponModuleTime.getStartHours()+":"+couponModuleTime.getStartMin()+":00";
					totalTimeStrList.add(eachAfterTomorrowTime);
					totalTimeStrList.add(eachTomorrowTime);
					totalTimeStrList.add(eachTodayTime);
				}
				paramMap = new HashMap<String,Object>();
				paramMap.put("memberId", memberId==null?0:memberId);
				paramMap.put("activityType", "2");
				paramMap.put("status", "1");
				paramMap.put("minTodayTime", minTodayTime);
				paramMap.put("maxDayTime", maxDayTime);
				couponCustomList = couponService.getCouponListByModuleTime(paramMap);
				Collections.sort(totalTimeStrList,new Comparator<String>(){
					public int compare(String o1,String o2){
						try {
							if(df.parse(o1).after(df.parse(o2))){
								return 1;
							}else{
								return -1;
							}
						} catch (ParseException e) {
							e.printStackTrace();
							return 0;
						}
					}
				});
				for(String eachTimeStr:totalTimeStrList){
					if(df.parse(eachTimeStr).before(df.parse(minTodayTime))){//比要展示的最小时间还小则跳过
						continue;
					}
					List<CouponCustom> eachTimeCouponCustomList = new ArrayList<CouponCustom>();
					for(CouponCustom couponCustom:couponCustomList){
						if(df.format(couponCustom.getRecBeginDate()).equals(eachTimeStr)){
							eachTimeCouponCustomList.add(couponCustom);
						}
					}
					Map<String,Object> timeCouponMap = new HashMap<String,Object>();
					if(eachTimeCouponCustomList.size()>0){
						String timeDesc = "即将开始";
						if(df.parse(eachTimeStr).before(currentDate)){
							timeDesc = "开抢中";
						}
						String dayStr = eachTimeStr.split(" ")[0].split("-")[2];
						dayStr = this.dealWithDayStr(dayStr);
						String hourMinute = eachTimeStr.split(" ")[1].substring(0, 5);
						timeCouponMap.put("currentTime", currentDate.getTime());
						timeCouponMap.put("recBeginDate", eachTimeStr);
						timeCouponMap.put("dayStr", dayStr);
						timeCouponMap.put("hourMinute", hourMinute);
						timeCouponMap.put("timeDesc", timeDesc);
					}
					if(timeCouponMap.size()>0){
						timeCouponList.add(timeCouponMap);
					}
				}
				Collections.reverse(timeCouponList); // 倒序排列 ,从大到小
				String beginMinDateStr = "";
				for(Map<String, Object> map:timeCouponList){
					String eachDateStr = (String)map.get("recBeginDate");
					if(df.parse(eachDateStr).before(currentDate)){
						beginMinDateStr  = eachDateStr;
						break;
					}
				}
				Collections.reverse(timeCouponList);// 再倒序，从小到大
				if(StringUtil.isEmpty(beginMinDateStr)){
					beginMinDateStr = (String)timeCouponList.get(0).get("recBeginDate");
				}
				for(Map<String, Object> map:timeCouponList){
					String eachDateStr = (String)map.get("recBeginDate");
					if(df.parse(eachDateStr).before(df.parse(beginMinDateStr))){
						continue;
					}else{
						if(finnalTimeCouponList.size()>=5){
							break;
						}else{
							finnalTimeCouponList.add(map);
						}
					}
				}
			}
			map.put("timeCouponList", finnalTimeCouponList);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"");
		}
	}
		
	private String dealWithDayStr(String dayStr){
		if(dayStr.equals("01")){
			dayStr = "1";
		}else if(dayStr.equals("02")){
			dayStr = "2";
		}else if(dayStr.equals("03")){
			dayStr = "3";
		}else if(dayStr.equals("04")){
			dayStr = "4";
		}else if(dayStr.equals("05")){
			dayStr = "5";
		}else if(dayStr.equals("06")){
			dayStr = "6";
		}else if(dayStr.equals("07")){
			dayStr = "7";
		}else if(dayStr.equals("08")){
			dayStr = "8";
		}else if(dayStr.equals("09")){
			dayStr = "9";
		}
		return dayStr+"日";
	}
	
	/**
	 * 
	 * 方法描述 ：获取主会场及其模块信息
	 * @author  yjc
	 * @date 创建时间：2019年12月9日10:34:26
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getMainVenueInfo")
	@ResponseBody
	public ResponseMsg getMainVenueInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			String mainVenueName = "";
			String bottomBgPic = "";
			String activityAreaModuleId = "";
			List<Map<String,Object>> moduleList = new ArrayList<Map<String,Object>>();
			if(reqDataJson.containsKey("activityAreaModuleId")){
				activityAreaModuleId = reqDataJson.getString("activityAreaModuleId");
			}
			ActivityAreaModule activityAreaModule = new ActivityAreaModule();
			ActivityAreaModuleExample e = new ActivityAreaModuleExample();
			e.setOrderByClause("IFNULL(seq_no,99999) asc,id desc");
			e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
			List<ActivityAreaModule> activityAreaModuleList = activityAreaModuleMapper.selectByExample(e);
			if(activityAreaModuleList!=null && activityAreaModuleList.size()>0){
				activityAreaModule = activityAreaModuleList.get(0);
				for(ActivityAreaModule item:activityAreaModuleList){
					Map<String,Object> itemMap = new HashMap<String,Object>();
					itemMap.put("activityAreaModuleId", item.getId().toString());
					itemMap.put("moduleName", item.getName());
					itemMap.put("defaultModulePic", StringUtil.getPic(item.getDefaultModulePic(),""));
					itemMap.put("selectedModulePic", StringUtil.getPic(item.getSelectedModulePic(),""));
					itemMap.put("linkType", item.getLinkType());
					itemMap.put("linkValue", item.getLinkValue());
					String type = "";
					String templetType = "";
					if("1".equals(item.getLinkType())){
						ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(item.getLinkValue()));
						if(activityArea!=null){
							type = activityArea.getType();
							templetType = activityArea.getTempletType();
						}
					}
					itemMap.put("type", type);
					itemMap.put("templetType", templetType);
					moduleList.add(itemMap);
				}
			}
			if(!StringUtil.isEmpty(activityAreaModuleId)){
				activityAreaModule = activityAreaModuleMapper.selectByPrimaryKey(Integer.parseInt(activityAreaModuleId));
			}
			ActivityAreaSettingExample aase = new ActivityAreaSettingExample();
			aase.createCriteria().andDelFlagEqualTo("0");
			List<ActivityAreaSetting> activityAreaSettingList = activityAreaSettingMapper.selectByExample(aase);
			if(activityAreaSettingList!=null && activityAreaSettingList.size()>0){
				mainVenueName = activityAreaSettingList.get(0).getName();
				bottomBgPic = StringUtil.getPic(activityAreaSettingList.get(0).getBottomBgPic(), "");
			}
			
			Map<String, Object> map = new HashMap<>();
			map.put("mainVenueName", mainVenueName);
			map.put("bottomBgPic", bottomBgPic);
			map.put("moduleName", activityAreaModule.getName());
			map.put("defaultModulePic", StringUtil.getPic(activityAreaModule.getDefaultModulePic(),""));
			map.put("selectedModulePic", StringUtil.getPic(activityAreaModule.getSelectedModulePic(),""));
			map.put("linkType", activityAreaModule.getLinkType());
			map.put("linkValue", activityAreaModule.getLinkValue());
			map.put("moduleList", moduleList);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
}
