package com.jf.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CutPriceOrderCustomMapper;
import com.jf.dao.CutPriceOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CutPriceCnf;
import com.jf.entity.CutPriceCnfDtl;
import com.jf.entity.CutPriceOrder;
import com.jf.entity.CutPriceOrderCustom;
import com.jf.entity.CutPriceOrderExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.dto.CutPriceOrderDTO;
import com.jf.vo.request.FindAssistanceCutCompleteLogRequest;
import com.jf.vo.response.AssistanceCutCompleteLogView;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class CutPriceOrderService extends BaseService<CutPriceOrder, CutPriceOrderExample> {
    @Autowired
    private CutPriceOrderMapper cutPriceOrderMapper;
    @Autowired
    private CutPriceOrderCustomMapper cutPriceOrderCustomMapper;
    @Autowired
    private CutPriceOrderDtlService cutPriceOrderDtlService;
    @Autowired
    private CutPriceCnfService cutPriceCnfService;
    @Autowired
    private CutPriceCnfDtlService cutPriceCnfDtlService;
    @Autowired
    private SingleProductActivityService singleProductActivityService;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private CutPriceOrderLogService cutPriceOrderLogService;
    @Resource
    private ProductItemService productItemService;
    @Resource
    private ProductService productService;
    @Autowired
    private CommonService commonService;

    @Autowired
    public void setCutPriceOrderMapper(CutPriceOrderMapper cutPriceOrderMapper) {
        this.setDao(cutPriceOrderMapper);
        this.cutPriceOrderMapper = cutPriceOrderMapper;
    }

    public int getcutOrderSuccessNum(String memberId, String activityType, String status, SingleProductActivity singleProductActivity, Integer singleProductActivityId) {
        if (singleProductActivity == null) {
            singleProductActivity = singleProductActivityService.selectByPrimaryKey(singleProductActivityId);
        }
        CutPriceOrderExample cutPriceOrderExample = new CutPriceOrderExample();
        CutPriceOrderExample.Criteria criteria = cutPriceOrderExample.createCriteria();
        criteria.andDelFlagEqualTo("0").andStatusEqualTo(status).andActivityTypeEqualTo(activityType).andProductIdEqualTo(singleProductActivity.getProductId());
        if (!StringUtil.isBlank(memberId)) {
            criteria.andMemberIdEqualTo(Integer.valueOf(memberId));
        }
        int num = countByExample(cutPriceOrderExample);
        num += singleProductActivity.getUnrealityNum(); //加上虚拟数
        return num;
    }

    public int updateCutOrderStatus(Integer cutOrderId) {

        return cutPriceOrderCustomMapper.updateCutOrderStatus(cutOrderId);
    }

    public int updateCutOrderSuccess(CutPriceOrder cutPriceOrder) {

        return cutPriceOrderCustomMapper.updateCutOrderSuccess(cutPriceOrder);
    }

    public List<CutPriceOrderCustom> getMyAssistanceGoodsTaskList(Map<String, Object> paramsMap) {

        return cutPriceOrderCustomMapper.getMyAssistanceGoodsTaskList(paramsMap);
    }

    /**
     * taskStatus：
     * 1、任务中 2、任务结束 3、邀请完成 4、超时未下单 5、已下单
     */
    public Map<String, String> getAssistanceTaskStatus(CutPriceOrder cutPriceOrder, Integer activeTime) {
        //assistanceNum 暂时没用
        Date currentDate = new Date();
        String taskStatus = "";
        String taskStatusStr = "";
        String errMsg = "";
        String status = cutPriceOrder.getStatus();//状态1砍价中 2砍价成功 4已下单 3砍价失败
        Date beginDate = cutPriceOrder.getCreateDate();
        if (cutPriceOrder.getActivityType().equals(Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE)) {
            if (activeTime == null) {
                List<CutPriceCnf> cutPriceCnfs = cutPriceCnfService.findModelBySingleActivityId(cutPriceOrder.getSingleProductActivityId());
                activeTime = cutPriceCnfs.get(0).getActiveTime();
            }
        } else {
            activeTime = 24;
        }
        Date endDate = DateUtil.addHour(beginDate, activeTime);
        if (status.equals("1")) {
            if (currentDate.after(beginDate) && currentDate.before(endDate)) {
                taskStatus = "1";
                taskStatusStr = "继续邀请";
            } else {
                if (currentDate.after(DateUtil.addHour(endDate, Const.ASSISTANCE_ACTIVITY_COUNT_DOWN_HOURS))) {
                    taskStatus = "4";
                    taskStatusStr = "超时未下单";
                } else {
                    taskStatus = "2";
                    taskStatusStr = "任务结束";
                }
            }
        } else if (status.equals("2")) {
            if (currentDate.after(DateUtil.addHour(cutPriceOrder.getUpdateDate(), Const.ASSISTANCE_ACTIVITY_COUNT_DOWN_HOURS))) {
                taskStatus = "4";
                taskStatusStr = "超时未下单";
            } else {
                taskStatus = "3";
                taskStatusStr = "邀请完成立即下单";
            }
        } else if (status.equals("3")) {
            taskStatus = "4";
            taskStatusStr = "超时未下单";
        } else if (status.equals("4")) {
            taskStatus = "5";
            taskStatusStr = "已下单前往查看";
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("taskStatus", taskStatus);
        map.put("taskStatusStr", taskStatusStr);
        map.put("errMsg", errMsg);
        return map;
    }

    public List<CutPriceOrder> findModelBySingleActivityId(Integer singleProductActivityId, Integer memberId) {
        CutPriceOrderExample cutPriceOrderExample = new CutPriceOrderExample();
        cutPriceOrderExample.createCriteria().andMemberIdEqualTo(memberId).andSingleProductActivityIdEqualTo(singleProductActivityId).andDelFlagEqualTo("0");
        cutPriceOrderExample.setOrderByClause("id desc");
        return selectByExample(cutPriceOrderExample);
    }

    public Map<String, Object> getMyAssistanceCutPriceShareInfo(MemberInfo memberInfo, JSONObject reqDataJson) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer cutOrderId = reqDataJson.getInt("cutOrderId");
        String auditStatus = "2";//1本人（分享的来源用户） 2邀请用户
        CutPriceOrder cutPriceOrder = selectByPrimaryKey(cutOrderId);
        String productName = cutPriceOrder.getProductName();
        BigDecimal salePrice = cutPriceOrder.getSalePrice();
        String skuPic = StringUtil.getPic(cutPriceOrder.getSkuPic(), "S");
        Integer sourceMemberId = cutPriceOrder.getMemberId();
        Integer memberId = memberInfo.getId();
        boolean mobileVerfiyFlag = false;
        if (memberId.equals(sourceMemberId)) {
            auditStatus = "1";
        }
        List<CutPriceCnf> cnfs = cutPriceCnfService.findModelBySingleActivityId(cutPriceOrder.getSingleProductActivityId());
        CutPriceCnf cnf = cnfs.get(0);
        List<CutPriceCnfDtl> cnfDtls = cutPriceCnfDtlService.findModelByCnfId(cnf.getId());
        CutPriceCnfDtl cnfDtl = cnfDtls.get(0);
        Integer maxInviteTimes = cnf.getMaxInviteTimes();
        Integer activeTime = cnf.getActiveTime();
        BigDecimal fixedAmount = cnfDtl.getFixedAmount();
        Date beginDate = cutPriceOrder.getCreateDate();
        Date endDate = DateUtil.addHour(beginDate, activeTime);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("activityType", Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE);
        paramsMap.put("cutOrderId", cutOrderId);
        paramsMap.put("type", "1");
        Integer assistanceNum = cutPriceOrderDtlService.getAlreadyInvitedCount(paramsMap);
        if (assistanceNum > maxInviteTimes) {
            assistanceNum = maxInviteTimes;
        }
        String mVerfiyFlag = memberInfo.getmVerfiyFlag();
        String mobile = memberInfo.getMobile();
        if (!StringUtil.isBlank(mobile) && "1".equals(mVerfiyFlag)) {
            mobileVerfiyFlag = true;
        }
        if (auditStatus.equals("1")) {
            //本人进来
            Map<String, String> taskStatusMap = getAssistanceTaskStatus(cutPriceOrder, activeTime);
            String taskStatus = taskStatusMap.get("taskStatus");
            map.put("taskStatus", taskStatus);
            map.put("taskStatusStr", taskStatusMap.get("taskStatusStr"));

            if (Objects.equals(taskStatus, "3")) { //邀请完成，完成时开始购买倒计时
                map.put("buyEndDate", DateUtil.addHour(cutPriceOrder.getUpdateDate(), Const.ASSISTANCE_ACTIVITY_COUNT_DOWN_HOURS)); //购买限时
            } else if (Objects.equals(taskStatus, "2")) { //任务结束，结束时开始购买倒计时
                map.put("buyEndDate", DateUtil.addHour(endDate, Const.ASSISTANCE_ACTIVITY_COUNT_DOWN_HOURS)); //购买限时
            }
        } else {
            memberInfo = memberInfoService.selectByPrimaryKey(cutPriceOrder.getMemberId());
            Map<String, String> assistanceStatusMap = cutPriceOrderDtlService.getMemberAssistanceStatus(cutOrderId, memberId, assistanceNum, maxInviteTimes, beginDate, endDate);
            map.put("memberAssistanceStatus", assistanceStatusMap.get("memberAssistanceStatus"));
            map.put("memberAssistanceStatusStr", assistanceStatusMap.get("memberAssistanceStatusStr"));
        }
        String headPic = memberInfo.getWeixinHead();
        if (!StringUtils.hasText(headPic)) {
            headPic = memberInfo.getPic();
        }
        headPic = StringUtil.getPic(headPic, "");
//		List<Map<String, Object>> inviteDetailList = singleProductActivityService.getCommenInviteDetail(0,Const.RETURN_SIZE_10,cutOrderId,sourceMemberId,Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE);
//		map.put("inviteDetailList", inviteDetailList);
        map.put("productName", productName);
        map.put("salePrice", salePrice);
        map.put("skuPic", skuPic);
        map.put("beginDate", beginDate);
        map.put("endDate", endDate);
        map.put("fixedAmount", fixedAmount);
        map.put("assistanceNum", assistanceNum);
        map.put("maxInviteTimes", maxInviteTimes);
        map.put("memberId", memberId);
        map.put("sourceMemberId", sourceMemberId);
        map.put("auditStatus", auditStatus);
        map.put("currentDate", new Date());
        map.put("cutOrderId", cutOrderId);
        map.put("headPic", headPic);
        map.put("productId", cutPriceOrder.getProductId());
        map.put("productItemId", cutPriceOrder.getProductItemId());
        map.put("quantity", cutPriceOrder.getQuantity());
        map.put("mobileVerfiyFlag", mobileVerfiyFlag);
        map.put("cutLadderList", buildCutLadderList(salePrice, maxInviteTimes, assistanceNum, fixedAmount));

        // 小程序分享
        commonService.fillWithXCXShareInfo(map, "page/activity/reducePrice/share/index?cutOrderId={}&sourceMemberId={}", cutOrderId, memberId);
        map.put("shareTitle", StringUtil.buildMsg("我喜欢这款，快来助我省钱，点击立省{}元", fixedAmount));
        map.put("shareDesc", "醒购助力减价拿");

        return map;
    }

    private static final String ELLIPSIS = "...";

    /**
     * 分5层阶梯，超过5层自动隐藏多余部分
     */
    private List<Map<String, Object>> buildCutLadderList(BigDecimal salePrice, int maxInviteTimes, int assistanceNum, BigDecimal fixedAmount) {
        List<Map<String, Object>> cutLadderList = Lists.newArrayList();
        // 5层内
        if (maxInviteTimes <= 4) {
            for (int i = 0; i <= maxInviteTimes; i++) {
                cutLadderList.add(buildCutLadder(salePrice.subtract(fixedAmount.multiply(new BigDecimal(i))).stripTrailingZeros().toPlainString(), assistanceNum == i));
            }
            return cutLadderList;
        }

        // 超过5层
        String lastLadderPrice = salePrice.subtract(fixedAmount.multiply(new BigDecimal(maxInviteTimes))).stripTrailingZeros().toPlainString();
        String lastSecondLadderPrice = salePrice.subtract(fixedAmount.multiply(new BigDecimal(maxInviteTimes - 1))).stripTrailingZeros().toPlainString();
        String lastThreeLadderPrice = salePrice.subtract(fixedAmount.multiply(new BigDecimal(maxInviteTimes - 2))).stripTrailingZeros().toPlainString();
        if (assistanceNum == 0) {
            cutLadderList.add(buildCutLadder(salePrice.stripTrailingZeros().toPlainString(), true));
            cutLadderList.add(buildCutLadder(salePrice.subtract(fixedAmount).stripTrailingZeros().toPlainString(), false));
            cutLadderList.add(buildCutLadder(ELLIPSIS, false));
            cutLadderList.add(buildCutLadder(lastSecondLadderPrice, false));
            cutLadderList.add(buildCutLadder(lastLadderPrice, false));
        }else if ( assistanceNum >= maxInviteTimes - 2) {
            cutLadderList.add(buildCutLadder(salePrice.stripTrailingZeros().toPlainString(), false));
            cutLadderList.add(buildCutLadder(ELLIPSIS, false));
            cutLadderList.add(buildCutLadder(lastThreeLadderPrice, assistanceNum == maxInviteTimes - 2));
            cutLadderList.add(buildCutLadder(lastSecondLadderPrice, assistanceNum == maxInviteTimes - 1));
            cutLadderList.add(buildCutLadder(lastLadderPrice, assistanceNum == maxInviteTimes));
        } else {
            cutLadderList.add(buildCutLadder(salePrice.stripTrailingZeros().toPlainString(), false));
            cutLadderList.add(buildCutLadder(salePrice.subtract(fixedAmount.multiply(new BigDecimal(assistanceNum))).stripTrailingZeros().toPlainString(), true));
            cutLadderList.add(buildCutLadder(salePrice.subtract(fixedAmount.multiply(new BigDecimal(assistanceNum + 1))).stripTrailingZeros().toPlainString(), false));
            cutLadderList.add(buildCutLadder(ELLIPSIS, false));
            cutLadderList.add(buildCutLadder(lastLadderPrice, false));
        }
        return cutLadderList;
    }

    private Map<String, Object> buildCutLadder(String price, boolean selected) {
        Map<String, Object> ladderMap = Maps.newHashMap();
        ladderMap.put("price", price);
        ladderMap.put("selected", selected);
        return ladderMap;
    }

    public Map<String, Object> getCutOrderPreferentialAmount(CutPriceOrder cutPriceOrder, Integer cutOrderId, Integer memberId, String type, CombineOrder combineOrder) {
        //type 1结算页面入口 2下订单入口
        Map<String, Object> map = new HashMap<>();
        Date currentDate = new Date();
        boolean returnCode = true;
        String returnMsg = "";
        BigDecimal preferentialAmount = new BigDecimal("0");
        Integer assistanceNum = 0;
        String status = "";
        BigDecimal payAmount = new BigDecimal("0");
        if (cutPriceOrder == null) {
            CutPriceOrderExample cutPriceOrderExample = new CutPriceOrderExample();
            cutPriceOrderExample.createCriteria().andMemberIdEqualTo(memberId).andIdEqualTo(cutOrderId).andDelFlagEqualTo("0");
            List<CutPriceOrder> cutPriceOrders = selectByExample(cutPriceOrderExample);
            if (CollectionUtils.isNotEmpty(cutPriceOrders)) {
                cutPriceOrder = cutPriceOrders.get(0);
                cutOrderId = cutPriceOrder.getId();
                status = cutPriceOrder.getStatus();
                if ("4".equals(status)) {
                    returnCode = false;
                    returnMsg = "您已下单，请勿重复下单";
                } else if ("0".equals(status)) {
                    returnCode = false;
                    returnMsg = "请先领取助力任务";
                }
            } else {
                returnCode = false;
                returnMsg = "请先领取助力任务";
            }
        } else {
            cutOrderId = cutPriceOrder.getId();
        }
        if (returnCode) {
            List<CutPriceCnf> cnfs = cutPriceCnfService.findModelBySingleActivityId(cutPriceOrder.getSingleProductActivityId());
            CutPriceCnf cnf = cnfs.get(0);
            List<CutPriceCnfDtl> cnfDtls = cutPriceCnfDtlService.findModelByCnfId(cnf.getId());
            CutPriceCnfDtl cnfDtl = cnfDtls.get(0);
            Integer maxInviteTimes = cnf.getMaxInviteTimes();
            Integer activeTime = cnf.getActiveTime();
            Date beginDate = cutPriceOrder.getCreateDate();
            Date endDate = DateUtil.addDay(DateUtil.addDay(beginDate, activeTime), 24);
            if (currentDate.after(beginDate) && currentDate.before(endDate)) {
                BigDecimal fixedAmount = cnfDtl.getFixedAmount();
                Map<String, Object> paramsMap = new HashMap<>();
                paramsMap.put("activityType", Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE);
                paramsMap.put("cutOrderId", cutOrderId);
                paramsMap.put("type", "1");
                assistanceNum = cutPriceOrderDtlService.getAlreadyInvitedCount(paramsMap);
                if (assistanceNum > maxInviteTimes) {
                    preferentialAmount = fixedAmount.multiply(new BigDecimal(maxInviteTimes + ""));
                } else {
                    preferentialAmount = fixedAmount.multiply(new BigDecimal(assistanceNum + ""));
                }
                payAmount = cutPriceOrder.getSalePrice().subtract(preferentialAmount);
            } else {
                returnCode = false;
                returnMsg = "任务已超时";
            }
            if ("2".equals(type) && returnCode) {
                cutPriceOrder.setStatus("4");//已下单
                cutPriceOrder.setCombineOrderId(combineOrder.getId());
                cutPriceOrder.setPayAmount(payAmount);
                cutPriceOrder.setAddressId(combineOrder.getAddressId());
                cutPriceOrder.setReceiverName(combineOrder.getReceiverName());
                cutPriceOrder.setReceiverPhone(combineOrder.getReceiverPhone());
                cutPriceOrder.setReceiverAddress(combineOrder.getReceiverAddress());
                cutPriceOrder.setUpdateBy(memberId);
                cutPriceOrder.setUpdateDate(currentDate);
                updateByPrimaryKeySelective(cutPriceOrder);
                cutPriceOrderLogService.addCutPriceOrderLog(memberId, cutPriceOrder.getId(), "4", "下单成功");
            }
        }
        map.put("returnCode", returnCode);
        map.put("returnMsg", returnMsg);
        map.put("preferentialAmount", preferentialAmount);
        return map;
    }

    public void updateActivationCutOrder(Integer cutOrderId, String activityType) {
        CutPriceOrderExample cutPriceOrderExample = new CutPriceOrderExample();
        cutPriceOrderExample.createCriteria().andStatusEqualTo("0").andIdEqualTo(cutOrderId).andDelFlagEqualTo("0");
        List<CutPriceOrder> orders = selectByExample(cutPriceOrderExample);
        if (CollectionUtils.isNotEmpty(orders)) {
            Date currentDate = new Date();
            CutPriceOrder order = orders.get(0);
            com.jf.entity.Product p = productService.selectByPrimaryKey(order.getProductId());
            productItemService.updateSkuLockedAmount(order.getProductItemId(), 1, p.getMchtId(), "1");
            String status = "1";
            order.setStatus(status);
            order.setUpdateBy(order.getMemberId());
            order.setUpdateDate(currentDate);
            int count = updateByExampleSelective(order, cutPriceOrderExample);
            if (count <= 0) {
                throw new ArgException("系统异常，请稍后再试");
            }
            cutPriceOrderLogService.addCutPriceOrderLog(order.getMemberId(), cutOrderId, status, "助力大减价激活成功");
        } else {
            throw new ArgException("请先领取商品减价任务");
        }
    }

    private List<CutPriceOrder> findModelByStatus(List<String> statusList, Integer cutOrderId) {
        CutPriceOrderExample cutPriceOrderExample = new CutPriceOrderExample();
        cutPriceOrderExample.createCriteria().andStatusIn(statusList).andIdEqualTo(cutOrderId).andDelFlagEqualTo("0");
        return selectByExample(cutPriceOrderExample);
    }

    public List<AssistanceCutCompleteLogView> findAssistanceCutCompleteLog(FindAssistanceCutCompleteLogRequest request) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("activityType", Const.PRODUCT_ACTIVITY_TYPE_ASSISTANCE_CUT_PRICE);
        params.put("offset", request.getOffset());
        params.put("fetchSize", request.getPageSize());
        params.put("productId", request.getProductId());
        List<CutPriceOrderDTO> dtoList = cutPriceOrderCustomMapper.findAssistanceCutCompleteLog(params);
        if (CollectionUtils.isEmpty(dtoList)) return Collections.emptyList();

        List<AssistanceCutCompleteLogView> viewList = Lists.newArrayListWithCapacity(dtoList.size());
        for (CutPriceOrderDTO dto : dtoList) {
            String wxHead = dto.getWeixinHead();
            if (!StringUtils.hasText(wxHead)) {
                wxHead = dto.getPic();
            }
            AssistanceCutCompleteLogView view = new AssistanceCutCompleteLogView();
            view.setNickName(dto.getMemberNick());
            view.setWxHead(StringUtil.getPic(wxHead, ""));
            view.setFinalPrice(dto.getPayAmount());
            if (dto.getUpdateDate() != null) {
                view.setUpdateDate(dto.getUpdateDate().getTime());
            } else {
                view.setUpdateDate(dto.getCreateDate().getTime());
            }
            viewList.add(view);
        }
        return viewList;
    }
}
