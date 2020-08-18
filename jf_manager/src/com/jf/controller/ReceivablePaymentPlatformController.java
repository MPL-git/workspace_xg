package com.jf.controller;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.CombineOrderCustom;
import com.jf.entity.CombineOrderCustomExample;
import com.jf.entity.PaymentPlatformReceivableDtl;
import com.jf.entity.PaymentPlatformReceivableDtlCustom;
import com.jf.entity.PaymentPlatformReceivableDtlCustomExample;
import com.jf.entity.PaymentPlatformReceivableDtlExample;
import com.jf.service.CombineOrderService;
import com.jf.service.MchtInfoService;
import com.jf.service.PaymentPlatformReceivableDtlService;
import com.jf.vo.Page;

@Controller
public class ReceivablePaymentPlatformController extends BaseController {
	
	@Resource
	private PaymentPlatformReceivableDtlService paymentPlatformReceivableDtlService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private CombineOrderService combineOrderService;

	private static final long serialVersionUID = 1L;
	
	/**
	 * 支付平台收款明细列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivablePaymentPlatform/paymentPlatformList.shtml")
	public ModelAndView paymentPlatformList(HttpServletRequest request) {
		String rtPage = "/receivablePaymentPlatform/paymentPlatformList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Date yesterDay = DateUtil.getYesterDayDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		resMap.put("received_date_begin", sdf.format(yesterDay));
		resMap.put("received_date_end", sdf.format(yesterDay));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 支付平台收款明细列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivablePaymentPlatform/paymentPlatformData.shtml")
	@ResponseBody
	public Map<String, Object> paymentPlatformData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			PaymentPlatformReceivableDtlCustomExample example = new PaymentPlatformReceivableDtlCustomExample();
			example.setOrderByClause("t.received_date desc");
			PaymentPlatformReceivableDtlCustomExample.PaymentPlatformReceivableDtlCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			String receivedDateBegin = request.getParameter("received_date_begin");
			String receivedDateEnd = request.getParameter("received_date_end");
			String type = request.getParameter("type");
			String paymentType = request.getParameter("paymentType");
			String createDateBegin = request.getParameter("create_date_begin");
			String createDateEnd = request.getParameter("create_date_end");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(receivedDateBegin)){
				criteria.andReceivedDateGreaterThanOrEqualTo(dateFormat.parse(receivedDateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(receivedDateEnd)){
				criteria.andReceivedDateLessThanOrEqualTo(dateFormat.parse(receivedDateEnd+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(type)){
				if(type.equals("1")){//实收当日
					criteria.andCombineOrderCodeIsNotNull();
					criteria.andReceivedDateEqualToCustomerPayDate();
				}else if(type.equals("2")){//实收非当日
					criteria.andCombineOrderCodeIsNotNull();
					criteria.andReceivedDateNotEqualToCustomerPayDate();
					
				}else if(type.equals("3")){//未在应收中
					criteria.andCombineOrderCodeIsNull();
					criteria.andCustomerPayDateIsNull();
				}
			}
			if(!StringUtil.isEmpty(paymentType)){
				criteria.andPaymentTypeEqualTo(paymentType);
			}
			if(!StringUtil.isEmpty(createDateBegin)){
				criteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(createDateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(createDateEnd)){
				criteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(createDateEnd+" 23:59:59"));
			}

			totalCount = paymentPlatformReceivableDtlService.countByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<PaymentPlatformReceivableDtlCustom> paymentPlatformReceivableDtlCustoms = paymentPlatformReceivableDtlService.selectByExample(example);
			resMap.put("Rows", paymentPlatformReceivableDtlCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/receivablePaymentPlatform/exportPaymentPlatform.shtml")
	public void exportPaymentPlatform(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			PaymentPlatformReceivableDtlCustomExample example = new PaymentPlatformReceivableDtlCustomExample();
			example.setOrderByClause("t.received_date desc");
			PaymentPlatformReceivableDtlCustomExample.PaymentPlatformReceivableDtlCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			String receivedDateBegin = request.getParameter("received_date_begin");
			String receivedDateEnd = request.getParameter("received_date_end");
			String type = request.getParameter("type");
			String paymentType = request.getParameter("paymentType");
			String createDateBegin = request.getParameter("create_date_begin");
			String createDateEnd = request.getParameter("create_date_end");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(receivedDateBegin)){
				criteria.andReceivedDateGreaterThanOrEqualTo(dateFormat.parse(receivedDateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(receivedDateEnd)){
				criteria.andReceivedDateLessThanOrEqualTo(dateFormat.parse(receivedDateEnd+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(type)){
				if(type.equals("1")){//实收当日
					criteria.andCombineOrderCodeIsNotNull();
					criteria.andReceivedDateEqualToCustomerPayDate();
				}else if(type.equals("2")){//实收非当日
					criteria.andCombineOrderCodeIsNotNull();
					criteria.andReceivedDateNotEqualToCustomerPayDate();
					
				}else if(type.equals("3")){//未在应收中
					criteria.andCombineOrderCodeIsNull();
					criteria.andCustomerPayDateIsNull();
				}
			}
			if(!StringUtil.isEmpty(paymentType)){
				criteria.andPaymentTypeEqualTo(paymentType);
			}
			if(!StringUtil.isEmpty(createDateBegin)){
				criteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(createDateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(createDateEnd)){
				criteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(createDateEnd+" 23:59:59"));
			}

			List<PaymentPlatformReceivableDtlCustom> paymentPlatformReceivableDtlCustoms = paymentPlatformReceivableDtlService.selectByExample(example);
			String[] titles = {"回款日期","交易号","金额","类型","支付渠道","客户付款日期","母订单号","导入时间"};
			ExcelBean excelBean = new ExcelBean("导出支付平台收款明细.xls",
					"导出支付平台收款明细", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<String[]> datas = new ArrayList<String[]>();
			for(PaymentPlatformReceivableDtlCustom paymentPlatformReceivableDtlCustom:paymentPlatformReceivableDtlCustoms){
				String typeDesc = "";
				if(!StringUtils.isEmpty(paymentPlatformReceivableDtlCustom.getCombineOrderCode()) && paymentPlatformReceivableDtlCustom.getReceivedDate().equals(paymentPlatformReceivableDtlCustom.getCustomerPayDate())){
					typeDesc = "实收当日";
				}else if(!StringUtils.isEmpty(paymentPlatformReceivableDtlCustom.getCombineOrderCode()) && !paymentPlatformReceivableDtlCustom.getReceivedDate().equals(paymentPlatformReceivableDtlCustom.getCustomerPayDate())){
					typeDesc = "实收非当日";
				}else if(StringUtils.isEmpty(paymentPlatformReceivableDtlCustom.getCombineOrderCode())){
					typeDesc = "未在应收中";
				}
				String paymentTypeDesc = "";
				if(paymentPlatformReceivableDtlCustom.getPaymentType().equals("1")){
					paymentTypeDesc = "微信(APP、H5)";
				}else if(paymentPlatformReceivableDtlCustom.getPaymentType().equals("2")){
					paymentTypeDesc = "微信(公众号)";
				}else if(paymentPlatformReceivableDtlCustom.getPaymentType().equals("3")){
					paymentTypeDesc = "支付宝(APP、H5)";
				}
				String[] data = {
					df.format(paymentPlatformReceivableDtlCustom.getReceivedDate()),
					"`"+paymentPlatformReceivableDtlCustom.getPaymentNo(),
					paymentPlatformReceivableDtlCustom.getAmount()==null?"":paymentPlatformReceivableDtlCustom.getAmount().toString(),
					typeDesc,
					paymentTypeDesc,
					paymentPlatformReceivableDtlCustom.getCustomerPayDate()==null?"":df.format(paymentPlatformReceivableDtlCustom.getCustomerPayDate()),
					paymentPlatformReceivableDtlCustom.getCombineOrderCode()==null?"":paymentPlatformReceivableDtlCustom.getCombineOrderCode(),
					sdf.format(paymentPlatformReceivableDtlCustom.getCreateDate())
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
	 * 导入页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivablePaymentPlatform/toImportData.shtml")
	public ModelAndView toConfirm(HttpServletRequest request) {
		String rtPage = "/receivablePaymentPlatform/toImportData";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivablePaymentPlatform/save.shtml")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String paymentType = request.getParameter("paymentType");
			String excelFilePath = request.getParameter("excelFilePath");
			int batchNo = paymentPlatformReceivableDtlService.getSequence("importSeq");
			InputStream stream = ReceivablePaymentPlatformController.class.getResourceAsStream("/base_config.properties");
  	        Properties properties = new Properties();
		    properties.load(stream);
		    String defaultPath = properties.getProperty("annex.filePath");
		    stream.close();
			File file = new File(defaultPath+excelFilePath);
			List<ArrayList<String>> dataList = ExcelUtils.read(file, file.getName(), 3,"1");//3表示取excel表格的前三列
			if(dataList == null || dataList.size() < 1) {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "excel表格没有数据，请重新导入");
				return resMap;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			List<PaymentPlatformReceivableDtl> paymentPlatformReceivableDtlList = new ArrayList<PaymentPlatformReceivableDtl>();
			int total = dataList.size()-1;
			int successTotal = 0;
			int errorTotal = 0;
			int exisitTotal = 0;
			for (int i = 1; i < dataList.size(); i++) {
				List<String> data = dataList.get(i);
				String receivedDate = data.get(0);
		        String paymentNo = data.get(1);
		        String amount = data.get(2);
		        if(StringUtils.isEmpty(receivedDate) || StringUtils.isEmpty(paymentNo) || StringUtils.isEmpty(amount)){
		        	errorTotal++;
		        	continue;
		        }
		        if(!StringUtils.isEmpty(amount)){
		        	int result = new BigDecimal(amount).compareTo(new BigDecimal(0));
		        	if(result<=0){
		        		errorTotal++;
			        	continue;
		        	}
		        }
		        PaymentPlatformReceivableDtlExample example = new PaymentPlatformReceivableDtlExample();
		        PaymentPlatformReceivableDtlExample.Criteria criteria = example.createCriteria();
		        criteria.andDelFlagEqualTo("0");
		        if(paymentNo.startsWith("'")){
		        	criteria.andPaymentNoEqualTo(paymentNo.substring(1));
		        }else{
		        	criteria.andPaymentNoEqualTo(paymentNo);
		        }
		        List<PaymentPlatformReceivableDtl> paymentPlatformReceivableDtls = paymentPlatformReceivableDtlService.selectByExample(example);
		        if(paymentPlatformReceivableDtls!=null && paymentPlatformReceivableDtls.size()>0){
		        	exisitTotal++;
		        	continue;
		        }
		        PaymentPlatformReceivableDtl pprd = new PaymentPlatformReceivableDtl();
		        pprd.setBatchNo(batchNo);
		        pprd.setReceivedDate(sdf.parse(receivedDate));
		        if(paymentNo.startsWith("'")){
		        	pprd.setPaymentNo(paymentNo.substring(1));
		        }else{
		        	pprd.setPaymentNo(paymentNo);
		        }
		        pprd.setAmount(new BigDecimal(amount));
		        pprd.setPaymentType(paymentType);
		        pprd.setCreateDate(date);
		        pprd.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		        pprd.setDelFlag("0");
		        paymentPlatformReceivableDtlList.add(pprd);
			}
			if(paymentPlatformReceivableDtlList!=null && paymentPlatformReceivableDtlList.size()>0){
				paymentPlatformReceivableDtlService.saveAndUpdate(paymentPlatformReceivableDtlList,batchNo);
				successTotal = paymentPlatformReceivableDtlList.size();
			}
			resMap.put("total", total);
			resMap.put("successTotal", successTotal);
			resMap.put("errorTotal", errorTotal);
			resMap.put("exisitTotal", exisitTotal);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "导入失败，请稍后重试");
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 批量删除
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivablePaymentPlatform/batchDelete.shtml")
	@ResponseBody
	public Map<String, Object> batchDelete(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String ids = request.getParameter("ids");
			PaymentPlatformReceivableDtl paymentPlatformReceivableDtl = new PaymentPlatformReceivableDtl();
			paymentPlatformReceivableDtl.setDelFlag("1");
			PaymentPlatformReceivableDtlExample example = new PaymentPlatformReceivableDtlExample();
			PaymentPlatformReceivableDtlExample.Criteria criteria = example.createCriteria();
			List<Integer> idsList = new ArrayList<Integer>();
			String[] idsArray = ids.split(",");
			for(int i=0;i<idsArray.length;i++){
				idsList.add(Integer.parseInt(idsArray[i]));
			}
			criteria.andIdIn(idsList);
			paymentPlatformReceivableDtlService.updateByExampleSelective(paymentPlatformReceivableDtl, example);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "批量删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "批量删除失败，请稍后重试");
		}
		return resMap;
	}
	
	/**
	 * 醒购平台收款明细列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivablePaymentPlatform/xgPlatformList.shtml")
	public ModelAndView xgPlatformList(HttpServletRequest request) {
		String rtPage = "/receivablePaymentPlatform/xgPlatformList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDay = sdf.format(new Date());
		resMap.put("customer_pay_date_begin", nowDay.substring(0,7)+"-01");
		resMap.put("customer_pay_date_end", nowDay);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 醒购平台收款明细列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivablePaymentPlatform/xgPaymentPlatformData.shtml")
	@ResponseBody
	public Map<String, Object> xgPaymentPlatformData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			CombineOrderCustomExample example = new CombineOrderCustomExample();
			example.setOrderByClause("a.pay_date asc");
			CombineOrderCustomExample.CombineOrderCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			String customerPayDateBegin = request.getParameter("customer_pay_date_begin");
			String customerPayDateEnd = request.getParameter("customer_pay_date_end");
			String type = request.getParameter("type");
			String paymentType = request.getParameter("paymentType");
			String atypism = request.getParameter("atypism");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(customerPayDateBegin)){
				criteria.andPayDateGreaterThanOrEqualTo(dateFormat.parse(customerPayDateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(customerPayDateEnd)){
				criteria.andPayDateLessThanOrEqualTo(dateFormat.parse(customerPayDateEnd+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(type)){
				if(type.equals("1")){//当日回款
					criteria.andRightDayReceived();
				}else if(type.equals("2")){//非当日回款
					criteria.andNotRightDayReceived();
				}else if(type.equals("3")){//未回款
					criteria.andNotReceived();
				}
			}
			if(!StringUtil.isEmpty(paymentType)){
				List<Integer> paymentIds = new ArrayList<Integer>();
				if(paymentType.equals("1")){
					paymentIds.add(2);
					paymentIds.add(5);
				}else if(paymentType.equals("2")){
					paymentIds.add(4);
				}else if(paymentType.equals("3")){
					paymentIds.add(1);
					paymentIds.add(6);
				}
				criteria.andPaymentIdIn(paymentIds);
			}
			if(!StringUtils.isEmpty(atypism) && atypism.equals("1")){//不一致
				criteria.andReceivedAmountNotEqual();
			}
			totalCount = combineOrderService.countByCustomExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<CombineOrderCustom> combineOrderCustoms = combineOrderService.selectByCustomExample(example);
			resMap.put("Rows", combineOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出醒购平台收款明细excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/receivablePaymentPlatform/exportXGPaymentPlatform.shtml")
	public void exportXGPaymentPlatform(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			CombineOrderCustomExample example = new CombineOrderCustomExample();
			example.setOrderByClause("a.pay_date asc");
			CombineOrderCustomExample.CombineOrderCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			String customerPayDateBegin = request.getParameter("customer_pay_date_begin");
			String customerPayDateEnd = request.getParameter("customer_pay_date_end");
			String type = request.getParameter("type");
			String paymentType = request.getParameter("paymentType");
			String atypism = request.getParameter("atypism");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(customerPayDateBegin)){
				criteria.andPayDateGreaterThanOrEqualTo(dateFormat.parse(customerPayDateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(customerPayDateEnd)){
				criteria.andPayDateLessThanOrEqualTo(dateFormat.parse(customerPayDateEnd+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(type)){
				if(type.equals("1")){//当日回款
					criteria.andRightDayReceived();
				}else if(type.equals("2")){//非当日回款
					criteria.andNotRightDayReceived();
				}else if(type.equals("3")){//未回款
					criteria.andNotReceived();
				}
			}
			if(!StringUtil.isEmpty(paymentType)){
				List<Integer> paymentIds = new ArrayList<Integer>();
				if(paymentType.equals("1")){
					paymentIds.add(2);
					paymentIds.add(5);
				}else if(paymentType.equals("2")){
					paymentIds.add(4);
				}else if(paymentType.equals("3")){
					paymentIds.add(1);
					paymentIds.add(6);
				}
				criteria.andPaymentIdIn(paymentIds);
			}
			if(!StringUtils.isEmpty(atypism) && atypism.equals("1")){//不一致
				criteria.andReceivedAmountNotEqual();
			}
			List<CombineOrderCustom> combineOrderCustoms = combineOrderService.selectByCustomExample(example);
			String[] titles = {"客户付款日期","母订单号","交易号","金额","类型","支付渠道","回款日期","回款金额"};
			ExcelBean excelBean = new ExcelBean("导出醒购平台收款明细.xls",
					"导出醒购平台收款明细", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<String[]> datas = new ArrayList<String[]>();
			for(CombineOrderCustom combineOrderCustom:combineOrderCustoms){
				String typeDesc = "";
				if(combineOrderCustom.getReceivedDate()!=null && df.format(combineOrderCustom.getReceivedDate()).equals(df.format(combineOrderCustom.getPayDate()))){
					typeDesc = "当日回款";
				}else if(combineOrderCustom.getReceivedDate()!=null && !df.format(combineOrderCustom.getReceivedDate()).equals(df.format(combineOrderCustom.getPayDate()))){
					typeDesc = "非当日回款";
				}else if(StringUtils.isEmpty(combineOrderCustom.getReceivedDate()) || StringUtils.isEmpty(combineOrderCustom.getReceivedAmount())){
					typeDesc = "未回款";
				}
				String paymentTypeDesc = "";
				if(combineOrderCustom.getPaymentId().equals(2) || combineOrderCustom.getPaymentId().equals(5)){
					paymentTypeDesc = "微信(APP、H5)";
				}else if(combineOrderCustom.getPaymentId().equals(4)){
					paymentTypeDesc = "微信(公众号)";
				}else if(combineOrderCustom.getPaymentId().equals(1) || combineOrderCustom.getPaymentId().equals(6)){
					paymentTypeDesc = "支付宝(APP、H5)";
				}
				String[] data = {
					df.format(combineOrderCustom.getPayDate()),
					combineOrderCustom.getCombineOrderCode(),
					"`"+combineOrderCustom.getPaymentNo(),
					combineOrderCustom.getTotalPayAmount()==null?"":combineOrderCustom.getTotalPayAmount().toString(),
					typeDesc,
					paymentTypeDesc,
					combineOrderCustom.getReceivedDate()==null?"":df.format(combineOrderCustom.getReceivedDate()),
					combineOrderCustom.getReceivedAmount()==null?"":combineOrderCustom.getReceivedAmount().toString()		
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
	 * 收款每日比对列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivablePaymentPlatform/receivedEachDayCompareList.shtml")
	public ModelAndView receivedEachDayCompareList(HttpServletRequest request) {
		String rtPage = "/receivablePaymentPlatform/receivedEachDayCompareList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDay = sdf.format(new Date());
		resMap.put("defaultYear", nowDay.substring(0,4));
		resMap.put("defaultMonth", nowDay.substring(5,7));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 收款每日比对列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivablePaymentPlatform/receivedEachDayCompareData.shtml")
	@ResponseBody
	public Map<String, Object> receivedEachDayCompareData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String paymentType = request.getParameter("paymentType");
		 	String dateBegin = year+"-"+month+"-01";
			String dateEnd = DateUtil.getMonthEnd(dateBegin);
			paramMap.put("dateBegin", dateBegin+" 00:00:00");
			paramMap.put("dateEnd", dateEnd+" 23:59:59");
			if(!StringUtils.isEmpty(paymentType)){
				paramMap.put("paymentType", paymentType);
			}
			List<HashMap<String, Object>> list = paymentPlatformReceivableDtlService.receivedEachDayCompare(paramMap);
			resMap.put("Rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
}
