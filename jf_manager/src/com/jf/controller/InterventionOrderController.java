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

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.entity.AppealOrder;
import com.jf.entity.AppealOrderExample;
import com.jf.entity.Attachment;
import com.jf.entity.AttachmentHistory;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.CustomerServiceOrderExample;
import com.jf.entity.InterventionOrder;
import com.jf.entity.InterventionOrderCustom;
import com.jf.entity.InterventionOrderCustomExample;
import com.jf.entity.InterventionOrderExample;
import com.jf.entity.InterventionOrderLogCustom;
import com.jf.entity.InterventionOrderLogExample;
import com.jf.entity.InterventionProcess;
import com.jf.entity.InterventionProcessExample;
import com.jf.entity.InterventionProcessPic;
import com.jf.entity.InterventionProcessPicExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExample;
import com.jf.entity.SysOrganization;
import com.jf.entity.SysOrganizationExample;
import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffInfoExample;
import com.jf.entity.SysStaffProductTypeCustom;
import com.jf.entity.SysStaffProductTypeCustomExample;
import com.jf.entity.WoRk;
import com.jf.entity.WoRkExample;
import com.jf.entity.WorkHistory;
import com.jf.entity.WorkRecord;
import com.jf.service.AppealOrderService;
import com.jf.service.AttachmentHistoryService;
import com.jf.service.AttachmentService;
import com.jf.service.ClientServiceCommentService;
import com.jf.service.CustomerServiceOrderService;
import com.jf.service.InterventionOrderLogService;
import com.jf.service.InterventionOrderService;
import com.jf.service.InterventionProcessPicService;
import com.jf.service.InterventionProcessService;
import com.jf.service.SubOrderService;
import com.jf.service.SysOrganizationService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.SysStaffProductTypeService;
import com.jf.service.WoRkHistoryService;
import com.jf.service.WoRkService;
import com.jf.service.WorkRecordService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class InterventionOrderController extends BaseController {

	@Autowired
	private InterventionOrderService interventionOrderService;
	
	@Autowired
	private CustomerServiceOrderService customerServiceOrderService;
	
	@Autowired
	private AppealOrderService appealOrderService;
	
	@Autowired
	private InterventionProcessService interventionProcessService;
	
	@Autowired
	private InterventionProcessPicService interventionProcessPicService;
	
	@Autowired
	private InterventionOrderLogService interventionOrderLogService;
	
	@Autowired
	private ClientServiceCommentService clientServiceCommentService;
	
	@Autowired
	private SysStaffProductTypeService sysstaffproductTypeService;
	
	@Autowired
	private SysOrganizationService sysOrganizationService;
	
	@Autowired
	private SysStaffInfoService sysStaffInfoService;
	
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
	
	@Resource
	private SubOrderService subOrderService;
	
	
	/**
	 * 
	 * @Title InterventionOrderManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月3日 下午4:10:38
	 */
	@RequestMapping("/interventionOrder/interventionOrderManager.shtml")
	public ModelAndView interventionOrderManager(HttpServletRequest request, String statusPage) {
		ModelAndView m = new ModelAndView();
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
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
		m.addObject("isCwOrgStatus", isCwOrgStatus);
		List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysstaffproductTypeService.selectByCustomExample(sysstaffProductTypeex);
		m.addObject("sysStaffProductTypeList", JSONArray.fromObject(sysStaffProductTypeList));
		
		if(!StringUtil.isEmpty(statusPage) && "1".equals(statusPage)) { //全部售后介入
			List<Map<String, Object>> platformProcessorList = interventionOrderService.getPlatformProcessorList(); //获取100天内平台处理人
			m.addObject("platformProcessorList", platformProcessorList);
			m.addObject("reasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "REASON")); //介入原因
			m.addObject("statusList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "STATUS")); //介入单状态
			m.setViewName("/interventionOrder/allInterventionOrderList");
		}else if(!StringUtil.isEmpty(statusPage) && "2".equals(statusPage)) { //待领取的介入
			m.addObject("reasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "REASON")); //介入原因
			m.setViewName("/interventionOrder/awaitInterventionOrderList");
		}else if(!StringUtil.isEmpty(statusPage) && "3".equals(statusPage)) { //我的待处理介入
			m.addObject("reasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "REASON")); //介入原因
			m.setViewName("/interventionOrder/myAwaitInterventionOrderList");
		}else if(!StringUtil.isEmpty(statusPage) && "4".equals(statusPage)) { //我的待评判
			m.addObject("reasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "REASON")); //介入原因
			m.setViewName("/interventionOrder/myJudgeInterventionOrderList");
		}else if(!StringUtil.isEmpty(statusPage) && "5".equals(statusPage)) { //我的待结案
			m.addObject("reasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "REASON")); //介入原因
			m.setViewName("/interventionOrder/myCloseInterventionOrderList");
		}else if(!StringUtil.isEmpty(statusPage) && "6".equals(statusPage)) { //待复审核
			m.addObject("reasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "REASON")); //介入原因
			m.setViewName("/interventionOrder/recheckInterventionOrderList");
		}else if(!StringUtil.isEmpty(statusPage) && "7".equals(statusPage)) { //处理中的介入
			List<Map<String, Object>> platformProcessorList = interventionOrderService.getPlatformProcessorList(); //获取100天内平台处理人
			m.addObject("platformProcessorList", platformProcessorList);
			m.addObject("reasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "REASON")); //介入原因
			m.addObject("statusList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "STATUS")); //介入单状态
			m.setViewName("/interventionOrder/disposeInterventionOrderList");
		}else if(!StringUtil.isEmpty(statusPage) && "8".equals(statusPage)) { //已结案
			List<Map<String, Object>> platformProcessorList = interventionOrderService.getPlatformProcessorList(); //获取100天内平台处理人
			m.addObject("platformProcessorList", platformProcessorList);
			m.addObject("reasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "REASON")); //介入原因
			m.setViewName("/interventionOrder/closeInterventionOrderList");
		}else if(!StringUtil.isEmpty(statusPage) && "9".equals(statusPage)) { //我的介入单
			m.addObject("reasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "REASON")); //介入原因
			m.addObject("statusList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "STATUS")); //介入单状态
			m.setViewName("/interventionOrder/myInterventionOrderList");
		}
		return m;
	}
	
	/**
	 * 
	 * @Title getInterventionOrderList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月3日 下午4:18:23
	 */
	@ResponseBody
	@RequestMapping("/interventionOrder/getInterventionOrderList.shtml")
	public Map<String, Object> getInterventionOrderList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<InterventionOrderCustom> dataList = null;
		Integer totalCount = 0;
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			StaffBean staffBean = this.getSessionStaffBean(request);
			String statusFlag = request.getParameter("statusFlag");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			InterventionOrderCustomExample interventionOrderCustomExample = new InterventionOrderCustomExample();
			InterventionOrderCustomExample.InterventionOrderCustomCriteria interventionOrderCustomCriteria = interventionOrderCustomExample.createCriteria();
			interventionOrderCustomCriteria.andDelFlagEqualTo("0");
			interventionOrderCustomCriteria.andProductTypeid(staffID);//用户只能查看到 自己负责的类目的商家数据
			if(!StringUtil.isEmpty(statusFlag) && "1".equals(statusFlag)) { //全部售后介入
				interventionOrderCustomExample.setOrderByClause(" isnull(t.platform_processor) desc, t.update_date desc");
			}else if(!StringUtil.isEmpty(statusFlag) && "2".equals(statusFlag)) { //待领取的介入  
				interventionOrderCustomCriteria.andStatusEqualTo("0");
				interventionOrderCustomExample.setOrderByClause(" t.create_date desc");
			}else if(!StringUtil.isEmpty(statusFlag) && "3".equals(statusFlag)) { //我的待处理介入
				interventionOrderCustomCriteria.andPlatformProcessorEqualTo(Integer.parseInt(staffBean.getStaffID()));
				interventionOrderCustomCriteria.andStatusEqualTo("1");
				interventionOrderCustomExample.setOrderByClause(" t.update_date desc");
			}else if(!StringUtil.isEmpty(statusFlag) && "4".equals(statusFlag)) { //我的待评判
				interventionOrderCustomCriteria.andPlatformProcessorEqualTo(Integer.parseInt(staffBean.getStaffID()));
				interventionOrderCustomCriteria.andStatusEqualTo("5");
				interventionOrderCustomExample.setOrderByClause(" t.update_date desc");
			}else if(!StringUtil.isEmpty(statusFlag) && "5".equals(statusFlag)) { //我的待结案
				interventionOrderCustomCriteria.andPlatformProcessorEqualTo(Integer.parseInt(staffBean.getStaffID()));
				interventionOrderCustomCriteria.andStatusEqualTo("7");
				interventionOrderCustomExample.setOrderByClause(" t.update_date desc");
			}else if(!StringUtil.isEmpty(statusFlag) && "6".equals(statusFlag)) { //待复审核
				interventionOrderCustomCriteria.andStatusEqualTo("2");
				interventionOrderCustomExample.setOrderByClause(" t.update_date desc");
			}else if(!StringUtil.isEmpty(statusFlag) && "7".equals(statusFlag)) { //处理中的介入
				List<String> statusList = Arrays.asList("1","2","3","4","5","6","7");
				interventionOrderCustomCriteria.andStatusIn(statusList);
				interventionOrderCustomExample.setOrderByClause(" t.update_date desc");
			}else if(!StringUtil.isEmpty(statusFlag) && "8".equals(statusFlag)) { //已结案
				interventionOrderCustomCriteria.andStatusEqualTo("8");
				interventionOrderCustomExample.setOrderByClause(" t.update_date desc");
			}else if(!StringUtil.isEmpty(statusFlag) && "9".equals(statusFlag)) { //我的介入单
				interventionOrderCustomCriteria.andPlatformProcessorEqualTo(Integer.parseInt(staffBean.getStaffID()));
				List<String> statusList = Arrays.asList("1","2","5","7","4","6");
				interventionOrderCustomCriteria.andStatusIn(statusList);
				interventionOrderCustomExample.setOrderByClause(" find_in_set(t.status, '1,5,7,2,4,6') asc, t.create_date asc");
			}
			if(!StringUtil.isEmpty(request.getParameter("interventionCode"))) { //介入单号
				interventionOrderCustomCriteria.andInterventionCodeEqualTo(request.getParameter("interventionCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("customerServiceOrderCode"))) { //售后单号
				interventionOrderCustomCriteria.andCustomerServiceOrderCode(request.getParameter("customerServiceOrderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("reason"))) { //介入原因
				interventionOrderCustomCriteria.andReasonEqualTo(request.getParameter("reason"));
			}
			if(!StringUtil.isEmpty(request.getParameter("platformProcessorId"))) { //平台处理人
				interventionOrderCustomCriteria.andPlatformProcessorEqualTo(Integer.parseInt(request.getParameter("platformProcessorId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateStart"))) { //申请介入时间
				interventionOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDateStart")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd"))) {
				interventionOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("updateDateStart"))) { //最后更新时间
				interventionOrderCustomCriteria.andUpdateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("updateDateStart")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("updateDateEnd"))) {
				interventionOrderCustomCriteria.andUpdateDateLessThanOrEqualTo(sdf.parse(request.getParameter("updateDateEnd")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))) { //介入单状态
				interventionOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) { //商家名称
				interventionOrderCustomCriteria.andMchtName(request.getParameter("mchtName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {//商家序号
				interventionOrderCustomCriteria.andmchtCode(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtPhone"))) { //商家联系电话
				interventionOrderCustomCriteria.andMchtPhone(request.getParameter("mchtPhone"));
			}
			if(!StringUtil.isEmpty(request.getParameter("tel"))) { //申请人联系电话
				interventionOrderCustomCriteria.andTelEqualTo(request.getParameter("tel"));
			}
			if(!StringUtil.isEmpty(request.getParameter("isInitiateViolate"))) { //是否发起违规
				if("1".equals(request.getParameter("isInitiateViolate"))) {
					interventionOrderCustomCriteria.andIsInitiateViolateEqualTo(request.getParameter("isInitiateViolate"));
				}else {
					interventionOrderCustomCriteria.andIsInitiateViolate();
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("isComment"))) { //是否评价
				if("1".equals(request.getParameter("isComment"))) {
					interventionOrderCustomCriteria.andIsCommentEqualTo(request.getParameter("isComment"));
				}else {
					interventionOrderCustomCriteria.andIsComment();
				}
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					interventionOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
					interventionOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("memberStatus"))) {
				interventionOrderCustomCriteria.andMemberStatus(request.getParameter("memberStatus"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productId"))) {
				interventionOrderCustomCriteria.andproductId(request.getParameter("productId"));
			}
			interventionOrderCustomExample.setLimitStart(page.getLimitStart());
			interventionOrderCustomExample.setLimitSize(page.getLimitSize());
			totalCount = interventionOrderService.countByCustomExample(interventionOrderCustomExample);
			dataList = interventionOrderService.selectByCustomExample(interventionOrderCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title showInterventionOrderManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月9日 上午11:00:22
	 */
	@RequestMapping("/interventionOrder/showInterventionOrderManager.shtml")
	public ModelAndView showInterventionOrderManager(HttpServletRequest request, String statusPage) {
		ModelAndView m = new ModelAndView();
		String interventionOrderId = request.getParameter("interventionOrderId");
		try {
			if(!StringUtil.isEmpty(interventionOrderId)) {
				InterventionOrderCustom interventionOrderCustom = interventionOrderService.selectByPrimaryKeyCustom(Integer.parseInt(interventionOrderId));
				m.addObject("interventionOrderCustom", interventionOrderCustom); //介入单信息
				CustomerServiceOrderCustom customerServiceOrderCustom = customerServiceOrderService.selectCustomerServiceOrderCustomByPrimaryKey(interventionOrderCustom.getServiceOrderId());
				m.addObject("customerServiceOrderCustom", customerServiceOrderCustom); //售后单信息
				AppealOrderExample appealOrderExample = new AppealOrderExample();
				appealOrderExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(interventionOrderCustom.getMchtId());
				Integer appealOrderCount = appealOrderService.countByExample(appealOrderExample);
				m.addObject("appealOrderCount", appealOrderCount); //总投诉单数
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, 0);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				String formatDate = format.format(calendar.getTime())+" 00:00:00";
				AppealOrderExample appealOrderExa = new AppealOrderExample();
				appealOrderExa.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(interventionOrderCustom.getMchtId())
					.andCreateDateGreaterThanOrEqualTo(sdf.parse(formatDate))
					.andCreateDateLessThanOrEqualTo(date);
				Integer appealOrderCountMonth = appealOrderService.countByExample(appealOrderExa);
				m.addObject("appealOrderCountMonth", appealOrderCountMonth); //当月被投诉数
				Map<String, Object> mapUser = new HashMap<String, Object>();
				InterventionProcessExample interventionProcessExample = new InterventionProcessExample();
				InterventionProcessExample.Criteria interventionProcessCriteria = interventionProcessExample.createCriteria();
				interventionProcessCriteria.andDelFlagEqualTo("0").andOperatorTypeEqualTo("1")
					.andInterventionOrderIdEqualTo(interventionOrderCustom.getId());
				interventionProcessExample.setOrderByClause(" create_date desc");
				interventionProcessExample.setLimitStart(0);
				interventionProcessExample.setLimitSize(1);
				List<InterventionProcess> interventionProcessUserList = interventionProcessService.selectByExample(interventionProcessExample);
				if(interventionProcessUserList != null && interventionProcessUserList.size() > 0) {
					InterventionProcessPicExample interventionProcessPicExample = new InterventionProcessPicExample();
					InterventionProcessPicExample.Criteria interventionProcessPicCriteria = interventionProcessPicExample.createCriteria();
					interventionProcessPicCriteria.andDelFlagEqualTo("0").andInterventionProcessIdEqualTo(interventionProcessUserList.get(0).getId());
					interventionProcessPicExample.setOrderByClause(" id asc");
					List<InterventionProcessPic> interventionProcessPicList = interventionProcessPicService.selectByExample(interventionProcessPicExample);
					mapUser.put("userInterventionProcess", interventionProcessUserList.get(0));
					mapUser.put("interventionProcessPicList", interventionProcessPicList);
				}
				m.addObject("mapUser", mapUser); //协商内容（客户）
				Map<String, Object> mapMcht = new HashMap<String, Object>();
				InterventionProcessExample interventionProcessMchtExample = new InterventionProcessExample();
				InterventionProcessExample.Criteria interventionProcessMchtCriteria = interventionProcessMchtExample.createCriteria();
				interventionProcessMchtCriteria.andDelFlagEqualTo("0").andOperatorTypeEqualTo("2")
					.andInterventionOrderIdEqualTo(interventionOrderCustom.getId());
				interventionProcessMchtExample.setOrderByClause(" create_date desc");
				interventionProcessMchtExample.setLimitStart(0);
				interventionProcessMchtExample.setLimitSize(1);
				List<InterventionProcess> interventionProcessMchtList = interventionProcessService.selectByExample(interventionProcessMchtExample);
				if(interventionProcessMchtList != null && interventionProcessMchtList.size() > 0) {
					InterventionProcessPicExample interventionProcessPicMchtExample = new InterventionProcessPicExample();
					InterventionProcessPicExample.Criteria interventionProcessPicMchtCriteria = interventionProcessPicMchtExample.createCriteria();
					interventionProcessPicMchtCriteria.andDelFlagEqualTo("0").andInterventionProcessIdEqualTo(interventionProcessMchtList.get(0).getId());
					interventionProcessPicMchtExample.setOrderByClause(" id asc");
					List<InterventionProcessPic> interventionProcessPicList = interventionProcessPicService.selectByExample(interventionProcessPicMchtExample);
					mapMcht.put("mchtInterventionProcess", interventionProcessMchtList.get(0));
					mapMcht.put("interventionProcessPicList", interventionProcessPicList);
				}
				m.addObject("mapMcht", mapMcht); //协商内容（商家）
				InterventionOrderLogExample interventionOrderLogExample = new InterventionOrderLogExample();
				InterventionOrderLogExample.Criteria interventionOrderLogCriteria = interventionOrderLogExample.createCriteria();
				interventionOrderLogCriteria.andDelFlagEqualTo("0").andInterventionOrderIdEqualTo(interventionOrderCustom.getId());
				interventionOrderLogExample.setOrderByClause(" create_date desc");
				List<InterventionOrderLogCustom> interventionOrderLogCustomList = interventionOrderLogService.selectByCustomExample(interventionOrderLogExample);
				m.addObject("interventionOrderLogCustomList", interventionOrderLogCustomList); //操作记录
				if(!StringUtil.isEmpty(statusPage) && "1".equals(statusPage)) { //全部售后介入——查看
					m.addObject("rejectReasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "REJECT_REASON")); //客服拒绝介入理由
					Map<String, Object> mapMchtAppeal = new HashMap<String, Object>();
					InterventionProcessExample interventionProcessMchtAppealExample = new InterventionProcessExample();
					InterventionProcessExample.Criteria interventionProcessMchtAppealCriteria = interventionProcessMchtAppealExample.createCriteria();
					interventionProcessMchtAppealCriteria.andDelFlagEqualTo("0").andOperatorTypeEqualTo("2").andProcessTypeEqualTo("1")
						.andInterventionOrderIdEqualTo(interventionOrderCustom.getId());
					interventionProcessMchtAppealExample.setOrderByClause(" create_date desc");
					interventionProcessMchtAppealExample.setLimitStart(0);
					interventionProcessMchtAppealExample.setLimitSize(1);
					List<InterventionProcess> interventionProcessMchtAppealList = interventionProcessService.selectByExample(interventionProcessMchtAppealExample);
					if(interventionProcessMchtAppealList != null && interventionProcessMchtAppealList.size() > 0) {
						InterventionProcessPicExample interventionProcessPicMchtExample = new InterventionProcessPicExample();
						InterventionProcessPicExample.Criteria interventionProcessPicMchtCriteria = interventionProcessPicMchtExample.createCriteria();
						interventionProcessPicMchtCriteria.andDelFlagEqualTo("0").andInterventionProcessIdEqualTo(interventionProcessMchtAppealList.get(0).getId());
						interventionProcessPicMchtExample.setOrderByClause(" id asc");
						List<InterventionProcessPic> interventionProcessPicList = interventionProcessPicService.selectByExample(interventionProcessPicMchtExample);
						mapMchtAppeal.put("mchtAppealInterventionProcess", interventionProcessMchtAppealList.get(0));
						mapMchtAppeal.put("interventionProcessPicList", interventionProcessPicList);
					}
					WoRkExample woRkExample=new WoRkExample();
					woRkExample.createCriteria().andDelFlagEqualTo("0").andRelevantIdEqualTo(Integer.valueOf(interventionOrderId));
					List<WoRk> woRks=woRkService.selectByExample(woRkExample);
					if (woRks.size()<=0) {
						mapMchtAppeal.put("woRks",true);						
					}
					m.addObject("mapMchtAppeal", mapMchtAppeal); //商家申诉
					m.addObject("clientResultReasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "CLIENT_RESULT_REASON")); //买家胜诉/败诉理由
					m.addObject("mchtResultReasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "MCHT_RESULT_REASON")); //商家败诉/胜诉理由
					List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
					InterventionProcessExample interventionProcessMchtProofExample = new InterventionProcessExample();
					InterventionProcessExample.Criteria interventionProcessMchtProofCriteria = interventionProcessMchtProofExample.createCriteria();
					interventionProcessMchtProofCriteria.andDelFlagEqualTo("0").andOperatorTypeEqualTo("2").andProcessTypeEqualTo("3")
						.andInterventionOrderIdEqualTo(interventionOrderCustom.getId());
					interventionProcessMchtProofExample.setOrderByClause(" create_date desc");
					interventionProcessMchtProofExample.setLimitStart(0);
					interventionProcessMchtProofExample.setLimitSize(2);
					List<InterventionProcess> interventionProcessMchtProofList = interventionProcessService.selectByExample(interventionProcessMchtProofExample);
					for(InterventionProcess interventionProcess : interventionProcessMchtProofList) {
						InterventionProcessPicExample interventionProcessPicMchtProofExample = new InterventionProcessPicExample();
						InterventionProcessPicExample.Criteria interventionProcessPicMchtProofCriteria = interventionProcessPicMchtProofExample.createCriteria();
						interventionProcessPicMchtProofCriteria.andDelFlagEqualTo("0").andInterventionProcessIdEqualTo(interventionProcess.getId());
						interventionProcessPicMchtProofExample.setOrderByClause(" id asc");
						List<InterventionProcessPic> interventionProcessPicList = interventionProcessPicService.selectByExample(interventionProcessPicMchtProofExample);
						Map<String, Object> mapMchtProof = new HashMap<String, Object>();
						mapMchtProof.put("interventionProcess", interventionProcess);
						mapMchtProof.put("interventionProcessPicList", interventionProcessPicList);
						listMap.add(mapMchtProof);
					}
					m.addObject("listMap", listMap); //商家处理凭证
					m.setViewName("/interventionOrder/allInterventionOrderShow");
				}else if(!StringUtil.isEmpty(statusPage) && "2".equals(statusPage)) { //我的待处理介入——查看
					m.addObject("rejectReasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "REJECT_REASON")); //客服拒绝介入理由
					m.setViewName("/interventionOrder/myAwaitInterventionOrderShow");
				}else if(!StringUtil.isEmpty(statusPage) && "3".equals(statusPage)) { //我的待评判——查看
					m.addObject("clientResultReasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "CLIENT_RESULT_REASON")); //买家胜诉/败诉理由
					m.addObject("mchtResultReasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "MCHT_RESULT_REASON")); //商家败诉/胜诉理由
					Map<String, Object> mapMchtAppeal = new HashMap<String, Object>();
					InterventionProcessExample interventionProcessMchtAppealExample = new InterventionProcessExample();
					InterventionProcessExample.Criteria interventionProcessMchtAppealCriteria = interventionProcessMchtAppealExample.createCriteria();
					interventionProcessMchtAppealCriteria.andDelFlagEqualTo("0").andOperatorTypeEqualTo("2").andProcessTypeEqualTo("1")
						.andInterventionOrderIdEqualTo(interventionOrderCustom.getId());
					interventionProcessMchtAppealExample.setOrderByClause(" create_date desc");
					interventionProcessMchtAppealExample.setLimitStart(0);
					interventionProcessMchtAppealExample.setLimitSize(1);
					List<InterventionProcess> interventionProcessMchtAppealList = interventionProcessService.selectByExample(interventionProcessMchtAppealExample);
					if(interventionProcessMchtAppealList != null && interventionProcessMchtAppealList.size() > 0) {
						InterventionProcessPicExample interventionProcessPicMchtExample = new InterventionProcessPicExample();
						InterventionProcessPicExample.Criteria interventionProcessPicMchtCriteria = interventionProcessPicMchtExample.createCriteria();
						interventionProcessPicMchtCriteria.andDelFlagEqualTo("0").andInterventionProcessIdEqualTo(interventionProcessMchtAppealList.get(0).getId());
						interventionProcessPicMchtExample.setOrderByClause(" id asc");
						List<InterventionProcessPic> interventionProcessPicList = interventionProcessPicService.selectByExample(interventionProcessPicMchtExample);
						mapMchtAppeal.put("mchtAppealInterventionProcess", interventionProcessMchtAppealList.get(0));
						mapMchtAppeal.put("interventionProcessPicList", interventionProcessPicList);
					}
					m.addObject("mapMchtAppeal", mapMchtAppeal); //商家申诉
					m.setViewName("/interventionOrder/myJudgeInterventionOrderShow");
				}else if(!StringUtil.isEmpty(statusPage) && "4".equals(statusPage)) { //我的待结案——查看
					m.addObject("clientResultReasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "CLIENT_RESULT_REASON")); //买家胜诉/败诉理由
					m.addObject("mchtResultReasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "MCHT_RESULT_REASON")); //商家败诉/胜诉理由
					Map<String, Object> mapMchtAppeal = new HashMap<String, Object>();
					InterventionProcessExample interventionProcessMchtAppealExample = new InterventionProcessExample();
					InterventionProcessExample.Criteria interventionProcessMchtAppealCriteria = interventionProcessMchtAppealExample.createCriteria();
					interventionProcessMchtAppealCriteria.andDelFlagEqualTo("0").andOperatorTypeEqualTo("2").andProcessTypeEqualTo("1")
						.andInterventionOrderIdEqualTo(interventionOrderCustom.getId());
					interventionProcessMchtAppealExample.setOrderByClause(" create_date desc");
					interventionProcessMchtAppealExample.setLimitStart(0);
					interventionProcessMchtAppealExample.setLimitSize(1);
					List<InterventionProcess> interventionProcessMchtAppealList = interventionProcessService.selectByExample(interventionProcessMchtAppealExample);
					if(interventionProcessMchtAppealList != null && interventionProcessMchtAppealList.size() > 0) {
						InterventionProcessPicExample interventionProcessPicMchtExample = new InterventionProcessPicExample();
						InterventionProcessPicExample.Criteria interventionProcessPicMchtCriteria = interventionProcessPicMchtExample.createCriteria();
						interventionProcessPicMchtCriteria.andDelFlagEqualTo("0").andInterventionProcessIdEqualTo(interventionProcessMchtAppealList.get(0).getId());
						interventionProcessPicMchtExample.setOrderByClause(" id asc");
						List<InterventionProcessPic> interventionProcessPicList = interventionProcessPicService.selectByExample(interventionProcessPicMchtExample);
						mapMchtAppeal.put("mchtAppealInterventionProcess", interventionProcessMchtAppealList.get(0));
						mapMchtAppeal.put("interventionProcessPicList", interventionProcessPicList);
					}
					m.addObject("mapMchtAppeal", mapMchtAppeal); //商家申诉
					List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
					InterventionProcessExample interventionProcessMchtProofExample = new InterventionProcessExample();
					InterventionProcessExample.Criteria interventionProcessMchtProofCriteria = interventionProcessMchtProofExample.createCriteria();
					interventionProcessMchtProofCriteria.andDelFlagEqualTo("0").andOperatorTypeEqualTo("2").andProcessTypeEqualTo("3")
						.andInterventionOrderIdEqualTo(interventionOrderCustom.getId());
					interventionProcessMchtProofExample.setOrderByClause(" create_date desc");
					interventionProcessMchtProofExample.setLimitStart(0);
					interventionProcessMchtProofExample.setLimitSize(2);
					List<InterventionProcess> interventionProcessMchtProofList = interventionProcessService.selectByExample(interventionProcessMchtProofExample);
					for(InterventionProcess interventionProcess : interventionProcessMchtProofList) {
						InterventionProcessPicExample interventionProcessPicMchtProofExample = new InterventionProcessPicExample();
						InterventionProcessPicExample.Criteria interventionProcessPicMchtProofCriteria = interventionProcessPicMchtProofExample.createCriteria();
						interventionProcessPicMchtProofCriteria.andDelFlagEqualTo("0").andInterventionProcessIdEqualTo(interventionProcess.getId());
						interventionProcessPicMchtProofExample.setOrderByClause(" id asc");
						List<InterventionProcessPic> interventionProcessPicList = interventionProcessPicService.selectByExample(interventionProcessPicMchtProofExample);
						Map<String, Object> mapMchtProof = new HashMap<String, Object>();
						mapMchtProof.put("interventionProcess", interventionProcess);
						mapMchtProof.put("interventionProcessPicList", interventionProcessPicList);
						listMap.add(mapMchtProof);
					}
					m.addObject("listMap", listMap); //商家处理凭证
					m.setViewName("/interventionOrder/myCloseInterventionOrderShow");
				}else if(!StringUtil.isEmpty(statusPage) && "5".equals(statusPage)) { //待复审核——查看
					m.addObject("rejectReasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "REJECT_REASON")); //客服拒绝介入理由
					m.setViewName("/interventionOrder/recheckInterventionOrderShow");
				}else if(!StringUtil.isEmpty(statusPage) && "6".equals(statusPage)) { //已结案——查看
					Map<String, Object> mapMchtAppeal = new HashMap<String, Object>();
					InterventionProcessExample interventionProcessMchtAppealExample = new InterventionProcessExample();
					InterventionProcessExample.Criteria interventionProcessMchtAppealCriteria = interventionProcessMchtAppealExample.createCriteria();
					interventionProcessMchtAppealCriteria.andDelFlagEqualTo("0").andOperatorTypeEqualTo("2").andProcessTypeEqualTo("1")
						.andInterventionOrderIdEqualTo(interventionOrderCustom.getId());
					interventionProcessMchtAppealExample.setOrderByClause(" create_date desc");
					interventionProcessMchtAppealExample.setLimitStart(0);
					interventionProcessMchtAppealExample.setLimitSize(1);
					List<InterventionProcess> interventionProcessMchtAppealList = interventionProcessService.selectByExample(interventionProcessMchtAppealExample);
					if(interventionProcessMchtAppealList != null && interventionProcessMchtAppealList.size() > 0) {
						InterventionProcessPicExample interventionProcessPicMchtExample = new InterventionProcessPicExample();
						InterventionProcessPicExample.Criteria interventionProcessPicMchtCriteria = interventionProcessPicMchtExample.createCriteria();
						interventionProcessPicMchtCriteria.andDelFlagEqualTo("0").andInterventionProcessIdEqualTo(interventionProcessMchtAppealList.get(0).getId());
						interventionProcessPicMchtExample.setOrderByClause(" id asc");
						List<InterventionProcessPic> interventionProcessPicList = interventionProcessPicService.selectByExample(interventionProcessPicMchtExample);
						mapMchtAppeal.put("mchtAppealInterventionProcess", interventionProcessMchtAppealList.get(0));
						mapMchtAppeal.put("interventionProcessPicList", interventionProcessPicList);
					}
					m.addObject("mapMchtAppeal", mapMchtAppeal); //商家申诉
					m.addObject("clientResultReasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "CLIENT_RESULT_REASON")); //买家胜诉/败诉理由
					m.addObject("mchtResultReasonList", DataDicUtil.getTableStatus("BU_INTERVENTION_ORDER", "MCHT_RESULT_REASON")); //商家败诉/胜诉理由
					List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
					InterventionProcessExample interventionProcessMchtProofExample = new InterventionProcessExample();
					InterventionProcessExample.Criteria interventionProcessMchtProofCriteria = interventionProcessMchtProofExample.createCriteria();
					interventionProcessMchtProofCriteria.andDelFlagEqualTo("0").andOperatorTypeEqualTo("2").andProcessTypeEqualTo("3")
						.andInterventionOrderIdEqualTo(interventionOrderCustom.getId());
					interventionProcessMchtProofExample.setOrderByClause(" create_date desc");
					interventionProcessMchtProofExample.setLimitStart(0);
					interventionProcessMchtProofExample.setLimitSize(2);
					List<InterventionProcess> interventionProcessMchtProofList = interventionProcessService.selectByExample(interventionProcessMchtProofExample);
					for(InterventionProcess interventionProcess : interventionProcessMchtProofList) {
						InterventionProcessPicExample interventionProcessPicMchtProofExample = new InterventionProcessPicExample();
						InterventionProcessPicExample.Criteria interventionProcessPicMchtProofCriteria = interventionProcessPicMchtProofExample.createCriteria();
						interventionProcessPicMchtProofCriteria.andDelFlagEqualTo("0").andInterventionProcessIdEqualTo(interventionProcess.getId());
						interventionProcessPicMchtProofExample.setOrderByClause(" id asc");
						List<InterventionProcessPic> interventionProcessPicList = interventionProcessPicService.selectByExample(interventionProcessPicMchtProofExample);
						Map<String, Object> mapMchtProof = new HashMap<String, Object>();
						mapMchtProof.put("interventionProcess", interventionProcess);
						mapMchtProof.put("interventionProcessPicList", interventionProcessPicList);
						listMap.add(mapMchtProof);
					}
													
					m.addObject("listMap", listMap); //商家处理凭证
					m.setViewName("/interventionOrder/closeInterventionOrderShow");
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	
	/**
	 * 
	 * @Title updateInterventionOrder   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月8日 下午4:38:27
	 */
	@ResponseBody
	@RequestMapping("/interventionOrder/updateInterventionOrder.shtml")
	public Map<String, Object> updateInterventionOrder(HttpServletRequest request, String statusFlag) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			if(!StringUtil.isEmpty(request.getParameter("interventionOrderId"))) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				map = interventionOrderService.updateInterventionOrder(request, statusFlag, staffBean);
			}else {
				code = "9999";
				msg = "介入单ID为空！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统错误！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title interventionProcessList   
	 * @Description TODO(协商详情)   
	 * @author pengl
	 * @date 2018年4月8日 下午6:07:30
	 */
	@RequestMapping("/interventionOrder/interventionProcessList.shtml")
	public ModelAndView interventionProcessList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/interventionOrder/interventionProcessList");
		String interventionOrderId = request.getParameter("interventionOrderId");
		if(!StringUtil.isEmpty(interventionOrderId)) {
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			InterventionProcessExample interventionProcessExample = new InterventionProcessExample();
			InterventionProcessExample.Criteria interventionProcessCriteria = interventionProcessExample.createCriteria();
			interventionProcessCriteria.andDelFlagEqualTo("0").andInterventionOrderIdEqualTo(Integer.parseInt(interventionOrderId));
			if(!StringUtil.isEmpty(request.getParameter("processType"))) {
				interventionProcessCriteria.andProcessTypeEqualTo(request.getParameter("processType"));
			}
			interventionProcessExample.setOrderByClause(" create_date desc");
			List<InterventionProcess> interventionProcessList = interventionProcessService.selectByExample(interventionProcessExample);
			for(InterventionProcess interventionProcess : interventionProcessList) {
				InterventionProcessPicExample interventionProcessPicExample = new InterventionProcessPicExample();
				InterventionProcessPicExample.Criteria interventionProcessPicCriteria = interventionProcessPicExample.createCriteria();
				interventionProcessPicCriteria.andDelFlagEqualTo("0").andInterventionProcessIdEqualTo(interventionProcess.getId());
				interventionProcessPicExample.setOrderByClause(" id asc");
				List<InterventionProcessPic> interventionProcessPicList = interventionProcessPicService.selectByExample(interventionProcessPicExample);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("interventionProcess", interventionProcess);
				map.put("interventionProcessPicList", interventionProcessPicList);
				listMap.add(map);
			}
			m.addObject("listMap", listMap);
		}
		return m;
	}
	
	/**
	 * 
	 * @Title userInterventionProcessPicList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月9日 上午9:51:21
	 */
	@ResponseBody
	@RequestMapping("/interventionOrder/userInterventionProcessPicList.shtml")
	public Map<String, Object> userInterventionProcessPicList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			if(!StringUtil.isEmpty(request.getParameter("interventionOrderId"))) {
				InterventionProcessExample interventionProcessExample = new InterventionProcessExample();
				InterventionProcessExample.Criteria interventionProcessCriteria = interventionProcessExample.createCriteria();
				interventionProcessCriteria.andDelFlagEqualTo("0").andOperatorTypeEqualTo("1")
					.andInterventionOrderIdEqualTo(Integer.parseInt(request.getParameter("interventionOrderId")));
				interventionProcessExample.setOrderByClause(" create_date asc");
				interventionProcessExample.setLimitStart(0);
				interventionProcessExample.setLimitSize(1);
				List<InterventionProcess> interventionProcessUserList = interventionProcessService.selectByExample(interventionProcessExample);
				if(interventionProcessUserList != null && interventionProcessUserList.size() > 0) {
					InterventionProcessPicExample interventionProcessPicExample = new InterventionProcessPicExample();
					InterventionProcessPicExample.Criteria interventionProcessPicCriteria = interventionProcessPicExample.createCriteria();
					interventionProcessPicCriteria.andDelFlagEqualTo("0").andInterventionProcessIdEqualTo(interventionProcessUserList.get(0).getId());
					interventionProcessPicExample.setOrderByClause(" id asc");
					List<InterventionProcessPic> interventionProcessPicList = interventionProcessPicService.selectByExample(interventionProcessPicExample);
					map.put("interventionProcessPicList", interventionProcessPicList); //协商内容（客户图片）
				}
			}else {
				code = "9999";
				msg = "介入单ID为空！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统错误！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @Title showIsComment   
	 * @Description TODO(查看评价)   
	 * @author pengl
	 * @date 2018年4月10日 下午2:14:38
	 */
	@RequestMapping("/interventionOrder/showIsComment.shtml")
	public ModelAndView showIsComment(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/interventionOrder/showIsComment");
		String interventionOrderId = request.getParameter("interventionOrderId");
		m.addObject("interventionOrderId", interventionOrderId);
		return m;
	}
	
	/**
	 * 
	 * @Title saveClientServiceComment   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月10日 下午5:09:49
	 */
	@ResponseBody
	@RequestMapping("/interventionOrder/saveClientServiceComment.shtml")
	public Map<String, Object> saveClientServiceComment(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			if(!StringUtil.isEmpty(request.getParameter("interventionOrderId"))) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				interventionOrderService.saveClientServiceComment(request, staffBean);
			}else {
				code = "9999";
				msg = "介入单ID为空！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统错误！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	

	/**
	 * 
	 * @Title editUpdateByManager   
	 * @Description TODO(批量变更领取人)   
	 * @author pengl
	 * @date 2018年9月25日 下午2:27:16
	 */
	@RequestMapping("/interventionOrder/editUpdateByManager.shtml")
	public ModelAndView editUpdateByManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/interventionOrder/editUpdateBy");
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
	@RequestMapping("/interventionOrder/editUpdateBy.shtml")
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
				InterventionOrderExample interventionOrderExample = new InterventionOrderExample();
				interventionOrderExample.createCriteria().andIdIn(idList);
				String platformProcessor = request.getParameter("platformProcessor");
				InterventionOrder interventionOrder = new InterventionOrder();
				interventionOrder.setPlatformProcessor(Integer.parseInt(platformProcessor));
				interventionOrder.setUpdateBy(staffId);
				interventionOrder.setUpdateDate(date);
				interventionOrderService.updateByExampleSelective(interventionOrder, interventionOrderExample);
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
	

	//创建介入工单
	@RequestMapping(value = "/interventionOrder/addinterventionOrderWork.shtml")
	public ModelAndView addinterventionOrderWork(HttpServletRequest request) {
		String rtPage = "/interventionOrder/addinterventionOrderWork";
		Map<String, Object> resMap = new HashMap<String, Object>();
		int id=Integer.valueOf(request.getParameter("id"));
		InterventionOrderCustom interventionOrderCustom = interventionOrderService.selectByPrimaryKeyCustom(Integer.valueOf(id));
		resMap.put("interventionOrderCustom", interventionOrderCustom);
		
		SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
		sysOrganizationExample.createCriteria().andStatusEqualTo("A");
		List<SysOrganization> sysOrganizationlist=sysOrganizationService.selectByExample(sysOrganizationExample);			
		resMap.put("sysOrganizationlist",sysOrganizationlist);
		return new ModelAndView(rtPage,resMap);
	}
	
	
	 //添加介入工单
	@ResponseBody
	@RequestMapping("/interventionOrder/addinterventionOrderWorklist.shtml")
	public ModelAndView addinterventionOrderWorklist(HttpServletRequest request, WoRk work,String attachmentName, String attachmentPath,String id) {
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
