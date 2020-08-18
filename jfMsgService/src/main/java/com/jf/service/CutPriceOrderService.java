package com.jf.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import com.jf.common.utils.NumberUtil;
import com.jf.dao.CombineOrderExtendMapper;
import com.jf.dao.OrderDtlExtendMapper;
import com.jf.dao.SubOrderExtendMapper;
import com.jf.entity.CombineOrderExtend;
import com.jf.entity.OrderDtlExtend;
import com.jf.entity.SubOrderExtend;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DateUtil;
import com.jf.dao.CutPriceOrderCustomMapper;
import com.jf.dao.CutPriceOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CutPriceOrder;
import com.jf.entity.CutPriceOrderExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;
import com.jf.entity.OrderDtl;
import com.jf.entity.Product;
import com.jf.entity.ProductItem;
import com.jf.entity.SubOrder;

@Service
@Transactional
public class CutPriceOrderService extends BaseService<CutPriceOrder, CutPriceOrderExample> {
	private static Logger logger = LoggerFactory.getLogger(CutPriceOrderService.class);
	@Autowired
	private CutPriceOrderMapper cutPriceOrderMapper;
	@Autowired
	private CutPriceOrderCustomMapper cutPriceOrderCustomMapper;
	@Resource
    private CombineOrderService combineOrderService;
    @Resource
    private SubOrderService subOrderService;
    @Resource
    private OrderDtlService orderDtlService;
    @Resource
    private MchtInfoService mchtInfoService;
    @Resource
    private MchtProductBrandService mchtProductBrandService;
    @Resource
    private DeliveryOvertimeCnfService deliveryOvertimeCnfService;
    @Resource
    private ProductService productService;
    @Resource
    private ProductItemService productItemService;
    @Resource
	private CutPriceOrderLogService cutPriceOrderLogService;
	@Autowired
	private CombineOrderExtendMapper combineOrderExtendMapper;
	@Autowired
	private SubOrderExtendMapper subOrderExtendMapper;
	@Autowired
	private OrderDtlExtendMapper orderDtlExtendMapper;

	@Autowired
	public void setCutPriceOrderMapper(CutPriceOrderMapper cutPriceOrderMapper) {
		this.setDao(cutPriceOrderMapper);
		this.cutPriceOrderMapper = cutPriceOrderMapper;
	}
	public void getCutExpireOrder() {
		//砍价单24小时后过期单据
		Date currentDate = new Date();
		CutPriceOrderExample cutPriceOrderExample = new CutPriceOrderExample();
		cutPriceOrderExample.createCriteria().andStatusEqualTo("1").andCanOrderEqualTo("0").andActivityTypeEqualTo("7")
		.andDelFlagEqualTo("0").andCreateDateLessThanOrEqualTo(DateUtil.addHour(currentDate, -24));
		List<CutPriceOrder> cutPriceOrders = selectByExample(cutPriceOrderExample);
		if(CollectionUtils.isNotEmpty(cutPriceOrders)){
			for (CutPriceOrder cutPriceOrder : cutPriceOrders) {
				productService.updateSkuLockedAmount(cutPriceOrder.getProductItemId(),-1);
				cutPriceOrder.setStatus("3");
				cutPriceOrder.setRemarks("过期提醒");
				updateByPrimaryKeySelective(cutPriceOrder);
				//日志
				cutPriceOrderLogService.addCutPriceOrderLog(cutPriceOrder.getMemberId(), cutPriceOrder.getId(), "3", "砍价失败，过期提醒");
			}
		}
	}
	public void getCutInviteExpireOrder() {
		//砍价享免单6小时后过期单据
		Date currentDate = new Date();
		CutPriceOrderExample cutPriceOrderExample = new CutPriceOrderExample();
		cutPriceOrderExample.createCriteria().andCanOrderEqualTo("0").andStatusEqualTo("1").andActivityTypeEqualTo("8")
		.andDelFlagEqualTo("0").andCreateDateLessThanOrEqualTo(DateUtil.addHour(currentDate, -24));
		List<CutPriceOrder> cutPriceOrders = selectByExample(cutPriceOrderExample);
		if(CollectionUtils.isNotEmpty(cutPriceOrders)){
			for (CutPriceOrder cutPriceOrder : cutPriceOrders) {
				productService.updateSkuLockedAmount(cutPriceOrder.getProductItemId(),-1);
				cutPriceOrder.setStatus("3");
				cutPriceOrder.setRemarks("过期提醒");
				updateByPrimaryKeySelective(cutPriceOrder);
				//日志
				cutPriceOrderLogService.addCutPriceOrderLog(cutPriceOrder.getMemberId(), cutPriceOrder.getId(), "3", "砍价失败，过期提醒");
			}
		}
		
	}
	
	public synchronized void submitCutOrder() {
		Date date = new Date();
		BigDecimal zero = new BigDecimal("0");
		CutPriceOrderExample cutPriceOrderExample = new CutPriceOrderExample();
		cutPriceOrderExample.createCriteria().andStatusEqualTo("2").andCanOrderEqualTo("1")
		.andDelFlagEqualTo("0").andAuditStatusEqualTo("1").andActivityTypeIn(Arrays.asList("7","8")).andProductItemIdNotEqualTo(0);
		List<CutPriceOrder> cutPriceOrders = selectByExample(cutPriceOrderExample);
		if(CollectionUtils.isNotEmpty(cutPriceOrders)){
			for (CutPriceOrder cutPriceOrder : cutPriceOrders) {
				String activityType = cutPriceOrder.getActivityType();
				String financialNo = "";
				String orderType  = "";
				Integer quantity = cutPriceOrder.getQuantity();
				if("7".equals(activityType)){
					financialNo = "KJ"+DateUtil.getFormatDate(date, "yyyyMMdd");
					orderType = "3";
				}else{
					financialNo = "MDKJ"+DateUtil.getFormatDate(date, "yyyyMMdd");
					orderType = "4";
				}
				BigDecimal popFeeRate = new BigDecimal("0");
				Product product = productService.selectByPrimaryKey(cutPriceOrder.getProductId());
				//已下单，库存-1，冻结库存-1
				ProductItem item = productItemService.updateProductSkuStock(cutPriceOrder.getProductItemId(),cutPriceOrder.getQuantity());
				
				MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(product.getMchtId());
				String mchtType = mchtInfo.getMchtType();//商家类型 1.联营 2.pop
				if("2".equals(mchtType)){
					MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
					mchtProductBrandExample.createCriteria().andMchtIdEqualTo(product.getMchtId())
					.andProductBrandIdEqualTo(product.getBrandId()).andDelFlagEqualTo("0").andStatusEqualTo("1");
					List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mchtProductBrandExample);
					if(CollectionUtils.isNotEmpty(mchtProductBrands)){
						popFeeRate = mchtProductBrands.get(0).getPopCommissionRate();
					}else{
						continue;
					}
				}
				
				//吊牌价
				BigDecimal tagPrice = cutPriceOrder.getTagPrice();
				//商品销售单价（即用户购买价格）
				BigDecimal salePrice = cutPriceOrder.getSalePrice();
				//实付金额
				BigDecimal payAmount = zero;
				//结算价（联营使用）
				BigDecimal settlePrice = item.getCostPrice();
				//平台优惠
				BigDecimal platformPreferential = salePrice;
				//商家优惠
				BigDecimal mchtPreferential = zero;
				//积分优惠
				BigDecimal integralPreferential = zero;
				//自营运费
				BigDecimal selfOperatedFreight = BigDecimal.ZERO;
				//结算金额
				BigDecimal settleAmount = zero;
				BigDecimal commissionAmount = zero;
				if (Const.MCHT_TYPE_POP.equals(mchtType)) {
					//结算金额 = （成交价 * 数量 - 商家优惠 + 运费） * （1-pop佣金比率）
					settleAmount = salePrice.multiply(BigDecimal.ONE.subtract(popFeeRate));
					//服务费 = （成交价 * 数量 - 商家优惠 + 运费） * pop佣金比率
					commissionAmount = salePrice.multiply(popFeeRate);
				} else {
					if ("0".equals(mchtInfo.getIsManageSelf())) {
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
				
				//查找物流发货时间，找不到默认为当前时间+48H
				Map<String,Object> dateCnfMap = deliveryOvertimeCnfService.getDeliveryOvertimeCnf();
				CombineOrder combineOrder = new CombineOrder();
				String combineOrderCode = "XG"+CommonUtil.getOrderCode(); 
				String subOrderCode = "XGS"+CommonUtil.getOrderCode(); 
				combineOrder.setCombineOrderCode(combineOrderCode);
				combineOrder.setMemberId(cutPriceOrder.getMemberId());
				combineOrder.setMemberNick(cutPriceOrder.getMemberNick());
				combineOrder.setAddressId(cutPriceOrder.getAddressId());
				combineOrder.setReceiverName(cutPriceOrder.getReceiverName());
				combineOrder.setReceiverPhone(cutPriceOrder.getReceiverPhone());
				combineOrder.setReceiverAddress(cutPriceOrder.getReceiverAddress());
				combineOrder.setTotalAmount(salePrice);
				combineOrder.setTotalPayAmount(payAmount);
				combineOrder.setTotalMchtPreferential(mchtPreferential);
				combineOrder.setTotalPlatformPreferential(platformPreferential);
				combineOrder.setTotalIntegralPreferential(integralPreferential);
				combineOrder.setStatus(Const.COMBINE_ORDER_STATUS_PAID);
				combineOrder.setFinancialStatus(Const.COMBINE_ORDER_FINANCIAL_STATUS_NULL);
				combineOrder.setFinancialNo(financialNo);
				combineOrder.setPayDate(date);
				combineOrder.setPayStatus("1");
				combineOrder.setOrderType(orderType);
				combineOrder.setSourceClient(cutPriceOrder.getSourceClient());
				combineOrder.setCreateBy(cutPriceOrder.getMemberId());
				combineOrder.setCreateDate(date);
				combineOrder.setDelFlag("0");
				combineOrderService.insertSelective(combineOrder);
				CombineOrderExtend combineOrderExtend = buildCombineOrderExtend(combineOrder);
				combineOrderExtend.setManageSelfFreight(selfOperatedFreight);
				combineOrderExtendMapper.insert(combineOrderExtend);
				
				SubOrder subOrder = new SubOrder();
				subOrder.setCombineOrderId(combineOrder.getId());
				subOrder.setSubOrderCode(subOrderCode);
				subOrder.setMchtId(product.getMchtId());
				subOrder.setMchtType(mchtInfo.getMchtType());
				subOrder.setIsManageSelf(mchtInfo.getIsManageSelf());
				subOrder.setAmount(salePrice);
				subOrder.setPayAmount(payAmount);
				subOrder.setMchtPreferential(mchtPreferential);
				subOrder.setPlatformPreferential(platformPreferential);
				subOrder.setIntegralPreferential(integralPreferential);
				subOrder.setStatus(Const.ORDER_STATUS_PAID);
				if(dateCnfMap.get("deliveryOvertime") != null){
					subOrder.setDeliveryOvertime(Integer.valueOf(dateCnfMap.get("deliveryOvertime").toString()));
				}
				subOrder.setDeliveryLastDate((Date)dateCnfMap.get("deliveryDate"));
				subOrder.setCreateBy(cutPriceOrder.getMemberId());
				subOrder.setCreateDate(date);
				subOrder.setDelFlag("0");
				subOrderService.insertSelective(subOrder);
				SubOrderExtend subOrderExtend = buildSubOrderExtend(subOrder);
				subOrderExtend.setManageSelfFreight(selfOperatedFreight);
				subOrderExtendMapper.insert(subOrderExtend);
				
				OrderDtl orderDtl = new OrderDtl();
				orderDtl.setSubOrderId(subOrder.getId());
				orderDtl.setProductId(cutPriceOrder.getProductId());
				orderDtl.setProductItemId(cutPriceOrder.getProductItemId());
				orderDtl.setSingleProductActivityId(cutPriceOrder.getSingleProductActivityId());
				orderDtl.setSkuPic(cutPriceOrder.getSkuPic());
				orderDtl.setSku(item.getSku());
				orderDtl.setProductName(cutPriceOrder.getProductName());
				orderDtl.setArtNo(cutPriceOrder.getArtNo());
				orderDtl.setBrandName(cutPriceOrder.getBrandName());
				orderDtl.setProductPropDesc(cutPriceOrder.getProductPropDesc());
				orderDtl.setQuantity(quantity);
				orderDtl.setTagPrice(tagPrice);
				orderDtl.setSalePrice(salePrice);
				orderDtl.setPayAmount(payAmount);
				orderDtl.setSettlePrice(settlePrice);
				orderDtl.setPopCommissionRate(popFeeRate);
				orderDtl.setMchtPreferential(mchtPreferential);
				orderDtl.setPlatformPreferential(platformPreferential);
				orderDtl.setIntegralPreferential(integralPreferential);
				orderDtl.setSettleAmount(settleAmount);
				orderDtl.setCommissionAmount(commissionAmount);
				orderDtl.setCreateBy(cutPriceOrder.getMemberId());
				orderDtl.setCreateDate(date);
				orderDtl.setDelFlag("0");
				orderDtlService.insertSelective(orderDtl);
				OrderDtlExtend orderDtlExtend = buildOrderDtlExtend(orderDtl);
				orderDtlExtend.setManageSelfFreight(selfOperatedFreight);
				orderDtlExtendMapper.insert(orderDtlExtend);
				
				//更新邀请享免单数据为已下单
				cutPriceOrder.setStatus("4");
				cutPriceOrder.setCombineOrderId(combineOrder.getId());
				cutPriceOrder.setPayAmount(combineOrder.getTotalPayAmount());
				updateByPrimaryKeySelective(cutPriceOrder);
				//日志
				cutPriceOrderLogService.addCutPriceOrderLog(cutPriceOrder.getMemberId(), cutPriceOrder.getId(), "4", "成功下单");
			}
		}
	}

	private CombineOrderExtend buildCombineOrderExtend(CombineOrder combineOrder) {
		CombineOrderExtend extend = new CombineOrderExtend();
		extend.setCombineOrderId(combineOrder.getId());
		extend.setManageSelfFreight(BigDecimal.ZERO);
		extend.setDelFlag("0");
		extend.setCreateBy(combineOrder.getCreateBy());
		extend.setCreateDate(combineOrder.getCreateDate());
		return extend;
	}

	private SubOrderExtend buildSubOrderExtend(SubOrder subOrder) {
		SubOrderExtend extend = new SubOrderExtend();
		extend.setSubOrderId(subOrder.getId());
		extend.setManageSelfFreight(BigDecimal.ZERO);
		extend.setDelFlag("0");
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
		extend.setDelFlag("0");
		return extend;
	}
	
	public void getAssistanceExpireOrder() {
		List<CutPriceOrder> cutPriceOrders = cutPriceOrderCustomMapper.getAssistanceExpireOrder();
		Date currentDate = new Date();
		if(CollectionUtils.isNotEmpty(cutPriceOrders)){
			for (CutPriceOrder cutPriceOrder : cutPriceOrders) {
				try {
					cutPriceOrder.setStatus("3");
					cutPriceOrder.setUpdateDate(currentDate);
					updateByPrimaryKeySelective(cutPriceOrder);
					productItemService.updateSkuStock(cutPriceOrder.getProductItemId(), cutPriceOrder.getQuantity(), 0);
				} catch (Exception e) {
					e.printStackTrace();
		    		logger.info(e.getMessage());
					logger.info("助力大减价单据释放库存失败:end-->"+cutPriceOrder.getOrderCode());
				}
			}
		}
	}
	
}
