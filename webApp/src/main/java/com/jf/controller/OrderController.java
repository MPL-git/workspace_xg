package com.jf.controller;

import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.common.collect.Sets;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.*;
import com.jf.common.utils.wechar.WXSignUtils;
import com.jf.entity.*;
import com.jf.entity.pay.WXPayResult;
import com.jf.service.*;
import com.jf.service.allowance.MemberAllowanceService;
import com.jf.vo.PreContext;
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
	 * 积分类型
	 */
	@Resource
	private IntegralTypeService integralTypeService;
	
	@Resource
	private MemberCouponService memberCouponService;
	
	@Resource
	private ActivityService activityService;
	@Resource
	private ProvinceFreightService provinceFreightService;
	@Resource
	private SubDepositOrderService subDepositOrderService;
	@Resource
	private OrderPreferentialInfoService orderPreferentialInfoService;
	@Resource
	private CutPriceOrderService cutPriceOrderService;
	@Resource
	private CombineDepositOrderService combineDepositOrderService;
	@Resource
	private ActivityProductDepositService activityProductDepositService;
	@Resource
	private ShopownerService shopownerService;
	@Resource
	private ShopownerOrderService shopownerOrderService;
	@Resource
	private MchtProductBrandService mchtProductBrandService;

	@Autowired
	private MemberCollegeStudentCertificationService memberCollegeStudentCertificationService;
	@Autowired
	private MemberAllowanceService memberAllowanceService;

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
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Object[] obj = {"shopCardIds", "payAmount" };
			this.requiredStr(obj, request);
			// 会员标识id
			Integer memberId = getMemberId(request);
			reqDataJson.put("memberId", memberId);
			String shopCardIds = reqDataJson.getString("shopCardIds").replaceAll("[\\[\\]\"]", "");
			Double payAmount = reqDataJson.getDouble("payAmount");
			// 全平台优惠券
			String mermberPlatformCouponId = "";
			if(reqDataJson.containsKey("mermberPlatformCouponId")){
				mermberPlatformCouponId = reqDataJson.getString("mermberPlatformCouponId");
			}
			Integer addressId = null;
			if(reqDataJson.containsKey("addressId") && !StringUtil.isBlank(reqDataJson.getString("addressId"))){
				addressId = reqDataJson.getInt("addressId");
			}
			Integer cutOrderId = null;
			if(reqDataJson.containsKey("cutOrderId") && !StringUtil.isBlank(reqDataJson.getString("cutOrderId"))){
				cutOrderId = reqDataJson.getInt("cutOrderId");
			}
			BigDecimal zero = new BigDecimal("0");
			BigDecimal totalProductAmount = zero;//所有商品总金额
			BigDecimal preferentialAmount = zero;//优惠金额
			BigDecimal platformSubsidyAmount = zero;//平台补贴金额
			BigDecimal cutAssistanceAmount = zero;//助力大减价助力金额
			BigDecimal freight = zero;//运费
			String shopwnerEquityStr = "";
			Date date = new Date();
			MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
			Map<String, Object> returnData = new HashMap<String, Object>();
			Map<String, Object> Rows = new HashMap<String, Object>();
			Map<Integer,List<ShoppingCartCustom>> categoryMap = new HashMap<Integer,List<ShoppingCartCustom>>();//放置相同品类的购物车map
			List<ShoppingCartCustom> categoryList = new ArrayList<ShoppingCartCustom>();
			Map<String, List<ShoppingCartCustom>> activityTypeMap=new HashMap<String, List<ShoppingCartCustom>>();//按活动类型来归类购物车
			Map<String, BigDecimal> activityTotalAmountMap=new HashMap<String, BigDecimal>();//储存活动类型的总金额
			BigDecimal activityTotalAmount = new BigDecimal("0");//活动商品总金额
			BigDecimal totalShoppingAmount = new BigDecimal("0");//优惠后的所有商品总金额
			BigDecimal totalSalePrice = new BigDecimal("0");//所有商品总金额
			Map<String, Integer> activityTypeQuantityMap=new HashMap<String, Integer>();//储存活动类型商品的总数量
			List<ShoppingCartCustom> shoppingCartCustoms = new ArrayList<ShoppingCartCustom>();
			// =============================地址信息================================
			Map<String, Object> addressMap = memberAddressService.getMemberDefaultAddress(memberId, addressId);
			Integer provinceId = null;
			if(addressMap.get("provinceId") != null){
				provinceId = Integer.parseInt(addressMap.get("provinceId").toString());
			}
			// =============================商品信息================================
			List<Integer> shopCardIdsList = new ArrayList<Integer>();
			for (String id : shopCardIds.split(",")) {
				shopCardIdsList.add(Integer.valueOf(id));
			}
			Map<String, Object> cartMap = new HashMap<String, Object>();
			cartMap.put("shopCardIdsList", shopCardIdsList);
			List<ShoppingCartCustom> shoppingCarts = shoppingCartService.getShopCars(cartMap);
			if (CollectionUtils.isEmpty(shoppingCarts)) {
				throw new ArgException("找不到购物车数据");
			}
			//SVIP
			String isSvip = memberInfoService.getIsSvip(memberInfo, memberId);
			List<Integer> productIds = new ArrayList<Integer>();
			List<Integer> skuIds = new ArrayList<Integer>();
			Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();
			Map<String, Integer> pMap=new HashMap<String, Integer>();//这个map是按商品来归类的
			List<Map<String, Object>> freights = new ArrayList<Map<String, Object>>();
			Set<Integer> mchtIdSet = Sets.newHashSet();
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
			Map<Integer, BigDecimal> productItemFreightMap = new HashMap<Integer, BigDecimal>();
			Map<Integer, String> freightContentMap = new HashMap<Integer, String>();
			Map<Integer, ProvinceFreightCustom> freightCustomMap = new HashMap<Integer, ProvinceFreightCustom>();
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
			Map<Integer, MchtInfo> mchtInfoMap = orderService.buildMchtInfoMap(mchtIdSet);
			Map<String, Integer> pTypeMap=new HashMap<String, Integer>();//这个map是活动类型来归类的，因为单品活动是针对整个活动来定义的
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
				if("1".equals(isSvip) && svipDiscount != null && svipDiscount.compareTo(zero) > 0){//svip折扣
					BigDecimal svipSalePrice = productService.getProductSvipSalePrice(salePrice,svipDiscount,activityType);
					if(svipSalePrice.compareTo(zero) > 0){
						salePrice = svipSalePrice;
						shoppingCart.setSalePrice(salePrice);
					}
				}
				totalShoppingAmount = totalShoppingAmount.add(salePrice.multiply(new BigDecimal(shoppingCart.getQuantity()+"")));
				totalSalePrice = totalShoppingAmount;
				
				if(pMap.containsKey(shoppingCart.getActivityType())){
					pTypeMap.put(shoppingCart.getActivityType(), pMap.get(shoppingCart.getProductId().toString())+shoppingCart.getQuantity());
				}else{
					pTypeMap.put(shoppingCart.getActivityType(), shoppingCart.getQuantity());
				}
				//按活动类型归类购物车
				if(activityTypeMap.containsKey(shoppingCart.getActivityType())){
					shoppingCartCustoms = activityTypeMap.get(shoppingCart.getActivityType());
					shoppingCartCustoms.add(shoppingCart);
				}else{
					shoppingCartCustoms = new ArrayList<ShoppingCartCustom>();
					shoppingCartCustoms.add(shoppingCart);
				}
				activityTypeMap.put(shoppingCart.getActivityType(),shoppingCartCustoms);
				//按活动类型储存活动总金额
				if(activityTotalAmountMap.containsKey(shoppingCart.getActivityType())){
					activityTotalAmount = shoppingCart.getSalePrice().multiply(new BigDecimal(shoppingCart.getQuantity()+"")).add(activityTotalAmountMap.get(shoppingCart.getActivityType()));
				}else{
					activityTotalAmount = shoppingCart.getSalePrice().multiply(new BigDecimal(shoppingCart.getQuantity()+""));
				}
				activityTotalAmountMap.put(shoppingCart.getActivityType(),activityTotalAmount);
				if(skuDepositTotalMap.containsKey(skuId)){
					skuDepositMap = skuDepositTotalMap.get(skuId);
					Integer depositQuantity = Integer.valueOf(skuDepositMap.get("depositQuantity").toString());
					BigDecimal deductAmount = new BigDecimal(skuDepositMap.get("deductAmount").toString());
					if(quantity >= depositQuantity){
						skuDepositTotalAmountMap.put(skuId, deductAmount);
					}else{
						skuDepositTotalAmountMap.put(skuId, deductAmount.multiply(new BigDecimal(quantity+"")).divide(new BigDecimal(depositQuantity+"")));
					}
					activityTotalAmountMap.put(shoppingCart.getActivityType(), activityTotalAmountMap.get(shoppingCart.getActivityType()).subtract(deductAmount));
				}
				if(!StringUtil.isBlank(mermberPlatformCouponId)){
					//map放置不同品列的购物车集合
					if(!shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY) 
							&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)
							&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
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
				if(activityTypeQuantityMap.containsKey(shoppingCart.getActivityType())){
					activityTypeQuantityMap.put(shoppingCart.getActivityType(), activityTypeQuantityMap.get(shoppingCart.getActivityType())+quantity);
				}else{
					activityTypeQuantityMap.put(shoppingCart.getActivityType(), quantity);
				}
				if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){
					if(cutOrderId != null){
						Map<String, Object> cutMap = cutPriceOrderService.getCutOrderPreferentialAmount(null,cutOrderId, memberId,"1",null);
						cutAssistanceAmount = (BigDecimal) cutMap.get("preferentialAmount");
						activityTotalAmountMap.put(shoppingCart.getActivityType(), activityTotalAmountMap.get(shoppingCart.getActivityType()).subtract(cutAssistanceAmount));
					}else{
						throw new ArgException("系统异常，你稍后再试！");
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
			Map<String, Object> platCouponMap = memberCouponService.computingPlatPreferentialInfo(false, salePriceMapList, totalShoppingAmount, mermberPlatformCouponId, memberId, null, activityTotalAmountMap, "2", mchtIdSet);
			totalShoppingAmount = new BigDecimal(platCouponMap.get("totalShoppingAmount").toString());//到这里已经计算出了总的实付支付金额 = 总的购物车金额-总的活动优惠金额-平台优惠金额
			salePriceMapList = (List<Map<String, Object>>) platCouponMap.get("salePriceMapList");
			activityTotalAmountMap = (Map<String, BigDecimal>) platCouponMap.get("activityTotalAmountMap");
			/** ================================平台券优惠（平台补贴）Start========================*/
			totalShoppingAmount = totalShoppingAmount.subtract(platformSubsidyAmount).subtract(cutAssistanceAmount);
			/** ================================店长权益（平台优惠）Start========================*/
			Map<String, Object> shopownerEquityMap = shopownerService.computingShopownerEquity(reqPRM,memberId,salePriceMapList,activityTotalAmountMap,"2");
			BigDecimal shopwnerEquityAmount = new BigDecimal(shopownerEquityMap.get("totalShopwnerEquityAmount").toString());
			salePriceMapList = (List<Map<String, Object>>) shopownerEquityMap.get("salePriceMapList");
			activityTotalAmountMap = (Map<String, BigDecimal>) shopownerEquityMap.get("activityTotalAmountMap");
			totalShoppingAmount = totalShoppingAmount.subtract(shopwnerEquityAmount);
			/** ================================积分（积分优惠）Start========================*/
			BigDecimal integralAmount = orderService.calculationIntegralAmount(activityTotalAmountMap,memberId,totalShoppingAmount,activityTypeQuantityMap,null,"2");

			//需要大学生认证
			boolean collegeStudentStatus = false;
			boolean cscStatus = true;
			MemberCollegeStudentCertificationExample cscExample = new MemberCollegeStudentCertificationExample();
			cscExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId).andStatusNotEqualTo("3");
			cscExample.setLimitStart(0);
			cscExample.setLimitSize(1);
			List<MemberCollegeStudentCertification> memberCollegeStudentCertification = memberCollegeStudentCertificationService.selectByExample(cscExample);
			if(memberCollegeStudentCertification.size() > 0) {
				cscStatus = false;
			}

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
				//
				Integer stockSum = stock-lockedAmount;
				if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){
					stockSum = 1;//注：助力大减价领取任务时已经冻结库存了，这边设置为1，前端stockSum<=0会先显示库存不足，不让用户去买，故做此操作
				}else{
					if(stockSum <= 0){
						isInvalidProduct = "1";
						invalidReason = "您选择的商品库存不足";
						limitBuyNum = "库存不足";
					}else{
						//库存足够了，在判断是否超出限购
						Map<String, String> invalidPMap = productService.isInvalidProduct(productId,activityType,memberId,shoppingCarts,date,shoppingCart.getActivityId(),pMap,pTypeMap);
						isInvalidProduct = invalidPMap.get("isInvalidProduct");
						invalidReason = invalidPMap.get("invalidReason");
						limitBuyNum = invalidPMap.get("limitBuyNum");
					}
				}
				//每只sku的运费数据
				String freightName = "（包邮）";
				String freightType = "1";
				if(freightContentMap.get(shoppingCart.getProductItemId()) != null && "2".equals(freightContentMap.get(shoppingCart.getProductItemId()))){
					freightName = "（不包邮）";
					freightType = "2";
				}
				totalProductAmount = totalProductAmount.add(saleOrMallPrice);
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
				
				productMapList.add(productMap);

				if(cscStatus) {
					//需要大学生认证的商品
					SysParamCfg sysParamCfg = DataDicUtil.getSysParamCfgModel("COLLEGE_STUDENT_CERTIFICATION_PRODUCT_ID", productId.toString());
					if(sysParamCfg != null) {
						collegeStudentStatus = true;
					}
				}
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
			returnData.put("Rows", Rows);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/api/y/submitOrderPayment")
	@ResponseBody
	public ResponseMsg submitOrderPayment(HttpServletRequest request,HttpServletResponse response) {
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		try {
			String combineOrderId = reqDataJson.getString("combineOrderId");
			if(!StringUtil.isBlank(combineOrderId)){
				Object[] obj = {"combineOrderId", "payId"};
				this.requiredStr(obj, request);

				return orderService.submitPaymentAgain(reqDataJson,request);
			}
			Object[] obj = {"sourceClient", "isUserIntegral", "payId", "payAmount", "addressId", "shopCardIds", "dataList"};
			this.requiredStr(obj, request);
			
			boolean isHasStock = true;
			//先判断是否有库存
			String shopCardIds = reqDataJson.getString("shopCardIds").replaceAll("[\\[\\]\"]", "");
			// 会员标识id
			Integer memberId = getMemberId(request);
			reqDataJson.put("memberId", memberId);
			List<Integer> shopCardIdsList = new ArrayList<Integer>();
			for (String id : shopCardIds.split(",")) {
				shopCardIdsList.add(Integer.valueOf(id));
			}
			
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
			ShoppingCartExample shoppingCartExampleSubOrders = new ShoppingCartExample();
			shoppingCartExampleSubOrders.createCriteria().andIdIn(shopCardIdsList).andDelFlagEqualTo("0");
			List<ShoppingCart> shoppingCartSubOrders = shoppingCartService.selectByExample(shoppingCartExampleSubOrders);
			if(CollectionUtils.isNotEmpty(shoppingCartSubOrders)){
				for (ShoppingCart shoppingCart : shoppingCartSubOrders) {
					if(!shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){
						Integer productItemId = shoppingCart.getProductItemId();
						ProductItem productItem = productItemService.selectByPrimaryKey(productItemId);
						Product product = productService.selectByPrimaryKey(productItem.getProductId());
						String auditStatus = product.getAuditStatus() == null ? "" : product.getAuditStatus();
						String status = product.getStatus() == null ? "" : product.getStatus();
						Map<String, Object> map = new HashMap<String, Object>();
						Integer productId = productItem.getProductId();
						Integer stock = productItem.getStock();//库存
						Integer lockedAmount = productItem.getLockedAmount();//冻结
						stock = stock - lockedAmount;
						if(shoppingCart.getQuantity() > stock){
							isHasStock = false;
							stock = 0;
						}else{
							if(!auditStatus.equals("2") || !status.equals("1")){
								isHasStock = false;
								stock = 0;
							}
						}
						map.put("stock", stock);
						map.put("productId", productId);
						dataList.add(map);
					}else{
						Integer version = reqPRM.getInt("version");
						String system = reqPRM.getString("system");
						if((system.equals(Const.ANDROID) && version <= 49) || system.equals(Const.IOS) && version <= 52){
							throw new ArgException("请下载app最新版本");
						}
					}
				}
			}
			
			if(!isHasStock){
				Map<String, Object> payMap = new HashMap<>();
				payMap.put("isHasStock", isHasStock);
				payMap.put("dataList", dataList);
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,payMap);
			}
			return orderService.submitOrderPayment(reqPRM,reqDataJson,request,memberId);
		} catch (ArgException args) {
			String error = ResponseMsg.ERROR;
			if(ResponseMsg.ERROR_4005.equals(args.getExceptionType())){
				error = ResponseMsg.ERROR_4005;
			}
			logger.info("订单结算参数："+reqDataJson.getString("shopCardIds").toString());
			args.printStackTrace();
			return new ResponseMsg(error, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, "订单提交失败");
		}
	}
	
	/**
	 * 
	 * 方法描述 ：签到领取奖品计算页面信息
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月22日 上午10:01:06 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/y/getSignInPayInfo")
	@ResponseBody
	public ResponseMsg getSignInPayInfo (HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数

			Integer memberId = getMemberId(request);
			Map<String, Object> map = orderService.getSignInPayInfo(reqPRM,reqDataJson,memberId);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args) {
			return new ResponseMsg(ResponseMsg.ERROR, args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, e.getMessage(),"提交订单失败");
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

			Object[] obj = {"combineOrderId", "payId"};
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
			
			return orderService.submitDepositOrderPayment(reqPRM,reqDataJson,request,getMemberId(request));
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
	@RequestMapping(value = "/api/y/submitShopownerOrderPayment")
	@ResponseBody
	public ResponseMsg submitShopownerOrderPayment(HttpServletRequest request) {
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"paymentId", "salesmanId"};
			this.requiredStr(obj, request);
			
			return orderService.submitShopownerOrderPayment(reqPRM,reqDataJson,request,getMemberId(request));
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

			Object[] obj = {"combineDepositOrderId", "payId"};
			this.requiredStr(obj, request);

			return orderService.submitDepositAfterPayment(reqDataJson, request);
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
			boolean failure = true;
			Date date = new Date();
			//退款编号，退款的异步才有值
			String out_biz_no = request.getParameter("out_biz_no");
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
				Enumeration<?> pNames = request.getParameterNames();
				Map<String, String> param = new HashMap<String, String>();
				while (pNames.hasMoreElements()) {
					String pName = (String) pNames.nextElement();
					param.put(pName, request.getParameter(pName));
				}
				boolean signVerified = AlipaySignature.rsaCheckV1(param,
						alipayConfigUtil.getProperty("ALIPAY_PUBLIC_KEY"), AlipayConstants.CHARSET_UTF8,
						AlipayConstants.SIGN_TYPE_RSA2); // 校验签名是否正确

				CombineOrderExample orderExample = new CombineOrderExample();
				orderExample.createCriteria().andCombineOrderCodeEqualTo(outTradeNo);
				List<CombineOrder> combineOrders = combineOrderService.selectByExample(orderExample);
				if (signVerified) {
					// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
					// 1:判断订单号在数据库是否存在
					if (CollectionUtils.isEmpty(combineOrders) && failure) {
						failure = false;
					}
					// 2:判断交易金额是否一致
					CombineOrder combineOrder = combineOrders.get(0);
					if (combineOrder.getTotalPayAmount().doubleValue() != Double.valueOf(totalAmount) && failure) {
						failure = false;
					}
					// 3:判断appid是否一致
					if (!appId.equals(alipayConfigUtil.getProperty("ALIPAY_APP_ID")) && failure) {
						failure = false;
					}
					// 4:判断sellerEmail是否一致
					if (!sellerEmail.equals(alipayConfigUtil.getProperty("ALIPAY_SELLER_EMAIL")) && failure) {
						failure = false;
					}
					// 5:判断支付状态已经支付过了，就不在修改
					if (!combineOrder.getStatus().equals(Const.COMBINE_ORDER_STATUS_NOT_PAID)&&!combineOrder.getStatus().equals(Const.COMBINE_ORDER_STATUS_CANCEL) && failure) {
						return "success";
					}

					if (failure) {
						orderService.updateOrderStatus(failure, combineOrder, tradeNo,payDate);
					} else {
						orderService.updateOrderStatus(failure, combineOrder, tradeNo,null);
					}

				} else {
					failure = false;
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
				Enumeration<?> pNames = request.getParameterNames();
				Map<String, String> param = new HashMap<String, String>();
				while (pNames.hasMoreElements()) {
					String pName = (String) pNames.nextElement();
					param.put(pName, request.getParameter(pName));
				}
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

			boolean failure = true;
			String localSign = "";
			if (!StringUtil.isBlank(inputString.toString())) {
				List<Element> list = JdomParseXmlUtils.getWXPayResultElement(inputString.toString());
				SortedMap<String, Object> smap = new TreeMap<String, Object>();
				WXPayResult wxPayResult = getWXPayResult(list);
				//JSAPI ,MWEB
				if(CollectionUtils.isNotEmpty(list)){
					for (Element e : list) {
						smap.put(e.getName(), e.getText());
					}
					String wxSign = wxPayResult.getSign();
					String tradeType = wxPayResult.getTrade_type().trim();
					String key = "";
					if("JSAPI".equalsIgnoreCase(tradeType)){
						key = WeixinUtil.key;
					}else if("MWEB".equalsIgnoreCase(tradeType)){
						key = wecharConfigUtil.getProperty("WX_KEY");
					}
					localSign = WXSignUtils.createSign("utf-8", smap,key);
					if(!wxSign.equals(localSign)){
						failure = false;
						logger.info("校验签名失败");
					}
				}else{
					failure = false;
				}
				CombineOrderExample combineOrderExample = new CombineOrderExample();
				combineOrderExample.createCriteria().andCombineOrderCodeEqualTo(wxPayResult.getOut_trade_no())
				.andDelFlagEqualTo("0");
				List<CombineOrder> combineOrders = combineOrderService.selectByExample(combineOrderExample);
				// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
				// 1:判断订单号在数据库是否存在
				if (CollectionUtils.isEmpty(combineOrders) && failure) {
					failure = false;
				}
				// 2:判断交易金额是否一致
				CombineOrder combineOrder = combineOrders.get(0);
				if (combineOrder.getTotalPayAmount().multiply(new BigDecimal(100)).doubleValue() != Double.valueOf(wxPayResult.getTotal_fee()) && failure) {
					failure = false;
				}
				// 3:判断appid是否一致
				// 4:判断Mch_id是否一致
				if(combineOrder.getPaymentId().equals(4)){
					if (!wxPayResult.getAppid().equals(WeixinUtil.appId) && failure) {
						failure = false;
					}
					if (!wxPayResult.getMch_id().equals(WeixinUtil.mchtId) && failure) {
						failure = false;
					}
				}else if(combineOrder.getPaymentId().equals(7)){
					if (!wxPayResult.getAppid().equals(WeixinUtil.xcxAppId) && failure) {
						failure = false;
					}
					if (!wxPayResult.getMch_id().equals(WeixinUtil.mchtId) && failure) {
						failure = false;
					}
				}else{
					if (!wxPayResult.getAppid().equals(wecharConfigUtil.getProperty("WX_APP_ID")) && failure) {
						failure = false;
					}
					
					if (!wxPayResult.getMch_id().equals(wecharConfigUtil.getProperty("WX_MCH_ID")) && failure) {
						failure = false;
					}
				}
				// 5:判断支付状态已经支付过了，就不在修改
				boolean isPay = false;
				if (!combineOrder.getStatus().equals(Const.COMBINE_ORDER_STATUS_NOT_PAID)&&!combineOrder.getStatus().equals(Const.COMBINE_ORDER_STATUS_CANCEL) && failure) {
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
						orderService.updateOrderStatus(true, combineOrder,wxPayResult.getTransaction_id(),payDate);
						writer.write(HttpXmlUtils.wxResponseXmlInfo("SUCCESS", "OK"));
					}
				} else {
					if(!isPay){
						System.out.println("---------微信支付返回Fail----------" + wxPayResult.getReturn_msg());
						orderService.updateOrderStatus(false, combineOrder,wxPayResult.getTransaction_id(),null);
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
					String tradeType = wxPayResult.getTrade_type().trim();
					String key = "";
					if("JSAPI".equalsIgnoreCase(tradeType)){
						key = WeixinUtil.key;
					}else if("MWEB".equalsIgnoreCase(tradeType)){
						key = wecharConfigUtil.getProperty("WX_KEY");
					}
					localSign = WXSignUtils.createSign("utf-8", smap,key);
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
				// 4:判断Mch_id是否一致
				if(order.getPaymentId().equals(4)){
					if (!wxPayResult.getAppid().equals(WeixinUtil.appId) && failure) {
						logger.info("appId校验不一致1："+wxPayResult.getOut_trade_no());
						failure = false;
					}
					if (!wxPayResult.getMch_id().equals(WeixinUtil.mchtId) && failure) {
						logger.info("mchtId校验不一致2："+wxPayResult.getOut_trade_no());
						failure = false;
					}
				}else if(order.getPaymentId().equals(7)){
					if (!wxPayResult.getAppid().equals(WeixinUtil.xcxAppId) && failure) {
						logger.info("appId校验不一致xcx："+wxPayResult.getOut_trade_no());
						failure = false;
					}
					if (!wxPayResult.getMch_id().equals(WeixinUtil.mchtId) && failure) {
						logger.info("mchtId校验不一致xcx："+wxPayResult.getOut_trade_no());
						failure = false;
					}
					logger.info("-------------->>："+wxPayResult.getAppid()+":"+WeixinUtil.xcxAppId+":"+wxPayResult.getMch_id()+":"+WeixinUtil.mchtId);
				}else{
					if (!wxPayResult.getAppid().equals(wecharConfigUtil.getProperty("WX_APP_ID")) && failure) {
						logger.info("appId校验不一致3："+wxPayResult.getOut_trade_no());
						failure = false;
					}
					
					if (!wxPayResult.getMch_id().equals(wecharConfigUtil.getProperty("WX_MCH_ID")) && failure) {
						logger.info("mcht校验不一致4："+wxPayResult.getOut_trade_no());
						failure = false;
					}
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
	@RequestMapping(value = "/n/wxShopownerNotifyUrl")
	@ResponseBody
	public void wxShopownerNotifyUrl(HttpServletRequest request, HttpServletResponse response) {
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
					String tradeType = wxPayResult.getTrade_type().trim();
					String key = "";
					if("JSAPI".equalsIgnoreCase(tradeType)){
						key = WeixinUtil.key;
					}else if("MWEB".equalsIgnoreCase(tradeType)){
						key = wecharConfigUtil.getProperty("WX_KEY");
					}
					localSign = WXSignUtils.createSign("utf-8", smap,key);
					if(!wxSign.equals(localSign)){
						failure = false;
						logger.info("校验签名失败");
					}
				}else{
					failure = false;
				}
				ShopownerOrderExample orderExample = new ShopownerOrderExample();
				orderExample.createCriteria().andOrderCodeEqualTo(wxPayResult.getOut_trade_no()).andDelFlagEqualTo("0");
				List<ShopownerOrder> orders = shopownerOrderService.selectByExample(orderExample);
				// 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
				// 1:判断订单号在数据库是否存在
				if (CollectionUtils.isEmpty(orders) && failure) {
					logger.info("订单异常："+wxPayResult.getOut_trade_no());
					failure = false;
				}
				// 2:判断交易金额是否一致
				ShopownerOrder order = orders.get(0);
				if (order.getPayAmount().multiply(new BigDecimal(100)).doubleValue() != Double.valueOf(wxPayResult.getTotal_fee()) && failure) {
					logger.info("金额校验不一致："+wxPayResult.getOut_trade_no());
					failure = false;
				}
				// 3:判断appid是否一致
				// 4:判断Mch_id是否一致
				if(order.getPaymentId().equals(4)){
					if (!wxPayResult.getAppid().equals(WeixinUtil.appId) && failure) {
						logger.info("appId校验不一致1："+wxPayResult.getOut_trade_no());
						failure = false;
					}
					if (!wxPayResult.getMch_id().equals(WeixinUtil.mchtId) && failure) {
						logger.info("mchtId校验不一致2："+wxPayResult.getOut_trade_no());
						failure = false;
					}
				}else if(order.getPaymentId().equals(7)){
					if (!wxPayResult.getAppid().equals(WeixinUtil.xcxAppId) && failure) {
						logger.info("appId校验不一致xcx："+wxPayResult.getOut_trade_no());
						failure = false;
					}
					if (!wxPayResult.getMch_id().equals(WeixinUtil.mchtId) && failure) {
						logger.info("mchtId校验不一致xcx："+wxPayResult.getOut_trade_no());
						failure = false;
					}
					logger.info("-------------->>："+wxPayResult.getAppid()+":"+WeixinUtil.xcxAppId+":"+wxPayResult.getMch_id()+":"+WeixinUtil.mchtId);
				}else{
					if (!wxPayResult.getAppid().equals(wecharConfigUtil.getProperty("WX_APP_ID")) && failure) {
						logger.info("appId校验不一致3："+wxPayResult.getOut_trade_no());
						failure = false;
					}
					
					if (!wxPayResult.getMch_id().equals(wecharConfigUtil.getProperty("WX_MCH_ID")) && failure) {
						logger.info("mcht校验不一致4："+wxPayResult.getOut_trade_no());
						failure = false;
					}
				}
				// 5:判断支付状态已经支付过了，就不在修改
				boolean isPay = false;
				if (!order.getStatus().equals(Const.COMBINE_ORDER_STATUS_NOT_PAID) && failure) {
					isPay = true;
					failure = false;
 				}
				logger.info("-------------------------->>>>>>>>>>>>>>1");
				if ("SUCCESS".equalsIgnoreCase(wxPayResult.getReturn_code()) && failure) {
					// 修改订单状态
					logger.info("-------------------------->>>>>>>>>>>>>>2");
					if(failure){
						logger.info("-------------------------->>>>>>>>>>>>>>3");
						String time_end = wxPayResult.getTime_end();
						Date payDate = new Date();
						if(!StringUtil.isBlank(time_end)){
							payDate = DateUtil.getFormatString(time_end, "yyyyMMddHHmmss");
						}
						logger.info("-------------------------->>>>>>>>>>>>>>5");
						orderService.updateShopownerOrderStatus(request, true, order, wxPayResult.getTransaction_id(), 2, payDate);
						writer.write(HttpXmlUtils.wxResponseXmlInfo("SUCCESS", "OK"));
					}
				} else {
					logger.info("-------------------------->>>>>>>>>>>>>>4");
					if(!isPay){
						System.out.println("---------微信支付返回Fail----------" + wxPayResult.getReturn_msg());
						orderService.updateShopownerOrderStatus(request, false, order,wxPayResult.getTransaction_id(),2,null);
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
				//System.out.println("key是" + element.getName() + "名称是" + element.getText());

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

				if ("trade_type".equals(element.getName())) {
					wxPayResult.setTrade_type(element.getText());
				}
			}
		}
		return wxPayResult;
	}

}
