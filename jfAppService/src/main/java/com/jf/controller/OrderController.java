package com.jf.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.*;
import com.jf.common.utils.wechar.WXSignUtils;
import com.jf.controller.base.BaseController;
import com.jf.entity.*;
import com.jf.entity.pay.WXPayResult;
import com.jf.service.*;
import com.jf.service.allowance.MemberAllowanceService;
import com.jf.vo.PreContext;
import com.jf.vo.SVipInfo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author chenwf:
 * @date 创建时间：2017年5月20日 下午2:13:15
 * @version 1.0
 * @parameter
 * @return
 */
@Controller
public class OrderController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	/**
	 * 总订单
	 */
	@Resource
	private MemberInfoService memberInfoService;
	/**
	 * 总订单
	 */
	@Resource
	private CombineOrderService combineOrderService;
	/**
	 * 子订单
	 */
	@Resource
	private SubOrderService subOrderService;
	/**
	 * 订单明细
	 */
	@Resource
	private OrderDtlService orderDtlService;

	/**
	 * 购物车
	 */
	@Resource
	private ShoppingCartService shoppingCartService;

	/**
	 * 商品sku
	 */
	@Resource
	private ProductItemService productItemService;

	/**
	 * 商品信息
	 */
	@Resource
	private ProductService productService;

	/**
	 * 商品品牌
	 */
	@Resource
	private ProductBrandService productBrandService;

	/**
	 * 商家信息
	 */
	@Resource
	private MchtInfoService mchtInfoService;

	/**
	 * 优惠券
	 */
	@Resource
	private CouponService couponService;

	/**
	 * 满赠表
	 */
	@Resource
	private FullGiveService fullGiveService;

	/**
	 * 满减表
	 */
	@Resource
	private FullCutService fullCutService;

	/**
	 * 多买优惠
	 */
	@Resource
	private FullDiscountService fullDiscountService;

	/**
	 * 会场
	 */
	@Resource
	private ActivityAreaService activityAreaService;

	/**
	 * 用户账号信息
	 */
	@Resource
	private MemberAccountService memberAccountService;

	/**
	 * 
	 */
	@Resource
	private OrderService orderService;

	/**
	 * 默认地址信息
	 */
	@Resource
	private MemberAddressService memberAddressService;

	/**
	 * 属性值
	 */
	@Resource
	private ProductPropValueService productPropValueService;
	
	/**
	 * 支付方式
	 */
	@Resource
	private SysPaymentService sysPaymentService;
	
	/**
	 * 积分明细
	 */
	@Resource
	private IntegralDtlService integralDtlService;
	/**
	 * 订单日志
	 */
	@Resource
	private OrderLogService orderLogService;
	
	/**
	 * 积分类型
	 */
	@Resource
	private IntegralTypeService integralTypeService;
	
	@Resource
	private MemberCouponService memberCouponService;
	
	@Resource
	private RefundOrderService refundOrderService;
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	
	@Resource
	private ActivityService activityService;
	@Autowired
	private ProvinceFreightService provinceFreightService;
	@Autowired
	private ActivityProductDepositService activityProductDepositService;
	@Autowired
	private CombineDepositOrderService combineDepositOrderService;
	
	@Autowired
	private SubDepositOrderService subDepositOrderService;
	@Autowired
	private OrderPreferentialInfoService orderPreferentialInfoService;
	@Autowired
	private SingleProductActivityService singleProductActivityService;
	@Autowired
	private SvipOrderService svipOrderService;
	@Autowired
	private ShopownerService shopownerService;
	@Autowired
	private MchtProductBrandService mchtProductBrandService;

	@Autowired
	private MemberCollegeStudentCertificationService memberCollegeStudentCertificationService;
	@Autowired
	private MemberAllowanceService memberAllowanceService;
	@Autowired
	private SvipMarketingSettingService svipMarketingSettingService;
	@Autowired
	private SysParamCfgService sysParamCfgService;
	
	/**
	 * 
	 * 方法描述 ：结算信息
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月7日 下午12:45:45 
	 * @version
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/api/y/gerPayOrderInfo")
	@ResponseBody
	public ResponseMsg gerPayOrderInfo(HttpServletRequest request) {

		/**
		 * 几个比较主要的变量说明
		 *
		 * activityMap				购物车中正在进行活动的品牌商品对应的活动ActivityCustom
		 *
		 * pTypeMap					购物车商品按活动类型对应的购买数量
		 * activityTypeQuantityMap	购物车商品按活动类型对应的购买数量
		 * activityTotalAmountMap	购物车商品按活动类型对应的总金额（salePrice * 数量 - 预售抵扣金额）
		 * activityTypeMap			购物车商品按活动类型归类
		 *
		 * areaMap					购物车商品按会场对应的购买数量
		 * pMap						购物车商品按商品对应的购买数量
		 *
		 * categoryMap				购物车商品按一级品类归类
		 *
		 * skuDepositTotalMap		预付定金的规格ID对应的数量和可抵扣金额
		 * skuDepositTotalAmountMap	实际购买的预付定金的规格ID对应的可抵扣金额
		 *
		 */

		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = { "memberId", "shopCardIds", "payAmount" };
			this.requiredStr(obj, request);
			// 会员标识id
			Integer memberId = reqDataJson.getInt("memberId");
			String shopCardIds = reqDataJson.getString("shopCardIds");
			Integer version = reqPRM.getInt("version");
			String system = reqPRM.getString("system");
			// 全平台优惠券
			String mermberPlatformCouponId = "";
			if(reqDataJson.containsKey("mermberPlatformCouponId")){
				mermberPlatformCouponId = reqDataJson.getString("mermberPlatformCouponId");
			}
			Integer addressId = null;
			if(reqDataJson.containsKey("addressId") && !StringUtil.isBlank(reqDataJson.getString("addressId"))){
				addressId = reqDataJson.getInt("addressId");
			}
			int svipMarketingCartId = reqDataJson.optInt("svipMarketingCartId", 0);
			SvipMarketingSetting svipMarketingSetting = svipMarketingSettingService.getSetting();
			BigDecimal zero = new BigDecimal("0");
			BigDecimal totalProductAmount = zero;//所有商品总金额
			BigDecimal preferentialAmount = zero;//优惠金额
			BigDecimal platformSubsidyAmount = zero;//平台补贴金额
			BigDecimal freight = zero;//运费
			Set<Integer> ignoredSvipCardIdSet = orderService.getIgnoredSvipCartIdSet(reqDataJson); //获取忽略使用svip价格的购物车id
			String shopwnerEquityStr = "";
			Date date = new Date();
			MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
			Map<String, Object> addressParamsMap = new HashMap<String, Object>();
			addressParamsMap.put("memberId", memberId);
			addressParamsMap.put("addressId", addressId);
			MemberAddressCustom memberAddressCustom = memberAddressService.getAddressByMemberId(addressParamsMap);
			Map<String, Object> returnData = new HashMap<String, Object>();
			Map<String, Object> Rows = new HashMap<String, Object>();
			Map<String, Object> addressMap = new HashMap<String, Object>();
			Map<Integer,List<ShoppingCartCustom>> categoryMap = new HashMap<Integer,List<ShoppingCartCustom>>();//放置相同品类的购物车map
			List<ShoppingCartCustom> categoryList = new ArrayList<ShoppingCartCustom>();
			Map<String, List<ShoppingCartCustom>> activityTypeMap=new HashMap<String, List<ShoppingCartCustom>>();//按活动类型来归类购物车
			Map<String, BigDecimal> activityTotalAmountMap=new HashMap<String, BigDecimal>();//储存活动类型的总金额
			BigDecimal activityTotalAmount = new BigDecimal("0");//活动商品总金额
			BigDecimal totalShoppingAmount = new BigDecimal("0");//优惠后的所有商品总金额
			BigDecimal totalSalePrice = new BigDecimal("0");//所有商品总金额
			Map<String, Integer> activityTypeQuantityMap=new HashMap<String, Integer>();//储存活动类型商品的总数量
			List<ShoppingCartCustom> shoppingCartCustoms = new ArrayList<ShoppingCartCustom>();
			String recipientAddressFullName = "";
			String recipientName = "";
			String recipientPhone = "";
			String recipientAddress = "";
			Integer provinceId = null;
			if(memberAddressCustom != null){
				recipientAddressFullName = memberAddressCustom.getProvinceName() + " "
						+ memberAddressCustom.getCityName() + " " + memberAddressCustom.getCountyName() + " "
						+ memberAddressCustom.getAddress();
				recipientName = memberAddressCustom.getRecipient();
				recipientPhone = memberAddressCustom.getPhone();
				recipientAddress = memberAddressCustom.getAddress();
				addressId = memberAddressCustom.getId();
				provinceId = memberAddressCustom.getProvince();
			}
			addressMap.put("recipientName", recipientName);
			addressMap.put("recipientPhone", recipientPhone);
			addressMap.put("recipientAddress", recipientAddress);
			addressMap.put("addressId", addressId);
			addressMap.put("recipientAddressFullName", recipientAddressFullName);

			// =============================商品信息================================
			List<Integer> shopCardIdsList = new ArrayList<Integer>();
			for (String id : shopCardIds.split(",")) {
				if(!StringUtil.isBlank(id)){
					shopCardIdsList.add(Integer.valueOf(id));
				}
			}
			Map<String, Object> cartMap = new HashMap<String, Object>();
			cartMap.put("shopCardIdsList", shopCardIdsList);
			List<ShoppingCartCustom> shoppingCarts = shoppingCartService.getShopCars(cartMap);
			if (CollectionUtils.isEmpty(shoppingCarts)) {
				throw new ArgException("找不到购物车数据");
			}
			//SVIP
			SVipInfo sVipInfo = memberInfoService.isSVip(memberInfo, memberId);
			List<Integer> productIds = new ArrayList<Integer>();
			List<Integer> skuIds = new ArrayList<Integer>();
			Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();	// 商品ID对应的活动信息
			Map<String, Integer> pMap=new HashMap<String, Integer>();//这个map是按商品来归类的
			List<Map<String, Object>> freights = new ArrayList<Map<String, Object>>();

			Set<Integer> mchtIdSet = Sets.newHashSet();
			/*
			 * 底下这个循环把购物车商品按商品归类放到pMap<productId, quantity>
			 * 把有运费模板的规格加入freights
			 */
			for (ShoppingCartCustom shoppingCart : shoppingCarts) {
				Map<String, Object> freightMap=new HashMap<String, Object>();
				if(shoppingCart.getFreightTemplateId() != null){
					freightMap.put("productItemId", shoppingCart.getProductItemId());
					freightMap.put("freightTemplateId", shoppingCart.getFreightTemplateId());
					freightMap.put("quantity", shoppingCart.getQuantity());
					freights.add(freightMap);
				}
				//重新规划0与101的类型，因为0会场结束了会变成101日常销售
				if(shoppingCart.getActivityType().equals("0") || shoppingCart.getActivityType().equals("101")){
					productIds.add(shoppingCart.getProductId());
				}
				if(pMap.containsKey(shoppingCart.getProductId().toString())){
					pMap.put(shoppingCart.getProductId().toString(), pMap.get(shoppingCart.getProductId().toString())+shoppingCart.getQuantity());
				}else{
					pMap.put(shoppingCart.getProductId().toString(), shoppingCart.getQuantity());
				}
				skuIds.add(shoppingCart.getProductItemId());
				mchtIdSet.add(shoppingCart.getMchtId());
			}
			//计算运费
			Map<Integer, BigDecimal> productItemFreightMap = new HashMap<Integer, BigDecimal>();	// 各规格对应的运费
			Map<Integer, String> freightContentMap = new HashMap<Integer, String>();	// 各规格是否包邮
			Map<Integer, ProvinceFreightCustom> freightCustomMap = new HashMap<Integer, ProvinceFreightCustom>();	// 各模板对应的运费信息
			if(provinceId != null){
				Map<String, Object> map = provinceFreightService.getProductFreightAmount(freights, provinceId);
				freightContentMap = (Map<Integer, String>) map.get("freightContentMap");
				productItemFreightMap = (Map<Integer, BigDecimal>) map.get("productItemFreightMap");//每只sku对应的运费金额
				freightCustomMap = (Map<Integer, ProvinceFreightCustom>) map.get("freightCustom");//每个模板所对应的名称和总运费金额
				freight = productItemFreightMap.get(-1);//总运费设置key值为-1
			}
			//活动中的
			if(CollectionUtils.isNotEmpty(productIds)){
				Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
				paramsActivityMap.put("productIdList", productIds);
				List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
				if(CollectionUtils.isNotEmpty(activityCustoms)){
					for (ActivityCustom activityCustom : activityCustoms) {
						activityMap.put(activityCustom.getProductId().toString(), activityCustom);
					}
				}
			}
			//获取该商品是否为预售商品
			Map<Integer, BigDecimal> skuDepositTotalAmountMap = new HashMap<Integer, BigDecimal>(); //预售商品购物车sku数量所对应的定金总金额，预防A商品买了2个定金，只去支付一个尾款，该sku所对应的可抵扣金额就为1个定金抵扣
			Map<Integer, Map<String, Object>> skuDepositTotalMap = new HashMap<Integer, Map<String, Object>>(); //放置每个sku所对应的定金总金额
			Map<String, Object> skuDepositMap = new HashMap<String, Object>();
			SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
			subDepositOrderExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId).andProductItemIdIn(skuIds).andStatusIn(Arrays.asList("2", "8")); //2 定金已付   8 尾款下单后未支付取消
			List<SubDepositOrder> subDepositOrderList = subDepositOrderService.selectByExample(subDepositOrderExample);
			if(CollectionUtils.isNotEmpty(subDepositOrderList)){
				for (SubDepositOrder subDepositOrder : subDepositOrderList) {
					if(skuDepositTotalMap.containsKey(subDepositOrder.getProductItemId())){
						skuDepositMap = skuDepositTotalMap.get(subDepositOrder.getProductItemId());
						skuDepositMap.put("depositQuantity", Integer.valueOf(skuDepositMap.get("depositQuantity").toString())+1);
						skuDepositMap.put("deductAmount", new BigDecimal(skuDepositMap.get("deductAmount").toString()).add(subDepositOrder.getDeductAmount()));
					}else{
						skuDepositMap = new HashMap<String, Object>();
						skuDepositMap.put("depositQuantity", 1);
						skuDepositMap.put("deductAmount", subDepositOrder.getDeductAmount());
					}
					skuDepositTotalMap.put(subDepositOrder.getProductItemId(), skuDepositMap);
				}
			}
			Map<String, Integer> pTypeMap=new HashMap<String, Integer>();//这个map是存储每个活动类型的购买数量，给单品活动的满几件优惠用的
			Map<String, Integer> areaMap=new HashMap<String, Integer>();//这个map是储存每个会场的购买数量

			Map<Integer, MchtInfo> mchtInfoMap = orderService.buildMchtInfoMap(mchtIdSet);
			/*
			 * 底下这个循环把购物车商品重新设置活动类型activityType
			 * 并根据活动类型设置salePrice为商品的活动价或商城价，如果当前用户为VIP则salePrice再乘以VIP折扣
			 *
			 * 把购物车商品按活动类型对应的购买数量存到pTypeMap、activityTypeQuantityMap
			 * 把购物车商品按活动类型对应的总金额存到activityTotalAmountMap， （salePrice * 数量 - 预售抵扣金额）
			 * 把购物车商品按活动类型归类存到activityTypeMap
			 *
			 * 把购物车商品按会场对应的购买数量存到areaMap
			 *
			 * 把购物车商品按一级品类归类存到categoryMap
			 *
			 */
			for (ShoppingCartCustom shoppingCart : shoppingCarts) {
				MchtInfo mchtInfo = mchtInfoMap.get(shoppingCart.getMchtId());

				Integer productType1Id = shoppingCart.getProductType1Id();
				Integer quantity = shoppingCart.getQuantity();
				Integer skuId = shoppingCart.getProductItemId();
				BigDecimal svipDiscount = shoppingCart.getSvipDiscount();
				if(shoppingCart.getActivityType().equals("0") || shoppingCart.getActivityType().equals("101")){
					//重新规划0与101的类型，因为0会场结束了会变成101日常销售
					ActivityCustom activityCustom = activityMap.get(shoppingCart.getProductId().toString());
					if(activityCustom != null && activityCustom.getActivityAreaId() != null){
						if(!activityCustom.getId().equals(shoppingCart.getActivityId())){
							shoppingCart.setActivityAreaId(activityCustom.getActivityAreaId());
							shoppingCart.setActivityId(activityCustom.getId());
							shoppingCart.setActivityType("0");
							shoppingCart.setSalePrice(shoppingCart.getSalePrice());
							shoppingCartService.updateByPrimaryKeySelective(shoppingCart);
						}
						if(areaMap.containsKey(shoppingCart.getActivityAreaId().toString())){
							areaMap.put(shoppingCart.getActivityAreaId().toString(), areaMap.get(shoppingCart.getActivityAreaId().toString())+shoppingCart.getQuantity());
						}else{
							areaMap.put(shoppingCart.getActivityAreaId().toString(), shoppingCart.getQuantity());
						}
					}else{
						shoppingCart.setActivityAreaId(null);
						shoppingCart.setActivityId(null);
						shoppingCart.setActivityType("101");
						shoppingCart.setSalePrice(shoppingCart.getMallPrice());
						Map<String, Object> activityInfoMap = new HashMap<String, Object>();
						activityInfoMap.put("shopCartId", shoppingCart.getId());
						activityInfoMap.put("activityType", "101");
						shoppingCartService.updateShopCartActivityInfo(activityInfoMap);
					}
				}
				String activityType = shoppingCart.getActivityType();
				if(activityType.equals("101")){
					shoppingCart.setSalePrice(shoppingCart.getMallPrice());
				}else{
					shoppingCart.setSalePrice(shoppingCart.getSalePrice());
				}
				BigDecimal salePrice = shoppingCart.getSalePrice();
				if ((sVipInfo.isSVip() && svipDiscount != null && svipDiscount.compareTo(zero) > 0
						&& (CollectionUtils.isEmpty(ignoredSvipCardIdSet) || !ignoredSvipCardIdSet.contains(shoppingCart.getId()))) //svip用户,未排除svip的使用
						|| (!sVipInfo.isSVip() && shoppingCart.getId() == svipMarketingCartId && svipMarketingSetting != null) //非svip用户，营销绑定
				) {
					//使用svip折扣
					BigDecimal svipSalePrice = productService.getProductSvipSalePrice(salePrice, svipDiscount, activityType);
					if (svipSalePrice.compareTo(zero) > 0) {
						salePrice = svipSalePrice;
						shoppingCart.setSalePrice(salePrice);
					}
				}
				totalShoppingAmount = totalShoppingAmount.add(salePrice.multiply(new BigDecimal(shoppingCart.getQuantity()+"")));
				totalSalePrice = totalShoppingAmount;
				if(pTypeMap.containsKey(activityType)){
					pTypeMap.put(activityType, pTypeMap.get(activityType)+shoppingCart.getQuantity());
				}else{
					pTypeMap.put(activityType, shoppingCart.getQuantity());
				}
				//按活动类型归类购物车
				if(activityTypeMap.containsKey(activityType)){
					shoppingCartCustoms = activityTypeMap.get(activityType);
					shoppingCartCustoms.add(shoppingCart);
				}else{
					shoppingCartCustoms = new ArrayList<ShoppingCartCustom>();
					shoppingCartCustoms.add(shoppingCart);
				}
				activityTypeMap.put(activityType,shoppingCartCustoms);
				//按活动类型储存活动总金额
				if(activityTotalAmountMap.containsKey(activityType)){
					activityTotalAmount = salePrice.multiply(new BigDecimal(shoppingCart.getQuantity()+"")).add(activityTotalAmountMap.get(activityType));
				}else{
					activityTotalAmount = salePrice.multiply(new BigDecimal(shoppingCart.getQuantity()+""));
				}
				activityTotalAmountMap.put(activityType,activityTotalAmount);
				if(skuDepositTotalMap.containsKey(skuId)){
					skuDepositMap = skuDepositTotalMap.get(skuId);
					Integer depositQuantity = Integer.valueOf(skuDepositMap.get("depositQuantity").toString());
					BigDecimal deductAmount = new BigDecimal(skuDepositMap.get("deductAmount").toString());
					if(quantity >= depositQuantity){
						skuDepositTotalAmountMap.put(skuId, deductAmount);
					}else{
						skuDepositTotalAmountMap.put(skuId, deductAmount.multiply(new BigDecimal(quantity+"")).divide(new BigDecimal(depositQuantity+"")));
					}
					activityTotalAmountMap.put(activityType, activityTotalAmountMap.get(activityType).subtract(deductAmount));
				}
				if(!StringUtil.isBlank(mermberPlatformCouponId)){
					//map放置不同品列的购物车集合
					if(!activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY) 
							&& !activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)
							&& !activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
						if(categoryMap.isEmpty() || !categoryMap.containsKey(productType1Id)){
							categoryList = new ArrayList<ShoppingCartCustom>();
							categoryList.add(shoppingCart);
						}else{
							categoryList = categoryMap.get(productType1Id);
							categoryList.add(shoppingCart);		
						}
						categoryMap.put(productType1Id, categoryList);
					}
				}
				if(activityTypeQuantityMap.containsKey(activityType)){
					activityTypeQuantityMap.put(activityType, activityTypeQuantityMap.get(activityType)+quantity);
				}else{
					activityTypeQuantityMap.put(activityType, quantity);
				}
				if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
					//因为新用户专享商品是不经过购物车，直接跳转到结算页面的。
					SingleProductActivity singleProductActivity = singleProductActivityService.selectByPrimaryKey(shoppingCart.getSingleProductActivityId());
					if(singleProductActivity != null && singleProductActivity.getPlatformPreferential() != null){
						platformSubsidyAmount = platformSubsidyAmount.add(singleProductActivity.getPlatformPreferential());
					}
				}

				if (mchtInfo != null && Const.MCHT_TYPE_POP.equals(mchtInfo.getMchtType())) {
					// pop佣金比例（pop使用）
					Map<String, Object> popFeeRateParamsMap = new HashMap<>();
					popFeeRateParamsMap.put("productId", shoppingCart.getProductId());
					popFeeRateParamsMap.put("activityId", shoppingCart.getActivityId());
					popFeeRateParamsMap.put("mchtId", shoppingCart.getMchtId());
					popFeeRateParamsMap.put("brandId", shoppingCart.getBrandId());
					popFeeRateParamsMap.put("productType1Id", shoppingCart.getProductType1Id());
					popFeeRateParamsMap.put("productType2Id", shoppingCart.getProductType2Id());
					popFeeRateParamsMap.put("productType3Id", shoppingCart.getProductTypeId());
					BigDecimal feeRate = mchtProductBrandService.getPopFeeRate(popFeeRateParamsMap);
					shoppingCart.setPopFeeRate(feeRate);
				}
			}
			PreContext preContext = new PreContext();
			preContext.setMemberAllowance(memberAllowanceService.getMemberAllowanceBalance(memberId));

			/** ================================楼层活动优惠（商家优惠）(包含定金优惠)Start========================*/
			Map<String, Object> activityPreferentialReturnMap = orderPreferentialInfoService.getPreferentialInfo(activityTypeMap, activityTotalAmountMap, memberId, activityMap, null, reqPRM, reqDataJson, skuDepositTotalAmountMap, skuDepositTotalMap, "2", mchtInfoMap, preContext);
			List<Map<String, Object>> salePriceMapList = (List<Map<String, Object>>) activityPreferentialReturnMap.get("salePriceMapList");
			activityTotalAmountMap = (Map<String, BigDecimal>) activityPreferentialReturnMap.get("activityTotalAmountMap");
			BigDecimal totalProductPreferentialAmount = new BigDecimal(activityPreferentialReturnMap.get("totalProductPreferentialAmount").toString());// 所有商品优惠总和
			totalShoppingAmount = totalShoppingAmount.subtract(totalProductPreferentialAmount);
			/** ================================平台券优惠（平台优惠）Start========================*/
			Map<String, Object> platCouponMap = memberCouponService.computingPlatPreferentialInfo(false, salePriceMapList, totalShoppingAmount, mermberPlatformCouponId, memberId, null, activityTotalAmountMap, "2", reqPRM, mchtIdSet);
			totalShoppingAmount = new BigDecimal(platCouponMap.get("totalShoppingAmount").toString());//到这里已经计算出了总的实付支付金额 = 总的购物车金额-总的活动优惠金额-平台优惠金额
			salePriceMapList = (List<Map<String, Object>>) platCouponMap.get("salePriceMapList");
			activityTotalAmountMap = (Map<String, BigDecimal>) platCouponMap.get("activityTotalAmountMap");
			/** ================================平台券优惠（平台补贴）Start========================*/
			totalShoppingAmount = totalShoppingAmount.subtract(platformSubsidyAmount);
			/** ================================店长权益（平台优惠）Start========================*/
			Map<String, Object> shopownerEquityMap = shopownerService.computingShopownerEquity(reqPRM,memberId,salePriceMapList,activityTotalAmountMap,"2");
			BigDecimal shopwnerEquityAmount = new BigDecimal(shopownerEquityMap.get("totalShopwnerEquityAmount").toString());
			salePriceMapList = (List<Map<String, Object>>) shopownerEquityMap.get("salePriceMapList");
			totalShoppingAmount = totalShoppingAmount.subtract(shopwnerEquityAmount);
			activityTotalAmountMap = (Map<String, BigDecimal>) shopownerEquityMap.get("activityTotalAmountMap");
			/** ================================积分（积分优惠）Start========================*/
			BigDecimal integralAmount = orderService.calculationIntegralAmount(activityTotalAmountMap,memberId,totalShoppingAmount,activityTypeQuantityMap,null,"2");
			//需要大学生认证
			boolean collegeStudentStatus = false;
			String collegeStudentUrl = "";
			boolean cscStatus = true;
			MemberCollegeStudentCertificationExample cscExample = new MemberCollegeStudentCertificationExample();
			cscExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId).andStatusNotEqualTo("3");
			cscExample.setLimitStart(0);
			cscExample.setLimitSize(1);
			List<MemberCollegeStudentCertification> memberCollegeStudentCertification = memberCollegeStudentCertificationService.selectByExample(cscExample);
			if(memberCollegeStudentCertification.size() > 0) {
				cscStatus = false;
			}

			boolean hasSvipMarketingProduct = false;
			List<Map<String, Object>> productMapList = new ArrayList<Map<String, Object>>();
			for (ShoppingCartCustom shoppingCart : shoppingCarts) {
				String activityType = shoppingCart.getActivityType();
				String isInvalidProduct = "0";//未失效
				String invalidReason = "";//失效原因
				String limitBuyNum = "";//失效具体原因
				List<Integer> propValIdList = new ArrayList<Integer>();
				Map<String, Object> productMap = new HashMap<String, Object>();
				Integer stock = shoppingCart.getStock();
				Integer lockedAmount = shoppingCart.getLockedAmount();
				String productName = shoppingCart.getProductName();
				String skuPic = StringUtil.getPic(shoppingCart.getProductPic(), "S");
				Integer productId = shoppingCart.getProductId();
				String[] propValIds = shoppingCart.getPropValId().toString().split(",");
				int quantity = shoppingCart.getQuantity();
				Integer freightTemplateId = shoppingCart.getFreightTemplateId();
				BigDecimal saleOrMallPrice = shoppingCart.getSalePrice();
				for (String propValId : propValIds) {
					propValIdList.add(Integer.valueOf(propValId));
				}
				// 查找规格
				Map<String, Object> propValIdsMap = new HashMap<String, Object>();
				propValIdsMap.put("propValIdList", propValIdList);
				List<ProductPropValueCustom> productPropValueCustoms = productPropValueService
						.getProductPropdesc(propValIdsMap);
				String productPropdesc = "";
				if (productPropValueCustoms != null && productPropValueCustoms.size() > 0) {
					for (ProductPropValueCustom productPropValueCustom : productPropValueCustoms) {
						productPropdesc += productPropValueCustom.getPropName() + " : "
								+ productPropValueCustom.getPropValue() + " ";
					}
				}
				//已付总定金 depositSum、抵用总金额 deductAmountSum
				BigDecimal depositSum = new BigDecimal("0");
				BigDecimal deductAmountSum = new BigDecimal("0");
				String depositStatus = "0";
				int fi = 0; //预售商品件数
				for(SubDepositOrder subDepositOrder : subDepositOrderList) {
					if(shoppingCart.getMchtId().intValue() == subDepositOrder.getMchtId().intValue() 
							&& shoppingCart.getProductItemId().intValue() == subDepositOrder.getProductItemId().intValue()
							&& fi < shoppingCart.getQuantity().intValue()) {
						fi++;
						depositSum = depositSum.add(subDepositOrder.getDeposit());
						deductAmountSum = deductAmountSum.add(subDepositOrder.getDeductAmount());
					}
				}
				if(fi != 0) {
					depositStatus = "1";
				}
				productMap.put("depositStatus", depositStatus);
				productMap.put("depositSum", depositSum);
				productMap.put("deductAmountSum", deductAmountSum);
				productMap.put("fi", fi);
				Integer stockSum = stock-lockedAmount+fi;
				if(stockSum <= 0){
					isInvalidProduct = "1";
					invalidReason = "您选择的商品库存不足";
					limitBuyNum = "库存不足";
				}else{
					//库存足够了，在判断是否超出限购
					Map<String, String> invalidPMap = productService.isInvalidProduct(productId,activityType,memberId,date,pMap,pTypeMap,activityMap,areaMap,"2",null);
					isInvalidProduct = invalidPMap.get("isInvalidProduct");
					invalidReason = invalidPMap.get("invalidReason");
					limitBuyNum = invalidPMap.get("limitBuyNum");
				}
				//每只sku的运费数据
				String freightName = "（包邮）";
				String freightType = "1";
				if(freightContentMap.get(shoppingCart.getProductItemId()) != null && "2".equals(freightContentMap.get(shoppingCart.getProductItemId()))){
					freightName = "（不包邮）";
					freightType = "2";
				}
				totalProductAmount = totalProductAmount.add(saleOrMallPrice);
				saleOrMallPrice = productService.getSaleOrMallPrice(activityType, version, system, saleOrMallPrice, platformSubsidyAmount, reqPRM);
				productMap.put("freightName", freightName);
				productMap.put("freightType", freightType);
				productMap.put("productName", productName);
				productMap.put("skuPic", skuPic);
				productMap.put("quantity", quantity);
				productMap.put("salePrice", saleOrMallPrice);
				productMap.put("productPropdesc", productPropdesc);
				productMap.put("productId", productId);
				productMap.put("stock", stockSum);
				productMap.put("isInvalidProduct", isInvalidProduct);
				productMap.put("invalidReason", invalidReason);
				productMap.put("limitBuyNum", limitBuyNum);
				productMap.put("freightTemplateId", freightTemplateId);
				if (!sVipInfo.isSVip() && shoppingCart.getId() == svipMarketingCartId && svipMarketingSetting != null) { //非svip用户，且营销绑定
					hasSvipMarketingProduct = true;
					productMap.put("svipMarketingTip", StringUtil.buildMsg("本商品已参与{}元换购SVIP年卡", svipMarketingSetting.getPrice()));
					productMap.put("svipMarketing", "1"); //svip营销绑定：1、绑定的商品 2、svip虚拟商品
				}
				productMapList.add(productMap);

				if(cscStatus) {
					//需要大学生认证的商品
					SysParamCfg sysParamCfg = DataDicUtil.getSysParamCfgModel("COLLEGE_STUDENT_CERTIFICATION_PRODUCT_ID", productId.toString());
					if(sysParamCfg != null) {
						collegeStudentStatus = true;
						collegeStudentUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/certification/index.html";
					}
				}
			}
			if (hasSvipMarketingProduct) {
				//svip绑定营销，尾部追加svip虚拟商品信息
				Map<String, Object> svipProduct = Maps.newHashMap();
				svipProduct.put("depositStatus", "0");
				svipProduct.put("depositSum", BigDecimal.ZERO);
				svipProduct.put("deductAmountSum", BigDecimal.ZERO);
				svipProduct.put("fi", 0);
				svipProduct.put("freightName", "");
				svipProduct.put("freightType", 1);
				svipProduct.put("productName", StringUtil.buildMsg("醒购SVIP年卡{}年", StringUtil.toCNNumber(svipMarketingSetting.getYear())));
				svipProduct.put("skuPic", StringUtil.getPic(sysParamCfgService.findValByCode("SVIP_MARKETING_PRODUCT_IMG"),""));
				svipProduct.put("quantity", 1);
				svipProduct.put("salePrice", svipMarketingSetting.getPrice());
				svipProduct.put("productPropdesc", "");
				svipProduct.put("productId", 0);
				svipProduct.put("stock", 1);
				svipProduct.put("isInvalidProduct", "0");
				svipProduct.put("invalidReason", "");
				svipProduct.put("limitBuyNum", "");
				svipProduct.put("freightTemplateId", 0);
				svipProduct.put("svipMarketing", "2");
				svipProduct.put("svipMarketingTip", "（SVIP年卡购买后不支持退款）");
				productMapList.add(svipProduct);
			}
			
			// 支付方式
			List<Map<String, Object>> payMapList = sysPaymentService.getPayMethod("",reqPRM);
			//金额和积分的兑换比例
			Integer iChargr = 0;
			if(!memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_TOURIST)){
				IntegralType integralType = integralTypeService.findModel(Integer.valueOf(Const.INTEGRAL_TYPE_ORDER_PAYMENT));
				if(integralType != null){
					String type = integralType.getIntType();
					if(type.equals("2")){
						iChargr = integralType.getiCharge() == null ? 0 : integralType.getiCharge();
					}
				}
			}
			if(shopwnerEquityAmount.compareTo(zero) > 0){
				shopwnerEquityStr = "可使用店长权益可省"+shopwnerEquityAmount.stripTrailingZeros()+"元";
			}
			Integer coinNum = integralAmount.multiply(new BigDecimal(iChargr)).intValue();
			preferentialAmount = totalSalePrice.subtract(totalShoppingAmount.add(shopwnerEquityAmount));

			if (hasSvipMarketingProduct) { //绑定营销svip售价不参与优惠，最后追加
				totalSalePrice = totalSalePrice.add(svipMarketingSetting.getPrice());
			}
			Rows.put("addressMap", addressMap);
			Rows.put("productMapList", productMapList);
			Rows.put("payMapList", payMapList);
			Rows.put("integral",integralAmount);
			Rows.put("coinNum",coinNum);
			Rows.put("freight", freight);//运费
			Rows.put("totalProductAmount", totalSalePrice);//商品金额
			Rows.put("preferentialAmount", preferentialAmount);//优惠金额 = 楼层优惠+定金+平台+平台补贴
			Rows.put("platformSubsidyAmount", "");//平台补贴金额(这个字段前端暂时不显示，屏蔽掉)
			Rows.put("freightCustomMap", freightCustomMap);
			Rows.put("shopwnerEquityStr", shopwnerEquityStr);
			Rows.put("shopwnerEquityAmount", shopwnerEquityAmount);
			Rows.put("collegeStudentStatus", collegeStudentStatus);
			Rows.put("collegeStudentUrl", collegeStudentUrl);
			returnData.put("Rows", Rows);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
		}
	}
	

	
	/**
	 * 获取预支付信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getPayDepositOrderInfo")
	@ResponseBody
	public ResponseMsg getPayDepositOrderInfo(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = { "productId","quantity"};
			this.requiredStr(obj, request);
			
			Date currentDate = new Date();
			Map<String,Object> map = new HashMap<String,Object>();
			Integer productId = reqDataJson.getInt("productId");
			Integer quantity = reqDataJson.getInt("quantity");
			Integer productItemId = null;
			Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
			paramsActivityMap.put("productId", productId);
			paramsActivityMap.put("type", "1");//这个字段用来获取未开始的活动数据
			List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				ActivityCustom activityCustom = activityCustoms.get(0);
				if(reqDataJson.containsKey("productItemId") && !StringUtil.isBlank(reqDataJson.getString("productItemId"))){
					productItemId = reqDataJson.getInt("productItemId");
				}else{
					if(!"1".equals(activityCustom.getIsSingleProp())){
						throw new ArgException("该商品已经卖光啦，再看看其他商品吧~");
					}
					ProductItemExample productItemExample=new ProductItemExample();
					productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productId);
					List<ProductItem> productItems=productItemService.selectByExample(productItemExample);
					productItemId=productItems.get(0).getId();
				}
				Date activityBeginDate = activityCustom.getActivityBeginTime();
				if(activityCustom.getActivityBeginTime().compareTo(currentDate) <= 0){
					//活动已开始
					throw new ArgException("预售已结束");
				}
				if(!"2".equals(activityCustom.getPreSellAuditStatus())){
					//预售审核未通过
					throw new ArgException("预售未开始");
				}
				ActivityProductDepositExample activityProductDepositExample = new ActivityProductDepositExample();
				activityProductDepositExample.createCriteria().andActivityIdEqualTo(activityCustom.getId()).andProductIdEqualTo(productId).andDelFlagEqualTo("0");
				List<ActivityProductDeposit> deposits = activityProductDepositService.selectByExample(activityProductDepositExample);
				if(CollectionUtils.isNotEmpty(deposits)){
					ActivityProductDeposit activityProductDeposit = deposits.get(0);
					//商品信息
					Map<String,Object> goodsMap = new HashMap<String,Object>();
					goodsMap.put("productItemId", productItemId);
					List<ProductCustom> productCustoms = productService.getGoodsBasicsInfo(goodsMap);
					if(CollectionUtils.isEmpty(productCustoms)){
						throw new ArgException("商品已售罄");
					}
					ProductCustom p = productCustoms.get(0);
					String productName = p.getName();//商品名称
					String skuPic = StringUtil.getPic(p.getPic(), "S");//sku图片
					String productPropdesc = p.getProductPropdesc();//sku描述
					BigDecimal salePrice = p.getSalePrice().multiply(new BigDecimal(quantity.toString())).setScale(2);//活动价格
					BigDecimal deposit = activityProductDeposit.getDeposit().multiply(new BigDecimal(quantity.toString())).setScale(2);//定金
					BigDecimal deductAmount = activityProductDeposit.getDeductAmount().multiply(new BigDecimal(quantity.toString())).setScale(2);//抵扣金额
					BigDecimal tailMoney = salePrice.subtract(deductAmount);//尾款
					String stageOne = "阶段一：定金￥"+deposit+" 定金抵用￥"+deductAmount;
					String stageTwo = "阶段二：尾款￥"+tailMoney+"其他优惠可在尾款使用";
					String payTailMoneyDate = DateUtil.getFormatDate(activityBeginDate, "MM月dd日 HH:mm:ss")+"可开始支付尾款";
					List<Map<String, Object>> payMapList = sysPaymentService.getPayMethod("",reqPRM);
					map.put("payMapList", payMapList);
					map.put("skuPic", skuPic);
					map.put("productPropdesc", productPropdesc);
					map.put("productName", productName);
					map.put("productId", productId);
					map.put("stageOne", stageOne);
					map.put("stageTwo", stageTwo);
					map.put("payTailMoneyDate", payTailMoneyDate);
					map.put("deposit", deposit);
					map.put("quantity", quantity);
					map.put("salePrice", p.getSalePrice());
				}else{
					throw new ArgException("预售未开始");
				}
			}else{
				throw new ArgException("预售未开始");
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
		}
	}
	
	/**
	 * 预售定金支付，获取预支付信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/submitDepositOrderPayment")
	@ResponseBody
	public ResponseMsg submitDepositOrderPayment(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = { "productId","totalQuantity","payId"};
			this.requiredStr(obj, request);
			
			return orderService.submitDepositOrderPayment(reqPRM,reqDataJson,request);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/y/submitDepositAfterPayment")
	@ResponseBody
	public ResponseMsg submitDepositAfterPayment (HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = { "memberId", "combineDepositOrderId", "payId"};
			this.requiredStr(obj, request);

			return orderService.submitDepositAfterPayment(reqDataJson, request);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage(),"提交订单失败");
		}
	}
	
	
	@RequestMapping(value = "/api/y/submitOrderPayment20170811")
	@ResponseBody
	public ResponseMsg submitOrderPayment20170811(HttpServletRequest request) {
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		String system = "";
		Integer version = 0;
		try {
			String combineOrderId = reqDataJson.getString("combineOrderId");
			if(!StringUtil.isBlank(combineOrderId)){
				Object[] obj = { "memberId", "combineOrderId", "ip", "payId"};
				this.requiredStr(obj, request);

				return orderService.submitPaymentAgain(reqDataJson,request);
			}
			if(reqPRM.containsKey("system")){
				system = reqPRM.getString("system");
			}
			if(reqPRM.containsKey("version")){
				version = reqPRM.getInt("version");
			}
			Object[] obj = { "memberId", "sourceClient", "isUserIntegral", "payId", "payAmount",
					"ip", "addressId", "shopCardIds", "dataList"};
			this.requiredStr(obj, request);
			
			return orderService.submitOrderPayment(reqPRM,reqDataJson,request,system,version);
		} catch (ArgException args) {
			String error = ResponseMsg.ERROR;
			if(ResponseMsg.ERROR_4005.equals(args.getExceptionType())){
				if((Const.ANDROID.equals(system) && version > 39) || (Const.IOS.equals(system) && version > 42)){
					error = ResponseMsg.ERROR_4005;
				}
			}
			logger.info("订单结算参数："+reqDataJson.getString("shopCardIds").toString());
			return new ResponseMsg(error, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "提交订单失败","提交订单失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：提交待付款单据
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月22日 上午10:01:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/submitAfterPayment")
	@ResponseBody
	public ResponseMsg submitAfterPayment (HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = { "memberId", "combineOrderId", "ip", "payId"};
			this.requiredStr(obj, request);

			return orderService.submitAfterPayment(reqPRM,reqDataJson, request);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage(),"提交订单失败");
		}
	}

	/**
	 * 
	 * 方法描述 ：支付宝回调地址
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年6月5日 上午10:51:27
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/n/alipayNotifyUrl")
	@ResponseBody
	public String alipayNotifyUrl(HttpServletRequest request) {
		try {
			boolean success = true;
			Date date = new Date();
			//退款编号，退款的异步才有值
			String out_biz_no = request.getParameter("out_biz_no");
			Enumeration<?> pNames = request.getParameterNames();
			Map<String, String> param = new HashMap<String, String>();
			while (pNames.hasMoreElements()) {
				String pName = (String) pNames.nextElement();
				param.put(pName, request.getParameter(pName));
			}
			
			// 获取到返回的所有参数 先判断是否交易成功trade_status 再做签名校验
			if ("TRADE_SUCCESS".equals(request.getParameter("trade_status")) && StringUtil.isBlank(out_biz_no)) {
				// 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
				String outTradeNo = request.getParameter("out_trade_no");
				// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
				String totalAmount = request.getParameter("total_amount");
				// 3、校验通知中的seller_id（或者seller_email)
				// 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
				String sellerEmail = request.getParameter("seller_email");
				// 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
				String appId = request.getParameter("app_id");
				
				String tradeNo = request.getParameter("trade_no");
				//交易付款时间
				String gmt_payment = request.getParameter("gmt_payment");
				Date payDate = new Date();
				if(!StringUtil.isBlank(gmt_payment)){
					payDate = DateUtil.getDate(gmt_payment);
				}
				boolean signVerified = AlipaySignature.rsaCheckV1(param,
						alipayConfigUtil.getProperty("ALIPAY_PUBLIC_KEY"), AlipayConstants.CHARSET_UTF8,
						AlipayConstants.SIGN_TYPE_RSA2); // 校验签名是否正确

				CombineOrderExample orderExample = new CombineOrderExample();
				orderExample.createCriteria().andCombineOrderCodeEqualTo(outTradeNo);
				List<CombineOrder> combineOrders = combineOrderService.selectByExample(orderExample);
				SvipOrder svipOrder = null;
				if (signVerified) {
					// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
					// 1:判断订单号在数据库是否存在
					if (CollectionUtils.isEmpty(combineOrders)) {
						success = false;
					}
					// 2:判断交易金额是否一致
					CombineOrder combineOrder = combineOrders.get(0);
					if (combineOrder.getSvipOrderId() != null) {
						svipOrder = svipOrderService.selectByPrimaryKey(combineOrder.getSvipOrderId());
					}
					BigDecimal orderPayAmount = combineOrder.getTotalPayAmount();
					if (svipOrder != null) {
						orderPayAmount = orderPayAmount.add(svipOrder.getPayAmount());
					}
					if (success && orderPayAmount.compareTo(new BigDecimal(totalAmount)) != 0) {
						success = false;
					}
					// 3:判断appid是否一致
					if (!appId.equals(alipayConfigUtil.getProperty("ALIPAY_APP_ID")) && success) {
						success = false;
					}
					// 4:判断sellerEmail是否一致
					if (!sellerEmail.equals(alipayConfigUtil.getProperty("ALIPAY_SELLER_EMAIL")) && success) {
						success = false;
					}
					// 5:判断支付状态已经支付过了，就不在修改
					if (!combineOrder.getStatus().equals(Const.COMBINE_ORDER_STATUS_NOT_PAID)&&!combineOrder.getStatus().equals(Const.COMBINE_ORDER_STATUS_CANCEL) && success) {
						return "success";
					}

					if (success) {
						orderService.updateOrderStatus(success, combineOrder, svipOrder, tradeNo, 1, payDate);
					} else {
						orderService.updateOrderStatus(success, combineOrder, svipOrder, tradeNo, 1, null);
					}
					
				}else {
					success = false;
				}
			}else if (!StringUtil.isBlank(out_biz_no)) {
				// 1、校验通知中的seller_id（或者seller_email)
				// 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
				String sellerEmail = request.getParameter("seller_email");
				
				String gmt_refund = request.getParameter("gmt_refund");
				String refundDate = null;
				if(StringUtil.isBlank(gmt_refund)){
					refundDate = DateUtil.getFormatDate(date, "yyyy-MM-dd HH:mm:ss");
				}else{
					refundDate = gmt_refund.substring(0, 19);
				}
				// 2、验证app_id是否为该商户本身。上述1、2有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
				String appId = request.getParameter("app_id");
				boolean signVerified = AlipaySignature.rsaCheckV1(param,
						alipayConfigUtil.getProperty("ALIPAY_PUBLIC_KEY"), AlipayConstants.CHARSET_UTF8,
						AlipayConstants.SIGN_TYPE_RSA2); // 校验签名是否正确
				if(signVerified 
						&& !StringUtil.isBlank(out_biz_no)
						&& appId.equals(alipayConfigUtil.getProperty("ALIPAY_APP_ID"))
						&& sellerEmail.equals(alipayConfigUtil.getProperty("ALIPAY_SELLER_EMAIL"))){
					//退款异步
//					RefundOrderExample refundOrderExample = new RefundOrderExample();
//					refundOrderExample.createCriteria().andDelFlagEqualTo("0").andRefundReqNoEqualTo(out_biz_no);
//					List<RefundOrder> refundOrders = refundOrderService.selectByExample(refundOrderExample);
//					if(CollectionUtils.isNotEmpty(refundOrders)){
//						RefundOrder refundOrder = refundOrders.get(0);
//						refundOrder.setRemarks(refundDate);
//						refundOrderService.updateByPrimaryKeySelective(refundOrder);
//						logger.info("支付宝退款流水"+out_biz_no+"退款成功");
//					}else{
//					}
					success = false;
				}
			} else {
				success = false;
			}
			if(success){
				return "success";
			}else{
				return "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}

	/**
	 * 
	 * 方法描述 ：微信回调地址
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年6月5日 上午10:52:24
	 * @version
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/n/wecharNotifyUrl")
	@ResponseBody
	public void wecharNotifyUrl(HttpServletRequest request, HttpServletResponse response) {
		try {
			BufferedReader reader = request.getReader();
			String line = "";
			StringBuffer inputString = new StringBuffer();
			PrintWriter writer = response.getWriter();

			while ((line = reader.readLine()) != null) {
				inputString.append(line);
			}
			if (reader == null) {
				reader.close();
			}

			boolean success = true;
			String localSign = "";
			if (!StringUtil.isBlank(inputString.toString())) {
				List<Element> list = JdomParseXmlUtils.getWXPayResultElement(inputString.toString());
				SortedMap<String, Object> smap = new TreeMap<String, Object>();
				WXPayResult wxPayResult = getWXPayResult(list);
				logger.debug("微信支付回调参数：{}", JSON.toJSONString(wxPayResult));
				if(CollectionUtils.isNotEmpty(list)){
					String wxSign = wxPayResult.getSign();
					for (Element e : list) {
						smap.put(e.getName(), e.getText());
					}
					localSign = WXSignUtils.createSign("utf-8", smap);
					if(!wxSign.equals(localSign)){
						success = false;
						logger.info("校验签名失败");
					}
				}else{
					success = false;
				}
				CombineOrderExample combineOrderExample = new CombineOrderExample();
				combineOrderExample.createCriteria().andCombineOrderCodeEqualTo(wxPayResult.getOut_trade_no())
				.andDelFlagEqualTo("0");
				List<CombineOrder> combineOrders = combineOrderService.selectByExample(combineOrderExample);
				SvipOrder svipOrder = null;
				// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
				// 1:判断订单号在数据库是否存在
				if (CollectionUtils.isEmpty(combineOrders) && success) {
					success = false;
				}
				// 2:判断交易金额是否一致
				CombineOrder combineOrder = combineOrders.get(0);
				if (combineOrder.getSvipOrderId() != null) {
					svipOrder = svipOrderService.selectByPrimaryKey(combineOrder.getSvipOrderId());
				}
				BigDecimal orderPayAmount = combineOrder.getTotalPayAmount();
				if (svipOrder != null) {
					orderPayAmount = orderPayAmount.add(svipOrder.getPayAmount());
				}
				if (success && orderPayAmount.multiply(new BigDecimal(100)).compareTo(new BigDecimal(wxPayResult.getTotal_fee())) != 0) {
					success = false;
				}
				// 3:判断appid是否一致
				if (!wxPayResult.getAppid().equals(wecharConfigUtil.getProperty("WX_APP_ID")) && success) {
					success = false;
				}
				// 4:判断Mch_id是否一致
				if (!wxPayResult.getMch_id().equals(wecharConfigUtil.getProperty("WX_MCH_ID")) && success) {
					success = false;
				}
				// 5:判断支付状态已经支付过了，就不在修改
				boolean isPay = false;
				if (!combineOrder.getStatus().equals(Const.COMBINE_ORDER_STATUS_NOT_PAID)&&!combineOrder.getStatus().equals(Const.COMBINE_ORDER_STATUS_CANCEL) && success) {
					isPay = true;
					success = false;
 				}
				if ("SUCCESS".equalsIgnoreCase(wxPayResult.getReturn_code()) && success) {
					// 修改订单状态
					String time_end = wxPayResult.getTime_end();
					Date payDate = new Date();
					if (!StringUtil.isBlank(time_end)) {
						payDate = DateUtil.getFormatString(time_end, "yyyyMMddHHmmss");
					}
					orderService.updateOrderStatus(true, combineOrder, svipOrder, wxPayResult.getTransaction_id(), 2, payDate);
					writer.write(HttpXmlUtils.wxResponseXmlInfo("SUCCESS", "OK"));
				} else {
					if(!isPay){
						System.out.println("---------微信支付返回Fail----------" + wxPayResult.getReturn_msg());
						orderService.updateOrderStatus(false, combineOrder, svipOrder, wxPayResult.getTransaction_id(), 2, null);
						writer.write(HttpXmlUtils.wxResponseXmlInfo("FAIL", wxPayResult.getReturn_msg()));
					}else{
						writer.write(HttpXmlUtils.wxResponseXmlInfo("SUCCESS", "OK"));
					}
				}
			}else{
				writer.write(HttpXmlUtils.wxResponseXmlInfo("FAIL","未获取到微信返回的结果"));
			}
			if (writer != null) {
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：支付宝微信预售定金支付回调地址
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年6月5日 上午10:51:27
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/n/alipayDepositNotifyUrl")
	@ResponseBody
	public String alipayDepositNotifyUrl(HttpServletRequest request) {
		try {
			boolean failure = true;
			//退款编号，退款的异步才有值
			String out_biz_no = request.getParameter("out_biz_no");
			Enumeration<?> pNames = request.getParameterNames();
			Map<String, String> param = new HashMap<String, String>();
			while (pNames.hasMoreElements()) {
				String pName = (String) pNames.nextElement();
				param.put(pName, request.getParameter(pName));
			}
			// 获取到返回的所有参数 先判断是否交易成功trade_status 再做签名校验
			if ("TRADE_SUCCESS".equals(request.getParameter("trade_status")) && StringUtil.isBlank(out_biz_no)) {
				// 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
				String outTradeNo = request.getParameter("out_trade_no");
				// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
				String totalAmount = request.getParameter("total_amount");
				// 3、校验通知中的seller_id（或者seller_email)
				// 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
				String sellerEmail = request.getParameter("seller_email");
				// 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
				String appId = request.getParameter("app_id");
				
				String tradeNo = request.getParameter("trade_no");
				//交易付款时间
				String gmt_payment = request.getParameter("gmt_payment");
				Date payDate = new Date();
				if(!StringUtil.isBlank(gmt_payment)){
					payDate = DateUtil.getDate(gmt_payment);
				}
				boolean signVerified = AlipaySignature.rsaCheckV1(param,
						alipayConfigUtil.getProperty("ALIPAY_PUBLIC_KEY"), AlipayConstants.CHARSET_UTF8,
						AlipayConstants.SIGN_TYPE_RSA2); // 校验签名是否正确
				
				CombineDepositOrderExample orderExample = new CombineDepositOrderExample();
				orderExample.createCriteria().andCombineDepositOrderCodeEqualTo(outTradeNo).andDelFlagEqualTo("0");
				List<CombineDepositOrder> orders = combineDepositOrderService.selectByExample(orderExample);
				if (signVerified) {
					// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
					// 1:判断订单号在数据库是否存在
					if (CollectionUtils.isEmpty(orders) && failure) {
						logger.info("订单异常:"+outTradeNo);
						failure = false;
					}
					// 2:判断交易金额是否一致
					CombineDepositOrder order = orders.get(0);
					if (order.getTotalDeposit().doubleValue() != Double.valueOf(totalAmount) && failure) {
						logger.info("金额校验不一致:"+outTradeNo);
						failure = false;
					}
					// 3:判断appid是否一致
					if (!appId.equals(alipayConfigUtil.getProperty("ALIPAY_APP_ID")) && failure) {
						logger.info("appId校验不一致"+outTradeNo);
						failure = false;
					}
					// 4:判断sellerEmail是否一致
					if (!sellerEmail.equals(alipayConfigUtil.getProperty("ALIPAY_SELLER_EMAIL")) && failure) {
						logger.info("商户校验不一致:"+outTradeNo);
						failure = false;
					}
					// 5:判断支付状态已经支付过了，就不在修改
					if (!order.getStatus().equals(Const.COMBINE_DEPOSIT_ORDER_STATUS_NO_PAID)&&!order.getStatus().equals(Const.COMBINE_DEPOSIT_ORDER_STATUS_CANCEL) && failure) {
						return "success";
					}

					if (failure) {
						orderService.updateDepositOrderStatus(request, failure, order, tradeNo,1,payDate);
					} else {
						orderService.updateDepositOrderStatus(request, failure, order, tradeNo,1,null);
					}
					
				}else {
					logger.info("签名校验不一致:"+outTradeNo);
					failure = false;
				}
			} else {
				failure = false;
			}
			if(failure){
				return "success";
			}else{
				return "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	
	/**
	 * 
	 * 方法描述 ：微信预售定金支付回调地址
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年6月5日 上午10:52:24
	 * @version
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/n/wxDepositNotifyUrl")
	@ResponseBody
	public void wxDepositNotifyUrl(HttpServletRequest request, HttpServletResponse response) {
		try {
			BufferedReader reader = request.getReader();
			String line = "";
			StringBuffer inputString = new StringBuffer();
			PrintWriter writer = response.getWriter();

			while ((line = reader.readLine()) != null) {
				inputString.append(line);
			}
			if (reader == null) {
				reader.close();
			}
			boolean failure = true;
			String localSign = "";
			if (!StringUtil.isBlank(inputString.toString())) {
				List<Element> list = JdomParseXmlUtils.getWXPayResultElement(inputString.toString());
				SortedMap<String, Object> smap = new TreeMap<String, Object>();
				WXPayResult wxPayResult = getWXPayResult(list);
				if(CollectionUtils.isNotEmpty(list)){
					String wxSign = wxPayResult.getSign();
					for (Element e : list) {
						smap.put(e.getName(), e.getText());
					}
					localSign = WXSignUtils.createSign("utf-8", smap);
					if(!wxSign.equals(localSign)){
						failure = false;
						logger.info("校验签名失败");
					}
				}else{
					failure = false;
				}
				CombineDepositOrderExample orderExample = new CombineDepositOrderExample();
				orderExample.createCriteria().andCombineDepositOrderCodeEqualTo(wxPayResult.getOut_trade_no()).andDelFlagEqualTo("0");
				List<CombineDepositOrder> orders = combineDepositOrderService.selectByExample(orderExample);
				// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
				// 1:判断订单号在数据库是否存在
				if (CollectionUtils.isEmpty(orders) && failure) {
					logger.info("订单异常："+wxPayResult.getOut_trade_no());
					failure = false;
				}
				// 2:判断交易金额是否一致
				CombineDepositOrder order = orders.get(0);
				if (order.getTotalDeposit().multiply(new BigDecimal(100)).doubleValue() != Double.valueOf(wxPayResult.getTotal_fee()) && failure) {
					logger.info("金额校验不一致："+wxPayResult.getOut_trade_no());
					failure = false;
				}
				// 3:判断appid是否一致
				if (!wxPayResult.getAppid().equals(wecharConfigUtil.getProperty("WX_APP_ID")) && failure) {
					logger.info("appId校验不一致："+wxPayResult.getOut_trade_no());
					failure = false;
				}
				// 4:判断Mch_id是否一致
				if (!wxPayResult.getMch_id().equals(wecharConfigUtil.getProperty("WX_MCH_ID")) && failure) {
					logger.info("商户号校验不一致："+wxPayResult.getOut_trade_no());
					failure = false;
				}
				// 5:判断支付状态已经支付过了，就不在修改
				boolean isPay = false;
				if (!order.getStatus().equals(Const.COMBINE_DEPOSIT_ORDER_STATUS_NO_PAID)&&!order.getStatus().equals(Const.COMBINE_DEPOSIT_ORDER_STATUS_CANCEL) && failure) {
					isPay = true;
					failure = false;
 				}
				
				if ("SUCCESS".equalsIgnoreCase(wxPayResult.getReturn_code()) && failure) {
					// 修改订单状态
					if(failure){
						String time_end = wxPayResult.getTime_end();
						Date payDate = new Date();
						if(!StringUtil.isBlank(time_end)){
							payDate = DateUtil.getFormatString(time_end, "yyyyMMddHHmmss");
						}
						orderService.updateDepositOrderStatus(request, true, order, wxPayResult.getTransaction_id(), 2, payDate);
						writer.write(HttpXmlUtils.wxResponseXmlInfo("SUCCESS", "OK"));
					}
				} else {
					if(!isPay){
						System.out.println("---------微信支付返回Fail----------" + wxPayResult.getReturn_msg());
						orderService.updateDepositOrderStatus(request, false, order,wxPayResult.getTransaction_id(),2,null);
						writer.write(HttpXmlUtils.wxResponseXmlInfo("FAIL", wxPayResult.getReturn_msg()));
					}else{
						writer.write(HttpXmlUtils.wxResponseXmlInfo("SUCCESS", "OK"));
					}
				}
			}else{
				writer.write(HttpXmlUtils.wxResponseXmlInfo("FAIL","未获取到微信返回的结果"));
			}
			if (writer != null) {
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private WXPayResult getWXPayResult(List<Element> list) {
		WXPayResult wxPayResult = null;
		if (list != null && list.size() > 0) {
			wxPayResult = new WXPayResult();
			for (Element element : list) {

				if ("return_code".equals(element.getName())) {
					wxPayResult.setReturn_code(element.getText());
				}

				if ("return_msg".equals(element.getName())) {
					wxPayResult.setReturn_msg(element.getText());
				}

				if ("appid".equals(element.getName())) {
					wxPayResult.setAppid(element.getText());
				}

				if ("mch_id".equals(element.getName())) {
					wxPayResult.setMch_id(element.getText());
				}
				
				if ("out_trade_no".equals(element.getName())) {
					wxPayResult.setOut_trade_no(element.getText());
				}
				if ("transaction_id".equals(element.getName())) {
					wxPayResult.setTransaction_id(element.getText());
				}
				
				if ("total_fee".equals(element.getName())) {
					wxPayResult.setTotal_fee(Integer.valueOf(element.getText()));
				}

				if ("nonce_str".equals(element.getName())) {
					wxPayResult.setNonce_str(element.getText());
				}

				if ("sign".equals(element.getName())) {
					wxPayResult.setSign(element.getText());
				}

				if ("result_code".equals(element.getName())) {
					wxPayResult.setResult_code(element.getText());
				}
				if ("time_end".equals(element.getName())) {
					wxPayResult.setTime_end(element.getText());
				}

			}
		}
		return wxPayResult;
	}
	
	/**
	 * 购买SVIP会员
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/submitSvipOrderPayment")
	@ResponseBody
	public ResponseMsg submitSvipOrderPayment(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = { "svipSettingId","memberId","payId"};
			this.requiredStr(obj, request);
			
			return orderService.submitSvipOrderPayment(reqPRM,reqDataJson,request);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：微信预售定金支付回调地址
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年6月5日 上午10:52:24
	 * @version
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/n/wxSvipOrderNotifyUrl")
	@ResponseBody
	public void wxSvipOrderNotifyUrl(HttpServletRequest request, HttpServletResponse response) {
		try {
			BufferedReader reader = request.getReader();
			String line = "";
			StringBuffer inputString = new StringBuffer();
			PrintWriter writer = response.getWriter();

			while ((line = reader.readLine()) != null) {
				inputString.append(line);
			}
			if (reader == null) {
				reader.close();
			}
			boolean failure = true;
			String localSign = "";
			if (!StringUtil.isBlank(inputString.toString())) {
				List<Element> list = JdomParseXmlUtils.getWXPayResultElement(inputString.toString());
				SortedMap<String, Object> smap = new TreeMap<String, Object>();
				WXPayResult wxPayResult = getWXPayResult(list);
				if(CollectionUtils.isNotEmpty(list)){
					String wxSign = wxPayResult.getSign();
					for (Element e : list) {
						smap.put(e.getName(), e.getText());
					}
					localSign = WXSignUtils.createSign("utf-8", smap);
					if(!wxSign.equals(localSign)){
						failure = false;
						logger.info("校验签名失败");
					}
				}else{
					failure = false;
				}
				SvipOrderExample svipOrderExample = new SvipOrderExample();
				svipOrderExample.createCriteria().andOrderCodeEqualTo(wxPayResult.getOut_trade_no()).andStatusEqualTo("0").andDelFlagEqualTo("0");
				List<SvipOrder> orders = svipOrderService.selectByExample(svipOrderExample);
				// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
				// 1:判断订单号在数据库是否存在
				if (CollectionUtils.isEmpty(orders) && failure) {
					logger.info("订单异常："+wxPayResult.getOut_trade_no());
					failure = false;
				}
				// 2:判断交易金额是否一致
				SvipOrder order = orders.get(0);
				if (order.getPayAmount().multiply(new BigDecimal(100)).doubleValue() != Double.valueOf(wxPayResult.getTotal_fee()) && failure) {
					logger.info("金额校验不一致："+wxPayResult.getOut_trade_no());
					failure = false;
				}
				// 3:判断appid是否一致
				if (!wxPayResult.getAppid().equals(wecharConfigUtil.getProperty("WX_APP_ID")) && failure) {
					logger.info("appId校验不一致："+wxPayResult.getOut_trade_no());
					failure = false;
				}
				// 4:判断Mch_id是否一致
				if (!wxPayResult.getMch_id().equals(wecharConfigUtil.getProperty("WX_MCH_ID")) && failure) {
					logger.info("商户号校验不一致："+wxPayResult.getOut_trade_no());
					failure = false;
				}
				// 5:判断支付状态已经支付过了，就不在修改
				boolean isPay = false;
				if (!order.getStatus().equals(Const.COMBINE_ORDER_STATUS_NOT_PAID) && failure) {
					isPay = true;
					failure = false;
 				}
				
				if ("SUCCESS".equalsIgnoreCase(wxPayResult.getReturn_code()) && failure) {
					// 修改订单状态
					if(failure){
						String time_end = wxPayResult.getTime_end();
						Date payDate = new Date();
						if(!StringUtil.isBlank(time_end)){
							payDate = DateUtil.getFormatString(time_end, "yyyyMMddHHmmss");
						}
						orderService.updateSvipOrderStatus(request, true, order, wxPayResult.getTransaction_id(), 2, payDate);
						writer.write(HttpXmlUtils.wxResponseXmlInfo("SUCCESS", "OK"));
					}
				} else {
					if(!isPay){
						System.out.println("---------微信支付返回Fail----------" + wxPayResult.getReturn_msg());
						orderService.updateSvipOrderStatus(request, false, order,wxPayResult.getTransaction_id(),2,null);
						writer.write(HttpXmlUtils.wxResponseXmlInfo("FAIL", wxPayResult.getReturn_msg()));
					}else{
						writer.write(HttpXmlUtils.wxResponseXmlInfo("SUCCESS", "OK"));
					}
				}
			}else{
				writer.write(HttpXmlUtils.wxResponseXmlInfo("FAIL","未获取到微信返回的结果"));
			}
			if (writer != null) {
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 方法描述 ：支付宝回调地址
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年6月5日 上午10:51:27
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/n/alipaySvipOrderNotifyUrl")
	@ResponseBody
	public String alipaySvipOrderNotifyUrl(HttpServletRequest request) {
		try {
			boolean failure = true;
			Date date = new Date();
			//退款编号，退款的异步才有值
			String out_biz_no = request.getParameter("out_biz_no");
			Enumeration<?> pNames = request.getParameterNames();
			Map<String, String> param = new HashMap<String, String>();
			while (pNames.hasMoreElements()) {
				String pName = (String) pNames.nextElement();
				param.put(pName, request.getParameter(pName));
			}
			System.out.println("---------------->"+"1");
			// 获取到返回的所有参数 先判断是否交易成功trade_status 再做签名校验
			if ("TRADE_SUCCESS".equals(request.getParameter("trade_status")) && StringUtil.isBlank(out_biz_no)) {
				// 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
				String outTradeNo = request.getParameter("out_trade_no");
				// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
				String totalAmount = request.getParameter("total_amount");
				// 3、校验通知中的seller_id（或者seller_email)
				// 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
				String sellerEmail = request.getParameter("seller_email");
				// 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
				String appId = request.getParameter("app_id");
				
				String tradeNo = request.getParameter("trade_no");
				//交易付款时间
				String gmt_payment = request.getParameter("gmt_payment");
				Date payDate = new Date();
				if(!StringUtil.isBlank(gmt_payment)){
					payDate = DateUtil.getDate(gmt_payment);
				}
				boolean signVerified = AlipaySignature.rsaCheckV1(param,
						alipayConfigUtil.getProperty("ALIPAY_PUBLIC_KEY"), AlipayConstants.CHARSET_UTF8,
						AlipayConstants.SIGN_TYPE_RSA2); // 校验签名是否正确
				System.out.println("---------------->"+"2");
				SvipOrderExample svipOrderExample = new SvipOrderExample();
				svipOrderExample.createCriteria().andOrderCodeEqualTo(outTradeNo).andStatusEqualTo("0").andDelFlagEqualTo("0");
				List<SvipOrder> orders = svipOrderService.selectByExample(svipOrderExample);
				if (signVerified) {
					System.out.println("---------------->"+"3");
					// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
					// 1:判断订单号在数据库是否存在
					if (CollectionUtils.isEmpty(orders) && failure) {
						System.out.println("---------------->"+"4");
						failure = false;
					}
					// 2:判断交易金额是否一致
					SvipOrder order = orders.get(0);
					if (order.getPayAmount().doubleValue() != Double.valueOf(totalAmount) && failure) {
						System.out.println("---------------->"+"5");
						failure = false;
					}
					// 3:判断appid是否一致
					if (!appId.equals(alipayConfigUtil.getProperty("ALIPAY_APP_ID")) && failure) {
						System.out.println("---------------->"+"6");
						failure = false;
					}
					// 4:判断sellerEmail是否一致
					if (!sellerEmail.equals(alipayConfigUtil.getProperty("ALIPAY_SELLER_EMAIL")) && failure) {
						System.out.println("---------------->"+"7");
						failure = false;
					}
					// 5:判断支付状态已经支付过了，就不在修改
					if (!order.getStatus().equals(Const.COMBINE_ORDER_STATUS_NOT_PAID) && failure) {
						System.out.println("---------------->"+"8");
						return "success";
					}

					if (failure) {
						System.out.println("---------------->"+"9");
						orderService.updateSvipOrderStatus(request, true, order, tradeNo, 1, payDate);
					} else {
						orderService.updateSvipOrderStatus(request, false, order,tradeNo,1,null);
					}
					
				}else {
					failure = false;
				}
			}
			if(failure){
				return "success";
			}else{
				return "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
}
