package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.ActivityAreaExample;
import com.jf.entity.ActivityExample;
import com.jf.entity.BrandteamType;
import com.jf.entity.BrandteamTypeExample;
import com.jf.entity.CustomPage;
import com.jf.entity.IndexPopupAd;
import com.jf.entity.MallCategoryCustom;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.MallCategory;
import com.jf.entity.MallCategoryCustomExample;
import com.jf.entity.MallCategoryExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.Product;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductCustomExample;
import com.jf.entity.ProductExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.StateCode;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityService;
import com.jf.service.BrandteamTypeService;
import com.jf.service.CouponService;
import com.jf.service.CustomPageService;
import com.jf.service.MallCategoryService;
import com.jf.service.MchtInfoService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class MallCategoryController extends BaseController {

	@Autowired
	private MallCategoryService mallCategoryService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private ActivityAreaService activityAreaService;
	
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private ProductBrandService productBrandService;
	
	@Autowired
	private BrandteamTypeService brandteamTypeService;
	
	@Autowired
	private CustomPageService customPageService;
	
	
	
	/**
	 * 
	 * @Title mallCategoryManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年7月19日 下午2:13:47
	 */
	@RequestMapping("/mallCategory/mallCategoryManager.shtml")
	public ModelAndView mallCategoryManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.addObject("statusList", DataDicUtil.getTableStatus("BU_MALL_CATEGORY", "STATUS")); //状态
		m.setViewName("/mallCategory/getMallCategoryList");
		return m;
	}
	
	/**
	 * 
	 * @Title getMallCategoryList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年7月19日 下午2:24:00
	 */
	@ResponseBody
	@RequestMapping("/mallCategory/getMallCategoryList.shtml")
	public Map<String, Object> getMallCategoryList(HttpServletRequest request, Page page, Integer mallCategoryId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MallCategoryCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MallCategoryCustomExample mallCategoryCustomExample = new MallCategoryCustomExample();
			MallCategoryCustomExample.MallCategoryCustomCriteria mallCategoryCustomCriteria = mallCategoryCustomExample.createCriteria();
			mallCategoryCustomCriteria.andDelFlagEqualTo("0");
			if(mallCategoryId != null ) {
				mallCategoryCustomCriteria.andIdEqualTo(mallCategoryId);
			}
			if(!StringUtil.isEmpty(request.getParameter("categoryName")) ) {
				mallCategoryCustomCriteria.andCategoryNameLike("%"+request.getParameter("categoryName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("status")) ) {
				mallCategoryCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate")) ) {
				mallCategoryCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate")) ) {
				mallCategoryCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")+" 23:59:59"));
			}
			mallCategoryCustomExample.setOrderByClause(" t.seq_no desc, t.create_date desc");
			mallCategoryCustomExample.setLimitStart(page.getLimitStart());
			mallCategoryCustomExample.setLimitSize(page.getLimitSize());
			totalCount = mallCategoryService.countByCustomExample(mallCategoryCustomExample);
			dataList = mallCategoryService.selectByCustomExample(mallCategoryCustomExample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title updateStatus   
	 * @Description TODO(上下架)   
	 * @author pengl
	 * @date 2018年7月19日 下午2:49:46
	 */
	@ResponseBody
	@RequestMapping("/mallCategory/updateStatus.shtml")
	public Map<String, Object> updateStatus(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mallCategoryId = request.getParameter("mallCategoryId");
			if(!StringUtil.isEmpty(mallCategoryId)) {
				MallCategoryExample mallCategoryExample = new MallCategoryExample();
				mallCategoryExample.createCriteria().andIdEqualTo(Integer.parseInt(mallCategoryId));
				MallCategory mallCategory = new MallCategory();
				mallCategory.setStatus(request.getParameter("status"));
				mallCategory.setUpdateBy(Integer.parseInt(staffID));
				mallCategory.setUpdateDate(date);
				mallCategoryService.updateByExampleSelective(mallCategory, mallCategoryExample);
			}else {
				code = "9999";
				msg = "ID为空！";
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
	
	/**
	 * 
	 * @Title updateSeqNo   
	 * @Description TODO(排序)   
	 * @author pengl
	 * @date 2018年7月19日 下午2:52:14
	 */
	@ResponseBody
	@RequestMapping("/mallCategory/updateSeqNo.shtml")
	public Map<String, Object> updateSeqNo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mallCategoryId = request.getParameter("mallCategoryId");
			String seqNo = request.getParameter("seqNo");
			if(!StringUtil.isEmpty(mallCategoryId) && !StringUtil.isEmpty(seqNo) ) {
				MallCategoryExample mallCategoryExample = new MallCategoryExample();
				mallCategoryExample.createCriteria().andIdEqualTo(Integer.parseInt(mallCategoryId));
				MallCategory mallCategory = new MallCategory();
				mallCategory.setSeqNo(Integer.parseInt(seqNo));
				mallCategory.setUpdateBy(Integer.parseInt(staffID));
				mallCategory.setUpdateDate(date);
				mallCategoryService.updateByExampleSelective(mallCategory, mallCategoryExample);
			}else {
				code = "9999";
				msg = "ID为空！";
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
	
	/**
	 * 
	 * @Title delMallCategory   
	 * @Description TODO(删除)   
	 * @author pengl
	 * @date 2018年7月19日 下午2:54:59
	 */
	@ResponseBody
	@RequestMapping("/mallCategory/delMallCategory.shtml")
	public Map<String, Object> delMallCategory(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String mallCategoryId = request.getParameter("mallCategoryId");
			if(!StringUtil.isEmpty(mallCategoryId) ) {
				MallCategoryExample mallCategoryExample = new MallCategoryExample();
				mallCategoryExample.createCriteria().andIdEqualTo(Integer.parseInt(mallCategoryId));
				MallCategory mallCategory = new MallCategory();
				mallCategory.setDelFlag("1");
				mallCategory.setUpdateBy(Integer.parseInt(staffID));
				mallCategory.setUpdateDate(date);
				mallCategoryService.updateByExampleSelective(mallCategory, mallCategoryExample);
			}else {
				code = "9999";
				msg = "ID为空！";
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
	
	/**
	 * 
	 * @Title addOrUpdateMallCategoryManager   
	 * @Description TODO(添加编辑)   
	 * @author pengl
	 * @date 2018年7月19日 下午3:21:36
	 */
	@RequestMapping("/mallCategory/addOrUpdateMallCategoryManager.shtml")
	public ModelAndView addOrUpdateMallCategoryManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		//上架的品牌团名称
		BrandteamTypeExample brandteamTypeExample = new BrandteamTypeExample();
		brandteamTypeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(brandteamTypeExample );
		m.addObject("brandteamTypes", brandteamTypes);
		
		//九大类目
		ProductTypeExample example = new ProductTypeExample();
		example.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(example );
		m.addObject("productTypes", productTypes);
		
		
		//商城一级分类
		MallCategoryExample mallCategoryExample = new MallCategoryExample();
		mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MallCategory> mallCategorys = mallCategoryService.selectByExample(mallCategoryExample );
		m.addObject("mallCategorys", mallCategorys);
		
		if(!StringUtil.isEmpty(request.getParameter("mallCategoryId")) ) {
			MallCategoryCustom mallCategoryCustom = mallCategoryService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("mallCategoryId")));
			
			 if("3".equals(mallCategoryCustom.getAdLinkType())){
				 Product product = productService.selectByPrimaryKey(Integer.parseInt(mallCategoryCustom.getAdLinkValue()));
				 if(product!=null){
					 mallCategoryCustom.setAdLinkValue(product.getCode());
				 }
			 }
			 if ("10".equals(mallCategoryCustom.getAdLinkType())){//将店铺id转为code存入
				 MchtInfo mcht = mchtInfoService.selectByPrimaryKey(Integer.parseInt(mallCategoryCustom.getAdLinkValue()));
				 if(mcht!=null){
					 mallCategoryCustom.setAdLinkValue(mcht.getMchtCode());
					 }
				} 
			m.addObject("mallCategoryCustom", mallCategoryCustom);

		}
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0")
			.andStatusEqualTo("1").andTLevelIn(Arrays.asList(0, 1));
		productTypeExample.setOrderByClause(" t_level asc");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		m.addObject("productTypeList", productTypeList);
		m.addObject("statusList", DataDicUtil.getTableStatus("BU_MALL_CATEGORY", "AD_LINK_TYPE")); //广告图链接类型
		m.setViewName("/mallCategory/addOrUpdateMallCategory");
		return m;
	}
	
	
	
	/**
	 * 广告保存前的检查
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	public  Map<String, String> MngAdMngCheckSubmit( MallCategoryCustom mallCategoryCustom) {
		Map<String, String> resMap = new HashMap<String, String>();
		if ("1".equals(mallCategoryCustom.getAdLinkType())){
			ActivityAreaExample activityAreaExample =new ActivityAreaExample();
			ActivityAreaExample.Criteria activityAreaCriteria=activityAreaExample.createCriteria();
			/*activityAreaCriteria.andDelFlagEqualTo("0").andSourceEqualTo("1").andStatusEqualTo("1").andIdEqualTo(adInfo.getLinkId());*/
			activityAreaCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(mallCategoryCustom.getAdLinkValue()));
			Integer totalCount=activityAreaService.countByExample(activityAreaExample);
			if (totalCount==0){
				resMap.put("YN", "0");
				resMap.put("msg", "会场不存在！");
				return resMap;
			}
		}
		if ("2".equals(mallCategoryCustom.getAdLinkType())){
			ActivityExample activityExample =new ActivityExample();
			ActivityExample.Criteria activityCriteria=activityExample.createCriteria();
			/*activityCriteria.andDelFlagEqualTo("0").andStatusEqualTo("6").andIdEqualTo(adInfo.getLinkId());*/
			activityCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(mallCategoryCustom.getAdLinkValue()));
			Integer totalCount=activityService.countByExample(activityExample);
			if (totalCount==0){
				resMap.put("YN", "0");
				resMap.put("msg", "活动不存在或未审核通过！");
				return resMap;
			}
		}
		if ("3".equals(mallCategoryCustom.getAdLinkType())){
/*			ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(adInfo.getLinkId());*/
			ProductCustomExample productCustomExample = new ProductCustomExample();
			productCustomExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(mallCategoryCustom.getAdLinkValue());
			List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
			if(productCustoms==null || productCustoms.size()<=0){
				resMap.put("YN", "0");
				resMap.put("msg", "商品不存在,或已下架");
				return resMap;

			}/*else{
				String productActivityStatus=productCustom.getProductActivityStatus();
				if (!"2".equals(productActivityStatus) && !"3".equals(productActivityStatus) && !"4".equals(productActivityStatus)){
					resMap.put("YN", "0");
					resMap.put("msg", "请检查商品的报名活动状态！");
					return resMap;
				}
			}*/
		}
		if ("7".equals(mallCategoryCustom.getAdLinkType())){//自建页面
			/*			ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(adInfo.getLinkId());*/
						CustomPage customPage = customPageService.selectByPrimaryKey(Integer.parseInt(mallCategoryCustom.getAdLinkValue()));
						if(customPage==null || !"0".equals(customPage.getDelFlag())){
							resMap.put("YN", "0");
							resMap.put("msg", "自建页面不存在");
							return resMap;

						}
					}
		
		if ("29".equals(mallCategoryCustom.getAdLinkType())){
			
			try {
				 if(!StringUtils.isEmpty(mallCategoryCustom.getAdLinkValue())){
					 List<Integer> couponIdList = new ArrayList<Integer>();
					 String[] couponIdsArray = mallCategoryCustom.getAdLinkValue().split(",");
					 for(String couponIdStr:couponIdsArray){
						 couponIdList.add(Integer.parseInt(couponIdStr));
					 }
					 if(couponIdList.size()>1){//输入的是多个优惠券ID
						 CouponExample example = new CouponExample();
						 example.createCriteria().andDelFlagEqualTo("0").andIdIn(couponIdList);
						 int count = couponService.countByExample(example);
						 if(couponIdList.size() != count){
								resMap.put("YN", "0");
								resMap.put("msg", "请输入正确的优惠券ID");
								return resMap;
						 }
					 }else{//输入的是单个优惠券ID
						CouponExample example = new CouponExample();
						CouponExample.Criteria c = example.createCriteria();
						c.andDelFlagEqualTo("0");
						c.andIdEqualTo(Integer.parseInt(mallCategoryCustom.getAdLinkValue()));
						List<Coupon> coupons = couponService.selectByExample(example);
						if(coupons==null || coupons.size() == 0){
							resMap.put("YN", "0");
							resMap.put("msg", "请输入正确的优惠券ID");
							return resMap;
						}
					 }
				 }else{//没有优惠券ID
					resMap.put("YN", "0");
					resMap.put("msg", "请输入正确优惠券ID");
					return resMap;
				 }
				
				
			} catch (Exception e) {//输入的优惠券ID不是数字
				// TODO: handle exception
				e.printStackTrace();
				resMap.put("returnCode", "0");
				resMap.put("returnMsg", "请输入正确的优惠券ID");
				return resMap;
			}

		}
		if ("10".equals(mallCategoryCustom.getAdLinkType())){
			MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
			mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(mallCategoryCustom.getAdLinkValue());
			Integer totalCount = mchtInfoService.countByExample(mchtInfoExample );
			if (totalCount==0){
				resMap.put("YN", "0");
				resMap.put("msg", "商家店铺不存在！");
				return resMap;

			}
		}
		
		resMap.put("YN", "1");
		return resMap;
	}
	
	
	
	
	/**
	 * 
	 * @Title addOrUpdateMallCategory   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年7月19日 下午3:39:20
	 */
	@RequestMapping("/mallCategory/addOrUpdateMallCategory.shtml")
	@ResponseBody
	public  Map<String, Object> addOrUpdateMallCategory(HttpServletRequest request,MallCategoryCustom mallCategoryCustom) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		String code = null;
 		String msg =null;
		try {
			Date date = new Date();
			String staffID = this.getSessionStaffBean(request).getStaffID();
			MallCategory mallCategory = new MallCategory();
			mallCategory.setCategoryName(request.getParameter("categoryName"));
			if(!StringUtil.isEmpty(request.getParameter("productType1Ids")) ) {
				mallCategory.setProductType1Ids(request.getParameter("productType1Ids"));
			}
			if(!StringUtil.isEmpty(request.getParameter("adPic")) ) {
				mallCategory.setAdPic(request.getParameter("adPic"));
				if(!StringUtil.isEmpty(request.getParameter("adLinkType")) ) {
					mallCategory.setAdLinkType(request.getParameter("adLinkType"));
				}
/*				if(!StringUtil.isEmpty(request.getParameter("adLinkValue")) ) {
					if("3".equals(request.getParameter("adLinkType"))) {
						
						mallCategory.setAdLinkValue(request.getParameter("adLinkValueProductId"));
					}else if("7".equals(request.getParameter("adLinkType"))) {
						
						mallCategory.setAdLinkValue(request.getParameter("adLinkValueMchtId"));
						
					}else {
						mallCategory.setAdLinkValue(request.getParameter("adLinkValue"));
					}
				}*/
			}else {
				mallCategory.setAdPic(request.getParameter("adPic"));
				mallCategory.setAdLinkType("");
				mallCategory.setAdLinkValue("");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("id")) ) {
				
	    		Map<String, String> mngAdMngCheckSubmit = MngAdMngCheckSubmit(mallCategoryCustom);
				if("0".equals(mngAdMngCheckSubmit.get("YN")) || "0".equals(mngAdMngCheckSubmit.get("returnCode"))){
					//检验错误的提示
					if("0".equals(mngAdMngCheckSubmit.get("YN"))){
						resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
						resMap.put(this.JSON_RESULT_MESSAGE,mngAdMngCheckSubmit.get("msg") );
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", mngAdMngCheckSubmit.get("msg"));
					}else {
						resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
						resMap.put(this.JSON_RESULT_MESSAGE,mngAdMngCheckSubmit.get("msg") );
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", mngAdMngCheckSubmit.get("returnMsg"));
					}
				}else{
				 
				 if("3".equals(mallCategoryCustom.getAdLinkType())){
						String pcode = mallCategoryCustom.getAdLinkValue();
						ProductExample productExample = new ProductExample();
						productExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(pcode+"");
						List<Product> products = productService.selectByExample(productExample);
						if(products.size()>0&&products!=null){
							Product product = products.get(0);
							mallCategory.setAdLinkValue(product.getId()+"");
						}
					
					}else if ("10".equals(mallCategoryCustom.getAdLinkType())){//将店铺code转成主键ID 存入
						String mcode = mallCategoryCustom.getAdLinkValue();
						MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
						mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(mcode+"");
						 List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.selectByExample(mchtInfoExample);
						if(mchtInfoCustoms.size()>0&&mchtInfoCustoms!=null){
							MchtInfoCustom mchtInfoCustom = mchtInfoCustoms.get(0);
							mallCategory.setAdLinkValue(mchtInfoCustom.getId()+"");
						}
					}else{
					mallCategory.setAdLinkValue(request.getParameter("adLinkValue"));

					}
					mallCategory.setId(Integer.parseInt(request.getParameter("id")));
					mallCategory.setUpdateBy(Integer.parseInt(staffID));
					mallCategory.setUpdateDate(date);
					mallCategoryService.updateByPrimaryKeySelective(mallCategory);
				
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
				}
			}else {
				
	    		Map<String, String> mngAdMngCheckSubmit = MngAdMngCheckSubmit(mallCategoryCustom);
				if("0".equals(mngAdMngCheckSubmit.get("YN")) || "0".equals(mngAdMngCheckSubmit.get("returnCode"))){
					//检验错误的提示
					if("0".equals(mngAdMngCheckSubmit.get("YN"))){
						resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
						resMap.put(this.JSON_RESULT_MESSAGE,mngAdMngCheckSubmit.get("msg") );
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", mngAdMngCheckSubmit.get("msg"));
					}else {
						resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
						resMap.put(this.JSON_RESULT_MESSAGE,mngAdMngCheckSubmit.get("msg") );
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", mngAdMngCheckSubmit.get("returnMsg"));
					}
				}else{
				 
				 if("3".equals(mallCategoryCustom.getAdLinkType())){
						String pcode = mallCategoryCustom.getAdLinkValue();
						ProductExample productExample = new ProductExample();
						productExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(pcode+"");
						List<Product> products = productService.selectByExample(productExample);
						if(products.size()>0&&products!=null){
							Product product = products.get(0);
							mallCategory.setAdLinkValue(product.getId()+"");
						}
					
					}else if ("10".equals(mallCategoryCustom.getAdLinkType())){//将店铺code转成主键ID 存入
						String mcode = mallCategoryCustom.getAdLinkValue();
						MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
						mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(mcode+"");
						 List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.selectByExample(mchtInfoExample);
						if(mchtInfoCustoms.size()>0&&mchtInfoCustoms!=null){
							MchtInfoCustom mchtInfoCustom = mchtInfoCustoms.get(0);
							mallCategory.setAdLinkValue(mchtInfoCustom.getId()+"");
						}
					}else{
						mallCategory.setAdLinkValue(request.getParameter("adLinkValue"));
					}
				 
				
				mallCategory.setStatus("0");
				mallCategory.setCreateBy(Integer.parseInt(staffID));
				mallCategory.setCreateDate(date);
				mallCategory.setDelFlag("0");
				mallCategoryService.insertSelective(mallCategory);
				
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
				}
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);

		return resMap ;
	}
	
	/**
	 * 
	 * @Title productCodeFun   
	 * @Description TODO(商品ID--->长ID转短ID)   
	 * @author pengl
	 * @date 2018年8月3日 下午8:50:07
	 */
	@ResponseBody
	@RequestMapping("/mallCategory/productCodeFun.shtml")
	public Map<String, Object> productCodeFun(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String productCode = request.getParameter("productCode");
			if(!StringUtil.isEmpty(productCode)) {
				ProductExample productExample = new ProductExample();
				productExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1")
					.andAuditStatusEqualTo("2").andCodeEqualTo(productCode);
				List<Product> productList = productService.selectByExample(productExample);
				if(productList != null && productList.size() > 0) {
					map.put("productId", productList.get(0).getId());
				}
			}else {
				code = "9999";
				msg = "商品ID为空！";
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
	
	/**
	 * 
	 * @Title mchtCodeFun   
	 * @Description TODO(商家序号)   
	 * @author pengl
	 * @date 2018年8月30日 上午10:14:55
	 */
	@ResponseBody
	@RequestMapping("/mallCategory/mchtCodeFun.shtml")
	public Map<String, Object> mchtCodeFun(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			String mchtCode = request.getParameter("mchtCode");
			if(!StringUtil.isEmpty(mchtCode)) {
				MchtInfoExample mchtInfoExample = new MchtInfoExample();
				mchtInfoExample.createCriteria().andDelFlagEqualTo("0")
					.andStatusEqualTo("1").andShopStatusEqualTo("1").andMchtCodeEqualTo(mchtCode);
				List<MchtInfo> mchtInfoList = mchtInfoService.selectByExample(mchtInfoExample);
				if(mchtInfoList != null && mchtInfoList.size() > 0) {
					map.put("mchtId", mchtInfoList.get(0).getId());
				}
			}else {
				code = "9999";
				msg = "商家序号为空！";
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
