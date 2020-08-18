package com.jf.controller;
import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.constant.Const;
import com.jf.common.constant.SysConfig;
import com.jf.common.utils.*;
import com.jf.dao.SysOrganizationMapper;
import com.jf.entity.*;
import com.jf.entity.SubOrderExample.Criteria;
import com.jf.service.*;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ViolateOrderController extends BaseController {
	
	private static final Log logger = LogFactory.getLog(MchtInfoService.class);
	
	@Resource
	private ViolateOrderService violateOrderService;
	
	@Resource
	
	private OrderDtlService orderDtlService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private ViolateComplainOrderService violateComplainOrderService;
	
	@Resource
	private ComplainOrderPicService complainOrderPicService;
	
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	
	@Resource
	private ViolatePlatformRemarksPicService violatePlatformRemarksPicService;
	
	@Resource
	private PlatformContactService platformContactService;
	
	@Resource
	private MchtDepositDtlService mchtDepositDtlService;
	
	@Resource
	private MchtDepositService mchtDepositService;
	
	@Resource
	private SysOrganizationMapper sysOrganizationMapper;
	
	@Resource
	private ViolatePunishStandardService violatePunishStandardService;
	
	@Resource
	private SysStatusService sysStatusService;
	
	@Resource
	private CombineOrderService combineOrderService;
	
	@Resource
	private MemberAccountService memberAccountService;
	
	@Resource
	private MemberInfoService memberInfoService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private MchtContactService mchtContactService;
	
	@Resource
	private SysStaffProductTypeService sysstaffproductTypeService;
	
	@Resource
	private SysStaffRoleService sysStaffRoleService;
	
	@Resource
	private MchtPlatformContactService mchtPlatformContactService;
	
	private static final String VIOLATEORDER_CODE = "WG";
	
	private static final String OPERATION_CENTER = "运营中心";

	private static final long serialVersionUID = 1L;
	
	/**
	 * 违规单详情
	 * @param request
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/view.shtml")
	public ModelAndView view(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String role = request.getParameter("role");
		String rtPage = "/violateOrder/view";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			int id=Integer.valueOf(request.getParameter("id"));
			ViolateOrder violateOrder = violateOrderService.selectByPrimaryKey(id);
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(violateOrder.getMchtId());
			ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
			ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
			violateOrderCustomCriteria.andMchtIdEqualTo(violateOrder.getMchtId());
			int totalViolateCount = violateOrderService.countViolateOrderCustomByExample(violateOrderCustomExample);
			ViolateOrderCustomExample example = new ViolateOrderCustomExample();
			ViolateOrderCustomExample.ViolateOrderCustomCriteria criteria = example.createCriteria();
			criteria.andMchtIdEqualTo(violateOrder.getMchtId());
			criteria.andCreateDateGreaterThanOrEqualTo(DateUtil.getDateAfter(new Date(), -30));
			criteria.andCreateDateLessThanOrEqualTo(new Date());
			int thirtyDaysViolateCount = violateOrderService.countViolateOrderCustomByExample(example);
			if(violateOrder.getSubOrderId()!=null){
				SubOrder subOrder = subOrderService.selectByPrimaryKey(violateOrder.getSubOrderId());
				if(subOrder!=null){
					resMap.put("subOrderCode", subOrder.getSubOrderCode());
				}
			}
			resMap.put("role",role);
			resMap.put("violateOrder", violateOrder);
			resMap.put("mchtInfo", mchtInfo);
			resMap.put("totalViolateCount", totalViolateCount);
			resMap.put("thirtyDaysViolateCount", thirtyDaysViolateCount);
			resMap.put("statusDesc", DataDicUtil.getStatusDesc("BU_VIOLATE_ORDER", "STATUS", violateOrder.getStatus()));
			resMap.put("violateTypeDesc", DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE", violateOrder.getViolateType()));
			resMap.put("violateActionDesc", DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION", violateOrder.getViolateAction()));
			resMap.put("orderSourceDesc", DataDicUtil.getStatusDesc("BU_VIOLATE_ORDER", "ORDER_SOURCE", violateOrder.getOrderSource()));
			resMap.put("auditStatusDesc", DataDicUtil.getStatusDesc("BU_VIOLATE_ORDER", "AUDIT_STATUS", violateOrder.getAuditStatus()));

			//获取订单信息
			SubOrderCustom subOrderCustom=subOrderService.selectSubOrderCustomByPrimaryKey(violateOrder.getSubOrderId());
			resMap.put("subOrderCustom", subOrderCustom);


			ViolateComplainOrderCustomExample violateComplainOrderCustomExample = new ViolateComplainOrderCustomExample();
			violateComplainOrderCustomExample.setOrderByClause("t.id desc");
			ViolateComplainOrderCustomExample.ViolateComplainOrderCustomCriteria violateComplainOrderCustomCriteria = violateComplainOrderCustomExample.createCriteria();
			violateComplainOrderCustomCriteria.andDelFlagEqualTo("0");
			violateComplainOrderCustomCriteria.andViolateOrderIdEqualTo(violateOrder.getId());
			List<ViolateComplainOrderCustom> violateComplainOrderCustoms = violateComplainOrderService.selectViolateComplainOrderCustomByExample(violateComplainOrderCustomExample);
			if(violateComplainOrderCustoms!=null && violateComplainOrderCustoms.size()>0){
				for(ViolateComplainOrderCustom violateComplainOrderCustom:violateComplainOrderCustoms){
					ComplainOrderPicExample complainOrderPicExample = new ComplainOrderPicExample();
					ComplainOrderPicExample.Criteria c = complainOrderPicExample.createCriteria();
					c.andDelFlagEqualTo("0");
					c.andOperTypeEqualTo("2");
					c.andComplainOrderIdEqualTo(violateComplainOrderCustom.getId());
					List<ComplainOrderPic> mchtComplainOrderPics = complainOrderPicService.selectByExample(complainOrderPicExample);
					violateComplainOrderCustom.setMchtComplainOrderPics(mchtComplainOrderPics);
					
					ComplainOrderPicExample cope = new ComplainOrderPicExample();
					ComplainOrderPicExample.Criteria ca = cope.createCriteria();
					ca.andDelFlagEqualTo("0");
					ca.andOperTypeEqualTo("1");
					ca.andComplainOrderIdEqualTo(violateComplainOrderCustom.getId());
					List<ComplainOrderPic> platformComplainOrderPics = complainOrderPicService.selectByExample(cope);
					violateComplainOrderCustom.setPlatFormComplainOrderPics(platformComplainOrderPics);
				}
				
			}
			resMap.put("violateComplainOrderCustoms", violateComplainOrderCustoms);
			resMap.put("staffId", Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			String contactName = platformContactService.getContactNameByMchtIdAndContactType(violateOrder.getMchtId(),2);//2.对接运营
			resMap.put("contactName", contactName);
			if(violateOrder.getCreateBy()!=null){
				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(violateOrder.getCreateBy());
				resMap.put("staffName", sysStaffInfo.getStaffName());
			}
			ViolatePlatformRemarksPicExample e = new ViolatePlatformRemarksPicExample();
			ViolatePlatformRemarksPicExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andViolateOrderIdEqualTo(id);
			List<ViolatePlatformRemarksPic> violatePlatformRemarksPics = violatePlatformRemarksPicService.selectByExample(e);
			resMap.put("violatePlatformRemarksPics", violatePlatformRemarksPics);
			if(!StringUtils.isEmpty(violateOrder.getEnclosure())){
				resMap.put("enclosureName", violateOrder.getEnclosure().substring(violateOrder.getEnclosure().lastIndexOf("/")+1));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 人工违规管理列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/manuallyViolateOrderList.shtml")
	public ModelAndView manuallyViolateOrderList(HttpServletRequest request) {
		String rtPage = "/violateOrder/manuallyViolateOrderList";
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("violateTypeList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE"));
		resMap.put("auditStatusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "AUDIT_STATUS"));
		resMap.put("statusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "STATUS"));
		resMap.put("violateActionList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION"));

		//新增的挂起状态列
		resMap.put("suspendedStatusList",DataDicUtil.getStatusList("BU_VIOLATE_ORDER","SUSPENDED_STATUS"));

		resMap.put("staffId", staffID);
		List<Map<String, Object>> createByInfos = violateOrderService.getAllCreateBy();
		resMap.put("createByInfos", createByInfos);

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
	 * 人工违规管理列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/manuallyData.shtml")
	@ResponseBody
	public Map<String, Object> manuallyData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
			violateOrderCustomExample.setOrderByClause("t.create_date desc");
			ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
			violateOrderCustomCriteria.andProductTypeIdEqualTo(staffID);
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				violateOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				violateOrderCustomCriteria.andDelFlagEqualTo("0");
			}
			violateOrderCustomCriteria.andOrderSourceEqualTo("2");
			String searchOrderCode = request.getParameter("orderCode");
			String searchViolateType = request.getParameter("violateType");
			String searchViolateAction = request.getParameter("violateAction");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchAuditStatus = request.getParameter("auditStatus");
			String searchStatus = request.getParameter("status");
			String searchSubOrderCode = request.getParameter("subOrderCode");
			String searchName = request.getParameter("name");

			//新增挂起状态
			String searchSuspendedStatus = request.getParameter("suspendedStatus");
			if (!StringUtil.isEmpty(searchSuspendedStatus)){
				violateOrderCustomCriteria.andSuspendedStatusEqualTo(searchSuspendedStatus);
			}

			if(!StringUtil.isEmpty(searchOrderCode)){
				violateOrderCustomCriteria.andOrderCodeEqualTo(searchOrderCode);
			}
			if(!StringUtil.isEmpty(searchViolateType)){
				violateOrderCustomCriteria.andViolateTypeEqualTo(searchViolateType);
			}
			if(!StringUtil.isEmpty(searchViolateAction)){
				violateOrderCustomCriteria.andViolateActionEqualTo(searchViolateAction);
			}
			if(!StringUtil.isEmpty(searchMchtCode)){
				violateOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchAuditStatus)){
				violateOrderCustomCriteria.andAuditStatusEqualTo(searchAuditStatus);
			}
			if(!StringUtil.isEmpty(searchStatus)){
				violateOrderCustomCriteria.andStatusEqualTo(searchStatus);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin"))){
				violateOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end"))){
				violateOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			String searchPlatformProcessor = request.getParameter("platformProcessor");
			if(!StringUtil.isEmpty(searchPlatformProcessor)){
				SysStaffInfoExample example = new SysStaffInfoExample();
				SysStaffInfoExample.Criteria criteria = example.createCriteria();
				criteria.andStaffNameLike("%"+searchPlatformProcessor+"%");
				List<SysStaffInfo> sysStaffInfos = sysStaffInfoService.selectByExample(example);
				List<Integer> platformProcessorIds = new ArrayList<Integer>();
				for(SysStaffInfo sysStaffInfo:sysStaffInfos){
					platformProcessorIds.add(sysStaffInfo.getStaffId());
				}
				violateOrderCustomCriteria.andPlatformProcessorIn(platformProcessorIds);
			}
			if(!StringUtil.isEmpty(searchSubOrderCode)){
				violateOrderCustomCriteria.andSubOrderCodeEqualTo(searchSubOrderCode);
			}
			if(!StringUtil.isEmpty(searchName)){
				violateOrderCustomCriteria.andNameLikeTo(searchName);
			}

			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					violateOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
					violateOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
				}
			}
			
			totalCount = violateOrderService.countViolateOrderCustomByExample(violateOrderCustomExample);
			violateOrderCustomExample.setLimitStart(page.getLimitStart());
			violateOrderCustomExample.setLimitSize(page.getLimitSize());
			List<ViolateOrderCustom> violateOrderCustoms = violateOrderService.selectViolateOrderCustomByExample(violateOrderCustomExample);
			resMap.put("Rows", violateOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 获取商家信息
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/violateOrder/mchtInfoData.shtml")
	public Map<String, Object> mchtInfoData(HttpServletRequest request,HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap, Page page){
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;
		String shortName = "";
		String mchtCode = "";
		try {
			if (paramMap.get("condition") != null) {
				JSONArray fromObject = JSONArray.fromObject(paramMap.get("condition"));
				for(int i=0;i<fromObject.size();i++){
					JSONObject jo = (JSONObject)fromObject.get(i);
					if(jo.getString("field").equals("shortName")){
						shortName = jo.getString("value");
					}
					if(jo.getString("field").equals("mchtCode")){
						mchtCode = jo.getString("value");
					}
				}
			}
			MchtInfoExample mchtInfoExample = new MchtInfoExample();
			MchtInfoExample.Criteria criteria = mchtInfoExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			mchtInfoExample.setLimitStart(page.getLimitStart());
			mchtInfoExample.setLimitSize(page.getLimitSize());
			if (!StringUtil.isEmpty(shortName)) {
				criteria.andShortNameLike("%"+shortName+"%");
			}
			if (!StringUtil.isEmpty(mchtCode)) {
				criteria.andMchtCodeEqualTo(mchtCode);
			}
			totalCount = mchtInfoService.countByExample(mchtInfoExample);
			List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtInfoExample);
			resMap.put("Rows", mchtInfos);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 人工违规查看列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/manuallyViolateOrderViewList.shtml")
	public ModelAndView manuallyViolateOrderViewList(HttpServletRequest request) {
		String rtPage = "/violateOrder/manuallyViolateOrderViewList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("violateTypeList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "VIOLATE_TYPE"));
		resMap.put("auditStatusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "AUDIT_STATUS"));
		resMap.put("staffId", Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 人工违规查看列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/manuallyViewData.shtml")
	@ResponseBody
	public Map<String, Object> manuallyViewData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
			ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				violateOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				violateOrderCustomCriteria.andDelFlagEqualTo("0");
			}
			violateOrderCustomCriteria.andOrderSourceEqualTo("2");
			String searchOrderCode = request.getParameter("orderCode");
			String searchViolateType = request.getParameter("violateType");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchAuditStatus = request.getParameter("auditStatus");
			if(!StringUtil.isEmpty(searchOrderCode)){
				violateOrderCustomCriteria.andOrderCodeEqualTo(searchOrderCode);
			}
			if(!StringUtil.isEmpty(searchViolateType)){
				violateOrderCustomCriteria.andViolateTypeEqualTo(searchViolateType);
			}
			if(!StringUtil.isEmpty(searchMchtCode)){
				violateOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchAuditStatus)){
				violateOrderCustomCriteria.andAuditStatusEqualTo(searchAuditStatus);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin"))){
				violateOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end"))){
				violateOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			String searchPlatformProcessor = request.getParameter("platformProcessor");
			if(!StringUtil.isEmpty(searchPlatformProcessor)){
				SysStaffInfoExample example = new SysStaffInfoExample();
				SysStaffInfoExample.Criteria criteria = example.createCriteria();
				criteria.andStaffNameLike("%"+searchPlatformProcessor+"%");
				List<SysStaffInfo> sysStaffInfos = sysStaffInfoService.selectByExample(example);
				List<Integer> platformProcessorIds = new ArrayList<Integer>();
				for(SysStaffInfo sysStaffInfo:sysStaffInfos){
					platformProcessorIds.add(sysStaffInfo.getStaffId());
				}
				violateOrderCustomCriteria.andPlatformProcessorIn(platformProcessorIds);
			}
			totalCount = violateOrderService.countViolateOrderCustomByExample(violateOrderCustomExample);
			violateOrderCustomExample.setLimitStart(page.getLimitStart());
			violateOrderCustomExample.setLimitSize(page.getLimitSize());
			List<ViolateOrderCustom> violateOrderCustoms = violateOrderService.selectViolateOrderCustomByExample(violateOrderCustomExample);
			resMap.put("Rows", violateOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 违规处罚标准管理列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/violatePunishStandardList.shtml")
	public ModelAndView violatePunishStandardList(HttpServletRequest request) {
		String rtPage = "/violateOrder/violatePunishStandardList";
		List<SysStatus> violateTypeList = DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE");
		List<SysStatus> violateActionList = DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION");
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("violateTypeList", violateTypeList);
		resMap.put("violateActionList", violateActionList);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 违规处罚标准管理列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/violatePunishStandardData.shtml")
	@ResponseBody
	public Map<String, Object> violatePunishStandardData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			ViolatePunishStandardCustomExample violatePunishStandardCustomExample = new ViolatePunishStandardCustomExample();
			violatePunishStandardCustomExample.setOrderByClause("t.seq_no,t.id asc");
			ViolatePunishStandardCustomExample.ViolatePunishStandardCustomCriteria criteria = violatePunishStandardCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			String violateType = request.getParameter("violateType");
			String violateAction = request.getParameter("violateAction");
			String content = request.getParameter("content");
			if(!StringUtil.isEmpty(violateType)){
				criteria.andViolateTypeEqualTo(violateType);
			}
			if(!StringUtil.isEmpty(violateAction)){
				criteria.andViolateActionEqualTo(violateAction);
			}
			if(!StringUtil.isEmpty(content)){
				criteria.andContentLike("%"+content+"%");
			}
			
			totalCount = violatePunishStandardService.countViolatePunishStandardCustomByExample(violatePunishStandardCustomExample);
			violatePunishStandardCustomExample.setLimitStart(page.getLimitStart());
			violatePunishStandardCustomExample.setLimitSize(page.getLimitSize());
			List<ViolatePunishStandardCustom> violatePunishStandardCustoms = violatePunishStandardService.selectViolatePunishStandardCustomByExample(violatePunishStandardCustomExample);
			resMap.put("Rows", violatePunishStandardCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 获取违规子类（行为）
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/getViolateActions.shtml")
	@ResponseBody
	public Map<String, Object> getViolateActions(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String violateType = request.getParameter("violateType");
			SysStatusExample example = new SysStatusExample();
			SysStatusExample.Criteria criteria = example.createCriteria();
			criteria.andTableNameEqualTo("BU_VIOLATE_PUNISH_STANDARD");
			criteria.andColNameEqualTo("VIOLATE_ACTION");
			criteria.andStatusValueLike("%"+violateType+"%");
			List<SysStatus> sysStatusList = sysStatusService.selectByExample(example);
			resMap.put("violateActions", sysStatusList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 获取违规处罚标准
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/getViolatePunishStandards.shtml")
	@ResponseBody
	public Map<String, Object> getViolatePunishStandards(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String violateType = request.getParameter("violateType");
			String violateAction = request.getParameter("violateAction");
			ViolatePunishStandardExample example = new ViolatePunishStandardExample();
			ViolatePunishStandardExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andViolateTypeEqualTo(violateType);
			criteria.andViolateActionEqualTo(violateAction);
			List<ViolatePunishStandard> violatePunishStandards = violatePunishStandardService.selectByExample(example);
			resMap.put("violatePunishStandards", violatePunishStandards);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 违规处罚标准查看列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/violatePunishStandardViewList.shtml")
	public ModelAndView violatePunishStandardViewList(HttpServletRequest request) {
		String rtPage = "/violateOrder/violatePunishStandardViewList";
		List<SysStatus> violateTypeList = DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE");
		List<SysStatus> complainFlagList = DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "COMPLAIN_FLAG");
		List<SysStatus> violateActionList = DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION");
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("violateTypeList", violateTypeList);
		resMap.put("complainFlagList", complainFlagList);
		resMap.put("violateActionList", violateActionList);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 违规处罚标准查看列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/violatePunishStandardViewData.shtml")
	@ResponseBody
	public Map<String, Object> violatePunishStandardViewData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			ViolatePunishStandardCustomExample violatePunishStandardCustomExample = new ViolatePunishStandardCustomExample();
			violatePunishStandardCustomExample.setOrderByClause("t.seq_no,t.id asc");
			ViolatePunishStandardCustomExample.ViolatePunishStandardCustomCriteria criteria = violatePunishStandardCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			String violateType = request.getParameter("violateType");
			String violateAction = request.getParameter("violateAction");
			String content = request.getParameter("content");
			String complainFlag = request.getParameter("complainFlag");
			if(!StringUtil.isEmpty(violateType)){
				criteria.andViolateTypeEqualTo(violateType);
			}
			if(!StringUtil.isEmpty(violateAction)){
				criteria.andViolateActionEqualTo(violateAction);
			}
			if(!StringUtil.isEmpty(content)){
				criteria.andContentLike("%"+content+"%");
			}
			if(!StringUtil.isEmpty(complainFlag)){
				criteria.andComplainFlagEqualTo(complainFlag);
			}
			
			totalCount = violatePunishStandardService.countViolatePunishStandardCustomByExample(violatePunishStandardCustomExample);
			violatePunishStandardCustomExample.setLimitStart(page.getLimitStart());
			violatePunishStandardCustomExample.setLimitSize(page.getLimitSize());
			List<ViolatePunishStandardCustom> violatePunishStandardCustoms = violatePunishStandardService.selectViolatePunishStandardCustomByExample(violatePunishStandardCustomExample);
			resMap.put("Rows", violatePunishStandardCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 添加/编辑违规处罚标准
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/editViolatePunishStandard.shtml")
	public ModelAndView editViolatePunishStandard(HttpServletRequest request) {
		String rtPage = "/violateOrder/editViolatePunishStandard";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			ViolatePunishStandard violatePunishStandard = violatePunishStandardService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("violatePunishStandard", violatePunishStandard);
			SysStatusExample example = new SysStatusExample();
			SysStatusExample.Criteria criteria = example.createCriteria();
			criteria.andTableNameEqualTo("BU_VIOLATE_PUNISH_STANDARD");
			criteria.andColNameEqualTo("VIOLATE_ACTION");
			criteria.andStatusValueLike("%"+violatePunishStandard.getViolateType()+"%");
			List<SysStatus> sysStatusList = sysStatusService.selectByExample(example);
			resMap.put("violateActions", sysStatusList);
		}
		List<SysStatus> violateTypeList = DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE");
		resMap.put("violateTypeList", violateTypeList);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存违规处罚标准
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/saveViolatePunishStandard.shtml")
	@ResponseBody
	public Map<String, Object> saveViolatePunishStandard(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String violateType = request.getParameter("violateType");
			String violateAction = request.getParameter("violateAction");
			String content = request.getParameter("content");
			String punishStandard = request.getParameter("punishStandard");
			String punishOther = request.getParameter("punishOther");
			String complainFlag = request.getParameter("complainFlag");
			String enableHours = request.getParameter("enableHours");
			String jifenIntegral = request.getParameter("jifenIntegral");
			String jifenIntegralDesc = request.getParameter("jifenIntegralDesc");
			String seqNo = request.getParameter("seqNo");
			//支付违约金模式            0.N元起          1.按比例
			String paymentBreachModel = request.getParameter("paymentBreachModel");
			//违约金扣除额度
			String breachDeductionQuota = request.getParameter("breachDeductionQuota");
			//最低扣款额度
			String minDeductionQuota = request.getParameter("minDeductionQuota");
			//积分补偿模式 0.固定积分 1.按比例 2.不发放
			String integralCompensateModel = request.getParameter("integralCompensateModel");
			
			ViolatePunishStandardExample example = new ViolatePunishStandardExample();
			ViolatePunishStandardExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andViolateTypeEqualTo(violateType);
			criteria.andViolateActionEqualTo(violateAction);
			criteria.andContentEqualTo(content);
			List<ViolatePunishStandard> violatePunishStandards = violatePunishStandardService.selectByExample(example);
			if(violatePunishStandards!=null && violatePunishStandards.size()>0 && StringUtils.isEmpty(id)){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "违规内容不能重复，请重新填写");
				return resMap;
			}
			ViolatePunishStandard violatePunishStandard = new ViolatePunishStandard();
			if(!StringUtils.isEmpty(id)){
				violatePunishStandard = violatePunishStandardService.selectByPrimaryKey(Integer.parseInt(id));
			}else{
				violatePunishStandard.setDelFlag("0");
				violatePunishStandard.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				violatePunishStandard.setCreateDate(new Date());
			}
			violatePunishStandard.setViolateType(violateType);
			violatePunishStandard.setViolateAction(violateAction);
			violatePunishStandard.setContent(content);
			violatePunishStandard.setPunishStandard(punishStandard);
			violatePunishStandard.setPunishOther(punishOther);
			violatePunishStandard.setComplainFlag(complainFlag);
			if(complainFlag.equals("1")){//可申诉
				violatePunishStandard.setEnableHours(enableHours);
			}else{//不可申诉
				violatePunishStandard.setEnableHours("");
			}
			if(!StringUtils.isEmpty(jifenIntegral)){
				if("0".equals(integralCompensateModel)){
					violatePunishStandard.setJifenIntegral(Integer.parseInt(jifenIntegral));
				}
				if("1".equals(integralCompensateModel)){
					violatePunishStandard.setIntegralCompensateRate(new BigDecimal(jifenIntegral));
				}
			}
		
			if(!StringUtils.isEmpty(paymentBreachModel)){ 
				violatePunishStandard.setPaymentBreachModel(paymentBreachModel);
			}
			if(!StringUtils.isEmpty(breachDeductionQuota)){
				violatePunishStandard.setBreachDeductionQuota(new BigDecimal(breachDeductionQuota));
			}
			if(!StringUtils.isEmpty(minDeductionQuota)){
				violatePunishStandard.setMinDeductionQuota(new BigDecimal(minDeductionQuota));
			}
			if(!StringUtils.isEmpty(integralCompensateModel)){
				violatePunishStandard.setIntegralCompensateModel(integralCompensateModel);
			}
			violatePunishStandard.setJifenIntegralDesc(jifenIntegralDesc);
			violatePunishStandard.setSeqNo(Integer.parseInt(seqNo));
			violatePunishStandard.setUpdateDate(new Date());
			violatePunishStandardService.save(violatePunishStandard);
			
			if(!StringUtils.isEmpty(paymentBreachModel)){
				if(Integer.parseInt(paymentBreachModel)==0&&!StringUtils.isEmpty(id)){
					violateOrderService.setMinDeductionQuotaNull(Integer.parseInt(id));
				}
			}
			if(StringUtils.isEmpty(minDeductionQuota)){
				if(!StringUtils.isEmpty(id)){
					violateOrderService.setMinDeductionQuotaNull(Integer.parseInt(id));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 删除违规处罚标准
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/deleteViolatePunishStandard.shtml")
	@ResponseBody
	public Map<String, Object> deleteViolatePunishStandard(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "删除成功");
		try {
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(id)){
				ViolatePunishStandard violatePunishStandard = violatePunishStandardService.selectByPrimaryKey(Integer.parseInt(id));
				if(violatePunishStandard!=null){
					violatePunishStandard.setDelFlag("1");
					violatePunishStandardService.updateByPrimaryKeySelective(violatePunishStandard);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 查看全部违规信息列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/allViolateOrderList.shtml")
	public ModelAndView allViolateOrderList(HttpServletRequest request) {
		String rtPage = "/violateOrder/allViolateOrderList";
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("violateTypeList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE"));
		resMap.put("auditStatusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "AUDIT_STATUS"));
		resMap.put("statusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "STATUS"));
		resMap.put("orderSourceList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "ORDER_SOURCE"));
		resMap.put("jifenStatusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "JIFEN_STATUS"));
		resMap.put("violateActionList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION"));
		List<Map<String, Object>> createByInfos = violateOrderService.getAllCreateBy();
		resMap.put("createByInfos", createByInfos);
		
		resMap.put("getplatformProcessorList", violateOrderService.getplatformProcessorList());//平台处理人
		
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
		
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact);
        
		resMap.put("isContact", isContact);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 查看全部违规信息列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/allViolateOrderData.shtml")
	@ResponseBody
	public Map<String, Object> allViolateOrderData(HttpServletRequest request,Page page) {
		String role = "1";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
			violateOrderCustomExample.setOrderByClause("t.create_date desc");
			ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
			violateOrderCustomCriteria.andDelFlagEqualTo("0");
			violateOrderCustomCriteria.andProductTypeIdEqualTo(staffID);
			String searchOrderSource = request.getParameter("orderSource");
			String searchOrderCode = request.getParameter("orderCode");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchName = request.getParameter("name");
			String searchSubOrderCode = request.getParameter("subOrderCode");
			String searchViolateType = request.getParameter("violateType");
			String searchViolateAction = request.getParameter("violateAction");
			String searchCreateBy = request.getParameter("createBy");
			String searchAuditStatus = request.getParameter("auditStatus");
			String searchStatus = request.getParameter("status");
			String searchJifenStatus = request.getParameter("jifenStatus");
			String violateDateBegin = request.getParameter("violate_date_begin");
			String violateDateEnd = request.getParameter("violate_date_end");
			String complainDateBegin = request.getParameter("complain_date_begin");
			String complainDateEnd = request.getParameter("complain_date_end");
			String createDateBegin = request.getParameter("create_date_begin");
			String createDateEnd = request.getParameter("create_date_end");
			if(!StringUtil.isEmpty(searchOrderSource)){
				violateOrderCustomCriteria.andOrderSourceEqualTo(searchOrderSource);
			}
			if(!StringUtil.isEmpty(searchOrderCode)){
				violateOrderCustomCriteria.andOrderCodeEqualTo(searchOrderCode);
			}
			if(!StringUtil.isEmpty(searchMchtCode)){
				violateOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchName)){
				violateOrderCustomCriteria.andNameLikeTo(searchName);
			}
			if(!StringUtil.isEmpty(searchSubOrderCode)){
				violateOrderCustomCriteria.andSubOrderCodeEqualTo(searchSubOrderCode);
			}
			if(!StringUtil.isEmpty(searchViolateType)){
				violateOrderCustomCriteria.andViolateTypeEqualTo(searchViolateType);
			}
			if(!StringUtil.isEmpty(searchViolateAction)){
				violateOrderCustomCriteria.andViolateActionEqualTo(searchViolateAction);
			}
			if(!StringUtil.isEmpty(searchCreateBy)){
				violateOrderCustomCriteria.andCreateByEqualTo(Integer.parseInt(searchCreateBy));
			}
			if(!StringUtil.isEmpty(searchAuditStatus)){
				violateOrderCustomCriteria.andAuditStatusEqualTo(searchAuditStatus);
			}
			if(!StringUtil.isEmpty(searchStatus)){
				violateOrderCustomCriteria.andStatusEqualTo(searchStatus);
			}
			if(!StringUtil.isEmpty(searchJifenStatus)){
				violateOrderCustomCriteria.andJifenStatusEqualTo(searchJifenStatus);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(violateDateBegin)){
				violateOrderCustomCriteria.andViolateDateGreaterThanOrEqualTo(dateFormat.parse(violateDateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(violateDateEnd)){
				violateOrderCustomCriteria.andViolateDateLessThanOrEqualTo(dateFormat.parse(violateDateEnd+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(complainDateBegin)){
				violateOrderCustomCriteria.andComplainDateGreaterThanOrEqualTo(dateFormat.parse(complainDateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(complainDateEnd)){
				violateOrderCustomCriteria.andComplainDateLessThanOrEqualTo(dateFormat.parse(complainDateEnd+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(createDateBegin)){
				violateOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(createDateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(createDateEnd)){
				violateOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(createDateEnd+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				violateOrderCustomCriteria.andPlatformContactEqualTo(platformContactId);
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					violateOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
					violateOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("platformProcessor"))){//平台处理人集合
				violateOrderCustomCriteria.andPlatformProcessorEqualTo(Integer.valueOf(request.getParameter("platformProcessor")));
			}
			totalCount = violateOrderService.countViolateOrderCustomByExample(violateOrderCustomExample);
			violateOrderCustomExample.setLimitStart(page.getLimitStart());
			violateOrderCustomExample.setLimitSize(page.getLimitSize());
			List<ViolateOrderCustom> violateOrderCustoms = violateOrderService.selectViolateOrderCustomByExample(violateOrderCustomExample);
			resMap.put("Rows", violateOrderCustoms);
			resMap.put("Total", totalCount);
			resMap.put("role",role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 违规申诉中列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/violateComplainList.shtml")
	public ModelAndView violateComplainList(HttpServletRequest request) {
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		String rtPage = "/violateOrder/violateComplainList";
		Map<String, Object> resMap = new HashMap<String, Object>();

		String type = request.getParameter("type");
		resMap.put("type",type);

		resMap.put("violateTypeList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE"));
		resMap.put("violateActionList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION"));
		List<Map<String, Object>> createByInfos = violateOrderService.getAllCreateBy();
		resMap.put("createByInfos", createByInfos);
		List<Map<String, Object>> procesByInfos = violateComplainOrderService.getAllProcesBy();
		resMap.put("procesByInfos", procesByInfos);


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
		
		Integer isContact = 0; //默认不是对接人
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact);
        
		resMap.put("isContact", isContact);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 违规申诉中数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/violateComplainData.shtml")
	@ResponseBody
	public Map<String, Object> violateComplainData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;



		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
			violateOrderCustomExample.setOrderByClause("t.complain_date asc");
			/*violateOrderCustomExample.setOrderByClause("t.violate_date desc");*/
			ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
			violateOrderCustomCriteria.andDelFlagEqualTo("0");
			violateOrderCustomCriteria.andComplainDateIsNotNull();
			violateOrderCustomCriteria.andProductTypeIdEqualTo(staffID);
			List<String> statusList = new ArrayList<String>();
			statusList.add("3");
			statusList.add("5");
			statusList.add("6");
			violateOrderCustomCriteria.andStatusIn(statusList);

			//suspended_status字段用来区分正常和挂起
			String suspendedStatus = request.getParameter("suspendedStatus");
			if (!StringUtil.isEmpty(suspendedStatus)){
				violateOrderCustomCriteria.andSuspendedStatusEqualTo(suspendedStatus);
			}

			String searchCheckStatus = request.getParameter("checkStatus");
			String searchOrderCode = request.getParameter("orderCode");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchName = request.getParameter("name");
			String searchSubOrderCode = request.getParameter("subOrderCode");
			String searchViolateType = request.getParameter("violateType");
			String searchViolateAction = request.getParameter("violateAction");
			String searchCreateBy = request.getParameter("createBy");
			String searchProcesBy = request.getParameter("procesBy");
			String violateDateBegin = request.getParameter("violate_date_begin");
			String violateDateEnd = request.getParameter("violate_date_end");
			String complainDateBegin = request.getParameter("complain_date_begin");
			String complainDateEnd = request.getParameter("complain_date_end");

			if(!StringUtil.isEmpty(searchCheckStatus)){
				if(searchCheckStatus.equals("2")){//申诉中（待审核）
					violateOrderCustomCriteria.andStatusEqualTo("3");
					violateOrderCustomCriteria.andComplainInfoStatusEqualTo("2");
				}else if(searchCheckStatus.equals("1")){//申诉中（待补充）
					violateOrderCustomCriteria.andStatusEqualTo("3");
					violateOrderCustomCriteria.andComplainInfoStatusEqualTo("1");
				}else{
					violateOrderCustomCriteria.andStatusEqualTo(searchCheckStatus);
				}
			}
			if(!StringUtil.isEmpty(searchOrderCode)){
				violateOrderCustomCriteria.andOrderCodeEqualTo(searchOrderCode);
			}
			if(!StringUtil.isEmpty(searchMchtCode)){
				violateOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchName)){
				violateOrderCustomCriteria.andNameLikeTo(searchName);
			}
			if(!StringUtil.isEmpty(searchSubOrderCode)){
				violateOrderCustomCriteria.andSubOrderCodeEqualTo(searchSubOrderCode);
			}
			if(!StringUtil.isEmpty(searchViolateType)){
				violateOrderCustomCriteria.andViolateTypeEqualTo(searchViolateType);
			}
			if(!StringUtil.isEmpty(searchViolateAction)){
				violateOrderCustomCriteria.andViolateActionEqualTo(searchViolateAction);
			}
			if(!StringUtil.isEmpty(searchCreateBy)){
				violateOrderCustomCriteria.andCreateByEqualTo(Integer.parseInt(searchCreateBy));
			}
			if(!StringUtil.isEmpty(searchProcesBy)){
				violateOrderCustomCriteria.andProcesByEqualTo(Integer.parseInt(searchProcesBy));
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(violateDateBegin)){
				violateOrderCustomCriteria.andViolateDateGreaterThanOrEqualTo(dateFormat.parse(violateDateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(violateDateEnd)){
				violateOrderCustomCriteria.andViolateDateLessThanOrEqualTo(dateFormat.parse(violateDateEnd+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(complainDateBegin)){
				violateOrderCustomCriteria.andComplainDateGreaterThanOrEqualTo(dateFormat.parse(complainDateBegin+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(complainDateEnd)){
				violateOrderCustomCriteria.andComplainDateLessThanOrEqualTo(dateFormat.parse(complainDateEnd+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				violateOrderCustomCriteria.andPlatformContactEqualTo(platformContactId);
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					violateOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
					violateOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
				}
			}
			
			totalCount = violateOrderService.countViolateOrderCustomByExample(violateOrderCustomExample);
			violateOrderCustomExample.setLimitStart(page.getLimitStart());
			violateOrderCustomExample.setLimitSize(page.getLimitSize());
			List<ViolateOrderCustom> violateOrderCustoms = violateOrderService.selectViolateOrderCustomByExample(violateOrderCustomExample);
			resMap.put("Rows", violateOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 编辑人工发起违规
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/editManuallyViolateOrder.shtml")
	public ModelAndView editManuallyViolateOrder(HttpServletRequest request,String remarkPics) {
		String rtPage = "/violateOrder/editManuallyViolateOrder";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("violateTypeList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE"));
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			ViolateOrder violateOrder = violateOrderService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("violateOrder", JSONObject.fromObject(violateOrder));
			resMap.put("type", violateOrder.getViolateType());
			if(violateOrder.getSubOrderId()!=null){
				SubOrder subOrder = subOrderService.selectByPrimaryKey(violateOrder.getSubOrderId());
				resMap.put("subOrderCode", subOrder.getSubOrderCode());
			}
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(violateOrder.getMchtId());
			resMap.put("mchtCode", mchtInfo.getMchtCode());
			SysStatusExample example = new SysStatusExample();
			SysStatusExample.Criteria criteria = example.createCriteria();
			criteria.andTableNameEqualTo("BU_VIOLATE_PUNISH_STANDARD");
			criteria.andColNameEqualTo("VIOLATE_ACTION");
			criteria.andStatusValueLike("%"+violateOrder.getViolateType()+"%");
			List<SysStatus> sysStatusList = sysStatusService.selectByExample(example);
			resMap.put("violateActions", sysStatusList);
			ViolatePunishStandardExample vpse = new ViolatePunishStandardExample();
			ViolatePunishStandardExample.Criteria c = vpse.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andViolateTypeEqualTo(violateOrder.getViolateType());
			c.andViolateActionEqualTo(violateOrder.getViolateAction());
			List<ViolatePunishStandard> violatePunishStandards = violatePunishStandardService.selectByExample(vpse);
			resMap.put("violatePunishStandards", violatePunishStandards);
			ViolatePlatformRemarksPicExample e = new ViolatePlatformRemarksPicExample();
			ViolatePlatformRemarksPicExample.Criteria ca = e.createCriteria();
			ca.andDelFlagEqualTo("0");
			ca.andViolateOrderIdEqualTo(violateOrder.getId());
			List<ViolatePlatformRemarksPic> violatePlatformRemarksPics = violatePlatformRemarksPicService.selectByExample(e);
			List<Map<String, Object>> remarkPicList = new ArrayList<Map<String, Object>>();
			for(ViolatePlatformRemarksPic violatePlatformRemarksPic:violatePlatformRemarksPics){
				Map<String, Object> pic=new HashMap<String, Object>();
				pic.put("PICTURE_PATH", violatePlatformRemarksPic.getPic());
				remarkPicList.add(pic);
			}
			resMap.put("remarkPics", remarkPicList);
		}
		String auditStatus = request.getParameter("auditStatus");//驳回时传递该参数
		resMap.put("auditStatus", auditStatus);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 保存人工违规
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/saveManuallyViolateOrder.shtml")
	@ResponseBody
	public Map<String, Object> saveManuallyViolateOrder(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String subOrderId = request.getParameter("subOrderId");
			String mchtId = request.getParameter("mchtId");
			String violateType = request.getParameter("violateType");
			String violateAction = request.getParameter("violateAction");
			String content = request.getParameter("content");
			String money = request.getParameter("money");
			String jifenIntegral = request.getParameter("jifenIntegral");
			String jifenIntegralDesc = request.getParameter("jifenIntegralDesc");
			String punishMeans = request.getParameter("punishMeans");
			String status = request.getParameter("status");
			String enableHours = request.getParameter("enableHours");
			String enclosure = request.getParameter("enclosure");//附件
			String platformRemarks = request.getParameter("platformRemarks");
			String remarkPics = request.getParameter("remarkPics");
			String auditStatus = request.getParameter("auditStatus");
			String integralCompensateModel = request.getParameter("integralCompensateModel");
			String integralCompensateRate = request.getParameter("integralCompensateRate");
			String minDeductionQuota = request.getParameter("minDeductionQuota");
			String paymentBreachModel = request.getParameter("paymentBreachModel");
			String breachDeductionQuota = request.getParameter("breachDeductionQuota");
			String payAmount = request.getParameter("payAmount");
			
			ViolateOrder violateOrder = new ViolateOrder();
			if(!StringUtils.isEmpty(id) && Integer.parseInt(id)>0){
				violateOrder = violateOrderService.selectByPrimaryKey(Integer.parseInt(id));
			}else{
				violateOrder.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				violateOrder.setCreateDate(new Date());
				violateOrder.setOrderCode(VIOLATEORDER_CODE+CommonUtil.getOrderCode());
				violateOrder.setOrderSource("2");
				violateOrder.setAuditStatus("0");
			}
			violateOrder.setUpdateDate(new Date());
			if(!StringUtils.isEmpty(subOrderId) && !subOrderId.equals("0")){
				SubOrder subOrder = subOrderService.selectByPrimaryKey(Integer.parseInt(subOrderId));
				if(!subOrder.getMchtId().equals(Integer.parseInt(mchtId))){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "该子订单号的商家与商家序号所对应的商家不一致，请检查子订单号或者商家序号是否有误。");
					return resMap;
				}else{
					violateOrder.setSubOrderId(Integer.parseInt(subOrderId));
				}
			}
			violateOrder.setMchtId(Integer.parseInt(mchtId));
			violateOrder.setViolateType(violateType);
			violateOrder.setViolateAction(violateAction);
			violateOrder.setContent(content);
			violateOrder.setPunishMeans(punishMeans);
			if(!StringUtils.isEmpty(paymentBreachModel) && !StringUtils.isEmpty(breachDeductionQuota) && !StringUtils.isEmpty(payAmount) && paymentBreachModel.equals("1")){
				BigDecimal temporaryMoney = (new BigDecimal(payAmount).multiply(new BigDecimal(breachDeductionQuota))).setScale(2,BigDecimal.ROUND_HALF_UP);
				if(!StringUtils.isEmpty(minDeductionQuota)){
					if(new BigDecimal(minDeductionQuota).compareTo(temporaryMoney) == 1){
						temporaryMoney = new BigDecimal(minDeductionQuota);
					}
				}
				violateOrder.setMoney(temporaryMoney);
			}else if(!StringUtils.isEmpty(money)){
				violateOrder.setMoney(new BigDecimal(money));
			}
			if(!StringUtils.isEmpty(jifenIntegral) && !jifenIntegral.equals("0")){
				if(!StringUtils.isEmpty(integralCompensateRate) && !StringUtils.isEmpty(integralCompensateModel) && integralCompensateModel.equals("1")){
					BigDecimal bigDecimal = (new BigDecimal(payAmount).multiply(new BigDecimal(integralCompensateRate))).multiply(new BigDecimal("100"));
					int IntValue = bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
					violateOrder.setJifenIntegral(IntValue);
				}else{
					violateOrder.setJifenIntegral(Integer.parseInt(jifenIntegral));
				}
				violateOrder.setJifenStatus("0");//未发放
			}else{
				violateOrder.setJifenStatus("2");//不用发放
			}
			violateOrder.setStatus(status);
			violateOrder.setStatusDate(new Date());
			violateOrder.setEnableHours(enableHours);
			violateOrder.setPlatformRemarks(platformRemarks);
			violateOrder.setEnclosure(enclosure);
			if(!StringUtils.isEmpty(auditStatus)){
				violateOrder.setAuditStatus(auditStatus);
			}
			violateOrder.setJifenIntegralDesc(jifenIntegralDesc);

			//新增，将挂起状态和挂起原因保持到数据库
			String suspendedStatus = request.getParameter("suspendedStatus");
			violateOrder.setSuspendedStatus(suspendedStatus);
			String suspendedReason = request.getParameter("suspendedReason");
			violateOrder.setSuspendedReason(suspendedReason);

			violateOrderService.save(violateOrder,remarkPics);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 根据子订单号获取商家信息
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/getMchtInfo.shtml")
	@ResponseBody
	public Map<String, Object> getMchtInfo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String subOrderCode = request.getParameter("subOrderCode");
			if(!StringUtils.isEmpty(subOrderCode)){
				List<HashMap<String,Object>> mchtInfos = violateOrderService.getMchtInfoBySubOrderCode(subOrderCode);
				resMap.put("mchtInfos", mchtInfos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 根据商家序号获取商家信息
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/getMchtInfoByMchtCode.shtml")
	@ResponseBody
	public Map<String, Object> getMchtInfoByMchtCode(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtCode = request.getParameter("mchtCode");
			MchtInfoExample example = new MchtInfoExample();
			MchtInfoExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtCodeEqualTo(mchtCode);
			List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(example);
			resMap.put("mchtInfos", mchtInfos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 审核人工违规列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/checkManuallyViolateOrderList.shtml")
	public ModelAndView checkManuallyViolateOrderList(HttpServletRequest request) {
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		String rtPage = "/violateOrder/checkManuallyViolateOrderList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("violateTypeList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE"));
		resMap.put("auditStatusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "AUDIT_STATUS"));
		resMap.put("statusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "STATUS"));
		resMap.put("violateActionList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION"));

		//新增的挂起状态列
		resMap.put("suspendedStatusList",DataDicUtil.getStatusList("BU_VIOLATE_ORDER","SUSPENDED_STATUS"));

		resMap.put("staffId", staffID);
		List<Map<String, Object>> createByInfos = violateOrderService.getAllCreateBy();
		resMap.put("createByInfos", createByInfos);
		
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
	 * 审核人工违规列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/checkManuallyData.shtml")
	@ResponseBody
	public Map<String, Object> checkManuallyData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
			violateOrderCustomExample.setOrderByClause("t.create_date desc");
			ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
			violateOrderCustomCriteria.andProductTypeIdEqualTo(staffID);
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				violateOrderCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				violateOrderCustomCriteria.andDelFlagEqualTo("0");
			}
			violateOrderCustomCriteria.andOrderSourceEqualTo("2");//人工
			String searchOrderCode = request.getParameter("orderCode");
			String searchViolateType = request.getParameter("violateType");
			String searchViolateAction = request.getParameter("violateAction");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchAuditStatus = request.getParameter("auditStatus");
			String searchStatus = request.getParameter("status");
			String searchSubOrderCode = request.getParameter("subOrderCode");
			String searchName = request.getParameter("name");

			//新增挂起状态
			String searchSuspendedStatus = request.getParameter("suspendedStatus");
			if (!StringUtil.isEmpty(searchSuspendedStatus)){
				violateOrderCustomCriteria.andSuspendedStatusEqualTo(searchSuspendedStatus);
			}

			if(!StringUtil.isEmpty(searchOrderCode)){
				violateOrderCustomCriteria.andOrderCodeEqualTo(searchOrderCode);
			}
			if(!StringUtil.isEmpty(searchViolateType)){
				violateOrderCustomCriteria.andViolateTypeEqualTo(searchViolateType);
			}
			if(!StringUtil.isEmpty(searchViolateAction)){
				violateOrderCustomCriteria.andViolateActionEqualTo(searchViolateAction);
			}
			if(!StringUtil.isEmpty(searchMchtCode)){
				violateOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchAuditStatus)){
				violateOrderCustomCriteria.andAuditStatusEqualTo(searchAuditStatus);
			}
			if(!StringUtil.isEmpty(searchStatus)){
				violateOrderCustomCriteria.andStatusEqualTo(searchStatus);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin"))){
				violateOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end"))){
				violateOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			String searchPlatformProcessor = request.getParameter("platformProcessor");
			if(!StringUtil.isEmpty(searchPlatformProcessor)){
				SysStaffInfoExample example = new SysStaffInfoExample();
				SysStaffInfoExample.Criteria criteria = example.createCriteria();
				criteria.andStaffNameLike("%"+searchPlatformProcessor+"%");
				List<SysStaffInfo> sysStaffInfos = sysStaffInfoService.selectByExample(example);
				List<Integer> platformProcessorIds = new ArrayList<Integer>();
				for(SysStaffInfo sysStaffInfo:sysStaffInfos){
					platformProcessorIds.add(sysStaffInfo.getStaffId());
				}
				violateOrderCustomCriteria.andPlatformProcessorIn(platformProcessorIds);
			}
			if(!StringUtil.isEmpty(searchSubOrderCode)){
				violateOrderCustomCriteria.andSubOrderCodeEqualTo(searchSubOrderCode);
			}
			if(!StringUtil.isEmpty(searchName)){
				violateOrderCustomCriteria.andNameLikeTo(searchName);
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					violateOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
					violateOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
				}
			}
			
			totalCount = violateOrderService.countViolateOrderCustomByExample(violateOrderCustomExample);
			violateOrderCustomExample.setLimitStart(page.getLimitStart());
			violateOrderCustomExample.setLimitSize(page.getLimitSize());
			List<ViolateOrderCustom> violateOrderCustoms = violateOrderService.selectViolateOrderCustomByExample(violateOrderCustomExample);
			resMap.put("Rows", violateOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 审核人工违规页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/toCheckManuallyViolateOrder.shtml")
	public ModelAndView toCheckManuallyViolateOrder(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/violateOrder/toCheckManuallyViolateOrder";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		int id=Integer.valueOf(request.getParameter("id"));
		ViolateOrder violateOrder = violateOrderService.selectByPrimaryKey(id);
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(violateOrder.getMchtId());
		String contactName = platformContactService.getContactNameByMchtIdAndContactType(violateOrder.getMchtId(),2);//2.对接运营
		resMap.put("contactName", contactName);
		ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
		ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
		violateOrderCustomCriteria.andMchtIdEqualTo(violateOrder.getMchtId());
		violateOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(DateUtil.getDateAfter(new Date(), -30));
		violateOrderCustomCriteria.andCreateDateLessThanOrEqualTo(new Date());
		int thirtyDaysViolateCount = violateOrderService.countViolateOrderCustomByExample(violateOrderCustomExample);
		resMap.put("thirtyDaysViolateCount", thirtyDaysViolateCount);
		ViolateOrderCustomExample voce = new ViolateOrderCustomExample();
		ViolateOrderCustomExample.ViolateOrderCustomCriteria vocc = voce.createCriteria();
		vocc.andMchtIdEqualTo(violateOrder.getMchtId());
		int totalViolateCount = violateOrderService.countViolateOrderCustomByExample(voce);
		resMap.put("totalViolateCount", totalViolateCount);
		
		if(violateOrder.getSubOrderId()!=null){
			SubOrder subOrder = subOrderService.selectByPrimaryKey(violateOrder.getSubOrderId());
			if(subOrder!=null){
				resMap.put("subOrderCode", subOrder.getSubOrderCode());
			}
		}
		resMap.put("statusDesc", DataDicUtil.getStatusDesc("BU_VIOLATE_ORDER", "STATUS", violateOrder.getStatus()));
		SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(violateOrder.getCreateBy());
		ViolatePlatformRemarksPicExample example = new ViolatePlatformRemarksPicExample();
		ViolatePlatformRemarksPicExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andViolateOrderIdEqualTo(id);
		List<ViolatePlatformRemarksPic> violatePlatformRemarksPics = violatePlatformRemarksPicService.selectByExample(example);
		resMap.put("violateOrder", violateOrder);
		resMap.put("mchtInfo", mchtInfo);
		if(sysStaffInfo!=null){
			resMap.put("staffName", sysStaffInfo.getStaffName());
		}
		resMap.put("orderSourceDesc", DataDicUtil.getStatusDesc("BU_VIOLATE_ORDER", "ORDER_SOURCE", violateOrder.getOrderSource()));
		resMap.put("auditStatusDesc", DataDicUtil.getStatusDesc("BU_VIOLATE_ORDER", "AUDIT_STATUS", violateOrder.getAuditStatus()));
		resMap.put("violateTypeDesc", DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE", violateOrder.getViolateType()));
		resMap.put("violateActionDesc", DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION", violateOrder.getViolateAction()));
		resMap.put("violatePlatformRemarksPics", violatePlatformRemarksPics);
		if(!StringUtils.isEmpty(violateOrder.getEnclosure())){
			resMap.put("enclosureName", violateOrder.getEnclosure().substring(violateOrder.getEnclosure().lastIndexOf("/")+1));
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 审核人工违规
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/checkManuallyViolateOrder.shtml")
	@ResponseBody
	public Map<String, Object> checkManuallyViolateOrder(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String violateOrderId = request.getParameter("violateOrderId");
			String auditStatus = request.getParameter("auditStatus");
			String rejectReason = request.getParameter("rejectReason");
			ViolateOrder violateOrder = violateOrderService.selectByPrimaryKey(Integer.parseInt(violateOrderId));
			violateOrder.setAuditStatus(auditStatus);
			violateOrder.setRemarks(rejectReason);
			Date date = new Date();
			violateOrder.setAuditDate(date);
			violateOrder.setPlatformProcessor(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			violateOrder.setUpdateDate(date);
			violateOrder.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			violateOrder.setStatusDate(date);
			if(auditStatus.equals("1")){//通过，A.保证金扣款 B.站内信 C.不可申诉的给用户积分
				violateOrder.setViolateDate(date);
				if(!violateOrder.getStatus().equals("2")){
					if(!StringUtils.isEmpty(violateOrder.getEnableHours())){
						if(violateOrder.getEnableHours().equals("12小时")){
							Calendar ca=Calendar.getInstance();
							ca.setTime(date);
							ca.add(Calendar.HOUR_OF_DAY, 12);
							violateOrder.setComplainEndDate(ca.getTime());
						}else if(violateOrder.getEnableHours().equals("24小时")){
							violateOrder.setComplainEndDate(DateUtil.getDateAfter(date, 1));
						}else if(violateOrder.getEnableHours().equals("48小时")){
							violateOrder.setComplainEndDate(DateUtil.getDateAfter(date, 2));
						}else if(violateOrder.getEnableHours().equals("72小时")){
							violateOrder.setComplainEndDate(DateUtil.getDateAfter(date, 3));
						}
					}
				}
				
				MchtDepositExample example = new MchtDepositExample();
				MchtDepositExample.Criteria criteria = example.createCriteria();
				criteria.andDelFlagEqualTo("0");
				criteria.andMchtIdEqualTo(violateOrder.getMchtId());
				List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(example);
				if(mchtDeposits!=null && mchtDeposits.size()>0){
					MchtDeposit mchtDeposit = mchtDeposits.get(0);
					MchtDepositDtl mchtDepositDtl = new MchtDepositDtl();
					if(violateOrder.getMoney()!=null){
						mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().subtract(violateOrder.getMoney()));
						mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().add(violateOrder.getMoney()));
					}else{
						mchtDeposit.setPayAmt(mchtDeposit.getPayAmt());
						mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt());
					}
					mchtDeposit.setUpdateDate(date);
					mchtDepositDtl.setDelFlag("0");
					mchtDepositDtl.setDepositId(mchtDeposit.getId());
					mchtDepositDtl.setTxnType("E");//违规扣款
					if(violateOrder.getViolateType().equals("A")){//入驻
						mchtDepositDtl.setTypeSub("E1");
					}else if(violateOrder.getViolateType().equals("B")){//发布信息
						mchtDepositDtl.setTypeSub("E2");
					}else if(violateOrder.getViolateType().equals("C")){//销售
						mchtDepositDtl.setTypeSub("E3");
					}else if(violateOrder.getViolateType().equals("D")){//售后
						mchtDepositDtl.setTypeSub("E4");
					}else{//其它
						mchtDepositDtl.setTypeSub("E5");
					}
					/*else if(violateOrder.getViolateType().equals("E")){//其它
						mchtDepositDtl.setTypeSub("E5");
					}*/
					if(violateOrder.getMoney()!=null){
						mchtDepositDtl.setTxnAmt(violateOrder.getMoney().negate());
					}else{
						mchtDepositDtl.setTxnAmt(new BigDecimal(0));
					}
					mchtDepositDtl.setPayAmt(mchtDeposit.getPayAmt());
					mchtDepositDtl.setBizType("2");//违规单
					mchtDepositDtl.setBizId(violateOrder.getId());
					mchtDepositDtl.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					mchtDepositDtl.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					mchtDepositDtl.setCreateDate(date);
					mchtDepositDtl.setUpdateDate(date);
					String violateTypeDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE", violateOrder.getViolateType());
					String violateActionDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION", violateOrder.getViolateAction());
					mchtDepositDtl.setRemarks("【"+violateTypeDesc+"】"+violateActionDesc);
					PlatformMsg platformMsg = new PlatformMsg();
					platformMsg.setMchtId(violateOrder.getMchtId());
					platformMsg.setMsgType("A5");//违规
					String statusDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_ORDER", "STATUS", violateOrder.getStatus());
					platformMsg.setTitle("关于违规{"+statusDesc+"}的通知");
					String content = "";
					if(StringUtils.isEmpty(violateOrder.getSubOrderId()) || violateOrder.getSubOrderId() == 0){
						content = "您好！您有1条违规状态更新为{"+statusDesc+"}，违规单编号《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('violateOrder/violateOrderView?id="+violateOrder.getId()+"')\""+">"+violateOrder.getOrderCode()+"</a>》，请知悉！";
					}else{
						SubOrder subOrder = subOrderService.selectByPrimaryKey(violateOrder.getSubOrderId());
						content = "您好！您有1条违规状态更新为{"+statusDesc+"}，违规单编号《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('violateOrder/violateOrderView?id="+violateOrder.getId()+"')\""+">"+violateOrder.getOrderCode()+"</a>》，相关订单号：《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('subOrder/subOrderView?id="+violateOrder.getSubOrderId()+"')\""+">"+subOrder.getSubOrderCode()+"</a>》请知悉！";
					}
					platformMsg.setContent(content);
					platformMsg.setBizId(violateOrder.getId());
					platformMsg.setStatus("0");//未读
					platformMsg.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					platformMsg.setCreateDate(new Date());
					platformMsg.setDelFlag("0");
					violateOrderService.update(violateOrder,mchtDeposit,mchtDepositDtl,platformMsg);
				}
				violateOrderService.updateByPrimaryKeySelective(violateOrder);
				if(violateOrder.getStatus().equals("2")){//不可申诉
					if(violateOrder.getJifenIntegral()!=null && violateOrder.getJifenIntegral()>0){//补偿用户积分
						//STORY #1430
//						if(violateOrder.getJifenStatus()!=null && violateOrder.getJifenStatus().equals("0")){//未补偿
//							//商家保证金不足时不给用户赠送积分
//							if(mchtDeposits==null||mchtDeposits.size()==0||mchtDeposits.get(0).getPayAmt().compareTo(new BigDecimal(0))<0){
//								if(!"3".equals(violateOrder.getJifenStatus())){
//									violateOrder.setJifenStatus("3");
//								}
//								violateOrderService.updateByPrimaryKeySelective(violateOrder);
//							}else{
//								IntegralDtl integralDtl = new IntegralDtl();
//								SubOrder subOrder = subOrderService.selectByPrimaryKey(violateOrder.getSubOrderId());
//								CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
//								MemberAccountExample e = new MemberAccountExample();
//								MemberAccountExample.Criteria c = e.createCriteria();
//								c.andDelFlagEqualTo("0");
//								c.andMemberIdEqualTo(combineOrder.getMemberId());
//								List<MemberAccount> memberAccounts = memberAccountService.selectByExample(e);
//								MemberAccount memberAccount = new MemberAccount();
//								if(memberAccounts!=null && memberAccounts.size()>0){
//									memberAccount = memberAccounts.get(0);
//								}
//								integralDtl.setAccId(memberAccount.getId());
//								integralDtl.setTallyMode("1");//进账
//								integralDtl.setType(6);//系统赠送
//								integralDtl.setIntegral(violateOrder.getJifenIntegral());
//								if(memberAccount.getIntegral()!=null){
//									integralDtl.setBalance(memberAccount.getIntegral()+violateOrder.getJifenIntegral());
//								}else{
//									integralDtl.setBalance(violateOrder.getJifenIntegral());
//								}
//								integralDtl.setOrderId(violateOrder.getSubOrderId());
//								integralDtl.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
//								integralDtl.setCreateDate(new Date());
//								integralDtl.setRemarks("商家违规，系统补偿用户积分");
//								integralDtl.setDelFlag("0");
//								if(memberAccount.getIntegral()!=null){
//									memberAccount.setIntegral(memberAccount.getIntegral()+violateOrder.getJifenIntegral());
//								}else{
//									memberAccount.setIntegral(violateOrder.getJifenIntegral());
//								}
//								memberAccount.setUpdateDate(new Date());
//								memberAccount.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
//								violateOrder.setJifenStatus("1");
//								memberAccountService.update(memberAccount, integralDtl,violateOrder);
//								//发送短信
//								MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(combineOrder.getMemberId());
//								JSONObject param=new JSONObject();
//								JSONObject reqData=new JSONObject();
//								reqData.put("mobile", memberInfo.getMobile());
//								reqData.put("content", "尊敬的用户，因商家违规，平台已对商家作出处罚，同时为您发放"+violateOrder.getJifenIntegral()+"个积分作为补偿。 ");
//								reqData.put("smsType", "3");
//								param.put("reqData", reqData);
//								JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
//								if(!"0000".equals(result.getString("returnCode"))){
//									logger.info("短信发送用户失败！！！！！");
//								}
//								
//							}
//						}
					}
				}
				//TODO 发送短信
				String toSendMobile = "";
				List<String> contactTypes = new ArrayList<String>();
				contactTypes.add("2");//运营
				contactTypes.add("1");//电商
				contactTypes.add("3");//订单对接人
				for(String contactType:contactTypes){
					MchtContactExample e = new MchtContactExample();
					MchtContactExample.Criteria c = e.createCriteria();
					c.andDelFlagEqualTo("0");
					c.andMchtIdEqualTo(violateOrder.getMchtId());
					c.andContactTypeEqualTo(contactType);
					List<MchtContact> mchtContacts = mchtContactService.selectByExample(e);
					if(mchtContacts!=null && mchtContacts.size()>0){
						toSendMobile = mchtContacts.get(0).getMobile();
						break;
					}
				}
				if(!StringUtils.isEmpty(toSendMobile)){
					JSONObject param=new JSONObject();
					JSONObject reqData=new JSONObject();
					reqData.put("mobile", toSendMobile);
					reqData.put("content", "您好！您有新的违规："+violateOrder.getOrderCode()+"，请尽快登录平台处理。");
					reqData.put("smsType", "4");
					param.put("reqData", reqData);
					JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
					
				}
			}else{//2.驳回
				violateOrderService.updateByPrimaryKeySelective(violateOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "提交失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 违规扣款记录列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/debitNoteList.shtml")
	public ModelAndView debitNoteList(HttpServletRequest request) {
		String rtPage = "/violateOrder/debitNoteList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("txnTypeList", DataDicUtil.getStatusList("BU_MCHT_DEPOSIT_DTL", "TXN_TYPE"));
		resMap.put("defaultTxnType", "E");
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact);
        
		resMap.put("isContact", isContact);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 违规扣款记录列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/debitNoteData.shtml")
	@ResponseBody
	public Map<String, Object> debitNoteData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			MchtDepositDtlCustomExample mchtDepositDtlCustomExample = new MchtDepositDtlCustomExample();
			MchtDepositDtlCustomExample.MchtDepositDtlCustomCriteria mchtDepositDtlCustomCriteria = mchtDepositDtlCustomExample.createCriteria();
			mchtDepositDtlCustomCriteria.andDelFlagEqualTo("0");
			mchtDepositDtlCustomCriteria.andProductTypeIdEqualTo(staffID);
			String searchMchtCode = request.getParameter("mchtCode");
			String searchCompanyName = request.getParameter("companyName");
			String searchTxnType = request.getParameter("txnType");
			if(!StringUtil.isEmpty(searchMchtCode)){
				mchtDepositDtlCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchCompanyName)){
				mchtDepositDtlCustomCriteria.andCompanyNameEqualTo(searchCompanyName);
			}
			if(!StringUtil.isEmpty(searchTxnType)){
				mchtDepositDtlCustomCriteria.andTxnTypeEqualTo(searchTxnType);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				mchtDepositDtlCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				mchtDepositDtlCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				mchtDepositDtlCustomCriteria.andPlatformContactEqualTo(platformContactId);
			}
			totalCount = mchtDepositDtlService.countByExample(mchtDepositDtlCustomExample);
			mchtDepositDtlCustomExample.setLimitStart(page.getLimitStart());
			mchtDepositDtlCustomExample.setLimitSize(page.getLimitSize());
			List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService.selectByExample(mchtDepositDtlCustomExample);
			resMap.put("Rows", mchtDepositDtlCustoms);
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
	@RequestMapping("/violateOrder/exportMchtDepositDtl.shtml")
	public void exportMchtDepositDtl(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			MchtDepositDtlCustomExample mchtDepositDtlCustomExample = new MchtDepositDtlCustomExample();
			MchtDepositDtlCustomExample.MchtDepositDtlCustomCriteria mchtDepositDtlCustomCriteria = mchtDepositDtlCustomExample.createCriteria();
			mchtDepositDtlCustomCriteria.andDelFlagEqualTo("0");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchCompanyName = request.getParameter("companyName");
			String searchTxnType = request.getParameter("txnType");
			if(!StringUtil.isEmpty(searchMchtCode)){
				mchtDepositDtlCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchCompanyName)){
				mchtDepositDtlCustomCriteria.andCompanyNameEqualTo(searchCompanyName);
			}
			if(!StringUtil.isEmpty(searchTxnType)){
				mchtDepositDtlCustomCriteria.andTxnTypeEqualTo(searchTxnType);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				mchtDepositDtlCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				mchtDepositDtlCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService.selectByExample(mchtDepositDtlCustomExample);
			String[] titles = {"时间","商家序号","公司名称","类型","子类","摘要","应缴额变化值","保证金余额变化值","变化后保证金余额"};
			ExcelBean excelBean = new ExcelBean("导出违规扣款记录表.xls",
					"导出违规扣款记录表", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtDepositDtlCustom mchtDepositDtlCustom:mchtDepositDtlCustoms){
				String[] data = {
					df.format(mchtDepositDtlCustom.getCreateDate()),
					mchtDepositDtlCustom.getMchtCode(),
					mchtDepositDtlCustom.getCompanyName(),
					mchtDepositDtlCustom.getTxnTypeDesc(),
					mchtDepositDtlCustom.getTypeSubDesc(),
					mchtDepositDtlCustom.getSummary(),
					mchtDepositDtlCustom.getTxnType().equals("A")? mchtDepositDtlCustom.getTxnAmt().toString() : "0",
					mchtDepositDtlCustom.getTxnType().equals("A")? "0" : mchtDepositDtlCustom.getTxnAmt().toString(),
					mchtDepositDtlCustom.getPayAmt().toString()
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
	 * 申诉审核页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/toCheck.shtml")
	public ModelAndView toCheck(HttpServletRequest request) {
		String rtPage = "/violateOrder/toCheck";
		String violateComplainOrderId = request.getParameter("violateComplainOrderId");
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("violateComplainOrderId", violateComplainOrderId);
		return new ModelAndView(rtPage,resMap);
	}

	/**
	 * 审核违规申诉单
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/violateOrder/checkViolateComplainOrder.shtml")
	@ResponseBody
	public Map<String, Object> checkViolateComplainOrder(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String violateComplainOrderId = request.getParameter("violateComplainOrderId");
			String status = request.getParameter("status");
			String remarks = request.getParameter("remarks");
			String complainOrderPics = request.getParameter("complainOrderPics");
			String processInnerRemarks = request.getParameter("processInnerRemarks");
			ViolateComplainOrder violateComplainOrder = violateComplainOrderService.selectByPrimaryKey(Integer.parseInt(violateComplainOrderId));
			violateComplainOrder.setProcesBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			//TODO 审核通过  添加内部备注
			if(status.equals("1") && !StringUtil.isEmpty(processInnerRemarks) ) {
				violateComplainOrder.setProcessInnerRemarks(processInnerRemarks.replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
			}
			violateComplainOrder.setStatus(status);
			violateComplainOrder.setStatusDate(new Date());
			violateComplainOrder.setRemarks(remarks);
			violateComplainOrder.setUpdateDate(new Date());
			ViolateOrder violateOrder = violateOrderService.selectByPrimaryKey(violateComplainOrder.getViolateOrderId());
			if(status.equals("1")){//1.通过，申诉成功
				violateOrder.setStatus("6");//申诉成功
				violateOrder.setStatusDate(new Date());
				violateOrder.setJifenStatus("2");//不用发放积分
				violateOrder.setComplainInfoStatus("3");//审核结束
				MchtDepositExample example = new MchtDepositExample();
				MchtDepositExample.Criteria criteria = example.createCriteria();
				criteria.andDelFlagEqualTo("0");
				criteria.andMchtIdEqualTo(violateOrder.getMchtId());
				List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(example);
				Date date = new Date();
				MchtDeposit mchtDeposit = new MchtDeposit();
				MchtDepositDtl mchtDepositDtl = new MchtDepositDtl();
				if(mchtDeposits!=null && mchtDeposits.size()>0){
					mchtDeposit = mchtDeposits.get(0);
					if(violateOrder.getMoney()!=null){
						mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().add(violateOrder.getMoney()));
					}else{
						mchtDeposit.setPayAmt(mchtDeposit.getPayAmt());
					}
					if(violateOrder.getMoney()!=null){
						mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().subtract(violateOrder.getMoney()));
					}else{
						mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt());
					}
					mchtDeposit.setUpdateDate(date);
					
					mchtDepositDtl.setDelFlag("0");
					mchtDepositDtl.setDepositId(mchtDeposit.getId());
					mchtDepositDtl.setTxnType("F");//申诉成功
					if(violateOrder.getViolateType().equals("A")){//入驻
						mchtDepositDtl.setTypeSub("F1");
					}else if(violateOrder.getViolateType().equals("B")){//发布信息
						mchtDepositDtl.setTypeSub("F2");
					}else if(violateOrder.getViolateType().equals("C")){//销售
						mchtDepositDtl.setTypeSub("F3");
					}else if(violateOrder.getViolateType().equals("D")){//售后
						mchtDepositDtl.setTypeSub("F4");
					}else{
						mchtDepositDtl.setTypeSub("F5");//其他
					}
					/*else if(violateOrder.getViolateType().equals("E")){//其他
						mchtDepositDtl.setTypeSub("F5");
					}*/
					if(violateOrder.getMoney()!=null){
						mchtDepositDtl.setTxnAmt(violateOrder.getMoney());
					}else{
						mchtDepositDtl.setTxnAmt(new BigDecimal(0));
					}
					mchtDepositDtl.setPayAmt(mchtDeposit.getPayAmt());
					mchtDepositDtl.setBizType("2");//违规单
					mchtDepositDtl.setBizId(violateOrder.getId());
					mchtDepositDtl.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					mchtDepositDtl.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
					mchtDepositDtl.setCreateDate(date);
					mchtDepositDtl.setUpdateDate(date);
					String violateTypeDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE", violateOrder.getViolateType());
					String violateActionDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION", violateOrder.getViolateAction());
					mchtDepositDtl.setRemarks("【"+violateTypeDesc+"】"+violateActionDesc);
				}
				PlatformMsg platformMsg = new PlatformMsg();
				platformMsg.setMchtId(violateOrder.getMchtId());
				platformMsg.setMsgType("A5");//违规
				platformMsg.setTitle("关于违规{申诉成功}的通知");
				String content = "";
				if(StringUtils.isEmpty(violateOrder.getSubOrderId()) || violateOrder.getSubOrderId() == 0){
					content = "您好！您有1条违规状态更新为{申诉成功}，违规单编号《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('violateOrder/violateOrderView?id="+violateOrder.getId()+"')\""+">"+violateOrder.getOrderCode()+"</a>》，请知悉！";
				}else{
					Integer subOrderId = violateOrder.getSubOrderId();
					SubOrder subOrder = subOrderService.selectByPrimaryKey(violateOrder.getSubOrderId());
					content = "您好！您有1条违规状态更新为{申诉成功}，违规单编号《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('violateOrder/violateOrderView?id="+violateOrder.getId()+"')\""+">"+violateOrder.getOrderCode()+"</a>》，相关订单号：《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('subOrder/subOrderView?id="+violateOrder.getSubOrderId()+"')\""+">"+subOrder.getSubOrderCode()+"</a>》请知悉！";
				}
				platformMsg.setContent(content);
				platformMsg.setBizId(violateOrder.getId());
				platformMsg.setStatus("0");//未读
				platformMsg.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				platformMsg.setCreateDate(new Date());
				platformMsg.setDelFlag("0");
				violateOrderService.update(violateOrder,violateComplainOrder, mchtDeposit, mchtDepositDtl, platformMsg);
			}else if(status.equals("2")){//2.驳回
				String violateOrderStatusDesc = "";
				if(violateOrder.getComplainEndDate().after(new Date())){//超时前
					violateOrder.setComplainInfoStatus("1");//待补充
					violateOrderStatusDesc = "申诉驳回";
				}else{//超时后
					violateOrder.setStatus("5");//申诉失败
					violateOrder.setStatusDate(new Date());
					violateOrder.setComplainInfoStatus("3");//审核结束
					violateOrderStatusDesc = "申诉失败";
					if(violateOrder.getJifenIntegral()!=null && violateOrder.getJifenIntegral()>0){//补偿用户积分
						//STORY #1430
//						if(!StringUtils.isEmpty(violateOrder.getJifenStatus()) && violateOrder.getJifenStatus().equals("0")){//未补偿
//							//商家保证金不足时不给用户赠送积分
//							MchtDepositExample mchtDepositExample=new MchtDepositExample();
//							mchtDepositExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(violateOrder.getMchtId());
//							List<MchtDeposit> mchtDeposits=mchtDepositService.selectByExample(mchtDepositExample);
//							if(mchtDeposits==null||mchtDeposits.size()==0||mchtDeposits.get(0).getPayAmt().compareTo(new BigDecimal(0))<0){
//								if(!"3".equals(violateOrder.getJifenStatus())){
//									violateOrder.setJifenStatus("3");
//								}
//								violateOrderService.updateByPrimaryKeySelective(violateOrder);
//							}else{
//								IntegralDtl integralDtl = new IntegralDtl();
//								SubOrder subOrder = subOrderService.selectByPrimaryKey(violateOrder.getSubOrderId());
//								CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
//								MemberAccountExample e = new MemberAccountExample();
//								MemberAccountExample.Criteria c = e.createCriteria();
//								c.andDelFlagEqualTo("0");
//								c.andMemberIdEqualTo(combineOrder.getMemberId());
//								List<MemberAccount> memberAccounts = memberAccountService.selectByExample(e);
//								MemberAccount memberAccount = new MemberAccount();
//								if(memberAccounts!=null && memberAccounts.size()>0){
//									memberAccount = memberAccounts.get(0);
//								}
//								integralDtl.setAccId(memberAccount.getId());
//								integralDtl.setTallyMode("1");//进账
//								integralDtl.setType(6);//系统赠送
//								integralDtl.setIntegral(violateOrder.getJifenIntegral());
//								if(memberAccount.getIntegral()!=null){
//									integralDtl.setBalance(memberAccount.getIntegral()+violateOrder.getJifenIntegral());
//								}else{
//									integralDtl.setBalance(violateOrder.getJifenIntegral());
//								}
//								
//								integralDtl.setOrderId(violateOrder.getSubOrderId());
//								integralDtl.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
//								integralDtl.setCreateDate(new Date());
//								integralDtl.setRemarks("商家违规，系统补偿用户积分");
//								integralDtl.setDelFlag("0");
//								if(memberAccount.getIntegral()!=null){
//									memberAccount.setIntegral(memberAccount.getIntegral()+violateOrder.getJifenIntegral());
//								}else{
//									memberAccount.setIntegral(violateOrder.getJifenIntegral());
//								}
//								memberAccount.setUpdateDate(new Date());
//								memberAccount.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
//								violateOrder.setJifenStatus("1");
//								memberAccountService.update(memberAccount, integralDtl,violateOrder);
//								//发送短信
//								MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(combineOrder.getMemberId());
//								JSONObject param=new JSONObject();
//								JSONObject reqData=new JSONObject();
//								reqData.put("mobile", memberInfo.getMobile());
//								reqData.put("content", "尊敬的用户，因商家违规，平台已对商家作出处罚，同时为您发放"+violateOrder.getJifenIntegral()+"个积分作为补偿。 ");
//								reqData.put("smsType", "3");
//								param.put("reqData", reqData);
//								JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
//								if(!"0000".equals(result.getString("returnCode"))){
//									logger.info("短信发送用户失败！！！！！");
//								}
//							}
//							
//						}
					}
				}
				PlatformMsg platformMsg = new PlatformMsg();
				platformMsg.setMchtId(violateOrder.getMchtId());
				platformMsg.setMsgType("A5");//违规
				platformMsg.setTitle("关于违规{"+violateOrderStatusDesc+"}的通知");
				String content = "";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String complainEndDate = sdf.format(violateOrder.getComplainEndDate());
				if(StringUtils.isEmpty(violateOrder.getSubOrderId()) || violateOrder.getSubOrderId() == 0){
					content = "您好！您的违规单编号《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('violateOrder/violateOrderView?id="+violateOrder.getId()+"')\""+">"+violateOrder.getOrderCode()+"</a>》申诉被驳回，请尽快申诉，申诉截止时间："+complainEndDate;
				}else{
					SubOrder subOrder = subOrderService.selectByPrimaryKey(violateOrder.getSubOrderId());
					content = "您好！您的违规单编号《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('violateOrder/violateOrderView?id="+violateOrder.getId()+"')\""+">"+violateOrder.getOrderCode()+"</a>》申诉被驳回，请尽快申诉，申诉截止时间："+complainEndDate+"，相关订单号：《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('subOrder/subOrderView?id="+violateOrder.getSubOrderId()+"')\""+">"+subOrder.getSubOrderCode()+"</a>》请知悉！";
				}
				platformMsg.setContent(content);
				platformMsg.setBizId(violateOrder.getId());
				platformMsg.setStatus("0");//未读
				platformMsg.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				platformMsg.setCreateDate(new Date());
				platformMsg.setDelFlag("0");
				//TODO 图片处理
				List<ComplainOrderPic> complainOrderPicList = new ArrayList<ComplainOrderPic>();
				if(!StringUtils.isEmpty(complainOrderPics)){
					JSONArray picArray = JSONArray.fromObject(complainOrderPics);
					Iterator<JSONObject> it= picArray.iterator();
					while (it.hasNext()) {
						JSONObject pic=it.next();
						ComplainOrderPic complainOrderPic = new ComplainOrderPic();
						complainOrderPic.setComplainOrderId(Integer.parseInt(violateComplainOrderId));
						complainOrderPic.setOperType("1");
						complainOrderPic.setPic(pic.getString("picPath"));
						complainOrderPic.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
						complainOrderPic.setCreateDate(new Date());
						complainOrderPicList.add(complainOrderPic);
					}
				}
				violateOrderService.update(violateOrder,violateComplainOrder, platformMsg,complainOrderPicList);
				//TODO 发送短信
				String toSendMobile="";
				if(!StringUtils.isEmpty(violateComplainOrder.getPhone())){
					toSendMobile = violateComplainOrder.getPhone();
				}else{
					List<String> contactTypes = new ArrayList<String>();
					contactTypes.add("2");//运营
					contactTypes.add("1");//电商
					contactTypes.add("3");//订单对接人
					for(String contactType:contactTypes){
						MchtContactExample example = new MchtContactExample();
						MchtContactExample.Criteria c = example.createCriteria();
						c.andDelFlagEqualTo("0");
						c.andMchtIdEqualTo(violateOrder.getMchtId());
						c.andContactTypeEqualTo(contactType);
						List<MchtContact> mchtContacts = mchtContactService.selectByExample(example);
						if(mchtContacts!=null && mchtContacts.size()>0){
							toSendMobile = mchtContacts.get(0).getMobile();
							break;
						}
					}
				}
				if(!StringUtils.isEmpty(toSendMobile)){
					JSONObject param=new JSONObject();
					JSONObject reqData=new JSONObject();
					reqData.put("mobile", toSendMobile);
					reqData.put("content", "您好！您的违规单编号："+violateOrder.getOrderCode()+"申诉被驳回 ，请尽快登录平台处理。");
					reqData.put("smsType", "4");
					param.put("reqData", reqData);
					JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "提交失败，请稍后重试");
		}
		return resMap;
	}
	
	
	@RequestMapping("/violateOrder/exportViolateOrder.shtml")
	public void exportViolateOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			//用来区分正常页和挂起页
			String suspendedStatus = request.getParameter("suspendedStatus");

			String pageStatus = request.getParameter("pageStatus");
			ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
			ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
			violateOrderCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(pageStatus) && pageStatus.equals("1")) { //查看全部违规信息
				violateOrderCustomExample.setOrderByClause("t.create_date desc");
			}else if(!StringUtil.isEmpty(pageStatus) && pageStatus.equals("2")) { //违规申诉中
				violateOrderCustomExample.setOrderByClause("t.complain_date desc");
				violateOrderCustomCriteria.andComplainDateIsNotNull();
				List<String> statusList = new ArrayList<String>();
				statusList.add("3");
				statusList.add("5");
				statusList.add("6");
				violateOrderCustomCriteria.andStatusIn(statusList);
				violateOrderCustomCriteria.andSuspendedStatusEqualTo(suspendedStatus);
			}else if(!StringUtil.isEmpty(pageStatus) && pageStatus.equals("3")) { //发起人工违规
				violateOrderCustomExample.setOrderByClause("t.create_date desc");
				violateOrderCustomCriteria.andOrderSourceEqualTo("2");
			}else if(!StringUtil.isEmpty(pageStatus) && pageStatus.equals("4")) { //审核人工违规
				violateOrderCustomExample.setOrderByClause("t.create_date desc");
				violateOrderCustomCriteria.andOrderSourceEqualTo("2"); //人工
			}
			if(!StringUtil.isEmpty(request.getParameter("orderSource"))){ //来源
				violateOrderCustomCriteria.andOrderSourceEqualTo(request.getParameter("orderSource"));
			}
			if(!StringUtil.isEmpty(request.getParameter("orderCode"))){ //违规编号
				violateOrderCustomCriteria.andOrderCodeEqualTo(request.getParameter("orderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){ //商家序号
				violateOrderCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("name"))){ //商家名称
				violateOrderCustomCriteria.andNameLikeTo(request.getParameter("name"));
			}
			if(!StringUtil.isEmpty(request.getParameter("subOrderCode"))){ //子订单号
				violateOrderCustomCriteria.andSubOrderCodeEqualTo(request.getParameter("subOrderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("violateType"))){ //类型
				violateOrderCustomCriteria.andViolateTypeEqualTo(request.getParameter("violateType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("violateAction"))){ //违规行为
				violateOrderCustomCriteria.andViolateActionEqualTo(request.getParameter("violateAction"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createBy"))){ //创建人
				violateOrderCustomCriteria.andCreateByEqualTo(Integer.parseInt(request.getParameter("createBy")));
			}
			if(!StringUtil.isEmpty(request.getParameter("auditStatus"))){ //发布审核状态
				violateOrderCustomCriteria.andAuditStatusEqualTo(request.getParameter("auditStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))){ //申诉状态
				violateOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("jifenStatus"))){ //积分状态
				violateOrderCustomCriteria.andJifenStatusEqualTo(request.getParameter("jifenStatus"));
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("violate_date_begin"))){ //违规日期
				violateOrderCustomCriteria.andViolateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("violate_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("violate_date_end"))){ //违规日期
				violateOrderCustomCriteria.andViolateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("violate_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("complain_date_begin"))){ //申诉日期
				violateOrderCustomCriteria.andComplainDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("complain_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("complain_date_end"))){ //申诉日期
				violateOrderCustomCriteria.andComplainDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("complain_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin"))){ //创建日期
				violateOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end"))){ //创建日期
				violateOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					violateOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
					violateOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("checkStatus"))){ //审核状态
				if(request.getParameter("checkStatus").equals("2")){ //申诉中（待审核）
					violateOrderCustomCriteria.andStatusEqualTo("3");
					violateOrderCustomCriteria.andComplainInfoStatusEqualTo("2");
				}else if(request.getParameter("checkStatus").equals("1")){ //申诉中（待补充）
					violateOrderCustomCriteria.andStatusEqualTo("3");
					violateOrderCustomCriteria.andComplainInfoStatusEqualTo("1");
				}else{
					violateOrderCustomCriteria.andStatusEqualTo(request.getParameter("checkStatus"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("procesBy"))){ //申诉审核人
				violateOrderCustomCriteria.andProcesByEqualTo(Integer.parseInt(request.getParameter("procesBy")));
			}
			List<ViolateOrderCustom> violateOrderCustoms = violateOrderService.selectExportViolateOrderCustomByExample(violateOrderCustomExample);
			String dateStr = "创建时间";
			if(!StringUtil.isEmpty(pageStatus) && pageStatus.equals("2")) { //违规申诉中
				dateStr = "违规时间";
			}
			String[] titles = {dateStr,"违规单编号","商家","店铺名称","公司名称","子订单号","付款时间","快递名称","快递单号","违规行为",
					"处罚金额（元）","发布审核状态","申诉状态","商家申诉时间","平台处理人","发货时间"};
			ExcelBean excelBean = new ExcelBean("违规导出表格.xls", "违规导出表格", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(ViolateOrderCustom violateOrderCustom : violateOrderCustoms){
				String dateString = "";
				if(!StringUtil.isEmpty(pageStatus) && pageStatus.equals("2")) { //违规申诉中
					if(violateOrderCustom.getViolateDate() != null) {
						dateString = dateFormat.format(violateOrderCustom.getViolateDate());
					}
				}else {
					if(violateOrderCustom.getCreateDate() != null) {
						dateString = dateFormat.format(violateOrderCustom.getCreateDate());
					}
				}
				
				String[] data = {
					dateString,
					violateOrderCustom.getOrderCode(),
					violateOrderCustom.getMchtCode(),
					violateOrderCustom.getShopName(),
					violateOrderCustom.getCompanyName(),
					violateOrderCustom.getSubOrderCode(),
					violateOrderCustom.getPayDate()==null?"":dateFormat.format(violateOrderCustom.getPayDate()),
					violateOrderCustom.getExpressName(),
					violateOrderCustom.getExpressNo()==null?"":"`"+violateOrderCustom.getExpressNo(),
					"【"+violateOrderCustom.getViolateTypeDesc()+"】"+violateOrderCustom.getViolateActionDesc(),
					violateOrderCustom.getMoney()==null?"":violateOrderCustom.getMoney().toString(),
					violateOrderCustom.getAuditStatusDesc(),
					violateOrderCustom.getStatusDesc(),
					violateOrderCustom.getComplainDate()==null?"":dateFormat.format(violateOrderCustom.getComplainDate()),
					violateOrderCustom.getStaffName(),
					violateOrderCustom.getDeliveryDate()==null?"":dateFormat.format(violateOrderCustom.getDeliveryDate())
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
	 * @Title violateOrderJifenIntegralManager   
	 * @Description TODO(违规扣款积分赠送记录)   
	 * @author pengl
	 * @date 2018年9月6日 下午4:39:02
	 */
	@RequestMapping("/violateOrder/violateOrderJifenIntegralManager.shtml")
	public ModelAndView violateOrderJifenIntegralManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/violateOrder/violateOrderJifenIntegralList");
		m.addObject("violateActionList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION"));
		return m;
	}
	
	/**
	 * 
	 * @Title violateOrderJifenIntegralList   
	 * @Description TODO(违规扣款积分赠送记录)   
	 * @author pengl
	 * @date 2018年9月6日 下午4:39:21
	 */
	@ResponseBody
	@RequestMapping("/violateOrder/violateOrderJifenIntegralList.shtml")
	public Map<String, Object> violateOrderJifenIntegralList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ViolateOrderCustom> violateOrderCustomList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
			ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
			violateOrderCustomCriteria.andDelFlagEqualTo("0")
				.andJifenStatusIn(Arrays.asList("0","1","3"))
				.andStatusIn(Arrays.asList("2","4","5"));
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate")) ) {
				violateOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate")) ) {
				violateOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ) {
				violateOrderCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("violateAction")) ) {
				violateOrderCustomCriteria.andViolateActionEqualTo(request.getParameter("violateAction"));
			}
			if(!StringUtil.isEmpty(request.getParameter("jifenStatus")) ) {
				violateOrderCustomCriteria.andJifenStatusEqualTo(request.getParameter("jifenStatus"));
			}
			violateOrderCustomExample.setOrderByClause(" create_date desc");
			violateOrderCustomExample.setLimitStart(page.getLimitStart());
			violateOrderCustomExample.setLimitSize(page.getLimitSize());
			totalCount = violateOrderService.countViolateOrderCustomByExample(violateOrderCustomExample);
			violateOrderCustomList = violateOrderService.selectViolateOrderCustomByExample(violateOrderCustomExample);
			resMap.put("Rows", violateOrderCustomList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 虚假发货审核列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/shamDeliveryOrderList.shtml")
	public ModelAndView shamDeliveryOrderList(HttpServletRequest request) {
		String rtPage = "violateOrder/shamDeliveryOrderList";
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("auditStatusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "AUDIT_STATUS"));
		resMap.put("statusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "STATUS"));
		//新增的挂起状态列
		resMap.put("suspendedStatusList",DataDicUtil.getStatusList("BU_VIOLATE_ORDER","SUSPENDED_STATUS"));
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
	 * 虚假发货审核列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/shamDeliveryOrderData.shtml")
	@ResponseBody
	public Map<String, Object> shamDeliveryOrderData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
			violateOrderCustomExample.setOrderByClause("t.create_date desc");
			ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
			violateOrderCustomCriteria.andProductTypeIdEqualTo(staffID);
			violateOrderCustomCriteria.andDelFlagEqualTo("0");
			violateOrderCustomCriteria.andOrderSourceEqualTo("1");
			String searchOrderCode = request.getParameter("orderCode");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchAuditStatus = request.getParameter("auditStatus");
			String searchStatus = request.getParameter("status");
			String searchSubOrderCode = request.getParameter("subOrderCode");
			String searchName = request.getParameter("name");

			//新增挂起状态
			String searchSuspendedStatus = request.getParameter("suspendedStatus");
			if (!StringUtil.isEmpty(searchSuspendedStatus)){
				violateOrderCustomCriteria.andSuspendedStatusEqualTo(searchSuspendedStatus);
			}
			if(!StringUtil.isEmpty(searchOrderCode)){
				violateOrderCustomCriteria.andOrderCodeEqualTo(searchOrderCode);
			}
			if(!StringUtil.isEmpty(searchMchtCode)){
				violateOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchAuditStatus)){
				violateOrderCustomCriteria.andAuditStatusEqualTo(searchAuditStatus);
			}
			if(!StringUtil.isEmpty(searchStatus)){
				violateOrderCustomCriteria.andStatusEqualTo(searchStatus);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin"))){
				violateOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end"))){
				violateOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(searchSubOrderCode)){
				violateOrderCustomCriteria.andSubOrderCodeEqualTo(searchSubOrderCode);
			}
			if(!StringUtil.isEmpty(searchName)){
				violateOrderCustomCriteria.andNameLikeTo(searchName);
			}
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					violateOrderCustomCriteria.andProductTypeIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}else {
				if(!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
					violateOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
				}
			}
			violateOrderCustomCriteria.andIsShamDeliveryOrder();
			totalCount = violateOrderService.countViolateOrderCustomByExample(violateOrderCustomExample);
			violateOrderCustomExample.setLimitStart(page.getLimitStart());
			violateOrderCustomExample.setLimitSize(page.getLimitSize());
			List<ViolateOrderCustom> violateOrderCustoms = violateOrderService.selectViolateOrderCustomByExample(violateOrderCustomExample);
			resMap.put("Rows", violateOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 审核页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/toAudit.shtml")
	public ModelAndView toAudit(HttpServletRequest request) {
		String rtPage = "/violateOrder/toAudit";
		String violateOrderId = request.getParameter("violateOrderId");
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("violateOrderId", violateOrderId);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 虚假发货审核
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/audit.shtml")
	@ResponseBody
	public Map<String, Object> audit(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String violateOrderId = request.getParameter("violateOrderId");
			String auditStatus = request.getParameter("auditStatus");
			String auditRemarks = request.getParameter("auditRemarks");
			String[] violateOrderIdArray = violateOrderId.split(",");
			for(int i=0;i<violateOrderIdArray.length;i++){
				ViolateOrder violateOrder = violateOrderService.selectByPrimaryKey(Integer.parseInt(violateOrderIdArray[i]));
				if(!violateOrder.getAuditStatus().equals("0")){
					continue;
				}
				violateOrder.setAuditStatus(auditStatus);
				violateOrder.setAuditRemarks(auditRemarks);
				Date date = new Date();
				violateOrder.setAuditDate(date);
				violateOrder.setPlatformProcessor(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				violateOrder.setUpdateDate(date);
				violateOrder.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				if(auditStatus.equals("1")){//通过，A.保证金扣款 B.站内信 
					violateOrder.setViolateDate(date);
					if(!violateOrder.getStatus().equals("2")){
						violateOrder.setComplainEndDate(DateUtil.getDateAfter(date, 3)); 
					}
					MchtDepositExample example = new MchtDepositExample();
					MchtDepositExample.Criteria criteria = example.createCriteria();
					criteria.andDelFlagEqualTo("0");
					criteria.andMchtIdEqualTo(violateOrder.getMchtId());
					List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(example);
					if(mchtDeposits!=null && mchtDeposits.size()>0){
						MchtDeposit mchtDeposit = mchtDeposits.get(0);
						MchtDepositDtl mchtDepositDtl = new MchtDepositDtl();
						if(violateOrder.getMoney()!=null){
							mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().subtract(violateOrder.getMoney()));
							mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().add(violateOrder.getMoney()));
						}else{
							mchtDeposit.setPayAmt(mchtDeposit.getPayAmt());
							mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt());
						}
						mchtDeposit.setUpdateDate(date);
						mchtDepositDtl.setDelFlag("0");
						mchtDepositDtl.setDepositId(mchtDeposit.getId());
						mchtDepositDtl.setTxnType("E");//违规扣款
						if(violateOrder.getViolateType().equals("A")){//入驻
							mchtDepositDtl.setTypeSub("E1");
						}else if(violateOrder.getViolateType().equals("B")){//发布信息
							mchtDepositDtl.setTypeSub("E2");
						}else if(violateOrder.getViolateType().equals("C")){//销售
							mchtDepositDtl.setTypeSub("E3");
						}else if(violateOrder.getViolateType().equals("D")){//售后
							mchtDepositDtl.setTypeSub("E4");
						}else if(violateOrder.getViolateType().equals("E")){//其它
							mchtDepositDtl.setTypeSub("E5");
						}
						if(violateOrder.getMoney()!=null){
							mchtDepositDtl.setTxnAmt(violateOrder.getMoney().negate());
						}else{
							mchtDepositDtl.setTxnAmt(new BigDecimal(0));
						}
						mchtDepositDtl.setPayAmt(mchtDeposit.getPayAmt());
						mchtDepositDtl.setBizType("2");//违规单
						mchtDepositDtl.setBizId(violateOrder.getId());
						mchtDepositDtl.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
						mchtDepositDtl.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
						mchtDepositDtl.setCreateDate(date);
						mchtDepositDtl.setUpdateDate(date);
						String violateTypeDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE", violateOrder.getViolateType());
						String violateActionDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION", violateOrder.getViolateAction());
						mchtDepositDtl.setRemarks("【"+violateTypeDesc+"】"+violateActionDesc);
						PlatformMsg platformMsg = new PlatformMsg();
						platformMsg.setMchtId(violateOrder.getMchtId());
						platformMsg.setMsgType("A5");//违规
						String statusDesc = DataDicUtil.getStatusDesc("BU_VIOLATE_ORDER", "STATUS", violateOrder.getStatus());
						platformMsg.setTitle("关于违规{"+statusDesc+"}的通知");
						String content = "";
						if(StringUtils.isEmpty(violateOrder.getSubOrderId()) || violateOrder.getSubOrderId() == 0){
							content = "您好！您有1条违规状态更新为{"+statusDesc+"}，违规单编号《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('violateOrder/violateOrderView?id="+violateOrder.getId()+"')\""+">"+violateOrder.getOrderCode()+"</a>》，请知悉！";
						}else{
							SubOrder subOrder = subOrderService.selectByPrimaryKey(violateOrder.getSubOrderId());
							content = "您好！您有1条违规状态更新为{"+statusDesc+"}，违规单编号《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('violateOrder/violateOrderView?id="+violateOrder.getId()+"')\""+">"+violateOrder.getOrderCode()+"</a>》，相关订单号：《<a href=\""+"javascript:;\""+"onclick="+"\"showDetail('subOrder/subOrderView?id="+violateOrder.getSubOrderId()+"')\""+">"+subOrder.getSubOrderCode()+"</a>》请知悉！";
						}
						platformMsg.setContent(content);
						platformMsg.setBizId(violateOrder.getId());
						platformMsg.setStatus("0");//未读
						platformMsg.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
						platformMsg.setCreateDate(new Date());
						platformMsg.setDelFlag("0");
						violateOrderService.update(violateOrder,mchtDeposit,mchtDepositDtl,platformMsg);
					}
					//TODO 发送短信
					String toSendMobile = "";
					List<String> contactTypes = new ArrayList<String>();
					contactTypes.add("2");//运营
					contactTypes.add("1");//电商
					contactTypes.add("3");//订单对接人
					for(String contactType:contactTypes){
						MchtContactExample e = new MchtContactExample();
						MchtContactExample.Criteria c = e.createCriteria();
						c.andDelFlagEqualTo("0");
						c.andMchtIdEqualTo(violateOrder.getMchtId());
						c.andContactTypeEqualTo(contactType);
						List<MchtContact> mchtContacts = mchtContactService.selectByExample(e);
						if(mchtContacts!=null && mchtContacts.size()>0){
							toSendMobile = mchtContacts.get(0).getMobile();
							break;
						}
					}
					if(!StringUtils.isEmpty(toSendMobile)){
						JSONObject param=new JSONObject();
						JSONObject reqData=new JSONObject();
						reqData.put("mobile", toSendMobile);
						reqData.put("content", "您好！您有新的违规："+violateOrder.getOrderCode()+"，请尽快登录平台处理。");
						reqData.put("smsType", "4");
						param.put("reqData", reqData);
						JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
					}
				}else{//2.驳回
					violateOrderService.updateByPrimaryKeySelective(violateOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "提交失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 驳回原因页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/toAuditRemarks.shtml")
	public ModelAndView toAuditRemarks(HttpServletRequest request) {
		String rtPage = "/violateOrder/toAuditRemarks";
		String violateOrderId = request.getParameter("violateOrderId");
		Map<String, Object> resMap = new HashMap<String, Object>();
		ViolateOrder violateOrder = violateOrderService.selectByPrimaryKey(Integer.parseInt(violateOrderId));
		resMap.put("remarks", violateOrder.getRemarks());
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @Title againAuditViolateOrderManager   
	 * @Description TODO(违规复审)   
	 * @author pengl
	 * @date 2019年2月21日 下午3:10:18
	 */
	@RequestMapping("/violateOrder/againAuditViolateOrderManager.shtml")
	public ModelAndView againAuditViolateOrderManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/violateOrder/againAuditViolateOrderList");
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		// 复审操作权限控制，复审状态为【未复审】时出现。权限控制为：角色ID为94
		boolean auditPower = false;
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andRoleIdEqualTo(Const.ROLE_ID_94).andStaffIdEqualTo(staffID);
		List<SysStaffRole> sysStaffRolesList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRolesList != null && sysStaffRolesList.size() > 0) {
			auditPower = true;
		}
		m.addObject("auditPower", auditPower);
		// 来源
		m.addObject("orderSourceList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "ORDER_SOURCE"));
		// 违规类型
		m.addObject("violateTypeList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_TYPE"));
		// 违规行为
		m.addObject("violateActionList", DataDicUtil.getStatusList("BU_VIOLATE_PUNISH_STANDARD", "VIOLATE_ACTION"));
		// 创建人
		List<Map<String, Object>> createByInfos = violateOrderService.getAllCreateBy();
		m.addObject("createByInfos", createByInfos);
		// 对接人
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID.toString());
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		m.addObject("sysStaffInfoCustomList", sysStaffInfoCustomList);
		// 复审状态
		m.addObject("againAuditStatusList", DataDicUtil.getStatusList("BU_VIOLATE_ORDER", "AGAIN_AUDIT_STATUS"));
		// 品类
		SysStaffProductTypeCustomExample sysstaffProductTypeex = new SysStaffProductTypeCustomExample();
		SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysstaffProductTypeex.createCriteria();
		sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(staffID);
		List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysstaffproductTypeService.selectByCustomExample(sysstaffProductTypeex);
		m.addObject("sysStaffProductTypeList", JSONArray.fromObject(sysStaffProductTypeList));
		return m;
	}
	
	/**
	 * 
	 * @Title againAuditViolateOrderList   
	 * @Description TODO(违规复审)   
	 * @author pengl
	 * @date 2019年2月21日 下午5:05:15
	 */
	@ResponseBody
	@RequestMapping(value = "/violateOrder/againAuditViolateOrderList.shtml")
	public Map<String, Object> againAuditViolateOrderList(HttpServletRequest request, Page page) {
		String role ="1";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			ViolateOrderCustomExample violateOrderCustomExample = new ViolateOrderCustomExample();
			ViolateOrderCustomExample.ViolateOrderCustomCriteria violateOrderCustomCriteria = violateOrderCustomExample.createCriteria();
			violateOrderCustomCriteria.andDelFlagEqualTo("0");
			violateOrderCustomCriteria.andProductTypeIdEqualTo(staffID);
			// 来源
			if(!StringUtil.isEmpty(request.getParameter("orderSource"))) {
				violateOrderCustomCriteria.andOrderSourceEqualTo(request.getParameter("orderSource"));
			}
			// 违规编号  orderCode
			if(!StringUtil.isEmpty(request.getParameter("orderCode"))) {
				violateOrderCustomCriteria.andOrderCodeEqualTo(request.getParameter("orderCode"));
			}
			// 商家序号
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				violateOrderCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			// 商家名称
			if(!StringUtil.isEmpty(request.getParameter("name"))) {
				violateOrderCustomCriteria.andNameLikeTo(request.getParameter("name"));
			}
			// 子订单号 
			if(!StringUtil.isEmpty(request.getParameter("subOrderCode"))) {
				violateOrderCustomCriteria.andSubOrderCodeEqualTo(request.getParameter("subOrderCode"));
			}
			// 违规类型
			if(!StringUtil.isEmpty(request.getParameter("violateType"))) {
				violateOrderCustomCriteria.andViolateTypeEqualTo(request.getParameter("violateType"));
			}
			// 违规行为 
			if(!StringUtil.isEmpty(request.getParameter("violateAction"))) {
				violateOrderCustomCriteria.andViolateActionEqualTo(request.getParameter("violateAction"));
			}
			// 创建人 
			if(!StringUtil.isEmpty(request.getParameter("createBy"))) {
				violateOrderCustomCriteria.andCreateByEqualTo(Integer.parseInt(request.getParameter("createBy")));
			}
			// 申诉状态
			if(!StringUtil.isEmpty(request.getParameter("status"))) {
				violateOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}else {
				violateOrderCustomCriteria.andStatusIn(Arrays.asList("4", "5", "6"));
			}
			// 对接人的商家
			if(!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {
				MchtPlatformContactCustomExample mchtPlatformContactCustomExample = new MchtPlatformContactCustomExample();
				MchtPlatformContactCustomExample.MchtPlatformContactCustomCriteria mchtPlatformContactCustomCriteria = mchtPlatformContactCustomExample.createCriteria();
				mchtPlatformContactCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
				mchtPlatformContactCustomCriteria.andplatContactStaffId(Integer.valueOf(request.getParameter("platContactStaffId")));
				List<MchtPlatformContactCustom> mchtPlatformContactCustoms = mchtPlatformContactService.selectMchtPlatformContactCustomByExample(mchtPlatformContactCustomExample);
				List<Integer> mchtIdlist = new ArrayList<Integer>();
				for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContactCustoms) {
					mchtIdlist.add(mchtPlatformContactCustom.getMchtId());
				}
				if(mchtIdlist != null && mchtIdlist.size() > 0) {
					violateOrderCustomCriteria.andMchtIdIn(mchtIdlist);
				}else {
					violateOrderCustomCriteria.andMchtIdEqualTo(0);
				}
			}
			// 复审状态
			if(!StringUtil.isEmpty(request.getParameter("againAuditStatus"))) {
				violateOrderCustomCriteria.andAgainAuditStatusEqualTo(request.getParameter("againAuditStatus"));
			}
			// 品类
			if(!StringUtil.isEmpty(request.getParameter("productTypeIds"))) {
				violateOrderCustomCriteria.andProductTypeIdIn(request.getParameter("productTypeIds").replaceAll(";", ","));
			}
			// 创建日期
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				violateOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				violateOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			// 申诉日期   
			if(!StringUtil.isEmpty(request.getParameter("beginComplainDate"))) {
				violateOrderCustomCriteria.andComplainDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("beginComplainDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endComplainDate"))){
				violateOrderCustomCriteria.andComplainDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("endComplainDate")+" 23:59:59"));
			}
			violateOrderCustomExample.setOrderByClause("t.create_date desc");
			violateOrderCustomExample.setLimitStart(page.getLimitStart());
			violateOrderCustomExample.setLimitSize(page.getLimitSize());
			totalCount = violateOrderService.countViolateOrderCustomByExample(violateOrderCustomExample);
			List<ViolateOrderCustom> violateOrderCustoms = violateOrderService.selectViolateOrderCustomByExample(violateOrderCustomExample);
			resMap.put("Rows", violateOrderCustoms);
			resMap.put("Total", totalCount);
			resMap.put("role",role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title againAuditManager   
	 * @Description TODO(违规复审)   
	 * @author pengl
	 * @date 2019年2月21日 下午5:25:12
	 */
	@RequestMapping(value = "/violateOrder/againAuditManager.shtml")
	public ModelAndView againAuditManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/violateOrder/updateAgainAuditStatus");
		if(!StringUtil.isEmpty(request.getParameter("id"))) {
			m.addObject("violateOrder", violateOrderService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id"))));
		}
		return m;
	}
	
	/**
	 * 
	 * @Title updateAgainAuditStatus   
	 * @Description TODO(违规复审)   
	 * @author pengl
	 * @date 2019年2月21日 下午9:41:06
	 */
	@RequestMapping("/violateOrder/updateAgainAuditStatus.shtml")
	public ModelAndView updateAgainAuditStatus(HttpServletRequest request, @RequestParam HashMap<String, String> paramMap) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			violateOrderService.updateAgainAuditStatus(paramMap, staffID);
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
	
	/**
	 * 
	 * @Title SubOrderPaymentAmount  
	 * @Description TODO(子订单的实付金额)   
	 * @author pengl
	 * @date 2019年2月21日 下午9:41:06
	 */
	@RequestMapping("/violateOrder/SubOrderPaymentAmount.shtml")
	@ResponseBody
	public Map<String, Object> SubOrderPaymentAmount(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		SubOrderExample subOrderExample = new SubOrderExample();
		Criteria createCriteria = subOrderExample.createCriteria();
		createCriteria.andSubOrderCodeEqualTo(id);
		List<SubOrder> subOrderList = subOrderService.selectByExample(subOrderExample);
		resMap.put("subOrder", subOrderList.get(0));
		return resMap;
	}

	//新增
	/**
	 * 是否挂起页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/toChoice.shtml")
	public ModelAndView toChoice(HttpServletRequest request) {
		String rtPage = "/violateOrder/toChoice";
		String violateComplainOrderId = request.getParameter("violateComplainOrderId");
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("violateComplainOrderId", violateComplainOrderId);
		return new ModelAndView(rtPage,resMap);
	}

	//新增
	/**
	 * 审核挂起原因
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/violateOrder/checkChoice.shtml")
	@ResponseBody
	public Map<String,Object> checkChoice(HttpServletRequest request){
		HashMap<String, Object> resMap = new HashMap<>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String violateComplainOrderId = request.getParameter("violateComplainOrderId");
			String suspendedReason = request.getParameter("suspendedReason");
			ViolateComplainOrder violateComplainOrder = violateComplainOrderService.selectByPrimaryKey(Integer.parseInt(violateComplainOrderId));
			violateComplainOrder.setProcesBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			ViolateOrder violateOrder = violateOrderService.selectByPrimaryKey(violateComplainOrder.getViolateOrderId());
			violateOrder.setSuspendedStatus("2");
			violateOrder.setSuspendedReason(suspendedReason);
			violateOrderService.update(violateOrder,violateComplainOrder);

		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "提交失败，请稍后重试");
			e.printStackTrace();
		}

		return resMap;
	}

}
