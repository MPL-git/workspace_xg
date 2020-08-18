
package com.jf.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ListHelper;
import com.jf.common.utils.NumberUtil;
import com.jf.common.utils.StringUtil;
import com.jf.controller.base.BaseController;
import com.jf.entity.ActivityCustom;
import com.jf.entity.AllowanceInfo;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.FullCut;
import com.jf.entity.FullCutExample;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullDiscountExample;
import com.jf.entity.FullGive;
import com.jf.entity.FullGiveCustom;
import com.jf.entity.FullGiveExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MemberPlatformCoupon;
import com.jf.entity.Product;
import com.jf.entity.ShoppingCart;
import com.jf.entity.ShoppingCartCustom;
import com.jf.entity.ShoppingCartExample;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderExample;
import com.jf.entity.SvipBindProduct;
import com.jf.entity.SvipMarketingSetting;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityProductDepositService;
import com.jf.service.ActivityService;
import com.jf.service.ConfigSpecialMchtService;
import com.jf.service.CouponService;
import com.jf.service.CustomAdPageService;
import com.jf.service.FullCutService;
import com.jf.service.FullDiscountService;
import com.jf.service.FullGiveService;
import com.jf.service.MchtInfoService;
import com.jf.service.MemberCouponService;
import com.jf.service.MemberInfoService;
import com.jf.service.ProductItemService;
import com.jf.service.ProductService;
import com.jf.service.ShopPreferentialInfoService;
import com.jf.service.ShoppingCartService;
import com.jf.service.SubDepositOrderService;
import com.jf.service.SvipBindProductService;
import com.jf.service.SvipMarketingSettingService;
import com.jf.service.allowance.AllowanceInfoService;
import com.jf.service.allowance.MemberAllowanceService;
import com.jf.vo.SVipInfo;
import com.jf.vo.response.allowance.AllowanceInfoView;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class ShopController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ShopController.class);
	@Resource
	private ShoppingCartService shoppingCartService;

	@Resource
	private ActivityAreaService activityAreaService;

	@Resource
	private ActivityService activityService;

	@Resource
	private ProductItemService productItemService;

	@Resource
	private ProductService productService;

	@Resource
	private CouponService couponService;

	@Resource
	private FullCutService fullCutService;

	@Resource
	private FullGiveService fullGiveService;

	@Resource
	private FullDiscountService fullDiscountService;

	@Resource
	private MemberCouponService memberCouponService;

	@Resource
	private ShopPreferentialInfoService shopPreferentialInfoService;

	@Resource
	private CustomAdPageService customAdPageService;

	@Resource
	private MchtInfoService mchtInfoService;

	@Autowired
	private ActivityProductDepositService activityProductDepositService;

	@Autowired
	private SubDepositOrderService subDepositOrderService;

	@Autowired
	private MemberInfoService memberInfoService;

	@Autowired
	private ConfigSpecialMchtService configSpecialMchtService;
	@Autowired
	private AllowanceInfoService allowanceInfoService;
	@Autowired
	private MemberAllowanceService memberAllowanceService;
	@Autowired
	private SvipBindProductService svipBindProductService;
	@Autowired
	private SvipMarketingSettingService svipMarketingSettingService;

	/**
	 *
	 * 方法描述 ：
	 * @author  chenwf:
	 * @date 创建时间：2017年8月24日 下午2:12:02
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/addShoppingCart")
	@ResponseBody
	public ResponseMsg addShoppingCart(HttpServletRequest request) {
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		try {
			Object[] obj = { "memberId", "productId"};
			this.requiredStr(obj, request);
			ShoppingCart shoppingCart = shoppingCartService.addShoppingCart(reqDataJson,reqPRM);

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,shoppingCart.getId().toString());
		} catch (ArgException arge) {
			String error = ResponseMsg.ERROR;
			if(!StringUtil.isBlank(arge.getExceptionType())){
				error = arge.getExceptionType();
			}
			return new ResponseMsg(error, arge.getMessage());
		} catch (Exception e) {
			logger.info("购物车参数："+reqDataJson.getString("productId").toString());
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "添加购物车失败");
		}

	}

	@RequestMapping(value = "/api/y/editShoppingCart")
	@ResponseBody
	public ResponseMsg editShoppingCart(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = { "memberId", "shoppingCartId", "type" };
			this.requiredStr(obj, request);
			shoppingCartService.editShoppingCart(reqDataJson,reqPRM);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}

	@RequestMapping(value = "/api/y/cart")
	@ResponseBody
	public String cart() {
		return json();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/api/y/getShoppingCart")
	@ResponseBody
	public ResponseMsg getShoppingCart(HttpServletRequest request) {
		try {
			Map<String, Map<String, Object>> resultMapTmp = new LinkedHashMap<String, Map<String, Object>>();
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
			Object[] obj = { "memberId" };
			this.requiredStr(obj, request);
			Integer memberId = Integer.valueOf(reqDataJson.getString("memberId"));
			SVipInfo sVipInfo = memberInfoService.isSVip(null, memberId);
			String system = reqPRM.getString("system");
			Integer version = reqPRM.getInt("version");
			String areaUrl = "";
			boolean depositControl = false;//控制是否需要购物车的商品数量 >= 定金的数量  true控制  false不控制
			Map<Integer, List<SubDepositOrder>> subDepositOrderMap = new HashMap<Integer, List<SubDepositOrder>>();
			List<SubDepositOrder> subDepositOrders = new ArrayList<SubDepositOrder>();
			List<Integer> cartIds = new ArrayList<Integer>();//这个用来储存日常销售店铺未开通的购物车id
			List<ShoppingCartCustom> shoppingCarts = shoppingCartService.getMemberShoppingCart(memberId);
			SvipMarketingSetting svipMarketingSetting = svipMarketingSettingService.getSetting();
			Set<Integer> svipMarketingBindProductIdSet = getSvipMarketBindProductIdSet(shoppingCarts, svipMarketingSetting); //获取购物车中绑定svip营销的商品id集合
			List<Integer> productIds = new ArrayList<Integer>();
			List<Integer> skuIds = new ArrayList<Integer>();
			List<Integer> depositProductIds = new ArrayList<Integer>();
			Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();//活动中
			Map<String,ActivityCustom> notActivityMap = new HashMap<String,ActivityCustom>();//活动未开始
			Set<Integer> mchtIdSet = Sets.newHashSet();
			for (ShoppingCartCustom shoppingCart : shoppingCarts) {
				if(shoppingCart.getActivityType().equals("0") || shoppingCart.getActivityType().equals("101")){
					productIds.add(shoppingCart.getProductId());
					skuIds.add(shoppingCart.getProductItemId());
				}
				mchtIdSet.add(shoppingCart.getMchtId());
			}
			if(CollectionUtils.isNotEmpty(productIds)){
				Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
				paramsActivityMap.put("productIdList", productIds);
				//旧版本预售购物车过滤掉
				if((system.equals(Const.ANDROID) && version >= 45) || (system.equals(Const.IOS) && version >= 48)){
					paramsActivityMap.put("type", "1");//这个字段用来获取未开始的活动数据
				}
				List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
				if(CollectionUtils.isNotEmpty(activityCustoms)){
					for (ActivityCustom activityCustom : activityCustoms) {
						if(activityCustom.getActivityBeginTime().compareTo(date) <= 0){ //活动中
							activityMap.put(activityCustom.getProductId().toString(), activityCustom);
						}else { //活动未开始
							notActivityMap.put(activityCustom.getProductId().toString(), activityCustom);
						}
					}
				}
			}
			if(CollectionUtils.isNotEmpty(skuIds)){
				//获取该商品有几张定金券
				SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
				subDepositOrderExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId)
						.andProductItemIdIn(skuIds).andStatusIn(Arrays.asList("2", "8")); //2 定金已付   8 尾款下单后未支付取消
				List<SubDepositOrder> subDepositOrderList = subDepositOrderService.selectByExample(subDepositOrderExample);
				if(CollectionUtils.isNotEmpty(subDepositOrderList)){
					for (SubDepositOrder subDepositOrder : subDepositOrderList) {
						depositProductIds.add(subDepositOrder.getProductId());
						if(subDepositOrderMap.containsKey(subDepositOrder.getProductItemId())){
							subDepositOrders = subDepositOrderMap.get(subDepositOrder.getProductItemId());
						}else{
							subDepositOrders = new ArrayList<SubDepositOrder>();
						}
						subDepositOrders.add(subDepositOrder);
						subDepositOrderMap.put(subDepositOrder.getProductItemId(), subDepositOrders);
					}
				}
			}


			// 下面这个循环主要给购物车商品重新分配activityType，并把购物车商品按楼层ID分好放在resultMapTmp
			for (ShoppingCartCustom shoppingCart : shoppingCarts) {
				ActivityCustom activityCustom = null;
				Map<String, Object> activityAreaMap = null;
				if ((shoppingCart.getActivityAreaId() == null
						&& shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_AREA))
						|| (shoppingCart.getSingleProductActivityId() == null
								&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_AREA)
								&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP))) {
					activityAreaMap = resultMapTmp.get("0");
				} else {
					String keyId = "";
					if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_EXPLOSION)){
						keyId = "-2";
					}else if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_BROKEN_CODE_CLEARING)){
						keyId = "-6";
					}else{
						// 查询时已经把不在购物车显示的商品过滤掉了，这里只剩会场和普通商品
						if(activityMap.get(shoppingCart.getProductId().toString()) != null){
							activityCustom = activityMap.get(shoppingCart.getProductId().toString());
							//活动中的商品
							shoppingCart.setActivityType("0");
							keyId = activityCustom.getActivityAreaId().toString();

						}else{
							activityCustom = notActivityMap.get(shoppingCart.getProductId().toString());
							if(activityCustom != null && "2".equals(activityCustom.getPreSellAuditStatus())
									&& subDepositOrderMap.containsKey(shoppingCart.getProductItemId())
									&& subDepositOrderMap.get(shoppingCart.getProductItemId()).size() > 0) { //预售状态为通过
								activityMap.put(shoppingCart.getProductId().toString(), activityCustom);
								shoppingCart.setActivityType("0");
								keyId = activityCustom.getActivityAreaId().toString();
							}else {
								//日常销售商品
								shoppingCart.setActivityType("101");
								keyId = "m"+shoppingCart.getMchtId();
								//判断日常销售的商品是否已开通店铺
								MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(shoppingCart.getMchtId());
								if(mchtInfo == null || !"1".equals(mchtInfo.getShopStatus()) || !"1".equals(mchtInfo.getStatus())){
									cartIds.add(shoppingCart.getId());
									continue;
								}
							}
						}
					}
					activityAreaMap = resultMapTmp.get(keyId);
				}
				if (activityAreaMap == null) {
					activityAreaMap = new HashMap<String, Object>();
					activityAreaMap.put("preSellAuditStatus", "0"); //预售审核状态
					activityAreaMap.put("endPaymentDateStr", ""); //尾款时间
					activityAreaMap.put("activityBeginTime", ""); //活动开始时间
					activityAreaMap.put("activityEndTime", ""); //活动截止时间

					if(shoppingCart.getActivityType().equals("2")){
						resultMapTmp.put("-2", activityAreaMap);
						activityAreaMap.put("activityAreaName", "爆款活动");
					}else if(shoppingCart.getActivityType().equals("6")){
						resultMapTmp.put("-6", activityAreaMap);
						activityAreaMap.put("activityAreaName", "断码清仓");
					}else{
						if(activityMap.get(shoppingCart.getProductId().toString()) != null && notActivityMap.get(shoppingCart.getProductId().toString()) == null){
							activityCustom = activityMap.get(shoppingCart.getProductId().toString());
							//活动中的商品
							shoppingCart.setActivityType("0");

							resultMapTmp.put(activityCustom.getActivityAreaId().toString(), activityAreaMap);
							activityAreaMap.put("activityAreaName", activityCustom.getName());
							activityAreaMap.put("preferentialType", activityCustom.getPreferentialType());
							activityAreaMap.put("preSellAuditStatus", activityCustom.getPreSellAuditStatus());
							activityAreaMap.put("endPaymentDateStr", sdf.format(activityCustom.getActivityEndTime())+"结束尾款支付");
							activityAreaMap.put("activityBeginTime", activityCustom.getActivityBeginTime().getTime()); //活动开始时间
							activityAreaMap.put("activityEndTime", activityCustom.getActivityEndTime().getTime()); //活动截止时间
						}else{
							activityCustom = notActivityMap.get(shoppingCart.getProductId().toString());
							if(activityCustom != null && "2".equals(activityCustom.getPreSellAuditStatus())
									&& subDepositOrderMap.containsKey(shoppingCart.getProductItemId())
									&& subDepositOrderMap.get(shoppingCart.getProductItemId()).size() > 0) { //预售状态为通过
								activityCustom = notActivityMap.get(shoppingCart.getProductId().toString());
								activityMap.put(shoppingCart.getProductId().toString(), activityCustom);
								//品牌活动开通预售（活动未开始）
								shoppingCart.setActivityType("0");

								resultMapTmp.put(activityCustom.getActivityAreaId().toString(), activityAreaMap);
								activityAreaMap.put("activityAreaName", activityCustom.getName());
								activityAreaMap.put("preferentialType", activityCustom.getPreferentialType());
								activityAreaMap.put("preSellAuditStatus", activityCustom.getPreSellAuditStatus());
								activityAreaMap.put("endPaymentDateStr", sdf.format(activityCustom.getActivityBeginTime())+"后付尾款");
								activityAreaMap.put("activityBeginTime", activityCustom.getActivityBeginTime().getTime()); //活动开始时间
								activityAreaMap.put("activityEndTime", activityCustom.getActivityEndTime().getTime()); //活动截止时间
							}else {
								//日常销售商品
								shoppingCart.setActivityType("101");
								resultMapTmp.put("m"+shoppingCart.getMchtId(), activityAreaMap);
								activityAreaMap.put("activityAreaName", shoppingCart.getShopName());
								//判断日常销售的商品是否已开通店铺
								MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(shoppingCart.getMchtId());
								if(mchtInfo == null || !"1".equals(mchtInfo.getShopStatus()) || !"1".equals(mchtInfo.getStatus())){
									cartIds.add(shoppingCart.getId());
									continue;
								}
							}
						}
					}
				}
				String source = "";
				String activityAreaType = "";
				Integer activityId = null;
				if(activityCustom != null){
					source = activityCustom.getSource();
					activityAreaType = activityCustom.getType();
					activityId = activityCustom.getId();
					if(!StringUtil.isBlank(source) && source.equals("1") && !StringUtil.isBlank(activityCustom.getTempletSuffix())){
						areaUrl = Config.getProperty("mUrl")+activityCustom.getTempletSuffix()+"?activityAreaId="+activityCustom.getActivityAreaId()+"&currentPage=0&pageSize=10&memberId=";
					}
					if(StringUtil.isBlank(source) && StringUtil.isBlank(activityAreaType)){
						source = "1";
						if(shoppingCart.getActivityType().equals("1")){
							activityAreaType = "7";
						}else if(shoppingCart.getActivityType().equals("2")){
							activityAreaType = "6";
						}
					}
				}
				activityAreaMap.put("source", source);
				activityAreaMap.put("activityId", activityId);
				activityAreaMap.put("areaUrl", areaUrl);
				activityAreaMap.put("activityAreaType", activityAreaType);
				activityAreaMap.put("activityType", shoppingCart.getActivityType());
				List<ShoppingCartCustom> shoppingCartCustoms;
				if (activityAreaMap.get("shoppingCartList") == null) {
					shoppingCartCustoms = new ArrayList<ShoppingCartCustom>();
					activityAreaMap.put("shoppingCartList", shoppingCartCustoms);
				} else {
					shoppingCartCustoms = (ArrayList<ShoppingCartCustom>) activityAreaMap.get("shoppingCartList");
				}
				shoppingCartCustoms.add(shoppingCart);
			}

			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			// 下面这个循环把楼层购物车的相关优惠信息和购物车里的商品信息填充进来并放到resultList最终下发给前端
			Map<String, Object> resultMap = new HashMap<String, Object>();
			for (String key : resultMapTmp.keySet()) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				Map<Integer, Object> productIdMap = new HashMap<>();
				List<Map<String, Object>> productCouponList = new ArrayList<>();
				dataMap.put("activityAreaName", resultMapTmp.get(key).get("activityAreaName"));
				dataMap.put("source", resultMapTmp.get(key).get("source"));
				dataMap.put("activityId", resultMapTmp.get(key).get("activityId"));
				dataMap.put("areaUrl", resultMapTmp.get(key).get("areaUrl"));
				dataMap.put("activityAreaType", resultMapTmp.get(key).get("activityAreaType"));
				dataMap.put("activityType", resultMapTmp.get(key).get("activityType"));
				dataMap.put("preSellAuditStatus", "2".equals(resultMapTmp.get(key).get("preSellAuditStatus"))?"1":"0"); //是否预售  0：否  1：是
//				dataMap.put("preSellAuditStatus", resultMapTmp.get(key).get("preSellAuditStatus").toString().equals("2")?"1":"0"); //是否预售  0：否  1：是
				dataMap.put("endPaymentDateStr", resultMapTmp.get(key).get("endPaymentDateStr"));
				dataMap.put("activityBeginTime", resultMapTmp.get(key).get("activityBeginTime")); //活动开始时间
				dataMap.put("activityEndTime", resultMapTmp.get(key).get("activityEndTime")); //活动截止时间

				// 楼层优惠信息填充开始
				String preferentialType=null;//会场优惠类型
				if(key.contains("m")){
					String newKey = key.substring(1,key.length());
					preferentialType="0";
					dataMap.put("activityAreaId", newKey);
					// dataMap填充店铺优惠信息
					dataMap = shopPreferentialInfoService.getMchtShopPreferInfoByMchtId(Integer.valueOf(newKey),dataMap,"","2",reqPRM);
					//获取会员已领店铺优惠券
					Map<String, Object> params=new HashMap<String, Object>();
					params.put("memberId", memberId);
					params.put("mchtId", newKey);
					List<Map<String, Object>> memberCoupons= memberCouponService.getMemberUsebleMchtShopCoupons(params);
					for(Map<String, Object> memberCoupon:memberCoupons){
						memberCoupon.put("expiryBeginDate", DateUtil.getStandardDateTime((Date)memberCoupon.get("expiryBeginDate")));
						memberCoupon.put("expiryEndDate", DateUtil.getStandardDateTime((Date)memberCoupon.get("expiryEndDate")));
					}

					dataMap.put("memberCoupons",memberCoupons);
				}else{
					dataMap.put("activityAreaId", key);

					if(resultMapTmp.get(key).get("preferentialType")!=null){
						preferentialType=(String)resultMapTmp.get(key).get("preferentialType");
					}else{
						preferentialType="0";
					}
					// dataMap填充会场优惠信息
					if("1".equals(preferentialType)){//优惠券
						CouponExample couponExample=new CouponExample();
						couponExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(Integer.valueOf(key))
						.andExpiryEndDateGreaterThanOrEqualTo(date).andExpiryBeginDateLessThanOrEqualTo(date).andStatusEqualTo("1")
						.andRecTypeNotEqualTo("3").andIsIntegralTurntableEqualTo("0");
						List<Coupon> coupons=couponService.selectByExample(couponExample);
						dataMap.put("coupons", coupons);
					}
					if("2".equals(preferentialType)){//满减
						FullCutExample fullCutExample=new FullCutExample();
						fullCutExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(Integer.valueOf(key));
						List<FullCut> fullCuts=fullCutService.selectByExample(fullCutExample);
						dataMap.put("fullCuts", fullCuts);
					}
					if("3".equals(preferentialType)){//满赠
						FullGiveExample fullGiveExample=new FullGiveExample();
						fullGiveExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(Integer.valueOf(key));
						List<FullGive> fullGives=fullGiveService.selectByExample(fullGiveExample);
						FullGive fullGive=fullGives.get(0);
						FullGiveCustom fullGiveCustom=new FullGiveCustom();
						BeanUtils.copyProperties(fullGive, fullGiveCustom);
						if("1".equals(fullGive.getProductFlag())){
							Product product=productService.selectByPrimaryKey(fullGive.getProductId());
							fullGiveCustom.setProductName(product.getName());
						}
						if("1".equals(fullGive.getCouponFlag())){
							String[] couponIds=fullGive.getCouponIdGroup().split(",");
							StringBuffer couponNameGroup=new StringBuffer();
							for (int i = 0; i < couponIds.length; i++) {
								Coupon coupon=couponService.selectByPrimaryKey(Integer.valueOf(couponIds[i]));
								couponNameGroup.append(",").append(coupon.getName());
							}
							fullGiveCustom.setCouponNameGroup(couponNameGroup.toString().substring(1));
						}
						List<FullGiveCustom> fullGiveCustoms=new ArrayList<>();
						fullGiveCustoms.add(fullGiveCustom);
						dataMap.put("fullGives", fullGiveCustoms);
					}
					if("4".equals(preferentialType)){//多买优惠
						FullDiscountExample fullDiscountExample=new FullDiscountExample();
						fullDiscountExample.createCriteria().andDelFlagEqualTo("0").andActivityAreaIdEqualTo(Integer.valueOf(key));
						List<FullDiscount> fullDiscounts=fullDiscountService.selectByExample(fullDiscountExample);

						if (CollectionUtils.isNotEmpty(fullDiscounts)) {
							String newRule = "";
							for (FullDiscount fullDiscount : fullDiscounts) {
								String newRule1 = "";
								String newRule2 = "";
								if(fullDiscount.getType().equals("3")){
									List<String> list = new ArrayList<String>();
									String[] rules = fullDiscount.getRule().split("\\|");
									for (String rule : rules) {
										String[] ruleStrs = rule.split(",");
										newRule1 = ruleStrs[0];
										newRule2 = String.valueOf(Double.valueOf(ruleStrs[1].toString())/10);
										newRule = newRule1+","+newRule2;
										list.add(newRule);
									}
									String returnStr=StringUtils.join(list, "|");
									fullDiscount.setRule(returnStr);
								}else if(fullDiscount.getType().equals("4")){
									if((version <= 34 && system.equals(Const.ANDROID)
											|| (version <= 35 && system.equals(Const.IOS)))){
										fullDiscounts.clear();
										break;
									}else{
										String[] rules = fullDiscount.getRule().split(",");
										newRule1 = rules[0];
										newRule2 = String.valueOf(Double.valueOf(rules[1].toString())/10);
										newRule = newRule1+","+newRule2;
										fullDiscount.setRule(newRule);
									}
								}
							}
						}


						dataMap.put("fullDiscounts", fullDiscounts);
					}
					if ("5".equals(preferentialType)) { //购物津贴
						List<AllowanceInfoView> allowanceInfoViewList = buildAllowanceInfoViewList(Integer.valueOf(key), date);
						dataMap.put("allowanceInfos", allowanceInfoViewList);
					}

					//获取会员已领会场优惠券
					Map<String, Object> params=new HashMap<String, Object>();
					params.put("memberId", memberId);
					params.put("activityAreaId", Integer.valueOf(key));
					List<Map<String, Object>> memberCoupons= memberCouponService.getMemberUsebleActivityAreaCoupons(params);
					for(Map<String, Object> memberCoupon:memberCoupons){
						memberCoupon.put("expiryBeginDate", DateUtil.getStandardDateTime((Date)memberCoupon.get("expiryBeginDate")));
						memberCoupon.put("expiryEndDate", DateUtil.getStandardDateTime((Date)memberCoupon.get("expiryEndDate")));
					}

					dataMap.put("memberCoupons",memberCoupons);

					dataMap.put("preferentialType",preferentialType);
				}
				// 楼层优惠信息填充完毕

				// 特殊商家商品不参与平台券优惠
				Set<Integer> specialMchtIdSet = configSpecialMchtService.findSpecialMchtIds(Lists.newArrayList(mchtIdSet));

				// 楼层里的购物车商品信息填充开始
				List<ShoppingCartCustom> shoppingCartList = (ArrayList<ShoppingCartCustom>) resultMapTmp.get(key).get("shoppingCartList");
				List<JSONObject> productList = new ArrayList<JSONObject>();
				for (ShoppingCartCustom shoppingCartCustom : shoppingCartList) {
                    JSONObject shopcart = new JSONObject();
                    BigDecimal addSalePrice = shoppingCartCustom.getAddSalePrice();
                    Integer stockSum = shoppingCartCustom.getStockSum();
                    String isSingleProp = shoppingCartCustom.getIsSingleProp();
                    Integer stock = shoppingCartCustom.getStock();
                    Integer lockedAmount = shoppingCartCustom.getLockedAmount();
                    BigDecimal saleOrMallPrice = shoppingCartCustom.getSalePrice();
                    Integer limitBuy = shoppingCartCustom.getLimitBuy();
                    Integer productId = shoppingCartCustom.getProductId();
                    limitBuy = productService.getProductLimitBuy(limitBuy, shoppingCartCustom.getActivityType(), activityMap.get(productId.toString()));
                    String cutPriceRemind = "";
                    String productPropDesc = shoppingCartCustom.getProductPropDesc();
                    if (productPropDesc.contains("颜色分类")) {
                        productPropDesc = productPropDesc.replace("颜色分类", "颜色");
                    }
                    if (shoppingCartCustom.getActivityType().equals("101")) {
                        saleOrMallPrice = shoppingCartCustom.getMallPrice();
                    }
                    if (addSalePrice.compareTo(saleOrMallPrice) > 0) {
                        cutPriceRemind = "比加购时降价" + addSalePrice.subtract(saleOrMallPrice) + "元!";
                    }
                    BigDecimal originalPrice = saleOrMallPrice;
                    BigDecimal svipSalePrice = new BigDecimal("0");
                    BigDecimal svipDiscount = shoppingCartCustom.getSvipDiscount();
                    if (svipDiscount != null && svipDiscount.compareTo(new BigDecimal("0")) > 0) {
                        svipSalePrice = productService.getProductSvipSalePrice(saleOrMallPrice, svipDiscount, shoppingCartCustom.getActivityType());
                        if (sVipInfo.isSVip()) {
                            if (svipSalePrice.compareTo(new BigDecimal("0")) > 0) {
                                saleOrMallPrice = svipSalePrice;
                            }
                        }
                    }
                    String depositProductStatus = "0"; //该商品是否为预售商品 0：否   1：是
                    BigDecimal deposit = new BigDecimal(0); //定金
                    BigDecimal deductAmount = new BigDecimal(0); //抵扣金额
                    int subDepositOrderQuantity = 0; //该商品有几张定金券
//					if("0".equals(shoppingCartCustom.getActivityType()) || "101".equals(shoppingCartCustom.getActivityType())) {
//						if(!"1".equals(isSingleProp)){
//							if(depositProductIds.contains(shoppingCartCustom.getProductId())){
//								isSingleProp = "1"; 
//							}
//						}
//					}
                    //判断是否为预售商品
                    if ("0".equals(shoppingCartCustom.getActivityType())) {
//						ActivityCustom activityCustom = notActivityMap.get(shoppingCartCustom.getProductId().toString()); //活动未开始
//						if(activityCustom == null && !"1".equals(isSingleProp)) {
//							activityCustom = activityMap.get(shoppingCartCustom.getProductId().toString()); //活动中
//							//判断该商品是否处于定金活动
//							ActivityProductDepositExample activityProductDepositExample = new ActivityProductDepositExample();
//							activityProductDepositExample.createCriteria().andActivityIdEqualTo(activityCustom.getId()).andProductIdEqualTo(shoppingCartCustom.getProductId()).andDelFlagEqualTo("0");
//							Integer activityProductDepositSize = activityProductDepositService.countByExample(activityProductDepositExample);
//							if(activityProductDepositSize > 0){
//								isSingleProp = "1";
//							}
//						}
                        //获取该商品有几张定金券
                        subDepositOrders = subDepositOrderMap.get(shoppingCartCustom.getProductItemId());
                        if (subDepositOrders != null && subDepositOrders.size() > 0) {
                            depositProductStatus = "1";
                            isSingleProp = "1";//购物车不展示选择规格下拉图标
                            deposit = subDepositOrders.get(0).getDeposit();
                            deductAmount = subDepositOrders.get(0).getDeductAmount();
                            for (SubDepositOrder subDepositOrder : subDepositOrders) {
                                subDepositOrderQuantity += subDepositOrder.getQuantity();
                            }
                            depositControl = true;
                        }
                    }
                    //真是库存 = 库存 -冻结库存
                    stock = stock - lockedAmount + subDepositOrderQuantity;
                    if (stock > 0 && shoppingCartCustom.getProductStatus().equals("1")) {
                        shopcart.put("stock", stock);
                    } else {
                        shopcart.put("stock", 0);
                    }
                    if ((system.equals(Const.ANDROID) && version <= 49) || (system.equals(Const.IOS) && version <= 52)) {
                        isSingleProp = "1";
                    }
                    //商品券
                    Map<String, Object> productCouponMap = memberCouponService.getProductCouponList(memberId, productId, productCouponList, productIdMap);
                    productIdMap = (Map<Integer, Object>) productCouponMap.get("productIdMap");
                    productCouponList = (List<Map<String, Object>>) productCouponMap.get("productCouponList");
                    List<Map<String, Object>> productCoupons = (List<Map<String, Object>>) productCouponMap.get("productCoupons");//商品对应的优惠
                    shopcart.put("productCoupons", productCoupons);
                    shopcart.put("productPic", StringUtil.getPic(shoppingCartCustom.getProductPic(), "S"));
                    shopcart.put("productName", shoppingCartCustom.getProductName());
                    shopcart.put("tagPrice", shoppingCartCustom.getTagPrice());
                    shopcart.put("salePrice", saleOrMallPrice);
                    shopcart.put("productPropDesc", productPropDesc);
                    shopcart.put("quantity", shoppingCartCustom.getQuantity());
                    shopcart.put("shoppingCartId", shoppingCartCustom.getId());
                    shopcart.put("limitBuy", limitBuy);
                    shopcart.put("productId", shoppingCartCustom.getProductId());
                    shopcart.put("productType1Id", shoppingCartCustom.getProductType1Id());
                    shopcart.put("stockSum", stockSum);
                    shopcart.put("cutPriceRemind", cutPriceRemind);
                    shopcart.put("productItemId", shoppingCartCustom.getProductItemId());
                    shopcart.put("isSingleProp", isSingleProp);
                    shopcart.put("depositProductStatus", depositProductStatus);
                    shopcart.put("deposit", deposit);
                    shopcart.put("deductAmount", deductAmount);
                    shopcart.put("subDepositOrderQuantity", subDepositOrderQuantity);
                    shopcart.put("depositControl", depositControl);
                    shopcart.put("originalPrice", originalPrice);
                    shopcart.put("svipSalePrice", svipSalePrice);
                    shopcart.put("platformCouponUsable", !specialMchtIdSet.contains(shoppingCartCustom.getMchtId())); //特殊商家商品不参与平台券优惠
					if(!sVipInfo.isSVip() && svipMarketingBindProductIdSet.contains(productId) && !depositProductIds.contains(productId)){ //排除预售
						shopcart.put("showSvipMarketingTip", true); //是否显示svip营销提示
						shopcart.put("svipMarketingTip", StringUtil.buildMsg("加{}元换购SVIP年卡且商品立享￥{}", NumberUtil.decorateDenomination(svipMarketingSetting.getPrice()), NumberUtil.decorateDenomination(svipSalePrice)));
					}else{
						shopcart.put("showSvipMarketingTip", false);
						shopcart.put("svipMarketingTip", "");
					}
                    productList.add(shopcart);
                }
				dataMap.put("productList", productList);
				dataMap.put("productCouponList", productCouponList);
				// 楼层里的购物车商品信息填充完毕

				resultList.add(dataMap);
			}

			//获取会员已领全场通用优惠券
			Map<String, Object> platParamsMap = new HashMap<String, Object>();
			platParamsMap.put("memberId", memberId);
			if((version <= 57 && system.equals(Const.ANDROID) || (version <= 58 && system.equals(Const.IOS)))){
				List<MemberPlatformCoupon> memberCouponDTOList= memberCouponService.getMemberUseblePlatformCouponsOld(platParamsMap);
				resultMap.put("memberPlatformCoupons", memberCouponDTOList);
			}else{
				List<Map<String, Object>> memberCouponDTOList= memberCouponService.getMemberUseblePlatformCoupons(platParamsMap);
				resultMap.put("memberPlatformCoupons", memberCouponDTOList);
			}
			String customAdPageUrl = "";
			if(CollectionUtils.isEmpty(resultList)){
				//类型 1 首页主题管 2 日常营销入口 3 商品主题馆 4 现金签到 5 砍价免费拿 6 购物车 7 消息 8 商品未上架
				Map<String,Object> adPageMap = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_6,null);
				customAdPageUrl = adPageMap.get("customAdPageUrl").toString();
			}
			if(CollectionUtils.isNotEmpty(cartIds)){
				ShoppingCartExample cartExample = new ShoppingCartExample();
				cartExample.createCriteria().andIdIn(cartIds);
				ShoppingCart c = new ShoppingCart();
				c.setDelFlag("1");
				c.setRemarks("店铺关闭删除");
				c.setUpdateDate(new Date());
				c.setUpdateBy(memberId);
				shoppingCartService.updateByExampleSelective(c, cartExample);
			}

			resultMap.put("allowanceBalance", memberAllowanceService.getMemberAllowanceBalance(memberId)); //可用津贴余额
			resultMap.put("customAdPageUrl", customAdPageUrl);
			resultMap.put("activityAreaList", resultList);
			resultMap.put("currentTime", new Date().getTime());
            resultMap.put("minimumDiffForSvipTipShow", 1); //显示svip价与销售价差提示的最低价格差
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, resultMap);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}

    private Set<Integer> getSvipMarketBindProductIdSet(List<ShoppingCartCustom> shoppingCarts, SvipMarketingSetting svipMarketingSetting) {
        if (CollectionUtils.isEmpty(shoppingCarts) || svipMarketingSetting == null) {
            return Collections.emptySet();
        }
        List<Integer> productIds = ListHelper.extractIds(shoppingCarts, new ListHelper.IdExtractor<ShoppingCartCustom>() {
            @Override
            public Integer getId(ShoppingCartCustom source) {
                return source.getProductId();
            }
        });
        List<SvipBindProduct> list = svipBindProductService.findByProductIds(productIds);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptySet();
        }
        Set<Integer> set = Sets.newHashSet();
        for (SvipBindProduct product : list) {
            set.add(product.getProductId());
        }
        return set;
    }

	private List<AllowanceInfoView> buildAllowanceInfoViewList(Integer activityAreaId, Date now) {
		List<AllowanceInfo> list = allowanceInfoService.findByActivityAreaId(activityAreaId);
		if (CollectionUtils.isEmpty(list)) return Collections.emptyList();

		List<AllowanceInfoView> viewList = Lists.newArrayList();
		for (AllowanceInfo allowanceInfo : list) {
			AllowanceInfoView view = new AllowanceInfoView();
			view.setId(allowanceInfo.getId());
			view.setRule(allowanceInfo.getRule());
			view.setUsageBeginTime(allowanceInfo.getUsageBeginTime().getTime());
			view.setUsageEndTime(allowanceInfo.getUsageEndTime().getTime());
			view.setSysCurrentTime(now.getTime());
			if (allowanceInfo.getUsageBeginTime().before(now) && allowanceInfo.getUsageEndTime().after(now)) {
				view.setUsable(true);
			} else if (allowanceInfo.getUsageEndTime().before(now)) {
				view.setUseTip("本场活动已结束");
				view.setUsable(false);
			} else {
				view.setUseTip(StringUtil.buildMsg("{}后可用", DateUtil.getFormatDate(allowanceInfo.getUsageBeginTime(), "MM月dd日HH:mm")));
				view.setUsable(false);
			}
			viewList.add(view);
		}
		return viewList;
	}


	/**
	 *
	 * 方法描述 ：获取购物车数量
	 * @author  chenwf:
	 * @date 创建时间：2017年7月5日 下午5:45:40
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getShoppingCartNum")
	@ResponseBody
	public ResponseMsg getShoppingCartNum(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = { "memberId"};
			this.requiredStr(obj, request);

			Integer memberId = Integer.valueOf(reqDataJson.getString("memberId"));
			Integer count = shoppingCartService.getShoppingCartNum(memberId);

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,count);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}

	/**
	 *
	 * @Title delShoppingCartList
	 * @Description TODO(清空购物车)
	 * @author pengl
	 * @date 2018年9月3日 下午2:52:07
	 */
	@RequestMapping(value = "/api/y/delShoppingCartList")
	@ResponseBody
	public ResponseMsg delShoppingCartList(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"memberId", "shoppingCartIdList"};
			this.requiredStr(obj, request);
			shoppingCartService.delShoppingCartList(reqDataJson,reqPRM);

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}

}
