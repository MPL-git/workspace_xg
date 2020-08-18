package com.jf.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.Activity;
import com.jf.entity.AppealLog;
import com.jf.entity.AppealLogCustom;
import com.jf.entity.AppealLogCustomExample;
import com.jf.entity.AppealOrder;
import com.jf.entity.AppealOrderCustom;
import com.jf.entity.AppealOrderCustomExample;
import com.jf.entity.AppealOrderExample;
import com.jf.entity.AppealPlatformRemarksPic;
import com.jf.entity.AppealPlatformRemarksPicExample;
import com.jf.entity.Attachment;
import com.jf.entity.AttachmentHistory;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderExample;
import com.jf.entity.InterventionOrderCustom;
import com.jf.entity.InterventionOrderCustomExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderPreferentialInfo;
import com.jf.entity.OrderPreferentialInfoExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderCustom;
import com.jf.entity.SubOrderExample;
import com.jf.entity.SysOrganization;
import com.jf.entity.SysOrganizationExample;
import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffInfoExample;
import com.jf.entity.SysStaffProductTypeCustom;
import com.jf.entity.SysStaffProductTypeCustomExample;
import com.jf.entity.ViolateOrderExample;
import com.jf.entity.WoRk;
import com.jf.entity.WoRkExample;
import com.jf.entity.WorkHistory;
import com.jf.entity.WorkRecord;
import com.jf.service.ActivityService;
import com.jf.service.AppealLogPicService;
import com.jf.service.AppealLogService;
import com.jf.service.AppealOrderService;
import com.jf.service.AppealPlatformRemarksPicService;
import com.jf.service.AttachmentHistoryService;
import com.jf.service.AttachmentService;
import com.jf.service.CustomerServiceOrderService;
import com.jf.service.InterventionOrderService;
import com.jf.service.InterventionProcessService;
import com.jf.service.MchtInfoService;
import com.jf.service.OrderDtlService;
import com.jf.service.OrderPreferentialInfoService;
import com.jf.service.PlatformContactService;
import com.jf.service.SubOrderService;
import com.jf.service.SysOrganizationService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.SysStaffProductTypeService;
import com.jf.service.ViolateOrderService;
import com.jf.service.WoRkHistoryService;
import com.jf.service.WoRkService;
import com.jf.service.WorkRecordService;
import com.jf.vo.Page;

@Controller
public class AppealOrderController extends BaseController {
	
	@Resource
	private ViolateOrderService violateOrderService;
	
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private AppealOrderService appealOrderService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private OrderPreferentialInfoService orderPreferentialInfoService;
	
	@Resource
	private AppealLogService appealLogService;
	
	@Resource
	private AppealLogPicService appealLogPicService;
	
	@Resource
	private PlatformContactService platformContactService;
	
	@Resource
	private AppealPlatformRemarksPicService appealPlatformRemarksPicService;
	
	@Autowired
	private SysStaffProductTypeService sysstaffproductTypeService;
	
	@Autowired
	private SysOrganizationService sysOrganizationService;
	
	@Autowired
	private SysStaffInfoService sysStaffInfoService;
	
	@Autowired
	private InterventionOrderService interventionOrderService;
	
	@Autowired
	private CustomerServiceOrderService customerServiceOrderService;
	
	@Resource
	private WoRkService woRkService;
	
	@Resource
	private WoRkHistoryService woRkHistoryService;
	
	@Resource
	private WorkRecordService workRecordService;
	
	@Resource
	private AttachmentHistoryService attachmentHistoryService;
	
	@Resource
	private AttachmentService attachmentService;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 客户投诉管理列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appealOrder/list.shtml")
	public ModelAndView appealOrderList(HttpServletRequest request) {
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		String rtPage = "/appealOrder/appealOrderList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("appealOrderAppealTypeList", DataDicUtil.getStatusList("BU_APPEAL_ORDER", "APPEAL_TYPE"));
		resMap.put("appealOrderStatusList", DataDicUtil.getStatusList("BU_APPEAL_ORDER", "STATUS"));
		resMap.put("appealOrderServiceStatusList", DataDicUtil.getStatusList("BU_APPEAL_ORDER", "SERVICE_STATUS"));
		resMap.put("platformProcessorList", appealOrderService.getPlatformProcessorList());
		
		SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
		SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
		sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(staffID);
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				sysstaffProductTypeexCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysstaffproductTypeService.selectByCustomExample(sysstaffProductTypeex);
		resMap.put("sysStaffProductTypeList", JSONArray.fromObject(sysStaffProductTypeList));
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 客户投诉管理列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appealOrder/data.shtml")
	@ResponseBody
	public Map<String, Object> appealOrderData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			AppealOrderCustomExample appealOrderCustomExample = new AppealOrderCustomExample();
			AppealOrderCustomExample.AppealOrderCustomCriteria appealOrderCustomCriteria = appealOrderCustomExample.createCriteria();
			appealOrderCustomCriteria.andProductTypeIdEqualTo(staffID);
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				appealOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				appealOrderCustomCriteria.andDelFlagEqualTo("0");
			}
			String searchOrderCode = request.getParameter("orderCode");
			String searchSubOrderCode = request.getParameter("subOrderCode");
			String searchAppealType = request.getParameter("appealType");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchStatus = request.getParameter("status");
			String searchServiceStatus = request.getParameter("serviceStatus");
			if(!StringUtil.isEmpty(searchOrderCode)){
				appealOrderCustomCriteria.andOrderCodeEqualTo(searchOrderCode);
			}
			if(!StringUtil.isEmpty(searchSubOrderCode)){
				appealOrderCustomCriteria.andSubOrderCodeEqualTo(searchSubOrderCode);
			}
			if(!StringUtil.isEmpty(searchAppealType)){
				appealOrderCustomCriteria.andAppealTypeEqualTo(searchAppealType);
			}
			if(!StringUtil.isEmpty(searchMchtCode)){
				appealOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchStatus)){
				appealOrderCustomCriteria.andStatusEqualTo(searchStatus);
			}
			if(!StringUtil.isEmpty(searchServiceStatus)){
				appealOrderCustomCriteria.andServiceStatusEqualTo(searchServiceStatus);
			}
			if(!StringUtil.isEmpty(request.getParameter("platformProcessor"))) {
				if("unclaimed".equals(request.getParameter("platformProcessor"))) {
					appealOrderCustomCriteria.andPlatformProcessorIsNull();
				}else {
					appealOrderCustomCriteria.andPlatformProcessorEqualTo(Integer.parseInt(request.getParameter("platformProcessor")));
				}
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				appealOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				appealOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("update_date_begin")) ){
				appealOrderCustomCriteria.andUpdateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("update_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("update_date_end")) ){
				appealOrderCustomCriteria.andUpdateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("update_date_end")+" 23:59:59"));
			}
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					appealOrderCustomCriteria.andProductTypeIdEqualTo(isCwOrgProductTypeId);
				}
			}else {
				if(!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
					appealOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("memberStatus"))) {
				appealOrderCustomCriteria.andMemberStatus(request.getParameter("memberStatus"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productId"))) {
				appealOrderCustomCriteria.andproductId(request.getParameter("productId"));
			}
			totalCount = appealOrderService.countAppealOrderCustomByExample(appealOrderCustomExample);
			appealOrderCustomExample.setLimitStart(page.getLimitStart());
			appealOrderCustomExample.setLimitSize(page.getLimitSize());
			List<AppealOrderCustom> appealOrderCustoms = appealOrderService.selectAppealOrderCustomByExample(appealOrderCustomExample);
			resMap.put("Rows", appealOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/* 投诉详情页*/
	@RequestMapping(value = "/appealOrder/view.shtml")
	public ModelAndView appealOrderView(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/appealOrder/view";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		int id=Integer.valueOf(request.getParameter("id"));
		AppealOrder appealOrder = appealOrderService.selectByPrimaryKey(id);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(appealOrder.getMchtId());
		ViolateOrderExample violateOrderExample = new ViolateOrderExample();
		ViolateOrderExample.Criteria violateOrderCriteria = violateOrderExample.createCriteria();
		violateOrderCriteria.andMchtIdEqualTo(appealOrder.getMchtId());
		int totalViolateCount = violateOrderService.countByExample(violateOrderExample);
		ViolateOrderExample example = new ViolateOrderExample();
		ViolateOrderExample.Criteria criteria = example.createCriteria();
		criteria.andMchtIdEqualTo(appealOrder.getMchtId());
		criteria.andCreateDateGreaterThanOrEqualTo(DateUtil.getDateAfter(new Date(), -30));
		criteria.andCreateDateLessThanOrEqualTo(new Date());
		int thirtyDaysViolateCount = violateOrderService.countByExample(example);
		SubOrderCustom subOrderCustom = subOrderService.selectSubOrderCustomByPrimaryKey(appealOrder.getSubOrderId());
		OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(appealOrder.getOrderDtlId());
		Activity activity = activityService.selectByPrimaryKey(orderDtl.getActivityId());
		if(activity!=null){
			resMap.put("belongActivity", activity.getName()+"["+activity.getId()+"]");
		}
		OrderPreferentialInfoExample orderPreferentialInfoExample = new OrderPreferentialInfoExample();
		OrderPreferentialInfoExample.Criteria orderPreferentialInfoCriteria = orderPreferentialInfoExample.createCriteria();
		orderPreferentialInfoCriteria.andDelFlagEqualTo("0");
		orderPreferentialInfoCriteria.andOrderDtlIdEqualTo(appealOrder.getOrderDtlId());
		List<OrderPreferentialInfo> orderPreferentialInfos = orderPreferentialInfoService.selectByExample(orderPreferentialInfoExample);
		AppealLogCustomExample appealLogCustomExample = new AppealLogCustomExample();
		AppealLogCustomExample.AppealLogCustomCriteria appealLogCustomCriteria = appealLogCustomExample.createCriteria();
		appealLogCustomCriteria.andAppealOrderIdEqualTo(id);
		appealLogCustomCriteria.andDelFlagEqualTo("0");
		List<AppealLogCustom> appealLogCustoms = appealLogService.selectByAppealLogCustomExample(appealLogCustomExample);
		for(AppealLogCustom appealLogCustom:appealLogCustoms){
			List<String> pics = appealLogPicService.getPicsByAppealLogId(appealLogCustom.getId());
			appealLogCustom.setPics(pics);
		}
		StaffBean staffBean = this.getSessionStaffBean(request);
		resMap.put("appealOrder", appealOrder);
		resMap.put("mchtInfo", mchtInfo);
		resMap.put("totalViolateCount", totalViolateCount);
		resMap.put("thirtyDaysViolateCount", thirtyDaysViolateCount);
		resMap.put("subOrderCustom", subOrderCustom);
		resMap.put("statusDesc", DataDicUtil.getStatusDesc("BU_APPEAL_ORDER", "STATUS", appealOrder.getStatus()));
		resMap.put("appealTypeDesc", DataDicUtil.getStatusDesc("BU_APPEAL_ORDER", "APPEAL_TYPE", appealOrder.getAppealType()));
		if(!StringUtils.isEmpty(appealOrder.getLiability())){
			resMap.put("liabilityDesc", DataDicUtil.getStatusDesc("BU_APPEAL_ORDER", "LIABILITY", appealOrder.getLiability()));
		}
		AppealPlatformRemarksPicExample appealPlatformRemarksPicExample = new AppealPlatformRemarksPicExample();
		AppealPlatformRemarksPicExample.Criteria appealPlatformRemarksPicCriteria = appealPlatformRemarksPicExample.createCriteria();
		appealPlatformRemarksPicCriteria.andDelFlagEqualTo("0").andAppealOrderIdEqualTo(appealOrder.getId());
		List<AppealPlatformRemarksPic> appealPlatformRemarksPicList = appealPlatformRemarksPicService.selectByExample(appealPlatformRemarksPicExample);
		resMap.put("appealPlatformRemarksPicList", appealPlatformRemarksPicList);
		resMap.put("orderDtl", orderDtl);
		resMap.put("orderPreferentialInfos", orderPreferentialInfos);
		resMap.put("appealLogCustoms", appealLogCustoms);
		resMap.put("staffId", staffBean.getStaffID());
		String contactName = platformContactService.getContactNameByMchtIdAndContactType(appealOrder.getMchtId(),2);
		resMap.put("contactName", contactName);
		
		WoRkExample woRkExample=new WoRkExample();
		woRkExample.createCriteria().andDelFlagEqualTo("0").andRelevantIdEqualTo(id);
		List<WoRk> woRks=woRkService.selectByExample(woRkExample);
		if (woRks.size()<=0) {
			resMap.put("woRks", true);
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 保存客服平台备注
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appealOrder/savePlatformRemarks.shtml")
	@ResponseBody
	public Map<String, Object> savePlatformRemarks(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			appealOrderService.updatePlatformRemarks(request, staffId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 保存平台处理人
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appealOrder/savePlatformProcessor.shtml")
	@ResponseBody
	public Map<String, Object> savePlatformProcessor(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			AppealOrder appealOrder = appealOrderService.selectByPrimaryKey(Integer.parseInt(id));
			appealOrder.setPlatformProcessor(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			appealOrder.setServiceStatus("1"); //客服状态(平台介入状态)	1 处理中
			appealOrderService.updateByPrimaryKey(appealOrder);
			resMap.put("staffName", this.getSessionStaffBean(request).getStaffName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 保存投诉流程日志
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appealOrder/saveAppealLog.shtml")
	@ResponseBody
	public Map<String, Object> saveAppealLog(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String staffName = this.getSessionStaffBean(request).getStaffName();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, Object> map = appealLogService.saveAppealLog(request, staffId, staffName);
			resMap.put("map", map);
			if(map.get("appealLog") != null) {
				resMap.put("appealLogCreateDate", sdf.format(((AppealLog)map.get("appealLog")).getCreateDate()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = "9999";
			msg = "操作失败！";
		}
		resMap.put("code", code);
		resMap.put("msg", msg);
		return resMap;
	}
	
	/**
	 * 待处理投诉列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appealOrder/pendingList.shtml")
	public ModelAndView appealOrderPendingList(HttpServletRequest request) {
		String rtPage = "/appealOrder/appealOrderPendingList";
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("appealOrderAppealTypeList", DataDicUtil.getStatusList("BU_APPEAL_ORDER", "APPEAL_TYPE"));
		resMap.put("appealOrderStatusList", DataDicUtil.getStatusList("BU_APPEAL_ORDER", "STATUS"));
		
		SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
		SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
		sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(staffID);
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				sysstaffProductTypeexCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysstaffproductTypeService.selectByCustomExample(sysstaffProductTypeex);
		resMap.put("sysStaffProductTypeList", JSONArray.fromObject(sysStaffProductTypeList));
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 待处理投诉列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appealOrder/pendingData.shtml")
	@ResponseBody
	public Map<String, Object> appealOrderPendingData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			AppealOrderCustomExample appealOrderCustomExample = new AppealOrderCustomExample();
			AppealOrderCustomExample.AppealOrderCustomCriteria appealOrderCustomCriteria = appealOrderCustomExample.createCriteria();
			appealOrderCustomCriteria.andProductTypeIdEqualTo(staffID);
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				appealOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				appealOrderCustomCriteria.andDelFlagEqualTo("0");
			}
			List<String> serviceStatusList = new ArrayList<String>();
			serviceStatusList.add("0");
			serviceStatusList.add("1");
			appealOrderCustomCriteria.andServiceStatusIn(serviceStatusList);
			String searchOrderCode = request.getParameter("orderCode");
			String searchSubOrderCode = request.getParameter("subOrderCode");
			String searchAppealType = request.getParameter("appealType");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchStatus = request.getParameter("status");
			String searchServiceStatus = request.getParameter("serviceStatus");
			if(!StringUtil.isEmpty(searchOrderCode)){
				appealOrderCustomCriteria.andOrderCodeEqualTo(searchOrderCode);
			}
			if(!StringUtil.isEmpty(searchSubOrderCode)){
				appealOrderCustomCriteria.andSubOrderCodeEqualTo(searchSubOrderCode);
			}
			if(!StringUtil.isEmpty(searchAppealType)){
				appealOrderCustomCriteria.andAppealTypeEqualTo(searchAppealType);
			}
			if(!StringUtil.isEmpty(searchMchtCode)){
				appealOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchStatus)){
				appealOrderCustomCriteria.andStatusEqualTo(searchStatus);
			}
			if(!StringUtil.isEmpty(searchServiceStatus)){
				appealOrderCustomCriteria.andServiceStatusEqualTo(searchServiceStatus);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				appealOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				appealOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("update_date_begin")) ){
				appealOrderCustomCriteria.andUpdateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("update_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("update_date_end")) ){
				appealOrderCustomCriteria.andUpdateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("update_date_end")+" 23:59:59"));
			}
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					appealOrderCustomCriteria.andProductTypeIdEqualTo(isCwOrgProductTypeId);
				}
			}else {
				if(!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
					appealOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("memberStatus"))) {
				appealOrderCustomCriteria.andMemberStatus(request.getParameter("memberStatus"));
			}
			totalCount = appealOrderService.countAppealOrderCustomByExample(appealOrderCustomExample);
			appealOrderCustomExample.setLimitStart(page.getLimitStart());
			appealOrderCustomExample.setLimitSize(page.getLimitSize());
			List<AppealOrderCustom> appealOrderCustoms = appealOrderService.selectAppealOrderCustomByExample(appealOrderCustomExample);
			resMap.put("Rows", appealOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title editUpdateByManager   
	 * @Description TODO(批量变更领取人)   
	 * @author pengl
	 * @date 2018年9月25日 下午2:27:16
	 */
	@RequestMapping("/appealOrder/editUpdateByManager.shtml")
	public ModelAndView editUpdateByManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/appealOrder/editUpdateBy");
		SysOrganizationExample sysOrganizationExample = new SysOrganizationExample();
		SysOrganizationExample.Criteria sysOrganizationCriteria = sysOrganizationExample.createCriteria();
		sysOrganizationCriteria.andOrgNameLike("%客服%");
		List<SysOrganization> sysOrganizationList = sysOrganizationService.selectByExample(sysOrganizationExample);
		List<Integer> orgIdList = new ArrayList<Integer>();
		for(SysOrganization sysOrganization : sysOrganizationList) {
			orgIdList.add(sysOrganization.getOrgId());
		}
		SysStaffInfoExample sysStaffInfoExample = new SysStaffInfoExample();
		SysStaffInfoExample.Criteria sysStaffInfoCriteria = sysStaffInfoExample.createCriteria();
		sysStaffInfoCriteria.andStatusEqualTo("A").andOrgIdIn(orgIdList);
		List<SysStaffInfo> sysStaffInfoList = sysStaffInfoService.selectByExample(sysStaffInfoExample);
		m.addObject("sysStaffInfoList", sysStaffInfoList);
		m.addObject("ids", request.getParameter("ids"));
		return m;
	}
	
	/**
	 * 
	 * @Title editUpdateBy   
	 * @Description TODO(批量变更领取人)   
	 * @author pengl
	 * @date 2018年9月25日 下午2:27:20
	 */
	@RequestMapping("/appealOrder/editUpdateBy.shtml")
	public ModelAndView editUpdateBy(HttpServletRequest request) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			Date date = new Date();
			String ids = request.getParameter("ids");
			if(!StringUtil.isEmpty(ids)) {
				List<Integer> idList = new ArrayList<Integer>();
				String[] idStr = ids.split(",");
				for(String id : idStr) {
					idList.add(Integer.parseInt(id));
				}
				AppealOrderExample appealOrderExample = new AppealOrderExample();
				appealOrderExample.createCriteria().andIdIn(idList);
				String platformProcessor = request.getParameter("platformProcessor");
				AppealOrder appealOrder = new AppealOrder();
				appealOrder.setPlatformProcessor(Integer.parseInt(platformProcessor));
				appealOrder.setUpdateBy(staffId);
				appealOrder.setUpdateDate(date);
				appealOrderService.updateByExampleSelective(appealOrder, appealOrderExample);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	    //创建投诉工单
		@RequestMapping(value = "/appealOrder/addappealOrderWork.shtml")
		public ModelAndView addappealOrderWork(HttpServletRequest request) {
			String rtPage = "/appealOrder/addappealOrderWork";
			Map<String, Object> resMap = new HashMap<String, Object>();
			int id=Integer.valueOf(request.getParameter("id"));
			AppealOrder appealOrder = appealOrderService.selectByPrimaryKey(id);
			resMap.put("appealOrder", appealOrder);
			
			SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
			sysOrganizationExample.createCriteria().andStatusEqualTo("A");
			List<SysOrganization> sysOrganizationlist=sysOrganizationService.selectByExample(sysOrganizationExample);			
			resMap.put("sysOrganizationlist",sysOrganizationlist);
			return new ModelAndView(rtPage,resMap);
		}
		
		 //添加投诉工单
		@ResponseBody
		@RequestMapping("/appealOrder/addappealOrderWorklist.shtml")
		public ModelAndView addappealOrderWorklist(HttpServletRequest request, WoRk work,String attachmentName, String attachmentPath,String id) {
			String rtPage = "/success/success";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = "";
			String msg = "";
			try {
				StaffBean staffBean = this.getSessionStaffBean(request);
				Integer staffId = Integer.valueOf(staffBean.getStaffID());
				Date date = new Date();
				
				   work.setStatus("0");
				   work.setStatusBehavior("1");
				   work.setDelFlag("0");
				   work.setRelevantId(Integer.valueOf(id));
				   work.setCreateBy(staffId);
				   work.setCreateDate(date);
				   
				   if (work.getRelevantType().equals("1")) {
					   InterventionOrderCustomExample interventionOrderCustomExample=new InterventionOrderCustomExample();
					   interventionOrderCustomExample.createCriteria().andDelFlagEqualTo("0").andInterventionCodeEqualTo(work.getRelevantCode());
					   List<InterventionOrderCustom> interventionOrderCustom=interventionOrderService.selectByCustomExample(interventionOrderCustomExample);
					   work.setRelevantId(interventionOrderCustom.get(0).getId());
					   
				   }		   
				   if (work.getRelevantType().equals("2")) {
					   AppealOrderExample appealOrderExample=new AppealOrderExample();
					   appealOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderCodeEqualTo(work.getRelevantCode());
					   List<AppealOrder> appealOrder=appealOrderService.selectByExample(appealOrderExample);
					   work.setRelevantId(appealOrder.get(0).getId());
				   }
				   if (work.getRelevantType().equals("4")) {
					   SubOrderExample subOrderExample=new SubOrderExample();
					   subOrderExample.createCriteria().andDelFlagEqualTo("0").andSubOrderCodeEqualTo(work.getRelevantCode());
					   List<SubOrder> subOrder=subOrderService.selectByExample(subOrderExample);
					   work.setRelevantId(subOrder.get(0).getId());
				   }
				   if (work.getRelevantType().equals("5")) {
					   CustomerServiceOrderExample customerServiceOrderExample=new CustomerServiceOrderExample();
					   customerServiceOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderCodeEqualTo(work.getRelevantCode());
					   List<CustomerServiceOrder> customerServiceOrder=customerServiceOrderService.selectByExample(customerServiceOrderExample);
					   work.setRelevantId(customerServiceOrder.get(0).getId());
				   }
				   
				   woRkService.insertSelective(work);
				  	   		   
				   Attachment attachment=new Attachment();
				   attachment.setWorkId(work.getId());
				   attachment.setAttachmentName(attachmentName);
				   attachment.setAttachmentPath(attachmentPath);
				   attachment.setDelFlag("0");
				   attachment.setCreateBy(staffId);
				   attachment.setCreateDate(date);
				   attachmentService.insertSelective(attachment);
				   						   
				   
				   WorkHistory workHistory=new WorkHistory();
				   workHistory.setWorkId(work.getId());
				   workHistory.setOrgId(work.getOrgId());
				   workHistory.setStaffId(work.getStaffId());
				   workHistory.setWorkType(work.getWorkType());
				   workHistory.setStatus(work.getStatus());
				   workHistory.setStatusBehavior(work.getStatusBehavior());
				   workHistory.setUrgentDegree(work.getUrgentDegree());
				   workHistory.setCloseReason(work.getCloseReason());
				   workHistory.setTitleContent(work.getTitleContent());
				   workHistory.setRelevantType(work.getRelevantType());
				   workHistory.setRelevantCode(work.getRelevantCode());
				   workHistory.setRelevantId(work.getRelevantId());
				   workHistory.setDescribeContent(work.getDescribeContent());
				   workHistory.setCreateBy(work.getCreateBy());
				   workHistory.setCreateDate(work.getCreateDate());
				   workHistory.setDelFlag("0");
				   woRkHistoryService.insertSelective(workHistory);
				   
				   SysStaffInfo sysStaffInfo=sysStaffInfoService.selectByPrimaryKey(staffId);
				   SysStaffInfo sysStaffInfos=sysStaffInfoService.selectByPrimaryKey(work.getStaffId());
				   
				   WorkRecord workRecord=new WorkRecord();
				   workRecord.setWorkHistoryId(workHistory.getId());
				   workRecord.setWorkId(work.getId());
				   workRecord.setOrgId(work.getOrgId());
				   workRecord.setStaffId(work.getStaffId());
				   workRecord.setRecordStatus("1");
				   workRecord.setRecordTitle("由"+sysStaffInfo.getStaffName()+"创建工单并指派给"+sysStaffInfos.getStaffName());
				   workRecord.setCreateBy(staffId);
				   workRecord.setCreateDate(date);
				   workRecord.setDelFlag("0");
				   workRecordService.insertSelective(workRecord);
				   
				   
				   AttachmentHistory attachmentHistory=new AttachmentHistory();
				   attachmentHistory.setWorkHistoryId(workHistory.getId());
				   attachmentHistory.setAttachmentName(attachmentName);
				   attachmentHistory.setAttachmentPath(attachmentPath);
				   attachmentHistory.setCreateBy(staffId);
				   attachmentHistory.setCreateDate(date);
				   attachmentHistory.setDelFlag("0");
				   attachmentHistoryService.insertSelective(attachmentHistory);
				   						   					   
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {
				e.printStackTrace();
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return new ModelAndView(rtPage, resMap); 
		}
	
}
