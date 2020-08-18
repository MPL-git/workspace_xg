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
import com.jf.entity.CutPriceCnfDtl;
import com.jf.entity.CutPriceCnfDtlExample;
import com.jf.entity.CutPriceCnfExample;
import com.jf.entity.Product;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityAuditLog;
import com.jf.entity.SingleProductActivityAuditLogExample;
import com.jf.entity.SingleProductActivityCustom;
import com.jf.entity.SingleProductActivityCustomExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.service.CutPriceCnfDtlService;
import com.jf.service.CutPriceCnfService;
import com.jf.service.ProductTypeService;
import com.jf.service.SingleProductActivityAuditLogService;
import com.jf.service.SingleProductActivityService;
import com.jf.vo.Page;

@Controller
@SuppressWarnings("serial")
public class AssistCutPriceProductController extends BaseController {

	@Autowired
	private SingleProductActivityService singleProductActivityService;
	
	@Autowired
	private SingleProductActivityAuditLogService singleProductActivityAuditLogService;
	
	@Autowired
	private CutPriceCnfService cutPriceCnfService;
	
	@Autowired
	private CutPriceCnfDtlService cutPriceCnfDtlService;
	
	
	@Autowired
	private ProductTypeService productTypeService;
	
	/**
	 * 
	 * @Title cutPriceProductManager   
	 * @Description TODO(助力减价)   
	 * @author pengl
	 * @date 2019年2月13日 下午3:31:01
	 */
	@RequestMapping("/assistCutPriceProduct/assistCutPriceProductManager.shtml")
	public ModelAndView cutPriceProductManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/assistCutPriceProduct/getAssistCutPriceProductList");
		ProductTypeExample productTypeExample=new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> lisProductTypes=productTypeService.selectByExample(productTypeExample);
		m.addObject("lisProductTypes", lisProductTypes);
		return m;
	}
	
	/**
	 * 
	 * @Title getSignInCnfList   
	 * @Description TODO(助力减价列表)   
	 * @author pengl
	 * @date 2019年2月13日 下午3:34:15
	 */
	@ResponseBody
	@RequestMapping("/assistCutPriceProduct/getAssistCutPriceProductList.shtml")
	public Map<String, Object> getSignInCnfList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SingleProductActivityCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
			SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
			singleProductActivityCustomCriteria.andDelFlagEqualTo("0")
				.andTypeEqualTo("10"); // 10：助力减价
			if(!StringUtil.isEmpty(request.getParameter("shopName")) && !StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				singleProductActivityCustomCriteria.andShopNameAndMchtCode(request.getParameter("shopName"), request.getParameter("mchtCode"));
			}else {
				if(!StringUtil.isEmpty(request.getParameter("shopName"))) {
					singleProductActivityCustomCriteria.andShopNameLike(request.getParameter("shopName"));
				}
				if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
					singleProductActivityCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("brandName"))) {
				singleProductActivityCustomCriteria.andBrandNameLike(request.getParameter("brandName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("artNo")) && !StringUtil.isEmpty(request.getParameter("productCode"))) {
				singleProductActivityCustomCriteria.andartNoAndProductCode(request.getParameter("artNo"), request.getParameter("productCode"));
			}else {
				if(!StringUtil.isEmpty(request.getParameter("artNo"))) {
					singleProductActivityCustomCriteria.andArtNoLike(request.getParameter("artNo"));
				}
				if(!StringUtil.isEmpty(request.getParameter("productCode"))) {
					singleProductActivityCustomCriteria.andProductCodeEqualTo(request.getParameter("productCode"));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("auditStatus"))) {
				singleProductActivityCustomCriteria.andAuditStatusEqualTo(request.getParameter("auditStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypes"))) {
				singleProductActivityCustomCriteria.andProductTypeEqualTo(Integer.valueOf(request.getParameter("productTypes")));
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))) {
				singleProductActivityCustomCriteria.andStatusEqualTo(request.getParameter("status"));
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
	 * @Title updateAuditStatusManager   
	 * @Description TODO(助力减价审核)   
	 * @author pengl
	 * @date 2019年2月13日 下午5:31:22
	 */
	@RequestMapping("/assistCutPriceProduct/updateAuditStatusManager.shtml")
	public ModelAndView updateAuditStatusManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/assistCutPriceProduct/updateAuditStatus");
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
	 * @Description TODO(助力减价审核)   
	 * @author pengl
	 * @date 2019年2月13日 下午6:04:58
	 */
	@RequestMapping("/assistCutPriceProduct/updateAuditStatus.shtml")
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
					singleProductActivity.setStatus("0"); //下架状态
				}else {
					singleProductActivity.setStatus("1"); //上架状态
				}
				if(!StringUtil.isEmpty(paramMap.get("activityPrice"))) {
					singleProductActivity.setActivityPrice(new BigDecimal(paramMap.get("activityPrice")));
				}
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
				product.setUpdateDate(date);
				product.setUpdateBy(staffId);
				//修改方案
				CutPriceCnf cutPriceCnf = null;
				CutPriceCnfDtl cutPriceCnfDtl = null;
				//审核驳回时，修改助力方案
				if("3".equals(paramMap.get("auditStatus"))) {
					CutPriceCnfExample cutPriceCnfExample = new CutPriceCnfExample();
					cutPriceCnfExample.createCriteria().andDelFlagEqualTo("0").andSingleProductActivityIdEqualTo(singleProductActivity.getId());
					List<CutPriceCnf> cutPriceCnfList = cutPriceCnfService.selectByExample(cutPriceCnfExample);
					if(cutPriceCnfList != null && cutPriceCnfList.size() > 0) {
						cutPriceCnf = cutPriceCnfList.get(0);
						cutPriceCnf.setMaxInviteTimes(Integer.valueOf(paramMap.get("maxInviteTimes")));
						cutPriceCnf.setMinCutToPrice(singleProductActivity.getActivityPrice().subtract(new BigDecimal(paramMap.get("fixedAmount")).multiply(new BigDecimal(paramMap.get("maxInviteTimes")))));
						cutPriceCnf.setActiveTime(Integer.parseInt(request.getParameter("activeTime")));
						cutPriceCnf.setUpdateBy(staffId);
						cutPriceCnf.setUpdateDate(date);
						CutPriceCnfDtlExample cutPriceCnfDtlExample = new CutPriceCnfDtlExample();
						cutPriceCnfDtlExample.createCriteria().andDelFlagEqualTo("0").andCutPriceCnfIdEqualTo(cutPriceCnf.getId());
						cutPriceCnfDtlExample.setOrderByClause(" id desc");
						cutPriceCnfDtlExample.setLimitStart(0);
						cutPriceCnfDtlExample.setLimitSize(1);
						List<CutPriceCnfDtl> cutPriceCnfDtlList = cutPriceCnfDtlService.selectByExample(cutPriceCnfDtlExample);
						if(cutPriceCnfDtlList != null && cutPriceCnfDtlList.size() > 0) {
							cutPriceCnfDtl = cutPriceCnfDtlList.get(0);
							cutPriceCnfDtl.setFixedAmount(new BigDecimal(paramMap.get("fixedAmount")));
							cutPriceCnfDtl.setUpdateBy(staffId);
							cutPriceCnfDtl.setUpdateDate(date);
						}
					}
				}
				cutPriceCnfService.updateAuditStatusAndCutPriceCnf(singleProductActivity, singleProductActivityAuditLog, product, cutPriceCnf, cutPriceCnfDtl);
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
