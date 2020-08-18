package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import com.jf.vo.ResponseMsg;

import net.sf.json.JSONArray;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @ClassName SingleProductActivityController
 * @Description TODO(单品活动)
 * @author pengl
 * @date 2017年10月9日 下午6:19:32
 */
@SuppressWarnings("serial")
@Controller
public class SingleProductActivityController extends BaseController {

	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private SeckillTimeService seckillTimeService;
	
	@Autowired
	private SingleProductActivityService singleProductActivityService;
	
	@Autowired
	private SingleProductActivityAuditLogService singleProductActivityAuditLogService;
	
	@Autowired
	private MchtSingleActivityCnfService mchtSingleActivityCnfService;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private SeckillBrandGroupService seckillBrandGroupService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SysStaffRoleService sysStaffRoleService;
	
	@Autowired
	private SingleProductActivityControlService singleProductActivityControlService;
	
	@Autowired
	private ActivityRuleConfigurationService activityRuleConfigurationService;
	
	@Autowired
	private SysStaffInfoService sysStaffInfoService;
	
	@Resource
	private ProductItemService productItemService;

	@Resource
	private TypeColumnPvStatisticalService typeColumnPvStatisticalService;

	/**
	 * 
	 * @Title itemActivityManager   
	 * @Description TODO(单品活动管理)   
	 * @author pengl
	 * @date 2017年9月29日 上午10:06:00
	 */
	@RequestMapping(value = "/singleProductActivity/itemActivityManager.shtml")
	public ModelAndView itemActivityManager(HttpServletRequest request, String type, String flag) {
		ModelAndView m = new ModelAndView();
		m.addObject("type", type);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String staffID = this.getSessionStaffBean(request).getStaffID(); //用户ID
		if(flag.equals("1")) { //秒杀时间设置 
			SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
			sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(staffID));
			List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
			for(SysStaffRole sysStaffRole : sysStaffRoleList) {
				if(sysStaffRole.getRoleId() == 1) {
					m.addObject("roleId", 1);
				}else if(sysStaffRole.getRoleId() == 90) {
					request.getSession().setAttribute("roleId90", sysStaffRole.getRoleId());
				}
			}
			m.setViewName("/singleProductActivity/seckillTime");
		}else {
			ProductTypeExample productTypeExample = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
			productTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
			m.addObject("isCwOrgStatus", isCwOrgStatus);
			
			List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
			m.addObject("productTypeList", productTypeList);
		}
		if(flag.equals("2")) { //审核
			boolean role60 = false;
			SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
			sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(staffID)).andRoleIdEqualTo(Const.ROLE_ID_60);
			List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
			if(sysStaffRoleList != null && sysStaffRoleList.size() > 0 ) {
				role60 = true;
			}
			m.addObject("role60", role60);
			SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
			SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
			sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
			List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
			m.addObject("sysStaffInfoCustomList", sysStaffInfoCustomList);
			String isManagement = this.getSessionStaffBean(request).getIsManagement();
			m.addObject("isManagement", isManagement);
			m.addObject("staffID", staffID);
			m.setViewName("/singleProductActivity/auditManagerList");
		}
		if(flag.equals("3")) { //排期
			SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
			SeckillTimeExample.Criteria seckillTimeCriteria = seckillTimeExample.createCriteria();
			seckillTimeCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(type) && !"3".equals(type)) {
				seckillTimeCriteria.andStatusEqualTo("1");
			}
			seckillTimeExample.setOrderByClause(" id asc");
			List<SeckillTime> seckillTimeList = seckillTimeService.selectByExample(seckillTimeExample);
			m.addObject("seckillTimeList", seckillTimeList);
			if(!StringUtil.isEmpty(type) && "3".equals(type)) {
				try {
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, -1);
					SeckillBrandGroupExample seckillBrandGroupExample = new SeckillBrandGroupExample();
					SeckillBrandGroupExample.Criteria seckillBrandGroupCriteria = seckillBrandGroupExample.createCriteria();
					seckillBrandGroupCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1")
						.andBeginTimeGreaterThanOrEqualTo(sdf.parse(sdf.format(calendar.getTime())));
					List<SeckillBrandGroup> seckillBrandGroupList = seckillBrandGroupService.selectByExample(seckillBrandGroupExample);
					m.addObject("seckillBrandGroupList", seckillBrandGroupList);
					if("3".equals(type)||"4".equals(type)){
					m.addObject("seckill", 1503);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			boolean role60 = false;
			SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
			sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(staffID)).andRoleIdEqualTo(Const.ROLE_ID_60);
			List<SysStaffRole> sysStaffRoleList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
			if(sysStaffRoleList != null && sysStaffRoleList.size() > 0 ) {
				role60 = true;
			}
			m.addObject("role60", role60);
			SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
			SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
			sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
			List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
			m.addObject("sysStaffInfoCustomList", sysStaffInfoCustomList);
			String isManagement = this.getSessionStaffBean(request).getIsManagement();
			m.addObject("isManagement", isManagement);
			m.addObject("staffID", staffID);
			m.setViewName("/singleProductActivity/scheduleManagerList");
		}
		if(flag.equals("4")) { //报名复审
			m.setViewName("/singleProductActivity/singleProductActivityRecheck");
			m.addObject("typeList", DataDicUtil.getStatusList("BU_SINGLE_PRODUCT_ACTIVITY", "TYPE"));
		}
		if(flag.equals("5")) { //全部报名单品
			m.setViewName("/singleProductActivity/singleProductActivityList");
			m.addObject("typeList", DataDicUtil.getStatusList("BU_SINGLE_PRODUCT_ACTIVITY", "TYPE"));
		}
		m.addObject("auditStatusList", DataDicUtil.getStatusList("BU_SINGLE_PRODUCT_ACTIVITY", "AUDIT_STATUS"));
		return m;
	}
	
	/**
	 * 
	 * @Title getSeckillTimeList   
	 * @Description TODO(异步获取限时秒杀时间)   
	 * @author pengl
	 * @date 2017年9月29日 上午11:14:54
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/getSeckillTimeList.shtml")
	public Map<String, Object> getSeckillTimeList(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount = "0";
		try {
			String status = request.getParameter("status");
			String seckillType = request.getParameter("seckillType");
			SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
			SeckillTimeExample.Criteria c = seckillTimeExample.createCriteria();
			c.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(status)){
				c.andStatusEqualTo(status);
			}
			if(!StringUtils.isEmpty(seckillType)){
				c.andSeckillTypeEqualTo(seckillType);
			}
			seckillTimeExample.setOrderByClause(" id asc");
			dataList = seckillTimeService.selectByExample(seckillTimeExample);
			Integer total = seckillTimeService.countByExample(seckillTimeExample);
			totalCount = total.toString();
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateSeckillTime   
	 * @Description TODO(修改限时秒杀时间)   
	 * @author pengl
	 * @date 2017年9月29日 下午2:43:33
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/updateSeckillTime.shtml")
	public Map<String, Object> updateSeckillTime(HttpServletRequest request, Integer id, String flag) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
			seckillTimeExample.createCriteria().andDelFlagEqualTo("0")
					.andIdEqualTo(id);
			SeckillTime seckillTime = new SeckillTime();
			seckillTime.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(
					request).getStaffID()));
			seckillTime.setUpdateDate(new Date());
			if (flag.equals("start")) {
				seckillTime.setStatus("1");
			} else if (flag.equals("del")) {
				seckillTime.setDelFlag("1");
			} else if (flag.equals("close")) {
				seckillTime.setStatus("0");
			}
			seckillTimeService.updateByExampleSelective(seckillTime, seckillTimeExample);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 
	 * @Title editSeckillTime   
	 * @Description TODO(编辑限时秒杀时间)   
	 * @author pengl
	 * @date 2017年9月29日 下午4:06:11
	 */
	@RequestMapping(value = "/singleProductActivity/editSeckillTime.shtml")
	public ModelAndView editSeckillTime(HttpServletRequest request, Integer id) {
		ModelAndView m = new ModelAndView("/singleProductActivity/editSeckillTime");
		if(id != null) {
			SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
			seckillTimeExample.createCriteria().andDelFlagEqualTo("0")
					.andIdEqualTo(id);
			List<SeckillTime> seckillTimeList = seckillTimeService.selectByExample(seckillTimeExample);
			m.addObject("id", id);
			m.addObject("seckillTime", seckillTimeList.get(0));
		}
		return m;
	}
	
	/**
	 * 
	 * @Title getSeckillTime   
	 * @Description TODO(验证限时秒杀开始时间是否存在)   
	 * @author pengl
	 * @date 2017年9月30日 下午1:51:37
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/getSeckillTime.shtml")
	public Map<String, Object> getSeckillTime(HttpServletRequest request, String startHours, String startMin, Integer id) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "200");
		resMap.put("returnMsg", "成功");
		try {
			SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
			SeckillTimeExample.Criteria seckillTimeCriteria = seckillTimeExample.createCriteria();
			seckillTimeCriteria.andDelFlagEqualTo("0")
					.andStartHoursEqualTo(startHours)
					.andStartMinEqualTo(startMin)
					.andSeckillTypeEqualTo(request.getParameter("seckillType"));
			if(id != null) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(id);
				seckillTimeCriteria.andIdNotIn(list);
			}
			List<SeckillTime> seckillTimeList = seckillTimeService.selectByExample(seckillTimeExample);
			if(seckillTimeList.size() > 0) {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "开始时间点已经存在，请重新设置！");
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateOrAddSeckillTime   
	 * @Description TODO(修改或新增限时秒杀时间)   
	 * @author pengl
	 * @date 2017年9月30日 下午2:33:57
	 */
	@RequestMapping(value = "/singleProductActivity/updateOrAddSeckillTime.shtml")
	public ModelAndView updateOrAddSeckillTime(HttpServletRequest request, SeckillTime seckillTime) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		try {
			if (seckillTime.getId() != null) { //修改
				SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
				seckillTimeExample.createCriteria().andIdEqualTo(
						seckillTime.getId());
				seckillTime.setUpdateBy(Integer.valueOf(this
						.getSessionStaffBean(request).getStaffID()));
				seckillTime.setUpdateDate(new Date());
				seckillTimeService.updateByExampleSelective(seckillTime,
						seckillTimeExample);
			} else {
				seckillTime.setStatus("0");
				seckillTime.setCreateBy(Integer.valueOf(this
						.getSessionStaffBean(request).getStaffID()));
				seckillTime.setCreateDate(new Date());
				seckillTime.setDelFlag("0");
				seckillTimeService.insertSelective(seckillTime);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * @Title getSingleProductActivityList   
	 * @Description TODO(单品活动审核OR排期)   
	 * @author pengl
	 * @date 2017年9月30日 下午2:59:37
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/getSingleProductActivityList.shtml")
	public Map<String, Object> getSingleProductActivityList(HttpServletRequest request, Page page, String flag,
			SingleProductActivityCustom singleProductActivityCustom, Long startActivityPrice, Long endActivityPrice, 
			Integer productTypeId, String updateDateFlag, String startTime) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SingleProductActivityCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
			SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) { //对接人
				Integer platContactStaffId = Integer.valueOf(request.getParameter("platContactStaffId"));
				//我对接的商家/我协助的商家
				singleProductActivityCustomCriteria.andPlatContactStaffIdEqualTo(platContactStaffId);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(flag != null && !"".equals(flag)) {
				if(flag.equals("1")) {// 审核
					singleProductActivityCustomCriteria.andSingleProductActivityProduct();
					singleProductActivityCustomCriteria.andAuditStatusIn("0,2,4,5");
					singleProductActivityCustomCriteria.andTypeEqualTo(singleProductActivityCustom.getType());// 单品活动类型
					if(!StringUtil.isEmpty(singleProductActivityCustom.getCode())) {
						singleProductActivityCustomCriteria.andCodeProductByEqualTo(singleProductActivityCustom.getCode());
					}
					if(!StringUtil.isEmpty(singleProductActivityCustom.getBrandName())) {
						singleProductActivityCustomCriteria.andBrandNameProductLike(singleProductActivityCustom.getBrandName());
					} 
					if(!StringUtil.isEmpty(singleProductActivityCustom.getArtNo())) {
						singleProductActivityCustomCriteria.andArtNoProductLike(singleProductActivityCustom.getArtNo());
					}
					if(!StringUtil.isEmpty(singleProductActivityCustom.getShopName())) {
						singleProductActivityCustomCriteria.andShopNameLike(singleProductActivityCustom.getShopName());
					}
					if(startActivityPrice != null && endActivityPrice != null) {
						singleProductActivityCustomCriteria.andActivityPriceBetween(BigDecimal.valueOf(startActivityPrice), BigDecimal.valueOf(endActivityPrice));
					}else {
						if(startActivityPrice != null)
							singleProductActivityCustomCriteria.andActivityPriceGreaterThan(BigDecimal.valueOf(startActivityPrice));
						if(endActivityPrice != null)
							singleProductActivityCustomCriteria.andActivityPriceLessThan(BigDecimal.valueOf(endActivityPrice));
					}
					if(singleProductActivityCustom.getExpectedDate() != null) {
						singleProductActivityCustomCriteria.andExpectedDateByEqualTo(sdf.format(singleProductActivityCustom.getExpectedDate()));
					}
					if(singleProductActivityCustom.getBeginTime() != null) {
						singleProductActivityCustomCriteria.andBeginTimeByEqualTo(sdf.format(singleProductActivityCustom.getBeginTime()), null);
					}
					if(!StringUtil.isEmpty(singleProductActivityCustom.getAuditStatus())) {
						singleProductActivityCustomCriteria.andAuditStatus(singleProductActivityCustom.getAuditStatus());
					}
					
					//钟表运营部状态，只获取主营类目为钟表 
					String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
					if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
						String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
						if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
							singleProductActivityCustomCriteria.andProductTypeEqualTo(Integer.parseInt(isCwOrgProductTypeId));
						}
					}else {
						if(productTypeId != null) {
							singleProductActivityCustomCriteria.andProductTypeEqualTo(productTypeId);
						}
					}
					
					if(!StringUtil.isEmpty(updateDateFlag)) {
						singleProductActivityCustomCriteria.andUpdateDateBetwwenOrBefore(updateDateFlag);
					}else {
						singleProductActivityCustomCriteria.andUpdateDateBetwwenOrBefore("between");
					}
					if(!StringUtil.isEmpty(request.getParameter("seckillType"))){
						singleProductActivityCustomCriteria.andSeckillTypeEqualTo(request.getParameter("seckillType"));
					}
					
					if(singleProductActivityCustom.getOriginalPriceBegin() != null) {
						singleProductActivityCustomCriteria.andOriginalPriceGreaterThanOrEqualTo(singleProductActivityCustom.getOriginalPriceBegin());
					}
					if(singleProductActivityCustom.getOriginalPriceEnd() != null) {
						singleProductActivityCustomCriteria.andOriginalPriceLessThanOrEqualTo(singleProductActivityCustom.getOriginalPriceEnd());
					}
					singleProductActivityCustomExample.setOrderByClause(" t.is_close asc, t.update_date asc");
					singleProductActivityCustomExample.setLimitSize(page.getLimitSize());
					singleProductActivityCustomExample.setLimitStart(page.getLimitStart());
					dataList = singleProductActivityService.selectBySingleProductActivityProductExampl(singleProductActivityCustomExample);
					totalCount = singleProductActivityService.countBySingleProductActivityProductExample(singleProductActivityCustomExample);
				}
				
				if(flag.equals("2")) {// 排期
					singleProductActivityCustomCriteria.andSingleProductActivityProduct();
					singleProductActivityCustomCriteria.andAuditStatusIn("1,4,3");
					singleProductActivityCustomCriteria.andTypeEqualTo(singleProductActivityCustom.getType());// 单品活动类型
					singleProductActivityCustomCriteria.andIsCloseEqualTo("0"); //是否关闭
					singleProductActivityCustomCriteria.andEndTimeByEqualTo();// 活动结束时间
					
					if(!StringUtil.isEmpty(singleProductActivityCustom.getCode())) {
						singleProductActivityCustomCriteria.andCodeProductByEqualTo(singleProductActivityCustom.getCode());
					}
					if(!StringUtil.isEmpty(singleProductActivityCustom.getBrandName())) {
						singleProductActivityCustomCriteria.andBrandNameProductLike(singleProductActivityCustom.getBrandName());
					} 
					if(!StringUtil.isEmpty(singleProductActivityCustom.getArtNo())) {
						singleProductActivityCustomCriteria.andArtNoProductLike(singleProductActivityCustom.getArtNo());
					}
					if(!StringUtil.isEmpty(singleProductActivityCustom.getShopName())) {
						singleProductActivityCustomCriteria.andShopNameLike(singleProductActivityCustom.getShopName());
					}
                    //合作类型
                    if (!StringUtil.isEmpty(request.getParameter("mchtType"))){
                        singleProductActivityCustomCriteria.andMchtTypeEqualTo(request.getParameter("mchtType"));
                    }
                    //是否自营
					if (!StringUtil.isEmpty(request.getParameter("isManageSelf"))){
						singleProductActivityCustomCriteria.andIsManageSelfEqualTo(request.getParameter("isManageSelf"));
					}

					if(startActivityPrice != null && endActivityPrice != null) {
						singleProductActivityCustomCriteria.andActivityPriceBetween(BigDecimal.valueOf(startActivityPrice), BigDecimal.valueOf(endActivityPrice));
					}else {
						if(startActivityPrice != null)
							singleProductActivityCustomCriteria.andActivityPriceGreaterThan(BigDecimal.valueOf(startActivityPrice));
						if(endActivityPrice != null)
							singleProductActivityCustomCriteria.andActivityPriceLessThan(BigDecimal.valueOf(endActivityPrice));
					}
					if(singleProductActivityCustom.getExpectedDate() != null) {
						singleProductActivityCustomCriteria.andExpectedDateByEqualTo(sdf.format(singleProductActivityCustom.getExpectedDate()));
					}
					if(singleProductActivityCustom.getBeginTime() != null) {
						singleProductActivityCustomCriteria.andBeginTimeByEqualTo(sdf.format(singleProductActivityCustom.getBeginTime()), startTime);
					}
					if(!StringUtil.isEmpty(singleProductActivityCustom.getAuditStatus())) {
						singleProductActivityCustomCriteria.andAuditStatus(singleProductActivityCustom.getAuditStatus());
					}
					
					//钟表运营部状态，只获取主营类目为钟表 
					String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
					if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
						String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
						if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
							singleProductActivityCustomCriteria.andProductTypeEqualTo(Integer.parseInt(isCwOrgProductTypeId));
						}
					}else {
						if(productTypeId != null) {
							singleProductActivityCustomCriteria.andProductTypeEqualTo(productTypeId);
						}
					}
					
//					if(singleProductActivityCustom.getSeqNo() != null) {
//						singleProductActivityCustomExample.setOrderByClause(" t.seq_no is NULL, t.seq_no = 0, t.seq_no asc, "
//								+ "t.expected_date asc, t.audit_status asc, t.begin_time asc ");
//					}else {
//						singleProductActivityCustomExample.setOrderByClause(" t.expected_date asc, t.audit_status asc, t.begin_time asc ");
//					}
					singleProductActivityCustomExample.setOrderByClause("ifnull(t.seq_no, 99999) asc,t.id desc");
					//品牌团
					if(!StringUtil.isEmpty(request.getParameter("seckillBrandGroupId"))) {
						if("000".equals(request.getParameter("seckillBrandGroupId"))) { //全部品牌团
							singleProductActivityCustomCriteria.andSeckillBrandGroupIn();
						}else if("999".equals(request.getParameter("seckillBrandGroupId"))) { //未加入
							singleProductActivityCustomCriteria.andNotExistsSeckillBrandGroupProduct();
						}else {
							singleProductActivityCustomCriteria.andSeckillBrandGroupIdEqualTo(request.getParameter("seckillBrandGroupId"));
						}
					}
					if(!StringUtil.isEmpty(request.getParameter("seckillType"))){
						singleProductActivityCustomCriteria.andSeckillTypeEqualTo(request.getParameter("seckillType"));
					}
					
					if(singleProductActivityCustom.getOriginalPriceBegin() != null) {
						singleProductActivityCustomCriteria.andOriginalPriceGreaterThanOrEqualTo(singleProductActivityCustom.getOriginalPriceBegin());
					}
					if(singleProductActivityCustom.getOriginalPriceEnd() != null) {
						singleProductActivityCustomCriteria.andOriginalPriceLessThanOrEqualTo(singleProductActivityCustom.getOriginalPriceEnd());
					}
					
					singleProductActivityCustomExample.setLimitSize(page.getLimitSize());
					singleProductActivityCustomExample.setLimitStart(page.getLimitStart());
					List<SingleProductActivityCustom> singleProductActivityCustomList = singleProductActivityService.selectBySingleProductActivityProductExampl(singleProductActivityCustomExample);
					
					//限时抢购排期（当 admin 打开《限时抢购排期》，当审核状态=排期通过，会出现一个【自定义排期】）
					if(!StringUtil.isEmpty(request.getParameter("type")) && "3".equals(request.getParameter("type"))) {
						String staffID = this.getSessionStaffBean(request).getStaffID();
						if(!StringUtil.isEmpty(staffID) && "1".equals(staffID)) { //admin
							for(SingleProductActivityCustom singleCustom : singleProductActivityCustomList) {
								if(!StringUtil.isEmpty(singleCustom.getAuditStatus()) && "3".equals(singleCustom.getAuditStatus())) { //审核状态=排期通过
									singleCustom.setCustomFlag("pass");
								}
							}
						}
					//限时抢购排期时 查询最近一个月的3条记录商品历史销量
				      ArrayList<Integer> productIds = new ArrayList<Integer>();
				      for(SingleProductActivityCustom singleCustom : singleProductActivityCustomList) {
				       if(!productIds.contains(singleCustom.getProductId())){
				        productIds.add(singleCustom.getProductId()); 
				       }
				      }
				      Date now = new Date();
				      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				      Calendar c = Calendar.getInstance();
				      c.setTime(now);
				      c.add(Calendar.MONTH, -1);
				      Date m = c.getTime();
				      List<SingleProductActivityCustom> allProductQuantitySums = singleProductActivityService.selectProductQuantitySumsByProductIds(productIds);
				      for(SingleProductActivityCustom singleCustom : singleProductActivityCustomList) {
				    	  ArrayList<SingleProductActivityCustom> list = new ArrayList<>();
				    	  for (SingleProductActivityCustom singleProductActivityCustom2 : allProductQuantitySums) {
				    		  if(singleCustom.getProductId().equals(singleProductActivityCustom2.getProductId())){
			    	             list.add(singleProductActivityCustom2);
			    	             if(list.size()==3){
			    	            	break;
			    	             }
			    	            }
				    	  } 
				    	 singleCustom.setProductQuantitySums(list);
			    	  }
					}
					dataList = singleProductActivityCustomList;
					totalCount = singleProductActivityService.countBySingleProductActivityProductExample(singleProductActivityCustomExample);
                    if (dataList!=null && dataList.size()>0){
                        dataList= caculateGrossMargin(dataList);
                    }
				}
				
				if(flag.equals("3")) {// 全部报名复审
					singleProductActivityCustomCriteria.andSingleProductActivityProduct();
					singleProductActivityCustomCriteria.andAuditStatusIn("3,5");
					singleProductActivityCustomCriteria.andIsCloseEqualTo("0");
					singleProductActivityCustomCriteria.andEndTimeByEqualTo();// 活动结束时间
					if(!StringUtils.isEmpty(singleProductActivityCustom.getType())) {
						singleProductActivityCustomCriteria.andTypeEqualTo(singleProductActivityCustom.getType());
					}
					if(!StringUtil.isEmpty(singleProductActivityCustom.getCode())) {
						singleProductActivityCustomCriteria.andCodeProductByEqualTo(singleProductActivityCustom.getCode());
					}
					if(!StringUtil.isEmpty(singleProductActivityCustom.getBrandName())) {
						singleProductActivityCustomCriteria.andBrandNameProductLike(singleProductActivityCustom.getBrandName());
					} 
					if(!StringUtil.isEmpty(singleProductActivityCustom.getArtNo())) {
						singleProductActivityCustomCriteria.andArtNoProductLike(singleProductActivityCustom.getArtNo());
					}
					if(!StringUtil.isEmpty(singleProductActivityCustom.getShopName())) {
						singleProductActivityCustomCriteria.andShopNameLike(singleProductActivityCustom.getShopName());
					}
					if(startActivityPrice != null && endActivityPrice != null) {
						singleProductActivityCustomCriteria.andActivityPriceBetween(BigDecimal.valueOf(startActivityPrice), BigDecimal.valueOf(endActivityPrice));
					}else {
						if(startActivityPrice != null)
							singleProductActivityCustomCriteria.andActivityPriceGreaterThan(BigDecimal.valueOf(startActivityPrice));
						if(endActivityPrice != null)
							singleProductActivityCustomCriteria.andActivityPriceLessThan(BigDecimal.valueOf(endActivityPrice));
					}
					if(singleProductActivityCustom.getBeginTime() != null)
						singleProductActivityCustomCriteria.andBeginTimeByEqualTo(sdf.format(singleProductActivityCustom.getBeginTime()), null);
					if(!StringUtil.isEmpty(singleProductActivityCustom.getAuditStatus())) {
						singleProductActivityCustomCriteria.andAuditStatus(singleProductActivityCustom.getAuditStatus());
					}
					
					//钟表运营部状态，只获取主营类目为钟表 
					String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
					if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
						String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
						if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
							singleProductActivityCustomCriteria.andProductTypeEqualTo(Integer.parseInt(isCwOrgProductTypeId));
						}
					}else {
						if(productTypeId != null) {
							singleProductActivityCustomCriteria.andProductTypeEqualTo(productTypeId);
						}
					}
					
					singleProductActivityCustomExample.setOrderByClause(" t.begin_time asc ");
					singleProductActivityCustomExample.setLimitSize(page.getLimitSize());
					singleProductActivityCustomExample.setLimitStart(page.getLimitStart());
					dataList = singleProductActivityService.selectBySingleProductActivityProductExampl(singleProductActivityCustomExample);
					totalCount = singleProductActivityService.countBySingleProductActivityProductExample(singleProductActivityCustomExample);
				}
				
				if(flag.equals("4")) {// 全部报名单品
					singleProductActivityCustomCriteria.andSingleProductActivityProduct();
					singleProductActivityCustomCriteria.andIsCloseEqualTo("0");
					singleProductActivityCustomCriteria.andEndTimeByEqualTo();// 活动结束时间
					if(!StringUtils.isEmpty(singleProductActivityCustom.getType())) {
						singleProductActivityCustomCriteria.andTypeEqualTo(singleProductActivityCustom.getType());
					}
					if(!StringUtil.isEmpty(singleProductActivityCustom.getCode())) {
						singleProductActivityCustomCriteria.andCodeProductByEqualTo(singleProductActivityCustom.getCode());
					}
					if(!StringUtil.isEmpty(singleProductActivityCustom.getBrandName())) {
						singleProductActivityCustomCriteria.andBrandNameProductLike(singleProductActivityCustom.getBrandName());
					} 
					if(!StringUtil.isEmpty(singleProductActivityCustom.getArtNo())) {
						singleProductActivityCustomCriteria.andArtNoProductLike(singleProductActivityCustom.getArtNo());
					}
					if(!StringUtil.isEmpty(singleProductActivityCustom.getShopName())) {
						singleProductActivityCustomCriteria.andShopNameLike(singleProductActivityCustom.getShopName());
					}
					if(startActivityPrice != null && endActivityPrice != null) {
						singleProductActivityCustomCriteria.andActivityPriceBetween(BigDecimal.valueOf(startActivityPrice), BigDecimal.valueOf(endActivityPrice));
					}else {
						if(startActivityPrice != null)
							singleProductActivityCustomCriteria.andActivityPriceGreaterThan(BigDecimal.valueOf(startActivityPrice));
						if(endActivityPrice != null)
							singleProductActivityCustomCriteria.andActivityPriceLessThan(BigDecimal.valueOf(endActivityPrice));
					}
					if(singleProductActivityCustom.getExpectedDate() != null)
						singleProductActivityCustomCriteria.andExpectedDateByEqualTo(sdf.format(singleProductActivityCustom.getExpectedDate()));
					if(singleProductActivityCustom.getBeginTime() != null)
						singleProductActivityCustomCriteria.andBeginTimeByEqualTo(sdf.format(singleProductActivityCustom.getBeginTime()), null);
					if(!StringUtil.isEmpty(singleProductActivityCustom.getAuditStatus())) {
						singleProductActivityCustomCriteria.andAuditStatus(singleProductActivityCustom.getAuditStatus());
					}
					singleProductActivityCustomExample.setOrderByClause(" t.expected_date desc, t.begin_time asc ");
					singleProductActivityCustomExample.setLimitSize(page.getLimitSize());
					singleProductActivityCustomExample.setLimitStart(page.getLimitStart());
					dataList = singleProductActivityService.selectBySingleProductActivityProductExampl(singleProductActivityCustomExample);
					totalCount = singleProductActivityService.countBySingleProductActivityProductExample(singleProductActivityCustomExample);
				}
				for(SingleProductActivityCustom singleCustom : dataList) {
					singleCustom.setProductPic(FileUtil.getMiddleImageName(singleCustom.getProductPic()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList();
			totalCount = 0;
		}

		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateSingleProductActivityList   
	 * @Description TODO(关闭)   
	 * @author pengl
	 * @date 2017年9月30日 下午2:59:37
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/updateSingleProductActivityList.shtml")
	public Map<String, Object> updateSingleProductActivityList(HttpServletRequest request, String str, String strProductId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			List<Integer> list = new ArrayList<Integer>();
			if(!StringUtil.isEmpty(str)) {
				String[] ids = str.split(",");
				for(String id : ids) {
					list.add(Integer.parseInt(id));
				}
			}
			List<Integer> productIdList = new ArrayList<Integer>();
			if(!StringUtil.isEmpty(strProductId)) {
				String[] productIds = strProductId.split(",");
				for(String productId : productIds) {
					productIdList.add(Integer.parseInt(productId));
				}
			}
			singleProductActivityService.updateSingleProductActivityOrProductIsClose(staffId, list, productIdList);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 
	 * @Title auditOrRetrial   
	 * @Description TODO(审核、重审)   
	 * @author pengl
	 * @date 2017年9月30日 下午2:59:37
	 */
	@RequestMapping(value = "/singleProductActivity/auditOrRetrial.shtml")
	public ModelAndView auditOrRetrial(HttpServletRequest request, Integer id) {
		ModelAndView m = new ModelAndView("/singleProductActivity/singleProductActivityAudit");
		SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
		SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
		singleProductActivityCustomCriteria.andDelFlagEqualTo("0").andIdEqualTo(id);
		List<SingleProductActivityCustom> singleProductActivityList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
		if(singleProductActivityList != null && singleProductActivityList.size() > 0) 
			m.addObject("singleProductActivity", singleProductActivityList.get(0));
		return m;
	}
	
	/**
	 * 
	 * @Title saveSingleProductActivityAuditLog   
	 * @Description TODO(保存单品活动审核流水)   
	 * @author pengl
	 * @date 2017年9月30日 下午2:59:37
	 */
	@RequestMapping(value = "/singleProductActivity/saveSingleProductActivityAuditLog.shtml")
	public ModelAndView saveSingleProductActivityAuditLog(HttpServletRequest request, Integer id, BigDecimal activityPrice, 
			BigDecimal comparePrice, String auditStatus, String remarks) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			String seckillType = request.getParameter("seckillType");
			singleProductActivityService.saveSingleProductActivityAuditLog(staffId, id, activityPrice, 
					comparePrice, auditStatus, remarks,seckillType);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 
	 * @Title showSingleProductActivity   
	 * @Description TODO(查看单品活动)   
	 * @author pengl
	 * @date 2017年9月30日 下午2:59:37
	 */
	@RequestMapping(value = "/singleProductActivity/showSingleProductActivity.shtml")
	public ModelAndView showSingleProductActivity(HttpServletRequest request, Integer id) {
		ModelAndView m = new ModelAndView("/singleProductActivity/showSingleProductActivity");
		SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
		SingleProductActivityCustomExample.Criteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
		singleProductActivityCustomCriteria.andDelFlagEqualTo("0")
			.andIdEqualTo(id);
		List<SingleProductActivityCustom> singleProductActivityCustomList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
		if(singleProductActivityCustomList != null && singleProductActivityCustomList.size() > 0) 
			m.addObject("singleProductActivityCustom", singleProductActivityCustomList.get(0));
		return m;
	}
	
	/**
	 * 
	 * @Title getSingleProductActivity   
	 * @Description TODO(根据商品ID获取单品活动)   
	 * @author pengl
	 * @date 2017年9月30日 下午2:59:37
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/getSingleProductActivity.shtml")
	public Map<String, Object> getSingleProductActivity(HttpServletRequest request, Page page, Integer productId ) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		Integer totalCount = 0;
		try {
			SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
			SingleProductActivityCustomExample.Criteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
			singleProductActivityCustomCriteria.andDelFlagEqualTo("0")
				.andAuditStatusEqualTo("3")
				.andProductIdEqualTo(productId);
			singleProductActivityCustomExample.setOrderByClause(" id desc");
			singleProductActivityCustomExample.setLimitSize(page.getLimitSize());
			singleProductActivityCustomExample.setLimitStart(page.getLimitStart());
			dataList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
			totalCount = singleProductActivityService.countByCustomExample(singleProductActivityCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = 0;
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title getSingleProductActivityAuditLog   
	 * @Description TODO(获取单品活动审核流水)   
	 * @author pengl
	 * @date 2017年9月30日 下午2:59:37
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/getSingleProductActivityAuditLog.shtml")
	public Map<String, Object> getSingleProductActivityAuditLog(HttpServletRequest request, Page page, Integer singleProductActivityId ) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		Integer totalCount = 0;
		try {
			SingleProductActivityAuditLogExample singleProductActivityAuditLogExample = new SingleProductActivityAuditLogExample();
			SingleProductActivityAuditLogExample.Criteria singleProductActivityAuditLogCriteria = singleProductActivityAuditLogExample.createCriteria();
			singleProductActivityAuditLogCriteria.andDelFlagEqualTo("0")
				.andSingleProductActivityIdEqualTo(singleProductActivityId);
			singleProductActivityAuditLogExample.setOrderByClause(" t.id desc");
			singleProductActivityAuditLogExample.setLimitSize(page.getLimitSize());
			singleProductActivityAuditLogExample.setLimitStart(page.getLimitStart());
			dataList = singleProductActivityAuditLogService.selectByCustomExample(singleProductActivityAuditLogExample);
			totalCount = singleProductActivityAuditLogService.countByCustomExample(singleProductActivityAuditLogExample);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = 0;
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title scheduleAuditOrRetrial   
	 * @Description TODO(排期审核、重审)   
	 * @author pengl
	 * @date 2017年9月30日 下午2:59:37
	 */
	@RequestMapping(value = "/singleProductActivity/scheduleAuditOrRetrial.shtml")
	public ModelAndView scheduleAuditOrRetrial(HttpServletRequest request, Integer id, String flag) {
		ModelAndView m = new ModelAndView("/singleProductActivity/scheduleAuditOrRetrial");
		SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
		SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
		singleProductActivityCustomCriteria.andDelFlagEqualTo("0").andIdEqualTo(id);
		List<SingleProductActivityCustom> singleProductActivityCustomList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
		if(singleProductActivityCustomList != null && singleProductActivityCustomList.size() > 0) {
			m.addObject("singleProductActivity", singleProductActivityCustomList.get(0));

			ProductItemExample e = new ProductItemExample();
			e.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(singleProductActivityCustomList.get(0).getProductId()).andStockGreaterThan(0);
			List<ProductItem> productItems = productItemService.selectByExample(e);
			ArrayList<Double> list = new ArrayList<Double>();
			if(productItems.size()!=0){
				for (int i = 0; i < productItems.size(); i++) {
					list.add(productItems.get(i).getSalePrice().doubleValue());
				}
			}
			if(list.size()>0){
				Double min = Collections.min(list);
				Double max = Collections.max(list);
				if(min==max){
					m.addObject("currentPrice", min);
				}if(min!=max){
					m.addObject("currentPrice", min+"-"+max);
				}
			}
			//爆款专区  断码清仓
			if("2".equals(singleProductActivityCustomList.get(0).getType()) || "6".equals(singleProductActivityCustomList.get(0).getType())) {
				//类目为钟表
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				ProductCustomExample productCustomExample = new ProductCustomExample();
				ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();
				productCustomCriteria.andDelFlagEqualTo("0").andIdEqualTo(singleProductActivityCustomList.get(0).getProductId());
				productCustomCriteria.andProductTypeAll(productService.getProductTypeAllChild(Integer.parseInt(isCwOrgProductTypeId)));
				List<ProductCustom> productCustomList = productService.selectProductCustomByExample(productCustomExample);
				if(productCustomList != null && productCustomList.size() > 0) {
					m.addObject("zbFlag", "zbFlag");
				}
			}
			ProductCustom productCustom = productService.selectProductCustomByPrimaryKey(singleProductActivityCustomList.get(0).getProductId());
			m.addObject("stock", productCustom.getStock());
			String[] originalPriceArray = singleProductActivityCustomList.get(0).getOriginalPrice().split("-");
			if(originalPriceArray.length>1){
				BigDecimal priceMin = new BigDecimal(originalPriceArray[0]).subtract(singleProductActivityCustomList.get(0).getPlatformPreferential()==null?new BigDecimal(0):singleProductActivityCustomList.get(0).getPlatformPreferential());
				BigDecimal priceMax = new BigDecimal(originalPriceArray[1]).subtract(singleProductActivityCustomList.get(0).getPlatformPreferential()==null?new BigDecimal(0):singleProductActivityCustomList.get(0).getPlatformPreferential());
				m.addObject("arrivalPrice", priceMin+"-"+priceMax);
			}else{
				BigDecimal priceMin = new BigDecimal(originalPriceArray[0]).subtract(singleProductActivityCustomList.get(0).getPlatformPreferential()==null?new BigDecimal(0):singleProductActivityCustomList.get(0).getPlatformPreferential());
				m.addObject("arrivalPrice", priceMin);
			}
			int day = 0;
			ActivityRuleConfigurationExample activityRuleConfigurationExample = new ActivityRuleConfigurationExample();
			activityRuleConfigurationExample.createCriteria().andTypeEqualTo(singleProductActivityCustomList.get(0).getType()).andDelFlagEqualTo("0");
			List<ActivityRuleConfiguration> activityRuleConfigurations = activityRuleConfigurationService.selectByExample(activityRuleConfigurationExample);
			if(activityRuleConfigurations!=null && activityRuleConfigurations.size()>0){
				ActivityRuleConfiguration activityRuleConfiguration = activityRuleConfigurations.get(0);
				if(!StringUtils.isEmpty(activityRuleConfiguration.getSalesCycleRules())){
					String[] salesCycleRulesArray = activityRuleConfiguration.getSalesCycleRules().split(";");
					for(int i=0;i<salesCycleRulesArray.length;i++){
						String[] salesCycleRuleArray = salesCycleRulesArray[i].split("_");
						if(salesCycleRuleArray[0].equals(productCustom.getProductType1Id().toString())){
							day = Integer.parseInt(salesCycleRuleArray[1]);
							break;
						}
					}
				}
			}
			if(singleProductActivityCustomList.get(0).getExpectedDate()!=null){
				m.addObject("endTime", DateUtils.addDays(singleProductActivityCustomList.get(0).getExpectedDate(), day));
			}
		}
		if(flag.equals("1")) {
			SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
			SeckillTimeExample.Criteria c = seckillTimeExample.createCriteria();
			c.andDelFlagEqualTo("0");
//			c.andStatusEqualTo("1");
			if(singleProductActivityCustomList!=null && singleProductActivityCustomList.size()>0){
				c.andSeckillTypeEqualTo(singleProductActivityCustomList.get(0).getSeckillType());
			}else{
				c.andSeckillTypeEqualTo("1");
			}
			seckillTimeExample.setOrderByClause("start_hours asc,start_min asc,id asc");
			List<SeckillTime> seckillTimeList = seckillTimeService.selectByExample(seckillTimeExample);
			m.addObject("seckillTimeList", seckillTimeList);
		}
		m.addObject("typeList", DataDicUtil.getStatusList("BU_SINGLE_PRODUCT_ACTIVITY", "TYPE"));
		m.addObject("flag", flag);
		m.addObject("ids", request.getParameter("ids"));
		return m;
	}
	
	/**
	 * yjc
	 * 排期批量审核
	 */
	@RequestMapping(value = "/singleProductActivity/batchAuditOrRetrial.shtml")
	public ModelAndView batchAuditOrRetrial(HttpServletRequest request, Integer id, String flag) {
		ModelAndView m = new ModelAndView("/singleProductActivity/batchAuditOrRetrial");
		if(flag.equals("1")) {
			SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
			SeckillTimeExample.Criteria c = seckillTimeExample.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andSeckillTypeEqualTo("1");
			seckillTimeExample.setOrderByClause("start_hours asc,start_min asc,id asc");
			List<SeckillTime> seckillTimeList = seckillTimeService.selectByExample(seckillTimeExample);
			m.addObject("seckillTimeList", seckillTimeList);
		}
		m.addObject("typeList", DataDicUtil.getStatusList("BU_SINGLE_PRODUCT_ACTIVITY", "TYPE"));
		m.addObject("id", id);
		m.addObject("flag", flag);
		m.addObject("ids", request.getParameter("ids"));
		return m;
	}

    /**
     * 批量调价页面跳转
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/singleProductActivity/batchChangePrice.shtml")
    public ModelAndView batchChangePrice(HttpServletRequest request, Integer id) {
        ModelAndView m = new ModelAndView("/singleProductActivity/batchChangePrice");
        m.addObject("id", id);
        m.addObject("ids", request.getParameter("ids"));
        return m;
    }





	/**
	 * 
	 * @Title scheduleAuditOrRetrialCustom   
	 * @Description TODO(自定义排期)   
	 * @author pengl
	 * @date 2018年6月8日 下午6:13:06
	 */
	@RequestMapping(value = "/singleProductActivity/scheduleAuditOrRetrialCustom.shtml")
	public ModelAndView scheduleAuditOrRetrialCustom(HttpServletRequest request, Integer id) {
		ModelAndView m = new ModelAndView("/singleProductActivity/scheduleAuditOrRetrialCustom");
		SingleProductActivityExample singleProductActivityExample = new SingleProductActivityExample();
		SingleProductActivityExample.Criteria singleProductActivityCriteria = singleProductActivityExample.createCriteria();
		singleProductActivityCriteria.andDelFlagEqualTo("0")
			.andIdEqualTo(id);
		List<SingleProductActivity> singleProductActivityList = singleProductActivityService.selectByExample(singleProductActivityExample);
		if(singleProductActivityList != null && singleProductActivityList.size() > 0) {
			m.addObject("singleProductActivity", singleProductActivityList.get(0));
		} 
		return m;
	}
	
	/**
	 * 
	 * @Title saveScheduleAuditOrRetrial   
	 * @Description TODO(保存单品活动审核流水)   
	 * @author pengl
	 * @date 2017年9月30日 下午2:59:37
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/saveScheduleAuditOrRetrial.shtml")
	public  Map<String, Object> saveScheduleAuditOrRetrial(HttpServletRequest request, String seckillStatus, Integer id, String flag, String beginTime, 
			String endTime, Integer seckillTimeId, String auditStatus, String remarks, Integer productId, Double aPrice, String type) {
		/*String rtPage = "/success/success";*/
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String returnCode = "0000";
		String returnMsg = "操作成功！";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			String seckillType = request.getParameter("seckillType");
			String platformPreferential = request.getParameter("platformPreferential");
			if(!id.equals(0)){
				singleProductActivityService.saveScheduleAuditOrRetrial(staffId, id, flag, beginTime, 
						endTime, seckillTimeId, auditStatus, remarks, productId, aPrice, type,seckillType,platformPreferential);
			}else{//批量
				singleProductActivityService.batchSaveScheduleAuditOrRetrial(staffId, request.getParameter("ids"), flag, beginTime, 
						endTime, seckillTimeId, auditStatus, remarks, type,seckillType);
			}
			if(seckillStatus!=null&&seckillStatus!=""&&seckillStatus!="null") {
				if(Integer.parseInt(seckillStatus)==1){
					productService.updateSeckillSkuPrice(id,productId,request);
				}
			}
		} catch (Exception e) {
			returnCode = "9999";
			returnMsg = "系统错误";
		}	
		resMap.put("id", id);
		resMap.put("auditStatus", auditStatus);
		resMap.put("returnCode", returnCode);
		resMap.put("returnMsg", returnMsg);
		return resMap;
		
	}

	/**
	 * 
	 * @Title saveScheduleAuditOrRetrialCustom   
	 * @Description TODO(自定义排期)   
	 * @author pengl
	 * @date 2018年6月8日 下午6:46:15
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/saveScheduleAuditOrRetrialCustom.shtml")
	public  Map<String, Object> saveScheduleAuditOrRetrialCustom(HttpServletRequest request, Integer id, String beginTime, 
			String endTime, String auditStatus, String remarks, Integer productId, Double aPrice, String type,String seckillType) {
		/*String rtPage = "/success/success";*/
		String returnCode = "0000";
		String returnMsg = "操作成功！";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			singleProductActivityService.saveScheduleAuditOrRetrialCustom(staffId, id, beginTime, 
					endTime, auditStatus, remarks, productId, aPrice, type,seckillType);

		} catch (Exception e) {
			returnCode = "999";
			returnMsg = "系统错误";
		}
		resMap.put("id", id);
		resMap.put("auditStatus", auditStatus);
		resMap.put("returnCode", returnCode);
		resMap.put("returnMsg", returnMsg);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateSingleProductActivity   
	 * @Description TODO(排序)   
	 * @author pengl
	 * @date 2017年9月30日 下午2:59:37
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/updateSingleProductActivity.shtml")
	public Map<String, Object> updateSingleProductActivity(HttpServletRequest request, Integer id, Integer seqNo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		StaffBean staffBean = this.getSessionStaffBean(request);
		Integer staffId = Integer.valueOf(staffBean.getStaffID());
		try {
			SingleProductActivityExample singleProductActivityExample = new SingleProductActivityExample();
			SingleProductActivityExample.Criteria singleProductActivityCriteria = singleProductActivityExample.createCriteria();
			singleProductActivityCriteria.andDelFlagEqualTo("0").andIdEqualTo(id);
            List<SingleProductActivity> singleProductActivities=singleProductActivityService.selectByExample(singleProductActivityExample);
			
			SingleProductActivityAuditLog singleProductActivityAuditLog=new SingleProductActivityAuditLog();//单品活动审核排序值
			singleProductActivityAuditLog.setCreateBy(staffId);
			singleProductActivityAuditLog.setCreateDate(new Date());
			singleProductActivityAuditLog.setSingleProductActivityId(id);
			singleProductActivityAuditLog.setStatus("6");
			if (singleProductActivities.get(0).getSeqNo()==null) {
				singleProductActivityAuditLog.setRemarks("排序值由空修改为"+seqNo);
			}else {
				singleProductActivityAuditLog.setRemarks("排序值由"+singleProductActivities.get(0).getSeqNo()+"修改为"+seqNo);
			}
			singleProductActivityAuditLog.setDelFlag("0");
			singleProductActivityAuditLogService.insert(singleProductActivityAuditLog);
			
			SingleProductActivity singleProductActivity = new SingleProductActivity();
			singleProductActivity.setSeqNo(seqNo);
			singleProductActivityService.updateByExampleSelective(singleProductActivity, singleProductActivityExample);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 
	 * @Title mchtSingleActivityCnfManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2017年11月29日 上午9:28:39
	 */
	@RequestMapping(value = "/singleProductActivity/mchtSingleActivityCnfManager.shtml")
	public ModelAndView mchtSingleActivityCnfManager(HttpServletRequest request, String flag) {
		ModelAndView m = new ModelAndView();
		if(flag.equals("1")) {
			ProductTypeExample productTypeExample = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
			productTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
			m.addObject("isCwOrgStatus", isCwOrgStatus);
			
			List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
			m.addObject("productTypeList", productTypeList);
			
			m.setViewName("/singleProductActivity/mchtSingleActivityCnfList");
		}else if(flag.equals("2")) {
			String mchtSingleActivityCnfId = request.getParameter("id");
			if(!StringUtil.isEmpty(mchtSingleActivityCnfId)) {
				MchtSingleActivityCnfCustomExample mchtSingleActivityCnfCustomExample = new MchtSingleActivityCnfCustomExample();
				MchtSingleActivityCnfCustomExample.MchtSingleActivityCnfCustomCriteria mchtSingleActivityCnfCustomCriteria  = mchtSingleActivityCnfCustomExample.createCriteria();
				mchtSingleActivityCnfCustomCriteria.andIdEqualTo(Integer.parseInt(mchtSingleActivityCnfId)).andDelFlagEqualTo("0");
				List<MchtSingleActivityCnfCustom> mchtSingleActivityCnfCustomList = mchtSingleActivityCnfService.selectByCustomExample(mchtSingleActivityCnfCustomExample);
				if(mchtSingleActivityCnfCustomList != null && mchtSingleActivityCnfCustomList.size() > 0) {
					m.addObject("mchtSingleActivityCnfCustom", mchtSingleActivityCnfCustomList.get(0));
				}
			}
			SingleProductActivityCnfExample singleProductActivityCnfExample = new SingleProductActivityCnfExample();
			singleProductActivityCnfExample.createCriteria().andDelFlagEqualTo("0");
			List<SingleProductActivityCnf> singleProductActivityCnfList = mchtSingleActivityCnfService.selectByExample(singleProductActivityCnfExample);
			m.addObject("singleProductActivityCnfList", singleProductActivityCnfList);
			m.setViewName("/singleProductActivity/mchtSingleActivityCnfAddOrUpdate");
		}
		m.addObject("activityTypeList", DataDicUtil.getStatusList("BU_MCHT_SINGLE_ACTIVITY_CNF", "ACTIVITY_TYPE"));
		return m;
	}
	
	/**
	 * 
	 * @Title getMchtSingleActivityCnfList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2017年11月29日 上午10:36:17
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/getMchtSingleActivityCnfList.shtml")
	public Map<String, Object> getMchtSingleActivityCnfList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MchtSingleActivityCnfCustom> dataList = null;
		String totalCount = "0";
		try {
			MchtSingleActivityCnfCustomExample mchtSingleActivityCnfCustomExample = new MchtSingleActivityCnfCustomExample();
			MchtSingleActivityCnfCustomExample.MchtSingleActivityCnfCustomCriteria mchtSingleActivityCnfCustomCriteria  = mchtSingleActivityCnfCustomExample.createCriteria();
			mchtSingleActivityCnfCustomCriteria.andDelFlagEqualTo("0");
			mchtSingleActivityCnfCustomCriteria.andMchtStatusByEqualTo("1");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("activityType"))) {
				mchtSingleActivityCnfCustomCriteria.andActivityTypeEqualTo(request.getParameter("activityType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtSingleActivityCnfCustomCriteria.andMchtCodeByEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("startCreateDate"))) {
				String startCreateDate = request.getParameter("startCreateDate")+" 00:00:00";
				mchtSingleActivityCnfCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(startCreateDate));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				String endCreateDate = request.getParameter("endCreateDate")+" 23:59:59";
				mchtSingleActivityCnfCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(endCreateDate));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtSingleActivityCnfCustomCriteria.andproductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			//开放特殊报名
			if(!StringUtil.isEmpty(request.getParameter("isSpecial"))) {			
				mchtSingleActivityCnfCustomCriteria.andIsSpecialEqualTo(request.getParameter("isSpecial"));
			}
			
			mchtSingleActivityCnfCustomExample.setOrderByClause(" id desc");
			mchtSingleActivityCnfCustomExample.setLimitSize(page.getLimitSize());
			mchtSingleActivityCnfCustomExample.setLimitStart(page.getLimitStart());
			dataList = mchtSingleActivityCnfService.selectByCustomExample(mchtSingleActivityCnfCustomExample);
			Integer total = mchtSingleActivityCnfService.countByCustomExample(mchtSingleActivityCnfCustomExample);
			totalCount = total.toString();
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList();
			totalCount = "0";
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title saveOrUpdateMchtSingleActivityCnf   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2017年11月29日 上午9:51:49
	 */
	@RequestMapping(value = "/singleProductActivity/saveOrUpdateMchtSingleActivityCnf.shtml")
	public ModelAndView saveOrUpdateMchtSingleActivityCnf(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg =null;
		try {
			String mchtSingleActivityCnfId = request.getParameter("id");
			MchtSingleActivityCnf mchtSingleActivityCnf = new MchtSingleActivityCnf();
			mchtSingleActivityCnf.setMchtId(Integer.parseInt(request.getParameter("mchtId")));
			mchtSingleActivityCnf.setActivityType(request.getParameter("activityType"));
			mchtSingleActivityCnf.setLimitQuality(Integer.parseInt(request.getParameter("limitQuality")));
			mchtSingleActivityCnf.setIsSpecial(request.getParameter("isSpecial"));
			if(!StringUtil.isEmpty(mchtSingleActivityCnfId)) { //修改
				mchtSingleActivityCnf.setId(Integer.parseInt(request.getParameter("id")));
				mchtSingleActivityCnf.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtSingleActivityCnf.setUpdateDate(new Date());
				mchtSingleActivityCnfService.updateByPrimaryKeySelective(mchtSingleActivityCnf);
			}else { //保存
				mchtSingleActivityCnf.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtSingleActivityCnf.setCreateDate(new Date());
				mchtSingleActivityCnfService.insertSelective(mchtSingleActivityCnf);
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
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
	 * @Title deleteMchtSingleActivityCnf   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2017年11月29日 上午10:04:15
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/deleteMchtSingleActivityCnf.shtml")
	public Map<String, Object> deleteMchtSingleActivityCnf(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			MchtSingleActivityCnf mchtSingleActivityCnf = new MchtSingleActivityCnf();
			mchtSingleActivityCnf.setId(Integer.parseInt(request.getParameter("id")));
			mchtSingleActivityCnf.setDelFlag("1");
			mchtSingleActivityCnfService.updateByPrimaryKeySelective(mchtSingleActivityCnf);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 
	 * @Title getMchtInfo   
	 * @Description TODO(验证商家是否存在)   
	 * @author pengl
	 * @date 2017年11月29日 上午10:18:56
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/getMchtInfo.shtml")
	public Map<String, Object> getMchtInfo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				MchtInfoExample mchtInfoExample = new MchtInfoExample();
				MchtInfoExample.Criteria MchtInfoCriteria = mchtInfoExample.createCriteria();
				MchtInfoCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtCodeEqualTo(request.getParameter("mchtCode"));
				List<MchtInfo> mchtInfoList = mchtInfoService.selectByExample(mchtInfoExample);
				if(mchtInfoList != null && mchtInfoList.size() > 0) {
					resMap.put("code", "200");
					resMap.put("data", mchtInfoList.get(0));
				}else {
					resMap.put("code", "202");
				}
			}else {
				resMap.put("code", "202");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resMap.put("code", "202");
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title getMchtSingleActivityCnf   
	 * @Description TODO(判断（商家+活动）是否已存在)   
	 * @author pengl
	 * @date 2017年11月29日 上午10:27:17
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/getMchtSingleActivityCnf.shtml")
	public Map<String, Object> getMchtSingleActivityCnf(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			MchtSingleActivityCnfExample mchtSingleActivityCnfExample = new MchtSingleActivityCnfExample();
			MchtSingleActivityCnfExample.Criteria mchtSingleActivityCnfCriteria = mchtSingleActivityCnfExample.createCriteria();
			mchtSingleActivityCnfCriteria.andDelFlagEqualTo("0")
				.andMchtIdEqualTo(Integer.parseInt(request.getParameter("mchtId")))
				.andActivityTypeEqualTo(request.getParameter("activityType"));
			List<MchtSingleActivityCnf> mchtSingleActivityCnfList = mchtSingleActivityCnfService.selectByExample(mchtSingleActivityCnfExample);
			if(mchtSingleActivityCnfList != null && mchtSingleActivityCnfList.size() > 0) {
				resMap.put("code", "200");
			}else {
				resMap.put("code", "202");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resMap.put("code", "202");
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateUnrealityNum   
	 * @Description TODO(修改虚拟数)   
	 * @author pengl
	 * @date 2017年12月19日 下午2:13:32
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/updateUnrealityNum.shtml")
	public Map<String, Object> updateUnrealityNum(HttpServletRequest request, Integer id, Integer unrealityNum) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			SingleProductActivity singleProductActivity = new SingleProductActivity();
			singleProductActivity.setId(id);
			singleProductActivity.setUnrealityNum(unrealityNum);
			singleProductActivityService.updateByPrimaryKeySelective(singleProductActivity);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 
	 * @Title getSeckillTimeList   
	 * @Description TODO(异步获取限时秒杀时间)   
	 * @author pengl
	 * @date 2017年9月29日 上午11:14:54
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/getSeckillTimeListBySeckillType.shtml")
	public Map<String, Object> getSeckillTimeListBySeckillType(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			String seckillType = request.getParameter("seckillType");
			SeckillTimeExample seckillTimeExample = new SeckillTimeExample();
			SeckillTimeExample.Criteria c = seckillTimeExample.createCriteria();
			c.andDelFlagEqualTo("0");
//			c.andStatusEqualTo("1");
			if(!StringUtils.isEmpty(seckillType)){
				c.andSeckillTypeEqualTo(seckillType);
			}
			seckillTimeExample.setOrderByClause(" id asc");
			List<SeckillTime> seckillTimeList = seckillTimeService.selectByExample(seckillTimeExample);
			resMap.put("seckillTimeList", seckillTimeList);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * 单品活动入口控制列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/singleProductActivity/singleProductActivityControl/list.shtml")
	public ModelAndView singleProductActivityControlList(HttpServletRequest request) {
		String rtPage = "singleProductActivity/singleProductActivityControl/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 单品活动入口控制数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/singleProductActivity/singleProductActivityControl/data.shtml")
	@ResponseBody
	public Map<String, Object> singleProductActivityControlData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			SingleProductActivityControlExample example = new SingleProductActivityControlExample();
			example.setOrderByClause("ABS(t.type) asc");
			SingleProductActivityControlExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			totalCount = singleProductActivityControlService.countByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<SingleProductActivityControlCustom> singleProductActivityControlCustoms = singleProductActivityControlService.selectCustomByExample(example);
			resMap.put("Rows", singleProductActivityControlCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 单品活动入口控制设置（编辑页面）
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/singleProductActivity/singleProductActivityControl/toSetting.shtml")
	public ModelAndView singleProductActivityControlToSetting(HttpServletRequest request) {
		String rtPage = "singleProductActivity/singleProductActivityControl/toSetting";
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> productTypeList = productTypeService.selectByExample(pte);
		resMap.put("productTypeList", productTypeList);
		String id = request.getParameter("id");
		SingleProductActivityControlCustom singleProductActivityControlCustom = singleProductActivityControlService.selectCustomByPrimaryKey(Integer.parseInt(id));
		resMap.put("singleProductActivityControlCustom", singleProductActivityControlCustom);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存单品活动入口控制设置
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/singleProductActivityControl/save.shtml")
	public Map<String, Object> singleProductActivityControlSave(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String productTypeIds = request.getParameter("productTypeIds");
			String showToMchtCodes = request.getParameter("showToMchtCodes");
			String hideToMchtCodes = request.getParameter("hideToMchtCodes");
			String showToMchtIds = singleProductActivityControlService.getMchtIdsByMchtCodes(showToMchtCodes);
			String hideToMchtIds = singleProductActivityControlService.getMchtIdsByMchtCodes(hideToMchtCodes);
			SingleProductActivityControl singleProductActivityControl = singleProductActivityControlService.selectByPrimaryKey(Integer.parseInt(id));
			singleProductActivityControl.setType(singleProductActivityControl.getType());
			singleProductActivityControl.setProductTypeIds(productTypeIds);
			singleProductActivityControl.setShowToMchtIds(showToMchtIds);
			singleProductActivityControl.setHideToMchtIds(hideToMchtIds);
			singleProductActivityControl.setCreateBy(singleProductActivityControl.getCreateBy());
			singleProductActivityControl.setCreateDate(singleProductActivityControl.getCreateDate());
			singleProductActivityControl.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			singleProductActivityControl.setUpdateDate(new Date());
			singleProductActivityControl.setDelFlag("0");
			singleProductActivityControlService.updateByPrimaryKey(singleProductActivityControl);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
		}
		return resMap;
	}
	
	/**
	 * 检测mchtCodes
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/singleProductActivityControl/checkMcht.shtml")
	public Map<String, Object> checkMcht(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtCodes = request.getParameter("mchtCodes");
			String mchtIds = singleProductActivityControlService.getMchtIdsByMchtCodes(mchtCodes);
			resMap.put("mchtIds", mchtIds);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
		}
		return resMap;
	}
	
	/**
	 * 单品活动入口控制列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/singleProductActivity/singleProductActivityControl/mchtList.shtml")
	public ModelAndView mchtList(HttpServletRequest request) {
		String rtPage = "singleProductActivity/singleProductActivityControl/mchtList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtIds = request.getParameter("mchtIds");
		resMap.put("mchtIds", mchtIds);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 单品活动入口控制数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/singleProductActivity/singleProductActivityControl/mchtData.shtml")
	@ResponseBody
	public Map<String, Object> mchtData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String mchtIds = request.getParameter("mchtIds");
			String[] mchtIdArray = mchtIds.split(",");
			List<Integer> mchtIdList = new ArrayList<Integer>();
			for(String mchtId:mchtIdArray){
				mchtIdList.add(Integer.parseInt(mchtId));
			}
			MchtInfoExample e = new MchtInfoExample();
			e.createCriteria().andDelFlagEqualTo("0").andIdIn(mchtIdList);
			totalCount = mchtInfoService.countByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<MchtInfo> mchtInfoList = mchtInfoService.selectByExample(e);
			resMap.put("Rows", mchtInfoList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 活动报名规则设置列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/singleProductActivity/activityRuleConfiguration/list.shtml")
	public ModelAndView activityRuleConfigurationList(HttpServletRequest request) {
		String rtPage = "singleProductActivity/activityRuleConfiguration/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 活动报名规则设置列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/singleProductActivity/activityRuleConfiguration/data.shtml")
	@ResponseBody
	public Map<String, Object> activityRuleConfigurationData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			ActivityRuleConfigurationExample e = new ActivityRuleConfigurationExample();
			e.createCriteria().andDelFlagEqualTo("0");
			totalCount = activityRuleConfigurationService.countCustomByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<ActivityRuleConfigurationCustom> activityRuleConfigurationList = activityRuleConfigurationService.selectCustomByExample(e);
			ProductTypeExample pte = new ProductTypeExample();
			pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
			List<ProductType> productTypeList = productTypeService.selectByExample(pte);
			Map<Integer,String> map = new HashMap<Integer,String>();
			for(ProductType productType:productTypeList){
				map.put(productType.getId(), productType.getName());
			}
			for(ActivityRuleConfigurationCustom activityRuleConfigurationCustom : activityRuleConfigurationList){
				String priceRules = activityRuleConfigurationCustom.getPriceRules();
				if(!StringUtils.isEmpty(priceRules)){
					String[] priceRulesArray = priceRules.split(";");
					String priceRulesDesc = "";
					for(String priceRule:priceRulesArray){
						String[] priceRuleArray = priceRule.split("_");
						String productTypeIdStr = priceRuleArray[0];
						String productTypeName = map.get(Integer.parseInt(productTypeIdStr));
						if(StringUtils.isEmpty(priceRulesDesc)){
							if(priceRuleArray[1].equals("0")){
								priceRulesDesc+=productTypeName+"低于吊牌价<span style='color:red;'>"+priceRuleArray[2]+"</span>折";
							}else if(priceRuleArray[1].equals("1")){
								priceRulesDesc+=productTypeName+"不高于<span style='color:red;'>"+priceRuleArray[2]+"</span>元";
							}
						}else{
							if(priceRuleArray[1].equals("0")){
								priceRulesDesc+="<br>"+productTypeName+"低于吊牌价<span style='color:red;'>"+priceRuleArray[2]+"</span>折";
							}else if(priceRuleArray[1].equals("1")){
								priceRulesDesc+="<br>"+productTypeName+"不高于<span style='color:red;'>"+priceRuleArray[2]+"</span>元";
							}
						}
					}
					activityRuleConfigurationCustom.setPriceRulesDesc(priceRulesDesc);
				}
				String salesRules = activityRuleConfigurationCustom.getSalesRules();
				if(!StringUtils.isEmpty(salesRules)){
					String[] salesRulesArray = salesRules.split(";");
					String salesRulesDesc = "";
					for(String salesRule:salesRulesArray){
						String[] salesRuleArray = salesRule.split("_");
						String productTypeIdStr = salesRuleArray[0];
						String productTypeName = map.get(Integer.parseInt(productTypeIdStr));
						if(StringUtils.isEmpty(salesRulesDesc)){
							salesRulesDesc+=productTypeName+"商品总销量高于<span style='color:red;'>"+salesRuleArray[1]+"</span>";
						}else{
							salesRulesDesc+="<br>"+productTypeName+"商品总销量高于<span style='color:red;'>"+salesRuleArray[1]+"</span>";
						}
					}
					activityRuleConfigurationCustom.setSalesRulesDesc(salesRulesDesc);
				}
				
				String stockRules = activityRuleConfigurationCustom.getStockRules();
				if(!StringUtils.isEmpty(stockRules)){
					String[] stockRulesArray = stockRules.split(";");
					String stockRulesDesc = "";
					for(String stockRule:stockRulesArray){
						String[] stockRuleArray = stockRule.split("_");
						String productTypeIdStr = stockRuleArray[0];
						String productTypeName = map.get(Integer.parseInt(productTypeIdStr));
						if(StringUtils.isEmpty(stockRulesDesc)){
							stockRulesDesc+=productTypeName+"商品总库存高于<span style='color:red;'>"+stockRuleArray[1]+"</span>";
						}else{
							stockRulesDesc+="<br>"+productTypeName+"商品总库存高于<span style='color:red;'>"+stockRuleArray[1]+"</span>";
						}
					}
					activityRuleConfigurationCustom.setStockRulesDesc(stockRulesDesc);
				}
				
				String salesCycleRules = activityRuleConfigurationCustom.getSalesCycleRules();
				if(!StringUtils.isEmpty(salesCycleRules)){
					String[] salesCycleRulesArray = salesCycleRules.split(";");
					String salesCycleRulesDesc = "";
					for(String salesCycleRule:salesCycleRulesArray){
						String[] salesCycleRuleArray = salesCycleRule.split("_");
						String productTypeIdStr = salesCycleRuleArray[0];
						String productTypeName = map.get(Integer.parseInt(productTypeIdStr));
						if(!activityRuleConfigurationCustom.getType().equals("3")){
							if(StringUtils.isEmpty(salesCycleRulesDesc)){
								salesCycleRulesDesc+=productTypeName+"商品活动销售期<span style='color:red;'>"+salesCycleRuleArray[1]+"</span>天 品牌报名数<span style='color:red;'>"+salesCycleRuleArray[2]+"</span>个";
							}else{
								salesCycleRulesDesc+="<br>"+productTypeName+"商品活动销售期<span style='color:red;'>"+salesCycleRuleArray[1]+"</span>天 品牌报名数<span style='color:red;'>"+salesCycleRuleArray[2]+"</span>个";
							}
						}else{//限时秒杀/抢购
							if(StringUtils.isEmpty(salesCycleRulesDesc)){
								salesCycleRulesDesc+=productTypeName+" 品牌报名数<span style='color:red;'>"+salesCycleRuleArray[2]+"</span>个";
							}else{
								salesCycleRulesDesc+="<br>"+productTypeName+" 品牌报名数<span style='color:red;'>"+salesCycleRuleArray[2]+"</span>个";
							}
						}
					}
					activityRuleConfigurationCustom.setSalesCycleRulesDesc(salesCycleRulesDesc);
				}
			}
			resMap.put("Rows", activityRuleConfigurationList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 编辑规则设置页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/singleProductActivity/activityRuleConfiguration/toEdit.shtml")
	public ModelAndView toEdit(HttpServletRequest request) {
		String rtPage = "singleProductActivity/activityRuleConfiguration/toEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		ActivityRuleConfiguration activityRuleConfiguration = activityRuleConfigurationService.selectByPrimaryKey(Integer.parseInt(id));
		if(!StringUtils.isEmpty(activityRuleConfiguration.getPriceRules())){
			JSONArray ja = new JSONArray();
			String[] priceRulesArray = activityRuleConfiguration.getPriceRules().split(";");
			for(String priceRule:priceRulesArray){
				Map<String,Object> map = new HashMap<String,Object>();
				String[] priceRuleArray = priceRule.split("_");
				map.put("productTypeId", Integer.parseInt(priceRuleArray[0]));
				map.put("modeType", priceRuleArray[1]);
				map.put("modeValue", priceRuleArray[2]);
				ja.add(map);
			}
			resMap.put("priceRuleList", ja);
		}
		if(!StringUtils.isEmpty(activityRuleConfiguration.getSalesRules())){
			JSONArray ja = new JSONArray();
			String[] salesRulesArray = activityRuleConfiguration.getSalesRules().split(";");
			for(String salesRule:salesRulesArray){
				Map<String,Object> map = new HashMap<String,Object>();
				String[] salesRuleArray = salesRule.split("_");
				map.put("productTypeId", Integer.parseInt(salesRuleArray[0]));
				map.put("salesVolume", salesRuleArray[1]);
				ja.add(map);
			}
			resMap.put("salesRuleList", ja);
		}
		if(!StringUtils.isEmpty(activityRuleConfiguration.getStockRules())){
			JSONArray ja = new JSONArray();
			String[] stockRulesArray = activityRuleConfiguration.getStockRules().split(";");
			for(String stockRule:stockRulesArray){
				Map<String,Object> map = new HashMap<String,Object>();
				String[] stockRuleArray = stockRule.split("_");
				map.put("productTypeId", Integer.parseInt(stockRuleArray[0]));
				map.put("stock", stockRuleArray[1]);
				ja.add(map);
			}
			resMap.put("stockRuleList", ja);
		}
		if(!StringUtils.isEmpty(activityRuleConfiguration.getSalesCycleRules())){
			JSONArray ja = new JSONArray();
			String[] salesCycleRulesArray = activityRuleConfiguration.getSalesCycleRules().split(";");
			for(String salesCycleRule:salesCycleRulesArray){
				Map<String,Object> map = new HashMap<String,Object>();
				String[] salesCycleRuleArray = salesCycleRule.split("_");
				map.put("productTypeId", Integer.parseInt(salesCycleRuleArray[0]));
				map.put("day", salesCycleRuleArray[1]);
				map.put("count", salesCycleRuleArray[2]);
				ja.add(map);
			}
			resMap.put("salesCycleRuleList", ja);
		}
		resMap.put("activityRuleConfiguration", activityRuleConfiguration);
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> productTypeList = productTypeService.selectByExample(pte);
		resMap.put("productTypeList", productTypeList);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存规则设置
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/activityRuleConfiguration/save.shtml")
	public Map<String, Object> saveActivityRuleConfiguration(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String priceRules = request.getParameter("priceRules");
			String salesRules = request.getParameter("salesRules");
			String stockRules = request.getParameter("stockRules");
			String favorableRate = request.getParameter("favorableRate");
			String shopComment = request.getParameter("shopComment");
			String salesCycleRules = request.getParameter("salesCycleRules");
			String otherRequirements = request.getParameter("otherRequirements");
			ActivityRuleConfiguration activityRuleConfiguration = new ActivityRuleConfiguration();
			activityRuleConfiguration.setId(Integer.parseInt(id));
			activityRuleConfiguration.setPriceRules(priceRules);
			activityRuleConfiguration.setSalesRules(salesRules);
			activityRuleConfiguration.setStockRules(stockRules);
			if(!StringUtils.isEmpty(favorableRate)){
				activityRuleConfiguration.setFavorableRate(new BigDecimal(favorableRate));
			}else{
				activityRuleConfiguration.setFavorableRate(null);
			}
			if(!StringUtils.isEmpty(shopComment)){
				activityRuleConfiguration.setShopComment(new BigDecimal(shopComment));
			}else{
				activityRuleConfiguration.setShopComment(null);
			}
			activityRuleConfiguration.setSalesCycleRules(salesCycleRules);
			activityRuleConfiguration.setOtherRequirements(otherRequirements);
			activityRuleConfiguration.setUpdateBy(this.getSessionStaffBean(request).getStaffID());
			activityRuleConfiguration.setUpdateDate(new Date());
			activityRuleConfigurationService.updateByEntity(activityRuleConfiguration);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
		}
		return resMap;
	}

	/**
	 * @Title singleProductActivityTrafficStatistics
	 * @Description 单品活动流量统计
	 * @author xdd
	 * @date 2019年11月26日
	 */
	@RequestMapping(value = "/singleProductActivity/singleProductActivityTrafficStatistics.shtml")
	public ModelAndView singleProductActivityTrafficStatistics(HttpServletRequest request, String type) {
		ModelAndView m = new ModelAndView();
		m.addObject("type", type);
		m.setViewName("/singleProductActivity/singleProductActivityTrafficStatistics");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		Date d=cal.getTime();
		String date = simpleDateFormat.format(d);
		m.addObject("dateBegin", date);
		m.addObject("dateEnd", date);
		String dateNow = simpleDateFormat.format(new Date());
		m.addObject("dateNow", dateNow);
		//获取一级目录
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		m.addObject("productTypeList", productTypeList);
		return m;
	}

	/**
	 * @Title singleProductActivityTrafficStatisticsData
	 * @Description 单品活动流量统计数据
	 * @author xdd
	 * @date 2019年11月26日
	 */
	@ResponseBody
	@RequestMapping("/singleProductActivity/singleProductActivityTrafficStatisticsData.shtml")
	public Map<String, Object> singleProductActivityTrafficStatisticsData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		List<HashMap<String,Object>> dataList = null;
		try {
			HashMap<String, Object> paraMap = new HashMap<>();
			paraMap.put("limitSize",page.getLimitSize());
			paraMap.put("limitStart",page.getLimitStart());
			String type = request.getParameter("type");
			if(StringUtils.isEmpty(type)){
				resMap.put("Rows", dataList);
				resMap.put("Total", totalCount);
				return resMap;
			}else {
				if("1".equals(type)){//新用户专区
					paraMap.put("columnType","5");
					paraMap.put("type","1");
				}else if("2".equals(type)){//限时抢购
					paraMap.put("columnType","7");
					paraMap.put("type","3");
				}else if("3".equals(type)){//爆款专区
					paraMap.put("columnType","6");
					paraMap.put("type","2");
				}else if("4".equals(type)){//新用户秒杀
					paraMap.put("columnType","8");
					paraMap.put("type","4");
				}else if("5".equals(type)){//积分商城
					paraMap.put("columnType","9");
					paraMap.put("type","5");
				}
			}
			if(!StringUtils.isEmpty(request.getParameter("productId"))){
				paraMap.put("productId",request.getParameter("productId"));
			}
			if(!StringUtils.isEmpty(request.getParameter("productName"))){
				paraMap.put("productName","%"+request.getParameter("productName")+"%");
			}
			if(!StringUtils.isEmpty(request.getParameter("mchtCode"))){
				paraMap.put("mchtCode",request.getParameter("mchtCode"));
			}
			if(!StringUtils.isEmpty(request.getParameter("mchtName"))){
				paraMap.put("mchtName","%"+request.getParameter("mchtName")+"%");
			}
			if(!StringUtils.isEmpty(request.getParameter("productTypeId"))){
				paraMap.put("productTypeId",request.getParameter("productTypeId"));
			}
			String selectType = request.getParameter("selectType");
			if("1".equals(selectType)){
				String dateNow =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				paraMap.put("beginTime",dateNow+" 00:00:00");
				paraMap.put("endTime",dateNow+" 23:59:59");
				totalCount = singleProductActivityService.countSingleProductActivityTrafficStatisticsRealTime(paraMap);
				dataList = singleProductActivityService.selectSingleProductActivityTrafficStatisticsRealTime(paraMap);
			}else {
				if(!StringUtils.isEmpty(request.getParameter("beginTime"))){
					paraMap.put("beginTime",request.getParameter("beginTime"));
				}
				if(!StringUtils.isEmpty(request.getParameter("endTime"))){
					paraMap.put("endTime",request.getParameter("endTime"));
				}
				totalCount = typeColumnPvStatisticalService.countSingleProductActivityTrafficStatistics(paraMap);
				dataList = typeColumnPvStatisticalService.selectSingleProductActivityTrafficStatistics(paraMap);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}


	/**
	 * 批量调价
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/singleProductActivity/saveBatchChangePrice.shtml")
	public ResponseMsg saveBatchChangePrice(HttpServletRequest request) {
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		ResponseMsg responseMsg = singleProductActivityControlService.saveBatchChangePrice(request,staffID);
		return  responseMsg;
	}



    //单品活动相关排期计算展示毛利和毛利率抽取
    private  List<SingleProductActivityCustom>  caculateGrossMargin(List<SingleProductActivityCustom> dataList ){
        for (SingleProductActivityCustom productActivityCustom : dataList) {
            BigDecimal salePriceMin=productActivityCustom.getSalePriceMin();
            BigDecimal salePriceMax=productActivityCustom.getSalePriceMax();
            BigDecimal costPriceMin=productActivityCustom.getCostPriceMin();
            BigDecimal costPriceMax=productActivityCustom.getCostPriceMax();
            BigDecimal svipDiscount=productActivityCustom.getSvipDiscount();
            BigDecimal popCommissionRate=productActivityCustom.getPopCommissionRate();
                if ("1".equals(productActivityCustom.getMchtType())){
                    if (salePriceMin.compareTo(BigDecimal.ZERO)==1 && salePriceMax.compareTo(BigDecimal.ZERO)==1 ) {
                    	if (svipDiscount==null) {
                         	svipDiscount = new BigDecimal(1);
             			}
                        BigDecimal grossProfitMin = salePriceMin.multiply(svipDiscount).subtract(costPriceMin);//spop商家直接按商品的结算价计算毛利
                        BigDecimal grossProfitMax = salePriceMax.multiply(svipDiscount).subtract(costPriceMax);
                        BigDecimal grossProfitRateMin = grossProfitMin.divide(salePriceMin, 2, BigDecimal.ROUND_HALF_UP);//spop商家毛利率=毛利/对应活动价,两位，四舍五入
                        BigDecimal grossProfitRateMax = grossProfitMax.divide(salePriceMax, 2, BigDecimal.ROUND_HALF_UP);
                        productActivityCustom.setGrossProfitMin(grossProfitMin.setScale(2, BigDecimal.ROUND_HALF_UP));//毛利保留两位小数，四舍五入
                        productActivityCustom.setGrossProfitMax(grossProfitMax.setScale(2, BigDecimal.ROUND_HALF_UP));
                        productActivityCustom.setGrossProfitRateMin(grossProfitRateMin.multiply(new BigDecimal("100")));
                        productActivityCustom.setGrossProfitRateMax(grossProfitRateMax.multiply(new BigDecimal("100")));
                        }
                    }else{
                    if (salePriceMin.compareTo(BigDecimal.ZERO)==1 && salePriceMax.compareTo(BigDecimal.ZERO)==1){
                    	if(svipDiscount == null){
                    		svipDiscount = new BigDecimal(1);
                    	}
                    	if(popCommissionRate == null){
                    		popCommissionRate = new BigDecimal(0);
                    	}
                        BigDecimal grossProfitMin=salePriceMin.multiply(svipDiscount).subtract(salePriceMin.multiply((new BigDecimal("1").subtract(popCommissionRate))));
                        BigDecimal grossProfitMax=salePriceMax.multiply(svipDiscount).subtract(salePriceMax.multiply((new BigDecimal("1").subtract(popCommissionRate))));
                        productActivityCustom.setGrossProfitMin(grossProfitMin.setScale(2, BigDecimal.ROUND_HALF_UP));
                        productActivityCustom.setGrossProfitMax(grossProfitMax.setScale(2, BigDecimal.ROUND_HALF_UP));
                        productActivityCustom.setGrossProfitRateMin(popCommissionRate.multiply(new BigDecimal("100")));
                        productActivityCustom.setGrossProfitRateMax(popCommissionRate.multiply(new BigDecimal("100")));
                    }
                }
            }
       	return dataList;
     }
}
