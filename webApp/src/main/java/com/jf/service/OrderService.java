package com.jf.service;


import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.HttpXmlUtils;
import com.jf.common.utils.JdomParseXmlUtils;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.NumberUtil;
import com.jf.common.utils.RandCharsUtils;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.WeixinUtil;
import com.jf.common.utils.alipay.PayUtil;
import com.jf.common.utils.alipayConfigUtil;
import com.jf.common.utils.wechar.WXSignUtils;
import com.jf.common.utils.wecharConfigUtil;
import com.jf.dao.CombineOrderExtendMapper;
import com.jf.dao.CombineOrderMapper;
import com.jf.dao.CouponMapper;
import com.jf.dao.FullGiveMapper;
import com.jf.dao.MchtInfoMapper;
import com.jf.dao.MemberAccountMapper;
import com.jf.dao.MemberAddressCustomMapper;
import com.jf.dao.MemberCouponMapper;
import com.jf.dao.OrderDtlExtendMapper;
import com.jf.dao.OrderDtlMapper;
import com.jf.dao.ProductCustomMapper;
import com.jf.dao.ProductItemCustomMapper;
import com.jf.dao.ProductItemMapper;
import com.jf.dao.ProductPropValueCustomMapper;
import com.jf.dao.ShoppingCartMapper;
import com.jf.dao.SubOrderExtendMapper;
import com.jf.dao.SubOrderMapper;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ActivityProductDeposit;
import com.jf.entity.ActivityProductDepositExample;
import com.jf.entity.CombineDepositOrder;
import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderExtend;
import com.jf.entity.CombineOrderExtendExample;
import com.jf.entity.Coupon;
import com.jf.entity.CutPriceOrder;
import com.jf.entity.FullGive;
import com.jf.entity.Information;
import com.jf.entity.IntegralType;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.MemberAddress;
import com.jf.entity.MemberAddressCustom;
import com.jf.entity.MemberAddressExample;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.MemberNovaRecord;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.OrderDtlExtend;
import com.jf.entity.OrderProductSnapshot;
import com.jf.entity.Product;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemCustom;
import com.jf.entity.ProductItemExample;
import com.jf.entity.ProductPropValueCustom;
import com.jf.entity.ProvinceFreightCustom;
import com.jf.entity.Salesman;
import com.jf.entity.Shopowner;
import com.jf.entity.ShopownerOrder;
import com.jf.entity.ShoppingCart;
import com.jf.entity.ShoppingCartCustom;
import com.jf.entity.ShoppingCartExample;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityCnf;
import com.jf.entity.SingleProductActivityCnfExample;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderExample;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExample;
import com.jf.entity.SubOrderExtend;
import com.jf.entity.SysParamCfg;
import com.jf.entity.pay.Unifiedorder;
import com.jf.entity.pay.UnifiedorderResult;
import com.jf.service.allowance.MemberAllowanceService;
import com.jf.vo.PreContext;
import net.sf.json.JSONArray;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

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
	 * 积分可优惠折扣
	 */
	public static final double INTEGERAL_DISOUNT = 0.5;
	
	/**
	 * 会员表
	 */
	@Resource
	private MemberInfoService memberInfoService;
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
	 * 购物车
	 */
	@Resource
	private ShoppingCartMapper shoppingCartMapper;

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
	 * 商品信息(辅助)
	 */
	@Resource
	private ProductCustomMapper productCustomMapper;

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
	 * 用户账号信息
	 */
	@Resource
	private MemberAccountMapper memberAccountMapper;

	/**
	 * 用户地址管理(辅助)
	 */
	@Resource
	private MemberAddressCustomMapper memberAddressCustomMapper;

	/**
	 * 商品属性值
	 */
	@Resource
	private ProductPropValueCustomMapper productPropValueCustomMapper;
	
	/**
	 * 订单日志
	 */
	@Resource
	private OrderLogService orderLogService;
	
	@Resource
	private IntegralTypeService integralTypeService;
	@Resource
	private IntegralDtlService integralDtlService;
	@Resource
	private ShoppingCartService shoppingCartService;
	@Resource
	private MemberAccountService memberAccountService;
	@Resource
	private MemberCouponService memberCouponService;
	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private ActivityService activityService;
	@Resource
	private ProductService productService;
	@Resource
	private OrderPreferentialInfoService orderPreferentialInfoService;
	@Resource
	private DouyinSprDtlService douYinSprDtlService;
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
	@Resource
	private ProvinceFreightService provinceFreightService;
	/**
	 * 商家商品品牌表
	 */
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	@Resource
	private CutPriceOrderService cutPriceOrderService;
	@Resource
	private SysPaymentService sysPaymentService;
	@Resource
	private SubDepositOrderService subDepositOrderService;
	@Resource
	private DepositOrderStatusLogService depositOrderStatusLogService;
	@Resource
	private CommonService commonService;
	@Resource
	private CombineDepositOrderService combineDepositOrderService;
	@Resource
	private ActivityProductDepositService activityProductDepositService;
	@Resource
	private NovaPlanService novaPlanService;
	@Resource
	private ShopownerService shopownerService;
	@Resource
	private ShopownerOrderService shopownerOrderService;
	@Resource
	private InformationService informationService;
	@Resource
	private MemberNovaRecordService memberNovaRecordService;
	@Resource
	private SalesmanService salesmanService;
	@Resource
	private CombineOrderExtendMapper combineOrderExtendMapper;
	@Autowired
	private MemberAllowanceService memberAllowanceService;

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
	 * @param reqPRM
	 * @param reqDataJson 
	 * @param request 
	 * @param memberId 
	 * @return
	 * @throws Exception 
	 * @throws ArgException 
	 */
	@SuppressWarnings("unchecked")
	public ResponseMsg submitOrderPayment(JSONObject reqPRM, JSONObject reqDataJson, HttpServletRequest request, Integer memberId){
		Date date = new Date();
		MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
		BigDecimal zero = new BigDecimal("0");
		BigDecimal totalFreight = zero;//总运费
		BigDecimal cutAssistanceAmount = zero;//助力大减价助力金额
		String memberNick = memberInfo.getNick();// 会员昵称
		String ua = request.getHeader("user-agent").toLowerCase();// 订单来源客户端 1.IOS 2.安卓  3 微商城 4 webApp
		String system = reqPRM.getString("system");;
		String sourceClient = system;
		String remarks = reqDataJson.getString("remarks");// 客户备注
		Integer payId = reqDataJson.getInt("payId");// 支付方式 1 支付宝  2 微信  3 银联
		String payAmount = reqDataJson.getString("payAmount");// 实付金额
		String mermberPlatformCouponId = reqDataJson.getString("mermberPlatformCouponId");// 全平台优惠券
		String ip = StringUtil.getIpAddr(request);// ip地址
		if(!StringUtil.isBlank(ip)){
			if(ip.trim().length() > 16 || ip.trim().length()==0){
				ip = "116.62.146.101";
			}
		}else{
			ip = "116.62.146.101";
		}
		String isSvip = memberInfoService.getIsSvip(memberInfo, memberId);
		String orderType = Const.COMBINE_ORDER_TYPE_ROUTINE;
		String extraInfo = getExtraInfo(reqPRM, reqDataJson, ip);
		Integer addressId = reqDataJson.getInt("addressId");// 收货地址id
		String openType = novaPlanService.getNovaPlanOpenType(null, memberInfo.getInvitationMemberId());
		Integer cutOrderId = null;
		if(reqDataJson.containsKey("cutOrderId") && !StringUtil.isBlank(reqDataJson.getString("cutOrderId"))){
			cutOrderId = reqDataJson.getInt("cutOrderId");
		}
		JSONArray dataList = reqDataJson.getJSONArray("dataList");
		
		// shopCardIds 储存包含所有会场的购物车id
		List<Integer> shopCardIdsList = new ArrayList<Integer>();
		String shopCardIds = reqDataJson.getString("shopCardIds").replaceAll("[\\[\\]\"]", "");//兼容小程购物车id包含"和[
		for (String id : shopCardIds.split(",")) {
			shopCardIdsList.add(Integer.valueOf(id));
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
				if(Const.PRODUCT_ACTIVITY_TYPE_SECKILL.equals(shoppingCart.getActivityType())
						|| Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL.equals(shoppingCart.getActivityType())){
					orderType = Const.COMBINE_ORDER_TYPE_SECKILL;
				}else if(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE.equals(shoppingCart.getActivityType())){
					orderType = Const.COMBINE_ORDER_ASSISTANCE_CUT_PRICE;
				}
				mchtIdSet.add(shoppingCart.getMchtId());
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
			
			for (ShoppingCartCustom shoppingCart : shoppingCartSubOrders) {
				Integer productType1Id = shoppingCart.getProductType1Id();
				Integer mchtId = shoppingCart.getMchtId();
				BigDecimal svipDiscount = shoppingCart.getSvipDiscount();
				if(shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_AREA) || shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)){
					if(activityMap.get(shoppingCart.getProductId().toString()) != null){
						shoppingCart.setActivityType(Const.PRODUCT_ACTIVITY_TYPE_AREA);
						shoppingCart.setSalePrice(shoppingCart.getSalePrice());
					}else{
						shoppingCart.setActivityType(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP);
						shoppingCart.setSalePrice(shoppingCart.getMallPrice());
					}
				}
				String isSvipBuy = "0";
				if("1".equals(isSvip) && svipDiscount != null && svipDiscount.compareTo(zero) > 0){//svip折扣
					BigDecimal svipSalePrice = productService.getProductSvipSalePrice(shoppingCart.getSalePrice(), svipDiscount,shoppingCart.getActivityType());
					if(svipSalePrice.compareTo(zero) > 0){
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
				if(shoppingCart.getStatus().equals("1") 
						&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY)
						&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)
						&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)
						&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL)
						&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_COUPON_PRODUCT)
						&& !shoppingCart.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){
					throw new ArgException("该笔订单已失效");
				}
			}
		}
		
		//获取该商品是否为预售商品
		Map<Integer, Map<String, Object>> skuDepositTotalMap = new HashMap<Integer, Map<String, Object>>(); //放置每个sku所对应的定金总金额，里面包含数量
		Map<String, Object> skuDepositMap = new HashMap<String, Object>();
		Map<Integer, BigDecimal> skuDepositTotalAmountMap = new HashMap<Integer, BigDecimal>(); //预售商品购物车sku数量所对应的定金总金额，预防A商品买了2个定金，只去支付一个尾款，该sku所对应的可抵扣金额就为1个定金抵扣
		Map<Integer, List<SubDepositOrder>> depositOrderMap = new HashMap<Integer, List<SubDepositOrder>>(); //sku对应预售定金子订单对象集合
		List<SubDepositOrder> depositOrderList = new ArrayList<SubDepositOrder>();
		SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
		subDepositOrderExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId).andStatusIn(Arrays.asList("2", "8")); //2 定金已付   8 尾款下单后未支付取消
		List<SubDepositOrder> subDepositOrderList = subDepositOrderService.selectByExample(subDepositOrderExample);
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
		
		
		
		//单品活动限购控制
		Map<String, Integer> singleProductCountMap=new HashMap<String, Integer>();
		Integer singleProductCount=null;
		for(ShoppingCart shoppingCart:shoppingCartSubOrders){
			
			if(shoppingCart.getSingleProductActivityId()!=null){
				SingleProductActivity singleProductActivity=singleProductActivityService.selectByPrimaryKey(shoppingCart.getSingleProductActivityId());
				Date beginTime = singleProductActivity.getBeginTime();
				Date endTime = singleProductActivity.getEndTime();
				if(date.before(beginTime)){
					throw new ArgException("活动未开始");
				}
				if(date.after(endTime)){
					throw new ArgException("活动已结束");
				}
				singleProductCount=singleProductCountMap.get(singleProductActivity.getType());
				if(singleProductCount==null){
					singleProductCountMap.put(singleProductActivity.getType(), shoppingCart.getQuantity());
				}else{
					singleProductCountMap.put(singleProductActivity.getType(), singleProductCountMap.get(singleProductActivity.getType())+shoppingCart.getQuantity());
				}
			}
		}
		
		
		
		for(String activityType:singleProductCountMap.keySet() ){
				
				SingleProductActivityCnfExample singleProductActivityCnfExample=new SingleProductActivityCnfExample();
				singleProductActivityCnfExample.createCriteria().andDelFlagEqualTo("0").andActivityTypeEqualTo(activityType);
				List<SingleProductActivityCnf> singleProductActivityCnfs=singleProductActivityCnfService.selectByExample(singleProductActivityCnfExample);
				if(singleProductActivityCnfs!=null&&singleProductActivityCnfs.size()>0){
					SingleProductActivityCnf singleProductActivityCnf=singleProductActivityCnfs.get(0);
					if(singleProductActivityCnf.getLimitBuy()!=null&&singleProductActivityCnf.getLimitBuy()>0){
						int activityLimitBuy=singleProductActivityCnf.getLimitBuy();
						//获取用户在此类型活动已下订单（除了取消状态，关闭外的所有订单）的数量
						if(activityLimitBuy>0){
							Map<String, Object> p = new HashMap<String, Object>();
							//查出用户id（包括同一设备号的用户）
							MemberInfo member=memberInfoService.selectByPrimaryKey(memberId);
							List<Integer> memberIds=new ArrayList<Integer>();
							memberIds.add(member.getId());
							String regImei=member.getReqImei();
							if(regImei!=null&&!"00000000-0000-0000-0000-000000000000".equals(regImei)&&!"".equals(regImei)&&!"unknown".equals(regImei)){
								MemberInfoExample memberInfoExample=new MemberInfoExample();
								memberInfoExample.createCriteria().andDelFlagEqualTo("0").andReqImeiEqualTo(regImei);
								List<MemberInfo> memberInfos=memberInfoService.selectByExample(memberInfoExample);
								if(memberInfos!=null&&memberInfos.size()>0){
									for(MemberInfo m:memberInfos){
										memberIds.add(m.getId());
									}
								}
							}
							
							//收货地址中手机号跟此会员绑定的手机号相同的会员id
							if(!StringUtil.isEmpty(member.getMobile())){
								MemberAddressExample memberAddressExample=new MemberAddressExample();
								memberAddressExample.createCriteria().andDelFlagEqualTo("0").andPhoneEqualTo(member.getMobile());
								List<MemberAddress> memberAddressList=memberAddressService.selectByExample(memberAddressExample);
								for(MemberAddress memberAddress:memberAddressList){
									memberIds.add(memberAddress.getMemberId());
								}
							}
							
							
							//查出收货地址中有相同手机号的用户id
							MemberAddressExample memberAddressExample=new MemberAddressExample();
							memberAddressExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId);
							List<MemberAddress> memberAddressList=memberAddressService.selectByExample(memberAddressExample);
							if(memberAddressList!=null&&memberAddressList.size()>0){
								List<String> mobiles=new ArrayList<String>();
								for(MemberAddress memberAddress:memberAddressList){
									mobiles.add(memberAddress.getPhone());
								}
								
								memberAddressExample=new MemberAddressExample();
								memberAddressExample.createCriteria().andDelFlagEqualTo("0").andPhoneIn(mobiles);
								memberAddressList=memberAddressService.selectByExample(memberAddressExample);
								for(MemberAddress memberAddress:memberAddressList){
									memberIds.add(memberAddress.getMemberId());
								}
							}
							
							p.put("memberIds", memberIds);
							p.put("activityType", activityType);
							if(singleProductActivityCnf.getLimitTime()!=null&&singleProductActivityCnf.getLimitTime()>0){
						    	Calendar calendar = Calendar.getInstance();
								calendar.add(Calendar.DATE, -singleProductActivityCnf.getLimitTime());
								p.put("limitDate", calendar.getTime());
							}
							int userHasBuyCount=productService.getUserBuyCountBySingleActivityType(p);
							int userShoppingCartCount=productService.getUserShoppingCartCountBySingleActivityType(p);
							
							
							//秒杀和新用户秒杀商品在加入购物车时设置了状态为1，所以要加上这次购买的数量
							int quantity=0;
							if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
								quantity=singleProductCountMap.get(activityType);
							}
							
							
							
							if(quantity+userHasBuyCount+userShoppingCartCount>activityLimitBuy){
								
								
								String limitMsg;
								if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)){
									limitMsg="秒杀专区每人限购"+activityLimitBuy+"件";
								}else
								if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
									limitMsg="新用户秒杀专区每人限购"+activityLimitBuy+"件";
								}else{
									limitMsg="每人限购"+activityLimitBuy+"件";
								}
								throw new ArgException(limitMsg);
							}
						}
					}
				}
		}
		
		// 封装总订单数据
		Map<String,Object> popMap = new HashMap<String,Object>();
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
		boolean isContainsSpecMcht = false;	//购物车是否包含特殊商家，需求号1696，特殊商家不允许使用平台优惠券
		for (Integer mchtId : mchtCartsMap.keySet()) {
			mchtInfo = mchtInfoMap.get(mchtId);
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
				BigDecimal saleOrMallPrice = new BigDecimal("0");
				if(activityType.equals("101")){
					saleOrMallPrice = productItemCustom.getMallPrice();
				}else{
					saleOrMallPrice = productItemCustom.getSalePrice();
				}
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
					String type = "1";
					if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){
						type = "2";
					}
					Map<String, Object> cloudMap = productItemService.updateSkuLockedAmount(productItemId,locklQuantity,mchtId,type);
					if((boolean) cloudMap.get("isCooperation")){
						if(cloudMap.get("sellingPrice") != null){
							orderDtl.setSellingPrice(new BigDecimal(cloudMap.get("sellingPrice").toString()));
						}
						orderDtl.setCloudProductItemId(shoppingCartOrderDtl.getCloudProductItemId());
					}
				}
				if("1".equals(shoppingCartOrderDtl.getIsSvipBuy())){
					orderDtl.setIsSvipBuy(shoppingCartOrderDtl.getIsSvipBuy());
					orderDtl.setSvipDiscount(shoppingCartOrderDtl.getSvipDiscount());
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
				if(activityTotalAmountMap.containsKey(activityType)){
					activityTotalAmount = shoppingCartOrderDtl.getSalePrice().multiply(new BigDecimal(shoppingCartOrderDtl.getQuantity()+"")).add(activityTotalAmountMap.get(activityType));
				}else{
					activityTotalAmount = shoppingCartOrderDtl.getSalePrice().multiply(new BigDecimal(shoppingCartOrderDtl.getQuantity()+""));
				}
				activityTotalAmountMap.put(activityType,activityTotalAmount);
				
				
				if(depositOrderListSize > 0){
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
						orderPreferentialInfoService.saveOrderPreferentialInfo("6", depositOrder.getId(), orderDtl.getId(), "3", "已付定金"+depositOrder.getDeposit()+" 抵用"+depositOrder.getDeductAmount(), 
								depositOrder.getDeductAmount(), belong, memberId);
						if(skuDepositTotalAmountMap.containsKey(productItemId)){
							skuDepositTotalAmountMap.put(productItemId, depositOrder.getDeductAmount().add(skuDepositTotalAmountMap.get(productItemId)));
						}else{
							skuDepositTotalAmountMap.put(productItemId, depositOrder.getDeductAmount());
						}
						activityTotalAmountMap.put(activityType, activityTotalAmountMap.get(activityType).subtract(depositOrder.getDeductAmount()));
					}
				}
				if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){
					if(cutOrderId != null){
						locklQuantity = 0;//助力大减价在此不进行库存冻结，在领取任务的时候已经冻结库存
						//如果类型是助力大减价商品，要更新砍价单
						Map<String, Object> cutMap = cutPriceOrderService.getCutOrderPreferentialAmount(null, cutOrderId, memberId,"2",combineOrder);
						if((boolean) cutMap.get("returnCode")){
							cutAssistanceAmount = (BigDecimal) cutMap.get("preferentialAmount");
							activityTotalAmountMap.put(activityType, activityTotalAmountMap.get(activityType).subtract(cutAssistanceAmount));
							if(cutAssistanceAmount.compareTo(zero) > 0){
								String belong = isSpopMcht ? Const.COUPON_BELONG_TYPE_PLATFORM : Const.COUPON_BELONG_TYPE_MCHT;
								orderPreferentialInfoService.saveOrderPreferentialInfo("8", cutOrderId, orderDtl.getId(), "5", "助力减价"+cutAssistanceAmount, 
										cutAssistanceAmount, belong, memberId);
							}
						}else{
							throw new ArgException(cutMap.get("returnMsg").toString());
						}
					}else{
						throw new ArgException("系统异常，你稍后再试！");
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
		Map<String, Object> activityPreferentialReturnMap = orderPreferentialInfoService.getPreferentialInfo(activityTypeMap, activityTotalAmountMap, memberId, activityMap, combineOrder.getId(), reqPRM,reqDataJson,skuDepositTotalAmountMap,skuDepositTotalMap,"1",mchtInfoMap,preContext);
		List<Map<String, Object>> salePriceMapList = (List<Map<String, Object>>) activityPreferentialReturnMap.get("salePriceMapList");
		activityTotalAmountMap = (Map<String, BigDecimal>) activityPreferentialReturnMap.get("activityTotalAmountMap");
		BigDecimal totalProductPreferentialAmount = new BigDecimal(activityPreferentialReturnMap.get("totalProductPreferentialAmount").toString());// 所有商品优惠总和
		totalShoppingAmount = totalShoppingAmount.subtract(totalProductPreferentialAmount);
		/** ================================平台券优惠（平台优惠）Start========================*/
		Map<String, Object> platCouponMap = memberCouponService.computingPlatPreferentialInfo(isContainsSpecMcht, salePriceMapList, totalShoppingAmount, mermberPlatformCouponId, memberId, combineOrder.getId(), activityTotalAmountMap, "1", mchtIdSet);
		totalShoppingAmount = new BigDecimal(platCouponMap.get("totalShoppingAmount").toString());//到这里已经计算出了总的实付支付金额 = 总的购物车金额-总的活动优惠金额-平台优惠金额
		salePriceMapList = (List<Map<String, Object>>) platCouponMap.get("salePriceMapList");
		activityTotalAmountMap = (Map<String, BigDecimal>) platCouponMap.get("activityTotalAmountMap");
		/** ================================平台券优惠（平台补贴）Start========================*/
		totalShoppingAmount = totalShoppingAmount.subtract(cutAssistanceAmount);
		/** ================================店长权益（平台优惠）Start========================*/
		Map<String, Object> shopownerEquityMap = shopownerService.computingShopownerEquity(reqPRM,memberId,salePriceMapList,activityTotalAmountMap,"1");
		BigDecimal totalShopwnerEquityAmount = new BigDecimal(shopownerEquityMap.get("totalShopwnerEquityAmount").toString());
		salePriceMapList = (List<Map<String, Object>>) shopownerEquityMap.get("salePriceMapList");
		totalShoppingAmount = totalShoppingAmount.subtract(totalShopwnerEquityAmount);
		activityTotalAmountMap = (Map<String, BigDecimal>) shopownerEquityMap.get("activityTotalAmountMap");
		/** ================================积分（积分优惠）Start========================*/
		if(isUserIntegral){
			integralAmount = calculationIntegralAmount(activityTotalAmountMap, memberId, totalShoppingAmount, activityTypeQuantityMap, combineOrder.getId(), "1");
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
			// 计算优惠券优惠金额
			if (salePriceMapList != null && salePriceMapList.size() > 0) {
				for (Map<String, Object> map : salePriceMapList) {
					if (map.get("orderDtlId").toString().equals(orderDtlId.toString())) {//8450
						OrderDtl orderDtl = orderDtlMapper.selectByPrimaryKey(Integer.valueOf(map.get("orderDtlId").toString()));
						OrderDtlExtend orderDtlExtend = orderDtlExtendMap.get(orderDtl.getId());
						BigDecimal productPreAmountDtl = (BigDecimal) map.get("productPreAmountDtl");//商家优惠（商家+定金）
						productPreAmountDtl = productPreAmountDtl.add(cutAssistanceAmount);
						BigDecimal allowance = (BigDecimal) map.get("allowance"); //津贴使用
						String activityDiscountStr = map.get("activityDiscount").toString();
						BigDecimal platformAmount = zero;//平台优惠
						if(map.containsKey("platformAmount")){
							//每只商品的平台优惠金额
							platformAmount = (BigDecimal) map.get("platformAmount");
						}
						if(!StringUtil.isBlank(activityDiscountStr)){
							activityDiscountStr = activityDiscountStr.substring(0, activityDiscountStr.length()-1);
						}
						saleOrMallPrice = saleOrMallPrice.multiply(new BigDecimal(orderDtl.getQuantity())).subtract(productPreAmountDtl).subtract(platformAmount);
						j++;
						//计算积分
						if(isUserIntegral){
							if (shoppingCartSubOrders.size() == j) {
								discountIntegralAmount = integralAmount.subtract(discountIntegralAmountAdd);
							} else {
								discountIntegralAmount = saleOrMallPrice.multiply(integralAmount).divide(totalShoppingAmount, 2, BigDecimal.ROUND_HALF_UP);
								if(saleOrMallPrice.compareTo(new BigDecimal("0.01")) == 0){
									discountIntegralAmount = new BigDecimal("0");
								}
								discountIntegralAmountAdd = discountIntegralAmountAdd.add(discountIntegralAmount);
							}
							orderDtl.setIntegralPreferential(discountIntegralAmount);
							orderPreferentialInfoService.saveOrderPreferentialInfo("5", 0, orderDtlId, "1", "积分优惠", discountIntegralAmount, Const.COUPON_BELONG_TYPE_PLATFORM,
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
						//商家类型 1.spop 2.pop
						if(subOrder.getMchtType().equals(Const.MCHT_TYPE_POP)){
							//2:pop：
							//结算金额 = （成交价 * 数量 - 商家优惠 + 运费） * （1-pop佣金比率）
							settleAmount =orderDtl.getSalePrice().multiply(quantity).subtract(orderDtl.getMchtPreferential()).add(freight)
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
						orderDtl.setCommissionAmount(commissionAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
						if("1".equals(openType)){
							orderDtl.setDistributionMemberId(memberInfo.getInvitationMemberId());
							orderDtl.setDistributionAmount(orderDtl.getPayAmount().multiply(orderDtl.getPopCommissionRate()).multiply(new BigDecimal(spreadAmountRate)));
							orderDtl.setDistributionSettleStatus("0");
						}
						orderDtlMapper.updateByPrimaryKeySelective(orderDtl);

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
						subOrder.setDistributionAmount(subOrder.getDistributionAmount().add(orderDtl.getDistributionAmount()));
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
		combineOrder = combineOrderMapper.selectByPrimaryKey(combineOrder.getId());
		if(Const.H5_DY.equals(system) && !JsonUtils.isEmpty(reqDataJson, "adSprId")){
			Integer adSprId = reqDataJson.getInt("adSprId");//广告计划id
			douYinSprDtlService.addModel(adSprId,combineOrder.getId(),memberId);
		}
		if(!(combineOrder.getTotalPayAmount().compareTo(new BigDecimal(payAmount)) == 0)){
			logger.error("系统开了下小差，请重新提交订单,前端提交金额："+payAmount+"--------后端计算金额:"+combineOrder.getTotalPayAmount());
			throw new ArgException("系统开了下小差，请重新提交订单");
		}else 
		if(combineOrder.getTotalPayAmount().compareTo(new BigDecimal(0)) == -1){
			throw new ArgException("支付金额不能小于0");
		}
		memberCouponService.updateMemberCouponUseInfo(memberId,combineOrder.getId(),"1");
		if (payId == 1) {//app支付宝支付
			Map<String, Object> payMap = zfbAlipayApp(combineOrder,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else if (payId == 2) {//app微信支付
			SortedMap<String, Object> payMap = getWecharSignApp(combineOrder,ip,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else if (payId == 4) {//微信公众号支付
			SortedMap<String, Object> payMap = getWecharSign(combineOrder,ip,request, "1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else if(payId == 5){//微信H5支付
			SortedMap<String, Object> payMap = wxPayH5(combineOrder,ip, "1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else if(payId == 6){//支付宝H5支付
			Map<String, Object> payMap = zfbAlipayH5(combineOrder, "1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else if(payId == 7){//微信小程序支付
			Map<String, Object> payMap = miniProgramPay(combineOrder,ip,request, "1");
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
	 * 
	 * 方法描述 ：app微信支付集成
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月3日 下午4:58:40 
	 * @version
	 * @param combineOrder
	 * @param type 
	 * @return
	 * @throws ArgException 
	 */
	private SortedMap<String, Object> getWecharSignApp(CombineOrder combineOrder,String ip, String type) {
		String appId = wecharConfigUtil.getProperty("WX_APP_ID");
		String mchId = wecharConfigUtil.getProperty("WX_MCH_ID");
		String notifyUrl = wecharConfigUtil.getProperty("WX_APP_NOTIFY_URL");
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
		
		String sign = WXSignUtils.createSign("UTF-8", params,wecharConfigUtil.getProperty("WX_KEY"));
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
		String paySign = WXSignUtils.createSign("UTF-8", payParams,wecharConfigUtil.getProperty("WX_KEY"));
		//添加packageName, package与java关键字冲突
		payParams.put("packageName", "Sign=WXPay");
		payParams.put("err_code", err_code);
		payParams.put("err_code_des", err_code_des);
		payParams.put("sign", paySign);
		payParams.put("combineOrderId", combineOrder.getId());
		payParams.put("isHasStock", true);//用来判断库存 true 表示有库存
		payParams.put("orderPayMsg", "");
		payParams.put("orderPaySuccess", Const.COMBINE_ORDER_STATUS_NOT_PAID);
		payParams.put("payId", combineOrder.getPaymentId());
		return payParams;
	}
	
	/**
	 * 
	 * 方法描述 ：app支付宝集成
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月3日 下午3:26:04 
	 * @version
	 * @param combineOrder
	 * @param type 
	 * @return
	 * 
	 */
	private Map<String, Object> zfbAlipayApp(CombineOrder combineOrder, String type){
		Map<String, String> param = new HashMap<>();
		// 公共请求参数
		String notifyUrl = alipayConfigUtil.getProperty("APP_ALIPAY_NOTIFY_URL");
//		if("2".equals(type)){//1下订单回调  2预售订单回调
//			notifyUrl = alipayConfigUtil.getProperty("ALIPAY_DEPOSIT_NOTIFY_URL");
//		}
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
		pcont.put("total_amount", combineOrder.getTotalPayAmount().toString());// 交易金额
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
		payMap.put("payId", combineOrder.getPaymentId());
		return payMap;
	}

	public String getExtraInfo(JSONObject reqPRM, JSONObject reqDataJson, String ip) {
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
		if(reqDataJson.containsKey("appVersion")){
			appVersion = reqDataJson.getString("appVersion");
		}
		extraInfo = "品牌:" + mobileBrand + "|型号:" + phoneModel + 
					"|系统版本:" +moblieSystem + "|醒购App版本号:" + appVersion +
					"|渠道号:" + sprChnl + "|公网ip:" + ip;
		return extraInfo;
	}

	/**
	 * 
	 * 方法描述 ：微信支付集成
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月3日 下午4:58:40 
	 * @version
	 * @param combineOrder
	 * @param request 
	 * @param type 
	 * @return
	 * @throws ArgException 
	 */
	private SortedMap<String, Object> getWecharSign(CombineOrder combineOrder,String ip, HttpServletRequest request, String type) throws ArgException {
		String appId = WeixinUtil.appId;
		String mchId = WeixinUtil.mchtId;
		String notifyUrl = wecharConfigUtil.getProperty("WX_NOTIFY_URL");
		if("2".equals(type)){
			notifyUrl = wecharConfigUtil.getProperty("WX_DEPOSIT_NOTIFY_URL");
		}else if("3".equals(type)){
			notifyUrl = wecharConfigUtil.getProperty("WX_SHOPOWNER_NOTIFY_URL");
		}
		String nonceStr = RandCharsUtils.getRandomString(32);
		String body = combineOrder.getCombineOrderCode();
		String outTradeNo = combineOrder.getCombineOrderCode();
		int totalFee = combineOrder.getTotalPayAmount().multiply(new BigDecimal(100)).intValue();
		String timeStart = RandCharsUtils.timeStart();
		String timeExpire = RandCharsUtils.timeExpire();
		//H5支付的交易类型为MWEB  app支付的交易类型为APP  公众号支付的交易类型为JSAPI
		String tradeType = "JSAPI";
		String spbillCreateIp = ip;
		String openId = (String) request.getSession().getAttribute(BaseDefine.OPENID);
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", appId);
		params.put("mch_id", mchId);
		params.put("notify_url", notifyUrl);
		params.put("nonce_str", nonceStr);
		params.put("body", body);
		params.put("out_trade_no", outTradeNo);
		params.put("total_fee", totalFee);
		params.put("time_start", timeStart);
		params.put("time_expire", timeExpire);
		params.put("trade_type", tradeType);
		params.put("spbill_create_ip", spbillCreateIp);
		params.put("openid", openId);
		
		String sign = WXSignUtils.createSign("UTF-8", params, WeixinUtil.key);
		params.put("sign", sign);
		
		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appId);
		unifiedorder.setMch_id(mchId);
		unifiedorder.setNotify_url(notifyUrl);
		unifiedorder.setNonce_str(nonceStr);
		unifiedorder.setBody(body);
		unifiedorder.setOut_trade_no(outTradeNo);
		unifiedorder.setTotal_fee(totalFee);
		unifiedorder.setTime_start(timeStart);
		unifiedorder.setTrade_type(tradeType);
		unifiedorder.setSpbill_create_ip(spbillCreateIp);
		unifiedorder.setSign(sign);
		unifiedorder.setTime_expire(timeExpire);
		unifiedorder.setOpenid(openId);
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
		payParams.put("appId", appId);
		payParams.put("nonceStr", RandCharsUtils.getRandomString(32));
		payParams.put("timeStamp", new Date().getTime()/1000);
		payParams.put("package", "prepay_id="+prepay_id);
		payParams.put("signType", "MD5");
		String paySign = WXSignUtils.createSign("UTF-8", payParams, WeixinUtil.key);
		payParams.put("paySign", paySign);
		payParams.put("err_code", err_code);
		payParams.put("err_code_des", err_code_des);
		payParams.put("combineOrderId", combineOrder.getId());
		payParams.put("isHasStock", true);//用来判断库存 true 表示有库存
		payParams.put("orderPayMsg", "");
		payParams.put("orderPaySuccess", Const.COMBINE_ORDER_STATUS_NOT_PAID);
		logger.debug("prepay_id--->>>:"+prepay_id);	
		return payParams;
	}
	
	
	
	/**
	 * 
	 * 方法描述 ：微信小程序支付集成
	 * @param combineOrder
	 * @param request 
	 * @param type 
	 * @return
	 * @throws ArgException 
	 */
	private SortedMap<String, Object> miniProgramPay(CombineOrder combineOrder,String ip, HttpServletRequest request, String type) throws ArgException {
		String appId = WeixinUtil.xcxAppId;
		String mchId = WeixinUtil.mchtId;
		String notifyUrl = wecharConfigUtil.getProperty("WX_NOTIFY_URL");
		if("2".equals(type)){
			notifyUrl = wecharConfigUtil.getProperty("WX_DEPOSIT_NOTIFY_URL");
		}
		String nonceStr = RandCharsUtils.getRandomString(32);
		String body = combineOrder.getCombineOrderCode();
		String outTradeNo = combineOrder.getCombineOrderCode();
		int totalFee = combineOrder.getTotalPayAmount().multiply(new BigDecimal(100)).intValue();
		String timeStart = RandCharsUtils.timeStart();
		String timeExpire = RandCharsUtils.timeExpire();
		//H5支付的交易类型为MWEB  app支付的交易类型为APP  公众号支付的交易类型为JSAPI
		String tradeType = "JSAPI";
		String spbillCreateIp = ip;
		String openId = (String) request.getSession().getAttribute("xcxOpenId");
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", appId);
		params.put("mch_id", mchId);
		params.put("notify_url", notifyUrl);
		params.put("nonce_str", nonceStr);
		params.put("body", body);
		params.put("out_trade_no", outTradeNo);
		params.put("total_fee", totalFee);
		params.put("time_start", timeStart);
		params.put("time_expire", timeExpire);
		params.put("trade_type", tradeType);
		params.put("spbill_create_ip", spbillCreateIp);
		params.put("openid", openId);
		
		String sign = WXSignUtils.createSign("UTF-8", params, WeixinUtil.key);
		params.put("sign", sign);
		
		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appId);
		unifiedorder.setMch_id(mchId);
		unifiedorder.setNotify_url(notifyUrl);
		unifiedorder.setNonce_str(nonceStr);
		unifiedorder.setBody(body);
		unifiedorder.setOut_trade_no(outTradeNo);
		unifiedorder.setTotal_fee(totalFee);
		unifiedorder.setTime_start(timeStart);
		unifiedorder.setTrade_type(tradeType);
		unifiedorder.setSpbill_create_ip(spbillCreateIp);
		unifiedorder.setSign(sign);
		unifiedorder.setTime_expire(timeExpire);
		unifiedorder.setOpenid(openId);
		//unifiedorder.setDetail(detail);
		
		
		//获取xml信息
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		logger.info("小程序xml数据------------>："+xmlInfo);
		String wxUrl = wecharConfigUtil.getProperty("WX_UNIFIED_ORDER");
		
//		System.out.println("小程序支付请求参数-----------"+xmlInfo);
		
		String method = "POST";
		
		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
		
//		System.out.println("-----------------小程序统一下单返回："+weixinPost);
		
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
		payParams.put("appId", appId);
		payParams.put("nonceStr", RandCharsUtils.getRandomString(32));
		payParams.put("timeStamp", new Date().getTime()/1000);
		payParams.put("package", "prepay_id="+prepay_id);
		payParams.put("signType", "MD5");
		String paySign = WXSignUtils.createSign("UTF-8", payParams, WeixinUtil.key);
		payParams.put("paySign", paySign);
		payParams.put("err_code", err_code);
		payParams.put("err_code_des", err_code_des);
		payParams.put("combineOrderId", combineOrder.getId());
		payParams.put("isHasStock", true);//用来判断库存 true 表示有库存
		payParams.put("orderPayMsg", "");
		payParams.put("orderPaySuccess", Const.COMBINE_ORDER_STATUS_NOT_PAID);
		logger.debug("prepay_id--->>>:"+prepay_id);	
		return payParams;
	}

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
			integral += calculationIntegral(activityType,activitySalePrice,iChargr,memberAccounts,payMoney,productQuantity,integral);
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


	

	/**
	 * 再次提交支付
	 * @param request 
	 * @throws Exception 
	 */
	public ResponseMsg submitPaymentAgain(JSONObject reqDataJson, HttpServletRequest request) throws Exception {
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
		if (payId == 1) {//app支付宝支付
			Map<String, Object> payMap = zfbAlipayApp(combineOrder,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else if (payId == 2) {//app微信支付
			SortedMap<String, Object> payMap = getWecharSignApp(combineOrder,ip,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else if (payId == 4) {//微信公众号支付
			//微信公众号支付
			SortedMap<String, Object> payMap = getWecharSign(combineOrder,ip,request,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		} else if(payId == 5){
			//微信H5支付
			SortedMap<String, Object> payMap = wxPayH5(combineOrder,ip,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		} else if(payId == 6){
			//支付宝H5支付
			Map<String, Object> payMap = zfbAlipayH5(combineOrder,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else if(payId == 7){
			//微信小程序支付
			Map<String, Object> payMap = miniProgramPay(combineOrder,ip,request,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else{
			throw new ArgException("请用微信或支付宝支付");
		}
		
	}
	
	/**
	 * 
	 * 方法描述 ：计算不做优惠的原价
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月3日 上午9:47:57 
	 * @version
	 * @param shoppingCarts
	 * @param min
	 * @return
	 */
	private BigDecimal getDiscountAmount(List<ShoppingCartCustom> shoppingCarts, int min) {
		double price = 0;
		for (ShoppingCartCustom shoppingCart : shoppingCarts) {
			double salePrice = 0.00;
			if(shoppingCart.getActivityType().equals("101")){
				salePrice = shoppingCart.getMallPrice().doubleValue();
			}else{
				salePrice = shoppingCart.getSalePrice().doubleValue();
			}
			
	    	int quantity = shoppingCart.getQuantity();
			if(quantity == min){
				price += salePrice*min;
				break;
			}else if(shoppingCart.getQuantity() > min){
				price += salePrice*min;
				break;
			}else if(shoppingCart.getQuantity() < min){
				price += salePrice*quantity;
				min = min-quantity;
			}
			if(min == 0){
				break;
			}
		}
		return new BigDecimal(price);
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
	 * @throws Exception 
	 */
	private SortedMap<String, Object> wxPayH5(CombineOrder combineOrder,String ip, String type){
		String appId = wecharConfigUtil.getProperty("WX_APP_ID");
		String mchId = wecharConfigUtil.getProperty("WX_MCH_ID");
		String notifyUrl = wecharConfigUtil.getProperty("WX_NOTIFY_URL");
		if("2".equals(type)){
			notifyUrl = wecharConfigUtil.getProperty("WX_DEPOSIT_NOTIFY_URL");
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
		String tradeType = "MWEB";
		//WAP网站应用
		String scene_info = "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"https://xgbuy.cc\",\"wap_name\": \"腾讯充值\"}}";
		String spbillCreateIp = ip;
		
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", appId);
		params.put("mch_id", mchId);
		params.put("notify_url", notifyUrl);
		params.put("nonce_str", nonceStr);
		params.put("body", body);
		params.put("attach", attach);
		params.put("out_trade_no", outTradeNo);
		params.put("total_fee", totalFee);
		params.put("time_start", timeStart);
		params.put("time_expire", timeExpire);
		params.put("trade_type", tradeType);
		params.put("spbill_create_ip", spbillCreateIp);
		params.put("scene_info", scene_info);
		
		String sign = WXSignUtils.createSign("UTF-8", params, wecharConfigUtil.getProperty("WX_KEY"));
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
		unifiedorder.setScene_info(scene_info);
		
		//获取xml信息
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		
		String wxUrl = wecharConfigUtil.getProperty("WX_UNIFIED_ORDER");
		
		String method = "POST";
		
		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
		
		List<Element> list = JdomParseXmlUtils.getWXPayResultElement(weixinPost);
		SortedMap<String, Object> payParams = new TreeMap<String, Object>();
		String mweb_url = "";
		String err_code = "";
		String err_code_des = "";
		//获取微信统一下单返回参数   封装model类
		UnifiedorderResult unifiedorderResult = getWxUnifiedorderResult(list);
		if(unifiedorderResult != null){
			if(unifiedorderResult.getReturn_code().equals("SUCCESS")){
				if(unifiedorderResult.getResult_code().equals("SUCCESS")){
					mweb_url = unifiedorderResult.getMweb_url();
//					System.out.println("我的web_url地址是："+mweb_url);
				}
				
			}else{
				err_code = unifiedorderResult.getErr_code();
				err_code_des = unifiedorderResult.getErr_code_des();
			}
		}
		if(StringUtil.isBlank(mweb_url)){
			throw new ArgException("订单支付失败，请重新提交");
		}
		payParams.put("mweb_url", mweb_url);
		payParams.put("err_code", err_code);
		payParams.put("err_code_des", err_code_des);
		payParams.put("combineOrderId", combineOrder.getId());
		payParams.put("isHasStock", true);//用来判断库存 true 表示有库存
		payParams.put("orderPayMsg", "");
		payParams.put("orderPaySuccess", Const.COMBINE_ORDER_STATUS_NOT_PAID);
		return payParams;
	}

	public UnifiedorderResult getWxUnifiedorderResult(List<Element> list) {
		UnifiedorderResult result = null;
		if (list != null && list.size() > 0) {
			result = new UnifiedorderResult();
			for (Element element : list) {
				logger.info(element.getName()+"--->>>:"+element.getText());	
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
				if ("mweb_url".equals(element.getName())) {
					result.setMweb_url(element.getText());
				}
				if ("trade_state".equals(element.getName())) {
					result.setTrade_state (element.getText());
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
	 * @throws ArgException 
	 * @throws AlipayApiException 
	 */
	private Map<String, Object> zfbAlipayH5(CombineOrder combineOrder, String type){
		Map<String, Object> pcont = new HashMap<>();
		// 支付业务请求参数
		pcont.put("out_trade_no", combineOrder.getCombineOrderCode()); // 商户订单号
		pcont.put("total_amount", combineOrder.getTotalPayAmount().toString());// 交易金额
		//pcont.put("total_amount", String.valueOf("0.01"));// 交易金额
		pcont.put("subject", combineOrder.getCombineOrderCode()); // 订单标题
		pcont.put("body", "聚买商品");// 对交易或商品的描述
		pcont.put("product_code", "QUICK_MSECURITY_PAY");// 销售产品码
		
		String content = JSON.toJSONString(pcont);// 业务请求参数
		AlipayTradeWapPayResponse alipayTradeWapPayResponse = AlipayService.pay(content,type);
		Map<String, Object> payMap = new HashMap<>();
		payMap.put("combineOrderId", combineOrder.getId());
		payMap.put("form", alipayTradeWapPayResponse.getBody());
		payMap.put("isHasStock", true);//用来判断库存 true 表示有库存
		payMap.put("orderPayMsg", "");
		payMap.put("orderPaySuccess", Const.COMBINE_ORDER_STATUS_NOT_PAID);
		return payMap;
	}
	
	/**
	 * 
	 * 方法描述 ：支付成功与失败，修改单据状态
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月5日 下午6:15:55 
	 * @version
	 * @param b
	 * @param combineOrder
	 * @param payDate 
	 * @throws ArgException
	 */
	public void updateOrderStatus(boolean b, CombineOrder combineOrder, String paymentNo, Date payDate) throws ArgException {
		Date date = new Date();
		if(b){
			List<Integer> productIds = new ArrayList<Integer>();
			combineOrder.setStatus(Const.COMBINE_ORDER_STATUS_PAID);
			combineOrder.setPayStatus(Const.COMBINE_ORDER_PAY_STATUS_SUCCESS);
			combineOrder.setPayDate(payDate);
			combineOrder.setPaymentNo(paymentNo);
			//1 ZFB 2WX
			String financialNo = "";
			int paymentId=combineOrder.getPaymentId();
			if(paymentId == 1||paymentId == 6){
				//支付宝(APP),支付宝(H5)
				financialNo = "ZFB"+DateUtil.getFormatDate(date, "yyyyMMdd");
			}else if(paymentId == 2 ||paymentId == 5){
				//微信(APP),微信(H5)
				financialNo = "WX"+DateUtil.getFormatDate(date, "yyyyMMdd");
			}else if(paymentId == 4){
				//微信(公众号)
				financialNo = "GZH"+DateUtil.getFormatDate(date, "yyyyMMdd");
			}
			combineOrder.setFinancialNo(financialNo);
			combineOrderMapper.updateByPrimaryKeySelective(combineOrder);
			
			//查找物流发货时间，找不到默认为当前时间+48H(承诺发货时间的取值判断顺序：1、判断特殊地区  2、 判断通用的  3、用NOW+48小时)
			
			Map<String,Object> dateCnfMap = deliveryOvertimeCnfService.getDeliveryOvertimeCnf(combineOrder.getReceiverAddress());
			//计算sku库存
			SubOrderExample subOrderExample = new SubOrderExample();
			subOrderExample.createCriteria().andCombineOrderIdEqualTo(combineOrder.getId());
			List<SubOrder> subOrderList = subOrderMapper.selectByExample(subOrderExample);
			for (SubOrder subOrder : subOrderList) {
				subOrder.setStatus(Const.ORDER_STATUS_PAID);
				subOrder.setStatusDate(date);
				if(dateCnfMap.get("deliveryOvertime") != null){
					subOrder.setDeliveryOvertime(Integer.valueOf(dateCnfMap.get("deliveryOvertime").toString()));
				}
				subOrder.setDeliveryLastDate((Date)dateCnfMap.get("deliveryDate"));
				if("1".equals(subOrder.getAuditStatus())) {
					subOrder.setAuditDate(payDate);
				}
				subOrderMapper.updateByPrimaryKeySelective(subOrder);
				//插入订单日志
				orderLogService.insertOrderLog(subOrder.getId(),Const.ORDER_STATUS_PAID,combineOrder.getMemberId());
				//待付款订单取消了  库存冻结要释放
				OrderDtlExample orderDtlExample = new OrderDtlExample();
				orderDtlExample.createCriteria().andSubOrderIdEqualTo(subOrder.getId());
				List<OrderDtl> orderDtlList = orderDtlMapper.selectByExample(orderDtlExample);
				for (OrderDtl orderDtl : orderDtlList) {
					Integer productId = orderDtl.getProductId();
					productItemService.updateProductSkuStock(orderDtl.getProductItemId(), orderDtl.getQuantity(),orderDtl.getCloudProductItemId());//库存
					if(!productIds.contains(productId)){
						productIds.add(productId);
					}
					//支付成功，查看是否有预付定金，有的话更新预付子订单状态
					subDepositOrderService.updateSubDepositOrderStatus(orderDtl.getId(),date,Const.SUB_DEPOSIT_STATUS_TAIL_ARREADY_ORDER,Const.SUB_DEPOSIT_STATUS_TAIL_PAID, payDate);
				}
			}
			//满额送,买即赠
//			fullGive(combineOrder,date);
			//修改sku规格,获取有库存的sku最低价格，更新商品主表
//			productItemService.updateMinProductItemPrice(productIds);
			
			//TODO STORY #1758 下单时是否是svip会员
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(combineOrder.getMemberId());
			String isSvipMember = "";
			if("1".equals(memberInfo.getIsSvip()) && memberInfo.getSvipExpireDate()!=null && memberInfo.getSvipExpireDate().after(date)){
				isSvipMember = "1";
			}else{
				isSvipMember = "0";
			}
			CombineOrderExtendExample e = new CombineOrderExtendExample();
			e.createCriteria().andDelFlagEqualTo("0").andCombineOrderIdEqualTo(combineOrder.getId());
			List<CombineOrderExtend> combineOrderExtends = combineOrderExtendMapper.selectByExample(e);
			if(combineOrderExtends!=null && combineOrderExtends.size()>0){//正常是不会>0,下单成功时是首条CombineOrderExtend记录
				CombineOrderExtend combineOrderExtend = combineOrderExtends.get(0);
				combineOrderExtend.setIsSvipMember(isSvipMember);
				combineOrderExtend.setUpdateDate(date);
				combineOrderExtend.setUpdateBy(combineOrder.getMemberId());
				combineOrderExtendMapper.updateByPrimaryKeySelective(combineOrderExtend);
			}else{
				CombineOrderExtend coe = new CombineOrderExtend();
				coe.setDelFlag("0");
				coe.setCreateBy(combineOrder.getMemberId());
				coe.setCreateDate(date);
				coe.setCombineOrderId(combineOrder.getId());
				coe.setIsSvipMember(isSvipMember);
				combineOrderExtendMapper.insertSelective(coe);
			}
		}else{
			combineOrder.setPayStatus(Const.COMBINE_ORDER_PAY_STATUS_FAILED);
			combineOrder.setPayDate(date);
			combineOrderMapper.updateByPrimaryKeySelective(combineOrder);
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
				if(activityCustom != null &&!StringUtil.isBlank(activityCustom.getPreferentialType()) && !StringUtil.isBlank(activityCustom.getPreferentialIdGroup())){
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
										saveNonCumulativeInfo(combineOrder, shoppingCart, productId, combineOrder.getMemberId(), date,
												productNum, productFlag, couponFlag, giveCouponId, integralFlag,
												integeral, belong, rang, preferentialType,
												Integer.valueOf(preferentialId));
									}
								} else if (sumFlag.equals("1")){// 累加
										// 单价*数量 > 最低消费
									if (activityAreaTotalSalePrice.compareTo(minimum) >= 0) {
										// 保存满额赠累加数据
										saveCumulativeInfo(combineOrder, shoppingCart, productId, combineOrder.getMemberId(), date,
												productNum, productFlag, couponFlag, giveCouponId, integralFlag,
												integeral, activityAreaTotalSalePrice, minimum, belong, rang, preferentialType,
												Integer.valueOf(preferentialId));
									}
								}
							} else if (type.equals("2")){// 买即赠
									// 买即赠送商品，插入订单明细中
								saveNonCumulativeInfo(combineOrder, shoppingCart, productId, combineOrder.getMemberId(), date,
										productNum, productFlag, couponFlag, giveCouponId, integralFlag, integeral,
										belong, rang, preferentialType, Integer.valueOf(preferentialId));
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * 方法描述 ：保存满额赠送，累加
	 * 
	 * @author chenwf:
	 * @date 创建时间：2017年5月24日 上午11:41:26
	 * @version
	 * @param combineOrder
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
	private void saveCumulativeInfo(CombineOrder combineOrder, ShoppingCart shoppingCart, Integer productId,
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
				subOrderExample.createCriteria().andCombineOrderIdEqualTo(combineOrder.getId())
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
						combineOrder.getId(), combineOrder.getMemberId(),"1");
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
	 * @param combineOrder
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
	private void saveNonCumulativeInfo(CombineOrder combineOrder, ShoppingCart shoppingCart, Integer productId,
			Integer memberId, Date date, Integer productNum, String productFlag, String couponFlag, String giveCouponId,
			String integralFlag, Integer integeral, String belong, String rang, String preferentialType,
			Integer preferentialId) {
		// 满额赠送商品，插入订单明细中
		if (!StringUtil.isBlank(productFlag) && productFlag.equals("1")) {
			ProductCustom productCustom = productCustomMapper.getProductInfo(productId);
			if(productCustom != null && productCustom.getId() != null){
				Integer mchtId = productCustom.getMchtId();
				SubOrderExample subOrderExample = new SubOrderExample();
				subOrderExample.createCriteria().andCombineOrderIdEqualTo(combineOrder.getId())
						.andMchtIdEqualTo(mchtId);
				List<SubOrder> subOrders = subOrderMapper.selectByExample(subOrderExample);
				if (subOrders != null && subOrders.size() > 0) {
					BigDecimal zero = new BigDecimal("0");
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
					combineOrder.getId(), combineOrder.getMemberId(),"1");
		}

	}

	public ResponseMsg submitAfterPayment(JSONObject reqPRM, JSONObject reqDataJson, HttpServletRequest request) throws ArgException {
		Integer combineOrderId = reqDataJson.getInt("combineOrderId");
		Integer payId = reqDataJson.getInt("payId");
		String ip = StringUtil.getIpAddr(request);
		
		
//		System.out.println("--------------------------支付ip："+ip+"---------------------------------");
//		System.out.println("--------------------------"+StringUtil.getIpAddr(request)+"---------------------------------");
		
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
		//需求2031 SVIP营销绑定，H5和小程序暂不开放
		if (combineOrder.getSvipOrderId() != null) {
			throw new ArgException("请到APP内付款");
		}
		combineOrder.setPaymentId(payId);
		combineOrder.setOrderExtraInfo(extraInfo);
		combineOrderMapper.updateByPrimaryKeySelective(combineOrder);
		if (payId == 1) {//app支付宝支付
			Map<String, Object> payMap = zfbAlipayApp(combineOrder,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else if (payId == 2) {//app微信支付
			SortedMap<String, Object> payMap = getWecharSignApp(combineOrder,ip,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else if (payId == 4) {//微信公众号支付
			//微信公众号支付
			SortedMap<String, Object> payMap = getWecharSign(combineOrder,ip,request,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		} else if(payId == 5){
			//微信H5支付
			SortedMap<String, Object> payMap = wxPayH5(combineOrder,ip,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		} else if(payId == 6){
			//支付宝H5支付
			Map<String, Object> payMap = zfbAlipayH5(combineOrder,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else if(payId == 7){
			//微信小程序支付
			Map<String, Object> payMap = miniProgramPay(combineOrder,ip,request,"1");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else{
			throw new ArgException("请用微信或支付宝支付");
		}
	}

	public Integer calculationIntegral(String activityType, BigDecimal activitySalePrice, BigDecimal iChargr, List<MemberAccount> memberAccounts, BigDecimal payAmount, Integer quantity, Integer alreadyUsedIntegral) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("activityType", activityType);
		List<SingleProductActivityCnf> cnfs = singleProductActivityCnfService.findListQuery(queryObject);
		BigDecimal integralExchangeRate = new BigDecimal(0);
		if(CollectionUtils.isNotEmpty(cnfs)){
			SingleProductActivityCnf activityCnf = cnfs.get(0);
			integralExchangeRate = activityCnf.getIntegralExchangeRate();
		}else{
			integralExchangeRate = new BigDecimal(INTEGERAL_DISOUNT);
		}

		Integer integral = 0;
		if(NumberUtil.gtZero(iChargr)){
			MemberAccount memberAccount = memberAccounts.get(0);
			//将用户总积分积分转换为元
			BigDecimal amount = activitySalePrice.multiply(integralExchangeRate);
			Integer memberIntegral = memberAccount.getIntegral()-alreadyUsedIntegral;
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

	@SuppressWarnings("unchecked")
	public Map<String, Object> getSignInPayInfo(JSONObject reqPRM, JSONObject reqDataJson,Integer memberId) {
		Map<String, Object> map = new HashMap<>();
		Integer productItemId = null;
		Integer productId = null;
		Integer addressId = null;
		ProductItem item = null;
		Product p = null;
		BigDecimal totalSalePrice = new BigDecimal("0");//商品总金额
		BigDecimal freight = new BigDecimal("0");//运费
		BigDecimal preferentialAmount = new BigDecimal("0");//优惠金额
		BigDecimal payAmount = new BigDecimal("0");//实付金额
		List<Map<String, Object>> payMapList = new ArrayList<>();//支付方式
		Map<Integer, String> freightContentMap = new HashMap<Integer, String>();//
		Map<Integer, ProvinceFreightCustom> freightCustomMap = new HashMap<Integer, ProvinceFreightCustom>();
		String productName = "";
		String skuPic = "";
		String productPropDesc = "";
		BigDecimal salePrice = new BigDecimal("0");//活动价格
		String type = "1";//1签到商品结算页面入口 2助力大减价结算页面入口
		if(reqDataJson.containsKey("type")&&!StringUtil.isBlank(reqDataJson.getString("type"))){
			type = reqDataJson.getString("type");
		}
		if(reqDataJson.containsKey("addressId") && !StringUtil.isBlank(reqDataJson.getString("addressId"))){
			addressId = reqDataJson.getInt("addressId");
		}
		//地址
		Map<String, Object> addressMap = memberAddressService.getMemberDefaultAddress(memberId,addressId);
		if("1".equals(type)){
			productId = reqDataJson.getInt("productId");
			p = productService.selectByPrimaryKey(productId);
			if(reqDataJson.containsKey("productItemId")&&!StringUtil.isEmpty(reqDataJson.getString("productItemId"))){
				productItemId=Integer.valueOf(reqDataJson.getString("productItemId"));
				item = productItemService.selectByPrimaryKey(productItemId);
			}else{
				if(!"1".equals(p.getIsSingleProp())){
					throw new ArgException("请选择商品规格");
				}
				ProductItemExample productItemExample=new ProductItemExample();
				productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(p.getId());
				List<ProductItem> productItems=productItemService.selectByExample(productItemExample);
				productItemId=productItems.get(0).getId();
				item = productItems.get(0);
			}
			productName = p.getName();
			skuPic = StringUtil.getPic(item.getPic(), "S");
			//获取sku描述
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("productItemId", productItemId);
			paramsMap.put("mark", "-");
			productPropDesc = productItemService.getProductItemPropDesc(paramsMap);
		}else{
			Integer cutOrderId = reqDataJson.getInt("cutOrderId");
			CutPriceOrder cutPriceOrder = cutPriceOrderService.selectByPrimaryKey(cutOrderId);
			totalSalePrice = cutPriceOrder.getSalePrice();
			salePrice = cutPriceOrder.getSalePrice();
			payAmount = totalSalePrice.subtract(preferentialAmount);
			productId = cutPriceOrder.getProductId();
			productName = cutPriceOrder.getProductName();
			productPropDesc = cutPriceOrder.getProductPropDesc();
			productItemId = cutPriceOrder.getProductItemId();
			p = productService.selectByPrimaryKey(productId);
			// 支付方式
			payMapList = sysPaymentService.getPayMethod("",reqPRM);
			//计算运费
			if(addressMap.get("provinceId") != null){
				Integer provinceId = Integer.parseInt(addressMap.get("provinceId").toString());
				List<Map<String, Object>> freights = new ArrayList<Map<String, Object>>();
				Map<String, Object> freightMap=new HashMap<String, Object>();
				freightMap.put("productItemId", productItemId);
				freightMap.put("freightTemplateId", p.getFreightTemplateId());
				freightMap.put("quantity", cutPriceOrder.getQuantity());
				freights.add(freightMap);
				Map<String, Object> fMap = provinceFreightService.getProductFreightAmount(freights, provinceId);
				freightContentMap = (Map<Integer, String>) fMap.get("freightContentMap");
				Map<Integer, BigDecimal> productItemFreightMap = (Map<Integer, BigDecimal>) fMap.get("productItemFreightMap");//每只sku对应的运费金额
				freightCustomMap = (Map<Integer, ProvinceFreightCustom>) fMap.get("freightCustom");//每个模板所对应的名称和总运费金额
				freight = productItemFreightMap.get(-1);//总运费设置key值为-1
			}
		}
		 
		//商品信息
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> dataMap = new HashMap<>();
		//每只sku的运费数据
		String freightName = "（包邮）";
		String freightType = "1";
		if(freightContentMap.get(productItemId) != null && "2".equals(freightContentMap.get(productItemId))){
			freightName = "（不包邮）";
			freightType = "2";
		}
		dataMap.put("productName", productName);
		dataMap.put("productPropDesc", productPropDesc);
		dataMap.put("skuPic", skuPic);
		dataMap.put("salePrice", salePrice);
		dataMap.put("productId", productId);
		dataMap.put("freightTemplateId", p.getFreightTemplateId());
		dataMap.put("freightName", freightName);
		dataMap.put("freightType", freightType);
		dataList.add(dataMap);
		
		map.put("productDataList", dataList);
		map.put("addressMap", addressMap);
		map.put("payMapList", payMapList);
		map.put("freight", freight);//运费
		map.put("totalProductAmount", totalSalePrice);//商品金额
		map.put("preferentialAmount", preferentialAmount);//优惠金额 = 楼层优惠+定金+平台+平台补贴
		map.put("freightCustomMap", freightCustomMap);
		map.put("payAmount", payAmount);
		return map;
	}
	
	public ResponseMsg submitDepositOrderPayment(JSONObject reqPRM, JSONObject reqDataJson, HttpServletRequest request,Integer memberId) {
		Date currentDate = new Date();
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
			ProductCustom p = productCustoms.get(0);
			//先锁定库存
			productItemService.updateSkuLockedAmount(productItemId,totalQuantity,p.getMchtId(),"1");
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
				if(mchtType.equals("2")){
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
				if (payId == 4) {
					//微信公众号支付
					SortedMap<String, Object> payMap = getWecharSign(combineOrder,ip,request,"2");
					return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
				} else if(payId == 5){
					//微信H5支付
					SortedMap<String, Object> payMap = wxPayH5(combineOrder,ip,"2");
					return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
				} else if(payId == 6){
					//支付宝H5支付
					Map<String, Object> payMap = zfbAlipayH5(combineOrder,"2");
					return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
				}else if(payId == 7){
					//微信小程序支付
					Map<String, Object> payMap = miniProgramPay(combineOrder,ip,request,"2");
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
		if (payId == 4) {
			//微信公众号支付
			SortedMap<String, Object> payMap = getWecharSign(combineOrder,ip,request,"2");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		} else if(payId == 5){
			//微信H5支付
			SortedMap<String, Object> payMap = wxPayH5(combineOrder,ip,"2");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		} else if(payId == 6){
			//支付宝H5支付
			Map<String, Object> payMap = zfbAlipayH5(combineOrder,"2");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else if(payId == 7){
			//微信小程序支付
			Map<String, Object> payMap = miniProgramPay(combineOrder,ip,request,"2");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else{
			throw new ArgException("请用微信或支付宝支付");
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


	public ResponseMsg submitShopownerOrderPayment(JSONObject reqPRM, JSONObject reqDataJson,HttpServletRequest request, Integer memberId) {
		MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		Integer paymentId = reqDataJson.getInt("paymentId");
		Integer salesmanId = reqDataJson.getInt("salesmanId");
		String system = reqPRM.getString("system");
		String ip = StringUtil.getIpAddr(request);
		Date currentDate = new Date();
		if(memberInfo == null || memberInfo.getInvitationMemberId() != null){
			throw new ArgException("已成为上级用户不能开通店长权益");
		}
		Salesman salesman = salesmanService.findModelById(salesmanId);
		if(salesman == null){
			throw new ArgException("该业务员不存在，请联系客服处理");
		}
		Shopowner shopowner = shopownerService.findModelByMemberId(memberId);
		if(shopowner != null){
			throw new ArgException("已开通店长权益");
		}
		SysParamCfg cfg = DataDicUtil.getSysParamCfgModel("APP_SHOWOWNER_EQUITY_OPEN_AMOUNT", null);
		if(cfg == null || StringUtil.isBlank(cfg.getParamValue())){
			throw new ArgException("系统异常，请联系客服处理!");
		}
		List<Information> informations = informationService.getNovaPlanAgreement(Const.NOVA_PLAN_SHOPWNER_AGREEMENT_CATALOG_ID);
		if(CollectionUtils.isEmpty(informations)){
			throw new ArgException("系统异常，请联系客服处理.!");
		}
		Integer informationId = informations.get(0).getId();
		BigDecimal payAmount = new BigDecimal(cfg.getParamValue());
		String orderCode = "S" + CommonUtil.getOrderCode();
		ShopownerOrder order = new ShopownerOrder();
		order.setOrderCode(orderCode);
		order.setMemberId(memberId);
		order.setSourceClient(system);
		order.setStatus("0");
		order.setPaymentId(paymentId);
		order.setPayAmount(payAmount);
		order.setIp(ip);
		order.setAgreementId(informationId);
		order.setSalesmanId(salesmanId);
		order.setCreateBy(memberId);
		order.setCreateDate(currentDate);
		order.setDelFlag("0");
		shopownerOrderService.insertSelective(order);
		
		CombineOrder combineOrder = new CombineOrder();
		combineOrder.setId(order.getId());
		combineOrder.setCombineOrderCode(order.getOrderCode());
		combineOrder.setTotalPayAmount(order.getPayAmount());
		if(paymentId == 4){
			//微信H5支付
			SortedMap<String, Object> payMap = getWecharSign(combineOrder, ip, request, "3");
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, payMap);
		}else{
			throw new ArgException("暂时只支持微信支付！");
		}
	}

	/**
	 * 店长权益回调
	 * @param request
	 * @param b
	 * @param order
	 * @param tradeNo
	 * @param paymentId
	 * @param payDate
	 */
	public void updateShopownerOrderStatus(HttpServletRequest request, boolean b, ShopownerOrder order,String tradeNo, Integer paymentId, Date payDate) {
		Date currentDate = new Date();
		if(b){
			Integer memberId = order.getMemberId();
			Date novaPlanEndDate = DateUtil.addYear(currentDate, 1);
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
			//支付成功，更新支付状态
			order.setStatus(Const.COMBINE_ORDER_STATUS_PAID);
			order.setPayStatus(Const.COMBINE_ORDER_PAY_STATUS_SUCCESS);
			order.setPaymentNo(tradeNo);
			order.setPayDate(payDate);
			order.setUpdateDate(currentDate);
			shopownerOrderService.updateByPrimaryKeySelective(order);
			
			//创建店长
			Shopowner shopowner = new Shopowner();
			shopowner.setSalesmanId(order.getSalesmanId());
			shopowner.setMemberId(memberId);
			shopowner.setCreateBy(memberId);
			shopowner.setCreateDate(currentDate);
			shopowner.setDelFlag("0");
			shopownerService.insertSelective(shopowner);
			
			//更新用户开通时间
			memberInfo.setNovaProjectBeginDate(currentDate);
			memberInfo.setNovaProjectEndDate(novaPlanEndDate);
			if(StringUtil.isBlank(memberInfo.getInvitationCode())){
				memberInfo.setInvitationCode((new Random().nextInt(99999999)%(99999999-10000000+1) + 10000000)+"");
			}
			memberInfoService.updateByPrimaryKeySelective(memberInfo);
			
			//创建合约日志
			MemberNovaRecord memberNovaRecord = new MemberNovaRecord();
			memberNovaRecord.setMemberId(memberId);
			memberNovaRecord.setInformationtId(order.getAgreementId());
			memberNovaRecord.setContractTime(currentDate);
			memberNovaRecord.setAgreementBeginDate(currentDate);
			memberNovaRecord.setAgreementEndDate(novaPlanEndDate);
			memberNovaRecord.setCreateBy(memberId);
			memberNovaRecord.setCreateDate(currentDate);
			memberNovaRecord.setDelFlag("0");
			memberNovaRecordService.insertSelective(memberNovaRecord);
			
			request.getSession().setAttribute(BaseDefine.MEMBER_INFO, memberInfo);
		}else{
			order.setPayStatus(Const.COMBINE_ORDER_PAY_STATUS_FAILED);
			order.setUpdateDate(currentDate);
			shopownerOrderService.updateByPrimaryKeySelective(order);
			logger.info("支付失败"+order.getOrderCode());
		}
	}

}
