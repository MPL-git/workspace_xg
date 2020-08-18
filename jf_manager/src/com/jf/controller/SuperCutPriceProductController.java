package com.jf.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.CutPriceCnf;
import com.jf.entity.CutPriceCnfExample;
import com.jf.entity.Product;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityAuditLog;
import com.jf.entity.SingleProductActivityAuditLogExample;
import com.jf.entity.SingleProductActivityCustom;
import com.jf.entity.SingleProductActivityCustomExample;
import com.jf.entity.SingleProductActivityExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.service.CutPriceCnfService;
import com.jf.service.SingleProductActivityAuditLogService;
import com.jf.service.SingleProductActivityService;
import com.jf.vo.Page;

/**
 * 
 * @ClassName SuperCutPriceProductController
 * @Description TODO(邀请享免单--->超级砍价)
 * @author pengl
 * @date 2018年6月11日 下午12:01:52
 */
@SuppressWarnings("serial")
@Controller
public class SuperCutPriceProductController extends BaseController {

	@Autowired
	private SingleProductActivityService singleProductActivityService;
	
	@Autowired
	private CutPriceCnfService cutPriceCnfService;
	
	@Autowired
	private SingleProductActivityAuditLogService singleProductActivityAuditLogService;
	
	/**
	 * 
	 * @Title superCutPriceProductManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月11日 下午12:29:54
	 */
	@RequestMapping("/superCutPriceProduct/superCutPriceProductManager.shtml")
	public ModelAndView superCutPriceProductManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/superCutPriceProduct/getSuperCutPriceProductList");
		return m;
	}
	
	/**
	 * 
	 * @Title getSuperCutPriceProductList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月11日 下午12:29:58
	 */
	@ResponseBody
	@RequestMapping("/superCutPriceProduct/getSuperCutPriceProductList.shtml")
	public Map<String, Object> getSuperCutPriceProductList(HttpServletRequest request, Page page, Integer singleProductActivityId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SingleProductActivityCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
			SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
			singleProductActivityCustomCriteria.andDelFlagEqualTo("0")
				.andIsCloseEqualTo("0")
				.andTypeEqualTo("8"); // 8：邀请享免单
			if(singleProductActivityId != null) {
				singleProductActivityCustomCriteria.andIdEqualTo(singleProductActivityId);
			}
			if(!StringUtil.isEmpty(request.getParameter("productCode"))) {
				singleProductActivityCustomCriteria.andCodeByEqualTo(request.getParameter("productCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("shopName"))) {
				singleProductActivityCustomCriteria.andShopNameLike(request.getParameter("shopName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productName"))) {
				singleProductActivityCustomCriteria.andProductNameLike("%"+request.getParameter("productName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("firstAuditName"))) {
				singleProductActivityCustomCriteria.andFirstAuditNameLike("%"+request.getParameter("firstAuditName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("auditStatus"))) {
				singleProductActivityCustomCriteria.andAuditStatusEqualTo(request.getParameter("auditStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("seqNo"))) {
				singleProductActivityCustomExample.setOrderByClause("IFNULL(t.seq_no, 999999999)");
			}
			/*singleProductActivityCustomExample.setOrderByClause(" t.id desc");*/
			singleProductActivityCustomExample.setLimitStart(page.getLimitStart());
			singleProductActivityCustomExample.setLimitSize(page.getLimitSize());
			totalCount = singleProductActivityService.countByCustomExample(singleProductActivityCustomExample);
			dataList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
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
	 * @Title updateSingleProductActivity   
	 * @Description TODO(排序)   
	 * @author pengl
	 * @date 2018年6月11日 下午12:30:40
	 */
	@ResponseBody
	@RequestMapping("/superCutPriceProduct/updateSingleProductActivity.shtml")
	public Map<String, Object> updateSingleProductActivity(HttpServletRequest request, Integer id, Integer seqNo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			SingleProductActivityExample singleProductActivityExample = new SingleProductActivityExample();
			SingleProductActivityExample.Criteria singleProductActivityCriteria = singleProductActivityExample.createCriteria();
			singleProductActivityCriteria.andDelFlagEqualTo("0")
				.andIdEqualTo(id);
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
	 * @Title updateIsClose   
	 * @Description TODO(上架或下架)   
	 * @author pengl
	 * @date 2018年6月11日 下午12:31:54
	 */
	@ResponseBody
	@RequestMapping("/superCutPriceProduct/updateStatus.shtml")
	public Map<String, Object> updateStatus(HttpServletRequest request, Integer id, String status) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			Date date = new Date();
			String staffId = this.getSessionStaffBean(request).getStaffID();
			SingleProductActivity singleProductActivity = new SingleProductActivity();
			singleProductActivity.setId(id);
			singleProductActivity.setStatus(status);
			singleProductActivity.setUpdateBy(Integer.parseInt(staffId));
			singleProductActivity.setUpdateDate(date);
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
	 * @Title addOrUpdateSuperCutPriceCnfManager   
	 * @Description TODO(砍价设置)   
	 * @author pengl
	 * @date 2018年6月11日 下午1:56:10
	 */
	@RequestMapping("/superCutPriceProduct/addOrUpdateSuperCutPriceCnfManager.shtml")
	public ModelAndView addOrUpdateSuperCutPriceCnfManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/superCutPriceProduct/addOrUpdateSuperCutPriceCnf");
		String singleProductActivityId = request.getParameter("singleProductActivityId");
		if(!StringUtil.isEmpty(singleProductActivityId)) {
			SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
			SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
			singleProductActivityCustomCriteria.andDelFlagEqualTo("0")
				.andIdEqualTo(Integer.parseInt(singleProductActivityId));
			List<SingleProductActivityCustom> singleProductActivityCustomList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
			m.addObject("singleProductActivityCustom", singleProductActivityCustomList.get(0));
			CutPriceCnfExample cutPriceCnfExample = new CutPriceCnfExample();
			CutPriceCnfExample.Criteria cutPriceCnfCriteria = cutPriceCnfExample.createCriteria();
			cutPriceCnfCriteria.andDelFlagEqualTo("0").andSingleProductActivityIdEqualTo(Integer.parseInt(singleProductActivityId));
			List<CutPriceCnf> cutPriceCnfList = cutPriceCnfService.selectByExample(cutPriceCnfExample);
			if(cutPriceCnfList != null && cutPriceCnfList.size() > 0) { //砍价设置（已设置过）
				m.addObject("cutPriceCnf", cutPriceCnfList.get(0));
			}
		}
		return m;
	}
	
	/**
	 * 
	 * @Title addOrUpdateCutPriceCnf   
	 * @Description TODO(砍价设置)   
	 * @author pengl
	 * @date 2018年6月11日 下午2:13:18
	 */
	@RequestMapping("/superCutPriceProduct/addOrUpdateSuperCutPriceCnf.shtml")
	public ModelAndView addOrUpdateCutPriceCnf(HttpServletRequest request, @RequestParam HashMap<String, String> paramMap) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String singleProductActivityId = paramMap.get("singleProductActivityId");
			if(!StringUtil.isEmpty(singleProductActivityId)) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				Integer staffId = Integer.valueOf(staffBean.getStaffID());
				Date date = new Date();
				CutPriceCnf cutPriceCnf = new CutPriceCnf();
				if(!StringUtil.isEmpty(paramMap.get("cutPriceCnfId"))) {
					cutPriceCnf.setId(Integer.parseInt(paramMap.get("cutPriceCnfId")));
					cutPriceCnf.setInviteTimes(Integer.parseInt(paramMap.get("inviteTimes")));
					cutPriceCnf.setPredictMinTimes(Integer.parseInt(paramMap.get("inviteTimes")));
					cutPriceCnf.setPredictMaxTimes(Integer.parseInt(paramMap.get("inviteTimes")));
					cutPriceCnf.setUpdateBy(staffId);
					cutPriceCnf.setUpdateDate(date);
					cutPriceCnfService.updateByPrimaryKeySelective(cutPriceCnf);
				}else {
					cutPriceCnf.setSingleProductActivityId(Integer.parseInt(singleProductActivityId));
					cutPriceCnf.setNeedCutToPrice(new BigDecimal(0));
					cutPriceCnf.setMinCutToPrice(new BigDecimal(0));
					cutPriceCnf.setInviteTimes(Integer.parseInt(paramMap.get("inviteTimes")));
					cutPriceCnf.setPredictMinTimes(Integer.parseInt(paramMap.get("inviteTimes")));
					cutPriceCnf.setPredictMaxTimes(Integer.parseInt(paramMap.get("inviteTimes")));
					cutPriceCnf.setCreateBy(staffId);
					cutPriceCnf.setCreateDate(date);
					cutPriceCnfService.insertSelective(cutPriceCnf);
				}
				//申请中修改为初审通过
				SingleProductActivityExample singleProductActivityExample = new SingleProductActivityExample();
				singleProductActivityExample.createCriteria().andDelFlagEqualTo("0")
					.andIdEqualTo(Integer.parseInt(singleProductActivityId))
					.andAuditStatusEqualTo("0");
				SingleProductActivity singleProductActivity = new SingleProductActivity();
				singleProductActivity.setAuditStatus("1");
				singleProductActivityService.updateByExampleSelective(singleProductActivity, singleProductActivityExample);
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
	
	/**
	 * 
	 * @Title updateAuditStatusManager   
	 * @Description TODO(审核)   
	 * @author pengl
	 * @date 2018年6月11日 下午2:15:56
	 */
	@RequestMapping("/superCutPriceProduct/updateAuditStatusManager.shtml")
	public ModelAndView updateAuditStatusManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/superCutPriceProduct/updateAuditStatus");
		String singleProductActivityId = request.getParameter("singleProductActivityId");
		if(!StringUtil.isEmpty(singleProductActivityId)) {
			SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
			SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
			singleProductActivityCustomCriteria.andDelFlagEqualTo("0")
				.andIdEqualTo(Integer.parseInt(singleProductActivityId));
			List<SingleProductActivityCustom> singleProductActivityCustomList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
			SingleProductActivityCustom singleProductActivityCustom = singleProductActivityCustomList.get(0);
			SingleProductActivityAuditLogExample singleProductActivityAuditLogExample = new SingleProductActivityAuditLogExample();
			singleProductActivityAuditLogExample.createCriteria().andDelFlagEqualTo("0")
				.andSingleProductActivityIdEqualTo(singleProductActivityCustom.getId())
				.andStatusEqualTo(singleProductActivityCustom.getAuditStatus());
			singleProductActivityAuditLogExample.setOrderByClause(" id desc");
			singleProductActivityAuditLogExample.setLimitStart(0);
			singleProductActivityAuditLogExample.setLimitSize(1);
			List<SingleProductActivityAuditLog> singleProductActivityAuditLogList = singleProductActivityAuditLogService.selectByExample(singleProductActivityAuditLogExample);
			if(singleProductActivityAuditLogList != null && singleProductActivityAuditLogList.size() > 0) {
				singleProductActivityCustom.setRemarksLog(singleProductActivityAuditLogList.get(0).getRemarks());
			}
			m.addObject("singleProductActivityCustom", singleProductActivityCustom);
		}
		return m;
	}
	
	/**
	 * 
	 * @Title updateAuditStatus   
	 * @Description TODO(审核)   
	 * @author pengl
	 * @date 2018年6月11日 下午2:15:44
	 */
	@RequestMapping("/superCutPriceProduct/updateAuditStatus.shtml")
	public ModelAndView updateAuditStatus(HttpServletRequest request, @RequestParam HashMap<String, String> paramMap) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String singleProductActivityId = paramMap.get("singleProductActivityId");
			if(!StringUtil.isEmpty(singleProductActivityId)) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				Integer staffId = Integer.valueOf(staffBean.getStaffID());
				Date date = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.YEAR, 1);
				SingleProductActivity singleProductActivity = new SingleProductActivity();
				singleProductActivity.setId(Integer.parseInt(singleProductActivityId));
				singleProductActivity.setBeginTime(date);
				singleProductActivity.setEndTime(calendar.getTime());
				singleProductActivity.setAuditStatus(paramMap.get("auditStatus"));
				if("4".equals(paramMap.get("auditStatus"))) { //审核驳回时，同时要把该商品关闭
					singleProductActivity.setIsClose("1");
				}else {
					if(!StringUtil.isEmpty(paramMap.get("unrealityNum"))) {
						singleProductActivity.setUnrealityNum(Integer.parseInt(paramMap.get("unrealityNum")));
					}
					if(!StringUtil.isEmpty(paramMap.get("tomorrowIncreaseMin"))) {
						singleProductActivity.setTomorrowIncreaseMin(Integer.parseInt(paramMap.get("tomorrowIncreaseMin")));
					}
					if(!StringUtil.isEmpty(paramMap.get("tomorrowIncreaseMax"))) {
						singleProductActivity.setTomorrowIncreaseMax(Integer.parseInt(paramMap.get("tomorrowIncreaseMax")));
					}
				}
				if(!StringUtil.isEmpty(paramMap.get("activityPrice"))) {
					singleProductActivity.setActivityPrice(new BigDecimal(paramMap.get("activityPrice")));
				}
				singleProductActivity.setStatus("0"); //下架
				singleProductActivity.setFirstAuditBy(staffId);
				singleProductActivity.setScheduleAuditBy(staffId);
				singleProductActivity.setUpdateBy(staffId);
				singleProductActivity.setUpdateDate(date);
				SingleProductActivityAuditLog singleProductActivityAuditLog = new SingleProductActivityAuditLog();
				singleProductActivityAuditLog.setSingleProductActivityId(Integer.parseInt(singleProductActivityId));
				singleProductActivityAuditLog.setStatus(paramMap.get("auditStatus"));
				singleProductActivityAuditLog.setCreateBy(staffId);
				singleProductActivityAuditLog.setCreateDate(date);
				singleProductActivityAuditLog.setRemarks(paramMap.get("remarksLog"));
				//商品上架、通过
				Product product = new Product();
				product.setId(Integer.parseInt(request.getParameter("productId")));
				product.setStatus("1");
				product.setAuditStatus("2");
				product.setMinSalePrice(singleProductActivity.getActivityPrice()); // 最低销售价
				product.setUpdateDate(new Date());
				product.setUpdateBy(staffId);
				cutPriceCnfService.updateAuditStatus(singleProductActivity, singleProductActivityAuditLog, product);
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
	
	
	
	
}
