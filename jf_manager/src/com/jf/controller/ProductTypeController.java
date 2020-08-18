package com.jf.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.beans.Menu;
import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.ProductTypeAptitudePicMapper;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeAptitudePic;
import com.jf.entity.ProductTypeAptitudePicExample;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysStaffInfo;
import com.jf.service.PlatformContactService;
import com.jf.service.ProductTypeService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.WebcommonService;

@Controller
public class ProductTypeController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private WebcommonService webcommonService;
	@Resource
	private ProductTypeAptitudePicMapper productTypeAptitudePicMapper;
	@Resource
	private PlatformContactService platformContactService;
	
	@Autowired
	private SysStaffInfoService sysStaffInfoService;
	
	@RequestMapping(value = "/service/prod/product_type/find.shtml")
    public ModelAndView productTypeFind(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/prod/product_type/index";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		try {
			List<Menu> selectProductType = productTypeService.selectProductType(paramMap);
			ObjectMapper objectMapper = new ObjectMapper();
			resMap.put("productTypeList", objectMapper.writeValueAsString(selectProductType));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/service/prod/product_type/list.shtml")
	public ModelAndView productTypeList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prod/product_type/list";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("id", paramMap.get("id"));
		resMap.put("roleId", this.getSessionStaffBean(request).getRoleId());
		PlatformContactExample pce = new PlatformContactExample();
		PlatformContactExample.Criteria c = pce.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andStatusEqualTo("0");
		c.andContactTypeEqualTo("7");
		c.andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		List<PlatformContact> platformContacts = platformContactService.selectByExample(pce);
		resMap.put("platformContacts", platformContacts.size());
		return new ModelAndView(rtPage,resMap);
	} 
	
	@RequestMapping(value = "/service/prod/product_type/datalist.shtml")
	@ResponseBody //ModelAndView
	public  Map<String, Object> datalist(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount ="0";
		try {
			
			int id=Integer.valueOf(request.getParameter("id"));
			
			ProductTypeExample productTypeExample = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
			
			productTypeCriteria.andParentIdEqualTo(id).andDelFlagEqualTo("0");
			
			int getprarent = productTypeService.countByExample(productTypeExample);
			
		    paramMap.put("ISNO", getprarent);
			paramMap=this.setPageParametersLiger(request,paramMap);
        	totalCount = productTypeService.queryDataCount(paramMap);
        	dataList = productTypeService.selectProductTypeList(paramMap);
        	resMap.put("id", paramMap.get("id"));
			resMap.put("Rows", dataList);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
			totalCount = "0";
		}
	   return resMap;
	}
	
	@RequestMapping(value = "/service/prod/product_type/add.shtml")
	public ModelAndView productTypeadd(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prod/product_type/toaddedit";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		String prarentId="";
		String prarentName="";
		Map<String,Object> tempMap1 = new HashMap<String,Object>();
		String totalCount ="0";
		paramMap.put("ISNO", 0);
		totalCount = productTypeService.queryDataCount(paramMap);
		if ("0".equals(totalCount)){
			resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
			resMap.put(this.JSON_RESULT_MESSAGE, "请在左侧选择好父级品类后再操作");
			rtPage = "/success/success";
			return new ModelAndView(rtPage,resMap);
		}
		tempMap1.put("parent_id", paramMap.get("id"));
		int telvel=productTypeService.gettlevvel(tempMap1);
		if(telvel==4)
		{
			resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
			resMap.put(this.JSON_RESULT_MESSAGE, "3级品类不可以再添加子类");
			rtPage = "/success/success";
			return new ModelAndView(rtPage,resMap);
		}

		int id=Integer.valueOf(request.getParameter("id"));
		
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		
		productTypeCriteria.andParentIdEqualTo(id).andDelFlagEqualTo("0");
		
		int getprarent = productTypeService.countByExample(productTypeExample);
		
		if(getprarent==0){
			String getprarentId=productTypeService.getprarentId(paramMap);//判断父级是否为根节点
			String getPrarentF=productTypeService.getPrarentF(paramMap);//判断 父级的父级是否为根节点
			if("0".equals(getprarentId)||"0".equals(getPrarentF)){
				prarentId=StringUtil.valueOf(paramMap.get("id"));
				prarentName=productTypeService.getProdTypeName(paramMap);
			}else
			{
				List prarentList=productTypeService.getprarentList(paramMap);
				for (Object obj:prarentList){
					Map prodMap=(Map)obj;
					prarentId=StringUtil.valueOf(prodMap.get("parent_id"));
					prarentName=StringUtil.valueOf(prodMap.get("parent_name"));}
			}
		}else
		{
			prarentId=StringUtil.valueOf(paramMap.get("id"));
			if("1".equals(prarentId)){
				prarentName="本次添加为1级品类";
			}else
			{
				prarentName=productTypeService.getProdTypeName(paramMap);
			}
		}
		
		resMap.put("type", "1");
		resMap.put("parent_id", prarentId);
		resMap.put("parent_name", prarentName);
		resMap.put("t_level", telvel);
		paramMap.put("TABLE_NAME", "BU_PRODUCT_TYPE");
		paramMap.put("COL_NAME", "STATUS");
		List<?> Showtype = webcommonService.sysStatusList(paramMap);
		resMap.put("Showtype", Showtype);
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/service/prod/product_type/edit.shtml")
	public ModelAndView productTypeEdit(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prod/product_type/toaddedit";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();	
		List<?> productList=productTypeService.selectProductTypeListByid(paramMap);
		for (Object obj:productList){
			Map<?, ?> prodtypeMap=(Map<?, ?>)obj;
			resMap.put("id", StringUtil.valueOf(prodtypeMap.get("id")));
			resMap.put("name", StringUtil.valueOf(prodtypeMap.get("name")));
			resMap.put("parent_id", StringUtil.valueOf(prodtypeMap.get("parent_id"))); 
			resMap.put("parent_name",StringUtil.valueOf(prodtypeMap.get("parent_name")));
			resMap.put("status",StringUtil.valueOf(prodtypeMap.get("status"))); 
			resMap.put("seq_no",StringUtil.valueOf(prodtypeMap.get("seq_no"))); 
			resMap.put("suit_sex",StringUtil.valueOf(prodtypeMap.get("suit_sex"))); 
			resMap.put("suit_group",StringUtil.valueOf(prodtypeMap.get("suit_group"))); 
			resMap.put("deposit",StringUtil.valueOf(prodtypeMap.get("deposit")));
			resMap.put("fee_rate",StringUtil.valueOf(prodtypeMap.get("fee_rate")));
			resMap.put("individual_deposit",StringUtil.valueOf(prodtypeMap.get("individual_deposit")));
			resMap.put("individual_fee_rate",StringUtil.valueOf(prodtypeMap.get("individual_fee_rate")));
			resMap.put("t_level",StringUtil.valueOf(prodtypeMap.get("t_level")));
			resMap.put("remarks",StringUtil.valueOf(prodtypeMap.get("remarks")));
			resMap.put("principal_staff_id",StringUtil.valueOf(prodtypeMap.get("principal_staff_id")));
			resMap.put("enterprise_activities_DSR",StringUtil.valueOf(prodtypeMap.get("enterprise_activities_DSR")));
			resMap.put("enterprise_turnover",StringUtil.valueOf(prodtypeMap.get("enterprise_turnover")));
			resMap.put("enterprise_activity_sales",StringUtil.valueOf(prodtypeMap.get("enterprise_activity_sales")));
			resMap.put("is_smallware",StringUtil.valueOf(prodtypeMap.get("is_smallware")));
		}
		resMap.put("type", "2");
		 
		paramMap.put("TABLE_NAME", "BU_PRODUCT_TYPE");
		paramMap.put("COL_NAME", "STATUS");
		List<?> Showtype = webcommonService.sysStatusList(paramMap);
		resMap.put("Showtype", Showtype);
		return new ModelAndView(rtPage,resMap);
	}
	
	
	@RequestMapping(value = "/service/prod/product_type/success.shtml")
	public ModelAndView success (@RequestParam(value = "imageFile", required = false) MultipartFile file, HttpServletRequest request, @RequestParam HashMap<String, Object> param,
			HttpServletResponse response) {	
		String rtPage = "/prod/product_type/success";//重定向
		Date date = new Date();
		Map<String,Object> resMap = new HashMap<String,Object>();
		String type=request.getParameter("type");
		String prodName=request.getParameter("name");
		String status=request.getParameter("status");
		String suitSex=request.getParameter("suit_sex");
		String suitGroup=request.getParameter("suit_group");
		String isSmallware=request.getParameter("isSmallware");
		BigDecimal feeRate =new BigDecimal(0);
		BigDecimal deposit =new BigDecimal(0);
		BigDecimal individualFeeRate =new BigDecimal(0);
		BigDecimal individualDeposit =new BigDecimal(0);
		if (!"".equals(request.getParameter("fee_rate"))){
			feeRate =new BigDecimal(request.getParameter("fee_rate"));
		}
		if (!"".equals(request.getParameter("deposit"))){
			deposit =new BigDecimal(request.getParameter("deposit"));
		}
		if (!"".equals(request.getParameter("individual_fee_rate"))){
			individualFeeRate =new BigDecimal(request.getParameter("individual_fee_rate"));
		}
		if (!"".equals(request.getParameter("individual_deposit"))){
			individualDeposit =new BigDecimal(request.getParameter("individual_deposit"));
		}
		int t_level=Integer.valueOf(request.getParameter("t_level"));
		// 品类等级为1时，则必填企业开通活动DSR、企业开通营业额、企业开通活动销量
		if (t_level == 1) {
			if ("".equals(request.getParameter("enterprise_activities_DSR"))){
				resMap.put("statusCode", "300");
				resMap.put("message", "企业开通活动DSR不能为空");
				return new ModelAndView(rtPage,resMap);
			}
			if ("".equals(request.getParameter("enterprise_turnover"))){
				resMap.put("statusCode", "300");
				resMap.put("message", "企业开通活动营业额不能为空");
				return new ModelAndView(rtPage,resMap);
			}
			if ("".equals(request.getParameter("enterprise_activity_sales"))){
				resMap.put("statusCode", "300");
				resMap.put("message", "企业开通活动销量不能为空");
				return new ModelAndView(rtPage,resMap);
			}
		}
		String remarks=request.getParameter("remarks");
		StaffBean staffBean = this.getSessionStaffBean(request);
		int staffId=Integer.valueOf(staffBean.getStaffID());
		try {
			if("1".equals(type)) {
				if(t_level <= 3) {
					String[] names = prodName.split("\r\n");
					List<ProductType> productTypeList = new ArrayList<ProductType>();
					for(String name : names) {
						if(!StringUtil.isEmpty(name)) {
							param.put("name", name);
							int getIsNotType = productTypeService.getIsNotType(param);
							if(getIsNotType > 0){
								resMap.put("statusCode", "300");
								resMap.put("message", name+"已存在，不能再增加");
								return new ModelAndView(rtPage,resMap);
							}
							int parentId=Integer.valueOf(request.getParameter("parent_id"));
							ProductType productType = new ProductType();
							productType.setName(name);
							productType.setParentId(parentId);
							productType.setStatus(status);
							productType.setSuitSex(suitSex);
							productType.setSuitGroup(suitGroup);
							productType.setFeeRate(feeRate);
							productType.setDeposit(deposit);
							productType.setIndividualFeeRate(individualFeeRate);
							productType.setIndividualDeposit(individualDeposit);
							productType.settLevel(t_level);
							productType.setCreateBy(staffId);
							productType.setCreateDate(date);
							productType.setUpdateBy(staffId);
							productType.setUpdateDate(date);
							productType.setRemarks(remarks);
							// 品类等级为1时，则必填企业开通活动DSR、企业开通营业额、企业开通活动销量
							if (t_level == 1) {
								BigDecimal enterpriseActivitiesDSR =new BigDecimal(request.getParameter("enterprise_activities_DSR"));
								int enterpriseTurnover = Integer.valueOf(request.getParameter("enterprise_turnover"));
								int enterpriseActivitySales = Integer.valueOf(request.getParameter("enterprise_activity_sales"));
								productType.setEnterpriseActivitiesDsr(enterpriseActivitiesDSR);
								productType.setEnterpriseTurnover(enterpriseTurnover);
								productType.setEnterpriseActivitySales(enterpriseActivitySales);
							}
							productType.setIsSmallware(isSmallware);
							productTypeList.add(productType);
						}else {
							resMap.put("statusCode", "300");
							resMap.put("message", "产品类型名称不能为空");
							return new ModelAndView(rtPage,resMap);
						}	
					}
					Map<String, Object> map = null;
					if(productTypeList.size() > 0) {
						map = productTypeService.addInsertSelective(productTypeList);
					}
					if(map != null) {
						resMap.put("id", map.get("ids"));
						resMap.put("name", map.get("names"));
					}
					resMap.put("type", type);
					resMap.put("statusCode", "200");
					resMap.put("message", "操作成功");
				}else{
					 resMap.put("statusCode", "300");
					 resMap.put("message","最多只能加三级分类"); 
				}		 
			}else if("2".equals(type)){
			/*	if("".equals(prodName)){
					resMap.put("statusCode", "300");
					resMap.put("message", "品类名称不能为空");
				}else{*/
				String[] names = prodName.split("\r\n");
				for (String name : names) {
					/*param.put("name", name);
					int getIsNotType = productTypeService.getIsNotType(param);
					if(getIsNotType > 1){
						resMap.put("statusCode", "300");
						resMap.put("message", name+"已存在，请重新命名");
						return new ModelAndView(rtPage,resMap);
					}*/
					
					int id=Integer.valueOf(request.getParameter("id"));
					
					ProductType productType1=productTypeService.selectByPrimaryKey(id);
					ProductTypeExample productTypeExample=new ProductTypeExample();
					productTypeExample.createCriteria().andDelFlagEqualTo("0").andNameEqualTo(name);
					List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
					if (!name.equals(productType1.getName())) {
						if (productTypes!=null && productTypes.size()>0) {
							resMap.put("statusCode", "300");
							resMap.put("message", name+"已存在，请重新命名");
							return new ModelAndView(rtPage,resMap);
					   }
					}
					
					int seq_no=Integer.valueOf(request.getParameter("seq_no"));
					ProductType productType=new ProductType();
					productType.setId(id);
					productType.setName(prodName);
					productType.setStatus(status);
					productType.setSeqNo(seq_no);
					productType.setSuitSex(suitSex);
					productType.setSuitGroup(suitGroup);
					productType.setFeeRate(feeRate);
					productType.setDeposit(deposit);
					productType.setIndividualFeeRate(individualFeeRate);
					productType.setIndividualDeposit(individualDeposit);
					productType.setUpdateBy(staffId);
					productType.setUpdateDate(date);
					productType.setRemarks(remarks);
					// 品类等级为1时，则必填企业开通活动DSR、企业开通营业额、企业开通活动销量
					if (t_level == 1) {
						BigDecimal enterpriseActivitiesDSR =new BigDecimal(request.getParameter("enterprise_activities_DSR"));
						int enterpriseTurnover = Integer.valueOf(request.getParameter("enterprise_turnover"));
						int enterpriseActivitySales = Integer.valueOf(request.getParameter("enterprise_activity_sales"));
						productType.setEnterpriseActivitiesDsr(enterpriseActivitiesDSR);
						productType.setEnterpriseTurnover(enterpriseTurnover);
						productType.setEnterpriseActivitySales(enterpriseActivitySales);
					}
					if(!StringUtil.isEmpty(request.getParameter("principal_staff_id"))) {
						productType.setPrincipalStaffId(Integer.parseInt(request.getParameter("principal_staff_id")));
					}
					productType.setIsSmallware(isSmallware);
					productTypeService.updateByPrimaryKeySelective(productType);
					resMap.put("id", id);
					resMap.put("name", prodName);
					resMap.put("type", type);
					resMap.put("statusCode", "200");
					resMap.put("message", "操作成功");
				}
				
				/*}*/
			} 
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("statusCode", "300");
			resMap.put("message", "操作异常");
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/service/prod/product_type/del_success.shtml")
	@ResponseBody
	public Map<String, Object> delSuccess(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = ""; 
		try {    
			int id=Integer.valueOf(request.getParameter("id"));
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			
			ProductTypeExample productTypeExample = new ProductTypeExample();
			ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
			
			productTypeCriteria.andParentIdEqualTo(id).andDelFlagEqualTo("0");
			
			int getprarent = productTypeService.countByExample(productTypeExample);
			
			if (getprarent==0) 
			{
				ProductType productType=new ProductType();
				productType.setId(id);
				productType.setUpdateBy(staffId);
				productType.setUpdateDate(new Date());
				productType.setDelFlag("1");
				productTypeService.updateByPrimaryKeySelective(productType);
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			}else
			{
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = "删除失败，先删除子类别";
			}
			  
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		resMap.put("id", request.getParameter("id"));
		return resMap;
	}
	
	@RequestMapping(value = "/service/prod/product_type/getProductTypeTree.shtml")
	@ResponseBody
	public List<ProductType> getProductTypeTree(HttpServletRequest request) {
		String parentId = request.getParameter("parentId");
		ProductTypeExample productTypeExample=new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
		if(!StringUtil.isEmpty(parentId)) {
			if("1".equals(parentId)) {
				//钟表运营部状态，只获取主营类目为钟表 
				String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
				if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
					String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
					if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
						productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
					}
				}
			}
			productTypeCriteria.andParentIdEqualTo(Integer.parseInt(parentId));
		}else {
			productTypeCriteria.andParentIdEqualTo(0);
		}
		List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
		return productTypes;
	}
	
	@RequestMapping(value="/service/prod/product_type/getProductTypeSub.shtml")
	@ResponseBody
	public List<ProductType> getProductTypeSub(HttpServletRequest request){
		String parentId=request.getParameter("parentId");
		
		if(StringUtil.isEmpty(parentId)){
			return new ArrayList<ProductType>();
		}
		
		ProductTypeExample productTypeExample=new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria=productTypeExample.createCriteria();
		if(!StringUtil.isEmpty(parentId)){
			productTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andParentIdEqualTo((Integer.valueOf(parentId)));
		}
		List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
		return productTypes;
	}
	
	@RequestMapping(value = "/service/prod/product_type/editBrandAptitude.shtml")
	public ModelAndView editBrandAptitude(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prod/product_type/editBrandAptitude";//重定向
		ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		Map<String,Object> resMap = new HashMap<String,Object>();	
		resMap.put("brandAptitude", productType.getBrandAptitude());
		ProductTypeAptitudePicExample example = new ProductTypeAptitudePicExample();
		ProductTypeAptitudePicExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andProductTypeIdEqualTo(productType.getId());
		List<ProductTypeAptitudePic> aptitudePicList = productTypeAptitudePicMapper.selectByExample(example);
		resMap.put("aptitudePicList", aptitudePicList);
		resMap.put("isView", request.getParameter("isView"));
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/service/prod/product_type/saveBrandAptitude.shtml")
	public ModelAndView saveBrandAptitude (HttpServletRequest request,HttpServletResponse response){	
		String rtPage = "/prod/product_type/success";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		String id=request.getParameter("id");
		String brandAptitude=request.getParameter("brandAptitude");
		String imgPaths=request.getParameter("imgPaths");
		ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(id));
		productType.setBrandAptitude(brandAptitude);
		productType.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		productType.setUpdateDate(new Date());
		productTypeService.updateByPrimaryKeySelective(productType);
		
		ProductTypeAptitudePicExample example = new ProductTypeAptitudePicExample();
		ProductTypeAptitudePicExample.Criteria c = example.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andProductTypeIdEqualTo(productType.getId());
		List<ProductTypeAptitudePic> oldAptitudePicList = productTypeAptitudePicMapper.selectByExample(example);
		List<String> addImgPathList = new ArrayList<String>();
		List<Integer> delIdsList = new ArrayList<Integer>();
		List<String> oldImgPathList = new ArrayList<String>();
		for(ProductTypeAptitudePic oldAptitudePic:oldAptitudePicList){
			oldImgPathList.add(oldAptitudePic.getPic());
		}
		String[] imgPathsArray = imgPaths.split(",");
		List<String> newImgPathList = new ArrayList<String>();
		if(imgPathsArray.length>0){
			for(String imgPath:imgPathsArray){
				if(!StringUtils.isEmpty(imgPath)){
					newImgPathList.add(imgPath);
				}
			}
			for(String newImgPath:newImgPathList){
				if(!oldImgPathList.contains(newImgPath)){
					addImgPathList.add(newImgPath);
				}
			}
			for(ProductTypeAptitudePic oldAptitudePic:oldAptitudePicList){
				if(!newImgPathList.contains(oldAptitudePic.getPic())){
					delIdsList.add(oldAptitudePic.getId());
				}
			}
			for(String addImgPath:addImgPathList){
				ProductTypeAptitudePic productTypeAptitudePic = new ProductTypeAptitudePic();
				productTypeAptitudePic.setProductTypeId(productType.getId());
				productTypeAptitudePic.setPic(addImgPath);
				productTypeAptitudePic.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				productTypeAptitudePic.setCreateDate(new Date());
				productTypeAptitudePic.setDelFlag("0");
				productTypeAptitudePicMapper.insertSelective(productTypeAptitudePic);
			}
			if(delIdsList!=null && delIdsList.size()>0){
				ProductTypeAptitudePicExample e = new ProductTypeAptitudePicExample();
				ProductTypeAptitudePicExample.Criteria c1 = e.createCriteria();
				c1.andDelFlagEqualTo("0");
				c1.andIdIn(delIdsList);
				List<ProductTypeAptitudePic> delProductTypeAptitudePics = productTypeAptitudePicMapper.selectByExample(e);
				ProductTypeAptitudePic ptap = new ProductTypeAptitudePic();
				ptap.setDelFlag("1");
				ptap.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				ptap.setUpdateDate(new Date());
				productTypeAptitudePicMapper.updateByExampleSelective(ptap, e);
				InputStream stream = ProductTypeController.class.getResourceAsStream("/base_config.properties");
				String defaultPath=null;
				try {
					Properties properties = new Properties();
					properties.load(stream);
					defaultPath = properties.getProperty("annex.filePath");
					stream.close();
					for(ProductTypeAptitudePic delAptitudePic:delProductTypeAptitudePics){
						File file = new File(defaultPath+delAptitudePic.getPic());
						//如果文件不存在
						if(!file.exists()){
							continue;
						}else{
							file.delete();
						}
					}
				} catch (IOException exception) {
					exception.printStackTrace();
					resMap.put("statusCode", "200");
					resMap.put("message", "操作成功");
					return new ModelAndView(rtPage,resMap);
				}
			}
		}else{
			ProductTypeAptitudePic ptap = new ProductTypeAptitudePic();
			ptap.setDelFlag("1");
			ptap.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			ptap.setUpdateDate(new Date());
			productTypeAptitudePicMapper.updateByExampleSelective(ptap, example);
			InputStream stream = ProductTypeController.class.getResourceAsStream("/base_config.properties");
			String defaultPath=null;
			try {
				Properties properties = new Properties();
				properties.load(stream);
				defaultPath = properties.getProperty("annex.filePath");
				stream.close();
				for(ProductTypeAptitudePic oldAptitudePic:oldAptitudePicList){
					File file = new File(defaultPath+oldAptitudePic.getPic());
					//如果文件不存在
					if(!file.exists()){
						continue;
					}else{
						file.delete();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				resMap.put("statusCode", "200");
				resMap.put("message", "操作成功");
				return new ModelAndView(rtPage,resMap);
			}
		}
		resMap.put("statusCode", "200");
		resMap.put("message", "操作成功");
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 
	 * @Title getPrincipalStaffId   
	 * @Description TODO(验证负责人ID是否存在)   
	 * @author pengl
	 * @date 2018年10月17日 上午9:55:11
	 */
	@ResponseBody
	@RequestMapping("/service/prod/product_type/getPrincipalStaffId.shtml")
	public Map<String, Object> getPrincipalStaffId(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = ""; 
		try {  
			String staffId = request.getParameter("staffId");
			if(!StringUtil.isEmpty(staffId)) {
				SysStaffInfo sysStaffInfo = sysStaffInfoService.selectByPrimaryKey(Integer.parseInt(staffId));
				if(sysStaffInfo == null) {
					resMap.put("status", 1);
				}
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			  
		} catch (Exception e) {
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put("code", code);
		resMap.put("msg", msg);
		return resMap;
	}
	
	/**
	 * 7天无理由退换设置页面
	 */
	@RequestMapping(value = "/service/prod/product_type/toEditReturn7days.shtml")
	public ModelAndView toEditReturn7days(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "prod/product_type/toEditReturn7days";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		resMap.put("productType", productType);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存7天无理由退换设置
	 */
	@ResponseBody
	@RequestMapping("/service/prod/product_type/editReturn7days.shtml")
	public Map<String, Object> editReturn7days(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {  
			String id = request.getParameter("id");
			String isReturn7days = request.getParameter("isReturn7days");
			if(!StringUtil.isEmpty(id)) {
				ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(id));
				productType.setIsReturn7days(isReturn7days);
				productType.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				productType.setUpdateDate(new Date());
				productTypeService.updateByPrimaryKeySelective(productType);
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "提交失败，请稍后重试");
		}
		return resMap;
	}
	
	/**
	 * 是否小商品页面
	 */
	@RequestMapping(value = "/service/prod/product_type/toEditSmallware.shtml")
	public ModelAndView toEditSmallware(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "prod/product_type/toEditSmallware";//重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		resMap.put("productType", productType);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 是否小商品保存
	 */
	@ResponseBody
	@RequestMapping("/service/prod/product_type/editSmallware.shtml")
	public Map<String, Object> editSmallware(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {  
			String id = request.getParameter("id");
			String isSmallware = request.getParameter("isSmallware");
			if(!StringUtil.isEmpty(id)) {
				ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(id));
				productType.setIsSmallware(isSmallware);
				productType.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				productType.setUpdateDate(new Date());
				productTypeService.update(productType);
			}
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "提交失败，请稍后重试");
		}
		return resMap;
	}
}
