package com.jf.controller;

import com.jf.bean.ExcelBean;
import com.jf.common.base.ArgException;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CloudProductPropValueMapper;
import com.jf.dao.ProductVideoMapper;
import com.jf.entity.*;
import com.jf.service.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ProductController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Resource
	private ProductService productService;

	@Resource
	private MchtProductBrandService mchtProductBrandService;

	@Resource
	private ProductPropService productPropService;

	@Resource
	private ProductPropValueService productPropValueService;
	
	@Resource
	private ProductItemService productItemService;
	
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ProductPicService productPicService;
	
	@Resource
	private ProductDescPicService productDescPicService;
	
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private MchtProductTypeService mchtProductTypeService;
	
	@Resource
	private SingleProductActivityService singleProductActivityService;
	
	@Resource
	private FreightTemplateService freightTemplateService;
	
	@Resource
	private ProvinceFreightService provinceFreightService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private CloudProductItemService cloudProductItemService;
	
	@Resource
	private CloudProductPropValueMapper cloudProductPropValueMapper;
	
	@Resource
	private ActivityService activityService;
	@Resource
	private ProductBrandService productBrandService;
	@Resource
	private SourceNicheService sourceNicheService;
	@Resource
	private ProductVideoMapper productVideoMapper;
	@Resource
	private SysParamCfgService sysParamCfgService;
	@Resource
    private ProductExtendService productExtendService;
	@Resource
    private ProductUpperLowerLogService productUpperLowerLogService;
	/**
	 * 商品列表首页
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/productReject")
	public String productReject(Model model, HttpServletRequest request) {
		List<MchtProductBrandCustom> productBrandList = mchtProductBrandService.getMchtUsebleProductBrand(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		return "product/productReject";
	}
	
	/**
	 * 商品列表,驳回
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/productIndex")
	public String productIndex(Model model, HttpServletRequest request) {
		List<MchtProductBrandCustom> productBrandList = mchtProductBrandService.getMchtUsebleProductBrand(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		model.addAttribute("shopStatus", this.getSessionMchtInfo(request).getShopStatus());
		model.addAttribute("saleType", request.getParameter("saleType"));
		if(!StringUtil.isEmpty(request.getParameter("pageNumber"))){
			model.addAttribute("pageNumber", Integer.parseInt(request.getParameter("pageNumber")));
		}else{
			model.addAttribute("pageNumber", 1);
		}
		//商家主营类目
		MchtProductTypeExample mchtProductTypeExample = new MchtProductTypeExample();
		mchtProductTypeExample.createCriteria().andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIsMainEqualTo("1").andDelFlagEqualTo("0");
		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mchtProductTypeExample);
		model.addAttribute("isMainProductType",mchtProductTypes.get(0).getProductTypeId());
		return "product/productIndex";
	}

	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/product/getProductList")
	@ResponseBody
	public ResponseMsg getProductList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		ProductCustomExample productCustomExample = new ProductCustomExample();
		ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();

		productCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		productCustomExample.setOrderByClause("id desc");

		if (!StringUtil.isEmpty(request.getParameter("delFlag"))) {
			productCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
		} else {
			productCustomCriteria.andDelFlagEqualTo("0");
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
		
		String saleType=request.getParameter("search_saleType");
		
		if(!StringUtil.isEmpty(saleType)){
			productCustomCriteria.andSaleTypeEqualTo(saleType);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("search_status"))){
			productCustomCriteria.andStatusEqualTo(request.getParameter("search_status"));
		}
		if(!StringUtil.isEmpty(request.getParameter("search_productActivityStatus"))){
			String productActivityStatus=request.getParameter("search_productActivityStatus");
			if("0".equals(productActivityStatus)){
				productCustomCriteria.andAuditStatusNotEqualTo("0");
			}
			if(productActivityStatus.equals("6")){
				productCustomCriteria.andAuditStatusEqualTo("0");
			}else if(productActivityStatus.equals("7")){
				productCustomCriteria.andAuditStatusEqualTo("3");
			}else{
				if("1".equals(saleType)){
					productCustomCriteria.andShopProductActivityStatusEqualTo(productActivityStatus);
				}
				if("2".equals(saleType)){
					productCustomCriteria.andSingleProductActivityStatusEqualTo(productActivityStatus);
				}
			}
		}
		
		if("2".equals(saleType)){
			productCustomCriteria.andBrandIsEffect();
			productCustomCriteria.andAuditStatusNotEqualTo("3");
		}
		

//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		try {
//			if (!StringUtil.isEmpty(request.getParameter("createTimeBegin"))) {
//				productCustomCriteria.andCreateDateGreaterThanOrEqualTo(sf.parse(request.getParameter("createTimeBegin") + " 00:00:00"));
//			}
//			if (!StringUtil.isEmpty(request.getParameter("createTimeEnd"))) {
//				productCustomCriteria.andCreateDateLessThanOrEqualTo(sf.parse(request.getParameter("createTimeEnd") + " 23:59:59"));
//			}
//			if (!StringUtil.isEmpty(request.getParameter("updateTimeBegin"))) {
//				productCustomCriteria.andUpdateDateGreaterThanOrEqualTo(sf.parse(request.getParameter("updateTimeBegin") + " 00:00:00"));
//			}
//			if (!StringUtil.isEmpty(request.getParameter("updateTimeEnd"))) {
//				productCustomCriteria.andUpdateDateLessThanOrEqualTo(sf.parse(request.getParameter("updateTimeEnd") + " 23:59:59"));
//			}
//
//			if (!StringUtil.isEmpty(request.getParameter("delTimeBegin"))) {
//				productCustomCriteria.andDelDateGreaterThanOrEqualTo(sf.parse(request.getParameter("delTimeBegin") + " 00:00:00"));
//			}
//			if (!StringUtil.isEmpty(request.getParameter("delTimeEnd"))) {
//				productCustomCriteria.andDelDateLessThanOrEqualTo(sf.parse(request.getParameter("delTimeEnd") + " 23:59:59"));
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

		if (!StringUtil.isEmpty(request.getParameter("searchKeywrod"))) {
			if ("1".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andNameLike("%" + request.getParameter("searchKeywrod") + "%");
			}
			if ("2".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andArtNoLike("%" + request.getParameter("searchKeywrod") + "%");
			}
			if ("3".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andCodeEqualTo(request.getParameter("searchKeywrod"));
			}
			if ("4".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andRemarksLike("%" + request.getParameter("searchKeywrod") + "%");
			}
			if ("5".equals(request.getParameter("searchKeywrodType"))) {
				try {
					Integer id=Integer.valueOf(request.getParameter("searchKeywrod"));
					productCustomCriteria.andSingleProductActivityIdEqualTo(id);
				} catch (NumberFormatException e) {
					productCustomCriteria.andSingleProductActivityIdEqualTo(0);
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
		
		productCustomExample.setLimitStart(page.getLimitSize()  * (pageNumber - 1));
		productCustomExample.setLimitSize(page.getLimitSize());
		List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);

		
		for (ProductCustom productCustom:productCustoms) {
			if(!StringUtil.isEmpty(productCustom.getPic()) && !productCustom.getPic().contains("http")){
				productCustom.setPic(FileUtil.getMiddleImageName(productCustom.getPic()));
			}
			
			//判断商品是否可删除
			if("1".equals(productCustom.getStatus())){//上架状态的都不可删除
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
	 * 商品回收站
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/productRecycleIndex")
	public String mchtBrandIndex(Model model, HttpServletRequest request) {
		List<ProductBrand> productBrandList = mchtProductBrandService.getMchtProductBrandList(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		return "product/productRecycleIndex";
	}



	@RequestMapping("/product/changeProductStatus")
	@ResponseBody
	public ResponseMsg changeProductStatus(HttpServletRequest request) {
		try {
			if (StringUtil.isEmpty(request.getParameter("id"))) {
				throw new ArgException("请选择商品");
			}
			
			Integer id=Integer.valueOf(request.getParameter("id"));
			
			Product product=productService.selectByPrimaryKey(id);
			
			if(!this.getSessionMchtInfo(request).getId().equals(product.getMchtId())){
				throw new ArgException("请刷新页面");
			}
			
			if("0".equals(product.getStatus())){
				
				if("2".equals(product.getAuditStatus())&&!"4".equals(product.getOffReason())){
					Product product4Update = new Product();
					product4Update.setId(id);
					product4Update.setStatus("1");
					productService.changeProductStatus(product4Update);
				}else{
					throw new ArgException("此商品不可上架!");
				}
				

			}
			
			if("1".equals(product.getStatus())){
				//查询营销活动是否有已报名改商品，有的话删除
				SourceNicheExample sourceNicheExample = new SourceNicheExample();
				sourceNicheExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andLinkIdEqualTo(id);
				List<SourceNiche> sourceNicheList = sourceNicheService.selectByExample(sourceNicheExample);
				if(!sourceNicheList.isEmpty()){
					SourceNiche sourceNiche = sourceNicheList.get(0);
					sourceNiche.setStatus("1");
					sourceNiche.setDelFlag("1");
					sourceNicheService.updateByPrimaryKeySelective(sourceNiche);
				}
				
				Product product4Update = new Product();
				product4Update.setId(id);
				product4Update.setStatus("0");
				product4Update.setOffReason("3");
				productService.changeProductStatus(product4Update);
			}
			

			
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}


	//批量下架
	@RequestMapping("/product/batchUndercarriage")
	@ResponseBody
	public ResponseMsg batchUndercarriage(HttpServletRequest request) {
		int successCount = 0;
		int errorCount = 0;
		try {
			if (StringUtil.isEmpty(request.getParameter("productIds"))) {
				throw new ArgException("请选择商品");
			}

			String ids= request.getParameter("productIds");
			List<String> strIds = Arrays.asList(ids.split(","));
			List<Integer> idsList = new ArrayList<>();
			for (String id : strIds) {
				idsList.add(Integer.parseInt(id));
			}

			ProductCustomExample productCustomExample = new ProductCustomExample();
			productCustomExample .createCriteria().andIdIn(idsList).andDelFlagEqualTo("0");
			List<ProductCustom> productList = productService.selectProductCustomByExample(productCustomExample);

			List<Integer> successIds = new ArrayList<>();
			for (ProductCustom productCustom : productList){
				if(!this.getSessionMchtInfo(request).getId().equals(productCustom.getMchtId())){
					throw new ArgException("请刷新页面");
				}

				//筛选出商品活动状态=可报名且是上架的商品
				if("1".equals(productCustom.getStatus()) && !"0".equals(productCustom.getAuditStatus()) && "0".equals(productCustom.getActivityStatusDesc())){
					successCount++;
					successIds.add(productCustom.getId());
				}
			}
			//批量修改状态
			if(successIds.size()>0){
				productService.batchUndercarriage(successIds,"0");
			}
			errorCount = idsList.size()-successCount;
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,"已成功下架"+successCount+"个,下架失败"+errorCount+"个。");
	}

	@RequestMapping("/product/delProduct")
	@ResponseBody
	public ResponseMsg delProduct(HttpServletRequest request) {
		try {
			if (StringUtil.isEmpty(request.getParameter("id"))) {
				throw new ArgException("请选择商品");
			}

			ProductExample productExample = new ProductExample();
			productExample.createCriteria().andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIdEqualTo(Integer.valueOf(request.getParameter("id")));

			Product product4Update = new Product();
			product4Update.setDelFlag("1");
			productService.updateByExampleSelective(product4Update, productExample);

		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	//批量删除
	@RequestMapping("/product/batchDelete")
	@ResponseBody
	public ResponseMsg batchDelete(HttpServletRequest request) {
		int successCount = 0;
		int errorCount = 0;
		try {
			if (StringUtil.isEmpty(request.getParameter("productIds"))) {
				throw new ArgException("请选择商品");
			}

			String ids= request.getParameter("productIds");
			List<String> strIds = Arrays.asList(ids.split(","));
			List<Integer> idsList = new ArrayList<>();
			for (String id : strIds) {
				idsList.add(Integer.parseInt(id));
			}

			ProductCustomExample productCustomExample = new ProductCustomExample();
			productCustomExample .createCriteria().andIdIn(idsList).andDelFlagEqualTo("0");
			List<ProductCustom> productList = productService.selectProductCustomByExample(productCustomExample);

			List<Integer> successIds = new ArrayList<>();
			for (ProductCustom productCustom : productList){
				if(!this.getSessionMchtInfo(request).getId().equals(productCustom.getMchtId())){
					throw new ArgException("请刷新页面");
				}

				//筛选出商品活动状态=可报名且是下架的商品
				if("0".equals(productCustom.getStatus()) && !"0".equals(productCustom.getAuditStatus()) && "0".equals(productCustom.getActivityStatusDesc())){
					successCount++;
					successIds.add(productCustom.getId());
				}
			}
			//批量修改状态
			if(successIds.size()>0){
				productService.batchUndercarriage(successIds,"1");
			}
			errorCount = idsList.size()-successCount;
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,"已成功删除"+successCount+"个,删除失败"+errorCount+"个。");
	}

	//批量修改库存
	@RequestMapping("/product/batchModifyStock")
	public ModelAndView batchModifyStock(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<>();
		String ids= request.getParameter("productIds");
		List<String> strIds = Arrays.asList(ids.split(","));
		List<Integer> idsList = new ArrayList<>();
		for (String id : strIds) {
			idsList.add(Integer.parseInt(id));
		}
		//查询所有商品信息及SKU信息
		ProductCustomExample productCustomExample = new ProductCustomExample();
		productCustomExample.createCriteria().andCustomIdIn(ids);
		List<ProductCustom> productCustomList = productService.selectProductAndPropValue(productCustomExample);
		//循环SKU数据,根据prop_val_id关联bu_product_prop_value
		StringBuilder propValIds = new StringBuilder();
		for (int i = 0;i<productCustomList.size();i++) {
			List<ProductItemCustom> productItemList = productCustomList.get(i).getProductItemList();
			for (int j = 0;j<productItemList.size();j++){
				//获取每个商品的prop_val_id
				propValIds.append(","+productItemList.get(j).getPropValId());
			}
			//图片处理
			if(StringUtils.isNotEmpty(productCustomList.get(i).getPic()) && productCustomList.get(i).getPic().indexOf("http")>=0){
				productCustomList.get(i).setCanDelete(true);
			}else{
				productCustomList.get(i).setCanDelete(false);
			}
		}
		Set<String> set = new HashSet<>(Arrays.asList(propValIds.toString().substring(1,propValIds.length()).split(",")));
		List<Integer> propValIdList = new ArrayList<>();
		for (String s : set){
			propValIdList.add(Integer.parseInt(s));
		}
		ProductPropValueExample productPropValueExample = new ProductPropValueExample();
		productPropValueExample.createCriteria().andIdIn(propValIdList).andDelFlagEqualTo("0");
		productCustomExample.setOrderByClause(" id asc");
		List<ProductPropValue> productPropValueList = productPropValueService.selectByExample(productPropValueExample);
		LinkedHashMap<String,ProductPropValue> map = new LinkedHashMap<>();
		for (ProductPropValue productPropValue : productPropValueList){
			if(1 == productPropValue.getPropId() || 2 == productPropValue.getPropId()){//筛选掉不是尺码跟颜色的属性
				map.put(String.valueOf(productPropValue.getId()),productPropValue);
			}
		}

		//给每个商品添加尺码、颜色
		for (int i = 0;i<productCustomList.size();i++) {
			LinkedHashSet<ProductPropValue> sizeValueList = new LinkedHashSet<>();
			LinkedHashSet<ProductPropValue> colorValueList = new LinkedHashSet<>();
			List<ProductItemCustom> productItemList = productCustomList.get(i).getProductItemList();
			for (int j = 0;j<productItemList.size();j++){
				String[] split = productItemList.get(j).getPropValId().split(",");
				for (String s : split){
					if(map.containsKey(s)) {
						if (map.get(s).getPropId() == 2) {//尺码
							sizeValueList.add(map.get(s));
							productItemList.get(j).setSizeValue(map.get(s).getPropValue());
						} else {//颜色
							colorValueList.add(map.get(s));
							productItemList.get(j).setColorValue(map.get(s).getPropValue());
						}
					}
				}
			}
			//一个商品多个规格的尺码、颜色信息
			productCustomList.get(i).setSizeValueList(sizeValueList);
			productCustomList.get(i).setColorValueList(colorValueList);
		}

		paramMap.put("productCustomList",productCustomList);
		return new ModelAndView("product/batchModifyStock",paramMap);
	}

	//修改库存
	@RequestMapping("/product/changeStock")
	@ResponseBody
	public ResponseMsg changeStock(HttpServletRequest request) {
		boolean type = false;
		try {
			if(StringUtils.isNotEmpty(request.getParameter("stock"))){
				int productItemId = Integer.parseInt(request.getParameter("productItemId"));
				int stock = Integer.parseInt(request.getParameter("stock"));
				ProductItemCustom productItemCustom = productItemService.selectByPrimaryKey(productItemId);
				if(productItemCustom.getStock() == stock){
					return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,type);
				}
				ProductItem productItem = new ProductItem();
				productItem.setId(productItemId);
				productItem.setStock(stock);
				productItemService.updateByPrimaryKeySelective(productItem);
				type = true;
			}
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,type);
	}

	//查看冻结库存
	@RequestMapping("/product/getLockedAmount")
	@ResponseBody
	public ResponseMsg getLockedAmount(HttpServletRequest request) {
		try {
				int productItemId = Integer.parseInt(request.getParameter("productItemId"));
				ProductItemCustom productItemCustom = productItemService.selectByPrimaryKey(productItemId);
				return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,productItemCustom.getLockedAmount());
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}

	//批量调价
	@RequestMapping("/product/batchAdjustPrice")
	@ResponseBody
	public ResponseMsg batchAdjustPrice(HttpServletRequest request) {
		return productItemService.batchAdjustPrice(request);
	}

	/**
	 * 商品还原
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/product/productRecover")
	@ResponseBody
	public ResponseMsg productRecover(HttpServletRequest request) {
		try {
			if (StringUtil.isEmpty(request.getParameter("id"))) {
				throw new ArgException("请选择商品");
			}

			ProductExample productExample = new ProductExample();
			productExample.createCriteria().andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIdEqualTo(Integer.valueOf(request.getParameter("id")));

			Product product4Update = new Product();
			product4Update.setDelFlag("0");
			productService.updateByExampleSelective(product4Update, productExample);

		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}

		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}

	/**
	 * 添加商品
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/productAdd")
	public String productAdd(Model model, HttpServletRequest request) {
		Product product = new Product();
		model.addAttribute("product", product);
		List<MchtProductBrandCustom> productBrandList = mchtProductBrandService.getMchtUsebleProductBrand(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);

		ProductPropExample productPropExample = new ProductPropExample();
		productPropExample.createCriteria().andDelFlagEqualTo("0").andIdNotEqualTo(0).andNameNotEqualTo("款式");
		List<ProductProp> productPropList = productPropService.selectByExample(productPropExample);
		List<ProductProp> productProps4SelectList=new ArrayList<ProductProp>();
		//当商家的主营类目为：运动、服饰，规格名的选项，只需显示：颜色、尺码
		//当商家的主营类目为：鞋包，规格名的选项，只需显示：颜色、尺码、款式、尺寸
        MchtProductTypeExample mchtProductTypeExample=new MchtProductTypeExample();
        mchtProductTypeExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andIsMainEqualTo("1");
		List<MchtProductType> mchtProductTypes=mchtProductTypeService.selectByExample(mchtProductTypeExample);
		
		if(mchtProductTypes!=null&&mchtProductTypes.size()>0){
			ProductType productType=productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
			//当主营类目是食品超市时，获取参数表的保健品、水果蔬菜参数值
			if("食品超市".equals(productType.getName())){
				SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
				sysParamCfgExample.createCriteria().andParamCodeIn(Arrays.asList("PRODUCT_TYPE2_HEALTH_IDS","PRODUCT_TYPE2_FRUITS_IDS"));
				List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(sysParamCfgExample);
				model.addAttribute("sysParamCfg1",sysParamCfgList.get(0));
				model.addAttribute("sysParamCfg2",sysParamCfgList.get(1));
				model.addAttribute("isShop", true);
			}
			if("运动户外".equals(productType.getName())||"服装配饰".equals(productType.getName())){
				for(ProductProp productProp:productPropList){
					if("颜色分类".equals(productProp.getName())||"尺码".equals(productProp.getName())){
						productProps4SelectList.add(productProp);
					}
				}
			}else if("鞋靴箱包".equals(productType.getName())){
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
		
		
		//获取最近添加的商品的品类
		
		
		Product p =  productService.getLastProduct4UsebleProductType(this.getSessionMchtInfo(request).getId());
		
		if(p!=null){
			ProductType productType1 = productTypeService.selectByPrimaryKey(p.getProductType1Id());
			ProductType productType2 = productTypeService.selectByPrimaryKey(p.getProductType2Id());
			ProductType productType3 = productTypeService.selectByPrimaryKey(p.getProductTypeId());
			model.addAttribute("productType1", productType1);
			model.addAttribute("productType2", productType2);
			model.addAttribute("productType3", productType3);
		}else{
			model.addAttribute("productType1", null);
			model.addAttribute("productType2", null);
			model.addAttribute("productType3", null);
		}
		
		
		
		model.addAttribute("productPropList", productProps4SelectList);
		model.addAttribute("mchtInfoStatus", this.getMchtInfo().getStatus());
		model.addAttribute("saleType", request.getParameter("saleType"));
		model.addAttribute("shopProduct", request.getParameter("shopProduct"));
		FreightTemplateExample example = new FreightTemplateExample();
		FreightTemplateExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		List<FreightTemplate> freightTemplates = freightTemplateService.selectByExample(example);
		model.addAttribute("freightTemplates", freightTemplates);
		ProductBrandExample pbe = new ProductBrandExample();
		pbe.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andNameEqualTo("小商品");
		List<ProductBrand> productBrands = productBrandService.selectByExample(pbe);
		Integer smallwareBrandId = 0;
		if(productBrands!=null && productBrands.size()>0){
			smallwareBrandId = productBrands.get(0).getId();
		}
		model.addAttribute("smallwareBrandId", smallwareBrandId);

		return "product/productAdd";
	}
	
	
	/**
	 * 修改商品
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/productEdit")
	public String productEdit(Model model, HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		
		Product product = productService.selectByPrimaryKey(id);
		if(!StringUtil.isEmpty(product.getProductDesc())){
			product.setProductDesc(product.getProductDesc().replace("&&&", "\n"));
		}
		model.addAttribute("product", product);

		//商品拓展类
        ProductExtendExample productExtendExample = new ProductExtendExample();
        productExtendExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(product.getId());
        List<ProductExtend> productExtends = productExtendService.selectByExample(productExtendExample);
        if(!productExtends.isEmpty()){
            model.addAttribute("productExtend", productExtends.get(0));
        }
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
			//当主营类目是食品超市时，获取参数表的保健品、水果蔬菜参数值
			if("食品超市".equals(productType.getName())){
				SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
				sysParamCfgExample.createCriteria().andParamCodeIn(Arrays.asList("PRODUCT_TYPE2_HEALTH_IDS","PRODUCT_TYPE2_FRUITS_IDS"));
				List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(sysParamCfgExample);
				model.addAttribute("sysParamCfg1",sysParamCfgList.get(0));
				model.addAttribute("sysParamCfg2",sysParamCfgList.get(1));
				model.addAttribute("isShop", true);
			}
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
				List<Integer> cloudProductItemIdList=new ArrayList<Integer>();
				for(ProductItem productItem:productItems){
					cloudProductItemIdList.add(productItem.getCloudProductItemId());
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
		
		model.addAttribute("shopProduct", request.getParameter("shopProduct"));
		model.addAttribute("pageNumber", request.getParameter("pageNumber"));
		model.addAttribute("s_auditStatus", request.getParameter("s_auditStatus"));
		FreightTemplateExample example = new FreightTemplateExample();
		FreightTemplateExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		List<FreightTemplate> freightTemplates = freightTemplateService.selectByExample(example);
		model.addAttribute("freightTemplates", freightTemplates);
		if(product.getFreightTemplateId()!=null){
			ProvinceFreightExample e = new ProvinceFreightExample();
			ProvinceFreightExample.Criteria criteria = e.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
			criteria.andFreightTemplateIdEqualTo(product.getFreightTemplateId());
			List<ProvinceFreightCustom> provinceFreightCustoms = provinceFreightService.selectByCustomExample(e);
			model.addAttribute("provinceFreightCustoms", provinceFreightCustoms);
		}
		model.addAttribute("from", request.getParameter("from"));
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(this.getSessionMchtInfo(request).getId());
		model.addAttribute("supplyChainStatus", mchtInfo.getSupplyChainStatus());
		String productActivityStatus = productService.getProductActivityStatus(id);
		model.addAttribute("productActivityStatus", productActivityStatus);
		if(product.getSvipDiscount()!=null){
			DecimalFormat df = new DecimalFormat("#.00");
			model.addAttribute("svipDiscount", df.format(product.getSvipDiscount()));
			
		}
		ProductBrandExample pbe = new ProductBrandExample();
		pbe.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andNameEqualTo("小商品");
		List<ProductBrand> productBrands = productBrandService.selectByExample(pbe);
		Integer smallwareBrandId = 0;
		if(productBrands!=null && productBrands.size()>0){
			smallwareBrandId = productBrands.get(0).getId();
		}
		model.addAttribute("smallwareBrandId", smallwareBrandId);
		ProductVideoExample e = new ProductVideoExample();
		e.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(id);
		List<ProductVideo> productVideos = productVideoMapper.selectByExample(e);
		if(productVideos!=null && productVideos.size()>0){
			for(ProductVideo productVideo:productVideos){
				if(productVideo.getVideoType().equals("1")){//1.商品主图
					model.addAttribute("mainVideoUrl", productVideo.getVideoUrl());
					model.addAttribute("videoName", productVideo.getVideoUrl().substring(productVideo.getVideoUrl().lastIndexOf("/")+1));
					model.addAttribute("mainVideoSize", productVideo.getSize());
				}else if(productVideo.getVideoType().equals("2")){//2.商品描述
					model.addAttribute("descVideoUrl", productVideo.getVideoUrl());
					model.addAttribute("descVideoSize", productVideo.getSize());
					model.addAttribute("videoCover", productVideo.getVideoCover());
				}
			}
		}
		return "product/productEdit";
	}
	
	/**
	 * 修改SKU
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/productSkuEdit")
	public String productSkuEdit(Model model, HttpServletRequest request) {
		Integer id=Integer.valueOf(request.getParameter("id"));
		
		Product product = productService.selectByPrimaryKey(id);
		model.addAttribute("product", product);

		String activityStatusDesc;
		if("1".equals(product.getSaleType())){
			activityStatusDesc = productService.getShopProductActivityStatus(id);
		}else{
			activityStatusDesc = productService.getSingleProductActivityStatus(id);
		}
		if(product.getAuditStatus().equals("0")){
			activityStatusDesc = "6";
		}else if(product.getAuditStatus().equals("3")){
			activityStatusDesc = "7";
		}
		
   		model.addAttribute("activityStatusDesc", activityStatusDesc);
   		
		ProductPropExample productPropExample = new ProductPropExample();
		productPropExample.createCriteria().andDelFlagEqualTo("0").andIdNotEqualTo(0);;
		List<ProductProp> productPropList = productPropService.selectByExample(productPropExample);
		model.addAttribute("productPropList", productPropList);
		
		
			//获取商品SKU
			ProductItemExample productItemExample=new ProductItemExample();
			productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(id);
			List<ProductItem> productItems=productItemService.selectByExample(productItemExample);
			
			
			if(productItems!=null&&productItems.size()>0){
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
				List<Integer> propIds=new ArrayList<Integer>();
				for(ProductPropValueCustom productPropValueCustom:productPropValues){
					propMap=productPropMap.get(productPropValueCustom.getPropId());
					if(propMap==null){
						propMap=new HashMap<String, Object>();
						propMap.put("propName", productPropValueCustom.getPropName());
						productPropValuesList=new ArrayList<>();
						propMap.put("productPropValues", productPropValuesList);
						productPropMap.put(productPropValueCustom.getPropId(), propMap);
						propIds.add(productPropValueCustom.getPropId());
					}else{
						productPropValuesList=(ArrayList)propMap.get("productPropValues");
					}
					productPropValuesList.add(productPropValueCustom);
					
				}
				
				model.addAttribute("props", productPropMap);
				model.addAttribute("propIds", propIds);
				
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
		model.addAttribute("from", request.getParameter("from"));
		String productActivityStatus = productService.getProductActivityStatus(id);
		model.addAttribute("productActivityStatus", productActivityStatus);
		if(product.getSvipDiscount()!=null){
			DecimalFormat df = new DecimalFormat("#.00");
			model.addAttribute("svipDiscount", df.format(product.getSvipDiscount()));
			
		}
		
		//判断是否是参加营销活动的商品
		String marketTag = "0";
		SourceNicheExample sourceNicheExample = new SourceNicheExample();
		sourceNicheExample.createCriteria().andLinkIdEqualTo(id);
		List<SourceNiche> sourceNicheList = sourceNicheService.selectByExample(sourceNicheExample);
		if(!sourceNicheList.isEmpty()){
			marketTag = "1";
		}
		model.addAttribute("marketTag",marketTag);
		return "product/productSkuEdit";
	}
	
	

	/**
	 * 获取商品sku数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/getProductItemDatas")
	@ResponseBody
	public ResponseMsg getProductItemDatas(Model model, HttpServletRequest request, Product product) {
		List<JSONObject> resultDataList=new ArrayList<>();
		Integer id=Integer.valueOf(request.getParameter("id"));
		try {
			//获取商品SKU
			
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("productId", id);
			
			ProductItemExample productItemExample1=new ProductItemExample();
			productItemExample1.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(id);
			productItemExample1.setLimitStart(0);
			productItemExample1.setLimitSize(1);
			List<ProductItem> productItems1=productItemService.selectByExample(productItemExample1);
			if(productItems1!=null&&productItems1.size()>0){
				String[] propValueArr=productItems1.get(0).getPropValId().split(",");
				if(propValueArr.length>3){
					paramMap.put("propCount", 3);
				}else{
					paramMap.put("propCount", propValueArr.length);
				}
			}

			
			List<ProductItem> productItems=productItemService.selectProductItemOrderByPropValue(paramMap);
			
			
			
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
			List<ProductPropValue> productPropValues=productPropValueService.selectByExample(productPropValueExample);
			Map<Integer, ProductPropValue> propVauleMap=new HashMap<Integer, ProductPropValue>();
			for(ProductPropValue productPropValue:productPropValues){
				propVauleMap.put(productPropValue.getId(), productPropValue);
				
			}
			
			JSONObject productItemObject;
			
			for(ProductItem productItem:productItems){
				productItemObject=new JSONObject();
				productItemObject.put("skuPic", productItem.getPic().contains("http")?productItem.getPic():(request.getContextPath()+"/file_servelt"+productItem.getPic()));
				productItemObject.put("tagPrice", productItem.getTagPrice());
				productItemObject.put("mallPrice", productItem.getMallPrice());
				productItemObject.put("salePrice", productItem.getSalePrice());
				productItemObject.put("lockedAmount", productItem.getLockedAmount());
				productItemObject.put("stock", productItem.getStock());
				productItemObject.put("sku", productItem.getSku()==null?"":productItem.getSku());
				productItemObject.put("productItemId", productItem.getId());
				productItemObject.put("cloudProductItemId", productItem.getCloudProductItemId()==null?"":productItem.getCloudProductItemId());
				productItemObject.put("skuPicBase64", "");
				productItemObject.put("costPrice", productItem.getCostPrice());
				productItemObject.put("sourceSkuPicItemId", "");
				productItemObject.put("isSourceSkuPicItem", "0");
				productItemObject.put("manageSelfFreight", productItem.getManageSelfFreight() == null?"":productItem.getManageSelfFreight());
				if(productItem.getCloudProductItemId()!=null && productItem.getCloudProductItemId()!=0){
					String supplierStatus = cloudProductItemService.getSupplierStatusByItemId(productItem.getCloudProductItemId());
					HashMap<String,Object> map = new HashMap<String,Object>();
					map.put("id", productItem.getCloudProductItemId());
					map.put("mchtId", this.getSessionMchtInfo(request).getId());
					String mchtSupplierStatus = cloudProductItemService.getMchtSupplierStatus(map);
					if("1".equals(supplierStatus) && "1".equals(mchtSupplierStatus)){
						productItemObject.put("readOnly", "1");
					}else{
						productItemObject.put("readOnly", "0");
					}
				}else{
					productItemObject.put("readOnly", "0");
				}
				String[] propValueIds=productItem.getPropValId().split(",");
				for (int i = 0; i < propValueIds.length; i++) {
					ProductPropValue productPropValue=propVauleMap.get(Integer.valueOf(propValueIds[i]));
					productItemObject.put(productPropValue.getPropId(), productPropValue.getPropValue());
				}
				productItemObject.put("DT_RowId", "oldItemData_"+productItem.getId());
				resultDataList.add(productItemObject);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,resultDataList);

	}
	
	
	

	/**
	 * 添加商品提交
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/productAddCommit")
	@ResponseBody
	public ResponseMsg productAddCommit(Model model, HttpServletRequest request, Product product) {

		try {
			
			if(StringUtil.isEmpty(product.getSaleType())){
				throw new ArgException("请选择商品销售类型");
			}
			
			if(StringUtil.isEmpty(product.getYearOfListing())){
				throw new ArgException("请选择上市年份");
			}
			
			if("".equals(product.getName().trim())){
				throw new ArgException("请输入商品名称");
			}
			
			if(StringUtil.isEmpty(request.getParameter("productMainPics"))){
				throw new ArgException("商品主图不能为空");
			}
			if(StringUtil.isEmpty(request.getParameter("productDescPics"))){
				throw new ArgException("商品详情图不能为空");
			}

			String productMainPics=request.getParameter("productMainPics");
			String productDescPics=request.getParameter("productDescPics");
			String foodLabelPics=request.getParameter("foodLabelPics");

			String commitType=request.getParameter("commitType");
			
			
			if ("1".equals(product.getSaleType())) {
				//品牌款 点击【保存草稿】，SET audit_status=未提审 点击【发布并上架】，SET audit_status=通过,status=上架
				if ("1".equals(commitType)) {
					product.setAuditStatus("2");
					product.setStatus("1");
				} else {
					product.setAuditStatus("0");
					product.setStatus("0");
					product.setOffReason("3");
				}
			} else {
				//单品款  点击【提交审核】，SET audit_status=待审核,status=下架，下架原因=待审校
				product.setAuditStatus("1");
				product.setStatus("0");
				product.setOffReason("1");
			}
			
			product.setName(product.getName().trim());
			product.setMchtId(this.getSessionMchtInfo(request).getId());

			product.setCreateDate(new Date());
			product.setDelFlag("0");
			product.setCreateBy(this.getSessionUserInfo(request).getId());
			JSONArray productItems = JSONArray.fromObject(request.getParameter("productItems"));
			JSONArray propValueList;
			propValueList=StringUtil.isEmpty(request.getParameter("propValueList"))?null:JSONArray.fromObject(request.getParameter("propValueList"));
			productService.addProduct(product, productItems, propValueList, this.getSessionMchtInfo(request),productMainPics,productDescPics,foodLabelPics,request,commitType);
			
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);

	}
	
	
	/**
	 * 编辑商品提交
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/productEditCommit")
	@ResponseBody
	public ResponseMsg productEditCommit(Model model, HttpServletRequest request, Product product) {
		
		try {
			
			
			if(product.getId()==null){
				throw new ArgException("商品不存在");
			}
			
			if("".equals(product.getName().trim())){
				throw new ArgException("请输入商品名称");
			}
			
			if(StringUtil.isEmpty(request.getParameter("productMainPics"))){
				throw new ArgException("商品主图不能为空");
			}
			if(StringUtil.isEmpty(request.getParameter("productDescPics"))){
				throw new ArgException("商品详情图不能为空");
			}
			
			
			//原因=强制下架  ,普通款 &&上架中,活动款 && 等于活动中 --- 不能修改商品
			
			ProductCustom oldProduct=productService.selectProductCustomByPrimaryKey(product.getId());
			
//			if("4".equals(oldProduct.getOffReason())||("1".equals(oldProduct.getSaleType())&&"1".equals(oldProduct.getStatus()))||("2".equals(oldProduct.getSaleType())&&!"0".equals(oldProduct.getActivityStatusDesc())&&!"6".equals(oldProduct.getActivityStatusDesc())&&!"7".equals(oldProduct.getActivityStatusDesc()))){
//				throw new ArgException("商品不能修改");
//			}
//			if("4".equals(oldProduct.getOffReason())||!("0".equals(oldProduct.getActivityStatusDesc())||"0".equals(oldProduct.getAuditStatus())||"3".equals(oldProduct.getAuditStatus()))){
//				throw new ArgException("商品不能修改");
//			}
			if(!StringUtil.isEmpty(product.getSaleType()) && !product.getSaleType().equals(oldProduct.getSaleType())){//修改商品类型时，需要判断是否在活动中
				String status = productService.getProductActivityStatus(oldProduct.getId());
				if(!"0".equals(status)){
					throw new ArgException("活动中的商品无法修改商品类型");
				}
			}
			
			String productMainPics=request.getParameter("productMainPics");
			String productDescPics=request.getParameter("productDescPics");
			String commitType=request.getParameter("commitType");
            String foodLabelPics=request.getParameter("foodLabelPics");
			if ("1".equals(product.getSaleType())) {
//				if("0".equals(product.getAuditStatus())||"2".equals(product.getAuditStatus())){
					//品牌款 点击【保存草稿】，SET audit_status=未提审 点击【发布并上架】，SET audit_status=通过,status=上架
					if ("1".equals(commitType)) {
						product.setAuditStatus("2");
						product.setStatus("1");

						if(!"3".equals(oldProduct.getAuditStatus())){
							//商品上下架日志
							ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
							productUpperLowerLog.setProductId(product.getId());
							productUpperLowerLog.setStatus(product.getStatus());
							productUpperLowerLog.setType(Const.BUSINESS);
							productUpperLowerLog.setCreateBy(product.getUpdateBy());
							productUpperLowerLog.setCreateDate(new Date());
							productUpperLowerLogService.insertSelective(productUpperLowerLog);
						}
					} else {
						product.setAuditStatus("0");
						product.setStatus("0");
						product.setOffReason("3");
					}
//				}
				if("1".equals(oldProduct.getAuditStatus())||"3".equals(oldProduct.getAuditStatus())){
					//品牌款 编辑商品为audit_status=驳回 or audit_status=待审核 点击【提交审核】，SET audit_status=待审核,status=下架，下架原因=待审校
					if ("1".equals(commitType)) {
						product.setAuditStatus("1");
						product.setStatus("0");
						product.setOffReason("1");

						if(!"3".equals(oldProduct.getAuditStatus())){
							//商品上下架日志
							ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
							productUpperLowerLog.setProductId(product.getId());
							productUpperLowerLog.setStatus(product.getStatus());
							productUpperLowerLog.setType(Const.BUSINESS);
							productUpperLowerLog.setCreateBy(product.getUpdateBy());
							productUpperLowerLog.setCreateDate(new Date());
							productUpperLowerLogService.insertSelective(productUpperLowerLog);
						}
					} else {
						throw new ArgException("不可保存草稿");
					}
				}
			} else {
				//单品款  点击【提交审核】，SET audit_status=待审核,status=下架，下架原因=待审校
				product.setAuditStatus("1");
				product.setStatus("0");
				product.setOffReason("1");
			}
			
			product.setName(product.getName().trim());
			product.setUpdateDate(new Date());
			product.setUpdateBy(this.getSessionUserInfo(request).getId());
			JSONArray productItems = JSONArray.fromObject(request.getParameter("productItems"));
			JSONArray propValueList= JSONArray.fromObject(request.getParameter("propValueList"));
			productService.editProduct(product, productItems, propValueList, this.getSessionMchtInfo(request),productMainPics,productDescPics,foodLabelPics,request);
			
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	/**
	 * 修改该商品SKU提交
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/productEditSkuCommit")
	@ResponseBody
	public ResponseMsg productEditSkuCommit(Model model, HttpServletRequest request,Product product) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			if(product.getId()==null){
				throw new ArgException("商品不存在");
			}
			
			Product oldProduct=productService.selectByPrimaryKey(product.getId());
			
			if(!oldProduct.getMchtId().equals(this.getSessionMchtInfo(request).getId())){
				throw new ArgException("没权修改");
			}
			if(!StringUtil.isEmpty(product.getSaleType()) && !product.getSaleType().equals(oldProduct.getSaleType())){//修改商品类型时，需要判断是否在活动中
				String status = productService.getProductActivityStatus(oldProduct.getId());
				if(!"0".equals(status)){
					throw new ArgException("活动中的商品无法修改商品类型");
				}
			}
			product.setUpdateBy(this.getSessionUserInfo(request).getId());
			product.setUpdateDate(new Date());
			JSONArray productItems = JSONArray.fromObject(request.getParameter("productItems"));
			productService.editProductSku(product,productItems,this.getSessionMchtInfo(request));
			ProductCustom productCustom = productService.selectProductCustomByPrimaryKey(product.getId());
			returnData.put("productCustom", productCustom);
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
		
	}

	

	/**
	 * 商品选择页
	 */
	@RequestMapping("/product/select")
	public String productSelect() {
		return "product/productSelect";
	}

	/**
	 * 活动商品列表
	 */
	@ResponseBody
	@RequestMapping("/product/list")
	public String listSelect(QueryObject queryObject) {
		MchtUser mchtUser = getUserInfo();

		queryObject.addQuery("delFlag", "0");
		queryObject.addQuery("saleType", "1");
		queryObject.addQuery("freeProduct", "闲置商品");
		queryObject.addQuery("productInActivity", getPara("activityId"));
		queryObject.addQuery("mchtId", mchtUser.getMchtId());
		queryObject.addQuery("name", getPara("name"));
		queryObject.addQuery("artNo", getPara("artNo"));
		queryObject.addSort("create_date", QueryObject.SORT_DESC);
		com.jf.common.ext.query.Page<Product> page = productService.paginate(queryObject);

		Map<String, Object> data = new HashMap<>();
		data.put("page", page);
		for (Product product : page.getList()) {
			productService.fillInfo(product);
		}
		return json(data);
	}
	
	/**
	 * 添加售后模版
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/product/toProductAfterTempletAdd")
	public String toProductAfterTempletAdd(Model model,HttpServletRequest request) {
		ProductAfterTemplet productAfterTemplet=new ProductAfterTemplet();
		productAfterTemplet.setMchtId(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productAfterTemplet", productAfterTemplet);
		
		List<MchtProductBrandCustom> productBrandList = mchtProductBrandService.getMchtUsebleProductBrand(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		
		model.addAttribute("brandId", request.getParameter("brandId"));
		
		return "/product/productAfterTempletAdd";
	}
	
	
	/**
	 * 编辑商品提交
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/setRemarks")
	@ResponseBody
	public ResponseMsg setRemarks( HttpServletRequest request) {
		
		try {
			String[] ids=request.getParameter("ids").split(",");
			if(ids.length<=0){
				throw new ArgException("备注出错");
			}
			
			List<Integer> idList=new ArrayList<Integer>();
			for (int i = 0; i < ids.length; i++) {
				if(!StringUtil.isEmpty(ids[i])){
					idList.add(Integer.valueOf(ids[i]));
				}
			}
			
			Product product4Update=new Product();
			product4Update.setRemarksColor(request.getParameter("remarksColor"));
			String remarks = request.getParameter("remarks");
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher m = p.matcher(remarks);
			remarks = m.replaceAll("");
			remarks = remarks.replaceAll(" +", " ");
			product4Update.setRemarks(remarks);
			product4Update.setUpdateBy(this.getSessionUserInfo(request).getId());
			product4Update.setUpdateDate(new Date());
			
			ProductExample productExample=new ProductExample();
			productExample.createCriteria().andIdIn(idList);
			
			productService.updateByExampleSelective(product4Update, productExample);
			
		} catch (ArgException e) {
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	/**
	 * 批量设置SVIP折扣提交
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/setSvipDiscount")
	@ResponseBody
	public ResponseMsg setSvipDiscount( HttpServletRequest request) {
		
		try {
			String[] productIds=request.getParameter("productIds").split(",");
			if(productIds.length<=0){
				throw new ArgException("设置出错");
			}
			
			List<Integer> idList=new ArrayList<Integer>();
			for (int i = 0; i < productIds.length; i++) {
				if(!StringUtil.isEmpty(productIds[i])){
					Integer productId = Integer.valueOf(productIds[i]);
					String productActivityStatus = productService.getProductActivityStatus(productId);
					if(productActivityStatus.equals("0")){
						idList.add(productId);
					}
				}
			}
			if(idList.size()>0){
				Product product4Update=new Product();
				product4Update.setSvipDiscount(new BigDecimal(request.getParameter("svipDiscount")));
				product4Update.setUpdateBy(this.getSessionUserInfo(request).getId());
				product4Update.setUpdateDate(new Date());
				
				ProductExample productExample=new ProductExample();
				productExample.createCriteria().andIdIn(idList);
				
				productService.updateByExampleSelective(product4Update, productExample);
			}
			
		} catch (ArgException e) {
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	public static void main(String[] args) {
		String a="红/黄/绿 蓝\\";
		System.out.println(a.replaceAll("/", "_").replace(" ","_").replace("\\", "_"));
	}

	
	/**
	 * 生成商品小图
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/createSmallProductImage")
	@ResponseBody
	public ResponseMsg createSmallProductImage( HttpServletRequest request) {
		InputStream stream = ProductController.class.getResourceAsStream("/config.properties");
		String defaultPath=null;
		try {
			Properties properties = new Properties();
			properties.load(stream);
			defaultPath = properties.getProperty("annex.filePath");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(defaultPath==null){
			return new ResponseMsg(ResponseMsg.ERROR, "文件目录不存在");
		}
		try {
			
//			//商品主图生成中图
//			ProductPicExample productPicExample=new ProductPicExample();
//			productPicExample.createCriteria().andDelFlagEqualTo("0");
//			List<ProductPic> productPics=productPicService.selectByExample(productPicExample);
//			for(ProductPic productPic:productPics){
//				String sourceFileName=productPic.getPic();
//				String middleFileName=FileUtil.getMiddleImageName(sourceFileName);
//				File targetFlie=new File(defaultPath.concat(middleFileName));
//				File sourceFile=new File(defaultPath.concat(sourceFileName));
//				if((!targetFlie.exists())&&sourceFile.exists()){
//					FileUtil.resizeImage(sourceFileName, middleFileName, 320, 320);
//				}
//			}
//			
//			//商品sku图生成小图
//			ProductItemExample productItemExample=new ProductItemExample();
//			productItemExample.createCriteria().andDelFlagEqualTo("0");
//			List<ProductItem> productItemList=productItemService.selectByExample(productItemExample);
//			for (ProductItem productItem:productItemList) {
//				if(!StringUtil.isEmpty(productItem.getPic())){
//					String sourceFileName=productItem.getPic();
//					String smallFileName=FileUtil.getSmallImageName(sourceFileName);
//					File targetFlie=new File(defaultPath.concat(smallFileName));
//					File sourceFile=new File(defaultPath.concat(sourceFileName));
//					if((!targetFlie.exists())&&sourceFile.exists()){
//						FileUtil.resizeImage(sourceFileName, smallFileName, 240, 240);
//					}
//				}
//			}
			
//			OrderDtlExample orderDtlExample=new OrderDtlExample();
//			List<OrderDtl> orderDtls=orderDtlService.selectByExample(orderDtlExample);
//			for(OrderDtl orderDtl:orderDtls){
//				if(!StringUtil.isEmpty(orderDtl.getSkuPic())){
//				String sourceFileName=orderDtl.getSkuPic();
//				String smallFileName=FileUtil.getSmallImageName(sourceFileName);
//				File targetFlie=new File(defaultPath.concat(smallFileName));
//				File sourceFile=new File(defaultPath.concat(sourceFileName));
//				if((!targetFlie.exists())&&sourceFile.exists()){
//					FileUtil.resizeImage(sourceFileName, smallFileName, 240, 240);
//				}
//			}
//			}
			
			
			
			//生成主图压缩图
			ProductPicExample productPicExample=new ProductPicExample();
			productPicExample.createCriteria().andDelFlagEqualTo("0");
			List<ProductPic> productPics=productPicService.selectByExample(productPicExample);
			for(ProductPic productPic:productPics){
				String sourceFileName=productPic.getPic();
				String middleFileName=FileUtil.getCompressImageName(sourceFileName, 0.7f);
				File targetFlie=new File(defaultPath.concat(middleFileName));
				File sourceFile=new File(defaultPath.concat(sourceFileName));
				if((!targetFlie.exists())&&sourceFile.exists()){
					FileUtil.compressImage(sourceFileName, middleFileName, 0.7f);
				}
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	/**
	 * 生成商品小图
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/createSmallProductImage1")
	@ResponseBody
	public ResponseMsg createSmallProductImage1( HttpServletRequest request) {
		InputStream stream = ProductController.class.getResourceAsStream("/config.properties");
		String defaultPath=null;
		try {
			Properties properties = new Properties();
			properties.load(stream);
			defaultPath = properties.getProperty("annex.filePath");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(defaultPath==null){
			return new ResponseMsg(ResponseMsg.ERROR, "文件目录不存在");
		}
		try {
			
			
			
			//详情图压缩图
			ProductDescPicExample productDescPicExample=new ProductDescPicExample();
			productDescPicExample.createCriteria().andDelFlagEqualTo("0").andIdBetween(0, 50000);
			List<ProductDescPic> productDescPics=productDescPicService.selectByExample(productDescPicExample);
			for(ProductDescPic productPic:productDescPics){
				String sourceFileName=productPic.getPic();
				String middleFileName=FileUtil.getCompressImageName(sourceFileName, 0.7f);
				File targetFlie=new File(defaultPath.concat(middleFileName));
				File sourceFile=new File(defaultPath.concat(sourceFileName));
				if((!targetFlie.exists())&&sourceFile.exists()){
					FileUtil.compressImage(sourceFileName, middleFileName, 0.7f);
				}
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	
	/**
	 * 导出订单excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/product/exportProduct")
	public void exportProduct(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ProductCustomExample productCustomExample = new ProductCustomExample();
		ProductCustomExample.ProductCustomCriteria productCustomCriteria = productCustomExample.createCriteria();

		productCustomCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		productCustomExample.setOrderByClause("id desc");

		if (!StringUtil.isEmpty(request.getParameter("delFlag"))) {
			productCustomCriteria.andDelFlagEqualTo(request.getParameter("delFlag"));
		} else {
			productCustomCriteria.andDelFlagEqualTo("0");
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
		
		String saleType=request.getParameter("search_saleType");
		
		if(!StringUtil.isEmpty(saleType)){
			productCustomCriteria.andSaleTypeEqualTo(saleType);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("search_status"))){
			productCustomCriteria.andStatusEqualTo(request.getParameter("search_status"));
		}
		if(!StringUtil.isEmpty(request.getParameter("search_productActivityStatus"))){
			String productActivityStatus=request.getParameter("search_productActivityStatus");
			if("0".equals(productActivityStatus)){
				productCustomCriteria.andAuditStatusNotEqualTo("0");
			}
			if(productActivityStatus.equals("6")){
				productCustomCriteria.andAuditStatusEqualTo("0");
			}else if(productActivityStatus.equals("7")){
				productCustomCriteria.andAuditStatusEqualTo("3");
			}else{
				if("1".equals(saleType)){
					productCustomCriteria.andShopProductActivityStatusEqualTo(productActivityStatus);
				}
				if("2".equals(saleType)){
					productCustomCriteria.andSingleProductActivityStatusEqualTo(productActivityStatus);
				}
			}
		}
		
		if("2".equals(saleType)){
			productCustomCriteria.andBrandIsEffect();
			productCustomCriteria.andAuditStatusNotEqualTo("3");
		}
		
		if (!StringUtil.isEmpty(request.getParameter("searchKeywrod"))) {
			if ("1".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andNameLike("%" + request.getParameter("searchKeywrod") + "%");
			}
			if ("2".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andArtNoLike("%" + request.getParameter("searchKeywrod") + "%");
			}
			if ("3".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andCodeEqualTo(request.getParameter("searchKeywrod"));
			}
			if ("4".equals(request.getParameter("searchKeywrodType"))) {
				productCustomCriteria.andRemarksLike("%" + request.getParameter("searchKeywrod") + "%");
			}
			if ("5".equals(request.getParameter("searchKeywrodType"))) {
				try {
					Integer id=Integer.valueOf(request.getParameter("searchKeywrod"));
					productCustomCriteria.andSingleProductActivityIdEqualTo(id);
				} catch (NumberFormatException e) {
					productCustomCriteria.andSingleProductActivityIdEqualTo(0);
				}
			}
		}

		List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
		String[] titles = { "商品ID", "上架状态", "下架原因","品牌","一级分类","二级分类","三级分类","货号","商品名称","规格","吊牌价","商城价","活动价","库存","SKU商品编码","售后模板名称","商家备注"};
		ExcelBean excelBean = new ExcelBean("导出商品列表" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls",
				"导出商品列表", titles);
		List<String[]> datas = new ArrayList<>();
		for (ProductCustom productCustom : productCustoms) {
			String[] data = {
				"`"+productCustom.getCode(),
				productCustom.getStatus().equals("0")?"下架":"上架",
				productCustom.getOffReasonDesc(),
				productCustom.getProductBrandName(),
				productCustom.getFirstProductTypeName(),
				productCustom.getSecondProductTypeName(),
				productCustom.getProductTypeName(),
				productCustom.getArtNo(),
				productCustom.getName(),
				productCustom.getProductPropValues(),
				productCustom.getTagPriceMin().equals(productCustom.getTagPriceMax())?productCustom.getTagPriceMin().toString():productCustom.getTagPriceMin().toString()+"-"+productCustom.getTagPriceMax().toString(),
				productCustom.getMallPriceMin().equals(productCustom.getMallPriceMax())?productCustom.getMallPriceMin().toString():productCustom.getMallPriceMin().toString()+"-"+productCustom.getMallPriceMax().toString(),
				productCustom.getSalePriceMin().equals(productCustom.getSalePriceMax())?productCustom.getSalePriceMin().toString():productCustom.getSalePriceMin().toString()+"-"+productCustom.getSalePriceMax().toString(),
				productCustom.getStock().toString(),
				productCustom.getSkus(),
				productCustom.getTempletName(),
				productCustom.getRemarks()
			};
			datas.add(data);
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
	}
	
	
	/**
	 * 活动商品列表
	 */
	@ResponseBody
	@RequestMapping("/product/productCopy")
	public String productCopy(HttpServletRequest request) {
        String sourceMchtId=request.getParameter("sourceMchtId");
        String targetMchtId=request.getParameter("targetMchtId");
        String brandId = request.getParameter("brandId");
        if(StringUtil.isEmpty(sourceMchtId)){
        	return "源商家不能为空";
        }
        if(StringUtil.isEmpty(targetMchtId)){
        	return "目标商家不能为空";
        }
//        if(StringUtil.isEmpty(brandId)){
//        	return "品牌不能为空";
//        }
        
        productService.productCopy(sourceMchtId,targetMchtId,brandId);
        
        return "成功";
	}
	
	/**
	 * 校验云宝ID
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/checkCloudProductItemId")
	@ResponseBody
	public ResponseMsg checkCloudProductItemId(HttpServletRequest request) {
		try {
			String cloudProductItemId = request.getParameter("cloudProductItemId");
			String productItemId = request.getParameter("productItemId");
			CloudProductItem cloudProductItem = cloudProductItemService.selectByPrimaryKey(Integer.parseInt(cloudProductItemId));
			if(cloudProductItem==null){
				return new ResponseMsg(ResponseMsg.ERROR, "云宝ID不存在，请重新填写");
			}else{
//				CloudProductPropValue cloudProductPropValue = cloudProductPropValueMapper.selectByPrimaryKey(Integer.parseInt(cloudProductItem.getPropValId()));
//				if(!StringUtil.isEmpty(productItemId)){
//					ProductItem productItem = productItemService.selectByPrimaryKey(Integer.parseInt(productItemId));
//					Product product = productService.selectByPrimaryKey(productItem.getProductId());
//					if(!StringUtil.isEmpty(product.getIsSingleProp()) && product.getIsSingleProp().equals("0")){//多规格
//						String[] propValIdArray = productItem.getPropValId().split(",");
//						List<Integer> propValIdList = new ArrayList<Integer>();
//						for(String propValId:propValIdArray){
//							propValIdList.add(Integer.parseInt(propValId));
//						}
//						if(propValIdList!=null && propValIdList.size()>0){
//							ProductPropValueExample e = new ProductPropValueExample();
//							e.createCriteria().andDelFlagEqualTo("0").andIdIn(propValIdList);
//							List<ProductPropValue> productPropValueList = productPropValueService.selectByExample(e);
//							List<String> values = new ArrayList<String>();
//							for(ProductPropValue productPropValue:productPropValueList){
//								values.add(productPropValue.getPropValue());
//							}
//							if(!values.contains(cloudProductPropValue.getPropValue())){
//								return new ResponseMsg(ResponseMsg.ERROR, "云宝ID有误，尺码匹配不对。");
//							}
//						}
//					}
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);

	}
	/**
	 * 修改该商品SKU提交
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/changeStockCommit")
	@ResponseBody
	public ResponseMsg changeStockCommit( HttpServletRequest request) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			
			String producItemId=request.getParameter("productItemId");
			String stock  =  request.getParameter("stock");
			if(StringUtil.isEmpty(producItemId)){
				throw new ArgException("请选择要修改的sku");
			}
			if(StringUtil.isEmpty(stock)){
				throw new ArgException("请输入库存数量");
			}
			ProductItem oldProductItem=productItemService.selectByPrimaryKey(Integer.valueOf(producItemId));
			
			Product oldProduct=productService.selectByPrimaryKey(oldProductItem.getProductId());
			
			if(!oldProduct.getMchtId().equals(this.getSessionMchtInfo(request).getId())){
				throw new ArgException("没权修改");
			}
			int realChangeStock=productService.changeStock(Integer.valueOf(producItemId), Integer.valueOf(stock), this.getSessionUserInfo(request).getId());
			if(realChangeStock!=Integer.valueOf(stock).intValue()){
				return new ResponseMsg("4001", ResponseMsg.ERROR_MSG,realChangeStock);
			}
		} catch (ArgException arge) {
			return new ResponseMsg(ResponseMsg.ERROR, arge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG,returnData);
		
	}


	/**
	 * 结算价除以活动价 小于 SVIP折扣率-商家品牌表中技术服务费率（SPOP）
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/product/costPriceRate")
	public ResponseMsg costPriceRate(HttpServletRequest request) {
		boolean flag = false;
		if(StringUtil.isEmpty(request.getParameter("priceArray")) ) {
			flag = false;
		}else {
			String brandId = request.getParameter("brandId");
			MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
			mchtProductBrandExample.createCriteria().andMchtIdEqualTo(this.getSessionMchtInfo(request).getId()).andProductBrandIdEqualTo(Integer.valueOf(brandId)).andStatusEqualTo("1").andAuditStatusEqualTo("3").andDelFlagEqualTo("0");
			List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mchtProductBrandExample);

			BigDecimal svipDiscount = new BigDecimal(StringUtil.isEmpty(request.getParameter("svipDiscount"))?"0.95":request.getParameter("svipDiscount"));
			JSONArray priceArray = JSONArray.fromObject(request.getParameter("priceArray"));
			for(Object price : priceArray) {
				JSONObject 	priceJson = JSONObject.fromObject(price);
				BigDecimal salePrice = new BigDecimal(StringUtil.isEmpty(priceJson.getString("salePrice"))?"0":priceJson.getString("salePrice"));
				BigDecimal costPrice = new BigDecimal(StringUtil.isEmpty(priceJson.getString("costPrice"))?"0":priceJson.getString("costPrice"));
				BigDecimal priceRate = costPrice.divide(salePrice, 3, BigDecimal.ROUND_DOWN);
				if(priceRate.compareTo(svipDiscount.subtract(mchtProductBrands.get(0).getPopCommissionRate())) ==-1 ) {
					flag = true;
					break;
				}
			}
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, flag);
	}
	
	
	
	//导出商品SKU
	@RequestMapping("/product/exportProductSku")
	public void exportProductsku(HttpServletRequest request,HttpServletResponse response) throws Exception {
		     ProductItemCustomExample productItemCustomExample=new ProductItemCustomExample();
		     ProductItemCustomExample.ProductItemExampleCriteria productItemExampleCriteria=productItemCustomExample.createCriteria();
		     productItemExampleCriteria.andDelFlagEqualTo("0");
		     productItemExampleCriteria.andMchtIdEqualTo(this.getSessionMchtInfo(request).getId());
		     productItemCustomExample.setOrderByClause("(SELECT p.id FROM bu_product p WHERE bpi.product_id=p.id AND p.del_flag='0') desc,bpi.id desc");
		     if (!StringUtil.isEmpty(request.getParameter("search_brandId"))) {
		    	   productItemExampleCriteria.andBrandIdEqualTo(Integer.valueOf(request.getParameter("search_brandId")));
				}
				
				if (!StringUtil.isEmpty(request.getParameter("search_auditStatus"))) {
					productItemExampleCriteria.andAuditStatusEqualTo(request.getParameter("search_auditStatus"));
				}

				if (!StringUtil.isEmpty(request.getParameter("productType3"))) {// 第三级分类
					productItemExampleCriteria.andProductType3(Integer.valueOf(request.getParameter("productType3")));
				} else if (!StringUtil.isEmpty(request.getParameter("productType2"))) {
					productItemExampleCriteria.andProductType2(Integer.valueOf(request.getParameter("productType2")));
				} else if (!StringUtil.isEmpty(request.getParameter("productType1"))) {
					productItemExampleCriteria.andProductType1(Integer.valueOf(request.getParameter("productType1")));
				}
				
				String saleType=request.getParameter("search_saleType");
				
				if(!StringUtil.isEmpty(saleType)){
					productItemExampleCriteria.andSaleTypeEqualTo(saleType);
				}
				
				if(!StringUtil.isEmpty(request.getParameter("search_status"))){
					productItemExampleCriteria.andStatusEqualTo(request.getParameter("search_status"));
				}
				if(!StringUtil.isEmpty(request.getParameter("search_productActivityStatus"))){
					String productActivityStatus=request.getParameter("search_productActivityStatus");
					if("0".equals(productActivityStatus)){
						productItemExampleCriteria.andAuditStatusNotEqualTo("0");
					}
					if(productActivityStatus.equals("6")){
						productItemExampleCriteria.andAuditStatusEqualTo("0");
					}else if(productActivityStatus.equals("7")){
						productItemExampleCriteria.andAuditStatusEqualTo("3");
					}
				}
				
				if("2".equals(saleType)){
					productItemExampleCriteria.andBrandIsEffect();
					productItemExampleCriteria.andAuditStatusNotEqualTo("3");
				}
				
				if (!StringUtil.isEmpty(request.getParameter("searchKeywrod"))) {
					if ("1".equals(request.getParameter("searchKeywrodType"))) {
						productItemExampleCriteria.andNameLike("%" + request.getParameter("searchKeywrod") + "%");
					}
					if ("2".equals(request.getParameter("searchKeywrodType"))) {
						productItemExampleCriteria.andArtNoLike("%" + request.getParameter("searchKeywrod") + "%");
					}
					if ("3".equals(request.getParameter("searchKeywrodType"))) {
						productItemExampleCriteria.andCodeEqualTo(request.getParameter("searchKeywrod"));
					}
					if ("4".equals(request.getParameter("searchKeywrodType"))) {
						productItemExampleCriteria.andRemarksLike("%" + request.getParameter("searchKeywrod") + "%");
					}
					if ("5".equals(request.getParameter("searchKeywrodType"))) {
						try {
							Integer id=Integer.valueOf(request.getParameter("searchKeywrod"));
							productItemExampleCriteria.andSingleProductActivityIdEqualTo(id);
						} catch (NumberFormatException e) {
							productItemExampleCriteria.andSingleProductActivityIdEqualTo(0);
						}
					}
				}

				List<ProductItemCustom> productItemCustoms = productItemService.selectBySkuExample(productItemCustomExample);
				String[] titles = { "SKU_ID", "SKU商家编码", "商家长ID","上架状态","商品名称","品牌","货号","规格","吊牌价","商城价","活动价","结算价","库存"};
				ExcelBean excelBean = new ExcelBean("导出商品SKU列表" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".xls",
						"导出商品SKU", titles);
				List<String[]> datas = new ArrayList<>();
				 String productStatus="";
				for (ProductItemCustom productItemCustom : productItemCustoms) {
					 if (productItemCustom.getProductStatus()!=null) {
						 if (productItemCustom.getProductStatus().equals("0")) {
							 productStatus="下架";
						}else {
							 productStatus="上架";
						}
					}
					String[] data = {
						productItemCustom.getId().toString(),
						productItemCustom.getSku(),
						"`"+productItemCustom.getProductCode(),
						productStatus,
						productItemCustom.getProductNamesku(),
						productItemCustom.getProductbrandName(),
						productItemCustom.getProductArtNo(),
						productItemCustom.getPropValueSku(),
						productItemCustom.getTagPrice().toString(),
						productItemCustom.getMallPrice().toString(),
						productItemCustom.getSalePrice().toString(),
						productItemCustom.getCostPrice().toString(),
						productItemCustom.getStock().toString()

					};
					datas.add(data);
				}
				excelBean.setDataList(datas);
				ExcelUtils.export(excelBean,response);
		     		
	}
}
