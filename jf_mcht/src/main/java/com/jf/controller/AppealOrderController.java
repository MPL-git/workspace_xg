package com.jf.controller;

import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/appealOrder")
public class AppealOrderController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(AppealOrderController.class);
	
	@Resource
	private AppealOrderService appealOrderService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private AppealLogService appealLogService;
	
	@Resource
	private AppealLogPicService appealLogPicService;
	
	@Resource
	private OrderPreferentialInfoService orderPreferentialInfoService;
	
	@Resource
	private CombineOrderService combineOrderService;
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 我收到的投诉
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/appealOrderIndex")
	public String appealOrderIndex(Model model, HttpServletRequest request) {
		model.addAttribute("appealOrderAppealTypeList", DataDicUtil.getStatusList("BU_APPEAL_ORDER", "APPEAL_TYPE"));
		model.addAttribute("appealOrderStatusList", DataDicUtil.getStatusList("BU_APPEAL_ORDER", "STATUS"));
		String status = request.getParameter("status");
		if(StringUtil.isEmpty(status)){
			status = "-1";
		}
		model.addAttribute("status", status);
		return "appealOrder/appealOrderIndex";
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/getAppealOrderList")
	@ResponseBody
	public ResponseMsg getAppealOrderList(HttpServletRequest request, Page page) throws ParseException {
		Map<String, Object> returnData = new HashMap<String, Object>();
		AppealOrderCustomExample appealOrderCustomExample = new AppealOrderCustomExample();
		AppealOrderCustomExample.AppealOrderCustomCriteria appealOrderCustomCriteria = appealOrderCustomExample.createCriteria();
		appealOrderCustomCriteria.andMchtIdEqualTo(this.getMchtInfo().getId());
		if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
			appealOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
		}else{
			appealOrderCustomCriteria.andDelFlagEqualTo("0");
		}
		appealOrderCustomExample.setOrderByClause("t.update_date desc");
		String searchOrderCode = request.getParameter("search_orderCode");
		String searchSubOrderCode = request.getParameter("search_subOrderCode");
		String searchAppealType = request.getParameter("search_appealType");
		String searchStatus = request.getParameter("search_status");
		String createTimeBegin = request.getParameter("createTimeBegin");
		String createTimeEnd = request.getParameter("createTimeEnd");
		if(!StringUtil.isEmpty(searchOrderCode)){
			appealOrderCustomCriteria.andOrderCodeEqualTo(searchOrderCode.trim());
		}
		if(!StringUtil.isEmpty(searchAppealType)){
			appealOrderCustomCriteria.andAppealTypeEqualTo(searchAppealType);
		}
		if(!StringUtil.isEmpty(searchSubOrderCode)){
			appealOrderCustomCriteria.andSubOrderCodeEqualTo(searchSubOrderCode.trim());
		}
		if(!StringUtil.isEmpty(searchStatus) && !searchStatus.equals("-1")){
			appealOrderCustomCriteria.andStatusEqualTo(searchStatus);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!StringUtil.isEmpty(createTimeBegin)){
			appealOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(createTimeBegin+" 00:00:00"));
		}
		if(!StringUtil.isEmpty(createTimeEnd)){
			appealOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(createTimeEnd+" 00:00:00"));
		}
		int totalCount = appealOrderService.countAppealOrderCustomByExample(appealOrderCustomExample);
		appealOrderCustomExample.setLimitStart(page.getLimitStart());
		appealOrderCustomExample.setLimitSize(page.getLimitSize());
		List<AppealOrderCustom> appealOrderCustoms = appealOrderService.selectAppealOrderCustomByExample(appealOrderCustomExample);
		returnData.put("Rows", appealOrderCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);

	}
	
	/**
	 * 投诉详情
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/appealOrderView")
	public String appealOrderView(Model model,HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		AppealOrder appealOrder = appealOrderService.selectByPrimaryKey(id);
		OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(appealOrder.getOrderDtlId());
		//TODO 根据orderDtlId获取订单优惠信息
		List<OrderPreferentialInfo> orderPreferentialInfos = orderPreferentialInfoService.getOrderPreferentialInfosByOrderDtlId(appealOrder.getOrderDtlId());
		String totalPreferentialInfoDesc="";
		for(OrderPreferentialInfo orderPreferentialInfo : orderPreferentialInfos){
			String preferentialTypeDesc = DataDicUtil.getStatusDesc("BU_ORDER_PREFERENTIAL_INFO", "PREFERENTIAL_TYPE", orderPreferentialInfo.getPreferentialType());
			totalPreferentialInfoDesc+=preferentialTypeDesc+":"+orderPreferentialInfo.getPreferentialAmount()+"元"+"<br>";
		}
		AppealLogCustomExample appealLogCustomExample = new AppealLogCustomExample();
		AppealLogCustomExample.AppealLogCustomCriteria appealLogCustomCriteria = appealLogCustomExample.createCriteria();
		appealLogCustomCriteria.andAppealOrderIdEqualTo(id);
		appealLogCustomCriteria.andDelFlagEqualTo("0");
		List<AppealLogCustom> appealLogCustoms = appealLogService.selectByAppealLogCustomExample(appealLogCustomExample);
		for(AppealLogCustom appealLogCustom:appealLogCustoms){
			List<String> pics = appealLogPicService.getPicsByAppealLogId(appealLogCustom.getId());
			appealLogCustom.setPics(pics);
		}
		SubOrder subOrder = subOrderService.selectByPrimaryKey(appealOrder.getSubOrderId());
		String appealTypeDesc = DataDicUtil.getStatusDesc("BU_APPEAL_ORDER", "APPEAL_TYPE", appealOrder.getAppealType());
		String statusDesc = DataDicUtil.getStatusDesc("BU_APPEAL_ORDER", "STATUS", appealOrder.getStatus());
		if(!StringUtil.isEmpty(appealOrder.getLiability())){
			String liabilityDesc = DataDicUtil.getStatusDesc("BU_APPEAL_ORDER", "LIABILITY", appealOrder.getLiability());
			model.addAttribute("liabilityDesc", liabilityDesc);
		}
		model.addAttribute("mchtShortName", this.getMchtInfo().getShortName());
		model.addAttribute("appealOrder", appealOrder);
		model.addAttribute("orderDtl", orderDtl);
		model.addAttribute("totalPreferentialInfoDesc", totalPreferentialInfoDesc);
		if(subOrder!=null){
			model.addAttribute("subOrderCode", subOrder.getSubOrderCode());
		}
		model.addAttribute("appealLogCustoms", appealLogCustoms);
		model.addAttribute("statusDesc", statusDesc);
		model.addAttribute("appealTypeDesc", appealTypeDesc);
		return "appealOrder/appealOrderView";
	}
	
	/**
	 * 
	 * 保存投诉流程日志
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveAppealLog")
	@ResponseBody
	public ResponseMsg saveAppealLog(HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Integer appealOrderId = Integer.parseInt(request.getParameter("appealOrderId"));
		String userType = request.getParameter("userType");
		String content = request.getParameter("content");
		String imgs = request.getParameter("imgs");
		AppealOrder appealOrder = appealOrderService.selectByPrimaryKey(appealOrderId);
		appealOrder.setStatus("1");
		appealOrder.setUpdateDate(new Date());
		AppealLog appealLog = new AppealLog();
		appealLog.setCreateDate(new Date());
		appealLog.setCreateBy(this.getMchtInfo().getId());
		appealLog.setUserId(this.getMchtInfo().getId());
		appealLog.setUserType(userType);
		appealLog.setAppealOrderId(appealOrderId);
		appealLog.setUserName(this.getMchtInfo().getShortName());
		appealLog.setContent(content);
		appealLog.setDelFlag("0");
		List<AppealLogPic> appealLogPics = new ArrayList<AppealLogPic>();
		if(!StringUtil.isEmpty(imgs)){
			String[] imgsArray = imgs.split(",");
			for(int i=0;i<imgsArray.length;i++){
				AppealLogPic appealLogPic = new AppealLogPic();
				appealLogPic.setCreateBy(this.getMchtInfo().getId());
				appealLogPic.setCreateDate(new Date());
				appealLogPic.setDelFlag("0");
				appealLogPic.setPic(imgsArray[i]);
				appealLogPics.add(appealLogPic);
			}
			returnData.put("imgs", imgs);
		}
		
		SysAppMessage sysAppMessage = new SysAppMessage();
//		sysAppMessage.setCreateBy(this.getMchtInfo().getId());
//		sysAppMessage.setCreateDate(new Date());
//		sysAppMessage.setDelFlag("0");
//		sysAppMessage.setType(SysAppMessageCustom.TYPE_WULIU);
//		sysAppMessage.setTitle(SysAppMessageCustom.TITLE_APPEAL);
//		sysAppMessage.setContent("您的投诉有新的回复");
//		sysAppMessage.setExpressNo("");
//		sysAppMessage.setLinkType(SysAppMessageCustom.LINKTYPE_APPEAL_ORDER);
//		sysAppMessage.setLinkId(appealOrderId);
		SubOrder subOrder = subOrderService.selectByPrimaryKey(appealOrder.getSubOrderId());
		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
//		sysAppMessage.setMemberId(combineOrder.getMemberId());
		
		appealLogService.saveAppealLog(appealLog,appealOrder,appealLogPics,sysAppMessage);
		returnData.put("appealLogCreateDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(appealLog.getCreateDate()));
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
	}
	
	/**
	 * 
	 * 申请客服介入
	 * @param request
	 * @return
	 */
	@RequestMapping("/applyIntervention")
	@ResponseBody
	public ResponseMsg applyIntervention(HttpServletRequest request) {
		Integer appealOrderId = Integer.parseInt(request.getParameter("appealOrderId"));
		String serviceSponsorType = request.getParameter("serviceSponsorType");
		String serviceStatus = request.getParameter("serviceStatus");
		String mchtRemarks = request.getParameter("mchtRemarks");
		AppealOrder appealOrder = appealOrderService.selectByPrimaryKey(appealOrderId);
		appealOrder.setServiceSponsorType(serviceSponsorType);
		appealOrder.setServiceStatus(serviceStatus);
		appealOrder.setMchtRemarks(mchtRemarks);
		appealOrder.setUpdateDate(new Date());
		
		AppealLog appealLog = new AppealLog();
		appealLog.setIsAppShow("1");
		appealLog.setCreateDate(new Date());
		appealLog.setCreateBy(this.getMchtInfo().getId());
		appealLog.setUserId(this.getMchtInfo().getId());
		appealLog.setUserType("3");
		appealLog.setAppealOrderId(appealOrderId);
		appealLog.setUserName(this.getMchtInfo().getShortName());
		String content = "<span style='color:red;'>申请客服介入</span>";
		if(!StringUtil.isEmpty(mchtRemarks)){
			content+="【"+mchtRemarks+"】";
		}
		appealLog.setContent(content);
		appealLog.setDelFlag("0");
		
		appealOrderService.updateAppealOrder(appealOrder,appealLog);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
	}
	
}
