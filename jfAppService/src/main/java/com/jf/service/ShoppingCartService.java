package com.jf.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ShoppingCartCustomMapper;
import com.jf.dao.ShoppingCartMapper;
import com.jf.dao.SubDepositOrderMapper;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemExample;
import com.jf.entity.ShoppingCart;
import com.jf.entity.ShoppingCartCustom;
import com.jf.entity.ShoppingCartExample;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderExample;

@Service
@Transactional
public class ShoppingCartService extends BaseService<ShoppingCart, ShoppingCartExample> {
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	private ShoppingCartCustomMapper shoppingCartCustomMapper;
	
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
	private CommonService commonService;
	
	
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

	public ShoppingCart addShoppingCart(JSONObject reqDataJson, JSONObject reqPRM){
		Integer memberId = Integer.valueOf(reqDataJson.getString("memberId"));
		Integer quantity = Integer.valueOf(reqDataJson.getString("quantity"));
		Integer productId = Integer.valueOf(reqDataJson.getString("productId"));
		String system = reqPRM.getString("system");
		Integer version = reqPRM.getInt("version");
		String oldProductItemId = "";//已存在购物车的规格id值，用来判断选择新的规格值是不是相同
		if(reqDataJson.containsKey("oldProductItemId") && !StringUtil.isBlank(reqDataJson.getString("oldProductItemId"))){
			oldProductItemId = reqDataJson.getString("oldProductItemId");
			ProductItem oldSku = productItemService.selectByPrimaryKey(Integer.parseInt(oldProductItemId));
			if (oldSku == null || !oldSku.getProductId().equals(productId)) {
				throw new ArgException("系统繁忙，请稍后重试！");
			}
		}
		ProductCustom pc = productService.getNormalOperationProduct(productId);//获取商品正常的运行状态信息
		if(pc == null || pc.getId() == null){
			throw new ArgException("该商品已经卖光啦，再看看其他商品吧~");
		}
		String isSingleProp = pc.getIsSingleProp();
		String saleType = pc.getSaleType();//销售类型\r\n1 品牌款 \r\n2 单品款
		Integer limitBuy = pc.getLimitBuy();
		Integer mchtId = pc.getMchtId();
		String shopStatus = pc.getShopStatus();//店铺状态(0关闭 1开通)
		String activityType = "0";
		String singleProductActivityId = "";
		Integer activityId = null;
		Integer productItemId=null;
		Date currentDate = new Date();
		Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();//放置活动中的商品
		ActivityCustom activityCustom = null;
		if(reqDataJson.containsKey("productItemId") 
				&& !StringUtil.isBlank(reqDataJson.getString("productItemId"))){
			productItemId=reqDataJson.getInt("productItemId");
			ProductItem newSku = productItemService.selectByPrimaryKey(productItemId);
			if (newSku == null || !newSku.getProductId().equals(productId)) {
				throw new ArgException("系统繁忙，请稍后重试！");
			}
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
			Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
			paramsActivityMap.put("productId", productId);
			List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				for (ActivityCustom ac : activityCustoms) {
					if(ac.getProductId().equals(productId)){
						activityMap.put(ac.getProductId().toString(), ac);
					}
				}
			}
			activityCustom = activityMap.get(productId.toString());
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
				activityType = singleProductActivity.getType();
				singleProductActivityId = singleProductActivity.getId().toString();
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
		limitBuy = productService.getProductLimitBuy(limitBuy, activityType, activityCustom);
		int subDepositOrderQuantity=0;
		if("0".equals(activityType)) {
			//获取该商品有几张定金券
			SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
			subDepositOrderExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(mchtId)
				.andMemberIdEqualTo(memberId)
				.andProductItemIdEqualTo(productItemId)
				.andStatusIn(Arrays.asList("2", "8")); //2 定金已付   8 尾款下单后未支付取消
			List<SubDepositOrder> subDepositOrderList = subDepositOrderMapper.selectByExample(subDepositOrderExample);
			if(subDepositOrderList != null && subDepositOrderList.size() > 0) {
				for(SubDepositOrder subDepositOrder:subDepositOrderList){
					subDepositOrderQuantity+=subDepositOrder.getQuantity();
				}
			}
		}
		//sku
		Integer stockSum = 0;
		ProductItem productItem = null;
		ProductItemExample productItemExample = new ProductItemExample();
		productItemExample.createCriteria().andIdEqualTo(productItemId).andDelFlagEqualTo("0");
		List<ProductItem> items = productItemService.selectByExample(productItemExample);
		if(CollectionUtils.isNotEmpty(items)){
			productItem = items.get(0);
			Integer stock = productItem.getStock();//库存
			Integer lockedAmount = productItem.getLockedAmount();//冻结
			stockSum = stock - lockedAmount + subDepositOrderQuantity;
			if(stockSum <= 0){
				throw new ArgException("该商品已经卖光啦，再看看其他商品吧~");
			}
		}
		ShoppingCart shoppingCart;
		ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
		ShoppingCartExample.Criteria shoppingCartCriteria=shoppingCartExample.createCriteria();
		shoppingCartCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0").andMemberIdEqualTo(memberId).andProductItemIdEqualTo(productItemId);
		if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)){
			shoppingCartCriteria.andActivityTypeIn(Arrays.asList(Const.PRODUCT_ACTIVITY_TYPE_AREA,Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP));
		}else{
			shoppingCartCriteria.andActivityTypeEqualTo(activityType);
		}
		List<ShoppingCart> shoppingCarts = shoppingCartService.selectByExample(shoppingCartExample);
		
		if (CollectionUtil.isNotEmpty(shoppingCarts)) {
			shoppingCart = shoppingCarts.get(0);
			Integer newQuantity = 0;
			if(oldProductItemId.equals(productItemId.toString())){
				newQuantity = quantity;
			}else{
				newQuantity = shoppingCart.getQuantity().intValue() + quantity;
			}
			Map<String, String> invalidPMap = productService.isInvalidProduct(productId, activityType, memberId, new Date(), null, null, activityMap, null, "4",newQuantity);
			if("1".equals(invalidPMap.get("isInvalidProduct"))){
				if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
					throw new ArgException(invalidPMap.get("limitErrorMsg"),ResponseMsg.ERROR_4005);
				}else{
					throw new ArgException(invalidPMap.get("limitErrorMsg"));
				}
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("memberId", memberId);
			params.put("productId", productId);
			params.put("productItemId", shoppingCart.getProductItemId());
			Integer shoppingCartProductNum = productService.getShoppingCartProductNum(params, activityType);
			if(quantity+shoppingCartProductNum > stockSum){
				throw new ArgException("库存不足，请重新编辑数量再操作哦~");
			}
			
			shoppingCart.setQuantity(newQuantity);
			shoppingCart.setUpdateBy(memberId);
			shoppingCart.setUpdateDate(currentDate);
			shoppingCartService.updateByPrimaryKeySelective(shoppingCart);
		} else {
			Map<String, String> invalidPMap = productService.isInvalidProduct(productId, activityType, memberId, new Date(), null, null, activityMap, null, "4",quantity);
			if("1".equals(invalidPMap.get("isInvalidProduct"))){
				if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
					throw new ArgException(invalidPMap.get("limitErrorMsg"),ResponseMsg.ERROR_4005);
				}else{
					throw new ArgException(invalidPMap.get("limitErrorMsg"));
				}
			}
			shoppingCart = new ShoppingCart();
			if(activityType.equals("0")){
				if (activityId!=null) {
					Activity activity = activityService.selectByPrimaryKey(activityId);
					shoppingCart.setActivityId(activityId);
					shoppingCart.setActivityAreaId(activity.getActivityAreaId());
				}else{
					throw new ArgException("该商品已经卖光啦，再看看其他商品吧~");
				}
			}else{
				//单品
				if(!StringUtil.isBlank(singleProductActivityId)){
					shoppingCart.setSingleProductActivityId(Integer.valueOf(singleProductActivityId));
				}
			}
			if(quantity > stockSum){
				throw new ArgException("库存不足，请重新编辑数量再操作哦~");
			}
			String status = "0";//0未下单 1已下单
			
			//秒杀，新用户秒杀不显示购物车
			if((version > 23 && system.equals(Const.ANDROID)) || (version > 23 && system.equals(Const.IOS))){
				//新版按本 
				if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY)||
						activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)
						||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)
						||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL)
						||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_COUPON_PRODUCT)){
					status = "1";
				}
				
			}else{
				if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)
						||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)
						||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL)){
					status = "1";
				}
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
			shoppingCart.setCreateDate(currentDate);
			shoppingCart.setDelFlag("0");
			shoppingCart.setQuantity(quantity);
			shoppingCart.setStatus(status);
			shoppingCart.setActivityType(activityType);
			insertSelective(shoppingCart);
		}
		return shoppingCart;
	}


	public void editShoppingCart(JSONObject reqDataJson, JSONObject reqPRM) throws ArgException {
		String type = reqDataJson.getString("type");
		Integer memberId = Integer.valueOf(reqDataJson.getString("memberId"));
		Integer shoppingCartId = Integer.valueOf(reqDataJson.getString("shoppingCartId"));
		String activityType = "0";
		if(reqDataJson.containsKey("activityType")){
			activityType = reqDataJson.getString("activityType");
		}
		String system = reqPRM.getString("system");
		Integer version = reqPRM.getInt("version");
		ShoppingCart shoppingCart;
		ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
		shoppingCartExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andMemberIdEqualTo(memberId).andIdEqualTo(shoppingCartId);
		List<ShoppingCart> shoppingCarts = shoppingCartService.selectByExample(shoppingCartExample);
		if (shoppingCarts == null || shoppingCarts.size() == 0) {
			throw new ArgException("购物车商品不存在");
		}
		shoppingCart = shoppingCarts.get(0);
		ProductItem productItem = productItemService.selectByPrimaryKey(shoppingCart.getProductItemId());
		activityType = shoppingCart.getActivityType();
		ActivityCustom activityCustom = null;
		Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();
		if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA)){
			ActivityArea activityArea = activityAreaService.selectByPrimaryKey(shoppingCart.getActivityAreaId());
			activityCustom = new ActivityCustom();
			activityCustom.setPurchaseLimits(activityArea.getPurchaseLimits());
			activityCustom.setPurchaseLimitsQuantity(activityArea.getPurchaseLimitsQuantity());
			activityCustom.setSource(activityArea.getSource());
			activityMap.put(productItem.getProductId().toString(), activityCustom);
		}
		if ("1".equals(shoppingCart.getStatus())) {
			throw new ArgException("购物车商品已经下单");
		}
		//过滤预售商品购物车不能删除
		int subDepositOrderQuantity=0;
		if("0".equals(activityType)) {
			//获取该商品有几张定金券
			SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
			subDepositOrderExample.createCriteria().andDelFlagEqualTo("0")
				.andMchtIdEqualTo(shoppingCart.getMchtId())
				.andMemberIdEqualTo(shoppingCart.getMemberId())
				.andProductItemIdEqualTo(shoppingCart.getProductItemId())
				.andStatusIn(Arrays.asList("2", "8")); //2 定金已付   8 尾款下单后未支付取消
			List<SubDepositOrder> subDepositOrderList = subDepositOrderMapper.selectByExample(subDepositOrderExample);
			if(subDepositOrderList != null && subDepositOrderList.size() > 0) {
				for(SubDepositOrder subDepositOrder:subDepositOrderList){
					subDepositOrderQuantity+=subDepositOrder.getQuantity();
				}
				if("2".equals(type)){
					throw new ArgException("已支付定金的预付商品不可删除");
				}
			}
		}
		if ("1".equals(type)) {// 改变数量
			int quantity = reqDataJson.optInt("quantity", 0);

            if (quantity == 0) {
                throw new ArgException("请输入需要改变的数量");
            } else if (quantity > 0) {
                //限购判断
                Map<String, String> invalidPMap = productService.isInvalidProduct(productItem.getProductId(), activityType, memberId, new Date(), null, null, activityMap, null, "5", shoppingCart.getQuantity() + quantity);
                if ("1".equals(invalidPMap.get("isInvalidProduct"))) {
                    int productBuyNum = Integer.parseInt(invalidPMap.get("productBuyNum"));
                    String limitErrorMsg = "";
                    if (productBuyNum > 0) {
                        limitErrorMsg = "每人限购" + invalidPMap.get("limitBuy") + "件,您已购买" + productBuyNum + "件";
                    } else {
                        limitErrorMsg = "每人限购" + invalidPMap.get("limitBuy") + "件";
                    }
                    throw new ArgException(limitErrorMsg);
                }
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("memberId", memberId);
                params.put("productId", productItem.getProductId());
                params.put("activityId", shoppingCart.getActivityId());
                params.put("productItemId", shoppingCart.getProductItemId());
                int shoppingCartProductNum = productService.getShoppingCartProductNum(params, activityType);
                Integer stock = productItem.getStock();//库存
                Integer lockedAmount = productItem.getLockedAmount();//冻结

                stock = stock - lockedAmount + subDepositOrderQuantity;
                if (shoppingCartProductNum + quantity > stock) {
                    throw new ArgException("库存不足");
                }
            } else {
				if (subDepositOrderQuantity > 0 && (shoppingCart.getQuantity() + quantity < subDepositOrderQuantity)) {
					throw new ArgException("已支付定金的预付商品不可删除");
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

		shoppingCart.setUpdateBy(memberId);
		shoppingCart.setUpdateDate(new Date());
		shoppingCartService.updateByPrimaryKeySelective(shoppingCart);

	}

	public List<ShoppingCartCustom> getFullCutData(Map<String, Object> params) {
		
		return shoppingCartCustomMapper.getFullCutData(params);
	}

	public List<ShoppingCartCustom> getShopCars(Map<String, Object> cartMap) {
		// TODO Auto-generated method stub
		return shoppingCartCustomMapper.getShopCars(cartMap);
	}

	public void updateShopCartActivityInfo(Map<String, Object> activityInfoMap) {
		
		shoppingCartCustomMapper.updateShopCartActivityInfo(activityInfoMap);
	}
	
	/**
	 * 
	 * @Title delShoppingCartList   
	 * @Description TODO(清空购物车)   
	 * @author pengl
	 * @date 2018年9月3日 下午2:51:16
	 */
	public void delShoppingCartList(JSONObject reqDataJson, JSONObject reqPRM) throws ArgException {
		Date date = new Date();
		Integer memberId = Integer.valueOf(reqDataJson.getString("memberId"));
		
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
