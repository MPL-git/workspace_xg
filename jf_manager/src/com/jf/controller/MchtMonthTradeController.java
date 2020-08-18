package com.jf.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.constant.Const;
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.MchtMonthTrade;
import com.jf.entity.MchtMonthTradeCustom;
import com.jf.entity.MchtMonthTradeCustomExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.StateCode;
import com.jf.entity.SysStaffRole;
import com.jf.entity.SysStaffRoleExample;
import com.jf.service.MchtMonthTradeService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductTypeService;
import com.jf.service.SysStaffRoleService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class MchtMonthTradeController extends BaseController{

	@Autowired
	private MchtMonthTradeService mchtMonthTradeService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Autowired
	private ProductBrandService productBrandService;
	
	@Autowired
	private SysStaffRoleService sysStaffRoleService;
	
	/**
	 * 
	 * @Title mchtMonthTradeManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月17日 下午7:32:58
	 */
	@RequestMapping("/mchtMonthTrade/mchtMonthTradeManager.shtml")
	public ModelAndView mchtMonthTradeManager(HttpServletRequest request, String statusPage) {
		ModelAndView m = new ModelAndView();
		if(!StringUtil.isEmpty(statusPage) && "1".equals(statusPage)) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String year = sdf.format(date).substring(0, 4);
			m.addObject("year", year);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat simple = new SimpleDateFormat("MM");
			String month = simple.format(c.getTime());
			m.addObject("month", month);
			
			ProductTypeExample productTypeExample = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
			productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
			List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
			m.addObject("productTypeList", productTypeList); //1级类目
			 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				 String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				  if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
			
			String staffID = this.getSessionStaffBean(request).getStaffID();
			SysStaffRoleExample staffRoleExample = new SysStaffRoleExample();
			staffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(staffID)).andRoleIdEqualTo(Const.ROLE_ID_99);
			List<SysStaffRole> staffRoleList = sysStaffRoleService.selectByExample(staffRoleExample);
			boolean managerFlag = false;
			if(!CollectionUtils.isEmpty(staffRoleList) ) {
				managerFlag = true;
			}
			m.addObject("managerFlag", managerFlag); //管理权限
			m.addObject("isCwOrgStatus", isCwOrgStatus);
			m.setViewName("/mchtMonthTrade/mchtMonthTradeList");
		}else if(!StringUtil.isEmpty(statusPage) && "2".equals(statusPage)) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String yearFlag = sdf.format(date).substring(0, 4);
			m.addObject("yearFlag", yearFlag);
			m.addObject("mchtId", request.getParameter("mchtId"));
			m.setViewName("/mchtMonthTrade/mchtMonthTradeChildList");
		}
		return m;
	}
	
	/**
	 * 
	 * @Title mchtMonthTradeList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月17日 下午7:33:01
	 */
	@ResponseBody
	@RequestMapping("/mchtMonthTrade/mchtMonthTradeList.shtml")
	public Map<String, Object> mchtMonthTradeList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MchtMonthTradeCustom> dataList = null;
		Integer totalCount = 0;
		try {
			MchtMonthTradeCustomExample mchtMonthTradeCustomExample = new MchtMonthTradeCustomExample();
			MchtMonthTradeCustomExample.MchtMonthTradeCustomCriteria mchtMonthTradeCustomCriteria = mchtMonthTradeCustomExample.createCriteria();
			mchtMonthTradeCustomCriteria.andDelFlagEqualTo("0");
			String yearMonthDate = null;
			if(!StringUtil.isEmpty(request.getParameter("yearFlag"))) {
				mchtMonthTradeCustomCriteria.andTradeMonthLike("%"+request.getParameter("yearFlag")+"%");
			}else {
				if(!StringUtil.isEmpty(request.getParameter("year")) && !StringUtil.isEmpty(request.getParameter("month"))) {
					yearMonthDate = request.getParameter("year")+ "-" + request.getParameter("month");
				}else {
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String year = sdf.format(date).substring(0, 4);
					Calendar c = Calendar.getInstance();
					c.add(Calendar.MONTH, -1);
					SimpleDateFormat simple = new SimpleDateFormat("MM");
					String month = simple.format(c.getTime());
					yearMonthDate = year + "-" + month;
				}
				mchtMonthTradeCustomCriteria.andTradeMonthEqualTo(yearMonthDate);
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtId"))) {
				mchtMonthTradeCustomCriteria.andMchtIdEqualTo(Integer.parseInt(request.getParameter("mchtId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtMonthTradeCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				mchtMonthTradeCustomCriteria.andMchtNameLike(request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtMonthTradeCustomCriteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				mchtMonthTradeCustomCriteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
			}
			mchtMonthTradeCustomExample.setLimitSize(page.getLimitSize());
			mchtMonthTradeCustomExample.setLimitStart(page.getLimitStart());
			mchtMonthTradeCustomExample.setOrderByClause(" mmt.mcht_id desc, mmt.trade_month desc");
			dataList = mchtMonthTradeService.selectByCustomExample(mchtMonthTradeCustomExample);
			totalCount = mchtMonthTradeService.countByCustomExample(mchtMonthTradeCustomExample);
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title exportMchtMonthTradeList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月17日 下午7:57:56
	 */
	@RequestMapping("/mchtMonthTrade/exportMchtMonthTradeList.shtml")
	public void exportMchtMonthTradeList(HttpServletRequest request, HttpServletResponse response) {
		try {
			MchtMonthTradeCustomExample mchtMonthTradeCustomExample = new MchtMonthTradeCustomExample();
			MchtMonthTradeCustomExample.MchtMonthTradeCustomCriteria mchtMonthTradeCustomCriteria = mchtMonthTradeCustomExample.createCriteria();
			mchtMonthTradeCustomCriteria.andDelFlagEqualTo("0");
			String yearMonthDate = null;
			if(!StringUtil.isEmpty(request.getParameter("yearFlag"))) {
				mchtMonthTradeCustomCriteria.andTradeMonthLike("%"+request.getParameter("yearFlag")+"%");
			}else {
				if(!StringUtil.isEmpty(request.getParameter("year")) && !StringUtil.isEmpty(request.getParameter("month"))) {
					yearMonthDate = request.getParameter("year")+ "-" + request.getParameter("month");
				}else {
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String year = sdf.format(date).substring(0, 4);
					Calendar c = Calendar.getInstance();
					c.add(Calendar.MONTH, -1);
					SimpleDateFormat simple = new SimpleDateFormat("MM");
					String month = simple.format(c.getTime());
					yearMonthDate = year + "-" + month;
				}
				mchtMonthTradeCustomCriteria.andTradeMonthEqualTo(yearMonthDate);
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtId"))) {
				mchtMonthTradeCustomCriteria.andMchtIdEqualTo(Integer.parseInt(request.getParameter("mchtId")));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtMonthTradeCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				mchtMonthTradeCustomCriteria.andMchtNameLike(request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtMonthTradeCustomCriteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				mchtMonthTradeCustomCriteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
			}
			mchtMonthTradeCustomExample.setOrderByClause(" mmt.mcht_id desc, mmt.trade_month desc");
			List<MchtMonthTradeCustom> mchtMonthTradeCustomList = mchtMonthTradeService.selectByCustomExample(mchtMonthTradeCustomExample);
			
			String[] titles = { "月份","商家序号","商家公司名称","店铺名称","初期结欠","本期应结货款","保证金现缴","本期付款","应扣违规",
					"保证金往来","折扣调差","期末结欠","本期订单实收金额","已收保证金","本期增减","保证金余额"};
			ExcelBean excelBean = new ExcelBean("商家月往来报表.xls", "商家月往来报表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtMonthTradeCustom mchtMonthTradeCustom : mchtMonthTradeCustomList){
				String[] data = {
					mchtMonthTradeCustom.getTradeMonth()==null?"":mchtMonthTradeCustom.getTradeMonth(),
					mchtMonthTradeCustom.getMchtCode()==null?"":mchtMonthTradeCustom.getMchtCode()+"-"+mchtMonthTradeCustom.getMchtId(),
					mchtMonthTradeCustom.getCompanyName()==null?"":mchtMonthTradeCustom.getCompanyName(),
					mchtMonthTradeCustom.getShopName()==null?"":mchtMonthTradeCustom.getShopName(),
					mchtMonthTradeCustom.getBeginUnpay()==null?"":String.valueOf(mchtMonthTradeCustom.getBeginUnpay()),
					mchtMonthTradeCustom.getCurrentMonthSettleAmount()==null?"":String.valueOf(mchtMonthTradeCustom.getCurrentMonthSettleAmount()),
					mchtMonthTradeCustom.getCurrentDepositAmount()==null?"":String.valueOf(mchtMonthTradeCustom.getCurrentDepositAmount()),
					mchtMonthTradeCustom.getCurrentPayAmount()==null?"":String.valueOf(mchtMonthTradeCustom.getCurrentPayAmount()),
					mchtMonthTradeCustom.getViolateNeedDeduct()==null?"":String.valueOf(mchtMonthTradeCustom.getViolateNeedDeduct()),
					mchtMonthTradeCustom.getDepositDtl()==null?"":String.valueOf(mchtMonthTradeCustom.getDepositDtl()),
					mchtMonthTradeCustom.getDiscount()==null?"":String.valueOf(mchtMonthTradeCustom.getDiscount()),
					mchtMonthTradeCustom.getEndUnpay()==null?"":String.valueOf(mchtMonthTradeCustom.getEndUnpay()),
					mchtMonthTradeCustom.getTotalOrderPayAmount()==null?"":String.valueOf(mchtMonthTradeCustom.getTotalOrderPayAmount()),
					mchtMonthTradeCustom.getCollectDepositAmount()==null?"":String.valueOf(mchtMonthTradeCustom.getCollectDepositAmount()),
					mchtMonthTradeCustom.getCurrentChangeAmount()==null?"":String.valueOf(mchtMonthTradeCustom.getCurrentChangeAmount()),
					mchtMonthTradeCustom.getDepositBalance()==null?"":String.valueOf(mchtMonthTradeCustom.getDepositBalance())
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
	 * @MethodName: updateDiscountManager
	 * @Description: (折扣调差)
	 * @author Pengl
	 * @date 2019年3月18日 上午10:03:44
	 */
	@RequestMapping("/mchtMonthTrade/updateDiscountManager.shtml")
	public ModelAndView updateDiscountManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mchtMonthTrade/updateDiscount");
		m.addObject("mchtMonthTradeId", request.getAttribute("mchtMonthTradeId"));
		m.addObject("endUnpay", request.getAttribute("endUnpay"));
		m.addObject("discount", request.getAttribute("discount"));
		return m;
	}
	
	/**
	 * 
	 * @MethodName: updateDiscount
	 * @Description: (折扣调差)
	 * @author Pengl
	 * @date 2019年3月18日 上午10:04:19
	 */
	@RequestMapping("/mchtMonthTrade/updateDiscount.shtml") 
	public ModelAndView updateDiscount(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/success/success");
		String code = null;
		String msg = null;
    	try {
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mchtMonthTradeId = request.getParameter("mchtMonthTradeId");
			String discount = request.getParameter("discount");
			String discountOld = request.getParameter("discountOld");
			String endUnpay = request.getParameter("endUnpay");
			MchtMonthTrade mchtMonthTrade = new MchtMonthTrade();
			mchtMonthTrade.setId(Integer.parseInt(mchtMonthTradeId));
			mchtMonthTrade.setDiscount(new BigDecimal(discount).add(new BigDecimal(discountOld)));
			mchtMonthTrade.setEndUnpay(new BigDecimal(endUnpay));
			mchtMonthTrade.setUpdateBy(Integer.parseInt(staffID));
			mchtMonthTrade.setUpdateDate(new Date());
			mchtMonthTradeService.updateByPrimaryKeySelective(mchtMonthTrade);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
    	m.addObject(this.JSON_RESULT_CODE, code);
    	m.addObject(this.JSON_RESULT_MESSAGE, msg);
		return m;
	}
	
	
	
}
