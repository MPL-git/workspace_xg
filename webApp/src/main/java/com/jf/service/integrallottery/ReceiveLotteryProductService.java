package com.jf.service.integrallottery;

import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.NumberUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CombineOrderExtendMapper;
import com.jf.dao.OrderDtlExtendMapper;
import com.jf.dao.SubOrderExtendMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderExtend;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;
import com.jf.entity.MemberAddressCustom;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberLottery;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlExtend;
import com.jf.entity.Product;
import com.jf.entity.ProductItem;
import com.jf.entity.ProductItemExample;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExtend;
import com.jf.service.CombineOrderService;
import com.jf.service.DeliveryOvertimeCnfService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.MemberAddressService;
import com.jf.service.MemberInfoService;
import com.jf.service.OrderDtlService;
import com.jf.service.OrderPreferentialInfoService;
import com.jf.service.ProductItemService;
import com.jf.service.ProductService;
import com.jf.service.SubOrderService;
import com.jf.vo.request.integrallottery.ReceiveLotteryProductRequest;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luoyb
 * Created on 2020/7/23
 */
@Service
public class ReceiveLotteryProductService {

    private static Logger logger = LoggerFactory.getLogger(ReceiveLotteryProductService.class);

    @Autowired
    private MemberLotteryService memberLotteryService;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductItemService productItemService;
    @Autowired
    private MemberAddressService memberAddressService;
    @Autowired
    private CombineOrderService combineOrderService;
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
    private OrderPreferentialInfoService orderPreferentialInfoService;
    @Autowired
    private CombineOrderExtendMapper combineOrderExtendMapper;
    @Autowired
    private SubOrderExtendMapper subOrderExtendMapper;
    @Autowired
    private OrderDtlExtendMapper orderDtlExtendMapper;

    @Transactional
    public void receiveProduct(ReceiveLotteryProductRequest request, Integer memberId) {
        MemberLottery memberLottery = memberLotteryService.selectByPrimaryKey(request.getMemberLotteryId());
        Map<String, Object> addressParamsMap = new HashMap<String, Object>();
        addressParamsMap.put("memberId", memberId);
        addressParamsMap.put("addressId", request.getMemberAddressId());
        MemberAddressCustom memberAddress = memberAddressService.getAddressById(addressParamsMap);
        // 校验
        validateBeforeReceive(memberLottery, memberAddress, memberId);

        Product product = productService.selectByPrimaryKey(memberLottery.getProductId());
        ProductItem productItem = getProductItem(request.getProductItemId(), product);

        // 扣除库存
        decreaseStock(productItem.getId());

        Date now = new Date();
        BigDecimal popFeeRate = BigDecimal.ZERO;
        MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(product.getMchtId());
        String mchtType = mchtInfo.getMchtType();//商家类型 1.spop 2.pop
        if ("2".equals(mchtType)) {
            MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
            mchtProductBrandExample.createCriteria()
                    .andMchtIdEqualTo(product.getMchtId())
                    .andProductBrandIdEqualTo(product.getBrandId())
                    .andDelFlagEqualTo(StateConst.FALSE)
                    .andStatusEqualTo("1");
            List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mchtProductBrandExample);
            if (CollectionUtils.isNotEmpty(mchtProductBrands)) {
                popFeeRate = mchtProductBrands.get(0).getPopCommissionRate();
            } else {
                logger.warn("商家【ID:{}】商品品牌【ID：{}】未配置POP佣金比例", product.getMchtId(), product.getBrandId());
                throw new BizException("领取失败");
            }
        }

        //吊牌价
        BigDecimal tagPrice = productItem.getTagPrice();
        //商品销售单价（即用户购买价格）
        BigDecimal salePrice = productItem.getSalePrice();
        //实付金额
        BigDecimal payAmount = BigDecimal.ZERO;
        //结算价（联营使用）
        BigDecimal settlePrice = productItem.getCostPrice();
        //平台优惠
        BigDecimal platformPreferential = salePrice;
        //商家优惠
        BigDecimal mchtPreferential = BigDecimal.ZERO;
        //积分优惠
        BigDecimal integralPreferential = BigDecimal.ZERO;
        //自营运费
        BigDecimal selfOperatedFreight = BigDecimal.ZERO;
        //结算金额
        BigDecimal settleAmount = BigDecimal.ZERO;
        BigDecimal commissionAmount = BigDecimal.ZERO;
        if (Const.MCHT_TYPE_POP.equals(mchtType)) {
            //结算金额 = （成交价 * 数量 - 商家优惠 + 运费） * （1-pop佣金比率）
            settleAmount = salePrice.multiply(BigDecimal.ONE.subtract(popFeeRate));
            //服务费 = （成交价 * 数量 - 商家优惠 + 运费） * pop佣金比率
            commissionAmount = salePrice.multiply(popFeeRate);
        } else {
            if (StateConst.FALSE.equals(mchtInfo.getIsManageSelf())) {
                //1:spop：
                //结算金额 = 结算价 * 数量 - 商家优惠 + 运费
                settleAmount = productItem.getCostPrice();
                //服务费 = （醒购价 - 结算价）* 数量
                commissionAmount = salePrice.subtract(productItem.getCostPrice());
            } else {
                //3:自营spop：
                selfOperatedFreight = NumberUtil.get(productItem.getManageSelfFreight());
                //结算金额 = 结算价 * 数量 - 商家优惠 + 自营运费
                settleAmount = productItem.getCostPrice().add(selfOperatedFreight);
                //服务费 = （醒购价 - 结算价）* 数量 + 订单运费 - 自营运费
                commissionAmount = salePrice.subtract(productItem.getCostPrice()).subtract(selfOperatedFreight);
            }
        }

        MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);

        CombineOrder combineOrder = new CombineOrder();
        String combineOrderCode = "XG" + CommonUtil.getOrderCode();
        String subOrderCode = "XGS" + CommonUtil.getOrderCode();
        combineOrder.setCombineOrderCode(combineOrderCode);
        combineOrder.setMemberId(memberId);
        combineOrder.setMemberNick(memberInfo.getNick());
        combineOrder.setAddressId(memberAddress.getId());
        combineOrder.setReceiverName(memberAddress.getRecipient());
        combineOrder.setReceiverPhone(memberAddress.getPhone());
        combineOrder.setReceiverAddress(memberAddress.getFullAddressName());
        combineOrder.setTotalAmount(salePrice);
        combineOrder.setTotalPayAmount(payAmount);
        combineOrder.setTotalMchtPreferential(mchtPreferential);
        combineOrder.setTotalPlatformPreferential(platformPreferential);
        combineOrder.setTotalIntegralPreferential(integralPreferential);
        combineOrder.setStatus(Const.COMBINE_ORDER_STATUS_PAID);
        combineOrder.setFinancialStatus(Const.COMBINE_ORDER_FINANCIAL_STATUS_NULL);
        combineOrder.setFinancialNo(StringUtil.buildMsg("JFZP{}", DateUtil.getFormatDate(now, "yyyyMMdd")));
        combineOrder.setPayDate(now);
        combineOrder.setPayStatus("1");
        combineOrder.setOrderType("7"); //积分转盘
        combineOrder.setSourceClient(request.getSourceClient());
        combineOrder.setCreateBy(memberId);
        combineOrder.setCreateDate(now);
        combineOrder.setDelFlag(StateConst.FALSE);
        combineOrderService.insertSelective(combineOrder);
        CombineOrderExtend combineOrderExtend = buildCombineOrderExtend(combineOrder);
        combineOrderExtend.setManageSelfFreight(selfOperatedFreight);
        combineOrderExtendMapper.insert(combineOrderExtend);

        //查找物流发货时间，找不到默认为当前时间+48H
        Map<String, Object> dateCnfMap = deliveryOvertimeCnfService.getDeliveryOvertimeCnf(memberAddress.getAddress());
        SubOrder subOrder = new SubOrder();
        subOrder.setCombineOrderId(combineOrder.getId());
        subOrder.setSubOrderCode(subOrderCode);
        subOrder.setMchtId(product.getMchtId());
        subOrder.setMchtType(mchtInfo.getMchtType());
        subOrder.setIsManageSelf(mchtInfo.getIsManageSelf());
        subOrder.setAmount(salePrice);
        subOrder.setPayAmount(payAmount);
        subOrder.setStatusDate(now);
        subOrder.setMchtPreferential(mchtPreferential);
        subOrder.setPlatformPreferential(platformPreferential);
        subOrder.setIntegralPreferential(integralPreferential);
        subOrder.setStatus(Const.ORDER_STATUS_PAID);
        if (dateCnfMap.get("deliveryOvertime") != null) {
            subOrder.setDeliveryOvertime(Integer.valueOf(dateCnfMap.get("deliveryOvertime").toString()));
        }
        subOrder.setDeliveryLastDate((Date) dateCnfMap.get("deliveryDate"));
        subOrder.setCreateBy(memberId);
        subOrder.setCreateDate(now);
        subOrder.setDelFlag(StateConst.FALSE);
        subOrderService.insertSelective(subOrder);
        SubOrderExtend subOrderExtend = buildSubOrderExtend(subOrder);
        subOrderExtend.setManageSelfFreight(selfOperatedFreight);
        subOrderExtendMapper.insert(subOrderExtend);

        OrderDtl orderDtl = new OrderDtl();
        orderDtl.setSubOrderId(subOrder.getId());
        orderDtl.setProductId(product.getId());
        orderDtl.setProductItemId(productItem.getId());
//        orderDtl.setSingleProductActivityId();
        orderDtl.setSkuPic(productItem.getPic());
        orderDtl.setSku(productItem.getSku());
        orderDtl.setProductName(product.getName());
        orderDtl.setArtNo(product.getArtNo());
        orderDtl.setBrandName(memberLottery.getBrand());
        orderDtl.setProductPropDesc(productItemService.getProductItemPropDesc(productItem.getId()));
        orderDtl.setQuantity(1);
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
        orderDtl.setCreateBy(memberId);
        orderDtl.setCreateDate(now);
        orderDtl.setDelFlag(StateConst.FALSE);
        orderDtlService.insertSelective(orderDtl);
        OrderDtlExtend orderDtlExtend = buildOrderDtlExtend(orderDtl);
        orderDtlExtend.setManageSelfFreight(selfOperatedFreight);
        orderDtlExtendMapper.insert(orderDtlExtend);

        orderPreferentialInfoService.saveOrderPreferentialInfo("12", 0, orderDtl.getId(),
                "6", "积分转盘", platformPreferential,
                Const.COUPON_BELONG_TYPE_PLATFORM, memberId);

        // 更新中奖记录
        memberLottery.setRelevantId(combineOrder.getId());
        memberLottery.setUpdateBy(memberId);
        memberLottery.setUpdateDate(now);
        memberLotteryService.updateByPrimaryKeySelective(memberLottery);
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

    private ProductItem getProductItem(Integer productItemId, Product product) {
        if (productItemId != null) {
            return productItemService.selectByPrimaryKey(productItemId);
        }
        if (!"1".equals(product.getIsSingleProp())) {
            throw new BizException("请选择规格");
        }
        ProductItemExample example = new ProductItemExample();
        example.createCriteria()
                .andDelFlagEqualTo("0")
                .andProductIdEqualTo(product.getId());
        List<ProductItem> list = productItemService.selectByExample(example);
        return list.get(0);
    }

    private void decreaseStock(Integer productItemId) {
        if (!productItemService.decreaseSkuStock(productItemId, 1)) {
            logger.warn("商品【规格ID：{}】库存不足", productItemId);
            throw new BizException("库存不足，领取失败，请联系客服！");
        }
    }

    private void validateBeforeReceive(MemberLottery memberLottery, MemberAddressCustom memberAddress, Integer memberId) {
        if (memberLottery == null || StateConst.TRUE.equals(memberLottery.getDelFlag()) || !memberLottery.getMemberId().equals(memberId) || !"3".equals(memberLottery.getType())) {
            throw new BizException("无中奖商品");
        }
        if (memberLottery.getRelevantId() != null) {
            throw new BizException("您已领取该商品");
        }
        if (memberAddress == null) {
            throw new BizException("请选择收货地址");
        }
    }
}
