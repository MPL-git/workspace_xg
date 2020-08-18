package com.jf.controller;

import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.*;
import com.jf.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/shopProduct")
public class ShopProductController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ShopProductController.class);

	@Resource
	private ProductService productService;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Resource
	private ShopProductCustomTypeService shopProductCustomTypeService;
	
	@Resource
	private ProductPropService productPropService;
	
	@Resource
	private MchtProductTypeService mchtProductTypeService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ProductPicService productPicService;
	
	@Resource
	private ProductDescPicService productDescPicService;
	
	@Resource
	private ProductItemService productItemService;
	
	@Resource
	private ProductPropValueService productPropValueService;
	
	
	@Resource
	private SingleProductActivityService singleProductActivityService;
	
	/**
	 * 店铺商品
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/productIndex")
	public String productIndex(Model model, HttpServletRequest request) {
		List<MchtProductBrandCustom> productBrandList = mchtProductBrandService.getMchtUsebleProductBrand(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		ShopProductCustomTypeExample e = new ShopProductCustomTypeExample();
		ShopProductCustomTypeExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		c.andStatusEqualTo("1");
		List<ShopProductCustomType> shopProductCustomTypeList = shopProductCustomTypeService.selectByExample(e);
		model.addAttribute("shopProductCustomTypeList",shopProductCustomTypeList);
		String showSetting = "";
		if(request.getSession().getAttribute("showSetting")!=null){
			showSetting = (String) request.getSession().getAttribute("showSetting");
		}
		String hideSetting = "";
		if(request.getSession().getAttribute("hideSetting")!=null){
			hideSetting = (String) request.getSession().getAttribute("hideSetting");
		}
		
		if(!StringUtil.isEmpty(request.getParameter("pageNumber"))){
			model.addAttribute("pageNumber", Integer.parseInt(request.getParameter("pageNumber")));
		}else{
			model.addAttribute("pageNumber", 1);
		}
		model.addAttribute("showSetting",showSetting);
		model.addAttribute("hideSetting",hideSetting);
		return "shopProduct/shopProductIndex";
	}

	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getProductList")
	@ResponseBody
	public ResponseMsg getProductList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		ProductCustomExample productCustomExample = new ProductCustomExample();
		ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();

		productCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
//		productCustomCriteria.andIsActivityNotEqualTo("1");//可报名
//		productCustomCriteria.andShopProductActivityStatusEqualTo("0");
		productCustomCriteria.andSaleTypeEqualTo("1");
		
		productCustomExample.setOrderByClause("sort is null,sort asc,weights desc ,id asc");

		if (!StringUtil.isEmpty(request.getParameter("delFlag"))) {
			productCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
		} else {
			productCustomCriteria.andDelFlagEqualTo("0");
		}
		
//		if (!StringUtil.isEmpty(request.getParameter("search_isShow"))) {
//			productCustomCriteria.andIsShowEqualTo(request.getParameter("search_isShow"));
//		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_status"))) {
			productCustomCriteria.andStatusEqualTo(request.getParameter("search_status"));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_brandId"))) {
			productCustomCriteria.andBrandIdEqualTo(Integer.valueOf(request.getParameter("search_brandId")));
		}
		
		if (!StringUtil.isEmpty(request.getParameter("search_auditStatus"))) {
			productCustomCriteria.andAuditStatusEqualTo(request.getParameter("search_auditStatus"));
		}

		if (!StringUtil.isEmpty(request.getParameter("productType3"))) {// 第三级分类
			productCustomCriteria.andProductTypeAll(Integer.valueOf(request.getParameter("productType3")));
		} else if (!StringUtil.isEmpty(request.getParameter("productType2"))) {
			productCustomCriteria.andProductTypeAll(Integer.valueOf(request.getParameter("productType2")));
		} else if (!StringUtil.isEmpty(request.getParameter("productType1"))) {
			productCustomCriteria.andProductTypeAll(Integer.valueOf(request.getParameter("productType1")));
		}
		
		String shopProductCustomTypeId=request.getParameter("search_shopProductCustomTypeId");
		
		if(!StringUtil.isEmpty(shopProductCustomTypeId)){
			if(!shopProductCustomTypeId.equals("-1")){
				productCustomCriteria.andShopProductCustomTypeIdEqualTo(shopProductCustomTypeId);
			}else{
				productCustomCriteria.andShopProductCustomTypeIdEqualNull();
			}
		}
		
		if (!StringUtil.isEmpty(request.getParameter("searchKeywrod"))) {
			if ("1".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andNameLike("%" + request.getParameter("searchKeywrod").trim() + "%");
			}
			if ("2".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andArtNoLike("%" + request.getParameter("searchKeywrod").trim() + "%");
			}
			if ("3".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andCodeEqualTo(request.getParameter("searchKeywrod").trim());
			}
			if ("4".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andRemarksLike("%" + request.getParameter("searchKeywrod").trim() + "%");
			}
			if ("5".equals(request.getParameter("searchKeywrodType"))) {
				try {
					Integer activityId=Integer.valueOf(request.getParameter("searchKeywrod").trim());
					productCustomCriteria.andActivityIdEqualTo(activityId);
				} catch (NumberFormatException e) {
					productCustomCriteria.andActivityIdEqualTo(0);
				}
			}
		}

		int totalCount = productService.countProductCustomByExample(productCustomExample);
		
		
		String pageNumberStr = request.getParameter("pageNumber");
		Integer pageNumber;
		if(!StringUtil.isEmpty(pageNumberStr)){
			pageNumber = Integer.parseInt(pageNumberStr);
		}else{
			pageNumber = page.getPage();
		}
		
		productCustomExample.setLimitStart(page.getLimitSize() * (pageNumber - 1));
		productCustomExample.setLimitSize(page.getLimitSize());
		
		List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
		
		for (ProductCustom productCustom:productCustoms) {
			if(!StringUtil.isEmpty(productCustom.getPic()) && !productCustom.getPic().contains("http")){
				productCustom.setPic(FileUtil.getMiddleImageName(productCustom.getPic()));
			}
			//判断商品是否可删除
			if("1".equals(productCustom.getStatus())){//上架状态的都不能删除
				productCustom.setCanDelete(false);
			}else{
				if("1".equals(productCustom.getSaleType())){//品牌款，可报名就可删除
					if("0".equals(productCustom.getActivityStatusDesc())){
						productCustom.setCanDelete(true);
					}else{
						productCustom.setCanDelete(false);
					}
				}else{//单品款  从未报名（该商品不存在[未关闭][未删除]的报名）就可删除
					SingleProductActivityExample singleProductActivityExample=new SingleProductActivityExample();
					singleProductActivityExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productCustom.getId()).andIsCloseEqualTo("0");
					if(singleProductActivityService.countByExample(singleProductActivityExample)>0)	{
						productCustom.setCanDelete(false);
					}else{
						productCustom.setCanDelete(true);
					}
				}
			}
			
		}
		returnData.put("Rows", productCustoms);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);

	}
	
	/**
	 *追加、更新、修改分类页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/toEditCustomType")
	public String toEditCustomType(Model model, HttpServletRequest request) {
		String id=request.getParameter("id");
		String ids=request.getParameter("ids");
		String actionType = request.getParameter("actionType");
		if(!StringUtil.isEmpty(id)){
			Product product=productService.selectByPrimaryKey(Integer.parseInt(id));
			String shopProductCustomTypeIdGroup = product.getShopProductCustomTypeIdGroup();
			List<ShopProductCustomType> shopProductCustomTypes = new ArrayList<ShopProductCustomType>();
			if(!StringUtil.isEmpty(shopProductCustomTypeIdGroup)){
				String[] shopProductCustomTypeIdArray = shopProductCustomTypeIdGroup.split(",");
				List<Integer> shopProductCustomTypeIdList = new ArrayList<Integer>();
				for(int i=0;i<shopProductCustomTypeIdArray.length;i++){
					if(!shopProductCustomTypeIdList.contains(Integer.parseInt(shopProductCustomTypeIdArray[i]))){
						shopProductCustomTypeIdList.add(Integer.parseInt(shopProductCustomTypeIdArray[i]));
					}
				}
				ShopProductCustomTypeExample e = new ShopProductCustomTypeExample();
				ShopProductCustomTypeExample.Criteria c = e.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
				c.andIdIn(shopProductCustomTypeIdList);
				shopProductCustomTypes = shopProductCustomTypeService.selectByExample(e);
				model.addAttribute("shopProductCustomTypes", shopProductCustomTypes);
			}
		}
		ShopProductCustomTypeExample e = new ShopProductCustomTypeExample();
		ShopProductCustomTypeExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		List<ShopProductCustomType> allShopProductCustomTypes = shopProductCustomTypeService.selectByExample(e);
		model.addAttribute("allShopProductCustomTypes", allShopProductCustomTypes);
		model.addAttribute("actionType", actionType);
		model.addAttribute("id", id);
		model.addAttribute("ids", ids);
		return "shopProduct/editCustomType";
	}
	
	
	
	/**
	 *清除分类
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/removeCustomType")
	@ResponseBody
	public ResponseMsg removeCustomType(HttpServletRequest request) {
		try {
			String ids=request.getParameter("ids");
			List<Integer> idList = new ArrayList<Integer>();
			if(!StringUtil.isEmpty(ids)){
				String[] idArray = ids.split(",");
				for(int i=0;i<idArray.length;i++){
					idList.add(Integer.parseInt(idArray[i]));
				}
			}
			Product product=new Product();
			product.setShopProductCustomTypeIdGroup("");
			product.setUpdateBy(this.getSessionMchtInfo(request).getId());
			product.setUpdateDate(new Date());
			ProductExample example = new ProductExample();
			ProductExample.Criteria c = example.createCriteria();
			c.andIdIn(idList);
			productService.updateByExampleSelective(product, example);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 *更新分类
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/updateCustomType")
	@ResponseBody
	public ResponseMsg updateCustomType(HttpServletRequest request) {
		try {
			String ids = request.getParameter("ids");
			String shopProductCustomTypeIds = request.getParameter("shopProductCustomTypeIds");
			String[] idArray = ids.split(",");
			List<Integer> idList = new ArrayList<Integer>();
			for(int i=0;i<idArray.length;i++){
				idList.add(Integer.parseInt(idArray[i]));
			}
			ProductExample example = new ProductExample();
			ProductExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andIdIn(idList);
			Product product = new Product();
			product.setShopProductCustomTypeIdGroup(shopProductCustomTypeIds);
			product.setUpdateBy(this.getSessionMchtInfo(request).getId());
			product.setUpdateDate(new Date());
			productService.updateByExampleSelective(product, example);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 *追加分类
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/appendCustomType")
	@ResponseBody
	public ResponseMsg appendCustomType(HttpServletRequest request) {
		try {
			String ids = request.getParameter("ids");
			String shopProductCustomTypeIds = request.getParameter("shopProductCustomTypeIds");
			String[] idArray = ids.split(",");
			List<Integer> idList = new ArrayList<Integer>();
			for(int i=0;i<idArray.length;i++){
				idList.add(Integer.parseInt(idArray[i]));
			}
			ProductExample example = new ProductExample();
			ProductExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andIdIn(idList);
			List<Product> products = productService.selectByExample(example);
			for(Product product:products){
				String shopProductCustomTypeIdGroup = "";
				if(StringUtil.isEmpty(product.getShopProductCustomTypeIdGroup())){
					shopProductCustomTypeIdGroup = shopProductCustomTypeIds;
				}else{
					String oldIds = product.getShopProductCustomTypeIdGroup();
					String[] oldIdsArray = oldIds.split(",");
					List<String> oldIdsList = new ArrayList<String>();
					for(String oldId:oldIdsArray){
						oldIdsList.add(oldId);
					}
					String[] appendIds = shopProductCustomTypeIds.split(",");
					String needAppendIds = "";
					for(int i=0;i<appendIds.length;i++){
						if(!oldIdsList.contains(appendIds[i])){
							needAppendIds+=appendIds[i]+",";
						}
					}
					if(!StringUtil.isEmpty(needAppendIds)){
						needAppendIds = needAppendIds.substring(0, needAppendIds.lastIndexOf(","));
						shopProductCustomTypeIdGroup = oldIds+","+needAppendIds;
					}else{
						shopProductCustomTypeIdGroup = oldIds;
					}
				}
				product.setShopProductCustomTypeIdGroup(shopProductCustomTypeIdGroup);
				product.setUpdateBy(this.getSessionMchtInfo(request).getId());
				product.setUpdateDate(new Date());
				productService.updateByPrimaryKeySelective(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	/**
	 * 修改商品
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopProductEdit")
	public String productEdit(Model model, HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		
		Product product = productService.selectByPrimaryKey(id);
		if(!StringUtil.isEmpty(product.getProductDesc())){
			product.setProductDesc(product.getProductDesc().replace("&&&", "\n"));
		}
		model.addAttribute("product", product);
		List<MchtProductBrandCustom> productBrandList = mchtProductBrandService.getMchtUsebleProductBrand(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		
		ProductPropExample productPropExample = new ProductPropExample();
		productPropExample.createCriteria().andDelFlagEqualTo("0").andIdNotEqualTo(0).andNameNotEqualTo("款式");
		List<ProductProp> productPropList = productPropService.selectByExample(productPropExample);
		List<ProductProp> productProps4SelectList = new ArrayList<ProductProp>();
		
		//当商家的主营类目为：运动、服饰，规格名的选项，只需显示：颜色、尺码
		//当商家的主营类目为：鞋包，规格名的选项，只需显示：颜色、尺码、款式、尺寸
        MchtProductTypeExample mchtProductTypeExample=new MchtProductTypeExample();
        mchtProductTypeExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIsMainEqualTo("1");
		List<MchtProductType> mchtProductTypes=mchtProductTypeService.selectByExample(mchtProductTypeExample);
		
		if(mchtProductTypes!=null&&mchtProductTypes.size()>0){
			ProductType productType=productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
			if("运动".equals(productType.getName())||"服饰".equals(productType.getName())){
				for(ProductProp productProp:productPropList){
					if("颜色分类".equals(productProp.getName())||"尺码".equals(productProp.getName())){
						productProps4SelectList.add(productProp);
					}
				}
			}else if("鞋包".equals(productType.getName())){
				for(ProductProp productProp:productPropList){
					if("颜色分类".equals(productProp.getName())||"尺码".equals(productProp.getName())||"尺寸".equals(productProp.getName())){
						productProps4SelectList.add(productProp);
					}
				}
			}else{
				productProps4SelectList.addAll(productPropList);
			}
		}else{
			productProps4SelectList.addAll(productPropList);
		}
		
		model.addAttribute("productPropList", productProps4SelectList);
		
		ProductType productType=productTypeService.selectByPrimaryKey(product.getProductTypeId());
		ProductType productTypeParent=productTypeService.selectByPrimaryKey(productType.getParentId());
		model.addAttribute("productTypeParent", productTypeParent);
		
		ProductPicExample productPicExample=new ProductPicExample();
		productPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(product.getId());
		productPicExample.setOrderByClause("seq_no");
		model.addAttribute("productPics", productPicService.selectByExample(productPicExample));
		
		ProductDescPicExample productDescPicExample=new ProductDescPicExample();
		productDescPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(product.getId());
		productDescPicExample.setOrderByClause("seq_no");
		model.addAttribute("productDescPics", productDescPicService.selectByExample(productDescPicExample));
		
		if("0".equals(product.getIsSingleProp())){
			//获取商品SKU
			ProductItemExample productItemExample=new ProductItemExample();
			productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(id);
			List<ProductItem> productItems=productItemService.selectByExample(productItemExample);
			
			
			if(productItems!=null&&productItems.size()>0){
				
				
				//获取propId集合，需按添加时的顺序排序，（取一个sku的属性值来获取即可）
				List<Integer> propIds=new ArrayList<Integer>();
				String[] pvIds=productItems.get(0).getPropValId().split(",");
				for (int i = 0; i < pvIds.length; i++) {
					ProductPropValue productPropValue=productPropValueService.selectByPrimaryKey(Integer.valueOf(pvIds[i]));
					propIds.add(productPropValue.getPropId());
				}
				model.addAttribute("propIds", propIds);
				
				//组织商品规格
				List<Integer> propValueIdList=new ArrayList<Integer>();
				for(ProductItem productItem:productItems){
					String[] propValueIds=productItem.getPropValId().split(",");
					if(propValueIds.length>0){
						for (int i = 0; i < propValueIds.length; i++) {
							Integer propId=Integer.valueOf(propValueIds[i]);
							if(!propValueIdList.contains(propId)){
								propValueIdList.add(propId);
							}
						}
					}
				}
				ProductPropValueExample productPropValueExample=new ProductPropValueExample();
				productPropValueExample.createCriteria().andDelFlagEqualTo("0").andIdIn(propValueIdList);
				List<ProductPropValueCustom> productPropValues=productPropValueService.selectProductPropValueCustomByExample(productPropValueExample);
				Map<Integer, Map<String, Object>> productPropMap=new HashMap<Integer, Map<String, Object>>();
				Map<String, Object> propMap;
				List<ProductPropValue> productPropValuesList;
		
				for(ProductPropValueCustom productPropValueCustom:productPropValues){
					propMap=productPropMap.get(productPropValueCustom.getPropId());
					if(propMap==null){
						propMap=new HashMap<String, Object>();
						propMap.put("propName", productPropValueCustom.getPropName());
						productPropValuesList=new ArrayList<>();
						propMap.put("productPropValues", productPropValuesList);
						productPropMap.put(productPropValueCustom.getPropId(), propMap);
					}else{
						productPropValuesList=(ArrayList)propMap.get("productPropValues");
					}
					productPropValuesList.add(productPropValueCustom);
					
				}
		
				model.addAttribute("props", productPropMap);
				
				List<List<String>> propValues=new ArrayList<List<String>>();
				for(Integer propId:propIds){
					List<ProductPropValue> propValueList=(List<ProductPropValue>)productPropMap.get(propId).get("productPropValues");
					List<String> valueList=new ArrayList<>();
					for (ProductPropValue roductPropValue:propValueList) {
						valueList.add(roductPropValue.getPropValue());
					}
					propValues.add(valueList);
				}
				
				model.addAttribute("propValues", propValues);
			}
		}else{
			model.addAttribute("props", new HashMap<Integer, Map<String, Object>>());
			model.addAttribute("propIds", new ArrayList<Integer>());
			model.addAttribute("propValues", new ArrayList<List<String>>());
		}
		return "shopProduct/shopProductEdit";
	}
	
	/**
	 *更新显示/隐藏状态
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/updateIsShow")
	@ResponseBody
	public ResponseMsg updateIsShow(HttpServletRequest request) {
		try {
			String productId = request.getParameter("productId");
			String showSetting = request.getParameter("showSetting");
			String hideSetting = request.getParameter("hideSetting");
			if(!StringUtil.isEmpty(showSetting)){
				request.getSession().setAttribute("showSetting", showSetting);
			}
			if(!StringUtil.isEmpty(hideSetting)){
				request.getSession().setAttribute("hideSetting", hideSetting);
			}
			Product product = productService.selectByPrimaryKey(Integer.parseInt(productId));
			product.setUpdateBy(this.getSessionMchtInfo(request).getId());
			product.setUpdateDate(new Date());
			if(product.getIsShow().equals("0")){
				product.setIsShow("1");
			}else{
				product.setIsShow("0");
			}
			productService.updateByPrimaryKeySelective(product);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 排序保存
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveSort")
	@ResponseBody
	public ResponseMsg saveSort(HttpServletRequest request) {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Integer sort = Integer.parseInt(request.getParameter("sort"));
			Product product = new Product();
			product.setId(id);
			product.setSort(sort);
			product.setUpdateDate(new Date());
			product.setUpdateBy(this.getSessionMchtInfo(request).getId());
			productService.updateByPrimaryKeySelective(product);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
}
