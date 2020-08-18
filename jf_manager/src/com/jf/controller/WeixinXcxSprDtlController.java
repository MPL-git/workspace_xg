package com.jf.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.entity.StateCode;
import com.jf.entity.WeixinXcxSprChnl;
import com.jf.entity.WeixinXcxSprChnlExample;
import com.jf.entity.WeixinXcxSprDtl;
import com.jf.service.WeixinXcxSprChnlService;
import com.jf.service.WeixinXcxSprDtlService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class WeixinXcxSprDtlController extends BaseController {

	@Autowired
	private WeixinXcxSprDtlService weixinXcxSprDtlService;
	
	@Autowired
	private WeixinXcxSprChnlService weixinXcxSprChnlService;
	
	/**
	 * 
	 * @Title weixinXcxSprDtlManager   
	 * @Description TODO(小程序推广统计)   
	 * @author pengl
	 * @date 2018年11月6日 下午5:57:59
	 */
	@RequestMapping("/weixinXcxSprDtl/weixinXcxSprDtlManager.shtml")
	public ModelAndView weixinXcxSprDtlManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/weixinXcxSpr/weixinXcxSprDtl/getWeixinXcxSprDtlList");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		WeixinXcxSprChnlExample weixinXcxSprChnlExample = new WeixinXcxSprChnlExample();
		weixinXcxSprChnlExample.createCriteria().andDelFlagEqualTo("0");
		weixinXcxSprChnlExample.setOrderByClause(" id desc");
		List<WeixinXcxSprChnl> weixinXcxSprChnlList = weixinXcxSprChnlService.selectByExample(weixinXcxSprChnlExample);
		m.addObject("weixinXcxSprChnlList", weixinXcxSprChnlList);
		m.addObject("sprTypeList", DataDicUtil.getTableStatus("BU_WEIXIN_XCX_SPR_DTL", "SPR_TYPE"));
		m.addObject("beginDate", DateUtil.getMonthFirstDay());
		m.addObject("endDate", sdf.format(date));
		return m;
	}
	
	/**
	 * 
	 * @Title getWeixinXcxSprDtlList   
	 * @Description TODO(小程序推广统计)   
	 * @author pengl
	 * @date 2018年11月6日 下午6:24:35
	 */
	@ResponseBody
	@RequestMapping("/weixinXcxSprDtl/getWeixinXcxSprDtlList.shtml")
	public Map<String, Object> getWeixinXcxSprDtlList(HttpServletRequest request, Page page, Integer weixinXcxSprDtlId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String weixinXcxSprChnlId = request.getParameter("weixinXcxSprChnlId");
			String sprPlanName = request.getParameter("sprPlanName");
			String sprType = request.getParameter("sprType");
			String sprValue = request.getParameter("sprValue");
			String accessType = request.getParameter("accessType");
			String beginCreateDate = request.getParameter("beginCreateDate");
			String endCreateDate = request.getParameter("endCreateDate");
			String beginCountDate = request.getParameter("beginCountDate");
			String endCountDate = request.getParameter("endCountDate");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("isEffect", "1"); // 是否有效
			paramMap.put("weixinXcxSprDtlId", weixinXcxSprDtlId);
			paramMap.put("weixinXcxSprChnlId", weixinXcxSprChnlId);
			paramMap.put("sprPlanName", sprPlanName);
			paramMap.put("sprType", sprType);
			paramMap.put("sprValue", sprValue);
			paramMap.put("accessType", accessType);
			if(!StringUtil.isEmpty(beginCreateDate)) {
				paramMap.put("beginCreateDate", sdf.parse(beginCreateDate+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(endCreateDate)) {
				paramMap.put("endCreateDate", sdf.parse(endCreateDate+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(beginCountDate)) {
				paramMap.put("beginCountDate", sdf.parse(beginCountDate+" 00:00:00"));
			}else {
				paramMap.put("beginCountDate", sdf.parse(DateUtil.getMonthFirstDay()+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(endCountDate)) {
				paramMap.put("endCountDate", sdf.parse(endCountDate+" 23:59:59"));
			}else {
				paramMap.put("endCountDate", sdf.parse(DateUtil.formatDateByFormat(date, "yyyy-MM-dd")+" 23:59:59"));
			}
			paramMap.put("orderByClause", " t.id desc");
			paramMap.put("limitStart", page.getLimitStart());
			paramMap.put("limitSize", page.getLimitSize());
			dataList = weixinXcxSprDtlService.getWeixinXcxSprDtlList(paramMap);
			Map<String, Object> parMap = new HashMap<String, Object>();
			List<Integer> weixinXcxSprDtlIdList = new ArrayList<Integer>();
			for(Map<String, Object> map : dataList) {
				weixinXcxSprDtlIdList.add(Integer.parseInt(map.get("id").toString()));
			}
			parMap.put("weixinXcxSprDtlIdList", weixinXcxSprDtlIdList);
			parMap.put("beginCountDate", paramMap.get("beginCountDate"));
			parMap.put("endCountDate", paramMap.get("endCountDate"));
			parMap.put("accessType", accessType);
			List<Map<String, Object>> weixinXcxSprDtlMapList = weixinXcxSprDtlService.getCountSubOrderList(parMap);
			for(Map<String, Object> map : dataList) {
				String new_guest_combine_order_count = "0";
				String new_guest_consume_count = "0";
				String new_guest_pay_amount = "0";
				String old_guest_combine_order_count = "0";
				String old_guest_consume_count = "0";
				String old_guest_pay_amount = "0";
				String total_pay_amount = "0";
				for(Map<String, Object> m :  weixinXcxSprDtlMapList) {
					if(map.get("id").toString().equals(m.get("id").toString())) {
						new_guest_combine_order_count = m.get("new_guest_combine_order_count").toString();
						new_guest_consume_count = m.get("new_guest_consume_count").toString();
						new_guest_pay_amount = m.get("new_guest_pay_amount").toString();
						old_guest_combine_order_count = m.get("old_guest_combine_order_count").toString();
						old_guest_consume_count = m.get("old_guest_consume_count").toString();
						old_guest_pay_amount = m.get("old_guest_pay_amount").toString();
						total_pay_amount = m.get("total_pay_amount").toString();
					}
				}
				map.put("new_guest_combine_order_count", new_guest_combine_order_count);
				map.put("new_guest_consume_count", new_guest_consume_count);
				map.put("new_guest_pay_amount", new_guest_pay_amount);
				map.put("old_guest_combine_order_count", old_guest_combine_order_count);
				map.put("old_guest_consume_count", old_guest_consume_count);
				map.put("old_guest_pay_amount", old_guest_pay_amount);
				map.put("total_pay_amount", total_pay_amount);
			}
			totalCount = weixinXcxSprDtlService.countWeixinXcxSprDtlList(paramMap);
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
	 * @Title addWeixinXcxSprDtlManager   
	 * @Description TODO(添加链接)   
	 * @author pengl
	 * @date 2018年11月7日 上午9:22:41
	 */
	@RequestMapping("/weixinXcxSprDtl/addWeixinXcxSprDtlManager.shtml")
	public ModelAndView addWeixinXcxSprDtlManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/weixinXcxSpr/weixinXcxSprDtl/addWeixinXcxSprDtl");
		WeixinXcxSprChnlExample weixinXcxSprChnlExample = new WeixinXcxSprChnlExample();
		weixinXcxSprChnlExample.createCriteria().andDelFlagEqualTo("0");
		List<WeixinXcxSprChnl> weixinXcxSprChnlList = weixinXcxSprChnlService.selectByExample(weixinXcxSprChnlExample);
		m.addObject("weixinXcxSprChnlList", weixinXcxSprChnlList);
		m.addObject("sprTypeList", DataDicUtil.getTableStatus("BU_WEIXIN_XCX_SPR_DTL", "SPR_TYPE"));
		return m;
	}
	
	/**
	 * 
	 * @Title addWeixinXcxSprDtl   
	 * @Description TODO(添加链接)   
	 * @author pengl
	 * @date 2018年11月7日 上午9:46:25
	 */
	@RequestMapping("/weixinXcxSprDtl/addWeixinXcxSprDtl.shtml")
	public ModelAndView addWeixinXcxSprDtl(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/success/success");
		String code = null;
		String msg = "";
		try {
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String weixinXcxSprChnlId = request.getParameter("weixinXcxSprChnlId");
			String sprPlanName = request.getParameter("sprPlanName");
			String sprType = request.getParameter("sprType");
			String sprValue = request.getParameter("sprValue");
			String pic = request.getParameter("pic");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("staffID", staffID);
			paramMap.put("weixinXcxSprChnlId", weixinXcxSprChnlId);
			paramMap.put("sprPlanName", sprPlanName);
			paramMap.put("sprType", sprType);
			paramMap.put("sprValue", sprValue);
			paramMap.put("pic", pic);
			Map<String, String> returnMap = weixinXcxSprDtlService.addWeixinXcxSprDtl(paramMap);
			code = returnMap.get("code");
			msg = returnMap.get("msg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		m.addObject(this.JSON_RESULT_CODE, code);
		m.addObject(this.JSON_RESULT_MESSAGE, msg);
		return m;
	}
	
	/**
	 * 
	 * @Title delWeixinXcxSprDtl   
	 * @Description TODO(删除推广计划)   
	 * @author pengl
	 * @date 2018年11月7日 上午9:57:29
	 */
	@ResponseBody
	@RequestMapping("/weixinXcxSprDtl/delWeixinXcxSprDtl.shtml")
	public Map<String, Object> delWeixinXcxSprDtl(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "成功";
		String weixinXcxSprDtlId = request.getParameter("weixinXcxSprDtlId");
		try {
			if(!StringUtil.isEmpty(weixinXcxSprDtlId)) {
				Date date = new Date();
				Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
				WeixinXcxSprDtl weixinXcxSprDtl = new WeixinXcxSprDtl();
				weixinXcxSprDtl.setId(Integer.parseInt(weixinXcxSprDtlId));
				weixinXcxSprDtl.setIsEffect("0");
				weixinXcxSprDtl.setUpdateBy(staffID);
				weixinXcxSprDtl.setUpdateDate(date);
				weixinXcxSprDtlService.updateByPrimaryKeySelective(weixinXcxSprDtl);
			}else {
				code = "400";
				msg = "推广计划ID为空";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "500";
			msg = "系统错误";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	
	
	
}
