package com.jf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeCustom;
import com.jf.entity.ProductTypeExample;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductTypeService;

@Controller
public class ProductTypeController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ProductTypeController.class);


	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ProductBrandService productBrandService;
	

	
	/**
	 * 跟据上级id获取下级品类
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/productType/getSubProductTypes")
	@ResponseBody
	public ResponseMsg getSubProductTypes(HttpServletRequest request) {
		if(StringUtil.isEmpty(request.getParameter("parentId"))){
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
		}
		
		Integer parentId=Integer.valueOf(request.getParameter("parentId"));
		
		ProductTypeExample productTypeExample=new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(parentId);
		
		List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,productTypes);
	}
	
	/**
	 * 跟据上级id获取下级品类
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/productType/getMchtProductTypes")
	@ResponseBody
	public ResponseMsg getMchtProductTypes(HttpServletRequest request) {
		if(StringUtil.isEmpty(request.getParameter("parentId"))){
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,null);
		}
		
		Integer parentId=Integer.valueOf(request.getParameter("parentId"));
		String productBrandId=request.getParameter("productBrandId");
		ProductBrandExample pbe = new ProductBrandExample();
		pbe.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andNameEqualTo("小商品");
		List<ProductBrand> productBrands = productBrandService.selectByExample(pbe);
		ProductBrandExample pbe1 = new ProductBrandExample();
		pbe1.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andNameEqualTo("无品牌");
		List<ProductBrand> noProductBrands = productBrandService.selectByExample(pbe1);
		Integer smallwareBrandId = 0;
		if(productBrands!=null && productBrands.size()>0){
			smallwareBrandId = productBrands.get(0).getId();
		}
		Integer noProductBrandId = 0;
		if(noProductBrands!=null && noProductBrands.size()>0){
			noProductBrandId = noProductBrands.get(0).getId();
		}
		if(!StringUtil.isEmpty(productBrandId) && smallwareBrandId.equals(Integer.parseInt(productBrandId)) && !smallwareBrandId.equals(0)){
			if(parentId==1){
				
				List<ProductTypeCustom> productTypes=productTypeService.getMchtUserbleProductType(this.getSessionMchtInfo(request).getId());
				
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,productTypes);
			}else{
				ProductTypeExample productTypeExample=new ProductTypeExample();
				productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(parentId).andStatusEqualTo("1").andIsSmallwareEqualTo("1");
				productTypeExample.setOrderByClause("seq_no asc");
				
				List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
				
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,productTypes);
			}
		}else if(!StringUtil.isEmpty(productBrandId) && noProductBrandId.equals(Integer.parseInt(productBrandId)) && !noProductBrandId.equals(0)){
			if(parentId==1){
				
				List<ProductTypeCustom> productTypes=productTypeService.getMchtUserbleProductType(this.getSessionMchtInfo(request).getId());
				
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,productTypes);
			}else{
				ProductTypeExample productTypeExample=new ProductTypeExample();
				productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(parentId).andStatusEqualTo("1").andIsSmallwareEqualTo("2");
				productTypeExample.setOrderByClause("seq_no asc");
				
				List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
				
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,productTypes);
			}
		}else{
			if(parentId==1){
				
				List<ProductTypeCustom> productTypes=productTypeService.getMchtUserbleProductType(this.getSessionMchtInfo(request).getId());
				
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,productTypes);
			}else{
				List<ProductType> productTypes = new ArrayList<ProductType>();
				ProductTypeExample productTypeExample=new ProductTypeExample();
				productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(parentId).andStatusEqualTo("1").andIsSmallwareEqualTo("0");
				productTypeExample.setOrderByClause("seq_no asc");
				productTypes=productTypeService.selectByExample(productTypeExample);
				
				ProductTypeExample e=new ProductTypeExample();
				e.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(parentId).andStatusEqualTo("1").andIsSmallwareIsNull();
				e.setOrderByClause("seq_no asc");
				List<ProductType> productTypeList=productTypeService.selectByExample(e);
				if(productTypeList!=null && productTypeList.size()>0){
					productTypes.addAll(productTypeList);
				}
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,productTypes);
			}
		}
	}
}
