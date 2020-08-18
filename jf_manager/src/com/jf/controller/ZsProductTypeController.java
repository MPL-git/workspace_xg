package com.jf.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.beans.Menu;
import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeCustomExample;
import com.jf.entity.ProductTypeCustomExample.ProductTypeCustomExampleCriteria;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.ZsProductType;
import com.jf.entity.ZsProductTypeCustom;
import com.jf.entity.ZsProductTypeExample;
import com.jf.entity.ZsproductTypeCustomExample;
import com.jf.entity.ZsproductTypeCustomExample.ZsproductTypeCustomCriteria;
import com.jf.service.ProductTypeService;
import com.jf.service.WebcommonService;
import com.jf.service.ZsProductTypeService;
import com.jf.vo.Page;

@Controller
public class ZsProductTypeController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private ZsProductTypeService zsProductTypeService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private WebcommonService webcommonService;
	
	@RequestMapping(value = "/prod/zsProductType/find.shtml")
    public ModelAndView productTypeFind(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/prod/zsProductType/index";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		try {
			List<Menu> selectZsProductType = zsProductTypeService.selectZsProductType(paramMap);
			ObjectMapper objectMapper = new ObjectMapper();
			resMap.put("zsProductTypeList", objectMapper.writeValueAsString(selectZsProductType));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/prod/zsProductType/list.shtml")
	public ModelAndView productTypeList(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prod/zsProductType/list";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer id = Integer.parseInt(String.valueOf(paramMap.get("id")));
		resMap.put("id", id);
		resMap.put("roleId", this.getSessionStaffBean(request).getRoleId());
		ZsProductType zsProductType = zsProductTypeService.selectByPrimaryKey(id);
		resMap.put("tLevel", zsProductType.gettLevel());
		return new ModelAndView(rtPage,resMap);
	} 
	
	@RequestMapping(value = "/prod/zsProductType/datalist.shtml")
	@ResponseBody 
	public  Map<String, Object> datalist(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		String totalCount ="0";
		try {
			
			int id=Integer.valueOf(request.getParameter("id"));
			
			ZsProductTypeExample zsProductTypeExample = new ZsProductTypeExample();
			ZsProductTypeExample.Criteria zsProductTypeCriteria = zsProductTypeExample.createCriteria();
			
			zsProductTypeCriteria.andParentIdEqualTo(id).andDelFlagEqualTo("0");
			
			int getprarent = zsProductTypeService.countByExample(zsProductTypeExample);
			
		    paramMap.put("ISNO", getprarent);
			paramMap=this.setPageParametersLiger(request,paramMap);
        	totalCount = zsProductTypeService.queryDataCount(paramMap);
        	dataList = zsProductTypeService.selectProductTypeList(paramMap);
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
	
	@RequestMapping(value = "/prod/zsProductType/add.shtml")
	public ModelAndView productTypeadd(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prod/zsProductType/toaddedit";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		String prarentId="";
		String prarentName="";
		Map<String,Object> tempMap1 = new HashMap<String,Object>();
		String totalCount ="0";
		paramMap.put("ISNO", 0);
		totalCount = zsProductTypeService.queryDataCount(paramMap);
		if ("0".equals(totalCount)){
			resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
			resMap.put(this.JSON_RESULT_MESSAGE, "请在左侧选择好父级品类后再操作");
			rtPage = "/success/success";
			return new ModelAndView(rtPage,resMap);
		}
		tempMap1.put("parent_id", paramMap.get("id"));
		int telvel=zsProductTypeService.gettlevvel(tempMap1);
		if(telvel==3)
		{
			resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
			resMap.put(this.JSON_RESULT_MESSAGE, "二级类目不可以再添加子类");
			rtPage = "/success/success";
			return new ModelAndView(rtPage,resMap);
		}

		int id=Integer.valueOf(request.getParameter("id"));
		
		ZsProductTypeExample zsProductTypeExample = new ZsProductTypeExample();
		ZsProductTypeExample.Criteria zsProductTypeCriteria = zsProductTypeExample.createCriteria();
		
		zsProductTypeCriteria.andParentIdEqualTo(id).andDelFlagEqualTo("0");
		
		int getprarent = zsProductTypeService.countByExample(zsProductTypeExample);
		
		if(getprarent==0){
			String getprarentId=zsProductTypeService.getprarentId(paramMap);//判断父级是否为根节点
			String getPrarentF=zsProductTypeService.getPrarentF(paramMap);//判断 父级的父级是否为根节点
			if("0".equals(getprarentId)||"0".equals(getPrarentF)){
				prarentId=StringUtil.valueOf(paramMap.get("id"));
				prarentName=zsProductTypeService.getProdTypeName(paramMap);
			}else
			{
				List prarentList=zsProductTypeService.getprarentList(paramMap);
				for (Object obj:prarentList){
					Map prodMap=(Map)obj;
					prarentId=StringUtil.valueOf(prodMap.get("parent_id"));
					prarentName=StringUtil.valueOf(prodMap.get("parent_name"));}
			}
		}else
		{
			prarentId=StringUtil.valueOf(paramMap.get("id"));
			if("1".equals(prarentId)){
				prarentName="本次创建为1级类目";
			}else
			{
				prarentName=zsProductTypeService.getProdTypeName(paramMap);
			}
		}
		
		resMap.put("id", 0);
		resMap.put("type", "1");
		resMap.put("parent_id", prarentId);
		resMap.put("parent_name", prarentName);
		resMap.put("t_level", telvel);
		paramMap.put("TABLE_NAME", "BU_ZS_PRODUCT_TYPE");
		paramMap.put("COL_NAME", "STATUS");
		List<?> Showtype = webcommonService.sysStatusList(paramMap);
		resMap.put("Showtype", Showtype);
		
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypes", productTypes);
		if(telvel == 2){//二级类目
			ZsProductType zsProductType = zsProductTypeService.selectByPrimaryKey(id);
			resMap.put("productTypeIds", zsProductType.getProductTypeIds());
			ProductTypeExample example = new ProductTypeExample();
			ProductTypeExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andParentIdEqualTo((Integer.parseInt(zsProductType.getProductTypeIds())));
			List<ProductType> secondProductTypes=productTypeService.selectByExample(example);
			resMap.put("secondProductTypes", secondProductTypes);
		}
		resMap.put("brandAptitude", "");
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/prod/zsProductType/edit.shtml")
	public ModelAndView productTypeEdit(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prod/zsProductType/toaddedit";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();	
		List<?> productList=zsProductTypeService.selectProductTypeListByid(paramMap);
		for (Object obj:productList){
			Map<?, ?> prodtypeMap=(Map<?, ?>)obj;
			Integer id = Integer.parseInt(StringUtil.valueOf(prodtypeMap.get("id")));
			resMap.put("id", id);
			resMap.put("name", StringUtil.valueOf(prodtypeMap.get("name")));
			resMap.put("parent_id", StringUtil.valueOf(prodtypeMap.get("parent_id"))); 
			resMap.put("parent_name",StringUtil.valueOf(prodtypeMap.get("parent_name")));
			resMap.put("status",StringUtil.valueOf(prodtypeMap.get("status"))); 
			resMap.put("seq_no",StringUtil.valueOf(prodtypeMap.get("seq_no")));
			
			DecimalFormat df = new DecimalFormat("#0.00");
			if(prodtypeMap.get("fee_rate")!=null){
				resMap.put("fee_rate",df.format(prodtypeMap.get("fee_rate")));
			}
			Integer t_level = Integer.parseInt(StringUtil.valueOf(prodtypeMap.get("t_level")));
			resMap.put("t_level",t_level);
			resMap.put("product_type_ids",StringUtil.valueOf(prodtypeMap.get("product_type_ids")));
			resMap.put("remarks",StringUtil.valueOf(prodtypeMap.get("remarks")));
			if(prodtypeMap.get("deposit")!=null){
				resMap.put("deposit", df.format(prodtypeMap.get("deposit")));
			}
			resMap.put("brandAptitude", prodtypeMap.get("brand_aptitude"));
			
			if(t_level.equals(2)){
				ZsProductType zsProductType = zsProductTypeService.selectByPrimaryKey(id);//二级类目
				ZsProductType parentZsProductType = zsProductTypeService.selectByPrimaryKey(zsProductType.getParentId());
				resMap.put("productTypeIds", parentZsProductType.getProductTypeIds());
				ProductTypeExample example = new ProductTypeExample();
				ProductTypeExample.Criteria criteria = example.createCriteria();
				criteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andParentIdEqualTo((Integer.parseInt(parentZsProductType.getProductTypeIds())));
				List<ProductType> secondProductTypes=productTypeService.selectByExample(example);
				resMap.put("secondProductTypes", secondProductTypes);
				//三级APP类目
				List<Integer> ids = new ArrayList<Integer>();
				String[] idsArray = zsProductType.getProductTypeIds().split(",");
				for(String idStr:idsArray){
					ids.add(Integer.parseInt(idStr));
				}
				ProductTypeExample pte = new ProductTypeExample();
				ProductTypeExample.Criteria c = pte.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andIdIn(ids);
				List<ProductType> thirdProductTypes = productTypeService.selectByExample(pte);
				List<Integer> selectSecondIds = new ArrayList<Integer>();
				for(ProductType pt:thirdProductTypes){
					if(!selectSecondIds.contains(pt.getParentId())){
						selectSecondIds.add(pt.getParentId());
					}
				}
				resMap.put("thirdProductTypes", thirdProductTypes);
				resMap.put("selectSecondIds", selectSecondIds);
			}
		}
		resMap.put("type", "2");
		 
		paramMap.put("TABLE_NAME", "BU_ZS_PRODUCT_TYPE");
		paramMap.put("COL_NAME", "STATUS");
		List<?> Showtype = webcommonService.sysStatusList(paramMap);
		resMap.put("Showtype", Showtype);
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypes", productTypes);
		return new ModelAndView(rtPage,resMap);
	}
	
	
	@RequestMapping(value = "/prod/zsProductType/success.shtml")
	public ModelAndView success (@RequestParam(value = "imageFile", required = false) MultipartFile file, HttpServletRequest request, @RequestParam HashMap<String, Object> param,
			HttpServletResponse response) {	
		String rtPage = "/prod/zsProductType/success";//重定向
		Date date = new Date();
		Map<String,Object> resMap = new HashMap<String,Object>();
		String type=request.getParameter("type");
		String prodName=request.getParameter("name");
		String status=request.getParameter("status");
		String productTypeIds = request.getParameter("productTypeIds");
		if(StringUtils.isEmpty(productTypeIds)){
			productTypeIds = request.getParameter("product_type_ids");
		}
		BigDecimal feeRate =new BigDecimal(0);
		if (!StringUtils.isEmpty(request.getParameter("fee_rate"))){
			feeRate =new BigDecimal(request.getParameter("fee_rate"));
		}
		int t_level=Integer.valueOf(request.getParameter("t_level"));
		String remarks=request.getParameter("remarks");
		String deposit=request.getParameter("deposit");
		String brandAptitude=request.getParameter("brandAptitude");
		StaffBean staffBean = this.getSessionStaffBean(request);
		int staffId=Integer.valueOf(staffBean.getStaffID());
		try {
			if("1".equals(type)) {
				if(t_level <= 3) {
					String[] names = prodName.split("\r\n");
					List<ZsProductType> zsProductTypeList = new ArrayList<ZsProductType>();
					for(String name : names) {
						if(!StringUtil.isEmpty(name)) {
							param.put("name", name);
							int getIsNotType = zsProductTypeService.getIsNotType(param);
							if(getIsNotType > 0){
								resMap.put("statusCode", "300");
								resMap.put("message", name+"已存在，不能再增加");
								return new ModelAndView(rtPage,resMap);
							}
							int parentId=Integer.valueOf(request.getParameter("parent_id"));
							ZsProductType zsProductType = new ZsProductType();
							zsProductType.setName(name);
							zsProductType.setParentId(parentId);
							zsProductType.setStatus(status);
							zsProductType.setFeeRate(feeRate);
							zsProductType.settLevel(t_level);
							zsProductType.setProductTypeIds(productTypeIds);
							zsProductType.setCreateBy(staffId);
							zsProductType.setCreateDate(date);
							zsProductType.setUpdateBy(staffId);
							zsProductType.setUpdateDate(date);
							zsProductType.setRemarks(remarks);
							if(!StringUtil.isEmpty(deposit)){
								zsProductType.setDeposit(new BigDecimal(deposit));
							}
							if(!StringUtil.isEmpty(brandAptitude)){
								zsProductType.setBrandAptitude(brandAptitude);
							}
							zsProductTypeList.add(zsProductType);
						}else {
							resMap.put("statusCode", "300");
							resMap.put("message", "产品类型名称不能为空");
							return new ModelAndView(rtPage,resMap);
						}	
					}
					Map<String, Object> map = null;
					if(zsProductTypeList.size() > 0) {
						map = zsProductTypeService.addInsertSelective(zsProductTypeList);
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
				if("".equals(prodName)){
					resMap.put("statusCode", "300");
					resMap.put("message", "品类名称不能为空");
				}else{
					int id=Integer.valueOf(request.getParameter("id"));
//					int seq_no=Integer.valueOf(request.getParameter("seq_no"));
					ZsProductType zsProductType=new ZsProductType();
					zsProductType.setId(id);
					zsProductType.setName(prodName);
					zsProductType.setStatus(status);
//					zsProductType.setSeqNo(seq_no);
					zsProductType.setFeeRate(feeRate);
					zsProductType.setProductTypeIds(productTypeIds);
					zsProductType.setUpdateBy(staffId);
					zsProductType.setUpdateDate(date);
					zsProductType.setRemarks(remarks);
					if(!StringUtil.isEmpty(deposit)){
						zsProductType.setDeposit(new BigDecimal(deposit));
					}
					if(!StringUtil.isEmpty(brandAptitude)){
						zsProductType.setBrandAptitude(brandAptitude);
					}
					zsProductTypeService.updateByPrimaryKeySelective(zsProductType);
					resMap.put("id", id);
					resMap.put("name", prodName);
					resMap.put("type", type);
					resMap.put("statusCode", "200");
					resMap.put("message", "操作成功");
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("statusCode", "300");
			resMap.put("message", "操作异常");
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/prod/zsProductType/del_success.shtml")
	@ResponseBody
	public Map<String, Object> delSuccess(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = ""; 
		try {    
			int id=Integer.valueOf(request.getParameter("id"));
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			
			ZsProductTypeExample zsProductTypeExample = new ZsProductTypeExample();
			ZsProductTypeExample.Criteria zsProductTypeCriteria = zsProductTypeExample.createCriteria();
			
			zsProductTypeCriteria.andParentIdEqualTo(id).andDelFlagEqualTo("0");
			
			int getprarent = zsProductTypeService.countByExample(zsProductTypeExample);
			
			if (getprarent==0) 
			{
				ZsProductType zsProductType=new ZsProductType();
				zsProductType.setId(id);
				zsProductType.setUpdateBy(staffId);
				zsProductType.setUpdateDate(new Date());
				zsProductType.setDelFlag("1");
				zsProductTypeService.updateByPrimaryKeySelective(zsProductType);
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
	
	@RequestMapping(value = "/prod/zsProductType/getProductTypeTree.shtml")
	@ResponseBody
	public List<ProductType> getProductTypeTree(HttpServletRequest request) {
		
		ProductTypeExample productTypeExample=new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0");
		List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
		return productTypes;
	}
	
	@RequestMapping(value="/prod/zsProductType/getProductTypeSub.shtml")
	@ResponseBody
	public Map<String, Object> getProductTypeSub(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		String parentIds=request.getParameter("parentIds");
		if(StringUtil.isEmpty(parentIds)){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "获取三级分类失败，请稍后重试");
			return resMap;
		}
		String[] secondProductTypeIds = parentIds.split(",");
		List<Integer> secondProductTypeIdList = new ArrayList<Integer>();
		for(String secondProductTypeId:secondProductTypeIds){
			secondProductTypeIdList.add(Integer.parseInt(secondProductTypeId));
		}
		ProductTypeExample productTypeExample=new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria=productTypeExample.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andParentIdIn(secondProductTypeIdList);
		List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		resMap.put("productTypes", productTypes);
		return resMap;
	}
	
	
	//招商类目一览表
	@RequestMapping(value = "/prod/zsProductType/catalogue.shtml")
	public ModelAndView cataLogue(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {	
		String rtPage = "/prod/zsProductType/catalogue";//重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		return new ModelAndView(rtPage,resMap);
		
	} 
	
	@RequestMapping(value = "/prod/zsProductType/zscataloguedata.shtml")
	@ResponseBody 
	public  Map<String, Object> datalistS(HttpServletRequest request, HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<?> dataList = null;
		try {
			
			ZsProductTypeExample zsProductTypeExample = new ZsProductTypeExample();
			ZsProductTypeExample.Criteria zsProductTypeCriteria = zsProductTypeExample.createCriteria();		
			zsProductTypeCriteria.andDelFlagEqualTo("0");
			
			paramMap=this.setPageParametersLiger(request,paramMap);
        	/*totalCount = zsProductTypeService.countdata(paramMap);*/
        	dataList = zsProductTypeService.zsselectProductTypeList(paramMap);

			resMap.put("Rows", dataList);
			/*resMap.put("Total", totalCount);*/
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<Map<String, Object>>();
		}
		return resMap;
	}
		/*Integer totalCount =0;		
		try {
			 
			ZsproductTypeCustomExample zsproductTypeCustomExample = new ZsproductTypeCustomExample();
			ZsproductTypeCustomCriteria createCriteria = zsproductTypeCustomExample.createCriteria();
			createCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(2);
			zsproductTypeCustomExample.setOrderByClause("a.product_type_ids asc,a.id asc");
			zsproductTypeCustomExample.setLimitStart(page.getLimitStart());
			zsproductTypeCustomExample.setLimitSize(page.getLimitSize());
			totalCount = zsProductTypeService.countZsProductTypeCustomByExample(zsproductTypeCustomExample);
			List<ZsProductTypeCustom> zsProductTypeCustom =  zsProductTypeService.selectZsProductTypeCustomByExample(zsproductTypeCustomExample);
			if (zsProductTypeCustom!=null) {
				for (ZsProductTypeCustom zsProductTypeCustom2 : zsProductTypeCustom) {
					String[] zsProductTypeCustoms=zsProductTypeCustom2.getProductTypeIds().split(",");
					List<Integer> ids=new ArrayList<Integer>();
					for (int i = 0; i < zsProductTypeCustoms.length; i++) {
						ids.add(Integer.valueOf(zsProductTypeCustoms[i]));
					}
					ProductTypeExample productTypeExample=new ProductTypeExample();
					ProductTypeExample.Criteria productTypeCriteria=productTypeExample.createCriteria();
					productTypeCriteria.andDelFlagEqualTo("0").andIdIn(ids);
					List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample);
					if (productTypes!=null) {
						for (ProductType productType : productTypes) {
							int productTypeid=productType.getParentId();						
							ProductTypeCustomExample ProductTypeCustomExample=new ProductTypeCustomExample();
							ProductTypeCustomExampleCriteria createcriteria=ProductTypeCustomExample.createCriteria();
							createcriteria.andDelFlagEqualTo("0");
							createcriteria.andproductTypeid(productTypeid);					
							List<ProductType> productTypess=productTypeService.selectByExample(ProductTypeCustomExample);
							if (productTypess!=null) {
								for (ProductType productType2 : productTypess) {
									 String productTypename=productType2.getName();
									 zsProductTypeCustom2.setProducttypeName(productTypename);
								}
							}
						}
					}			
			
				}
			}
			resMap.put("Rows", zsProductTypeCustom);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	
	//查看资质要求
	@RequestMapping(value = "/prod/info.shtml")
	public ModelAndView updateeditEstablishMail(HttpServletRequest request, String id) {
		ModelAndView m = new ModelAndView();			
		if(!StringUtil.isEmpty(id)) {
		  ZsProductType zsProductType = zsProductTypeService.selectByPrimaryKey(Integer.valueOf(id));
		  m.addObject("zsProductType", zsProductType);
		}			
		  m.setViewName("/prod/zsProductType/info");
		return m;
	}
}
