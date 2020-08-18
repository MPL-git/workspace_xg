package com.jf.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.dao.ReceiptRegisterDateLockMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderCustom;
import com.jf.entity.CombineOrderExample;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.OrderDtlCustomExample;
import com.jf.entity.OrderPreferentialInfoCustom;
import com.jf.entity.ReceiptRegisterDateLock;
import com.jf.entity.ReceiptRegisterDateLockExample;
import com.jf.service.AppealLogService;
import com.jf.service.AppealOrderService;
import com.jf.service.CombineOrderService;
import com.jf.service.CustomerServiceLogService;
import com.jf.service.CustomerServiceOrderService;
import com.jf.service.CustomerServicePicService;
import com.jf.service.ExpressService;
import com.jf.service.OrderDtlService;
import com.jf.service.OrderLogService;
import com.jf.service.OrderPreferentialInfoService;
import com.jf.service.RemarksLogService;
import com.jf.service.ServiceLogPicService;
import com.jf.service.SubOrderService;
import com.jf.service.SysPaymentService;
import com.jf.service.SysStatusService;
import com.jf.vo.Page;

@Controller
public class ReceivablesController extends BaseController {
	
	@Resource
	private CombineOrderService combineOrderService;
	
	@Resource
	private SysPaymentService sysPaymentService;
	
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private OrderPreferentialInfoService orderPreferentialInfoService;
	
	@Resource
	private RemarksLogService remarksLogService;
	
	@Resource
	private OrderLogService orderLogService;
	
	@Resource
	private ExpressService expressService;
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	
	@Resource
	private CustomerServicePicService customerServicePicService;
	
	@Resource
	private CustomerServiceLogService customerServiceLogService;
	
	@Resource
	private ServiceLogPicService serviceLogPicService;
	
	@Resource
	private SysStatusService sysStatusService;
	
	@Resource
	private AppealOrderService appealOrderService;

	@Resource
	private AppealLogService appealLogService;
	
	@Resource
	private ReceiptRegisterDateLockMapper receiptRegisterDateLockMapper;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 收款母订单列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivables/combine/list.shtml")
	public ModelAndView orderCombineList(HttpServletRequest request) {
		String rtPage = "/receivables/combine/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("statusList", DataDicUtil.getStatusList("BU_COMBINE_ORDER", "STATUS"));
		resMap.put("financialStatus", DataDicUtil.getStatusList("BU_COMBINE_ORDER", "FINANCIAL_STATUS"));
		if(!StringUtils.isEmpty(request.getParameter("payDate"))){
			resMap.put("nowDate", request.getParameter("payDate"));
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			resMap.put("nowDate", sdf.format(new Date()));
		}
		resMap.put("paymentId", request.getParameter("paymentId"));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 收款母订单列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivables/combine/data.shtml")
	@ResponseBody
	public Map<String, Object> orderCombineData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			CombineOrderExample combineOrderExample=new CombineOrderExample();
			CombineOrderExample.Criteria combineOrderCriteria=combineOrderExample.createCriteria();
			combineOrderCriteria.andDelFlagEqualTo("0");
			combineOrderCriteria.andStatusEqualTo("1");
			combineOrderExample.setOrderByClause("a.id desc");
			if(!StringUtil.isEmpty(request.getParameter("paymentId"))){
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("1")){//微信APP/H5
					Integer[] wxStr={2,5};
					List<Integer> wxList = Arrays.asList(wxStr);
					combineOrderCriteria.andPaymentIdIn(wxList);
				}
				if (paymentId.equals("2")){//支付宝APP/H5
					Integer[] zfbStr={1,6};
					List<Integer> zfbList = Arrays.asList(zfbStr);
					combineOrderCriteria.andPaymentIdIn(zfbList);
				}
				if (paymentId.equals("3")){//微信公众号
					Integer[] wxStr2={4,7};
					List<Integer> wxList2 = Arrays.asList(wxStr2);
					combineOrderCriteria.andPaymentIdIn(wxList2);
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("financialStatus")) ){
				String financialStatus=request.getParameter("financialStatus");
				combineOrderCriteria.andFinancialStatusEqualTo(financialStatus);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("combineOrderCode"))){
				String combineOrderCode=request.getParameter("combineOrderCode");
				combineOrderCriteria.andCombineOrderCodeEqualTo(combineOrderCode);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ){
				String paymentNo=request.getParameter("paymentNo");
				combineOrderCriteria.andPaymentNoEqualTo(paymentNo);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("financialNo")) ){
				String financialNo=request.getParameter("financialNo");
				combineOrderCriteria.andFinancialNoEqualTo(financialNo);
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				combineOrderCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				combineOrderCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("pay_date_begin")) ){
				combineOrderCriteria.andPayDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("pay_date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("pay_date_end")) ){
				combineOrderCriteria.andPayDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("pay_date_end")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("register_date_begin")) ){
				combineOrderCriteria.andCollectionRegisterDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("register_date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("register_date_end")) ){
				combineOrderCriteria.andCollectionRegisterDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("register_date_end")+" 23:59:59"));
			}
			
			totalCount = combineOrderService.countCombineOrderCustomByExample(combineOrderExample);
			
			combineOrderExample.setLimitStart(page.getLimitStart());
			combineOrderExample.setLimitSize(page.getLimitSize());
			List<CombineOrderCustom> combineOrderCustoms=combineOrderService.selectCombineOrderCustomByExample(combineOrderExample);
		
			resMap.put("Rows", combineOrderCustoms);
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
	@RequestMapping("/receivables/combine/export.shtml")
	public void exportMchtSettleOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			CombineOrderExample combineOrderExample=new CombineOrderExample();
			CombineOrderExample.Criteria combineOrderCriteria=combineOrderExample.createCriteria();
			combineOrderCriteria.andDelFlagEqualTo("0");
			combineOrderCriteria.andStatusEqualTo("1");
			combineOrderExample.setOrderByClause("a.id desc");
			if(!StringUtil.isEmpty(request.getParameter("paymentId"))){
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("1")){//微信APP/H5
					Integer[] wxStr={2,5};
					List<Integer> wxList = Arrays.asList(wxStr);
					combineOrderCriteria.andPaymentIdIn(wxList);
				}
				if (paymentId.equals("2")){//支付宝APP/H5
					Integer[] zfbStr={1,6};
					List<Integer> zfbList = Arrays.asList(zfbStr);
					combineOrderCriteria.andPaymentIdIn(zfbList);
				}
				if (paymentId.equals("3")){//微信公众号
					combineOrderCriteria.andPaymentIdEqualTo(4);
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("financialStatus")) ){
				String financialStatus=request.getParameter("financialStatus");
				combineOrderCriteria.andFinancialStatusEqualTo(financialStatus);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("combineOrderCode"))){
				String combineOrderCode=request.getParameter("combineOrderCode");
				combineOrderCriteria.andCombineOrderCodeEqualTo(combineOrderCode);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ){
				String paymentNo=request.getParameter("paymentNo");
				combineOrderCriteria.andPaymentNoEqualTo(paymentNo);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("financialNo")) ){
				String financialNo=request.getParameter("financialNo");
				combineOrderCriteria.andFinancialNoEqualTo(financialNo);
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				combineOrderCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				combineOrderCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("pay_date_begin")) ){
				combineOrderCriteria.andPayDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("pay_date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("pay_date_end")) ){
				combineOrderCriteria.andPayDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("pay_date_end")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("register_date_begin")) ){
				combineOrderCriteria.andCollectionRegisterDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("register_date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("register_date_end")) ){
				combineOrderCriteria.andCollectionRegisterDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("register_date_end")+" 23:59:59"));
			}
			List<CombineOrderCustom> combineOrderCustoms=combineOrderService.selectCombineOrderCustomByExample(combineOrderExample);
			String[] titles = { "母订单号", "品牌/货号", "付款渠道","实付金额（元）","母订单状态","下单时间","付款时间","收款状态","收款编号","收款登记日期","收款人","支付交易号"};
			ExcelBean excelBean = new ExcelBean("导出收款母订单列表.xls",
					"导出收款母订单列表", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for(CombineOrderCustom combineOrderCustom:combineOrderCustoms){
				String[] data = {
						combineOrderCustom.getCombineOrderCode(),
						combineOrderCustom.getArtBrandGroup(),
						combineOrderCustom.getPaymentName()==null?"":combineOrderCustom.getPaymentName(),
						combineOrderCustom.getTotalPayAmount().toString(),
						combineOrderCustom.getStatusDesc(),
						dateFormat.format(combineOrderCustom.getCreateDate()),
						combineOrderCustom.getPayDate()==null?"":dateFormat.format(combineOrderCustom.getPayDate()),
						combineOrderCustom.getFinancialStatusDesc()== null?"":combineOrderCustom.getFinancialStatusDesc(),
						combineOrderCustom.getFinancialNo() == null?"":combineOrderCustom.getFinancialNo(),
						combineOrderCustom.getCollectionRegisterDate() == null? "" : df.format(combineOrderCustom.getCollectionRegisterDate()),
						combineOrderCustom.getFinancialStaffName()== null?"":combineOrderCustom.getFinancialStaffName(),
						combineOrderCustom.getPaymentNo()==null?"":"`"+combineOrderCustom.getPaymentNo()
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
	 * 母订单详情
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivables/combine/detail.shtml")
	public ModelAndView orderCombineDetail(HttpServletRequest request) {
		String rtPage = "/receivables/combine/detail";
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		int combineOrderId=Integer.valueOf(request.getParameter("id"));
		CombineOrderCustom combineOrderCustom=combineOrderService.selectCombineOrderCustomByPrimaryKey(combineOrderId);
		resMap.put("combineOrderCustom", combineOrderCustom);
		
		OrderDtlCustomExample orderDtlCustomExample=new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria=orderDtlCustomExample.createCriteria();
		orderDtlCustomCriteria.andDelFlagEqualTo("0");
		orderDtlCustomCriteria.andCombineOrderIdEqualTo(combineOrderId);
		orderDtlCustomCriteria.andMchtTypeEqualTo("1");
		orderDtlCustomExample.setOrderByClause("a.sub_order_id, a.id");
		
		List<OrderDtlCustom> orderDtlCustoms=orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
		resMap.put("orderDtlCustoms", orderDtlCustoms);
		
		OrderDtlCustomExample orderDtlCustomExample_P=new OrderDtlCustomExample();
		OrderDtlCustomExample.OrderDtlCustomCriteria orderDtlCustomCriteria_P=orderDtlCustomExample_P.createCriteria();
		orderDtlCustomCriteria_P.andDelFlagEqualTo("0");
		orderDtlCustomCriteria_P.andCombineOrderIdEqualTo(combineOrderId);
		orderDtlCustomCriteria_P.andMchtTypeEqualTo("2");
		orderDtlCustomExample_P.setOrderByClause("a.sub_order_id, a.id");
		
		List<OrderDtlCustom> orderDtlCustoms_P=orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample_P);
		resMap.put("orderDtlCustoms_P", orderDtlCustoms_P);
		
		List<OrderPreferentialInfoCustom> orderPreferentialInfoCustoms=orderPreferentialInfoService.selectOrderPreferentialInfoCustomByCombineOrder(combineOrderId);
		resMap.put("orderPreferentialInfoCustoms", orderPreferentialInfoCustoms);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 收款登记操作页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivables/combine/toRegister.shtml")
	public ModelAndView toRegister(HttpServletRequest request){
		String rtPage = "/receivables/combine/toRegister";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String idStr = request.getParameter("id");
		resMap.put("id", idStr);
		if(idStr.split(",").length>1){
			resMap.put("batch", 1);
		}else{
			resMap.put("batch", 0);
			int combineOrderId=Integer.valueOf(request.getParameter("id"));
			CombineOrder combineOrder=combineOrderService.selectByPrimaryKey(combineOrderId);
			if(combineOrder.getCollectionRegisterDate()==null){
				combineOrder.setCollectionRegisterDate(combineOrder.getPayDate());
			}
			resMap.put("combineOrder", combineOrder);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 收款登记操作
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivables/combine/register.shtml")
	@ResponseBody
	public Map<String, Object> register(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String financialStatus =request.getParameter("financialStatus");
			String collectionRegisterDate =request.getParameter("collectionRegisterDate");
			String financialNo =request.getParameter("financialNo");
			String idStr = request.getParameter("id");
			ReceiptRegisterDateLockExample example = new ReceiptRegisterDateLockExample();
			ReceiptRegisterDateLockExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andLockDateEqualTo(collectionRegisterDate);
			List<ReceiptRegisterDateLock> receiptRegisterDateLocks = receiptRegisterDateLockMapper.selectByExample(example);
			if(receiptRegisterDateLocks!=null && receiptRegisterDateLocks.size()>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "收款日期已被锁住，无法使用当前日期，请更换收款日期");
				return resMap;
			}
			if(idStr.split(",").length>1){
				String[] idsArray = idStr.split(",");
				for(int i=0;i<idsArray.length;i++){
					CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(Integer.parseInt(idsArray[i]));
					combineOrder.setFinancialStatus(financialStatus);
					combineOrder.setCollectionRegisterDate(DateUtil.getDateByFormat(collectionRegisterDate));
					combineOrder.setFinancialNo(financialNo);
					combineOrder.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					combineOrder.setUpdateDate(new Date());
					combineOrderService.updateByPrimaryKey(combineOrder);
				}
			}else{
				int combineOrderId=Integer.valueOf(request.getParameter("id"));
				CombineOrder combineOrder=combineOrderService.selectByPrimaryKey(combineOrderId);
				combineOrder.setFinancialStatus(financialStatus);
				combineOrder.setCollectionRegisterDate(DateUtil.getDateByFormat(collectionRegisterDate));
				combineOrder.setFinancialNo(financialNo);
				combineOrder.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				combineOrder.setUpdateDate(new Date());
				combineOrderService.updateByPrimaryKey(combineOrder);
			}
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 收款确认操作页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivables/combine/toConfirm.shtml")
	public ModelAndView toConfirm(HttpServletRequest request) {
		String rtPage = "/receivables/combine/toConfirm";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String idStr = request.getParameter("id");
		resMap.put("id", idStr);
		resMap.put("paymentId", request.getParameter("paymentId"));
		resMap.put("financialStatus", request.getParameter("financialStatus"));
		resMap.put("combineOrderCode", request.getParameter("combineOrderCode"));
		resMap.put("paymentNo", request.getParameter("paymentNo"));
		resMap.put("financialNo", request.getParameter("financialNo"));
		resMap.put("create_date_begin", request.getParameter("create_date_begin"));
		resMap.put("create_date_end", request.getParameter("create_date_end"));
		resMap.put("pay_date_begin", request.getParameter("pay_date_begin"));
		resMap.put("pay_date_end", request.getParameter("pay_date_end"));
		resMap.put("register_date_begin", request.getParameter("register_date_begin"));
		resMap.put("register_date_end", request.getParameter("register_date_end"));
		if(StringUtils.isEmpty(idStr) || idStr.split(",").length>1){
			resMap.put("batch", 1);
		}else{
			resMap.put("batch", 0);
			int combineOrderId=Integer.valueOf(request.getParameter("id"));
			CombineOrder combineOrder=combineOrderService.selectByPrimaryKey(combineOrderId);
			resMap.put("combineOrder", combineOrder);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 收款确认操作
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivables/combine/confirm.shtml")
	@ResponseBody
	public Map<String, Object> confirm(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String saveFinancialStatus =request.getParameter("saveFinancialStatus");
			String collectionRegisterDate =request.getParameter("collectionRegisterDate");
			String idStr = request.getParameter("id");
			ReceiptRegisterDateLockExample example = new ReceiptRegisterDateLockExample();
			ReceiptRegisterDateLockExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andLockDateEqualTo(collectionRegisterDate);
			List<ReceiptRegisterDateLock> receiptRegisterDateLocks = receiptRegisterDateLockMapper.selectByExample(example);
			if(receiptRegisterDateLocks!=null && receiptRegisterDateLocks.size()>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "收款日期已被锁住，无法使用当前日期，请更换收款日期");
				return resMap;
			}
			List<Integer> idsList = new ArrayList<Integer>();
			if(StringUtils.isEmpty(idStr) || idStr.split(",").length>1){
				if(StringUtils.isEmpty(idStr)){
					CombineOrderExample combineOrderExample=new CombineOrderExample();
					CombineOrderExample.Criteria combineOrderCriteria=combineOrderExample.createCriteria();
					combineOrderCriteria.andDelFlagEqualTo("0");
					combineOrderCriteria.andStatusEqualTo("1");
					combineOrderExample.setOrderByClause("a.id desc");
					if(!StringUtil.isEmpty(request.getParameter("paymentId"))){
						int paymentId=Integer.valueOf(request.getParameter("paymentId"));
						combineOrderCriteria.andPaymentIdEqualTo(paymentId);
					}
					
					if(!StringUtil.isEmpty(request.getParameter("financialStatus")) ){
						String financialStatus=request.getParameter("financialStatus");
						combineOrderCriteria.andFinancialStatusEqualTo(financialStatus);
					}
					
					if(!StringUtil.isEmpty(request.getParameter("combineOrderCode"))){
						String combineOrderCode=request.getParameter("combineOrderCode");
						combineOrderCriteria.andCombineOrderCodeEqualTo(combineOrderCode);
					}
					
					if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ){
						String paymentNo=request.getParameter("paymentNo");
						combineOrderCriteria.andPaymentNoEqualTo(paymentNo);
					}
					
					if(!StringUtil.isEmpty(request.getParameter("financialNo")) ){
						String financialNo=request.getParameter("financialNo");
						combineOrderCriteria.andFinancialNoEqualTo(financialNo);
					}
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
						combineOrderCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
					}
					
					if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
						combineOrderCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
					}
					
					if(!StringUtil.isEmpty(request.getParameter("pay_date_begin")) ){
						combineOrderCriteria.andPayDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("pay_date_begin")+" 00:00:00"));
					}
					
					if(!StringUtil.isEmpty(request.getParameter("pay_date_end")) ){
						combineOrderCriteria.andPayDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("pay_date_end")+" 23:59:59"));
					}
					
					if(!StringUtil.isEmpty(request.getParameter("register_date_begin")) ){
						combineOrderCriteria.andCollectionRegisterDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("register_date_begin")+" 00:00:00"));
					}
					
					if(!StringUtil.isEmpty(request.getParameter("register_date_end")) ){
						combineOrderCriteria.andCollectionRegisterDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("register_date_end")+" 23:59:59"));
					}
					List<CombineOrderCustom> combineOrderCustoms=combineOrderService.selectCombineOrderCustomByExample(combineOrderExample);
					for(CombineOrderCustom combineOrder:combineOrderCustoms){
						idsList.add(combineOrder.getId());
					}
				}else{
					String[] idsArray = idStr.split(",");
					for(int i=0;i<idsArray.length;i++){
						idsList.add(Integer.parseInt(idsArray[i]));
					}
				}
				HashMap<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("idsList", idsList);
				paramMap.put("financialStatus", saveFinancialStatus);
				paramMap.put("collectionRegisterDate", DateUtil.getDateByFormat(collectionRegisterDate));
				paramMap.put("updateBy", Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				paramMap.put("updateDate", new Date());
				combineOrderService.updateCombineOrders(paramMap);
			}else{
				int combineOrderId=Integer.valueOf(request.getParameter("id"));
				CombineOrder combineOrder=combineOrderService.selectByPrimaryKey(combineOrderId);
				combineOrder.setFinancialStatus(saveFinancialStatus);
				combineOrder.setCollectionRegisterDate(DateUtil.getDateByFormat(collectionRegisterDate));
				combineOrder.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				combineOrder.setUpdateDate(new Date());
				combineOrderService.updateByPrimaryKey(combineOrder);
			}
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 每日收款汇总
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivables/count/list.shtml")
	public ModelAndView countList(HttpServletRequest request) {
		String rtPage = "/receivables/count/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String pay_date_end = df.format(new Date());
		String pay_date_begin = pay_date_end.substring(0,7)+"-01";
		resMap.put("pay_date_end", pay_date_end);
		resMap.put("pay_date_begin", pay_date_begin);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 每日收款汇总列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivables/count/data.shtml")
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

			List<CombineOrderCustom> combineOrderCustoms = combineOrderService.receivablesCountEachDayList(paramMap);
			resMap.put("Rows", combineOrderCustoms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	public List<String> getBetweenDays(String stime,String etime){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date sdate=null;
        Date eDate=null;
        try {
             sdate=df.parse(stime);
             eDate=df.parse(etime);
        } catch (Exception e) {
              e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        List<String> list=new ArrayList<String>();
        while (sdate.getTime()<=eDate.getTime()) {
              list.add(df.format(sdate));
              c.setTime(sdate);
              c.add(Calendar.DATE, 1); // 日期加1天
              sdate = c.getTime();
              }
        return list;
  }
	
	/**
	 * 导出每日收款汇总excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/receivables/count/export.shtml")
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
			List<CombineOrderCustom> combineOrderCustoms = combineOrderService.receivablesCountEachDayList(paramMap);
			String[] titles = { "付款日期", "笔数", "母订单金额","微信APP/H5金额","支付宝金额","微信公众号金额","微信APP/H5笔数","支付宝笔数","微信公众号笔数","已确认金额","锁住日期","已登记金额","未处理金额","异常金额"};
			ExcelBean excelBean = new ExcelBean("导出每日收款汇总列表.xls",
					"导出每日收款汇总列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(CombineOrderCustom combineOrderCustom:combineOrderCustoms){
				String lockDesc = "";
				if(combineOrderCustom.getTotalCount()>0){
	            	if(!StringUtils.isEmpty(combineOrderCustom.getLockDate())){
	            		lockDesc = "已锁";	                		
                	}else{
                		lockDesc ="未锁";
                	}
            	}
				String[] data = {
						combineOrderCustom.getEachDay(),
						combineOrderCustom.getTotalCount() == null? "":combineOrderCustom.getTotalCount().toString(),
						combineOrderCustom.getCombineAmount() == null? "":combineOrderCustom.getCombineAmount().toString(),
						combineOrderCustom.getWxAmount() == null? "":combineOrderCustom.getWxAmount().toString(),
						combineOrderCustom.getZfbAmount() == null? "":combineOrderCustom.getZfbAmount().toString(),
						combineOrderCustom.getGzhAmount() == null? "":combineOrderCustom.getGzhAmount().toString(),
						combineOrderCustom.getWxCount() == null? "":combineOrderCustom.getWxCount().toString(),
						combineOrderCustom.getZfbCount() == null? "":combineOrderCustom.getZfbCount().toString(),
						combineOrderCustom.getGzhCount() == null? "":combineOrderCustom.getGzhCount().toString(),
						combineOrderCustom.getConfirmAmount() == null? "":combineOrderCustom.getConfirmAmount().toString(),
						lockDesc,
						combineOrderCustom.getRegisterAmount() == null? "":combineOrderCustom.getRegisterAmount().toString(),
						combineOrderCustom.getNoDealAmount() == null? "":combineOrderCustom.getNoDealAmount().toString(),
						combineOrderCustom.getUnusualAmount() == null? "":combineOrderCustom.getUnusualAmount().toString()
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
	 * 锁住收款日期操作页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivables/count/toLockDate.shtml")
	public ModelAndView toLockDate(HttpServletRequest request){
		String rtPage = "/receivables/count/toLockDate";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String lockDate = request.getParameter("lockDate");
		resMap.put("lockDate", lockDate);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 锁住收款日期
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/receivables/count/lockDate.shtml")
	@ResponseBody
	public Map<String, Object> lockDate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String lockDate = request.getParameter("lockDate");
			ReceiptRegisterDateLockExample receiptRegisterDateLockExample = new ReceiptRegisterDateLockExample();
			ReceiptRegisterDateLockExample.Criteria criteria = receiptRegisterDateLockExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andLockDateEqualTo(lockDate);
			List<ReceiptRegisterDateLock> receiptRegisterDateLocks = receiptRegisterDateLockMapper.selectByExample(receiptRegisterDateLockExample);
			if(receiptRegisterDateLocks!=null && receiptRegisterDateLocks.size()>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "当前日期已被锁住，收款不能用此日期");
				return resMap;
			}
			ReceiptRegisterDateLock receiptRegisterDateLock = new ReceiptRegisterDateLock();
			receiptRegisterDateLock.setCreateBy(this.getSessionStaffBean(request).getStaffID());
			receiptRegisterDateLock.setCreateDate(new Date());
			receiptRegisterDateLock.setDelFlag("0");
			receiptRegisterDateLock.setLockDate(lockDate);
			receiptRegisterDateLockMapper.insertSelective(receiptRegisterDateLock);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 收款母订单列表统计
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/receivables/combine/count.shtml")
	public ModelAndView count(HttpServletRequest request) throws ParseException {
		String rtPage = "/receivables/combine/count";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(request.getParameter("paymentId"))){
			int paymentId=Integer.valueOf(request.getParameter("paymentId"));
			paramMap.put("paymentId", paymentId);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("financialStatus")) ){
			String financialStatus=request.getParameter("financialStatus");
			paramMap.put("financialStatus", financialStatus);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("combineOrderCode"))){
			String combineOrderCode=request.getParameter("combineOrderCode");
			paramMap.put("combineOrderCode", combineOrderCode);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ){
			String paymentNo=request.getParameter("paymentNo");
			paramMap.put("paymentNo", paymentNo);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("financialNo")) ){
			String financialNo=request.getParameter("financialNo");
			paramMap.put("financialNo", financialNo);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
			paramMap.put("create_date_begin", request.getParameter("create_date_begin")+" 00:00:00");
		}
		
		if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
			paramMap.put("create_date_end", request.getParameter("create_date_end")+" 23:59:59");
		}
		
		if(!StringUtil.isEmpty(request.getParameter("pay_date_begin")) ){
			paramMap.put("pay_date_begin", request.getParameter("pay_date_begin")+" 00:00:00");
		}
		
		if(!StringUtil.isEmpty(request.getParameter("pay_date_end")) ){
			paramMap.put("pay_date_end", request.getParameter("pay_date_end")+" 23:59:59");
		}
		
		if(!StringUtil.isEmpty(request.getParameter("register_date_begin")) ){
			paramMap.put("register_date_begin", request.getParameter("register_date_begin")+" 00:00:00");
		}
		
		if(!StringUtil.isEmpty(request.getParameter("register_date_end")) ){
			paramMap.put("register_date_end", request.getParameter("register_date_end")+" 23:59:59");
		}
		List<Map<String, Object>> list = combineOrderService.receivablesCount(paramMap);
		if(list!=null && list.size()>0){
			resMap.put("result", list.get(0));
		}
		return new ModelAndView(rtPage,resMap);
	}
}
