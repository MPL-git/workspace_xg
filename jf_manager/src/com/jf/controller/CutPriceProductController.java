package com.jf.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
import com.jf.entity.CutPriceCnfTpl;
import com.jf.entity.CutPriceCnfTplCustom;
import com.jf.entity.CutPriceCnfTplCustomExample;
import com.jf.entity.CutPriceCnfTplDtl;
import com.jf.entity.CutPriceCnfTplDtlExample;
import com.jf.entity.CutPriceCnfTplExample;
import com.jf.entity.Product;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityAuditLog;
import com.jf.entity.SingleProductActivityAuditLogExample;
import com.jf.entity.SingleProductActivityCustom;
import com.jf.entity.SingleProductActivityCustomExample;
import com.jf.entity.SingleProductActivityExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.service.CutPriceCnfDtlService;
import com.jf.service.CutPriceCnfService;
import com.jf.service.CutPriceCnfTplDtlService;
import com.jf.service.CutPriceCnfTplService;
import com.jf.service.SingleProductActivityAuditLogService;
import com.jf.service.SingleProductActivityService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class CutPriceProductController extends BaseController {

	@Autowired
	private SingleProductActivityService singleProductActivityService;
	
	@Autowired
	private CutPriceCnfService cutPriceCnfService;
	
	@Autowired
	private CutPriceCnfDtlService cutPriceCnfDtlService;
	
	@Autowired
	private CutPriceCnfTplService cutPriceCnfTplService;
	
	@Autowired
	private CutPriceCnfTplDtlService cutPriceCnfTplDtlService;
	
	@Autowired
	private SingleProductActivityAuditLogService singleProductActivityAuditLogService;
	
	
	/**
	 * 
	 * @Title cutPriceProductManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月5日 下午2:43:52
	 */
	@RequestMapping("/cutPriceProduct/cutPriceProductManager.shtml")
	public ModelAndView cutPriceProductManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/cutPriceProduct/getCutPriceProductList");
		return m;
	}
	
	/**
	 * 
	 * @Title getSignInCnfList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月5日 下午4:46:25
	 */
	@ResponseBody
	@RequestMapping("/cutPriceProduct/getCutPriceProductList.shtml")
	public Map<String, Object> getSignInCnfList(HttpServletRequest request, Page page, Integer singleProductActivityId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SingleProductActivityCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
			SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
			singleProductActivityCustomCriteria.andDelFlagEqualTo("0")
				.andIsCloseEqualTo("0")
				.andTypeEqualTo("7"); // 7：砍价免费拿
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
			singleProductActivityCustomExample.setOrderByClause(" t.id desc");
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
	 * @date 2018年6月5日 下午5:38:03
	 */
	@ResponseBody
	@RequestMapping("/cutPriceProduct/updateSingleProductActivity.shtml")
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
	 * @date 2018年6月5日 下午6:10:54
	 */
	@ResponseBody
	@RequestMapping("/cutPriceProduct/updateStatus.shtml")
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
	 * @Title cutPriceCnfManager   
	 * @Description TODO(砍价设置)   
	 * @author pengl
	 * @date 2018年6月5日 下午6:26:46
	 */
	@RequestMapping("/cutPriceProduct/addOrUpdateCutPriceCnfManager.shtml")
	public ModelAndView addOrUpdateCutPriceCnfManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/cutPriceProduct/addOrUpdateCutPriceCnf");
		String singleProductActivityId = request.getParameter("singleProductActivityId");
		m.addObject("singleProductActivityId", singleProductActivityId);
		if(!StringUtil.isEmpty(singleProductActivityId)) {
			CutPriceCnfExample cutPriceCnfExample = new CutPriceCnfExample();
			CutPriceCnfExample.Criteria cutPriceCnfCriteria = cutPriceCnfExample.createCriteria();
			cutPriceCnfCriteria.andDelFlagEqualTo("0").andSingleProductActivityIdEqualTo(Integer.parseInt(singleProductActivityId));
			List<CutPriceCnf> cutPriceCnfList = cutPriceCnfService.selectByExample(cutPriceCnfExample);
			if(cutPriceCnfList != null && cutPriceCnfList.size() > 0) { //砍价设置（已设置过）
				CutPriceCnfDtlExample cutPriceCnfDtlExample = new CutPriceCnfDtlExample();
				CutPriceCnfDtlExample.Criteria cutPriceCnfDtlCriteria = cutPriceCnfDtlExample.createCriteria();
				cutPriceCnfDtlCriteria.andDelFlagEqualTo("0")
					.andCutPriceCnfIdEqualTo(cutPriceCnfList.get(0).getId());
				List<CutPriceCnfDtl> cutPriceCnfDtlList = cutPriceCnfDtlService.selectByExample(cutPriceCnfDtlExample);
				if(cutPriceCnfDtlList != null && cutPriceCnfDtlList.size() > 0) {
					List<CutPriceCnfDtl> cutPriceCnfDtls = new ArrayList<CutPriceCnfDtl>();
					for(CutPriceCnfDtl cutPriceCnfDtl : cutPriceCnfDtlList) {
						if("1".equals(cutPriceCnfDtl.getRateType())) {
							m.addObject("jcCutPriceCnfDtl", cutPriceCnfDtl); // 1 基础比例
						}else if("2".equals(cutPriceCnfDtl.getRateType())) {
							m.addObject("scCutPriceCnfDtl", cutPriceCnfDtl); // 2 首次砍
						}else {
							cutPriceCnfDtls.add(cutPriceCnfDtl);
						}
					}
					Collections.sort(cutPriceCnfDtls, new Comparator<CutPriceCnfDtl>() {
						@Override
						public int compare(CutPriceCnfDtl o1, CutPriceCnfDtl o2) {
							if(o1.getEndAmount().compareTo(o2.getEndAmount()) > 0) {
								return 1;
							}
							if(o1.getEndAmount().compareTo(o2.getEndAmount()) == 0) {
								return 0;
							}
							return -1;
						}
					});
					m.addObject("oneCutPriceCnfDtl", cutPriceCnfDtls.get(0));
					m.addObject("twoCutPriceCnfDtl", cutPriceCnfDtls.get(1));
					m.addObject("threeCutPriceCnfDtl", cutPriceCnfDtls.get(2));
					m.addObject("fourCutPriceCnfDtl", cutPriceCnfDtls.get(3));
					m.addObject("fiveCutPriceCnfDtl", cutPriceCnfDtls.get(4));
					m.addObject("sixCutPriceCnfDtl", cutPriceCnfDtls.get(5));
					m.addObject("sevenCutPriceCnfDtl", cutPriceCnfDtls.get(6));
					m.addObject("cutPriceCnf", cutPriceCnfList.get(0));
				}
			}else { //砍价设置（没设置过）
				SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
				SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
				singleProductActivityCustomCriteria.andDelFlagEqualTo("0")
					.andIdEqualTo(Integer.parseInt(singleProductActivityId));
				List<SingleProductActivityCustom> singleProductActivityCustomList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
				BigDecimal tagPriceMax =  singleProductActivityCustomList.get(0).getTagPriceMax(); //最大吊牌价
				CutPriceCnfTplExample cutPriceCnfTplExample = new CutPriceCnfTplExample();
				CutPriceCnfTplExample.Criteria cutPriceCnfTplCriteria = cutPriceCnfTplExample.createCriteria();
				cutPriceCnfTplCriteria.andDelFlagEqualTo("0");
				List<CutPriceCnfTpl> cutPriceCnfTplList = cutPriceCnfTplService.selectByExample(cutPriceCnfTplExample);
				for(CutPriceCnfTpl cutPriceCnfTpl : cutPriceCnfTplList) {
					if(tagPriceMax.compareTo(cutPriceCnfTpl.getBeginPrice()) >= 0 && tagPriceMax.compareTo(cutPriceCnfTpl.getEndPrice()) < 0) {
						CutPriceCnfTplDtlExample cutPriceCnfTplDtlExample = new CutPriceCnfTplDtlExample();
						CutPriceCnfTplDtlExample.Criteria cutPriceCnfTplDtlCriteria = cutPriceCnfTplDtlExample.createCriteria();
						cutPriceCnfTplDtlCriteria.andDelFlagEqualTo("0").andCutPriceCnfTplIdEqualTo(cutPriceCnfTpl.getId());
						List<CutPriceCnfTplDtl> cutPriceCnfTplDtlList = cutPriceCnfTplDtlService.selectByExample(cutPriceCnfTplDtlExample);
						List<CutPriceCnfTplDtl> cutPriceCnfTplDtls = new ArrayList<CutPriceCnfTplDtl>();
						for(CutPriceCnfTplDtl cutPriceCnfTplDtl : cutPriceCnfTplDtlList) {
							if("1".equals(cutPriceCnfTplDtl.getRateType())) {
								m.addObject("jcCutPriceCnfDtl", cutPriceCnfTplDtl); // 1 基础比例
							}else if("2".equals(cutPriceCnfTplDtl.getRateType())) {
								m.addObject("scCutPriceCnfDtl", cutPriceCnfTplDtl); // 2 首次砍
							}else {
								cutPriceCnfTplDtls.add(cutPriceCnfTplDtl);
							}
						}
						Collections.sort(cutPriceCnfTplDtls, new Comparator<CutPriceCnfTplDtl>() {
							@Override
							public int compare(CutPriceCnfTplDtl o1, CutPriceCnfTplDtl o2) {
								if(o1.getEndAmount().compareTo(o2.getEndAmount()) > 0) {
									return 1;
								}
								if(o1.getEndAmount().compareTo(o2.getEndAmount()) == 0) {
									return 0;
								}
								return -1;
							}
						});
						m.addObject("oneCutPriceCnfDtl", cutPriceCnfTplDtls.get(0));
						m.addObject("twoCutPriceCnfDtl", cutPriceCnfTplDtls.get(1));
						m.addObject("threeCutPriceCnfDtl", cutPriceCnfTplDtls.get(2));
						m.addObject("fourCutPriceCnfDtl", cutPriceCnfTplDtls.get(3));
						m.addObject("fiveCutPriceCnfDtl", cutPriceCnfTplDtls.get(4));
						m.addObject("sixCutPriceCnfDtl", cutPriceCnfTplDtls.get(5));
						m.addObject("sevenCutPriceCnfDtl", cutPriceCnfTplDtls.get(6));
						break;
					}
				}
			}
		}
		return m;
	}
	
	/**
	 * 
	 * @Title showResult   
	 * @Description TODO(开始计算)   
	 * @author pengl
	 * @date 2018年6月6日 下午5:50:29
	 */
	@ResponseBody
	@RequestMapping("/cutPriceProduct/showResult.shtml")
	public Map<String, Object> showResult(HttpServletRequest request, @RequestParam HashMap<String, String> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			String singleProductActivityId = paramMap.get("singleProductActivityId");
			if(!StringUtil.isEmpty(singleProductActivityId)) {
				SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
				SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
				singleProductActivityCustomCriteria.andDelFlagEqualTo("0")
					.andIdEqualTo(Integer.parseInt(singleProductActivityId));
				List<SingleProductActivityCustom> singleProductActivityCustomList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
				BigDecimal tagPriceMax =  singleProductActivityCustomList.get(0).getTagPriceMax(); //最大吊牌价
				Map<String, CutPriceCnfDtl> cutPriceCnfDtlMap = new HashMap<String, CutPriceCnfDtl>();
				CutPriceCnfDtl oneCutPriceCnfDtl = new CutPriceCnfDtl();
				if(!StringUtil.isEmpty(paramMap.get("oneBeginAmount"))) {
					oneCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("oneBeginAmount")));
				}else {
					oneCutPriceCnfDtl.setBeginAmount(new BigDecimal(0));
				}
				oneCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("oneEndAmount")));
				oneCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("oneBeginRate")));
				oneCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("oneEndRate")));
				cutPriceCnfDtlMap.put("oneCutPriceCnfDtl", oneCutPriceCnfDtl);
				CutPriceCnfDtl twoCutPriceCnfDtl = new CutPriceCnfDtl();
				twoCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("oneEndAmount")));
				twoCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("twoEndAmount")));
				twoCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("twoBeginRate")));
				twoCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("twoEndRate")));
				cutPriceCnfDtlMap.put("twoCutPriceCnfDtl", twoCutPriceCnfDtl);
				CutPriceCnfDtl threeCutPriceCnfDtl = new CutPriceCnfDtl();
				threeCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("twoEndAmount")));
				threeCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("threeEndAmount")));
				threeCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("threeBeginRate")));
				threeCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("threeEndRate")));
				cutPriceCnfDtlMap.put("threeCutPriceCnfDtl", threeCutPriceCnfDtl);
				CutPriceCnfDtl fourCutPriceCnfDtl = new CutPriceCnfDtl();
				fourCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("threeEndAmount")));
				fourCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("fourEndAmount")));
				fourCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("fourBeginRate")));
				fourCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("fourEndRate")));
				cutPriceCnfDtlMap.put("fourCutPriceCnfDtl", fourCutPriceCnfDtl);
				CutPriceCnfDtl fiveCutPriceCnfDtl = new CutPriceCnfDtl();
				fiveCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("fourEndAmount")));
				fiveCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("fiveEndAmount")));
				fiveCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("fiveBeginRate")));
				fiveCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("fiveEndRate")));
				cutPriceCnfDtlMap.put("fiveCutPriceCnfDtl", fiveCutPriceCnfDtl);
				CutPriceCnfDtl sixCutPriceCnfDtl = new CutPriceCnfDtl();
				sixCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("fiveEndAmount")));
				sixCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("sixEndAmount")));
				sixCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("sixBeginRate")));
				sixCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("sixEndRate")));
				cutPriceCnfDtlMap.put("sixCutPriceCnfDtl", sixCutPriceCnfDtl);
				CutPriceCnfDtl sevenCutPriceCnfDtl = new CutPriceCnfDtl();
				sevenCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("sixEndAmount")));
				sevenCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("sevenEndAmount")));
				sevenCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("sevenBeginRate")));
				sevenCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("sevenEndRate")));
				cutPriceCnfDtlMap.put("sevenCutPriceCnfDtl", sevenCutPriceCnfDtl);
				CutPriceCnfDtl jcCutPriceCnfDtl = new CutPriceCnfDtl();
				jcCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("jcBeginRate")));
				jcCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("jcEndRate")));
				cutPriceCnfDtlMap.put("jcCutPriceCnfDtl", jcCutPriceCnfDtl);
				String flagBegin = "begin";
				float amountMin = tagPriceMax.multiply(new BigDecimal(paramMap.get("scBeginRate")).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)).floatValue();
				String flagEnd = "end";
				float amountMax = tagPriceMax.multiply(new BigDecimal(paramMap.get("scEndRate")).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)).floatValue();
				Map<String, Object> mapNum = new HashMap<String, Object>();
				mapNum.put("daySum", 1);
				mapNum.put("oneNum", 0);
				mapNum.put("twoNum", 0);
				mapNum.put("threeNum", 0);
				mapNum.put("fourNum", 0);
				mapNum.put("fiveNum", 0);
				mapNum.put("sixNum", 0);
				mapNum.put("sevenNum", 0);
				Map<String, Object> mapMin = this.kjNum(tagPriceMax, cutPriceCnfDtlMap, amountMax, flagEnd, mapNum);
				Map<String, Object> mapMax = this.kjNum(tagPriceMax, cutPriceCnfDtlMap, amountMin, flagBegin, mapNum);
				resMap.put("mapMin", mapMin);
				resMap.put("mapMax", mapMax);
			}
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
	 * @Title kjDay   
	 * @Description TODO(开始结算递归方法)   
	 * @author pengl
	 * @date 2018年6月7日 上午9:29:19
	 */
	public Map<String, Object> kjNum(BigDecimal tagPrice, Map<String, CutPriceCnfDtl> cutPriceCnfDtlMap, float amount, String flag, Map<String, Object> map) {
		if(cutPriceCnfDtlMap != null) {
			if(tagPrice != null && amount < tagPrice.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()) {
				Integer daySum = (Integer) map.get("daySum");
				Integer oneNum = (Integer) map.get("oneNum");
				Integer twoNum = (Integer) map.get("twoNum");
				Integer threeNum = (Integer) map.get("threeNum");
				Integer fourNum = (Integer) map.get("fourNum");
				Integer fiveNum = (Integer) map.get("fiveNum");
				Integer sixNum = (Integer) map.get("sixNum");
				Integer sevenNum = (Integer) map.get("sevenNum");
				CutPriceCnfDtl oneCutPriceCnfDtl = cutPriceCnfDtlMap.get("oneCutPriceCnfDtl");
				CutPriceCnfDtl twoCutPriceCnfDtl = cutPriceCnfDtlMap.get("twoCutPriceCnfDtl");
				CutPriceCnfDtl threeCutPriceCnfDtl = cutPriceCnfDtlMap.get("threeCutPriceCnfDtl");
				CutPriceCnfDtl fourCutPriceCnfDtl = cutPriceCnfDtlMap.get("fourCutPriceCnfDtl");
				CutPriceCnfDtl fiveCutPriceCnfDtl = cutPriceCnfDtlMap.get("fiveCutPriceCnfDtl");
				CutPriceCnfDtl sixCutPriceCnfDtl = cutPriceCnfDtlMap.get("sixCutPriceCnfDtl");
				CutPriceCnfDtl sevenCutPriceCnfDtl = cutPriceCnfDtlMap.get("sevenCutPriceCnfDtl");
				CutPriceCnfDtl jcCutPriceCnfDtl = cutPriceCnfDtlMap.get("jcCutPriceCnfDtl");
				if(amount >= oneCutPriceCnfDtl.getBeginAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() && amount < oneCutPriceCnfDtl.getEndAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()) {
					if("begin".equals(flag)) {
						amount = tagPrice.multiply(oneCutPriceCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}else if("end".equals(flag)) {
						amount = tagPrice.multiply(oneCutPriceCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}
					++oneNum;
				}else if(amount >= twoCutPriceCnfDtl.getBeginAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() && amount < twoCutPriceCnfDtl.getEndAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()) {
					if("begin".equals(flag)) {
						amount = tagPrice.multiply(twoCutPriceCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}else if("end".equals(flag)) {
						amount = tagPrice.multiply(twoCutPriceCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}
					++twoNum;
				}else if(amount >= threeCutPriceCnfDtl.getBeginAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() && amount < threeCutPriceCnfDtl.getEndAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()) {
					if("begin".equals(flag)) {
						amount = tagPrice.multiply(threeCutPriceCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}else if("end".equals(flag)) {
						amount = tagPrice.multiply(threeCutPriceCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}
					++threeNum;
				}else if(amount >= fourCutPriceCnfDtl.getBeginAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() && amount < fourCutPriceCnfDtl.getEndAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()) {
					if("begin".equals(flag)) {
						amount = tagPrice.multiply(fourCutPriceCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}else if("end".equals(flag)) {
						amount = tagPrice.multiply(fourCutPriceCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}
					++fourNum;
				}else if(amount >= fiveCutPriceCnfDtl.getBeginAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() && amount < fiveCutPriceCnfDtl.getEndAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()) {
					if("begin".equals(flag)) {
						amount = tagPrice.multiply(fiveCutPriceCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}else if("end".equals(flag)) {
						amount = tagPrice.multiply(fiveCutPriceCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}
					++fiveNum;
				}else if(amount >= sixCutPriceCnfDtl.getBeginAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() && amount < sixCutPriceCnfDtl.getEndAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()) {
					if("begin".equals(flag)) {
						amount = tagPrice.multiply(sixCutPriceCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}else if("end".equals(flag)) {
						amount = tagPrice.multiply(sixCutPriceCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}
					++sixNum;
				}else if(amount >= sevenCutPriceCnfDtl.getBeginAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() && amount < sevenCutPriceCnfDtl.getEndAmount().setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()) {
					if("begin".equals(flag)) {
						amount = tagPrice.multiply(sevenCutPriceCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}else if("end".equals(flag)) {
						amount = tagPrice.multiply(sevenCutPriceCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}
					++sevenNum;
				}else{
					if("begin".equals(flag)) {
						amount = tagPrice.multiply(jcCutPriceCnfDtl.getBeginRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}else if("end".equals(flag)) {
						amount = tagPrice.multiply(jcCutPriceCnfDtl.getEndRate()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
					}
				}
				++daySum;
				Map<String, Object> mapNew = new HashMap<String, Object>();
				mapNew.put("daySum", daySum);
				mapNew.put("oneNum", oneNum);
				mapNew.put("twoNum", twoNum);
				mapNew.put("threeNum", threeNum);
				mapNew.put("fourNum", fourNum);
				mapNew.put("fiveNum", fiveNum);
				mapNew.put("sixNum", sixNum);
				mapNew.put("sevenNum", sevenNum);
				return kjNum(tagPrice, cutPriceCnfDtlMap, amount, flag, mapNew);
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @Title addOrUpdateCutPriceCnf   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月7日 下午2:52:00
	 */
	@RequestMapping("/cutPriceProduct/addOrUpdateCutPriceCnf.shtml")
	public ModelAndView addOrUpdateCutPriceCnf(HttpServletRequest request, @RequestParam HashMap<String, String> paramMap) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			String singleProductActivityId = paramMap.get("singleProductActivityId");
			String singleAuditStatus=null;
			if(!StringUtil.isEmpty(singleProductActivityId)) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				Integer staffId = Integer.valueOf(staffBean.getStaffID());
				Date date = new Date();
				SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
				SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
				singleProductActivityCustomCriteria.andDelFlagEqualTo("0")
					.andIdEqualTo(Integer.parseInt(singleProductActivityId));
				List<SingleProductActivityCustom> singleProductActivityCustomList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
				if(singleProductActivityCustomList == null || singleProductActivityCustomList.size() == 0) {
					resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
					resMap.put(this.JSON_RESULT_MESSAGE, "单品不存在！");
					return new ModelAndView(rtPage,resMap);
				}
				singleAuditStatus=singleProductActivityCustomList.get(0).getAuditStatus();
				BigDecimal tagPriceMax =  singleProductActivityCustomList.get(0).getTagPriceMax(); //最大吊牌价
				Map<String, CutPriceCnfDtl> cutPriceCnfDtlMap = new HashMap<String, CutPriceCnfDtl>();
				CutPriceCnfDtl oneCutPriceCnfDtl = new CutPriceCnfDtl();
				if(!StringUtil.isEmpty(paramMap.get("oneBeginAmount"))) {
					oneCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("oneBeginAmount")));
				}else {
					oneCutPriceCnfDtl.setBeginAmount(new BigDecimal(0));
				}
				oneCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("oneEndAmount")));
				oneCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("oneBeginRate")));
				oneCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("oneEndRate")));
				cutPriceCnfDtlMap.put("oneCutPriceCnfDtl", oneCutPriceCnfDtl);
				
				CutPriceCnfDtl twoCutPriceCnfDtl = new CutPriceCnfDtl();
				twoCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("oneEndAmount")));
				twoCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("twoEndAmount")));
				twoCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("twoBeginRate")));
				twoCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("twoEndRate")));
				cutPriceCnfDtlMap.put("twoCutPriceCnfDtl", twoCutPriceCnfDtl);
				
				CutPriceCnfDtl threeCutPriceCnfDtl = new CutPriceCnfDtl();
				threeCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("twoEndAmount")));
				threeCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("threeEndAmount")));
				threeCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("threeBeginRate")));
				threeCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("threeEndRate")));
				cutPriceCnfDtlMap.put("threeCutPriceCnfDtl", threeCutPriceCnfDtl);
				
				CutPriceCnfDtl fourCutPriceCnfDtl = new CutPriceCnfDtl();
				fourCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("threeEndAmount")));
				fourCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("fourEndAmount")));
				fourCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("fourBeginRate")));
				fourCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("fourEndRate")));
				cutPriceCnfDtlMap.put("fourCutPriceCnfDtl", fourCutPriceCnfDtl);
				
				CutPriceCnfDtl fiveCutPriceCnfDtl = new CutPriceCnfDtl();
				fiveCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("fourEndAmount")));
				fiveCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("fiveEndAmount")));
				fiveCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("fiveBeginRate")));
				fiveCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("fiveEndRate")));
				cutPriceCnfDtlMap.put("fiveCutPriceCnfDtl", fiveCutPriceCnfDtl);
				
				CutPriceCnfDtl sixCutPriceCnfDtl = new CutPriceCnfDtl();
				sixCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("fiveEndAmount")));
				sixCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("sixEndAmount")));
				sixCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("sixBeginRate")));
				sixCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("sixEndRate")));
				cutPriceCnfDtlMap.put("sixCutPriceCnfDtl", sixCutPriceCnfDtl);
				
				CutPriceCnfDtl sevenCutPriceCnfDtl = new CutPriceCnfDtl();
				sevenCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("sixEndAmount")));
				sevenCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("sevenEndAmount")));
				sevenCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("sevenBeginRate")));
				sevenCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("sevenEndRate")));
				cutPriceCnfDtlMap.put("sevenCutPriceCnfDtl", sevenCutPriceCnfDtl);
				
				CutPriceCnfDtl jcCutPriceCnfDtl = new CutPriceCnfDtl();
				jcCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("jcBeginRate")));
				jcCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("jcEndRate")));
				cutPriceCnfDtlMap.put("jcCutPriceCnfDtl", jcCutPriceCnfDtl);
				String flagBegin = "begin";
				float amountMin = tagPriceMax.multiply(new BigDecimal(paramMap.get("scBeginRate")).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)).floatValue();
				String flagEnd = "end";
				float amountMax = tagPriceMax.multiply(new BigDecimal(paramMap.get("scEndRate")).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)).floatValue();
				Map<String, Object> mapNum = new HashMap<String, Object>();
				mapNum.put("daySum", 1);
				mapNum.put("oneNum", 0);
				mapNum.put("twoNum", 0);
				mapNum.put("threeNum", 0);
				mapNum.put("fourNum", 0);
				mapNum.put("fiveNum", 0);
				mapNum.put("sixNum", 0);
				mapNum.put("sevenNum", 0);
				Map<String, Object> mapMin = this.kjNum(tagPriceMax, cutPriceCnfDtlMap, amountMax, flagEnd, mapNum);
				Map<String, Object> mapMax = this.kjNum(tagPriceMax, cutPriceCnfDtlMap, amountMin, flagBegin, mapNum);
				
				CutPriceCnf cutPriceCnf = new CutPriceCnf();
				cutPriceCnf.setPredictMinTimes((Integer)mapMin.get("daySum"));
				cutPriceCnf.setPredictMaxTimes((Integer)mapMax.get("daySum"));
				CutPriceCnfDtl scCutPriceCnfDtl = new CutPriceCnfDtl();
				scCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("scBeginRate")));
				scCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("scEndRate")));
				String cutPriceCnfId = paramMap.get("cutPriceCnfId");
				List<CutPriceCnfDtl> cutPriceCnfDtlList = new ArrayList<CutPriceCnfDtl>();
				if(!StringUtil.isEmpty(cutPriceCnfId)) { //修改
					//砍价方案
					cutPriceCnf.setId(Integer.parseInt(cutPriceCnfId));
					cutPriceCnf.setUpdateBy(staffId);
					cutPriceCnf.setUpdateDate(date);
					//砍价方案明细
					oneCutPriceCnfDtl.setUpdateBy(staffId);
					oneCutPriceCnfDtl.setUpdateDate(date);
					oneCutPriceCnfDtl.setId(Integer.parseInt(paramMap.get("oneCutPriceCnfDtlId")));
					cutPriceCnfDtlList.add(oneCutPriceCnfDtl);
					
					twoCutPriceCnfDtl.setUpdateBy(staffId);
					twoCutPriceCnfDtl.setUpdateDate(date);
					twoCutPriceCnfDtl.setId(Integer.parseInt(paramMap.get("twoCutPriceCnfDtlId")));
					cutPriceCnfDtlList.add(twoCutPriceCnfDtl);
					
					threeCutPriceCnfDtl.setUpdateBy(staffId);
					threeCutPriceCnfDtl.setUpdateDate(date);
					threeCutPriceCnfDtl.setId(Integer.parseInt(paramMap.get("threeCutPriceCnfDtlId")));
					cutPriceCnfDtlList.add(threeCutPriceCnfDtl);
					
					fourCutPriceCnfDtl.setUpdateBy(staffId);
					fourCutPriceCnfDtl.setUpdateDate(date);
					fourCutPriceCnfDtl.setId(Integer.parseInt(paramMap.get("fourCutPriceCnfDtlId")));
					cutPriceCnfDtlList.add(fourCutPriceCnfDtl);
					
					fiveCutPriceCnfDtl.setUpdateBy(staffId);
					fiveCutPriceCnfDtl.setUpdateDate(date);
					fiveCutPriceCnfDtl.setId(Integer.parseInt(paramMap.get("fiveCutPriceCnfDtlId")));
					cutPriceCnfDtlList.add(fiveCutPriceCnfDtl);
					
					sixCutPriceCnfDtl.setUpdateBy(staffId);
					sixCutPriceCnfDtl.setUpdateDate(date);
					sixCutPriceCnfDtl.setId(Integer.parseInt(paramMap.get("sixCutPriceCnfDtlId")));
					cutPriceCnfDtlList.add(sixCutPriceCnfDtl);
					
					sevenCutPriceCnfDtl.setUpdateBy(staffId);
					sevenCutPriceCnfDtl.setUpdateDate(date);
					sevenCutPriceCnfDtl.setId(Integer.parseInt(paramMap.get("sevenCutPriceCnfDtlId")));
					cutPriceCnfDtlList.add(sevenCutPriceCnfDtl);
					
					jcCutPriceCnfDtl.setUpdateBy(staffId);
					jcCutPriceCnfDtl.setUpdateDate(date);
					jcCutPriceCnfDtl.setId(Integer.parseInt(paramMap.get("jcCutPriceCnfDtlId")));
					cutPriceCnfDtlList.add(jcCutPriceCnfDtl);
					
					scCutPriceCnfDtl.setUpdateBy(staffId);
					scCutPriceCnfDtl.setUpdateDate(date);
					scCutPriceCnfDtl.setId(Integer.parseInt(paramMap.get("scCutPriceCnfDtlId")));
					cutPriceCnfDtlList.add(scCutPriceCnfDtl);
				}else { //新增
					//砍价方案
					cutPriceCnf.setSingleProductActivityId(Integer.parseInt(singleProductActivityId));
					cutPriceCnf.setNeedCutToPrice(new BigDecimal(0));
					cutPriceCnf.setMinCutToPrice(new BigDecimal(0));
					cutPriceCnf.setCreateBy(staffId);
					cutPriceCnf.setCreateDate(date);
					//砍价方案明细
					oneCutPriceCnfDtl.setCreateBy(staffId);
					oneCutPriceCnfDtl.setCreateDate(date);
					oneCutPriceCnfDtl.setRateType("3");
					cutPriceCnfDtlList.add(oneCutPriceCnfDtl);
					
					twoCutPriceCnfDtl.setCreateBy(staffId);
					twoCutPriceCnfDtl.setCreateDate(date);
					twoCutPriceCnfDtl.setRateType("3");
					cutPriceCnfDtlList.add(twoCutPriceCnfDtl);
					
					threeCutPriceCnfDtl.setCreateBy(staffId);
					threeCutPriceCnfDtl.setCreateDate(date);
					threeCutPriceCnfDtl.setRateType("3");
					cutPriceCnfDtlList.add(threeCutPriceCnfDtl);
					
					fourCutPriceCnfDtl.setCreateBy(staffId);
					fourCutPriceCnfDtl.setCreateDate(date);
					fourCutPriceCnfDtl.setRateType("3");
					cutPriceCnfDtlList.add(fourCutPriceCnfDtl);
					
					fiveCutPriceCnfDtl.setCreateBy(staffId);
					fiveCutPriceCnfDtl.setCreateDate(date);
					fiveCutPriceCnfDtl.setRateType("3");
					cutPriceCnfDtlList.add(fiveCutPriceCnfDtl);
					
					sixCutPriceCnfDtl.setCreateBy(staffId);
					sixCutPriceCnfDtl.setCreateDate(date);
					sixCutPriceCnfDtl.setRateType("3");
					cutPriceCnfDtlList.add(sixCutPriceCnfDtl);
					
					sevenCutPriceCnfDtl.setCreateBy(staffId);
					sevenCutPriceCnfDtl.setCreateDate(date);
					sevenCutPriceCnfDtl.setRateType("3");
					cutPriceCnfDtlList.add(sevenCutPriceCnfDtl);
					
					jcCutPriceCnfDtl.setCreateBy(staffId);
					jcCutPriceCnfDtl.setCreateDate(date);
					jcCutPriceCnfDtl.setRateType("1");
					cutPriceCnfDtlList.add(jcCutPriceCnfDtl);
					
					scCutPriceCnfDtl.setCreateBy(staffId);
					scCutPriceCnfDtl.setCreateDate(date);
					scCutPriceCnfDtl.setRateType("2");
					cutPriceCnfDtlList.add(scCutPriceCnfDtl);
				}
				cutPriceCnfService.addOrUpdateCutPriceCnf(cutPriceCnf, cutPriceCnfDtlList);
			}
			//申请中修改为初审通过
			if ("0".equals(singleAuditStatus)){
				SingleProductActivity singleProductActivity2=new SingleProductActivity(); 
				singleProductActivity2.setId(Integer.parseInt(singleProductActivityId));
				singleProductActivity2.setAuditStatus("1");
				singleProductActivityService.updateByPrimaryKeySelective(singleProductActivity2);
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
	 * @date 2018年6月7日 下午3:05:05
	 */
	@RequestMapping("/cutPriceProduct/updateAuditStatusManager.shtml")
	public ModelAndView updateAuditStatusManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/cutPriceProduct/updateAuditStatus");
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
	 * @date 2018年6月7日 下午4:09:55
	 */
	@RequestMapping("/cutPriceProduct/updateAuditStatus.shtml")
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
				singleProductActivity.setStatus("0"); //下架状态
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
	
	/**
	 * 
	 * @Title cutPriceCnfTplManager   
	 * @Description TODO(砍价方案模版)   
	 * @author pengl
	 * @date 2018年6月5日 下午6:20:57
	 */
	@RequestMapping("/cutPriceProduct/cutPriceCnfTplManager.shtml")
	public ModelAndView cutPriceCnfTplManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/cutPriceProduct/getCutPriceCnfTplList");
		return m;
	}
	
	/**
	 * 
	 * @Title getCutPriceCnfTplList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月5日 下午6:24:53
	 */
	@ResponseBody
	@RequestMapping("/cutPriceProduct/getCutPriceCnfTplList.shtml")
	public Map<String, Object> getCutPriceCnfTplList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CutPriceCnfTplCustom> dataList = null;
		Integer totalCount = 0;
		try {
			CutPriceCnfTplCustomExample cutPriceCnfTplCustomExample = new CutPriceCnfTplCustomExample();
			CutPriceCnfTplCustomExample.CutPriceCnfTplCriteria cutPriceCnfTplCustomCriteria = cutPriceCnfTplCustomExample.createCriteria();
			cutPriceCnfTplCustomCriteria.andDelFlagEqualTo("0");
			cutPriceCnfTplCustomExample.setOrderByClause(" t.id desc");
			cutPriceCnfTplCustomExample.setLimitStart(page.getLimitStart());
			cutPriceCnfTplCustomExample.setLimitSize(page.getLimitSize());
			totalCount = cutPriceCnfTplService.countByCustomExample(cutPriceCnfTplCustomExample);
			dataList = cutPriceCnfTplService.selectByCustomExample(cutPriceCnfTplCustomExample);
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
	 * @Title addOrUpdateOrSeeCutPriceCnfTplManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月7日 下午6:04:30
	 */
	@RequestMapping("/cutPriceProduct/addOrUpdateOrSeeCutPriceCnfTplManager.shtml")
	public ModelAndView addOrUpdateOrSeeCutPriceCnfTplManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/cutPriceProduct/addOrUpdateOrSeeCutPriceCnfTpl");
		String cutPriceCnfTplId = request.getParameter("cutPriceCnfTplId");
		if(!StringUtil.isEmpty(cutPriceCnfTplId)) {
			CutPriceCnfTpl cutPriceCnfTpl = cutPriceCnfTplService.selectByCustomPrimaryKey(Integer.parseInt(cutPriceCnfTplId));
			CutPriceCnfTplDtlExample cutPriceCnfTplDtlExample = new CutPriceCnfTplDtlExample();
			CutPriceCnfTplDtlExample.Criteria cutPriceCnfTplDtlCriteria = cutPriceCnfTplDtlExample.createCriteria();
			cutPriceCnfTplDtlCriteria.andDelFlagEqualTo("0")
				.andCutPriceCnfTplIdEqualTo(cutPriceCnfTpl.getId());
			List<CutPriceCnfTplDtl> cutPriceCnfTplDtlList = cutPriceCnfTplDtlService.selectByExample(cutPriceCnfTplDtlExample);
			List<CutPriceCnfTplDtl> cutPriceCnfTplDtls = new ArrayList<CutPriceCnfTplDtl>();
			for(CutPriceCnfTplDtl cutPriceCnfTplDtl : cutPriceCnfTplDtlList) {
				if("1".equals(cutPriceCnfTplDtl.getRateType())) {
					m.addObject("jcCutPriceCnfTplDtl", cutPriceCnfTplDtl);
				}else if("2".equals(cutPriceCnfTplDtl.getRateType())) {
					m.addObject("scCutPriceCnfTplDtl", cutPriceCnfTplDtl);
				}else {
					cutPriceCnfTplDtls.add(cutPriceCnfTplDtl);
				}
			}
			Collections.sort(cutPriceCnfTplDtls, new Comparator<CutPriceCnfTplDtl>() {
				@Override
				public int compare(CutPriceCnfTplDtl o1, CutPriceCnfTplDtl o2) {
					if(o1.getEndAmount().compareTo(o2.getEndAmount()) > 0) {
						return 1;
					}
					if(o1.getEndAmount().compareTo(o2.getEndAmount()) == 0) {
						return 0;
					}
					return -1;
				}
			});
			m.addObject("flag", request.getParameter("flag"));
			m.addObject("cutPriceCnfTpl", cutPriceCnfTpl);
			m.addObject("oneCutPriceCnfTplDtl", cutPriceCnfTplDtls.get(0));
			m.addObject("twoCutPriceCnfTplDtl", cutPriceCnfTplDtls.get(1));
			m.addObject("threeCutPriceCnfTplDtl", cutPriceCnfTplDtls.get(2));
			m.addObject("fourCutPriceCnfTplDtl", cutPriceCnfTplDtls.get(3));
			m.addObject("fiveCutPriceCnfTplDtl", cutPriceCnfTplDtls.get(4));
			m.addObject("sixCutPriceCnfTplDtl", cutPriceCnfTplDtls.get(5));
			m.addObject("sevenCutPriceCnfTplDtl", cutPriceCnfTplDtls.get(6));
			m.addObject("beginPrice", cutPriceCnfTpl.getBeginPrice().setScale(2, BigDecimal.ROUND_DOWN));
			m.addObject("endPrice", cutPriceCnfTpl.getEndPrice().setScale(2, BigDecimal.ROUND_DOWN));
		}
		return m;
	}
	
	/**
	 * 
	 * @Title showCutPriceCnfTplResult   
	 * @Description TODO(砍价方案模版计算)   
	 * @author pengl
	 * @date 2018年6月8日 上午10:24:18
	 */
	@ResponseBody
	@RequestMapping("/cutPriceProduct/showCutPriceCnfTplResult.shtml")
	public Map<String, Object> showCutPriceCnfTplResult(HttpServletRequest request, @RequestParam HashMap<String, String> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			String beginPriceStr = paramMap.get("beginPrice");
			String endPriceStr = paramMap.get("endPrice");
			if(!StringUtil.isEmpty(beginPriceStr) && !StringUtil.isEmpty(endPriceStr)) {
				BigDecimal beginPrice = new BigDecimal(beginPriceStr);
				BigDecimal endPrice = new BigDecimal(endPriceStr);
				BigDecimal averagePrice = (beginPrice.add(endPrice)).divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP);
				Map<String, CutPriceCnfDtl> cutPriceCnfDtlMap = new HashMap<String, CutPriceCnfDtl>();
				CutPriceCnfDtl oneCutPriceCnfDtl = new CutPriceCnfDtl();
				if(!StringUtil.isEmpty(paramMap.get("oneBeginAmount"))) {
					oneCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("oneBeginAmount")));
				}else {
					oneCutPriceCnfDtl.setBeginAmount(new BigDecimal(0));
				}
				oneCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("oneEndAmount")));
				oneCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("oneBeginRate")));
				oneCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("oneEndRate")));
				cutPriceCnfDtlMap.put("oneCutPriceCnfDtl", oneCutPriceCnfDtl);
				CutPriceCnfDtl twoCutPriceCnfDtl = new CutPriceCnfDtl();
				twoCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("oneEndAmount")));
				twoCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("twoEndAmount")));
				twoCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("twoBeginRate")));
				twoCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("twoEndRate")));
				cutPriceCnfDtlMap.put("twoCutPriceCnfDtl", twoCutPriceCnfDtl);
				CutPriceCnfDtl threeCutPriceCnfDtl = new CutPriceCnfDtl();
				threeCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("twoEndAmount")));
				threeCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("threeEndAmount")));
				threeCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("threeBeginRate")));
				threeCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("threeEndRate")));
				cutPriceCnfDtlMap.put("threeCutPriceCnfDtl", threeCutPriceCnfDtl);
				CutPriceCnfDtl fourCutPriceCnfDtl = new CutPriceCnfDtl();
				fourCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("threeEndAmount")));
				fourCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("fourEndAmount")));
				fourCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("fourBeginRate")));
				fourCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("fourEndRate")));
				cutPriceCnfDtlMap.put("fourCutPriceCnfDtl", fourCutPriceCnfDtl);
				CutPriceCnfDtl fiveCutPriceCnfDtl = new CutPriceCnfDtl();
				fiveCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("fourEndAmount")));
				fiveCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("fiveEndAmount")));
				fiveCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("fiveBeginRate")));
				fiveCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("fiveEndRate")));
				cutPriceCnfDtlMap.put("fiveCutPriceCnfDtl", fiveCutPriceCnfDtl);
				CutPriceCnfDtl sixCutPriceCnfDtl = new CutPriceCnfDtl();
				sixCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("fiveEndAmount")));
				sixCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("sixEndAmount")));
				sixCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("sixBeginRate")));
				sixCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("sixEndRate")));
				cutPriceCnfDtlMap.put("sixCutPriceCnfDtl", sixCutPriceCnfDtl);
				CutPriceCnfDtl sevenCutPriceCnfDtl = new CutPriceCnfDtl();
				sevenCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("sixEndAmount")));
				sevenCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("sevenEndAmount")));
				sevenCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("sevenBeginRate")));
				sevenCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("sevenEndRate")));
				cutPriceCnfDtlMap.put("sevenCutPriceCnfDtl", sevenCutPriceCnfDtl);
				CutPriceCnfDtl jcCutPriceCnfDtl = new CutPriceCnfDtl();
				jcCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("jcBeginRate")));
				jcCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("jcEndRate")));
				cutPriceCnfDtlMap.put("jcCutPriceCnfDtl", jcCutPriceCnfDtl);
				String flagBegin = "begin";
				float amountMin = averagePrice.multiply(new BigDecimal(paramMap.get("scBeginRate")).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)).floatValue();
				String flagEnd = "end";
				float amountMax = averagePrice.multiply(new BigDecimal(paramMap.get("scEndRate")).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)).floatValue();
				Map<String, Object> mapNum = new HashMap<String, Object>();
				mapNum.put("daySum", 1);
				mapNum.put("oneNum", 0);
				mapNum.put("twoNum", 0);
				mapNum.put("threeNum", 0);
				mapNum.put("fourNum", 0);
				mapNum.put("fiveNum", 0);
				mapNum.put("sixNum", 0);
				mapNum.put("sevenNum", 0);
				Map<String, Object> mapMin = this.kjNum(averagePrice, cutPriceCnfDtlMap, amountMax, flagEnd, mapNum);
				Map<String, Object> mapMax = this.kjNum(averagePrice, cutPriceCnfDtlMap, amountMin, flagBegin, mapNum);
				resMap.put("mapMin", mapMin);
				resMap.put("mapMax", mapMax);
			}
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
	 * @Title addOrUpdateCutPriceCnfTpl   
	 * @Description TODO(新增或修改砍价方案模版)   
	 * @author pengl
	 * @date 2018年6月8日 上午11:38:57
	 */
	@RequestMapping("/cutPriceProduct/addOrUpdateCutPriceCnfTpl.shtml")
	public ModelAndView addOrUpdateCutPriceCnfTpl(HttpServletRequest request, @RequestParam HashMap<String, String> paramMap) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "";
		String msg = "";
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			Integer staffId = Integer.valueOf(staffBean.getStaffID());
			Date date = new Date();
			Map<String, CutPriceCnfDtl> cutPriceCnfDtlMap = new HashMap<String, CutPriceCnfDtl>();
			List<CutPriceCnfTplDtl> cutPriceCnfTplDtlList = new ArrayList<CutPriceCnfTplDtl>();
			
			//砍价方案模版明细
			//首次
			CutPriceCnfTplDtl scCutPriceCnfTplDtl = new CutPriceCnfTplDtl();
			scCutPriceCnfTplDtl.setBeginRate(new BigDecimal(paramMap.get("scBeginRate")));
			scCutPriceCnfTplDtl.setEndRate(new BigDecimal(paramMap.get("scEndRate")));
			
			//计算
			CutPriceCnfDtl oneCutPriceCnfDtl = new CutPriceCnfDtl();
			if(!StringUtil.isEmpty(paramMap.get("oneBeginAmount"))) {
				oneCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("oneBeginAmount")));
			}else {
				oneCutPriceCnfDtl.setBeginAmount(new BigDecimal(0));
			}
			oneCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("oneEndAmount")));
			oneCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("oneBeginRate")));
			oneCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("oneEndRate")));
			cutPriceCnfDtlMap.put("oneCutPriceCnfDtl", oneCutPriceCnfDtl);
			CutPriceCnfDtl twoCutPriceCnfDtl = new CutPriceCnfDtl();
			twoCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("oneEndAmount")));
			twoCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("twoEndAmount")));
			twoCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("twoBeginRate")));
			twoCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("twoEndRate")));
			cutPriceCnfDtlMap.put("twoCutPriceCnfDtl", twoCutPriceCnfDtl);
			CutPriceCnfDtl threeCutPriceCnfDtl = new CutPriceCnfDtl();
			threeCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("twoEndAmount")));
			threeCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("threeEndAmount")));
			threeCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("threeBeginRate")));
			threeCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("threeEndRate")));
			cutPriceCnfDtlMap.put("threeCutPriceCnfDtl", threeCutPriceCnfDtl);
			CutPriceCnfDtl fourCutPriceCnfDtl = new CutPriceCnfDtl();
			fourCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("threeEndAmount")));
			fourCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("fourEndAmount")));
			fourCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("fourBeginRate")));
			fourCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("fourEndRate")));
			cutPriceCnfDtlMap.put("fourCutPriceCnfDtl", fourCutPriceCnfDtl);
			CutPriceCnfDtl fiveCutPriceCnfDtl = new CutPriceCnfDtl();
			fiveCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("fourEndAmount")));
			fiveCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("fiveEndAmount")));
			fiveCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("fiveBeginRate")));
			fiveCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("fiveEndRate")));
			cutPriceCnfDtlMap.put("fiveCutPriceCnfDtl", fiveCutPriceCnfDtl);
			CutPriceCnfDtl sixCutPriceCnfDtl = new CutPriceCnfDtl();
			sixCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("fiveEndAmount")));
			sixCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("sixEndAmount")));
			sixCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("sixBeginRate")));
			sixCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("sixEndRate")));
			cutPriceCnfDtlMap.put("sixCutPriceCnfDtl", sixCutPriceCnfDtl);
			CutPriceCnfDtl sevenCutPriceCnfDtl = new CutPriceCnfDtl();
			sevenCutPriceCnfDtl.setBeginAmount(new BigDecimal(paramMap.get("sixEndAmount")));
			sevenCutPriceCnfDtl.setEndAmount(new BigDecimal(paramMap.get("sevenEndAmount")));
			sevenCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("sevenBeginRate")));
			sevenCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("sevenEndRate")));
			cutPriceCnfDtlMap.put("sevenCutPriceCnfDtl", sevenCutPriceCnfDtl);
			CutPriceCnfDtl jcCutPriceCnfDtl = new CutPriceCnfDtl();
			jcCutPriceCnfDtl.setBeginRate(new BigDecimal(paramMap.get("jcBeginRate")));
			jcCutPriceCnfDtl.setEndRate(new BigDecimal(paramMap.get("jcEndRate")));
			cutPriceCnfDtlMap.put("jcCutPriceCnfDtl", jcCutPriceCnfDtl);
			
			//金额区间
			CutPriceCnfTplDtl oneCutPriceCnfTplDtl = new CutPriceCnfTplDtl();
			if(!StringUtil.isEmpty(paramMap.get("oneBeginAmount"))) {
				oneCutPriceCnfTplDtl.setBeginAmount(new BigDecimal(paramMap.get("oneBeginAmount")));
			}else {
				oneCutPriceCnfTplDtl.setBeginAmount(new BigDecimal(0));
			}
			oneCutPriceCnfTplDtl.setEndAmount(new BigDecimal(paramMap.get("oneEndAmount")));
			oneCutPriceCnfTplDtl.setBeginRate(new BigDecimal(paramMap.get("oneBeginRate")));
			oneCutPriceCnfTplDtl.setEndRate(new BigDecimal(paramMap.get("oneEndRate")));
			
			CutPriceCnfTplDtl twoCutPriceCnfTplDtl = new CutPriceCnfTplDtl();
			twoCutPriceCnfTplDtl.setBeginAmount(new BigDecimal(paramMap.get("oneEndAmount")));
			twoCutPriceCnfTplDtl.setEndAmount(new BigDecimal(paramMap.get("twoEndAmount")));
			twoCutPriceCnfTplDtl.setBeginRate(new BigDecimal(paramMap.get("twoBeginRate")));
			twoCutPriceCnfTplDtl.setEndRate(new BigDecimal(paramMap.get("twoEndRate")));
			
			CutPriceCnfTplDtl threeCutPriceCnfTplDtl = new CutPriceCnfTplDtl();
			threeCutPriceCnfTplDtl.setBeginAmount(new BigDecimal(paramMap.get("twoEndAmount")));
			threeCutPriceCnfTplDtl.setEndAmount(new BigDecimal(paramMap.get("threeEndAmount")));
			threeCutPriceCnfTplDtl.setBeginRate(new BigDecimal(paramMap.get("threeBeginRate")));
			threeCutPriceCnfTplDtl.setEndRate(new BigDecimal(paramMap.get("threeEndRate")));
			
			CutPriceCnfTplDtl fourCutPriceCnfTplDtl = new CutPriceCnfTplDtl();
			fourCutPriceCnfTplDtl.setBeginAmount(new BigDecimal(paramMap.get("threeEndAmount")));
			fourCutPriceCnfTplDtl.setEndAmount(new BigDecimal(paramMap.get("fourEndAmount")));
			fourCutPriceCnfTplDtl.setBeginRate(new BigDecimal(paramMap.get("fourBeginRate")));
			fourCutPriceCnfTplDtl.setEndRate(new BigDecimal(paramMap.get("fourEndRate")));
			
			CutPriceCnfTplDtl fiveCutPriceCnfTplDtl = new CutPriceCnfTplDtl();
			fiveCutPriceCnfTplDtl.setBeginAmount(new BigDecimal(paramMap.get("fourEndAmount")));
			fiveCutPriceCnfTplDtl.setEndAmount(new BigDecimal(paramMap.get("fiveEndAmount")));
			fiveCutPriceCnfTplDtl.setBeginRate(new BigDecimal(paramMap.get("fiveBeginRate")));
			fiveCutPriceCnfTplDtl.setEndRate(new BigDecimal(paramMap.get("fiveEndRate")));
			
			CutPriceCnfTplDtl sixCutPriceCnfTplDtl = new CutPriceCnfTplDtl();
			sixCutPriceCnfTplDtl.setBeginAmount(new BigDecimal(paramMap.get("fiveEndAmount")));
			sixCutPriceCnfTplDtl.setEndAmount(new BigDecimal(paramMap.get("sixEndAmount")));
			sixCutPriceCnfTplDtl.setBeginRate(new BigDecimal(paramMap.get("sixBeginRate")));
			sixCutPriceCnfTplDtl.setEndRate(new BigDecimal(paramMap.get("sixEndRate")));
			
			CutPriceCnfTplDtl sevenCutPriceCnfTplDtl = new CutPriceCnfTplDtl();
			sevenCutPriceCnfTplDtl.setBeginAmount(new BigDecimal(paramMap.get("sixEndAmount")));
			sevenCutPriceCnfTplDtl.setEndAmount(new BigDecimal(paramMap.get("sevenEndAmount")));
			sevenCutPriceCnfTplDtl.setBeginRate(new BigDecimal(paramMap.get("sevenBeginRate")));
			sevenCutPriceCnfTplDtl.setEndRate(new BigDecimal(paramMap.get("sevenEndRate")));
			//基础
			CutPriceCnfTplDtl jcCutPriceCnfTplDtl = new CutPriceCnfTplDtl();
			jcCutPriceCnfTplDtl.setBeginRate(new BigDecimal(paramMap.get("jcBeginRate")));
			jcCutPriceCnfTplDtl.setEndRate(new BigDecimal(paramMap.get("jcEndRate")));
			
			BigDecimal beginPrice = new BigDecimal(paramMap.get("beginPrice"));
			BigDecimal endPrice = new BigDecimal(paramMap.get("endPrice"));
			BigDecimal averagePrice = (beginPrice.add(endPrice)).divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP);
			String flagBegin = "begin";
			float amountMin = averagePrice.multiply(new BigDecimal(paramMap.get("scBeginRate")).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)).floatValue();
			String flagEnd = "end";
			float amountMax = averagePrice.multiply(new BigDecimal(paramMap.get("scEndRate")).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)).floatValue();
			Map<String, Object> mapNum = new HashMap<String, Object>();
			mapNum.put("daySum", 1);
			mapNum.put("oneNum", 0);
			mapNum.put("twoNum", 0);
			mapNum.put("threeNum", 0);
			mapNum.put("fourNum", 0);
			mapNum.put("fiveNum", 0);
			mapNum.put("sixNum", 0);
			mapNum.put("sevenNum", 0);
			Map<String, Object> mapMin = this.kjNum(averagePrice, cutPriceCnfDtlMap, amountMax, flagEnd, mapNum);
			Map<String, Object> mapMax = this.kjNum(averagePrice, cutPriceCnfDtlMap, amountMin, flagBegin, mapNum);
			
			//砍价方案模版
			CutPriceCnfTpl cutPriceCnfTpl = new CutPriceCnfTpl();
			cutPriceCnfTpl.setName(paramMap.get("name"));
			cutPriceCnfTpl.setBeginPrice(new BigDecimal(paramMap.get("beginPrice")));
			cutPriceCnfTpl.setEndPrice(new BigDecimal(paramMap.get("endPrice")));
			cutPriceCnfTpl.setPredictMinTime((Integer)mapMin.get("daySum"));
			cutPriceCnfTpl.setPredictMaxTime((Integer)mapMax.get("daySum"));
			
			String cutPriceCnfTplId = paramMap.get("cutPriceCnfTplId");
			if(!StringUtil.isEmpty(cutPriceCnfTplId)) { //修改
				cutPriceCnfTpl.setId(Integer.parseInt(cutPriceCnfTplId));
				cutPriceCnfTpl.setUpdateBy(staffId);
				cutPriceCnfTpl.setUpdateDate(date);
				
				scCutPriceCnfTplDtl.setId(Integer.parseInt(paramMap.get("scCutPriceCnfTplDtlId")));
				scCutPriceCnfTplDtl.setUpdateBy(staffId);
				scCutPriceCnfTplDtl.setUpdateDate(date);
				cutPriceCnfTplDtlList.add(scCutPriceCnfTplDtl);
				
				oneCutPriceCnfTplDtl.setId(Integer.parseInt(paramMap.get("oneCutPriceCnfTplDtlId")));
				oneCutPriceCnfTplDtl.setUpdateBy(staffId);
				oneCutPriceCnfTplDtl.setUpdateDate(date);
				cutPriceCnfTplDtlList.add(oneCutPriceCnfTplDtl);
				
				twoCutPriceCnfTplDtl.setId(Integer.parseInt(paramMap.get("twoCutPriceCnfTplDtlId")));
				twoCutPriceCnfTplDtl.setUpdateBy(staffId);
				twoCutPriceCnfTplDtl.setUpdateDate(date);
				cutPriceCnfTplDtlList.add(twoCutPriceCnfTplDtl);
				
				threeCutPriceCnfTplDtl.setId(Integer.parseInt(paramMap.get("threeCutPriceCnfTplDtlId")));
				threeCutPriceCnfTplDtl.setUpdateBy(staffId);
				threeCutPriceCnfTplDtl.setUpdateDate(date);
				cutPriceCnfTplDtlList.add(threeCutPriceCnfTplDtl);
				
				fourCutPriceCnfTplDtl.setId(Integer.parseInt(paramMap.get("fourCutPriceCnfTplDtlId")));
				fourCutPriceCnfTplDtl.setUpdateBy(staffId);
				fourCutPriceCnfTplDtl.setUpdateDate(date);
				cutPriceCnfTplDtlList.add(fourCutPriceCnfTplDtl);
				
				fiveCutPriceCnfTplDtl.setId(Integer.parseInt(paramMap.get("fiveCutPriceCnfTplDtlId")));
				fiveCutPriceCnfTplDtl.setUpdateBy(staffId);
				fiveCutPriceCnfTplDtl.setUpdateDate(date);
				cutPriceCnfTplDtlList.add(fiveCutPriceCnfTplDtl);
				
				sixCutPriceCnfTplDtl.setId(Integer.parseInt(paramMap.get("sixCutPriceCnfTplDtlId")));
				sixCutPriceCnfTplDtl.setUpdateBy(staffId);
				sixCutPriceCnfTplDtl.setUpdateDate(date);
				cutPriceCnfTplDtlList.add(sixCutPriceCnfTplDtl);
				
				sevenCutPriceCnfTplDtl.setId(Integer.parseInt(paramMap.get("sevenCutPriceCnfTplDtlId")));
				sevenCutPriceCnfTplDtl.setUpdateBy(staffId);
				sevenCutPriceCnfTplDtl.setUpdateDate(date);
				cutPriceCnfTplDtlList.add(sevenCutPriceCnfTplDtl);
				
				jcCutPriceCnfTplDtl.setId(Integer.parseInt(paramMap.get("jcCutPriceCnfTplDtlId")));
				jcCutPriceCnfTplDtl.setUpdateBy(staffId);
				jcCutPriceCnfTplDtl.setUpdateDate(date);
				cutPriceCnfTplDtlList.add(jcCutPriceCnfTplDtl);
				
			}else { //新增
				cutPriceCnfTpl.setNeedCutToPrice(new BigDecimal(0));
				cutPriceCnfTpl.setMinCutToPrice(new BigDecimal(0));
				cutPriceCnfTpl.setCreateBy(staffId);
				cutPriceCnfTpl.setCreateDate(date);
				
				scCutPriceCnfTplDtl.setRateType("2");
				scCutPriceCnfTplDtl.setCreateBy(staffId);
				scCutPriceCnfTplDtl.setCreateDate(date);
				cutPriceCnfTplDtlList.add(scCutPriceCnfTplDtl);
				
				oneCutPriceCnfTplDtl.setRateType("3");
				oneCutPriceCnfTplDtl.setCreateBy(staffId);
				oneCutPriceCnfTplDtl.setCreateDate(date);
				cutPriceCnfTplDtlList.add(oneCutPriceCnfTplDtl);
				
				twoCutPriceCnfTplDtl.setRateType("3");
				twoCutPriceCnfTplDtl.setCreateBy(staffId);
				twoCutPriceCnfTplDtl.setCreateDate(date);
				cutPriceCnfTplDtlList.add(twoCutPriceCnfTplDtl);
				
				threeCutPriceCnfTplDtl.setRateType("3");
				threeCutPriceCnfTplDtl.setCreateBy(staffId);
				threeCutPriceCnfTplDtl.setCreateDate(date);
				cutPriceCnfTplDtlList.add(threeCutPriceCnfTplDtl);
				
				fourCutPriceCnfTplDtl.setRateType("3");
				fourCutPriceCnfTplDtl.setCreateBy(staffId);
				fourCutPriceCnfTplDtl.setCreateDate(date);
				cutPriceCnfTplDtlList.add(fourCutPriceCnfTplDtl);
				
				fiveCutPriceCnfTplDtl.setRateType("3");
				fiveCutPriceCnfTplDtl.setCreateBy(staffId);
				fiveCutPriceCnfTplDtl.setCreateDate(date);
				cutPriceCnfTplDtlList.add(fiveCutPriceCnfTplDtl);
				
				sixCutPriceCnfTplDtl.setRateType("3");
				sixCutPriceCnfTplDtl.setCreateBy(staffId);
				sixCutPriceCnfTplDtl.setCreateDate(date);
				cutPriceCnfTplDtlList.add(sixCutPriceCnfTplDtl);
				
				sevenCutPriceCnfTplDtl.setRateType("3");
				sevenCutPriceCnfTplDtl.setCreateBy(staffId);
				sevenCutPriceCnfTplDtl.setCreateDate(date);
				cutPriceCnfTplDtlList.add(sevenCutPriceCnfTplDtl);
				
				jcCutPriceCnfTplDtl.setRateType("1");
				jcCutPriceCnfTplDtl.setCreateBy(staffId);
				jcCutPriceCnfTplDtl.setCreateDate(date);
				cutPriceCnfTplDtlList.add(jcCutPriceCnfTplDtl);
				
			}
			cutPriceCnfTplService.addOrUpdateCutPriceCnfTpl(cutPriceCnfTpl, cutPriceCnfTplDtlList);
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
	 * @Title validatePrice   
	 * @Description TODO(验证商品价格区间不能重叠)   
	 * @author pengl
	 * @date 2018年6月19日 下午3:17:34
	 */
	@ResponseBody
	@RequestMapping("/cutPriceProduct/validatePrice.shtml")
	public Map<String, Object> validatePrice(HttpServletRequest request, @RequestParam HashMap<String, String> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			String beginPriceStr = paramMap.get("beginPrice");
			String endPriceStr = paramMap.get("endPrice");
			String cutPriceCnfTplId = paramMap.get("cutPriceCnfTplId");
			if(!StringUtil.isEmpty(beginPriceStr) && !StringUtil.isEmpty(endPriceStr)) {
				BigDecimal beginPrice = new BigDecimal(beginPriceStr);
				BigDecimal endPrice = new BigDecimal(endPriceStr);
				CutPriceCnfTplCustomExample cutPriceCnfTplCustomExample = new CutPriceCnfTplCustomExample();
				CutPriceCnfTplCustomExample.CutPriceCnfTplCriteria cutPriceCnfTplCustomCriteria = cutPriceCnfTplCustomExample.createCriteria();
				cutPriceCnfTplCustomCriteria.andDelFlagEqualTo("0");
				cutPriceCnfTplCustomExample.setOrderByClause(" t.id desc");
				List<CutPriceCnfTplCustom> cutPriceCnfTplCustomList = cutPriceCnfTplService.selectByCustomExample(cutPriceCnfTplCustomExample);
				for(CutPriceCnfTplCustom cutPriceCnfTplCustom : cutPriceCnfTplCustomList) {
					if(StringUtil.isEmpty(cutPriceCnfTplId) || !cutPriceCnfTplId.equals(cutPriceCnfTplCustom.getId().toString())) {
						if(beginPrice.compareTo(cutPriceCnfTplCustom.getBeginPrice()) > -1 && beginPrice.compareTo(cutPriceCnfTplCustom.getEndPrice()) == -1 ) {
							resMap.put("flag", "此商品价格区间重叠");
							break;
						}else if(endPrice.compareTo(cutPriceCnfTplCustom.getBeginPrice()) == 1 && endPrice.compareTo(cutPriceCnfTplCustom.getEndPrice()) == -1 ) {
							resMap.put("flag", "此商品价格区间重叠");
							break;
						}else if(beginPrice.compareTo(cutPriceCnfTplCustom.getBeginPrice()) == -1 && endPrice.compareTo(cutPriceCnfTplCustom.getEndPrice()) == 1 ) {
							resMap.put("flag", "此商品价格区间重叠");
							break;
						}
					}
				}
			}
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
	 * @Title cutPriceCnfTplCountPrice   
	 * @Description TODO(模版计算金额)   
	 * @author pengl
	 * @date 2018年8月7日 上午10:44:59
	 */
	@ResponseBody
	@RequestMapping("/cutPriceProduct/cutPriceCnfTplCountPrice.shtml")
	public Map<String, Object> cutPriceCnfTplCountPrice(HttpServletRequest request, @RequestParam HashMap<String, String> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			String beginPriceStr = paramMap.get("beginPrice");
			String endPriceStr = paramMap.get("endPrice");
			String priceStr = paramMap.get("price");
			if(!StringUtil.isEmpty(beginPriceStr) && !StringUtil.isEmpty(endPriceStr) && !StringUtil.isEmpty(priceStr)) {
				BigDecimal beginPrice = new BigDecimal(beginPriceStr);
				BigDecimal endPrice = new BigDecimal(endPriceStr);
				BigDecimal averagePrice = (beginPrice.add(endPrice)).divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP);
				BigDecimal price = averagePrice.multiply(new BigDecimal(priceStr)).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
				resMap.put("price", price);
			}
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
	 * @Title cutPriceCnfCountPrice   
	 * @Description TODO(方案计算金额)   
	 * @author pengl
	 * @date 2018年8月7日 上午11:26:39
	 */
	@ResponseBody
	@RequestMapping("/cutPriceProduct/cutPriceCnfCountPrice.shtml")
	public Map<String, Object> cutPriceCnfCountPrice(HttpServletRequest request, @RequestParam HashMap<String, String> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = null;
		try {
			String priceStr = paramMap.get("price");
			String singleProductActivityId = paramMap.get("singleProductActivityId");
			if(!StringUtil.isEmpty(singleProductActivityId) && !StringUtil.isEmpty(priceStr) ) {
				SingleProductActivityCustomExample singleProductActivityCustomExample = new SingleProductActivityCustomExample();
				SingleProductActivityCustomExample.SingleProductActivityCustomCriteria singleProductActivityCustomCriteria = singleProductActivityCustomExample.createCriteria();
				singleProductActivityCustomCriteria.andDelFlagEqualTo("0")
					.andIdEqualTo(Integer.parseInt(singleProductActivityId));
				List<SingleProductActivityCustom> singleProductActivityCustomList = singleProductActivityService.selectByCustomExampl(singleProductActivityCustomExample);
				BigDecimal tagPriceMax =  singleProductActivityCustomList.get(0).getTagPriceMax(); //最大吊牌价
				BigDecimal price = tagPriceMax.multiply(new BigDecimal(priceStr)).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
				resMap.put("price", price);
			}
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
	
	
}
