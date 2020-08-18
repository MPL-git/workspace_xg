package com.jf.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.common.constant.Const;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.dao.AppAccessTokenMapper;
import com.jf.dao.ShoppingCartCustomMapper;
import com.jf.dao.ShoppingCartMapper;
import com.jf.dao.SubDepositOrderMapper;
import com.jf.entity.Activity;
import com.jf.entity.ActivityCustom;
import com.jf.entity.MemberAddress;
import com.jf.entity.MemberAddressExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemExample;
import com.jf.entity.ShoppingCart;
import com.jf.entity.ShoppingCartCustom;
import com.jf.entity.ShoppingCartExample;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityCnf;
import com.jf.entity.SingleProductActivityCnfExample;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderExample;

import net.sf.json.JSONObject;

@Service
@Transactional
public class ShoppingCartService extends BaseService<ShoppingCart,ShoppingCartExample> {
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	private ShoppingCartCustomMapper shoppingCartCustomMapper;
	
	@Autowired
	private AppAccessTokenMapper appAccessTokenMapper;
	
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
	private SingleProductActivityService singleProductActivityService;
	
	@Resource
	private SingleProductActivityCnfService singleProductActivityCnfService;
	
	@Resource
	private MemberInfoService memberInfoService;
	
	@Resource
	private MemberAddressService memberAddressService;
	
	@Resource
	private SubDepositOrderMapper subDepositOrderMapper;
	
	
	@Autowired
	public void setShoppingCartMapper(ShoppingCartMapper shoppingCartMapper) {
		super.setDao(shoppingCartMapper);
		this.shoppingCartMapper = shoppingCartMapper;
	}
	
	public  List<ShoppingCartCustom> getMemberShoppingCart(Integer memberId){
		return shoppingCartCustomMapper.getMemberShoppingCart(memberId);
	}

	public List<ShoppingCartCustom> findShoppingCartById(Map<String, Object> shopCarParams) {
		
		return shoppingCartCustomMapper.findShoppingCartById(shopCarParams);
	}

	public List<ShoppingCart> findCartsByIds(List<Integer> shopCardIdsList) {
		ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
		shoppingCartExample.createCriteria().andIdIn(shopCardIdsList).andDelFlagEqualTo("0");
		return shoppingCartMapper.selectByExample(shoppingCartExample);
	}

	public Integer getShoppingCartNum(Integer memberId) {
		
		return shoppingCartCustomMapper.getShoppingCartNum(memberId);
	}

	public ShoppingCart addShoppingCart(JSONObject reqDataJson, Integer memberId) throws ArgException {
		Integer quantity = Integer.valueOf(reqDataJson.getString("quantity"));
		Integer productId = Integer.valueOf(reqDataJson.getString("productId"));
		ProductCustom pc = productService.getNormalOperationProduct(productId);//获取商品正常的运行状态信息
		if(pc == null || pc.getId() == null){
			throw new ArgException("该商品已经卖光啦，再看看其他商品吧~");
		}
		Integer productItemId=null;
		Integer activityId = null;
		String isSingleProp = pc.getIsSingleProp();
		String saleType = pc.getSaleType();//销售类型\r\n1 品牌款 \r\n2 单品款
		String shopStatus = pc.getShopStatus();//店铺状态(0关闭 1开通)
		Integer limitBuy = pc.getLimitBuy();
		String activityType = "0";
		String singleProductActivityId = "";
		Date currentDate = new Date();
		if(reqDataJson.containsKey("productItemId") 
				&& !StringUtil.isBlank(reqDataJson.getString("productItemId"))){
			productItemId=reqDataJson.getInt("productItemId");
		}else{
			if(!"1".equals(isSingleProp)){
				throw new ArgException("该商品已经卖光啦，再看看其他商品吧~");
			}
			ProductItemExample productItemExample=new ProductItemExample();
			productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productId);
			List<ProductItem> productItems=productItemService.selectByExample(productItemExample);
			productItemId=productItems.get(0).getId();
		}
		
		if("1".equals(saleType)){
			//品牌款
			Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();//放置活动中的商品
			Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
			paramsActivityMap.put("productId", productId);
			List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				for (ActivityCustom activityCustom : activityCustoms) {
					if(activityCustom.getProductId().equals(productId)){
						activityMap.put(activityCustom.getProductId().toString(), activityCustom);
					}
				}
			}
			ActivityCustom activityCustom = activityMap.get(productId.toString());
			if(activityCustom == null){
				activityType = "101";
				if("0".equals(shopStatus)){
					throw new ArgException("该商品已经卖光啦，再看看其他商品吧~");
				}
			}else{
				activityType = "0";
				activityId = activityCustom.getId();
			}
		}else if("2".equals(saleType)){
			//单品款
			SingleProductActivity singleProductActivity=null;
			if(reqDataJson.containsKey("activityType") && !StringUtil.isBlank(activityType) && !StringUtil.isBlank(reqDataJson.getString("singleProductActivityId"))){
				activityType = reqDataJson.getString("activityType");
				if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_EXPLOSION)
						|| activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)
						|| activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_BROKEN_CODE_CLEARING)
						|| activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_COUPON_PRODUCT)){
					singleProductActivityId = reqDataJson.getString("singleProductActivityId");
					if(StringUtil.isBlank(singleProductActivityId)){
						throw new ArgException("单品活动id不能为空");
					}
					singleProductActivity=singleProductActivityService.selectByPrimaryKey(Integer.valueOf(singleProductActivityId));
				}
			}else{
				singleProductActivity = singleProductActivityService.findModelByProductId(Integer.valueOf(productId));
			}
			if(singleProductActivity != null){
				singleProductActivityId = singleProductActivity.getId().toString();
				activityType = singleProductActivity.getType();
				Date beginTime = singleProductActivity.getBeginTime();
				Date endTime = singleProductActivity.getEndTime();
				if(currentDate.before(beginTime)){
					throw new ArgException("活动未开始");
				}
				if(currentDate.after(endTime)){
					throw new ArgException("活动已结束");
				}
			}else{
				throw new ArgException("该商品已经卖光啦，再看看其他商品吧~");
			}
		}else {
			throw new ArgException("该商品已经卖光啦，再看看其他商品吧~");
		}
		
		//params 获取已购买数量和获取购物车购买数量 一起复用
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberId);
		params.put("productId", productId);
		Integer productBuyNum = 0;
		//获取添加购物车的件数,20180711去除 需求676
		ProductCustom productCustom = productService.getUserBuyCount(params,activityType);
		ProductItem productItem = productItemService.selectByPrimaryKey(productItemId);
		if (productItem == null || !productId.equals(productItem.getProductId())) {
			throw new ArgException("系统繁忙，请稍后重试！");
		}
		
		ShoppingCart shoppingCart;
		if(activityType.equals("0")){
			if (!JsonUtils.isEmpty(reqDataJson, "activityId")) {
				activityId = Integer.valueOf(reqDataJson.getString("activityId"));
			}else{
				throw new ArgException("添加购物车失败");
			}
		}
		
		ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
		ShoppingCartExample.Criteria shoppingCartCriteria=shoppingCartExample.createCriteria();
		shoppingCartCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andMemberIdEqualTo(memberId).andProductItemIdEqualTo(productItemId).andActivityTypeEqualTo(activityType);
		if(activityId!=null){
			shoppingCartCriteria.andActivityIdEqualTo(activityId);
		}else{
			shoppingCartCriteria.andActivityIdIsNull();
		}
		
		List<ShoppingCart> shoppingCarts = shoppingCartService.selectByExample(shoppingCartExample);
		
		if (CollectionUtil.isNotEmpty(shoppingCarts)) {
			shoppingCart = shoppingCarts.get(0);
			params.put("activityId", shoppingCart.getActivityId());
			Integer shoppingCartProductNum = 0;
			//获取添加购物车的件数
			//shoppingCartProductNum = productService.getShoppingCartProductNum(params, activityType);
			if(productCustom != null){
				productBuyNum = productCustom.getProductBuyNum() == null ? 0 : productCustom.getProductBuyNum();
			}else{
				throw new ArgException("添加购物车失败");
			}
			
			//limitBuy = 0 不做限购判断
			if(productBuyNum+shoppingCartProductNum+quantity > limitBuy && limitBuy != 0){
				throw new ArgException("每人限购"+limitBuy+"件");
			}else{
				params.put("productItemId", shoppingCart.getProductItemId());
				shoppingCartProductNum = productService.getShoppingCartProductNum(params, activityType);
				Integer stock = productItem.getStock();//库存
				Integer lockedAmount = productItem.getLockedAmount();//冻结
				stock = stock - lockedAmount;
				if(quantity+shoppingCartProductNum > stock){
					throw new ArgException("库存不足");
				}
			}
			
			shoppingCart.setQuantity(shoppingCart.getQuantity().intValue() + quantity);
			shoppingCart.setUpdateBy(memberId);
			shoppingCart.setUpdateDate(new Date());
			shoppingCartService.updateByPrimaryKeySelective(shoppingCart);
		} else {
			shoppingCart = new ShoppingCart();
			if(activityType.equals("0")){
				if (activityId!=null) {
					activityId = Integer.valueOf(reqDataJson.getString("activityId"));
					Activity activity = activityService.selectByPrimaryKey(activityId);
					shoppingCart.setActivityId(activityId);
					shoppingCart.setActivityAreaId(activity.getActivityAreaId());
					params.put("activityId", shoppingCart.getActivityId());
				}else{
					throw new ArgException("添加购物车失败");
				}
			}else{
				//单品
				if(!StringUtil.isBlank(singleProductActivityId)){
					shoppingCart.setSingleProductActivityId(Integer.valueOf(singleProductActivityId));
				}
			}
			Integer shoppingCartProductNum = 0;
			
			if(productCustom != null){
				productBuyNum = productCustom.getProductBuyNum() == null ? 0 : productCustom.getProductBuyNum();
			}else{
				throw new ArgException("添加购物车失败");
			}
			//获取添加购物车的件数,20180711去除 需求676
			//shoppingCartProductNum = productService.getShoppingCartProductNum(params,activityType);
			
			Integer mchtId = pc.getMchtId();
			//limitBuy = 0 不做限购判断
			if(productBuyNum+quantity+shoppingCartProductNum > limitBuy && limitBuy != 0){
				throw new ArgException("每人限购"+limitBuy+"件");
			}else{
				Integer stock = productItem.getStock();//库存
				Integer lockedAmount = productItem.getLockedAmount();//冻结
				stock = stock - lockedAmount;
				if(quantity > stock && !activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){//助力大减价在领取任务的时候已经冻结库存了，在这边就不用在判断是否不足
					throw new ArgException("库存不足");
				}
			}
			String status = "0";
			
			if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY)
					||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)
					||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)
					||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL)
					||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)){
				status = "1";
			}
			shoppingCart.setProductItemId(productItemId);
			if(activityType.equals("101")){
				shoppingCart.setAddSalePrice(productItem.getMallPrice());
			}else{
				shoppingCart.setAddSalePrice(productItem.getSalePrice());
			}
			shoppingCart.setAddTagPrice(productItem.getTagPrice());
			shoppingCart.setMemberId(memberId);
			shoppingCart.setMchtId(mchtId);
			shoppingCart.setCreateBy(memberId);
			shoppingCart.setCreateDate(new Date());
			shoppingCart.setDelFlag("0");
			shoppingCart.setQuantity(quantity);
			shoppingCart.setStatus(status);
			shoppingCart.setActivityType(activityType);
			insertSelective(shoppingCart);
		}
		return shoppingCart;
	}


	public void editShoppingCart(JSONObject reqDataJson, Integer memberId) throws ArgException {
		String type = reqDataJson.getString("type");
		Integer shoppingCartId = Integer.valueOf(reqDataJson.getString("shoppingCartId"));
		String activityType = "0";
		if(reqDataJson.containsKey("activityType")){
			activityType = reqDataJson.getString("activityType");
		}
		ShoppingCart shoppingCart;
		ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
		shoppingCartExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andMemberIdEqualTo(memberId).andIdEqualTo(shoppingCartId);
		List<ShoppingCart> shoppingCarts = shoppingCartService.selectByExample(shoppingCartExample);
		if (shoppingCarts == null || shoppingCarts.size() == 0) {
			throw new ArgException("购物车商品不存在");
		}

		shoppingCart = shoppingCarts.get(0);

		if ("1".equals(shoppingCart.getStatus())) {
			throw new ArgException("购物车商品已经下单");
		}
		if ("1".equals(type)) {// 改变数量
			Integer quantity = Integer.valueOf(reqDataJson.getString("quantity"));
			
			if(quantity > 0){
				
				ProductItem productItem = productItemService.selectByPrimaryKey(shoppingCart.getProductItemId());
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("memberId", memberId);
				params.put("productId", productItem.getProductId());
				params.put("activityId", shoppingCart.getActivityId());
				//获取添加购物车的件数
				ProductCustom productCustom = productService.getUserBuyCount(params,activityType);
				//单品活动限购控制
				if(shoppingCart.getSingleProductActivityId()!=null){
					SingleProductActivity singleProductActivity=singleProductActivityService.selectByPrimaryKey(shoppingCart.getSingleProductActivityId());
					activityType=singleProductActivity.getType();
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
								
								if(quantity+userHasBuyCount+userShoppingCartCount>activityLimitBuy){
									Integer limitb = activityLimitBuy;
									if(productCustom.getLimitBuy() > 0 && activityLimitBuy > productCustom.getLimitBuy()){
										limitb = productCustom.getLimitBuy();
									}
									throw new ArgException("每人限购"+limitb+"件");
								}
							}
						}
					}
				}
				
				
				
				
				
				
				Integer shoppingCartProductNum = 0;
				Integer limitBuy = 0;
				//20180711 去除  需求676
				//shoppingCartProductNum = productService.getShoppingCartProductNum(params,activityType);
				Integer productBuyNum = 0;
				if(productCustom != null){
					limitBuy = productCustom.getLimitBuy() == null ? 0 : productCustom.getLimitBuy();
					productBuyNum = productCustom.getProductBuyNum() == null ? 0 : productCustom.getProductBuyNum();
				}else{
					throw new ArgException("异常数据");
				}
				if(shoppingCart.getQuantity() >=limitBuy-productBuyNum && limitBuy != 0){
					if(productBuyNum > 0){
						throw new ArgException("每人限购"+limitBuy+"件,您已购买"+productBuyNum+"件");
					}else{
						throw new ArgException("每人限购"+limitBuy+"件");
					}
				}else{
					params.put("productItemId", shoppingCart.getProductItemId());
					shoppingCartProductNum = productService.getShoppingCartProductNum(params,activityType);
					Integer stock = productItem.getStock();//库存
					Integer lockedAmount = productItem.getLockedAmount();//冻结
					stock = stock - lockedAmount;
					if(shoppingCartProductNum+quantity > stock){
						throw new ArgException("库存不足");
					}
				}
			}
			
			shoppingCart.setQuantity(shoppingCart.getQuantity().intValue() + quantity);
		}
		if ("2".equals(type)) {// 直接删除购物车
			shoppingCart.setDelFlag("1");
		}
		if (shoppingCart.getQuantity().intValue() <= 0) {// 购物车数量为0，则删除
			shoppingCart.setDelFlag("1");
		}
		//过滤预售商品购物车不能删除
		if("0".equals(activityType)) {
			//获取该商品有几张定金券
			SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
			subDepositOrderExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(shoppingCart.getMchtId())
				.andMemberIdEqualTo(shoppingCart.getMemberId())
				.andProductItemIdEqualTo(shoppingCart.getProductItemId())
				.andStatusIn(Arrays.asList("2", "8")); //2 定金已付   8 尾款下单后未支付取消
			List<SubDepositOrder> subDepositOrderList = subDepositOrderMapper.selectByExample(subDepositOrderExample);
			if(subDepositOrderList.size() > shoppingCart.getQuantity()) {
				throw new ArgException("已支付定金的预付商品不可删除");
			}
		}
		shoppingCart.setUpdateBy(memberId);
		shoppingCart.setUpdateDate(new Date());
		shoppingCartService.updateByPrimaryKeySelective(shoppingCart);
		
	}

	public List<ShoppingCartCustom> getShopCars(Map<String, Object> cartMap) {
		
		return shoppingCartCustomMapper.getShopCars(cartMap);
	}

	public void updateShopCartActivityInfo(Map<String, Object> activityInfoMap) {
		// TODO Auto-generated method stub
		shoppingCartCustomMapper.updateShopCartActivityInfo(activityInfoMap);
	}

	public void delShoppingCartList(JSONObject reqDataJson, JSONObject reqPRM, Integer memberId) throws ArgException {
		Date date = new Date();
		
		//获取该商品是否为预售商品
		SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
		subDepositOrderExample.createCriteria().andDelFlagEqualTo("0")
			.andMemberIdEqualTo(memberId)
			.andStatusIn(Arrays.asList("2", "8")); //2 定金已付   8 尾款下单后未支付取消
		List<SubDepositOrder> subDepositOrderList = subDepositOrderMapper.selectByExample(subDepositOrderExample);
		if(subDepositOrderList != null && subDepositOrderList.size() > 0) { //存在预售商品定金
			//不能删除的购物车ID
			List<Integer> shoppingCartIdList = new ArrayList<Integer>();
			//过滤预售商品购物车不能删除
			ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
			shoppingCartExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("0").andMemberIdEqualTo(memberId);
			List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectByExample(shoppingCartExample);
			for(ShoppingCart shoppingCart : shoppingCartList) {
				for(SubDepositOrder subDepositOrder : subDepositOrderList) {
					if(shoppingCart.getMchtId().intValue() == subDepositOrder.getMchtId().intValue()
							&& shoppingCart.getProductItemId().intValue() == subDepositOrder.getProductItemId().intValue()) {
						shoppingCartIdList.add(shoppingCart.getId());
					}
				}
			}
			ShoppingCartExample delShoppingCartExample = new ShoppingCartExample();
			ShoppingCartExample.Criteria delShoppingCartCriteria = delShoppingCartExample.createCriteria();
			delShoppingCartCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andMemberIdEqualTo(memberId);
			if(shoppingCartIdList.size() > 0) {
				delShoppingCartCriteria.andIdNotIn(shoppingCartIdList);
			}
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setDelFlag("1");
			shoppingCart.setUpdateDate(date);
			shoppingCartMapper.updateByExampleSelective(shoppingCart, delShoppingCartExample);
		}else { //不存在预售商品定金
			ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
			shoppingCartExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusEqualTo("0").andMemberIdEqualTo(memberId);
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setDelFlag("1");
			shoppingCart.setUpdateDate(date);
			shoppingCartMapper.updateByExampleSelective(shoppingCart, shoppingCartExample);
		}
		
	}
	
}
