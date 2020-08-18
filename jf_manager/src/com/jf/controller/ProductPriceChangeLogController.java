package com.jf.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.jf.entity.ProductPriceChangeLog;
import com.jf.entity.ProductPriceChangeLogCustom;
import com.jf.entity.ProductPriceChangeLogCustomExample;
import com.jf.entity.ProductPriceChangeLogExample;
import com.jf.entity.StateCode;
import com.jf.service.ProductPriceChangeLogService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class ProductPriceChangeLogController extends BaseController {

	@Autowired
	private ProductPriceChangeLogService productPriceChangeLogService;
	
	/**
	 * 
	 * @Title productPriceChangeLogManager   
	 * @Description TODO(商品价格修改记录)   
	 * @author pengl
	 * @date 2018年9月8日 下午2:24:03
	 */
	@RequestMapping("/productPriceChangeLog/productPriceChangeLogManager.shtml")
	public ModelAndView productPriceChangeLogManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/prod/productPriceChangeLog/productPriceChangeLogList");
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/productPriceChangeLog/productPriceChangeLogList.shtml")
	public Map<String, Object> productPriceChangeLogList(HttpServletRequest request, Page page, Integer productBandId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<ProductPriceChangeLogCustom> productPriceChangeLogCustomList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ProductPriceChangeLogCustomExample productPriceChangeLogCustomExample = new ProductPriceChangeLogCustomExample();
			ProductPriceChangeLogCustomExample.ProductPriceChangeLogCustomCriteria productPriceChangeLogCustomCriteria = productPriceChangeLogCustomExample.createCriteria();
			productPriceChangeLogCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ) {
				productPriceChangeLogCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName")) ) {
				productPriceChangeLogCustomCriteria.andMchtNameLike("%"+request.getParameter("mchtName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("productCode")) ) {
				productPriceChangeLogCustomCriteria.andProductCodeEqualTo(request.getParameter("productCode"));
			}
			if(productBandId != null ) {
				productPriceChangeLogCustomCriteria.andProductBandIdEqualTo(productBandId);
			}
			if(!StringUtil.isEmpty(request.getParameter("artNo")) ) {
				productPriceChangeLogCustomCriteria.andArtNoEqualTo("%"+request.getParameter("artNo")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("beginMinMallPrice")) ) {
				productPriceChangeLogCustomCriteria.andMinMallPriceGreaterThanOrEqualTo(new BigDecimal(request.getParameter("beginMinMallPrice")));
			}
			if(!StringUtil.isEmpty(request.getParameter("endMinMallPrice")) ) {
				productPriceChangeLogCustomCriteria.andMinMallPriceLessThanOrEqualTo(new BigDecimal(request.getParameter("endMinMallPrice")));
			}
			if(!StringUtil.isEmpty(request.getParameter("beginMinSalePrice")) ) {
				productPriceChangeLogCustomCriteria.andMinSalePriceGreaterThanOrEqualTo(new BigDecimal(request.getParameter("beginMinSalePrice")));
			}
			if(!StringUtil.isEmpty(request.getParameter("endMinSalePrice")) ) {
				productPriceChangeLogCustomCriteria.andMinSalePriceLessThanOrEqualTo(new BigDecimal(request.getParameter("endMinSalePrice")));
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate")) ) {
				productPriceChangeLogCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate")) ) {
				productPriceChangeLogCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			productPriceChangeLogCustomExample.setOrderByClause(" t.create_date desc");
			productPriceChangeLogCustomExample.setLimitStart(page.getLimitStart());
			productPriceChangeLogCustomExample.setLimitSize(page.getLimitSize());
			totalCount = productPriceChangeLogService.countByCustomExample(productPriceChangeLogCustomExample);
			productPriceChangeLogCustomList = productPriceChangeLogService.selectByCustomExample(productPriceChangeLogCustomExample);
			resMap.put("Rows", productPriceChangeLogCustomList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title productPriceChangeLogDel   
	 * @Description TODO(删除商品价格修改记录)   
	 * @author pengl
	 * @date 2018年9月8日 下午4:28:40
	 */
	@ResponseBody
	@RequestMapping("/productPriceChangeLog/productPriceChangeLogDel.shtml")
	public Map<String, Object> productPriceChangeLogDel(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			ProductPriceChangeLog productPriceChangeLog = new ProductPriceChangeLog();
			productPriceChangeLog.setId(Integer.parseInt(request.getParameter("id")));
			productPriceChangeLog.setDelFlag("1");
			productPriceChangeLog.setUpdateBy(staffId);
			productPriceChangeLog.setUpdateDate(new Date());
			productPriceChangeLogService.updateByPrimaryKeySelective(productPriceChangeLog);
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
	
	
}
