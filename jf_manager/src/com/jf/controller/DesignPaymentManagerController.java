package com.jf.controller;


import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.DesignPaymentService;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 设计付费管理
 * @author chenyk
 *
 */




@Controller
public class DesignPaymentManagerController extends BaseController {


    @Autowired
    private DesignPaymentService designPaymentService;




    /**
     *
     * @Title
     * @Description TODO(设计收款明细)
     * @author chenyk
     * @date 2020年3月19日 上午11:40
     */
    @RequestMapping("/desiPayment/designReceiptManager.shtml")
    public ModelAndView designReceiptOrderManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("designPayment/designReceipt");

        if(!StringUtils.isEmpty(request.getParameter("payDate"))){
            m.addObject("nowDate", request.getParameter("payDate"));
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            m.addObject("nowDate",sdf.format(new Date()));
            Calendar begin = Calendar.getInstance();
            begin.add(Calendar.MONTH,0);
            begin.set(Calendar.DAY_OF_MONTH,1);
            m.addObject("monthBegin", sdf.format(begin.getTime()));
            Calendar end = Calendar.getInstance();
            end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
            m.addObject("monthEnd", sdf.format(end.getTime()));
        }
        return m;
    }


    /**
     *
     * @Title
     * @Description TODO(设计收款明细列表)
     * @author chenyk
     * @date 2020年3月19日 上午11:40
     */
    @ResponseBody
    @RequestMapping("/desiPayment/designReceiptList.shtml")
    public Map<String, Object> designReceiptOrderList(HttpServletRequest request, Page page) {
        Map<String,Object> resMap = new HashMap<String,Object>();
        List<DesignTaskOrderCustom> dataList = null;
        Integer totalCount = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DesignTaskOrderCustomExample designTaskOrderCustomExample = new DesignTaskOrderCustomExample();
            DesignTaskOrderCustomExample.DesignTaskOrderCustomCriteria DesignTaskOrderCC = designTaskOrderCustomExample.createCriteria();
            DesignTaskOrderCC.andDStatusEqualTo("0").andPayStatusEqualTo("1");
            DesignTaskOrderCC.andDDelFlagEqualTo("0");

            if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
                DesignTaskOrderCC.andOrderCodeLike("%"+request.getParameter("orderCode")+"%");
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ) {
                DesignTaskOrderCC.andMchtCodeEqualTo(request.getParameter("mchtCode"));
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtName")) ) {
                DesignTaskOrderCC.andMchtNameLike("%"+request.getParameter("mchtName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("taskName")) ) {
                DesignTaskOrderCC.andTaskNameLike("%"+request.getParameter("taskName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) ) {
                DesignTaskOrderCC.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
            }
            if(!StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
                DesignTaskOrderCC.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
            }
            if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ) {
                DesignTaskOrderCC.andPaymentNoLike("%"+request.getParameter("paymentNo")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("taskType")) ) {
                DesignTaskOrderCC.andTaskTypeEqualTo(request.getParameter("taskType"));
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "2".equals(request.getParameter("tag"))) {
                DesignTaskOrderCC.andDesignRefundOrderEqualTo();
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "1".equals(request.getParameter("tag"))) {
                DesignTaskOrderCC.andDesignReceivablesEqualTo();
            }

            designTaskOrderCustomExample.setOrderByClause(" d.create_date desc");
            designTaskOrderCustomExample.setLimitStart(page.getLimitStart());
            designTaskOrderCustomExample.setLimitSize(page.getLimitSize());
            dataList = designPaymentService.selectByCustomExample(designTaskOrderCustomExample);
            totalCount = designPaymentService.countByCustomExample(designTaskOrderCustomExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    /**
     *
     * @Title
     * @Description TODO(设计收款明细导出)
     * @author chenyk
     * @date 2020年3月19日 上午11:40
     */
    @RequestMapping("/desiPayment/exportDesignReceiptList.shtml")
    public void exportDesignReceiptOrderList(HttpServletRequest request, HttpServletResponse response) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DesignTaskOrderCustomExample designTaskOrderCustomExample = new DesignTaskOrderCustomExample();
            DesignTaskOrderCustomExample.DesignTaskOrderCustomCriteria DesignTaskOrderCC = designTaskOrderCustomExample.createCriteria();
            DesignTaskOrderCC.andDStatusEqualTo("0").andPayStatusEqualTo("1");
            DesignTaskOrderCC.andDDelFlagEqualTo("0");

            if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
                DesignTaskOrderCC.andOrderCodeLike("%"+request.getParameter("orderCode")+"%");
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ) {
                DesignTaskOrderCC.andMchtCodeEqualTo(request.getParameter("mchtCode"));
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtName")) ) {
                DesignTaskOrderCC.andMchtNameLike("%"+request.getParameter("mchtName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("taskName")) ) {
                DesignTaskOrderCC.andTaskNameLike("%"+request.getParameter("taskName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) ) {
                DesignTaskOrderCC.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
            }
            if(!StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
                DesignTaskOrderCC.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
            }
            if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ) {
                DesignTaskOrderCC.andPaymentNoLike("%"+request.getParameter("paymentNo")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("taskType")) ) {
                DesignTaskOrderCC.andTaskTypeEqualTo(request.getParameter("taskType"));
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "2".equals(request.getParameter("tag"))) {
                DesignTaskOrderCC.andDesignRefundOrderEqualTo();
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "1".equals(request.getParameter("tag"))) {
                DesignTaskOrderCC.andDesignReceivablesEqualTo();
            }

            designTaskOrderCustomExample.setOrderByClause(" d.create_date desc ");
            List<DesignTaskOrderCustom> designTaskOrderCustomList = designPaymentService.selectByCustomExample(designTaskOrderCustomExample);
            String[] titles = {"订单编号","类型","任务名称","商家序号","公司名称","店铺名称","付款渠道","实收金额(元)","付款状态","付款时间","支付宝交易号"};
            ExcelBean excelBean = new ExcelBean("设计收款明细.xls", "设计收款明细", titles);
            List<String[]> datas = new ArrayList<String[]>();
            for(DesignTaskOrderCustom designTaskOrderCustom : designTaskOrderCustomList) {
                String[] data = {
                        designTaskOrderCustom.getOrderCode()==null?"":designTaskOrderCustom.getOrderCode(),
                        designTaskOrderCustom.getTaskType()==null?"":designTaskOrderCustom.getTaskType().equals("1")?"品牌特卖":"店铺装修",
                        designTaskOrderCustom.getTaskName()==null?"":designTaskOrderCustom.getTaskName(),
                        designTaskOrderCustom.getMchtCode()==null?"":designTaskOrderCustom.getMchtCode(),
                        designTaskOrderCustom.getCompanyName()==null?"":designTaskOrderCustom.getCompanyName(),
                        designTaskOrderCustom.getShopName()==null?"":designTaskOrderCustom.getShopName(),
                        designTaskOrderCustom.getPaymentName()==null?"":designTaskOrderCustom.getPaymentName(),
                        designTaskOrderCustom.getPayAmount()==null?"":designTaskOrderCustom.getPayAmount().toString(),
                        designTaskOrderCustom.getPayStatusDesc()==null?"已付款":designTaskOrderCustom.getPayStatusDesc().equals("0")?"退款中": designTaskOrderCustom.getPayStatusDesc().equals("1")?"退款中": designTaskOrderCustom.getPayStatusDesc().equals("2")?"退款成功":"退款失败",
                        designTaskOrderCustom.getPayDate()==null?"":sdf.format(designTaskOrderCustom.getPayDate()),
                        "`"+designTaskOrderCustom.getPaymentNo()
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @Title
     * @Description TODO(设计收款明细查询总金额)
     * @author chenyk
     * @date 2020年3月20日 上午09:25
     */
    @ResponseBody
    @RequestMapping("/desiPayment/getDesignReceiptTotalPayment.shtml")
    public Map<String, Object> getTotalPayment(HttpServletRequest request){
        Map<String,Object> resMap = new HashMap<String,Object>();
        String totalPayment = "0";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DesignTaskOrderCustomExample designTaskOrderCustomExample = new DesignTaskOrderCustomExample();
        DesignTaskOrderCustomExample.DesignTaskOrderCustomCriteria DesignTaskOrderCC = designTaskOrderCustomExample.createCriteria();
        DesignTaskOrderCC.andDStatusEqualTo("0").andPayStatusEqualTo("1");
        DesignTaskOrderCC.andDDelFlagEqualTo("0");

            if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
                DesignTaskOrderCC.andOrderCodeLike("%"+request.getParameter("orderCode")+"%");
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ) {
                DesignTaskOrderCC.andMchtCodeEqualTo(request.getParameter("mchtCode"));
            }
            if(!StringUtil.isEmpty(request.getParameter("taskType")) ) {
                DesignTaskOrderCC.andTaskTypeEqualTo(request.getParameter("taskType"));
            }


            if(!StringUtil.isEmpty(request.getParameter("taskName")) ) {
                DesignTaskOrderCC.andTaskNameLike("%"+request.getParameter("taskName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ) {
                DesignTaskOrderCC.andPaymentNoLike("%"+request.getParameter("paymentNo")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("mchtName")) ) {
                DesignTaskOrderCC.andMchtNameLike("%"+request.getParameter("mchtName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) ) {
                DesignTaskOrderCC.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
            }
            if(!StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
                DesignTaskOrderCC.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "2".equals(request.getParameter("tag"))){
                DesignTaskOrderCC.andDesignRefundOrderEqualTo();
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "1".equals(request.getParameter("tag"))) {
                DesignTaskOrderCC.andDesignReceivablesEqualTo();
            }

            DesignTaskOrderCustom designTaskOrderCustom = designPaymentService.totalPaymentByCustomExample(designTaskOrderCustomExample);
            if(designTaskOrderCustom.getTotalPayment() == null)
            {
                totalPayment = "0";
            }else {
                totalPayment = designTaskOrderCustom.getTotalPayment().toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("totalPayment",totalPayment);
        return resMap;

    }
    /**
     * 每日设计退款页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/designRefundOrder/count/list.shtml")
    public ModelAndView countList(HttpServletRequest request) {
        String rtPage = "designPayment/designRefund";
        Map<String, Object> resMap = new HashMap<String, Object>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String pay_date_end = df.format(new Date());
        String pay_date_begin = pay_date_end.substring(0,7)+"-01";
        resMap.put("pay_date_end", pay_date_end);
        resMap.put("pay_date_begin", pay_date_begin);
        resMap.put("status","2");
        return new ModelAndView(rtPage,resMap);
    }


    /**
     * 每日设计退款列表
     * @param request
     * @param page
     * @return
     */
    @RequestMapping(value = "/designRefundOrder/count/data.shtml")
    @ResponseBody
    public Map<String, Object> countData(HttpServletRequest request,Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            String payDateBegin = request.getParameter("pay_date_begin");
            String payDateEnd = request.getParameter("pay_date_end");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(!StringUtil.isEmpty(payDateBegin) ){
                paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
            }else{
                paramMap.put("payDateBegin", df.format(new Date()).substring(0,7)+"-01 00:00:00");
            }
            if(!StringUtil.isEmpty(payDateEnd) ){
                paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
            }else{
                paramMap.put("payDateEnd", df.format(new Date()));
            }

            List<DesignTaskOrderCustom> designTaskOrderCustoms =designPaymentService.designRefundOrderStatistics(paramMap);
            resMap.put("Rows", designTaskOrderCustoms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }

    /**
     * 导出每日设计退款汇总excel
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/designRefundOrder/count/export.shtml")
    public void exportCount(HttpServletRequest request,HttpServletResponse response) throws Exception {
        try {
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            String payDateBegin = request.getParameter("pay_date_begin");
            String payDateEnd = request.getParameter("pay_date_end");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(!StringUtil.isEmpty(payDateBegin) ){
                paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
            }else{
                paramMap.put("payDateBegin", df.format(new Date()).substring(0,7)+"-01 00:00:00");
            }
            if(!StringUtil.isEmpty(payDateEnd) ){
                paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
            }else{
                paramMap.put("payDateEnd", df.format(new Date()));
            }
            List<DesignTaskOrderCustom> designTaskOrderCustoms =designPaymentService.designRefundOrderStatistics(paramMap);
            String[] titles = { "付款日期", "支付宝笔数", "支付宝金额"};
            ExcelBean excelBean = new ExcelBean("导出每日设计退款汇总列表.xls",
                    "导出每日设计退款汇总列表", titles);
            List<String[]> datas = new ArrayList<String[]>();
            for(DesignTaskOrderCustom designTaskOrderCustom:designTaskOrderCustoms){
                String[] data = {
                        designTaskOrderCustom.getEachDay(),
                        designTaskOrderCustom.getZfbCount() == null? "":designTaskOrderCustom.getZfbCount().toString(),
                        designTaskOrderCustom.getZfbAmount() == null? "":designTaskOrderCustom.getZfbAmount().toString(),

                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设计每日退款明细导出
     * @param request
     * @param response
     */
    @RequestMapping("/designRefundOrderDtl/count/export.shtml")
    public void exportDesignRefundOrderDtlList(HttpServletRequest request, HttpServletResponse response) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DesignTaskOrderCustomExample designTaskOrderCustomExample = new DesignTaskOrderCustomExample();
            DesignTaskOrderCustomExample.DesignTaskOrderCustomCriteria DesignTaskOrderCC = designTaskOrderCustomExample.createCriteria();
            DesignTaskOrderCC.andDStatusEqualTo("0").andPayStatusEqualTo("1");
            DesignTaskOrderCC.andDDelFlagEqualTo("0");

            if(!StringUtil.isEmpty(request.getParameter("payDateBegin")) ) {
                DesignTaskOrderCC.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin")+" 00:00:00"));
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ) {
                DesignTaskOrderCC.andMchtCodeEqualTo(request.getParameter("mchtCode"));
            }

            if(!StringUtil.isEmpty(request.getParameter("taskName")) ) {
                DesignTaskOrderCC.andTaskNameLike("%"+request.getParameter("taskName")+"%");
            }
            //商家名称匹配
            if(!StringUtil.isEmpty(request.getParameter("mchtName")) ) {
                DesignTaskOrderCC.andMchtNameLike("%"+request.getParameter("mchtName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
                DesignTaskOrderCC.andOrderCodeLike("%"+request.getParameter("orderCode")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("payDateEnd")) ) {
                DesignTaskOrderCC.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd")+" 23:59:59"));
            }
            if(!StringUtil.isEmpty(request.getParameter("taskType")) ) {
                DesignTaskOrderCC.andTaskTypeEqualTo(request.getParameter("taskType"));
            }
            if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ) {
                DesignTaskOrderCC.andPaymentNoLike("%"+request.getParameter("paymentNo")+"%");
            }

            //每日设计退款
            if (!StringUtil.isEmpty(request.getParameter("tag")) && "2".equals(request.getParameter("tag"))){
                DesignTaskOrderCC.andDesignRefundOrderEqualTo();
            }

            //设计退款导出
            designTaskOrderCustomExample.setOrderByClause(" d.create_date desc ");
            List<DesignTaskOrderCustom> designTaskOrderCustomList = designPaymentService.selectByCustomExample(designTaskOrderCustomExample);
            String[] titles = {"订单编号","类型","任务名称","商家序号","公司名称","店铺名称","付款渠道","实收金额(元)","付款状态","付款时间","支付宝交易号"};
            ExcelBean excelBean = new ExcelBean("设计退款明细.xls", "设计退款明细", titles);
            List<String[]> datas = new ArrayList<String[]>();
            for(DesignTaskOrderCustom designTaskOrderCustom : designTaskOrderCustomList) {
                String[] data = {
                        designTaskOrderCustom.getOrderCode()==null?"":designTaskOrderCustom.getOrderCode(),
                        designTaskOrderCustom.getTaskType()==null?"":designTaskOrderCustom.getTaskType().equals("1")?"品牌特卖":"店铺装修",
                        designTaskOrderCustom.getTaskName()==null?"":designTaskOrderCustom.getTaskName(),
                        designTaskOrderCustom.getMchtCode()==null?"":designTaskOrderCustom.getMchtCode(),
                        designTaskOrderCustom.getCompanyName()==null?"":designTaskOrderCustom.getCompanyName(),
                        designTaskOrderCustom.getShopName()==null?"":designTaskOrderCustom.getShopName(),
                        designTaskOrderCustom.getPaymentName()==null?"":designTaskOrderCustom.getPaymentName(),
                        designTaskOrderCustom.getPayAmount()==null?"":designTaskOrderCustom.getPayAmount().toString(),
                        designTaskOrderCustom.getPayStatusDesc()==null?"已付款":designTaskOrderCustom.getPayStatusDesc().equals("0")?"退款中": designTaskOrderCustom.getPayStatusDesc().equals("1")?"退款中": designTaskOrderCustom.getPayStatusDesc().equals("2")?"退款成功":"退款失败",
                        designTaskOrderCustom.getPayDate()==null?"":sdf.format(designTaskOrderCustom.getPayDate()),
                        "`"+designTaskOrderCustom.getPaymentNo()
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设计每日退款明细
     * @param request
     * @return
     */
    @RequestMapping(value = "/designRefundOrderDtl/list.shtml")
    public ModelAndView orderCombineList(HttpServletRequest request) {
        String rtPage = "designPayment/designRefundOrderDtl";
        Map<String, Object> resMap = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(request.getParameter("payDate"))){
            resMap.put("nowDate", request.getParameter("payDate"));
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            resMap.put("nowDate", sdf.format(new Date()));
        }
        resMap.put("paymentId", request.getParameter("paymentId"));
        return new ModelAndView(rtPage,resMap);
    }


    @RequestMapping({"/designReceivables/count/export.shtml"})
    public void exportDesignReceivablesCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            HashMap<String, Object> paramMap = new HashMap();
            String payDateBegin = request.getParameter("pay_date_begin");
            String payDateEnd = request.getParameter("pay_date_end");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(payDateBegin)) {
                paramMap.put("payDateBegin", payDateBegin + " 00:00:00");
            } else {
                paramMap.put("payDateBegin", df.format(new Date()).substring(0, 7) + "-01 00:00:00");
            }

            if (!StringUtil.isEmpty(payDateEnd)) {
                paramMap.put("payDateEnd", payDateEnd + " 23:59:59");
            } else {
                paramMap.put("payDateEnd", df.format(new Date()));
            }

            List<DesignTaskOrderCustom> designTaskOrderCustoms = designPaymentService.designReceivablesStatistics(paramMap);
            String[] titles = new String[]{"付款日期", "支付宝笔数", "支付宝金额"};
            ExcelBean excelBean = new ExcelBean("导出每日设计付款汇总列表.xls", "导出每日设计付款汇总列表", titles);
            List<String[]> datas = new ArrayList<String[]>();
            for(DesignTaskOrderCustom designTaskOrderCustom : designTaskOrderCustoms) {
                String[] data = new String[]{designTaskOrderCustom.getEachDay(),
                        designTaskOrderCustom.getZfbCount() == null ? "" : designTaskOrderCustom.getZfbCount().toString(),
                        designTaskOrderCustom.getZfbAmount() == null ? "" : designTaskOrderCustom.getZfbAmount().toString()};
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean,response);
        } catch (Exception var14) {
            var14.printStackTrace();
        }

    }

    @RequestMapping({"/designReceivablesDtl/count/export.shtml"})
    public void exportDesignReceivablesDtlList(HttpServletRequest request, HttpServletResponse response) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DesignTaskOrderCustomExample designTaskOrderCustomExample = new DesignTaskOrderCustomExample();
            DesignTaskOrderCustomExample.DesignTaskOrderCustomCriteria DesignTaskOrderCC = designTaskOrderCustomExample.createCriteria();
            DesignTaskOrderCC.andDStatusEqualTo("0").andPayStatusEqualTo("1");
            DesignTaskOrderCC.andDDelFlagEqualTo("0");
            if (!StringUtil.isEmpty(request.getParameter("payDateBegin"))) {
                DesignTaskOrderCC.andPayDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("payDateBegin") + " 00:00:00"));
            }

            if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
                DesignTaskOrderCC.andMchtCodeEqualTo(request.getParameter("mchtCode"));
            }

            if (!StringUtil.isEmpty(request.getParameter("taskName"))) {
                DesignTaskOrderCC.andTaskNameLike("%" + request.getParameter("taskName") + "%");
            }

            if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
                DesignTaskOrderCC.andMchtNameLike("%" + request.getParameter("mchtName") + "%");
            }

            if (!StringUtil.isEmpty(request.getParameter("orderCode"))) {
                DesignTaskOrderCC.andOrderCodeLike("%" + request.getParameter("orderCode") + "%");
            }

            if (!StringUtil.isEmpty(request.getParameter("payDateEnd"))) {
                DesignTaskOrderCC.andPayDateLessThanOrEqualTo(sdf.parse(request.getParameter("payDateEnd") + " 23:59:59"));
            }

            if (!StringUtil.isEmpty(request.getParameter("taskType"))) {
                DesignTaskOrderCC.andTaskTypeEqualTo(request.getParameter("taskType"));
            }

            if (!StringUtil.isEmpty(request.getParameter("paymentNo"))) {
                DesignTaskOrderCC.andPaymentNoLike("%" + request.getParameter("paymentNo") + "%");
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "1".equals(request.getParameter("tag"))) {
                DesignTaskOrderCC.andDesignReceivablesEqualTo();
            }

            designTaskOrderCustomExample.setOrderByClause(" d.create_date desc ");
            List<DesignTaskOrderCustom> designTaskOrderCustomList = designPaymentService.selectByCustomExample(designTaskOrderCustomExample);
            String[] titles = new String[]{"订单编号", "类型", "任务名称", "商家序号", "公司名称", "店铺名称", "付款渠道", "实收金额(元)", "付款状态", "付款时间", "支付宝交易号"};
            ExcelBean excelBean = new ExcelBean("设计付款明细.xls", "设计付款明细", titles);
            List<String[]> datas = new ArrayList();
            for (DesignTaskOrderCustom designTaskOrderCustom : designTaskOrderCustomList) {
                String[] data = new String[]{designTaskOrderCustom.getOrderCode() == null ? "" : designTaskOrderCustom.getOrderCode(), designTaskOrderCustom.getTaskType() == null ? "" : (designTaskOrderCustom.getTaskType().equals("1") ? "品牌特卖" : "店铺装修"), designTaskOrderCustom.getTaskName() == null ? "" : designTaskOrderCustom.getTaskName(), designTaskOrderCustom.getMchtCode() == null ? "" : designTaskOrderCustom.getMchtCode(), designTaskOrderCustom.getCompanyName() == null ? "" : designTaskOrderCustom.getCompanyName(), designTaskOrderCustom.getShopName() == null ? "" : designTaskOrderCustom.getShopName(), designTaskOrderCustom.getPaymentName() == null ? "" : designTaskOrderCustom.getPaymentName(), designTaskOrderCustom.getPayAmount() == null ? "" : designTaskOrderCustom.getPayAmount().toString(), designTaskOrderCustom.getPayStatusDesc() == null ? "已付款" : (designTaskOrderCustom.getPayStatusDesc().equals("0") ? "退款中" : (designTaskOrderCustom.getPayStatusDesc().equals("1") ? "退款中" : (designTaskOrderCustom.getPayStatusDesc().equals("2") ? "退款成功" : "退款失败"))), designTaskOrderCustom.getPayDate() == null ? "" : sdf.format(designTaskOrderCustom.getPayDate()), "`" + designTaskOrderCustom.getPaymentNo()};
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean, response);
        } catch (Exception var13) {
            var13.printStackTrace();
        }

    }

    @RequestMapping({"/designReceivablesDtl/list.shtml"})
    public ModelAndView designReceivablesDtl(HttpServletRequest request) {
        String rtPage = "designPayment/designReceivablesDtl";
        Map<String, Object> resMap = new HashMap();
        if (!StringUtils.isEmpty(request.getParameter("payDate"))) {
            resMap.put("nowDate", request.getParameter("payDate"));
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            resMap.put("nowDate", sdf.format(new Date()));
        }

        resMap.put("paymentId", request.getParameter("paymentId"));
        return new ModelAndView(rtPage, resMap);
    }

    @RequestMapping(value = "/designReceivables/count/list.shtml")
    public ModelAndView designReceivables(HttpServletRequest request) {
        String rtPage = "designPayment/designReceivables";
        Map<String, Object> resMap = new HashMap<String, Object>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String pay_date_end = df.format(new Date());
        String pay_date_begin = pay_date_end.substring(0,7)+"-01";
        resMap.put("pay_date_end", pay_date_end);
        resMap.put("pay_date_begin", pay_date_begin);
        return new ModelAndView(rtPage,resMap);
    }

    @RequestMapping({"/designReceivables/count/data.shtml"})
    @ResponseBody
    public Map<String, Object> designReceivablesCountData(HttpServletRequest request, Page page) {
        HashMap resMap = new HashMap();

        try {
            HashMap<String, Object> paramMap = new HashMap();
            String payDateBegin = request.getParameter("pay_date_begin");
            String payDateEnd = request.getParameter("pay_date_end");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtil.isEmpty(payDateBegin)) {
                paramMap.put("payDateBegin", payDateBegin + " 00:00:00");
            } else {
                paramMap.put("payDateBegin", df.format(new Date()).substring(0, 7) + "-01 00:00:00");
            }

            if (!StringUtil.isEmpty(payDateEnd)) {
                paramMap.put("payDateEnd", payDateEnd + " 23:59:59");
            } else {
                paramMap.put("payDateEnd", df.format(new Date()));
            }

            List<DesignTaskOrderCustom> designTaskOrderCustoms = designPaymentService.designReceivablesStatistics(paramMap);
            resMap.put("Rows", designTaskOrderCustoms);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resMap;
    }

    /**
     *
     * @Title
     * @Description TODO(设计退款明细)
     * @author chenyk
     * @date 2020年6月19日 上午11:40
     */
    @RequestMapping("/desiPayment/designRefundManager.shtml")
    public ModelAndView designRefundOrderManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("designPayment/designRefund");

        if(!StringUtils.isEmpty(request.getParameter("refundCreateDate"))){
            m.addObject("nowDate", request.getParameter("refundCreateDate"));
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            m.addObject("nowDate",sdf.format(new Date()));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String pay_date_end = df.format(new Date());
            String pay_date_begin = pay_date_end.substring(0,7)+"-01";
            m.addObject("pay_date_end", pay_date_end);
            m.addObject("pay_date_begin", pay_date_begin);
        }
        return m;
    }


    /**3
     *
     * @Title
     * @Description TODO(设计退款明细列表)
     * @author chenyk
     * @date 2020年3月19日 上午11:40
     */
    @ResponseBody
    @RequestMapping("/desiPayment/designRefundList.shtml")
    public Map<String, Object> designRefundOrderList(HttpServletRequest request, Page page) {
        Map<String,Object> resMap = new HashMap<String,Object>();
        List<DesignTaskRefundOrderCustom> dataList = null;
        Integer totalCount = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DesignTaskRefundOrderCustomExample refundOrderExample = new DesignTaskRefundOrderCustomExample();
            DesignTaskRefundOrderCustomExample.DesignTaskRefundOrderCustomCriteria refundOrderCC = refundOrderExample.createCriteria();
            refundOrderCC.andTDelFlagEqualTo("0");


            if(!StringUtil.isEmpty(request.getParameter("status"))&&request.getParameter("status").equals("2")){ //只展示退款成功
                refundOrderCC.andTStatusEqualTo("2");
            }

            if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
                refundOrderCC.andOrderCodeLike("%"+request.getParameter("orderCode")+"%");
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ) {
                refundOrderCC.andMchtCodeEqualTo(request.getParameter("mchtCode"));
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtName")) ) {
                refundOrderCC.andMchtNameLike("%"+request.getParameter("mchtName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("taskName")) ) {
                refundOrderCC.andTaskNameLike("%"+request.getParameter("taskName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ) {
                refundOrderCC.andTCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin")+" 00:00:00"));
            }
            if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ) {
                refundOrderCC.andTCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
            }
            if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ) {
                refundOrderCC.andPaymentNoLike("%"+request.getParameter("paymentNo")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("taskType")) ) {
                refundOrderCC.andTaskTypeEqualTo(request.getParameter("taskType"));
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "2".equals(request.getParameter("tag"))) {
                refundOrderCC.andDesignRefundOrderEqualTo();
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "1".equals(request.getParameter("tag"))) {
                refundOrderCC.andDesignReceivablesEqualTo();
            }

            refundOrderExample.setOrderByClause(" t.id desc");
            refundOrderExample.setLimitStart(page.getLimitStart());
            refundOrderExample.setLimitSize(page.getLimitSize());
            dataList = designPaymentService.selectRefByCustomExample(refundOrderExample);
            totalCount = designPaymentService.designTaskRefundOrderCountByExample(refundOrderExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    /**
     *
     * @Title
     * @Description TODO(设计退款明细导出)
     * @author chenyk
     * @date 2020年3月19日 上午11:40
     */
    @RequestMapping("/desiPayment/exportDesignRefundList.shtml")
    public void exportDesignRefundOrderList(HttpServletRequest request, HttpServletResponse response) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DesignTaskRefundOrderCustomExample refundOrderExample = new DesignTaskRefundOrderCustomExample();
            DesignTaskRefundOrderCustomExample.DesignTaskRefundOrderCustomCriteria refundOrderCC = refundOrderExample.createCriteria();
            refundOrderCC.andTDelFlagEqualTo("0");


            if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
                refundOrderCC.andOrderCodeLike("%"+request.getParameter("orderCode")+"%");
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ) {
                refundOrderCC.andMchtCodeEqualTo(request.getParameter("mchtCode"));
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtName")) ) {
                refundOrderCC.andMchtNameLike("%"+request.getParameter("mchtName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("taskName")) ) {
                refundOrderCC.andTaskNameLike("%"+request.getParameter("taskName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ) {
                refundOrderCC.andTCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin")+" 00:00:00"));
            }
            if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ) {
                refundOrderCC.andTCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
            }

            if(!StringUtil.isEmpty(request.getParameter("taskType")) ) {
                refundOrderCC.andTaskTypeEqualTo(request.getParameter("taskType"));
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "2".equals(request.getParameter("tag"))) {
                refundOrderCC.andDesignRefundOrderEqualTo();
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "1".equals(request.getParameter("tag"))) {
                refundOrderCC.andDesignReceivablesEqualTo();
            }

            refundOrderExample.setOrderByClause(" t.id desc");
            List<DesignTaskRefundOrderCustom> list = designPaymentService.selectRefByCustomExample(refundOrderExample);
            String[] titles = {"订单编号","类型","任务名称","商家序号","公司名称","店铺名称","付款渠道","实退金额(元)","退款状态","退款时间","支付宝交易号"};
            ExcelBean excelBean = new ExcelBean("设计退款明细.xls", "设计退款明细", titles);
            List<String[]> datas = new ArrayList<String[]>();
            for(DesignTaskRefundOrderCustom designTaskRefundOrderCustom : list) {
                String[] data = {
                        designTaskRefundOrderCustom.getOrderCode()==null?"":designTaskRefundOrderCustom.getOrderCode(),
                        designTaskRefundOrderCustom.getTaskType()==null?"":designTaskRefundOrderCustom.getTaskType().equals("1")?"品牌特卖":"店铺装修",
                        designTaskRefundOrderCustom.getTaskName()==null?"":designTaskRefundOrderCustom.getTaskName(),
                        designTaskRefundOrderCustom.getMchtCode()==null?"":designTaskRefundOrderCustom.getMchtCode(),
                        designTaskRefundOrderCustom.getCompanyName()==null?"":designTaskRefundOrderCustom.getCompanyName(),
                        designTaskRefundOrderCustom.getShopName()==null?"":designTaskRefundOrderCustom.getShopName(),
                        designTaskRefundOrderCustom.getPaymentName()==null?"":designTaskRefundOrderCustom.getPaymentName(),
                        designTaskRefundOrderCustom.getRefundAmount()==null?"":designTaskRefundOrderCustom.getRefundAmount()+"",
                        designTaskRefundOrderCustom.getStatus().equals("0")?"未退":designTaskRefundOrderCustom.getStatus().equals("1")?"退款中": designTaskRefundOrderCustom.getStatus().equals("2")?"已退":"退款失败",
                        designTaskRefundOrderCustom.getCreateDate()==null?"":sdf.format(designTaskRefundOrderCustom.getCreateDate()),
                        "`"+designTaskRefundOrderCustom.getPaymentNo()
                };
                datas.add(data);
            }
            excelBean.setDataList(datas);
            ExcelUtils.export(excelBean,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @Title
     * @Description TODO(设计退款明细查询总金额)
     * @author chenyk
     * @date 2020年3月20日 上午09:25
     */
    @ResponseBody
    @RequestMapping("/desiPayment/getDesignTotalRefund.shtml")
    public Map<String, Object> getTotalRefund(HttpServletRequest request){
        Map<String,Object> resMap = new HashMap<String,Object>();
        String totalRefund = "0";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DesignTaskRefundOrderCustomExample refundOrderExample = new DesignTaskRefundOrderCustomExample();
            DesignTaskRefundOrderCustomExample.DesignTaskRefundOrderCustomCriteria refundOrderCC = refundOrderExample.createCriteria();
            refundOrderCC.andTDelFlagEqualTo("0");
            refundOrderCC.andTStatusEqualTo("2");

            if(!StringUtil.isEmpty(request.getParameter("orderCode")) ) {
                refundOrderCC.andOrderCodeLike("%"+request.getParameter("orderCode")+"%");
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ) {
                refundOrderCC.andMchtCodeEqualTo(request.getParameter("mchtCode"));
            }
            if(!StringUtil.isEmpty(request.getParameter("mchtName")) ) {
                refundOrderCC.andMchtNameLike("%"+request.getParameter("mchtName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("taskName")) ) {
                refundOrderCC.andTaskNameLike("%"+request.getParameter("taskName")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ) {
                refundOrderCC.andTCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateBegin")+" 00:00:00"));
            }
            if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ) {
                refundOrderCC.andTCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
            }
            if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ) {
                refundOrderCC.andPaymentNoLike("%"+request.getParameter("paymentNo")+"%");
            }

            if(!StringUtil.isEmpty(request.getParameter("taskType")) ) {
                refundOrderCC.andTaskTypeEqualTo(request.getParameter("taskType"));
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "2".equals(request.getParameter("tag"))) {
                refundOrderCC.andDesignRefundOrderEqualTo();
            }

            if (!StringUtil.isEmpty(request.getParameter("tag")) && "1".equals(request.getParameter("tag"))) {
                refundOrderCC.andDesignReceivablesEqualTo();
            }

            DesignTaskRefundOrderCustom  designTaskRefundOrderCustom = designPaymentService.totalRefundByCustomExample(refundOrderExample);
            if(designTaskRefundOrderCustom.getTotalRefund() != null)
            {
                totalRefund = designTaskRefundOrderCustom.getTotalRefund()+"";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("totalRefund",totalRefund);
        return resMap;

    }

}


