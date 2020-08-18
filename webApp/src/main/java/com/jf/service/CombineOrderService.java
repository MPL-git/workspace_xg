package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.BaseDefine;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.NumberUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CombineOrderExtendMapper;
import com.jf.dao.CombineOrderMapper;
import com.jf.dao.MemberAddressCustomMapper;
import com.jf.dao.OrderDtlExtendMapper;
import com.jf.dao.SubOrderExtendMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderExample;
import com.jf.entity.CombineOrderExtend;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;
import com.jf.entity.MemberAddressCustom;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberNovaRecord;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlExtend;
import com.jf.entity.Product;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductExample;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemExample;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExtend;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月27日 上午10:57:54 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CombineOrderService extends BaseService<CombineOrder, CombineOrderExample> {
	private static Logger logger = LoggerFactory.getLogger(CombineOrderService.class);
	@Autowired
	private CombineOrderMapper combineOrderMapper;
	@Autowired
	private SubOrderService subOrderService;
	@Autowired
	private OrderDtlService orderDtlService;
	@Autowired
	private MchtInfoService mchtInfoService;
	@Autowired
	private MchtProductBrandService mchtProductBrandService;
	@Autowired
	private DeliveryOvertimeCnfService deliveryOvertimeCnfService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductItemService productItemService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductBrandService productBrandService;
	@Autowired
	private MemberAddressCustomMapper memberAddressCustomMapper;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private CloudProductItemService cloudProductItemService;
	@Autowired
	private PayService payService;
	@Autowired
	private DouyinSprDtlService douYinSprDtlService;
	@Autowired
	private MemberNovaRecordService memberNovaRecordService;
	@Autowired
	private CombineOrderExtendMapper combineOrderExtendMapper;
	@Autowired
	private SubOrderExtendMapper subOrderExtendMapper;
	@Autowired
	private OrderDtlExtendMapper orderDtlExtendMapper;

	@Autowired
	public void setCombineOrderMapper(CombineOrderMapper combineOrderMapper) {
		this.setDao(combineOrderMapper);
		this.combineOrderMapper = combineOrderMapper;
	}

	public void addSignInOrder(Date date, HttpServletRequest request, Integer memberId, String type, String productCode) {
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		Integer productItemId = null;
		ProductItem item = null;
		String sourceClient = "";
		ProductExample productExample = new ProductExample();
		productExample.createCriteria().andCodeEqualTo(productCode).andDelFlagEqualTo("0");
		List<Product> products = productService.selectByExample(productExample);
		if(CollectionUtils.isEmpty(products)){
			throw new ArgException("商品已下架");
		}
		Product p = products.get(0);
		Integer productId = p.getId();
		if(reqDataJson.containsKey("productItemId")&&!StringUtil.isEmpty(reqDataJson.getString("productItemId"))){
			productItemId=reqDataJson.getInt("productItemId");
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
		if(!reqDataJson.containsKey("addressId") && StringUtil.isEmpty(reqDataJson.getString("addressId"))){
			throw new ArgException("请选择地址");
		}
		Integer addressId=reqDataJson.getInt("addressId");
		Map<String,Object> addressParamsMap = new HashMap<String,Object>();
		addressParamsMap.put("memberId", memberId);
		addressParamsMap.put("addressId", addressId);
		MemberAddressCustom memberAddressCustom = memberAddressCustomMapper.getAddressById(addressParamsMap);
		String address = memberAddressCustom.getFullAddressName();
		if(reqDataJson.containsKey("sourceClient")&&!StringUtil.isEmpty(reqDataJson.getString("sourceClient"))){
			sourceClient=reqDataJson.getString("sourceClient");
		}
		MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(BaseDefine.MEMBER_INFO);
		if(memberInfo == null){
			memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		}
		Integer cloudProductItemId = item.getCloudProductItemId();
		BigDecimal sellingPrice = new BigDecimal("0");
		ProductBrand brand = productBrandService.selectByPrimaryKey(p.getBrandId());
		//获取sku描述
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("productItemId", productItemId);
		paramsMap.put("mark", "-");
		String productPropDesc = productItemService.getProductItemPropDesc(paramsMap);
		BigDecimal zero = new BigDecimal("0");
		BigDecimal popFeeRate = new BigDecimal("0");
		String ip = StringUtil.getIpAddr(request);
		if(!StringUtil.isBlank(ip)){
			if(ip.trim().length() > 16 || ip.trim().length()==0){
				ip = "116.62.146.101";
			}
		}else{
			ip = "116.62.146.101";
		}
		
		//下单完成，库存-1
		if("1".equals(type)){
			return;
		}else{
			Map<String, Object> cloudMap = productItemService.updateSkuLockedAmount(productItemId,1,p.getMchtId(),"1");
			if((boolean) cloudMap.get("isCooperation")){
				if(cloudMap.get("sellingPrice") != null){
					sellingPrice = new BigDecimal(cloudMap.get("sellingPrice").toString());
				}
			}else{
				cloudProductItemId = null;
			}
		}
		String orderExtraInfo = orderService.getExtraInfo(reqPRM, reqDataJson, ip);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(p.getMchtId());
		MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
		mchtProductBrandExample.createCriteria().andMchtIdEqualTo(p.getMchtId())
		.andProductBrandIdEqualTo(p.getBrandId()).andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mchtProductBrandExample);
		if(CollectionUtils.isNotEmpty(mchtProductBrands)){
			popFeeRate = mchtProductBrands.get(0).getPopCommissionRate();
		}else{
			throw new ArgException("商品库存不足");
		}
		
		//查找物流发货时间，找不到默认为当前时间+48H(承诺发货时间的取值判断顺序：1、判断特殊地区  2、 判断通用的  3、用NOW+48小时)
		
		Map<String,Object> dateCnfMap = deliveryOvertimeCnfService.getDeliveryOvertimeCnf(address);
		
		BigDecimal tagPrice = item.getTagPrice();
		BigDecimal salePrice = item.getSalePrice();
		BigDecimal platformPreferential = salePrice;
		BigDecimal mchtPreferential = zero;
		BigDecimal integralPreferential = zero;
		BigDecimal selfOperatedFreight = zero;
		//结算金额
		BigDecimal settleAmount = zero;
		BigDecimal commissionAmount = zero;
		//商家类型 1.spop 2.pop
		if(mchtInfo.getMchtType().equals(Const.MCHT_TYPE_POP)){
			//2:pop：
			//结算金额 = （成交价 * 数量 - 商家优惠 + 运费） * （1-pop佣金比率）
			settleAmount = salePrice.multiply(BigDecimal.ONE.subtract(popFeeRate));
			//服务费 = （成交价 * 数量 - 商家优惠 + 运费） * pop佣金比率
			commissionAmount = salePrice.multiply(popFeeRate);
		} else {
			if (StateConst.FALSE.equals(mchtInfo.getIsManageSelf())) {
				//1:spop：
				//结算金额 = 结算价 * 数量 - 商家优惠 + 运费
				settleAmount = item.getCostPrice();
				//服务费 = （醒购价 - 结算价）* 数量
				commissionAmount = salePrice.subtract(item.getCostPrice());
			} else {
				//3:自营spop：
				selfOperatedFreight = NumberUtil.get(item.getManageSelfFreight());
				//结算金额 = 结算价 * 数量 - 商家优惠 + 自营运费
				settleAmount = item.getCostPrice().add(selfOperatedFreight);
				//服务费 = （醒购价 - 结算价）* 数量 + 订单运费 - 自营运费
				commissionAmount = salePrice.subtract(item.getCostPrice()).subtract(selfOperatedFreight);
			}
		}
		CombineOrder combineOrder = new CombineOrder();
		String combineOrderCode = "XG"+CommonUtil.getOrderCode(); 
		String subOrderCode = "XGS"+CommonUtil.getOrderCode(); 
		combineOrder.setCombineOrderCode(combineOrderCode);
		combineOrder.setMemberId(memberId);
		combineOrder.setMemberNick(memberInfo.getNick());
		combineOrder.setAddressId(addressId);
		combineOrder.setReceiverName(memberAddressCustom.getRecipient());
		combineOrder.setReceiverPhone(memberAddressCustom.getPhone());
		combineOrder.setReceiverAddress(address);
		combineOrder.setSourceClient(sourceClient);
		combineOrder.setTotalAmount(salePrice);
		combineOrder.setTotalPayAmount(zero);
		combineOrder.setTotalPlatformPreferential(platformPreferential);
		combineOrder.setTotalMchtPreferential(mchtPreferential);
		combineOrder.setTotalIntegralPreferential(integralPreferential);
		combineOrder.setStatus(Const.COMBINE_ORDER_STATUS_PAID);
		combineOrder.setFinancialStatus(Const.COMBINE_ORDER_FINANCIAL_STATUS_NULL);
		combineOrder.setFinancialNo("KJ"+DateUtil.getFormatDate(date, "yyyyMMdd"));
		combineOrder.setPayDate(date);
		combineOrder.setPayStatus("1");
		combineOrder.setOrderType(Const.COMBINE_ORDER_SIGN_IN_ORDER);
		combineOrder.setOrderExtraInfo(orderExtraInfo);
		combineOrder.setCreateBy(memberId);
		combineOrder.setCreateDate(date);
		combineOrder.setDelFlag("0");
		insertSelective(combineOrder);
		CombineOrderExtend combineOrderExtend = buildCombineOrderExtend(combineOrder);
		combineOrderExtend.setManageSelfFreight(selfOperatedFreight);
		combineOrderExtendMapper.insert(combineOrderExtend);

		SubOrder subOrder = new SubOrder();
		subOrder.setCombineOrderId(combineOrder.getId());
		subOrder.setSubOrderCode(subOrderCode);
		subOrder.setMchtId(p.getMchtId());
		subOrder.setMchtType(mchtInfo.getMchtType());
		subOrder.setIsManageSelf(mchtInfo.getIsManageSelf());
		subOrder.setAmount(salePrice);
		subOrder.setPayAmount(zero);
		subOrder.setPlatformPreferential(platformPreferential);
		subOrder.setMchtPreferential(mchtPreferential);
		subOrder.setIntegralPreferential(integralPreferential);
		subOrder.setStatus(Const.ORDER_STATUS_PAID);
		if(dateCnfMap.get("deliveryOvertime") != null){
			subOrder.setDeliveryOvertime(Integer.valueOf(dateCnfMap.get("deliveryOvertime").toString()));
		}
		subOrder.setDeliveryLastDate((Date)dateCnfMap.get("deliveryDate"));
		subOrder.setCreateBy(memberId);
		subOrder.setCreateDate(date);
		subOrder.setDelFlag("0");
		subOrderService.insertSelective(subOrder);
		SubOrderExtend subOrderExtend = buildSubOrderExtend(subOrder);
		subOrderExtend.setManageSelfFreight(selfOperatedFreight);
		subOrderExtendMapper.insert(subOrderExtend);

		OrderDtl orderDtl = new OrderDtl();
		orderDtl.setSubOrderId(subOrder.getId());
		orderDtl.setProductId(productId);
		orderDtl.setProductItemId(productItemId);
//		orderDtl.setSingleProductActivityId(cutPriceOrder.getSingleProductActivityId());
		orderDtl.setSkuPic(item.getPic());
		orderDtl.setSku(item.getSku());
		orderDtl.setProductName(p.getName());
		orderDtl.setArtNo(p.getArtNo());
		orderDtl.setBrandName(brand.getName());
		orderDtl.setProductPropDesc(productPropDesc);
		orderDtl.setQuantity(1);
		orderDtl.setIsGive("1");
		orderDtl.setTagPrice(tagPrice);
		orderDtl.setSalePrice(salePrice);
		orderDtl.setPayAmount(zero);
		orderDtl.setPopCommissionRate(popFeeRate);
		orderDtl.setPlatformPreferential(platformPreferential);
		orderDtl.setMchtPreferential(mchtPreferential);
		orderDtl.setIntegralPreferential(integralPreferential);
		orderDtl.setSettleAmount(settleAmount);
		orderDtl.setCommissionAmount(commissionAmount);
		orderDtl.setCloudProductItemId(cloudProductItemId);
		orderDtl.setSellingPrice(sellingPrice);
		orderDtl.setCreateBy(memberId);
		orderDtl.setCreateDate(date);
		orderDtl.setDelFlag("0");
		orderDtlService.insertSelective(orderDtl);
		OrderDtlExtend orderDtlExtend = buildOrderDtlExtend(orderDtl);
		orderDtlExtend.setManageSelfFreight(selfOperatedFreight);
		orderDtlExtendMapper.insert(orderDtlExtend);
		productItemService.updateProductSkuStock(productItemId, 1, orderDtl.getCloudProductItemId());
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

	public boolean getMemberOrderPaySuccess(Integer memberId, JSONObject reqDataJson, JSONObject reqPRM) {
		String system = reqPRM.getString("system");
		Integer combineOrderId = reqDataJson.getInt("combineOrderId");//总订单id
		logger.info("抖音"+system+"请求参数:"+reqPRM);
		boolean isPaySuccess = false;
		if(!Const.H5_DY.equals(system)){
			return isPaySuccess;
		}
		CombineOrder combineOrder = selectByPrimaryKey(combineOrderId);
		if(combineOrder != null && combineOrder.getId() != null){
			if(Const.COMBINE_DEPOSIT_ORDER_STATUS_PAID.equals(combineOrder.getStatus())){
				isPaySuccess = true;
			}else{
				isPaySuccess = payService.queryWxOrAliPaySuccess(combineOrder);
			}
		}
//		if(isPaySuccess){
//			Integer adSprId = null;
//			if(reqDataJson.containsKey("adSprId") && !StringUtil.isBlank(reqDataJson.getString("adSprId"))){
//				adSprId = reqDataJson.getInt("adSprId");//广告计划id
//				douYinSprDtlService.addModel(adSprId,combineOrderId,memberId);
//			}
//		}
		return isPaySuccess;
	}

	public int getMemberSubOrderCount(MemberInfo memberInfo, Integer memberId, String openType) {
		int count = 0;
		if(memberInfo == null){
			memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		}
		MemberNovaRecord memberNovaRecord = memberNovaRecordService.getCurrentContractSigning(memberInfo.getId(),openType);
		if(memberNovaRecord != null){
			Date novaProjectBeginDate = memberNovaRecord.getAgreementBeginDate();
			Date novaProjectEndDate = memberNovaRecord.getAgreementEndDate();
			//找出会员的下级
			List<Integer> memberIds = memberInfoService.getMemberSubUser(memberId);
			if(CollectionUtils.isNotEmpty(memberIds)){
				CombineOrderExample combineOrderExample = new CombineOrderExample();
				combineOrderExample.createCriteria().andMemberIdIn(memberIds).andStatusEqualTo("1").andPayDateGreaterThanOrEqualTo(novaProjectBeginDate)
				.andPayDateLessThanOrEqualTo(new Date()).andDelFlagEqualTo("0");
				count = countByExample(combineOrderExample);
			}
		}
		return count;
	}

	public CombineOrder getMemberOrder(Integer memberId, Integer combineOrderId) {
		CombineOrderExample example = new CombineOrderExample();
		example.createCriteria()
				.andIdEqualTo(combineOrderId)
				.andMemberIdEqualTo(memberId);
		List<CombineOrder> list = this.selectByExample(example);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}
}
