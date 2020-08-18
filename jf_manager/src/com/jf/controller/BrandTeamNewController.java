package com.jf.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.ActivityAreaExample;
import com.jf.entity.ActivityExample;
import com.jf.entity.BrandteamType;
import com.jf.entity.BrandteamTypeCustom;
import com.jf.entity.BrandteamTypeCustomExample;
import com.jf.entity.BrandteamTypeExample;
import com.jf.entity.BrandteamTypeadInfo;
import com.jf.entity.BrandteamTypeadInfoCustom;
import com.jf.entity.BrandteamTypeadInfoCustomExample;
import com.jf.entity.BrandteamTypeadInfoExample;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.CustomPage;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateAreaExample;
import com.jf.entity.DecorateInfo;
import com.jf.entity.DecorateModule;
import com.jf.entity.DecorateModuleCustom;
import com.jf.entity.DecorateModuleExample;
import com.jf.entity.MallCategory;
import com.jf.entity.MallCategoryExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.ModuleMap;
import com.jf.entity.ModuleMapExample;
import com.jf.entity.Product;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductCustomExample;
import com.jf.entity.ProductExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityService;
import com.jf.service.BrandteamTypeService;
import com.jf.service.BrandteamTypeadInfoService;
import com.jf.service.CouponService;
import com.jf.service.CustomPageService;
import com.jf.service.DecorateAreaService;
import com.jf.service.DecorateModuleService;
import com.jf.service.MallCategoryService;
import com.jf.service.MchtInfoService;
import com.jf.service.ModuleMapService;
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;
import com.jf.vo.Page;

@Controller
public class BrandTeamNewController extends BaseController {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private BrandteamTypeService brandteamTypeService;
	
	@Resource
	private BrandteamTypeadInfoService brandteamTypeadInfoService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ActivityAreaService activityAreaService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private ProductService productService;
	
	@Autowired
	private DecorateAreaService decorateAreaService;
	
	@Autowired
	private DecorateModuleService decorateModuleService;
	
	@Autowired
	private ModuleMapService moduleMapService;
	
	@Autowired
	private MallCategoryService mallCategoryService;
	
	@Resource
	private CustomPageService customPageService;
	
	@Resource
	private CouponService couponService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	
	
	//品牌团类目
	@RequestMapping(value = "/brandgroupRenovationNew/brandgroupRenovation.shtml")
	public ModelAndView brandgroupRenovation(HttpServletRequest request) {
		String rtPage = "brandgroupRenovationNew/brandteamTypelist";
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andTLevelEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypeList", productTypeList); //一级类目
		return new ModelAndView(rtPage,resMap);
	}
	
	//品牌团类目数据
	@RequestMapping(value = "/brandteamType/data.shtml")
	@ResponseBody
	public Map<String, Object> brandteamTypedata(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			BrandteamTypeCustomExample brandteamTypeCustomExample = new BrandteamTypeCustomExample();
			brandteamTypeCustomExample.setOrderByClause("IFNULL(btt.seq_no,999999999) asc");
			BrandteamTypeCustomExample.Criteria criteria = brandteamTypeCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			String productTypeId = request.getParameter("productTypeId");
			String name = request.getParameter("name");
			String status = request.getParameter("status");
			
			if (!StringUtil.isEmpty(productTypeId)) {
				if (productTypeId.equals("0")) {
					criteria.andProductTypeIdEqualTo(0);
				}else {
					criteria.andProductTypeIdEqualTo(Integer.valueOf(productTypeId));
				}
			}
			if(!StringUtil.isEmpty(name)){
				criteria.andNameLike("%"+name+"%");
			}
		
			if(!StringUtil.isEmpty(status)){
				criteria.andStatusEqualTo(status);
			}
			totalCount = brandteamTypeService.brandteamTypeCountByExample(brandteamTypeCustomExample);
			brandteamTypeCustomExample.setLimitStart(page.getLimitStart());
			brandteamTypeCustomExample.setLimitSize(page.getLimitSize());
			List<BrandteamTypeCustom> brandteamTypeCustoms = brandteamTypeService.brandteamTypeCustomselectByExample(brandteamTypeCustomExample);
			ResourceBundle resource = ResourceBundle.getBundle("base_config"); 
			String mUrl = resource.getString("mUrl");
			for(BrandteamTypeCustom brandteamTypeCustom:brandteamTypeCustoms){
				String previewUrl = mUrl+"/xgbuy/views/index.html?redirect_url=activity/templet/brand_decorate.html?infoId="+brandteamTypeCustom.getDecorateInfoId();
				brandteamTypeCustom.setPreviewUrl(previewUrl);
			}
			resMap.put("Rows", brandteamTypeCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//添加,编辑界面
	@RequestMapping(value = "/brandteamType/brandteamtypeEdit.shtml")
	public ModelAndView toEdit(HttpServletRequest request) {
		String rtPage = "brandgroupRenovationNew/brandteamTypeEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andTLevelEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypeList", productTypeList); //1级类目
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			BrandteamType brandteamType = brandteamTypeService.selectByPrimaryKey(Integer.valueOf(id));
			resMap.put("brandteamType", brandteamType);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存品牌团类目
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/brandteamType/save.shtml")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String productTypeId = request.getParameter("productTypeId");
			BrandteamTypeExample brandteamTypeExample = new BrandteamTypeExample();
			BrandteamTypeExample.Criteria braCriteria =brandteamTypeExample.createCriteria();
			braCriteria.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(productTypeId)){
				braCriteria.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}else{
				braCriteria.andProductTypeIdIsNull();
			}
			List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(brandteamTypeExample);
			BrandteamType brandteamType = new BrandteamType();
			if(!StringUtils.isEmpty(id)){
				if(brandteamTypes!=null && brandteamTypes.size()>0){
					if(!brandteamTypes.get(0).getId().toString().equals(id)){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "一级分类已存在，不能重复添加");
						return resMap;
					}
				}
				brandteamType = brandteamTypeService.selectByPrimaryKey(Integer.parseInt(id));
			}else{
				if(brandteamTypes!=null && brandteamTypes.size()>0){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "一级分类已存在，不能重复添加");
					return resMap;
				}
				brandteamType.setDelFlag("0");
				brandteamType.setCreateDate(new Date());
				brandteamType.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				brandteamType.setStatus("0");
			}
			brandteamType.setName(name);
			if(!StringUtils.isEmpty(productTypeId)){
				brandteamType.setProductTypeId(Integer.parseInt(productTypeId));
			}
			brandteamType.setUpdateDate(new Date());
			brandteamType.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(!StringUtils.isEmpty(id)){
				brandteamTypeService.update(brandteamType);
			}else{
				DecorateInfo di = new DecorateInfo();
				di.setDecorateName(name);
				di.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				di.setCreateDate(new Date());
				di.setDelFlag("0");
				
				DecorateArea da = new DecorateArea();
				da.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				da.setCreateDate(new Date());
				da.setDelFlag("0");
				brandteamTypeService.save(di,da,brandteamType);
			}
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//保存排序值
	@RequestMapping(value = "/brandteamType/saveSeqNo.shtml")
	@ResponseBody
	public Map<String, Object> saveSeqNo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			BrandteamType brandteamType = brandteamTypeService.selectByPrimaryKey(Integer.parseInt(id));
			if(!StringUtils.isEmpty(seqNo)){
				brandteamType.setSeqNo(Integer.parseInt(seqNo));
			}else{
				brandteamType.setSeqNo(null);
			}
			brandteamType.setUpdateDate(new Date());
			brandteamType.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			brandteamTypeService.updateByPrimaryKey(brandteamType);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 上下架
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/brandteamType/changeStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			BrandteamType brandteamType = brandteamTypeService.selectByPrimaryKey(Integer.parseInt(id));
			if(brandteamType.getStatus().equals("0")){
				brandteamType.setStatus("1");
			}else{
				brandteamType.setStatus("0");
			}
			brandteamType.setUpdateDate(new Date());
			brandteamType.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			brandteamTypeService.updateByPrimaryKeySelective(brandteamType);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//添加编辑品牌团类目广告
	@RequestMapping(value = "/brandteamType/toEditbrandteamTypeAdInfo.shtml")
	public ModelAndView toEditbrandteamTypeAdInfo(HttpServletRequest request) {
		String rtPage = "brandgroupRenovationNew/toEditbrandteamTypeAdInfo";
 		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");

		if(!StringUtils.isEmpty(id)){
		  BrandteamTypeadInfo brandteamTypeadInfo=brandteamTypeadInfoService.selectByPrimaryKey(Integer.valueOf(id));
		  
		  if("3".equals(brandteamTypeadInfo.getLinkType())){//为商品的时候,将id转换成code
			  Product product = productService.selectByPrimaryKey(Integer.parseInt(brandteamTypeadInfo.getLinkValue()));
			  if(product!=null){
				  brandteamTypeadInfo.setLinkValue(product.getCode());
			  }
		  }
		  
		  if("10".equals(brandteamTypeadInfo.getLinkType())){//为店铺的时候,将id转换成code
			   MchtInfo MchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(brandteamTypeadInfo.getLinkValue()));  
			  if(MchtInfo!=null){
				  brandteamTypeadInfo.setLinkValue(MchtInfo.getMchtCode());
			  }
		  }
		  
			//二级分类下的一级类目和二级类目
			if("13".equals(brandteamTypeadInfo.getLinkType())){
				ProductTypeExample productTypeExample = new ProductTypeExample();
				productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
				List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample );
				 resMap.put("productTypes", productTypes);
				String sonIds = brandteamTypeadInfo.getLinkValue();
				String[] split = sonIds.split(",");
				if(split.length>0&&split!=null){
				ProductType partenType = productTypeService.selectByPrimaryKey(Integer.parseInt(split[0]));
				  resMap.put("partenId", partenType.getParentId());
				  resMap.put("sonIds", sonIds);
				}
			}

		  resMap.put("brandteamTypeadInfo", brandteamTypeadInfo);
		}
		String brandteamTypeid = request.getParameter("brandteamTypeid");
		resMap.put("brandteamTypeid", brandteamTypeid);
		resMap.put("linkTypeList", DataDicUtil.getStatusList("BU_BRAND_TEAM_TYPR_AD_INFO", "LINK_TYPE"));
		
		//商城一级分类
		MallCategoryExample mallCategoryExample = new MallCategoryExample();
		mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MallCategory> mallCategorys = mallCategoryService.selectByExample(mallCategoryExample );
		resMap.put("mallCategorys", mallCategorys);
		
		//上架的品牌团名称
		BrandteamTypeExample brandteamTypeExample = new BrandteamTypeExample();
		brandteamTypeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(brandteamTypeExample );
		resMap.put("brandteamTypes", brandteamTypes);
		
		//九大类目
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample );
		resMap.put("productTypes", productTypes);
		return new ModelAndView(rtPage,resMap);
	}
	
	
	
	
	//保存品牌团类目广告
	@RequestMapping(value = "/brandteamType/savebrandteamTypeAdInfo.shtml")
	@ResponseBody
	public Map<String, Object> savebrandteamTypeAdInfo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			String brandteamTypeid = request.getParameter("brandteamTypeid");
			String pic = request.getParameter("pic");
			String linkType = request.getParameter("linkType");
			String linkValue = request.getParameter("linkValue");
			

			BrandteamTypeadInfo brandteamTypeadInfo = new BrandteamTypeadInfo();
			if(!StringUtils.isEmpty(id)){
				brandteamTypeadInfo = brandteamTypeadInfoService.selectByPrimaryKey(Integer.parseInt(id));
			}
			
			if ("13".equals(linkType)){
				linkValue=request.getParameter("values");
			}
			
			
				if ("1".equals(linkType)){
					ActivityAreaExample activityAreaExample =new ActivityAreaExample();
					ActivityAreaExample.Criteria activityAreaCriteria=activityAreaExample.createCriteria();
//					activityAreaCriteria.andDelFlagEqualTo("0").andSourceEqualTo("1").andStatusEqualTo("1").andIdEqualTo(Integer.parseInt(linkValue));
					activityAreaCriteria.andDelFlagEqualTo("0").andSourceEqualTo("1").andIdEqualTo(Integer.parseInt(linkValue));
					Integer totalCount=activityAreaService.countByExample(activityAreaExample);
					if (totalCount==0){
						resMap.put("returnCode", "4004");
//						resMap.put("returnMsg", "会场不存在或未启用！");
						resMap.put("returnMsg", "会场不存在");
						return resMap;
					}
				}
				if ("2".equals(linkType)){
					ActivityExample activityExample =new ActivityExample();
					ActivityExample.Criteria activityCriteria=activityExample.createCriteria();
//					activityCriteria.andDelFlagEqualTo("0").andStatusEqualTo("6").andIdEqualTo(Integer.parseInt(linkValue));
					activityCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(linkValue));
					Integer totalCount=activityService.countByExample(activityExample);
					if (totalCount==0){
						resMap.put("returnCode", "4004");
//						resMap.put("returnMsg", "活动不存在或未审核通过！");
						resMap.put("returnMsg", "活动不存在");
						return resMap;
					}
				}
				if ("3".equals(linkType)){
					ProductCustomExample productCustomExample = new ProductCustomExample();
//					productCustomExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andCodeEqualTo(linkValue);
					productCustomExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(linkValue);
					List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
					ProductCustom productCustom = productCustoms.get(0);
					if(productCustom==null || !"0".equals(productCustom.getDelFlag())){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "商品不存在！");
						return resMap;
					}/*else{
						String productActivityStatus=productCustom.getProductActivityStatus();
						if (!"2".equals(productActivityStatus) && !"3".equals(productActivityStatus) && !"4".equals(productActivityStatus)){
							resMap.put("returnCode", "4004");
							resMap.put("returnMsg", "请检查商品的报名活动状态！");
							return resMap;
						}
					}*/	
				}
				if ("7".equals(linkType)){//自建页面
					/*			ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(adInfo.getLinkId());*/
								CustomPage customPage = customPageService.selectByPrimaryKey(Integer.parseInt(linkValue));
								if(customPage==null || !"0".equals(customPage.getDelFlag())){
									resMap.put("returnCode", "4004");
									resMap.put("returnMsg", "自建页面不存在");
									return resMap;

								}
							}
				
				if ("29".equals(linkType)){
					CouponExample example = new CouponExample();
					CouponExample.Criteria c = example.createCriteria();
					c.andDelFlagEqualTo("0");
//					c.andStatusEqualTo("1");
					c.andIdEqualTo(Integer.parseInt(linkValue));
					List<Coupon> coupons = couponService.selectByExample(example);
					if(coupons==null || coupons.size() == 0){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "优惠券ID不可用");
						return resMap;
					}/*else{
						Coupon coupon = coupons.get(0);
						Date now = new Date();
						if(coupon.getExpiryType().equals("1")){//1.绝对时间
							if(now.after(coupon.getExpiryEndDate())){
								resMap.put("returnCode", "4004");
								resMap.put("returnMsg","优惠券已失效");
								return resMap;
							}
						}else if(coupon.getExpiryType().equals("2")){//2.相对时间
							Date last = DateUtil.getDateAfter(coupon.getRecEndDate(), coupon.getExpiryDays());
							if(now.after(last)){
								resMap.put("returnCode", "4004");
								resMap.put("returnMsg", "优惠券已失效");
								return resMap;
							}
						}
						
					}*/
				}
				if ("10".equals(linkType)){
					MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
//					mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtCodeEqualTo(linkValue);
					mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(linkValue);
					Integer totalCount = mchtInfoService.countByExample(mchtInfoExample );
					if (totalCount==0){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "商家店铺不存在！");
						return resMap;

					}
				}
				
				if(!StringUtils.isEmpty(brandteamTypeid)){
					brandteamTypeadInfo.setBrandTeamTypeId(Integer.parseInt(brandteamTypeid));
				}	
				brandteamTypeadInfo.setDelFlag("0");
				brandteamTypeadInfo.setCreateDate(new Date());
				brandteamTypeadInfo.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				brandteamTypeadInfo.setStatus("0");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("upDate")) ){
				brandteamTypeadInfo.setUpDate(dateFormat.parse(request.getParameter("upDate")+":00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("downDate")) ){
				brandteamTypeadInfo.setDownDate(dateFormat.parse(request.getParameter("downDate")+":59"));
			}
			brandteamTypeadInfo.setPic(pic);
			brandteamTypeadInfo.setLinkType(linkType);
			
			if ("3".equals(linkType)){//商品code转换为id存入
					ProductExample productExample = new ProductExample();
					productExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(linkValue);
					List<Product> products = productService.selectByExample(productExample);
					if(products.size()>0&&products!=null){
						Product product = products.get(0);
						brandteamTypeadInfo.setLinkValue(product.getId()+"");
					}
			}else if ("10".equals(linkType)){//将店铺code转成主键ID 存入
				MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
				mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(linkValue);
				 List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.selectByExample(mchtInfoExample);
				if(mchtInfoCustoms.size()>0&&mchtInfoCustoms!=null){
					MchtInfoCustom mchtInfoCustom = mchtInfoCustoms.get(0);
					brandteamTypeadInfo.setLinkValue(mchtInfoCustom.getId()+"");
				}
			}else{
				brandteamTypeadInfo.setLinkValue(linkValue);
			}
			
			
			
			
			brandteamTypeadInfo.setUpdateDate(new Date());
			brandteamTypeadInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(!StringUtils.isEmpty(id)){
				brandteamTypeadInfoService.updateByPrimaryKeySelective(brandteamTypeadInfo);
			}else{
				brandteamTypeadInfoService.insertSelective(brandteamTypeadInfo);
			}
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//管理广告
	@RequestMapping(value = "/brandteamTypeAdInfo/list.shtml")
	public ModelAndView brandteamTypeAdInfolist(HttpServletRequest request) {
		String rtPage = "brandgroupRenovationNew/brandteamTypeAdInfolist";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String brandteamTypeid = request.getParameter("brandteamTypeid");
		resMap.put("brandteamTypeid", brandteamTypeid);
		return new ModelAndView(rtPage,resMap);
	}
	
	//管理广告列表
	@RequestMapping(value = "/brandteamTypeAdInfo/data.shtml")
	@ResponseBody
	public Map<String, Object> brandteamTypeAdInfodata(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			BrandteamTypeadInfoCustomExample brandteamTypeadInfoCustomExample = new BrandteamTypeadInfoCustomExample();
			brandteamTypeadInfoCustomExample.setOrderByClause("IFNULL(bta.seq_no,999999999) asc");
			BrandteamTypeadInfoCustomExample.BrandteamTypeadInfoCustomCriteria brandteamTypeadInfoCustomCriteria = brandteamTypeadInfoCustomExample.createCriteria();
			brandteamTypeadInfoCustomCriteria.andDelFlagEqualTo("0");
			String brandteamTypeid = request.getParameter("brandteamTypeid");
			if(!StringUtil.isEmpty(brandteamTypeid)){
				brandteamTypeadInfoCustomCriteria.andBrandTeamTypeIdEqualTo(Integer.parseInt(brandteamTypeid));
			}
			totalCount = brandteamTypeadInfoService.brandteamTypeadInfoCountByExample(brandteamTypeadInfoCustomExample);
			brandteamTypeadInfoCustomExample.setLimitStart(page.getLimitStart());
			brandteamTypeadInfoCustomExample.setLimitSize(page.getLimitSize());
			List<BrandteamTypeadInfoCustom> brandteamTypeadInfoCustoms = brandteamTypeadInfoService.brandteamTypeadInfoCustomselectByExample(brandteamTypeadInfoCustomExample);
			resMap.put("Rows", brandteamTypeadInfoCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//保存管理广告排序值
	@RequestMapping(value = "/brandteamTypeAdInfo/savebrandteamTypeAdInfoSeqNo.shtml")
	@ResponseBody
	public Map<String, Object> savebrandteamTypeAdInfoSeqNo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			BrandteamTypeadInfo brandteamTypeadInfo = brandteamTypeadInfoService.selectByPrimaryKey(Integer.parseInt(id));
			if(!StringUtils.isEmpty(seqNo)){
				brandteamTypeadInfo.setSeqNo(Integer.parseInt(seqNo));
			}else {
				brandteamTypeadInfo.setSeqNo(null);
			}
			brandteamTypeadInfo.setUpdateDate(new Date());
			brandteamTypeadInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			brandteamTypeadInfoService.updateByPrimaryKey(brandteamTypeadInfo);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//删除管理广告
	@RequestMapping(value = "/brandteamTypeAdInfo/deleteAdInfo.shtml")
	@ResponseBody
	public Map<String, Object> deleteAdInfo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			BrandteamTypeadInfo brandteamTypeadInfo = new BrandteamTypeadInfo();
			brandteamTypeadInfo.setDelFlag("1");
			brandteamTypeadInfo.setUpdateDate(new Date());
			brandteamTypeadInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			BrandteamTypeadInfoExample brandteamTypeadInfoExample = new BrandteamTypeadInfoExample();
			brandteamTypeadInfoExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(id));
			brandteamTypeadInfoService.updateByExampleSelective(brandteamTypeadInfo, brandteamTypeadInfoExample);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 上下架
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/brandteamTypeAdInfo/changeAdInfoStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeAdInfoStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			BrandteamTypeadInfo brandteamTypeadInfo = brandteamTypeadInfoService.selectByPrimaryKey(Integer.parseInt(id));
			if(brandteamTypeadInfo.getStatus().equals("0")){
				brandteamTypeadInfo.setStatus("1");
			}else{
				brandteamTypeadInfo.setStatus("0");
			}
			brandteamTypeadInfo.setUpdateDate(new Date());
			brandteamTypeadInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			brandteamTypeadInfoService.updateByPrimaryKeySelective(brandteamTypeadInfo);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	/**
	 * 装修页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/brandteamTypeAdInfo/design.shtml")
	public ModelAndView toDesign(HttpServletRequest request) {
		String rtPage = "brandgroupRenovationNew/design";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		if(decorateAreas!=null && decorateAreas.size()>0){
			resMap.put("decorateAreaId", decorateAreas.get(0).getId());
			DecorateModuleExample dme = new DecorateModuleExample();
			dme.setOrderByClause("ifnull(t.seq_no,99999) asc,t.id desc");
			DecorateModuleExample.Criteria dmec = dme.createCriteria();
			dmec.andDelFlagEqualTo("0");
			dmec.andDecorateAreaIdEqualTo(decorateAreas.get(0).getId());
			List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
			resMap.put("decorateModuleCustoms", decorateModuleCustoms);
		}
		ResourceBundle resource = ResourceBundle.getBundle("base_config"); 
		String mUrl = resource.getString("mUrl");
		resMap.put("previewUrl", mUrl+"/xgbuy/views/index.html?redirect_url=activity/templet/brand_decorate.html?infoId="+decorateInfoId);
		resMap.put("decorateInfoId", decorateInfoId);
		return new ModelAndView(rtPage,resMap);
	}
	
	
	/**
	 * 添加/编辑图片模块
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/brandteamTypeAdInfo/editSourceNicheDecorateModule.shtml")
	public ModelAndView editDecorateModule(HttpServletRequest request) {
		String rtPage = "brandgroupRenovationNew/editSourceNicheDecorateModule";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		String decorateAreaId = request.getParameter("decorateAreaId");
		String moduleType = request.getParameter("moduleType");
		String seqNo = request.getParameter("seqNo");
		String decorateModuleId = request.getParameter("decorateModuleId");
		if(!StringUtils.isEmpty(decorateModuleId)){
			DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(decorateModuleId));
			resMap.put("decorateModule", decorateModule);
			if(decorateModule.getModuleType().equals("1") || decorateModule.getModuleType().equals("9")){//1.图片 9.滑动栏目
				ModuleMapExample mme = new ModuleMapExample();
				ModuleMapExample.Criteria criteria = mme.createCriteria();
				criteria.andDelFlagEqualTo("0");
				criteria.andDecorateModuleIdEqualTo(decorateModule.getId());
				List<ModuleMap> moduleMaps = moduleMapService.selectByExample(mme);
				JSONArray ja = new JSONArray();
				for(ModuleMap moduleMap:moduleMaps){
					JSONObject jo = new JSONObject();
					String[] coordsArray = moduleMap.getCoords().trim().split(",");
					jo.put("x1", coordsArray[0]);
					jo.put("y1", coordsArray[1]);
					jo.put("x2", coordsArray[2]);
					jo.put("y2", coordsArray[3]);
					jo.put("linkType", moduleMap.getLinkType());
					if(moduleMap.getLinkType().equals("3")){//商品
						Product product = productService.selectByPrimaryKey(moduleMap.getLinkValue());
						if(product!=null){
							jo.put("linkValue", product.getCode());
						}
					}else{//非商品
						if(moduleMap.getLinkType().equals("9") || moduleMap.getLinkType().equals("14")){//URL,关键字
							jo.put("linkValue", moduleMap.getLinkUrl());
						}else{
							jo.put("linkValue", moduleMap.getLinkValue());
						}
					}
					jo.put("width", Double.valueOf(coordsArray[2])-Double.valueOf(coordsArray[0]));
					jo.put("height", Double.valueOf(coordsArray[3])-Double.valueOf(coordsArray[1]));
					ja.add(jo);
				}
				resMap.put("moduleMapJSONArray", ja);
				
				ProductTypeExample pte = new ProductTypeExample();
				ProductTypeExample.Criteria ptec = pte.createCriteria();
				ptec.andDelFlagEqualTo("0");
				ptec.andTLevelEqualTo(1);
				ptec.andStatusEqualTo("1");
				List<ProductType> productTypes = productTypeService.selectByExample(pte);
				resMap.put("productTypes", productTypes);
			}
		}else{
			if(moduleType.equals("1")){
				ProductTypeExample pte = new ProductTypeExample();
				ProductTypeExample.Criteria ptec = pte.createCriteria();
				ptec.andDelFlagEqualTo("0");
				ptec.andTLevelEqualTo(1);
				ptec.andStatusEqualTo("1");
				List<ProductType> productTypes = productTypeService.selectByExample(pte);
				resMap.put("productTypes", productTypes);
			}
		}
		resMap.put("decorateInfoId", decorateInfoId);
		resMap.put("decorateAreaId", decorateAreaId);
		resMap.put("decorateModuleId", decorateModuleId);
		resMap.put("moduleType", moduleType);
		resMap.put("seqNo", seqNo);
		return new ModelAndView(rtPage,resMap);
	}
	
}
