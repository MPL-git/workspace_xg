package com.jf.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.CombineOrderCustom;
import com.jf.entity.RefundOrderCustom;
import com.jf.service.CombineDepositOrderService;
import com.jf.service.CombineOrderService;
import com.jf.service.RefundOrderService;
import com.jf.vo.Page;

@Controller
public class PaymentCountController extends BaseController {
	
	@Resource
	private CombineOrderService combineOrderService;
	
	@Resource
	private RefundOrderService refundOrderService;
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 支付收入汇总
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/payment/paymentIncome.shtml")
	public ModelAndView combineDepositOrderCountList(HttpServletRequest request) {
		String rtPage = "/payment/paymentIncome";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String pay_date_end = df.format(new Date());
		String pay_date_begin = pay_date_end.substring(0,7)+"-01";
		resMap.put("pay_date_end", pay_date_end);
		resMap.put("pay_date_begin", pay_date_begin);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 支付收入汇总列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/paymentIncome/data.shtml")
	@ResponseBody
	public Map<String, Object> paymentIncomeCountData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
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
			paramMap.put("limitStart",page.getLimitStart());
			paramMap.put("limitSize",page.getLimitSize());
			List<CombineOrderCustom> paymentIncomeCustoms = combineOrderService.paymentIncomeCountEachDayList(paramMap);
			totalCount=combineOrderService.paymentIncomeCountEachDayCount(paramMap);
	
			resMap.put("Rows", paymentIncomeCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	
	/**
	 * 导出 支付收入汇总excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/paymentIncome/export.shtml")
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
			List<CombineOrderCustom> paymentIncomeCustoms = combineOrderService.paymentIncomeCountEachDayList(paramMap);
			String[] titles = { "收款日期", "微信APP日常销售", "公众号日常销售","微信APP定金","公众号定金","微信APPSVIP","微信公众号SVIP","支付宝定金","支付宝日常销售","支付宝SVIP"};
			ExcelBean excelBean = new ExcelBean("导出支付收入汇总列表.xls",
					"导出支付收入汇总列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(CombineOrderCustom paymentIncomeCustom:paymentIncomeCustoms){
				
				String[] data = {
						paymentIncomeCustom.getEachDay1(),
						paymentIncomeCustom.getWxappAmount() == null? "":paymentIncomeCustom.getWxappAmount().toString(),
						paymentIncomeCustom.getWxgzhAmount() == null? "":paymentIncomeCustom.getWxgzhAmount().toString(),
						paymentIncomeCustom.getCdowxAmount() == null? "":paymentIncomeCustom.getCdowxAmount().toString(),
						paymentIncomeCustom.getCdogzhAmount() == null? "":paymentIncomeCustom.getCdogzhAmount().toString(),
						paymentIncomeCustom.getSvipwxAmount() == null? "":paymentIncomeCustom.getSvipwxAmount().toString(),
						paymentIncomeCustom.getSvipgzhAmount() == null? "":paymentIncomeCustom.getSvipgzhAmount().toString(),
						paymentIncomeCustom.getCdozfbAmount() == null? "":paymentIncomeCustom.getCdozfbAmount().toString(),
						paymentIncomeCustom.getzFbamount() == null? "":paymentIncomeCustom.getzFbamount().toString(),
						paymentIncomeCustom.getSvipzfbAmount() == null? "":paymentIncomeCustom.getSvipzfbAmount().toString()
						
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
	 *  支付支出汇总
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/payment/paymentExpenditure.shtml")
	public ModelAndView countList(HttpServletRequest request) {
		String rtPage = "/payment/paymentExpenditure";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String success_date_end = df.format(new Date());
		String success_date_begin = success_date_end.substring(0,7)+"-01";
		resMap.put("success_date_end", success_date_end);
		resMap.put("success_date_begin", success_date_begin);
		return new ModelAndView(rtPage,resMap);
	}
	
	
	/**
	 * 支付支出汇总数据列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/payment/paymentExpendituredata.shtml")
	@ResponseBody
	public Map<String, Object> paymentExpendituredata(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount=0;
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			String successDateBegin = request.getParameter("success_date_begin");
			String successDateEnd = request.getParameter("success_date_end");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(successDateBegin) ){
				paramMap.put("successDateBegin", successDateBegin+" 00:00:00");
			}else{
				paramMap.put("successDateBegin", df.format(new Date()).substring(0,7)+"-01 00:00:00");
			}
			if(!StringUtil.isEmpty(successDateEnd) ){
				paramMap.put("successDateEnd", successDateEnd+" 23:59:59");
			}else{
				paramMap.put("successDateEnd", df.format(new Date()));
			}
			paramMap.put("limitStart",page.getLimitStart());
			paramMap.put("limitSize",page.getLimitSize());
			List<RefundOrderCustom> refundOrderCustoms = refundOrderService.paymenExpenditureCountEachDayList(paramMap);
			totalCount=refundOrderService.paymenExpenditureCountEachDayCount(paramMap);
			resMap.put("Rows", refundOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	/**
	 * 导出支付支出汇总excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/paymentExpenditure/export.shtml")
	public void paymentExpenditureExport(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			String successDateBegin = request.getParameter("success_date_begin");
			String successDateEnd = request.getParameter("success_date_end");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(successDateBegin) ){
				paramMap.put("successDateBegin", successDateBegin+" 00:00:00");
			}else{
				paramMap.put("successDateBegin", df.format(new Date()).substring(0,7)+"-01 00:00:00");
			}
			if(!StringUtil.isEmpty(successDateEnd) ){
				paramMap.put("successDateEnd", successDateEnd+" 23:59:59");
			}else{
				paramMap.put("successDateEnd", df.format(new Date()));
			}
			List<RefundOrderCustom> refundOrderCustoms = refundOrderService.paymenExpenditureCountEachDayList(paramMap);
			String[] titles = { "日期", "微信APP日常销售退款", "公众号日常销售退款","微信APP定金退款","公众号定金退款","微信APPSVIP退款","微信公众号SVIP退款","支付宝定金退款","支付宝日常销售退款","支付宝SVIP退款","微信红包","支付宝支付"};
			ExcelBean excelBean = new ExcelBean("导出支付支出汇总列表.xls",
					"导出支付支出汇总列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(RefundOrderCustom refundOrderCustom:refundOrderCustoms){
				
				String[] data = {
						refundOrderCustom.getEachDay1(),
						refundOrderCustom.getRowxappAmount() == null? "":refundOrderCustom.getRowxappAmount().toString(),
						refundOrderCustom.getRowxgzhAmount() == null? "":refundOrderCustom.getRowxgzhAmount().toString(),
						refundOrderCustom.getRocdowxAmount() == null? "":refundOrderCustom.getRocdowxAmount().toString(),
						refundOrderCustom.getRocdogzhAmount() == null? "":refundOrderCustom.getRocdogzhAmount().toString(),
						/*refundOrderCustom.getRovipwxAmount() == null? "":refundOrderCustom.getRovipwxAmount().toString(),
						refundOrderCustom.getRosvipgzhAmount() == null? "":refundOrderCustom.getRosvipgzhAmount().toString(),*/
						"0.00",
						"0.00",
						refundOrderCustom.getRocdozfbAmount() == null? "":refundOrderCustom.getRocdozfbAmount().toString(),
						refundOrderCustom.getRozFbAmount() == null? "":refundOrderCustom.getRozFbAmount().toString(),
						/*refundOrderCustom.getRosvipzfbAmount() == null? "":refundOrderCustom.getRosvipzfbAmount().toString(),*/
						"0.00",		
					    refundOrderCustom.getWxrAmount()==null?"":refundOrderCustom.getWxrAmount().toString(),
					    "0.00"
						
			};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
