package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.NumberUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.OrderPreferentialInfoMapper;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityCustom;
import com.jf.entity.AllowanceInfo;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.FullCut;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullGive;
import com.jf.entity.MchtInfo;
import com.jf.entity.MemberAllowanceUsage;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponExample;
import com.jf.entity.OrderPreferentialInfo;
import com.jf.entity.OrderPreferentialInfoExample;
import com.jf.entity.ShopPreferentialInfo;
import com.jf.entity.ShoppingCartCustom;
import com.jf.service.allowance.AllowanceInfoService;
import com.jf.service.allowance.MemberAllowanceUsageService;
import com.jf.vo.PreContext;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenwf:
 * @date 创建时间：2017年5月26日 上午9:41:44
 * @version 1.0
 * @parameter
 * @return
 */
@Service
@Transactional
public class OrderPreferentialInfoService extends BaseService<OrderPreferentialInfo, OrderPreferentialInfoExample> {
	
	@Autowired
	private OrderPreferentialInfoMapper orderPreferentialInfoMapper;
	@Autowired
	private ActivityAreaService activityAreaService;
	@Autowired
	private MemberCouponService memberCouponService;
	@Autowired 
	private CouponService couponService;
	@Resource
	private FullGiveService fullGiveService;
	@Resource
	private FullCutService fullCutService;
	@Resource
	private FullDiscountService fullDiscountService;
	@Resource
	private ShopPreferentialInfoService shopPreferentialInfoService;
	@Autowired
	private AllowanceInfoService allowanceInfoService;
	@Autowired
	private MemberAllowanceUsageService memberAllowanceUsageService;
	@Autowired
	private OrderService orderService;

	@Autowired
	public void setOrderPreferentialInfoMapper(OrderPreferentialInfoMapper orderPreferentialInfoMapper) {
		this.setDao(orderPreferentialInfoMapper);
		this.orderPreferentialInfoMapper = orderPreferentialInfoMapper;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getPreferentialInfo(Map<String, List<ShoppingCartCustom>> activityTypeMap,
			Map<String, BigDecimal> activityTotalAmountMap, Integer memberId, Map<String, ActivityCustom> activityMap,
			Integer combineOrderId, JSONObject reqPRM, JSONObject reqDataJson,
			Map<Integer, BigDecimal> skuDepositTotalAmountMap, Map<Integer, Map<String, Object>> skuDepositTotalMap, String tType,
			Map<Integer, MchtInfo> mchtInfoMap, PreContext preContext) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> activityPreferentialMap = new HashMap<String, Object>();
		List<Map<String, Object>> salePriceMapList = new ArrayList<Map<String, Object>>();
		Map<Integer, List<ShoppingCartCustom>> shopCartAreaMap = new HashMap<Integer, List<ShoppingCartCustom>>();
		List<ShoppingCartCustom> shopCartAreaList= new ArrayList<ShoppingCartCustom>();
		Map<Integer, List<ShoppingCartCustom>> shopCartMchtMap = new HashMap<Integer, List<ShoppingCartCustom>>();
		List<ShoppingCartCustom> shopCartMchtList = new ArrayList<ShoppingCartCustom>();
		BigDecimal totalProductPreferentialAmount = new BigDecimal("0");//所有商品的优惠总金额
		Integer version = reqPRM.getInt("version");
		String system = reqPRM.getString("system");
		for (String key : activityTypeMap.keySet()) {
			Map<Integer, BigDecimal> activityAmountMap = new HashMap<Integer, BigDecimal>();
			BigDecimal activityAmount = new BigDecimal("0");
			List<ShoppingCartCustom> carts = activityTypeMap.get(key);
			if (key.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA)) {
				for (ShoppingCartCustom shoppingCartCustom : carts) {
					Integer productItemId = shoppingCartCustom.getProductItemId();
					Integer activityAreaId = shoppingCartCustom.getActivityAreaId();
					if (shopCartAreaMap.containsKey(activityAreaId)) {
						shopCartAreaList = shopCartAreaMap.get(activityAreaId);
					} else {
						shopCartAreaList = new ArrayList<ShoppingCartCustom>();
					}
					shopCartAreaList.add(shoppingCartCustom);
					shopCartAreaMap.put(activityAreaId, shopCartAreaList);

					if (activityAmountMap.containsKey(activityAreaId)) {
						activityAmount = shoppingCartCustom.getSalePrice().multiply(new BigDecimal(shoppingCartCustom.getQuantity())).add(activityAmountMap.get(activityAreaId));
					} else {
						activityAmount = shoppingCartCustom.getSalePrice()
								.multiply(new BigDecimal(shoppingCartCustom.getQuantity() + ""));
					}
					if(skuDepositTotalAmountMap.containsKey(productItemId)){
						activityAmount = activityAmount.subtract(skuDepositTotalAmountMap.get(productItemId));
					}
					activityAmountMap.put(activityAreaId, activityAmount);
				}
				for (Integer areaId : shopCartAreaMap.keySet()) {
					activityPreferentialMap = getActivityOrMchtPreferential(shopCartAreaMap.get(areaId),
							salePriceMapList, activityMap, areaId, null, memberId, combineOrderId, system, version,
							reqDataJson, activityAmountMap.get(areaId), skuDepositTotalAmountMap,skuDepositTotalMap,activityTotalAmountMap, tType,mchtInfoMap,preContext);
					if (activityPreferentialMap.containsKey("salePriceMapList")) {
						salePriceMapList = (List<Map<String, Object>>) activityPreferentialMap.get("salePriceMapList");
					}
					if (activityPreferentialMap.containsKey("activityTotalAmountMap")) {
						activityTotalAmountMap = (Map<String, BigDecimal>) activityPreferentialMap.get("activityTotalAmountMap");
					}
					if (activityPreferentialMap.containsKey("totalProductPreferentialAmount")) {
						totalProductPreferentialAmount = totalProductPreferentialAmount
								.add((BigDecimal) activityPreferentialMap.get("totalProductPreferentialAmount"));
					}
				}

			} else if (key.equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)) {
				for (ShoppingCartCustom shoppingCartCustom : carts) {
					Integer mchtId = shoppingCartCustom.getMchtId();
					Integer productItemId = shoppingCartCustom.getProductItemId();
					if (shopCartMchtMap.containsKey(mchtId)) {
						shopCartMchtList = shopCartMchtMap.get(mchtId);
					} else {
						shopCartMchtList = new ArrayList<ShoppingCartCustom>();
					}
					shopCartMchtList.add(shoppingCartCustom);
					shopCartMchtMap.put(mchtId, shopCartMchtList);
					if (activityAmountMap.containsKey(mchtId)) {
						activityAmount = shoppingCartCustom.getSalePrice()
								.multiply(new BigDecimal(shoppingCartCustom.getQuantity() + ""))
								.add(activityAmountMap.get(mchtId));
					} else {
						activityAmount = shoppingCartCustom.getSalePrice()
								.multiply(new BigDecimal(shoppingCartCustom.getQuantity() + ""));
					}
					if(skuDepositTotalAmountMap.containsKey(productItemId)){
						activityAmount = activityAmount.subtract(skuDepositTotalAmountMap.get(productItemId));
					}
					activityAmountMap.put(mchtId, activityAmount);
				}
				for (Integer mId : shopCartMchtMap.keySet()) {
					activityPreferentialMap = getActivityOrMchtPreferential(shopCartMchtMap.get(mId), salePriceMapList,
							activityMap, null, mId, memberId, combineOrderId, system, version, reqDataJson,
							activityAmountMap.get(mId), skuDepositTotalAmountMap,skuDepositTotalMap,activityTotalAmountMap, tType,mchtInfoMap,preContext);
					if (activityPreferentialMap.containsKey("salePriceMapList")) {
						salePriceMapList = (List<Map<String, Object>>) activityPreferentialMap.get("salePriceMapList");
					}
					if (activityPreferentialMap.containsKey("activityTotalAmountMap")) {
						activityTotalAmountMap = (Map<String, BigDecimal>) activityPreferentialMap.get("activityTotalAmountMap");
					}
					if (activityPreferentialMap.containsKey("totalProductPreferentialAmount")) {
						totalProductPreferentialAmount = totalProductPreferentialAmount
								.add((BigDecimal) activityPreferentialMap.get("totalProductPreferentialAmount"));
					}
				}
			} else {
				activityPreferentialMap = getActivityOrMchtPreferential(carts, salePriceMapList, activityMap, null,
						null, memberId, combineOrderId, system, version, reqDataJson, activityTotalAmountMap.get(key),
						skuDepositTotalAmountMap,skuDepositTotalMap,activityTotalAmountMap, tType,mchtInfoMap,preContext);
				if (activityPreferentialMap.containsKey("salePriceMapList")) {
					salePriceMapList = (List<Map<String, Object>>) activityPreferentialMap.get("salePriceMapList");
				}
				if (activityPreferentialMap.containsKey("activityTotalAmountMap")) {
					activityTotalAmountMap = (Map<String, BigDecimal>) activityPreferentialMap.get("activityTotalAmountMap");
				}
				if (activityPreferentialMap.containsKey("totalProductPreferentialAmount")) {
					totalProductPreferentialAmount = totalProductPreferentialAmount
							.add((BigDecimal) activityPreferentialMap.get("totalProductPreferentialAmount"));
				}
			}
		}
		map.put("activityTotalAmountMap", activityTotalAmountMap);
		map.put("salePriceMapList", activityPreferentialMap.get("salePriceMapList"));
		map.put("totalProductPreferentialAmount", totalProductPreferentialAmount);
		return map;
		
	}

	public Map<String, Object> getActivityOrMchtPreferential(List<ShoppingCartCustom> shoppingCartAreas, List<Map<String, Object>> salePriceMapList,
			Map<String, ActivityCustom> activityMap, Integer activityAreaId, Integer mchtId, Integer memberId,
			Integer combineOrderId, String system, Integer version, JSONObject reqDataJson, BigDecimal activityTotalAmount, Map<Integer, BigDecimal> skuDepositTotalAmountMap
			, Map<Integer, Map<String, Object>> skuDepositTotalMap, Map<String, BigDecimal> activityTotalAmountMap, String tType,Map<Integer, MchtInfo> mchtInfoMap, PreContext preContext) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> salePriceMap = new HashMap<String, Object>();
		Date date = new Date();
		BigDecimal zero  = new BigDecimal("0");
		Map<String, MemberCoupon> memberCouponMap = new HashMap<String, MemberCoupon>();
		// 每只商品所优惠后的价格
		BigDecimal productPreAmountDtl = zero;
		// 津贴使用
		BigDecimal allowance = BigDecimal.ZERO;
		// 每只商品所优惠后的价格的总和
		BigDecimal totalProductPreAmountDtl = zero;
		//储存多买（多买优惠）中的【第M件N折】，按降序排的第M件价格
		BigDecimal MPiece  = new BigDecimal("0");
		Integer productQuantitySum = 0;//会场购买总件数
		BigDecimal totalProductPreferentialAmount = new BigDecimal("0");//所有商品的优惠总金额
		Coupon coupon = null;
		FullCut fullCut = null;
		FullGive fullGive = null;
		FullDiscount fullDiscount = null;
		AllowanceInfo allowanceInfo = null;
		BigDecimal discountAmount = new BigDecimal("0");
		Integer fullDiscountLadder = null;
		//价格按降序排序
		List<BigDecimal> shopCardSalePriceList = new ArrayList<BigDecimal>();
		String preferentialType = "";
		String preferentialId = "";
		String couponId = "";
		Integer totalQuantity = 0;

		// 商品券优惠
		for (ShoppingCartCustom shoppingCart : shoppingCartAreas) {
			boolean isSelftSpopMcht = isSelftSpopMcht(mchtInfoMap.get(shoppingCart.getMchtId()));
			totalQuantity = totalQuantity + shoppingCart.getQuantity();
			Integer productItemId = shoppingCart.getProductItemId();
			Integer productId = shoppingCart.getProductId();
			Integer orderDtlId = shoppingCart.getOrderDtlId();
			BigDecimal salePrice = shoppingCart.getSalePrice();
			BigDecimal skuDepositTotalAmount = zero;
			if(skuDepositTotalAmountMap.containsKey(productItemId)){
				skuDepositTotalAmount = skuDepositTotalAmountMap.get(productItemId);
			}
			BigDecimal saleOrMallPrice = salePrice.multiply(new BigDecimal(shoppingCart.getQuantity()+"")).subtract(skuDepositTotalAmount);//减去定金后的商品价格
			Map<String, Object> productCouponMap = memberCouponService.getProductCouponAmount(productId,productItemId,reqDataJson,saleOrMallPrice,orderDtlId,tType,combineOrderId,isSelftSpopMcht);//商品券
			BigDecimal productCouponAmount = new BigDecimal(productCouponMap.get("productCouponAmount")+"");//使用商品券的面额
			String productCouponBelong = productCouponMap.get("belong") + "";
			shoppingCart.setSkuDepositTotalAmount(skuDepositTotalAmount);
			shoppingCart.setProductCouponAmount(productCouponAmount);
			shoppingCart.setProductCouponBelong(productCouponBelong);
			activityTotalAmount = activityTotalAmount.subtract(productCouponAmount);
		}
		if(activityAreaId != null){
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activityAreaId);
			// 特惠类型(0无 1优惠劵 2满减 3满赠 4多买优惠)
			preferentialType = activityArea.getPreferentialType();
			preferentialId = activityArea.getPreferentialIdGroup();
		}else if(mchtId != null){
			//获取商家优惠
			BigDecimal preAmount = zero;
			List<MemberCoupon> memberCoupons = memberCouponService.getMemberMchtCouponByMchtIdOrMemberId(memberId,mchtId,null);
			if(CollectionUtils.isNotEmpty(memberCoupons)){
				for (MemberCoupon memberCoupon : memberCoupons) {
					Coupon c = couponService.selectByPrimaryKey(memberCoupon.getCouponId());
					String preType = c.getPreferentialType();
					BigDecimal money = c.getMoney();
					BigDecimal discount = c.getDiscount();
					BigDecimal maxDiscountMoney = c.getMaxDiscountMoney();
					Integer minicount = c.getMinicount();
					BigDecimal minimum = c.getMinimum();
					String conditionType = c.getConditionType();
					if(preType.equals("2")){
						money = activityTotalAmount.multiply(new BigDecimal("1").subtract(discount)).setScale(2, BigDecimal.ROUND_DOWN);
						if(maxDiscountMoney != null && money.compareTo(maxDiscountMoney) > 0){
							money = maxDiscountMoney;
						}
					}
					if(money.compareTo(preAmount) >= 0){
						if(preType.equals("1")){
							if(activityTotalAmount.compareTo(minimum) >= 0){
								preAmount = money;
								preferentialType = "1";
								preferentialId = c.getId()+"";
							}
						}else if(preType.equals("2")){
							if("1".equals(conditionType)){
								preAmount = money;
								preferentialType = "1";
								preferentialId = c.getId()+"";
							}else if("2".equals(conditionType)){
								if(activityTotalAmount.compareTo(minimum) >= 0){
									preAmount = money;
									preferentialType = "1";
									preferentialId = c.getId()+"";
								}
							}else if("3".equals(conditionType)){
								if(totalQuantity >= minicount){
									preAmount = money;
									preferentialType = "1";
									preferentialId = c.getId()+"";
								}
							}
						}
					}
				}
			}
			List<ShopPreferentialInfo> shopPreferentialInfos = shopPreferentialInfoService.findModelsByMchtId(mchtId,"2");
			if(CollectionUtils.isNotEmpty(shopPreferentialInfos)){
				ShopPreferentialInfo shopPreferentialInfo = shopPreferentialInfos.get(0);
				fullCut = fullCutService.selectByPrimaryKey(shopPreferentialInfo.getPreferentialId());
				String ladderFlag = fullCut.getLadderFlag();// 是否阶梯(0否 1是)
				String sumFlag = fullCut.getSumFlag();// 是否累加(0否 1是)
				String rule = fullCut.getRule();// 优惠规则
				if (ladderFlag.equals("0")) {// 非阶梯
					String[] rules = rule.split(",");
					if (sumFlag.equals("0")) {// 非累计
						if (activityTotalAmount.compareTo(new BigDecimal(rules[0])) >= 0) {
							if(new BigDecimal(rules[1]).compareTo(preAmount) >= 0){
								preferentialType = "2";
								preferentialId = shopPreferentialInfo.getPreferentialId()+"";
							}
						}
					} else if (sumFlag.equals("1")) {// 累计
						if (activityTotalAmount.compareTo(new BigDecimal(rules[0])) >= 0) {
							// 商品满减优惠金额 = 单价*数量/总金额*优惠金额
							int size = (int) (Double.valueOf(activityTotalAmount.toString()) / Double.valueOf(rules[0]));
							BigDecimal totalDiscount = new BigDecimal((size * (Double.valueOf(rules[1])))+"");
							if(totalDiscount.compareTo(preAmount) >= 0){
								preferentialType = "2";
								preferentialId = shopPreferentialInfo.getPreferentialId()+"";
							}
						}
					}
				} else if (ladderFlag.equals("1")){// 阶梯
					BigDecimal upMax = new BigDecimal(0);
					String[] rules = rule.split("\\|");
					for (String ladderRules : rules) {
						String[] ladderRule = ladderRules.split(",");
						BigDecimal max = new BigDecimal(ladderRule[0].trim());
						if (activityTotalAmount.compareTo(max) >= 0 && max.compareTo(upMax) >= 0) {
							if(new BigDecimal(ladderRule[1]).compareTo(preAmount) >= 0){
								preferentialType = "2";
								preferentialId = shopPreferentialInfo.getPreferentialId()+"";
							}
							upMax = max;// 获取上一次循环的值
						}
					}
				}
			}
		}
		if(StringUtil.isBlank(preferentialType)){
			preferentialType = "0";
		}else if("1".equals(preferentialType)){
			List<Integer> couponIds = new ArrayList<Integer>();
			List<Integer> cIds = new ArrayList<Integer>();
			for (String cId : preferentialId.split(",")) {
				cIds.add(Integer.valueOf(cId));
			}
			MemberCouponExample memberCouponExample = new MemberCouponExample();
			memberCouponExample.createCriteria().andMemberIdEqualTo(memberId).andCouponIdIn(cIds).andStatusEqualTo("0")
			.andExpiryBeginDateLessThanOrEqualTo(date).andExpiryEndDateGreaterThanOrEqualTo(date).andDelFlagEqualTo("0");
			List<MemberCoupon> memberCoupons = memberCouponService.selectByExample(memberCouponExample);
			if(CollectionUtils.isNotEmpty(memberCoupons)){
				for (MemberCoupon memberCoupon : memberCoupons) {
					memberCouponMap.put(memberCoupon.getCouponId().toString(), memberCoupon);
					couponIds.add(memberCoupon.getCouponId());
				}
				CouponExample couponExample = new CouponExample();
				couponExample.createCriteria().andIdIn(couponIds).andDelFlagEqualTo("0");
				couponExample.setOrderByClause("minimum");
				List<Coupon> coupons = couponService.selectByExample(couponExample);
				if(CollectionUtils.isNotEmpty(coupons)){
					if(mchtId != null){
						coupon = coupons.get(0);
						couponId = coupon.getId().toString();
					}else{
						for (Coupon c : coupons) {
							if(activityTotalAmount.compareTo(c.getMinimum()) >= 0){
								coupon = c;
								couponId = c.getId().toString();
							}
						}
					}
				}
				if (!StringUtil.isBlank(couponId) && "1".equals(tType)) {
					MemberCoupon memberCoupon = memberCouponMap.get(couponId);
					if(memberCoupon != null && combineOrderId != null){
						// 获取用户会员优惠券，用过过标记为1 表示已使用过
						memberCoupon.setStatus("1");
						memberCoupon.setUseDate(date);
						memberCoupon.setOrderId(combineOrderId);
						memberCouponService.updateByPrimaryKeySelective(memberCoupon);
					}
				}
			}
			for (ShoppingCartCustom shoppingCart : shoppingCartAreas) {
				totalQuantity = totalQuantity + shoppingCart.getQuantity();
			}
		}else if("2".equals(preferentialType)){
			fullCut = fullCutService.selectByPrimaryKey(Integer.valueOf(preferentialId));
		}else if("3".equals(preferentialType)){
			fullGive = fullGiveService.selectByPrimaryKey(Integer.valueOf(preferentialId));
		}else if("4".equals(preferentialType)){
			// 多买优惠
			fullDiscount = fullDiscountService.selectByPrimaryKey(Integer.valueOf(preferentialId));
			// 多买类型(1:满M件减N件 2:M元任选N件 3:M件N折)
			String type = fullDiscount.getType();
			//扣除定金的商品金额排序，针对每个商品
			for (ShoppingCartCustom shoppingCart : shoppingCartAreas) {
				Integer quantity = shoppingCart.getQuantity();
				Integer productItemId = shoppingCart.getProductItemId();
				BigDecimal salePrice = shoppingCart.getSalePrice();
				BigDecimal productCouponAmount = shoppingCart.getProductCouponAmount();
				BigDecimal productAeductAmount = new BigDecimal("0");
				Integer depositQuantity = 0;
				boolean b = true;
				if(skuDepositTotalMap.containsKey(productItemId)){
					Map<String, Object>  skuDepositMap = skuDepositTotalMap.get(productItemId);
					depositQuantity = Integer.valueOf(skuDepositMap.get("depositQuantity").toString());
					BigDecimal deductAmount = new BigDecimal(skuDepositMap.get("deductAmount").toString());
					productAeductAmount = deductAmount.divide(new BigDecimal(depositQuantity+""));//得出每个商品的定金可抵扣价格
				}
				for (int i = 0; i < quantity; i++) {
					BigDecimal newSalePrice = salePrice;
					if(i+1 <= depositQuantity){
						newSalePrice = salePrice.subtract(productAeductAmount);
					}
					if(b){
						if(quantity == depositQuantity){
							if(newSalePrice.compareTo(productCouponAmount) > 0){
								newSalePrice = newSalePrice.subtract(productCouponAmount);
								b = false;
							}
						}else{
							//购买的数量大于定金的数量
							if(i+1 > depositQuantity){
								if(newSalePrice.compareTo(productCouponAmount) > 0){
									newSalePrice = newSalePrice.subtract(productCouponAmount);
									b = false;
								}
							}
						}
					}
					shopCardSalePriceList.add(newSalePrice);
				}
				productQuantitySum += shoppingCart.getQuantity();
			}
			//升序排序
			Collections.sort(shopCardSalePriceList);
			if("1".equals(type)){
				int min = Integer.parseInt(fullDiscount.getRule().split(",")[1]);
				if(shopCardSalePriceList != null ){
					if(shopCardSalePriceList.size() < min) {
						min = shopCardSalePriceList.size();
					}
					for (int i = 0; i < min; i++) {
						discountAmount = shopCardSalePriceList.get(i).add(discountAmount);
					}
				}
			}else if("2".equals(type)){
				//满 M件N元 用价格降序排序
				Collections.reverse(shopCardSalePriceList);
				String[] rules = fullDiscount.getRule().split(",");
				int max = Integer.valueOf(rules[0]);// 满M件
				BigDecimal min = new BigDecimal(rules[1]);// N元
				if(productQuantitySum >= max){
					int multiple = productQuantitySum/max;
					max = multiple*max;
					for (int i = 0; i < max; i++) {
						discountAmount = discountAmount.add(shopCardSalePriceList.get(i));
					}
					discountAmount = discountAmount.subtract(min.multiply(new BigDecimal(multiple+"")));
				}
			}else if("4".equals(type)){
				//逆序排序
				Collections.reverse(shopCardSalePriceList);
			}
		}else if ("5".equals(preferentialType)) { //购物津贴
			allowanceInfo = allowanceInfoService.selectByPrimaryKey(Integer.valueOf(preferentialId));
		}
		//在此计算优惠的时候做下升序排序，平摊的的金额保留2位小数，舍尾
		shoppingCartAreas = ascCart(shoppingCartAreas);	
		int i = 0;
		for (ShoppingCartCustom shoppingCart : shoppingCartAreas) {
			boolean isSelftSpopMcht = isSelftSpopMcht(mchtInfoMap.get(shoppingCart.getMchtId()));
			salePriceMap = new HashMap<String, Object>();
			Integer orderDtlId = shoppingCart.getOrderDtlId();
			Integer productItemId = shoppingCart.getProductItemId();
			Integer productId = shoppingCart.getProductId();
			BigDecimal salePrice = shoppingCart.getSalePrice();
			String activityDiscount = "";//活动优惠
			Integer productType1Id = shoppingCart.getProductType1Id();
			BigDecimal skuDepositTotalAmount = shoppingCart.getSkuDepositTotalAmount();
			BigDecimal productCouponAmount = shoppingCart.getProductCouponAmount();
			String productCouponBelong = shoppingCart.getProductCouponBelong();
			BigDecimal saleOrMallPrice = salePrice.multiply(new BigDecimal(shoppingCart.getQuantity()+"")).subtract(skuDepositTotalAmount).subtract(productCouponAmount);//减去定金后的商品价格
			String activityType = shoppingCart.getActivityType();
			String belong = "";
			++i;
			if (preferentialType.equals("1")) {
				// 优惠券
				if (coupon != null) {
					BigDecimal minimum = coupon.getMinimum();
					BigDecimal money = coupon.getMoney();
					String rang = coupon.getRang();
					String name = coupon.getName();
					String couponPreferentialType = coupon.getPreferentialType();
					String conditionType = coupon.getConditionType();
					BigDecimal maxDiscountMoney = coupon.getMaxDiscountMoney();
					BigDecimal discount = coupon.getDiscount();
					Integer minicount = coupon.getMinicount();
					belong = isSelftSpopMcht ? "1" : coupon.getBelong();
					if("2".equals(couponPreferentialType)){//优惠券折扣
						money = activityTotalAmount.multiply(new BigDecimal("1").subtract(discount)).setScale(2, BigDecimal.ROUND_DOWN);
						if(maxDiscountMoney != null && money.compareTo(maxDiscountMoney) > 0){
							money = maxDiscountMoney;
						}
					}
					if(("1".equals(couponPreferentialType) && activityTotalAmount.compareTo(minimum) >= 0) ||
							 ("2".equals(couponPreferentialType) && 
									("1".equals(conditionType) || ("2".equals(conditionType) && activityTotalAmount.compareTo(minimum) >= 0) || ("3".equals(conditionType) && totalQuantity >= minicount)))){
						if (shoppingCartAreas.size() == i) {
							// 最后一次计算
							productPreAmountDtl = money.subtract(totalProductPreAmountDtl);
						} else {
							productPreAmountDtl = saleOrMallPrice.multiply(money)
									.divide(activityTotalAmount, 2, BigDecimal.ROUND_HALF_UP);
							totalProductPreAmountDtl = totalProductPreAmountDtl.add(productPreAmountDtl);
						}				
					}
					if("1".equals(tType)){// 保存用过的优惠信息
						saveOrderPreferentialInfo(preferentialType, Integer.valueOf(couponId),orderDtlId, rang, name, productPreAmountDtl, belong, memberId);
					}
				}
				//获取优惠券的优惠信息
				if(!StringUtil.isBlank(preferentialId)){
					List<Integer> couponIdList = new ArrayList<Integer>();
					for (String cId : preferentialId.split(",")) {
						if(!StringUtil.isBlank(cId)){
							couponIdList.add(Integer.parseInt(cId));
						}
					}
					if(CollectionUtils.isNotEmpty(couponIdList)){
						CouponExample couponExample = new CouponExample();
						couponExample.createCriteria().andIdIn(couponIdList).andDelFlagEqualTo("0");
						List<Coupon> coupons = couponService.selectByExample(couponExample);
						if(CollectionUtils.isNotEmpty(coupons)){
							for (Coupon c : coupons) {
								activityDiscount +=  "满"+c.getMinimum()+"元减 " + c.getMoney()+"元优惠券,";
							}
						}
					}
				}
			} else if (preferentialType.equals("2")) {// 满减
				// 是否阶梯(0否 1是)
				String ladderFlag = fullCut.getLadderFlag();
				// 是否累加(0否 1是)
				String sumFlag = fullCut.getSumFlag();
				// 优惠规则
				String rule = fullCut.getRule();
				belong =isSelftSpopMcht ? "1" : fullCut.getBelong();
				String rang = fullCut.getRang();
				String name = rule;
				String sumFlagStr = "";
				if("1".equals(sumFlag)){
					sumFlagStr = "(累加)";
				}
				if (ladderFlag.equals("0")) {// 非阶梯
					String[] rules = rule.split(",");
					if (sumFlag.equals("0")) {// 非累计
						if (activityTotalAmount.compareTo(new BigDecimal(rules[0])) >= 0) {
							// 商品满减优惠金额 = 单价*数量/总金额*优惠金额
							if (shoppingCartAreas.size() == i) {
								productPreAmountDtl = new BigDecimal(rules[1])
										.subtract(totalProductPreAmountDtl);
							} else {
								productPreAmountDtl = saleOrMallPrice.multiply(new BigDecimal(rules[1])).divide(activityTotalAmount, 2, BigDecimal.ROUND_HALF_UP);
								totalProductPreAmountDtl = totalProductPreAmountDtl.add(productPreAmountDtl);
							}
						}
					} else if (sumFlag.equals("1")) {// 累计
						if (activityTotalAmount.compareTo(new BigDecimal(rules[0])) >= 0) {
							// 商品满减优惠金额 = 单价*数量/总金额*优惠金额
							int size = (int) (Double.valueOf(activityTotalAmount.toString()) / Double.valueOf(rules[0]));
							BigDecimal totalDiscount = new BigDecimal((size * (Double.valueOf(rules[1])))+"");
							if (shoppingCartAreas.size() == i) {
								productPreAmountDtl = totalDiscount.subtract(totalProductPreAmountDtl);
							} else {
								productPreAmountDtl = saleOrMallPrice.multiply(totalDiscount).divide(activityTotalAmount, 2, BigDecimal.ROUND_HALF_UP);
								totalProductPreAmountDtl = totalProductPreAmountDtl.add(productPreAmountDtl);
							}
						}
					}
					name = "满"+rules[0]+"元减 " + rules[1]+"元";
					activityDiscount = sumFlagStr+"满"+rules[0]+"元减 " + rules[1]+"元,";
				} else if (ladderFlag.equals("1")){// 阶梯
						// 获取上一次循环的值
					BigDecimal upMax = new BigDecimal(0);
					String[] rules = rule.split("\\|");

					for (String ladderRules : rules) {
						String[] ladderRule = ladderRules.split(",");
						BigDecimal max = new BigDecimal(ladderRule[0].trim());
						if (activityTotalAmount.compareTo(max) >= 0 && max.compareTo(upMax) >= 0) {
							if (shoppingCartAreas.size() == i) {
								productPreAmountDtl = new BigDecimal(ladderRule[1])
										.subtract(totalProductPreAmountDtl);
							} else {
								productPreAmountDtl = saleOrMallPrice.multiply(new BigDecimal(ladderRule[1])).divide(activityTotalAmount, 2, BigDecimal.ROUND_HALF_UP);
							}
							// 获取上一次循环的值
							upMax = max;
							name = "满"+ladderRule[0]+"元减 " + ladderRule[1]+"元";
						}
						activityDiscount += "满"+ladderRule[0]+"元减 " + ladderRule[1]+"元,";
					}
					totalProductPreAmountDtl = totalProductPreAmountDtl.add(productPreAmountDtl);
				}
				if("1".equals(tType)){
					saveOrderPreferentialInfo(preferentialType, Integer.valueOf(preferentialId),orderDtlId, rang, name, productPreAmountDtl, belong, memberId);
				}

			} else if (preferentialType.equals("3")) {
				// 满赠
				if(!StringUtil.isBlank(preferentialId)){
					// 满赠类型(1满额赠 2买即赠)
					String giveType = fullGive.getType();
					// 是否累加(0否 1是)
					String sumFlag = fullGive.getSumFlag();
					// 最低消费
					BigDecimal minimum = fullGive.getMinimum();
					// 是否赠送商品(0否 1是)
					String productFlag = fullGive.getProductFlag();
					// 赠送商品ID
					Integer gifgProductId = fullGive.getProductId();
					// 赠送商品数量
					Integer productNum = fullGive.getProductNum();
					// 是否赠送优惠劵(0否 1是)
					String couponFlag = fullGive.getCouponFlag();
					// 否赠优惠劵ID集合
					String giveCouponId = fullGive.getCouponIdGroup();
					// 是否赠送金币(0否 1是)
					String integralFlag = fullGive.getIntegralFlag();
					// 否赠金币数量
					Integer giveIntegeral = fullGive.getIntegral();
					String sumFlagStr = "";
					if("1".equals(sumFlag)){
						sumFlagStr = "(累加)";
					}
					if("1".equals(giveType)){
						activityDiscount = "满额赠"+sumFlagStr+"，满"+minimum+"赠送:";
					}else if("2".equals(giveType)){
						activityDiscount = "买即赠"+sumFlagStr+":";
					}
					if("1".equals(productFlag)) {
						activityDiscount += "商品ID--"+gifgProductId+",";
					}
					if("1".equals(couponFlag)) {
						activityDiscount += "优惠券ID--"+giveCouponId+",";
					}
					if("1".equals(integralFlag)) {
						activityDiscount += "赠送"+giveIntegeral+"积分,";
					}
					
				}
			} else if (preferentialType.equals("4")) {
				// 多买类型(1:满M件减N件 2:M元任选N件 3:M件N折)
				String type = fullDiscount.getType();
				// 规则串
				String rule = fullDiscount.getRule();
				String name = rule;
				belong =isSelftSpopMcht ? "1" : fullDiscount.getBelong();
				String rang = fullDiscount.getRang();
				if (type.equals("1")) {
					// 满M件减N件
					String[] rules = rule.split(",");
					int max = Integer.valueOf(rules[0]);// 满M件
					int min = Integer.valueOf(rules[1]);// 减N件
					name = "满"+max+"件减"+min+"件";
					activityDiscount = name = "满"+max+"件减"+min+"件,";
					if (productQuantitySum >= max) {// 会场购买件数符合条件满几件
						// 平摊到每只商品
						if (shoppingCartAreas.size() == i) {
							// 最后一次计算
							productPreAmountDtl = discountAmount.subtract(totalProductPreAmountDtl);
						} else {
							productPreAmountDtl = saleOrMallPrice.multiply(discountAmount).divide(activityTotalAmount, 2, BigDecimal.ROUND_HALF_UP);
							totalProductPreAmountDtl = totalProductPreAmountDtl.add(productPreAmountDtl);
						}
					}
				} else if (type.equals("2")) {
					// 每满M件N元 2件4元，4件8元
					String[] rules = rule.split(",");
					int max = Integer.valueOf(rules[0]);// 满M件
					double min = Double.valueOf(rules[1]);// 减N元
					name = "满"+max+"件减"+min+"元";
					activityDiscount = "满"+max+"件减"+min+"元,";
					if (productQuantitySum >= max) {// 会场购买件数符合条件满几件
						// 平摊到每只商品
						if (shoppingCartAreas.size() == i) {
							// 最后一次计算
							productPreAmountDtl = discountAmount.subtract(totalProductPreAmountDtl);
						} else {
							productPreAmountDtl = saleOrMallPrice.multiply(discountAmount).divide(activityTotalAmount, 2, BigDecimal.ROUND_HALF_UP);
							totalProductPreAmountDtl = totalProductPreAmountDtl.add(productPreAmountDtl);
						}
					}
				} else if (type.equals("3")) {
					String[] rules = rule.split("\\|");// 满M件打N折
					int upMax = 0;// 获取上一次循环的值
					if(fullDiscountLadder == null){//阶梯型优惠，判断用户满足哪个优惠条件
						for (int j = 0; j < rules.length; j++) {
							String[] ladderRule = rules[j].split(",");
							int max = Integer.valueOf(ladderRule[0]);// 满M件
							if (productQuantitySum >= max && max > upMax) {// 会场购买件数符合条件满几件
								fullDiscountLadder = j;
								upMax = max;
								name = "满"+ladderRule[0]+"件打"+ladderRule[1]+"折";
							}
							activityDiscount = "满"+ladderRule[0]+"件打"+ladderRule[1]+"折,";
						}
					}
					if(fullDiscountLadder != null){
						String[] ladderRule = rules[fullDiscountLadder].split(",");
						BigDecimal min = new BigDecimal(ladderRule[1]);// 打几折
						productPreAmountDtl = new BigDecimal(0);
						discountAmount = activityTotalAmount.multiply(new BigDecimal("1").subtract(min.divide(new BigDecimal("10")))).setScale(2, BigDecimal.ROUND_DOWN);
						// 平摊到每只商品
						if (shoppingCartAreas.size() == i) {
							// 最后一次计算
							productPreAmountDtl = discountAmount.subtract(totalProductPreAmountDtl);
						} else {
							productPreAmountDtl = saleOrMallPrice.multiply(discountAmount).divide(activityTotalAmount, 2, BigDecimal.ROUND_DOWN);
							totalProductPreAmountDtl = totalProductPreAmountDtl.add(productPreAmountDtl);
						}
					}
				} else if (type.equals("4")) {
					//第M件打N折
					String[] rules = rule.split(",");
					int min = Integer.valueOf(rules[0]);
					BigDecimal discount = new BigDecimal(rules[1]);
					//productQuantitySum购买总商品数是否满足最低门槛
					if(productQuantitySum >= min){
						MPiece = shopCardSalePriceList.get(min-1);
						discountAmount = MPiece.multiply(new BigDecimal("1").subtract(discount.divide(new BigDecimal("10")))).setScale(2, BigDecimal.ROUND_DOWN);;
						// 平摊到每只商品
						if (shoppingCartAreas.size() == i) {
							// 最后一次计算
							productPreAmountDtl = discountAmount.subtract(totalProductPreAmountDtl);
						} else {
							productPreAmountDtl = saleOrMallPrice.multiply(discountAmount).divide(activityTotalAmount, 2, BigDecimal.ROUND_HALF_UP);
							productPreAmountDtl = productPreAmountDtl.setScale(2, BigDecimal.ROUND_DOWN);
							totalProductPreAmountDtl = totalProductPreAmountDtl.add(productPreAmountDtl);
						}
					}
					activityDiscount = "第"+min+"件打"+discount+"折,";
				}
				if("1".equals(tType)){
					saveOrderPreferentialInfo(preferentialType, Integer.valueOf(preferentialId),orderDtlId, rang, name, productPreAmountDtl, belong, memberId);
				}
			}else if (preferentialType.equals("5")) { //购物津贴
				String rule = allowanceInfo.getRule();
				belong = isSelftSpopMcht ? "1" : allowanceInfo.getBelong();
				String rang = allowanceInfo.getRang();
				String sumFlagStr = "(累加)";
				String[] rules = rule.split(",");
				BigDecimal full = new BigDecimal(rules[0]); //满
				BigDecimal min = new BigDecimal(rules[1]); //减
				if (allowanceInfo.getUsageBeginTime().before(date) && allowanceInfo.getUsageEndTime().after(date)
						&& activityTotalAmount.compareTo(full) >= 0 && NumberUtil.gtZero(preContext.getRestAllowance())) {
					BigDecimal multi = activityTotalAmount.divide(full, 0, BigDecimal.ROUND_DOWN);
					BigDecimal totalDiscount = min.multiply(multi);
					totalDiscount = NumberUtil.min(totalDiscount, preContext.getRestAllowance());
					if (shoppingCartAreas.size() == i) {
						productPreAmountDtl = totalDiscount.subtract(totalProductPreAmountDtl);
						preContext.setUsedAllowance(preContext.getUsedAllowance().add(totalDiscount)); //使用津贴累计
						if ("1".equals(tType)) {
							MemberAllowanceUsage memberAllowanceUsage = new MemberAllowanceUsage();
							memberAllowanceUsage.setMemberId(memberId);
							memberAllowanceUsage.setCombineOrderId(combineOrderId);
							memberAllowanceUsage.setActivityAreaId(activityAreaId);
							memberAllowanceUsage.setType("1"); // 1、下单
							memberAllowanceUsage.setRule(rule);
							memberAllowanceUsage.setUsageAmount(totalDiscount);
							memberAllowanceUsage.setUsageDate(date);
							memberAllowanceUsage.setCreateBy(memberId);
							memberAllowanceUsage.setCreateDate(date);
							memberAllowanceUsage.setDelFlag(StateConst.FALSE);
							memberAllowanceUsageService.insert(memberAllowanceUsage);
						}
					} else {
						productPreAmountDtl = saleOrMallPrice.multiply(totalDiscount).divide(activityTotalAmount, 2, BigDecimal.ROUND_HALF_UP);
						totalProductPreAmountDtl = totalProductPreAmountDtl.add(productPreAmountDtl);
					}
					allowance = productPreAmountDtl;

					String name = "满" + rules[0] + "元减 " + rules[1] + "元";
					activityDiscount = sumFlagStr + "满" + rules[0] + "元减 " + rules[1] + "元,";
					if ("1".equals(tType)) {
						saveOrderPreferentialInfo("11", Integer.valueOf(preferentialId), orderDtlId, rang, name, productPreAmountDtl, belong, memberId);
					}
				}
			}
			BigDecimal platformPreAmount = zero;
			BigDecimal mchtPreAmount = zero;
			if (isSelftSpopMcht) { //spop商家 所有优惠费用归属平台（包括定金抵扣）
				platformPreAmount = platformPreAmount.add(productPreAmountDtl).add(skuDepositTotalAmount).add(productCouponAmount);
			}else{
				if("1".equals(belong)){
					platformPreAmount = productPreAmountDtl;
				}else if("2".equals(belong)){
					mchtPreAmount = productPreAmountDtl;
				}
				mchtPreAmount = mchtPreAmount.add(skuDepositTotalAmount);
				if("1".equals(productCouponBelong)){
					platformPreAmount = platformPreAmount.add(productCouponAmount);
				}else if("2".equals(productCouponBelong)){
					mchtPreAmount = mchtPreAmount.add(productCouponAmount);
				}
			}
			salePriceMap.put("productPreAmountDtl", mchtPreAmount);
			salePriceMap.put("platformAmount", platformPreAmount);
			salePriceMap.put("allowance", allowance);
			salePriceMap.put("orderDtlId", orderDtlId);
			salePriceMap.put("belong", belong);
			salePriceMap.put("activityDiscount", activityDiscount);
			salePriceMap.put("productType1Id", productType1Id);
			salePriceMap.put("salePrice", salePrice);
			salePriceMap.put("quantity", shoppingCart.getQuantity());
			salePriceMap.put("productId", productId);
			salePriceMap.put("productItemId", productItemId);
			salePriceMap.put("activityType", activityType);
			salePriceMap.put("popFeeRate", shoppingCart.getPopFeeRate());
			salePriceMap.put("mchtId", shoppingCart.getMchtId());
			salePriceMapList.add(salePriceMap);
			//计算出所有商品优惠的总金额
			BigDecimal preferentialAmount = productPreAmountDtl.add(productCouponAmount);
			totalProductPreferentialAmount = totalProductPreferentialAmount.add(preferentialAmount).add(skuDepositTotalAmount);
			activityTotalAmountMap.put(activityType, activityTotalAmountMap.get(activityType).subtract(preferentialAmount));
		}
		map.put("activityTotalAmountMap", activityTotalAmountMap);
		map.put("salePriceMapList", salePriceMapList);
		map.put("totalProductPreferentialAmount", totalProductPreferentialAmount);
		return map;
	}

	//商家是否为自营spop类型
	private boolean isSelftSpopMcht(MchtInfo mchtInfo) {
		return mchtInfo != null && Const.MCHT_TYPE_SPOP.equals(mchtInfo.getMchtType()) && StateConst.TRUE.equals(mchtInfo.getIsManageSelf());
	}

    /**
	 * 
	 * 方法描述 ：保存订单优惠内容
	 * @author  chenwf: 
	 * @date 创建时间：2017年7月19日 下午5:20:46 
	 * @version
	 * @param preferentialType
	 * @param preferentialId
	 * @param orderDtlId
	 * @param rang
	 * @param name
	 * @param productPreAmountDtl
	 * @param belong
	 * @param membertId
	 */
	public void saveOrderPreferentialInfo(String preferentialType, Integer preferentialId, Integer orderDtlId, String rang,
			String name, BigDecimal productPreAmountDtl, String belong, Integer memberId) {
		if(productPreAmountDtl.compareTo(new BigDecimal("0")) > 0){//优惠金额 =0 不做插入
			if(belong.equals(Const.COUPON_BELONG_TYPE_PLATFORM)){
				name = "【平台】"+name;
			}else if(belong.equals(Const.COUPON_BELONG_TYPE_MCHT)){
				name = "【商家】"+name;
			}
			OrderPreferentialInfo orderPreferentialInfo = new OrderPreferentialInfo();
			orderPreferentialInfo.setPreferentialType(preferentialType);
			orderPreferentialInfo.setPreferentialId(preferentialId);
			orderPreferentialInfo.setOrderDtlId(orderDtlId);
			orderPreferentialInfo.setRang(rang);
			orderPreferentialInfo.setPreferentialContent(name);
			orderPreferentialInfo.setPreferentialAmount(productPreAmountDtl);
			orderPreferentialInfo.setBelong(belong);
			orderPreferentialInfo.setCreateDate(new Date());
			orderPreferentialInfo.setCreateBy(memberId);
			orderPreferentialInfo.setDelFlag("0");
			orderPreferentialInfoMapper.insertSelective(orderPreferentialInfo);
		}
		
	}
	

	/**
	 * 在此计算优惠的时候做下升序排序，平摊的的金额保留2位小数，舍尾
	 * @param shoppingCartAreas
	 * @return
	 */
	public List<ShoppingCartCustom> ascCart(List<ShoppingCartCustom> shoppingCartAreas) {
		Collections.sort(shoppingCartAreas, new Comparator<ShoppingCartCustom>() {
			@Override
			public int compare(ShoppingCartCustom o1, ShoppingCartCustom o2) {
				BigDecimal saleOrMallPrice1 = o1.getSalePrice().multiply(new BigDecimal(o1.getQuantity()+"")).subtract(o1.getSkuDepositTotalAmount()).subtract(o1.getProductCouponAmount());//减去定金后的商品价格
				BigDecimal saleOrMallPrice2 = o2.getSalePrice().multiply(new BigDecimal(o2.getQuantity()+"")).subtract(o2.getSkuDepositTotalAmount()).subtract(o2.getProductCouponAmount());//减去定金后的商品价格
				return saleOrMallPrice1.compareTo(saleOrMallPrice2);
			}
		});
		return shoppingCartAreas;
	}
	
}
