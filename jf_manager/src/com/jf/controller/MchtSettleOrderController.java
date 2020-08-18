package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.entity.MchtInfoCustomExample.MchtInfoCustomCriteria;
import com.jf.service.*;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MchtSettleOrderController extends BaseController {

    @Resource
    private PayToMchtLogService payToMchtLogService;

    @Resource
    private MchtSettleOrderService mchtSettleOrderService;

    @Resource
    private MchtInfoService mchtInfoService;

    @Resource
    private MchtBankAccountService mchtBankAccountService;

    @Resource
    private MchtPlatformContactService mchtPlatformContactService;

    @Resource
    private PlatformContactService platformContactService;

    @Resource
    private MchtTaxInvoiceInfoService mchtTaxInvoiceInfoService;

    @Resource
    private MchtDepositService mchtDepositService;

    @Resource
    private MchtDepositDtlService mchtDepositDtlService;

    @Resource
    private SysStaffInfoService sysStaffInfoService;

    @Resource
    private AreaService areaService;

    @Resource
    private CustomerServiceOrderService customerServiceOrderService;

    @Resource
    private OrderDtlService orderDtlService;

    @Resource
    private SysParamCfgService sysParamCfgService;

    @Resource
    private ExpressService expressService;

    @Resource
    private MchtContactService mchtContactService;

    @Resource
    private ProductTypeService productTypeService;

    @Autowired
    private ProductBrandService productBrandService;

    @Autowired
    private SubDepositOrderService subDepositOrderService;

    @Autowired
    private PopSettlePlanService popSettlePlanservice;
    private static final long serialVersionUID = 1L;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Author Jun
     * @Description 结算单续缴
     * @Date 15:04 2020/1/10
     * @Param [request]
     */
    @RequestMapping(value = "/mchtSettleOrder/planPay.shtml")
    public ModelAndView planPay(HttpServletRequest request) {
        String rtPage = "/mchtSettleOrder/pay/continuePay";
        Map<String, Object> resMap = new HashMap<String, Object>();
        //比较保证金还需补缴金额和还需支付金额大小,取小的
        Integer id = Integer.valueOf(request.getParameter("id"));
        MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(id);
        MchtDepositExample where = new MchtDepositExample();
        where.createCriteria().andMchtIdEqualTo(mchtSettleOrder.getMchtId().intValue()).andDelFlagEqualTo("0");
        List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(where);
        BigDecimal needPayAmount = mchtSettleOrder.getNeedPayAmount();
        for (MchtDeposit mchtDeposit : mchtDeposits) {
            BigDecimal unpayAmt = mchtDeposit.getUnpayAmt();
            //BigDecimal needPayAmount = new BigDecimal(2.00);
            if (unpayAmt.compareTo(needPayAmount) == -1) {
                resMap.put("pay", unpayAmt);
            } else {
                resMap.put("pay", needPayAmount);
            }
            resMap.put("unpayAmt", unpayAmt);
        }
        resMap.put("needPayAmount", needPayAmount);
        resMap.put("id", id);
        return new ModelAndView(rtPage, resMap);
    }

    @RequestMapping(value = "/mchtSettleOrder/planPayCommit.shtml")
    @ResponseBody
    public Map<String, Object> planPayCommit(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "确认成功");
        try {
            String staffId = this.getSessionStaffBean(request).getStaffID();
            Integer id = Integer.valueOf(request.getParameter("id"));
            String pay = request.getParameter("pay");
            if (Double.parseDouble(pay)<=0) {
                resMap.put("returnCode", "4004");
                resMap.put("returnMsg", "要提交的金额必须大于零");
            }
            BigDecimal payBigDecimal = new BigDecimal(pay);
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(id);
            MchtDepositExample where = new MchtDepositExample();
            where.createCriteria().andMchtIdEqualTo(mchtSettleOrder.getMchtId().intValue()).andDelFlagEqualTo("0");
            List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(where);
            BigDecimal needPayAmount = mchtSettleOrder.getNeedPayAmount();
            if (needPayAmount.compareTo(payBigDecimal) == -1) {
                resMap.put("returnCode", "4004");
                resMap.put("returnMsg", "续缴输入金额不能大于结算单还需支付金额");
            }
            for (MchtDeposit mchtDeposit : mchtDeposits) {
                BigDecimal unpayAmt = mchtDeposit.getUnpayAmt();
                if (unpayAmt.compareTo(payBigDecimal) == -1) {
                    resMap.put("returnCode", "4004");
                    resMap.put("returnMsg", "续缴输入金额不能大于保证金还需补缴金额");
                }
            }
//      POP结算列表的字段相应修改
            BigDecimal newDepositAmount = mchtSettleOrder.getDepositAmount().add(payBigDecimal);
            BigDecimal newNeedPayAmount = mchtSettleOrder.getNeedPayAmount().subtract(payBigDecimal);
            mchtSettleOrder.setDepositAmount(newDepositAmount);
            mchtSettleOrder.setNeedPayAmount(newNeedPayAmount);
            mchtSettleOrderService.updateByPrimaryKeySelective(mchtSettleOrder);
//      商家保证金信息对应变化
            for (MchtDeposit mchtDeposit : mchtDeposits) {
                mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().add(payBigDecimal));
                mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().subtract(payBigDecimal));
                mchtDepositService.updateByPrimaryKeySelective(mchtDeposit);
//      余额变化明细表对应修改
                MchtDepositDtlCustomExample w = new MchtDepositDtlCustomExample();
                w.createCriteria().andDepositIdEqualTo(mchtDeposit.getId());
                w.setOrderByClause("create_date DESC");
                w.setLimitStart(0);
                w.setLimitSize(1);
                List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService.selectByExample(w);
                for (MchtDepositDtlCustom mchtDepositDtlCustom : mchtDepositDtlCustoms) {
                    MchtDepositDtl mchtDepositDtl = new MchtDepositDtl();
                    mchtDepositDtl.setDepositId(mchtDepositDtlCustom.getDepositId());
                    mchtDepositDtl.setBizId(id);
                    mchtDepositDtl.setBizType("3");
                    mchtDepositDtl.setTxnAmt(payBigDecimal);
                    mchtDepositDtl.setPayAmt(mchtDepositDtlCustom.getPayAmt().add(payBigDecimal));
                    mchtDepositDtl.setTxnType("C");
                    mchtDepositDtl.setTypeSub("C1");
                    mchtDepositDtl.setRemarks("货款划到保证金");
                    mchtDepositDtl.setCreateBy(Integer.valueOf(staffId));
                    mchtDepositDtl.setCreateDate(new Date());
                    mchtDepositDtl.setDelFlag("0");
                    //mchtDepositDtlCustom.setId(null);
                    mchtDepositDtlService.insertSelective(mchtDepositDtl);
                }
            }
        } catch (NumberFormatException e) {
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "系统繁忙,请联系管理员");
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * POP结算列表
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/list.shtml")
    public ModelAndView list(HttpServletRequest request) {
        String rtPage = "/mchtSettleOrder/mchtSettleOrderList";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String selectedConfirmStatus = request.getParameter("confirmStatus");
        if (StringUtils.isEmpty(selectedConfirmStatus)) {
            selectedConfirmStatus = "3";
        }
        resMap.put("mchtCode", request.getParameter("mchtCode"));
        resMap.put("selectedConfirmStatus", selectedConfirmStatus);
        resMap.put("confirmStatusList", DataDicUtil.getStatusList("BU_MCHT_SETTLE_ORDER", "CONFIRM_STATUS"));
        resMap.put("platformInvoiceStatusList", DataDicUtil.getStatusList("BU_MCHT_SETTLE_ORDER", "PLATFORM_INVOICE_STATUS"));
        resMap.put("payStatusList", DataDicUtil.getStatusList("BU_MCHT_SETTLE_ORDER", "PAY_STATUS"));
        resMap.put("mchtCollectTypeList", DataDicUtil.getStatusList("BU_MCHT_SETTLE_ORDER", "MCHT_COLLECT_TYPE"));
        ProductTypeExample pte = new ProductTypeExample();
        ProductTypeExample.Criteria c = pte.createCriteria();
        c.andDelFlagEqualTo("0");
        c.andStatusEqualTo("1");
        c.andTLevelEqualTo(1);
        List<ProductType> productTypes = productTypeService.selectByExample(pte);
        resMap.put("productTypes", productTypes);
        resMap.put("needPayAmount", request.getParameter("needPayAmount"));
        resMap.put("pay_status_list", request.getParameter("pay_status_list"));
        resMap.put("toHide", request.getParameter("toHide"));
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * POP结算列表数据
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/data.shtml")
    @ResponseBody
    public Map<String, Object> data(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Integer totalCount = 0;
        try {
            MchtSettleOrderCustomExample example = new MchtSettleOrderCustomExample();
            MchtSettleOrderCustomExample.MchtSettleOrderCustomCriteria criteria = example.createCriteria();
            if (!StringUtil.isEmpty(request.getParameter("delFlag"))) {
                criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
            } else {
                criteria.andDelFlagEqualTo("0");
            }
            String mchtType = request.getParameter("mchtType");
            //criteria.andMchtTypeEqualTo("2");//POP
            if (!StringUtil.isEmpty(mchtType)) {
                criteria.andMchtTypeEqualTo(mchtType);
            }
            String searchMchtCode = request.getParameter("mchtCode");
            if (!StringUtil.isEmpty(searchMchtCode)) {
                criteria.andMchtCodeEqualTo(searchMchtCode);
            }
            if (!StringUtil.isEmpty(request.getParameter("id"))) {
                criteria.andIdEqualTo(Integer.parseInt(request.getParameter("id")));
            }
            String searchConfirmStatus = request.getParameter("confirmStatus");
            if (!StringUtil.isEmpty(searchConfirmStatus)) {
                criteria.andConfirmStatusEqualTo(searchConfirmStatus);
            }
            String searchPlatformInvoiceStatus = request.getParameter("platformInvoiceStatus");
            if (!StringUtil.isEmpty(searchPlatformInvoiceStatus)) {
                criteria.andPlatformInvoiceStatusEqualTo(searchPlatformInvoiceStatus);
            }
            String searchPayStatus = request.getParameter("payStatus");
            if (!StringUtil.isEmpty(searchPayStatus)) {
                criteria.andPayStatusEqualTo(searchPayStatus);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (!StringUtil.isEmpty(request.getParameter("beginDate"))) {
                criteria.andBeginDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("beginDate")));
            }
            if (!StringUtil.isEmpty(request.getParameter("endDate"))) {
                criteria.andEndDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("endDate")));
            }
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(request.getParameter("mcht_confirm_date_begin"))) {
                criteria.andMchtConfirmDateGreaterThanOrEqualTo(dateFormat2.parse(request.getParameter("mcht_confirm_date_begin") + " 00:00:00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("mcht_confirm_date_end"))) {
                criteria.andMchtConfirmDateLessThanOrEqualTo(dateFormat2.parse(request.getParameter("mcht_confirm_date_end") + " 23:59:59"));
            }
			/*String settleAmount = request.getParameter("settleAmount");
			if(!StringUtil.isEmpty(settleAmount) ){
				if(settleAmount.equals("0")){
					criteria.andSettleAmountEqualTo(new BigDecimal(0));
				}else{
					criteria.andSettleAmountGreaterThan(new BigDecimal(0));
				}
			}*/
            String mchtDeposit = request.getParameter("mchtDeposit");
            if (!StringUtil.isEmpty(mchtDeposit)) {
                if (mchtDeposit.equals("0")) {
                    criteria.andMchtDepositUnpayAmtEqualTo();
                }
                if (mchtDeposit.equals("1")) {
                    criteria.andMchtDepositUnpayAmtGreaterThanZero();
                }
                if (mchtDeposit.equals("2")) {
                    criteria.andMchtDepositUnpayAmtLessThanZero();
                }
            }
            //搜索时结算价金额改成对订单金额的搜索
            String orderAmountBegin = request.getParameter("orderAmountBegin");
            String orderAmountEnd = request.getParameter("orderAmountEnd");
            if (!StringUtil.isEmpty(orderAmountBegin)) {
                //criteria.andSettleAmountGreaterThanOrEqualTo(new BigDecimal(settleAmountBegin));
                criteria.andOrderAmountGreaterThanOrEqualTo(new BigDecimal(orderAmountBegin));
            }
            if (!StringUtil.isEmpty(orderAmountEnd)) {
                //criteria.andSettleAmountLessThanOrEqualTo(new BigDecimal(settleAmountEnd));
                criteria.andOrderAmountLessThanOrEqualTo(new BigDecimal(orderAmountEnd));
            }
            String mchtCollectType = request.getParameter("mchtCollectType");
            if (!StringUtil.isEmpty(mchtCollectType)) {
                criteria.andMchtCollectTypeEqualTo(mchtCollectType);
            }
            String productTypeId = request.getParameter("productTypeId");
            if (!StringUtil.isEmpty(productTypeId)) {
                criteria.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
            }
            String mchtName = request.getParameter("mchtName");
            if (!StringUtil.isEmpty(mchtName)) {
                criteria.andMchtNameLikeTo(mchtName);
            }

            if (!StringUtil.isEmpty(request.getParameter("productBrandName"))) {
                criteria.andProductBrandNameEqualTo("%" + request.getParameter("productBrandName") + "%");
            }
			/*if(!StringUtil.isEmpty(request.getParameter("mchtDeposit"))){
				if(request.getParameter("mchtDeposit").equals("0")){//需补缴
					criteria.andMchtDepositUnpayAmtGreaterThanZero();
				}else{//无需补缴
					criteria.andMchtDepositUnpayAmtLessThanZero();
				}
			}*/
            if (!StringUtil.isEmpty(request.getParameter("pay_status_list"))) {
                criteria.andPayStatusIn(Arrays.asList("1,2".split(",")));
            }
            if (!StringUtil.isEmpty(request.getParameter("needPayAmount"))) {
                criteria.andNeedPayAmountNotEqualTo(new BigDecimal(0));
            }
            totalCount = mchtSettleOrderService.countByExample(example);
            example.setLimitStart(page.getLimitStart());
            example.setLimitSize(page.getLimitSize());
            example.setOrderByClause("t.id desc");
            List<MchtSettleOrderCustom> mchtSettleOrderCustoms = mchtSettleOrderService.selectByExample(example);
            resMap.put("Rows", mchtSettleOrderCustoms);
            resMap.put("Total", totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * @MethodName allocationPlanIndex
     * @Description TODO(POP排款计划表)
     * @author chengh
     * @date 2019年8月22日 上午11:34:40
     */
    @RequestMapping(value = "/mchtSettleOrder/allocationPlanIndex.shtml")
    public ModelAndView allocationPlanIndex(HttpServletRequest request) {
        String rtPage = "/mchtSettleOrder/allocationPlanIndex";
        Map<String, Object> resMap = new HashMap<String, Object>();
        ProductTypeExample pte = new ProductTypeExample();
        ProductTypeExample.Criteria c = pte.createCriteria();
        c.andDelFlagEqualTo("0");
        c.andStatusEqualTo("1");
        c.andTLevelEqualTo(1);
        List<ProductType> productTypes = productTypeService.selectByExample(pte);
        resMap.put("productTypes", productTypes);
        return new ModelAndView(rtPage, resMap);
    }

    @RequestMapping(value = "/mchtSettleOrder/allocationPlanList.shtml")
    @ResponseBody
    public Map<String, Object> allocationPlanList(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Integer totalCount = 0;
        try {
            MchtInfoCustomExample mchtInfoCustomExample = new MchtInfoCustomExample();
            MchtInfoCustomCriteria criteria = mchtInfoCustomExample.createCriteria();
            criteria.andDelFlagCustomEqualTo();
            //商家序号
            if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
                criteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
            }
            //商家名称
            if (!StringUtil.isEmpty(request.getParameter("companyOrShop"))) {
                criteria.andCompanyOrShopNameLike(request.getParameter("companyOrShop"));
            }
            //类目
            if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
                criteria.andProductTypeIdEqualTo(Integer.parseInt(request.getParameter("productTypeId")));
            }
            //结算类型
            if (!StringUtil.isEmpty(request.getParameter("type"))) {
                criteria.andPopSettlePlanTypeEqualTo(request.getParameter("type"));
            }

            totalCount = mchtInfoService.countByCustomExample(mchtInfoCustomExample);
            mchtInfoCustomExample.setLimitStart(page.getLimitStart());
            mchtInfoCustomExample.setLimitSize(page.getLimitSize());
            List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.selectByCustomExample(mchtInfoCustomExample);
            //用于收集超期的数据IdList
            List<Integer> idList = new ArrayList<>();
            for (MchtInfoCustom mchtInfoCustom : mchtInfoCustoms) {
                //如果POP排款计划表是有对应的数据，才进行超期金额的计算
                if (mchtInfoCustom.getPopId() != null) {
                    int count = mchtInfoCustom.getUnpaidCount() - mchtInfoCustom.getPeriods();//超时几期
                    if (count > 0) {
                        List<String> StringIdList = Arrays.asList(mchtInfoCustom.getSettleOrderIds().split(","));
                        for (int i = 0; i < count; i++) {
                            idList.add(Integer.parseInt(StringIdList.get(i)));
                        }
                    }
                }
            }
            //当idList不为空，说明有超期金额需要计算
            if (!idList.isEmpty()) {
                //获取全部超期的数据
                MchtSettleOrderCustomExample mchtSettleOrderCustomExample = new MchtSettleOrderCustomExample();
                mchtSettleOrderCustomExample.createCriteria().andDelFlagEqualTo("0").andIdIn(idList);
                List<MchtSettleOrderCustom> mchtSettleOrderCustoms = mchtSettleOrderService.selectByCustomExample(mchtSettleOrderCustomExample);
                //对查询出来的数据进行循环赋值
                Map<Integer, BigDecimal> map = new HashMap<>();
                for (MchtSettleOrderCustom mchtSettleOrderCustom : mchtSettleOrderCustoms) {
                    map.put(mchtSettleOrderCustom.getMchtId(), mchtSettleOrderCustom.getExceedAmount());
                }
                for (MchtInfoCustom mchtInfoCustom : mchtInfoCustoms) {
                    if (map.containsKey(mchtInfoCustom.getId())) {
                        BigDecimal amount = map.get(mchtInfoCustom.getId());
                        mchtInfoCustom.setExceedAmount(amount);
                    }
                }
            }
            resMap.put("Rows", mchtInfoCustoms);
            resMap.put("Total", totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }


    /**
     * @MethodName audit
     * @Description TODO(编辑POP排款计划)
     * @author chengh
     * @date 2019年8月23日 下午3:33:51
     */
    @RequestMapping("/mchtSettleOrder/audit.shtml")
    public ModelAndView audit(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String rtPage = "/mchtSettleOrder/audit";
        //判断是新增还是修改
        String popId = request.getParameter("id");
        if (popId != null && !"null".equals(popId) && !popId.equals("")) {
            PopSettlePlan popSettlePlan = popSettlePlanservice.selectByPrimaryKey(Integer.parseInt(popId));
            map.put("popSettlePlan", popSettlePlan);
        }
        map.put("mchtId", request.getParameter("mchtId"));
        return new ModelAndView(rtPage, map);
    }


    /**
     * @MethodName auditSubmit(POP排款计划提交)
     * @Description TODO
     * @author chengh
     * @date 2019年8月23日 下午3:45:11
     */
    @ResponseBody
    @RequestMapping("/mchtSettleOrder/auditSubmit.shtml")
    public Map<String, Object> auditSubmit(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
        String msg = "保存成功！";
        try {
            int staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
            PopSettlePlan popSettlePlan = new PopSettlePlan();
            popSettlePlan.setType(request.getParameter("type"));
            popSettlePlan.setPeriods(Integer.parseInt(request.getParameter("periods")));
            popSettlePlan.setUpdateBy(staffId);
            popSettlePlan.setUpdateDate(new Date());
            if (!org.apache.commons.lang.StringUtils.isBlank(request.getParameter("id"))) {
                popSettlePlan.setId(Integer.parseInt(request.getParameter("id")));
                popSettlePlanservice.updateByPrimaryKeySelective(popSettlePlan);
            } else {
                popSettlePlan.setMchtId(Integer.parseInt(request.getParameter("mchtId")));
                popSettlePlan.setCreateBy(staffId);
                popSettlePlan.setCreateDate(new Date());
                popSettlePlan.setDelFlag("0");
                popSettlePlanservice.insertSelective(popSettlePlan);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            code = StateCode.JSON_AJAX_ERROR.getStateCode();
            msg = StateCode.ERR_DB_INVOKE.getStateMessage();
        }
        // 审核、重审操作
        map.put("statusCode", code);
        map.put("message", msg);
        return map;
    }

    /**
     * 导出POPexcel
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mchtSettleOrder/exportMchtSettleOrder.shtml")
    public void exportMchtSettleOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            MchtSettleOrderCustomExample example = new MchtSettleOrderCustomExample();
            MchtSettleOrderCustomExample.MchtSettleOrderCustomCriteria criteria = example.createCriteria();
            if (!StringUtil.isEmpty(request.getParameter("delFlag"))) {
                criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
            } else {
                criteria.andDelFlagEqualTo("0");
            }
            String mchtType = request.getParameter("mchtType");
            //criteria.andMchtTypeEqualTo("2");//POP
            if (!StringUtil.isEmpty(mchtType)) {
                criteria.andMchtTypeEqualTo(mchtType);
            }
            String searchMchtCode = request.getParameter("mchtCode");
            if (!StringUtil.isEmpty(searchMchtCode)) {
                criteria.andMchtCodeEqualTo(searchMchtCode);
            }
            if (!StringUtil.isEmpty(request.getParameter("id"))) {
                criteria.andIdEqualTo(Integer.parseInt(request.getParameter("id")));
            }
            String searchConfirmStatus = request.getParameter("confirmStatus");
            if (!StringUtil.isEmpty(searchConfirmStatus)) {
                criteria.andConfirmStatusEqualTo(searchConfirmStatus);
            }
            String searchPlatformInvoiceStatus = request.getParameter("platformInvoiceStatus");
            if (!StringUtil.isEmpty(searchPlatformInvoiceStatus)) {
                criteria.andPlatformInvoiceStatusEqualTo(searchPlatformInvoiceStatus);
            }
            String searchPayStatus = request.getParameter("payStatus");
            if (!StringUtil.isEmpty(searchPayStatus)) {
                criteria.andPayStatusEqualTo(searchPayStatus);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (!StringUtil.isEmpty(request.getParameter("beginDate"))) {
                criteria.andBeginDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("beginDate")));
            }
            if (!StringUtil.isEmpty(request.getParameter("endDate"))) {
                criteria.andEndDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("endDate")));
            }
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(request.getParameter("mcht_confirm_date_begin"))) {
                criteria.andMchtConfirmDateGreaterThanOrEqualTo(dateFormat2.parse(request.getParameter("mcht_confirm_date_begin") + " 00:00:00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("mcht_confirm_date_end"))) {
                criteria.andMchtConfirmDateLessThanOrEqualTo(dateFormat2.parse(request.getParameter("mcht_confirm_date_end") + " 23:59:59"));
            }
			/*String settleAmount = request.getParameter("settleAmount");
			if(!StringUtil.isEmpty(settleAmount) ){
				if(settleAmount.equals("0")){
					criteria.andSettleAmountEqualTo(new BigDecimal(0));
				}else{
					criteria.andSettleAmountGreaterThan(new BigDecimal(0));
				}
			}*/
            String depositAmount = request.getParameter("depositAmount");
            if (!StringUtil.isEmpty(depositAmount)) {
                if (depositAmount.equals("0")) {
                    criteria.andDepositAmountEqualTo(new BigDecimal(0));
                }
                if (depositAmount.equals("1")) {
                    criteria.andDepositAmountGreaterThan(new BigDecimal(0));
                }
                if (depositAmount.equals("2")) {
                    criteria.andDepositAmountLessThan(new BigDecimal(0));
                }
            }
            //导出时按照订单金额的搜索结果
            String orderAmountBegin = request.getParameter("orderAmountBegin");
            String orderAmountEnd = request.getParameter("orderAmountEnd");
            if (!StringUtil.isEmpty(orderAmountBegin)) {
                //criteria.andSettleAmountGreaterThanOrEqualTo(new BigDecimal(settleAmountBegin));
                criteria.andOrderAmountGreaterThanOrEqualTo(new BigDecimal(orderAmountBegin));
            }
            if (!StringUtil.isEmpty(orderAmountEnd)) {
                //criteria.andSettleAmountLessThanOrEqualTo(new BigDecimal(settleAmountEnd));
                criteria.andOrderAmountLessThanOrEqualTo(new BigDecimal(orderAmountEnd));
            }
            String mchtCollectType = request.getParameter("mchtCollectType");
            if (!StringUtil.isEmpty(mchtCollectType)) {
                criteria.andMchtCollectTypeEqualTo(mchtCollectType);
            }
            String productTypeId = request.getParameter("productTypeId");
            if (!StringUtil.isEmpty(productTypeId)) {
                criteria.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
            }
            String mchtName = request.getParameter("mchtName");
            if (!StringUtil.isEmpty(mchtName)) {
                criteria.andMchtNameLikeTo(mchtName);
            }

            if (!StringUtil.isEmpty(request.getParameter("productBrandName"))) {
                criteria.andProductBrandNameEqualTo("%" + request.getParameter("productBrandName") + "%");
            }
            List<MchtSettleOrderCustom> mchtSettleOrderCustoms = mchtSettleOrderService.selectByExample(example);
            String[] titles = {"结算单日期", "商家序号", "结算类型", "商家公司名称", "店铺名称", "类目", "品牌", "订单金额（元）", "直赔单金额（元）", "技术服务费（元）", "结算单金额（元）", "划到保证金（元）", "已实付金额（元）", "还需支付（元）", "确认状态", "商家确认", "开票类型", "开票状态", "排款日期", "付款状态", "付款日期", "平台优惠", "积分优惠", "毛利率"};
            ExcelBean excelBean = new ExcelBean("导出POP结算列表.xls",
                    "导出POP结算列表", titles);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            List<String[]> datas = new ArrayList<String[]>();
            for (MchtSettleOrderCustom mchtSettleOrderCustom : mchtSettleOrderCustoms) {
                String[] data = {
                        df.format(mchtSettleOrderCustom.getBeginDate()) + "到" + df.format(mchtSettleOrderCustom.getEndDate()),
                        mchtSettleOrderCustom.getMchtCode() + "-" + mchtSettleOrderCustom.getMchtId().toString(),
                        mchtSettleOrderCustom.getMchtType().equals("1") ? "SPOP" : "POP",
                        mchtSettleOrderCustom.getCompanyName(),
                        mchtSettleOrderCustom.getShopName(),
                        mchtSettleOrderCustom.getOpenProductType() == null ? "" : mchtSettleOrderCustom.getOpenProductType(),
                        mchtSettleOrderCustom.getOpenProductBrand() == null ? "" : mchtSettleOrderCustom.getOpenProductBrand(),
                        mchtSettleOrderCustom.getOrderAmount() == null ? "" : String.valueOf(mchtSettleOrderCustom.getOrderAmount()),
                        mchtSettleOrderCustom.getRefundAmount() == null ? "" : String.valueOf(mchtSettleOrderCustom.getRefundAmount()),
                        mchtSettleOrderCustom.getCommissionAmount() == null ? "" : String.valueOf(mchtSettleOrderCustom.getCommissionAmount()),
                        mchtSettleOrderCustom.getSettleAmount() == null ? "" : String.valueOf(mchtSettleOrderCustom.getSettleAmount()),
                        mchtSettleOrderCustom.getDepositAmount() == null ? "" : String.valueOf(mchtSettleOrderCustom.getDepositAmount()),
                        mchtSettleOrderCustom.getPayAmount() == null ? "" : String.valueOf(mchtSettleOrderCustom.getPayAmount()),
                        mchtSettleOrderCustom.getNeedPayAmount() == null ? "" : String.valueOf(mchtSettleOrderCustom.getNeedPayAmount()),
                        mchtSettleOrderCustom.getConfirmStatusDesc(),
                        mchtSettleOrderCustom.getMchtConfirmDate() == null ? "" : df.format(mchtSettleOrderCustom.getMchtConfirmDate()),
                        mchtSettleOrderCustom.getMchtCollectTypeName() == null ? "" : mchtSettleOrderCustom.getMchtCollectTypeName(),
                        mchtSettleOrderCustom.getPlatformInvoiceStatusDesc(),
                        mchtSettleOrderCustom.getPayReadyDate() == null ? "" : df.format(mchtSettleOrderCustom.getPayReadyDate()),
                        mchtSettleOrderCustom.getPayStatusDesc(),
                        mchtSettleOrderCustom.getPayDate() == null ? "" : df.format(mchtSettleOrderCustom.getPayDate()),
                        mchtSettleOrderCustom.getPlatformPreferential() == null ? "" : String.valueOf(mchtSettleOrderCustom.getPlatformPreferential()),
                        mchtSettleOrderCustom.getIntegralPreferential() == null ? "" : String.valueOf(mchtSettleOrderCustom.getIntegralPreferential()),
                        mchtSettleOrderCustom.getGrossRate() == null ? "" : mchtSettleOrderCustom.getGrossRate()
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 排款页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/toSetPayReadyDate.shtml")
    public ModelAndView toSetPayReadyDate(HttpServletRequest request) {
        String rtPage = "/mchtSettleOrder/toSetPayReadyDate";
        Map<String, Object> resMap = new HashMap<String, Object>();
        MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
        MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtSettleOrder.getMchtId());
        resMap.put("mchtSettleOrder", mchtSettleOrder);
        resMap.put("mchtInfo", mchtInfo);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 更新排款日期及状态
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/setPayReadyDate.shtml")
    @ResponseBody
    public Map<String, Object> setPayReadyDate(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            Integer mchtSettleOrderId = Integer.parseInt(request.getParameter("id"));
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(mchtSettleOrderId);
            mchtSettleOrder.setPayStatus("2");
            mchtSettleOrder.setPayReadyDate(DateUtil.getDateByFormat(request.getParameter("payReadyDate")));
            mchtSettleOrderService.updateByPrimaryKey(mchtSettleOrder);
            resMap.put("returnCode", "0000");
            resMap.put("returnMsg", "确认成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * POP结算单详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/view.shtml")
    public ModelAndView view(HttpServletRequest request, Page page) {
        String rtPage = "/mchtSettleOrder/view";// 重定向
        Map<String, Object> resMap = new HashMap<String, Object>();
        MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
        MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtSettleOrder.getMchtId());
        MchtSettleOrderCustom mchtSettleOrderCustom = mchtSettleOrderService.countCompleteSubOrder(mchtSettleOrder.getId());
        resMap.put("mchtSettleOrderCustom", mchtSettleOrderCustom);
        MchtBankAccountCustomExample example = new MchtBankAccountCustomExample();
        example.setOrderByClause("t.create_date desc");
        MchtBankAccountCustomExample.MchtBankAccountCustomCriteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo("0");
        criteria.andMchtIdEqualTo(mchtSettleOrder.getMchtId());
        criteria.andIsDefaultEqualTo("1");
        List<MchtBankAccountCustom> mchtBankAccountCustoms = mchtBankAccountService.selectMchtBankAccountCustomByExample(example);
        if (mchtBankAccountCustoms != null && mchtBankAccountCustoms.size() > 0) {
            resMap.put("mchtBankAccountCustom", mchtBankAccountCustoms.get(0));//商家开户行及账号
        }
        if (!StringUtils.isEmpty(mchtSettleOrder.getMchtCollectType())) {
            resMap.put("mchtCollectTypeDesc", DataDicUtil.getStatusDesc("BU_MCHT_SETTLE_ORDER", "MCHT_COLLECT_TYPE", mchtSettleOrder.getMchtCollectType()));
        }

        MchtContactExample mchtContactExample = new MchtContactExample();
        MchtContactExample.Criteria c = mchtContactExample.createCriteria();
        c.andDelFlagEqualTo("0");
        c.andMchtIdEqualTo(mchtSettleOrder.getMchtId());
        c.andContactTypeEqualTo("5");
        c.andIsPrimaryEqualTo("1");
        List<MchtContact> mchtContacts = mchtContactService.selectByExample(mchtContactExample);
        if (mchtContacts != null && mchtContacts.size() > 0) {
            resMap.put("mchtContact", mchtContacts.get(0));

        }
        MchtTaxInvoiceInfoExample mchtTaxInvoiceInfoExample = new MchtTaxInvoiceInfoExample();
        MchtTaxInvoiceInfoExample.Criteria mchtTaxInvoiceInfoCriteria = mchtTaxInvoiceInfoExample.createCriteria();
        mchtTaxInvoiceInfoCriteria.andDelFlagEqualTo("0");
        mchtTaxInvoiceInfoCriteria.andMchtIdEqualTo(mchtSettleOrder.getMchtId());
        List<MchtTaxInvoiceInfo> mchtTaxInvoiceInfos = mchtTaxInvoiceInfoService.selectByExample(mchtTaxInvoiceInfoExample);
        if (mchtTaxInvoiceInfos != null && mchtTaxInvoiceInfos.size() > 0) {
            resMap.put("mchtTaxInvoiceInfo", mchtTaxInvoiceInfos.get(0));//商家税票信息
        }
        resMap.put("confirmStatusDesc", DataDicUtil.getStatusDesc("BU_MCHT_SETTLE_ORDER", "CONFIRM_STATUS", mchtSettleOrder.getConfirmStatus()));
        resMap.put("platformInvoiceStatusDesc", DataDicUtil.getStatusDesc("BU_MCHT_SETTLE_ORDER", "PLATFORM_INVOICE_STATUS", mchtSettleOrder.getPlatformInvoiceStatus()));
        resMap.put("payStatusDesc", DataDicUtil.getStatusDesc("BU_MCHT_SETTLE_ORDER", "PAY_STATUS", mchtSettleOrder.getPayStatus()));
        if (mchtSettleOrder.getPayStaffId() != null) {
            SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(mchtSettleOrder.getPayStaffId());
            if (sysStaffInfo != null) {
                resMap.put("payStaffName", sysStaffInfo.getStaffName());
            }
        }
        resMap.put("mchtSettleOrder", mchtSettleOrder);
        resMap.put("mchtInfo", mchtInfo);
        if (mchtInfo.getContactProvince() != null) {
            Area areaProvince = areaService.selectByPrimaryKey(mchtInfo.getContactProvince());
            if (areaProvince != null) {
                resMap.put("province", areaProvince.getAreaName());
            }
        }
        if (mchtInfo.getContactCity() != null) {
            Area areaCity = areaService.selectByPrimaryKey(mchtInfo.getContactCity());
            resMap.put("city", areaCity.getAreaName());
        }
        if (mchtInfo.getContactCounty() != null) {
            Area areaCounty = areaService.selectByPrimaryKey(mchtInfo.getContactCounty());
            resMap.put("county", areaCounty.getAreaName());
        }
        resMap.put("BillingAmount", mchtSettleOrder.getOrderAmount().subtract(mchtSettleOrder.getSettleAmount()));
        if (mchtSettleOrderCustom.getProductSettleAmount() != null) {
            resMap.put("exceptionAmount", mchtSettleOrderCustom.getProductSettleAmount().subtract(mchtSettleOrder.getRefundAmount()));
        } else {
            resMap.put("exceptionAmount", new BigDecimal(0).subtract(mchtSettleOrder.getRefundAmount()));
        }
        resMap.put("needSettleAmount", mchtSettleOrder.getSettleAmount().subtract(mchtSettleOrder.getDepositAmount()));
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * POP历史付款记录列表数据
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/historyPayData.shtml")
    @ResponseBody
    public Map<String, Object> historyPayData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Integer totalCount = 0;
        try {
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
            PayToMchtLogCustomExample payToMchtLogCustomExample = new PayToMchtLogCustomExample();
            PayToMchtLogCustomExample.PayToMchtLogCustomCriteria payToMchtLogCustomCriteria = payToMchtLogCustomExample.createCriteria();
            payToMchtLogCustomCriteria.andDelFlagEqualTo("0");
            payToMchtLogCustomCriteria.andTypeEqualTo("1");
            payToMchtLogCustomCriteria.andMchtIdEqualTo(mchtSettleOrder.getMchtId());
            payToMchtLogCustomCriteria.andMchtSettleOrderIdEqualTo(mchtSettleOrder.getId());
            totalCount = payToMchtLogService.countByExample(payToMchtLogCustomExample);
            payToMchtLogCustomExample.setLimitStart(page.getLimitStart());
            payToMchtLogCustomExample.setLimitSize(page.getLimitSize());
            List<PayToMchtLogCustom> payToMchtLogCustoms = payToMchtLogService.selectByExample(payToMchtLogCustomExample);
            resMap.put("Rows", payToMchtLogCustoms);
            resMap.put("Total", totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }


    /**
     * 确认结算单页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/toConfirmStatus.shtml")
    public ModelAndView toConfirmStatus(HttpServletRequest request) {
        String rtPage = "/mchtSettleOrder/toConfirmStatus";
        Map<String, Object> resMap = new HashMap<String, Object>();
        MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
        MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtSettleOrder.getMchtId());
        MchtDepositExample mchtDepositExample = new MchtDepositExample();
        MchtDepositExample.Criteria mchtDepositCriteria = mchtDepositExample.createCriteria();
        mchtDepositCriteria.andDelFlagEqualTo("0");
        mchtDepositCriteria.andMchtIdEqualTo(mchtSettleOrder.getMchtId());
        List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(mchtDepositExample);
        MchtDeposit mchtDeposit = mchtDeposits.get(0);
        resMap.put("mchtInfo", mchtInfo);
        resMap.put("mchtDeposit", mchtDeposit);
        resMap.put("mchtSettleOrder", mchtSettleOrder);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 确认状态
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/confirmStatus.shtml")
    @ResponseBody
    public synchronized Map<String, Object> confirmStatus(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
            if (mchtSettleOrder.getConfirmStatus().equals("3")) {
                resMap.put("returnCode", "4004");
                resMap.put("returnMsg", "该结算单已确认过，无需再次确认");
                return resMap;
            }
            mchtSettleOrder.setConfirmStatus("3");
            mchtSettleOrder.setPlatformConfirmDate(new Date());
            BigDecimal depositAmount = new BigDecimal(request.getParameter("depositAmount"));
            if (depositAmount.compareTo(BigDecimal.ZERO) == 1) {//大于0
                mchtSettleOrder.setDepositAmount(mchtSettleOrder.getDepositAmount().add(depositAmount));
                mchtSettleOrder.setNeedPayAmount(mchtSettleOrder.getSettleAmount().subtract(depositAmount));
                //TODO 保存保证金明细
                MchtDepositDtl mchtDepositDtl = new MchtDepositDtl();
                mchtDepositDtl.setDelFlag("0");
                mchtDepositDtl.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                mchtDepositDtl.setCreateDate(new Date());
                MchtDepositExample mchtDepositExample = new MchtDepositExample();
                MchtDepositExample.Criteria mchtDepositCriteria = mchtDepositExample.createCriteria();
                mchtDepositCriteria.andDelFlagEqualTo("0");
                mchtDepositCriteria.andMchtIdEqualTo(mchtSettleOrder.getMchtId());
                List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(mchtDepositExample);
                MchtDeposit mchtDeposit = mchtDeposits.get(0);
                mchtDepositDtl.setDepositId(mchtDeposit.getId());
                mchtDepositDtl.setPayAmt(mchtDeposit.getPayAmt().add(depositAmount));
                mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().add(depositAmount));
                mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().subtract(depositAmount));
                mchtDepositDtl.setTxnType("C");
                mchtDepositDtl.setTypeSub("C1");
                mchtDepositDtl.setTxnAmt(depositAmount);
                mchtDepositDtl.setBizType("3");//结算单？
                mchtDepositDtl.setBizId(mchtSettleOrder.getId());
                mchtSettleOrderService.updateMchtSettleOrder(mchtSettleOrder, mchtDepositDtl, mchtDeposit);
            } else {
                mchtSettleOrderService.updateByPrimaryKeySelective(mchtSettleOrder);
            }
            resMap.put("returnCode", "0000");
            resMap.put("returnMsg", "确认成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 开票状态页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/toPlatformInvoiceStatus.shtml")
    public ModelAndView toPlatformInvoiceStatus(HttpServletRequest request) {
        String rtPage = "/mchtSettleOrder/toPlatformInvoiceStatus";
        Map<String, Object> resMap = new HashMap<String, Object>();
        MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
        ExpressExample example = new ExpressExample();
        ExpressExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo("0");
        List<Express> expressList = expressService.selectByExample(example);
        resMap.put("mchtSettleOrder", mchtSettleOrder);
        resMap.put("expressList", expressList);
        resMap.put("platformInvoiceStatusList", DataDicUtil.getStatusList("BU_MCHT_SETTLE_ORDER", "PLATFORM_INVOICE_STATUS"));
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 开票状态
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/platformInvoiceStatus.shtml")
    @ResponseBody
    public Map<String, Object> platformInvoiceStatus(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
            String platformInvoiceStatus = request.getParameter("platformInvoiceStatus");
            String platformInvoiceExpressNo = request.getParameter("platformInvoiceExpressNo");
            String expressId = request.getParameter("expressId");
            mchtSettleOrder.setPlatformInvoiceStatus(platformInvoiceStatus);
            mchtSettleOrder.setPlatformInvoiceExpressNo(platformInvoiceExpressNo);
            mchtSettleOrder.setPlatformInvoiceExpressId(Integer.parseInt(expressId));
            if (platformInvoiceStatus.equals("2")) {//已开
                mchtSettleOrder.setPlatformInvoiceDate(new Date());
            }
            mchtSettleOrderService.updateByPrimaryKeySelective(mchtSettleOrder);
            resMap.put("returnCode", "0000");
            resMap.put("returnMsg", "确认成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 结算单付款页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/toPay.shtml")
    public ModelAndView toPay(HttpServletRequest request) {
        String rtPage = "/mchtSettleOrder/toPay";
        Map<String, Object> resMap = new HashMap<String, Object>();
        MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
        MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtSettleOrder.getMchtId());
        resMap.put("mchtSettleOrder", mchtSettleOrder);
        resMap.put("needSettleAmount", mchtSettleOrder.getSettleAmount().subtract(mchtSettleOrder.getDepositAmount()));
        resMap.put("mchtInfo", mchtInfo);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 结算单付款
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/savePayToMchtLog.shtml")
    @ResponseBody
    public Map<String, Object> savePayToMchtLog(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            Integer mchtSettleOrderId = Integer.parseInt(request.getParameter("id"));
            BigDecimal payAmount = new BigDecimal(request.getParameter("payAmount"));
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(mchtSettleOrderId);
            PayToMchtLog payToMchtLog = new PayToMchtLog();
            payToMchtLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            payToMchtLog.setCreateDate(new Date());
            payToMchtLog.setDelFlag("0");
            payToMchtLog.setType("1");
            payToMchtLog.setMchtSettleOrderId(mchtSettleOrderId);
            payToMchtLog.setMchtId(mchtSettleOrder.getMchtId());
            payToMchtLog.setPayCode(request.getParameter("payCode"));
            payToMchtLog.setPayDate(DateUtil.getDateByFormat(request.getParameter("payDate")));
            payToMchtLog.setPayStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            payToMchtLog.setSettleAmount(new BigDecimal(request.getParameter("settleAmount")));
            payToMchtLog.setDepositAmount(new BigDecimal(request.getParameter("depositAmount")));
            payToMchtLog.setPayAmount(payAmount);
            payToMchtLog.setNeedPayAmount(mchtSettleOrder.getNeedPayAmount());
            payToMchtLog.setBeforePayAmount(mchtSettleOrder.getPayAmount());
            payToMchtLog.setConfirmStatus("0");
//			mchtSettleOrder.setPayStatus("3");
            if (mchtSettleOrder.getNeedPayAmount().compareTo(payAmount) == 0) {
                mchtSettleOrder.setPayStatus("3");//已付款
            } else {
                mchtSettleOrder.setPayStatus("2");//已排款
            }
            mchtSettleOrder.setPayDate(DateUtil.getDateByFormat(request.getParameter("payDate")));
            mchtSettleOrder.setPayCode(request.getParameter("payCode"));
            mchtSettleOrder.setPayStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            mchtSettleOrder.setPayAmount(payAmount.add(mchtSettleOrder.getPayAmount()));
            mchtSettleOrder.setNeedPayAmount(mchtSettleOrder.getNeedPayAmount().subtract(payAmount));
            mchtSettleOrder.setUpdateDate(new Date());
            mchtSettleOrder.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
            mchtSettleOrderService.updateMchtSettleOrder(mchtSettleOrder, payToMchtLog);
            resMap.put("returnCode", "0000");
            resMap.put("returnMsg", "确认成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 导出直赔单明细
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mchtSettleOrder/exportPoolCustomerServiceOrder.shtml")
    public void exportPoolCustomerServiceOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("mchtSettleOrderId")));
            MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtSettleOrder.getMchtId());
            CustomerServiceOrderCustomExample example = new CustomerServiceOrderCustomExample();
            CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria criteria = example.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andServiceTypeEqualTo("D");
            criteria.andMchtSettleOrderIdEqualTo(mchtSettleOrder.getId());
            List<CustomerServiceOrderCustom> customerServiceOrderCustoms = customerServiceOrderService.selectCustomerServiceOrderCustomByExample(example);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String[] titles = {"售后类型", "订单编号（子）", "售后单号", "创建时间", "直赔单金额（元）", "商家序号", "商家名称", "SPOP结算单ID"};
            ExcelBean excelBean = new ExcelBean("结算单明细-POP__" + mchtInfo.getMchtCode() + "_" + dateFormat.format(mchtSettleOrder.getBeginDate()) + "到" + dateFormat.format(mchtSettleOrder.getEndDate()) + ".xls",
                    "下载直赔单明细", titles);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<String[]> datas = new ArrayList<String[]>();
            for (CustomerServiceOrderCustom customerServiceOrderCustom : customerServiceOrderCustoms) {
                String[] data = {
                        customerServiceOrderCustom.getServiceTypeDesc(),
                        customerServiceOrderCustom.getSubOrderCode(),
                        customerServiceOrderCustom.getOrderCode(),
                        df.format(customerServiceOrderCustom.getCreateDate()),
                        String.valueOf(customerServiceOrderCustom.getAmount()),
                        mchtInfo.getMchtCode(),
                        mchtInfo.getShortName(),
                        mchtSettleOrder.getId().toString()
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出POP订单明细
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mchtSettleOrder/exportOrderDtl.shtml")
    public void exportOrderDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("mchtSettleOrderId")));
            MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtSettleOrder.getMchtId());
            OrderDtlCustomExample example = new OrderDtlCustomExample();
            OrderDtlCustomExample.OrderDtlCustomCriteria criteria = example.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andMchtSettleOrderIdEqualTo(mchtSettleOrder.getId());
            List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(example);
            //获取退款，退货状态的订单明细
            OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
            OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria = orderDtlCustomExample.createCriteria();
            orderDtlCustomCriteria.andDelFlagEqualTo("0");
            orderDtlCustomCriteria.andRefundDateIsNotNull();
            orderDtlCustomCriteria.andRefundDateGreaterThanOrEqualTo(df.parse(dateFormat.format(mchtSettleOrder.getBeginDate()) + " 00:00:00"));
            orderDtlCustomCriteria.andRefundDateLessThanOrEqualTo(df.parse(dateFormat.format(mchtSettleOrder.getEndDate()) + " 00:00:00"));
            orderDtlCustomCriteria.andMchtIdEqualTo(mchtSettleOrder.getMchtId());
            List<OrderDtlCustom> orderDtlCustomList = orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
            if (orderDtlCustomList != null && orderDtlCustomList.size() > 0) {
                orderDtlCustoms.addAll(orderDtlCustomList);
            }
            String[] titles = {"订单编号(子)", "订单的商品明细ID", "客户下单时间", "客户付款时间", "商家发货时间", "退款时间", "完成时间", "状态", "商品名称", "品牌", "货号", "规格", "SKU商家编码", "醒购价", "数量", "销售商品金额", "商家优惠", "运费", "平台优惠", "积分优惠", "订单客户实付金额", "类型", "商家序号", "商家名称", "技术服务系数", "技术服务费", "结算金额", "结算单ID"};
            ExcelBean excelBean = new ExcelBean("结算单明细-POP__" + mchtInfo.getMchtCode() + "_" + dateFormat.format(mchtSettleOrder.getBeginDate()) + "到" + dateFormat.format(mchtSettleOrder.getEndDate()) + ".xls",
                    "下载订单明细", titles);
            List<String[]> datas = new ArrayList<String[]>();
            for (OrderDtlCustom orderDtlCustom : orderDtlCustoms) {
                String[] data = {
                        orderDtlCustom.getSubOrderCode(),
                        String.valueOf(orderDtlCustom.getId()),
                        df.format(orderDtlCustom.getSubOrderCreateDate()),
                        orderDtlCustom.getPayDate() == null ? "" : df.format(orderDtlCustom.getPayDate()),
                        orderDtlCustom.getDeliveryDate() == null ? "" : df.format(orderDtlCustom.getDeliveryDate()),
                        orderDtlCustom.getRefundDate() == null ? "" : df.format(orderDtlCustom.getRefundDate()),
                        orderDtlCustom.getCompleteDate() == null ? "" : df.format(orderDtlCustom.getCompleteDate()),
                        orderDtlCustom.getProductStatusDesc(),
                        orderDtlCustom.getProductName(),
                        orderDtlCustom.getBrandName(),
                        orderDtlCustom.getArtNo(),
                        orderDtlCustom.getProductPropDesc(),
                        orderDtlCustom.getSku(),
                        orderDtlCustom.getSalePrice() == null ? "" : String.valueOf(orderDtlCustom.getSalePrice()),
                        orderDtlCustom.getQuantity() == null ? "" : String.valueOf(orderDtlCustom.getQuantity()),
                        orderDtlCustom.getSalePrice() == null ? "" : String.valueOf(orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity()))),
                        orderDtlCustom.getMchtPreferential() == null ? "" : String.valueOf(orderDtlCustom.getMchtPreferential()),
                        orderDtlCustom.getFreight() == null ? "" : orderDtlCustom.getFreight().toString(),
                        orderDtlCustom.getPlatformPreferential() == null ? "" : String.valueOf(orderDtlCustom.getPlatformPreferential()),
                        orderDtlCustom.getIntegralPreferential() == null ? "" : String.valueOf(orderDtlCustom.getIntegralPreferential()),
                        orderDtlCustom.getPayAmount() == null ? "" : String.valueOf(orderDtlCustom.getPayAmount()),
                        "POP",
                        mchtInfo.getMchtCode(),
                        mchtInfo.getShortName(),
                        orderDtlCustom.getPopCommissionRate() == null ? "" : String.valueOf(orderDtlCustom.getPopCommissionRate()),
                        orderDtlCustom.getCommissionAmount() == null ? "" : String.valueOf(orderDtlCustom.getCommissionAmount()),
                        orderDtlCustom.getSettleAmount() == null ? "" : String.valueOf(orderDtlCustom.getSettleAmount()),
                        mchtSettleOrder.getId() == null ? "" : String.valueOf(mchtSettleOrder.getId()),
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出预付定金明细
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mchtSettleOrder/exportSubDepositOrder.shtml")
    public void exportSubDepositOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("mchtSettleOrderId")));
            MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtSettleOrder.getMchtId());
            SubDepositOrderCustomExample e = new SubDepositOrderCustomExample();
            SubDepositOrderCustomExample.SubDepositOrderCustomCriteria c = e.createCriteria();
            c.andDelFlagEqualTo("0");
            c.andMchtSettleOrderIdEqualTo(mchtSettleOrder.getId());
            List<SubDepositOrderCustom> subDepositOrderCustoms = subDepositOrderService.selectSubDepositOrderCustomByExample(e);
            String[] titles = {"定金订单编号(子)", "品牌", "商品名称", "数量", "实际付款", "定金状态", "下单时间", "付款方式", "佣金比例", "技术服务费"};
            ExcelBean excelBean = new ExcelBean("预付定金明细-POP__" + mchtInfo.getMchtCode() + "_" + dateFormat.format(mchtSettleOrder.getBeginDate()) + "到" + dateFormat.format(mchtSettleOrder.getEndDate()) + ".xls",
                    "下载预付定金明细", titles);
            List<String[]> datas = new ArrayList<String[]>();
            for (SubDepositOrderCustom subDepositOrderCustom : subDepositOrderCustoms) {
                String paymentDesc = "";
                if (subDepositOrderCustom.getPaymentId().equals(1) || subDepositOrderCustom.getPaymentId().equals(6)) {
                    paymentDesc = "支付宝";
                } else if (subDepositOrderCustom.getPaymentId().equals(2) || subDepositOrderCustom.getPaymentId().equals(4) || subDepositOrderCustom.getPaymentId().equals(5)) {
                    paymentDesc = "微信";
                } else if (subDepositOrderCustom.getPaymentId().equals(3)) {
                    paymentDesc = "银联";
                }
                String[] data = {
                        subDepositOrderCustom.getSubDepositOrderCode(),
                        subDepositOrderCustom.getBrandName() == null ? "" : subDepositOrderCustom.getBrandName(),
                        subDepositOrderCustom.getProductName(),
                        subDepositOrderCustom.getQuantity().toString(),
                        subDepositOrderCustom.getPayAmount().toString(),
                        subDepositOrderCustom.getStatusDesc(),
                        df.format(subDepositOrderCustom.getCreateDate()),
                        paymentDesc,
                        subDepositOrderCustom.getPopCommissionRate().toString(),
                        subDepositOrderCustom.getCommissionAmount().toString()
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * SPOP结算列表
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/poolList.shtml")
    public ModelAndView poolList(HttpServletRequest request) {
        String rtPage = "/mchtSettleOrder/poolList";
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("defaultConfirmStatus", "2");
        resMap.put("mchtCode", request.getParameter("request"));
        resMap.put("confirmStatusList", DataDicUtil.getStatusList("BU_MCHT_SETTLE_ORDER", "CONFIRM_STATUS"));
        resMap.put("platformCollectStatusList", DataDicUtil.getStatusList("BU_MCHT_SETTLE_ORDER", "PLATFORM_COLLECT_STATUS"));
        resMap.put("payStatusList", DataDicUtil.getStatusList("BU_MCHT_SETTLE_ORDER", "PAY_STATUS"));
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * SPOP结算列表数据
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/poolData.shtml")
    @ResponseBody
    public Map<String, Object> poolData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Integer totalCount = 0;
        try {
            MchtSettleOrderCustomExample example = new MchtSettleOrderCustomExample();
            MchtSettleOrderCustomExample.MchtSettleOrderCustomCriteria criteria = example.createCriteria();
            if (!StringUtil.isEmpty(request.getParameter("delFlag"))) {
                criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
            } else {
                criteria.andDelFlagEqualTo("0");
            }
            criteria.andMchtTypeEqualTo("1");//SPOP
            String searchMchtCode = request.getParameter("mchtCode");
            if (!StringUtil.isEmpty(searchMchtCode)) {
                criteria.andMchtCodeEqualTo(searchMchtCode);
            }
            String searchConfirmStatus = request.getParameter("confirmStatus");
            if (!StringUtil.isEmpty(searchConfirmStatus)) {
                criteria.andConfirmStatusEqualTo(searchConfirmStatus);
            }
            String searchPlatformCollectStatus = request.getParameter("platformCollectStatus");
            if (!StringUtil.isEmpty(searchPlatformCollectStatus)) {
                criteria.andPlatformCollectStatusEqualTo(searchPlatformCollectStatus);
            }
            String searchPayStatus = request.getParameter("payStatus");
            if (!StringUtil.isEmpty(searchPayStatus)) {
                criteria.andPayStatusEqualTo(searchPayStatus);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(request.getParameter("mcht_confirm_date_begin"))) {
                criteria.andMchtConfirmDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("mcht_confirm_date_begin") + " 00:00:00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("mcht_confirm_date_end"))) {
                criteria.andMchtConfirmDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("mcht_confirm_date_end") + " 23:59:59"));
            }
            String settleAmount = request.getParameter("settleAmount");
            if (!StringUtil.isEmpty(settleAmount)) {
                if (settleAmount.equals("0")) {
                    criteria.andSettleAmountEqualTo(new BigDecimal(0));
                } else {
                    criteria.andSettleAmountGreaterThan(new BigDecimal(0));
                }
            }
            totalCount = mchtSettleOrderService.countByExample(example);
            example.setLimitStart(page.getLimitStart());
            example.setLimitSize(page.getLimitSize());
            example.setOrderByClause("t.id desc");
            List<MchtSettleOrderCustom> mchtSettleOrderCustoms = mchtSettleOrderService.selectByExample(example);
            resMap.put("Rows", mchtSettleOrderCustoms);
            resMap.put("Total", totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 导出SPOPexcel
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mchtSettleOrder/exportPoolMchtSettleOrder.shtml")
    public void exportPoolMchtSettleOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            MchtSettleOrderCustomExample example = new MchtSettleOrderCustomExample();
            MchtSettleOrderCustomExample.MchtSettleOrderCustomCriteria criteria = example.createCriteria();
            if (!StringUtil.isEmpty(request.getParameter("delFlag"))) {
                criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
            } else {
                criteria.andDelFlagEqualTo("0");
            }
            criteria.andMchtTypeEqualTo("1");//SPOP
            String searchMchtCode = request.getParameter("mchtCode");
            if (!StringUtil.isEmpty(searchMchtCode)) {
                criteria.andMchtCodeEqualTo(searchMchtCode);
            }
            String searchConfirmStatus = request.getParameter("confirmStatus");
            if (!StringUtil.isEmpty(searchConfirmStatus)) {
                criteria.andConfirmStatusEqualTo(searchConfirmStatus);
            }
            String searchPlatformCollectStatus = request.getParameter("platformCollectStatus");
            if (!StringUtil.isEmpty(searchPlatformCollectStatus)) {
                criteria.andPlatformCollectStatusEqualTo(searchPlatformCollectStatus);
            }
            String searchPayStatus = request.getParameter("payStatus");
            if (!StringUtil.isEmpty(searchPayStatus)) {
                criteria.andPayStatusEqualTo(searchPayStatus);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(request.getParameter("mcht_confirm_date_begin"))) {
                criteria.andMchtConfirmDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("mcht_confirm_date_begin") + " 00:00:00"));
            }
            if (!StringUtil.isEmpty(request.getParameter("mcht_confirm_date_end"))) {
                criteria.andMchtConfirmDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("mcht_confirm_date_end") + " 23:59:59"));
            }
            List<MchtSettleOrderCustom> mchtSettleOrderCustoms = mchtSettleOrderService.selectByExample(example);
            String[] titles = {"结算单日期", "商家序号", "商家简称", "结算单金额（元）", "抵缴保证金（元）", "还需支付（元）", "实付金额（元）", "确认状态", "商家确认日期", "收票状态", "排款日期", "付款状态", "付款日期"};
            ExcelBean excelBean = new ExcelBean("导出SPOP结算列表.xls",
                    "导出SPOP结算列表", titles);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            List<String[]> datas = new ArrayList<String[]>();
            for (MchtSettleOrderCustom mchtSettleOrderCustom : mchtSettleOrderCustoms) {
                String[] data = {
                        df.format(mchtSettleOrderCustom.getBeginDate()) + "到" + df.format(mchtSettleOrderCustom.getEndDate()),
                        mchtSettleOrderCustom.getMchtCode(),
                        mchtSettleOrderCustom.getShortName(),
                        mchtSettleOrderCustom.getSettleAmount() == null ? "" : String.valueOf(mchtSettleOrderCustom.getSettleAmount()),
                        mchtSettleOrderCustom.getDepositAmount() == null ? "" : String.valueOf(mchtSettleOrderCustom.getDepositAmount()),
                        mchtSettleOrderCustom.getNeedPayAmount() == null ? "" : String.valueOf(mchtSettleOrderCustom.getNeedPayAmount()),
                        mchtSettleOrderCustom.getPayAmount() == null ? "" : String.valueOf(mchtSettleOrderCustom.getPayAmount()),
                        mchtSettleOrderCustom.getConfirmStatusDesc(),
                        mchtSettleOrderCustom.getMchtConfirmDate() == null ? "" : df.format(mchtSettleOrderCustom.getMchtConfirmDate()),
                        mchtSettleOrderCustom.getPlatformCollectStatusDesc(),
                        mchtSettleOrderCustom.getPayReadyDate() == null ? "" : df.format(mchtSettleOrderCustom.getPayReadyDate()),
                        mchtSettleOrderCustom.getPayStatusDesc(),
                        mchtSettleOrderCustom.getPayDate() == null ? "" : df.format(mchtSettleOrderCustom.getPayDate())
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * SPOP结算单详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/poolView.shtml")
    public ModelAndView poolView(HttpServletRequest request, Page page) {
        String rtPage = "/mchtSettleOrder/poolView";// 重定向
        Map<String, Object> resMap = new HashMap<String, Object>();
        MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
        MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtSettleOrder.getMchtId());
        MchtSettleOrderCustom mchtSettleOrderCustom = mchtSettleOrderService.countCompleteSubOrder(mchtSettleOrder.getId());
        resMap.put("mchtSettleOrderCustom", mchtSettleOrderCustom);
        MchtBankAccountExample example = new MchtBankAccountExample();
        MchtBankAccountExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo("0");
        criteria.andMchtIdEqualTo(mchtSettleOrder.getMchtId());
        criteria.andIsDefaultEqualTo("1");
        List<MchtBankAccount> mchtBankAccounts = mchtBankAccountService.selectByExample(example);
        if (mchtBankAccounts != null && mchtBankAccounts.size() > 0) {
            resMap.put("mchtBankAccount", mchtBankAccounts.get(0));//商家开户行及账号
        }
        SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
        SysParamCfgExample.Criteria sysParamCfgCriteria = sysParamCfgExample.createCriteria();
        sysParamCfgCriteria.andParamCodeLike("%PTKP_%");
        List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
        for (SysParamCfg sysParamCfg : sysParamCfgs) {
            resMap.put(sysParamCfg.getParamCode(), sysParamCfg.getParamValue());
        }
        if (mchtSettleOrder.getMchtInvoiceExpressId() != null) {
            Express express = expressService.selectByPrimaryKey(mchtSettleOrder.getMchtInvoiceExpressId());
            if (express != null) {
                resMap.put("expressName", express.getName());
            }
        }
        resMap.put("confirmStatusDesc", DataDicUtil.getStatusDesc("BU_MCHT_SETTLE_ORDER", "CONFIRM_STATUS", mchtSettleOrder.getConfirmStatus()));
        resMap.put("mchtInvoiceStatusDesc", DataDicUtil.getStatusDesc("BU_MCHT_SETTLE_ORDER", "MCHT_INVOICE_STATUS", mchtSettleOrder.getMchtInvoiceStatus()));
        resMap.put("platformCollectStatusDesc", DataDicUtil.getStatusDesc("BU_MCHT_SETTLE_ORDER", "PLATFORM_COLLECT_STATUS", mchtSettleOrder.getPlatformCollectStatus()));
        resMap.put("payStatusDesc", DataDicUtil.getStatusDesc("BU_MCHT_SETTLE_ORDER", "PAY_STATUS", mchtSettleOrder.getPayStatus()));
        if (mchtSettleOrder.getPayStaffId() != null) {
            SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(mchtSettleOrder.getPayStaffId());
            if (sysStaffInfo != null) {
                resMap.put("payStaffName", sysStaffInfo.getStaffName());
            }
        }
        resMap.put("mchtSettleOrder", mchtSettleOrder);
        resMap.put("mchtInfo", mchtInfo);

        return new ModelAndView(rtPage, resMap);
    }

    /**
     * SPOP历史付款记录列表数据
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/poolHistoryPayData.shtml")
    @ResponseBody
    public Map<String, Object> poolHistoryPayData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Integer totalCount = 0;
        try {
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
            PayToMchtLogCustomExample payToMchtLogCustomExample = new PayToMchtLogCustomExample();
            PayToMchtLogCustomExample.PayToMchtLogCustomCriteria payToMchtLogCustomCriteria = payToMchtLogCustomExample.createCriteria();
            payToMchtLogCustomCriteria.andDelFlagEqualTo("0");
            payToMchtLogCustomCriteria.andTypeNotEqualTo("1");
            payToMchtLogCustomCriteria.andMchtIdEqualTo(mchtSettleOrder.getMchtId());
            payToMchtLogCustomCriteria.andMchtSettleOrderIdEqualTo(mchtSettleOrder.getId());
            totalCount = payToMchtLogService.countByExample(payToMchtLogCustomExample);
            payToMchtLogCustomExample.setLimitStart(page.getLimitStart());
            payToMchtLogCustomExample.setLimitSize(page.getLimitSize());
            List<PayToMchtLogCustom> payToMchtLogCustoms = payToMchtLogService.selectByExample(payToMchtLogCustomExample);
            resMap.put("Rows", payToMchtLogCustoms);
            resMap.put("Total", totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 导出SPOP订单明细
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mchtSettleOrder/exportPoolOrderDtl.shtml")
    public void exportPoolOrderDtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("mchtSettleOrderId")));
            MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtSettleOrder.getMchtId());
            OrderDtlCustomExample example = new OrderDtlCustomExample();
            OrderDtlCustomExample.OrderDtlCustomCriteria criteria = example.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andMchtSettleOrderIdEqualTo(mchtSettleOrder.getId());
            List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(example);
            //获取退款，退货状态的订单明细
            OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
            OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria = orderDtlCustomExample.createCriteria();
            orderDtlCustomCriteria.andDelFlagEqualTo("0");
            orderDtlCustomCriteria.andRefundDateIsNotNull();
            orderDtlCustomCriteria.andRefundDateGreaterThanOrEqualTo(df.parse(dateFormat.format(mchtSettleOrder.getBeginDate()) + " 00:00:00"));
            orderDtlCustomCriteria.andRefundDateLessThanOrEqualTo(df.parse(dateFormat.format(mchtSettleOrder.getEndDate()) + " 00:00:00"));
            orderDtlCustomCriteria.andMchtIdEqualTo(mchtSettleOrder.getMchtId());
            List<OrderDtlCustom> orderDtlCustomList = orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
            if (orderDtlCustomList != null && orderDtlCustomList.size() > 0) {
                orderDtlCustoms.addAll(orderDtlCustomList);
            }
            String[] titles = {"订单编号(子)", "订单的商品明细ID", "客户下单时间", "客户付款时间", "商家发货时间", "退款时间", "完成时间", "商品状态", "商品名称", "品牌", "货号", "规格", "SKU商家编码", "吊牌价", "醒购价", "结算价", "数量", "销售商品金额", "销售商品商家优惠", "销售商品平台优惠", "销售商品积分优惠", "订单客户实付金额", "货款金额", "商家优惠", "供应商序号", "商家名称"};
            ExcelBean excelBean = new ExcelBean("结算单明细-POP__" + mchtInfo.getMchtCode() + "_" + dateFormat.format(mchtSettleOrder.getBeginDate()) + "到" + dateFormat.format(mchtSettleOrder.getEndDate()) + ".xls",
                    "下载订单明细", titles);
            List<String[]> datas = new ArrayList<String[]>();
            for (OrderDtlCustom orderDtlCustom : orderDtlCustoms) {
                String[] data = {
                        orderDtlCustom.getSubOrderCode(),
                        String.valueOf(orderDtlCustom.getId()),
                        df.format(orderDtlCustom.getSubOrderCreateDate()),
                        orderDtlCustom.getPayDate() == null ? "" : df.format(orderDtlCustom.getPayDate()),
                        orderDtlCustom.getDeliveryDate() == null ? "" : df.format(orderDtlCustom.getDeliveryDate()),
                        orderDtlCustom.getRefundDate() == null ? "" : df.format(orderDtlCustom.getRefundDate()),
                        orderDtlCustom.getCompleteDate() == null ? "" : df.format(orderDtlCustom.getCompleteDate()),
                        orderDtlCustom.getProductStatusDesc(),
                        orderDtlCustom.getProductName(),
                        orderDtlCustom.getBrandName(),
                        orderDtlCustom.getArtNo(),
                        orderDtlCustom.getProductPropDesc(),
                        orderDtlCustom.getSku(),
                        orderDtlCustom.getTagPrice() == null ? "" : String.valueOf(orderDtlCustom.getTagPrice()),
                        orderDtlCustom.getSalePrice() == null ? "" : String.valueOf(orderDtlCustom.getSalePrice()),
                        orderDtlCustom.getSettlePrice() == null ? "" : String.valueOf(orderDtlCustom.getSettlePrice()),
                        orderDtlCustom.getQuantity() == null ? "" : String.valueOf(orderDtlCustom.getQuantity()),
                        orderDtlCustom.getSalePrice() == null ? "" : String.valueOf(orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity()))),
                        orderDtlCustom.getMchtPreferential() == null ? "" : String.valueOf(orderDtlCustom.getMchtPreferential()),
                        orderDtlCustom.getPlatformPreferential() == null ? "" : String.valueOf(orderDtlCustom.getPlatformPreferential()),
                        orderDtlCustom.getIntegralPreferential() == null ? "" : String.valueOf(orderDtlCustom.getIntegralPreferential()),
                        orderDtlCustom.getPayAmount() == null ? "" : String.valueOf(orderDtlCustom.getPayAmount()),
//				mchtSettleOrder.getSettlePriceTotal(),
                        "",
                        orderDtlCustom.getMchtPreferential() == null ? "" : String.valueOf(orderDtlCustom.getMchtPreferential()),
                        mchtInfo.getMchtCode(),
                        mchtInfo.getShortName(),
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出直赔单明细
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mchtSettleOrder/exportCustomerServiceOrder.shtml")
    public void exportCustomerServiceOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("mchtSettleOrderId")));
            MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtSettleOrder.getMchtId());
            CustomerServiceOrderCustomExample example = new CustomerServiceOrderCustomExample();
            CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria criteria = example.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andServiceTypeEqualTo("D");
            criteria.andMchtSettleOrderIdEqualTo(mchtSettleOrder.getId());
            List<CustomerServiceOrderCustom> customerServiceOrderCustoms = customerServiceOrderService.selectCustomerServiceOrderCustomByExample(example);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String[] titles = {"售后类型", "订单编号（子）", "售后单号", "退款时间", "实退金额（元）", "类型", "商家序号", "商家名称", "结算单ID"};
            ExcelBean excelBean = new ExcelBean("结算单明细-POP__" + mchtInfo.getMchtCode() + "_" + dateFormat.format(mchtSettleOrder.getBeginDate()) + "到" + dateFormat.format(mchtSettleOrder.getEndDate()) + ".xls",
                    "下载直赔单明细", titles);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<String[]> datas = new ArrayList<String[]>();
            for (CustomerServiceOrderCustom customerServiceOrderCustom : customerServiceOrderCustoms) {
                String[] data = {
                        customerServiceOrderCustom.getServiceTypeDesc(),
                        customerServiceOrderCustom.getSubOrderCode(),
                        customerServiceOrderCustom.getOrderCode(),
                        df.format(customerServiceOrderCustom.getCreateDate()),
                        String.valueOf(customerServiceOrderCustom.getAmount()),
                        "POP",
                        mchtInfo.getMchtCode(),
                        mchtInfo.getShortName(),
                        mchtSettleOrder.getId().toString()
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 收票状态页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/toPlatformCollectStatus.shtml")
    public ModelAndView toPlatformCollectStatus(HttpServletRequest request) {
        String rtPage = "/mchtSettleOrder/toPlatformCollectStatus";
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("mchtSettleOrderId", Integer.parseInt(request.getParameter("id")));
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 收票状态
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/updatePlatformCollectStatus.shtml")
    @ResponseBody
    public Map<String, Object> updatePlatformCollectStatus(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
            mchtSettleOrder.setPlatformCollectStatus(request.getParameter("platformCollectStatus"));
            mchtSettleOrderService.updateByPrimaryKey(mchtSettleOrder);
            resMap.put("returnCode", "0000");
            resMap.put("returnMsg", "确认成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 批量确认页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/toBatchConfirm.shtml")
    public ModelAndView toBatchConfirm(HttpServletRequest request) {
        String rtPage = "/mchtSettleOrder/toBatchConfirm";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String ids = request.getParameter("ids");
        resMap.put("ids", ids);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 批量确认
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/batchConfirm.shtml")
    @ResponseBody
    public Map<String, Object> batchConfirm(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            String ids = request.getParameter("ids");
            List<Integer> idsList = new ArrayList<Integer>();
            if (!StringUtils.isEmpty(ids)) {
                String[] idsArray = ids.split(",");
                for (int i = 0; i < idsArray.length; i++) {
                    idsList.add(Integer.parseInt(idsArray[i]));
                }
            }
            if (idsList != null && idsList.size() > 0) {
                mchtSettleOrderService.batchConfirmConfirmStatus(idsList);
            }
            resMap.put("returnCode", "0000");
            resMap.put("returnMsg", "确认成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 批量排款页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/toBatchPk.shtml")
    public ModelAndView toBatchPk(HttpServletRequest request) {
        String rtPage = "/mchtSettleOrder/toBatchPk";
        Map<String, Object> resMap = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        resMap.put("payReadyDate", sdf.format(new Date()));
        String ids = request.getParameter("ids");
        resMap.put("ids", ids);
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 批量排款
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/batchPk.shtml")
    @ResponseBody
    public Map<String, Object> batchPk(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            String pay_ready_date = request.getParameter("pay_ready_date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date payReadyDate = sdf.parse(pay_ready_date);
            String ids = request.getParameter("ids");
            List<Integer> idsList = new ArrayList<Integer>();
            if (!StringUtils.isEmpty(ids)) {
                String[] idsArray = ids.split(",");
                for (int i = 0; i < idsArray.length; i++) {
                    idsList.add(Integer.parseInt(idsArray[i]));
                }
            }
            if (idsList != null && idsList.size() > 0) {
                HashMap<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("payReadyDate", payReadyDate);
                paramMap.put("idsList", idsList);
                mchtSettleOrderService.batchPayStatus(paramMap);
            }
            resMap.put("returnCode", "0000");
            resMap.put("returnMsg", "确认成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 批量付款页面
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/toBatchPay.shtml")
    public ModelAndView toBatchPay(HttpServletRequest request) {
        String rtPage = "/mchtSettleOrder/toBatchPay";
        Map<String, Object> resMap = new HashMap<String, Object>();
        String ids = request.getParameter("ids");
        resMap.put("ids", ids);
        String[] idsArray = ids.split(",");
        List<Integer> idsList = new ArrayList<Integer>();
        resMap.put("totalCount", idsArray.length);
        for (int i = 0; i < idsArray.length; i++) {
            if (!idsList.contains(Integer.parseInt(idsArray[i]))) {
                idsList.add(Integer.parseInt(idsArray[i]));
            }
        }
        if (idsList != null && idsList.size() > 0) {
            BigDecimal totalNeedPayAmount = mchtSettleOrderService.getNeedPayAmountByIds(idsList);
            resMap.put("totalNeedPayAmount", totalNeedPayAmount);
        }
        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 批量付款
     *
     * @param request
     * @param response
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/mchtSettleOrder/batchPay.shtml")
    @ResponseBody
    public Map<String, Object> batchPay(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            String ids = request.getParameter("ids");
            String payCode = request.getParameter("payCode");
            List<MchtSettleOrder> mchtSettleOrderList = new ArrayList<MchtSettleOrder>();
            List<PayToMchtLog> payToMchtLogList = new ArrayList<PayToMchtLog>();
            if (!StringUtils.isEmpty(ids)) {
                String[] idsArray = ids.split(",");
                for (int i = 0; i < idsArray.length; i++) {
                    MchtSettleOrder mchtSettleOrder = mchtSettleOrderService.selectByPrimaryKey(Integer.parseInt(idsArray[i]));
                    if (mchtSettleOrder.getPayStatus().equals("3") || mchtSettleOrder.getPayStatus().equals("4")) {
                        continue;
                    }
                    mchtSettleOrder.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                    mchtSettleOrder.setUpdateDate(new Date());
                    mchtSettleOrder.setPayStatus("3");
                    if (mchtSettleOrder.getPayAmount() != null) {
                        mchtSettleOrder.setPayAmount(mchtSettleOrder.getNeedPayAmount().add(mchtSettleOrder.getPayAmount()));
                    } else {
                        mchtSettleOrder.setPayAmount(mchtSettleOrder.getNeedPayAmount());
                    }
                    mchtSettleOrder.setPayStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                    mchtSettleOrder.setPayDate(new Date());
                    mchtSettleOrder.setPayCode(payCode);

                    PayToMchtLog payToMchtLog = new PayToMchtLog();
                    payToMchtLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                    payToMchtLog.setCreateDate(new Date());
                    payToMchtLog.setDelFlag("0");
                    payToMchtLog.setType("1");
                    payToMchtLog.setMchtSettleOrderId(mchtSettleOrder.getId());
                    payToMchtLog.setMchtId(mchtSettleOrder.getMchtId());
                    payToMchtLog.setPayCode(payCode);
                    payToMchtLog.setPayDate(new Date());
                    payToMchtLog.setPayStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
                    payToMchtLog.setSettleAmount(mchtSettleOrder.getSettleAmount());
                    payToMchtLog.setDepositAmount(mchtSettleOrder.getDepositAmount());
                    payToMchtLog.setPayAmount(mchtSettleOrder.getNeedPayAmount());
                    payToMchtLog.setConfirmStatus("0");
                    payToMchtLogList.add(payToMchtLog);

                    mchtSettleOrder.setNeedPayAmount(new BigDecimal(0));
                    mchtSettleOrderList.add(mchtSettleOrder);
                }
            }
            mchtSettleOrderService.batchPay(mchtSettleOrderList, payToMchtLogList);
            resMap.put("returnCode", "0000");
            resMap.put("returnMsg", "确认成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    //品牌
    @ResponseBody
    @RequestMapping("/mchtSettleOrder/getProductBrandList.shtml")
    public Map<String, Object> getProductBrandList(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<ProductBrand> dataList = null;
        Integer totalCount = 0;
        try {
            ProductBrandExample productBrandExample = new ProductBrandExample();
            ProductBrandExample.Criteria productBrandCriteria = productBrandExample.createCriteria();
            productBrandCriteria.andDelFlagEqualTo("0");
            if (!StringUtil.isEmpty(request.getParameter("condition"))) {
                JSONArray jsonArray = JSONArray.fromObject(request.getParameter("condition"));
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String searchvalue = jsonObject.getString("value");
                    String searchitem = jsonObject.getString("field");
                    if (!StringUtil.isEmpty(searchvalue)) {
                        if (searchitem.equals("name")) {
                            productBrandCriteria.andNameLike("%" + searchvalue + "%");
                        }
                    }
                }
            }
            productBrandExample.setLimitSize(page.getLimitSize());
            productBrandExample.setLimitStart(page.getLimitStart());
            dataList = productBrandService.selectByExample(productBrandExample);
            totalCount = productBrandService.countByExample(productBrandExample);

        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }
}
