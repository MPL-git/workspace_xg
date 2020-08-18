package com.jf.controller;

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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.MchtMonthlyCollectionsCustom;
import com.jf.entity.MchtMonthlyCollectionsCustomExample;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.service.MchtMonthlyCollectionsService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductTypeService;
import com.jf.vo.Page;

@Controller
@SuppressWarnings("serial")
public class MchtMonthlyCollectionsController extends BaseController {

	@Autowired
	private MchtMonthlyCollectionsService mchtMonthlyCollectionsService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Autowired
	private ProductBrandService productBrandService;
	
	/**
	 * 
	 * @Title mchtMonthlyCollectionsManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年3月19日 下午1:53:24
	 */
	@RequestMapping("/mchtMonthlyCollections/mchtMonthlyCollectionsManager.shtml")
	public ModelAndView mchtMonthlyCollectionsManager(HttpServletRequest request, String statusPage) {
		ModelAndView m = new ModelAndView();
		if(!StringUtil.isEmpty(statusPage) && "1".equals(statusPage)) { //商家月结报表
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String year = sdf.format(date).substring(0, 4);
			m.addObject("year", year);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat simple = new SimpleDateFormat("MM");
			String month = simple.format(c.getTime());
			m.addObject("month", month);
			m.addObject("mchtStatusList", DataDicUtil.getTableStatus("BU_MCHT_INFO", "STATUS"));
			
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
			m.addObject("isCwOrgStatus", isCwOrgStatus);
			m.setViewName("/mchtMonthlyCollections/mchtEveryMonthCollectionsList");
		}
		return m;
	}
	
	/**
	 * 
	 * @Title getMchtEveryMonthCollectionsList   
	 * @Description TODO(商家月结报表)   
	 * @author pengl
	 * @date 2018年3月19日 下午5:33:32
	 */
	@ResponseBody
	@RequestMapping("/mchtMonthlyCollections/getMchtEveryMonthCollectionsList.shtml")
	public Map<String, Object> getMchtEveryMonthCollectionsList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MchtMonthlyCollectionsCustom> dataList = null;
		Integer totalCount = 0;
		try {
			MchtMonthlyCollectionsCustomExample mchtMonthlyCollectionsCustomExample = new MchtMonthlyCollectionsCustomExample();
			MchtMonthlyCollectionsCustomExample.MchtMonthlyCollectionsCustomCriteria mchtMonthlyCollectionsCustomCriteria = mchtMonthlyCollectionsCustomExample.createCriteria();
			mchtMonthlyCollectionsCustomCriteria.andDelFlagEqualTo("0");
			String yearMonthDate = null;
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
			mchtMonthlyCollectionsCustomCriteria.andCollectionDateEqualTo(yearMonthDate);
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtMonthlyCollectionsCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				mchtMonthlyCollectionsCustomCriteria.andNameLikeTo(request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtStatus"))) {
				mchtMonthlyCollectionsCustomCriteria.andMchtStatusEqualTo(request.getParameter("mchtStatus"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtMonthlyCollectionsCustomCriteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				mchtMonthlyCollectionsCustomCriteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
			}
			mchtMonthlyCollectionsCustomExample.setLimitStart(page.getLimitStart());
			mchtMonthlyCollectionsCustomExample.setLimitSize(page.getLimitSize());
			mchtMonthlyCollectionsCustomExample.setOrderByClause(" a.id desc");
			dataList = mchtMonthlyCollectionsService.selectByExample(mchtMonthlyCollectionsCustomExample);
			totalCount = mchtMonthlyCollectionsService.countByExample(mchtMonthlyCollectionsCustomExample);
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
	 * @Title mchtEveryMonthCollectionsListExcel   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月25日 上午11:44:25
	 */
	@RequestMapping("/mchtMonthlyCollections/mchtEveryMonthCollectionsListExcel.shtml")
	public void mchtEveryMonthCollectionsListExcel(HttpServletRequest request, HttpServletResponse response) {
		try {
			MchtMonthlyCollectionsCustomExample mchtMonthlyCollectionsCustomExample = new MchtMonthlyCollectionsCustomExample();
			MchtMonthlyCollectionsCustomExample.MchtMonthlyCollectionsCustomCriteria mchtMonthlyCollectionsCustomCriteria = mchtMonthlyCollectionsCustomExample.createCriteria();
			mchtMonthlyCollectionsCustomCriteria.andDelFlagEqualTo("0");
			String yearMonthDate = null;
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
			mchtMonthlyCollectionsCustomCriteria.andCollectionDateEqualTo(yearMonthDate);
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtMonthlyCollectionsCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				mchtMonthlyCollectionsCustomCriteria.andNameLikeTo(request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtStatus"))) {
				mchtMonthlyCollectionsCustomCriteria.andMchtStatusEqualTo(request.getParameter("mchtStatus"));
			}
			if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtMonthlyCollectionsCustomCriteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				mchtMonthlyCollectionsCustomCriteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
			}
			mchtMonthlyCollectionsCustomExample.setOrderByClause(" a.id desc");
			List<MchtMonthlyCollectionsCustom> mchtMonthlyCollectionsCustomList = mchtMonthlyCollectionsService.selectByExample(mchtMonthlyCollectionsCustomExample);
			
			String[] titles = {"月份","商家序号","公司名称","店铺名称","初期结欠","本期商家应结款","本期付款","折扣","抵缴保证金","期末结欠","订单客户实付金额","应收保证金","已收保证金","合作状态"};
			ExcelBean excelBean = new ExcelBean("商家月结报表.xls", "商家月结报表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtMonthlyCollectionsCustom mchtMonthlyCollectionsCustom : mchtMonthlyCollectionsCustomList) {
				String[] data = {
						mchtMonthlyCollectionsCustom.getCollectionDate()==null?"":mchtMonthlyCollectionsCustom.getCollectionDate(),
						mchtMonthlyCollectionsCustom.getMchtCode()==null?"":mchtMonthlyCollectionsCustom.getMchtCode()+"-"+mchtMonthlyCollectionsCustom.getMchtId(),
						mchtMonthlyCollectionsCustom.getMchtCompanyName()==null?"":mchtMonthlyCollectionsCustom.getMchtCompanyName(),
						mchtMonthlyCollectionsCustom.getMchtShopName()==null?"":mchtMonthlyCollectionsCustom.getMchtShopName(),
						mchtMonthlyCollectionsCustom.getBeginUnpay()==null?"0.00":mchtMonthlyCollectionsCustom.getBeginUnpay().toString(),
						mchtMonthlyCollectionsCustom.getSettleAmount().subtract(mchtMonthlyCollectionsCustom.getReturnOrderAmount()).toString(),	
						mchtMonthlyCollectionsCustom.getPayAmount()==null?"0.00":mchtMonthlyCollectionsCustom.getPayAmount().toString(),
						mchtMonthlyCollectionsCustom.getDiscount()==null?"0.00":mchtMonthlyCollectionsCustom.getDiscount().toString(),
						mchtMonthlyCollectionsCustom.getDeductionDepositTotal()==null?"0.00":mchtMonthlyCollectionsCustom.getDeductionDepositTotal().toString(),
						mchtMonthlyCollectionsCustom.getEndUnpay()==null?"0.00":mchtMonthlyCollectionsCustom.getEndUnpay().toString(),
						mchtMonthlyCollectionsCustom.getOrderAmount()==null?"0.00":mchtMonthlyCollectionsCustom.getOrderAmount().toString(),
						mchtMonthlyCollectionsCustom.getTotalAmt()==null?"0.00":mchtMonthlyCollectionsCustom.getTotalAmt().toString(),
						mchtMonthlyCollectionsCustom.getPayAmt()==null?"0.00":mchtMonthlyCollectionsCustom.getPayAmt().toString(),
						mchtMonthlyCollectionsCustom.getMchtStatusDesc()==null?"":mchtMonthlyCollectionsCustom.getMchtStatusDesc()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
