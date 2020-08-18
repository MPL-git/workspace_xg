package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.constant.SysConfig;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.*;
import com.jf.entity.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class CustomerServiceOrderService extends BaseService<CustomerServiceOrder, CustomerServiceOrderExample> {
    @Autowired
    private CustomerServiceOrderMapper dao;

    @Autowired
    private CustomerServiceOrderCustomMapper customerServiceOrderCustomMapper;

    @Autowired
    private CustomerServiceStatusLogService customerServiceStatusLogService;

    @Autowired
    private CustomerServiceLogService customerServiceLogService;

    @Autowired
    private ServiceLogPicService serviceLogPicService;

    @Autowired
    private SubOrderService subOrderService;

    @Autowired
    private OrderDtlService orderDtlService;

    @Autowired
    private RefundOrderService refundOrderService;

    @Autowired
    private SysAppMessageService sysAppMessageService;

    @Autowired
    private IntegralDtlService integralDtlService;

    @Autowired
    private MemberAccountService memberAccountService;

    @Autowired
    private MemberCouponService memberCouponService;

    @Autowired
    private MchtDepositMapper mchtDepositMapper;

    @Autowired
    private MchtDepositDtlMapper mchtDepositDtlMapper;

    @Autowired
    private CombineOrderService combineOrderService;

    @Autowired
    private SubDepositOrderMapper subDepositOrderMapper;

    @Autowired
    private CombineDepositOrderMapper combineDepositOrderMapper;

    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductAfterTempletService productAfterTempletService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private MemberAllowanceMapper memberAllowanceMapper;

    @Autowired
    public void setCustomerServiceOrderMapper(CustomerServiceOrderMapper customerServiceOrderMapper) {
        super.setDao(customerServiceOrderMapper);
        this.dao = customerServiceOrderMapper;
    }

    public CustomerServiceOrder selectCustomerServiceOrderBySubOrderIdAndOrderDtlId(Integer subOrderId, Integer orderDtlId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subOrderId", subOrderId);
        map.put("orderDtlId", orderDtlId);
        return customerServiceOrderCustomMapper.selectCustomerServiceOrderBySubOrderIdAndOrderDtlId(map);
    }

    public int countCustomerServiceOrderCustomByExample(CustomerServiceOrderCustomExample example) {
        return customerServiceOrderCustomMapper.countByExample(example);
    }

    public List<CustomerServiceOrderCustom> selectCustomerServiceOrderCustomByExample(CustomerServiceOrderCustomExample example) {
        return customerServiceOrderCustomMapper.selectByExample(example);
    }

    public int countCustomerServiceOrderByServiceTypeAndProStatusAndStatus(Integer mchtId, String serviceType, String proStatus, String status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mchtId", mchtId);
        map.put("serviceType", serviceType);
        map.put("proStatus", proStatus);
        map.put("status", status);
        return customerServiceOrderCustomMapper.countCustomerServiceOrderByServiceTypeAndProStatusAndStatus(map);
    }

    public CustomerServiceOrder getCustomerServiceOrderByOrderDtlId(int orderDtlId) {
        return customerServiceOrderCustomMapper.getCustomerServiceOrderByOrderDtlId(orderDtlId);
    }

    public void updateCustomerServiceOrder(CustomerServiceOrder customerServiceOrder, CustomerServiceStatusLog customerServiceStatusLog, CustomerServiceLog customerServiceLog, OrderDtl orderDtl, RefundOrder refundOrder, SubOrder subOrder, SysAppMessage sysAppMessage) {
        if (customerServiceOrder.getProStatus().equals("B5") && customerServiceOrder.getServiceType().equals("C")) {//换货改退款
            customerServiceOrder.setServiceType("B");
        }
        this.updateByPrimaryKeySelective(customerServiceOrder);


        customerServiceStatusLogService.insertSelective(customerServiceStatusLog);
        customerServiceLogService.insertSelective(customerServiceLog);
        if (orderDtl != null) {
            orderDtlService.updateByPrimaryKey(orderDtl);
        }
        if (refundOrder != null) {
            refundOrderService.insertSelective(refundOrder);
        }
        if (subOrder != null) {
            boolean orderClose = true;
            OrderDtlExample example = new OrderDtlExample();
            OrderDtlExample.Criteria criteria = example.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andSubOrderIdEqualTo(customerServiceOrder.getSubOrderId());
            List<OrderDtl> orderDtls = orderDtlService.selectByExample(example);
            if (orderDtls != null && orderDtls.size() > 0) {//多个订单明细时
                for (OrderDtl od : orderDtls) {
                    if (od.getIsGive().equals("0")) {//不是赠品
                        CustomerServiceOrder cso = this.getCustomerServiceOrderByOrderDtlId(od.getId());
                        if (cso != null) {
                            String ps = cso.getProStatus();
                            if (ps.equals(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_REFUND) || ps.equals(CustomerServiceStatusLogCustom.ORDER_PLATFORM_HAS_REFUND) || ps.equals(CustomerServiceStatusLogCustom.ORDER_HAS_ACCEPT_RETURN_GOODS) || ps.equals(CustomerServiceStatusLogCustom.ORDER_PLATFORM_HAS_REFUND_RETURN_GOODS)) {
                                orderClose = true;
                            } else {
                                orderClose = false;
                                break;
                            }
                        } else {
                            orderClose = false;
                            break;
                        }
                    }
                }
            }
            if (orderClose) {//所有商品都已经同意退款
                subOrder.setStatus("5");//关闭(退款后关闭)
                subOrder.setCloseDate(new Date());
            }
            subOrderService.updateByPrimaryKey(subOrder);
        }
//		if(sysAppMessage!=null){
//			sysAppMessageService.insertSelective(sysAppMessage);
//		}
    }

    public CustomerServiceOrder getDirectCompensationOrder(Integer subOrderId, String serviceType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subOrderId", subOrderId);
        map.put("serviceType", serviceType);
        return customerServiceOrderCustomMapper.getDirectCompensationOrder(map);
    }

    public void updateCustomerServiceOrder(
            CustomerServiceOrder customerServiceOrder,
            CustomerServiceStatusLog customerServiceStatusLog,
            CustomerServiceLog customerServiceLog,
            List<ServiceLogPic> serviceLogPics,
            SysAppMessage sysAppMessage) {
        this.updateByPrimaryKeySelective(customerServiceOrder);
        customerServiceStatusLogService.insertSelective(customerServiceStatusLog);
        customerServiceLogService.insertSelective(customerServiceLog);
        for (ServiceLogPic serviceLogPic : serviceLogPics) {
            serviceLogPic.setServiceLogId(customerServiceLog.getId());
            serviceLogPicService.insertSelective(serviceLogPic);
        }
//		sysAppMessageService.insertSelective(sysAppMessage);
    }

    public void insertCustomerServiceOrder(CustomerServiceOrder customerServiceOrder, CustomerServiceLog customerServiceLog, CustomerServiceStatusLog customerServiceStatusLog, RefundOrder refundOrder) {
        this.insertSelective(customerServiceOrder);
        customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
        customerServiceLogService.insertSelective(customerServiceLog);
        customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
        customerServiceStatusLogService.insertSelective(customerServiceStatusLog);
        refundOrder.setServiceOrderId(customerServiceOrder.getId());
        refundOrderService.insertSelective(refundOrder);
    }


    public List<CustomerServiceOrder> list(QueryObject queryObject) {
        return dao.selectByExample(builderQuery(queryObject));
    }

    public Page<CustomerServiceOrder> paginate(QueryObject queryObject) {
        CustomerServiceOrderExample example = builderQuery(queryObject);
        example.setLimitStart(queryObject.getLimitStart());
        example.setLimitSize(queryObject.getPageSize());

        List<CustomerServiceOrder> list = dao.selectByExample(example);
        int totalCount = dao.countByExample(example);
        return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
    }

    private CustomerServiceOrderExample builderQuery(QueryObject queryObject) {
        CustomerServiceOrderExample example = new CustomerServiceOrderExample();
        CustomerServiceOrderExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
        if (queryObject.getQuery("subOrderIds") != null) {
            List<Integer> subOrderIds = queryObject.getQuery("subOrderIds");
            criteria.andSubOrderIdIn(subOrderIds);
        }
        if (queryObject.getQuery("mchtSettleOrderId") != null) {
            criteria.andMchtSettleOrderIdEqualTo(queryObject.getQueryToInt("mchtSettleOrderId"));
        }
        if (queryObject.getSort().size() > 0) {
            example.setOrderByClause(queryObject.getSortString());
        }
        return example;
    }

    public CustomerServiceOrder fillInfo(CustomerServiceOrder info) {
        SubOrder subOrder = subOrderService.selectByPrimaryKey(info.getSubOrderId());
        info.put("subOrder", subOrder);
        return info;
    }

    public void updateCustomerServiceOrder(
            CustomerServiceOrder customerServiceOrder,
            CustomerServiceStatusLog customerServiceStatusLog,
            CustomerServiceLog customerServiceLog, OrderDtl orderDtl,
            RefundOrder refundOrder, SubOrder subOrder,
            SysAppMessage sysAppMessage, IntegralDtl integralDtl,
            MemberAccount memberAccount,
            MchtDeposit mchtDeposit,
            MchtDepositDtl mchtDepositDtl,
            List<RefundOrder> depositRefundOrder) {
        this.updateCustomerServiceOrder(customerServiceOrder, customerServiceStatusLog, customerServiceLog, orderDtl, refundOrder, subOrder, sysAppMessage);
        if (customerServiceOrder.getProStatus().equals("A2")) {
            memberCouponService.changeStatusByCustomerServiceOrder(customerServiceOrder);
        }
        if (integralDtl != null) {
            integralDtlService.insertSelective(integralDtl);
        }
        if (memberAccount != null) {
            memberAccountService.updateByPrimaryKeySelective(memberAccount);
        }
        if (mchtDeposit != null) {
            mchtDepositMapper.updateByPrimaryKeySelective(mchtDeposit);
        }
        if (mchtDepositDtl != null) {
            mchtDepositDtlMapper.insertSelective(mchtDepositDtl);
        }
        if (depositRefundOrder != null) {
            for (RefundOrder refundOrder1 : depositRefundOrder) {
                refundOrderService.insertSelective(refundOrder1);
            }
        }
    }

    public List<CustomerServiceOrder> getByCombineOrderId(Integer combineOrderId) {
        return customerServiceOrderCustomMapper.getByCombineOrderId(combineOrderId);
    }

    /**
     * 批量退款
     *
     * @param request
     * @param id
     * @return
     */
    public ResponseMsg saveBatchRefund(HttpServletRequest request, Integer id) {
        String proStatus = request.getParameter("proStatus");
        String content = request.getParameter("content");
        String[] customerServiceOrderIds = request.getParameter("customerServiceOrderIds").split(",");
        if (customerServiceOrderIds.length <= 0) {
            throw new ArgException("批量退款出错了");
        }
        List<Integer> idList = new ArrayList<Integer>();
        for (int i = 0; i < customerServiceOrderIds.length; i++) {
            if (!StringUtil.isEmpty(customerServiceOrderIds[i])) {
                Integer productId = Integer.valueOf(customerServiceOrderIds[i]);
                idList.add(productId);
            }
        }
        Date now = new Date();
        int errorCount = 0;
        int successCount = 0;
        try {
            CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
            customerServiceOrderExample.createCriteria().andIdIn(idList);
            List<CustomerServiceOrder> customerServiceOrders = dao.selectByExample(customerServiceOrderExample);
            for (CustomerServiceOrder customerServiceOrder : customerServiceOrders) {
                //TODO 有重复的售后单时，直接跳过
                CustomerServiceOrderExample customerServiceOrderExample1 = new CustomerServiceOrderExample();
                customerServiceOrderExample1.createCriteria()
                        .andDelFlagEqualTo("0").andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId())
                        .andServiceTypeEqualTo(customerServiceOrder.getServiceType()).andStatusIn(Arrays.asList("0", "1"));
                int count = dao.countByExample(customerServiceOrderExample1);
                if (count > 1) {
                    errorCount++;
                    continue;
                }
                if (!customerServiceOrder.getStatus().equals("0") || !customerServiceOrder.getProStatus().equals("A1") || customerServiceOrder.getProStatus().equals(proStatus)) {
                    errorCount++;
                    continue;
                }

                customerServiceOrder.setProStatus(proStatus);
                customerServiceOrder.setStatus("0");//进行中
                customerServiceOrder.setUpdateDate(now);

                CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
                customerServiceStatusLog.setCreateBy(id);
                customerServiceStatusLog.setCreateDate(now);
                customerServiceStatusLog.setUpdateDate(now);
                customerServiceStatusLog.setDelFlag("0");
                customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
                customerServiceStatusLog.setStatus("0");//进行中
                customerServiceStatusLog.setProStatus(proStatus);

                CustomerServiceLog customerServiceLog = new CustomerServiceLog();
                customerServiceLog.setCreateBy(id);
                customerServiceLog.setCreateDate(now);
                customerServiceLog.setUpdateDate(now);
                customerServiceLog.setDelFlag("0");
                customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
                customerServiceLog.setOperatorType(CustomerServiceLogCustom.OPERATOR_TYPE_MCHT);//商家
                customerServiceLog.setContent(StringUtil.unEscapeHtmlAndIllegal(content));

                SubOrder subOrder = subOrderService.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
                CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());

                SysAppMessage sysAppMessage = new SysAppMessage();

                OrderDtl orderDtl = null;
                RefundOrder refundOrder = null;
                IntegralDtl integralDtl = null;
                MemberAccount memberAccount = null;
                MchtDeposit mchtDeposit = null;
                MchtDepositDtl mdd = null;
                List<RefundOrder> refundOrderList = new ArrayList<>();

                BigDecimal amount = customerServiceOrder.getAmount();
                if (customerServiceOrder.getOrderDtlId() != null) {
                    orderDtl = orderDtlService.selectByPrimaryKey(customerServiceOrder.getOrderDtlId());
                    if (customerServiceOrder.getServiceType().equals("A")) {//A.退款单
                        orderDtl.setProductStatus("2");//1 完成 2退款 3退货
                    }

                    //根据订单明细id获取所有的售后单，进而获取相应的退款单，若存在退款单，则不生成退款单，以免重复退款
                    CustomerServiceOrderExample example = new CustomerServiceOrderExample();
                    CustomerServiceOrderExample.Criteria criteria = example.createCriteria();
                    criteria.andDelFlagEqualTo("0");
                    criteria.andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId());
                    List<CustomerServiceOrder> customerServiceOrders1 = dao.selectByExample(example);
                    List<Integer> customerServiceOrderIds1 = new ArrayList<Integer>();
                    if (customerServiceOrders1 != null && customerServiceOrders1.size() > 0) {
                        for (CustomerServiceOrder cso : customerServiceOrders1) {
                            customerServiceOrderIds1.add(cso.getId());
                        }
                    } else {
                        customerServiceOrderIds1.add(0);
                    }
                    RefundOrderExample e = new RefundOrderExample();
                    RefundOrderExample.Criteria c = e.createCriteria();
                    c.andDelFlagEqualTo("0");
                    c.andServiceOrderIdIn(customerServiceOrderIds1);
                    c.andOrderTypeEqualTo("1");//1.总订单
                    List<RefundOrder> ros = refundOrderService.selectByExample(e);
                    if (ros != null && ros.size() > 0) {
                        errorCount++;
                        continue;
                    }
                }
                //往退款单表里插入数据,先判断退款单是否存在
                RefundOrderExample example = new RefundOrderExample();
                RefundOrderExample.Criteria criteria = example.createCriteria();
                criteria.andDelFlagEqualTo("0");
                criteria.andServiceOrderIdEqualTo(customerServiceOrder.getId());
                criteria.andOrderTypeEqualTo("1");//1.总订单
                List<RefundOrder> refundOrders = refundOrderService.selectByExample(example);
                if (refundOrders != null && refundOrders.size() > 0) {
                    errorCount++;
                    continue;
                }
                refundOrder = new RefundOrder();
                refundOrder.setDelFlag("0");
                refundOrder.setCreateBy(id);
                refundOrder.setCreateDate(now);
                refundOrder.setUpdateDate(now);
                refundOrder.setCombineOrderId(subOrder.getCombineOrderId());
                refundOrder.setServiceType(customerServiceOrder.getServiceType());//直接存售后类型了
                refundOrder.setServiceOrderId(customerServiceOrder.getId());
                refundOrder.setRefundAmount(amount);
                refundOrder.setRefundAgreeDate(new Date());
                refundOrder.setRefundMethod("1");
                refundOrder.setTryTimes(0);
                refundOrder.setStatus("0");
                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
                if (combineOrder.getPaymentId() == 1 || combineOrder.getPaymentId() == 6) {
                    refundOrder.setRefundCode("ZFB" + sf.format(new Date()) + "T");
                } else if (combineOrder.getPaymentId() == 2 || combineOrder.getPaymentId() == 5) {
                    refundOrder.setRefundCode("WX" + sf.format(new Date()) + "T");
                } else if (combineOrder.getPaymentId() == 3) {
                    refundOrder.setRefundCode("YL" + sf.format(new Date()) + "T");
                } else if (combineOrder.getPaymentId() == 4) {
                    refundOrder.setRefundCode("GZH" + sf.format(new Date()) + "T");
                }
                refundOrder.setOrderType("1");
                int result = orderDtl.getIntegralPreferential().compareTo(new BigDecimal(0));
                if (result > 0) {
                    MemberAccountExample mae = new MemberAccountExample();
                    MemberAccountExample.Criteria c = mae.createCriteria();
                    c.andDelFlagEqualTo("0");
                    c.andMemberIdEqualTo(customerServiceOrder.getCreateBy());
                    List<MemberAccount> memberAccounts = memberAccountService.selectByExample(mae);
                    if (memberAccounts != null && memberAccounts.size() > 0) {
                        Integer integral = Integer.parseInt(orderDtl.getIntegralPreferential().multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
                        memberAccount = memberAccounts.get(0);
                        memberAccount.setUpdateBy(id);
                        memberAccount.setIntegral(memberAccount.getIntegral() + integral);
                        memberAccount.setUpdateDate(new Date());
                        integralDtl = new IntegralDtl();
                        integralDtl.setAccId(memberAccount.getId());
                        integralDtl.setType(7);
                        integralDtl.setTallyMode("1");
                        integralDtl.setIntegral(integral);
                        integralDtl.setBalance(memberAccount.getIntegral());
                        integralDtl.setOrderId(orderDtl.getSubOrderId());
                        integralDtl.setCreateBy(id);
                        integralDtl.setCreateDate(new Date());
                        integralDtl.setDelFlag("0");
                    }
                }

                // 返还津贴
                if (orderDtl.getAllowance() != null && orderDtl.getAllowance().compareTo(BigDecimal.ZERO) > 0) {
                    MemberAllowance memberAllowance = new MemberAllowance();
                    memberAllowance.setMemberId(combineOrder.getMemberId());
                    memberAllowance.setSource("2");
                    memberAllowance.setAllowanceAmount(orderDtl.getAllowance().setScale(0,BigDecimal.ROUND_DOWN));
                    memberAllowance.setCreateDate(new Date());
                    memberAllowance.setDelFlag("0");
                    memberAllowanceMapper.insert(memberAllowance);
                }

                //TODO 定金退款单生成处理
                if (customerServiceOrder.getDepositAmount() != null && customerServiceOrder.getDepositAmount().compareTo(new BigDecimal(0)) > 0) {
                    SubDepositOrderExample sdoe = new SubDepositOrderExample();
                    sdoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(id).andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId());
                    List<SubDepositOrder> subDepositOrders = subDepositOrderMapper.selectByExample(sdoe);
                    //循环插入退款单表数据
                    for (SubDepositOrder subDepositOrder : subDepositOrders) {
                        RefundOrder depositRefundOrder = new RefundOrder();
                        depositRefundOrder.setDelFlag("0");
                        depositRefundOrder.setCreateBy(id);
                        depositRefundOrder.setCreateDate(now);
                        depositRefundOrder.setUpdateDate(now);
                        depositRefundOrder.setCombineOrderId(subOrder.getCombineOrderId());
                        Integer combineDepositOrderId = subDepositOrder.getCombineDepositOrderId();
                        depositRefundOrder.setCombineDepositOrderId(combineDepositOrderId);
                        CombineDepositOrder combineDepositOrder = combineDepositOrderMapper.selectByPrimaryKey(combineDepositOrderId);
                        if (combineDepositOrder.getPaymentId() == 1 || combineDepositOrder.getPaymentId() == 6) {
                            depositRefundOrder.setRefundCode("ZFB" + sf.format(new Date()) + "T");
                        } else if (combineDepositOrder.getPaymentId() == 2 || combineDepositOrder.getPaymentId() == 5) {
                            depositRefundOrder.setRefundCode("WX" + sf.format(new Date()) + "T");
                        } else if (combineDepositOrder.getPaymentId() == 3) {
                            depositRefundOrder.setRefundCode("YL" + sf.format(new Date()) + "T");
                        } else if (combineDepositOrder.getPaymentId() == 4) {
                            depositRefundOrder.setRefundCode("GZH" + sf.format(new Date()) + "T");
                        }
                        if (customerServiceOrder.getServiceType().equals("C") && customerServiceOrder.getProStatus().equals("B5")) {//换货改退货
                            depositRefundOrder.setServiceType("B");
                        } else {
                            depositRefundOrder.setServiceType(customerServiceOrder.getServiceType());
                        }
                        depositRefundOrder.setServiceOrderId(customerServiceOrder.getId());
                        depositRefundOrder.setRefundAmount(subDepositOrder.getDeposit());
                        depositRefundOrder.setRefundAgreeDate(new Date());
                        depositRefundOrder.setRefundMethod("1");
                        depositRefundOrder.setTryTimes(0);
                        depositRefundOrder.setStatus("0");
                        depositRefundOrder.setOrderType("2");
                        refundOrderList.add(depositRefundOrder);
                    }
                }

                //TODO 定金，商家责任，扣除保证金（保证金金额为定金金额），送用户积分
                if (customerServiceOrder.getServiceType().equals("A") && proStatus.equals("A2") && customerServiceOrder.getDepositAmount() != null && customerServiceOrder.getDepositAmount().compareTo(new BigDecimal(0)) > 0) {
                    if (customerServiceOrder.getReason().equals("A7")) {//定金付款时的商家责任。
                        BigDecimal totalDeposit = customerServiceOrder.getDepositAmount();
                        MchtDepositExample mde = new MchtDepositExample();
                        mde.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(id);
                        List<MchtDeposit> mchtDeposits = mchtDepositMapper.selectByExample(mde);
                        mchtDeposit = mchtDeposits.get(0);
                        mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().add(totalDeposit));
                        mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().subtract(totalDeposit));
                        mchtDeposit.setUpdateBy(id);
                        mchtDeposit.setUpdateDate(new Date());

                        mdd = new MchtDepositDtl();
                        mdd.setDepositId(mchtDeposit.getId());
                        mdd.setTxnType("E");//E.违规扣款
                        mdd.setTypeSub("E4");//售后
                        mdd.setTxnAmt(totalDeposit.negate());
                        mdd.setPayAmt(mchtDeposit.getPayAmt());
                        mdd.setBizType("2");
                        mdd.setRemarks("【售后】预售违规");
                        mdd.setCreateBy(id);
                        mdd.setCreateDate(new Date());
                        mdd.setDelFlag("0");

                        MemberAccountExample mae = new MemberAccountExample();
                        MemberAccountExample.Criteria c = mae.createCriteria();
                        c.andDelFlagEqualTo("0");
                        c.andMemberIdEqualTo(customerServiceOrder.getCreateBy());
                        List<MemberAccount> memberAccounts = memberAccountService.selectByExample(mae);
                        Integer integral = Integer.parseInt(totalDeposit.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
                        memberAccount = memberAccounts.get(0);
                        memberAccount.setIntegral(memberAccount.getIntegral() + integral);
                        memberAccount.setUpdateBy(id);
                        memberAccount.setUpdateDate(new Date());
                        integralDtl = new IntegralDtl();
                        integralDtl.setAccId(memberAccount.getId());
                        integralDtl.setTallyMode("1");
                        integralDtl.setType(7);
                        integralDtl.setIntegral(integral);
                        integralDtl.setBalance(memberAccount.getIntegral());
                        integralDtl.setOrderId(orderDtl.getSubOrderId());
                        integralDtl.setCreateBy(id);
                        integralDtl.setCreateDate(new Date());
                        integralDtl.setDelFlag("0");
                    }
                }
                //事务
                updateCustomerServiceOrder(customerServiceOrder, customerServiceStatusLog, customerServiceLog, orderDtl, refundOrder, subOrder, sysAppMessage, integralDtl, memberAccount, mchtDeposit, mdd, refundOrderList);

            }

        } catch (ArgException arge) {
            arge.printStackTrace();
            return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("successCount", idList.size() - errorCount);
        returnData.put("errorCount", errorCount);

        return new ResponseMsg(ResponseMsg.SUCCESS, "操作成功", returnData);
    }

    /**
     * 批量退换货
     *
     * @param request
     * @param id
     * @return
     */
    public ResponseMsg saveBatchReturn(HttpServletRequest request, Integer id) {
        String[] customerServiceOrderIds = request.getParameter("customerServiceOrderIds").split(",");
        if (customerServiceOrderIds.length <= 0) {
            throw new ArgException("批量退款出错");
        }
        List<Integer> idList = new ArrayList<Integer>();
        for (int i = 0; i < customerServiceOrderIds.length; i++) {
            if (!StringUtil.isEmpty(customerServiceOrderIds[i])) {
                Integer productId = Integer.valueOf(customerServiceOrderIds[i]);
                idList.add(productId);
            }
        }
        Date now = new Date();
        int errorCount = 0;
        int successCount = 0;
        String proStatus = "";
        try {
            CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
            customerServiceOrderExample.createCriteria().andIdIn(idList);
            List<CustomerServiceOrder> customerServiceOrders = dao.selectByExample(customerServiceOrderExample);
            for (CustomerServiceOrder customerServiceOrder : customerServiceOrders) {
                //TODO 有重复的售后单时，直接提示商家
                CustomerServiceOrderExample customerServiceOrderExample1 = new CustomerServiceOrderExample();
                customerServiceOrderExample1.createCriteria()
                        .andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId()).andDelFlagEqualTo("0")
                        .andServiceTypeEqualTo(customerServiceOrder.getServiceType()).andStatusIn(Arrays.asList("0", "1"));
                int count = dao.countByExample(customerServiceOrderExample1);
                if (count > 1) {
                    errorCount++;
                    continue;
                }
                //如果售后状态不是进行中的话就直接error
                if (!customerServiceOrder.getStatus().equals("0")) {
                    errorCount++;
                    continue;
                }
                //地址拼接省市区area
                StringBuilder finaladdress = new StringBuilder();
                String address = "";//收货地址
                String phone = "";//电话
                String recipient = "";//收货人
                String remarks = "";//模板备注
                String content = "";//日志content
                OrderDtl orderDtl1 = orderDtlService.selectByPrimaryKey(customerServiceOrder.getOrderDtlId());
                Product product = productService.selectByPrimaryKey(orderDtl1.getProductId());
                if (product.getCsTempletId() != null) {
                    ProductAfterTemplet productAfterTemplet = productAfterTempletService.selectByPrimaryKey(product.getCsTempletId());
                    address = productAfterTemplet.getAddress();//收货地址
                    phone = productAfterTemplet.getPhone();//电话
                    recipient = productAfterTemplet.getRecipient();//收货人
                    remarks = productAfterTemplet.getRemarks();//模板备注
                    if (productAfterTemplet.getProvince() != null) {
                        Area provinceArea = areaService.selectByPrimaryKey(productAfterTemplet.getProvince());
                        finaladdress.append(provinceArea.getAreaName());
                    }
                    if (productAfterTemplet.getCity() != null) {
                        Area cityArea = areaService.selectByPrimaryKey(productAfterTemplet.getCity());
                        finaladdress.append(cityArea.getAreaName());
                    }
                    if (productAfterTemplet.getCounty() != null) {
                        Area countyArea = areaService.selectByPrimaryKey(productAfterTemplet.getCounty());
                        finaladdress.append(countyArea.getAreaName());
                    }
                    finaladdress.append(address);
                }

                //退货
                if (customerServiceOrder.getServiceType().equals("B")) {//类型：退货单
                    if (orderDtl1.getDeliveryStatus().equals("1")) {  //已发货
                        proStatus = "B2"; //商品明细中有发货的退货
                        content = "同意售后退货申请,请客户寄件到以下地址：收货人：" + recipient + ",联系电话：" + phone + ",地址：" + finaladdress.toString() + ",注意事项：" + remarks;
                        if (!customerServiceOrder.getProStatus().equals("B1")) {
                            errorCount++;
                            continue;
                        }
                        if (customerServiceOrder.getProStatus().equals(proStatus)) {
                            errorCount++;
                            continue;
                        }
                        customerServiceOrder.setAddressType("0");
                        customerServiceOrder.setProStatus(proStatus);
                        customerServiceOrder.setStatus("0");//进行中
                        customerServiceOrder.setUpdateDate(now);
                        //退换货单的寄回地址
                        customerServiceOrder.setProductReturnAddress(finaladdress.toString());
                        customerServiceOrder.setProductReturnContactPhone(phone);
                        customerServiceOrder.setProductReturnConsignee(recipient);

                        CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
                        customerServiceStatusLog.setCreateBy(id);
                        customerServiceStatusLog.setCreateDate(now);
                        customerServiceStatusLog.setUpdateDate(now);
                        customerServiceStatusLog.setDelFlag("0");
                        customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
                        customerServiceStatusLog.setStatus("0");//进行中
                        customerServiceStatusLog.setProStatus(proStatus);

                        CustomerServiceLog customerServiceLog = new CustomerServiceLog();
                        customerServiceLog.setCreateBy(id);
                        customerServiceLog.setCreateDate(now);
                        customerServiceLog.setUpdateDate(now);
                        customerServiceLog.setDelFlag("0");
                        customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
                        customerServiceLog.setOperatorType(CustomerServiceLogCustom.OPERATOR_TYPE_MCHT);//商家
                        customerServiceLog.setContent(content);

                        SubOrder subOrder = subOrderService.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
                        CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());

                        SysAppMessage sysAppMessage = new SysAppMessage();

                        OrderDtl orderDtl = null;
                        RefundOrder refundOrder = null;
                        IntegralDtl integralDtl = null;
                        MemberAccount memberAccount = null;
                        MchtDeposit mchtDeposit = null;
                        MchtDepositDtl mdd = null;
                        List<RefundOrder> refundOrderList = new ArrayList<>();
                        //事务
                        updateCustomerServiceOrder(customerServiceOrder, customerServiceStatusLog, customerServiceLog, orderDtl, refundOrder, subOrder, sysAppMessage, integralDtl, memberAccount, mchtDeposit, mdd, refundOrderList);
                        String toSendMobile = "";
                        String contactPhone = customerServiceOrder.getContactPhone();
                        if (StringUtil.isEmpty(contactPhone)) {
                            MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(combineOrder.getMemberId());
                            String mobile = memberInfo.getMobile();
                            if (StringUtil.isEmpty(mobile)) {
                                String receiverPhone = combineOrder.getReceiverPhone();
                                if (!StringUtil.isEmpty(receiverPhone)) {
                                    if (StringUtil.isMobileNO(receiverPhone)) {
                                        toSendMobile = receiverPhone;
                                    }
                                }
                            } else {
                                if (StringUtil.isMobileNO(mobile)) {
                                    toSendMobile = mobile;
                                }
                            }
                        } else {
                            if (StringUtil.isMobileNO(contactPhone)) {
                                toSendMobile = contactPhone;
                            }
                        }
                        if (!StringUtil.isEmpty(toSendMobile)) {
                            try {
                                if (proStatus.equals(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_RETURN_GOODS)) {
                                    JSONObject param = new JSONObject();
                                    JSONObject reqData = new JSONObject();
                                    reqData.put("mobile", toSendMobile);
                                    reqData.put("content", "商家已同意您的申请，请尽快登录APP，在个人中心“退款/售后”找到相应的售后单填写寄回物流单号。");
                                    reqData.put("smsType", "4");
                                    param.put("reqData", reqData);
                                    JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl + "/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (orderDtl1.getDeliveryStatus().equals("0")) {//退货中商品明细为未发货；直接退款
                        proStatus = "B5";
                        content = "同意售后退货申请，并且同意退款，请耐心等待";
                        String isRefund = "1";
                        if (!customerServiceOrder.getProStatus().equals("B1") && !customerServiceOrder.getProStatus().equals("B4") && !customerServiceOrder.getProStatus().equals("C4")) {
                            errorCount++;
                            continue;
                        }
                        if (customerServiceOrder.getProStatus().equals(proStatus)) {  //只能同意一次
                            errorCount++;
                            continue;
                        }
                        customerServiceOrder.setProStatus(proStatus);
                        customerServiceOrder.setStatus("0");//进行中
                        customerServiceOrder.setUpdateDate(now);

                        CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
                        customerServiceStatusLog.setCreateBy(id);
                        customerServiceStatusLog.setCreateDate(now);
                        customerServiceStatusLog.setUpdateDate(now);
                        customerServiceStatusLog.setDelFlag("0");
                        customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
                        customerServiceStatusLog.setStatus("0");//进行中
                        customerServiceStatusLog.setProStatus(proStatus);

                        CustomerServiceLog customerServiceLog = new CustomerServiceLog();
                        customerServiceLog.setCreateBy(id);
                        customerServiceLog.setCreateDate(now);
                        customerServiceLog.setUpdateDate(now);
                        customerServiceLog.setDelFlag("0");
                        customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
                        customerServiceLog.setOperatorType(CustomerServiceLogCustom.OPERATOR_TYPE_MCHT);//商家
                        customerServiceLog.setContent(content);

                        SubOrder subOrder = subOrderService.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
                        CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());

                        SysAppMessage sysAppMessage = new SysAppMessage();
                        OrderDtl orderDtl = null;
                        RefundOrder refundOrder = null;
                        IntegralDtl integralDtl = null;
                        MemberAccount memberAccount = null;
                        MchtDeposit mchtDeposit = null;
                        MchtDepositDtl mdd = null;
                        List<RefundOrder> refundOrderList = new ArrayList<>();
                        if (isRefund.equals("1")) {//同意退款操作
                            BigDecimal amount = customerServiceOrder.getAmount();
                            if (customerServiceOrder.getOrderDtlId() != null) {
                                orderDtl = orderDtlService.selectByPrimaryKey(customerServiceOrder.getOrderDtlId());
                                if (amount.compareTo(new BigDecimal(0)) <= 0) {//针对换货转退货退款
                                    amount = orderDtl.getPayAmount();
                                    customerServiceOrder.setAmount(amount);
                                }
                                if (customerServiceOrder.getServiceType().equals("B")) {//B.退货单
                                    orderDtl.setProductStatus("3");//1 完成 2退款 3退货
                                }
                                //根据订单明细id获取所有的售后单，进而获取相应的退款单，若存在退款单，则不生成退款单，以免重复退款
                                CustomerServiceOrderExample example = new CustomerServiceOrderExample();
                                CustomerServiceOrderExample.Criteria criteria = example.createCriteria();
                                criteria.andDelFlagEqualTo("0");
                                criteria.andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId());
                                List<CustomerServiceOrder> customerServiceOrders2 = dao.selectByExample(example);
                                List<Integer> customerServiceOrderIds2 = new ArrayList<Integer>();
                                if (customerServiceOrders2 != null && customerServiceOrders2.size() > 0) {
                                    for (CustomerServiceOrder cso : customerServiceOrders2) {
                                        customerServiceOrderIds2.add(cso.getId());
                                    }
                                } else {
                                    customerServiceOrderIds2.add(0);
                                }
                                RefundOrderExample e = new RefundOrderExample();
                                RefundOrderExample.Criteria c = e.createCriteria();
                                c.andDelFlagEqualTo("0");
                                c.andServiceOrderIdIn(customerServiceOrderIds2);
                                c.andOrderTypeEqualTo("1");//1.总订单
                                List<RefundOrder> ros = refundOrderService.selectByExample(e);
                                if (ros != null && ros.size() > 0) {
                                    errorCount++;
                                    continue;
                                }
                            }
                            //往退款单表里插入数据,先判断退款单是否存在
                            RefundOrderExample example = new RefundOrderExample();
                            RefundOrderExample.Criteria criteria = example.createCriteria();
                            criteria.andDelFlagEqualTo("0");
                            criteria.andServiceOrderIdEqualTo(customerServiceOrder.getId());
                            criteria.andOrderTypeEqualTo("1");//1.总订单
                            List<RefundOrder> refundOrders = refundOrderService.selectByExample(example);
                            if (refundOrders != null && refundOrders.size() > 0) {
                                errorCount++;
                                continue;
                            }
                            refundOrder = new RefundOrder();
                            refundOrder.setDelFlag("0");
                            refundOrder.setCreateBy(id);
                            refundOrder.setCreateDate(now);
                            refundOrder.setUpdateDate(now);
                            refundOrder.setCombineOrderId(subOrder.getCombineOrderId());
                            if (customerServiceOrder.getServiceType().equals("C") && customerServiceOrder.getProStatus().equals("B5")) {//换货改退货
                                refundOrder.setServiceType("B");
                            } else {
                                refundOrder.setServiceType(customerServiceOrder.getServiceType());
                            }
                            refundOrder.setServiceOrderId(customerServiceOrder.getId());
                            refundOrder.setRefundAmount(amount);
                            refundOrder.setRefundAgreeDate(new Date());
                            refundOrder.setRefundMethod("1");
                            refundOrder.setTryTimes(0);
                            refundOrder.setStatus("0");
                            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
                            if (combineOrder.getPaymentId() == 1 || combineOrder.getPaymentId() == 6) {
                                refundOrder.setRefundCode("ZFB" + sf.format(new Date()) + "T");
                            } else if (combineOrder.getPaymentId() == 2 || combineOrder.getPaymentId() == 5) {
                                refundOrder.setRefundCode("WX" + sf.format(new Date()) + "T");
                            } else if (combineOrder.getPaymentId() == 3) {
                                refundOrder.setRefundCode("YL" + sf.format(new Date()) + "T");
                            } else if (combineOrder.getPaymentId() == 4) {
                                refundOrder.setRefundCode("GZH" + sf.format(new Date()) + "T");
                            }
                            refundOrder.setOrderType("1");
                            //返还积分
                            int result = orderDtl.getIntegralPreferential().compareTo(new BigDecimal(0));
                            if (result > 0) {
                                MemberAccountExample mae = new MemberAccountExample();
                                MemberAccountExample.Criteria c = mae.createCriteria();
                                c.andDelFlagEqualTo("0");
                                c.andMemberIdEqualTo(customerServiceOrder.getCreateBy());
                                List<MemberAccount> memberAccounts = memberAccountService.selectByExample(mae);
                                if (memberAccounts != null && memberAccounts.size() > 0) {
                                    Integer integral = Integer.parseInt(orderDtl.getIntegralPreferential().multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
                                    memberAccount = memberAccounts.get(0);
                                    memberAccount.setIntegral(memberAccount.getIntegral() + integral);
                                    memberAccount.setUpdateBy(id);
                                    memberAccount.setUpdateDate(new Date());
                                    integralDtl = new IntegralDtl();
                                    integralDtl.setAccId(memberAccount.getId());
                                    integralDtl.setTallyMode("1");
                                    integralDtl.setType(7);
                                    integralDtl.setIntegral(integral);
                                    integralDtl.setBalance(memberAccount.getIntegral());
                                    integralDtl.setOrderId(orderDtl.getSubOrderId());
                                    integralDtl.setCreateBy(id);
                                    integralDtl.setCreateDate(new Date());
                                    integralDtl.setDelFlag("0");
                                }
                            }

                            // 返还津贴
                            if (orderDtl.getAllowance() != null && orderDtl.getAllowance().compareTo(BigDecimal.ZERO) > 0) {
                                MemberAllowance memberAllowance = new MemberAllowance();
                                memberAllowance.setMemberId(combineOrder.getMemberId());
                                memberAllowance.setSource("2");
                                memberAllowance.setAllowanceAmount(orderDtl.getAllowance().setScale(0,BigDecimal.ROUND_DOWN));//购物津贴截取取整
                                memberAllowance.setCreateDate(new Date());
                                memberAllowance.setDelFlag("0");
                                memberAllowanceMapper.insert(memberAllowance);
                            }

                            //TODO 定金退款单生成处理
                            if (customerServiceOrder.getDepositAmount() != null && customerServiceOrder.getDepositAmount().compareTo(new BigDecimal(0)) > 0) {
                                SubDepositOrderExample sdoe = new SubDepositOrderExample();
                                sdoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(id).andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId());
                                List<SubDepositOrder> subDepositOrders = subDepositOrderMapper.selectByExample(sdoe);
                                //循环插入退款单表数据
                                for (SubDepositOrder subDepositOrder : subDepositOrders) {
                                    RefundOrder depositRefundOrder = new RefundOrder();
                                    depositRefundOrder.setDelFlag("0");
                                    depositRefundOrder.setCreateBy(id);
                                    depositRefundOrder.setCreateDate(now);
                                    depositRefundOrder.setUpdateDate(now);
                                    depositRefundOrder.setCombineOrderId(subOrder.getCombineOrderId());
                                    Integer combineDepositOrderId = subDepositOrder.getCombineDepositOrderId();
                                    depositRefundOrder.setCombineDepositOrderId(combineDepositOrderId);
                                    CombineDepositOrder combineDepositOrder = combineDepositOrderMapper.selectByPrimaryKey(combineDepositOrderId);
                                    if (combineDepositOrder.getPaymentId() == 1 || combineDepositOrder.getPaymentId() == 6) {
                                        depositRefundOrder.setRefundCode("ZFB" + sf.format(new Date()) + "T");
                                    } else if (combineDepositOrder.getPaymentId() == 2 || combineDepositOrder.getPaymentId() == 5) {
                                        depositRefundOrder.setRefundCode("WX" + sf.format(new Date()) + "T");
                                    } else if (combineDepositOrder.getPaymentId() == 3) {
                                        depositRefundOrder.setRefundCode("YL" + sf.format(new Date()) + "T");
                                    } else if (combineDepositOrder.getPaymentId() == 4) {
                                        depositRefundOrder.setRefundCode("GZH" + sf.format(new Date()) + "T");
                                    }
                                    if (customerServiceOrder.getServiceType().equals("C") && customerServiceOrder.getProStatus().equals("B5")) {//换货改退货
                                        depositRefundOrder.setServiceType("B");
                                    } else {
                                        depositRefundOrder.setServiceType(customerServiceOrder.getServiceType());
                                    }
                                    depositRefundOrder.setServiceOrderId(customerServiceOrder.getId());
                                    depositRefundOrder.setRefundAmount(subDepositOrder.getDeposit());
                                    depositRefundOrder.setRefundAgreeDate(new Date());
                                    depositRefundOrder.setRefundMethod("1");
                                    depositRefundOrder.setTryTimes(0);
                                    depositRefundOrder.setStatus("0");
                                    depositRefundOrder.setOrderType("2");
                                    refundOrderList.add(depositRefundOrder);
                                }
                            }
                            //事务，商家责任  没
                            updateCustomerServiceOrder(customerServiceOrder, customerServiceStatusLog, customerServiceLog, orderDtl, refundOrder, subOrder, sysAppMessage, integralDtl, memberAccount, mchtDeposit, mdd, refundOrderList);
                        }
                    }
                }
                //换货，同意并给出默认模板地址
                if (customerServiceOrder.getServiceType().equals("C")) {
                    proStatus = "C2";
                    content = "同意售后换货申请,请客户寄件到以下地址：收货人：" + recipient + ",联系电话：" + phone + ",地址：" + finaladdress.toString() + ",注意事项：" + remarks;
                    if (!customerServiceOrder.getProStatus().equals("C1")) {
                        errorCount++;
                        continue;
                    }
                    if (customerServiceOrder.getProStatus().equals(proStatus)) {
                        errorCount++;
                        continue;
                    }
                    customerServiceOrder.setAddressType("0");
                    customerServiceOrder.setProStatus(proStatus);
                    customerServiceOrder.setStatus("0");//进行中
                    customerServiceOrder.setUpdateDate(now);
                    //换货单的寄回地址优化
                    customerServiceOrder.setProductReturnAddress(finaladdress.toString());
                    customerServiceOrder.setProductReturnContactPhone(phone);
                    customerServiceOrder.setProductReturnConsignee(recipient);

                    CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
                    customerServiceStatusLog.setCreateBy(id);
                    customerServiceStatusLog.setCreateDate(now);
                    customerServiceStatusLog.setUpdateDate(now);
                    customerServiceStatusLog.setDelFlag("0");
                    customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
                    customerServiceStatusLog.setStatus("0");//进行中
                    customerServiceStatusLog.setProStatus(proStatus);

                    CustomerServiceLog customerServiceLog = new CustomerServiceLog();
                    customerServiceLog.setCreateBy(id);
                    customerServiceLog.setCreateDate(now);
                    customerServiceLog.setUpdateDate(now);
                    customerServiceLog.setDelFlag("0");
                    customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
                    customerServiceLog.setOperatorType(CustomerServiceLogCustom.OPERATOR_TYPE_MCHT);//商家
                    customerServiceLog.setContent(content);

                    SubOrder subOrder = subOrderService.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
                    CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
                    SysAppMessage sysAppMessage = new SysAppMessage();

                    OrderDtl orderDtl = null;
                    RefundOrder refundOrder = null;
                    IntegralDtl integralDtl = null;
                    MemberAccount memberAccount = null;
                    MchtDeposit mchtDeposit = null;
                    MchtDepositDtl mdd = null;
                    List<RefundOrder> refundOrderList = new ArrayList<>();
                    //事务
                    updateCustomerServiceOrder(customerServiceOrder, customerServiceStatusLog, customerServiceLog, orderDtl, refundOrder, subOrder, sysAppMessage, integralDtl, memberAccount, mchtDeposit, mdd, refundOrderList);
                    String toSendMobile = "";
                    String contactPhone = customerServiceOrder.getContactPhone();
                    if (StringUtil.isEmpty(contactPhone)) {
                        MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(combineOrder.getMemberId());
                        String mobile = memberInfo.getMobile();
                        if (StringUtil.isEmpty(mobile)) {
                            String receiverPhone = combineOrder.getReceiverPhone();
                            if (!StringUtil.isEmpty(receiverPhone)) {
                                if (StringUtil.isMobileNO(receiverPhone)) {
                                    toSendMobile = receiverPhone;
                                }
                            }
                        } else {
                            if (StringUtil.isMobileNO(mobile)) {
                                toSendMobile = mobile;
                            }
                        }
                    } else {
                        if (StringUtil.isMobileNO(contactPhone)) {
                            toSendMobile = contactPhone;
                        }
                    }
                    if (!StringUtil.isEmpty(toSendMobile)) {
                        try {
                            if (proStatus.equals(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_RETURN_GOODS) || proStatus.equals(CustomerServiceStatusLogCustom.ORDER_AGREE_APPLY_EXCHANGE_GOODS)) {
                                JSONObject param = new JSONObject();
                                JSONObject reqData = new JSONObject();
                                reqData.put("mobile", toSendMobile);
                                reqData.put("content", "商家已同意您的申请，请尽快登录APP，在个人中心“退款/售后”找到相应的售后单填写寄回物流单号。");
                                reqData.put("smsType", "4");
                                param.put("reqData", reqData);
                                JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl + "/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (ArgException arge) {
            arge.printStackTrace();
            return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回消息类
        Map<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("successCount", idList.size() - errorCount);
        returnData.put("errorCount", errorCount);

        return new ResponseMsg(ResponseMsg.SUCCESS, "操作成功", returnData);
    }
}

