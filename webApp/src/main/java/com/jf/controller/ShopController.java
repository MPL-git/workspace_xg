package com.jf.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
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
import com.jf.entity.Product;
import com.jf.entity.ShoppingCart;
import com.jf.entity.ShoppingCartCustom;
import com.jf.entity.ShoppingCartExample;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderExample;
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
import com.jf.service.ProductService;
import com.jf.service.ShopPreferentialInfoService;
import com.jf.service.ShoppingCartService;
import com.jf.service.SubDepositOrderService;
import com.jf.service.allowance.AllowanceInfoService;
import com.jf.service.allowance.MemberAllowanceService;
import com.jf.vo.response.allowance.AllowanceInfoView;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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

	@Resource
	private ShoppingCartService shoppingCartService;

	@Resource
	private ActivityService activityService;

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
	
	@Resource
	private SubDepositOrderService subDepositOrderService;
	
	@Resource
	private MemberInfoService memberInfoService;

	@Autowired
	private ConfigSpecialMchtService configSpecialMchtService;
	@Autowired
	private AllowanceInfoService allowanceInfoService;
	@Autowired
	private MemberAllowanceService memberAllowanceService;

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
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"productId"};
			this.requiredStr(obj, request);
			ShoppingCart shoppingCart = shoppingCartService.addShoppingCart(reqDataJson,getMemberId(request));
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,shoppingCart.getId().toString());
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
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

			Object[] obj = { "shoppingCartId", "type" };
			this.requiredStr(obj, request);
			shoppingCartService.editShoppingCart(reqDataJson,getMemberId(request));
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

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
			String system  = reqPRM.getString("system");
			Integer memberId = getMemberId(request);
			String isSvip = memberInfoService.getIsSvip(null, memberId);
			String areaUrl = "";
			boolean depositControl = false;//控制是否需要购物车的商品数量 >= 定金的数量  true控制  false不控制
			Map<Integer, List<SubDepositOrder>> subDepositOrderMap = new HashMap<Integer, List<SubDepositOrder>>();
			List<SubDepositOrder> subDepositOrders = new ArrayList<SubDepositOrder>();
			List<Integer> cartIds = new ArrayList<Integer>();//这个用来储存日常销售店铺未开通的购物车id
			List<ShoppingCartCustom> shoppingCarts = shoppingCartService.getMemberShoppingCart(memberId);
			List<Integer> productIds = new ArrayList<Integer>();
			List<Integer> skuIds = new ArrayList<Integer>();
			Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();
			Map<String,ActivityCustom> notActivityMap = new HashMap<String,ActivityCustom>();//活动未开始
			Set<Integer> mchtIdSet = Sets.newHashSet();
			for (ShoppingCartCustom shoppingCart : shoppingCarts) {
				if(shoppingCart.getActivityType().equals("0") || shoppingCart.getActivityType().equals("101")){
					productIds.add(shoppingCart.getProductId());
					skuIds.add(shoppingCart.getProductItemId());
				}
				mchtIdSet.add(shoppingCart.getMchtId());
			}
			//活动中的
			if(CollectionUtils.isNotEmpty(productIds)){
				Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
				paramsActivityMap.put("productIdList", productIds);
				paramsActivityMap.put("type", "1");
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
			for (ShoppingCartCustom shoppingCart : shoppingCarts) {
				ActivityCustom activityCustom = null;
				Map<String, Object> activityAreaMap = null;
				if ((shoppingCart.getActivityAreaId() == null
						&& shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_AREA))
						|| (shoppingCart.getSingleProductActivityId() == null
								&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_AREA))
								&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)){
					activityAreaMap = resultMapTmp.get("0");
				} else {
					String keyId = "";
					if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY)){
						keyId = "-1";
					}else if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_EXPLOSION)){
						keyId = "-2";
					}else if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)){
						keyId = "-3";
					}else if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
						keyId = "-4";
					}else if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL)){
						keyId = "-5";
					}else if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_BROKEN_CODE_CLEARING)){
						keyId = "-6";
					}else{
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

					if(shoppingCart.getActivityType().equals("1")){
						resultMapTmp.put("-1", activityAreaMap);
						activityAreaMap.put("activityAreaName", "新用户专享");
					}else if(shoppingCart.getActivityType().equals("2")){
						resultMapTmp.put("-2", activityAreaMap);
						activityAreaMap.put("activityAreaName", "爆款活动");
					}else if(shoppingCart.getActivityType().equals("3")){
						resultMapTmp.put("-3", activityAreaMap);
						activityAreaMap.put("activityAreaName", "醒购秒杀");
					}else if(shoppingCart.getActivityType().equals("4")){
						resultMapTmp.put("-4", activityAreaMap);
						activityAreaMap.put("activityAreaName", "新人秒杀");
					}else if(shoppingCart.getActivityType().equals("5")){
						resultMapTmp.put("-5", activityAreaMap);
						activityAreaMap.put("activityAreaName", "积分兑换");
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
							activityAreaMap.put("endPaymentDateStr", DateUtil.getFormatDate(activityCustom.getActivityEndTime(), "MM月dd日 HH:mm")+"结束尾款支付");
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
								activityAreaMap.put("endPaymentDateStr", DateUtil.getFormatDate(activityCustom.getActivityBeginTime(), "MM月dd日 HH:mm")+"后付尾款");
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
				dataMap.put("endPaymentDateStr", resultMapTmp.get(key).get("endPaymentDateStr"));
				dataMap.put("activityBeginTime", resultMapTmp.get(key).get("activityBeginTime")); //活动开始时间
				dataMap.put("activityEndTime", resultMapTmp.get(key).get("activityEndTime")); //活动截止时间
				String preferentialType=null;//会场优惠类型
				if(key.contains("m")){
					String newKey = key.substring(1,key.length());
					preferentialType="0";
					dataMap.put("activityAreaId", newKey);
					dataMap = shopPreferentialInfoService.getMchtShopPreferInfoByMchtId(Integer.valueOf(newKey),dataMap,memberId,"2",system);
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

				// 特殊商家商品不参与平台券优惠
				Set<Integer> specialMchtIdSet = configSpecialMchtService.findSpecialMchtIds(Lists.newArrayList(mchtIdSet));

				List<ShoppingCartCustom> shoppingCartList = (ArrayList<ShoppingCartCustom>) resultMapTmp.get(key).get("shoppingCartList");
				List<JSONObject> productList = new ArrayList<JSONObject>();
				for (ShoppingCartCustom shoppingCartCustom : shoppingCartList) {
					JSONObject shopcart = new JSONObject();
					Integer stock = shoppingCartCustom.getStock();
					Integer lockedAmount = shoppingCartCustom.getLockedAmount();
					//真是库存 = 库存 -冻结库存
					stock = stock - lockedAmount;
					BigDecimal saleOrMallPrice = shoppingCartCustom.getSalePrice();
					if(shoppingCartCustom.getActivityType().equals("101")){
						saleOrMallPrice = shoppingCartCustom.getMallPrice();
					}
					String productPropDesc = shoppingCartCustom.getProductPropDesc();
					if(productPropDesc.contains("颜色分类")){
						productPropDesc = productPropDesc.replace("颜色分类", "颜色");
					}
					BigDecimal svipSalePrice = new BigDecimal("0");
					BigDecimal svipDiscount = shoppingCartCustom.getSvipDiscount();
					if(svipDiscount != null && svipDiscount.compareTo(new BigDecimal("0")) > 0){
						svipSalePrice = productService.getProductSvipSalePrice(saleOrMallPrice,svipDiscount,shoppingCartCustom.getActivityType());
						if("1".equals(isSvip)){
							if(svipSalePrice.compareTo(new BigDecimal("0")) > 0){
								saleOrMallPrice = svipSalePrice;
							}
						}
					}
					//商品券
					Map<String, Object> productCouponMap = memberCouponService.getProductCouponList(memberId,shoppingCartCustom.getProductId(),productCouponList,productIdMap);
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
					shopcart.put("productItemId",shoppingCartCustom.getProductItemId());
					shopcart.put("shoppingCartId",shoppingCartCustom.getId());
					shopcart.put("limitBuy",shoppingCartCustom.getLimitBuy());
					shopcart.put("productId",shoppingCartCustom.getProductId());
					shopcart.put("activityType",shoppingCartCustom.getActivityType());
					if(stock > 0 && shoppingCartCustom.getProductStatus().equals("1")){
						shopcart.put("stock",stock);
					}else{
						shopcart.put("stock",0);
					}
					String depositProductStatus = "0"; //该商品是否为预售商品 0：否   1：是
					BigDecimal deposit = new BigDecimal(0); //定金
					BigDecimal deductAmount = new BigDecimal(0); //抵扣金额
					int subDepositOrderQuantity = 0; //该商品有几张定金券
					//判断是否为预售商品
					if("0".equals(shoppingCartCustom.getActivityType()) ) {
						ActivityCustom activityCustom = notActivityMap.get(shoppingCartCustom.getProductId().toString()); //活动未开始
						if(activityCustom == null) {
							activityCustom = activityMap.get(shoppingCartCustom.getProductId().toString()); //活动中
						}
						//获取该商品有几张定金券
						subDepositOrders = subDepositOrderMap.get(shoppingCartCustom.getProductItemId());
						if(subDepositOrders != null && subDepositOrders.size() > 0) {
							depositProductStatus = "1";
							deposit = subDepositOrders.get(0).getDeposit();
							deductAmount = subDepositOrders.get(0).getDeductAmount();
							for(SubDepositOrder subDepositOrder : subDepositOrders) {
								subDepositOrderQuantity += subDepositOrder.getQuantity();
							}
							depositControl = true;
						}
					}
					shopcart.put("depositProductStatus", depositProductStatus);
					shopcart.put("deposit", deposit);
					shopcart.put("deductAmount", deductAmount);
					shopcart.put("subDepositOrderQuantity", subDepositOrderQuantity);
					shopcart.put("depositControl", depositControl);
					shopcart.put("productType1Id",shoppingCartCustom.getProductType1Id());
					shopcart.put("platformCouponUsable", !specialMchtIdSet.contains(shoppingCartCustom.getMchtId())); //特殊商家商品不参与平台券优惠
					productList.add(shopcart);
				}
				dataMap.put("productCouponList", productCouponList);
				dataMap.put("productList", productList);
				resultList.add(dataMap);
			}
			//获取会员已领全场通用优惠券
			Map<String, Object> platParamsMap = new HashMap<String, Object>();
			platParamsMap.put("memberId", memberId);
			List<Map<String, Object>> memberCouponDTOList= memberCouponService.getMemberUseblePlatformCoupons(platParamsMap);
			String decorateInfoId = "";
			if(CollectionUtils.isEmpty(resultList)){
				//类型 1 首页主题管 2 日常营销入口 3 商品主题馆 4 现金签到 5 砍价免费拿 6 购物车 7 消息 8 商品未上架
				Map<String,Object> adPageMap = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_6,null);
				decorateInfoId = adPageMap.get("decorateInfoId").toString();
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
			resultMap.put("decorateInfoId", decorateInfoId);
			resultMap.put("memberPlatformCoupons", memberCouponDTOList);
			resultMap.put("activityAreaList", resultList);
			resultMap.put("currentTime", new Date().getTime());
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, resultMap);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

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

			Object[] obj = {};
			this.requiredStr(obj, request);

			Integer memberId = getMemberId(request);
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
	 * @authorchenwf
	 * @date 2019年04月17日 下午2:52:07
	 */
	@RequestMapping(value = "/api/y/delShoppingCartList")
	@ResponseBody
	public ResponseMsg delShoppingCartList(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			
			Object[] obj = {"shoppingCartIdList"};
			this.requiredStr(obj, request);
			Integer memberId = getMemberId(request);
			shoppingCartService.delShoppingCartList(reqDataJson,reqPRM,memberId);

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}

}
