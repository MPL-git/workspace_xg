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
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.MchtSettleCompareCustom;
import com.jf.entity.MchtSettleCompareCustomExample;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.service.MchtSettleCompareService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductTypeService;
import com.jf.vo.Page;

@Controller
@SuppressWarnings("serial")
public class MchtSettleCompareController extends BaseController {

	@Autowired
	private MchtSettleCompareService mchtSettleCompareService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Autowired
	private ProductBrandService productBrandService;
	/**
	 * 
	 * @Title mchtSettleCompareManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年3月20日 下午6:18:30
	 */
	@RequestMapping("/mchtSettleCompare/mchtSettleCompareManager.shtml")
	public ModelAndView mchtSettleCompareManager(HttpServletRequest request, String statusPage) {
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
			m.addObject("isCwOrgStatus", isCwOrgStatus);
			m.setViewName("/mchtSettleCompare/mchtSettleCompareList");
		}
		return m;
	}
	
	/**
	 * 
	 * @Title getMchtSettleCompareList   
	 * @Description TODO(结算对比表)   
	 * @author pengl
	 * @date 2018年3月20日 下午6:18:24
	 */
	@ResponseBody
	@RequestMapping("/mchtSettleCompare/getMchtSettleCompareList.shtml")
	public Map<String, Object> getMchtSettleCompareList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MchtSettleCompareCustom> dataList = null;
		Integer totalCount = 0;
		try {
			MchtSettleCompareCustomExample mchtSettleCompareCustomExample = new MchtSettleCompareCustomExample();
			MchtSettleCompareCustomExample.MchtSettleCompareCustomCriteria mchtSettleCompareCustomCriteria = mchtSettleCompareCustomExample.createCriteria();
			mchtSettleCompareCustomCriteria.andDelFlagEqualTo("0");
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
			mchtSettleCompareCustomCriteria.andSettleMonthEqualTo(yearMonthDate);
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtSettleCompareCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				mchtSettleCompareCustomCriteria.andMchtNameLike(request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtSettleCompareCustomCriteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
		
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				mchtSettleCompareCustomCriteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
			}
			mchtSettleCompareCustomExample.setLimitStart(page.getLimitStart());
			mchtSettleCompareCustomExample.setLimitSize(page.getLimitSize());
			mchtSettleCompareCustomExample.setOrderByClause(" id desc");
			dataList = mchtSettleCompareService.selectByCustomExample(mchtSettleCompareCustomExample);
			totalCount = mchtSettleCompareService.countByCustomExample(mchtSettleCompareCustomExample);
			resMap.put("Rows", dataList);
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
	@RequestMapping("/mchtSettleCompare/export.shtml")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			MchtSettleCompareCustomExample mchtSettleCompareCustomExample = new MchtSettleCompareCustomExample();
			MchtSettleCompareCustomExample.MchtSettleCompareCustomCriteria mchtSettleCompareCustomCriteria = mchtSettleCompareCustomExample.createCriteria();
			mchtSettleCompareCustomCriteria.andDelFlagEqualTo("0");
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
			mchtSettleCompareCustomCriteria.andSettleMonthEqualTo(yearMonthDate);
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				mchtSettleCompareCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				mchtSettleCompareCustomCriteria.andMchtNameLike(request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				mchtSettleCompareCustomCriteria.andProductTypeIdEqualTo(request.getParameter("productTypeId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				mchtSettleCompareCustomCriteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
			}
			mchtSettleCompareCustomExample.setOrderByClause(" id desc");
			List<MchtSettleCompareCustom> dataList = mchtSettleCompareService.selectByCustomExample(mchtSettleCompareCustomExample);
			String[] titles = {"月份","商家序号","公司名称","店铺名称","上期未结算","跨期结算","当月结算","当月未结","月末未结算","本月应结算款","本月结算单金额","本月直赔单金额"};
			ExcelBean excelBean = new ExcelBean("导出POP交易商品明细.xls",
					"导出POP交易商品明细", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtSettleCompareCustom mchtSettleCompareCustom:dataList){
				String[] data = {
					yearMonthDate.split("-")[0]+"年"+yearMonthDate.split("-")[1]+"月"	,
					mchtSettleCompareCustom.getMchtCode()+"-"+mchtSettleCompareCustom.getMchtId(),
					mchtSettleCompareCustom.getCompanyName(),
					mchtSettleCompareCustom.getShopName(),
					mchtSettleCompareCustom.getBeginNotSettle()==null?"":mchtSettleCompareCustom.getBeginNotSettle().toString(),
					mchtSettleCompareCustom.getCrossMonthSettle()==null?"":mchtSettleCompareCustom.getCrossMonthSettle().toString(),
					mchtSettleCompareCustom.getCurrentMonthSettle()==null?"":mchtSettleCompareCustom.getCurrentMonthSettle().toString(),
					mchtSettleCompareCustom.getCurrentMonthNotSettle()==null?"":mchtSettleCompareCustom.getCurrentMonthNotSettle().toString(),
					mchtSettleCompareCustom.getEndNotSettle()==null?"":mchtSettleCompareCustom.getEndNotSettle().toString(),
					mchtSettleCompareCustom.getCurrentMonthNeedSettle()==null?"":mchtSettleCompareCustom.getCurrentMonthNeedSettle().toString(),
					mchtSettleCompareCustom.getCurrentSettleOrderAmount()==null?"":mchtSettleCompareCustom.getCurrentSettleOrderAmount().toString(),
					mchtSettleCompareCustom.getCurrentRefundAmount()==null?"":mchtSettleCompareCustom.getCurrentRefundAmount().toString()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
