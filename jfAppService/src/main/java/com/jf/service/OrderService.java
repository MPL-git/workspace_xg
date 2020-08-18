package com.jf.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayConstants;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.*;
import com.jf.common.utils.alipay.PayUtil;
import com.jf.common.utils.wechar.WXSignUtils;
import com.jf.dao.*;
import com.jf.entity.*;
import com.jf.entity.pay.Unifiedorder;
import com.jf.entity.pay.UnifiedorderResult;

import com.jf.service.allowance.MemberAllowanceService;
import com.jf.vo.PreContext;
import com.jf.vo.SVipInfo;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author chenwf:
 * @date 创建时间：2017年5月23日 下午6:14:12
 * @version 1.0
 * @parameter
 * @return
 */
@Service
@Transactional
public class OrderService {
	private static Logger logger = LoggerFactory.getLogger(OrderService.class);
	/**
	 * 会员表
	 */
	@Resource
	private MemberInfoService memberInfoService;
	/**
	 * 会员组表
	 */
	@Resource
	private MemberGroupService memberGroupService;
	/**
	 * 总订单
	 */
	@Resource
	private CombineOrderMapper combineOrderMapper;
	/**
	 * 子订单
	 */
	@Resource
	private SubOrderMapper subOrderMapper;
	@Autowired
	private SubOrderExtendMapper subOrderExtendMapper;
	/**
	 * 订单明细
	 */
	@Resource
	private OrderDtlMapper orderDtlMapper;
	@Autowired
	private OrderDtlExtendMapper orderDtlExtendMapper;

	/**
	 * 订单明细(辅助)
	 */
	@Resource
	private OrderDtlCustomMapper orderDtlCustomMapper;

	/**
	 * 购物车
	 */
	@Resource
	private ShoppingCartMapper shoppingCartMapper;

	/**
	 * 购物车
	 */
	@Resource
	private ShoppingCartCustomMapper shoppingCartCustomMapper;

	/**
	 * 商品sku
	 */
	@Resource
	private ProductItemMapper productItemMapper;

	/**
	 * 商品sku(辅助)
	 */
	@Resource
	private ProductItemCustomMapper productItemCustomMapper;

	/**
	 * 商品信息
	 */
	@Resource
	private ProductMapper productMapper;

	/**
	 * 商品信息(辅助)
	 */
	@Resource
	private ProductCustomMapper productCustomMapper;

	/**
	 * 商品品牌
	 */
	@Resource
	private ProductBrandMapper productBrandMapper;

	/**
	 * 商家信息
	 */
	@Resource
	private MchtInfoMapper mchtInfoMapper;

	/**
	 * 优惠券
	 */
	@Resource
	private CouponMapper couponMapper;

	/**
	 * 用户优惠券
	 */
	@Resource
	private MemberCouponMapper memberCouponMapper;

	/**
	 * 满赠表
	 */
	@Resource
	private FullGiveMapper fullGiveMapper;

	/**
	 * 满减表
	 */
	@Resource
	private FullCutMapper fullCutMapper;

	/**
	 * 多买优惠
	 */
	@Resource
	private FullDiscountMapper fullDiscountMapper;

	/**
	 * 会场
	 */
	@Resource
	private ActivityAreaMapper activityAreaMapper;

	/**
	 * 用户账号信息
	 */
	@Resource
	private MemberAccountMapper memberAccountMapper;

	/**
	 * 用户地址管理
	 */
	@Resource
	private MemberAddressMapper memberAddressMapper;
	
	/**
	 * 用户地址管理(辅助)
	 */
	@Resource
	private MemberAddressCustomMapper memberAddressCustomMapper;

	/**
	 * 订单优惠信息表
	 */
	@Resource
	private OrderPreferentialInfoMapper orderPreferentialInfoMapper;

	/**
	 * 商品属性值
	 */
	@Resource
	private ProductPropValueCustomMapper productPropValueCustomMapper;
	
	/**
	 * 商品属性值
	 */
	@Resource
	private IntegralDtlMapper integralDtlMapper;
	
	/**
	 * 商家商品品牌表
	 */
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Autowired
	private ProvinceFreightService provinceFreightService;
	
	/**
	 * 订单日志
	 */
	@Resource
	private OrderLogService orderLogService;
	
	@Resource
	private IntegralTypeService integralTypeService;
	@Resource
	private GrowthDtlService growthDtlService;
	@Resource
	private IntegralDtlService integralDtlService;
	@Resource
	private ShoppingCartService shoppingCartService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private MemberCouponService memberCouponService;
	@Resource
	private CouponService couponService;
	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private ActivityService activityService;
	@Resource
	private ProductService productService;
	@Resource
	private ShopPreferentialInfoService shopPreferentialInfoService;
	/**
	 * 商品sku
	 */
	@Resource
	private ProductItemService productItemService;
	
	@Resource
	private SingleProductActivityService singleProductActivityService;
	
	@Resource
	private SingleProductActivityCnfService singleProductActivityCnfService;
	
	@Resource
	private MemberAddressService memberAddressService;
	@Resource
	private DeliveryOvertimeCnfService deliveryOvertimeCnfService;
	@Resource
	private OrderProductSnapshotService orderProductSnapshotService;
	@Autowired
	private ActivityProductDepositService activityProductDepositService;
	@Autowired
	private CombineDepositOrderService combineDepositOrderService;
	@Autowired
	private SubDepositOrderService subDepositOrderService;
	@Autowired
	private DepositOrderStatusLogService depositOrderStatusLogService;
	@Autowired
	private SubDepositOrderMapper subDepositOrderMapper;
	@Autowired
	private CommonService commonService;
	@Resource
	private OrderPreferentialInfoService orderPreferentialInfoService;
	@Resource
	private SvipMemberSettingService svipMemberSettingService;
	@Resource
	private SvipOrderService svipOrderService;
	@Resource
	private NovaPlanService novaPlanService;
	@Resource
	private ShopownerService shopownerService;
	@Resource
	private CombineOrderExtendMapper combineOrderExtendMapper;

	@Autowired
	private DeliveryOvertimeSpecialCnfMapper deliveryOvertimeSpecialCnfMapper;

	@Autowired
	private DeliveryOvertimeSpecialCnfAreaMapper deliveryOvertimeSpecialCnfAreaMapper;
	@Autowired
	private MemberAllowanceService memberAllowanceService;
	@Autowired
	private MemberExtendMapper memberExtendMapper;
	@Autowired
	private SvipMarketingSettingService svipMarketingSettingService;
	@Autowired
	private SvipBindProductService svipBindProductService;

	/**
	 * 积分可优惠折扣
	 */
	public static final double INTEGERAL_DISOUNT = 0.5;
	
	/**
	 * 承若发货时间
	 */
	public static final int DELIVERY_OVER_TIME = 48;//H
	
	/**
	 * 
	 * 方法描述 ：下订单
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年5月31日 下午4:01:40
	 * @version
	 * @param reqDataJson
	 * @param reqDataJson 
	 * @param request 
	 * @param version 
	 * @return
	 * @throws Exception 
	 * @throws ArgException 
	 */
	@SuppressWarnings("unchecked")
	public ResponseMsg submitOrderPayment(JSONObject reqPRM, JSONObject reqDataJson, HttpServletRequest request,String system, Integer version){
		// 会员标识id
		Integer memberId = reqDataJson.getInt("memberId");
		int svipMarketingCartId = reqDataJson.optInt("svipMarketingCartId", 0);
		SvipMarketingSetting svipMarketingSetting = svipMarketingSettingService.getSetting();
		Set<Integer> ignoredSvipCardIdSet = getIgnoredSvipCartIdSet(reqDataJson); //获取忽略使用svip价格的购物车id
		//获取该商品是否为预售商品
		Map<Integer, Map<String, Object>> skuDepositTotalMap = new HashMap<Integer, Map<String, Object>>(); //放置每个sku所对应的定金总金额，里面包含数量
		Map<String, Object> skuDepositMap = new HashMap<String, Object>();
		Map<Integer, BigDecimal> skuDepositTotalAmountMap = new HashMap<Integer, BigDecimal>(); //预售商品购物车sku数量所对应的定金总金额，预防A商品买了2个定金，只去支付一个尾款，该sku所对应的可抵扣金额就为1个定金抵扣
		Map<Integer, List<SubDepositOrder>> depositOrderMap = new HashMap<Integer, List<SubDepositOrder>>(); //sku对应预售定金子订单对象集合
		List<SubDepositOrder> depositOrderList = new ArrayList<SubDepositOrder>();
		SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
		subDepositOrderExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId).andStatusIn(Arrays.asList("2", "8")); //2 定金已付   8 尾款下单后未支付取消
		List<SubDepositOrder> subDepositOrderList = subDepositOrderMapper.selectByExample(subDepositOrderExample);
		if(CollectionUtils.isNotEmpty(subDepositOrderList)){
			for (SubDepositOrder subDepositOrder : subDepositOrderList) {
				if(depositOrderMap.containsKey(subDepositOrder.getProductItemId())){
					depositOrderList = depositOrderMap.get(subDepositOrder.getProductItemId());
				}else{
					depositOrderList = new ArrayList<SubDepositOrder>();
				}
				depositOrderList.add(subDepositOrder);
				depositOrderMap.put(subDepositOrder.getProductItemId(), depositOrderList);
				
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
		
		Map<String, Object> hasStockMap = isHasStock(reqDataJson,skuDepositTotalMap);
		if(!(boolean) hasStockMap.get("isHasStock")){
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("isHasStock", (boolean) hasStockMap.get("isHasStock"));
			dataMap.put("dataList", hasStockMap.get("dataList"));
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,dataMap);
		}
		Date date = new Date();
		BigDecimal totalFreight = new BigDecimal("0");//总运费
		// 订单来源客户端 1.IOS 2.安卓
		String sourceClient = reqDataJson.getString("sourceClient");
		// 客户备注
		String remarks = reqDataJson.getString("remarks");
		remarks = StringUtil.filterEmoji(remarks);
		// 支付方式 1 支付宝  2 微信  3 银联
		Integer payId = reqDataJson.getInt("payId");
		// 实付金额
		String payAmount = reqDataJson.getString("payAmount");
		// 收货地址id
		Integer addressId = reqDataJson.getInt("addressId");
		// 全平台优惠券
		String mermberPlatformCouponId = reqDataJson.getString("mermberPlatformCouponId");
		// ip地址
		String ip = StringUtil.getIpAddr(request);
		if(!StringUtil.isBlank(ip)){
			if(ip.trim().length() > 16 || ip.trim().length()==0){
				ip = "116.62.146.101";
			}
		}else{
			ip = "116.62.146.101";
		}
		MemberInfo member = memberInfoService.selectByPrimaryKey(memberId);
		// 会员昵称
		String memberNick = member.getNick();
		SVipInfo sVipInfo = memberInfoService.isSVip(member, memberId);

		String openType = novaPlanService.getNovaPlanOpenType(null, member.getInvitationMemberId());
		BigDecimal zero = new BigDecimal("0");
		BigDecimal platformSubsidyAmount = zero;//平台补贴金额
		Integer platformSubsidyId = null;//记录平台补贴所对应的单品id
		String orderType = Const.COMBINE_ORDER_TYPE_ROUTINE;
		//采集额外信息
		String extraInfo = getExtraInfo(reqPRM,reqDataJson,ip);
		// shopCardIds 储存包含所有会场的购物车id
		List<Integer> shopCardIdsList = new ArrayList<Integer>();
		String shopCardIds = reqDataJson.getString("shopCardIds");
		for (String id : shopCardIds.split(",")) {
			if(!StringUtil.isBlank(id)){
				shopCardIdsList.add(Integer.valueOf(id));
			}
		}
		Map<String, List<ShoppingCartCustom>> activityTypeMap=new HashMap<String, List<ShoppingCartCustom>>();//按活动类型来归类购物车
		Map<String, BigDecimal> activityTotalAmountMap=new HashMap<String, BigDecimal>();//储存活动类型的总金额（去掉所有优惠信息的总金额）
		Map<String, Integer> activityTypeQuantityMap=new HashMap<String, Integer>();//储存活动类型商品的总数量
		BigDecimal activityTotalAmount = new BigDecimal("0");//活动商品总金额
		List<ShoppingCartCustom> shoppingCartCustoms = new ArrayList<ShoppingCartCustom>();
		List<Integer> productIds = new ArrayList<Integer>();//放置整个购物车类型为会场和日常销售的商品
		Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();//放置活动中的商品
		Map<Integer,List<ShoppingCartCustom>> categoryMap = new HashMap<Integer,List<ShoppingCartCustom>>();//放置相同品类的购物车map（用来计算品类优惠）
		BigDecimal totalShoppingAmount = new BigDecimal(0);// 购物车总金额
		List<Map<String, Object>> freights = new ArrayList<Map<String, Object>>();//运费
		Map<Integer,List<ShoppingCartCustom>> mchtCartsMap = new HashMap<Integer,List<ShoppingCartCustom>>();//放置相同商家的购物车map
		List<ShoppingCartCustom> cartList = new ArrayList<ShoppingCartCustom>();
		Map<String, Object> shopCarsParams = new HashMap<String, Object>();
		shopCarsParams.put("shopCardIdList", shopCardIdsList);
		List<ShoppingCartCustom> shoppingCartSubOrders = shoppingCartService.findShoppingCartById(shopCarsParams);
		Map<String, Integer> pMap=new HashMap<String, Integer>();//这个map是储存购物车每个商品的总数量（品牌款：日常销售+特卖活动使用）
		Map<String, Integer> pTypeMap=new HashMap<String, Integer>();//这个map是储存购物车每个活动类型的总数量（单品款使用）
		Map<String, Integer> areaMap=new HashMap<String, Integer>();//这个map是储存每个会场的购买数量（品牌款:会场活动中使用）


		boolean isContainsSpecMcht = false;	//购物车是否包含特殊商家，需求号1696，特殊商家不允许使用平台优惠券
		boolean hasSvipMarketingProduct = false;
		Set<Integer> mchtIdSet = Sets.newHashSet();
		if(CollectionUtils.isNotEmpty(shoppingCartSubOrders)){
			for (ShoppingCartCustom shoppingCart : shoppingCartSubOrders) {
				if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_AREA) || shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)){
					// 品牌款商品转为单品款商品时不让下单，来自需求1655 add by huangdl 2019-09-29
					if(!shoppingCart.getSaleType().equals("1")){
						throw new ArgException("已下架商品不可选购，请重新选择商品");
					}

					productIds.add(shoppingCart.getProductId());
				}
				if(Const.PRODUCT_ACTIVITY_TYPE_SECKILL.equals(shoppingCart.getActivityType()) || Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL.equals(shoppingCart.getActivityType())){
					orderType = Const.COMBINE_ORDER_TYPE_SECKILL;
				}
				mchtIdSet.add(shoppingCart.getMchtId());

				if (!sVipInfo.isSVip() && shoppingCart.getId() == svipMarketingCartId && svipMarketingSetting != null) { //非svip用户，且为选定的营销绑定商品
					//校验svip营销绑定
					checkSvipMarketing(shoppingCart.getProductId());

					hasSvipMarketingProduct = true;
				}
			}
			//活动中的
			if(CollectionUtils.isNotEmpty(productIds)){
				Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
				paramsActivityMap.put("productIdList", productIds);
				List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
				if(CollectionUtils.isNotEmpty(activityCustoms)){
					for (ActivityCustom activityCustom : activityCustoms) {
						if(activityCustom.getActivityEndTime().compareTo(date) < 0){
							//throw new ArgException("活动已结束");
							throw new ArgException("已下架商品不可选购，请重新选择商品");
						}else if(activityCustom.getActivityBeginTime().compareTo(date) > 0){
							throw new ArgException("活动未开始");
						}
						activityMap.put(activityCustom.getProductId().toString(), activityCustom);
					}
				}
			}
			for (ShoppingCartCustom shoppingCart : shoppingCartSubOrders) {
				Integer productType1Id = shoppingCart.getProductType1Id();
				Integer mchtId = shoppingCart.getMchtId();
				BigDecimal svipDiscount = shoppingCart.getSvipDiscount();
				if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_AREA) || shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)){
					if(activityMap.get(shoppingCart.getProductId().toString()) != null){
						shoppingCart.setActivityType(Const.PRODUCT_ACTIVITY_TYPE_AREA);
						shoppingCart.setSalePrice(shoppingCart.getSalePrice());
						if(areaMap.containsKey(shoppingCart.getActivityAreaId().toString())){
							areaMap.put(shoppingCart.getActivityAreaId().toString(), areaMap.get(shoppingCart.getActivityAreaId().toString())+shoppingCart.getQuantity());
						}else{
							areaMap.put(shoppingCart.getActivityAreaId().toString(), shoppingCart.getQuantity());
						}
					}else{
						shoppingCart.setActivityType(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP);
						shoppingCart.setSalePrice(shoppingCart.getMallPrice());
					}
				}else{
					if(pTypeMap.containsKey(shoppingCart.getActivityType())){
						pTypeMap.put(shoppingCart.getActivityType(), pTypeMap.get(shoppingCart.getActivityType())+shoppingCart.getQuantity());
					}else{
						pTypeMap.put(shoppingCart.getActivityType(), shoppingCart.getQuantity());
					}
				}
				if(pMap.containsKey(shoppingCart.getProductId().toString())){
					pMap.put(shoppingCart.getProductId().toString(), pMap.get(shoppingCart.getProductId().toString())+shoppingCart.getQuantity());
				}else{
					pMap.put(shoppingCart.getProductId().toString(), shoppingCart.getQuantity());
				}
				String isSvipBuy = "0";
				if ((sVipInfo.isSVip() && svipDiscount != null && svipDiscount.compareTo(zero) > 0
						&& (CollectionUtils.isEmpty(ignoredSvipCardIdSet) || !ignoredSvipCardIdSet.contains(shoppingCart.getId()))) //svip用户,未排除svip的使用
					|| (!sVipInfo.isSVip() && shoppingCart.getId() == svipMarketingCartId && svipMarketingSetting != null) //非svip用户，营销绑定
				) {
					//使用svip折扣
					BigDecimal svipSalePrice = productService.getProductSvipSalePrice(shoppingCart.getSalePrice(), svipDiscount, shoppingCart.getActivityType());
					if (svipSalePrice.compareTo(zero) > 0) {
						isSvipBuy = "1";
						shoppingCart.setSalePrice(svipSalePrice);
					}
				}
				shoppingCart.setIsSvipBuy(isSvipBuy);
				totalShoppingAmount = totalShoppingAmount.add(shoppingCart.getSalePrice().multiply(new BigDecimal(shoppingCart.getQuantity())));
				//运费
				Map<String, Object> freightMap=new HashMap<String, Object>();
				if(shoppingCart.getFreightTemplateId() != null){
					freightMap.put("productItemId", shoppingCart.getProductItemId());
					freightMap.put("freightTemplateId", shoppingCart.getFreightTemplateId());
					freightMap.put("quantity", shoppingCart.getQuantity());
					freights.add(freightMap);
				}
				if(!StringUtil.isBlank(mermberPlatformCouponId)){
					//map放置不同品列的购物车集合
					if(!shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY) 
							&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)
							&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
						if(categoryMap.containsKey(productType1Id)){
							cartList = categoryMap.get(productType1Id);
						}else{
							cartList = new ArrayList<ShoppingCartCustom>();
						}
						cartList.add(shoppingCart);		
						categoryMap.put(productType1Id, cartList);
					}
				}
				
				if(mchtCartsMap.containsKey(mchtId)){
					cartList = mchtCartsMap.get(mchtId);
				}else{
					cartList = new ArrayList<ShoppingCartCustom>();
				}
				cartList.add(shoppingCart);		
				mchtCartsMap.put(mchtId, cartList);
				if((version > 23 && system.equals(Const.ANDROID)) || (version > 23 && system.equals(Const.IOS))){
					if(shoppingCart.getStatus().equals("1") 
							&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY)
							&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)
							&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)
							&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL)
							&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_COUPON_PRODUCT)){
						throw new ArgException("该笔订单已失效");
					}
				}else{
					if(shoppingCart.getStatus().equals("1") 
							&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)
							&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)
							&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL)){
						throw new ArgException("该笔订单已失效");
					}
				}

			}
		}
		
		//单品活动限购控制
		Map<String, Integer> singleProductCountMap=new HashMap<String, Integer>();
		Integer singleProductCount=null;
		for(ShoppingCartCustom shoppingCart:shoppingCartSubOrders){
			Map<String, String> invalidPMap = productService.isInvalidProduct(shoppingCart.getProductId(), shoppingCart.getActivityType(), memberId, date, pMap, pTypeMap, activityMap, areaMap,"3",null);
			String isInvalidProduct = invalidPMap.get("isInvalidProduct");
			String limitErrorMsg = invalidPMap.get("limitErrorMsg");
			if("1".equals(isInvalidProduct)){
				throw new ArgException(limitErrorMsg);
			}
			if(shoppingCart.getSingleProductActivityId()!=null){
				SingleProductActivity singleProductActivity=singleProductActivityService.selectByPrimaryKey(shoppingCart.getSingleProductActivityId());
				Date beginTime = singleProductActivity.getBeginTime();
				Date endTime = singleProductActivity.getEndTime();
				if(date.before(beginTime)){
					throw new ArgException("活动未开始");
				}
				if(date.after(endTime)){
					//throw new ArgException("活动已结束");
					throw new ArgException("已下架商品不可选购，请重新选择商品");
				}
				singleProductCount=singleProductCountMap.get(singleProductActivity.getType());
				if(singleProductCount==null){
					singleProductCountMap.put(singleProductActivity.getType(), shoppingCart.getQuantity());
				}else{
					singleProductCountMap.put(singleProductActivity.getType(), singleProductCountMap.get(singleProductActivity.getType())+shoppingCart.getQuantity());
				}
				if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
					if(singleProductActivity != null && singleProductActivity.getPlatformPreferential() != null){
						platformSubsidyAmount = platformSubsidyAmount.add(singleProductActivity.getPlatformPreferential());
						platformSubsidyId = singleProductActivity.getId();
					}
				}
			}
		}

		SvipOrder svipOrder = null;
		if (hasSvipMarketingProduct) { //svip营销绑定
			svipOrder = createSvipOrder(svipMarketingSetting, memberId, date, payId, ip, system);
		}
		
		// 封装总订单数据
		Map<String,Object> addressParamsMap = new HashMap<String,Object>();
		addressParamsMap.put("memberId", memberId);
		addressParamsMap.put("addressId", addressId);
		MemberAddressCustom memberAddressCustom = memberAddressCustomMapper.getAddressById(addressParamsMap);
		String address = memberAddressCustom.getFullAddressName();
		Integer provinceId = memberAddressCustom.getProvince();
		String provinceName = memberAddressCustom.getProvinceName();

		Map<Integer, SubOrderExtend> subOrderExtendMap = Maps.newHashMap(); //key:subOrderId
		Map<Integer, OrderDtlExtend> orderDtlExtendMap = Maps.newHashMap(); //key:orderDtlId

		CombineOrder combineOrder = new CombineOrder();
		/*
		 * ================================总订单Start=========================
		 * ==========
		 */
		String combineOrderCode = "XG" + CommonUtil.getOrderCode();
		combineOrder.setCombineOrderCode(combineOrderCode);
		combineOrder.setMemberId(memberId);
		combineOrder.setMemberNick(memberNick);
		// 财务确认状态 0 未处理 1 已登记 2 已确认（付款成功，默认为已登记）
		combineOrder.setFinancialStatus(Const.COMBINE_ORDER_FINANCIAL_STATUS_NULL);
		// 状态，0 未付 1 已付 4 取消
		combineOrder.setStatus(Const.COMBINE_ORDER_STATUS_NOT_PAID);
		combineOrder.setSourceClient(sourceClient);
		// 订单类型 1.常规订单 2.秒杀订单
		combineOrder.setOrderType(orderType);
		combineOrder.setRemarks(remarks);
		combineOrder.setCreateBy(memberId);
		combineOrder.setCreateDate(date);
		combineOrder.setAddressId(addressId);
		combineOrder.setReceiverName(memberAddressCustom.getRecipient());
		combineOrder.setReceiverPhone(memberAddressCustom.getPhone());
		combineOrder.setReceiverAddress(address);
		combineOrder.setPaymentId(payId);
		combineOrder.setPayExtraInfo(extraInfo);
		combineOrder.setDelFlag("0");
		if("1".equals(openType)){
			combineOrder.setPromotionType("1");
		}
		if (svipOrder != null) {
			combineOrder.setSvipOrderId(svipOrder.getId());
		}

		combineOrderMapper.insertSelective(combineOrder);
		CombineOrderExtend combineOrderExtend = buildCombineOrderExtend(combineOrder);
		combineOrderExtendMapper.insert(combineOrderExtend);

		Map<Integer, MchtInfo> mchtInfoMap = buildMchtInfoMap(mchtIdSet);
		/*
		 * ================================总订单end===========================
		 * ========
		 */
		MchtInfo mchtInfo = null;
		Map<Integer, Integer> orderDtlMap = new HashMap<Integer, Integer>();
        List<String> specMchtCodeList = commonService.listSpecMchtCode();
		for (Integer mchtId : mchtCartsMap.keySet()) {
			mchtInfo =mchtInfoMap.get(mchtId);
			if(!isContainsSpecMcht && specMchtCodeList.contains(mchtInfo.getMchtCode())){
				isContainsSpecMcht = true;
			}
            boolean isSpopMcht = Const.MCHT_TYPE_SPOP.equals(mchtInfo.getMchtType());

            //需求号1696，特殊商家不允许使用积分
            if(isContainsSpecMcht && reqDataJson.getBoolean("isUserIntegral")){
				logger.warn(StringUtil.buildMsg("特殊商家不允许使用积分【商家ID:{}】", mchtId));
                throw new ArgException("系统开了下小差，请重新提交订单");
            }


			SubOrder subOrder = new SubOrder();
			String subOrderCode = "XGS" + CommonUtil.getOrderCode();
			subOrder.setSubOrderCode(subOrderCode);
			subOrder.setCombineOrderId(combineOrder.getId());
			subOrder.setMchtId(mchtId);
			// 商家类型 1.spop 2.pop
			subOrder.setMchtType(mchtInfo.getMchtType());
			// 是否自营(0 开放 1自营)
			subOrder.setIsManageSelf(mchtInfo.getIsManageSelf());
			subOrder.setStatus(Const.ORDER_STATUS_NOT_PAID);
			subOrder.setStatusDate(date);
			subOrder.setCreateBy(memberId);
			subOrder.setCreateDate(date);
			subOrder.setDelFlag("0");
			subOrder.setAuditStatus("1");
			subOrderMapper.insertSelective(subOrder);
			SubOrderExtend subOrderExtend = buildSubOrderExtend(subOrder);
			subOrderExtendMapper.insert(subOrderExtend);
			subOrderExtendMap.put(subOrderExtend.getSubOrderId(), subOrderExtend);

			//需要大学生认证的子订单
			boolean subOrderFlag = false;
			//插入订单日志
			orderLogService.insertOrderLog(subOrder.getId(),Const.ORDER_STATUS_NOT_PAID,memberId);
			for (ShoppingCartCustom shoppingCartOrderDtl : mchtCartsMap.get(mchtId)) {
				Integer quantity = shoppingCartOrderDtl.getQuantity();
				String activityType = shoppingCartOrderDtl.getActivityType();
				Integer locklQuantity = 0;//sku锁定的数量
				Integer productItemId = shoppingCartOrderDtl.getProductItemId();
				Integer cloudProductItemId = shoppingCartOrderDtl.getCloudProductItemId();
				List<String> propValIdList = new ArrayList<String>();
				Map<String,Object> productItemParamsMap = new HashMap<String,Object>();
				productItemParamsMap.put("productItemId", productItemId);
				productItemParamsMap.put("activityId", shoppingCartOrderDtl.getActivityId());
				ProductItemCustom productItemCustom = productItemCustomMapper.getProductInfoByActivityId(productItemParamsMap);
				//需要大学生认证的商品
				SysParamCfg sysParamCfg = DataDicUtil.getSysParamCfgModel("COLLEGE_STUDENT_CERTIFICATION_PRODUCT_ID", productItemCustom.getProductId().toString());
				if(sysParamCfg != null) {
					subOrderFlag = true;
				}
				//spop商家商品必须设置结算价
				if (isSpopMcht && (productItemCustom.getCostPrice() == null || productItemCustom.getCostPrice().compareTo(BigDecimal.ZERO) < 0)) {
					logger.warn(StringUtil.buildMsg("SPOP商家ID:{}的规格ID:{}未设置结算价", mchtInfo.getId(), productItemCustom.getId()));
					throw new ArgException("系统开了下小差，请重新提交订单");
				}

				for (String propValId : productItemCustom.getPropValId().split(",")) {
					propValIdList.add(propValId);
				}
				BigDecimal originalPrice = new BigDecimal("0");
				if(activityType.equals("101")){
					originalPrice = productItemCustom.getMallPrice();
				}else{
					originalPrice = productItemCustom.getSalePrice();
				}
				BigDecimal saleOrMallPrice = shoppingCartOrderDtl.getSalePrice();
				Map<String, Object> productParams = new HashMap<String, Object>();
				productParams.put("propValIdList", propValIdList);
				List<ProductPropValueCustom> productPropValueCustoms = productPropValueCustomMapper
						.getProductPropdesc(productParams);
				String productPropdesc = "";
				if (productPropValueCustoms != null && productPropValueCustoms.size() > 0) {
					for (ProductPropValueCustom productPropValueCustom : productPropValueCustoms) {
						productPropdesc += productPropValueCustom.getPropValue() + "_";
					}
					productPropdesc = productPropdesc.substring(0,productPropdesc.length()-1);
				}

				BigDecimal feeRate = BigDecimal.ZERO;
				if (Const.MCHT_TYPE_POP.equals(mchtInfo.getMchtType())) {
					// pop佣金比例（pop使用）
					Map<String, Object> popFeeRateParamsMap = new HashMap<>();
					popFeeRateParamsMap.put("productId", productItemCustom.getProductId());
					popFeeRateParamsMap.put("activityId", shoppingCartOrderDtl.getActivityId());
					popFeeRateParamsMap.put("mchtId", mchtId);
					popFeeRateParamsMap.put("brandId", productItemCustom.getBrandId());
					popFeeRateParamsMap.put("productType1Id", productItemCustom.getProductType1Id());
					popFeeRateParamsMap.put("productType2Id", productItemCustom.getProductType2Id());
					popFeeRateParamsMap.put("productType3Id", productItemCustom.getProductTypeId());
					feeRate = mchtProductBrandService.getPopFeeRate(popFeeRateParamsMap);
				}
				shoppingCartOrderDtl.setPopFeeRate(feeRate);
				OrderDtl orderDtl = new OrderDtl();
				Integer depositOrderListSize = 0;//该sku有下过定金
				if(depositOrderMap.containsKey(productItemId)){
					depositOrderList = depositOrderMap.get(productItemId);
					if(quantity >= depositOrderList.size()){
						depositOrderListSize = depositOrderList.size();//购物车购买的数量 >= 定金购买的数量
						locklQuantity = quantity - depositOrderListSize;
					}else{
						throw new ArgException("结算数量异常");
					}
				}else{
					locklQuantity = quantity;
				}
				//冻结数量
				if(locklQuantity > 0){
					Map<String, Object> cloudMap = productItemService.updateSkuLockedAmount(productItemId,locklQuantity,mchtId);
					if((boolean) cloudMap.get("isCooperation")){
						if(cloudMap.get("sellingPrice") != null){
							orderDtl.setSellingPrice(new BigDecimal(cloudMap.get("sellingPrice").toString()));
						}
						orderDtl.setCloudProductItemId(cloudProductItemId);
					}
				}
				orderDtl.setSubOrderId(subOrder.getId());
				orderDtl.setArtNo(productItemCustom.getArtNo());
				orderDtl.setActivityAreaId(shoppingCartOrderDtl.getActivityAreaId());
				orderDtl.setActivityId(shoppingCartOrderDtl.getActivityId());
				orderDtl.setSingleProductActivityId(shoppingCartOrderDtl.getSingleProductActivityId());
				orderDtl.setProductId(productItemCustom.getProductId());
				orderDtl.setProductItemId(shoppingCartOrderDtl.getProductItemId());
				orderDtl.setSkuPic(productItemCustom.getPic());
				orderDtl.setSku(productItemCustom.getSku());
				orderDtl.setBrandName(productItemCustom.getBrandName());
				orderDtl.setProductPropDesc(productPropdesc);
				orderDtl.setProductName(productItemCustom.getProductName());
				orderDtl.setQuantity(shoppingCartOrderDtl.getQuantity());
				orderDtl.setSalePrice(saleOrMallPrice);
				orderDtl.setTagPrice(productItemCustom.getTagPrice());
				orderDtl.setSettlePrice(productItemCustom.getCostPrice());
				orderDtl.setOriginalPrice(originalPrice);
				if("1".equals(shoppingCartOrderDtl.getIsSvipBuy())){
					orderDtl.setIsSvipBuy(shoppingCartOrderDtl.getIsSvipBuy());
					orderDtl.setSvipDiscount(shoppingCartOrderDtl.getSvipDiscount());
				}
				orderDtl.setPopCommissionRate(feeRate);
				orderDtl.setCreateDate(date);
				orderDtl.setCreateBy(memberId);
				orderDtl.setDelFlag("0");
				orderDtlMapper.insertSelective(orderDtl);
				OrderDtlExtend orderDtlExtend = buildOrderDtlExtend(orderDtl);
				orderDtlExtendMapper.insert(orderDtlExtend);
				orderDtlExtendMap.put(orderDtlExtend.getOrderDtlId(), orderDtlExtend);

				//把购物车和订单明细关联上
				shoppingCartOrderDtl.setOrderDtlId(orderDtl.getId());
				shoppingCartOrderDtl.setStatus("1");
				shoppingCartMapper.updateByPrimaryKeySelective(shoppingCartOrderDtl);
				//按活动类型储存活动总金额
				BigDecimal totalProductAmount = saleOrMallPrice.multiply(new BigDecimal(shoppingCartOrderDtl.getQuantity()+""));
				if(activityTotalAmountMap.containsKey(activityType)){
					activityTotalAmount = totalProductAmount.add(activityTotalAmountMap.get(activityType));
				}else{
					activityTotalAmount = totalProductAmount;
				}
				activityTotalAmountMap.put(activityType,activityTotalAmount);
				
				
				if(depositOrderListSize > 0){
					if(depositOrderMap.containsKey(productItemId)){
						for (int i = 0; i < depositOrderListSize; i++) {
							SubDepositOrder depositOrder = depositOrderList.get(i);
                            String belong = Const.COUPON_BELONG_TYPE_MCHT;
                            if (isSpopMcht) {
                                belong = Const.COUPON_BELONG_TYPE_PLATFORM;
                            }

							depositOrder.setOrderDtlId(orderDtl.getId());
							depositOrder.setStatus(Const.SUB_DEPOSIT_STATUS_TAIL_ARREADY_ORDER);
							depositOrder.setUpdateDate(date);
							depositOrder.setUpdateBy(memberId);
							subDepositOrderService.updateByPrimaryKeySelective(depositOrder); //更新订金子订单状态
							depositOrderStatusLogService.addLog(depositOrder.getId(), Const.SUB_DEPOSIT_STATUS_TAIL_ARREADY_ORDER,orderDtl.getId(), memberId); //保存订金子订单日志
							saveOrderPreferentialInfo("6", depositOrder.getId(), orderDtl.getId(), "3", "已付定金"+depositOrder.getDeposit()+" 抵用"+depositOrder.getDeductAmount(), 
									depositOrder.getDeductAmount(), belong, memberId);
							if(skuDepositTotalAmountMap.containsKey(productItemId)){
								skuDepositTotalAmountMap.put(productItemId, depositOrder.getDeductAmount().add(skuDepositTotalAmountMap.get(productItemId)));
							}else{
								skuDepositTotalAmountMap.put(productItemId, depositOrder.getDeductAmount());
							}
							activityTotalAmountMap.put(activityType, activityTotalAmountMap.get(activityType).subtract(depositOrder.getDeductAmount()));
						}
					}
				}
				//按活动类型归类购物车
				if(activityTypeMap.containsKey(activityType)){
					shoppingCartCustoms = activityTypeMap.get(activityType);
					shoppingCartCustoms.add(shoppingCartOrderDtl);
				}else{
					shoppingCartCustoms = new ArrayList<ShoppingCartCustom>();
					shoppingCartCustoms.add(shoppingCartOrderDtl);
				}
				activityTypeMap.put(activityType,shoppingCartCustoms);
				//获取每个活动类型的商品总数量
				if(activityTypeQuantityMap.containsKey(activityType)){
					activityTypeQuantityMap.put(activityType, activityTypeQuantityMap.get(activityType)+quantity);
				}else{
					activityTypeQuantityMap.put(activityType, quantity);
				}
				orderDtlMap.put(shoppingCartOrderDtl.getId(), orderDtl.getId());
			}
			//需要大学生认证的子订单
			if(subOrderFlag) {
				SubOrder so = new SubOrder();
				so.setId(subOrder.getId());
				so.setAuditStatus("2");
				subOrderMapper.updateByPrimaryKeySelective(so);
			}
		}
		for (ShoppingCartCustom cart : shoppingCartSubOrders) {
			cart.setOrderDtlId(orderDtlMap.get(cart.getId()));
		}
		
		//获取除最后一次的积分总和
		BigDecimal discountIntegralAmountAdd = new BigDecimal(0);
		// 每只商品的优惠的积分金额
		BigDecimal discountIntegralAmount = new BigDecimal(0);
		// 是否使用积分
		boolean isUserIntegral = reqDataJson.getBoolean("isUserIntegral");
		// 积分抵扣金额
		BigDecimal integralAmount = BigDecimal.ZERO;
		//服务说明
		String serviceDesc = "";
		List<SysParamCfg> serviceCfgList = DataDicUtil.getSysParamCfg("APP_SERVICE_DESCRIPTION");

		PreContext preContext = new PreContext();
		preContext.setMemberAllowance(memberAllowanceService.getMemberAllowanceBalance(memberId));

		/** ================================楼层活动优惠（商家优惠）Start========================*/
		Map<String, Object> activityPreferentialReturnMap = orderPreferentialInfoService.getPreferentialInfo(activityTypeMap, activityTotalAmountMap, memberId, activityMap, combineOrder.getId(), reqPRM, reqDataJson, skuDepositTotalAmountMap, skuDepositTotalMap, "1", mchtInfoMap, preContext);
		List<Map<String, Object>> salePriceMapList = (List<Map<String, Object>>) activityPreferentialReturnMap.get("salePriceMapList");
		activityTotalAmountMap = (Map<String, BigDecimal>) activityPreferentialReturnMap.get("activityTotalAmountMap");
		BigDecimal totalProductPreferentialAmount = new BigDecimal(activityPreferentialReturnMap.get("totalProductPreferentialAmount").toString());// 所有商品优惠总和
		totalShoppingAmount = totalShoppingAmount.subtract(totalProductPreferentialAmount);
		/** ================================平台券优惠（平台优惠）Start========================*/
		Map<String, Object> platCouponMap = memberCouponService.computingPlatPreferentialInfo(isContainsSpecMcht, salePriceMapList, totalShoppingAmount, mermberPlatformCouponId, memberId, combineOrder.getId(), activityTotalAmountMap, "1", reqPRM, mchtIdSet);
		totalShoppingAmount = new BigDecimal(platCouponMap.get("totalShoppingAmount").toString());//到这里已经计算出了总的实付支付金额 = 总的购物车金额-总的活动优惠金额-平台优惠金额
		salePriceMapList = (List<Map<String, Object>>) platCouponMap.get("salePriceMapList");
		activityTotalAmountMap = (Map<String, BigDecimal>) platCouponMap.get("activityTotalAmountMap");
		/** ================================店长权益（平台优惠）Start========================*/
		Map<String, Object> shopownerEquityMap = shopownerService.computingShopownerEquity(reqPRM,memberId,salePriceMapList,activityTotalAmountMap,"1");
		BigDecimal totalShopwnerEquityAmount = new BigDecimal(shopownerEquityMap.get("totalShopwnerEquityAmount").toString());
		salePriceMapList = (List<Map<String, Object>>) shopownerEquityMap.get("salePriceMapList");
		totalShoppingAmount = totalShoppingAmount.subtract(totalShopwnerEquityAmount);
		activityTotalAmountMap = (Map<String, BigDecimal>) shopownerEquityMap.get("activityTotalAmountMap");
		/** ================================积分（积分优惠）Start========================*/
		if(isUserIntegral){
			integralAmount = calculationIntegralAmount(activityTotalAmountMap,memberId,totalShoppingAmount,activityTypeQuantityMap,combineOrder.getId(),"1");
		}
		/** ================================运费Start========================*/
		Map<Integer, BigDecimal> productItemFreightMap = new HashMap<Integer, BigDecimal>();
		Map<Integer, ProvinceFreightCustom> provinceFreightCustom = new HashMap<Integer, ProvinceFreightCustom>();
		/** ================================新星计划分润余额比例========================*/
		List<SysParamCfg> cfgs = DataDicUtil.getSysParamCfg("APP_DISTRIBUTION_RATE");
		Double spreadAmountRate = 0.00;
		if(CollectionUtils.isNotEmpty(cfgs)){
			spreadAmountRate = Double.valueOf(cfgs.get(0).getParamValue());
		}
		if(provinceId != null){
			Map<String, Object> map = provinceFreightService.getProductFreightAmount(freights, provinceId);
			productItemFreightMap = (Map<Integer, BigDecimal>) map.get("productItemFreightMap");//每只sku对应的运费金额
			provinceFreightCustom = (Map<Integer, ProvinceFreightCustom>) map.get("freightCustom");//用途：获取每个模板的名称和每个模板的总运费
			totalFreight = productItemFreightMap.get(-1);//总运费设置key值为-1
		}
		CombineOrder combineOrder2 = new CombineOrder();
		int j = 0;
		for (ShoppingCartCustom shoppingCart : shoppingCartSubOrders) {
			Integer orderDtlId = shoppingCart.getOrderDtlId();
			Integer productItemId = shoppingCart.getProductItemId();
			BigDecimal saleOrMallPrice = shoppingCart.getSalePrice();
			String activityType = shoppingCart.getActivityType();
			mchtInfo = mchtInfoMap.get(shoppingCart.getMchtId());
			// 计算优惠券优惠金额
			if (salePriceMapList != null && salePriceMapList.size() > 0) {
				for (Map<String, Object> map : salePriceMapList) {
					if (map.get("orderDtlId").toString().equals(orderDtlId.toString())) {//8450
						OrderDtl orderDtl = orderDtlMapper.selectByPrimaryKey(Integer.valueOf(map.get("orderDtlId").toString()));
						OrderDtlExtend orderDtlExtend = orderDtlExtendMap.get(orderDtl.getId());
						BigDecimal productPreAmountDtl = (BigDecimal) map.get("productPreAmountDtl");//商家优惠（商家+定金）
						BigDecimal allowance = (BigDecimal) map.get("allowance"); //津贴使用
						String activityDiscountStr = map.get("activityDiscount").toString();
						BigDecimal platformAmount = zero;//平台优惠
						if(map.containsKey("platformAmount")){
							//每只商品的平台优惠金额
							platformAmount = (BigDecimal) map.get("platformAmount");
						}
						if(platformSubsidyAmount != null && platformSubsidyAmount.compareTo(zero) > 0 && Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL.equals(activityType)){
							platformAmount = platformAmount.add(platformSubsidyAmount);
							saveOrderPreferentialInfo("7", platformSubsidyId, orderDtlId, "4", "平台补贴", platformSubsidyAmount, "1",
									memberId);
						}
						if(!StringUtil.isBlank(activityDiscountStr)){
							activityDiscountStr = activityDiscountStr.substring(0, activityDiscountStr.length()-1);
						}
						saleOrMallPrice = saleOrMallPrice.multiply(new BigDecimal(orderDtl.getQuantity())).subtract(productPreAmountDtl).subtract(platformAmount);
						j++;
						//计算积分
						if(isUserIntegral){
							if (shoppingCartSubOrders.size() == j) { //购物车最后一个
								discountIntegralAmount = integralAmount.subtract(discountIntegralAmountAdd);
							} else {
								discountIntegralAmount = saleOrMallPrice.multiply(integralAmount).divide(totalShoppingAmount, 2, BigDecimal.ROUND_HALF_UP);
								if(saleOrMallPrice.compareTo(new BigDecimal("0.01")) == 0){
									discountIntegralAmount = new BigDecimal("0");
								}
								discountIntegralAmountAdd = discountIntegralAmountAdd.add(discountIntegralAmount);
							}
							orderDtl.setIntegralPreferential(discountIntegralAmount);
							saveOrderPreferentialInfo("5", 0, orderDtlId, "1", "积分优惠", discountIntegralAmount, Const.COUPON_BELONG_TYPE_PLATFORM,
									memberId);
						}
						orderDtl.setPlatformPreferential(platformAmount);
						orderDtl.setMchtPreferential(productPreAmountDtl);
						orderDtl.setAllowance(allowance);
						BigDecimal payAomutDtl = saleOrMallPrice.subtract(discountIntegralAmount);
						BigDecimal freight = productItemFreightMap.get(productItemId) == null ? zero : productItemFreightMap.get(productItemId);
						payAomutDtl = payAomutDtl.add(freight);
						orderDtl.setPayAmount(payAomutDtl);
						orderDtl.setFreight(freight);
						SubOrder subOrder = subOrderMapper.selectByPrimaryKey(orderDtl.getSubOrderId());
						SubOrderExtend subOrderExtend = subOrderExtendMap.get(subOrder.getId());
						//结算金额
						BigDecimal settleAmount = zero;
						BigDecimal commissionAmount = zero;
						BigDecimal quantity = new BigDecimal(orderDtl.getQuantity());
						//商家类型 1.开放spop 2.pop 3.自营spop
						if (subOrder.getMchtType().equals(Const.MCHT_TYPE_POP)) {
							//2:pop：
							//结算金额 = （成交价 * 数量 - 商家优惠 + 运费） * （1-pop佣金比率）
							settleAmount = orderDtl.getSalePrice().multiply(quantity).subtract(orderDtl.getMchtPreferential()).add(freight)
									.multiply(BigDecimal.ONE.subtract(orderDtl.getPopCommissionRate()));
							//服务费 = （成交价 * 数量 - 商家优惠 + 运费） * pop佣金比率
							commissionAmount = (orderDtl.getSalePrice().multiply(new BigDecimal(orderDtl.getQuantity())).subtract(orderDtl.getMchtPreferential()).add(freight))
									.multiply(orderDtl.getPopCommissionRate());
						} else {
							if (StateConst.FALSE.equals(mchtInfo.getIsManageSelf())) {
								//1:开放spop：
								//结算金额 = 结算价 * 数量 - 商家优惠 + 运费
								settleAmount = orderDtl.getSettlePrice().multiply(quantity).subtract(orderDtl.getMchtPreferential()).add(freight);
								//服务费 = （醒购价 - 结算价）* 数量
								commissionAmount = orderDtl.getSalePrice().subtract(orderDtl.getSettlePrice()).multiply(new BigDecimal(orderDtl.getQuantity()));
							} else {
								//3:自营spop：
								BigDecimal selfOperatedFreight = NumberUtil.get(shoppingCart.getManageSelfFreight());
								//结算金额 = 结算价 * 数量 - 商家优惠 + 自营运费
								settleAmount = orderDtl.getSettlePrice().multiply(quantity).subtract(orderDtl.getMchtPreferential()).add(selfOperatedFreight);
								//服务费 = （醒购价 - 结算价）* 数量 + 订单运费 - 自营运费
								commissionAmount = orderDtl.getSalePrice().subtract(orderDtl.getSettlePrice()).multiply(new BigDecimal(orderDtl.getQuantity()))
										.add(freight).subtract(selfOperatedFreight);
								orderDtlExtend.setManageSelfFreight(selfOperatedFreight);
							}

						}
						orderDtl.setSettleAmount(settleAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
						orderDtl.setCommissionAmount(commissionAmount.setScale(2,BigDecimal.ROUND_HALF_UP));
						if("1".equals(openType)){
							orderDtl.setDistributionMemberId(member.getInvitationMemberId());
							orderDtl.setDistributionAmount(orderDtl.getPayAmount().multiply(orderDtl.getPopCommissionRate()).multiply(new BigDecimal(spreadAmountRate)));
							orderDtl.setDistributionSettleStatus("0");
						}
						orderDtlMapper.updateByPrimaryKeySelective(orderDtl);
						orderDtlExtendMapper.updateByPrimaryKeySelective(orderDtlExtend);

						subOrder.setPlatformPreferential(
								subOrder.getPlatformPreferential().add(orderDtl.getPlatformPreferential()));
						subOrder.setMchtPreferential(
								subOrder.getMchtPreferential().add(orderDtl.getMchtPreferential()));
						subOrder.setIntegralPreferential(
								subOrder.getIntegralPreferential().add(orderDtl.getIntegralPreferential()));
						subOrder.setFreight(subOrder.getFreight().add(orderDtl.getFreight()));
						subOrder.setPayAmount(subOrder.getPayAmount().add(orderDtl.getPayAmount()));
						subOrder.setAmount(subOrder.getAmount()
								.add(orderDtl.getSalePrice().multiply(new BigDecimal(orderDtl.getQuantity()))));
						subOrder.setDistributionAmount(subOrder.getDistributionAmount().add(orderDtl.getDistributionAmount()));
						subOrderMapper.updateByPrimaryKeySelective(subOrder);
						subOrderExtend.setManageSelfFreight(subOrderExtend.getManageSelfFreight().add(orderDtlExtend.getManageSelfFreight()));
						subOrderExtendMapper.updateByPrimaryKeySelective(subOrderExtend);

						combineOrder2 = combineOrderMapper.selectByPrimaryKey(subOrder.getCombineOrderId());
						combineOrder2.setTotalPlatformPreferential(combineOrder2.getTotalPlatformPreferential()
								.add(orderDtl.getPlatformPreferential()));
						combineOrder2.setTotalMchtPreferential(
								combineOrder2.getTotalMchtPreferential().add(orderDtl.getMchtPreferential()));
						combineOrder2.setTotalIntegralPreferential(
								combineOrder2.getTotalIntegralPreferential().add(orderDtl.getIntegralPreferential()));
						combineOrder2.setFreight(combineOrder2.getFreight().add(orderDtl.getFreight()));
						combineOrder2
								.setTotalPayAmount(combineOrder2.getTotalPayAmount().add(orderDtl.getPayAmount()));
						combineOrder2.setTotalAmount(combineOrder2.getTotalAmount().add(orderDtl.getSalePrice().multiply(new BigDecimal(orderDtl.getQuantity()))));
						combineOrder2.setDistributionAmount(combineOrder2.getDistributionAmount().add(orderDtl.getDistributionAmount()));
						combineOrderMapper.updateByPrimaryKeySelective(combineOrder2);
						combineOrderExtend.setManageSelfFreight(combineOrderExtend.getManageSelfFreight().add(orderDtlExtend.getManageSelfFreight()));
						combineOrderExtendMapper.updateByPrimaryKeySelective(combineOrderExtend);
						//如果每个订单明细的支付金额-运费的金额 <=0,不能支付成功
						if(orderDtl.getPayAmount().subtract(freight).compareTo(new BigDecimal("0")) < 0 && "0".equals(orderDtl.getIsGive())){
							throw new ArgException("结算金额异常无法进行结算");
						}
						//快照
						ProvinceFreightCustom freightCustom = provinceFreightCustom.get(shoppingCart.getFreightTemplateId());
						String freightDesc = "";
						if(freightCustom != null){
							provinceName = freightCustom.getProvinceName();
							String freightTemplateName = freightCustom.getFreightTemplateName();
							freightDesc = provinceName+","+freightTemplateName;
						}else{
							freightDesc = provinceName+",免运费";
						}
						Map<String, Object> productServiceMap = productService.getProductService(null,orderDtl.getProductId(),serviceCfgList);
						serviceDesc = productServiceMap.get("serviceDec").toString();
						OrderProductSnapshot snapshot = new OrderProductSnapshot();
						snapshot.setOrderDtlId(orderDtlId);
						snapshot.setMainPicGroup(shoppingCart.getMainPicGroup());
						snapshot.setDescPicGroup(shoppingCart.getDescPicGroup());
						snapshot.setActivityDiscount(activityDiscountStr);
						snapshot.setSuitGroup(shoppingCart.getSuitGroup());
						snapshot.setSuitSex(shoppingCart.getSuitSex());
						snapshot.setSeason(shoppingCart.getSeason());
						snapshot.setServiceDesc(serviceDesc);
						snapshot.setFreightDesc(freightDesc);
						snapshot.setProductDesc(shoppingCart.getProductDesc());
						snapshot.setCreateBy(memberId);
						snapshot.setCreateDate(date);
						snapshot.setDelFlag("0");
						orderProductSnapshotService.insertSelective(snapshot);
					}
				}
			}

		}

		// 若存在svip营销绑定，此处最后加上svip购买金额，仅做逻辑判断，不保存入数据库
		if (hasSvipMarketingProduct && svipOrder != null) {
			combineOrder2.setTotalPayAmount(combineOrder2.getTotalPayAmount().add(svipOrder.getPayAmount()));
		}

		BigDecimal totalPayAmount = combineOrder2.getTotalPayAmount();
		BigDecimal differValue = totalPayAmount.subtract(new BigDecimal(payAmount));//相差值 = 前端的实付金额-后端的实付金额
		if(differValue.stripTrailingZeros().toPlainString().equals("0.01") || differValue.stripTrailingZeros().toPlainString().equals("-0.01")){
			logger.info("总订单【"+combineOrder2.getId()+"】实付金额相差一分钱pass");
		}else if(differValue.compareTo(zero) != 0){
			if(system.equals(Const.ANDROID) && version == 40){
				differValue = differValue.compareTo(zero) < 0 ? differValue.multiply(new BigDecimal("-1")) : differValue;
				if(differValue.compareTo(totalFreight) != 0){
					boolean isSameFreight = provinceFreightService.getReckonFreightIsSame(freights,provinceId,differValue,memberId,new BigDecimal(payAmount),totalFreight,totalPayAmount);
					//这边判断相差值不等与运费
					//或许前端切换了地址，没有刷新获取正确地址运费，还是使用默认地址的运费计算方式
					//在这边去获取默认运费的总金额，判断相差值是否相等，相等就放过去
					if(!isSameFreight){
						throw new ArgException("系统开了下小差，请重新提交订单");
					}
				}
			}else if(system.equals(Const.ANDROID) && version < 40){
				//ANDROID前端没有计算运费
				if(differValue.compareTo(totalFreight) != 0){
					//这边判断相差值不等与运费
					//或许前端切换了地址，没有刷新获取正确地址运费，还是使用默认地址的运费计算方式
					//在这边去获取默认运费的总金额，判断相差值是否相等，相等就放过去
					boolean isSameFreight = provinceFreightService.getReckonFreightIsSame(freights,provinceId,differValue,memberId,new BigDecimal(payAmount),totalFreight,totalPayAmount);
					if(!isSameFreight){
						throw new ArgException("系统开了下小差，请重新提交订单");
					}
				}
			}else if(system.equals(Const.IOS) && version < 45){
				//ios<45版本不去校验前后端金额差距
			}else{
				logger.warn(StringUtil.buildMsg("前后端实付金额不相等，后端实付:{}", totalPayAmount));
				throw new ArgException("系统开了下小差，请重新提交订单");
			}
		}else if(combineOrder2.getTotalPayAmount().compareTo(new BigDecimal(0)) <= 0){
			throw new ArgException("支付金额不能小于0");
		}
//		String a = "1";
//		if("1".equals(a)){
//			throw new ArgException("ceshi");
//		}
		memberCouponService.updateMemberCouponUseInfo(memberId, combineOrder.getId(), "1");
		if (payId == 1) {
			Map<String, Object> payMap = zfbAlipay(combineOrder2,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		} else if (payId == 2) {//WX
			SortedMap<String, Object> payMap = getWecharSign(combineOrder2,ip,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else{
			throw new ArgException("请用微信或支付宝支付");
		}
	}

	private CombineOrderExtend buildCombineOrderExtend(CombineOrder combineOrder) {
		CombineOrderExtend extend = new CombineOrderExtend();
		extend.setCombineOrderId(combineOrder.getId());
		extend.setManageSelfFreight(BigDecimal.ZERO);
		extend.setDelFlag(StateConst.FALSE);
		extend.setCreateBy(combineOrder.getCreateBy());
		extend.setCreateDate(combineOrder.getCreateDate());
		return extend;
	}

	private SubOrderExtend buildSubOrderExtend(SubOrder subOrder) {
		SubOrderExtend extend = new SubOrderExtend();
		extend.setSubOrderId(subOrder.getId());
		extend.setManageSelfFreight(BigDecimal.ZERO);
		extend.setDelFlag(StateConst.FALSE);
		extend.setCreateBy(subOrder.getCreateBy());
		extend.setCreateDate(subOrder.getCreateDate());
		return extend;
	}

	private OrderDtlExtend buildOrderDtlExtend(OrderDtl orderDtl) {
		OrderDtlExtend extend = new OrderDtlExtend();
		extend.setOrderDtlId(orderDtl.getId());
		extend.setManageSelfFreight(BigDecimal.ZERO);
		extend.setCreateBy(orderDtl.getCreateBy());
		extend.setCreateDate(orderDtl.getCreateDate());
		extend.setDelFlag(StateConst.FALSE);
		return extend;
	}

	private void checkSvipMarketing(int svipMarketingProductId) {
		if (svipMarketingProductId > 0) {
			if (!svipBindProductService.isProductBind(svipMarketingProductId)) {
				logger.warn("该商品未绑定SVIP营销");
				throw new ArgException("订单异常请重新下单");
			}
		}

	}

	private SvipOrder createSvipOrder(SvipMarketingSetting svipMarketingSetting, Integer memberId, Date date,int payId,String ip,String system) {
		SvipOrder order = new SvipOrder();
		String orderCode = "SVIP" + CommonUtil.getOrderCode();
		String buyType = "3"; //1.首次购买 2.会员续费 3.绑定商品购买
		order.setMemberId(memberId);
		order.setOrderCode(orderCode);
		order.setBuyType(buyType);
		order.setSvipMemberSettingId(0);
		order.setYearsOfPurchase(svipMarketingSetting.getYear());
		order.setSourceClient(system);
		order.setIp(ip);
		order.setPaymentId(payId);
		order.setPayAmount(svipMarketingSetting.getPrice());
		order.setStatus("0"); //0.未付款1.已付款2.已退款
		order.setCreateBy(memberId);
		order.setCreateDate(date);
		order.setDelFlag(StateConst.FALSE);
		svipOrderService.insertSelective(order);
		return order;
	}

	public Set<Integer> getIgnoredSvipCartIdSet(JSONObject reqDataJson) {
		if (reqDataJson.has("ignoredSvipIds")) {
			String ignoredSvipIds = reqDataJson.optString("ignoredSvipIds");
			if (StringUtil.isBlank(ignoredSvipIds)) {
				return Collections.emptySet();
			}
			Set<Integer> ignoredSvipCardIdSet = Sets.newHashSet();
			for (String idStr : ignoredSvipIds.split(",")) {
				ignoredSvipCardIdSet.add(Integer.valueOf(idStr));
			}
			return ignoredSvipCardIdSet;
		}
		return Collections.emptySet();
	}

	public Map<Integer, MchtInfo> buildMchtInfoMap(Set<Integer> mchtIdSet) {
		if (CollectionUtils.isEmpty(mchtIdSet)) return Collections.emptyMap();

		MchtInfoExample example = new MchtInfoExample();
		example.createCriteria().andIdIn(Lists.newArrayList(mchtIdSet));
		List<MchtInfo> list = mchtInfoMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) return Collections.emptyMap();
		Map<Integer, MchtInfo> map = Maps.newHashMap();
		for (MchtInfo mchtInfo : list) {
			map.put(mchtInfo.getId(), mchtInfo);
		}
		return map;
	}

	/**
	 * 计算积分抵扣金额
	 *
	 * @param activityTotalAmountMap	各activityType对应的活动总金额（已扣除定金抵扣金额、单品优惠金额、会场或商家优惠、平台优惠），可能有多扣了一次定金
	 * @param memberId
	 * @param payMoney					购物车总金额（已扣减定金、单品优惠、活动或商家优惠、平台优惠、店长优惠）
	 * @param activityTypeQuantityMap
	 * @param combineOrderId
	 * @param tType
	 * @return 本次积分可抵扣金额
	 */
	public BigDecimal calculationIntegralAmount(Map<String, BigDecimal> activityTotalAmountMap,Integer memberId, BigDecimal payMoney, Map<String, Integer> activityTypeQuantityMap, Integer combineOrderId, String tType) {
		BigDecimal integralAmount = BigDecimal.ZERO;
		Integer integral = 0;//总积分
		//用户积分计算,取得的最大积分优惠 < 用户的实付金额/INTEGERAL_DISOUNT  (INTEGERAL_DISOUNT=0.5)
		MemberAccountExample memberAccountExample = new MemberAccountExample();
		memberAccountExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		List<MemberAccount> memberAccounts = memberAccountMapper.selectByExample(memberAccountExample);
		//金额和积分的兑换比例
		BigDecimal iChargr = BigDecimal.ZERO;
		MemberInfo memberInfo = memberInfoService.findMemberById(memberId);
		if(!memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_TOURIST)){
			IntegralType integralType = integralTypeService.findModel(Integer.valueOf(Const.INTEGRAL_TYPE_ORDER_PAYMENT));
			if(integralType != null){
				String type = integralType.getIntType();
				if(type.equals("2")){
					iChargr = integralType.getiCharge() == null ? BigDecimal.ZERO : new BigDecimal(integralType.getiCharge());
				}
			}
		}
		for (String activityType : activityTotalAmountMap.keySet()) {
			BigDecimal activitySalePrice = activityTotalAmountMap.get(activityType);
			Integer productQuantity = activityTypeQuantityMap.get(activityType);
			integral += calculationIntegral(activityType, activitySalePrice, iChargr, memberAccounts, payMoney, productQuantity, integral);
		}
		
		//计算可抵扣总积分
		if(NumberUtil.gtZero(iChargr)){
			//计算可抵扣积分总金额
			integralAmount = new BigDecimal(integral).divide(iChargr, 2, BigDecimal.ROUND_DOWN);
			// 订单支付使用积分
			if (memberAccounts != null && memberAccounts.size() > 0 && "1".equals(tType)) {
				MemberAccount memberAccount = memberAccounts.get(0);
				if(integral > memberAccount.getIntegral()){
					throw new ArgException("积分使用异常");
				}
				// 订单支付使用积分
				if (integral > 0) {
					memberAccount.setIntegral(memberAccount.getIntegral() - integral);
					memberAccount.setUpdateBy(memberId);
					memberAccountService.updateModel(memberAccount);
					integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_ACCOUNT,
							Const.INTEGRAL_TYPE_ORDER_PAYMENT, integral, memberAccount.getIntegral(),
							combineOrderId, memberId,"1");
				}
 			}
		}
		return integralAmount;
	}

	private String getExtraInfo(JSONObject reqPRM, JSONObject reqDataJson, String ip) {
		String extraInfo = "";
		//手机操作系统
		String moblieSystem = "";
		//手机品牌
		String mobileBrand = "";
		//手机型号
		String phoneModel = "";
		//渠道
		String sprChnl = "";
		//app版本
		String appVersion = "";
		if(reqPRM.containsKey("systemVersion")){
			moblieSystem = reqPRM.getString("systemVersion");
		}
		if(reqDataJson.containsKey("mobileBrand")){
			mobileBrand = reqDataJson.getString("mobileBrand");
		}
		if(reqDataJson.containsKey("phoneModel")){
			phoneModel = reqDataJson.getString("phoneModel");		
		}
		if(reqDataJson.containsKey("sprChnl")){
			sprChnl = reqDataJson.getString("sprChnl");
			sprChnl = DataDicUtil.getSprChnl(sprChnl);
		}
		if(reqDataJson.containsKey("appVersion")){
			appVersion = reqDataJson.getString("appVersion");
		}
		extraInfo = "品牌:" + mobileBrand + "|型号:" + phoneModel + 
					"|系统版本:" +moblieSystem + "|醒购App版本号:" + appVersion +
					"|渠道号:" + sprChnl + "|公网ip:" + ip;
		return extraInfo;
	}

	private Map<String, Object> isHasStock(JSONObject reqDataJson,Map<Integer, Map<String, Object>> skuDepositTotalMap) {
		boolean isHasStock = true;
		//先判断是否有库存
		String shopCardIds = reqDataJson.getString("shopCardIds");
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
				Integer productItemId = shoppingCart.getProductItemId();
				ProductItem productItem = productItemService.selectByPrimaryKey(productItemId);
				Product product = productService.selectByPrimaryKey(productItem.getProductId());
				String auditStatus = product.getAuditStatus() == null ? "" : product.getAuditStatus();
				String status = product.getStatus() == null ? "" : product.getStatus();
				Map<String, Object> map = new HashMap<String, Object>();
				Integer productId = productItem.getProductId();
				Integer stock = productItem.getStock();//库存
				Integer lockedAmount = productItem.getLockedAmount();//冻结
				//需要加上该会员的预售订单的商品数量 2019年10月21日14:05:28
				Integer depositQuantity = 0;
				if(skuDepositTotalMap.containsKey(productItemId)){
					Map<String, Object>  skuDepositMap = skuDepositTotalMap.get(productItemId);
					depositQuantity = Integer.valueOf(skuDepositMap.get("depositQuantity").toString());
				}
				stock = stock - lockedAmount + depositQuantity;
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
			}
		}
		
		Map<String, Object> payMap = new HashMap<>();
		payMap.put("isHasStock", isHasStock);
		payMap.put("dataList", dataList);
		return payMap;
	}


//	/**
//	 * 
//	 * 方法描述 ：计算不做优惠的原价(废弃)
//	 * @author  chenwf: 
//	 * @date 创建时间：2017年6月3日 上午9:47:57 8229
//	 * @version
//	 * @param shoppingCarts
//	 * @param min
//	 * @param skuDepositTotalMap 
//	 * @return
//	 */
//	public BigDecimal getDiscountAmount(List<ShoppingCartCustom> shoppingCarts, int min, Map<Integer, Map<String, Object>> skuDepositTotalMap) {
//		//b false;表示多买优惠类型中的M件N元，不用再去扣除定金优惠
//		BigDecimal price = new BigDecimal("0");
//		List<BigDecimal> shopCardSalePriceList = new ArrayList<BigDecimal>();
//		for (ShoppingCartCustom shoppingCart : shoppingCarts) {
//			Integer quantity = shoppingCart.getQuantity();
//			Integer productItemId = shoppingCart.getProductItemId();
//			BigDecimal salePrice = shoppingCart.getSalePrice();
//			BigDecimal productAeductAmount = new BigDecimal("0");
//			Integer depositQuantity = 0;
//			if(skuDepositTotalMap.containsKey(productItemId)){
//				Map<String, Object>  skuDepositMap = skuDepositTotalMap.get(productItemId);
//				depositQuantity = Integer.valueOf(skuDepositMap.get("depositQuantity").toString());
//				BigDecimal deductAmount = new BigDecimal(skuDepositMap.get("deductAmount").toString());
//				productAeductAmount = deductAmount.divide(new BigDecimal(depositQuantity+""));//得出每个商品的定金可抵扣价格
//			}
//			for (int i = 0; i < quantity; i++) {
//				BigDecimal newSalePrice = salePrice;
//				if(i+1 <= depositQuantity){
//					newSalePrice = salePrice.subtract(productAeductAmount);
//				}
//				shopCardSalePriceList.add(newSalePrice);
//			}
//		}
//		//升序排序
//		Collections.sort(shopCardSalePriceList);
//		for (int i = 0; i < min; i++) {
//			price = shopCardSalePriceList.get(i).add(price);
//		}
//		return price;
//	}
	
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
	public void saveOrderPreferentialInfo(String preferentialType, Integer preferentialId, Integer orderDtlId,
			String rang, String name, BigDecimal productPreAmountDtl, String belong, Integer membertId) {
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
			orderPreferentialInfo.setCreateBy(membertId);
			orderPreferentialInfo.setDelFlag("0");
			orderPreferentialInfoMapper.insertSelective(orderPreferentialInfo);
		}
	}

	/**
	 * 
	 * 方法描述 ：保存满额赠送，累加
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年5月24日 上午11:41:26
	 * @version
	 * @param combineOrderId
	 * @param shoppingCart
	 * @param productId
	 * @param memberId
	 * @param date
	 * @param productNum
	 * @param productFlag
	 * @param couponFlag
	 * @param giveCouponId
	 * @param integralFlag
	 * @param integeral
	 * @param salePrice
	 * @param minimum
	 * @param preferentialType
	 * @param rang
	 * @param belong
	 */
	public void saveCumulativeInfo(Integer combineOrderId, ShoppingCart shoppingCart, Integer productId,
			Integer memberId, Date date, Integer productNum, String productFlag, String couponFlag, String giveCouponId,
			String integralFlag, Integer integeral, BigDecimal salePrice, BigDecimal minimum, String belong,
			String rang, String preferentialType, Integer preferentialId) {
		// 用总价除以最低价格，获取可以优惠几次
		int quantity = salePrice.divide(minimum,0,BigDecimal.ROUND_DOWN).intValue();
		// 满额赠送商品，插入订单明细中
		if (!StringUtil.isBlank(productFlag) && productFlag.equals("1")) {
			ProductCustom productCustom = productCustomMapper.getProductInfo(productId);
			if(productCustom != null && productCustom.getId() != null){
				Integer mchtId = productCustom.getMchtId();
				SubOrderExample subOrderExample = new SubOrderExample();
				subOrderExample.createCriteria().andCombineOrderIdEqualTo(combineOrderId)
						.andMchtIdEqualTo(mchtId);
				List<SubOrder> subOrders = subOrderMapper.selectByExample(subOrderExample);
				if (subOrders != null && subOrders.size() > 0) {
					BigDecimal zero = new BigDecimal("0");
					for (int i = 0; i < quantity; i++) {
						Integer subOrderId = subOrders.get(0).getId();
						OrderDtl orderDtl = new OrderDtl();
						orderDtl.setSubOrderId(subOrderId);
						orderDtl.setActivityAreaId(shoppingCart.getActivityAreaId());
						orderDtl.setActivityId(shoppingCart.getActivityId());
						orderDtl.setBrandName(productCustom.getBrandName());
						orderDtl.setCreateBy(memberId);
						orderDtl.setCreateDate(date);
						orderDtl.setDelFlag("0");
						orderDtl.setProductId(productId);
						orderDtl.setProductItemId(0);
						orderDtl.setSkuPic(productCustom.getPic());
						orderDtl.setProductName(productCustom.getName());
						orderDtl.setArtNo(productCustom.getArtNo());
						orderDtl.setProductPropDesc("赠品");
						orderDtl.setIsGive("1");
						orderDtl.setQuantity(productNum);
						orderDtl.setRemarks("赠送商品");
						orderDtl.setSettleAmount(zero);
						orderDtl.setCommissionAmount(zero);
						orderDtlMapper.insertSelective(orderDtl);
					}
				}
			}
		}
		// 满额赠送优惠券
		if (!StringUtil.isBlank(couponFlag) && couponFlag.equals("1")) {
			String[] giveCouponIds = giveCouponId.split(",");
			for (String id : giveCouponIds) {
				for (int i = 0; i < quantity; i++) {
					Coupon coupon = couponMapper.selectByPrimaryKey(Integer.valueOf(id));
					Date expiryBeginDate = coupon.getExpiryBeginDate();
					Date expiryEndDate = coupon.getExpiryBeginDate();
					Integer expiryDates = coupon.getExpiryDays();
					if(coupon.getExpiryType().equals("2")){
						expiryBeginDate = date;
						expiryEndDate = DateUtil.addDay(date, expiryDates);
					}
					MemberCoupon memberCoupon = new MemberCoupon();
					memberCoupon.setMemberId(memberId);
					memberCoupon.setCouponId(coupon.getId());
					memberCoupon.setRecDate(date);
					memberCoupon.setExpiryBeginDate(expiryBeginDate);
					memberCoupon.setExpiryEndDate(expiryEndDate);
					memberCoupon.setCreateDate(date);
					memberCoupon.setCreateBy(memberId);
					memberCoupon.setDelFlag("0");
					memberCouponMapper.insertSelective(memberCoupon);
				}
			}
		}

		// 满额赠送金币
		if (!StringUtil.isBlank(integralFlag) && integralFlag.equals("1")) {
			for (int i = 0; i < quantity; i++) {
				MemberAccountExample memberAccountExample = new MemberAccountExample();
				memberAccountExample.createCriteria().andMemberIdEqualTo(memberId);
				MemberAccount memberAccount = memberAccountMapper.selectByExample(memberAccountExample).get(0);
				memberAccount.setIntegral(integeral + memberAccount.getIntegral());
				memberAccount.setUpdateBy(memberId);
				memberAccount.setUpdateDate(date);
				memberAccountMapper.updateByPrimaryKeySelective(memberAccount);
				integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME,
						Const.INTEGRAL_TYPE_SHOPPONG, integeral, memberAccount.getIntegral(),
						combineOrderId, memberId,"1");
			}
		}
	}

	/**
	 * 
	 * 方法描述 ：保存满额赠送，非累加 或者 买即赠
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年5月24日 下午2:54:56
	 * @version
	 * @param combineOrderId
	 * @param shoppingCart
	 * @param productId
	 * @param memberId
	 * @param date
	 * @param productNum
	 * @param productFlag
	 * @param couponFlag
	 * @param giveCouponId
	 * @param integralFlag
	 * @param integeral
	 * @param preferentialType
	 * @param rang
	 * @param belong
	 */
	public void saveNonCumulativeInfo(Integer combineOrderId, ShoppingCart shoppingCart, Integer productId,
			Integer memberId, Date date, Integer productNum, String productFlag, String couponFlag, String giveCouponId,
			String integralFlag, Integer integeral, String belong, String rang, String preferentialType,
			Integer preferentialId) {
		// 满额赠送商品，插入订单明细中
		if (!StringUtil.isBlank(productFlag) && productFlag.equals("1")) {
			ProductCustom productCustom = productCustomMapper.getProductInfo(productId);
			if(productCustom != null && productCustom.getId() != null){
				Integer mchtId = productCustom.getMchtId();
				SubOrderExample subOrderExample = new SubOrderExample();
				subOrderExample.createCriteria().andCombineOrderIdEqualTo(combineOrderId)
						.andMchtIdEqualTo(mchtId);
				List<SubOrder> subOrders = subOrderMapper.selectByExample(subOrderExample);
				BigDecimal zero = new BigDecimal("0");
				if (subOrders != null && subOrders.size() > 0) {
					Integer subOrderId = subOrders.get(0).getId();
					OrderDtl orderDtl = new OrderDtl();
					orderDtl.setSubOrderId(subOrderId);
					orderDtl.setActivityAreaId(shoppingCart.getActivityAreaId());
					orderDtl.setActivityId(shoppingCart.getActivityId());
					orderDtl.setBrandName(productCustom.getBrandName());
					orderDtl.setCreateBy(memberId);
					orderDtl.setCreateDate(date);
					orderDtl.setDelFlag("0");
					orderDtl.setProductId(productId);
					orderDtl.setProductItemId(0);
					orderDtl.setSkuPic(productCustom.getPic());
					orderDtl.setProductName(productCustom.getName());
					orderDtl.setArtNo(productCustom.getArtNo());
					orderDtl.setProductPropDesc("赠品");
					orderDtl.setIsGive("1");
					orderDtl.setQuantity(productNum);
					orderDtl.setRemarks("赠送商品");
					orderDtl.setSettleAmount(zero);
					orderDtl.setCommissionAmount(zero);
					orderDtlMapper.insertSelective(orderDtl);
				}
			}
		}
		// 满额赠送优惠券
		if (!StringUtil.isBlank(couponFlag) && couponFlag.equals("1")) {
			String[] giveCouponIds = giveCouponId.split(",");
			for (String id : giveCouponIds) {
				Coupon coupon = couponMapper.selectByPrimaryKey(Integer.valueOf(id));
				Date expiryBeginDate = coupon.getExpiryBeginDate();
				Date expiryEndDate = coupon.getExpiryBeginDate();
				Integer expiryDates = coupon.getExpiryDays();
				if(coupon.getExpiryType().equals("2")){
					expiryBeginDate = date;
					expiryEndDate = DateUtil.addDay(date, expiryDates);
				}
				MemberCoupon memberCoupon = new MemberCoupon();
				memberCoupon.setMemberId(memberId);
				memberCoupon.setCouponId(coupon.getId());
				memberCoupon.setRecDate(date);
				memberCoupon.setExpiryBeginDate(expiryBeginDate);
				memberCoupon.setExpiryEndDate(expiryEndDate);
				memberCoupon.setCreateDate(date);
				memberCoupon.setCreateBy(memberId);
				memberCoupon.setDelFlag("0");
				memberCouponMapper.insertSelective(memberCoupon);
			}
		}

		// 满额赠送金币
		if (!StringUtil.isBlank(integralFlag) && integralFlag.equals("1")) {
			MemberAccountExample memberAccountExample = new MemberAccountExample();
			memberAccountExample.createCriteria().andMemberIdEqualTo(memberId);
			MemberAccount memberAccount = memberAccountMapper.selectByExample(memberAccountExample).get(0);
			memberAccount.setIntegral(integeral + memberAccount.getIntegral());
			memberAccount.setUpdateBy(memberId);
			memberAccount.setUpdateDate(date);
			memberAccountMapper.updateByPrimaryKeySelective(memberAccount);
			integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME,
					Const.INTEGRAL_TYPE_SHOPPONG, integeral, memberAccount.getIntegral(),
					combineOrderId, memberId,"1");
		}

	}

	
	/**
	 * 
	 * 方法描述 ：微信支付集成
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月3日 下午4:58:40 
	 * @version
	 * @param combineOrder
	 * @param type 
	 * @return
	 * @throws ArgException 
	 */
	private SortedMap<String, Object> getWecharSign(CombineOrder combineOrder,String ip, String type) {
		String appId = wecharConfigUtil.getProperty("WX_APP_ID");
		String mchId = wecharConfigUtil.getProperty("WX_MCH_ID");
		String notifyUrl = wecharConfigUtil.getProperty("WX_NOTIFY_URL");
		if("2".equals(type)){//1下订单回调  2预售订单回调 3SVIP订单
			notifyUrl = wecharConfigUtil.getProperty("WX_DEPOSIT_NOTIFY_URL");
		}else if("3".equals(type)){
			notifyUrl = wecharConfigUtil.getProperty("WX_SVIP_NOTIFY_URL");
		}
		String nonceStr = RandCharsUtils.getRandomString(32);
		String body = combineOrder.getCombineOrderCode();
		//String detail = "cwf测试微信支付1";
		String attach = "1234";
		String outTradeNo = combineOrder.getCombineOrderCode();
		int totalFee = combineOrder.getTotalPayAmount().multiply(new BigDecimal(100)).intValue();
		//int totalFee = 1;
		String timeStart = RandCharsUtils.timeStart();
		String timeExpire = RandCharsUtils.timeExpire();
		//H5支付的交易类型为MWEB  app支付的交易类型为APP  公众号支付的交易类型为JSAPI
		String tradeType = "APP";
		String spbillCreateIp = ip;
		
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", appId);
		params.put("mch_id", mchId);
		params.put("notify_url", notifyUrl);
		params.put("nonce_str", nonceStr);
		params.put("body", body);
		//params.put("detail", detail);
		params.put("attach", attach);
		params.put("out_trade_no", outTradeNo);
		params.put("total_fee", totalFee);
		params.put("time_start", timeStart);
		params.put("time_expire", timeExpire);
		params.put("trade_type", tradeType);
		params.put("spbill_create_ip", spbillCreateIp);
		
		String sign = WXSignUtils.createSign("UTF-8", params);
		params.put("sign", sign);
		
		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appId);
		unifiedorder.setMch_id(mchId);
		unifiedorder.setNotify_url(notifyUrl);
		unifiedorder.setNonce_str(nonceStr);
		unifiedorder.setBody(body);
		unifiedorder.setAttach(attach);
		unifiedorder.setOut_trade_no(outTradeNo);
		unifiedorder.setTotal_fee(totalFee);
		unifiedorder.setTime_start(timeStart);
		unifiedorder.setTrade_type(tradeType);
		unifiedorder.setSpbill_create_ip(spbillCreateIp);
		unifiedorder.setSign(sign);
		unifiedorder.setTime_expire(timeExpire);
		//unifiedorder.setDetail(detail);

		
		//获取xml信息
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		
		String wxUrl = wecharConfigUtil.getProperty("WX_UNIFIED_ORDER");
		
		String method = "POST";
		
		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
		
		List<Element> list = JdomParseXmlUtils.getWXPayResultElement(weixinPost);
		SortedMap<String, Object> payParams = new TreeMap<String, Object>();
		String prepay_id = "";
		String err_code = "";
		String err_code_des = "";
		//获取微信统一下单返回参数   封装model类
		UnifiedorderResult unifiedorderResult = getWxUnifiedorderResult(list);
		if(unifiedorderResult != null){
			if(unifiedorderResult.getReturn_code().equals("SUCCESS")){
				if(unifiedorderResult.getResult_code().equals("SUCCESS")){
					prepay_id = unifiedorderResult.getPrepay_id();
				}
				
			}else{
				err_code = unifiedorderResult.getErr_code();
				err_code_des = unifiedorderResult.getErr_code_des();
			}
		}
	
		payParams.put("appid", appId);
		payParams.put("partnerid", mchId);
		payParams.put("package", "Sign=WXPay");
		payParams.put("noncestr", RandCharsUtils.getRandomString(32));
		payParams.put("timestamp", new Date().getTime()/1000);
		payParams.put("prepayid", prepay_id);
		String paySign = WXSignUtils.createSign("UTF-8", payParams);
		//添加packageName, package与java关键字冲突
		payParams.put("packageName", "Sign=WXPay");
		payParams.put("err_code", err_code);
		payParams.put("err_code_des", err_code_des);
		payParams.put("sign", paySign);
		payParams.put("combineOrderId", combineOrder.getId());
		payParams.put("isHasStock", true);//用来判断库存 true 表示有库存
		payParams.put("orderPayMsg", "");
		payParams.put("orderPaySuccess", Const.COMBINE_ORDER_STATUS_NOT_PAID);
		return payParams;
	}

	private UnifiedorderResult getWxUnifiedorderResult(List<Element> list) {
		UnifiedorderResult result = null;
		if (list != null && list.size() > 0) {
			result = new UnifiedorderResult();
			for (Element element : list) {

				if ("return_code".equals(element.getName())) {
					result.setReturn_code(element.getText());
				}

				if ("return_msg".equals(element.getName())) {
					result.setReturn_msg(element.getText());
				}

				if ("appid".equals(element.getName())) {
					result.setAppid(element.getText());
				}

				if ("mch_id".equals(element.getName())) {
					result.setMch_id(element.getText());
				}
				
				if ("device_info".equals(element.getName())) {
					result.setDevice_info(element.getText());
				}

				if ("nonce_str".equals(element.getName())) {
					result.setNonce_str(element.getText());
				}

				if ("sign".equals(element.getName())) {
					result.setSign(element.getText());
				}

				if ("err_code".equals(element.getName())) {
					result.setErr_code(element.getText());
				}

				if ("err_code_des".equals(element.getName())) {
					result.setErr_code_des(element.getText());
				}
				
				if ("result_code".equals(element.getName())) {
					result.setResult_code(element.getText());
				}
				
				if ("trade_type".equals(element.getName())) {
					result.setTrade_type(element.getText());
				}
				
				if ("prepay_id".equals(element.getName())) {
					result.setPrepay_id(element.getText());
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * 方法描述 ：支付宝集成
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月3日 下午3:26:04 
	 * @version
	 * @param combineOrder
	 * @param type
	 * @return
	 * 
	 */
	private Map<String, Object> zfbAlipay(CombineOrder combineOrder, String type){
		Map<String, String> param = new HashMap<>();
		// 公共请求参数
		String notifyUrl = alipayConfigUtil.getProperty("ALIPAY_NOTIFY_URL");
		if("2".equals(type)){//1下订单回调  2预售订单回调 3 SVIP订单
			notifyUrl = alipayConfigUtil.getProperty("ALIPAY_DEPOSIT_NOTIFY_URL");
		}else if("3".equals(type)){
			notifyUrl = alipayConfigUtil.getProperty("ALIPAY_SVIP_NOTIFY_URL");
		}
		param.put("app_id", alipayConfigUtil.getProperty("ALIPAY_APP_ID"));// 商户订单号
		param.put("method", "alipay.trade.app.pay");// 交易金额
		param.put("format", AlipayConstants.FORMAT_JSON);
		param.put("charset", AlipayConstants.CHARSET_UTF8);
		param.put("timestamp", DateUtil.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		param.put("version", "1.0");
		param.put("notify_url", notifyUrl); // 支付宝服务器主动通知商户服务
		param.put("sign_type", AlipayConstants.SIGN_TYPE_RSA2);

		Map<String, Object> pcont = new HashMap<>();
		// 支付业务请求参数
		pcont.put("out_trade_no", combineOrder.getCombineOrderCode()); // 商户订单号
		pcont.put("total_amount", combineOrder.getTotalPayAmount().stripTrailingZeros().toPlainString());// 交易金额
		//pcont.put("total_amount", String.valueOf("0.01"));// 交易金额
		pcont.put("subject", combineOrder.getCombineOrderCode()); // 订单标题
		pcont.put("body", "聚买商品");// 对交易或商品的描述
		pcont.put("product_code", "QUICK_MSECURITY_PAY");// 销售产品码

		param.put("biz_content", JSON.toJSONString(pcont)); // 业务请求参数
															// 不需要对json字符串转义
		Map<String, Object> payMap = new HashMap<>();
		param.put("sign", PayUtil.getSign(param, alipayConfigUtil.getProperty("ALIPAY_PRIVATE_KEY"))); // 业务请求参数APP_PRIVATE_KEY
		payMap.put("orderStr", PayUtil.getSignEncodeUrl(param, true));
		payMap.put("combineOrderId", combineOrder.getId());
		payMap.put("isHasStock", true);//用来判断库存 true 表示有库存
		payMap.put("orderPayMsg", "");
		payMap.put("orderPaySuccess", Const.COMBINE_ORDER_STATUS_NOT_PAID);
		return payMap;
	}

	/**
	 * 方法描述 ：支付成功与失败，修改单据状态
	 *
	 * @param success
	 * @param combineOrder
	 * @param payDate
	 * @throws ArgException
	 * @author chenwf:
	 * @date 创建时间：2017年6月5日 下午6:15:55
	 * @version
	 */
	public void updateOrderStatus(boolean success, CombineOrder combineOrder, SvipOrder svipOrder, String paymentNo, Integer paymentId, Date payDate) throws ArgException {
		Date date = new Date();
		if (success) {
			List<Integer> productIds = new ArrayList<Integer>();
			String subStatus = Const.ORDER_STATUS_PAID;
			combineOrder.setStatus(Const.COMBINE_ORDER_STATUS_PAID);
			combineOrder.setPayStatus(Const.COMBINE_ORDER_PAY_STATUS_SUCCESS);
			combineOrder.setPayDate(payDate);
			combineOrder.setPaymentId(paymentId);
			combineOrder.setPaymentNo(paymentNo);
			//1 ZFB 2WX
			String financialNo = "";
			if (paymentId == 1 || paymentId == 6) {
				//支付宝(APP),支付宝(H5)
				financialNo = "ZFB" + DateUtil.getFormatDate(date, "yyyyMMdd");
			} else if (paymentId == 2 || paymentId == 5) {
				//微信(APP),微信(H5)
				financialNo = "WX" + DateUtil.getFormatDate(date, "yyyyMMdd");
			} else if (paymentId == 4) {
				//微信(公众号)
				financialNo = "GZH" + DateUtil.getFormatDate(date, "yyyyMMdd");
			}
			combineOrder.setFinancialNo(financialNo);
			combineOrderMapper.updateByPrimaryKeySelective(combineOrder);
			//查找物流发货时间，找不到默认为当前时间+48H(承诺发货时间的取值判断顺序：1、判断特殊地区  2、 判断通用的  3、用NOW+48小时)
			Integer deliveryOvertime = null;
			Date deliveryDate = null;
			boolean deliveryDateFlag = true;
			DeliveryOvertimeSpecialCnfExample deliveryOvertimeSpecialCnfExample = new DeliveryOvertimeSpecialCnfExample();
			deliveryOvertimeSpecialCnfExample.createCriteria().andDelFlagEqualTo("0")
					.andBeginPayDateLessThanOrEqualTo(date)
					.andEndPayDateGreaterThanOrEqualTo(date);
			List<DeliveryOvertimeSpecialCnf> deliveryOvertimeSpecialCnfList = deliveryOvertimeSpecialCnfMapper.selectByExample(deliveryOvertimeSpecialCnfExample);
			if (deliveryOvertimeSpecialCnfList.size() > 0) {
				DeliveryOvertimeSpecialCnf deliveryOvertimeSpecialCnf = deliveryOvertimeSpecialCnfList.get(0);
				DeliveryOvertimeSpecialCnfAreaExample deliveryOvertimeSpecialCnfAreaExample = new DeliveryOvertimeSpecialCnfAreaExample();
				deliveryOvertimeSpecialCnfAreaExample.createCriteria().andDelFlagEqualTo("0")
						.andSpecialCnfIdEqualTo(deliveryOvertimeSpecialCnf.getId());
				List<DeliveryOvertimeSpecialCnfArea> deliveryOvertimeSpecialCnfAreaList = deliveryOvertimeSpecialCnfAreaMapper.selectByExample(deliveryOvertimeSpecialCnfAreaExample);
				for (DeliveryOvertimeSpecialCnfArea deliveryOvertimeSpecialCnfArea : deliveryOvertimeSpecialCnfAreaList) {
					if (combineOrder.getReceiverAddress().contains(deliveryOvertimeSpecialCnfArea.getAddress())) {
						String timeType = deliveryOvertimeSpecialCnf.getTimeType();
						if (timeType.equals("0")) {
							deliveryOvertime = deliveryOvertimeSpecialCnf.getOvertime();
							if (deliveryOvertime != null) {
								deliveryDate = DateUtil.addHour(date, deliveryOvertime);
							}
						} else if (timeType.equals("1")) {
							deliveryDate = deliveryOvertimeSpecialCnf.getDeliveryDate();
						}
						deliveryDateFlag = false;
						break;
					}
				}
			}
			if (deliveryDateFlag) {
				DeliveryOvertimeCnfExample deliveryOvertimeCnfExample = new DeliveryOvertimeCnfExample();
				deliveryOvertimeCnfExample.createCriteria().andDelFlagEqualTo("0").andBeginDateLessThanOrEqualTo(date).andEndDateGreaterThanOrEqualTo(date);
				deliveryOvertimeCnfExample.setOrderByClause("id desc");
				List<DeliveryOvertimeCnf> deliveryOvertimeCnfs = deliveryOvertimeCnfService.selectByExample(deliveryOvertimeCnfExample);
				if (CollectionUtils.isNotEmpty(deliveryOvertimeCnfs)) {
					DeliveryOvertimeCnf deliveryOvertimeCnf = deliveryOvertimeCnfs.get(0);
					String timeType = deliveryOvertimeCnf.getTimeType();
					if (timeType.equals("0")) {
						deliveryOvertime = deliveryOvertimeCnf.getOvertime();
						if (deliveryOvertime != null) {
							deliveryDate = DateUtil.addHour(date, deliveryOvertime);
						}
					} else if (timeType.equals("1")) {
						deliveryDate = deliveryOvertimeCnf.getDeliveryDate();
					}
				}
			}
			if (deliveryDate == null) {
				deliveryOvertime = DELIVERY_OVER_TIME;
				deliveryDate = DateUtil.addHour(date, deliveryOvertime);
			}
			//计算sku库存
			SubOrderExample subOrderExample = new SubOrderExample();
			subOrderExample.createCriteria().andCombineOrderIdEqualTo(combineOrder.getId());
			List<SubOrder> subOrderList = subOrderMapper.selectByExample(subOrderExample);
			for (SubOrder subOrder : subOrderList) {
				//待付款订单取消了  库存冻结要释放
				OrderDtlExample orderDtlExample = new OrderDtlExample();
				orderDtlExample.createCriteria().andSubOrderIdEqualTo(subOrder.getId());
				List<OrderDtl> orderDtlList = orderDtlMapper.selectByExample(orderDtlExample);
				for (OrderDtl orderDtl : orderDtlList) {
					Integer productId = orderDtl.getProductId();
					//库存
					productItemService.updateProductSkuStock(orderDtl.getProductItemId(), orderDtl.getQuantity(), orderDtl.getCloudProductItemId());
					//支付成功，查看是否有预付定金，有的话更新预付子订单状态
					subDepositOrderService.updateSubDepositOrderStatus(orderDtl.getId(), date, Const.SUB_DEPOSIT_STATUS_TAIL_ARREADY_ORDER, Const.SUB_DEPOSIT_STATUS_TAIL_PAID, payDate);
					//判断商品类型
					if (orderDtl.getSingleProductActivityId() != null) {
						SingleProductActivity singleProductActivity = singleProductActivityService.selectByPrimaryKey(orderDtl.getSingleProductActivityId());
						if (Const.PRODUCT_ACTIVITY_TYPE_COUPON_PRODUCT.equals(singleProductActivity.getType())) {
							//该商品是优惠券商品，子订单设置为已完成的状态
							subStatus = Const.ORDER_STATUS_SUCCESS;
							//把用户购买的优惠券赠送给用户
							memberCouponService.purchaseCoupon(singleProductActivity, combineOrder.getMemberId(), orderDtl.getId());
						}
					}
					if (!productIds.contains(productId)) {
						productIds.add(productId);
					}
				}
				subOrder.setStatus(subStatus);
				subOrder.setStatusDate(date);
				if (deliveryOvertime != null) {
					subOrder.setDeliveryOvertime(deliveryOvertime);
				}
				if ("1".equals(subOrder.getAuditStatus())) {
					subOrder.setAuditDate(payDate);
				}
				subOrder.setDeliveryLastDate(deliveryDate);
				subOrderMapper.updateByPrimaryKeySelective(subOrder);
				//插入订单日志
				orderLogService.insertOrderLog(subOrder.getId(), Const.ORDER_STATUS_PAID, combineOrder.getMemberId());
				if (subStatus.equals(Const.ORDER_STATUS_SUCCESS)) {
					orderLogService.insertOrderLog(subOrder.getId(), Const.ORDER_STATUS_SUCCESS, combineOrder.getMemberId());
				}

			}
			//满额送,买即赠
//			fullGive(combineOrder,date);

			//修改sku规格,获取有库存的sku最低价格，更新商品主表
//			productItemService.updateMinProductItemPrice(productIds);

//			//购买成功赠送积分
//			//预防刷积分和重复送积分
//			//2018年1月1日不在此赠送积分(支付成功)
//			//2018之前还是在此赠送积分(支付成功)
//			MemberInfo memberInfo = memberInfoService.findMemberById(combineOrder.getMemberId());
//			int year = Integer.valueOf(DateUtil.getFormatDate(combineOrder.getPayDate(), "yyyy"));
//			if(!memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_TOURIST) && year < 2018){
//				//购物赠送积分比例
//				Integer iChargr = 0;
//				//积分比例成长值比例
//				Integer gChargr = 0;
//				//获取赠送积分比例
//				IntegralType integralType = integralTypeService.findModel(Integer.valueOf(Const.INTEGRAL_TYPE_SHOPPONG));
//				if(integralType != null){
//					if(integralType.getIntType().equals("2")){
//						iChargr = integralType.getiCharge() == null ? 0 : integralType.getiCharge();
//					}
//					if(integralType.getGrowType().equals("2")){
//						gChargr = integralType.getgCharge() == null ? 0 : integralType.getgCharge();
//					}
//				}
//				//订单交易成功，计算会员剩余积分 = 会员账户积分 +赠送积分(实付金额/100)-使用的积分数量
//				MemberAccountExample memberAccountExample = new MemberAccountExample();
//				memberAccountExample.createCriteria().andMemberIdEqualTo(combineOrder.getMemberId());
//				List<MemberAccount> memberAccounts = memberAccountMapper.selectByExample(memberAccountExample);
//				if(CollectionUtils.isNotEmpty(memberAccounts)){
//					MemberAccount memberAccount = memberAccounts.get(0);
//					//会员账户积分
//					Integer memberIntegral = memberAccount.getIntegral();
//					//赠送积分 
//					Integer giveIntegral = 0;
//					if(iChargr != 0){
//						giveIntegral = combineOrder.getTotalPayAmount().multiply(new BigDecimal(iChargr)).intValue();
//					}
//					//赠送成长值
//					Integer giveGrowth = 0;
//					if(gChargr != 0){
//						giveGrowth = combineOrder.getTotalPayAmount().multiply(new BigDecimal(gChargr)).intValue();
//					}
//					Integer integral = memberIntegral + giveIntegral;
//					
//					//用户成长值 = 用户成长值 + 赠送成长值
//					Integer gValue = memberAccount.getgValue();
//					if(giveGrowth > 0){
//						gValue = gValue+giveGrowth;
//						//更改level
//						memberGroupService.updateMemberGroup(gValue,memberAccount.getMemberId());
//						//插入成长值明细
//						growthDtlService.addGrowthDtl(gValue,giveGrowth,memberAccount.getId(),
//								memberAccount.getMemberId(),combineOrder.getId(),Const.INTEGRAL_TYPE_SHOPPONG);
//					}
//					if(integral >= 0){
//						memberAccount.setIntegral(integral);
//					}else{
//						memberAccount.setIntegral(0);
//					}
//					memberAccount.setgValue(gValue);
//					memberAccountMapper.updateByPrimaryKeySelective(memberAccount);
//					
//					// 购物赠送积分
//					if (giveIntegral > 0) {
//						integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME,
//								Const.INTEGRAL_TYPE_SHOPPONG, giveIntegral, memberAccount.getIntegral(),
//								combineOrder.getId(), combineOrder.getMemberId(),"1");
//					}
//				}
//			}
			//TODO STORY #1758 下单时是否是svip会员
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(combineOrder.getMemberId());
			String isSvipMember = "";
			if ("1".equals(memberInfo.getIsSvip()) && memberInfo.getSvipExpireDate() != null && memberInfo.getSvipExpireDate().after(date)) {
				isSvipMember = "1";
			} else {
				isSvipMember = "0";
			}

			String sprChnl = memberInfo.getSprChnl();
			MemberExtendExample memberExtendExample = new MemberExtendExample();
			memberExtendExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberInfo.getId());
			memberExtendExample.setLimitStart(0);
			memberExtendExample.setLimitSize(1);
			List<MemberExtend> memberExtendList = memberExtendMapper.selectByExample(memberExtendExample);
			if(CollectionUtil.isNotEmpty(memberExtendList) && !StringUtil.isEmpty(memberExtendList.get(0).getSprChnl())) {
				sprChnl = memberExtendList.get(0).getSprChnl();
			}


			CombineOrderExtendExample e = new CombineOrderExtendExample();
			e.createCriteria().andDelFlagEqualTo("0").andCombineOrderIdEqualTo(combineOrder.getId());
			List<CombineOrderExtend> combineOrderExtends = combineOrderExtendMapper.selectByExample(e);
			if (combineOrderExtends != null && combineOrderExtends.size() > 0) {//正常是不会>0,下单成功时是首条CombineOrderExtend记录
				CombineOrderExtend combineOrderExtend = combineOrderExtends.get(0);
				combineOrderExtend.setIsSvipMember(isSvipMember);
				combineOrderExtend.setUpdateDate(date);
				combineOrderExtend.setUpdateBy(combineOrder.getMemberId());
				combineOrderExtend.setSprChnl(sprChnl);
				combineOrderExtendMapper.updateByPrimaryKeySelective(combineOrderExtend);
			} else {
				CombineOrderExtend coe = new CombineOrderExtend();
				coe.setDelFlag("0");
				coe.setCreateBy(combineOrder.getMemberId());
				coe.setCreateDate(date);
				coe.setCombineOrderId(combineOrder.getId());
				coe.setIsSvipMember(isSvipMember);
				coe.setSprChnl(sprChnl);
				combineOrderExtendMapper.insertSelective(coe);
			}

			// svip订单处理
			if (svipOrder != null) {
				svipOrder.setStatus(Const.COMBINE_ORDER_STATUS_PAID);
				svipOrder.setPayStatus(Const.COMBINE_ORDER_PAY_STATUS_SUCCESS);
				svipOrder.setPayDate(payDate);
				svipOrder.setPaymentNo(paymentNo);
				svipOrder.setUpdateDate(date);
				svipOrderService.updateByPrimaryKeySelective(svipOrder);
				memberInfoService.updateMemberSvipInfo(svipOrder);
			}

		} else {
			combineOrder.setPayStatus(Const.COMBINE_ORDER_PAY_STATUS_FAILED);
			combineOrder.setPayDate(date);
			combineOrder.setPaymentId(paymentId);
			combineOrderMapper.updateByPrimaryKeySelective(combineOrder);

			// svip订单处理
			if (svipOrder != null) {
				svipOrder.setStatus(Const.COMBINE_ORDER_PAY_STATUS_FAILED);
				svipOrder.setPayStatus(Const.COMBINE_ORDER_PAY_STATUS_FAILED);
				svipOrder.setUpdateDate(date);
				svipOrderService.updateByPrimaryKeySelective(svipOrder);
			}
		}
	}

	private void productItem(OrderDtl orderDtl) {
		Integer quantity = orderDtl.getQuantity();
		ProductItem productItem = productItemMapper.selectByPrimaryKey(orderDtl.getProductItemId());
		if(productItem != null){
			//冻结数量
			Integer lockedAmount = productItem.getLockedAmount();
			//库存数量
			Integer stock = productItem.getStock();
			//支付成功  库存冻结要释放  冻结数量-购买数量
			if(lockedAmount-quantity > 0){
				productItem.setLockedAmount(lockedAmount-quantity);
			}else{
				productItem.setLockedAmount(0);
			}
			//库存 = 库存 - 购买数量
			if(stock-quantity > 0){
				productItem.setStock(stock-quantity);
			}else{
				productItem.setStock(0);
			}
			productItemMapper.updateByPrimaryKeySelective(productItem);
		}
	}
	
	//满额赠,买即送
	public void fullGive(CombineOrder combineOrder,Date date) {
		List<ActivityCustom> activities = activityService.getOrderDataByCombineOrderId(combineOrder.getId());
		if(CollectionUtils.isNotEmpty(activities)){
			for (ActivityCustom activityCustom : activities) {
				if(activityCustom != null && !StringUtil.isBlank(activityCustom.getPreferentialType()) && !StringUtil.isBlank(activityCustom.getPreferentialIdGroup())){
					String preferentialType = activityCustom.getPreferentialType();
					String preferentialId = activityCustom.getPreferentialIdGroup();
					BigDecimal activityAreaTotalSalePrice = activityCustom.getActivityAreaTotalSalePrice();
					ShoppingCart shoppingCart = new ShoppingCart();
					shoppingCart.setActivityAreaId(activityCustom.getActivityAreaId());
					shoppingCart.setActivityId(activityCustom.getId());
					//特惠类型(0无 1优惠劵 2满减 3满赠 4多买优惠)
					if(preferentialType.equals("3")){
						FullGive fullGive = fullGiveMapper.selectByPrimaryKey(Integer.valueOf(preferentialId));
						if (fullGive != null) {
							// 满赠类型(1满额赠 2买即赠)
							String type = fullGive.getType();
							// 是否累加(0否 1是)
							String sumFlag = fullGive.getSumFlag();
							// 最低消费
							BigDecimal minimum = fullGive.getMinimum();
							// 是否赠送商品(0否 1是)
							String productFlag = fullGive.getProductFlag();
							// 赠送商品ID
							Integer productId = fullGive.getProductId();
							// 赠送商品数量
							Integer productNum = fullGive.getProductNum();
							// 是否赠送优惠劵(0否 1是)
							String couponFlag = fullGive.getCouponFlag();
							// 否赠优惠劵ID集合
							String giveCouponId = fullGive.getCouponIdGroup();
							// 是否赠送金币(0否 1是)
							String integralFlag = fullGive.getIntegralFlag();
							// 否赠金币数量
							Integer integeral = fullGive.getIntegral();
							String belong = fullGive.getBelong();
							String rang = fullGive.getRang();
							// 每个会场只可以优惠一次
							if (type.equals("1")) {// 满额赠
								if (sumFlag.equals("0")) {// 非累加
									// 单价*数量 > 最低消费
									if (activityAreaTotalSalePrice.compareTo(minimum) >= 0) {
										// 保存满额赠飞累加数据
										saveNonCumulativeInfo(combineOrder.getId(), shoppingCart, productId, combineOrder.getMemberId(), date,
												productNum, productFlag, couponFlag, giveCouponId, integralFlag,
												integeral, belong, rang, preferentialType,
												Integer.valueOf(preferentialId));
									}
								} else if (sumFlag.equals("1")){// 累加
										// 单价*数量 > 最低消费
									if (activityAreaTotalSalePrice.compareTo(minimum) >= 0) {
										// 保存满额赠累加数据
										saveCumulativeInfo(combineOrder.getId(), shoppingCart, productId, combineOrder.getMemberId(), date,
												productNum, productFlag, couponFlag, giveCouponId, integralFlag,
												integeral, activityAreaTotalSalePrice, minimum, belong, rang, preferentialType,
												Integer.valueOf(preferentialId));
									}
								}
							} else if (type.equals("2")){// 买即赠
									// 买即赠送商品，插入订单明细中
								saveNonCumulativeInfo(combineOrder.getId(), shoppingCart, productId, combineOrder.getMemberId(), date,
										productNum, productFlag, couponFlag, giveCouponId, integralFlag, integeral,
										belong, rang, preferentialType, Integer.valueOf(preferentialId));
							}
						}
					}
				}
			}
		}
	}

	public ResponseMsg submitAfterPayment(JSONObject reqPRM, JSONObject reqDataJson, HttpServletRequest request) throws ArgException {
		Integer combineOrderId = reqDataJson.getInt("combineOrderId");
		Integer payId = reqDataJson.getInt("payId");
		String ip = StringUtil.getIpAddr(request);
		if(!StringUtil.isBlank(ip)){
			if(ip.trim().length() > 16 || ip.trim().length()==0){
				ip = "116.62.146.101";
			}
		}else{
			ip = "116.62.146.101";
		}
		String extraInfo = getExtraInfo(reqPRM, reqDataJson, ip);
		CombineOrder combineOrder = combineOrderMapper.selectByPrimaryKey(combineOrderId);
		if(combineOrder.getStatus().equals("4")){
			throw new ArgException("订单已被取消");
		}
		if(combineOrder.getStatus().equals("1")){
			throw new ArgException("订单已被支付");
		}
		combineOrder.setPaymentId(payId);
		combineOrder.setOrderExtraInfo(extraInfo);
		combineOrderMapper.updateByPrimaryKeySelective(combineOrder);

		if (combineOrder.getSvipOrderId() != null) {
			SvipOrder svipOrder = svipOrderService.selectByPrimaryKey(combineOrder.getSvipOrderId());
			if (svipOrder != null) {
				combineOrder.setTotalPayAmount(combineOrder.getTotalPayAmount().add(svipOrder.getPayAmount()));
			}
		}

		if (payId == 1) {
			Map<String, Object> payMap = zfbAlipay(combineOrder,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		} else{//WX
			SortedMap<String, Object> payMap = getWecharSign(combineOrder,ip,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}
	}
	
	/**
	 * 再次提交支付
	 * @param request 
	 */
	public ResponseMsg submitPaymentAgain(JSONObject reqDataJson, HttpServletRequest request) throws ArgException {
		Integer combineOrderId = reqDataJson.getInt("combineOrderId");
		Integer payId = reqDataJson.getInt("payId");
		String ip = StringUtil.getIpAddr(request);
		if(!StringUtil.isBlank(ip)){
			if(ip.trim().length() > 16 || ip.trim().length()==0){
				ip = "116.62.146.101";
			}
		}else{
			ip = "116.62.146.101";
		}
		CombineOrder combineOrder = combineOrderMapper.selectByPrimaryKey(combineOrderId);
		Map<String, Object> errorMap = new HashMap<String, Object>();
		if(combineOrder.getStatus().equals("4")){
			errorMap.put("orderPayMsg", "订单已被取消");
			errorMap.put("orderPaySuccess", Const.COMBINE_ORDER_STATUS_CANCEL);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, errorMap);
		}
		if(combineOrder.getStatus().equals("1")){
			errorMap.put("orderPayMsg", "订单已被支付");
			errorMap.put("orderPaySuccess", Const.COMBINE_ORDER_STATUS_PAID);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, errorMap);
		}
		combineOrder.setPaymentId(payId);
		combineOrderMapper.updateByPrimaryKeySelective(combineOrder);

		if (combineOrder.getSvipOrderId() != null) {
			SvipOrder svipOrder = svipOrderService.selectByPrimaryKey(combineOrder.getSvipOrderId());
			if (svipOrder != null) {
				combineOrder.setTotalPayAmount(combineOrder.getTotalPayAmount().add(svipOrder.getPayAmount()));
			}
		}

		if (payId == 1) {
			Map<String, Object> payMap = zfbAlipay(combineOrder,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		} else{//WX
			SortedMap<String, Object> payMap = getWecharSign(combineOrder,ip,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}
	}

	public Integer calculationIntegral(String activityType, BigDecimal activitySalePrice, BigDecimal iChargr, List<MemberAccount> memberAccounts, BigDecimal payAmount, Integer quantity, Integer alreadyUsedIntegral) {
		List<SingleProductActivityCnf> cnfs = new ArrayList<SingleProductActivityCnf>();
		if(!StringUtil.isBlank(activityType)){
			QueryObject queryObject = new QueryObject();
			queryObject.addQuery("activityType", activityType);
			cnfs = singleProductActivityCnfService.findListQuery(queryObject);
		}
		BigDecimal integralExchangeRate;
		if(CollectionUtils.isNotEmpty(cnfs)){
			SingleProductActivityCnf activityCnf = cnfs.get(0);
			integralExchangeRate = activityCnf.getIntegralExchangeRate();
		}else{
			//INTEGERAL_DISOUNT
			integralExchangeRate = new BigDecimal(INTEGERAL_DISOUNT);
		}

		Integer integral = 0;
		if(NumberUtil.gtZero(iChargr)){
			MemberAccount memberAccount = memberAccounts.get(0);
			//将用户总积分积分转换为元
			BigDecimal amount = activitySalePrice.multiply(integralExchangeRate);
			int memberIntegral = memberAccount.getIntegral() - alreadyUsedIntegral;
			if(memberIntegral < 0){
				memberIntegral = 0;
			}
			//获取用户积分
			BigDecimal integralAmount = new BigDecimal(memberIntegral).divide(iChargr, 2, BigDecimal.ROUND_DOWN);
			if (amount.compareTo(integralAmount) < 0) {
				integralAmount = amount;
			}
			//可以使用的积分*(预防积分出现小数，把积分抵扣金额再次转化为积分)
			integral = integralAmount.multiply(iChargr).intValue();
			//可用积分小于 购买商品的总数量，就不能使用积分抵扣
			if(integral < quantity){
				integral = 0;
			}else{
				BigDecimal minPayAmount = new BigDecimal(quantity).multiply(new BigDecimal("0.01")); // 每个商品最低支付1分钱
				if (payAmount.subtract(integralAmount).compareTo(minPayAmount) < 0) {
					integral = payAmount.subtract(minPayAmount).multiply(iChargr).intValue();
				}
			}
		}
		return integral;
	}

	public ResponseMsg submitDepositOrderPayment(JSONObject reqPRM, JSONObject reqDataJson, HttpServletRequest request) {
		Date currentDate = new Date();
		Integer memberId = reqDataJson.getInt("memberId");
		Integer productId = reqDataJson.getInt("productId");
		Integer totalQuantity = reqDataJson.getInt("totalQuantity");
		Integer payId = reqDataJson.getInt("payId");
		String remarks = reqDataJson.getString("remarks");
		Integer productItemId = null;
		String ip = StringUtil.getIpAddr(request);// ip地址
		if(!StringUtil.isBlank(ip)){
			if(ip.trim().length() > 16 || ip.trim().length()==0){
				ip = "116.62.146.101";
			}
		}else{
			ip = "116.62.146.101";
		}
		MemberInfo member = memberInfoService.selectByPrimaryKey(memberId);
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
			//商品信息
			Map<String,Object> goodsMap = new HashMap<String,Object>();
			goodsMap.put("productItemId", productItemId);
			List<ProductCustom> productCustoms = productService.getGoodsBasicsInfo(goodsMap);
			if(CollectionUtils.isEmpty(productCustoms)){
				throw new ArgException("商品已售罄");
			}
			//先锁定库存
			productItemService.updateSkuLockedAmount(productItemId,totalQuantity,productCustoms.get(0).getMchtId());
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
				ProductCustom p = productCustoms.get(0);
				Integer subQuantity = 1;//固定1
				String mchtType = p.getMchtType();
				String productName = p.getName();//商品名称
				String skuPic = p.getPic();//sku图片
				String productPropDesc = p.getProductPropdesc();//sku描述
				Integer mchtId = p.getMchtId();
				Integer brandId = p.getBrandId();
				Integer productType1Id = p.getProductType1Id();
				Integer productType2Id = p.getProductType2Id();
				Integer productType3Id = p.getProductTypeId();
				String artNo = p.getArtNo();
				String sku = p.getSku();
				String brandName = p.getBrandName();
				BigDecimal zero = new BigDecimal("0");
				double settleAmount = 0.00;
				BigDecimal commissionAmount = zero;
				BigDecimal tagPrice = p.getTagPrice();
				BigDecimal salePrice = p.getSalePrice().setScale(2);//活动价格
				BigDecimal mallPrice = p.getMallPrice();
				BigDecimal settlePrice = p.getCostPrice();
				BigDecimal deposit = activityProductDeposit.getDeposit().setScale(2);//定金
				BigDecimal deductAmount = activityProductDeposit.getDeductAmount().setScale(2);//抵扣金额
				Integer activityProductDepositId = activityProductDeposit.getId();
				BigDecimal mchtPreferential = deductAmount.subtract(deposit);
				String combineDepositOrderCode = "Y" + CommonUtil.getOrderCode();
				Map<String, Object> popFeeRateParamsMap = new HashMap<>();
				popFeeRateParamsMap.put("productId", productId);
				popFeeRateParamsMap.put("activityId", activityProductDeposit.getActivityId());
				popFeeRateParamsMap.put("mchtId", mchtId);
				popFeeRateParamsMap.put("brandId", brandId);
				popFeeRateParamsMap.put("productType1Id", productType1Id);
				popFeeRateParamsMap.put("productType2Id", productType2Id);
				popFeeRateParamsMap.put("productType3Id", productType3Id);
				BigDecimal popFeeRate = mchtProductBrandService.getPopFeeRate(popFeeRateParamsMap);
				//商家类型 1.spop 2.pop
				if(mchtType.equals(Const.MCHT_TYPE_POP)){
					//2:pop：
					//结算金额 = （成交价 * 数量） * （1-pop佣金比率）
					settleAmount = (deposit.doubleValue() * subQuantity) * (1 - popFeeRate.doubleValue());
					//服务费 = （成交价 * 数量 ） * pop佣金比率
					commissionAmount = (deposit.multiply(new BigDecimal(subQuantity))).multiply(popFeeRate);
				}else{
					//1:spop：
					//结算金额 = 0
					settleAmount = 0;
					//服务费 = 定金金额
					commissionAmount = deposit.multiply(new BigDecimal(subQuantity));
				}
				CombineDepositOrder combineDepositOrder = new CombineDepositOrder();
				combineDepositOrder.setCombineDepositOrderCode(combineDepositOrderCode);
				combineDepositOrder.setMemberId(memberId);
				combineDepositOrder.setMemberNick(member.getNick());
				combineDepositOrder.setTotalQuantity(totalQuantity);
				combineDepositOrder.setTotalDeposit(deposit.multiply(new BigDecimal(totalQuantity.toString())));
				combineDepositOrder.setStatus(Const.COMBINE_DEPOSIT_ORDER_STATUS_NO_PAID);
				combineDepositOrder.setStatusDate(currentDate);
				combineDepositOrder.setPaymentId(payId);
				combineDepositOrder.setCreateDate(currentDate);
				combineDepositOrder.setCreateBy(memberId);
				combineDepositOrder.setRemarks(remarks);
				combineDepositOrder.setDelFlag("0");
				combineDepositOrderService.insertSelective(combineDepositOrder);
				
				for (int i = 0; i < totalQuantity; i++) {
					SubDepositOrder subDepositOrder = new SubDepositOrder();
					String subDepositOrderCode = combineDepositOrderCode + "S"+(i+1);
					subDepositOrder.setCombineDepositOrderId(combineDepositOrder.getId());
					subDepositOrder.setSubDepositOrderCode(subDepositOrderCode);
					subDepositOrder.setMchtId(mchtId);
					subDepositOrder.setMchtType(mchtType);
					subDepositOrder.setMemberId(memberId);
					subDepositOrder.setActivityPreSellProductId(activityProductDepositId);
					subDepositOrder.setProductId(productId);
					subDepositOrder.setProductItemId(productItemId);
					subDepositOrder.setSkuPic(skuPic);
					subDepositOrder.setProductName(productName);
					subDepositOrder.setArtNo(artNo);
					subDepositOrder.setBrandName(brandName);
					subDepositOrder.setProductPropDesc(productPropDesc);
					subDepositOrder.setSku(sku);
					subDepositOrder.setTagPrice(tagPrice);
					subDepositOrder.setSalePrice(salePrice);
					subDepositOrder.setMallPrice(mallPrice);
					subDepositOrder.setSettlePrice(settlePrice);
					subDepositOrder.setDeposit(deposit);
					subDepositOrder.setDeductAmount(deductAmount);
					subDepositOrder.setQuantity(subQuantity);
					subDepositOrder.setPayAmount(deposit);
					subDepositOrder.setStatus(Const.SUB_DEPOSIT_STATUS_NO_PAID);
					subDepositOrder.setPopCommissionRate(popFeeRate);
					subDepositOrder.setSettleAmount(new BigDecimal(settleAmount));
					subDepositOrder.setCommissionAmount(commissionAmount);
					subDepositOrder.setMemberRemarks(remarks);
					subDepositOrder.setCreateDate(currentDate);
					subDepositOrder.setCreateBy(memberId);
					subDepositOrder.setDelFlag("0");
					subDepositOrderService.insertSelective(subDepositOrder);
					
					//日志
					depositOrderStatusLogService.addLog(subDepositOrder.getId(),subDepositOrder.getStatus(),null,memberId);
				}
				CombineOrder combineOrder = new CombineOrder();
				combineOrder.setId(combineDepositOrder.getId());
				combineOrder.setCombineOrderCode(combineDepositOrder.getCombineDepositOrderCode());
				combineOrder.setTotalPayAmount(combineDepositOrder.getTotalDeposit());
				if (payId == 1) {
					Map<String, Object> payMap = zfbAlipay(combineOrder,"2");
					return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
				} else if (payId == 2) {//WX
					SortedMap<String, Object> payMap = getWecharSign(combineOrder,ip,"2");
					return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
				}else{
					throw new ArgException("请用微信或支付宝支付");
				}
			}else{
				throw new ArgException("预售未开始");
			}
		}else{
			throw new ArgException("预售未开始");
		}
	}
	
	
	/**
	 * 定金支付失败再次提交支付
	 * @param request 
	 */
	public ResponseMsg submitDepositAfterPayment(JSONObject reqDataJson, HttpServletRequest request) throws ArgException {
		Integer combineDepositOrderId = reqDataJson.getInt("combineDepositOrderId");
		Integer payId = reqDataJson.getInt("payId");
		String ip = StringUtil.getIpAddr(request);
		if(!StringUtil.isBlank(ip)){
			if(ip.trim().length() > 16 || ip.trim().length()==0){
				ip = "116.62.146.101";
			}
		}else{
			ip = "116.62.146.101";
		}
		CombineDepositOrder combineDepositOrder = combineDepositOrderService.selectByPrimaryKey(combineDepositOrderId);
		Map<String, Object> errorMap = new HashMap<String, Object>();
		if(combineDepositOrder.getStatus().equals(Const.COMBINE_DEPOSIT_ORDER_STATUS_CANCEL)){
			errorMap.put("orderPayMsg", "订单已被取消");
			errorMap.put("orderPaySuccess", Const.COMBINE_ORDER_STATUS_CANCEL);
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.SUCCESS_MSG, errorMap);
		}
		if(combineDepositOrder.getStatus().equals(Const.COMBINE_DEPOSIT_ORDER_STATUS_PAID)){
			errorMap.put("orderPayMsg", "订单已被支付");
			errorMap.put("orderPaySuccess", Const.COMBINE_ORDER_STATUS_PAID);
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.SUCCESS_MSG, errorMap);
		}
		combineDepositOrder.setPaymentId(payId);
		combineDepositOrderService.updateByPrimaryKeySelective(combineDepositOrder);
		
		CombineOrder combineOrder = new CombineOrder();
		combineOrder.setId(combineDepositOrder.getId());
		combineOrder.setCombineOrderCode(combineDepositOrder.getCombineDepositOrderCode());
		combineOrder.setTotalPayAmount(combineDepositOrder.getTotalDeposit());
		if (payId == 1) {
			Map<String, Object> payMap = zfbAlipay(combineOrder,"2");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		} else{//WX
			SortedMap<String, Object> payMap = getWecharSign(combineOrder,ip,"2");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}
	}

	public void updateDepositOrderStatus(HttpServletRequest request, boolean b, CombineDepositOrder order, String tradeNo, Integer paymentId,Date payDate) {
		Date currentDate = new Date();
		if(b){
			//支付成功
			order.setStatus(Const.COMBINE_DEPOSIT_ORDER_STATUS_PAID);
			order.setPayStatus(Const.COMBINE_ORDER_PAY_STATUS_SUCCESS);
			order.setStatusDate(currentDate);
			order.setPaymentNo(tradeNo);
			order.setPayDate(payDate);
			order.setUpdateDate(currentDate);
			combineDepositOrderService.updateByPrimaryKeySelective(order);
			
			SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
			subDepositOrderExample.createCriteria().andCombineDepositOrderIdEqualTo(order.getId()).andStatusEqualTo(Const.SUB_DEPOSIT_STATUS_NO_PAID).andDelFlagEqualTo("0");
			
			List<SubDepositOrder> subDepositOrders = subDepositOrderService.selectByExample(subDepositOrderExample);
			if(CollectionUtils.isNotEmpty(subDepositOrders)){
				for (SubDepositOrder subDepositOrder : subDepositOrders) {
					depositOrderStatusLogService.addLog(subDepositOrder.getId(), Const.SUB_DEPOSIT_STATUS_PAID, null, order.getMemberId());
				}
			}
			SubDepositOrder subDeposit = subDepositOrders.get(0);
			ActivityProductDeposit activityProductDeposit = activityProductDepositService.selectByPrimaryKey(subDeposit.getActivityPreSellProductId());
			Activity activity = activityService.selectByPrimaryKey(activityProductDeposit.getActivityId());
			
			SubDepositOrder subDepositOrderUpdate = new SubDepositOrder();
			subDepositOrderUpdate.setStatus(Const.SUB_DEPOSIT_STATUS_PAID);
			subDepositOrderUpdate.setPayDate(payDate);
			subDepositOrderUpdate.setUpdateDate(currentDate);
			int subUpdateCount = subDepositOrderService.updateByExampleSelective(subDepositOrderUpdate, subDepositOrderExample);
			
			ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
			shoppingCartExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andMchtIdEqualTo(subDeposit.getMchtId())
				.andMemberIdEqualTo(order.getMemberId()).andProductItemIdEqualTo(subDeposit.getProductItemId());
			List<ShoppingCart> shoppingCartList = shoppingCartService.selectByExample(shoppingCartExample);
			if(shoppingCartList != null && shoppingCartList.size() > 0) { //购物车已存在
				ShoppingCart shoppingCart = shoppingCartList.get(0);
				Integer quantity = shoppingCart.getQuantity();
				shoppingCart.setQuantity(quantity+order.getTotalQuantity());
				shoppingCart.setActivityType(Const.PRODUCT_ACTIVITY_TYPE_AREA);
				shoppingCart.setActivityId(activity.getId());
				shoppingCart.setActivityAreaId(activity.getActivityAreaId());
				shoppingCart.setAddSalePrice(subDeposit.getSalePrice());
				shoppingCart.setAddTagPrice(subDeposit.getTagPrice());
				shoppingCart.setQuantity(quantity+order.getTotalQuantity());
				shoppingCart.setStatus("0");
				shoppingCartService.updateByPrimaryKeySelective(shoppingCart);
			}else{ //购物车不存在
				//购物车增加一条记录
				ShoppingCart cart = new ShoppingCart();
				cart.setActivityId(activity.getId());
				cart.setActivityAreaId(activity.getActivityAreaId());
				cart.setMchtId(subDeposit.getMchtId());
				cart.setMemberId(order.getMemberId());
				cart.setActivityType(Const.PRODUCT_ACTIVITY_TYPE_AREA);
				cart.setProductItemId(subDeposit.getProductItemId());
				cart.setAddSalePrice(subDeposit.getSalePrice());
				cart.setAddTagPrice(subDeposit.getTagPrice());
				cart.setQuantity(order.getTotalQuantity());
				cart.setStatus("0");
				cart.setCreateDate(currentDate);
				cart.setCreateBy(order.getMemberId());
				cart.setDelFlag("0");
				shoppingCartService.insertSelective(cart);
			}
			
			//支付成功发送短信给用户
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(order.getMemberId());
			if(memberInfo != null && !StringUtil.isBlank(memberInfo.getMobile())){
				ActivityArea activityArea = activityAreaService.selectByPrimaryKey(activity.getActivityAreaId());
				String nick = memberInfo.getNick();
				String productName = subDeposit.getProductName();
				String mobile = memberInfo.getMobile();
				String tailPayDate = DateUtil.getFormatDate(activityArea.getActivityBeginTime(), "MM月dd日 HH:mm:ss");
				String content = "亲爱的"+nick+"，您已成功预购商品"+productName+"，请在"+tailPayDate+"时间后前往醒购进行支付";
				commonService.sendSms(mobile,content,"4","1",StringUtil.getIpAddr(request));
			}

		}else{
			order.setPayStatus(Const.COMBINE_ORDER_PAY_STATUS_FAILED);
			order.setStatusDate(currentDate);
			order.setUpdateDate(currentDate);
			combineDepositOrderService.updateByPrimaryKeySelective(order);
			logger.info("支付失败"+order.getCombineDepositOrderCode());
		}
	}

	public ResponseMsg submitSvipOrderPayment(JSONObject reqPRM, JSONObject reqDataJson, HttpServletRequest request) {
		Date currentDate = new Date();
		String system = reqPRM.getString("system");
		Integer svipSettingId = reqDataJson.getInt("svipSettingId");//购买会员id
		Integer memberId = reqDataJson.getInt("memberId");// 会员标识id
		Integer payId = reqDataJson.getInt("payId");// 支付方式 1 支付宝  2 微信  3 银联
		String payAmount = reqDataJson.getString("payAmount");// 实付金额
		String ip = StringUtil.getIpAddr(request);// ip地址
		if(!StringUtil.isBlank(ip)){
			if(ip.trim().length() > 16 || ip.trim().length()==0){
				ip = "116.62.146.101";
			}
		}else{
			ip = "116.62.146.101";
		}
		MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		if("P".equalsIgnoreCase(memberInfo.getStatus())){
			throw new ArgException("状态异常，购买失败");
		}
		List<SvipMemberSetting> svipMemberSettings = svipMemberSettingService.findModel(svipSettingId);
		if(CollectionUtils.isEmpty(svipMemberSettings)){
			throw new ArgException("该功能已下架");
		}
		SvipMemberSetting svipMemberSetting = svipMemberSettings.get(0);
		BigDecimal price = svipMemberSetting.getPrice();
		SvipOrder order = new SvipOrder();
		String orderCode = "SVIP" + CommonUtil.getOrderCode();
		String buyType = "1";//1.首次购买2.会员续费
		if(memberInfo.getSvipExpireDate() != null){
			buyType = "2";
		}
		order.setMemberId(memberId);
		order.setOrderCode(orderCode);
		order.setBuyType(buyType);
		order.setSvipMemberSettingId(svipSettingId);
		order.setYearsOfPurchase(svipMemberSetting.getOpeningYear());
		order.setSourceClient(system);
		order.setIp(ip);
		order.setPaymentId(payId);
		order.setPayAmount(price);
		order.setStatus("0");
		order.setCreateBy(memberId);
		order.setCreateDate(currentDate);
		order.setDelFlag("0");
		svipOrderService.insertSelective(order);
		
		CombineOrder combineOrder = new CombineOrder();
		combineOrder.setId(order.getId());
		combineOrder.setCombineOrderCode(order.getOrderCode());
		combineOrder.setTotalPayAmount(order.getPayAmount());
		if (payId == 1) {
			Map<String, Object> payMap = zfbAlipay(combineOrder,"3");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		} else if (payId == 2) {//WX
			SortedMap<String, Object> payMap = getWecharSign(combineOrder,ip,"3");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else{
			throw new ArgException("请用微信或支付宝支付");
		}
	}
	
	/**
	 * SVIP支付回调修改订单状态
	 * @param request
	 * @param b
	 * @param order
	 * @param tradeNo
	 * @param paymentId
	 * @param payDate
	 */
	public void updateSvipOrderStatus(HttpServletRequest request, boolean b, SvipOrder order, String tradeNo,int paymentId, Date payDate) {
		Date currentDate = new Date();
		if(b){
			//支付成功
			order.setStatus(Const.COMBINE_ORDER_STATUS_PAID);
			order.setPayStatus(Const.COMBINE_ORDER_PAY_STATUS_SUCCESS);
			order.setPayDate(payDate);
			order.setPaymentNo(tradeNo);
			order.setUpdateDate(currentDate);
			svipOrderService.updateByPrimaryKeySelective(order);
			memberInfoService.updateMemberSvipInfo(order);
		}else{
			order.setStatus(Const.COMBINE_ORDER_PAY_STATUS_FAILED);
			order.setPayStatus(Const.COMBINE_ORDER_PAY_STATUS_FAILED);
			order.setUpdateDate(currentDate);
			svipOrderService.updateByPrimaryKeySelective(order);
			logger.info("支付失败"+order.getOrderCode());
		}
		
	}


}
