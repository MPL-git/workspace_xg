package com.jf.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.jf.common.utils.StringUtils;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;
import com.jf.service.SubOrderService;
import com.jf.vo.Page;

@Controller
@RequestMapping("/customerserviceorderData")
public class CustomerServiceOrderDataController extends BaseController {
		
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	private static final long serialVersionUID = 1L;

	//客服订单商品统计
	@RequestMapping("/customerserviceOrderCountList.shtml")
	public ModelAndView cstomerserviceOrderCountList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/customerserviceorderData/customerserviceOrderCountList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String payDateEnd = sdf.format(now);
		String payDateBegin = payDateEnd.substring(0, 7)+"-01";
		m.addObject("payDateBegin", payDateBegin);
		m.addObject("payDateEnd", payDateEnd);
		return m;
	}
	
	//客服订单商品数据列表 
	@RequestMapping("/customerserviceOrderCountdata.shtml")
	@ResponseBody
	public Map<String, Object> cstomerserviceOrderCountData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
			String payDateBegin = request.getParameter("payDateBegin");
			String payDateEnd = request.getParameter("payDateEnd");
	
			if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date now = new Date();
				payDateEnd = sdf.format(now);
				payDateBegin = payDateEnd.substring(0, 7)+"-01";
			}
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
		   
			List<HashMap<String, Object>> list = subOrderService.customerserviceOrderCount(paramMap); 
			/*for (int i = 0; i < list.size(); i++) {
				Map<String, Object> mchtInfoMap=(Map<String, Object>)list.get(i);
				String name = (String) mchtInfoMap.get("name");
				ProductTypeExample productTypeExample = new ProductTypeExample();
				ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
				productTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andNameEqualTo(name);
				List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample);
				if (productTypes!=null) {
					for (ProductType productType : productTypes) {
						ProductTypeExample productTypeExamples = new ProductTypeExample();
						ProductTypeExample.Criteria productTypecriteria = productTypeExamples.createCriteria();
						productTypecriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdEqualTo(productType.getParentId());
						List<ProductType> producttype = productTypeService.selectByExample(productTypeExamples);
						if (producttype!=null) {
							for (ProductType productType2 : producttype) {
								ProductTypeExample producttypeExamples = new ProductTypeExample();
								ProductTypeExample.Criteria producttypecriteria = producttypeExamples.createCriteria();
								producttypecriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdEqualTo(productType2.getParentId());
								List<ProductType> producttypes = productTypeService.selectByExample(producttypeExamples);
								if (producttypes!=null) {
									for (ProductType productType3 : producttypes) {
										 mchtInfoMap.put("productTypesname", productType3.getName());
									}
								}
							}
						}
					}
				}
			}*/

			resMap.put("Rows", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resMap;
	}
	
}
