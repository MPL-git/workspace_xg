package com.jf.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.BrandTmkPicTmp;
import com.jf.entity.BrandTmkPicTmpExample;
import com.jf.entity.MchtBrandAptitude;
import com.jf.entity.MchtBrandAptitudeExample;
import com.jf.entity.MchtBrandAptitudePic;
import com.jf.entity.MchtBrandAptitudePicExample;
import com.jf.entity.MchtBrandProductType;
import com.jf.entity.MchtBrandProductTypeExample;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandCustom;
import com.jf.entity.ProductBrandCustomExample;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductBrandTmp;
import com.jf.entity.ProductBrandTmpCustom;
import com.jf.entity.ProductBrandTmpCustomExample;
import com.jf.entity.ProductBrandTrademarkInfo;
import com.jf.entity.ProductBrandTrademarkInfoExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.service.BrandTmkPicTmpService;
import com.jf.service.MchtBrandAptitudePicService;
import com.jf.service.MchtBrandAptitudeService;
import com.jf.service.MchtBrandProductTypeService;
import com.jf.service.MchtProductBrandService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductBrandTmpService;
import com.jf.service.ProductBrandTrademarkInfoService;
import com.jf.service.ProductTypeService;
import com.jf.service.WebcommonService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class ProductBrandController extends BaseController{
	@Resource
	private ProductBrandService productBrandService;
	
	@Resource
	private ProductBrandTmpService productBrandTmpService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private WebcommonService webcommonService;
	
	@Resource
	private BrandTmkPicTmpService brandTmkPicTmpService;
	
	@Resource
	private MchtBrandAptitudePicService mchtBrandAptitudePicService;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Resource
	private ProductBrandTrademarkInfoService productBrandTrademarkInfoService;
	
	@Resource
	private MchtBrandProductTypeService mchtBrandProductTypeService;
	
	@Resource
	private MchtBrandAptitudeService mchtBrandAptitudeService;
	
	@RequestMapping(value = "/service/prod/product_brand/index.shtml")
	public ModelAndView productBrandIndex(HttpServletRequest request, HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prod/product_brand/index";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		paramMap.put("TABLE_NAME", "BU_PRODUCT_BRAND");
		paramMap.put("COL_NAME", "STATUS");
		List<?> brandTmpStatus = webcommonService.sysStatusList(paramMap);
		resMap.put("brandTmpStatus", brandTmpStatus);
		
		
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andDelFlagEqualTo("0");
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		resMap.put("isCwOrgStatus", isCwOrgStatus);
		char firstE = 'A', lastE = 'Z';
		int firstEnglish  = (int)firstE, lastEnglish = (int)lastE;
		List<String> listAZ = new ArrayList<String>();
		for (int i = firstEnglish; i <= lastEnglish; i++) {
			listAZ.add(String.valueOf((char)i));
		}
		resMap.put("listAZ", listAZ);
		List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypes", productTypes);
		return new ModelAndView(rtPage,resMap);
	}
	
	
	
	@RequestMapping(value = "/service/prod/product_brand/data.shtml")
	@ResponseBody
	public Map<String, Object> initProductBrandData(HttpServletRequest request, Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		List<ProductBrandCustom> dataList = null;		
		Integer totalCount = 0;
		try {
			ProductBrandCustomExample productBrandCustomExample = new ProductBrandCustomExample();
			ProductBrandCustomExample.ProductBrandExampleCriteria createCriteria = productBrandCustomExample.createCriteria();
			createCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				createCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					createCriteria.andProductTypeGroupLikeOrClause(isCwOrgProductTypeId);
				}
			}else {
				if(!StringUtil.isEmpty(request.getParameter("productTypeGroup")) ){
					createCriteria.andProductTypeGroupLikeOrClause(request.getParameter("productTypeGroup"));
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("tmkTypeGroup")) ){
				createCriteria.andTmkTypeGroupEqualTo(request.getParameter("tmkTypeGroup"));
			}
			if(!StringUtil.isEmpty(request.getParameter("tmkCode")) ){
				createCriteria.andTmkCodeEqualTo(request.getParameter("tmkCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("SEARCHCHAR"))){
				createCriteria.andSearchNameECLike(request.getParameter("SEARCHCHAR"));
			}
			if(!StringUtil.isEmpty(request.getParameter("letterIndex"))){
				if("AA".equals(request.getParameter("letterIndex"))) {
					createCriteria.andLetterIndexIsNullEqualTo();
				}else {
					createCriteria.andLetterIndexEqualTo(request.getParameter("letterIndex"));
				}
			}
			productBrandCustomExample.setLimitStart(page.getLimitStart());
			productBrandCustomExample.setLimitSize(page.getLimitSize());
			totalCount = productBrandService.countByCustomExample(productBrandCustomExample);
			dataList =  productBrandService.selectByCustomExample(productBrandCustomExample);
			
			ProductTypeExample productTypeExample = new ProductTypeExample();
			productTypeExample.createCriteria().andDelFlagEqualTo("0");
			List<ProductType> listproductType = productTypeService.selectByExample(productTypeExample);
			
			HashMap<Integer, ProductType> hashType = new HashMap<Integer, ProductType>();
			for (int i = 0; i < listproductType.size(); i++) {
				ProductType productType = listproductType.get(i);
				hashType.put(productType.getId(), productType);
			}
			for (ProductBrandCustom productBrandCustom : dataList) {
				//获取品类
				if(!StringUtil.isEmpty(productBrandCustom.getProductTypeGroup())) {
					String[] productTypeGroupIds = productBrandCustom.getProductTypeGroup().split(",");
					StringBuffer strBuf = new StringBuffer();
					for (int i = 0; i < productTypeGroupIds.length; i++) {
						int typeid = Integer.valueOf(productTypeGroupIds[i]);
						ProductType productType = hashType.get(typeid);
						if (productType != null) {
							if(strBuf.length() > 0) {
								strBuf.append(",");
							}
							strBuf.append(productType.getName());
						}
					}
					productBrandCustom.setProductTypes(strBuf.toString());
				}
			}
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/* 品牌修改或添加 */
	@RequestMapping(value = "/service/prod/product_brand/editbrand.shtml")
	public ModelAndView edit(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/prod/product_brand/editbrand";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		paramMap.put("TABLE_NAME", "BU_PRODUCT_BRAND");
		paramMap.put("COL_NAME", "STATUS");
		
		if (!StringUtil.isEmpty(request.getParameter("productBrandID"))) {
			//获取id对应的品牌信息
			ProductBrand productBrand = productBrandService.selectByPrimaryKey(Integer.valueOf(request.getParameter("productBrandID")));
			resMap.put("ProductBrand", productBrand);
			BrandTmkPicTmpExample brandTmkPicTmpExample=new BrandTmkPicTmpExample();
			brandTmkPicTmpExample.createCriteria().andBrandIdEqualTo(productBrand.getId()).andDelFlagEqualTo("0");
			List<BrandTmkPicTmp> brandTmkPicTmps=brandTmkPicTmpService.selectByExample(brandTmkPicTmpExample);
			List<Map<String, Object>> bandTmkPicList=new ArrayList<Map<String, Object>>();
			for(BrandTmkPicTmp brandTmkPicTmp:brandTmkPicTmps){
				Map<String, Object> pic=new HashMap<String, Object>();
				pic.put("PICTURE_PATH", brandTmkPicTmp.getPic());
				bandTmkPicList.add(pic);
			}
			resMap.put("brandTmkPics", bandTmkPicList);
			//品牌商标信息
			ProductBrandTrademarkInfoExample productBrandTrademarkInfoExample = new ProductBrandTrademarkInfoExample();
			productBrandTrademarkInfoExample.createCriteria().andDelFlagEqualTo("0").andProductBrandIdEqualTo(productBrand.getId());
			productBrandTrademarkInfoExample.setOrderByClause(" id asc");
			List<ProductBrandTrademarkInfo> productBrandTrademarkInfoList = productBrandTrademarkInfoService.selectByExample(productBrandTrademarkInfoExample);
			if(productBrandTrademarkInfoList != null && productBrandTrademarkInfoList.size() > 0) {
				resMap.put("productBrandTrademarkInfoList", JSONArray.fromObject(productBrandTrademarkInfoList));
			}
		}
		
		//获取所有的品牌状态
		List<?> brandTmpStatus = webcommonService.sysStatusList(paramMap);
		List<JSONObject> resulList=new ArrayList<JSONObject>();
		JSONObject productTypeJson;
		for (int i = 0; i < brandTmpStatus.size(); i++) {
			productTypeJson=new JSONObject();
			String object = brandTmpStatus.get(i).toString();
			String statu_value = object.replace("{STATUS_VALUE=", "");
			int statu_index = statu_value.indexOf(",");
			String statuValue = statu_value.substring(0, statu_index);
			
			String statudesc = statu_value.substring(statu_index+1);
			String replacedesc = statudesc.replace("STATUS_DESC=", "");
			int indexOf = replacedesc.indexOf("}");
			String statuDes = replacedesc.substring(0, indexOf);
		
			productTypeJson.put("id", statuValue);
			productTypeJson.put("text", statuDes);
			resulList.add(productTypeJson);
		}
		resMap.put("resulList", resulList);
		resMap.put("brandTmpStatus", brandTmpStatus);
		return new ModelAndView(rtPage, resMap);
	}


	/**
	 * 商家申请的品牌添加到品牌库 
	 */
	@RequestMapping(value = "/service/prod/product_brand/add2BrandFromMchtBrand.shtml")
	public ModelAndView add2BrandFromMchtBrand(HttpServletRequest request,
			@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/prod/product_brand/editbrand";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		paramMap.put("TABLE_NAME", "BU_PRODUCT_BRAND");
		paramMap.put("COL_NAME", "STATUS");
		
		//获取id对应的品牌信息
		
		MchtProductBrand mchtProductBrand =mchtProductBrandService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		
		ProductBrand productBrand = new ProductBrand();
		productBrand.setName(mchtProductBrand.getProductBrandName());
		productBrand.setLogo(mchtProductBrand.getLogo());
		MchtBrandProductTypeExample mbpte = new MchtBrandProductTypeExample();
		mbpte.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId()).andTLevelEqualTo(1);
		List<MchtBrandProductType> mchtBrandProductTypes = mchtBrandProductTypeService.selectByExample(mbpte);
		if(mchtBrandProductTypes!=null && mchtBrandProductTypes.size()>0){
			productBrand.setProductTypeGroup(mchtBrandProductTypes.get(0).getProductTypeId().toString());
		}
		resMap.put("ProductBrand", productBrand);
		
		//品牌商标信息
		MchtBrandAptitudeExample mbae = new MchtBrandAptitudeExample();
		mbae.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandAptitude> mchtBrandAptitudes = mchtBrandAptitudeService.selectByExample(mbae);
		List<ProductBrandTrademarkInfo> productBrandTrademarkInfoList = new ArrayList<ProductBrandTrademarkInfo>();
		for(MchtBrandAptitude mchtBrandAptitude:mchtBrandAptitudes){
			ProductBrandTrademarkInfo pbti = new ProductBrandTrademarkInfo();
			pbti.setTmkCode(mchtBrandAptitude.getCertificateNo());
			productBrandTrademarkInfoList.add(pbti);
		}
		if(productBrandTrademarkInfoList != null && productBrandTrademarkInfoList.size() > 0) {
			resMap.put("productBrandTrademarkInfoList", JSONArray.fromObject(productBrandTrademarkInfoList));
		}
		
		MchtBrandAptitudePicExample mchtBrandAptitudePicExample=new MchtBrandAptitudePicExample();
		mchtBrandAptitudePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandAptitudePic> mchtBrandAptitudePics=mchtBrandAptitudePicService.selectByExample(mchtBrandAptitudePicExample);
		List<Map<String, Object>> bandTmkPicList=new ArrayList<Map<String, Object>>();
		for(MchtBrandAptitudePic mchtBrandAptitudePic:mchtBrandAptitudePics){
			Map<String, Object> pic=new HashMap<String, Object>();
			pic.put("PICTURE_PATH", mchtBrandAptitudePic.getPic());
			bandTmkPicList.add(pic);
		}
		resMap.put("brandTmkPics", bandTmkPicList);
		
		
		//获取所有的品牌状态
		List<?> brandTmpStatus = webcommonService.sysStatusList(paramMap);
		
		
		List<JSONObject> resulList=new ArrayList<JSONObject>();
		JSONObject productTypeJson;
		for (int i = 0; i < brandTmpStatus.size(); i++) {
			productTypeJson=new JSONObject();
			String object = brandTmpStatus.get(i).toString();
			String statu_value = object.replace("{STATUS_VALUE=", "");
			int statu_index = statu_value.indexOf(",");
			String statuValue = statu_value.substring(0, statu_index);
			
			String statudesc = statu_value.substring(statu_index+1);
			String replacedesc = statudesc.replace("STATUS_DESC=", "");
			int indexOf = replacedesc.indexOf("}");
			String statuDes = replacedesc.substring(0, indexOf);
			
			productTypeJson.put("id", statuValue);
			productTypeJson.put("text", statuDes);
			resulList.add(productTypeJson);
		}
		
		
		
		
		resMap.put("resulList", resulList);
		resMap.put("brandTmpStatus", brandTmpStatus);
		
		return new ModelAndView(rtPage, resMap);
	}
	
	//获取品牌状态
	@ResponseBody
	@RequestMapping(value = "/service/prod/product_brand/getbrandstatus.shtml")
	public List<JSONObject> getbrandstatus(HttpServletRequest request, HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap){
		paramMap.put("TABLE_NAME", "BU_PRODUCT_BRAND");
		paramMap.put("COL_NAME", "STATUS");
		List<?> sysStatusList = webcommonService.sysStatusList(paramMap);
		List<JSONObject> resulList=new ArrayList<JSONObject>();
		JSONObject productTypeJson;
		for (int i = 0; i < sysStatusList.size(); i++) {
			productTypeJson=new JSONObject();
			String object = sysStatusList.get(i).toString();
			String statu_value = object.replace("{STATUS_VALUE=", "");
			int statu_index = statu_value.indexOf(",");
			String statuValue = statu_value.substring(0, statu_index);
			
			String statudesc = statu_value.substring(statu_index+1);
			String replacedesc = statudesc.replace("STATUS_DESC=", "");
			int indexOf = replacedesc.indexOf("}");
			String statuDes = replacedesc.substring(0, indexOf);
		
			productTypeJson.put("id", statuValue);
			productTypeJson.put("text", statuDes);
			resulList.add(productTypeJson);
		}
		return resulList;
	}
	
	
	//根据品牌id获取到品牌信息
	@ResponseBody
	@RequestMapping(value = "/service/prod/product_brand/getproductbrand.shtml")
	public ModelAndView getproductbrand(HttpServletRequest request, HttpServletResponse response,
			@RequestParam HashMap<String, Object> paramMap){
		String rtPage = "";
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(request.getParameter("productid"))) {
				return new ModelAndView(rtPage,resMap);
			}
			String parameter = request.getParameter("productid");
			ProductBrand productBrand = productBrandService.selectByPrimaryKey(Integer.valueOf(parameter));
			resMap.put("productBrand", productBrand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage,resMap);
	}

	//保存数据
	@ResponseBody
	@RequestMapping(value = "/service/prod/product_brand/editbrandsave.shtml")
	public ModelAndView editbrandsave(HttpServletRequest request,ProductBrand productBrand,String brandTmkPics){
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			if(!StringUtil.isEmpty(request.getParameter("tmk"))) {
				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId = Integer.valueOf(staffBean.getStaffID());
				Date date = new Date();
				if(productBrand.getId() == null) {
					//添加数据
					productBrand.setCreateBy(staffId);
					productBrand.setCreateDate(date);
					productBrandService.insertProductBrand(productBrand, brandTmkPics, request.getParameter("tmk"));
				}else {
					//更新数据
					productBrand.setUpdateBy(staffId);
					productBrand.setUpdateDate(date);
					productBrandService.updateAuditing(productBrand, brandTmkPics, request.getParameter("tmk"));
				}
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	@RequestMapping(value = "/productBrandTmp/index.shtml")
	public ModelAndView productBrandTmpIndex(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prod/productBrandTmp/list";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		paramMap.put("TABLE_NAME", "BU_PRODUCT_BRAND_TMP");
		paramMap.put("COL_NAME", "STATUS");
		List<?> brandTmpStatus = webcommonService.sysStatusList(paramMap);
		resMap.put("brandTmpStatus", brandTmpStatus);
		
		
		ProductTypeExample productTypeExample=new ProductTypeExample();
		productTypeExample.createCriteria().andParentIdEqualTo(1).andDelFlagEqualTo("0");
		List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypes", productTypes);
		
		return new ModelAndView(rtPage,resMap);
	}
	/**
	 * 品牌申请表列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "productBrandTmp/datalist.shtml")
	@ResponseBody
	public Map<String, Object> datalist(HttpServletRequest request,
			HttpServletResponse response,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		int totalCount=0;
		try {
			ProductBrandTmpCustomExample productBrandTmpExample=new ProductBrandTmpCustomExample();
			ProductBrandTmpCustomExample.ProductBrandTmpExampleCriteria productBrandTmpCriteria=productBrandTmpExample.createCriteria();
			productBrandTmpCriteria.andDelFlagEqualTo("0");
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				productBrandTmpCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeGroup")) ){
				productBrandTmpCriteria.andProductTypeGroupLikeOrClause(request.getParameter("productTypeGroup"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("tmkTypeGroup")) ){
				productBrandTmpCriteria.andTmkTypeGroupLikeOrClause(request.getParameter("tmkTypeGroup"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("searchKeyWord")) ){
				productBrandTmpCriteria.andSearchKeyWordLikeOrClause(request.getParameter("searchKeyWord"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("id")) ){
				productBrandTmpCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			
			totalCount=productBrandTmpService.countByExample(productBrandTmpExample);
			
			productBrandTmpExample.setLimitStart(page.getLimitStart());
			productBrandTmpExample.setLimitSize(page.getLimitSize());
			List<ProductBrandTmp> productBrandTmps=productBrandTmpService.selectByExample(productBrandTmpExample);
			List<ProductBrandTmpCustom> proBrandTmpCustoms=new ArrayList<ProductBrandTmpCustom>();
			for(ProductBrandTmp productBrandTmp:productBrandTmps){
				if(!StringUtil.isEmpty(productBrandTmp.getProductTypeGroup())){
					
					//获取品类
					String[] productTypeGroupIds=productBrandTmp.getProductTypeGroup().split(",");
					List<Integer> ids=new ArrayList<Integer>();
					for (int i = 0; i < productTypeGroupIds.length; i++) {
						ids.add(Integer.valueOf(productTypeGroupIds[i]));
					}
					ProductTypeExample productTypeExample=new ProductTypeExample();
					productTypeExample.createCriteria().andDelFlagEqualTo("0").andIdIn(ids);
					List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
					
					
					ProductBrandTmpCustom productBrandTmpCustom=new ProductBrandTmpCustom();
					productBrandTmpCustom.setProductBrandTmp(productBrandTmp);
					productBrandTmpCustom.setProductTypes(productTypes);
					productBrandTmpCustom.setStatusName(DataDicUtil.getStatusDesc("BU_PRODUCT_BRAND_TMP", "STATUS", productBrandTmp.getStatus()));
					proBrandTmpCustoms.add(productBrandTmpCustom);
				}
			}
			
			resMap.put("Rows", proBrandTmpCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 品牌申请查看
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/productBrandTmp/view.shtml")
	public ModelAndView view(HttpServletRequest request) {
		String rtPage = "/prod/productBrandTmp/view";// 重定向

		Map<String, Object> resMap = new HashMap<String, Object>();
		
		ProductBrandTmp productBrandTmp=productBrandTmpService.selectByPrimaryKey(Integer.valueOf(request.getParameter("productBrandTmpId")));
		BrandTmkPicTmpExample brandTmkPicTmpExample=new BrandTmkPicTmpExample();
		brandTmkPicTmpExample.createCriteria().andBrandIdEqualTo(productBrandTmp.getId()).andDelFlagEqualTo("0");
		List<BrandTmkPicTmp> brandTmkPicTmps=brandTmkPicTmpService.selectByExample(brandTmkPicTmpExample);
		List<Map<String, Object>> bandTmkPicList=new ArrayList<Map<String, Object>>();
		for(BrandTmkPicTmp brandTmkPicTmp:brandTmkPicTmps){
			Map<String, Object> pic=new HashMap<String, Object>();
			pic.put("PICTURE_PATH", brandTmkPicTmp.getPic());
			bandTmkPicList.add(pic);
		}
		resMap.put("productBrandTmp", productBrandTmp);
		resMap.put("brandTmkPics", bandTmkPicList);
		
		
		return new ModelAndView(rtPage, resMap);
	}
	
	
	/**
	 * 品牌申请审核
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/productBrandTmp/auditing.shtml")
	public ModelAndView auditing(HttpServletRequest request) {
		String rtPage = "/prod/productBrandTmp/auditing";// 重定向

		Map<String, Object> resMap = new HashMap<String, Object>();
		
		ProductBrandTmp productBrandTmp=productBrandTmpService.selectByPrimaryKey(Integer.valueOf(request.getParameter("productBrandTmpId")));
		if(!("1".equals(productBrandTmp.getStatus())||"4".equals(productBrandTmp.getStatus()))){
			return new ModelAndView(rtPage, resMap);
		}
		BrandTmkPicTmpExample brandTmkPicTmpExample=new BrandTmkPicTmpExample();
		brandTmkPicTmpExample.createCriteria().andBrandIdEqualTo(productBrandTmp.getId()).andDelFlagEqualTo("0");
		List<BrandTmkPicTmp> brandTmkPicTmps=brandTmkPicTmpService.selectByExample(brandTmkPicTmpExample);
		List<Map<String, Object>> bandTmkPicList=new ArrayList<Map<String, Object>>();
		for(BrandTmkPicTmp brandTmkPicTmp:brandTmkPicTmps){
			Map<String, Object> pic=new HashMap<String, Object>();
			pic.put("PICTURE_PATH", brandTmkPicTmp.getPic());
			bandTmkPicList.add(pic);
		}
		resMap.put("productBrandTmp", productBrandTmp);
		resMap.put("brandTmkPics", bandTmkPicList);
		
		
		return new ModelAndView(rtPage, resMap);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/productType/getProductType4ListBox.shtml")
	public List<JSONObject> getProductTypeGroup(HttpServletRequest request) {
		String getProductTypeIdsStr=request.getParameter("productTypeIds");
		ProductTypeExample productTypeExample=new ProductTypeExample();
		ProductTypeExample.Criteria productCriteria = productTypeExample.createCriteria();
		productCriteria.andDelFlagEqualTo("0");
		
		//钟表运营部状态，只获取主营类目为钟表 
		String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
				productCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
			}
		}
		
		if(!StringUtils.isEmpty(getProductTypeIdsStr)){
			String[] productTypeGroupIds=getProductTypeIdsStr.split(",");
			List<Integer> ids=new ArrayList<Integer>();
			for (int i = 0; i < productTypeGroupIds.length; i++) {
				ids.add(Integer.valueOf(productTypeGroupIds[i]));
			}
			productCriteria.andIdIn(ids);			
		}
		String parentId=request.getParameter("parentId");
		if(!StringUtils.isEmpty(parentId)){
			productCriteria.andParentIdEqualTo(Integer.valueOf(parentId));
		}
		List<JSONObject> resulList=new ArrayList<JSONObject>();
		JSONObject productTypeJson;
		if(StringUtils.isEmpty(getProductTypeIdsStr) && StringUtils.isEmpty(parentId)){
		}else{
			List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
			for(ProductType productType:productTypes){
				productTypeJson=new JSONObject();
				productTypeJson.put("id", productType.getId());
				productTypeJson.put("text", productType.getName());
				resulList.add(productTypeJson);
			}
		}
		return resulList;
	}
	
	
	
	/**
	 * 品牌申请审核提交
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/productBrandTmp/auditingSave.shtml")
	public ModelAndView auditingSave(HttpServletRequest request,ProductBrandTmp productBrandTmp,String brandTmkPics) {	
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		try {
			productBrandTmp.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			productBrandTmp.setUpdateDate(new Date());
			productBrandTmp.setName(productBrandTmp.getName()==null?null:productBrandTmp.getName().trim().toUpperCase());
			productBrandTmp.setNameEn(productBrandTmp.getNameEn()==null?null:productBrandTmp.getNameEn().trim().toUpperCase());
			productBrandTmp.setNameZh(productBrandTmp.getNameZh()==null?null:productBrandTmp.getNameZh().trim().toLowerCase());
			productBrandTmpService.updateAuditing(productBrandTmp, brandTmkPics);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);

		return new ModelAndView(rtPage, resMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "/productBrand/checkName.shtml")
	public Map<String, Object> checkName(HttpServletRequest request) {
		
		

		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			
			boolean hasErrorMsg=false;
            String errorMsg="";
            
    		
    		String name=request.getParameter("name").toString().trim();
    		String catalog=request.getParameter("catalog").toString().trim();
    		
    		
    		
    		ProductBrandExample productBrandExample=new ProductBrandExample();
    		productBrandExample.createCriteria().andNameEqualTo(name).andDelFlagEqualTo("0");
    		int count=productBrandService.countByExample(productBrandExample);
			if(count>0){
				errorMsg=errorMsg+";品牌已存在";
				hasErrorMsg=true;
				resMap.put("hasBrandNameExist", "1");
			}else{
				resMap.put("hasBrandNameExist", "0");
			}
		    productBrandExample=new ProductBrandExample();
    		productBrandExample.createCriteria().andCatalogEqualTo(catalog).andDelFlagEqualTo("0");
     		count=productBrandService.countByExample(productBrandExample);
			if(count>0){
				errorMsg=errorMsg+";品牌目录已存在";
				hasErrorMsg=true;
				resMap.put("hasCatalogExist", "1");
			}else{
				resMap.put("hasCatalogExist", "0");
			}
			
			if(hasErrorMsg){
				throw new ArgException(errorMsg.substring(1));
			}
            
			
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/productBrand/checkBrandCatalog.shtml")
	public Map<String, Object> checkBrandCatalog(HttpServletRequest request) {
		
		
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			ProductBrandExample productBrandExample=new ProductBrandExample();
			ProductBrandExample.Criteria criteria=productBrandExample.createCriteria();
			criteria.andCatalogEqualTo(request.getParameter("catalog"));
			if(!StringUtil.isEmpty(request.getParameter("id"))){
				criteria.andIdNotEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			int count=productBrandService.countByExample(productBrandExample);
			if(count>0){
				throw new ArgException("品牌目录已经存在");
			}
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/productBrand/checkBrandName.shtml")
	public Map<String, Object> checkBrandName(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			ProductBrandExample productBrandExample=new ProductBrandExample();
			ProductBrandExample.Criteria criteria=productBrandExample.createCriteria();
			criteria.andNameEqualTo(request.getParameter("name"));
			if(!StringUtil.isEmpty(request.getParameter("id"))){
				criteria.andIdNotEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			int count=productBrandService.countByExample(productBrandExample);
			if(count>0){
				throw new ArgException("品牌名称已经存在");
			}
		} catch (ArgException e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title checkProductBrandName   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年5月7日 下午3:59:34
	 */
	@ResponseBody
	@RequestMapping("/productBrand/checkProductBrandName.shtml")
	public Map<String, Object> checkProductBrandName(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		Integer count = 0;
		try {
			ProductBrandExample productBrandExample = new ProductBrandExample();
			ProductBrandExample.Criteria productBrandCriteria = productBrandExample.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("id"))){
				productBrandCriteria.andIdNotEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			if(!StringUtil.isEmpty(request.getParameter("nameZh"))){
				productBrandCriteria.andNameZhEqualTo(request.getParameter("nameZh"));
			}
			if(!StringUtil.isEmpty(request.getParameter("nameEn"))){
				productBrandCriteria.andNameEnEqualTo(request.getParameter("nameEn"));
			}
			count = productBrandService.countByExample(productBrandExample);
		} catch (Exception e) {
			code = "9999";
			msg = "操作失败！";
			e.printStackTrace();
		}
		resMap.put("code", code);
		resMap.put("msg", msg);
		resMap.put("count", count);
		return resMap;
	}
	
	/**
	 * 品牌搜索下拉联想框数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/productBrand/selectDatalist.shtml")
	@ResponseBody
	public Map<String, Object> selectDatalist(HttpServletRequest request,
			HttpServletResponse response,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		try {
			ProductBrandExample productBrandExample=new ProductBrandExample();
			ProductBrandExample.Criteria productBrandCriteria= productBrandExample.createCriteria();
			productBrandCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
			JSONArray conditionArray = JSONArray.fromObject(request.getParameter("condition"));
			if (conditionArray != null && conditionArray.size() > 0 && conditionArray.getJSONObject(0) != null) {
				for (int i = 0; i < conditionArray.size(); i++) {
					JSONObject condition = conditionArray.getJSONObject(i);
					if (!condition.isNullObject()) {
						if("name".equals(condition.getString("field"))){
							productBrandCriteria.andNameLike("%"+condition.getString("value")+"%");
						}
					}
				}
			}
			int totalCount=productBrandService.countByExample(productBrandExample);
			productBrandExample.setLimitStart(page.getLimitStart());
			productBrandExample.setLimitSize(page.getLimitSize());
			List<ProductBrand> productBrands=productBrandService.selectByExample(productBrandExample);
			resMap.put("Rows", productBrands);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping("/productBrand/validateTmkCodeType.shtml")
	public Map<String, Object> validateTmkCodeType(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		Integer count = 0;
		try {
			if(!StringUtil.isEmpty(request.getParameter("tmkCodeStr"))) {
				String[] tmkCodes = request.getParameter("tmkCodeStr").split(",");
				List<String> tmkCodeList = new ArrayList<String>();
				for(String tmkCode : tmkCodes) {
					tmkCodeList.add(tmkCode);
				}
				ProductBrandTrademarkInfoExample productBrandTrademarkInfoExample = new ProductBrandTrademarkInfoExample();
				ProductBrandTrademarkInfoExample.Criteria productBrandTrademarkInfoCriteria = productBrandTrademarkInfoExample.createCriteria();
				productBrandTrademarkInfoCriteria.andDelFlagEqualTo("0").andTmkCodeIn(tmkCodeList);
				if(!StringUtil.isEmpty(request.getParameter("id"))) {
					productBrandTrademarkInfoCriteria.andProductBrandIdNotEqualTo(Integer.parseInt(request.getParameter("id")));
				}
				count = productBrandTrademarkInfoService.countByExample(productBrandTrademarkInfoExample);
			}
		} catch (Exception e) {
			code = "9999";
			msg = "操作失败！";
			e.printStackTrace();
		}
		resMap.put("code", code);
		resMap.put("msg", msg);
		resMap.put("count", count);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateLetterIndex   
	 * @Description TODO(首字母)   
	 * @author pengl
	 * @date 2018年8月7日 下午5:27:11
	 */
	@ResponseBody
	@RequestMapping("/service/prod/product_brand/updateLetterIndex.shtml")
	public Map<String, Object> updateLetterIndex(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String productBrandId = request.getParameter("productBrandId");
			String letterIndex = request.getParameter("letterIndex");
			if(!StringUtil.isEmpty(productBrandId)) {
				ProductBrand productBrand = new ProductBrand();
				productBrand.setId(Integer.parseInt(productBrandId));
				productBrand.setLetterIndex(letterIndex);
				productBrandService.updateByPrimaryKeySelective(productBrand);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	
}

