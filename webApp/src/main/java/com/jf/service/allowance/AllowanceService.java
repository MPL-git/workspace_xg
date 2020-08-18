package com.jf.service.allowance;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jf.common.base.Page;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.exception.BizException;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.NumberUtil;
import com.jf.common.utils.PageDTO;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberAllowanceCustomMapper;
import com.jf.entity.ActivityCustom;
import com.jf.entity.AllowanceInfo;
import com.jf.entity.AllowanceSetting;
import com.jf.entity.AllowanceSettingIntegralExchange;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAllowance;
import com.jf.entity.MemberAllowanceUsage;
import com.jf.service.ActivityService;
import com.jf.service.MemberAccountService;
import com.jf.service.ProductService;
import com.jf.vo.request.PageRequest;
import com.jf.vo.request.allowance.FindAreaProductListRequest;
import com.jf.vo.response.allowance.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
@Service
public class AllowanceService {

    @Autowired
    private AllowanceInfoService allowanceInfoService;
    @Autowired
    private MemberAllowanceService memberAllowanceService;
    @Autowired
    private AllowanceSettingIntegralExchangeService allowanceSettingIntegralExchangeService;
    @Autowired
    private MemberAccountService memberAccountService;
    @Autowired
    private AllowanceSettingService allowanceSettingService;
    @Autowired
    private MemberAllowanceUsageService memberAllowanceUsageService;
    @Autowired
    private MemberAllowanceCustomMapper memberAllowanceCustomMapper;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ProductService productService;

    public GetAllowanceInfoResponse getAllowanceInfo(Integer activityAreaId, Integer memberId) {
        AllowanceInfo allowanceInfo = allowanceInfoService.getByActivityAreaId(activityAreaId);

        MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
        AllowanceSetting setting = allowanceSettingService.getActivitySetting();
        List<AllowanceSettingIntegralExchange> exchangeSettingList = allowanceSettingIntegralExchangeService.findByAllowanceId(setting.getId());
        MemberAllowance memberAllowance = memberAllowanceService.getLatestRecord(memberId);
        String exchangeDesc;
        BigDecimal lastExchangeAllowance = null;
        if (memberAllowance != null) {
            exchangeDesc = buildExchangeDesc(memberAllowance);
            lastExchangeAllowance = memberAllowance.getAllowanceAmount();
        } else {
            exchangeDesc = buildExchangeDesc(exchangeSettingList.get(0));
        }

        GetAllowanceInfoResponse response = new GetAllowanceInfoResponse();
        response.setAllowanceInfoId(allowanceInfo.getId());
        response.setAreaAllowanceDesc(buildAllowanceDesc(allowanceInfo));
        response.setExchangeDesc(exchangeDesc);
        response.setAllowanceBalance(memberAllowanceService.getMemberAllowanceBalance(memberId));
        response.setLastExchangeAllowance(lastExchangeAllowance);
        response.setIntegral(memberAccount.getIntegral());
        for (AllowanceSettingIntegralExchange exchange : exchangeSettingList) {
            response.getExchangeList().add(buildExchangeView(exchange));
        }
        return response;
    }

    private String buildExchangeDesc(MemberAllowance memberAllowance) {
        return StringUtil.buildMsg("抢到【{}元】购物津贴", memberAllowance.getAllowanceAmount());
    }

    private String buildExchangeDesc(AllowanceSettingIntegralExchange exchange) {
        if (exchange.getExchangeAmountMin().equals(exchange.getExchangeAmountMax())) {
            return StringUtil.buildMsg("{}积分抢{}元速来", exchange.getIntegral()
                    , exchange.getExchangeAmountMin().stripTrailingZeros().toPlainString());
        }
        return StringUtil.buildMsg("{}积分抢{}-{}元速来", exchange.getIntegral()
                , exchange.getExchangeAmountMin().stripTrailingZeros().toPlainString()
                , exchange.getExchangeAmountMax().stripTrailingZeros().toPlainString());
    }

    private AllowanceExchangeView buildExchangeView(AllowanceSettingIntegralExchange exchange) {
        String desc;
        if (exchange.getExchangeAmountMin().equals(exchange.getExchangeAmountMax())) {
            desc = StringUtil.buildMsg("{}积分抢{}元", exchange.getIntegral()
                    , exchange.getExchangeAmountMin().stripTrailingZeros().toPlainString());
        } else {
            desc = StringUtil.buildMsg("{}积分抢{}-{}元", exchange.getIntegral()
                    , exchange.getExchangeAmountMin().stripTrailingZeros().toPlainString()
                    , exchange.getExchangeAmountMax().stripTrailingZeros().toPlainString());
        }
        AllowanceExchangeView view = new AllowanceExchangeView();
        view.setId(exchange.getId());
        view.setDesc(desc);
        return view;
    }

    private String buildAllowanceDesc(AllowanceInfo allowanceInfo) {
        String[] rules = allowanceInfo.getRule().split(",");
        String full = rules[0];
        String min = rules[1];
        return StringUtil.buildMsg("每满{}减{}，上不封顶", full, min);
    }

    public GetAllowanceDetailResponse getAllowanceDetail(Integer memberId) {
        List<AllowanceInfo> allowanceInfoList = allowanceInfoService.findActiveAllowance();
        AllowanceSetting setting = allowanceSettingService.getActivitySetting();

        GetAllowanceDetailResponse response = new GetAllowanceDetailResponse();
        response.setInformationId(296); //津贴活动规则ID
        response.setAllowanceBalance(memberAllowanceService.getMemberAllowanceBalance(memberId));
        if (!CollectionUtils.isEmpty(allowanceInfoList)) {
            Collections.sort(allowanceInfoList, new Comparator<AllowanceInfo>() {
                @Override
                public int compare(AllowanceInfo o1, AllowanceInfo o2) {
                    try {
                        String[] rules1 = o1.getRule().split(",");
                        Double rate1 = Double.parseDouble(rules1[1]) / Double.parseDouble(rules1[0]);
                        String[] rules2 = o2.getRule().split(",");
                        Double rate2 = Double.parseDouble(rules2[1]) / Double.parseDouble(rules2[0]);
                        return -1 * rate1.compareTo(rate2);
                    } catch (Exception e) {
                        return -1;
                    }
                }
            });
            response.setActivityAreaId(allowanceInfoList.get(0).getActivityAreaId());
            response.setTipContent(buildTipContent(allowanceInfoList.get(0)));
            Set<String> allowanceDescSet = Sets.newHashSet();
            for (AllowanceInfo allowanceInfo : allowanceInfoList) {
                allowanceDescSet.add(allowanceInfo.getAllowanceDescription());
            }
            response.getAllowanceDescList().addAll(allowanceDescSet);

            List<AllowanceProductTypeView> allowanceProductTypeViewList = activityService.listAllowanceProductType(allowanceInfoList.get(0).getActivityAreaId());
            for(AllowanceProductTypeView allowanceProductTypeView : allowanceProductTypeViewList) {
                response.getProductTypeList().add(allowanceProductTypeView);
            }
        }
        if (setting == null || setting.getExchangeEndDate().before(new Date())) {
            response.setActivityFinish(true);
        } else {
            response.setActivityFinish(false);
            List<AllowanceSettingIntegralExchange> exchangeSettingList = allowanceSettingIntegralExchangeService.findByAllowanceId(setting.getId());
            for (AllowanceSettingIntegralExchange exchange : exchangeSettingList) {
                response.getExchangeList().add(buildExchangeView(exchange));
            }
        }
        return response;
    }

    private String buildTipContent(AllowanceInfo allowanceInfo) {
        String[] rules = allowanceInfo.getRule().split(",");
        String full = rules[0];
        String min = rules[1];
        return StringUtil.buildMsg("购物津贴可用商品，每满{}减{}", full, min);
    }

    public GetAllowancePopInfoResponse getAllowancePopInfo(Integer activityAreaId) {
        AllowanceInfo allowanceInfo = allowanceInfoService.getByActivityAreaId(activityAreaId);
        AllowanceSetting setting = allowanceSettingService.getActivitySetting();
        GetAllowancePopInfoResponse response = new GetAllowancePopInfoResponse();
        if (allowanceInfo == null || setting == null || setting.getExchangeEndDate().before(new Date())) {
            // 未配置/活动时间已过均不显示
            response.setAllowance(false);
            return response;
        }

        response.setAllowance(true);
        response.setPic(StringUtil.getPic(setting.getPic(), ""));
        response.setPopType(setting.getPopupCount());
        if ("3".equals(setting.getPopupCount())) {
            response.setDay(setting.getDay());
        }
        return response;
    }

    public List<MemberAllowanceRecordView> findMemberAllowance(Integer memberId, PageRequest request) {
        List<MemberAllowance> list = memberAllowanceService.findMemberAllowance(memberId, request);
        if (CollectionUtils.isEmpty(list)) return Collections.emptyList();

        List<MemberAllowanceRecordView> viewList = Lists.newArrayList();
        for (MemberAllowance memberAllowance : list) {
            MemberAllowanceRecordView view = new MemberAllowanceRecordView();
            if ("1".equals(memberAllowance.getSource())) {
                view.setName("购物津贴大礼包");
            } else if ("2".equals(memberAllowance.getSource())) {
                view.setName("商品退款");
            }
            view.setCreateDateDesc(DateUtil.getFormatDate(memberAllowance.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
            view.setAmount(StringUtil.buildMsg("+￥{}", memberAllowance.getAllowanceAmount().stripTrailingZeros().toPlainString()));
            viewList.add(view);
        }
        return viewList;
    }

    public List<MemberAllowanceRecordView> findMemberUsedAllowance(Integer memberId, PageRequest request) {
        List<MemberAllowanceUsage> list = memberAllowanceUsageService.findMemberUsedAllowance(memberId, request);
        if (CollectionUtils.isEmpty(list)) return Collections.emptyList();

        List<MemberAllowanceRecordView> viewList = Lists.newArrayList();
        for (MemberAllowanceUsage usedAllowance : list) {
            MemberAllowanceRecordView view = new MemberAllowanceRecordView();

            if ("1".equals(usedAllowance.getType()) && StringUtils.hasText(usedAllowance.getRule())) {
                String[] rules = usedAllowance.getRule().split(",");
                String full = rules[0];
                String min = rules[1];
                view.setName(StringUtil.buildMsg("满{}减{}抵扣", full, min));
            } else if ("2".equals(usedAllowance.getType())) {
                view.setName("活动结束清零");
            }
            view.setCreateDateDesc(DateUtil.getFormatDate(usedAllowance.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
            view.setAmount(StringUtil.buildMsg("-￥{}", usedAllowance.getUsageAmount().stripTrailingZeros().toPlainString()));
            viewList.add(view);
        }
        return viewList;
    }

    /**
     * 获取用户累计兑换津贴
     */
    public BigDecimal getMemberTotalAllowance(Integer memberId) {
        return memberAllowanceCustomMapper.getMemberAllowanceBalance(memberId);
    }

    public Map<String, Object> findAreaProductList(FindAreaProductListRequest findAreaProductListRequest) {
        Map<String, Object> returnData = Maps.newHashMap();
        returnData.put("activityAreaId", findAreaProductListRequest.getActivityAreaId());
        returnData.put("currentPage", findAreaProductListRequest.getCurrentPage());
        findAreaProductListRequest.setPagesize(Const.RETURN_SIZE_10);
        findAreaProductListRequest.setCurrentPage(findAreaProductListRequest.getCurrentPage()*findAreaProductListRequest.getPagesize());
        List<ActivityCustom> activityCustoms = activityService.findAreaProductList(findAreaProductListRequest);
        List<Map<String, Object>> productInfoList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(activityCustoms)) {
            for (ActivityCustom activityCustom : activityCustoms) {
                Map<String, Object> productMap = new HashMap<String, Object>();
                String pic = activityCustom.getProductPic();
                BigDecimal salePrice = new BigDecimal(activityCustom.getSalePrice() + "");
                BigDecimal svipSalePrice = new BigDecimal("0");
                BigDecimal svipDiscount = activityCustom.getSvipDiscount() == null ? new BigDecimal("0") : activityCustom.getSvipDiscount();
                if (activityCustom.getIsWatermark().equals("1")) {
                    pic = StringUtil.getActivityMkPic(pic, "M_WM", activityCustom.getActivityAreaId());
                } else {
                    pic = StringUtil.getPic(pic, "M");
                }
                if (svipDiscount != null && svipDiscount.compareTo(new BigDecimal("0")) > 0) {
                    svipSalePrice = productService.getProductSvipSalePrice(salePrice, svipDiscount, "0");
                }
                BigDecimal arrivalPrice = new BigDecimal("0");
                BigDecimal deductAmount = new BigDecimal("0");
                BigDecimal deposit = new BigDecimal("0");
                if (activityCustom.getActivityEndTime().after(new Date()) && activityCustom.getDeductAmount() != null && activityCustom.getDeposit() != null) {
                    deductAmount = activityCustom.getDeductAmount();
                    deposit = activityCustom.getDeposit();
                    arrivalPrice = salePrice.subtract(deductAmount).add(deposit);
                }
                if (activityCustom.getMaxSalePrice().compareTo(salePrice) == 0) {
                    productMap.put("hasDifferentPrice", "");
                } else {
                    productMap.put("hasDifferentPrice", "起");
                }
                productMap.put("arrivalPrice", arrivalPrice);
                productMap.put("deductAmount", deductAmount);
                productMap.put("deposit", deposit);
                productMap.put("svipSalePrice", svipSalePrice);
                productMap.put("productName", activityCustom.getProductName());
                productMap.put("salePrice", salePrice);
                productMap.put("tagPrice", activityCustom.getTagPrice());
                productMap.put("discount", activityCustom.getDiscount());
                productMap.put("activityAreaId", activityCustom.getActivityAreaId());
                productMap.put("activityId", activityCustom.getId());
                productMap.put("productId", activityCustom.getProductId());
                productMap.put("stock", activityCustom.getStockSum());
                productMap.put("pic", pic);
                productInfoList.add(productMap);
            }
        }
        returnData.put("productInfoList", productInfoList);
        return returnData;
    }

    @Transactional
    public ExchangeAllowanceResponse exchangeAllowance(Integer allowanceInfoId, Integer id, Integer memberId) {
        AllowanceSettingIntegralExchange exchange = allowanceSettingIntegralExchangeService.selectByPrimaryKey(id);
        AllowanceSetting setting = allowanceSettingService.selectByPrimaryKey(exchange.getAllowanceId());
        if (setting == null || "0".equals(setting.getStatus())) {
            throw new BizException("活动不存在");
        }
        Date now = new Date();
        if (setting.getExchangeBeginDate().after(now)) {
            throw new BizException("活动未开始");
        }
        if (setting.getExchangeEndDate().before(now)) {
            throw new BizException("活动已结束");
        }

        MemberAccount memberAccount = memberAccountService.findAccountByMemberId(memberId);
        if (memberAccount.getIntegral() < exchange.getIntegral()) {
            throw new BizException("您的积分不足");
        }
        // 本次兑换津贴
        BigDecimal amount;
        if (exchange.getExchangeAmountMin().compareTo(exchange.getExchangeAmountMax()) == 0) {
            amount = exchange.getExchangeAmountMin();
        } else {
            int randomAmount = NumberUtil.rand(exchange.getExchangeAmountMin().intValue(), exchange.getExchangeAmountMax().intValue());
            amount = new BigDecimal(randomAmount);
        }

        // 记录积分使用
        memberAccountService.decreaseGiftIntegral(exchange.getIntegral(), memberId, Const.INTEGRAL_TYPE_ALLOWANCE_EXCHANGE);

        // 添加兑换记录
        MemberAllowance memberAllowance = new MemberAllowance();
        memberAllowance.setMemberId(memberId);
        memberAllowance.setAllowanceSettingId(setting.getId());
        if (allowanceInfoId != null) {
            memberAllowance.setAllowanceInfoId(allowanceInfoId);
        }
        memberAllowance.setAllowanceAmount(amount);
        memberAllowance.setSource("1");
        memberAllowance.setSpendIntegral(exchange.getIntegral());
        memberAllowance.setDelFlag(StateConst.FALSE);
        memberAllowance.setCreateBy(memberId);
        memberAllowance.setCreateDate(now);
        memberAllowanceService.insert(memberAllowance);

        ExchangeAllowanceResponse response = new ExchangeAllowanceResponse();
        response.setIntegral(memberAccount.getIntegral() - exchange.getIntegral());
        response.setAllowanceBalance(memberAllowanceService.getMemberAllowanceBalance(memberId));
        response.setLastExchangeAllowance(memberAllowance.getAllowanceAmount());
        response.setExchangeDesc(buildExchangeDesc(memberAllowance));
        return response;
    }
}
