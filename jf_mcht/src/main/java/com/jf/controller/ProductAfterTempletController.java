package com.jf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Area;
import com.jf.entity.AreaExample;
import com.jf.entity.MchtContact;
import com.jf.entity.MchtProductBrandCustom;
import com.jf.entity.ProductAfterTemplet;
import com.jf.entity.ProductAfterTempletCustom;
import com.jf.entity.ProductAfterTempletCustomExample;
import com.jf.entity.ProductAfterTempletExample;
import com.jf.entity.ProductBrand;
import com.jf.service.MchtProductBrandService;
import com.jf.service.ProductAfterTempletService;

@Controller
public class ProductAfterTempletController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ProductAfterTempletController.class);
	
	@Resource
	private ProductAfterTempletService productAfterTempletService;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;

	/**
	 * 售后模版
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/productAfterTemplet/productAfterTempletIndex")
	public String productIndex(Model model, HttpServletRequest request) {
		return "productAfterTemplet/productAfterTempletIndex";
	}
	
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/productAfterTemplet/getProductAfterTempletList")
	@ResponseBody
	public ResponseMsg getProductAfterTempletList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		ProductAfterTempletCustomExample productAfterTempletExample = new ProductAfterTempletCustomExample();
		ProductAfterTempletCustomExample.ProductAfterTempletCustomCriteria criteria=productAfterTempletExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		
		int totalCount = productAfterTempletService.countProductAfterTempletCustomByExample(productAfterTempletExample);
		productAfterTempletExample.setLimitStart(page.getLimitStart());
		productAfterTempletExample.setLimitSize(page.getLimitSize());
		List<ProductAfterTempletCustom> productAfterTemplets = productAfterTempletService.selectProductAfterTempletCustomByExample(productAfterTempletExample);

		returnData.put("Rows", productAfterTemplets);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);

	}
	
	
	/**
	 * 添加模版
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/productAfterTemplet/productAfterTempletAdd")
	public String productAfterTempletAdd(Model model,HttpServletRequest request) {
		ProductAfterTemplet productAfterTemplet=new ProductAfterTemplet();
		productAfterTemplet.setMchtId(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productAfterTemplet", productAfterTemplet);
		
		List<MchtProductBrandCustom> productBrandList = mchtProductBrandService.getMchtUsebleProductBrand(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		
		return "/productAfterTemplet/productAfterTempletEdit";
	}
	
	/**
	 * 模版信息修改
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/productAfterTemplet/productAfterTempletEdit")
	public String productAfterTempletEdit(Model model,HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		ProductAfterTemplet productAfterTemplet=productAfterTempletService.selectByPrimaryKey(id);
		model.addAttribute("productAfterTemplet", productAfterTemplet);
		
		List<MchtProductBrandCustom> productBrandList = mchtProductBrandService.getMchtUsebleProductBrand(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		
		return "/productAfterTemplet/productAfterTempletEdit";
	}
	
	
	/**
	 * 模版信息修改保存
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/productAfterTemplet/productAfterTempletSave")
	@ResponseBody
	public ResponseMsg productAfterTempletSave(HttpServletRequest request,ProductAfterTemplet productAfterTemplet) {
		try {
			productAfterTemplet.setUpdateDate(new Date());
			productAfterTemplet.setUpdateBy(this.getSessionUserInfo(request).getId());
			
			if(productAfterTemplet.getId()==null){
				productAfterTemplet.setMchtId(this.getSessionMchtInfo(request).getId());
				productAfterTemplet.setCreateDate(productAfterTemplet.getUpdateDate());
				productAfterTemplet.setCreateBy(productAfterTemplet.getUpdateBy());
			}
			productAfterTempletService.productAfterTempletSave(productAfterTemplet);
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,productAfterTemplet);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

	}
	
	
	/**
	 * 设置为默认联系人
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/productAfterTemplet/delProductAfterTemplet")
	@ResponseBody
	public ResponseMsg delProductAfterTemplet(HttpServletRequest request) {
		try {
			if(StringUtil.isEmpty(request.getParameter("id"))){
				throw new ArgException("模板不存在");
			}
			ProductAfterTemplet productAfterTemplet4Update=new ProductAfterTemplet();
			productAfterTemplet4Update.setId(Integer.valueOf(request.getParameter("id")));
			productAfterTemplet4Update.setUpdateBy(this.getSessionUserInfo(request).getId());
			productAfterTemplet4Update.setUpdateDate(new Date());
			productAfterTemplet4Update.setDelFlag("1");
			productAfterTempletService.updateByPrimaryKeySelective(productAfterTemplet4Update);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	
	@RequestMapping(value="/productAfterTemplet/getProductAfterTemplets")
	@ResponseBody
	public List<ProductAfterTempletCustom> getProductAfterTempletList(HttpServletRequest request){
		List<ProductAfterTempletCustom> productAfterTemplets=null;
		if(!StringUtil.isEmpty(request.getParameter("brandId"))){
			productAfterTemplets=productAfterTempletService.selectProductAfterTempletCustom4DefaultBrand(Integer.valueOf(request.getParameter("brandId")), this.getSessionMchtInfo(request).getId());
		}else{
			ProductAfterTempletCustomExample productAfterTempletExample = new ProductAfterTempletCustomExample();
			ProductAfterTempletCustomExample.ProductAfterTempletCustomCriteria criteria=productAfterTempletExample.createCriteria();
			criteria.andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
			
			productAfterTemplets= productAfterTempletService.selectProductAfterTempletCustomByExample(productAfterTempletExample);
		}
		

		return productAfterTemplets;
	}
}
