package com.jf.controller;
import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.constant.Const;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.ProductWeightMapper;
import com.jf.entity.*;
import com.jf.entity.ProductPropValueCustomExample.ProductPropValueCustomCriteria;
import com.jf.service.*;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ProductController extends BaseController {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private ProductService productService;
	
	@Resource
	private ProductPicService productPicService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ProductItemService productItemService;
	
	@Resource
	private ProductPropService productPropService;
	@Resource
	private ProductPropValueService productPropValueService;
	
	@Resource
	private ProductDescPicService productDescPicService;
	
	@Resource
	private ProductAfterTempletService productAfterTempletService;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private MchtProductTypeService mchtProductTypeService;
	
	@Autowired
	private ProductAuditLogService productAuditLogService;
	
	@Autowired
	private ProductPropValueService productpropvalueservice;
	
	@Resource
	private PlatformContactService platformContactService;
	
	@Resource
	private ProductWeightMapper productWeightMapper;
	
	@Autowired
	private ProductBrandService productBrandService;
	
	@Autowired
	private SysStaffInfoService sysStaffInfoService;
	
	@Autowired
	private MchtPlatformContactService mchtPlatformContactService;

	@Autowired
	private ActivityProductService activityProductService;
	@Autowired
	private ProductPriceChangeLogService productPriceChangeLogService;

	@Autowired ProductVideoService productVideoService;

	@Autowired
	private ProductExtendService productExtendService;

	@Autowired
	private SysParamCfgService sysParamCfgService;

	@Autowired
	private ProductUpperLowerLogService productUpperLowerLogService;

	@Autowired
	private SysStaffRoleService sysStaffRoleService;
	@RequestMapping(value="/product/productIndex.shtml")
	public String productIndex(Model model,HttpServletRequest request){
		model.addAttribute("mchtType", request.getParameter("mchtType"));
		model.addAttribute("auditStatus", request.getParameter("auditStatus"));
		
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar calendar = Calendar.getInstance();//上个月1号0点
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		Calendar calendars = Calendar.getInstance();//本月明天0点
		calendars.add(Calendar.DAY_OF_MONTH, +1);
		calendars.set(Calendar.HOUR_OF_DAY,0);
		calendars.set(Calendar.MINUTE, 0);
		calendars.set(Calendar.SECOND, 0);
		
		String updateend = df.format(calendars.getTime());
		String updatebegin = df.format(calendar.getTime());
		model.addAttribute("update_end", updateend);
		model.addAttribute("update_begin", updatebegin);*/
		
		
		String staffID =this.getSessionStaffBean(request).getStaffID();
		/*Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			model.addAttribute("myContactId", myContactId);
			model.addAttribute("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		model.addAttribute("platformMchtContacts", platformMchtContact);
        
		model.addAttribute("isContact", isContact);*/
		
		//对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		model.addAttribute("isManagement", isManagement);
		model.addAttribute("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		model.addAttribute("sysStaffInfoCustomList", sysStaffInfoCustomList);
		
		//已开通列表跳转过来的
		if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
			model.addAttribute("mchtCode", request.getParameter("mchtCode"));
		}
		if(!StringUtil.isEmpty(request.getParameter("productBrandId"))) {
			ProductBrand productBrand = productBrandService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("productBrandId")));
			model.addAttribute("productBrandName", productBrand.getName());
			model.addAttribute("productBrandId", productBrand.getId());
		}
		
		// 商家商品构成 跳转
		if(!StringUtil.isEmpty(request.getParameter("productBrandName"))) {
			try {
				model.addAttribute("productBrandName", URLDecoder.decode(request.getParameter("productBrandName"),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtil.isEmpty(request.getParameter("type1Id"))) {
			model.addAttribute("type1Id", request.getParameter("type1Id"));
			try {
				model.addAttribute("type1Name", URLDecoder.decode(request.getParameter("type1Name"), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtil.isEmpty(request.getParameter("type2Id"))) {
			model.addAttribute("type2Id", request.getParameter("type2Id"));
			try {
				model.addAttribute("type2Name", URLDecoder.decode(request.getParameter("type2Name"), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtil.isEmpty(request.getParameter("status"))) {
			model.addAttribute("status", request.getParameter("status"));
		}
		if(!StringUtil.isEmpty(request.getParameter("saleType"))) {
			model.addAttribute("saleType", request.getParameter("saleType"));
		}

		//数据中心-商家商品数据点商品数
		if(!StringUtil.isEmpty(request.getParameter("mchtType"))) {
			model.addAttribute("mchtType", request.getParameter("mchtType"));
		}

		if(!StringUtil.isEmpty(request.getParameter("statisticsTag"))) {
			model.addAttribute("statisticsTag", request.getParameter("statisticsTag"));
		}
		return "/prod/product/productIndex";
	}
	
	@RequestMapping(value="/product/dataList.shtml")
	@ResponseBody
	public Map<String, Object> dataList(Model model,HttpServletRequest request,Page page){
		Map<String,Object> resMap = new HashMap<String,Object>();
		int totalCount =0;
		List<ProductCustom> productCustoms=new ArrayList<ProductCustom>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mchtType=request.getParameter("mchtType");
		String auditStatus=request.getParameter("auditStatus");
		//数据中心下的商家上架商品统计的商品点进去要审核通过的pop/spop标识
		String statisticsTag = request.getParameter("statisticsTag");

		if(StringUtil.isEmpty(mchtType) && StringUtil.isEmpty(auditStatus)){
			return resMap;
		}
		
		try {
			ProductCustomExample productCustomExample=new ProductCustomExample();
			ProductCustomExample.ProductCustomCriteria productCustomCriteria=productCustomExample.createCriteria();
			
			productCustomCriteria.andDelFlagEqualTo("0").andAuditStatusNotEqualTo("0");
			productCustomExample.setOrderByClause(" t.id desc");
			
			if(!StringUtil.isEmpty(mchtType)){
				productCustomCriteria.andMchtTypeEqualTo(request.getParameter("mchtType"));
			}
			if(!StringUtil.isEmpty(auditStatus)){
				if ("1".equals(auditStatus)){
					productCustomCriteria.andAuditStatusEqualTo("1");
				}else if("2".equals(auditStatus)){ //商品列表（待查）
					productCustomCriteria.andAuditStatusEqualTo("2"); //审核状态：2 通过
					productCustomCriteria.andStatusEqualTo("1"); //上架状态：1 上架
					productCustomCriteria.andAuditByIsNull(); //审核人为空
				}else{
					String[] strs={"2","3"};
					List<String> sList = Arrays.asList(strs);
					productCustomCriteria.andAuditStatusIn(sList);
				}				
			}
			if (!StringUtil.isEmpty(statisticsTag)){
				productCustomCriteria.andAuditStatusEqualTo("2");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("saleType"))){
				productCustomCriteria.andSaleTypeEqualTo(request.getParameter("saleType"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("shopStatus"))){
				productCustomCriteria.andShopStatusEqualTo(request.getParameter("shopStatus"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status"))){
				productCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
		
			
			if(!StringUtil.isEmpty(request.getParameter("productActivityStatus"))){
				productCustomCriteria.andProductActivityStatusEqualTo(request.getParameter("productActivityStatus"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("salePriceBegin"))){
				productCustomCriteria.andSalePriceGreaterThanOrEqualTo(new BigDecimal(request.getParameter("salePriceBegin")));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("salePriceEnd"))){
				productCustomCriteria.andSalePriceLessThanOrEqualTo(new BigDecimal(request.getParameter("salePriceEnd")));
			}

			if(!StringUtil.isEmpty(request.getParameter("costPriceBegin"))){
				productCustomCriteria.andCostPriceGreaterThanOrEqualTo(new BigDecimal(request.getParameter("costPriceBegin")));
			}

			if(!StringUtil.isEmpty(request.getParameter("costPriceEnd"))){
				productCustomCriteria.andCostPriceLessThanOrEqualTo(new BigDecimal(request.getParameter("costPriceEnd")));
			}
			
			String suitSex = request.getParameter("suitSex");
			if(!StringUtils.isEmpty(suitSex)){
				if(suitSex.equals("10")){//男
					productCustomCriteria.andSuitSexEqualToManOrWomen(suitSex);
				}else if(suitSex.equals("01")){//女
					productCustomCriteria.andSuitSexEqualToManOrWomen(suitSex);
				}else{//男丶女
					productCustomCriteria.andSuitSexEqualTo(suitSex);
				}
			}
			String suitGroup = request.getParameter("suitGroup");
			if(!StringUtils.isEmpty(suitGroup)){
				if(suitGroup.equals("100")){//青年
					productCustomCriteria.andSuitGroupLike("1%");
				}else if(suitGroup.equals("010") || suitGroup.equals("001")){//儿童，中老年
					productCustomCriteria.andSuitGroupLikeTo(suitGroup);
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("productType"))){
				productCustomCriteria.andProductTypeAll(productService.getProductTypeAllChild(Integer.valueOf(request.getParameter("productType"))));
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)){
					if(!StringUtil.isEmpty(isCwOrgProductTypeId)){
						productCustomCriteria.andProductTypeAll(productService.getProductTypeAllChild(Integer.valueOf(isCwOrgProductTypeId)));
					}
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				productCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))){
				productCustomCriteria.andMchtNameLikeTo("%"+request.getParameter("mchtName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				if(!StringUtil.isEmpty(request.getParameter("productBrandId"))){
					productCustomCriteria.andBrandIdEqualTo(Integer.parseInt(request.getParameter("productBrandId")));
				}else{
					productCustomCriteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("artNo"))){
				productCustomCriteria.andArtNoLike("%"+request.getParameter("artNo")+"%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("name"))){
				productCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("id"))){
				productCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("code"))){
				productCustomCriteria.andCodeEqualTo(request.getParameter("code"));
			}
			
			/*if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				productCustomCriteria.andPlatformContactEqualTo(platformContactId);
			}*/
			/*if(!StringUtil.isEmpty(request.getParameter("update_begin")) && !StringUtil.isEmpty(request.getParameter("update_end"))){
				productCustomCriteria.andUpdateDateBetween(dateFormat.parse(request.getParameter("update_begin")+" 00:00"), dateFormat.parse(request.getParameter("update_end")+" 23:59"));
			}*/
			if (!StringUtil.isEmpty(request.getParameter("platContactStaffId"))) {//对接人的商家
				MchtPlatformContactCustomExample mchtPlatformContactCustomExample=new MchtPlatformContactCustomExample();
				MchtPlatformContactCustomExample.MchtPlatformContactCustomCriteria mchtPlatformContactCustomCriteria=mchtPlatformContactCustomExample.createCriteria();
				mchtPlatformContactCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
				mchtPlatformContactCustomCriteria.andplatContactStaffId(Integer.valueOf(request.getParameter("platContactStaffId")));
				List<MchtPlatformContactCustom> mchtPlatformContactCustoms=mchtPlatformContactService.selectMchtPlatformContactCustomByExample(mchtPlatformContactCustomExample);
				List<Integer> mchtIdlist=new ArrayList<Integer>();
					for (MchtPlatformContactCustom mchtPlatformContactCustom : mchtPlatformContactCustoms) {
						mchtIdlist.add(mchtPlatformContactCustom.getMchtId());
					}
					if (mchtIdlist!=null && mchtIdlist.size()>0) {
						
						productCustomCriteria.andMchtIdIn(mchtIdlist);
					}else {
						productCustomCriteria.andMchtIdEqualTo(0);
					}
																											
				}
			if(!StringUtil.isEmpty(request.getParameter("update_begin"))){
				productCustomCriteria.andStartUpdatedateBeginDate(request.getParameter("update_begin")+" 00:00:00");
			}
			if(!StringUtil.isEmpty(request.getParameter("update_end"))){
				productCustomCriteria.andEndUpdatedateBeginDate(request.getParameter("update_end")+" 23:59:59");
			}
	
			totalCount=	productService.countProductCustomByExample(productCustomExample);	
			productCustomExample.setLimitStart(page.getLimitStart());
			productCustomExample.setLimitSize(page.getLimitSize());
			productCustoms =productService.selectProductCustomByExample(productCustomExample);
			for (ProductCustom productCustom:productCustoms) {
				if(!StringUtils.isEmpty(productCustom.getPic()) && !productCustom.getPic().contains("http")){
					productCustom.setPic(FileUtil.getMiddleImageName(productCustom.getPic()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", productCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}	
	
	@RequestMapping(value="/product/viewProduct.shtml")
	public String viewProduct(Model model,HttpServletRequest request){
		ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("productCustom", productCustom);
		ProductType productType=productTypeService.selectByPrimaryKey(productCustom.getProductTypeId());
		BigDecimal thirdFeeRate = new BigDecimal(0);
		if(productCustom.getBrandId().equals(0)){
			thirdFeeRate = productType.getIndividualFeeRate();
		}
		String productTypeStr="";
		productTypeStr=productType.getName()+" > "+productTypeStr;
		while (productType.getParentId()!=0&&productType.getParentId()!=1){
			productType=productTypeService.selectByPrimaryKey(productType.getParentId());
			productTypeStr=productType.getName()+" > "+productTypeStr;
			if(thirdFeeRate!=null && thirdFeeRate.equals(new BigDecimal(0) )){
				thirdFeeRate = productType.getIndividualFeeRate();
			}
		}
		
		model.addAttribute("productTypeStr", productTypeStr.substring(0,productTypeStr.length()-3));
		model.addAttribute("thirdFeeRate", thirdFeeRate);
		
		//适合性别
		String suiteSexStr="";
		if(!StringUtil.isEmpty(productCustom.getSuitSex())){
			if(productCustom.getSuitSex().charAt(0)=='1'){
				suiteSexStr=suiteSexStr+"、"+"男";
			}
			if(productCustom.getSuitSex().charAt(1)=='1'){
				suiteSexStr=suiteSexStr+"、"+"女";
			}
			if(!StringUtil.isEmpty(suiteSexStr)){
				suiteSexStr=suiteSexStr.substring(1);
			}
		}
		
		//适合人群
		String suiteGroupStr="";
		if(!StringUtil.isEmpty(productCustom.getSuitGroup())){
			if(productCustom.getSuitGroup().charAt(0)=='1'){
				suiteGroupStr=suiteGroupStr+"、"+"青年";
			}
			if(productCustom.getSuitGroup().charAt(1)=='1'){
				suiteGroupStr=suiteGroupStr+"、"+"儿童";
			}
			if(productCustom.getSuitGroup().charAt(2)=='1'){
				suiteGroupStr=suiteGroupStr+"、"+"中老年";
			}
			if(!StringUtil.isEmpty(suiteGroupStr)){
				suiteGroupStr=suiteGroupStr.substring(1);
			}
		}
		
		model.addAttribute("suiteSexStr", suiteSexStr);
		model.addAttribute("suiteGroupStr", suiteGroupStr);
		
		ProductPicExample productPicExample=new ProductPicExample();
		productPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(Integer.valueOf(request.getParameter("id")));
		productPicExample.setOrderByClause("seq_no asc");
		List<ProductPic> productPics=productPicService.selectByExample(productPicExample);
		for (ProductPic productPic:productPics) {
			if(productCustom.getSource().equals("0")){//0.醒购
				productPic.setPic(FileUtil.getQuality70ImageName(productPic.getPic()));
			}
		}
		model.addAttribute("pics", productPics);
		
		
//		ProductItemExample productItemExample=new ProductItemExample();
//		productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(Integer.valueOf(request.getParameter("id"))).andStockGreaterThan(0);
//		List<ProductItemCustom> productItemCustoms=productItemService.selectProductItemCustomByExample(productItemExample);
//		for (ProductItemCustom productItemCustom:productItemCustoms) {
//			productItemCustom.setPic(FileUtil.getSmallImageName(productItemCustom.getPic()));
//		}
//		model.addAttribute("productItems", productItemCustoms);
		
		
		List<ProductProp> productProps=productService.getProductPropByProductId(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("productProps", productProps);
		
		
		
		//商品详情描述信息
		if(productCustom.getProductDesc()!=null){
			String[] productDesc=productCustom.getProductDesc().split("&&&");
			model.addAttribute("productDesc", productDesc);
		}
		
		//商品详情图
		ProductDescPicExample productDescPicExample=new ProductDescPicExample();
		productDescPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(Integer.valueOf(request.getParameter("id")));
		productDescPicExample.setOrderByClause("seq_no asc");
		List<ProductDescPic> productDescPics=productDescPicService.selectByExample(productDescPicExample);
		for (ProductDescPic productDescPic:productDescPics) {
			productDescPic.setPic(FileUtil.getQuality70ImageName(productDescPic.getPic()));
		}
		model.addAttribute("productDescPics", productDescPics);
		
		//商品主图视频、商品详情视频
		ProductVideoExample productVideoExample = new ProductVideoExample();
		productVideoExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productCustom.getId());
		List<ProductVideo> productVideos = productVideoService.selectByExample(productVideoExample);
		Map<String,Object> map = new HashMap<>();
		for (ProductVideo productVideo : productVideos) {
			if("1".equals(productVideo.getVideoType())){
				map.put("mainVideo", productVideo.getVideoUrl());
			}
			if("2".equals(productVideo.getVideoType())){
				map.put("descVideo", productVideo.getVideoUrl());
			}
		}
		model.addAttribute("videoMap",map);
		//售后信息
		ProductAfterTemplet productAfterTemplet=productAfterTempletService.selectByPrimaryKey(productCustom.getCsTempletId());
		model.addAttribute("productAfterTemplet", productAfterTemplet);
		
		//审核记录
		ProductAuditLogCustomExample productAuditLogCustomExample = new ProductAuditLogCustomExample();
		ProductAuditLogCustomExample.ProductAuditLogCustomExampleCriteria productAuditLogCustomCriteria = productAuditLogCustomExample.createCriteria();
		productAuditLogCustomCriteria.andDelFlagEqualTo("0").andProductIdEqualTo(Integer.valueOf(request.getParameter("id")));
		productAuditLogCustomExample.setOrderByClause(" t.id desc");
		productAuditLogCustomExample.setLimitSize(5);
		productAuditLogCustomExample.setLimitStart(0);
		List<ProductAuditLogCustom> productAuditLogList = productAuditLogService.selectByCustomExample(productAuditLogCustomExample);
		model.addAttribute("productAuditLogList", productAuditLogList);
		model.addAttribute("productId", request.getParameter("id"));
		
		model.addAttribute("productAuditStatusList", DataDicUtil.getStatusList("BU_PRODUCT_AUDIT_LOG", "STATUS"));

		//所在活动ID/专区
		List<HashMap<String, Object>> activityResult = productService.getLastActivityIdByProductId(productCustom.getId());
		if(activityResult!=null && activityResult.size()>0){
			String source = activityResult.get(0).get("source").toString();
			if(source.equals("1")){//1.会场
				String activityAreaId = activityResult.get(0).get("activityAreaId").toString();
				model.addAttribute("lastActivityIdStr", "会场ID "+activityAreaId);
			}else if(source.equals("2")){//2.品牌特卖
				String activityId = activityResult.get(0).get("activityId").toString();
				model.addAttribute("lastActivityIdStr", "特卖活动ID "+activityId);
			}
		}else{
			List<HashMap<String, Object>> singleProductActivityResult  = productService.getLastSingleProductActivityIdByProductId(productCustom.getId());
			if(singleProductActivityResult!=null && singleProductActivityResult.size()>0){
				String typeDesc = singleProductActivityResult.get(0).get("typeDesc").toString();
				String singleProductActivityId = singleProductActivityResult.get(0).get("singleProductActivityId").toString();
				model.addAttribute("lastActivityIdStr", typeDesc+"ID "+singleProductActivityId);
			}
		}
		//往期品牌特卖ID
		String activityIds = productService.getActivityIdsByProductId(productCustom.getId());
		model.addAttribute("activityIds", activityIds);
		ProductWeightExample pwe = new ProductWeightExample();
		ProductWeightExample.Criteria c = pwe.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andProductIdEqualTo(productCustom.getId());
		List<ProductWeight> productWeights = productWeightMapper.selectByExample(pwe);
		if(productWeights!=null && productWeights.size()>0){
			ProductWeight productWeight = productWeights.get(0);
			model.addAttribute("productWeight", productWeight);
			int seasonWeight = 0;
			if(productWeight.getSeasonWeight() != null){
				seasonWeight = productWeight.getSeasonWeight();
			}
			int saleWeight = 0;
			if(productWeight.getSaleWeight()!=null){
				saleWeight = productWeight.getSaleWeight(); 
			}
			int saleAmountWeight = 0;
			if(productWeight.getSaleAmountWeight()!=null){
				saleAmountWeight = productWeight.getSaleAmountWeight();
			}
			/*int pvWeight = 0;
			if(productWeight.getPvWeight()!=null){
				pvWeight = productWeight.getPvWeight();
			}*/
			int mchtGradeWeight = 0;
			if(productWeight.getMchtGradeWeight()!=null){
				mchtGradeWeight = productWeight.getMchtGradeWeight();
			}
			int brandGradeWeight = 0;
			if(productWeight.getBrandGradeWeight()!=null){
				brandGradeWeight = productWeight.getBrandGradeWeight();
			}
			int commentWeight = 0;
			if(productWeight.getCommentWeight() != null){
				commentWeight = productWeight.getCommentWeight();
			}
			int manualWeight = 0;
			if(productWeight.getManualWeight()!=null){
				manualWeight = productWeight.getManualWeight();
			}
			model.addAttribute("totalWeight", seasonWeight+saleWeight+saleAmountWeight+mchtGradeWeight+brandGradeWeight+commentWeight+manualWeight);
		}

		//食品安全信息
		Integer productType1Id = productCustom.getProductType1Id();
		if(productType1Id==364){
			String flag = "0";
			SysParamCfg fruitsValue = sysParamCfgService.findByCode("PRODUCT_TYPE2_FRUITS_IDS");
			SysParamCfg healthValue = sysParamCfgService.findByCode("PRODUCT_TYPE2_HEALTH_IDS");
			Integer productType2Id = productCustom.getProductType2Id();

			String[] fruitSplit = fruitsValue.getParamValue().split(",");
			List<String> fruitsList = Arrays.asList(fruitSplit);
			String[] healthSplit = healthValue.getParamValue().split(",");
			List<String> healthList = Arrays.asList(healthSplit);

			if(fruitsList.contains(productType2Id+"")){
				flag="1";
			}else if (healthList.contains(productType2Id+"")){
				flag ="2";
			}
			model.addAttribute("flag",flag);
			ProductExtendExample productExtendExample = new ProductExtendExample();
			productExtendExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productCustom.getId());
			List<ProductExtend> productExtends = productExtendService.selectByExample(productExtendExample);
			if(productExtends.size()>0 && productExtends!=null){
				ProductExtend productExtend = productExtends.get(0);
				model.addAttribute("productExtend",productExtend);
			}
		}
		return "/prod/product/viewProduct";
	}
	
	@RequestMapping(value="/product/productAuditLogList.shtml")
	public String productAuditLogList(Model model,HttpServletRequest request,Integer productId){
		model.addAttribute("productId", productId);
		return "/prod/product/productAuditLogList";
	}
	
	@RequestMapping(value="/product/getProductAuditLogList.shtml")
	@ResponseBody
	public Map<String, Object> getProductAuditLogList(HttpServletRequest request, Page page, Integer productId){
		Map<String, Object> resMap=new HashMap<String, Object>();
		List<?> dataList = null;
		Integer totalCount = 0;
		ProductAuditLogCustomExample productAuditLogCustomExample = new ProductAuditLogCustomExample();
		ProductAuditLogCustomExample.ProductAuditLogCustomExampleCriteria productAuditLogCustomCriteria = productAuditLogCustomExample.createCriteria();
		productAuditLogCustomCriteria.andDelFlagEqualTo("0").andProductIdEqualTo(productId);
		productAuditLogCustomExample.setOrderByClause(" t.id desc");
		productAuditLogCustomExample.setLimitSize(page.getLimitSize());
		productAuditLogCustomExample.setLimitStart(page.getLimitStart());
		dataList = productAuditLogService.selectByCustomExample(productAuditLogCustomExample);
		totalCount = productAuditLogService.countByCustomExample(productAuditLogCustomExample);
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	
	@RequestMapping(value="/product/viewProductMchtInfo.shtml")
	public String viewProductMchtInfo(Model model,HttpServletRequest request){
		Integer mchtId=Integer.valueOf(request.getParameter("mchtId"));
		Integer productId=Integer.valueOf(request.getParameter("productId"));
		MchtInfoCustom mchtInfoCustom =mchtInfoService.selectMchtInfoCustomById(mchtId);
		List<MchtProductTypeCustom> mchtProductTypeCustoms=mchtProductTypeService.getMchtProductTypeCustomsByMchtId(mchtId, null);
		List<MchtProductBrandCustom> mchtProductBrandCustoms=mchtProductBrandService.getMchtProductBrandCustomsByMchtId(mchtId, null);
		ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(productId);
		String productTypeStr="";
		String productBrandStr="";
		for(MchtProductTypeCustom mchtProductTypeCustom:mchtProductTypeCustoms){
			productTypeStr=productTypeStr+"，"+mchtProductTypeCustom.getProductTypeName();
		}
		for(MchtProductBrandCustom mchtProductBrandCustom:mchtProductBrandCustoms){
			productBrandStr=productBrandStr+"，"+mchtProductBrandCustom.getProductBrandName();
		}
		if(productTypeStr.length()!=0){
			productTypeStr=productTypeStr.substring(1);
		}
		if(productBrandStr.length()!=0){
			productBrandStr=productBrandStr.substring(1);
		}
		
		model.addAttribute("productCustom", productCustom);
		model.addAttribute("productTypeStr", productTypeStr);
		model.addAttribute("productBrandStr", productBrandStr);
		model.addAttribute("mchtInfoCustom", mchtInfoCustom);
		
		return "/prod/product/viewProductMchtInfo";
	}
	
	
	//法务审核页面
	@RequestMapping(value="/product/lawAuditProduct.shtml")
	public String lawAuditProduct(Model model,HttpServletRequest request){
		ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("productCustom", productCustom);
		ProductItemExample productItemExample=new ProductItemExample();
		productItemExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(Integer.valueOf(request.getParameter("id")));
		List<ProductItemCustom> productItemCustoms=productItemService.selectProductItemCustomByExample(productItemExample);
		for (ProductItemCustom productItemCustom:productItemCustoms) {
			productItemCustom.setPic(FileUtil.getSmallImageName(productItemCustom.getPic()));
		}
		model.addAttribute("productItems", productItemCustoms);
		List<ProductProp> productProps=productService.getProductPropByProductId(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("productProps", productProps);
		model.addAttribute("auditStatusList", DataDicUtil.getStatusList("BU_PRODUCT", "AUDIT_STATUS"));

		try {
			InputStream stream = ActivityNewController.class.getResourceAsStream("/base_config.properties");
			Properties properties = new Properties();
			properties.load(stream);
			String mUrl = properties.getProperty("mUrl");
			model.addAttribute("mUrl", mUrl+"/share.html?id="); //手机预览路径
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/prod/product/lawAuditProduct";
	}
	
	//法务审核 提交
	@RequestMapping(value = "/product/lawAuditSubmit.shtml")
	@ResponseBody
	public Map<String, Object> lawAuditSubmit(Model model,HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if(StringUtil.isEmpty(request.getParameter("id"))){
				throw new ArgException("商品不存在");
			}
			Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id = Integer.valueOf(request.getParameter("id"));
			String auditRemarks=request.getParameter("auditRemarks");
			String auditStatus=request.getParameter("auditStatus");
			productAuditLogService.updateProductAndSaveProductAuditLog(id, auditRemarks, auditStatus, staffID);
		} catch (ArgException e){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	@ResponseBody
	@RequestMapping(value = "/product/updateProductItem.shtml")
	public Map<String, Object> updateProductItem(Model model,HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			String mallPrice = request.getParameter("mallPrice");
			String salePrice = request.getParameter("salePrice");
			String cooauditStatus = request.getParameter("cooauditStatus");
			String productid = request.getParameter("productid");

			if (!StringUtil.isEmpty(cooauditStatus) && !StringUtil.isEmpty(productid)) {
				if (cooauditStatus.equals("2")) {
					ActivityProductCustomExample activityProductCustomExample=new ActivityProductCustomExample();
					activityProductCustomExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(Integer.valueOf(productid));
					List<ActivityProductCustom> activityProductCustoms = activityProductService.selectByCustomExample(activityProductCustomExample);

					ActivityProduct activityProduct = activityProductService.selectByPrimaryKey(activityProductCustoms.get(0).getId());
					activityProduct.setUpdateBy(staffID);
					activityProduct.setUpdateDate(new Date());
					activityProduct.setCooAuditStatus("2");
					activityProductService.updateByPrimaryKey(activityProduct);
				}
			}

			if(StringUtil.isEmpty(request.getParameter("mallPrice"))){
				throw new ArgException("商品不存在");
			}
			productAuditLogService.updateProductItem(staffID, mallPrice, salePrice);
		} catch (ArgException e){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	@RequestMapping(value="/product/getPoductPic.shtml")
	@ResponseBody
	public List<ProductPic> getPoductPic(HttpServletRequest request){
		List<ProductPic> productPics=new ArrayList<ProductPic>();
		if(StringUtil.isEmpty(request.getParameter("productId"))){
			return productPics;
		}
		ProductPicExample productPicExample=new ProductPicExample();
		productPicExample.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(Integer.valueOf(request.getParameter("productId")));
		productPicExample.setOrderByClause("seq_no asc");
		productPics=productPicService.selectByExample(productPicExample);
		return productPics;
	}
	
	//商品售后模板首页
	@RequestMapping(value = "/product/afterTemplet/index.shtml")
	public String productAfterTemplet(Model model,HttpServletRequest request){
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			model.addAttribute("myContactId", myContactId);
			model.addAttribute("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		model.addAttribute("platformMchtContacts", platformMchtContact);
        
		model.addAttribute("isContact", isContact);
			
		return "/prod/productAfterTemplet/index";
	}
	
	//获取列表数据
	@RequestMapping(value = "/product/afterTemplet/data.shtml")
	@ResponseBody
	public Map<String, Object> productAfterTempletData(Model model,HttpServletRequest request,Page page){
		Map<String,Object> resMap = new HashMap<String,Object>();
		int totalCount =0;
		List<ProductAfterTempletCustom> productAfterTempletCustoms=new ArrayList<ProductAfterTempletCustom>();
		
		try {
			ProductAfterTempletCustomExample productAfterTempletCustomExample=new ProductAfterTempletCustomExample();
			ProductAfterTempletCustomExample.ProductAfterTempletCustomCriteria productAfterTempletCustomCriteria=productAfterTempletCustomExample.createCriteria();
		
			productAfterTempletCustomCriteria.andDelFlagEqualTo("0");
			
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				productAfterTempletCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))){
				productAfterTempletCustomCriteria.andMchtNameLikeTo("%"+request.getParameter("mchtName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId=Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				productAfterTempletCustomCriteria.andPlatformContactEqualTo(platformContactId);
			}
			
			totalCount=	productAfterTempletService.countProductAfterTempletCustomByExample(productAfterTempletCustomExample);	
			productAfterTempletCustomExample.setLimitStart(page.getLimitStart());
			productAfterTempletCustomExample.setLimitSize(page.getLimitSize());
			productAfterTempletCustoms =productAfterTempletService.selectProductAfterTempletCustomByExample(productAfterTempletCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", productAfterTempletCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	//规格标准名称管理首页
	@RequestMapping(value = "/product/SpecificationManagementList.shtml")
	public ModelAndView SpecificationManagementList(Model model,HttpServletRequest request) {
		  String rtPage="/prod/product/SpecificationManagementList";
		  Map<String, Object> resMap = new HashMap<String, Object>();
			try {		
				ProductTypeExample productTypeExample = new ProductTypeExample();
				productTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
				List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample);
				resMap.put("productTypeList", productTypes);
				
				ProductPropExample productPropExample=new ProductPropExample();
				productPropExample.createCriteria().andDelFlagEqualTo("0");
				List<ProductProp> productProps=productPropService.selectByExample(productPropExample);
				resMap.put("productPropsList", productProps);
								
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return new ModelAndView(rtPage, resMap);
		}
	
	//规格标准名称管理列表数据	  		
	@RequestMapping(value = "/product/SpecificationManagementListdata.shtml")
	@ResponseBody
	public Map<String, Object> SpecificationManagemen(HttpServletRequest request,ProductPropValueCustom productvropvalueCustom,Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;
		List<ProductPropValueCustom> dataList=new ArrayList<ProductPropValueCustom>();
     
		try {
	        ProductPropValueCustomExample  productpropvaluecustomexample=new ProductPropValueCustomExample();
	        ProductPropValueCustomCriteria criteria=productpropvaluecustomexample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			
	
			if(!StringUtil.isEmpty(request.getParameter("propValue"))) {
				
			   criteria.andPropValueLike(request.getParameter("propValue"));
								
			}
			if(!StringUtil.isEmpty(request.getParameter("alias"))) {
				
				criteria.andaliasLike(request.getParameter("alias"));	
				
			}
           
			if(!StringUtil.isEmpty(request.getParameter("createbymchtcode"))) {
				
				criteria.andMchtCodeEqualTo(request.getParameter("createbymchtcode"));	
			}
			
            if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				
				criteria.andProductTypeEqualTo(request.getParameter("productTypeId"));	
			}
			 
            if(!StringUtil.isEmpty(request.getParameter("productPropId"))) {
				
				criteria.andproductPropTypeEqualTo(request.getParameter("productPropId"));	
			}
                                     
            if(productvropvalueCustom.getSeqNo()!=null){//勾选排序值排序
            	
               productpropvaluecustomexample.setOrderByClause( " t.seq_no is NULL, t.seq_no = 0, t.seq_no asc," + " t.id asc " );
            	
            }else{
            	
               productpropvaluecustomexample.setOrderByClause(" t.id asc");
            	
            }
            
            if(!StringUtil.isEmpty(request.getParameter("SeqNo")) && request.getParameter("SeqNo").equals("1")){
            	criteria.andseqNoIsNull();           	
            }
            
            if(!StringUtil.isEmpty(request.getParameter("SeqNo")) && request.getParameter("SeqNo").equals("2")){
            	criteria.andseqNoIsNotNull();
            	
            }
            
            if(!StringUtil.isEmpty(request.getParameter("alIaS")) && request.getParameter("alIaS").equals("1")){
            	criteria.andAliasIsNull();
            	
            }
            
            if(!StringUtil.isEmpty(request.getParameter("alIaS")) && request.getParameter("alIaS").equals("2")){
            	criteria.andAliasIsNotNull();
            	
            }
            
			totalCount=productpropvalueservice.countByCustomExample(productpropvaluecustomexample);
			productpropvaluecustomexample.setLimitStart(page.getLimitStart());
			productpropvaluecustomexample.setLimitSize(page.getLimitSize());
			dataList=productpropvalueservice.selectByCustomExample(productpropvaluecustomexample);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;	
	}
	
	//修改排序值
	@ResponseBody
	@RequestMapping(value = "/product/updateSpecificationManagementList.shtml")
	public Map<String, Object> updateSingleProductActivity(HttpServletRequest request, Integer id, Integer seqNo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			ProductPropValueExample productPropValueExample = new ProductPropValueExample();
			ProductPropValueExample.Criteria productPropValueExampleCriteria = productPropValueExample.createCriteria();
			productPropValueExampleCriteria.andDelFlagEqualTo("0").andIdEqualTo(id);
			ProductPropValue productPropValue = new ProductPropValue();
			productPropValue.setSeqNo(seqNo);
			productPropValueService.updateByExampleSelective(productPropValue, productPropValueExample);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	//修改标准名称
	@ResponseBody
	@RequestMapping(value = "/prod/product/updateAlias.shtml")
	public Map<String, Object> updateAliasSingleProductActivity(HttpServletRequest request, Integer id, String alias) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		Boolean striBoolean=true;
		try {
			
			/*if (productPropValueList !=null && productPropValueList.size()>0) {*/
				ProductPropValueExample productPropValueExamplealias = new ProductPropValueExample();
				ProductPropValueExample.Criteria productPropValueExampleCriteria = productPropValueExamplealias.createCriteria();
				productPropValueExampleCriteria.andDelFlagEqualTo("0").andIdEqualTo(id);
				ProductPropValue productPropValuealias = new ProductPropValue();
				productPropValuealias.setAlias(alias);
				productPropValueService.updateByExampleSelective(productPropValuealias, productPropValueExamplealias);
				if (striBoolean) {
					ProductPropValueExample productPropValueExampl= new ProductPropValueExample();
					ProductPropValueExample.Criteria Criteria = productPropValueExampl.createCriteria();
					Criteria.andDelFlagEqualTo("0").andAliasEqualTo(alias).andseqNoIsNotNull();
					productPropValueExampl.setOrderByClause("id asc");
					List<ProductPropValue> productPropValueList=productpropvalueservice.selectByExample(productPropValueExampl);
					Integer productPropValueSeqNo=productPropValueList.get(0).getSeqNo();				
				    productPropValuealias.setSeqNo(productPropValueSeqNo);
				    productPropValueService.updateByExampleSelective(productPropValuealias, productPropValueExamplealias);	
				}
			/*}*/
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
     //批量修改排序值
	@RequestMapping(value="/prod/product/updateSpecificationManagementList.shtml")
	public ModelAndView changeStatupage(HttpServletRequest request,HttpServletResponse response,
				@RequestParam HashMap<String, Object> paramMap){
			String resPage = "/prod/product/updateSpecificationManagementList";
			HashMap<String, Object> resMap = new HashMap<String, Object>();	
			resMap.put("seqnoId", paramMap.get("seqnoId"));		
			return new ModelAndView(resPage, resMap);
		}
		
	
		@RequestMapping(value = "/product/updateSpecificationManagemendata.shtml")
		@ResponseBody
		public ModelAndView changeStatu(HttpServletRequest request, 
				HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap,String id,String alias,String seqNo) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			String rtPage = "/success/success";
			String code = null;
			String msg = ""; 
			try {    
				String[] split = id.split(",");
				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId=Integer.valueOf(staffBean.getStaffID());
				for (int i = 0; i < split.length; i++) {
					int seqnoId=Integer.valueOf(split[i]);  
					ProductPropValueCustomExample productpropValueCustomExample = new ProductPropValueCustomExample();
					ProductPropValueCustomCriteria criteria=productpropValueCustomExample.createCriteria();		
					criteria.andIdEqualTo(Integer.valueOf(seqnoId));
					criteria.andDelFlagEqualTo("0");
					ProductPropValue selectByPrimaryKey = productPropValueService.selectByPrimaryKey(seqnoId);
					selectByPrimaryKey.setUpdateBy(staffId);
					selectByPrimaryKey.setAlias(alias);
					selectByPrimaryKey.setSeqNo(Integer.valueOf(seqNo));
					selectByPrimaryKey.setUpdateDate(new Date());
					productPropValueService.updateByExampleSelective(selectByPrimaryKey, productpropValueCustomExample);
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
		
		@RequestMapping(value="/product/toEditManualWeight.shtml")
		public String toEditManualWeight(Model model,HttpServletRequest request){
			model.addAttribute("productId", request.getParameter("productId"));
			Integer productId = Integer.valueOf(request.getParameter("productId"));
			ProductWeightExample e = new ProductWeightExample();
			ProductWeightExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andProductIdEqualTo(productId);
			List<ProductWeight> productWeights = productWeightMapper.selectByExample(e);
			if(productWeights!=null && productWeights.size()>0){
				model.addAttribute("productWeight", productWeights.get(0));
			}
			return "/prod/product/toEditManualWeight";
		}
	
		//修改人工分
		@RequestMapping(value = "/product/saveProductWeight.shtml")
		@ResponseBody
		public Map<String, Object> saveProductWeight(Model model,HttpServletRequest request) {
			Map<String, Object> resMap=new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "成功");
			try {
				if(StringUtil.isEmpty(request.getParameter("productId"))){
					throw new ArgException("商品不存在");
				}
				String manualWeight = request.getParameter("manualWeight");
				Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
				Integer productId = Integer.valueOf(request.getParameter("productId"));
				ProductWeightExample e = new ProductWeightExample();
				ProductWeightExample.Criteria c = e.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andProductIdEqualTo(productId);
				List<ProductWeight> productWeights = productWeightMapper.selectByExample(e);
				ProductWeight pw = new ProductWeight();
				if(productWeights!=null && productWeights.size()>0){
					pw = productWeights.get(0);
					pw.setUpdateDate(new Date());
					pw.setUpdateBy(staffID);
				}else{
					pw.setDelFlag("0");
					pw.setCreateBy(staffID);
					pw.setCreateDate(new Date());
					pw.setProductId(productId);
				}
				pw.setManualWeight(Integer.parseInt(manualWeight));
				Product product = productService.selectByPrimaryKey(productId);
				int seasonWeight = 0;
				if(pw.getSeasonWeight() != null){
					seasonWeight = pw.getSeasonWeight();
				}
				int saleWeight = 0;
				if(pw.getSaleWeight()!=null){
					saleWeight = pw.getSaleWeight(); 
				}
				int pvWeight = 0;
				if(pw.getPvWeight()!=null){
					pvWeight = pw.getPvWeight();
				}
				int mchtGradeWeight = 0;
				if(pw.getMchtGradeWeight()!=null){
					mchtGradeWeight = pw.getMchtGradeWeight();
				}
				int brandGradeWeight = 0;
				if(pw.getBrandGradeWeight()!=null){
					brandGradeWeight = pw.getBrandGradeWeight();
				}
				product.setWeights(seasonWeight+saleWeight+pvWeight+mchtGradeWeight+brandGradeWeight+pw.getManualWeight());
				if(pw.getId()!=null){
					productWeightMapper.updateByPrimaryKeySelective(pw);
				}else{
					productWeightMapper.insertSelective(pw);
				}
				productService.updateByPrimaryKeySelective(product);
			} catch (ArgException e){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", e.getMessage());
				e.printStackTrace();
			}catch (Exception e) {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", e.getMessage());
				e.printStackTrace();
			}
			return resMap;
		}
		
		
		//商品复制列表
		@RequestMapping(value="/product/productCopy.shtml")
		public ModelAndView productCopy(Model model,HttpServletRequest request){
			  String rtPage="/prod/product/productCopy";			 
			  Map<String, Object> resMap = new HashMap<String, Object>();
			  return new ModelAndView(rtPage, resMap);
		}
		
		@RequestMapping(value="/product/productCopydata.shtml")
		@ResponseBody
		public Map<String, Object> productCopydata(Model model,HttpServletRequest request,Page page){
			Map<String,Object> resMap = new HashMap<String,Object>();
			int totalCount =0;
			List<ProductCustom> productCustoms=new ArrayList<ProductCustom>();
		
			try {
				ProductCustomExample productCustomExample=new ProductCustomExample();
				ProductCustomExample.ProductCustomCriteria productCustomCriteria=productCustomExample.createCriteria();
				
				productCustomCriteria.andDelFlagEqualTo("0").andAuditStatusNotEqualTo("0").andSaleTypeEqualTo("1");
				productCustomExample.setOrderByClause(" t.id desc");				
								
				//钟表运营部状态，只获取主营类目为钟表 
				String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
				if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
					String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
					if(!StringUtil.isEmpty(isCwOrgProductTypeId)){
						if(!StringUtil.isEmpty(isCwOrgProductTypeId)){
							productCustomCriteria.andProductTypeAll(productService.getProductTypeAllChild(Integer.valueOf(isCwOrgProductTypeId)));
						}
					}
				}
				
				if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
					productCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
				}
						
				if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
					productCustomExample.setProductBrandName(request.getParameter("productBrandName"));
					/*productCustomCriteria.andProductBrandName2EqualTo(request.getParameter("productBrandName"));*/
				}
								
				if(!StringUtil.isEmpty(request.getParameter("name"))){
					productCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
				}

				if(!StringUtil.isEmpty(request.getParameter("code"))){
					productCustomCriteria.andCodeEqualTo(request.getParameter("code"));
				}
					
				totalCount=	productService.countProductCustomByExample(productCustomExample);	
				productCustomExample.setLimitStart(page.getLimitStart());
				productCustomExample.setLimitSize(page.getLimitSize());
				productCustoms =productService.selectProductCustomByExample(productCustomExample);
				for (ProductCustom productCustom:productCustoms) {
					 productCustom.setPic(FileUtil.getMiddleImageName(productCustom.getPic()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			resMap.put("Rows", productCustoms);
			resMap.put("Total", totalCount);
			return resMap;
		}
		
		//商品复制信息界面
		@RequestMapping("/product/copyCommodityList.shtml")
		public ModelAndView copyCommodityList(HttpServletRequest request) {
			 ModelAndView m = new ModelAndView("/prod/product/copyCommodityList");			
			 m.addObject("id", request.getParameter("id"));
			 List<Integer> productId=new ArrayList<Integer>();
			 String[] split=request.getParameter("id").split(",");
			 for (String productIds : split) {
				  productId.add(Integer.valueOf(productIds));

			 } 
			 ProductCustomExample productCustom=new ProductCustomExample();
			 productCustom.createCriteria().andDelFlagEqualTo("0").andIdIn(productId);
			 List<ProductCustom> productCustoms=productService.selectProductCustomByExample(productCustom);
		     m.addObject("brandId", productCustoms.get(0).getBrandId());

			List<Integer> roleIds = new ArrayList<Integer>();
			roleIds.add(1);
			roleIds.add(142);
			SysStaffRoleExample sysStaffRoleExample1 = new SysStaffRoleExample();
			sysStaffRoleExample1.createCriteria().andStaffIdEqualTo(Integer.parseInt(this.getSessionStaffBean(request).getStaffID())).andRoleIdIn(roleIds).andStatusEqualTo("A");
			List<SysStaffRole> sysStaffRoleList1 = sysStaffRoleService.selectByExample(sysStaffRoleExample1);
			if(sysStaffRoleList1!=null && sysStaffRoleList1.size()>0){
				m.addObject("role142", true);
			}else{
				m.addObject("role142", false);
			}
			return m;
		}
		
		//开始复制商品
		@RequestMapping("/product/copycommoditydata.shtml")
		public ModelAndView copycommoditydata(HttpServletRequest request,String mchtCode) {
			String rtPage = "/success/success";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = "";
			String msg = "";
			try {
				 Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
					 	
				 MchtProductBrandCustomExample mchtProductBrandCustomExample=new MchtProductBrandCustomExample();
				 MchtProductBrandCustomExample.MchtProductBrandCustomCriteria mchtProductBrandCustomCriteria=mchtProductBrandCustomExample.createCriteria();
				 mchtProductBrandCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1");
				 mchtProductBrandCustomCriteria.andProductBrandIdEqualTo(Integer.valueOf(request.getParameter("brandId")));
				 mchtProductBrandCustomCriteria.andMchtCodeEqualTo(mchtCode);
				 List<MchtProductBrandCustom> mchtProductBrandCustoms=mchtProductBrandService.selectByCustomExample(mchtProductBrandCustomExample);
				 	 
				 if (mchtProductBrandCustoms.size()==0) {
					    code = StateCode.JSON_AJAX_ERROR.getStateCode();
						msg = "商家序号"+mchtCode+"运营状态异常或者商家序号"+mchtCode+"的品牌运营状态异常,不可以复制~！";
						resMap.put(this.JSON_RESULT_CODE, code);
						resMap.put(this.JSON_RESULT_MESSAGE, msg);
						return new ModelAndView(rtPage, resMap);
				 }else {
					 
					 List<Integer> productIdslist=new ArrayList<Integer>();
					 String[] split=request.getParameter("id").split(",");
					 for (String productIds : split) {
						  productIdslist.add(Integer.valueOf(productIds));

					 } 
					 int  product=productService.productCopy(mchtProductBrandCustoms.get(0).getMchtId(), productIdslist,staffID,request.getParameter("copyMoreTag"));
					 int total = productIdslist.size();				 
					 code = StateCode.JSON_AJAX_SUCCESS.getStateCode();					 
					 msg = "选择"+total+"条数据"+"成功"+product+"条";
					 resMap.put(this.JSON_RESULT_CODE, code);
					 resMap.put(this.JSON_RESULT_MESSAGE, msg);
					 return new ModelAndView(rtPage, resMap);				 
					 		
				}			
               
			} catch (Exception e) {
				e.printStackTrace();
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return new ModelAndView(rtPage, resMap); 
		}
		
	/**
	 * 商家商品构成
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/product/mchtProductForm.shtml")
	public String mchtProductForm(HttpServletRequest request, Model model) {
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		model.addAttribute("productTypeList", productTypeList); //1级类目
		return "/prod/product/mchtProductForm";
	}
	
	/**
	 * 商家商品构成
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/product/mchtProductFormData.shtml")
	@ResponseBody
	public Map<String, Object> mchtProductFormData(Model model, HttpServletRequest request, Page page){
		Map<String,Object> resMap = new HashMap<String,Object>();
		int totalCount = 0;
		List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				paramMap.put("mchtCode", request.getParameter("mchtCode"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("shopName"))){
				paramMap.put("shopName", request.getParameter("shopName"));
			}
					
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				paramMap.put("productBrandName", request.getParameter("productBrandName"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("type1Id"))){
				paramMap.put("type1Id", request.getParameter("type1Id"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("type2Id"))){
				paramMap.put("type2Id", request.getParameter("type2Id"));
			}
							
			totalCount = productService.countMchtProductForm(paramMap);	
			paramMap.put("limitStart", page.getLimitStart());
			paramMap.put("limitSize", page.getLimitSize());
			resList = productService.selectMchtProductForm(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", resList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	// 商品SKU信息
	@RequestMapping(value = "/product/productItemData.shtml")
	@ResponseBody
	public Map<String, Object> productItemData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer productId = Integer.valueOf(request.getParameter("productId"));
		Product product = productService.selectByPrimaryKey(productId);
		int totalCount = 0;
		List<ProductItemCustom> productItemCustoms = new ArrayList<ProductItemCustom>();
		try {
			ProductItemExample e = new ProductItemExample();
			e.createCriteria().andDelFlagEqualTo("0").andProductIdEqualTo(productId).andStockGreaterThan(0);
			totalCount = productItemService.countByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			productItemCustoms = productItemService.selectProductItemCustomByExample(e);
			for (ProductItemCustom productItemCustom:productItemCustoms) {
				if(product.getSource().equals("0")){//0.醒购平台
					productItemCustom.setPic(FileUtil.getSmallImageName(productItemCustom.getPic()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", productItemCustoms);
		resMap.put("Total", totalCount);
		return resMap;
	}

	// 商品上下架日志
	@RequestMapping(value = "/product/upperLowerLogItemData.shtml")
	@ResponseBody
	public Map<String, Object> upperLowerLogItemData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer productId = Integer.valueOf(request.getParameter("productId"));
		int totalCount = 0;
		List<ProductUpperLowerLog> productUpperLowerLogItem = new ArrayList<ProductUpperLowerLog>();
		try {
			ProductUpperLowerLogExample productUpperLowerLogExample = new ProductUpperLowerLogExample();
			productUpperLowerLogExample.createCriteria().andProductIdEqualTo(productId);
			productUpperLowerLogExample.setOrderByClause("create_date desc");
			productUpperLowerLogExample.setLimitStart(page.getLimitStart());
			productUpperLowerLogExample.setLimitSize(page.getLimitSize());
			productUpperLowerLogItem = productUpperLowerLogService.selectByExample(productUpperLowerLogExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", productUpperLowerLogItem);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 * 品牌折扣监控
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/product/brandProductDiscountMonitoring.shtml")
	public String brandProductDiscountMonitoring(HttpServletRequest request, Model model) {
		model.addAttribute("mchtType", request.getParameter("mchtType"));
		model.addAttribute("auditStatus", "23");
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		model.addAttribute("productTypeList", productTypeList); //1级类目
		//所有店铺的商管运营人员
		PlatformContactExample example = new PlatformContactExample();
		example.createCriteria().andDelFlagEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> yyPlatformContacts = platformContactService.selectByExample(example);
		model.addAttribute("yyPlatformContacts", yyPlatformContacts);
		return "/prod/discountMonitoring/BrandProductDiscountMonitoring";
	}
	
	/**
	 * 单品折扣监控
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/product/singleProductDiscountMonitoring.shtml")
	public String singleProductDiscountMonitoring(HttpServletRequest request, Model model) {
		model.addAttribute("mchtType", request.getParameter("mchtType"));
		model.addAttribute("auditStatus", "23");
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		model.addAttribute("productTypeList", productTypeList); //1级类目
		//所有店铺的商管运营人员
		PlatformContactExample example = new PlatformContactExample();
		example.createCriteria().andDelFlagEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> yyPlatformContacts = platformContactService.selectByExample(example);
		model.addAttribute("yyPlatformContacts", yyPlatformContacts);
		return "/prod/discountMonitoring/SingleProductDiscountMonitoring";
	}
	/**
	 * 店铺暂停商品监控
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/product/shopPauseMonitoring.shtml")
	public String shopPauseMonitoring(HttpServletRequest request, Model model) {
		model.addAttribute("mchtType", request.getParameter("mchtType"));
		model.addAttribute("auditStatus", "23");
		//所有店铺的商管运营人员
		PlatformContactExample example = new PlatformContactExample();
		example.createCriteria().andDelFlagEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> yyPlatformContacts = platformContactService.selectByExample(example);
		model.addAttribute("yyPlatformContacts", yyPlatformContacts);
		return "/prod/discountMonitoring/ShopPauseMonitoring";
	}
	/**
	 * 店铺关闭商品监控
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/product/shopShutMonitoring.shtml")
	public String shopShutMonitoring(HttpServletRequest request, Model model) {
		model.addAttribute("mchtType", request.getParameter("mchtType"));
		model.addAttribute("auditStatus", "23");
		//所有店铺的商管运营人员
		PlatformContactExample example = new PlatformContactExample();
		example.createCriteria().andDelFlagEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> yyPlatformContacts = platformContactService.selectByExample(example);
		model.addAttribute("yyPlatformContacts", yyPlatformContacts);
		return "/prod/discountMonitoring/ShopShutMonitoring";
	}
	
	/**
	 * 监控商品列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/product/dataMonitoringList.shtml")
	@ResponseBody
	public Map<String, Object> dataMonitoringList(Model model,HttpServletRequest request,Page page){
		Map<String,Object> resMap = new HashMap<String,Object>();
		int totalCount =0;
		List<ProductCustom> productCustoms=new ArrayList<ProductCustom>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mchtType=request.getParameter("mchtType");
		String auditStatus=request.getParameter("auditStatus");
		
		try {
			ProductCustomExample productCustomExample=new ProductCustomExample();
			ProductCustomExample.ProductCustomCriteria productCustomCriteria=productCustomExample.createCriteria();
			
			productCustomCriteria.andDelFlagEqualTo("0").andAuditStatusNotEqualTo("0").andSourceEqualTo("0");
			productCustomExample.setOrderByClause(" t.id desc");
			
			if(!StringUtil.isEmpty(mchtType)){
				productCustomCriteria.andMchtTypeEqualTo(request.getParameter("mchtType"));
			}
			if(!StringUtil.isEmpty(auditStatus)){
				if ("1".equals(auditStatus)){
					productCustomCriteria.andAuditStatusEqualTo("1");
				}else if("2".equals(auditStatus)){ //商品列表（待查）
					productCustomCriteria.andAuditStatusEqualTo("2"); //审核状态：2 通过
					productCustomCriteria.andStatusEqualTo("1"); //上架状态：1 上架
					productCustomCriteria.andAuditByIsNull(); //审核人为空
				}else{
					String[] strs={"1","2","3"};
					List<String> sList = Arrays.asList(strs);
					productCustomCriteria.andAuditStatusIn(sList);
				}				
			}
			if(!StringUtil.isEmpty(request.getParameter("saleType"))){
				productCustomCriteria.andSaleTypeEqualTo(request.getParameter("saleType"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("productType"))){
				productCustomCriteria.andProductTypeAll(productService.getProductTypeAllChild(Integer.valueOf(request.getParameter("productType"))));
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)){
					if(!StringUtil.isEmpty(isCwOrgProductTypeId)){
						productCustomCriteria.andProductTypeAll(productService.getProductTypeAllChild(Integer.valueOf(isCwOrgProductTypeId)));
					}
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				productCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))){
				productCustomCriteria.andMchtNameLikeTo("%"+request.getParameter("mchtName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				if(!StringUtil.isEmpty(request.getParameter("productBrandId"))){
					productCustomCriteria.andBrandIdEqualTo(Integer.parseInt(request.getParameter("productBrandId")));
				}else{
					productCustomCriteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("name"))){
				productCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("id"))){
				productCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("code"))){
				productCustomCriteria.andCodeEqualTo(request.getParameter("code"));
			}
			int monitoring = Integer.parseInt(request.getParameter("monitoring"));
			if((request.getParameter("monitoring"))!=null){
				if(monitoring==1||monitoring==2){
				productCustomCriteria.andSaleTypeEqualTo(request.getParameter("monitoring")+"");
				productCustomCriteria.andStatusEqualTo("1");
				if(monitoring==2){
					if(!StringUtil.isEmpty(request.getParameter("acType"))){
						productCustomCriteria.andSingleActivityOnLine(request.getParameter("acType"));
					}else {
						productCustomCriteria.andSingleActivityOnLine("");
					}
				}
				Integer disCountScope = Integer.parseInt(request.getParameter("discountScope"));
				if(disCountScope!=0){
					switch (disCountScope) {
					case 1:
						productCustomCriteria.betweenDiscountMonitoring(0.00, 0.10);
						break;
					case 2:
						productCustomCriteria.betweenDiscountMonitoring(0.10, 0.15);
						break;
					case 3:
						productCustomCriteria.betweenDiscountMonitoring(0.15, 0.20);
						break;
					default:
						break;
					}
				}else {
					productCustomCriteria.discountMonitoring(0.2);
				}
				}
				if(monitoring==3){
					productCustomCriteria.andStatusEqualTo("1");
					productCustomCriteria.andShopProductMonitoring(2);
					if(!StringUtils.isEmpty(request.getParameter("saleType"))){
						productCustomCriteria.andSaleTypeEqualTo(request.getParameter("saleType"));
					}
				}
				if(monitoring==4){
					productCustomCriteria.andStatusEqualTo("1");
					productCustomCriteria.andShopProductMonitoring(3);
					if(!StringUtils.isEmpty(request.getParameter("saleType"))){
						productCustomCriteria.andSaleTypeEqualTo(request.getParameter("saleType"));
					}
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("yyPlatformContactId"))){
				productCustomCriteria.andYyPlatformContactIdEqualTo(Integer.parseInt(request.getParameter("yyPlatformContactId")));
			}
			totalCount=	productService.countProductCustomByExample(productCustomExample);	
			productCustomExample.setLimitStart(page.getLimitStart());
			productCustomExample.setLimitSize(page.getLimitSize());
			productCustoms = productService.selectProductCustomByExample(productCustomExample);
			for (ProductCustom productCustom:productCustoms) {
				if(!StringUtils.isEmpty(productCustom.getPic()) && !productCustom.getPic().contains("http")){
					productCustom.setPic(FileUtil.getMiddleImageName(productCustom.getPic()));
				}}

	} catch (Exception e) {
		e.printStackTrace();
	}
	resMap.put("Rows", productCustoms);
	resMap.put("Total", totalCount);
	return resMap;
	}
	/**
	 * 法务折扣驳回
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/product/discountReject.shtml")
	@ResponseBody
	public Map<String, Object> discountReject(Model model,HttpServletRequest request){
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if(StringUtil.isEmpty(request.getParameter("id"))){
				throw new ArgException("商品不存在");
			}
			Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id = Integer.valueOf(request.getParameter("id"));
			Product product = new Product();
			product.setId(id);
			product.setStatus("0");
			product.setAuditStatus("3");
			Integer monitoring = Integer.parseInt(request.getParameter("monitoring"));
			if(monitoring==1||monitoring==2){
				product.setAuditRemarks("【低价监测】商品不可折扣低于2.0");
			}
			if(monitoring==3){
				product.setAuditRemarks("店铺暂停驳回");
			}
			if(monitoring==4){
				product.setAuditRemarks("店铺关闭驳回");
			}			
			productService.updateByPrimaryKeySelective(product);

			//商品上下架日志
			ProductUpperLowerLog productUpperLowerLog = new ProductUpperLowerLog();
			productUpperLowerLog.setProductId(id);
			productUpperLowerLog.setStatus(product.getStatus());
			productUpperLowerLog.setType(Const.PLATFORM);
			productUpperLowerLog.setOffReason(product.getOffReason());
			productUpperLowerLog.setCreateBy(staffID);
			productUpperLowerLog.setCreateDate(new Date());
			productUpperLowerLogService.insertSelective(productUpperLowerLog);
		} catch (ArgException e){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 法务折扣批量驳回
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/product/batchDiscountReject.shtml")
	@ResponseBody
	public Map<String, Object> batchDiscountReject(Model model,HttpServletRequest request){
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
		String idsString = request.getParameter("ids");
		if(StringUtil.isEmpty(idsString)){
			throw new ArgException("商品不存在");
		}
		String[] idsArr = idsString.split(",");
		productService.batchDiscountReject(idsArr,request);		
		} catch (ArgException e){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		
		return resMap;
	}
	
	/**
	 * 导出品牌折扣监控总excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/product/export.shtml")
	public void exportCount(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<ProductCustom> productCustoms = new ArrayList<ProductCustom>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();

			ProductCustomExample productCustomExample=new ProductCustomExample();
			ProductCustomExample.ProductCustomCriteria productCustomCriteria=productCustomExample.createCriteria();
			
			productCustomCriteria.andDelFlagEqualTo("0").andAuditStatusNotEqualTo("0").andSourceEqualTo("0");
			productCustomExample.setOrderByClause(" t.id desc");
						
			if(!StringUtil.isEmpty(request.getParameter("saleType"))){
				productCustomCriteria.andSaleTypeEqualTo(request.getParameter("saleType"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("productType"))){
				productCustomCriteria.andProductTypeAll(productService.getProductTypeAllChild(Integer.valueOf(request.getParameter("productType"))));
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)){
					if(!StringUtil.isEmpty(isCwOrgProductTypeId)){
						productCustomCriteria.andProductTypeAll(productService.getProductTypeAllChild(Integer.valueOf(isCwOrgProductTypeId)));
					}
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				productCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))){
				productCustomCriteria.andMchtNameLikeTo("%"+request.getParameter("mchtName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				if(!StringUtil.isEmpty(request.getParameter("productBrandId"))){
					productCustomCriteria.andBrandIdEqualTo(Integer.parseInt(request.getParameter("productBrandId")));
				}else{
					productCustomCriteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("name"))){
				productCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("id"))){
				productCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("code"))){
				productCustomCriteria.andCodeEqualTo(request.getParameter("code"));
			}
			if((request.getParameter("monitoring"))!=null){
				productCustomCriteria.andSaleTypeEqualTo(request.getParameter("monitoring")+"");
				productCustomCriteria.andStatusEqualTo("1");
				Integer disCountScope = Integer.parseInt(request.getParameter("discountScope"));
				if(disCountScope!=0){
					switch (disCountScope) {
					case 1:
						productCustomCriteria.betweenDiscountMonitoring(0.00, 0.10);
						break;
					case 2:
						productCustomCriteria.betweenDiscountMonitoring(0.10, 0.15);
						break;
					case 3:
						productCustomCriteria.betweenDiscountMonitoring(0.15, 0.20);
						break;
					default:
						break;
					}
				}else {
					productCustomCriteria.discountMonitoring(0.2);
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("yyPlatformContactId"))){
				productCustomCriteria.andYyPlatformContactIdEqualTo(Integer.parseInt(request.getParameter("yyPlatformContactId")));
			}
			productCustoms = productService.selectProductCustomByExample(productCustomExample);
			
			String[] titles = { "系统ID", "商品ID", "品牌","名称","一级类目","对接人","分类","吊牌价","商城价","活动价","SVIP折扣","折扣","类型","更新时间","公司名称"};
			ExcelBean excelBean = new ExcelBean("低价监控总列表.xls",
					"低价监控总列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(ProductCustom product:productCustoms){
				Date updateDate;
    	 	    if (product.getUpdateDate()!=null){
    	 			updateDate=product.getUpdateDate();
    	 	    }else{
    	 		  	updateDate=product.getCreateDate();
    	 	    }
    	 	    String tagPriceString="";
   	 	    	if(product.getTagPriceMin().compareTo(product.getTagPriceMax())!=0){
   	 	    		tagPriceString = product.getTagPriceMin()+"-"+product.getTagPriceMax();
				}else {
					tagPriceString=product.getTagPriceMin()+"";
				};
				String mallPriceString="";
				if(product.getMallPriceMin().compareTo(product.getMallPriceMax())!=0){
					mallPriceString = product.getMallPriceMin()+"-"+product.getMallPriceMax();
				}else {
					mallPriceString=product.getMallPriceMin()+"";
				};
				String salePriceString="";
				if(product.getSalePriceMin().compareTo(product.getSalePriceMax())!=0){
					salePriceString = product.getSalePriceMin()+"-"+product.getSalePriceMax();
				}else {
					salePriceString=product.getSalePriceMin()+"";
				};
				//Double discount = Math.floor((product.getMinSalePrice().doubleValue()/product.getTagPriceMin().doubleValue()*10) * 100) / 100;
				BigDecimal discount = product.getMinSalePrice().multiply(new BigDecimal("10")).divide(product.getMinTagPrice(),2,BigDecimal.ROUND_DOWN);
				String[] data = {
						product.getId()+"",
						"`"+product.getCode(),
						product.getProductBrandName(),
						product.getName(),
						product.getProductType1Name(),
						product.getYyContactName(),
						product.getProductTypeName(),
						tagPriceString,
						mallPriceString,
						salePriceString,
						product.getSvipDiscount() == null? "" : product.getSvipDiscount()+"",   
						discount+"",
						product.getSaleTypeDesc(),
						df.format(updateDate),
						product.getCompanyName()
			};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出单品折扣监控总excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/product/singleExport.shtml")
	public void singleExport(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<ProductCustom> productCustoms = new ArrayList<ProductCustom>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			
			ProductCustomExample productCustomExample=new ProductCustomExample();
			ProductCustomExample.ProductCustomCriteria productCustomCriteria=productCustomExample.createCriteria();
			
			productCustomCriteria.andDelFlagEqualTo("0").andAuditStatusNotEqualTo("0");
			productCustomExample.setOrderByClause(" t.id desc");
			
			if(!StringUtil.isEmpty(request.getParameter("saleType"))){
				productCustomCriteria.andSaleTypeEqualTo(request.getParameter("saleType"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("productType"))){
				productCustomCriteria.andProductTypeAll(productService.getProductTypeAllChild(Integer.valueOf(request.getParameter("productType"))));
			}
			
			//钟表运营部状态，只获取主营类目为钟表 
			String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
			if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
				String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
				if(!StringUtil.isEmpty(isCwOrgProductTypeId)){
					if(!StringUtil.isEmpty(isCwOrgProductTypeId)){
						productCustomCriteria.andProductTypeAll(productService.getProductTypeAllChild(Integer.valueOf(isCwOrgProductTypeId)));
					}
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				productCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))){
				productCustomCriteria.andMchtNameLikeTo("%"+request.getParameter("mchtName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				if(!StringUtil.isEmpty(request.getParameter("productBrandId"))){
					productCustomCriteria.andBrandIdEqualTo(Integer.parseInt(request.getParameter("productBrandId")));
				}else{
					productCustomCriteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("name"))){
				productCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("id"))){
				productCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("code"))){
				productCustomCriteria.andCodeEqualTo(request.getParameter("code"));
			}
			if((request.getParameter("monitoring"))!=null){
				productCustomCriteria.andSaleTypeEqualTo(request.getParameter("monitoring")+"");
				productCustomCriteria.andStatusEqualTo("1");
				int monitoring = Integer.parseInt(request.getParameter("monitoring"));
				if(monitoring==2){
					if(!StringUtil.isEmpty(request.getParameter("acType"))){
						productCustomCriteria.andSingleActivityOnLine(request.getParameter("acType"));
					}else {
						productCustomCriteria.andSingleActivityOnLine("");
					}
				}
				Integer disCountScope = Integer.parseInt(request.getParameter("discountScope"));
				if(disCountScope!=0){
					switch (disCountScope) {
					case 1:
						productCustomCriteria.betweenDiscountMonitoring(0.00, 0.10);
						break;
					case 2:
						productCustomCriteria.betweenDiscountMonitoring(0.10, 0.15);
						break;
					case 3:
						productCustomCriteria.betweenDiscountMonitoring(0.15, 0.20);
						break;
					default:
						break;
					}
				}else {
					productCustomCriteria.discountMonitoring(0.2);
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("yyPlatformContactId"))){
				productCustomCriteria.andYyPlatformContactIdEqualTo(Integer.parseInt(request.getParameter("yyPlatformContactId")));
			}
			productCustoms = productService.selectProductCustomByExample(productCustomExample);
			
			String[] titles = { "系统ID", "商品ID", "品牌","名称","一级类目","对接人","分类","吊牌价","商城价","活动价","SVIP折扣","折扣","商品来源","类型","更新时间","公司名称"};
			ExcelBean excelBean = new ExcelBean("低价监控总列表.xls",
					"低价监控总列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(ProductCustom product:productCustoms){
				Date updateDate;
				if (product.getUpdateDate()!=null){
					updateDate=product.getUpdateDate();
				}else{
					updateDate=product.getCreateDate();
				}
					String tagPriceString="";
	    	 	    if(product.getTagPriceMin().compareTo(product.getTagPriceMax())!=0){
	    	 		  tagPriceString = product.getTagPriceMin()+"-"+product.getTagPriceMax();
					}else {
						tagPriceString=product.getTagPriceMin()+"";
					};
					String mallPriceString="";
					if(product.getMallPriceMin().compareTo(product.getMallPriceMax())!=0){
						mallPriceString = product.getMallPriceMin()+"-"+product.getMallPriceMax();
					}else {
						mallPriceString=product.getMallPriceMin()+"";
					};
					String salePriceString="";
					if(product.getSalePriceMin().compareTo(product.getSalePriceMax())!=0){
						salePriceString = product.getSalePriceMin()+"-"+product.getSalePriceMax();
					}else {
						salePriceString=product.getSalePriceMin()+"";
					};
					Integer acType = Integer.parseInt(product.getAcType());
					String acTypeString="";
					switch (acType) {
					case 1:
						acTypeString="新用户专享";
						break;
					case 2:
						acTypeString="单品疯折";
						break;
					case 3:
						acTypeString="限时秒杀";
						break;
					case 4:
						acTypeString="新用户秒杀";
						break;
					case 5:
						acTypeString="积分商城";
						break;
					case 6:
						acTypeString="断码清仓";
						break;
					case 7:
						acTypeString="砍价免费拿";
						break;
					case 8:
						acTypeString="邀请享免单";
						break;
					case 9:
						acTypeString="优惠券";
						break;
					case 10:
						acTypeString="助力减价";
						break;

					default:
						break;
					}
				//Double discount = Math.floor((product.getMinSalePrice().doubleValue()/product.getTagPriceMin().doubleValue()*10) * 100) / 100;
				BigDecimal discount = product.getMinSalePrice().multiply(new BigDecimal("10")).divide(product.getMinTagPrice(),2,BigDecimal.ROUND_DOWN);
				String[] data = {
						product.getId()+"",
						"`"+product.getCode(),
						product.getProductBrandName(),
						product.getName(),
						product.getProductType1Name(),
						product.getYyContactName(),
						product.getProductTypeName(),
						tagPriceString,
						mallPriceString,
						salePriceString,
						product.getSvipDiscount() == null? "" : product.getSvipDiscount()+"",   
						discount+"",
						acTypeString,
						product.getSaleTypeDesc(),
						df.format(updateDate),
						product.getCompanyName()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//商品管理->法务商品抽查模板首页
	@RequestMapping(value = "/product/spotCheck/legalProduct.shtml")
	public String productSpotCheck(Model model,HttpServletRequest request){
		//Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		String staffID = this.getSessionStaffBean(request).getStaffID();
		//对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
		String isManagement = this.getSessionStaffBean(request).getIsManagement();
		model.addAttribute("isManagement", isManagement);
		model.addAttribute("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		model.addAttribute("sysStaffInfoCustomList", sysStaffInfoCustomList);

        //查询主营类目
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample);
		model.addAttribute("productTypeList", productTypes);

		return "/prod/productSpotCheck/legalProduct";
	}
	//获取法务商家列表数据
	@RequestMapping(value = "/product/productSpotCheckData.shtml")
	@ResponseBody
	public Map<String, Object> productSpotCheckData(Model model,HttpServletRequest request,Page page){
		Map<String,Object> resMap = new HashMap<String,Object>();
		int totalCount =0;
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String productType = request.getParameter("productType");//主营类目
			String mchtSalesRank = request.getParameter("mchtSalesRank");//规定销量排行
			String platformContact = request.getParameter("platContactStaffId");//对接人的要求
			String mchtStatus = request.getParameter("mchtStatus");//商家状态(规定值:正常)
			HashMap<String, Object> parameMap = new HashMap<>();

			if (!StringUtils.isEmpty(productType)&&!"0".equals(productType)){
				parameMap.put("productType",productType);
			}else {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "请选主营类目");
				return resMap;
			}
				int i = Integer.parseInt(mchtSalesRank);
			if (!StringUtils.isEmpty(mchtSalesRank)&&Integer.parseInt(mchtSalesRank)<=50){
				if(page.getLimitSize()<i){
					parameMap.put("limitStart",page.getLimitStart());
					parameMap.put("limitSize",page.getLimitSize());
				}else {
					parameMap.put("limitStart",0);
					parameMap.put("limitSize",i);
				}
			}else {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "请正确填写商家总销量排行前（X）名，但不能超过50");
				return resMap;
			}
			if(!"".equals(platformContact)&&platformContact.length()>0){
				parameMap.put("staffID",platformContact);
			}

			List<Map<String, Object>> spotChecks = mchtInfoService.selectLegalSpotCheck(parameMap);
			resMap.put("Rows", spotChecks);
			parameMap.put("limitSize",i);
			List<Map<String, Object>> counts = mchtInfoService.selectLegalSpotCheck(parameMap);
			resMap.put("Total",counts.size());
		} catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "系统繁忙,请联系管理员");
			e.printStackTrace();
			return resMap;
		}
		return resMap;
	}

	@RequestMapping("/product/spotCheck/spotCheckExport.shtml")
	public void spotCheckExport(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String productType = request.getParameter("productType");//主营类目
			String mchtSalesRank = request.getParameter("mchtSalesRank");//规定销量排行
			String platformContact = request.getParameter("platContactStaffId");//对接人的要求
			String mchtStatus = request.getParameter("mchtStatus");//商家状态(规定值:正常)
			HashMap<String, Object> parameMap = new HashMap<>();

			if (!StringUtils.isEmpty(productType)&&!"0".equals(productType)){
				parameMap.put("productType",productType);
			}
			if (!StringUtils.isEmpty(mchtSalesRank)&&Integer.parseInt(mchtSalesRank)<=50){
			int i = Integer.parseInt(mchtSalesRank);
					parameMap.put("limitStart",0);
					parameMap.put("limitSize",i);
			}
			if(!"".equals(platformContact)&&platformContact.length()>0){
				parameMap.put("staffID",platformContact);
			}
			List<Map<String, Object>> spotChecks = mchtInfoService.selectLegalSpotCheck(parameMap);

			//List<SubOrderCustom> subOrderCustoms = null;
			if (spotChecks!=null&&spotChecks.size()>0){
				String result = "";
				String  type = String.valueOf(spotChecks.get(0).get("productType"));
				for (int i = 0; i < type.length(); i++) {
					if (!"*".contains(type.charAt(i)+"")) {
						result += type.charAt(i);
					}
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String[] titles = { "商家序号", "商家名称", "店铺名称", "主营类目", "商管运营", "总SKU数", "法务对接人"};
				ExcelBean excelBean = new ExcelBean(""+sdf.format(new Date())+result+"商家销售前"+mchtSalesRank+"排行.xls", ""+sdf.format(new Date())+result+"商家销售前"+mchtSalesRank+"排行", titles);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				List<String[]> datas = new ArrayList<String[]>();
				for (Map<String, Object> check : spotChecks) {
					String[] data = {
							check.get("mchtCode") == null?"":check.get("mchtCode").toString(),
							check.get("name") == null?"":check.get("name").toString(),
							check.get("shopName") == null?"":check.get("shopName").toString(),
							check.get("productType") == null?"":check.get("productType").toString(),
							check.get("yy_contact_name") == null?"":check.get("yy_contact_name").toString(),
							check.get("skuTotal") == null?"":check.get("skuTotal").toString(),
							check.get("totalAuditBy") == null?"":check.get("totalAuditBy").toString()
					};
					datas.add(data);
				}
				excelBean.setDataList(datas);
				ExcelUtils.export(excelBean, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	}


