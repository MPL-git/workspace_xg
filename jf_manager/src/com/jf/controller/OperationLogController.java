package com.jf.controller;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.MchtViewlogCustom;
import com.jf.entity.MchtViewlogCustomExample;
import com.jf.entity.OrderViewlogCustom;
import com.jf.entity.OrderViewlogCustomExample;
import com.jf.service.MchtViewlogService;
import com.jf.service.OrderViewlogService;
import com.jf.vo.Page;

@Controller
public class OperationLogController extends BaseController {
	
	private static final long serialVersionUID = 1L;
		
	@Resource
	private OrderViewlogService orderViewlogService;
	
	@Resource
	private MchtViewlogService mchtViewlogService;
	
	//订单详情日志
	@RequestMapping("/operationLog/details.shtml")
	public ModelAndView details(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/operationLog/details");
		return m;
	}
	
	
	@ResponseBody
	@RequestMapping("/operationlog/detailsdata.shtml")
	public Map<String, Object> detailsdata(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<OrderViewlogCustom> dataList = null;
		Integer totalCount = 0;
		try {
			OrderViewlogCustomExample orderViewlogCustomExample=new OrderViewlogCustomExample();
			OrderViewlogCustomExample.OrderViewlogCustomCriteria orderViewlogCustomcriteria=orderViewlogCustomExample.createCriteria();
			orderViewlogCustomcriteria.andDelFlagEqualTo("0");
			orderViewlogCustomExample.setOrderByClause("ov.create_date desc,"+" ov.create_by desc");
			if(!StringUtil.isEmpty(request.getParameter("createBy"))){	
				orderViewlogCustomcriteria.andCreateByEqualTo(Integer.valueOf(request.getParameter("createBy")));
			}
			if (!StringUtil.isEmpty(request.getParameter("staffName"))) {
				orderViewlogCustomcriteria.andstaffNameLikeTo(request.getParameter("staffName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("orderType"))) {
				String orderType=request.getParameter("orderType");
				if (orderType.equals("1")) {
					orderViewlogCustomcriteria.andOrderTypeEqualTo("1");
				}else {
					orderViewlogCustomcriteria.andOrderTypeEqualTo("2");
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("createDatestar")) ){
				orderViewlogCustomcriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDatestar")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ){
				orderViewlogCustomcriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				orderViewlogCustomcriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				orderViewlogCustomcriteria.andMchtNameLikeTo(request.getParameter("mchtName"));
			}
			totalCount=orderViewlogService.orderViewlogExamplecountByExample(orderViewlogCustomExample);
			orderViewlogCustomExample.setLimitStart(page.getLimitStart());
			orderViewlogCustomExample.setLimitSize(page.getLimitSize());
			dataList=orderViewlogService.orderViewlogCustomselectByExample(orderViewlogCustomExample);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	//商家基础资料日志
	@RequestMapping("/operationLog/mchtdetails.shtml")
	public ModelAndView mchtdetails(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/operationLog/mchtdetails");
		return m;
	}
    
	@ResponseBody
	@RequestMapping("operationlog/mchtdetailsdata.shtml")
	public Map<String, Object> mchtdetailsdata(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<MchtViewlogCustom> dataList = null;
		Integer totalCount = 0;
		try {
			MchtViewlogCustomExample mchtViewlogCustomExample=new MchtViewlogCustomExample();
			MchtViewlogCustomExample.MchtViewlogCustomCriteria mchtViewlogCustomCriteria=mchtViewlogCustomExample.createCriteria();
			mchtViewlogCustomCriteria.andDelFlagEqualTo("0");
			mchtViewlogCustomExample.setOrderByClause("mv.create_date desc,"+" mv.create_by desc");
			
			if (!StringUtil.isEmpty(request.getParameter("createBy"))) {
				mchtViewlogCustomCriteria.andCreateByEqualTo(Integer.valueOf(request.getParameter("createBy")));
			}
			if (!StringUtil.isEmpty(request.getParameter("staffName"))) {
				mchtViewlogCustomCriteria.andstaffNameLikeTo(request.getParameter("staffName"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtViewlogCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				mchtViewlogCustomCriteria.andMchtNameLikeTo(request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDatestar")) ){
				mchtViewlogCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDatestar")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ){
				mchtViewlogCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDateEnd")+" 23:59:59"));
			}
			totalCount=mchtViewlogService.orderViewlogExamplecountByExample(mchtViewlogCustomExample);
			mchtViewlogCustomExample.setLimitStart(page.getLimitStart());
			mchtViewlogCustomExample.setLimitSize(page.getLimitSize());
			dataList=mchtViewlogService.orderViewlogCustomselectByExample(mchtViewlogCustomExample);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
				
				
}
