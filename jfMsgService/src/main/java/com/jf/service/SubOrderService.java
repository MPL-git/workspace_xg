package com.jf.service;

import com.google.common.collect.Maps;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DateUtil;
import com.jf.dao.CommentMapper;
import com.jf.dao.CustomerServiceStatusLogMapper;
import com.jf.dao.InterventionOrderMapper;
import com.jf.dao.MchtDepositDtlMapper;
import com.jf.dao.MchtDepositMapper;
import com.jf.dao.MsgTplMapper;
import com.jf.dao.PlatformMsgMapper;
import com.jf.dao.ShopScoreMapper;
import com.jf.dao.SubOrderCustomMapper;
import com.jf.dao.SubOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.Comment;
import com.jf.entity.CustomerServiceLog;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderExample;
import com.jf.entity.CustomerServiceStatusLog;
import com.jf.entity.CustomerServiceStatusLogExample;
import com.jf.entity.IntegralDtl;
import com.jf.entity.InterventionOrder;
import com.jf.entity.InterventionOrderExample;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositDtl;
import com.jf.entity.MchtDepositExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MsgTpl;
import com.jf.entity.MsgTplExample;
import com.jf.entity.OrderAbnormalLog;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.PlatformMsg;
import com.jf.entity.RefundOrder;
import com.jf.entity.RefundOrderExample;
import com.jf.entity.ShopScore;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderCustom;
import com.jf.entity.SubOrderExample;
import com.jf.entity.ViolateOrder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SubOrderService extends BaseService<SubOrder, SubOrderExample> {

    private static Logger logger = LoggerFactory.getLogger(SubOrderService.class);

    @Autowired
    private SubOrderMapper dao;

    @Autowired
    private OrderDtlService orderDtlService;
    @Autowired
    private CustomerServiceOrderService customerServiceOrderService;
    @Autowired
    private ViolateOrderService violateOrderService;
    @Autowired
    private OrderLogService orderLogService;
    @Autowired
    private MchtDepositMapper mchtDepositMapper;
    @Autowired
    private MchtDepositDtlMapper mchtDepositDtlMapper;

    @Autowired
    private SubOrderCustomMapper subOrderCustomMapper;

    @Autowired
    private SubOrderMapper subOrderMapper;

    @Autowired
    private PlatformMsgMapper platformMsgMapper;

    @Autowired
    private MsgTplMapper msgTplMapper;

    @Autowired
    private InterventionOrderMapper interventionOrderMapper;

    @Autowired
    private CombineOrderService combineOrderService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ShopScoreMapper shopScoreMapper;

    @Autowired
    private OrderAbnormalLogService orderAbnormalLogService;

    @Autowired
    private CustomerServiceStatusLogMapper customerServiceStatusLogMapper;

    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private RefundOrderService refundOrderService;

    @Autowired
    private MemberAccountService memberAccountService;

    @Autowired
    private CustomerServiceStatusLogService customerServiceStatusLogService;

    @Autowired
    private CustomerServiceLogService customerServiceLogService;

    @Autowired
    private IntegralDtlService integralDtlService;


    @Autowired
    public void setSubOrderMapper(SubOrderMapper subOrderMapper) {
        this.setDao(subOrderMapper);
        this.subOrderMapper = subOrderMapper;
    }

    public List<SubOrderCustom> selectCollegeStudentOrderList(String auditStatus) {
        return subOrderCustomMapper.selectCollegeStudentOrderList(auditStatus);
    }

    /**
     * 订单超时未付款处理
     *
     * @param combineOrderId the combile order id
     */
    public void closeByCombileOrderId(int combineOrderId) {
        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("combineOrderId", combineOrderId);
        List<SubOrder> subOrderList = findList(queryObject);
        for (SubOrder subOrder : subOrderList) {
            close(subOrder);
            //订单超时关闭，商品sku数量恢复
            orderDtlService.updateSku(subOrder);
        }
    }

    /**
     * 订单关闭
     *
     * @param model the model
     */
    public void close(SubOrder model) {
        model.setStatus(Const.ORDER_STATUS_CANCEL);
        model.setStatusDate(new Date());
        update(model);

        // 记录订单状态流水
        orderLogService.save(model.getId(), model.getStatus(), "订单超时未付款，系统自动关闭");
    }


    /**
     * 订单超时未发货处理
     *
     * @param combineOrderId the combile order id
     * @throws ParseException
     */
    public void deliveryOverDueByCombileOrderId(int combineOrderId) throws ParseException {
        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("combineOrderId", combineOrderId);
        List<SubOrder> subOrderList = findList(queryObject);
        for (SubOrder subOrder : subOrderList) {
            deliveryOverDue(subOrder);
        }
    }

    /**
     * 生成违规单
     *
     * @param model the model
     * @throws ParseException
     */
    public void deliveryOverDue(SubOrder model) throws ParseException {


        CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(model.getCombineOrderId());
        MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(combineOrder.getMemberId());
        if ("P".equals(memberInfo.getStatus())) {//黑名单用户下的单无需生成违规单
            return;
        }
        Date now = new Date();
        ViolateOrder violateOrder = new ViolateOrder();
        violateOrder.setOrderCode("WG" + CommonUtil.getOrderCode());    //违规单号
        violateOrder.setMchtId(model.getMchtId());
        violateOrder.setSubOrderId(model.getId());
        violateOrder.setViolateType(Const.VIOLATE_ORDER_TYPE_D);
        violateOrder.setViolateAction(Const.VIOLATE_ORDER_ACTION_D3);
        violateOrder.setContent("商家应在收到醒购订单详情后" + model.getDeliveryOvertime() + "小时内（包括周六、周日、节假日）发货，超过" + model.getDeliveryOvertime() + "小时视为延迟发货");    //违规详情
        Date date = combineOrder.getPayDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date limitDate = sdf.parse("2019-01-01 00:00:00");
        if (date.before(limitDate)) {
            violateOrder.setMoney(new BigDecimal(20));    //违约金
            violateOrder.setJifenIntegral(1000);
        } else {
            OrderDtlExample ode = new OrderDtlExample();
            ode.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(model.getId());
            List<OrderDtl> orderDtls = orderDtlService.selectByExample(ode);
            List<Integer> orderDtlIds = new ArrayList<Integer>();
            for (OrderDtl orderDtl : orderDtls) {
                orderDtlIds.add(orderDtl.getId());
            }
            CustomerServiceOrderExample csoe = new CustomerServiceOrderExample();
            csoe.createCriteria().andDelFlagEqualTo("0").andOrderDtlIdIn(orderDtlIds);
            List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(csoe);
            List<Integer> customerServiceOrderIds = new ArrayList<Integer>();
            for (CustomerServiceOrder customerServiceOrder : customerServiceOrders) {
                customerServiceOrderIds.add(customerServiceOrder.getId());
            }
            List<RefundOrder> refundOrderList = new ArrayList<RefundOrder>();//无重复退款单的集合
            HashMap<Integer, RefundOrder> map = new HashMap<Integer, RefundOrder>();
            BigDecimal refundAmount = new BigDecimal(0);
            if (customerServiceOrderIds != null && customerServiceOrderIds.size() > 0) {
                RefundOrderExample roe = new RefundOrderExample();
                roe.createCriteria().andDelFlagEqualTo("0").andServiceOrderIdIn(customerServiceOrderIds).andOrderTypeEqualTo("1");
                List<RefundOrder> refundOrders = refundOrderService.selectByExample(roe);
                for (RefundOrder refundOrder : refundOrders) {
                    if (!map.containsKey(refundOrder.getServiceOrderId())) {
                        map.put(refundOrder.getServiceOrderId(), refundOrder);
                        refundOrderList.add(refundOrder);
                    }
                }
                for (RefundOrder refundOrder : refundOrderList) {
                    refundAmount = refundAmount.add(refundOrder.getRefundAmount());
                }
            }
            BigDecimal realAmount = model.getPayAmount().subtract(refundAmount);
            if (realAmount.compareTo(new BigDecimal(0)) < 0) {
                realAmount = new BigDecimal(0);
            }
            violateOrder.setMoney(realAmount.multiply(new BigDecimal(0.05 + "")));
            violateOrder.setJifenIntegral(realAmount.multiply(new BigDecimal(0.025 + "")).multiply(new BigDecimal(100)).intValue());
            violateOrder.setEnableHours("72小时");
        }
        violateOrder.setAuditStatus(Const.VIOLATE_ORDER_AUDIT_STATUS_PASS);//系统生成的违规单直接通过，不用审核
        violateOrder.setAuditDate(now);
        violateOrder.setViolateDate(now);
        violateOrder.setPunishMeans("每单按实际付款订单额的5%扣款，补偿用户实际付款订单额2.5%的金额对应的积分。");    //处罚方式
        violateOrder.setOrderSource(Const.VIOLATE_ORDER_SOURCE_SYSTEM);
        violateOrder.setStatus(Const.VIOLATE_ORDER_STATUS_NOT_APPEAL);    //未申诉
        violateOrder.setStatusDate(new Date());
        violateOrder.setComplainInfoStatus("1");
        violateOrder.setComplainEndDate(DateUtil.getDateAfter(now, 3));
        violateOrder.setJifenStatus("0");//未补偿
        violateOrder.setCreateDate(now);
        violateOrder.setUpdateDate(now);
        violateOrder.setDelFlag("0");
        violateOrderService.save(violateOrder);

        //发送站内信
        PlatformMsg platformMsg = new PlatformMsg();
        platformMsg.setMchtId(model.getMchtId());
        platformMsg.setMsgType("A5");
        platformMsg.setTitle("关于超时发货违规通知");
        String msgContent = "您好！您因违反醒购网相关规范，现平台对您做出相关处罚，违规编码：" + violateOrder.getOrderCode() + "，请尽快申诉，申诉截止时间：" + DateUtil.getStandardDateTime(violateOrder.getComplainEndDate());
        platformMsg.setContent(msgContent);
        platformMsg.setBizId(violateOrder.getId());
        platformMsg.setStatus("0");
        platformMsg.setCreateDate(new Date());
        platformMsg.setRemarks("系统自动生成");
        platformMsgMapper.insertSelective(platformMsg);

        //将子订单的超时发货标志置为1
//		SubOrder subOrder4Update=new SubOrder();
//		subOrder4Update.setId(model.getId());
//		subOrder4Update.setIsDeliveryOvertime("1");
//		subOrderMapper.updateByPrimaryKeySelective(subOrder4Update);

        //插入一条超时发货标志
        OrderAbnormalLog orderAbnormalLog4insert = new OrderAbnormalLog();
        orderAbnormalLog4insert.setSubOrderId(model.getId());
        orderAbnormalLog4insert.setAbnormalType("3");
        orderAbnormalLog4insert.setDelFlag("0");
        orderAbnormalLog4insert.setFollowStatus("1");
        orderAbnormalLog4insert.setAbnormalDesc("超时发货");
        orderAbnormalLog4insert.setCreateDate(new Date());
        orderAbnormalLogService.insertSelective(orderAbnormalLog4insert);

        // todo 扣除商家保证金
        MchtDepositExample example = new MchtDepositExample();
        MchtDepositExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo("0");
        criteria.andMchtIdEqualTo(violateOrder.getMchtId());
        List<MchtDeposit> mchtDeposits = mchtDepositMapper.selectByExample(example);
        if (mchtDeposits != null && mchtDeposits.size() > 0) {
            MchtDeposit mchtDeposit = mchtDeposits.get(0);
            MchtDepositDtl mchtDepositDtl = new MchtDepositDtl();
            if (violateOrder.getMoney() != null) {
                mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().subtract(violateOrder.getMoney()));
                mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().add(violateOrder.getMoney()));
            } else {
                mchtDeposit.setPayAmt(mchtDeposit.getPayAmt());
                mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt());
            }
            mchtDeposit.setUpdateDate(now);
            mchtDepositDtl.setDelFlag("0");
            mchtDepositDtl.setDepositId(mchtDeposit.getId());
            mchtDepositDtl.setTxnType("E");//违规扣款
            mchtDepositDtl.setTypeSub("E4");//售后
            if (violateOrder.getMoney() != null) {
                mchtDepositDtl.setTxnAmt(violateOrder.getMoney().negate());
            } else {
                mchtDepositDtl.setTxnAmt(new BigDecimal(0));
            }
            mchtDepositDtl.setPayAmt(mchtDeposit.getPayAmt());
            mchtDepositDtl.setBizType("2");//违规单
            mchtDepositDtl.setBizId(violateOrder.getId());
            mchtDepositDtl.setCreateDate(now);
            mchtDepositDtl.setUpdateDate(now);
            mchtDepositDtl.setRemarks("【售后】延迟发货");
            mchtDepositMapper.updateByPrimaryKeySelective(mchtDeposit);
            mchtDepositDtlMapper.insertSelective(mchtDepositDtl);
        }
    }

    /**
     * 订单超时未确认收货系统自动确认收货
     *
     * @param model the sub order
     */
    public void receipt(SubOrder model) {
        Date date = new Date();
        //有进行中的售后单时，不自动确认收货
        QueryObject customerServiceOrderQuery = new QueryObject();
        customerServiceOrderQuery.addQuery("subOrderId", model.getId());
        customerServiceOrderQuery.addQuery("status", Const.CUSTOMER_ORDER_STATUS_REFUNDING);
        List<CustomerServiceOrder> customerServiceOrderList = customerServiceOrderService.findList(customerServiceOrderQuery);
        if (customerServiceOrderList.size() > 0) {
            logger.warn("订单自动确认收货时发现售后中，暂不自动确认收货,订单ID：{}", model.getId());
            return;
        }
        if (orderDtlService.existsUnDeliveryDtl(model.getId())) {
            logger.warn("订单自动确认收货时发现存在未发货的商品明细，暂不自动确认收货,订单ID：{}", model.getId());
            return;
        }
        //售后单拒绝状态下有介入单时，不自动确认收货
        boolean hasInterventionOrder = false;
        QueryObject qb = new QueryObject();
        qb.addQuery("subOrderId", model.getId());
        qb.addQuery("status", Const.CUSTOMER_ORDER_STATUS_REJECT);
        List<String> serviceTypeList = new ArrayList<String>();
        serviceTypeList.add("B");
        serviceTypeList.add("C");
        qb.addQuery("serviceTypeIn", serviceTypeList);
        List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.findList(qb);
        if (customerServiceOrders != null && customerServiceOrders.size() > 0) {
            for (CustomerServiceOrder cso : customerServiceOrders) {
                InterventionOrderExample ioe = new InterventionOrderExample();
                InterventionOrderExample.Criteria ioec = ioe.createCriteria();
                ioec.andDelFlagEqualTo("0");
                ioec.andServiceOrderIdEqualTo(cso.getId());
                ioec.andProStatusEqualTo(cso.getProStatus());
                List<InterventionOrder> interventionOrders = interventionOrderMapper.selectByExample(ioe);
                if (interventionOrders != null && interventionOrders.size() > 0) {
                    if (!interventionOrders.get(0).getStatus().equals("8")) {//有介入单且状态不是已结案
                        logger.warn("订单自动确认收货时发现有不是已结案的介入单，暂不自动确认收货,订单ID：{}", model.getId());
                        hasInterventionOrder = true;
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        if (hasInterventionOrder) {
            return;
        }
        // 确认收货
        model.setStatus(Const.ORDER_STATUS_RECEIPT);
        model.setIsComment("0");
        model.setReceiptDate(date);
        model.setReceiverType("2");//系统
        model.setStatusDate(date);
        update(model);

        // 记录订单状态流水
        orderLogService.save(model.getId(), model.getStatus(), "订单超时未确认收货系统自动确认收货");
    }

    /**
     * 订单完成
     *
     * @param model the sub order
     */
    public void complateOrder(SubOrder model) {
        Date date = new Date();
        //退款，退货完成的总金额，计算积分赠送，需扣除售后完成的总金额
        BigDecimal customerServiceOrderAmount = new BigDecimal("0");
        //有进行中的售后单时，不自动完成
        QueryObject customerServiceOrderQuery = new QueryObject();
        customerServiceOrderQuery.addQuery("subOrderId", model.getId());
        customerServiceOrderQuery.addQuery("status", Const.CUSTOMER_ORDER_STATUS_REFUNDING);
        List<String> customerServiceOrderStatusList = new ArrayList<String>();
        List<CustomerServiceOrder> customerServiceOrderList = customerServiceOrderService.findList(customerServiceOrderQuery);
        if (customerServiceOrderList.size() > 0) {
            logger.warn("订单自动完成时发现售后中，暂不自动完成,订单ID：{}", model.getId());
            return;
        }
        //售后单拒绝状态下有介入单时，不自动完成
        boolean hasInterventionOrder = false;
        QueryObject qb = new QueryObject();
        qb.addQuery("subOrderId", model.getId());
        qb.addQuery("status", Const.CUSTOMER_ORDER_STATUS_REJECT);
        List<String> serviceTypeList = new ArrayList<String>();
        serviceTypeList.add("A");
        serviceTypeList.add("B");
        serviceTypeList.add("C");
        qb.addQuery("serviceTypeIn", serviceTypeList);
        List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.findList(qb);
        if (customerServiceOrders != null && customerServiceOrders.size() > 0) {
            for (CustomerServiceOrder cso : customerServiceOrders) {
                if (cso.getServiceType().equals("B") || cso.getServiceType().equals("C")) {
                    //售后单拒绝时间如果是收货确认第5天之后，延长两天可以申请售后（即先不进行订单完成）
                    CustomerServiceStatusLogExample customerServiceStatusLogExample = new CustomerServiceStatusLogExample();
                    customerServiceStatusLogExample.createCriteria().andDelFlagEqualTo("0").andServiceOrderIdEqualTo(cso.getId()).andStatusEqualTo(Const.CUSTOMER_ORDER_STATUS_REJECT);
                    customerServiceStatusLogExample.setOrderByClause(" id desc");
                    List<CustomerServiceStatusLog> customerServiceStatusLogs = customerServiceStatusLogMapper.selectByExample(customerServiceStatusLogExample);

                    if (customerServiceStatusLogs != null && customerServiceStatusLogs.size() > 0) {
                        Date rejectDate = customerServiceStatusLogs.get(0).getCreateDate();
                        Date now = new Date();
                        if (rejectDate.getTime() >= DateUtil.getDateAfter(model.getReceiptDate(), 5).getTime() && now.getTime() <= DateUtil.getDateAfter(rejectDate, 2).getTime()) {
                            logger.warn("订单自动完成时发现商家拒绝售后，延长两天可申请售后,订单ID：{}", model.getId());
                            return;
                        }
                    }

                    InterventionOrderExample ioe = new InterventionOrderExample();
                    InterventionOrderExample.Criteria ioec = ioe.createCriteria();
                    ioec.andDelFlagEqualTo("0");
                    ioec.andServiceOrderIdEqualTo(cso.getId());
                    ioec.andProStatusEqualTo(cso.getProStatus());
                    List<InterventionOrder> interventionOrders = interventionOrderMapper.selectByExample(ioe);
                    if (interventionOrders != null && interventionOrders.size() > 0) {
                        if (!interventionOrders.get(0).getStatus().equals("8")) {//有介入单且状态不是已结案
                            logger.warn("订单自动完成时发现有不是已结案的介入单，暂不自动完成,订单ID：{}", model.getId());
                            hasInterventionOrder = true;
                            break;
                        }
                    } else {
                        continue;
                    }
                }
                if (cso.getServiceType().equals("A") || cso.getServiceType().equals("B")) {
                    if (cso.getStatus().equals(Const.CUSTOMER_ORDER_STATUS_SUCCESS)) {
                        customerServiceOrderAmount = customerServiceOrderAmount.add(cso.getAmount());
                    }
                }
            }
        }
        if (hasInterventionOrder) {
            return;
        }
        // 订单完成
        model.setStatus(Const.ORDER_STATUS_SUCCESS);
        model.setCompleteDate(date);
        model.setStatusDate(date);
        update(model);

        // 明细表商品状态改为完成
        orderDtlService.complateBySubOrderId(model, customerServiceOrderAmount);


        // 记录订单状态流水
        orderLogService.save(model.getId(), model.getStatus(), "确认收货7天后，订单自动完成");

        //首单赠送用户3元优惠券
        memberAccountService.rewardSuperiorUser1(model);
    }


    public SubOrder findById(int id) {
        return dao.selectByPrimaryKey(id);
    }

    public SubOrder save(SubOrder model) {
        model.setCreateDate(new Date());
        dao.insertSelective(model);
        return model;
    }

    public SubOrder update(SubOrder model) {
        model.setUpdateDate(new Date());
        dao.updateByPrimaryKeySelective(model);
        return model;
    }

    public void delete(int id) {
        SubOrder model = findById(id);
        if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
            throw new BizException("ID为[" + id + "]的数据不存在");
        }
        model.setDelFlag(Const.FLAG_TRUE);
        update(model);
    }

    public int count(QueryObject queryObject) {
        return dao.countByExample(builderQuery(queryObject));
    }

    public List<SubOrder> findList(QueryObject queryObject) {
        return dao.selectByExample(builderQuery(queryObject));
    }

    public Page<SubOrder> paginate(QueryObject queryObject) {
        SubOrderExample example = builderQuery(queryObject);
        example.setLimitStart(queryObject.getLimitStart());
        example.setLimitSize(queryObject.getPageSize());

        List<SubOrder> list = dao.selectByExample(example);
        int totalCount = dao.countByExample(example);
        return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
    }

    private SubOrderExample builderQuery(QueryObject queryObject) {
        SubOrderExample example = new SubOrderExample();
        SubOrderExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
        if (queryObject.getQuery("idNotIn") != null) {
            List<Integer> idNotIn = queryObject.getQuery("idNotIn");
            criteria.andIdNotIn(idNotIn);
        }
        if (queryObject.getQuery("combineOrderId") != null) {
            criteria.andCombineOrderIdEqualTo(queryObject.getQueryToInt("combineOrderId"));
        }
        if (queryObject.getQuery("status") != null) {
            criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
        }
        if (queryObject.getQuery("expressId") != null) {
            criteria.andExpressIdEqualTo(queryObject.getQueryToStr("expressId"));
        }
        if (queryObject.getQuery("expressNo") != null) {
            criteria.andExpressNoEqualTo(queryObject.getQueryToStr("expressNo"));
        }
        // 下单超过多少小时的订单
        if (queryObject.getQuery("createDateBeforeHours") != null) {
            int createDateBeforeHours = queryObject.getQueryToInt("createDateBeforeHours");
            criteria.andCreateDateLessThan(DateTime.now().minusHours(createDateBeforeHours).toDate());
        }
        // 发货超过多少天的订单
        if (queryObject.getQuery("deliveryDateBeforDays") != null) {
            int deliveryDateBeforDays = queryObject.getQueryToInt("deliveryDateBeforDays");
            criteria.andDeliveryDateLessThan(DateTime.now().minusDays(deliveryDateBeforDays).toDate());
        }
        // 发货超过多少分钟的订单
        if (queryObject.getQuery("deliveryDateBeforMinutes") != null) {
            int deliveryDateBeforMinutes = queryObject.getQueryToInt("deliveryDateBeforMinutes");
            criteria.andDeliveryDateLessThan(DateTime.now().minusMinutes(deliveryDateBeforMinutes).toDate());
        }
        // 当status='1'时，即为付款时间超过多少小时的订单
        if (queryObject.getQuery("statusDateBeforeHours") != null) {
            int statusDateBeforeHours = queryObject.getQueryToInt("statusDateBeforeHours");
            criteria.andStatusDateLessThan(DateTime.now().minusHours(statusDateBeforeHours).toDate());
        }
        // 只对付款时间大于等于2017-12-01的子订单自动生成违规单，这时间之前的订单已人工生成了。
        if (queryObject.getQuery("statusDateAfter") != null) {
            Date statusDateAfter = queryObject.getQuery("statusDateAfter");
            criteria.andStatusDateGreaterThanOrEqualTo(statusDateAfter);
        }
        if (queryObject.getQuery("isComment") != null) {
            criteria.andIsCommentEqualTo(queryObject.getQueryToStr("isComment"));
        }
        if (queryObject.getQuery("receiptDateLessThan") != null) {
            criteria.andReceiptDateLessThan((Date) queryObject.getQuery("receiptDateLessThan"));
        }
        // 订单完成超过多少天的订单
        if (queryObject.getQuery("completeDateBeforDays") != null) {
            int completeDateBeforDays = queryObject.getQueryToInt("completeDateBeforDays");
            criteria.andCompleteDateLessThan(DateTime.now().minusDays(completeDateBeforDays).toDate());
        }
        if (queryObject.getQuery("statusIn") != null) {
            List<String> statusIn = queryObject.getQuery("statusIn");
            criteria.andStatusIn(statusIn);
        }
        if (queryObject.getSort().size() > 0) {
            example.setOrderByClause(queryObject.getSortString());
        }
        return example;
    }

    public List<SubOrder> findListOfDeliveryOverDue() {
		/*
         List<SubOrder> subOrders=new ArrayList<SubOrder>();
		//根据配置表查出超时发货为生成违规单的子订单
         List<SubOrder> subOrders1 =subOrderCustomMapper.selectOverTimeDeliveryListByCnf();
         List<SubOrder> subOrders2 =subOrderCustomMapper.selectOverTimeDeliveryListByDefault();
         if(subOrders1!=null&&subOrders1.size()>0){
        	 subOrders.addAll(subOrders1);
         }
         if(subOrders2!=null&&subOrders2.size()>0){
        	 subOrders.addAll(subOrders2);
         }
         
         return subOrders;
		*/
        List<SubOrder> subOrders = subOrderCustomMapper.selectOverTimeDeliveryList();
        return subOrders;
    }


    /**
     * 批量插入缺货异常记录
     *
     * @return
     */
    public int batchInsertOutOfStock() {
        int count = subOrderCustomMapper.batchInsertOutOfStock();
        return count;
    }


    /**
     * 查询虚假发货的子订单
     *
     * @return
     */
    public List<SubOrder> selectShamDeliveryList(Date bgnDeliveryDate) {
        return subOrderCustomMapper.selectShamDeliveryList(bgnDeliveryDate);
    }

    /**
     * 查询虚假发货未生成违规单的子订单
     *
     * @return
     */
    public List<SubOrder> selectShamDeliveryOrderWithNoViolateOrder() {
        return subOrderCustomMapper.selectShamDeliveryOrderWithNoViolateOrder();
    }

    /**
     * 查询缺货未生成违规单的子订单
     *
     * @return
     */
    public List<SubOrder> selectOutOfStockOrderWithNoViolateOrder() {
        return subOrderCustomMapper.selectOutOfStockOrderWithNoViolateOrder();
    }

    /**
     * 批量插入虚假发货
     *
     * @return
     */
    public int batchInsertShamDelivery(Date bgnDeliveryDate) {
        return subOrderCustomMapper.batchInsertShamDelivery(bgnDeliveryDate);
    }

    /**
     * 订单标记为虚假发货
     *
     * @return
     */
    public void shamDelivery(SubOrder subOrder) {
//	   SubOrder subOrder4Update=new SubOrder();
//	   subOrder4Update.setId(subOrder.getId());
//	   subOrder4Update.setIsShamDelivery("1");
//	   subOrder4Update.setUpdateDate(new Date());
//	   subOrderMapper.updateByPrimaryKeySelective(subOrder4Update);


    }

    /**
     * 虚假发货生成违规单
     *
     * @return
     * @throws ParseException
     */
    public void shamDeliveryOrderCreateViolateOrder(SubOrder subOrder) throws ParseException {
        if ("5".equals(subOrder.getStatus())) {//已关闭的订单不生成违规单
            return;
        }
        Date now = new Date();
        ViolateOrder violateOrder = new ViolateOrder();
        violateOrder.setOrderCode("WG" + CommonUtil.getOrderCode());    //违规单号
        violateOrder.setMchtId(subOrder.getMchtId());
        violateOrder.setSubOrderId(subOrder.getId());
        violateOrder.setViolateType(Const.VIOLATE_ORDER_TYPE_D);
        violateOrder.setViolateAction(Const.VIOLATE_ORDER_ACTION_D1);
        violateOrder.setContent("指商家填写快递单号并确认订单发货后48小时内无真实有效物流信息，包括但不限于无揽件跟踪信息或快递跟踪信息明显异常的已发货订单视为虚假发货。");    //违规详情
        CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
        Date date = combineOrder.getPayDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date limitDate = sdf.parse("2019-01-01 00:00:00");
        if (date.before(limitDate)) {
            violateOrder.setMoney(new BigDecimal(70));    //违约金
            violateOrder.setAuditStatus(Const.VIOLATE_ORDER_AUDIT_STATUS_PASS);//系统生成的违规单直接通过，不用审核
            violateOrder.setAuditDate(new Date());
            violateOrder.setJifenIntegral(4000);
            violateOrder.setComplainEndDate(DateUtil.getDateAfter(now, 3));
        } else {
            OrderDtlExample ode = new OrderDtlExample();
            ode.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrder.getId());
            List<OrderDtl> orderDtls = orderDtlService.selectByExample(ode);
            List<Integer> orderDtlIds = new ArrayList<Integer>();
            for (OrderDtl orderDtl : orderDtls) {
                orderDtlIds.add(orderDtl.getId());
            }
            CustomerServiceOrderExample csoe = new CustomerServiceOrderExample();
            csoe.createCriteria().andDelFlagEqualTo("0").andOrderDtlIdIn(orderDtlIds);
            List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(csoe);
            List<Integer> customerServiceOrderIds = new ArrayList<Integer>();
            for (CustomerServiceOrder customerServiceOrder : customerServiceOrders) {
                customerServiceOrderIds.add(customerServiceOrder.getId());
            }
            List<RefundOrder> refundOrderList = new ArrayList<RefundOrder>();//无重复退款单的集合
            HashMap<Integer, RefundOrder> map = new HashMap<Integer, RefundOrder>();
            BigDecimal refundAmount = new BigDecimal(0);
            if (customerServiceOrderIds != null && customerServiceOrderIds.size() > 0) {
                RefundOrderExample roe = new RefundOrderExample();
                roe.createCriteria().andDelFlagEqualTo("0").andServiceOrderIdIn(customerServiceOrderIds).andOrderTypeEqualTo("1");
                List<RefundOrder> refundOrders = refundOrderService.selectByExample(roe);
                for (RefundOrder refundOrder : refundOrders) {
                    if (!map.containsKey(refundOrder.getServiceOrderId())) {
                        map.put(refundOrder.getServiceOrderId(), refundOrder);
                        refundOrderList.add(refundOrder);
                    }
                }
                for (RefundOrder refundOrder : refundOrderList) {
                    refundAmount = refundAmount.add(refundOrder.getRefundAmount());
                }
            }
            BigDecimal realAmount = subOrder.getPayAmount().subtract(refundAmount);
            if (realAmount.compareTo(new BigDecimal(0)) < 0) {
                realAmount = new BigDecimal(0);
            }
            violateOrder.setMoney(realAmount.multiply(new BigDecimal(0.3 + "")));
            violateOrder.setAuditStatus(Const.VIOLATE_ORDER_AUDIT_STATUS_WAIT);
            violateOrder.setJifenIntegral(realAmount.multiply(new BigDecimal(0.15 + "")).multiply(new BigDecimal(100)).intValue());
            violateOrder.setEnableHours("72小时");
        }

        violateOrder.setViolateDate(now);
        violateOrder.setPunishMeans("每单按实际付款订单额的30%扣款，补偿用户实际付款订单额15%的金额对应的积分。");    //处罚方式
        violateOrder.setOrderSource(Const.VIOLATE_ORDER_SOURCE_SYSTEM);
        violateOrder.setStatus(Const.VIOLATE_ORDER_STATUS_NOT_APPEAL);    //未申诉
        violateOrder.setStatusDate(new Date());
        violateOrder.setComplainInfoStatus("1");
        violateOrder.setJifenStatus("0");//未补偿
        violateOrder.setCreateDate(now);
        violateOrder.setUpdateDate(now);
        violateOrder.setDelFlag("0");
        violateOrderService.save(violateOrder);
        if (date.before(limitDate)) {
            //发送站内信
            MsgTplExample msgTplExample = new MsgTplExample();
            msgTplExample.createCriteria().andDelFlagEqualTo("0").andTplTypeEqualTo("A").andMsgTypeEqualTo("A5");
            List<MsgTpl> msgTpls = msgTplMapper.selectByExample(msgTplExample);
            if (msgTpls != null && msgTpls.size() > 0) {
                MsgTpl msgTpl = msgTpls.get(0);

                PlatformMsg platformMsg = new PlatformMsg();
                platformMsg.setMchtId(subOrder.getMchtId());
                platformMsg.setMsgType("A5");
                platformMsg.setTitle("关于虚假发货违规通知");
                String msgContent = msgTpl.getContent().replace("{violation_code}", violateOrder.getOrderCode()).replace("{end_date}", DateUtil.getStandardDateTime(violateOrder.getComplainEndDate()));
                platformMsg.setContent(msgContent);
                platformMsg.setBizId(violateOrder.getId());
                platformMsg.setStatus("0");
                platformMsg.setCreateDate(new Date());
                platformMsg.setRemarks("系统自动生成");
                platformMsgMapper.insertSelective(platformMsg);
            }
            // todo 扣除商家保证金
            MchtDepositExample example = new MchtDepositExample();
            MchtDepositExample.Criteria criteria = example.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andMchtIdEqualTo(violateOrder.getMchtId());
            List<MchtDeposit> mchtDeposits = mchtDepositMapper.selectByExample(example);
            if (mchtDeposits != null && mchtDeposits.size() > 0) {
                MchtDeposit mchtDeposit = mchtDeposits.get(0);
                MchtDepositDtl mchtDepositDtl = new MchtDepositDtl();
                if (violateOrder.getMoney() != null) {
                    mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().subtract(violateOrder.getMoney()));
                    mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().add(violateOrder.getMoney()));
                } else {
                    mchtDeposit.setPayAmt(mchtDeposit.getPayAmt());
                    mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt());
                }
                mchtDeposit.setUpdateDate(now);
                mchtDepositDtl.setDelFlag("0");
                mchtDepositDtl.setDepositId(mchtDeposit.getId());
                mchtDepositDtl.setTxnType("E");//违规扣款
                mchtDepositDtl.setTypeSub("E4");//售后
                if (violateOrder.getMoney() != null) {
                    mchtDepositDtl.setTxnAmt(violateOrder.getMoney().negate());
                } else {
                    mchtDepositDtl.setTxnAmt(new BigDecimal(0));
                }
                mchtDepositDtl.setPayAmt(mchtDeposit.getPayAmt());
                mchtDepositDtl.setBizType("2");//违规单
                mchtDepositDtl.setBizId(violateOrder.getId());
                mchtDepositDtl.setCreateDate(now);
                mchtDepositDtl.setUpdateDate(now);
                mchtDepositDtl.setRemarks("【售后】虚假发货(系统自动扣除) ");
                mchtDepositMapper.updateByPrimaryKeySelective(mchtDeposit);
                mchtDepositDtlMapper.insertSelective(mchtDepositDtl);
            }
        }

    }

    /**
     * 生成默认好评
     *
     * @param
     */
    public void autoComment(SubOrder subOrder) {
        CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
        OrderDtlExample ode = new OrderDtlExample();
        OrderDtlExample.Criteria odec = ode.createCriteria();
        odec.andDelFlagEqualTo("0");
        odec.andSubOrderIdEqualTo(subOrder.getId());
        List<OrderDtl> orderDtls = orderDtlService.selectByExample(ode);
        boolean isComment = false;
        for (OrderDtl orderDtl : orderDtls) {
            CustomerServiceOrderExample example = new CustomerServiceOrderExample();
            CustomerServiceOrderExample.Criteria c = example.createCriteria();
            c.andDelFlagEqualTo("0");
            c.andSubOrderIdEqualTo(subOrder.getId());
            c.andOrderDtlIdEqualTo(orderDtl.getId());
            List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(example);
            boolean toComment = false;
            if (customerServiceOrders != null && customerServiceOrders.size() > 0) {
                if (customerServiceOrders.get(0).getStatus().equals("2") || customerServiceOrders.get(0).getStatus().equals("3")) {//2.拒绝 3.撤销
                    toComment = true;
                } else {
                    toComment = false;
                    continue;
                }
            } else {
                toComment = true;
            }
            if (toComment) {
                isComment = true;
                Comment comment = new Comment();
                comment.setDelFlag("0");
                comment.setCreateDate(new Date());
                comment.setMchtId(subOrder.getMchtId());
                comment.setSubOrderId(subOrder.getId());
                comment.setOrderDtlId(orderDtl.getId());
                comment.setProductId(orderDtl.getProductId());
                comment.setMemberId(combineOrder.getMemberId());
                comment.setIsAppendComment("0");
                comment.setProductScore(5);
                comment.setContent("该用户觉得商品很好，给出了5星好评");
                comment.setCommentWeight(45);//默认45分
                comment.setIsDraw("0");
                comment.setCommentSource("2");
                comment.setIsShow("1");
                commentMapper.insertSelective(comment);
            }
        }
        if (isComment) {
            ShopScore shopScore = new ShopScore();
            shopScore.setMchtId(subOrder.getMchtId());
            shopScore.setSubOrderId(subOrder.getId());
            shopScore.setMchtScore(5);
            shopScore.setWuliuScore(5);
            shopScore.setCreateDate(new Date());
            shopScore.setDelFlag("0");
            shopScore.setCommentSource("2");
            shopScoreMapper.insertSelective(shopScore);
            subOrder.setIsComment("1");
            subOrder.setUpdateDate(new Date());
            dao.updateByPrimaryKeySelective(subOrder);
        }
    }


    /**
     * 缺货生成违规单
     *
     * @return
     * @throws ParseException
     */
    public void outOfStockOrderOrderCreateViolateOrder(SubOrder subOrder) throws ParseException {
        if ("5".equals(subOrder.getStatus())) {//已关闭的订单不生成违规单
            return;
        }
        Date now = new Date();
        ViolateOrder violateOrder = new ViolateOrder();
        violateOrder.setOrderCode("WG" + CommonUtil.getOrderCode());    //违规单号
        violateOrder.setMchtId(subOrder.getMchtId());
        violateOrder.setSubOrderId(subOrder.getId());
        violateOrder.setViolateType(Const.VIOLATE_ORDER_TYPE_D);
        violateOrder.setViolateAction("D4");
        violateOrder.setContent("缺货，指在承诺发货时间的24小时之后未发货标记为缺货。");    //违规详情
        CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
        Date date = combineOrder.getPayDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date limitDate = sdf.parse("2019-01-01 00:00:00");
        if (date.before(limitDate)) {
            violateOrder.setMoney(new BigDecimal(50));    //违约金
            violateOrder.setJifenIntegral(3000);
        } else {
            OrderDtlExample ode = new OrderDtlExample();
            ode.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrder.getId());
            List<OrderDtl> orderDtls = orderDtlService.selectByExample(ode);
            List<Integer> orderDtlIds = new ArrayList<Integer>();
            for (OrderDtl orderDtl : orderDtls) {
                orderDtlIds.add(orderDtl.getId());
            }
            CustomerServiceOrderExample csoe = new CustomerServiceOrderExample();
            csoe.createCriteria().andDelFlagEqualTo("0").andOrderDtlIdIn(orderDtlIds);
            List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(csoe);
            List<Integer> customerServiceOrderIds = new ArrayList<Integer>();
            for (CustomerServiceOrder customerServiceOrder : customerServiceOrders) {
                customerServiceOrderIds.add(customerServiceOrder.getId());
            }
            List<RefundOrder> refundOrderList = new ArrayList<RefundOrder>();//无重复退款单的集合
            HashMap<Integer, RefundOrder> map = new HashMap<Integer, RefundOrder>();
            BigDecimal refundAmount = new BigDecimal(0);
            if (customerServiceOrderIds != null && customerServiceOrderIds.size() > 0) {
                RefundOrderExample roe = new RefundOrderExample();
                roe.createCriteria().andDelFlagEqualTo("0").andServiceOrderIdIn(customerServiceOrderIds).andOrderTypeEqualTo("1");
                List<RefundOrder> refundOrders = refundOrderService.selectByExample(roe);
                for (RefundOrder refundOrder : refundOrders) {
                    if (!map.containsKey(refundOrder.getServiceOrderId())) {
                        map.put(refundOrder.getServiceOrderId(), refundOrder);
                        refundOrderList.add(refundOrder);
                    }
                }
                for (RefundOrder refundOrder : refundOrderList) {
                    refundAmount = refundAmount.add(refundOrder.getRefundAmount());
                }
            }
            BigDecimal realAmount = subOrder.getPayAmount().subtract(refundAmount);
            if (realAmount.compareTo(new BigDecimal(0)) < 0) {
                realAmount = new BigDecimal(0);
            }
            violateOrder.setMoney(realAmount.multiply(new BigDecimal(0.2 + "")));
            violateOrder.setJifenIntegral(realAmount.multiply(new BigDecimal(0.1 + "")).multiply(new BigDecimal(100)).intValue());
            violateOrder.setEnableHours("72小时");
        }
        violateOrder.setAuditStatus(Const.VIOLATE_ORDER_AUDIT_STATUS_PASS);//系统生成的违规单直接通过，不用审核
        violateOrder.setAuditDate(now);
        violateOrder.setViolateDate(now);
        violateOrder.setPunishMeans("每单按实际付款订单额的20%扣款，补偿用户实际付款订单额10%的金额对应的积分。");    //处罚方式
        violateOrder.setOrderSource(Const.VIOLATE_ORDER_SOURCE_SYSTEM);
        violateOrder.setStatus(Const.VIOLATE_ORDER_STATUS_NOT_APPEAL);    //未申诉
        violateOrder.setStatusDate(new Date());
        violateOrder.setComplainInfoStatus("1");
        violateOrder.setComplainEndDate(DateUtil.getDateAfter(now, 3));
        violateOrder.setJifenStatus("0");//未补偿
        violateOrder.setCreateDate(now);
        violateOrder.setUpdateDate(now);
        violateOrder.setDelFlag("0");
        violateOrderService.save(violateOrder);

        //发送站内信

        MsgTplExample msgTplExample = new MsgTplExample();
        msgTplExample.createCriteria().andDelFlagEqualTo("0").andTplTypeEqualTo("A").andMsgTypeEqualTo("A5");
        List<MsgTpl> msgTpls = msgTplMapper.selectByExample(msgTplExample);
        if (msgTpls != null && msgTpls.size() > 0) {
            MsgTpl msgTpl = msgTpls.get(0);

            PlatformMsg platformMsg = new PlatformMsg();
            platformMsg.setMchtId(subOrder.getMchtId());
            platformMsg.setMsgType("A5");
            platformMsg.setTitle("关于缺货违规通知");
            String msgContent = msgTpl.getContent().replace("{violation_code}", violateOrder.getOrderCode()).replace("{end_date}", DateUtil.getStandardDateTime(violateOrder.getComplainEndDate()));
            platformMsg.setContent(msgContent);
            platformMsg.setBizId(violateOrder.getId());
            platformMsg.setStatus("0");
            platformMsg.setCreateDate(new Date());
            platformMsg.setRemarks("系统自动生成");
            platformMsgMapper.insertSelective(platformMsg);
        }


        //扣除商家保证金
        MchtDepositExample example = new MchtDepositExample();
        MchtDepositExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo("0");
        criteria.andMchtIdEqualTo(violateOrder.getMchtId());
        List<MchtDeposit> mchtDeposits = mchtDepositMapper.selectByExample(example);
        if (mchtDeposits != null && mchtDeposits.size() > 0) {
            MchtDeposit mchtDeposit = mchtDeposits.get(0);
            MchtDepositDtl mchtDepositDtl = new MchtDepositDtl();

            mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().subtract(violateOrder.getMoney()));
            mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().add(violateOrder.getMoney()));
            mchtDeposit.setUpdateDate(now);

            mchtDepositDtl.setDelFlag("0");
            mchtDepositDtl.setDepositId(mchtDeposit.getId());
            mchtDepositDtl.setTxnType("E");//违规扣款
            mchtDepositDtl.setTypeSub("E4");//售后
            mchtDepositDtl.setTxnAmt(violateOrder.getMoney().negate());
            mchtDepositDtl.setPayAmt(mchtDeposit.getPayAmt());
            mchtDepositDtl.setBizType("2");//违规单
            mchtDepositDtl.setBizId(violateOrder.getId());
            mchtDepositDtl.setCreateDate(now);
            mchtDepositDtl.setUpdateDate(now);
            mchtDepositDtl.setRemarks("【售后】缺货(系统自动扣除) ");
            mchtDepositMapper.updateByPrimaryKeySelective(mchtDeposit);
            mchtDepositDtlMapper.insertSelective(mchtDepositDtl);
        }
    }

    public void addCollegeStudentRefundOrder(List<SubOrderCustom> subOrderCustomList) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        for (SubOrderCustom subOrderCustom : subOrderCustomList) {
            OrderDtlExample orderDtlExample = new OrderDtlExample();
            orderDtlExample.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrderCustom.getId());
            List<OrderDtl> orderDtlList = orderDtlService.selectByExample(orderDtlExample);
            if (orderDtlList.size() > 0) {
                OrderDtl orderDtl = orderDtlList.get(0);
                orderDtl.setProductStatus(Const.ORDER_PRODUCT_STATUS_REFUND_MONEY);
                orderDtlService.updateByPrimaryKeySelective(orderDtl);

                //1售后单表
                CustomerServiceOrder customerServiceOrder = new CustomerServiceOrder();
                customerServiceOrder.setSubOrderId(subOrderCustom.getId());
                customerServiceOrder.setOrderDtlId(orderDtl.getId());
                customerServiceOrder.setServiceType(Const.CUSTOMER_ORDER_TYPE_A);
                //售后状态 0.进行中 1已完成 2已拒绝 3已撤销
                customerServiceOrder.setStatus(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
                customerServiceOrder.setOrderCode(Const.CUSTOMER_ORDER_TYPE_A + CommonUtil.getOrderCode());
                customerServiceOrder.setProStatus(Const.CUSTOMER_ORDER_PRO_STATUS_A2);
                customerServiceOrder.setContactPhone(subOrderCustom.getReceiverPhone());
                customerServiceOrder.setReason("其他原因");
                customerServiceOrder.setQuantity(orderDtl.getQuantity());
                customerServiceOrder.setAmount(orderDtl.getPayAmount());
                customerServiceOrder.setCreateBy(1);
                customerServiceOrder.setCreateDate(DateUtil.getDate());
                customerServiceOrder.setUpdateDate(DateUtil.getDate());
                customerServiceOrder.setRemarks("违反活动规则，自动退款");
                customerServiceOrder.setDelFlag("0");
                customerServiceOrderService.insertSelective(customerServiceOrder);

                //2插入售后单状态流水日志表
                CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
                customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
                customerServiceStatusLog.setStatus(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
                customerServiceStatusLog.setProStatus(customerServiceOrder.getProStatus());
                customerServiceStatusLog.setCreateBy(customerServiceOrder.getCreateBy());
                customerServiceStatusLog.setCreateDate(DateUtil.getDate());
                customerServiceStatusLog.setRemarks(customerServiceOrder.getRemarks());
                customerServiceStatusLog.setDelFlag("0");
                customerServiceStatusLogService.insertSelective(customerServiceStatusLog);

                //3售后记录表
                CustomerServiceLog customerServiceLog = new CustomerServiceLog();
                customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
                customerServiceLog.setOperatorType(Const.OPERATOR_TYPE_SYSTEM);
                String context = "<font>申请退款</font><br>"
                        + "申请数量：" + customerServiceOrder.getQuantity() + "<br>"
                        + "退款金额：" + customerServiceOrder.getAmount() + "<br>"
                        + "退款原因：" + customerServiceOrder.getReason() + "<br>"
                        + "原因说明：" + customerServiceOrder.getRemarks();
                customerServiceLog.setContent(context);
                customerServiceLog.setRemarks(customerServiceOrder.getRemarks());
                customerServiceLog.setCreateBy(customerServiceOrder.getCreateBy());
                customerServiceLog.setCreateDate(DateUtil.getDate());
                customerServiceLog.setDelFlag("0");
                customerServiceLogService.insertSelective(customerServiceLog);

                //4积分
                if (orderDtl.getIntegralPreferential().compareTo(new BigDecimal(0)) > 0) {
                    MemberAccountExample mae = new MemberAccountExample();
                    MemberAccountExample.Criteria c = mae.createCriteria();
                    c.andDelFlagEqualTo("0");
                    c.andMemberIdEqualTo(orderDtl.getCreateBy());
                    List<MemberAccount> memberAccounts = memberAccountService.selectByExample(mae);
                    if (memberAccounts != null && memberAccounts.size() > 0) {
                        Integer integral = Integer.parseInt(orderDtl.getIntegralPreferential().multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
                        //会员账户
                        MemberAccount memberAccount = memberAccounts.get(0);
                        memberAccount.setIntegral(memberAccount.getIntegral() + integral);
                        memberAccount.setUpdateBy(customerServiceOrder.getCreateBy());
                        memberAccount.setUpdateDate(new Date());
                        memberAccountService.updateByPrimaryKeySelective(memberAccount);
                        //积分明细
                        IntegralDtl integralDtl = new IntegralDtl();
                        integralDtl.setAccId(memberAccount.getId());
                        integralDtl.setTallyMode("1");
                        integralDtl.setType(7);
                        integralDtl.setIntegral(integral);
                        integralDtl.setBalance(memberAccount.getIntegral());
                        integralDtl.setOrderId(orderDtl.getSubOrderId());
                        integralDtl.setCreateBy(customerServiceOrder.getCreateBy());
                        integralDtl.setCreateDate(new Date());
                        integralDtl.setDelFlag("0");
                        integralDtlService.insertSelective(integralDtl);
                    }
                }

                //5退款单
                RefundOrder refundOrder = new RefundOrder();
                refundOrder.setCombineOrderId(subOrderCustom.getCombineOrderId());
                refundOrder.setServiceType(Const.CUSTOMER_ORDER_TYPE_A);
                refundOrder.setOrderType("1");
                refundOrder.setServiceOrderId(customerServiceOrder.getId());
                refundOrder.setRefundAmount(customerServiceOrder.getAmount());
                refundOrder.setRefundAgreeDate(DateUtil.getDate());
                refundOrder.setRefundMethod("1");
                refundOrder.setTryTimes(0);
                refundOrder.setStatus("0");
                if (subOrderCustom.getPaymentId() == 1) {
                    refundOrder.setRefundCode("ZFB" + sf.format(new Date()) + "T");
                } else if (subOrderCustom.getPaymentId() == 2) {
                    refundOrder.setRefundCode("WX" + sf.format(new Date()) + "T");
                } else if (subOrderCustom.getPaymentId() == 3) {
                    refundOrder.setRefundCode("YL" + sf.format(new Date()) + "T");
                }
                refundOrder.setCreateBy(customerServiceOrder.getCreateBy());
                refundOrder.setCreateDate(DateUtil.getDate());
                refundOrder.setDelFlag("0");
                refundOrderService.insertSelective(refundOrder);
            }
        }
    }

    public void bulkUpdateSubOrderAuditStatus() {
        Map<String, Object> params = Maps.newHashMap();
        Date now = new Date();
        params.put("now", now);
        params.put("endTime", DateUtil.addMinute(now, -10));
        subOrderCustomMapper.bulkUpdateSubOrderAuditStatus(params);
    }

}
