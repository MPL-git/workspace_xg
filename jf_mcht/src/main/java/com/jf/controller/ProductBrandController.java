package com.jf.controller;

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
import com.jf.entity.BrandTmkPicTmp;
import com.jf.entity.BrandTmkPicTmpExample;
import com.jf.entity.ProductBrandCustom;
import com.jf.entity.ProductBrandCustomExample;
import com.jf.entity.ProductBrandTmp;
import com.jf.entity.ProductBrandTmpCustom;
import com.jf.entity.ProductBrandTmpCustomExample;
import com.jf.entity.ProductTypeExample;
import com.jf.service.BrandTmkPicTmpService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductBrandTmpService;
import com.jf.service.ProductTypeService;

@Controller
public class ProductBrandController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ProductBrandController.class);


	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ProductBrandService productBrandService;
	
	@Resource
	private ProductBrandTmpService productBrandTmpService;
	
	@Resource
	private BrandTmkPicTmpService brandTmkPicTmpService;

	/**
	 * 品牌库列表页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/productBrand/productBrandIndex")
	public String mchtBrandIndex(Model model, HttpServletRequest request) {
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1);
		model.addAttribute("productTypes", productTypeService.selectByExample(productTypeExample));
		return "productBrand/productBrandIndex";
	}
	
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/productBrand/getProductBrandList")
	@ResponseBody
	public ResponseMsg getMchtBrandList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		ProductBrandCustomExample productBrandCustomExample = new ProductBrandCustomExample();
		ProductBrandCustomExample.ProductBrandExampleCriteria criteria = productBrandCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		if (!StringUtil.isEmpty(request.getParameter("productType"))) {
			criteria.andProductTypeGroupLikeOrClause(request.getParameter("productType"));
		}
		if (!StringUtil.isEmpty(request.getParameter("tmkType"))) {
			criteria.andTmkTypeGroupLikeOrClause(request.getParameter("tmkType"));
		}
		if (!StringUtil.isEmpty(request.getParameter("searchKeyWord"))) {
			criteria.andSearchKeywordLike(request.getParameter("searchKeyWord"));
		}

		int totalCount = productBrandService.countProductBrandCustomByExample(productBrandCustomExample);
		productBrandCustomExample.setLimitStart(page.getLimitStart());
		productBrandCustomExample.setLimitSize(page.getLimitSize());
		List<ProductBrandCustom> productBrands = productBrandService.selectProductBrandCustomByExample(productBrandCustomExample);
		returnData.put("Rows", productBrands);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}

	
	
	/**
	 * 商家申请品牌列表页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/productBrand/productBrandTmpIndex")
	public String mchtBrandTmpIndex(Model model, HttpServletRequest request) {
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1);
		model.addAttribute("productTypes", productTypeService.selectByExample(productTypeExample));
		return "productBrand/productBrandTmpIndex";
	}
	
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/productBrand/getProductBrandTmpList")
	@ResponseBody
	public ResponseMsg getProductBrandTmpList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		ProductBrandTmpCustomExample productBrandTmpCustomExample = new ProductBrandTmpCustomExample();
		ProductBrandTmpCustomExample.ProductBrandTmpExampleCriteria criteria = productBrandTmpCustomExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		
		if (!StringUtil.isEmpty(request.getParameter("productType"))) {
			criteria.andProductTypeGroupLikeOrClause(request.getParameter("productType"));
		}
		if (!StringUtil.isEmpty(request.getParameter("tmkType"))) {
			criteria.andTmkTypeGroupLikeOrClause(request.getParameter("tmkType"));
		}
		if (!StringUtil.isEmpty(request.getParameter("searchKeyWord"))) {
			criteria.andSearchKeyWordLikeOrClause(request.getParameter("searchKeyWord"));
		}

		int totalCount = productBrandTmpService.countByExample(productBrandTmpCustomExample);
		productBrandTmpCustomExample.setLimitStart(page.getLimitStart());
		productBrandTmpCustomExample.setLimitSize(page.getLimitSize());
		List<ProductBrandTmpCustom> productBrandTmps = productBrandTmpService.selectProductBrandTmpCustomByExample(productBrandTmpCustomExample);
		returnData.put("Rows", productBrandTmps);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}

	
	/**
	 * 查看，修改申请品牌
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/productBrand/productBrandTmpEdit")
	public String productBrandTmpView(Model model, HttpServletRequest request) {
		
		if(StringUtil.isEmpty(request.getParameter("id"))){
			ProductTypeExample productTypeExample = new ProductTypeExample();
			productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1);
			model.addAttribute("productTypes", productTypeService.selectByExample(productTypeExample));
			model.addAttribute("productBrandTmp",new ProductBrandTmpCustom());
			return "productBrand/productBrandTmpEdit";
		}
		
		Integer id=Integer.valueOf(request.getParameter("id"));
		ProductBrandTmpCustom productBrandTmpCustom=productBrandTmpService.selectProductBrandTmpCustomByPrimaryKey(id);
		model.addAttribute("productBrandTmp",productBrandTmpCustom);
		
		//商标图片
		BrandTmkPicTmpExample brandTmkPicTmpExample=new BrandTmkPicTmpExample();
		brandTmkPicTmpExample.createCriteria().andDelFlagEqualTo("0").andBrandIdEqualTo(productBrandTmpCustom.getId());
		List<BrandTmkPicTmp> brandTmkPicTmps=brandTmkPicTmpService.selectByExample(brandTmkPicTmpExample);
		model.addAttribute("brandTmkPicTmps", brandTmkPicTmps);
		
		if(productBrandTmpCustom.getStatus().equals("2")||productBrandTmpCustom.getStatus().equals("1")){
			return "productBrand/productBrandTmpView";
		}else{
			ProductTypeExample productTypeExample = new ProductTypeExample();
			productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1);
			model.addAttribute("productTypes", productTypeService.selectByExample(productTypeExample));
			return "productBrand/productBrandTmpEdit";
		}
		
		
	}
	
	
	/**
	 * 品牌申请保存
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/productBrand/productBrandTmpCommitSave")
	@ResponseBody
	public ResponseMsg productBrandTmpCommitSave(Model model, HttpServletRequest request,ProductBrandTmp productBrandTmp) {
		try {
			
			if(StringUtil.isEmpty(request.getParameter("brandTmkPics"))){
				throw new ArgException("请上传商标图");
			}
			
			productBrandTmp.setUpdateDate(new Date());
			productBrandTmp.setUpdateBy(this.getSessionUserInfo(request).getId());
			productBrandTmp.setMchtId(this.getSessionMchtInfo(request).getId());
			productBrandTmpService.applyCommitSave(productBrandTmp,request.getParameter("brandTmkPics"));
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
		
	}
	/**
	 * 品牌申请提审
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/productBrand/productBrandTmpCommitAudit")
	@ResponseBody
	public ResponseMsg productBrandTmpCommitAudit(Model model, HttpServletRequest request,ProductBrandTmp productBrandTmp) {
		try {
			if(StringUtil.isEmpty(request.getParameter("brandTmkPics"))){
				throw new ArgException("请上传商标图");
			}
			productBrandTmp.setUpdateDate(new Date());
			productBrandTmp.setUpdateBy(this.getSessionUserInfo(request).getId());
			productBrandTmpService.applyCommitAudit(productBrandTmp,request.getParameter("brandTmkPics"));
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
		
	}
	

}
