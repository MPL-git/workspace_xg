package com.jf.controller;

import com.jf.bean.ExcelBean;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtil;
import com.jf.entity.*;
import com.jf.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/interventionOrder")
public class InterventionOrderController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(InterventionOrderController.class);

	@Resource
	private SysStatusService sysStatusService;
	@Resource
	private InterventionOrderService interventionOrderService;
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	@Resource
	private OrderDtlService orderDtlService;
	@Resource
	private ProductService productService;
	@Resource
	private InterventionProcessService interventionProcessService;
	@Resource
	private InterventionProcessPicService interventionProcessPicService;
	@Resource
	private RefundOrderService refundOrderService;
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 平台介入单
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/interventionOrderIndex")
	public String interventionOrderIndex(Model model, HttpServletRequest request) {
		List<SysStatus> reasonList = DataDicUtil.getStatusList("BU_INTERVENTION_ORDER", "REASON");
		List<SysStatus> statusList = DataDicUtil.getStatusList("BU_INTERVENTION_ORDER", "STATUS");
		model.addAttribute("reasonList", reasonList);
		model.addAttribute("statusList", statusList);
		model.addAttribute("searchOrderType", request.getParameter("searchOrderType"));
		return "interventionOrder/interventionOrderIndex";
	}
	
	/**
	 * 平台介入单数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getInterventionOrderList")
	@ResponseBody
	public ResponseMsg getInterventionOrderList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		InterventionOrderCustomExample ioce = new InterventionOrderCustomExample();
		InterventionOrderCustomExample.InterventionOrderCustomCriteria iocc = ioce.createCriteria();
		ioce.setOrderByClause("t.id desc");
		iocc.andDelFlagEqualTo("0");
		iocc.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		String searchOrderType = request.getParameter("searchOrderType");
		String searchInterventionCode = request.getParameter("search_interventionCode");
		String searchCustomerServiceOrderCode = request.getParameter("search_customerServiceOrderCode");
		String searchReason = request.getParameter("search_reason");
		String searchContacts = request.getParameter("search_contacts");
		String searchStatus = request.getParameter("search_status");
		if(!StringUtil.isEmpty(searchOrderType) && !searchOrderType.equals("0")){
			if(searchOrderType.equals("1")){//待申诉
				iocc.andStatusEqualTo("4");
			}else if(searchOrderType.equals("2")){//申诉中
				iocc.andStatusEqualTo("5");
			}else if(searchOrderType.equals("3")){
				iocc.andStatusEqualTo("6");
			}
		}
		if(!StringUtil.isEmpty(searchInterventionCode)){
			iocc.andInterventionCodeEqualTo(searchInterventionCode.trim());
		}
		if(!StringUtil.isEmpty(searchCustomerServiceOrderCode)){
			iocc.andCustomerServiceOrderCodeEqualTo(searchCustomerServiceOrderCode.trim());
		}
		if(!StringUtil.isEmpty(searchReason)){
			iocc.andReasonEqualTo(searchReason);
		}
		if(!StringUtil.isEmpty(searchContacts)){
			iocc.andContactsEqualTo(searchContacts.trim());
		}
		if(!StringUtil.isEmpty(searchStatus)){
			if(searchStatus.equals("81")){
				iocc.andStatusEqualTo("8");
				iocc.andWinTypeEqualTo("1");
			}else if(searchStatus.equals("82")){
				iocc.andStatusEqualTo("8");
				iocc.andWinTypeEqualTo("2");
			}else{
				iocc.andStatusEqualTo(searchStatus);
			}
		}
		int totalCount = interventionOrderService.countInterventionOrderCustomByExample(ioce);
		ioce.setLimitStart(page.getLimitStart());
		ioce.setLimitSize(page.getLimitSize());
		List<InterventionOrderCustom> interventionOrderCustoms = interventionOrderService.selectInterventionOrderCustomByExample(ioce);
		returnData.put("Rows", interventionOrderCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 介入单详情
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/interventionOrderView")
	public String interventionOrderView(Model model,HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		InterventionOrder interventionOrder = interventionOrderService.selectByPrimaryKey(id);
		model.addAttribute("interventionOrder", interventionOrder);//介入信息
		InterventionProcessExample ipe = new InterventionProcessExample();
		ipe.setOrderByClause("id asc");
		InterventionProcessExample.Criteria ipec = ipe.createCriteria();
		ipec.andDelFlagEqualTo("0");
		ipec.andInterventionOrderIdEqualTo(id);
		ipec.andOperatorTypeEqualTo("1");//1.客户
		ipec.andProcessTypeEqualTo("1");//1.申诉
		List<InterventionProcess> interventionProcessList = interventionProcessService.selectByExample(ipe);
		if(interventionProcessList!=null && interventionProcessList.size()>0){
			InterventionProcess interventionProcess = interventionProcessList.get(0);
			model.addAttribute("customerContent", interventionProcess.getContent());
			InterventionProcessPicExample ippe = new InterventionProcessPicExample();
			InterventionProcessPicExample.Criteria ippec = ippe.createCriteria();
			ippec.andDelFlagEqualTo("0");
			ippec.andInterventionProcessIdEqualTo(interventionProcess.getId());
			List<InterventionProcessPic> interventionProcessPicList = interventionProcessPicService.selectByExample(ippe);
			model.addAttribute("customerInterventionProcessPicList", interventionProcessPicList);//客户凭证
		}
		CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(interventionOrder.getServiceOrderId());
		model.addAttribute("customerServiceOrderCode", customerServiceOrder.getOrderCode());//售后单号
		model.addAttribute("proStatusDesc", DataDicUtil.getStatusDesc("BU_CUSTOMER_SERVICE_ORDER", "PRO_STATUS", customerServiceOrder.getProStatus()));//售后进度状态
		RefundOrderExample roe = new RefundOrderExample();
		RefundOrderExample.Criteria roec = roe.createCriteria();
		roec.andDelFlagEqualTo("0");
		roec.andServiceOrderIdEqualTo(customerServiceOrder.getId());
		List<RefundOrder> refundOrders = refundOrderService.selectByExample(roe);
		if(refundOrders!=null && refundOrders.size()>0){
			model.addAttribute("refundAmount", refundOrders.get(0).getRefundAmount());//退款金额
		}
		OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(customerServiceOrder.getOrderDtlId());
		model.addAttribute("orderDtl", orderDtl);//相关商品信息
		Product product = productService.selectByPrimaryKey(orderDtl.getProductId());
		model.addAttribute("productCode", product.getCode());
		//商家申诉
		InterventionProcessExample mchtIpe = new InterventionProcessExample();
		mchtIpe.setOrderByClause("id asc");
		InterventionProcessExample.Criteria mchtIpec = mchtIpe.createCriteria();
		mchtIpec.andDelFlagEqualTo("0");
		mchtIpec.andInterventionOrderIdEqualTo(id);
		mchtIpec.andOperatorTypeEqualTo("2");//2.商家 
		mchtIpec.andProcessTypeEqualTo("1");//1.申诉
		List<InterventionProcess> mchtInterventionProcessList = interventionProcessService.selectByExample(mchtIpe);
		if(mchtInterventionProcessList!=null && mchtInterventionProcessList.size()>0){
			InterventionProcess mchtInterventionProcess = mchtInterventionProcessList.get(0);
			model.addAttribute("mchtContent", mchtInterventionProcess.getContent());
			InterventionProcessPicExample mchtIppe = new InterventionProcessPicExample();
			InterventionProcessPicExample.Criteria mchtIppec = mchtIppe.createCriteria();
			mchtIppec.andDelFlagEqualTo("0");
			mchtIppec.andInterventionProcessIdEqualTo(mchtInterventionProcess.getId());
			List<InterventionProcessPic> mchtInterventionProcessPicList = interventionProcessPicService.selectByExample(mchtIppe);
			model.addAttribute("mchtInterventionProcessPicList", mchtInterventionProcessPicList);
		}
		//商家协商内容
		InterventionProcessExample example = new InterventionProcessExample();
		example.setOrderByClause("id desc");
		example.setLimitStart(0);
		example.setLimitSize(2);
		InterventionProcessExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andInterventionOrderIdEqualTo(id);
		criteria.andOperatorTypeEqualTo("2");//2.商家 
		criteria.andProcessTypeEqualTo("2");//2.留言
		List<InterventionProcessCustom> interventionProcessCustoms = interventionProcessService.selectInterventionProcessCustomByExample(example);
		for(InterventionProcessCustom interventionProcessCustom:interventionProcessCustoms){
			InterventionProcessPicExample mchtIppe = new InterventionProcessPicExample();
			InterventionProcessPicExample.Criteria mchtIppec = mchtIppe.createCriteria();
			mchtIppec.andDelFlagEqualTo("0");
			mchtIppec.andInterventionProcessIdEqualTo(interventionProcessCustom.getId());
			List<InterventionProcessPic> picList = interventionProcessPicService.selectByExample(mchtIppe);
			interventionProcessCustom.setInterventionProcessPics(picList);
		}
		model.addAttribute("interventionProcessCustoms", interventionProcessCustoms);
		//处理凭证记录
		InterventionProcessExample proofExample = new InterventionProcessExample();
		proofExample.setOrderByClause("id desc");
		proofExample.setLimitStart(0);
		proofExample.setLimitSize(2);
		InterventionProcessExample.Criteria proofCriteria = proofExample.createCriteria();
		proofCriteria.andDelFlagEqualTo("0");
		proofCriteria.andInterventionOrderIdEqualTo(id);
		proofCriteria.andOperatorTypeNotEqualTo("1");//2.商家 3.平台
		proofCriteria.andProcessTypeEqualTo("3");//3.处理凭证
		List<InterventionProcessCustom> proofInterventionProcessCustoms = interventionProcessService.selectInterventionProcessCustomByExample(proofExample);
		for(InterventionProcessCustom interventionProcessCustom:proofInterventionProcessCustoms){
			if(interventionProcessCustom.getOperatorType().equals("2")){//2.商家
				InterventionProcessPicExample proofIppe = new InterventionProcessPicExample();
				InterventionProcessPicExample.Criteria proofIppec = proofIppe.createCriteria();
				proofIppec.andDelFlagEqualTo("0");
				proofIppec.andInterventionProcessIdEqualTo(interventionProcessCustom.getId());
				List<InterventionProcessPic> picList = interventionProcessPicService.selectByExample(proofIppe);
				interventionProcessCustom.setInterventionProcessPics(picList);
			}
		}
		model.addAttribute("proofInterventionProcessCustoms", proofInterventionProcessCustoms);
		if(!StringUtil.isEmpty(interventionOrder.getMchtResultReason())){
			model.addAttribute("mchtResultReasonDesc", DataDicUtil.getStatusDesc("BU_INTERVENTION_ORDER", "MCHT_RESULT_REASON", interventionOrder.getMchtResultReason()));
		}
		return "interventionOrder/interventionOrderView";
	}
	
	/**
	 * 商家不同意申请,商家留言
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/saveInterventionProcess")
	@ResponseBody
	public ResponseMsg saveInterventionProcess(HttpServletRequest request) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String interventionOrderId = request.getParameter("interventionOrderId");
		String status = request.getParameter("status");
		String operatorType = request.getParameter("operatorType");
		String processType = request.getParameter("processType");
		String content = request.getParameter("content");
		String imgs = request.getParameter("imgs");
		InterventionOrder interventionOrder = interventionOrderService.selectByPrimaryKey(Integer.parseInt(interventionOrderId));
		Date now = new Date();
		if(!StringUtil.isEmpty(status)){
			interventionOrder.setStatus(status);
			interventionOrder.setUpdateDate(now);
		}
		InterventionProcess interventionProcess = new InterventionProcess();
		interventionProcess.setDelFlag("0");
		interventionProcess.setCreateBy(this.getSessionMchtInfo(request).getId());
		interventionProcess.setCreateDate(now);
		interventionProcess.setInterventionOrderId(interventionOrder.getId());
		interventionProcess.setOperatorType(operatorType);
		interventionProcess.setProcessType(processType);
		interventionProcess.setContent(content);
		List<InterventionProcessPic> interventionProcessPics = new ArrayList<InterventionProcessPic>();
		if(!StringUtil.isEmpty(imgs)){
			String[] imgsArray = imgs.split(",");
			for(int i=0;i<imgsArray.length;i++){
				InterventionProcessPic ipp = new InterventionProcessPic();
				ipp.setDelFlag("0");
				ipp.setCreateBy(this.getSessionMchtInfo(request).getId());
				ipp.setCreateDate(now);
				ipp.setPic(imgsArray[i]);
				interventionProcessPics.add(ipp);
			}
		}
		InterventionOrderStatusLog iosl = null;
		if(!StringUtil.isEmpty(status)){
			iosl = new InterventionOrderStatusLog();
			iosl.setDelFlag("0");
			iosl.setCreateBy(this.getSessionMchtInfo(request).getId());
			iosl.setCreateDate(now);
			iosl.setInterventionOrderId(interventionOrder.getId());
			iosl.setStatus(status);
			if(status.equals("5")){//不接受，申诉中
				iosl.setRemarks("商家不同意买家申请，状态变更为商家申诉中（平台待评判）");
			}else if(status.equals("6")){//接受，待上传凭证
				iosl.setRemarks("商家同意买家申请，状态变更为待商家上传凭证（买家胜诉）");
			}else if(status.equals("7")){//已上传凭证，待结案
				iosl.setRemarks("商家已上传凭证，状态变更为待结案");
			}
		}
		interventionProcessService.save(interventionOrder,interventionProcess,interventionProcessPics,iosl);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 商家同意申请
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/saveInterventionOrder")
	@ResponseBody
	public ResponseMsg saveInterventionOrder(HttpServletRequest request) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String interventionOrderId = request.getParameter("interventionOrderId");
		String status = request.getParameter("status");
		String winType = request.getParameter("winType");
		InterventionOrder interventionOrder = interventionOrderService.selectByPrimaryKey(Integer.parseInt(interventionOrderId));
		Date now = new Date();
		interventionOrder.setStatus(status);
		interventionOrder.setWinType(winType);
		interventionOrder.setUpdateDate(now);
		interventionOrder.setClientResultReason("3");
		interventionOrder.setMchtResultReason("3");
		InterventionOrderStatusLog iosl = new InterventionOrderStatusLog();
		iosl.setDelFlag("0");
		iosl.setCreateBy(this.getSessionMchtInfo(request).getId());
		iosl.setCreateDate(now);
		iosl.setInterventionOrderId(interventionOrder.getId());
		iosl.setStatus(status);
		iosl.setRemarks("商家同意买家申请，状态变更为待商家上传凭证");
		interventionOrderService.updateInterventionOrder(interventionOrder,iosl);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 查看更多
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/showMore")
	@ResponseBody
	public ResponseMsg showMore(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String interventionOrderId = request.getParameter("interventionOrderId");
		String processType = request.getParameter("processType");
		InterventionProcessExample example = new InterventionProcessExample();
		example.setOrderByClause("t.id desc");
		InterventionProcessExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andInterventionOrderIdEqualTo(Integer.parseInt(interventionOrderId));
		c.andProcessTypeEqualTo(processType);
		c.andOperatorTypeEqualTo("2");//2.商家
		List<InterventionProcessCustom> interventionProcessCustoms = interventionProcessService.selectInterventionProcessCustomByExample(example);
		for(InterventionProcessCustom interventionProcessCustom:interventionProcessCustoms){
			InterventionProcessPicExample mchtIppe = new InterventionProcessPicExample();
			InterventionProcessPicExample.Criteria mchtIppec = mchtIppe.createCriteria();
			mchtIppec.andDelFlagEqualTo("0");
			mchtIppec.andInterventionProcessIdEqualTo(interventionProcessCustom.getId());
			List<InterventionProcessPic> picList = interventionProcessPicService.selectByExample(mchtIppe);
			interventionProcessCustom.setInterventionProcessPics(picList);
		}
		returnData.put("interventionProcessCustoms", interventionProcessCustoms);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 查看凭证(用户介入单申诉图片)
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/showProof")
	@ResponseBody
	public ResponseMsg showProof(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String interventionOrderId = request.getParameter("interventionOrderId");
		String processType = request.getParameter("processType");
		InterventionProcessExample example = new InterventionProcessExample();
		example.setOrderByClause("id asc");
		InterventionProcessExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andInterventionOrderIdEqualTo(Integer.parseInt(interventionOrderId));
		c.andProcessTypeEqualTo(processType);
		c.andOperatorTypeEqualTo("1");//1.客户
		List<InterventionProcess> interventionProcessList = interventionProcessService.selectByExample(example);
		if(interventionProcessList!=null && interventionProcessList.size()>0){
			InterventionProcess interventionProcess = interventionProcessList.get(0);
			InterventionProcessPicExample ippe = new InterventionProcessPicExample();
			InterventionProcessPicExample.Criteria ippec = ippe.createCriteria();
			ippec.andDelFlagEqualTo("0");
			ippec.andInterventionProcessIdEqualTo(interventionProcess.getId());
			List<InterventionProcessPic> picList = interventionProcessPicService.selectByExample(ippe);
			returnData.put("interventionProcessPics", picList);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 导出订单excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/export")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		InterventionOrderCustomExample ioce = new InterventionOrderCustomExample();
		InterventionOrderCustomExample.InterventionOrderCustomCriteria iocc = ioce.createCriteria();
		ioce.setOrderByClause("t.id desc");
		iocc.andDelFlagEqualTo("0");
		iocc.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		String searchOrderType = request.getParameter("searchOrderType");
		String searchInterventionCode = request.getParameter("search_interventionCode");
		String searchCustomerServiceOrderCode = request.getParameter("search_customerServiceOrderCode");
		String searchReason = request.getParameter("search_reason");
		String searchContacts = request.getParameter("search_contacts");
		String searchStatus = request.getParameter("search_status");
		if(!StringUtil.isEmpty(searchOrderType) && !searchOrderType.equals("0")){
			if(searchOrderType.equals("1")){//待申诉
				iocc.andStatusEqualTo("4");
			}else if(searchOrderType.equals("2")){//申诉中
				iocc.andStatusEqualTo("5");
			}else if(searchOrderType.equals("3")){
				iocc.andStatusEqualTo("6");
			}
		}
		if(!StringUtil.isEmpty(searchInterventionCode)){
			iocc.andInterventionCodeEqualTo(searchInterventionCode);
		}
		if(!StringUtil.isEmpty(searchCustomerServiceOrderCode)){
			iocc.andCustomerServiceOrderCodeEqualTo(searchCustomerServiceOrderCode);
		}
		if(!StringUtil.isEmpty(searchReason)){
			iocc.andReasonEqualTo(searchReason);
		}
		if(!StringUtil.isEmpty(searchContacts)){
			iocc.andContactsEqualTo(searchContacts);
		}
		if(!StringUtil.isEmpty(searchStatus)){
			if(searchStatus.equals("81")){
				iocc.andStatusEqualTo("8");
				iocc.andWinTypeEqualTo("1");
			}else if(searchStatus.equals("82")){
				iocc.andStatusEqualTo("8");
				iocc.andWinTypeEqualTo("2");
			}else{
				iocc.andStatusEqualTo(searchStatus);
			}
		}
		List<InterventionOrderCustom> interventionOrderCustoms = interventionOrderService.selectInterventionOrderCustomByExample(ioce);
		String[] titles = { "介入申请时间", "介入单号", "售后单号","介入原因","联系人","联系人电话","申诉状态","剩余处理时间"};
		ExcelBean excelBean = new ExcelBean("导出介入单" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls",
				"导出介入单", titles);
		List<String[]> datas = new ArrayList<>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数  
		long nh = 1000 * 60 * 60;// 一小时的毫秒数  
		long nm = 1000 * 60;// 一分钟的毫秒数  
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		for (InterventionOrderCustom interventionOrderCustom : interventionOrderCustoms) {
			String statusDesc = "";
			if(interventionOrderCustom.getStatus().equals("1")){
				statusDesc = "平台未受理";
			}else if(interventionOrderCustom.getStatus().equals("4")){
				statusDesc = "待申诉";
        	}else if(interventionOrderCustom.getStatus().equals("5")){
        		statusDesc = "申诉中";
        	}else if(interventionOrderCustom.getStatus().equals("6")){
        		statusDesc = "待上传凭证";
        	}else if(interventionOrderCustom.getStatus().equals("8")){
        		if(interventionOrderCustom.getWinType() == "1"){//买家胜诉
        			statusDesc = "申诉失败";
        		}else if(interventionOrderCustom.getWinType() == "2"){//商家胜诉
        			statusDesc = "申诉成功";
        		}
        	}
			String dealTime = "";
			if(interventionOrderCustom.getStatus().equals("4") || interventionOrderCustom.getStatus().equals("6")){
				Date now = new Date();
				Date end = DateUtil.addDay(interventionOrderCustom.getUpdateDate(), 2);
				if(end.after(now)){
					diff = end.getTime()-now.getTime();
					day = diff / nd;// 计算差多少天  
					hour = diff % nd / nh + day * 24;// 计算差多少小时  
					min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟 
					dealTime=day+"天"+(hour-day*24)+"小时"+(min-day * 24 * 60)+"分钟";
				}else{
					dealTime = "已超时";
				}
			}
			String[] data = {
				sf.format(interventionOrderCustom.getCreateDate()),
				interventionOrderCustom.getInterventionCode(),
				interventionOrderCustom.getCustomerServiceOrderCode(),
				interventionOrderCustom.getReasonDesc(),
				interventionOrderCustom.getContacts(),
				interventionOrderCustom.getTel(),
				statusDesc,
				dealTime
			};
			datas.add(data);
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
	}
}
