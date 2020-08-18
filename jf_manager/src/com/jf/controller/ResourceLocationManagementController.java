package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.ActivityAreaCustom;
import com.jf.entity.ActivityAreaCustomExample;
import com.jf.entity.ActivityAreaCustomExample.ActivityAreaCustomCriteria;
import com.jf.entity.ActivityAreaExample;
import com.jf.entity.ActivityExample;
import com.jf.entity.BannerRecommendProduct;
import com.jf.entity.BannerRecommendProductCustom;
import com.jf.entity.BannerRecommendProductExample;
import com.jf.entity.BrandteamType;
import com.jf.entity.BrandteamTypeExample;
import com.jf.entity.ColumnPvStatisticalCustom;
import com.jf.entity.ColumnPvStatisticalCustomExample;
import com.jf.entity.ColumnPvStatisticalExample;
import com.jf.entity.Coupon;
import com.jf.entity.CouponCenterBottomNavigation;
import com.jf.entity.CouponCenterBottomNavigationCustom;
import com.jf.entity.CouponCenterBottomNavigationExample;
import com.jf.entity.CouponCenterTime;
import com.jf.entity.CouponCenterTimeExample;
import com.jf.entity.CouponCustom;
import com.jf.entity.CouponCustomExample;
import com.jf.entity.CouponExample;
import com.jf.entity.CustomPage;
import com.jf.entity.LotterySettings;
import com.jf.entity.LotterySettingsExample;
import com.jf.entity.MallCategory;
import com.jf.entity.MallCategoryExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.MemberLotteryCustom;
import com.jf.entity.MemberLotteryCustomExample;
import com.jf.entity.Product;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductCustomExample;
import com.jf.entity.ProductDataStatistics;
import com.jf.entity.ProductExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.ShopStory;
import com.jf.entity.ShopStoryDetail;
import com.jf.entity.ShopStoryDetailExample;
import com.jf.entity.ShopStoryExample;
import com.jf.entity.SourceNiche;
import com.jf.entity.SourceNicheCustom;
import com.jf.entity.SourceNicheCustomExample;
import com.jf.entity.SourceNicheCustomExample.SourceNicheCustomCriteria;
import com.jf.entity.SourceNicheExample;
import com.jf.entity.SourceNicheProduct;
import com.jf.entity.SourceNicheProductCustom;
import com.jf.entity.SourceNicheProductExample;
import com.jf.entity.SourceProductType;
import com.jf.entity.SourceProductTypeBanner;
import com.jf.entity.SourceProductTypeBannerCustom;
import com.jf.entity.SourceProductTypeBannerExample;
import com.jf.entity.SourceProductTypeBannerExample.Criteria;
import com.jf.entity.SourceProductTypeCustom;
import com.jf.entity.SourceProductTypeCustomExample;
import com.jf.entity.SourceProductTypeExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.TypeColumnPvStatisticalCustom;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityService;
import com.jf.service.AdInfoService;
import com.jf.service.BannerRecommendProductService;
import com.jf.service.BrandteamTypeService;
import com.jf.service.ColumnPvStatisticalService;
import com.jf.service.CommentService;
import com.jf.service.CouponCenterBottomNavigationService;
import com.jf.service.CouponCenterTimeService;
import com.jf.service.CouponService;
import com.jf.service.CustomPageService;
import com.jf.service.LotterySettingsService;
import com.jf.service.MallCategoryService;
import com.jf.service.MchtInfoService;
import com.jf.service.MemberLotteryService;
import com.jf.service.MemberPvService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;
import com.jf.service.ShopScoreService;
import com.jf.service.ShopStoryDetailService;
import com.jf.service.ShopStoryService;
import com.jf.service.SourceNicheProductService;
import com.jf.service.SourceNicheService;
import com.jf.service.SourceProductTypeBannerService;
import com.jf.service.SourceProductTypeService;
import com.jf.service.SysParamCfgService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.TypeColumnPvStatisticalService;
import com.jf.vo.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
public class ResourceLocationManagementController extends BaseController {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	public SourceNicheService sourceNicheService;
	
	@Resource
	public SourceProductTypeService sourceProductTypeService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private ShopScoreService shopScoreService;
	
	
	@Resource
	private SourceProductTypeBannerService sourceProductTypeBannerService;
	
	@Resource
	private BannerRecommendProductService bannerRecommendProductService;
	
	@Resource
	private AdInfoService adInfoService;
	
	@Resource
	private BrandteamTypeService brandteamTypeService;
	
	
	@Resource
	private ActivityAreaService activityAreaService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private CouponService couponService;
	
	@Resource
	private ProductBrandService productBrandService;
	
	@Resource
	private MallCategoryService mallCategoryService;
	
	@Resource
	private SourceNicheProductService sourceNicheProductService;
	
	@Resource
	private ShopStoryDetailService shopStoryDetailService;
	
	@Resource
	private ShopStoryService shopStoryService;
	
	@Resource
	private CouponCenterTimeService couponCenterTimeService;

	@Resource
	private CouponCenterBottomNavigationService couponCenterBottomNavigationService;

	@Resource
	private CustomPageService customPageService;

	@Resource
	private ColumnPvStatisticalService columnPvStatisticalService;

	@Resource
	private MemberPvService memberPvService;

	@Resource
	private SysStaffInfoService sysStaffInfoService;

	@Resource
	private TypeColumnPvStatisticalService typeColumnPvStatisticalService;

	@Resource
	private MemberLotteryService memberLotteryService;

	@Resource
	private LotterySettingsService lotterySettingsService;

	@Resource
	private SysParamCfgService sysParamCfgService;

    //有好货
    @RequestMapping("/resourceLocationManagement/goodsMerchandise.shtml")
    public ModelAndView goodsMerchandise(HttpServletRequest request) {
        String rtPage = "/resourceLocationManagement/goodsMerchandise";
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("goodsMerchandise", true);
        return new ModelAndView(rtPage, resMap);
    }


    //每日好店
    @RequestMapping("/resourceLocationManagement/goodsStore.shtml")
    public ModelAndView goodsStore(HttpServletRequest request) {
        String rtPage = "/resourceLocationManagement/goodsMerchandise";
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("goodsStore", true);
        return new ModelAndView(rtPage, resMap);


		}
		
		//拉新分润好货推荐
		@RequestMapping("/resourceLocationManagement/LaXinRunGoodQuality.shtml")
		public ModelAndView LaXinRunGoodQuality(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/goodsMerchandise";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("laXinRunGoodQuality", true);
			return new ModelAndView(rtPage,resMap);
		}
		
		//潮鞋上新
		@RequestMapping("/resourceLocationManagement/upTideShoes.shtml")
		public ModelAndView upTideShoes(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/goodsMerchandise";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("upTideShoes", true);
			return new ModelAndView(rtPage,resMap);
		}
		
		
		//潮流男装
		@RequestMapping("/resourceLocationManagement/fashionMenswear.shtml")
		public ModelAndView fashionMenswear(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/goodsMerchandise";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("fashionMenswear", true);
			return new ModelAndView(rtPage,resMap);
		}
		
		//断码特惠
		@RequestMapping("/resourceLocationManagement/breakingPreference.shtml")
		public ModelAndView breakingPreference(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/goodsMerchandise";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("breakingPreference", true);
			return new ModelAndView(rtPage,resMap);
		}
		
		//运动鞋服
		@RequestMapping("/resourceLocationManagement/sportswear.shtml")
		public ModelAndView sportswear(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/goodsMerchandise";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("sportswear", true);
			return new ModelAndView(rtPage,resMap);
		}
		
		//潮流美妆
		@RequestMapping("/resourceLocationManagement/fashionCosmetics.shtml")
		public ModelAndView FashionCosfashionCosmeticsmetics(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/goodsMerchandise";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("fashionCosmetics", true);
			return new ModelAndView(rtPage,resMap);
		}
		
		//食品超市
		@RequestMapping("/resourceLocationManagement/foodSupermarket.shtml")
		public ModelAndView foodSupermarket(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/goodsMerchandise";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("foodSupermarket", true);
			return new ModelAndView(rtPage,resMap);
		}
		
		//大学生创业
		@RequestMapping("/resourceLocationManagement/studentEntrepreneurship.shtml")
		public ModelAndView studentEntrepreneurship(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/goodsMerchandise";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("studentEntrepreneurship", true);
			return new ModelAndView(rtPage,resMap);
		}
		//领劵中心
		@RequestMapping("/resourceLocationManagement/bringStockCenter.shtml")
		public ModelAndView bringStockCenter(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/goodsMerchandise";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("bringStockCenter", true);
			return new ModelAndView(rtPage,resMap);
		}
		//爆款推荐,Recommended models
		@RequestMapping("/resourceLocationManagement/recommendedModels.shtml")
		public ModelAndView recommendedModels(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/goodsMerchandise";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("recommendedModels", true);
			return new ModelAndView(rtPage,resMap);
		}
		//积分抽奖
		@RequestMapping("/resourceLocationManagement/integralLottery.shtml")
		public ModelAndView integralLottery(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/goodsMerchandise";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("integralLottery", true);
			return new ModelAndView(rtPage,resMap);
		}

//==============	
		
	//Banner页面
	@RequestMapping("/resourceLocationManagement/bannerList.shtml")
	public ModelAndView bannerList(HttpServletRequest request, Integer activitybrandgroupid) {
		String rtPage= "/resourceLocationManagement/bannerList";	
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		String sourceProductTypeId = request.getParameter("sourceProductTypeId");
		String pagetype = request.getParameter("pagetype");
		resMap.put("sourceProductTypeId", sourceProductTypeId);
		resMap.put("pagetype", pagetype);
		return new ModelAndView(rtPage,resMap);
	}	
	
	
	//banner 图
	@RequestMapping(value="/resourceLocationManagement/getBannerPic.shtml")
	@ResponseBody
	public List<SourceProductTypeBanner> getPoductPic(HttpServletRequest request){
		List<SourceProductTypeBanner> bannerPics=new ArrayList<SourceProductTypeBanner>();
		if(StringUtil.isEmpty(request.getParameter("bannerId"))){
			return bannerPics;
		}
		SourceProductTypeBannerExample example = new SourceProductTypeBannerExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(request.getParameter("bannerId")));
		bannerPics = sourceProductTypeBannerService.selectByExample(example );
		return bannerPics;
	}
		
		
	//Banner页面的数据显示
	@RequestMapping("/resourceLocationManagement/bannerListDate.shtml")
	@ResponseBody
	public 	Map<String, Object> bannerListDate(HttpServletRequest request , Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<SourceProductTypeBannerCustom> SourceProductTypeBannerCustoms  =null;
		Integer totalCount = 0;
		try {		
			SourceProductTypeBannerExample sourceProductTypeBannerExample = new SourceProductTypeBannerExample();
			Criteria sourceProductTypeBannerCriteria = sourceProductTypeBannerExample.createCriteria();
			sourceProductTypeBannerCriteria.andDelFlagEqualTo("0");
			sourceProductTypeBannerExample.setOrderByClause("up_date desc , id desc");
			
			if(!StringUtil.isEmpty(request.getParameter("sourceProductTypeId"))){//类目
				sourceProductTypeBannerCriteria.andSourceProductTypeIdEqualTo(Integer.parseInt(request.getParameter("sourceProductTypeId")));
			}

			if(!StringUtil.isEmpty(request.getParameter("linkType"))){
				sourceProductTypeBannerCriteria.andLinkTypeEqualTo(request.getParameter("linkType"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("sign_begin"))){//上线时间
			
			/*	sourceProductTypeBannerCriteria.andUpDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("sign_begin")+"00:00:00"));*/
				sourceProductTypeBannerCriteria.andUpDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("sign_begin")+":00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("sign_end"))){
				
			/*	sourceProductTypeBannerCriteria.andUpDateLessThanOrEqualTo(sdf.parse(request.getParameter("sign_end")+"23:59:59"));*/
				sourceProductTypeBannerCriteria.andUpDateLessThanOrEqualTo(sdf.parse(request.getParameter("sign_end")+":00"));
			}

			sourceProductTypeBannerExample.setLimitStart(page.getLimitStart());
			sourceProductTypeBannerExample.setLimitSize(page.getLimitSize());
			totalCount	= sourceProductTypeBannerService.countByCustomExample(sourceProductTypeBannerExample);
			SourceProductTypeBannerCustoms = sourceProductTypeBannerService.selectByCustomExample(sourceProductTypeBannerExample );
			
			List<Integer> idsList = new ArrayList<Integer>();
			for(SourceProductTypeBannerCustom spbc : SourceProductTypeBannerCustoms){
				if(spbc.getIds()!=null && !StringUtil.isEmpty(spbc.getIds())){
					String[] split = spbc.getIds().split(",");
				for (int i = 0; i < split.length; i++) {
						idsList.add(Integer.parseInt(split[i]));
					}
				}	
			}
			
			if(idsList!=null&&idsList.size()>0){
				
				List<BannerRecommendProductCustom> BannerRecommendProductCustoms = bannerRecommendProductService.selectByIds(idsList);
				
				for(SourceProductTypeBannerCustom spbc : SourceProductTypeBannerCustoms){
					Integer id = spbc.getId();
					List<BannerRecommendProductCustom> templateList = new ArrayList<BannerRecommendProductCustom>();
					for(BannerRecommendProductCustom brp : BannerRecommendProductCustoms){
						Integer bannerId = brp.getBannerId();
						if(id==bannerId){
							templateList.add(brp);
						}
					}
					spbc.setBannerList(templateList);
					
				}
				
			}
		
	

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		resMap.put("Rows", SourceProductTypeBannerCustoms);
		resMap.put("Total", totalCount);
		return resMap;
		
	}
		
	
	/**
	 * 添加banner
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/resourceLocationManagement/bannerAdd.shtml")
	public ModelAndView bannerEdit(HttpServletRequest request) {
		String rtPage = "/resourceLocationManagement/bannerAdd";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		String sourceProductTypeId = request.getParameter("sourceProductTypeId");	
		String pagetype = request.getParameter("pagetype");
		resMap.put("sourceProductTypeId", sourceProductTypeId);
		resMap.put("pagetype", pagetype);
		
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
		ProductTypeExample example = new ProductTypeExample();
		example.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(example );
		resMap.put("productTypes", productTypes);

		return new ModelAndView(rtPage,resMap);

	}
	
	
	/**
	 * 修改banner
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/resourceLocationManagement/bannerEdit.shtml")
	public ModelAndView edit(HttpServletRequest request) {
		String rtPage = "/resourceLocationManagement/bannerEdit";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();

		String sourceProductTypeId = request.getParameter("sourceProductTypeId");
		resMap.put("sourceProductTypeId", sourceProductTypeId);
		
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
		ProductTypeExample example = new ProductTypeExample();
		example.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(example );
		resMap.put("productTypes", productTypes);
		
		String id = request.getParameter("id");
		if(!StringUtil.isEmpty(id)){
			SourceProductTypeBanner sourceProductTypeBanner = sourceProductTypeBannerService.selectByPrimaryKey(Integer.parseInt(id));
			
			if("3".equals(sourceProductTypeBanner.getLinkType())){
				Product product = productService.selectByPrimaryKey(Integer.parseInt(sourceProductTypeBanner.getLinkValue()));
				sourceProductTypeBanner.setLinkValue(product.getCode());
			}
			
			if("10".equals(sourceProductTypeBanner.getLinkType())){
				MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(sourceProductTypeBanner.getLinkValue()));
				sourceProductTypeBanner.setLinkValue(mchtInfo.getMchtCode());
			}
			
			resMap.put("sourceProductTypeBanner", sourceProductTypeBanner);
			
			//二级分类下的一级类目和二级类目
			if("14".equals(sourceProductTypeBanner.getLinkType())){
				String sonIds = sourceProductTypeBanner.getLinkValue();
				String[] split = sonIds.split(",");
				if(split.length>0&&split!=null){
				ProductType partenType = productTypeService.selectByPrimaryKey(Integer.parseInt(split[0]));
				resMap.put("partenId", partenType.getParentId());
				resMap.put("sonIds", sonIds);
				}
			}
			
		}
		return new ModelAndView(rtPage,resMap);

	}

	/**
	 * banner保存前的检查
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	public  Map<String, String> bannerSubmitCheck( String linkType , String linkValue) {
		String rtPage = "/success/success";
		Map<String, String> resMap = new HashMap<String, String>();
		String code = null;
		String msg =null;
		if ("1".equals(linkType)){
			ActivityAreaExample activityAreaExample =new ActivityAreaExample();
			ActivityAreaExample.Criteria activityAreaCriteria=activityAreaExample.createCriteria();
			/*activityAreaCriteria.andDelFlagEqualTo("0").andSourceEqualTo("1").andStatusEqualTo("1").andIdEqualTo(Integer.parseInt(linkValue));*/
			activityAreaCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(linkValue));
			Integer totalCount=activityAreaService.countByExample(activityAreaExample);
			if (totalCount==0){
				resMap.put("YN", "0");
				resMap.put("msg", "会场不存在或未启用！");
				return resMap;
			}
		}
		if ("2".equals(linkType)){
			ActivityExample activityExample =new ActivityExample();
			ActivityExample.Criteria activityCriteria=activityExample.createCriteria();
			/*activityCriteria.andDelFlagEqualTo("0").andStatusEqualTo("6").andIdEqualTo(Integer.parseInt(linkValue));*/
			activityCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(linkValue));
			Integer totalCount=activityService.countByExample(activityExample);
			if (totalCount==0){
				resMap.put("YN", "0");
				resMap.put("msg", "活动不存在或未审核通过！");
				return resMap;
			}
		}
		if ("3".equals(linkType)){
			ProductCustomExample productCustomExample = new ProductCustomExample();
			productCustomExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(linkValue);
			List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
			if(productCustoms.size()<=0||productCustoms==null){
				resMap.put("YN", "0");
				resMap.put("msg", "商品不存在,或已下架");
				return resMap;
			}
		/*	ProductCustom productCustom = productCustoms.get(0);
			if(productCustom==null || !"0".equals(productCustom.getDelFlag())){
			

			}*/
	/*		ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(Integer.parseInt(linkValue));
			if(productCustom==null || !"0".equals(productCustom.getDelFlag())){
				resMap.put("YN", "0");
				resMap.put("msg", "商品不存在！");
				return resMap;

			}else{
				String productActivityStatus=productCustom.getProductActivityStatus();
				if (!"2".equals(productActivityStatus) && !"3".equals(productActivityStatus) && !"4".equals(productActivityStatus)){
					resMap.put("YN", "0");
					resMap.put("msg", "请检查商品的报名活动状态！");
					return resMap;
				}
			}*/
		}
		if ("29".equals(linkType)){
			CouponExample example = new CouponExample();
			CouponExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
		/*	c.andStatusEqualTo("1");*/
			c.andIdEqualTo(Integer.parseInt(linkValue));
			List<Coupon> coupons = couponService.selectByExample(example);
			if(coupons==null || coupons.size() == 0){
				resMap.put("YN", "0");
				resMap.put("msg", "优惠券ID不可用");
				return resMap;
			}/*else{
				Coupon coupon = coupons.get(0);
				Date now = new Date();
				if(coupon.getExpiryType().equals("1")){//1.绝对时间
					if(now.after(coupon.getExpiryEndDate())){
						resMap.put("YN", "0");
						resMap.put("msg", "优惠券已失效");
						return resMap;
					}
				}else if(coupon.getExpiryType().equals("2")){//2.相对时间
					Date last = DateUtil.getDateAfter(coupon.getRecEndDate(), coupon.getExpiryDays());
					if(now.after(last)){
						resMap.put("YN", "0");
						resMap.put("msg", "优惠券已失效");
						return resMap;
					}
				}
				
			}*/
		}
		if ("10".equals(linkType)){
			MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
			/*mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtCodeEqualTo(linkValue);*/
			mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(linkValue);
			Integer totalCount = mchtInfoService.countByExample(mchtInfoExample );
			if (totalCount==0){
				resMap.put("YN", "0");
				resMap.put("msg", "商家店铺不存在！");
				return resMap;

			}
		}
		if ("13".equals(linkType)){
			String[] split = linkValue.split(",");
			int index;
			for (int i = 0; i < split.length; i++) {
				ProductBrandExample productBrandExample = new ProductBrandExample();
				/*productBrandExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(split[i])).andStatusEqualTo("1");*/
				productBrandExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(split[i]));
				List<ProductBrand> productBrands = productBrandService.selectByExample(productBrandExample );
				if(productBrands==null || productBrands.size()<=0){
					resMap.put("YN", "0");
					resMap.put("msg", "品牌ID"+split[i]+"不存在！");
					return resMap;
				}
			}
		}
		
		resMap.put("YN", "1");
		return resMap;
	}
	
	/**
	 * 保存banner
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/resourceLocationManagement/bannerSave.shtml")
	@ResponseBody
	public Map<String, Object> bannerSave(HttpServletRequest request, SourceProductTypeBanner sourceProductTypeBanner) throws ParseException {
		/*String rtPage = "/success/success";*/
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		String code = null;
		String msg =null;
		String linkType = sourceProductTypeBanner.getLinkType();
		String linkValue = sourceProductTypeBanner.getLinkValue();	
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String upDate = request.getParameter("upDate");
		Date date = sdf.parse(upDate);
		sourceProductTypeBanner.setUpDate(date);
		
		if("5".equals(linkType)){
			sourceProductTypeBanner.setLinkValue("0");
		}
		
		if("3".equals(linkType)){
			/*String pcode = adInfo.getLinkUrl();*/
			ProductExample productExample = new ProductExample();
			productExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(linkValue);
			List<Product> products = productService.selectByExample(productExample);
			if(products.size()>0&&products!=null){
				Product product = products.get(0);
				sourceProductTypeBanner.setLinkValue(product.getId()+"");
			}
		}
		
		if ("10".equals(linkType)){//将店铺code转成主键ID 存入
			/*String mcode = adInfo.getLinkUrl();*/
			MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
			mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(linkValue);
			 List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.selectByExample(mchtInfoExample);
			if(mchtInfoCustoms.size()>0&&mchtInfoCustoms!=null){
				MchtInfoCustom mchtInfoCustom = mchtInfoCustoms.get(0);
				sourceProductTypeBanner.setLinkValue(mchtInfoCustom.getId()+"");
			}
		}
		
		
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			
		if (StringUtil.isEmpty(request.getParameter("id"))) {
			//保存
			Map<String, String> bannerSubmitCheck = bannerSubmitCheck(linkType,linkValue);
			if("0".equals(bannerSubmitCheck.get("YN"))){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", bannerSubmitCheck.get("msg"));
			
				return resMap ;
			}else{
				sourceProductTypeBanner.setCreateBy(staffId);
				sourceProductTypeBanner.setCreateDate(new Date());
				sourceProductTypeBanner.setDelFlag("0");
				sourceProductTypeBanner.setStatus("0");
				sourceProductTypeBannerService.insertSelective(sourceProductTypeBanner);
				}
			
			}else{	
				//更新
				Map<String, String> bannerSubmitCheck = bannerSubmitCheck(linkType,linkValue);
				if("0".equals(bannerSubmitCheck.get("YN"))){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", bannerSubmitCheck.get("msg"));
				
					return resMap ;
				/*	return new ModelAndView(rtPage, resMap);*/ 
				}else{
					sourceProductTypeBanner.setCreateBy(staffId);
					sourceProductTypeBanner.setCreateDate(new Date());
					sourceProductTypeBannerService.updateByPrimaryKeySelective(sourceProductTypeBanner);
				}

			}

		} catch (Exception e) {			
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}

		return resMap ;
		/*return new ModelAndView(rtPage, resMap);*/
	}
	
	//变更banner类目上下线
	@RequestMapping(value = "/resourceLocationManagement/changeBannerStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeBannerStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			SourceProductTypeBanner sourceProductTypeBanner = sourceProductTypeBannerService.selectByPrimaryKey(Integer.parseInt(id));
			if(sourceProductTypeBanner.getStatus().equals("0")){
				sourceProductTypeBanner.setStatus("1");
			}else{
				sourceProductTypeBanner.setStatus("0");
			}
			sourceProductTypeBanner.setUpdateDate(new Date());
			sourceProductTypeBanner.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			sourceProductTypeBannerService.updateByPrimaryKeySelective(sourceProductTypeBanner);
		
			
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	
	//删除banner类目
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/deleteBannerCategory.shtml")
	public Map<String, Object> deleteBannerCategory(HttpServletRequest request, String id) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		String code = "200";
		String msg = "操作成功！";
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			if(!StringUtil.isEmpty(id)) {
				SourceProductTypeBanner sourceProductTypeBanner = sourceProductTypeBannerService.selectByPrimaryKey(Integer.parseInt(id));
				sourceProductTypeBanner.setUpdateBy(staffID);
				sourceProductTypeBanner.setUpdateDate(new Date());
				sourceProductTypeBanner.setDelFlag("1");
				sourceProductTypeBannerService.updateByPrimaryKeySelective(sourceProductTypeBanner);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "操作失败！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	//商品管理
	@RequestMapping("/resourceLocationManagement/commodityManagement.shtml")
	public ModelAndView CommodityManagement(HttpServletRequest request) {
		String rtPage = "resourceLocationManagement/commodityManagement";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		String bannerId = request.getParameter("bannerId");
		resMap.put("bannerId", bannerId);
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes",productTypes);//一级分类
		return new ModelAndView(rtPage,resMap);

	}
	
	//商品管理数据
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/commodityManagementDate.shtml")
	public Map<String, Object> commodityManagementDate(HttpServletRequest request, Page page) {
		String bannerId = request.getParameter("bannerId");
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SourceNicheCustom> dataList = null;
		Integer totalCount = 0;
		try {
		SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
		SourceNicheCustomExample.SourceNicheCustomCriteria sourceNicheCustomCriteria=sourceNicheCustomExample.createCriteria();
		sourceNicheCustomCriteria.andDelFlagEqualTo("0").andTypeEqualTo("1").andAuditStatusEqualTo("1").andStatusEqualTo("0");
		sourceNicheCustomCriteria.andpStatusEqualTo("1");//商品处于上线状态
		sourceNicheCustomCriteria.andStatusEqualTo("0");//活动状态为添加

		String productBrandName = request.getParameter("productBrandName");
		String name = request.getParameter("name");
		String bannerStatus = request.getParameter("bannerStatus");
		String productType = request.getParameter("productTypeId");

		if(!StringUtil.isEmpty(productBrandName)){//品牌
			sourceNicheCustomCriteria.andproductbrandNameLike("%"+productBrandName+"%");
		}

		if(!StringUtil.isEmpty(bannerStatus)){//banner上下线状态
			if("1".equals(bannerStatus)&&!StringUtil.isEmpty(bannerId)){
				sourceNicheCustomCriteria.andBannerStatusEuqalTo(Integer.parseInt(bannerId));
			}
			if("0".equals(bannerStatus)&&!StringUtil.isEmpty(bannerId)){
				sourceNicheCustomCriteria.andBannerStatusNotIn(Integer.parseInt(bannerId));
			}
		}

		if(!StringUtil.isEmpty(name)){//商品名称
			sourceNicheCustomCriteria.andNameLike("%"+name+"%");
		}
		if(!StringUtil.isEmpty(productType)){//商品一级分类
			sourceNicheCustomCriteria.andProductTypeEuqalTo(productType);
		}
		if(!StringUtil.isEmpty(request.getParameter("whichProduct"))){
			String goodIds = "";
			if(!StringUtil.isEmpty(request.getParameter("goodIds"))){
				goodIds = request.getParameter("goodIds");
			}else if(!StringUtil.isEmpty(request.getParameter("artnos"))){
				goodIds = request.getParameter("artnos");
			}
			if(!StringUtil.isEmpty(goodIds)){
				String[] split = goodIds.split("\n");
				String aaaString = "";
				for (int i = 0; i < split.length; i++) {
					if(aaaString==""){
						aaaString+=split[i];
					}else{
						aaaString+=","+split[i];
					}
				}
				if("1".equals(request.getParameter("whichProduct"))){//商品id
					sourceNicheCustomCriteria.andCodesIn(aaaString);
				}else if("2".equals(request.getParameter("whichProduct"))){//货物id
					sourceNicheCustomCriteria.andArtNosIn(aaaString);
				}
			}

		}

		sourceNicheCustomExample.setLimitStart(page.getLimitStart());
		sourceNicheCustomExample.setLimitSize(page.getLimitSize());
		totalCount=sourceNicheService.sourceNichecountByExample(sourceNicheCustomExample);
		dataList=sourceNicheService.sourceNicheCustomselectByExample(sourceNicheCustomExample);

	/*	List<SourceNicheCustom> sourceNicheCustomList = new ArrayList<SourceNicheCustom>();*/
		//查找对应的source 在对应的bannerId中的上下线状态

		BannerRecommendProductExample bannerRecommendProductExample = new BannerRecommendProductExample();
		com.jf.entity.BannerRecommendProductExample.Criteria brpCriteria = bannerRecommendProductExample.createCriteria();
		brpCriteria.andDelFlagEqualTo("0");

		if(!StringUtil.isEmpty(bannerId)){
			brpCriteria.andBannerIdEqualTo(Integer.parseInt(bannerId));
		}
		List<BannerRecommendProduct> bannerRecommendProductList = bannerRecommendProductService.selectByExample(bannerRecommendProductExample);
		for(SourceNicheCustom sc :dataList){
			for(BannerRecommendProduct brp :bannerRecommendProductList){
				if(sc.getId().equals(brp.getSourceNicheId())){
					sc.setBannerStatus(brp.getStatus());
				}
			}
		}



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	//变更banner轮播图的上下线
	@RequestMapping(value = "/resourceLocationManagement/upAndDownLine.shtml")
	@ResponseBody
	public Map<String, Object> upAndDownLine(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String bannerId = request.getParameter("bannerId");//bannerId
			String id = request.getParameter("id");//资源位ID
			BannerRecommendProductExample bannerRecommendProductExample = new BannerRecommendProductExample();
			bannerRecommendProductExample.createCriteria().andDelFlagEqualTo("0").andSourceNicheIdEqualTo(Integer.parseInt(id)).andBannerIdEqualTo(Integer.parseInt(bannerId));
			List<BannerRecommendProduct> BannerRecommendProducts = bannerRecommendProductService.selectByExample(bannerRecommendProductExample);

			if(BannerRecommendProducts!=null &&BannerRecommendProducts.size()>0){//有
				BannerRecommendProduct bannerRecommendProduct = BannerRecommendProducts.get(0);
				if(bannerRecommendProduct.getStatus().equals("0")){
					BannerRecommendProductExample bannerExample = new BannerRecommendProductExample();
					bannerExample.createCriteria().andBannerIdEqualTo(Integer.parseInt(bannerId)).andStatusEqualTo("1").andDelFlagEqualTo("0");
					int count = bannerRecommendProductService.countByExample(bannerExample);
					if(count>11){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "保存失败，上线产品数量已超过12个");
						return resMap;
					}

					bannerRecommendProduct.setStatus("1");
				}else{
					bannerRecommendProduct.setStatus("0");
				}
				bannerRecommendProduct.setUpdateDate(new Date());
				bannerRecommendProduct.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				bannerRecommendProductService.updateByPrimaryKeySelective(bannerRecommendProduct);
			}else{//没有

				BannerRecommendProductExample bannerExample = new BannerRecommendProductExample();
				bannerExample.createCriteria().andBannerIdEqualTo(Integer.parseInt(bannerId)).andStatusEqualTo("1").andDelFlagEqualTo("0");
				int count = bannerRecommendProductService.countByExample(bannerExample);
				if(count>11){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "保存失败，上线产品数量已超过12个");
					return resMap;
				}

				BannerRecommendProduct bannerRecommendProduct = new BannerRecommendProduct();
				bannerRecommendProduct.setStatus("1");
				bannerRecommendProduct.setBannerId(Integer.parseInt(bannerId));
				bannerRecommendProduct.setSourceNicheId(Integer.parseInt(id));
				bannerRecommendProduct.setCreateDate(new Date());
				bannerRecommendProduct.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				bannerRecommendProductService.insertSelective(bannerRecommendProduct);
			}


		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}




	//====================

	//商品审核
	@RequestMapping("/resourceLocationManagement/commodityAudit.shtml")
	public ModelAndView commodityAudit(HttpServletRequest request) {
		String rtPage = "/resourceLocationManagement/commodityAudit";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
		suCustomExample.setOrderByClause("id desc");
		SourceProductTypeExample.Criteria createCriteria = suCustomExample.createCriteria();
		createCriteria.andDelFlagEqualTo("0");
		String pagetype = request.getParameter("pagetype");
		if(!StringUtil.isEmpty(pagetype)&&"1009".equals(pagetype)){
			createCriteria.andTypeEqualTo("1");//有好货
		}else if(!StringUtil.isEmpty(pagetype)&&"1010".equals(pagetype)){
			createCriteria.andTypeEqualTo("4");//潮鞋上新
		}else if(!StringUtil.isEmpty(pagetype)&&"1020".equals(pagetype)){
			createCriteria.andTypeEqualTo("5");//潮流男装
		}else if(!StringUtil.isEmpty(pagetype)&&"1030".equals(pagetype)){
			createCriteria.andTypeEqualTo("6");//断码特惠
		}else if(!StringUtil.isEmpty(pagetype)&&"1040".equals(pagetype)){
			createCriteria.andTypeEqualTo("7");//运动鞋服
		}else if(!StringUtil.isEmpty(pagetype)&&"1050".equals(pagetype)){
			createCriteria.andTypeEqualTo("8");//潮流美妆
		}else if(!StringUtil.isEmpty(pagetype)&&"1060".equals(pagetype)){
			createCriteria.andTypeEqualTo("9");//食品超市
		}
		if(!StringUtil.isEmpty(pagetype)){
			resMap.put("pagetype",pagetype);
		}

		List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
		resMap.put("sourceProductTypes",sourceProductTypes);

		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes",productTypes);

		return new ModelAndView(rtPage,resMap);
	}


	//商品审核数据列表
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/commodityAuditData.shtml")
	public Map<String, Object> commodityAuditData(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<SourceNicheCustom> dataList = null;
		Integer totalCount = 0;
		try {
		SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
		SourceNicheCustomExample.SourceNicheCustomCriteria sourceNicheCustomCriteria=sourceNicheCustomExample.createCriteria();
		sourceNicheCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("0");
		sourceNicheCustomCriteria.andpStatusEqualTo("1");//商品处于上线状态
		sourceNicheCustomExample.setOrderByClause("id desc");

		String pagetype = request.getParameter("pagetype");
		if(!StringUtil.isEmpty(pagetype)&&"1009".equals(pagetype)){
			sourceNicheCustomCriteria.andTypeEqualTo("1");//有好货
		}else if(!StringUtil.isEmpty(pagetype)&&"1010".equals(pagetype)){
			sourceNicheCustomCriteria.andTypeEqualTo("4");//潮鞋上新
		}else if(!StringUtil.isEmpty(pagetype)&&"1020".equals(pagetype)){
			sourceNicheCustomCriteria.andTypeEqualTo("5");//潮流男装
		}else if(!StringUtil.isEmpty(pagetype)&&"1030".equals(pagetype)){
			sourceNicheCustomCriteria.andTypeEqualTo("6");//断码特惠
		if(!StringUtil.isEmpty(request.getParameter("twoType"))){//二级分类
			sourceNicheCustomCriteria.andProductTypeTwoLike("%"+request.getParameter("twoType")+"%");
		}
		}else if(!StringUtil.isEmpty(pagetype)&&"1040".equals(pagetype)){
			sourceNicheCustomCriteria.andTypeEqualTo("7");//运动鞋服
		}else if(!StringUtil.isEmpty(pagetype)&&"1050".equals(pagetype)){
			sourceNicheCustomCriteria.andTypeEqualTo("8");//潮流美妆
		}else if(!StringUtil.isEmpty(pagetype)&&"1060".equals(pagetype)){
			sourceNicheCustomCriteria.andTypeEqualTo("9");//食品超市
		}else if(!StringUtil.isEmpty(pagetype)&&"10000".equals(pagetype)){
			sourceNicheCustomCriteria.andTypeEqualTo("13");//积分转盘
		}

		if(!StringUtil.isEmpty(request.getParameter("name"))){//商品名称
			sourceNicheCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
		}
		if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){//品牌
	/*		sourceNicheCustomCriteria.andProductBrandNameEqualTo(request.getParameter("productBrandName"));*/
			sourceNicheCustomCriteria.andproductbrandNameLike("%"+request.getParameter("productBrandName")+"%");
		}
		if(!StringUtil.isEmpty(request.getParameter("sign_begin"))){//上线时间
			sourceNicheCustomCriteria.andUpDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("sign_begin")+" 00:00:00"));
		}
		if(!StringUtil.isEmpty(request.getParameter("sign_end"))){
			sourceNicheCustomCriteria.andUpDateLessThanOrEqualTo(sdf.parse(request.getParameter("sign_end")+" 23:59:59"));
		}
		if(!StringUtil.isEmpty(request.getParameter("create_begin"))){//报名时间
			sourceNicheCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("create_begin")+" 00:00:00"));
		}
		if(!StringUtil.isEmpty(request.getParameter("create_end"))){
			sourceNicheCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("create_end")+" 23:59:59"));
		}
		if(!StringUtil.isEmpty(request.getParameter("whichProduct"))){
			String whichProduct = request.getParameter("whichProduct");
			String goodIds = "";
			if("1".equals(whichProduct)){
				goodIds = request.getParameter("goodIds");
			}else if("2".equals(whichProduct)){
				goodIds = request.getParameter("artnos");
			}
			if(!StringUtil.isEmpty(goodIds)){
				String[] split = goodIds.split("\n");
				String aaaString = "";
				if("1".equals(whichProduct)){//商品id
					for (int i = 0; i < split.length; i++) {
						if(aaaString==""){
							aaaString+=split[i];
						}else{
							aaaString+=","+split[i];
						}
					}
					sourceNicheCustomCriteria.andCodesIn(aaaString);
				}else if("2".equals(whichProduct)){//货物id
					for (int i = 0; i < split.length; i++) {
						if(aaaString==""){
							aaaString+=split[i];
						}else{
							aaaString+="','"+split[i];
						}
					}
					sourceNicheCustomCriteria.andArtNosIn(aaaString);
				}
			}

		}
		if(!StringUtil.isEmpty(request.getParameter("productTypeId"))){//品类
			String productTypeId = request.getParameter("productTypeId");
			sourceNicheCustomCriteria.andsourceProductTypeIdEqualTo(Integer.parseInt(productTypeId));
		}


		if(!StringUtil.isEmpty(request.getParameter("auditStatus"))){//审核状态
			sourceNicheCustomCriteria.andAuditStatusEqualTo(request.getParameter("auditStatus"));
		}


		sourceNicheCustomExample.setLimitStart(page.getLimitStart());
		sourceNicheCustomExample.setLimitSize(page.getLimitSize());
		totalCount=sourceNicheService.sourceNichecountByExample(sourceNicheCustomExample);
		dataList=sourceNicheService.sourceNicheCustomselectByExample(sourceNicheCustomExample);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	//修改上线时间
	@RequestMapping("/resourceLocationManagement/toEditDate.shtml")
	public ModelAndView toEditDate(HttpServletRequest request) {
		String rtPage = "resourceLocationManagement/toEditDate";
		String id = request.getParameter("id");
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(id)){
			SourceNiche SourceNiche = sourceNicheService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("SourceNiche", SourceNiche);
		}
		String setType = request.getParameter("setType");
		resMap.put("setType", setType);
		return new ModelAndView(rtPage,resMap);
	}

	//保存修改的上线时间
		@RequestMapping("/resourceLocationManagement/saveEditUpdate.shtml")
		@ResponseBody
		public Map<String, Object> save(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
			try{
			String id = request.getParameter("id");
			String upDate = request.getParameter("upDate");
			if(!StringUtils.isEmpty(id)){
				SourceNiche SourceNiche = sourceNicheService.selectByPrimaryKey(Integer.parseInt(id));
				SourceNiche.setUpDate(sdf.parse(upDate));//更新上线时间
				SourceNiche.setWeightUpdateDate(sdf.parse(upDate));//权重更新日期
				sourceNicheService.updateByPrimaryKey(SourceNiche);
			}

		}catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "确认失败");
		}
		return resMap;
		}


		//批量驳回
		@ResponseBody
		@RequestMapping("/resourceLocationManagement/toBatchRejection.shtml")
		public Map<String, Object> toBatchRejection(HttpServletRequest request, String id) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			String ids = request.getParameter("ids");
			try{
				if(!StringUtils.isEmpty(ids)){
				String[] split = ids.split(",");
				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId=Integer.valueOf(staffBean.getStaffID());
				for (int i = 0; i < split.length; i++) {
					SourceNicheExample example = new SourceNicheExample();
					example .createCriteria().andIdEqualTo(Integer.parseInt(split[i])).andDelFlagEqualTo("0");
					List<SourceNiche> SourceNiches = sourceNicheService.selectByExample(example);
					if(SourceNiches!=null&&SourceNiches.size()>0){
						//资源位驳回
						SourceNiche sourceNiche = SourceNiches.get(0);
						sourceNiche.setAuditBy(staffId);
						sourceNiche.setAuditStatus("2");
						sourceNiche.setAuditDate(new Date());
						sourceNicheService.updateByPrimaryKeySelective(sourceNiche);

						//banner轮播图下线
						BannerRecommendProductExample bannerRecommendProductExample = new BannerRecommendProductExample();
						bannerRecommendProductExample.createCriteria().andDelFlagEqualTo("0").andSourceNicheIdEqualTo(sourceNiche.getId());
						List<BannerRecommendProductCustom> BannerRecommendProductList = bannerRecommendProductService.selectByCustomExample(bannerRecommendProductExample );
						if(BannerRecommendProductList!=null && BannerRecommendProductList.size()>0){
								for(BannerRecommendProductCustom brpc : BannerRecommendProductList){
									brpc.setStatus("0");
									bannerRecommendProductService.updateByPrimaryKeySelective(brpc);
								}
							}

						}
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "批量修改失败，请稍后重试");
				return resMap;
			}
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			return resMap;
		}

		//批量通过
		@ResponseBody
		@RequestMapping("/resourceLocationManagement/toBatchPass.shtml")
		public Map<String, Object> toBatchPass(HttpServletRequest request, String ids) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			String productIds = request.getParameter("ids");
			try{
				if(!StringUtils.isEmpty(productIds)){
				String[] split = productIds.split(",");
				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId=Integer.valueOf(staffBean.getStaffID());
				for (int i = 0; i < split.length; i++) {
					SourceNicheExample example = new SourceNicheExample();
					example .createCriteria().andIdEqualTo(Integer.parseInt(split[i])).andDelFlagEqualTo("0");
					List<SourceNiche> SourceNiches = sourceNicheService.selectByExample(example);
					if(SourceNiches!=null&&SourceNiches.size()>0){
						SourceNiche sourceNiche = SourceNiches.get(0);
						sourceNiche.setAuditBy(staffId);
						sourceNiche.setAuditStatus("1");
						sourceNiche.setAuditDate(new Date());

					/*	Double selectTotalSales = sourceNicheService.selectTotalSales(sourceNiche.getId());
					*/
						sourceNicheService.updateByPrimaryKeySelective(sourceNiche);
						}
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "批量修改失败，请稍后重试");
				return resMap;
			}
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			return resMap;
		}


	//==============================================
	//管理商品
	@RequestMapping("/resourceLocationManagement/sourceNiche.shtml")
	public ModelAndView sourceNiche(HttpServletRequest request) {
		/*ModelAndView m = new ModelAndView("/resourceLocationManagement/sourceNiche");
		 * return m;
		 * */
		String pagetype = request.getParameter("pagetype");
		String rtPage="";
		if(!StringUtil.isEmpty(pagetype)&&"1071".equals(pagetype)){
			rtPage = "/resourceLocationManagement/1832/recommendedModels";
		}else {
			rtPage = "/resourceLocationManagement/sourceNiche";
		}
		Map<String, Object> resMap = new HashMap<String, Object>();
		SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
		com.jf.entity.SourceProductTypeExample.Criteria createCriteria = suCustomExample.createCriteria();
		createCriteria.andDelFlagEqualTo("0");


		if(!StringUtil.isEmpty(pagetype)&&"1001".equals(pagetype)){
			createCriteria.andTypeEqualTo("1");//有好货
			resMap.put("sourceType",1);
		}else if(!StringUtil.isEmpty(pagetype)&&"1011".equals(pagetype)){
			createCriteria.andTypeEqualTo("4");//潮鞋上新
			resMap.put("sourceType",4);
		}else if(!StringUtil.isEmpty(pagetype)&&"1021".equals(pagetype)){
			createCriteria.andTypeEqualTo("5");//潮流男装
			resMap.put("sourceType",5);
		}else if(!StringUtil.isEmpty(pagetype)&&"1031".equals(pagetype)){
			createCriteria.andTypeEqualTo("6");//断码特惠
			resMap.put("sourceType",6);
		}else if(!StringUtil.isEmpty(pagetype)&&"1041".equals(pagetype)){
			createCriteria.andTypeEqualTo("7");//运动鞋服
			resMap.put("sourceType",7);
		}else if(!StringUtil.isEmpty(pagetype)&&"1051".equals(pagetype)){
			createCriteria.andTypeEqualTo("8");//潮流美妆
			resMap.put("sourceType",8);
		}else if(!StringUtil.isEmpty(pagetype)&&"1061".equals(pagetype)){
			createCriteria.andTypeEqualTo("9");//食品超市
			resMap.put("sourceType",9);
		}
		if(!StringUtil.isEmpty(pagetype)){
			resMap.put("pagetype",pagetype);
		}

		StaffBean sessionStaffBean = this.getSessionStaffBean(request);
		resMap.put("staffID",sessionStaffBean.getStaffID());
		List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
		resMap.put("sourceProductTypes",sourceProductTypes);
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes",productTypes);
		return new ModelAndView(rtPage,resMap);

	}

	//爆款推荐-管理商品
	@RequestMapping("/resourceLocationManagement/sourceNiche12.shtml")
	public ModelAndView sourceNiche12(Model model, HttpServletRequest request) {
		String pagetype = request.getParameter("pagetype");
		String rtPage="";
		if(!StringUtil.isEmpty(pagetype)&&"1071".equals(pagetype)){
			rtPage = "/resourceLocationManagement/1832/recommendedModels";
		}
		Map<String, Object> resMap = new HashMap<String, Object>();
		SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
		com.jf.entity.SourceProductTypeExample.Criteria createCriteria = suCustomExample.createCriteria();
		createCriteria.andDelFlagEqualTo("0");
		if(!StringUtil.isEmpty(pagetype)&&"1071".equals(pagetype)){
		createCriteria.andTypeEqualTo("12");//爆款推荐
		resMap.put("sourceType",12);
		}
		if(!StringUtil.isEmpty(pagetype)){
			resMap.put("pagetype",pagetype);
		}

		/*StaffBean sessionStaffBean = this.getSessionStaffBean(request);
		resMap.put("staffID",sessionStaffBean.getStaffID());
		List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
		resMap.put("sourceProductTypes",sourceProductTypes);
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes",productTypes);*/

		return new ModelAndView(rtPage,resMap);

	}

	//管理商品数据列表
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/sourcenichedata12.shtml")
	public Map<String, Object> sourcenichedata12(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String priceNum = request.getParameter("priceNum");
		if (!"".equals(priceNum)){
			paramMap.put("priceNum",Integer.parseInt(priceNum));
		}
		String priceBegin = request.getParameter("priceBegin");
		if (!"".equals(priceBegin)){
		paramMap.put("priceBegin",new BigDecimal(priceBegin));
		}
		String priceEnd = request.getParameter("priceEnd");
		if (!"".equals(priceEnd)){
		paramMap.put("priceEnd",new BigDecimal(priceEnd));
		}
		String whichProduct = request.getParameter("whichProduct");
		if (!"".equals(whichProduct)){
			paramMap.put("whichProduct",Integer.parseInt(whichProduct));
		}
		String goodIds = request.getParameter("goodIds");
		if (!"".equals(goodIds)){
			String[] split = goodIds.split("\n");
			List<String> aaaString = Arrays.asList(split);

		paramMap.put("goodIds",aaaString);
		}
		String artnos = request.getParameter("artnos");
		if (!"".equals(artnos)) {
			String[] split = artnos.split("\n");
			List<String> aaaString = Arrays.asList(split);

			paramMap.put("artnos",aaaString);
		}
		String name = request.getParameter("name");
		if (!"".equals(name)) {
			name = "%"+name+"%";
			paramMap.put("name", name);
		}
		String productType = request.getParameter("productType");
		if (!"".equals(productType)) {
			paramMap.put("productType", productType);
		}
		String addType = request.getParameter("addType");
		if (!"".equals(addType)) {
			paramMap.put("addType", addType);
		}
		String mchtCode = request.getParameter("mchtCode");
		if (!"".equals(mchtCode)) {
			paramMap.put("mchtCode", mchtCode);
		}
		String mchtName = request.getParameter("mchtName");
		if (!"".equals(mchtName)) {
			mchtName = "%"+mchtName+"%";
			paramMap.put("mchtName", mchtName);
		}
		String productBrandName = request.getParameter("productBrandName");
		if (!"".equals(productBrandName)) {
			paramMap.put("productBrandName", "%"+productBrandName+"%");
		}

		paramMap.put("limitStart",page.getLimitStart());
		paramMap.put("limitSize",page.getLimitSize());

		List<HashMap<String, Object>> dataList = sourceNicheService.queryProductlist(paramMap);
		Integer totalCount = sourceNicheService.queryProductlistCount(paramMap);
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}


	//管理商品数据列表
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/sourcenichedata.shtml")
	public Map<String, Object> sourcenichedata(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<SourceNicheCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
			SourceNicheCustomExample.SourceNicheCustomCriteria sourceNicheCustomCriteria=sourceNicheCustomExample.createCriteria();
			/*sourceNicheCustomCriteria.andDelFlagEqualTo("0").andTypeEqualTo("1").andStatusEqualTo("0");*/
			sourceNicheCustomCriteria.andDelFlagEqualTo("0");
			sourceNicheCustomCriteria.andAuditStatusEqualTo("1");//审核通过
			sourceNicheCustomCriteria.andpStatusEqualTo("1");//商品处于上线状态
			sourceNicheCustomCriteria.andStatusEqualTo("0");//活动状态为添加
			/*sourceNicheCustomExample.setOrderByClause("IFNULL(sn.seq_no, 99999999999) asc, (select p.weights from bu_product p where sn.link_id = p.id and p.del_flag = '0') desc");*/
			sourceNicheCustomExample.setOrderByClause("IFNULL(sn.seq_no, 99999999999) asc, IFNULL(sn.weight,0) desc");

			String pagetype = request.getParameter("pagetype");
			if(!StringUtil.isEmpty(pagetype)&&"1001".equals(pagetype)){
				sourceNicheCustomCriteria.andTypeEqualTo("1");//有好货
			}else if(!StringUtil.isEmpty(pagetype)&&"1011".equals(pagetype)){
				sourceNicheCustomCriteria.andTypeEqualTo("4");//潮鞋上新
			}else if(!StringUtil.isEmpty(pagetype)&&"1021".equals(pagetype)){
				sourceNicheCustomCriteria.andTypeEqualTo("5");//潮流男装
			}else if(!StringUtil.isEmpty(pagetype)&&"1031".equals(pagetype)){
				sourceNicheCustomCriteria.andTypeEqualTo("6");//断码特惠
			if(!StringUtil.isEmpty(request.getParameter("twoType"))){//二级分类
				sourceNicheCustomCriteria.andProductTypeTwoLike("%"+request.getParameter("twoType")+"%");
			}
			}else if(!StringUtil.isEmpty(pagetype)&&"1041".equals(pagetype)){
				sourceNicheCustomCriteria.andTypeEqualTo("7");//运动鞋服
			}else if(!StringUtil.isEmpty(pagetype)&&"1051".equals(pagetype)){
				sourceNicheCustomCriteria.andTypeEqualTo("8");//潮流美妆
			}else if(!StringUtil.isEmpty(pagetype)&&"1061".equals(pagetype)){
				sourceNicheCustomCriteria.andTypeEqualTo("9");//食品超市
			}


			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))){//品类
				String productTypeId = request.getParameter("productTypeId");
				sourceNicheCustomCriteria.andsourceProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}

			if(!StringUtil.isEmpty(request.getParameter("salePriceBegin"))){//醒购价
				sourceNicheCustomCriteria.andSalePriceGreaterThanOrEqualTo(new BigDecimal(request.getParameter("salePriceBegin")));
			}

			if(!StringUtil.isEmpty(request.getParameter("salePriceEnd"))){
				sourceNicheCustomCriteria.andSalePriceLessThanOrEqualTo(new BigDecimal(request.getParameter("salePriceEnd")));
			}

			if(!StringUtil.isEmpty(request.getParameter("sign_begin"))){//上线时间
				sourceNicheCustomCriteria.andUpDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("sign_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("sign_end"))){
				sourceNicheCustomCriteria.andUpDateLessThanOrEqualTo(sdf.parse(request.getParameter("sign_end")+" 23:59:59"));
			}


			if(!StringUtil.isEmpty(request.getParameter("name"))){//商品名称
				sourceNicheCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
			}

			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){//品牌
				sourceNicheCustomCriteria.andproductbrandNameLike("%"+request.getParameter("productBrandName")+"%");
			}

			if(!StringUtil.isEmpty(request.getParameter("whichProduct"))){
				String goodIds = "";
				if(!StringUtil.isEmpty(request.getParameter("goodIds"))){
					goodIds = request.getParameter("goodIds");
				}else if(!StringUtil.isEmpty(request.getParameter("artnos"))){
					goodIds = request.getParameter("artnos");
				}
				if(!StringUtil.isEmpty(goodIds)){
					String[] split = goodIds.split("\n");
					String aaaString = "";
					for (int i = 0; i < split.length; i++) {
						if(aaaString==""){
							aaaString+=split[i];
						}else{
							aaaString+=","+split[i];
						}
					}
					if("1".equals(request.getParameter("whichProduct"))){//商品id
						sourceNicheCustomCriteria.andCodesIn(aaaString);
					}else if("2".equals(request.getParameter("whichProduct"))){//货物id
						sourceNicheCustomCriteria.andArtNosIn(aaaString);
					}
				}

			}

			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){//商家序号
				sourceNicheCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}

			sourceNicheCustomExample.setLimitStart(page.getLimitStart());
			sourceNicheCustomExample.setLimitSize(page.getLimitSize());
			totalCount=sourceNicheService.sourceNichecountByExample(sourceNicheCustomExample);
			dataList=sourceNicheService.sourceNicheCustomselectByExample(sourceNicheCustomExample);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}



	    //商品信息表
		@RequestMapping("/resourceLocationManagement/administrationCommodity.shtml")
		public ModelAndView administrationCommodity(HttpServletRequest request, Integer activitybrandgroupid) {
			ModelAndView m = new ModelAndView("/resourceLocationManagement/administrationCommodity");
			String sourceType = request.getParameter("sourceType");
			if(!StringUtil.isEmpty(sourceType)){
				m.addObject("sourceType", sourceType);
			}
			m.setViewName("/resourceLocationManagement/administrationCommodity");
			/*ModelAndView m = new ModelAndView("/resourceLocationManagement/administrationCommodity");*/
			return m;
		}


		@RequestMapping(value="/resourceLocationManagement/administrationCommoditydata.shtml")
		@ResponseBody
		public Map<String, Object> administrationCommoditydata(Model model, HttpServletRequest request, Page page){
			Map<String,Object> resMap = new HashMap<String,Object>();
			int totalCount =0;
			List<ProductCustom> productCustoms=new ArrayList<ProductCustom>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sourceType = request.getParameter("sourceType");
			try {
				ProductCustomExample productCustomExample=new ProductCustomExample();
				ProductCustomExample.ProductCustomCriteria productCustomCriteria=productCustomExample.createCriteria();
				productCustomCriteria.andDelFlagEqualTo("0");//商品没有删除
				productCustomCriteria.andStatusEqualTo("1");//商品为上线状态
				productCustomCriteria.andSaleTypeEqualTo("1");//销售类型 品牌款
				productCustomCriteria.andSourceEqualTo("0");//商品来源醒购平台
				//爆款推荐过滤条件
				if ("12".equals(sourceType)){
					productCustomCriteria.andMchtIdInRecommendedModels();
					//过滤掉爆款推荐已添加的商品
					productCustomCriteria.andIdNotInRecommendedModels();
					productCustomExample.setOrderByClause("p.weights desc");
				}else {
					productCustomExample.setOrderByClause("t.weights desc");
				}
				/*productCustomCriteria.andSourceNicheNotEqualTo();*/
				/*productCustomCriteria.andAuditStatusEqualTo();*///参加活动
				/*productCustomCriteria.andSourceTypeEqualTo(sourceType);*/

/*				//没有参加有好货的商品
				select id from bu_product where id in (select sn.link_id from bu_source_niche sn where sn.type != "1" )*/

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
					productCustomCriteria.andProductBrandNameEqualTo(request.getParameter("productBrandName"));
				}

				if(!StringUtil.isEmpty(request.getParameter("artNo"))){
					productCustomCriteria.andArtNoEqualTo(request.getParameter("artNo"));
				}

				if(!StringUtil.isEmpty(request.getParameter("name"))){
					productCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
				}

				if(!StringUtil.isEmpty(request.getParameter("codes"))){
					String codes = request.getParameter("codes");
					String[] split = codes.split("\n");
					List<String> asList = Arrays.asList(split);
					productCustomCriteria.andCodeIn(asList);
				}

				if(!StringUtil.isEmpty(request.getParameter("update_begin"))){
					productCustomCriteria.andStartUpdatedateBeginDate(request.getParameter("update_begin")+" 00:00:00");
				}
				if(!StringUtil.isEmpty(request.getParameter("update_end"))){
					productCustomCriteria.andEndUpdatedateBeginDate(request.getParameter("update_end")+" 23:59:59");
				}
				if(!StringUtil.isEmpty(request.getParameter("salePriceBegin"))){
					productCustomCriteria.andSalePriceGreaterThanOrEqualTo(new BigDecimal(request.getParameter("salePriceBegin")));
				}

				if(!StringUtil.isEmpty(request.getParameter("salePriceEnd"))){
					productCustomCriteria.andSalePriceLessThanOrEqualTo(new BigDecimal(request.getParameter("salePriceEnd")));
				}

				totalCount=	productService.countProductCustomByExample(productCustomExample);
				productCustomExample.setLimitStart(page.getLimitStart());
				productCustomExample.setLimitSize(page.getLimitSize());
		  		if("12".equals(sourceType)){
					productCustoms =productService.selectRecommendedProductCustomByExample(productCustomExample);
				}else {
					productCustoms =productService.selectProductCustomByExample(productCustomExample);
				}

				for (ProductCustom productCustom:productCustoms) {
					if(!productCustom.getPic().contains("http")){
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

		//手动添加商品修改权重
		@ResponseBody
		@RequestMapping("/resourceLocationManagement/reviseWeights.shtml")
		public Map<String, Object> reviseWeights(HttpServletRequest request) {
			Map<String, Object> map = new HashMap<String, Object>();
			String code = "200";
			String msg = "操作成功！";
			try {
				Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
				String id = request.getParameter("id");//资源位表ID
				String weights = request.getParameter("weights");

				SourceNiche record = new SourceNiche();
				record.setId(Integer.parseInt(id));
				record.setWeight(Integer.parseInt(weights));
				record.setUpdateBy(staffID);
				record.setUpDate(new Date());
				sourceNicheService.updateByPrimaryKeySelective(record);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				code = "9999";
				msg = "操作失败！";
			}
			map.put("code", code);
			map.put("msg", msg);
			return map;
		}

		//添加至资源位管理表
		@ResponseBody
		@RequestMapping("/resourceLocationManagement/addProductlist.shtml")
		public Map<String, Object> addProductlist(HttpServletRequest request) {
			Map<String, Object> map = new HashMap<String, Object>();
			String code = "200";
			String msg = "操作成功！";
			try {
				Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
				String id = request.getParameter("id");//商品ID
				String sourceType = request.getParameter("sourceType");//type
				if(!StringUtil.isEmpty(id)&&!StringUtil.isEmpty(sourceType)) {
					if ("12".equals(sourceType)){
						sourceNicheService.addProductlist12(staffID, id, sourceType);
					}else {
						sourceNicheService.addProductlist(staffID, id, sourceType);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				code = "9999";
				msg = "操作失败！";
			}
			map.put("code", code);
			map.put("msg", msg);
			return map;
		}


		//删除
		@ResponseBody
		@RequestMapping("/resourceLocationManagement/deleteviewProduct.shtml")
		public Map<String, Object> deleteviewProduct(HttpServletRequest request, String id) {
			Map<String, Object> map = new HashMap<String, Object>();
			String code = "200";
			String msg = "操作成功！";
			try {
				Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
				if(!StringUtil.isEmpty(id)) {
					SourceNiche sourceNiche=sourceNicheService.selectByPrimaryKey(Integer.valueOf(id));
					sourceNiche.setUpdateBy(staffID);
					sourceNiche.setUpdateDate(new Date());
					sourceNiche.setStatus("1");
					sourceNicheService.updateByPrimaryKeySelective(sourceNiche);

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				code = "9999";
				msg = "操作失败！";
			}
			map.put("code", code);
			map.put("msg", msg);
			return map;
		}


		/**
		 * 到时间驳回
		 *
		 * @param model
		 * @param request
		 * @return
		 */
/*		@RequestMapping("/resourceLocationManagement/rejectMethod.shtml")
		public String rejectMethod(HttpServletRequest request){
			String id = request.getParameter("id");
			if(!StringUtil.isEmpty(id)){
				SourceNiche sourceNiche = sourceNicheService.selectByPrimaryKey(Integer.parseInt(id));
				if("0".equals(sourceNiche.getAuditStatus())){
				sourceNiche.setAuditStatus("2");
				sourceNiche.setAuditDate(new Date());
				sourceNicheService.updateByPrimaryKeySelective(sourceNiche);
				}
			}
			return "resourceLocationManagement/commodityAudit";
		}*/

		//修改排序值
		@ResponseBody
		@RequestMapping(value = "/resourceLocationManagement/updateSourcenicheseNo.shtml")
		public Map<String, Object> updateSourcenicheseNo(HttpServletRequest request, Integer id, Integer seqNo) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = null;
			String msg =null;
			try {
				SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
				sourceNicheCustomExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
				SourceNiche sourceNiche=new SourceNiche();
				sourceNiche.setSeqNo(seqNo);
				sourceNicheService.updateByExampleSelective(sourceNiche, sourceNicheCustomExample);
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

		        //清空排序值
				@ResponseBody
				@RequestMapping(value = "/resourceLocationManagement/delseqNo.shtml")
				public Map<String, Object> deleteSeqNo(HttpServletRequest request, Integer id) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg =null;
					try {
						Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
						 SourceNiche sourceNiche=sourceNicheService.selectByPrimaryKey(id);
						 sourceNiche.setSeqNo(null);
						 sourceNiche.setUpdateBy(staffID);
						 sourceNiche.setUpdateDate(new Date());
						 sourceNicheService.updateByPrimaryKey(sourceNiche);
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


				//主营类目
				@RequestMapping("/resourceLocationManagement/categoryAdministration.shtml")
				public ModelAndView categoryAdministration(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/resourceLocationManagement/mallCategorySubList");
					String pagetype=request.getParameter("pagetype");
					if (pagetype.equals("1003")) {
						m.addObject("pageType", pagetype);
					}else if (pagetype.equals("1005")) {
						m.addObject("pageType", pagetype);
					}else if (pagetype.equals("1008")) {
						m.addObject("pageType", pagetype);
					}else if (pagetype.equals("1033")) {
						m.addObject("pageType", pagetype);
					}else if (pagetype.equals("1096")) {
						m.addObject("pageType", pagetype);
					}else if (pagetype.equals("1106")) {
						m.addObject("pageType", pagetype);
					}
					return m;
				}

				//主营类目数据列表
				@ResponseBody
				@RequestMapping("/resourceLocationManagement/mallCategorySub/data.shtml")
				public Map<String, Object> mallCategorySub(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					List<SourceProductTypeCustom> dataList = null;
					Integer totalCount = 0;
					try {
						SourceProductTypeCustomExample sourceProductTypeCustomExample=new SourceProductTypeCustomExample();
						SourceProductTypeCustomExample.SourceProductTypeCustomCriteria sourceProductTypeCustomCriteria=sourceProductTypeCustomExample.createCriteria();
						sourceProductTypeCustomCriteria.andDelFlagEqualTo("0");
						sourceProductTypeCustomExample.setOrderByClause("IFNULL(spt.seq_no, 99999999999) asc, spt.create_date desc");
						if (request.getParameter("pagetype").equals("1003")) {
							sourceProductTypeCustomCriteria.andTypeEqualTo("1");
						}else if (request.getParameter("pagetype").equals("1005")) {
							sourceProductTypeCustomCriteria.andTypeEqualTo("2");
						}else if (request.getParameter("pagetype").equals("1008")) {
							sourceProductTypeCustomCriteria.andTypeEqualTo("3");
						}else if (request.getParameter("pagetype").equals("1033")) {
							sourceProductTypeCustomCriteria.andTypeEqualTo("6");
						}else if (request.getParameter("pagetype").equals("1096")) {
							sourceProductTypeCustomCriteria.andTypeEqualTo("11");
						}else if (request.getParameter("pagetype").equals("1106")) {
							sourceProductTypeCustomCriteria.andTypeEqualTo("12");
						}
						if (!StringUtil.isEmpty(request.getParameter("status"))) {
							if (request.getParameter("status").equals("0")) {
								sourceProductTypeCustomCriteria.andStatusEqualTo("0");
							}else {
								sourceProductTypeCustomCriteria.andStatusEqualTo("1");
							}
						}
						if(!StringUtil.isEmpty(request.getParameter("create_begin"))){
							sourceProductTypeCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("create_begin")+" 00:00:00"));
						}
						if(!StringUtil.isEmpty(request.getParameter("create_end"))){
							sourceProductTypeCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("create_end")+" 23:59:59"));
						}
						sourceProductTypeCustomExample.setLimitStart(page.getLimitStart());
						sourceProductTypeCustomExample.setLimitSize(page.getLimitSize());
						totalCount=sourceProductTypeService.sourceProductTypecountByExample(sourceProductTypeCustomExample);
						dataList=sourceProductTypeService.sourceProductTypeCustomselectByExample(sourceProductTypeCustomExample);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}

				//添加主营类目界面
				@RequestMapping(value = "/resourceLocationManagement/toEdit.shtml")
				public ModelAndView toEditMallCategorySub(HttpServletRequest request) {
					String rtPage = "/resourceLocationManagement/editMallCategorySub";
					Map<String, Object> resMap = new HashMap<String, Object>();
					ProductTypeExample pte = new ProductTypeExample();
					ProductTypeExample.Criteria ptec = pte.createCriteria();
					ptec.andDelFlagEqualTo("0");
					ptec.andTLevelEqualTo(1);
					ptec.andStatusEqualTo("1");
					List<ProductType> productTypes = productTypeService.selectByExample(pte);
					resMap.put("productTypes", productTypes);

					String id = request.getParameter("id");
					if (!StringUtils.isEmpty(id)) {
						SourceProductType sourceProductType = sourceProductTypeService.selectByPrimaryKey(Integer.parseInt(id));
						resMap.put("sourceProductType", sourceProductType);
						if(!StringUtils.isEmpty(sourceProductType.getProductType1Id())){
							ProductTypeExample secondPte = new ProductTypeExample();
							ProductTypeExample.Criteria secondPtec = secondPte.createCriteria();
							secondPtec.andDelFlagEqualTo("0");
							secondPtec.andParentIdEqualTo(sourceProductType.getProductType1Id());
							secondPtec.andStatusEqualTo("1");
							List<ProductType> secondProductTypes = productTypeService.selectByExample(secondPte);
							resMap.put("secondProductTypes", secondProductTypes);
						}
					}
					resMap.put("sourceProductTypeid", id);
					String pagetype=request.getParameter("pagetype");
					resMap.put("pagetype", pagetype);
					return new ModelAndView(rtPage,resMap);
				}

				//保存主营类目
				@RequestMapping(value = "/resourceLocationManagement/mallCategorySub/save.shtml")
				@ResponseBody
				public Map<String, Object> mallCategorySubSave(HttpServletRequest request) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "成功");
					Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
					try {
						String id = request.getParameter("id");
						String name = request.getParameter("name");
						String productType1 = request.getParameter("productType1");
						String productType2Ids = request.getParameter("productType2Ids");
						String pagetype = request.getParameter("pagetype");

						SourceProductType sourceProductType = new SourceProductType();
						if(!StringUtils.isEmpty(id)){
							sourceProductType = sourceProductTypeService.selectByPrimaryKey(Integer.parseInt(id));
							if (!sourceProductType.getSourceProductTypeName().equals(name)) {
								SourceProductTypeExample sourceProductTypeExample=new SourceProductTypeExample();
								SourceProductTypeExample.Criteria sourceProductTypeExampleCriteria=sourceProductTypeExample.createCriteria();
								sourceProductTypeExampleCriteria.andDelFlagEqualTo("0").andSourceProductTypeNameEqualTo(name);
								if (pagetype.equals("1003")) {
									sourceProductTypeExampleCriteria.andTypeEqualTo("1");
								}else if (pagetype.equals("1005")) {
									sourceProductTypeExampleCriteria.andTypeEqualTo("2");
								}else if (pagetype.equals("1008")) {
									sourceProductTypeExampleCriteria.andTypeEqualTo("3");
								}else if (pagetype.equals("1033")) {
									sourceProductTypeExampleCriteria.andTypeEqualTo("6");
								}else if (pagetype.equals("1096")) {
									sourceProductTypeExampleCriteria.andTypeEqualTo("11");
								}else if (pagetype.equals("1106")) {
									sourceProductTypeExampleCriteria.andTypeEqualTo("12");
								}
								List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(sourceProductTypeExample);
								if (sourceProductTypes!=null && sourceProductTypes.size()>0) {
									 resMap.put("returnCode", "9999");
									 resMap.put("returnMsg", "分类名称不能相同!");
									 return resMap;
								}else {
									sourceProductType.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
									sourceProductType.setUpdateDate(new Date());
									sourceProductType.setSourceProductTypeName(name);
									if (productType1.equals("0")) {
										sourceProductType.setProductType1Id(null);
									}else {
										sourceProductType.setProductType1Id(Integer.valueOf(productType1));
									}
									if (productType2Ids.equals("2")) {
										sourceProductType.setProductType2Id(null);
									}else {
										sourceProductType.setProductType2Id(productType2Ids);
									}
									sourceProductTypeService.updateByPrimaryKey(sourceProductType);
								}

							}else {

								sourceProductType.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
								sourceProductType.setUpdateDate(new Date());
								sourceProductType.setSourceProductTypeName(name);
								if (productType1.equals("0")) {
									sourceProductType.setProductType1Id(null);
								}else {
									sourceProductType.setProductType1Id(Integer.valueOf(productType1));
								}
								if (productType2Ids.equals("2")) {
									sourceProductType.setProductType2Id(null);
								}else {
									sourceProductType.setProductType2Id(productType2Ids);
								}
								sourceProductTypeService.updateByPrimaryKey(sourceProductType);
							}


						}else{

							SourceProductTypeExample sourceProductTypeExample=new SourceProductTypeExample();
							SourceProductTypeExample.Criteria sourceProductTypeExampleCriteria=sourceProductTypeExample.createCriteria();
							sourceProductTypeExampleCriteria.andDelFlagEqualTo("0").andSourceProductTypeNameEqualTo(name);
							if (pagetype.equals("1003")) {
								sourceProductTypeExampleCriteria.andTypeEqualTo("1");
							}else if (pagetype.equals("1005")) {
								sourceProductTypeExampleCriteria.andTypeEqualTo("2");
							}else if (pagetype.equals("1008")) {
								sourceProductTypeExampleCriteria.andTypeEqualTo("3");
							}else if (pagetype.equals("1033")) {
								sourceProductTypeExampleCriteria.andTypeEqualTo("6");
							}else if (pagetype.equals("1096")) {
								sourceProductTypeExampleCriteria.andTypeEqualTo("11");
							}else if (pagetype.equals("1106")) {
								sourceProductTypeExampleCriteria.andTypeEqualTo("12");
							}

							List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(sourceProductTypeExample);
							if (sourceProductTypes!=null && sourceProductTypes.size()>0) {
								 resMap.put("returnCode", "9999");
								 resMap.put("returnMsg", "分类名称不能相同!");
								 return resMap;
							}else {

								sourceProductType.setDelFlag("0");
								sourceProductType.setSourceProductTypeName(name);
								if (pagetype.equals("1003")) {
									sourceProductType.setType("1");
								}else if (pagetype.equals("1005")) {
									sourceProductType.setType("2");
								}else if (pagetype.equals("1008")) {
									sourceProductType.setType("3");
								}else if (pagetype.equals("1033")) {
									sourceProductType.setType("6");
								}else if (pagetype.equals("1096")) {
									sourceProductType.setType("11");
								}else if (pagetype.equals("1106")) {
									sourceProductType.setType("12");
								}
								sourceProductType.setStatus("0");
								sourceProductType.setCreateBy(staffID);
								sourceProductType.setCreateDate(new Date());
								if (productType1.equals("0")) {
									sourceProductType.setProductType1Id(null);
								}else {
									sourceProductType.setProductType1Id(Integer.valueOf(productType1));
								}
								if (productType2Ids.equals("2")) {
									sourceProductType.setProductType2Id(null);
								}else {
									sourceProductType.setProductType2Id(productType2Ids);
								}
								sourceProductTypeService.insert(sourceProductType);
							}


						}

					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "保存失败，请稍后重试");
						return resMap;
					}
					return resMap;
				}

				//变更主营类目上下架
				@RequestMapping(value = "/resourceLocationManagement/changeStatus.shtml")
				@ResponseBody
				public Map<String, Object> mallCategorySubChangeStatus(HttpServletRequest request) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("returnCode", "0000");
					resMap.put("returnMsg", "操作成功");
					try {
						String id = request.getParameter("id");
						SourceProductType sourceProductType=sourceProductTypeService.selectByPrimaryKey(Integer.valueOf(id));
						if(sourceProductType.getStatus().equals("0")){
							sourceProductType.setStatus("1");
						}else{
							sourceProductType.setStatus("0");
						}
						sourceProductType.setUpdateDate(new Date());
						sourceProductType.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
						sourceProductTypeService.updateByPrimaryKeySelective(sourceProductType);
					} catch (Exception e) {
						e.printStackTrace();
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "保存失败，请稍后重试");
						return resMap;
					}
					return resMap;
				}


				//删除类目
				@ResponseBody
				@RequestMapping("/resourceLocationManagement/deleteviewCategory.shtml")
				public Map<String, Object> deleteviewCategory(HttpServletRequest request, String id) {
					Map<String, Object> map = new HashMap<String, Object>();
					String code = "200";
					String msg = "操作成功！";
					try {
						Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
						if(!StringUtil.isEmpty(id)) {
							SourceProductType sourceProductType=sourceProductTypeService.selectByPrimaryKey(Integer.valueOf(id));
							sourceProductType.setUpdateBy(staffID);
							sourceProductType.setUpdateDate(new Date());
							sourceProductType.setDelFlag("1");
							sourceProductTypeService.updateByPrimaryKeySelective(sourceProductType);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						code = "9999";
						msg = "操作失败！";
					}
					map.put("code", code);
					map.put("msg", msg);
					return map;
				}

				//修改类目排序值
				@ResponseBody
				@RequestMapping(value = "/resourceLocationManagement/updateProducttypeArtNo.shtml")
				public Map<String, Object> updateProducttypeArtNo(HttpServletRequest request, Integer id, Integer seqNo) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					String code = null;
					String msg =null;
					Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
					try {
						SourceProductType sourceProductType=sourceProductTypeService.selectByPrimaryKey(Integer.valueOf(id));
						sourceProductType.setSeqNo(seqNo);
						sourceProductType.setUpdateBy(staffID);
						sourceProductType.setUpdateDate(new Date());
						sourceProductTypeService.updateByPrimaryKey(sourceProductType);
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

				//管理店铺
				@RequestMapping("/resourceLocationManagement/sourceNicheShop.shtml")
				public ModelAndView sourceNicheShop(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/resourceLocationManagement/sourceNicheShop");
					ProductTypeExample productTypeExample = new ProductTypeExample();
					ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
					productTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);

					//判断89
					String pagetype = request.getParameter("pagetype");
					if(!StringUtil.isEmpty(pagetype)){
						m.addObject("pagetype", pagetype);
					}

					//钟表运营部状态，只获取主营类目为钟表
					String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
					if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
						String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
						if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
							productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
						}
					}
					m.addObject("isCwOrgStatus", isCwOrgStatus);

					List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
					m.addObject("productTypeList", productTypeList);

					ResourceBundle resource = ResourceBundle.getBundle("base_config");
					String mUrl = resource.getString("mUrl");
					m.addObject("previewUrl", mUrl+"/xgbuy/views/index.html?redirect_url=seller/index.html?mchtId=");
					return m;
				}


				//管理店铺数据列表
				@ResponseBody
				@RequestMapping("/resourceLocationManagement/sourcenicheshopdata.shtml")
				public Map<String, Object> sourcenicheshopdata(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<SourceNicheCustom> dataList = null;
					Integer totalCount = 0;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					try {
						String type = "";
						String pagetype = request.getParameter("pagetype");
						if(!StringUtil.isEmpty(pagetype)){
							switch (pagetype) {
							case "1000":
								type = "2";
								break;
							case "1081":
								type = "10";
								break;
							default:
								break;
							}
						}
						SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
						SourceNicheCustomExample.SourceNicheCustomCriteria sourceNicheCustomCriteria=sourceNicheCustomExample.createCriteria();
						sourceNicheCustomCriteria.andDelFlagEqualTo("0").andTypeEqualTo(type).andStatusEqualTo("0").andAuditStatusEqualTo("1");
					/*	sourceNicheCustomExample.setOrderByClause("IFNULL(sn.seq_no, 99999999999) asc, sn.link_id desc");*/
						sourceNicheCustomExample.setOrderByClause("IFNULL(sn.seq_no, 99999999999) asc ,IFNULL(sn.weight, 0) desc ,up_date desc");
						if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
							sourceNicheCustomCriteria.andmchtCodeEqualTo(request.getParameter("mchtCode"));
						}
						if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
							sourceNicheCustomCriteria.andmchtNameLikeTo("%"+request.getParameter("mchtName")+"%");
						}
						/*if (!StringUtil.isEmpty(request.getParameter("status"))) {
							sourceNicheCustomCriteria.andmchtStatusDescEqualTo(request.getParameter("status"));
						}*/
						/*if (!StringUtil.isEmpty(request.getParameter("gradeDesc"))) {
							sourceNicheCustomCriteria.andgradeDescEqualTo(request.getParameter("gradeDesc"));
						}*/
						if (!StringUtil.isEmpty(request.getParameter("productBrandName"))) {
							sourceNicheCustomCriteria.andproductBrandNameEqualTo(request.getParameter("productBrandName"));
						}
						if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
							sourceNicheCustomCriteria.andproductTypeIdEqualTo(request.getParameter("productTypeId"));
						}
						/*if (!StringUtil.isEmpty(request.getParameter("degreeAuditriskDesc"))) {
							sourceNicheCustomCriteria.andDegreeAuditriskDescEqualTo(request.getParameter("degreeAuditriskDesc"));
						}*/
						/*if (!StringUtil.isEmpty(request.getParameter("degreeAdaptabilityDesc"))) {
							sourceNicheCustomCriteria.anddegreeAdaptabilityDescEqualTo(request.getParameter("degreeAdaptabilityDesc"));
						}*/
						/*if (!StringUtil.isEmpty(request.getParameter("shopStatusDesc"))) {
							sourceNicheCustomCriteria.andshopStatusEqualTo(request.getParameter("shopStatusDesc"));
						}*/
						if(!StringUtil.isEmpty(request.getParameter("signBegin"))){//上线时间
							sourceNicheCustomCriteria.andUpDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("signBegin")+" 00:00:00"));
						}
						if(!StringUtil.isEmpty(request.getParameter("signEnd"))){
							sourceNicheCustomCriteria.andUpDateLessThanOrEqualTo(sdf.parse(request.getParameter("signEnd")+" 23:59:59"));
						}
/*						if(!StringUtil.isEmpty(request.getParameter("signBegin") )){//报名时间
							sourceNicheCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("signBegin")+" 00:00:00"));
						}
						if(!StringUtil.isEmpty(request.getParameter("signEnd"))){
							sourceNicheCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("signEnd")+" 23:59:59"));
						}*/
						sourceNicheCustomExample.setLimitStart(page.getLimitStart());
						sourceNicheCustomExample.setLimitSize(page.getLimitSize());
						totalCount=sourceNicheService.sourceNichecountByExample(sourceNicheCustomExample);
						dataList=sourceNicheService.sourceNicheCustomselectByExample(sourceNicheCustomExample);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}


				//商家信息
				@RequestMapping("/resourceLocationManagement/admchtinfolist.shtml")
				public ModelAndView addmchtinfolist(HttpServletRequest request, Integer activitybrandgroupid) {
					ModelAndView m = new ModelAndView("/resourceLocationManagement/admchtinfoList");
					ProductTypeExample productTypeExample = new ProductTypeExample();
					ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
					productTypeCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);

					//钟表运营部状态，只获取主营类目为钟表
					String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
					if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
						String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
						if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
							productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
						}
					}
					m.addObject("isCwOrgStatus", isCwOrgStatus);

					List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
					m.addObject("productTypeList", productTypeList);

					ResourceBundle resource = ResourceBundle.getBundle("base_config");
					String mUrl = resource.getString("mUrl");
					m.addObject("previewUrl", mUrl+"/xgbuy/views/index.html?redirect_url=seller/index.html?mchtId=");
					return m;
				}


				//商家信息数据列表
				@ResponseBody
				@RequestMapping("/resourceLocationManagement/admchtinfoListdata.shtml")
				public Map<String, Object> addmchtinfoListdata(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					List<MchtInfoCustom> dataList = null;
					Integer totalCount = 0;
					try {
						MchtInfoCustomExample mchtInfoCustomExample=new MchtInfoCustomExample();
						MchtInfoCustomExample.MchtInfoCustomCriteria mchtInfoCustomCriteria=mchtInfoCustomExample.createCriteria();
						mchtInfoCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andShopStatusEqualTo("1");
					    mchtInfoCustomCriteria.andSourceNicheShopNotEqualTo();
						mchtInfoCustomExample.setOrderByClause("a.id desc");
						if (!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
							mchtInfoCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
						}
						if (!StringUtil.isEmpty(request.getParameter("mchtName"))) {
							mchtInfoCustomCriteria.andMchtnameLike("%"+request.getParameter("mchtName")+"%");
						}
						if (!StringUtil.isEmpty(request.getParameter("status"))) {
							if (request.getParameter("status").equals("1")) {
								mchtInfoCustomCriteria.andStatusEqualTo("1");
							}else if (request.getParameter("status").equals("2")) {
								mchtInfoCustomCriteria.andStatusEqualTo("2");
							}else {
								mchtInfoCustomCriteria.andStatusEqualTo("3");
							}
						}
						if (!StringUtil.isEmpty(request.getParameter("gradeDesc"))) {
							if (request.getParameter("gradeDesc").equals("1")) {
								mchtInfoCustomCriteria.andGradeEqualTo("1");
							}else if (request.getParameter("gradeDesc").equals("2")) {
								mchtInfoCustomCriteria.andGradeEqualTo("2");
							}else if (request.getParameter("gradeDesc").equals("3")) {
								mchtInfoCustomCriteria.andGradeEqualTo("3");
							}else {
								mchtInfoCustomCriteria.andGradeEqualTo("4");
							}
						}
						if (!StringUtil.isEmpty(request.getParameter("productBrandName"))) {
							mchtInfoCustomCriteria.andproductBrandNameEqualTo(request.getParameter("productBrandName"));
						}
						if (!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
							mchtInfoCustomCriteria.andproductTypeIdEqualTo(request.getParameter("productTypeId"));
						}
						if (!StringUtil.isEmpty(request.getParameter("auditriskDesc"))) {
							mchtInfoCustomCriteria.andauditriskDescEqualTo(request.getParameter("auditriskDesc"));
						}
						if (!StringUtil.isEmpty(request.getParameter("degreeAdaptabilityDesc"))) {
							mchtInfoCustomCriteria.anddegreeAdaptabilityDescEqualTo(request.getParameter("degreeAdaptabilityDesc"));
						}
						mchtInfoCustomExample.setLimitStart(page.getLimitStart());
						mchtInfoCustomExample.setLimitSize(page.getLimitSize());
						totalCount=mchtInfoService.countByExample(mchtInfoCustomExample);
						dataList=mchtInfoService.selectByExample(mchtInfoCustomExample);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}

				//添加至资源位管理表
				@ResponseBody
				@RequestMapping("/resourceLocationManagement/editMchtinfoList.shtml")
				public Map<String, Object> editMchtinfoList(HttpServletRequest request) {
					Map<String, Object> map = new HashMap<String, Object>();
					String code = "200";
					String msg = "操作成功！";
					try {
						Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
						String id = request.getParameter("id");//商家ID
						if(!StringUtil.isEmpty(id)) {
							sourceNicheService.addMchtinfoList(staffID, id);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						code = "9999";
						msg = "操作失败！";
					}
					map.put("code", code);
					map.put("msg", msg);
					return map;
				}



				//拉新分润管理商品
				@RequestMapping("/resourceLocationManagement/laXinRunGoodQuality.shtml")
				public ModelAndView laXinRunGoodQuality(HttpServletRequest request) {
					ModelAndView m = new ModelAndView("/resourceLocationManagement/laXinRunGoodQuality");
					return m;
				}

				//拉新分润管理商品数据列表
				@ResponseBody
				@RequestMapping("/resourceLocationManagement/laXinRunGoodQualitydata.shtml")
				public Map<String, Object> laXinRunGoodQualitydata(HttpServletRequest request, Page page) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					List<SourceNicheCustom> dataList = null;
					Integer totalCount = 0;
					try {
						SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
						SourceNicheCustomExample.SourceNicheCustomCriteria sourceNicheCustomCriteria=sourceNicheCustomExample.createCriteria();
						sourceNicheCustomCriteria.andDelFlagEqualTo("0").andTypeEqualTo("3").andStatusEqualTo("0");
						sourceNicheCustomExample.setOrderByClause("IFNULL(sn.seq_no, 99999999999) asc, (select p.weights from bu_product p where sn.link_id = p.id and p.del_flag = '0') desc");

						if (!StringUtils.isEmpty(request.getParameter("status"))) {
							if (request.getParameter("status").equals("0")) {
								sourceNicheCustomCriteria.andpStatusEqualTo("0");
							}else {
								sourceNicheCustomCriteria.andpStatusEqualTo("1");
							}
						}
						String suitSex = request.getParameter("suitSex");
						if(!StringUtils.isEmpty(suitSex)){
							if(suitSex.equals("10")){//男
								sourceNicheCustomCriteria.andSuitSexEqualToManOrWomen(suitSex);
							}else if(suitSex.equals("01")){//女
								sourceNicheCustomCriteria.andSuitSexEqualToManOrWomen(suitSex);
							}else{//男丶女
								sourceNicheCustomCriteria.andSuitSexEqualTo(suitSex);
							}
						}
						String suitGroup = request.getParameter("suitGroup");
						if(!StringUtils.isEmpty(suitGroup)){
							if(suitGroup.equals("100")){//青年
								sourceNicheCustomCriteria.andSuitGroupLike("1%");
							}else if(suitGroup.equals("010") || suitGroup.equals("001")){//儿童，中老年
								sourceNicheCustomCriteria.andSuitGroupLikeTo(suitGroup);
							}
						}
						if(!StringUtil.isEmpty(request.getParameter("productType"))){
							sourceNicheCustomCriteria.andProductTypeAll(productService.getProductTypeAllChild(Integer.valueOf(request.getParameter("productType"))));
						}
						if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
							sourceNicheCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
						}
						if(!StringUtil.isEmpty(request.getParameter("mchtName"))){
							sourceNicheCustomCriteria.andMchtNameLikeTo("%"+request.getParameter("mchtName")+"%");
						}
						if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
							sourceNicheCustomCriteria.andProductBrandNameEqualTo(request.getParameter("productBrandName"));
						}
						if(!StringUtil.isEmpty(request.getParameter("artNo"))){
							sourceNicheCustomCriteria.andArtNoEqualTo(request.getParameter("artNo"));
						}

						if(!StringUtil.isEmpty(request.getParameter("name"))){
							sourceNicheCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
						}
						if(!StringUtil.isEmpty(request.getParameter("code"))){
							sourceNicheCustomCriteria.andCodeEqualTo(request.getParameter("code"));
						}
						if(!StringUtil.isEmpty(request.getParameter("salePriceBegin"))){
							sourceNicheCustomCriteria.andSalePriceGreaterThanOrEqualTo(new BigDecimal(request.getParameter("salePriceBegin")));
						}

						if(!StringUtil.isEmpty(request.getParameter("salePriceEnd"))){
							sourceNicheCustomCriteria.andSalePriceLessThanOrEqualTo(new BigDecimal(request.getParameter("salePriceEnd")));
						}
						if(!StringUtil.isEmpty(request.getParameter("create_begin"))){
							sourceNicheCustomCriteria.andStartUpdatedateBeginDate(request.getParameter("create_begin")+" 00:00:00");
						}
						if(!StringUtil.isEmpty(request.getParameter("create_end"))){
							sourceNicheCustomCriteria.andEndUpdatedateBeginDate(request.getParameter("create_end")+" 23:59:59");
						}
						sourceNicheCustomExample.setLimitStart(page.getLimitStart());
						sourceNicheCustomExample.setLimitSize(page.getLimitSize());
						totalCount=sourceNicheService.sourceNichecountByExample(sourceNicheCustomExample);
						dataList=sourceNicheService.sourceNicheCustomselectByExample(sourceNicheCustomExample);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resMap.put("Rows", dataList);
					resMap.put("Total", totalCount);
					return resMap;
				}



				//拉新分润商品信息
				@RequestMapping("/resourceLocationManagement/addLaxincommodity.shtml")
				public ModelAndView addLaxincommodity(HttpServletRequest request, Integer activitybrandgroupid) {
					   ModelAndView m = new ModelAndView("/resourceLocationManagement/addLaXinCommodity");
					return m;
				}

				//拉新分润商品信息数据
				@RequestMapping(value="/resourceLocationManagement/addLaxinCommoditydata.shtml")
				@ResponseBody
				public Map<String, Object> addLaxinCommoditydata(Model model, HttpServletRequest request, Page page){
					Map<String,Object> resMap = new HashMap<String,Object>();
					int totalCount =0;
					List<ProductCustom> productCustoms=new ArrayList<ProductCustom>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						ProductCustomExample productCustomExample=new ProductCustomExample();
						ProductCustomExample.ProductCustomCriteria productCustomCriteria=productCustomExample.createCriteria();
						productCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("1").andSaleTypeEqualTo("1").andSourceEqualTo("0").andAuditStatusEqualTo("2");
						productCustomCriteria.andLaxinSourceNicheNotEqualTo();
						productCustomExample.setOrderByClause("t.weights desc");

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
							productCustomCriteria.andProductBrandNameEqualTo(request.getParameter("productBrandName"));
						}

						if(!StringUtil.isEmpty(request.getParameter("artNo"))){
							productCustomCriteria.andArtNoEqualTo(request.getParameter("artNo"));
						}

						if(!StringUtil.isEmpty(request.getParameter("name"))){
							productCustomCriteria.andNameLike("%"+request.getParameter("name")+"%");
						}

						if(!StringUtil.isEmpty(request.getParameter("code"))){
							productCustomCriteria.andCodeEqualTo(request.getParameter("code"));
						}

						if(!StringUtil.isEmpty(request.getParameter("update_begin"))){
							productCustomCriteria.andStartUpdatedateBeginDate(request.getParameter("update_begin")+" 00:00:00");
						}
						if(!StringUtil.isEmpty(request.getParameter("update_end"))){
							productCustomCriteria.andEndUpdatedateBeginDate(request.getParameter("update_end")+" 23:59:59");
						}
						if(!StringUtil.isEmpty(request.getParameter("salePriceBegin"))){
							productCustomCriteria.andSalePriceGreaterThanOrEqualTo(new BigDecimal(request.getParameter("salePriceBegin")));
						}

						if(!StringUtil.isEmpty(request.getParameter("salePriceEnd"))){
							productCustomCriteria.andSalePriceLessThanOrEqualTo(new BigDecimal(request.getParameter("salePriceEnd")));
						}

						totalCount=	productService.countProductCustomByExample(productCustomExample);
						productCustomExample.setLimitStart(page.getLimitStart());
						productCustomExample.setLimitSize(page.getLimitSize());
						productCustoms =productService.selectProductCustomByExample(productCustomExample);
						for (ProductCustom productCustom:productCustoms) {
							if(!productCustom.getPic().contains("http")){
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

				//拉新分润添加至资源位管理表
				@ResponseBody
				@RequestMapping("/resourceLocationManagement/addLaxincommoditylist.shtml")
				public Map<String, Object> addLaxincommoditylist(HttpServletRequest request) {
					Map<String, Object> map = new HashMap<String, Object>();
					String code = "200";
					String msg = "操作成功！";
					try {
						Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
						String id = request.getParameter("id");//商品ID
						String[] strs = id.split(",");
						List<Integer> listproductid=new ArrayList<Integer>();
						for (String productid : strs) {
							listproductid.add(Integer.valueOf(productid));
						}
						if(!StringUtil.isEmpty(id)) {
							SourceNicheExample sourceNicheExample=new SourceNicheExample();
							sourceNicheExample.createCriteria().andDelFlagEqualTo("0").andLinkIdIn(listproductid).andTypeEqualTo("3").andStatusEqualTo("1");
							List<SourceNiche> sourceNiches=sourceNicheService.selectByExample(sourceNicheExample);
							if (sourceNiches!=null && sourceNiches.size()>0) {

								sourceNicheService.addLaxinProductlist(staffID, id);
							}else {
								SourceNicheExample sourceNicheExample2=new SourceNicheExample();
								sourceNicheExample2.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("3").andLinkIdIn(listproductid);
								List<SourceNiche> sourceNiches2=sourceNicheService.selectByExample(sourceNicheExample2);
								if (sourceNiches2.size()<=0) {
									sourceNicheService.addLaxinProductlist(staffID, id);
								}
							}

						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						code = "9999";
						msg = "操作失败！";
					}
					map.put("code", code);
					map.put("msg", msg);
					return map;
				}



		//店铺审核
		@RequestMapping("/resourceLocationManagement/shopExamine.shtml")
		public ModelAndView shopExamine(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/shopExamine";// 首页
			Map<String, Object> resMap = new HashMap<String, Object>();
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.setOrderByClause("create_date desc");
			SourceProductTypeExample.Criteria createCriteria = suCustomExample.createCriteria();
			createCriteria.andDelFlagEqualTo("0");
			String pagetype = request.getParameter("pagetype");
			if(!StringUtil.isEmpty(pagetype)){
				resMap.put("pagetype",pagetype);
			}

			ProductTypeExample pte = new ProductTypeExample();
			pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
			List<ProductType> productTypes = productTypeService.selectByExample(pte);
			resMap.put("productTypes",productTypes);//一级分类

        return new ModelAndView(rtPage, resMap);
    }

    //店铺审核的数据
    @RequestMapping("/resourceLocationManagement/shopExamineDate.shtml")
    @ResponseBody
    public Map<String, Object> shopExamineDate(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<SourceNicheCustom> dataList = null;
        Integer totalCount = 0;
        String mchtCode = request.getParameter("mchtCode");//商家序号
        String mchtName = request.getParameter("mchtName");//商家名称
        String createBegin = request.getParameter("create_begin");//报名时间
        String createEnd = request.getParameter("create_end");
        String productTypeId = request.getParameter("productTypeId");//类目
        String productBrandName = request.getParameter("productBrandName");//品牌
        String signBegin = request.getParameter("sign_begin");//上线时间
        String signEnd = request.getParameter("sign_end");
        String auditStatus = request.getParameter("auditStatus");//审核状态
        String pagetype = request.getParameter("pagetype");//分类89
        try {
            SourceNicheCustomExample sourceNicheCustomExample = new SourceNicheCustomExample();
            sourceNicheCustomExample.setOrderByClause("create_date desc");
            SourceNicheCustomCriteria sourceNicheCustomCriteria = sourceNicheCustomExample.createCriteria();
            sourceNicheCustomCriteria.andDelFlagEqualTo("0");
            sourceNicheCustomCriteria.andStatusEqualTo("0");
            /*sourceNicheCustomCriteria.andAuditStatusEqualTo("0");*/

            if (!StringUtil.isEmpty(pagetype)) {//1070 每日好店  1080 大学生创业
                if ("1070".equals(pagetype)) {
                    sourceNicheCustomCriteria.andTypeEqualTo("2");
                }
                if ("1080".equals(pagetype)) {
                    sourceNicheCustomCriteria.andTypeEqualTo("10");
                }
            }
            if (!StringUtil.isEmpty(mchtCode)) {
                sourceNicheCustomCriteria.andmchtCodeEqualTo(mchtCode);
            }
            if (!StringUtil.isEmpty(mchtName)) {
                sourceNicheCustomCriteria.andmchtNameLikeTo("%" + mchtName + "%");
            }

            if (!StringUtil.isEmpty(productBrandName)) {
                sourceNicheCustomCriteria.andproductBrandNameEqualTo(productBrandName);
            }
            if (!StringUtil.isEmpty(productTypeId)) {
                sourceNicheCustomCriteria.andproductTypeIdEqualTo(productTypeId);
            }
            if (!StringUtil.isEmpty(auditStatus)) {
                sourceNicheCustomCriteria.andAuditStatusEqualTo(auditStatus);
            }
            if (!StringUtil.isEmpty(createBegin)) {//上线时间
                sourceNicheCustomCriteria.andUpDateGreaterThanOrEqualTo(sdf.parse(createBegin + " 00:00:00"));
            }
            if (!StringUtil.isEmpty(createEnd)) {
                sourceNicheCustomCriteria.andUpDateLessThanOrEqualTo(sdf.parse(createEnd + " 23:59:59"));
            }
            if (!StringUtil.isEmpty(signBegin)) {//报名时间
                sourceNicheCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(signBegin + " 00:00:00"));
            }
            if (!StringUtil.isEmpty(signEnd)) {
                sourceNicheCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(signEnd + " 23:59:59"));
            }


            sourceNicheCustomExample.setLimitStart(page.getLimitStart());
            sourceNicheCustomExample.setLimitSize(page.getLimitSize());
            totalCount = sourceNicheService.sourceNichecountByExample(sourceNicheCustomExample);
            dataList = sourceNicheService.sourceNicheCustomselectByExample(sourceNicheCustomExample);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    @RequestMapping(value = "resourceLocationManagement/watchProduct.shtml")
    public String watchProduct(Model model, HttpServletRequest request) {
        String sourceId = request.getParameter("sourceId");//89的资源位ID
        if (!StringUtil.isEmpty(sourceId)) {
            //资源位
            SourceNicheCustom sourceNicheCustom = sourceNicheService.sourceNicheCustomselectByPrimaryKey(Integer.parseInt(sourceId));
            model.addAttribute("sourceNicheCustom", sourceNicheCustom);

            //资源位商品
            SourceNicheProductExample sourceNicheProductExample = new SourceNicheProductExample();
            com.jf.entity.SourceNicheProductExample.Criteria createCriteria = sourceNicheProductExample.createCriteria();
            createCriteria.andDelFlagEqualTo("0").andSourceNicheIdEqualTo(Integer.parseInt(sourceId));
            List<SourceNicheProductCustom> sourceNicheProductList = sourceNicheProductService.selectCustomByExample(sourceNicheProductExample);
            if ((sourceNicheProductList != null) && (sourceNicheProductList.size() > 0)) {
                model.addAttribute("sourceNicheProductList", sourceNicheProductList);
            } else {
                model.addAttribute("sourceNicheProductList", "-1");
            }


            //店铺故事
            ShopStoryExample shopStoryExample = new ShopStoryExample();
            shopStoryExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(sourceNicheCustom.getLinkId());
            List<ShopStory> shopStoryList = shopStoryService.selectByExample(shopStoryExample);
            if (shopStoryList != null && shopStoryList.size() > 0) {
                ShopStory shopStory = shopStoryList.get(0);
                model.addAttribute("shopStory", shopStory);


                //店铺故事详情
                ShopStoryDetailExample shopStoryDetailExample = new ShopStoryDetailExample();
                shopStoryDetailExample.createCriteria().andDelFlagEqualTo("0").andShopStoryIdEqualTo(shopStory.getId()).andMchtIdEqualTo(sourceNicheCustom.getLinkId());
                List<ShopStoryDetail> shopStoryDetailList = shopStoryDetailService.selectByExample(shopStoryDetailExample);
                if (shopStoryDetailList != null && shopStoryDetailList.size() > 0) {
                    model.addAttribute("shopStoryDetailList", shopStoryDetailList);

                }

            }
        }
        return "resourceLocationManagement/watchProduct";
    }


    @RequestMapping(value = "resourceLocationManagement/shopBatchRejection.shtml")
    public String shopBatchPass(Model model, HttpServletRequest request) {
        String ids = request.getParameter("ids");//89的资源位ID
        if (!StringUtil.isEmpty(ids)) {
            model.addAttribute("ids", ids);
        }
        return "resourceLocationManagement/shopBatchRejection";
    }

    //店铺驳回
    @ResponseBody
    @RequestMapping("/resourceLocationManagement/toShopBatchRejection.shtml")
    public Map<String, Object> toShopBatchRejection(HttpServletRequest request, String id) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        String ids = request.getParameter("ids");
        String auditRemarks = request.getParameter("auditRemarks");
        String canApply = request.getParameter("canApply");
        try {
            Date nowDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(nowDate);
            c.add(Calendar.DAY_OF_MONTH, 7);
            Date afterTime = c.getTime();//驳回后七天内不可再次报名

            if (!StringUtils.isEmpty(ids)) {
                String[] split = ids.split(",");
                StaffBean staffBean = this.getSessionStaffBean(request);
                int staffId = Integer.valueOf(staffBean.getStaffID());
                for (int i = 0; i < split.length; i++) {

                    SourceNicheExample example = new SourceNicheExample();
                    example.createCriteria().andIdEqualTo(Integer.parseInt(split[i])).andDelFlagEqualTo("0");
                    List<SourceNiche> SourceNiches = sourceNicheService.selectByExample(example);
                    if (SourceNiches != null && SourceNiches.size() > 0) {
                        //店铺资源位驳回
                        SourceNiche sourceNiche = SourceNiches.get(0);
                        sourceNiche.setAuditBy(staffId);
                        sourceNiche.setAuditStatus("2");
                        sourceNiche.setAuditDate(new Date());
                        if (!StringUtil.isEmpty(auditRemarks)) {
                            sourceNiche.setAuditRemarks(auditRemarks);//驳回原因
                        }
                        if (!StringUtil.isEmpty(canApply)) {
                            sourceNiche.setCanApply(canApply);//驳回类型
                            if ("0".equals(canApply)) {
                                sourceNiche.setAuditDate(afterTime);
                            }
                        }
                        sourceNicheService.updateByPrimaryKeySelective(sourceNiche);

                        //店铺对应推荐商品的驳回
                        SourceNicheProductExample sourceNicheProductExample = new SourceNicheProductExample();
                        sourceNicheProductExample.createCriteria().andDelFlagEqualTo("0").andSourceNicheIdEqualTo(sourceNiche.getId());
                        List<SourceNicheProduct> sourceNicheProductList = sourceNicheProductService.selectByExample(sourceNicheProductExample);
                        for (SourceNicheProduct s : sourceNicheProductList) {
                            s.setDelFlag("1");
                            sourceNicheProductService.updateByPrimaryKey(s);
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "修改失败，请稍后重试");
            return resMap;
        }
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "操作成功");
        return resMap;
    }

    /**
     * 领劵中心-时间设置
     *
     * @param request
     * @return
     */
    @RequestMapping("/resourceLocationManagement/couponCenterTime.shtml")
    public ModelAndView couponCenterTime(HttpServletRequest request) {
        String rtPage = "/resourceLocationManagement/couponCenterTime";// 时间设置首页
        Map<String, Object> resMap = new HashMap<String, Object>();
        String pagetype = request.getParameter("pagetype");
        if (!StringUtil.isEmpty(pagetype)) {
            resMap.put("pagetype", pagetype);
        }

        return new ModelAndView(rtPage, resMap);
    }

    /**
     * 领劵中心-时间设置数据
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/resourceLocationManagement/couponCenterTimeData.shtml")
    @ResponseBody
    public Map<String, Object> couponCenterTimeData(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<>();
        List<CouponCenterTime> dataList = null;
        Integer totalCount = 0;
        try {
            CouponCenterTimeExample couponCenterTimeExample = new CouponCenterTimeExample();
            CouponCenterTimeExample.Criteria criteria = couponCenterTimeExample.createCriteria();
            criteria.andDelFlagEqualTo("0");
            couponCenterTimeExample.setOrderByClause("status desc ,start_hours asc ,start_min asc");
            couponCenterTimeExample.setLimitStart(page.getLimitStart());
            couponCenterTimeExample.setLimitSize(page.getLimitSize());
            totalCount = couponCenterTimeService.countByExample(couponCenterTimeExample);
            dataList = couponCenterTimeService.selectByExample(couponCenterTimeExample);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        resMap.put("Total", totalCount);
        return resMap;
    }

    /**
     * 领劵中心-编辑时间点
     * @param request
     * @return
     */
    @RequestMapping(value = "/resourceLocationManagement/editCouponCenterTime.shtml")
    public ModelAndView editCouponCenterTime(HttpServletRequest request) {
        String rtPage = "/resourceLocationManagement/editCouponCenterTime";// 重定向
        Map<String, Object> resMap = new HashMap<String, Object>();

        if(!StringUtils.isEmpty(request.getParameter("id"))){
            String id = request.getParameter("id");
            resMap.put("id", id);
            CouponCenterTime couponCenterTime = couponCenterTimeService.selectByPrimaryKey(Integer.parseInt(id));
            resMap.put("couponCenterTime", couponCenterTime);
        }

        return new ModelAndView(rtPage, resMap);

    }

    /**
     * 领劵中心-保存编辑时间点
     * @param request
     * @return
     */
    @RequestMapping(value = "/resourceLocationManagement/saveEditCouponCenterTime.shtml")
    @ResponseBody
    public Map<String, Object> saveEditCouponCenterTime(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("returnCode", "0000");
        resMap.put("returnMsg", "成功");
        try{
            String id = request.getParameter("id");
            String startHours = request.getParameter("startHours");
            String startMin = request.getParameter("startMin");
            String continueHours = request.getParameter("continueHours");
            String continueMin = request.getParameter("continueMin");
			CouponCenterTimeExample couponCenterTimeExample = new CouponCenterTimeExample();
			couponCenterTimeExample.createCriteria().andDelFlagEqualTo("0").andStartHoursEqualTo(startHours).andStartMinEqualTo(startMin);
			List<CouponCenterTime> couponCenterTimes = couponCenterTimeService.selectByExample(couponCenterTimeExample);
			if(couponCenterTimes!=null && couponCenterTimes.size()>0 && StringUtils.isEmpty(id)){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "该时间点已存在，请重新选择");
				return resMap;
			}
			if(couponCenterTimes!=null && couponCenterTimes.size()>0 && !StringUtils.isEmpty(id) && !couponCenterTimes.get(0).getId().equals(Integer.parseInt(id))){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "该时间点已存在，请重新选择");
				return resMap;
			}
			CouponCenterTime couponCenterTime = new CouponCenterTime();
            if(!StringUtils.isEmpty(id)){
                couponCenterTime=couponCenterTimeService.selectByPrimaryKey(Integer.parseInt(id));
            }
            couponCenterTime.setStartHours(startHours);
            couponCenterTime.setStartMin(startMin);
            couponCenterTime.setContinueHours(continueHours);
            couponCenterTime.setContinueMin(continueMin);
            Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
            couponCenterTimeService.saveEditCouponCenterTime(couponCenterTime,staffID);

        }catch (Exception e) {
            resMap.put("returnCode", "4004");
            resMap.put("returnMsg", "失败");
        }
        return resMap;
    }

	/**
	 * 领劵中心-修改时间点状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/resourceLocationManagement/updateStatusCouponCenterTime.shtml")
	@ResponseBody
	public Map<String, Object> updateStatusCouponCenterTime(HttpServletRequest request){
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if(StringUtil.isEmpty(request.getParameter("id")) && StringUtil.isEmpty(request.getParameter("status"))){
				throw new ArgException("参数异常");
			}
			Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id = Integer.valueOf(request.getParameter("id"));
			CouponCenterTime couponCenterTime = new CouponCenterTime();
			couponCenterTime.setId(id);
			couponCenterTime.setStatus(request.getParameter("status"));
			couponCenterTime.setUpdateBy(staffID);
			couponCenterTime.setUpdateDate(new Date());
			couponCenterTimeService.updateByPrimaryKeySelective(couponCenterTime);
		} catch (ArgException e){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 领劵中心-删除时间点
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/resourceLocationManagement/delCouponCenterTime.shtml")
	@ResponseBody
	public Map<String, Object> delCouponCenterTime(HttpServletRequest request){
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			if(StringUtil.isEmpty(request.getParameter("id"))){
				throw new ArgException("参数异常");
			}
			Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id = Integer.valueOf(request.getParameter("id"));
			CouponCenterTime couponCenterTime = new CouponCenterTime();
			couponCenterTime.setId(id);
			couponCenterTime.setDelFlag("1");
			couponCenterTime.setUpdateBy(staffID);
			couponCenterTime.setUpdateDate(new Date());
			couponCenterTimeService.updateByPrimaryKeySelective(couponCenterTime);
		} catch (ArgException e){
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}



	/**
	 * 查看优惠券
	 * @param request
	 * @return
	 */
		@RequestMapping("/resourceLocationManagement/checkOutCoupons.shtml")
		public ModelAndView checkOutCoupons(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			String rtPage = "";
			String pagetype = request.getParameter("pagetype");
			if("1091".equals(pagetype)){//查看优惠券(平台劵)
				 Date nowDate = new Date();
				 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				 resMap.put("nowDate", df.format(nowDate));
				 rtPage = "/resourceLocationManagement/coupon/platformCouponsList";
				 CouponCenterTimeExample couponCenterTimeExample = new CouponCenterTimeExample();
				 couponCenterTimeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");//未删除的启用状态
				 couponCenterTimeExample.setOrderByClause(" start_hours ,start_min");
				List<CouponCenterTime> couponCenterTimes = couponCenterTimeService.selectByExample(couponCenterTimeExample);
				resMap.put("couponCenterTimes", couponCenterTimes);

			}else if("1092".equals(pagetype)){//查看优惠券(商品劵)
				 rtPage = "/resourceLocationManagement/coupon/commodityCouponsList";

			}else if("1093".equals(pagetype)){//查看优惠券(店铺劵)
				 rtPage = "/resourceLocationManagement/coupon/shopCouponsList";

			}else if("1094".equals(pagetype)){//查看优惠券(特卖劵)
				 rtPage = "/resourceLocationManagement/coupon/SpecialSaleCouponsList";

			}
			return new ModelAndView(rtPage,resMap);
		}

		  //查看优惠券(平台劵)
		  @RequestMapping("/resourceLocationManagement/platformCouponsListData.shtml")
		  @ResponseBody
		  public  Map<String, Object> platformCouponsListData(HttpServletRequest request, Page page ) {
		   Map<String, Object> resMap = new HashMap<String, Object>();
		   Integer totalCount = 0;
		   try {
		    CouponCustomExample couponCustomExample=new CouponCustomExample();
		    CouponCustomExample.CouponCustomCriteria couponCustomCriteria=couponCustomExample.createCriteria();
		    couponCustomCriteria.andDelFlagEqualTo("0").andRangEqualTo("1");
		    couponCustomExample.setOrderByClause("rec_begin_date desc");

		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    if(!StringUtil.isEmpty(request.getParameter("recBeginDate")) && !StringUtil.isEmpty(request.getParameter("couponCenterTime")) ){
		     couponCustomCriteria.andRecBeginDateEqualTo(dateFormat.parse(request.getParameter("recBeginDate")+" "+request.getParameter("couponCenterTime")+":00"));
		    }

		    if(StringUtil.isEmpty(request.getParameter("recBeginDate")) && !StringUtil.isEmpty(request.getParameter("couponCenterTime")) ){
		     couponCustomCriteria.andBeginDateCenterTimeEqualTo(request.getParameter("couponCenterTime"));
		    }

		    if(!StringUtil.isEmpty(request.getParameter("recBeginDate")) && StringUtil.isEmpty(request.getParameter("couponCenterTime")) ){
		     couponCustomCriteria.andBeginDateYMDEqualTo(request.getParameter("recBeginDate"));
		    }

		    if(!StringUtil.isEmpty(request.getParameter("preferentialType"))){//类型
		     couponCustomCriteria.andPreferentialTypeEqualTo(request.getParameter("preferentialType"));
		    }
		    totalCount = couponService.countCouponCustomByExample(couponCustomExample);
		    couponCustomExample.setLimitStart(page.getLimitStart());
		    couponCustomExample.setLimitSize(page.getLimitSize());
		    List<CouponCustom> couponCustoms=couponService.selectCouponCustomByExample(couponCustomExample);
		    resMap.put("Rows", couponCustoms);
		    resMap.put("Total", totalCount);
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		   return resMap;
		  }


		//查看优惠券(商品劵)
		@RequestMapping("/resourceLocationManagement/commodityCouponsListData.shtml")
		@ResponseBody
		public  Map<String, Object> commodityCouponsListData(HttpServletRequest request, Page page ) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			Integer totalCount = 0;
			try {
				CouponCustomExample couponCustomExample=new CouponCustomExample();
				CouponCustomExample.CouponCustomCriteria couponCustomCriteria=couponCustomExample.createCriteria();
				couponCustomCriteria.andDelFlagEqualTo("0").andRangEqualTo("4").andCouponTypeEqualTo("4");//商品券
				couponCustomExample.setOrderByClause("rec_begin_date desc");
				String seachStatus = request.getParameter("seachStatus");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				couponCustomCriteria.andIsIntegralTurntableEqualTo(request.getParameter("isIntegralTurntable"));
				if(!StringUtil.isEmpty(request.getParameter("recBeginDate"))){
					couponCustomCriteria.andRecBeginDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("recBeginDate")+" 00:00:00"));
				}
				if(!StringUtil.isEmpty(request.getParameter("recEndDate"))){
					couponCustomCriteria.andRecBeginDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("recEndDate")+" 23:59:59"));
				}
				if(!StringUtil.isEmpty(request.getParameter("recDate"))){
					couponCustomCriteria.andRecBeginDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("recDate")+" 00:00:00"));
					couponCustomCriteria.andRecEndDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("recDate")+" 23:59:59"));
				}
				if(!StringUtil.isEmpty(request.getParameter("moneyMin"))){
					couponCustomCriteria.andMoneyGreaterThanOrEqualTo(new BigDecimal(request.getParameter("moneyMin")));
				}
				if(!StringUtil.isEmpty(request.getParameter("moneyMax"))){
					couponCustomCriteria.andMoneyLessThanOrEqualTo(new BigDecimal(request.getParameter("moneyMax")));
				}
				if(!StringUtil.isEmpty(request.getParameter("moneyLessThan"))){
					couponCustomCriteria.andMoneyLessThan(new BigDecimal(request.getParameter("moneyLessThan")));
				}
				if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
					couponCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
				}
				if(!StringUtil.isEmpty(request.getParameter("mchtName"))){
					couponCustomCriteria.andMchtNameLike(request.getParameter("mchtName"));
				}
				if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
					couponCustomCriteria.andProductBrandNameEqualTo(request.getParameter("productBrandName"));
				}

				if(!StringUtil.isEmpty(seachStatus)){
					if("1".equals(seachStatus)){//未开始
						couponCustomCriteria.andRecBeginDateGreaterThan(new Date());
					}
					if("2".equals(seachStatus)){//活动中
						couponCustomCriteria.andRecBeginDateLessThan(new Date());
						couponCustomCriteria.andRecEndDateGreaterThan(new Date());
					}
					if("3".equals(seachStatus)){//已结束
						couponCustomCriteria.andRecEndDateLessThanOrEqualTo(new Date());
					}
				}
				totalCount = couponService.countCouponCustomByExample(couponCustomExample);
				couponCustomExample.setLimitStart(page.getLimitStart());
				couponCustomExample.setLimitSize(page.getLimitSize());
				List<CouponCustom> couponCustoms=couponService.selectCouponCustomByExample(couponCustomExample);
				resMap.put("Rows", couponCustoms);
				resMap.put("Total", totalCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resMap;
		}




		//查看优惠券(店铺券)
		@RequestMapping("/resourceLocationManagement/shopCouponsListData.shtml")
		@ResponseBody
		public  Map<String, Object> shopCouponsListData(HttpServletRequest request, Page page ) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			Integer totalCount = 0;
			try {
				CouponCustomExample couponCustomExample=new CouponCustomExample();
				CouponCustomExample.CouponCustomCriteria couponCustomCriteria=couponCustomExample.createCriteria();
				couponCustomCriteria.andDelFlagEqualTo("0").andRangEqualTo("4").andCouponTypeEqualTo("1");//店铺券
				couponCustomExample.setOrderByClause("create_date desc");
				String seachStatus = request.getParameter("seachStatus");//状态
				String preferentialType = request.getParameter("preferentialType");//优惠类型
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(!StringUtil.isEmpty(request.getParameter("recBeginDate"))){
					couponCustomCriteria.andRecBeginDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("recBeginDate")+" 00:00:00"));
				}
				if(!StringUtil.isEmpty(request.getParameter("recEndDate"))){
					couponCustomCriteria.andRecBeginDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("recEndDate")+" 23:59:59"));
				}

				if(!StringUtil.isEmpty(seachStatus)){
					if("1".equals(seachStatus)){//未开始
						couponCustomCriteria.andRecBeginDateGreaterThan(new Date());
					}
					if("2".equals(seachStatus)){//活动中
						couponCustomCriteria.andRecBeginDateLessThan(new Date());
						couponCustomCriteria.andRecEndDateGreaterThan(new Date());
					}
					if("3".equals(seachStatus)){//已结束
						couponCustomCriteria.andRecEndDateLessThanOrEqualTo(new Date());
					}
				}

				if(!StringUtil.isEmpty(preferentialType)){
					if("1".equals(preferentialType)){//满减
						couponCustomCriteria.andPreferentialTypeEqualTo("1");
					}
					if("2".equals(preferentialType)){//折扣
						couponCustomCriteria.andPreferentialTypeEqualTo("2");
					}
				}

				totalCount = couponService.countCouponCustomByExample(couponCustomExample);
				couponCustomExample.setLimitStart(page.getLimitStart());
				couponCustomExample.setLimitSize(page.getLimitSize());
				List<CouponCustom> couponCustoms=couponService.selectCouponCustomByExample(couponCustomExample);
				resMap.put("Rows", couponCustoms);
				resMap.put("Total", totalCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resMap;
		}

		//查看优惠券(特卖券)
		@RequestMapping("/resourceLocationManagement/SpecialSaleCouponsListData.shtml")
		@ResponseBody
		public  Map<String, Object> SpecialSaleCouponsListData(HttpServletRequest request, Page page ) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			Integer totalCount = 0;
			try {

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				ActivityAreaCustomExample activityAreaCustomExample = new ActivityAreaCustomExample();
				ActivityAreaCustomCriteria createCriteria = activityAreaCustomExample.createCriteria();
				createCriteria.andDelFlagEqualTo("0").andSourceEqualTo("2").andPreferentialTypeEqualTo("1");//特卖活动,并且属于优惠券
				createCriteria.andCooAuditStatusEqualToPass();//活动审核状态通过
				activityAreaCustomExample.setOrderByClause("activity_begin_time desc");
				if(!StringUtil.isEmpty(request.getParameter("recBeginDate"))){
					createCriteria.andActivityBeginTimeGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("recBeginDate")+" 00:00:00"));
				}
				if(!StringUtil.isEmpty(request.getParameter("recEndDate"))){
					createCriteria.andActivityBeginTimeLessThanOrEqualTo(dateFormat.parse(request.getParameter("recEndDate")+" 23:59:59"));
				}

				activityAreaCustomExample.setLimitStart(page.getLimitStart());
				activityAreaCustomExample.setLimitSize(page.getLimitSize());
			     totalCount = activityAreaService.countByCustomExample(activityAreaCustomExample);
				List<ActivityAreaCustom> activityAreaCustoms = activityAreaService.selectByCustomExample(activityAreaCustomExample);


			    List<Integer> couponIds = new ArrayList<>();
				for(ActivityAreaCustom aac : activityAreaCustoms){
					String[] split = aac.getPreferentialIdGroup().split(",");
					 for (int i = 0; i < split.length; i++) {
				    	 couponIds.add(Integer.parseInt(split[i]));
					}
				}

			 	CouponExample couponExample = new CouponExample();
				couponExample.createCriteria().andDelFlagEqualTo("0").andIdIn(couponIds);
				couponExample.setOrderByClause("id");
				List<CouponCustom> coupons = couponService.selectCouponCustomByExample(couponExample );


					for(ActivityAreaCustom aac : activityAreaCustoms){
						List<String> asList = Arrays.asList(aac.getPreferentialIdGroup().split(","));
						List<CouponCustom>  couponList = new ArrayList<>();
						for(CouponCustom cc : coupons){
						 if(asList.contains(cc.getId()+"")){
							 couponList.add(cc);
						}
					}
						aac.setCouponList(couponList);
				}


				//将优惠券设置到特卖活动中
/*				for(ActivityAreaCustom aac : activityAreaCustoms){
				     String[] split = aac.getPreferentialIdGroup().split(",");
				     List<Integer> couponIds = new ArrayList<>();
				     for (int i = 0; i < split.length; i++) {
				    	 couponIds.add(Integer.parseInt(split[i]));
					}
				 	CouponExample couponExample = new CouponExample();
					couponExample.createCriteria().andDelFlagEqualTo("0").andIdIn(couponIds);
					couponExample.setOrderByClause("id");
					List<CouponCustom> coupons = couponService.selectCouponCustomByExample(couponExample );
					if(coupons!=null && coupons.size()>0){
						aac.setCouponList(coupons);
					}
				}*/

				resMap.put("Rows", activityAreaCustoms);
				resMap.put("Total", totalCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resMap;
		}

		/**
		 * tab管理
		 * @param request
		 * @return
		 */
		@RequestMapping("/resourceLocationManagement/tabAdministration.shtml")
		public ModelAndView tabAdministration(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			String rtPage = "/resourceLocationManagement/tabAdministrationList";
			return new ModelAndView(rtPage,resMap);
		}

		//tab管理的数据
		@RequestMapping("/resourceLocationManagement/tabAdministrationListData.shtml")
		@ResponseBody
		public  Map<String, Object> tabAdministrationListData(HttpServletRequest request, Page page ) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			Integer totalCount = 0;
			try {

				CouponCenterBottomNavigationExample couponCenterBottomNavigationExample = new CouponCenterBottomNavigationExample();
				couponCenterBottomNavigationExample.createCriteria().andDelFlagEqualTo("0");
				couponCenterBottomNavigationExample.setOrderByClause("seq_no");
				List<CouponCenterBottomNavigationCustom> couponCenterBottomNavigations = couponCenterBottomNavigationService.selectByCouponCustomExample(couponCenterBottomNavigationExample );

				resMap.put("Rows", couponCenterBottomNavigations);
				resMap.put("Total", totalCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resMap;
		}

		//编辑Tab
		@RequestMapping("/resourceLocationManagement/changeEditTab.shtml")
		public ModelAndView changeEditTab(HttpServletRequest request) {
			String rtPage = "/resourceLocationManagement/changeEditTab";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String id = request.getParameter("id");
			if(!StringUtil.isEmpty(id)){
				resMap.put("tabId", id);
				CouponCenterBottomNavigation couponCenterBottomNavigation = couponCenterBottomNavigationService.selectByPrimaryKey(Integer.parseInt(id));
				String linkType = couponCenterBottomNavigation.getLinkType();
				if(StringUtil.isEmpty(linkType)){
					couponCenterBottomNavigation.setLinkType("0");
				}
				 if ("13".equals(couponCenterBottomNavigation.getLinkType())){//将店铺id 转成code 存入
					 MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(couponCenterBottomNavigation.getLinkValue()));
					if(mchtInfo!=null){
						couponCenterBottomNavigation.setLinkValue(mchtInfo.getMchtCode());
					}
				}
				 if ("3".equals(couponCenterBottomNavigation.getLinkType())){//将id转成code 存入
					 Product productCustoms = productService.selectByPrimaryKey(Integer.parseInt(couponCenterBottomNavigation.getLinkValue()));
						if(productCustoms!=null){
							couponCenterBottomNavigation.setLinkValue(productCustoms.getCode());
						}
				 }


				resMap.put("couponCenterBottomNavigation", couponCenterBottomNavigation);


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

			//二级分类下的一级类目和二级类目
			if( "16".equals(couponCenterBottomNavigation.getLinkType()) || "18".equals(couponCenterBottomNavigation.getLinkType()) ||
					"20".equals(couponCenterBottomNavigation.getLinkType()) || "22".equals(couponCenterBottomNavigation.getLinkType()) || "24".equals(couponCenterBottomNavigation.getLinkType()) ||
					"26".equals(couponCenterBottomNavigation.getLinkType())  || "28".equals(couponCenterBottomNavigation.getLinkType()) || "30".equals(couponCenterBottomNavigation.getLinkType()) ||
					"32".equals(couponCenterBottomNavigation.getLinkType()) || "34".equals(couponCenterBottomNavigation.getLinkType())){
				ProductTypeExample productTypeExamples = new ProductTypeExample();
				productTypeExamples.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
				List<ProductType> productType = productTypeService.selectByExample(productTypeExample );
				resMap.put("productTypes", productType);
				String sonIds = couponCenterBottomNavigation.getLinkValue();
				String[] split = sonIds.split(",");
				if(split.length>0&&split!=null){
				ProductType partenType = productTypeService.selectByPrimaryKey(Integer.parseInt(split[0]));
				resMap.put("partenId", partenType.getParentId());
				resMap.put("sonIds", sonIds);
				}
			}

			}
			return new ModelAndView(rtPage,resMap);
		}



		/**
		 * Tab保存前的检查
		 * @param request
		 * @param response
		 * @param paramMap
		 * @return
		 */
		public  Map<String, String> MngAdMngCheckSubmit( CouponCenterBottomNavigation ccbn) {
			Map<String, String> resMap = new HashMap<String, String>();

			//判断Tab的名字是否重复
			CouponCenterBottomNavigationExample Tabexample = new CouponCenterBottomNavigationExample();
			Tabexample.createCriteria().andDelFlagEqualTo("0").andNameEqualTo(ccbn.getName());
			int nameCount = couponCenterBottomNavigationService.countByExample(Tabexample );
			List<CouponCenterBottomNavigation> tabNavigationList = couponCenterBottomNavigationService.selectByExample(Tabexample);
			for(CouponCenterBottomNavigation couponTab : tabNavigationList){
				if(couponTab.getId()!=ccbn.getId()){
					resMap.put("YN", "0");
					resMap.put("msg", "您输入的名称已存在,请更改！");
					return resMap;
				}
			}

			if ("1".equals(ccbn.getLinkType())){
				ActivityAreaExample activityAreaExample =new ActivityAreaExample();
				ActivityAreaExample.Criteria activityAreaCriteria=activityAreaExample.createCriteria();
				activityAreaCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(ccbn.getLinkValue()));
				Integer totalCount=activityAreaService.countByExample(activityAreaExample);
				if (totalCount==0){
					resMap.put("YN", "0");
					resMap.put("msg", "会场不存在！");
					return resMap;
				}
			}
			if ("2".equals(ccbn.getLinkType())){
				ActivityExample activityExample =new ActivityExample();
				ActivityExample.Criteria activityCriteria=activityExample.createCriteria();
				activityCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(ccbn.getLinkValue()));
				Integer totalCount=activityService.countByExample(activityExample);
				if (totalCount==0){
					resMap.put("YN", "0");
					resMap.put("msg", "活动不存在或未审核通过！");
					return resMap;
				}
			}
			if ("3".equals(ccbn.getLinkType())){
				ProductCustomExample productCustomExample = new ProductCustomExample();
				productCustomExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(ccbn.getLinkValue());
				List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
				if(productCustoms==null || productCustoms.size()<=0){
					resMap.put("YN", "0");
					resMap.put("msg", "商品不存在,或已下架");
					return resMap;

			 }
			}
			if ("4".equals(ccbn.getLinkType())){//自建页面
							CustomPage customPage = customPageService.selectByPrimaryKey(Integer.parseInt(ccbn.getLinkValue()));
							if(customPage==null || !"0".equals(customPage.getDelFlag())){
								resMap.put("YN", "0");
								resMap.put("msg", "自建页面不存在");
								return resMap;

							}
						}
			if ("9".equals(ccbn.getLinkType())){//自建页面  https:// http://
				String urlHttp = ccbn.getLinkValue().substring(0, 7);
				String urlHttps = ccbn.getLinkValue().substring(0, 8);
				if(!"http://".equals(urlHttp) && !"https://".equals(urlHttps)){
					resMap.put("YN", "0");
					resMap.put("msg", "请输入正确的url连接格式");
					return resMap;
				}

			}


			if ("11".equals(ccbn.getLinkType())){
				CouponExample example = new CouponExample();
				CouponExample.Criteria c = example.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andIdEqualTo(Integer.parseInt(ccbn.getLinkValue()));
				List<Coupon> coupons = couponService.selectByExample(example);
				if(coupons==null || coupons.size() == 0){
					resMap.put("YN", "0");
					resMap.put("msg", "优惠券ID不可用");
					return resMap;
				}
			}
			if ("13".equals(ccbn.getLinkType())){
				MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
				mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(ccbn.getLinkValue());
				Integer totalCount = mchtInfoService.countByExample(mchtInfoExample );
				if (totalCount==0){
					resMap.put("YN", "0");
					resMap.put("msg", "商家店铺不存在！");
					return resMap;

				}
			}
			if (  "19".equals(ccbn.getLinkType())  || "21".equals(ccbn.getLinkType()) || "23".equals(ccbn.getLinkType())  || "25".equals(ccbn.getLinkType())
					|| "27".equals(ccbn.getLinkType()) || "29".equals(ccbn.getLinkType()) || "31".equals(ccbn.getLinkType())){
				String linkUrl = ccbn.getLinkValue();
				String[] split = linkUrl.split(",");
				int index;
				for (int i = 0; i < split.length; i++) {
					ProductBrandExample productBrandExample = new ProductBrandExample();
					productBrandExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(split[i]));
					List<ProductBrand> productBrands = productBrandService.selectByExample(productBrandExample );
					if(productBrands==null || productBrands.size()<=0){
						resMap.put("YN", "0");
						resMap.put("msg", "品牌ID"+split[i]+"不存在！");
						return resMap;
					}
				}
			}

			resMap.put("YN", "1");
			return resMap;
		 }


		//保存编辑Tab
		@ResponseBody
		@RequestMapping(value = "/resourceLocationManagement/saveChangeEditTab.shtml")
		public Map<String, Object> saveChangeEditTab(HttpServletRequest request, CouponCenterBottomNavigation couponCenterBottomNavigation) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			try {
					String id = request.getParameter("id");
					if(!StringUtil.isEmpty(id)){

						Map<String, String> mngAdMngCheckSubmit = MngAdMngCheckSubmit(couponCenterBottomNavigation);
						if("0".equals(mngAdMngCheckSubmit.get("YN"))){
							resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
							resMap.put(this.JSON_RESULT_MESSAGE,mngAdMngCheckSubmit.get("msg") );
							resMap.put("returnCode", "4004");
							resMap.put("returnMsg", mngAdMngCheckSubmit.get("msg"));
						}else{
							couponCenterBottomNavigation.setUpdateBy(staffId);
							couponCenterBottomNavigation.setUpdateDate(new Date());

							 if ("13".equals(couponCenterBottomNavigation.getLinkType())){//将店铺code转成主键ID 存入
								String mcode = couponCenterBottomNavigation.getLinkValue();
								MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
								mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(mcode);
								 List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.selectByExample(mchtInfoExample);
								if(mchtInfoCustoms.size()>0&&mchtInfoCustoms!=null){
									MchtInfoCustom mchtInfoCustom = mchtInfoCustoms.get(0);
									couponCenterBottomNavigation.setLinkValue(mchtInfoCustom.getId()+"");
								}
							}

							 if ("3".equals(couponCenterBottomNavigation.getLinkType())){//将商品code转成主键ID 存入
								 ProductCustomExample productCustomExample = new ProductCustomExample();
									productCustomExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(couponCenterBottomNavigation.getLinkValue());
									List<ProductCustom> productCustoms = productService.selectProductCustomByExample(productCustomExample);
									if(productCustoms.size()>0&&productCustoms!=null){
										ProductCustom productCustom = productCustoms.get(0);
										couponCenterBottomNavigation.setLinkValue(productCustom.getId()+"");
									}
							 }

							couponCenterBottomNavigationService.updateByPrimaryKeySelective(couponCenterBottomNavigation);
						}

					}


			} catch (Exception e) {
				e.printStackTrace();
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "保存失败，请稍后重试");
			}

			return resMap ;
		}

		//修改Tab排序值
		@ResponseBody
		@RequestMapping(value = "/resourceLocationManagement/updateTabSeqNo.shtml")
		public Map<String, Object> updateTabSeqNo(HttpServletRequest request, Integer id, Integer seqNo) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("code", "200");
			resMap.put("msg", "修改成功");
			try {
				couponCenterBottomNavigationService.exchangeSeq( id,  seqNo);
			} catch (Exception e) {
				resMap.put("code", "400");
				resMap.put("msg", "修改失败");
			}

			return resMap;
		}

		//修改Tab的状态启用
		@ResponseBody
		@RequestMapping(value = "/resourceLocationManagement/changeEnable.shtml")
		public Map<String, Object> changeEnable(HttpServletRequest request, Integer id ) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("code", "200");
			resMap.put("msg", "修改成功");
			try {
				CouponCenterBottomNavigationExample couponCenterBottomNavigationExample = new CouponCenterBottomNavigationExample();
				couponCenterBottomNavigationExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
				CouponCenterBottomNavigation couponCenterBottomNavigation = new CouponCenterBottomNavigation();
				couponCenterBottomNavigation.setStatus("1");
				couponCenterBottomNavigationService.updateByExampleSelective(couponCenterBottomNavigation, couponCenterBottomNavigationExample);
			} catch (Exception e) {
				resMap.put("code", "400");
				resMap.put("msg", "修改失败");
			}
			return resMap;
		}


		//修改Tab的状态关闭
		@ResponseBody
		@RequestMapping(value = "/resourceLocationManagement/changeClose.shtml")
		public Map<String, Object> changeClose(HttpServletRequest request, Integer id ) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("code", "200");
			resMap.put("msg", "修改成功");
			try {
				CouponCenterBottomNavigationExample couponCenterBottomNavigationExample = new CouponCenterBottomNavigationExample();
				couponCenterBottomNavigationExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(id);
				CouponCenterBottomNavigation couponCenterBottomNavigation = new CouponCenterBottomNavigation();
				couponCenterBottomNavigation.setStatus("0");
				couponCenterBottomNavigationService.updateByExampleSelective(couponCenterBottomNavigation, couponCenterBottomNavigationExample);
			} catch (Exception e) {
				resMap.put("code", "400");
				resMap.put("msg", "修改失败");
			}
			return resMap;
		}

	/**
	 * 权重明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/weightDetail.shtml")
	public ModelAndView weightDetail(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		String linkId = request.getParameter("linkId");
		if(!StringUtil.isEmpty(linkId)){
			SourceNicheExample sourceNicheExample = new SourceNicheExample();
			sourceNicheExample.createCriteria().andDelFlagEqualTo("0").andLinkIdEqualTo(Integer.parseInt(linkId)).andStatusEqualTo("0")
					.andAuditStatusEqualTo("1").andTypeIn(Arrays.asList("1","4","5","6","7","8","9"));
			sourceNicheExample.setOrderByClause(" weight_update_date ASC");
			List<SourceNiche> sourceNiches = sourceNicheService.selectByExample(sourceNicheExample);
			HashMap<String,Object> map = sourceNicheService.selectWeightDetail(sourceNiches.get(0).getId());
            m.addObject("map", map);
		}
		m.setViewName("/resourceLocationManagement/weightDetail");
		return m;
	}


	/**
	 * 商品统计 在线商品
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/onlineProductDataStatistics.shtml")
	public ModelAndView onlineProductDataStatistics(HttpServletRequest request, Integer activitybrandgroupid) {
		String pagetype = request.getParameter("pagetype");
		ModelAndView m = new ModelAndView();
		m.addObject("pagetype",pagetype);
		m.setViewName("/resourceLocationManagement/dataStatistics/onlineProductDataStatistics");

        //九大类目
        ProductTypeExample example = new ProductTypeExample();
        example.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
        List<ProductType> productTypes = productTypeService.selectByExample(example );
        m.addObject("productTypes", productTypes);

        //昨天的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-1);
        Date yestertime = calendar.getTime();
        String yesterday = sdf.format(yestertime);
        m.addObject("yesterday",yesterday);

        //今天日期
        Date nowDate = new Date();
        String today = sdf.format(nowDate);
        m.addObject("today",today);
        return m;
	}


    /**
     * 商品统计 在线商品的数据
     * @param request
     * @return
     */
	@RequestMapping("/resourceLocationManagement/onlineProductDataStatisticsList.shtml")
    @ResponseBody
    public Map<String, Object> onlineProductDataStatisticsList(HttpServletRequest request, Page page) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<TypeColumnPvStatisticalCustom> dataList = null;
        Integer totalCount = 0;
        try {
			Map<String, Object> map = new HashMap<String, Object>();
			SourceNicheCustomExample sourceNicheCustomExample = new SourceNicheCustomExample();
			SourceNicheCustomCriteria criteria = sourceNicheCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andpStatusEqualTo("1");//商品处于上线状态
			criteria.andStatusEqualTo("0");//活动状态为添加

			String pagetype = request.getParameter("pagetype");
			if(!StringUtil.isEmpty(pagetype)){
				if("1101".equals(pagetype)){
					map.put("columnType",15);
					map.put("type",1);
					criteria.andTypeEqualTo("1");
				}
				if("1201".equals(pagetype)){
					map.put("columnType",16);
					map.put("type",2);
					criteria.andTypeEqualTo("2");
				}
				if("1301".equals(pagetype)){
					map.put("columnType",18);
					map.put("type",3);
					criteria.andTypeEqualTo("3");
				}
				if("1401".equals(pagetype)){
					map.put("columnType",20);
					map.put("type",4);
					criteria.andTypeEqualTo("4");
				}
				if("1501".equals(pagetype)){
					map.put("columnType",21);
					map.put("type",5);
					criteria.andTypeEqualTo("5");
				}
				if("1601".equals(pagetype)){
					map.put("columnType",29);
					map.put("type",6);
					criteria.andTypeEqualTo("6");
				}
				if("1701".equals(pagetype)){
					map.put("columnType",22);
					map.put("type",7);
					criteria.andTypeEqualTo("7");
				}
				if("1801".equals(pagetype)){
					map.put("columnType",23);
					map.put("type",8);
					criteria.andTypeEqualTo("8");
				}
				if("1901".equals(pagetype)) {
					map.put("columnType", 24);
					map.put("type", 9);
					criteria.andTypeEqualTo("9");
				}
				map.put("statisticType","1");
			}

			Object type = map.get("type");
			if(!type.equals(3)){
				criteria.andAuditStatusEqualTo("1");//审核通过
			}

			if(!StringUtil.isEmpty(request.getParameter("productId"))){//商品ID
				String productId = request.getParameter("productId");
				map.put("productId",productId);
				criteria.andCodesIn(productId);
			}

			if(!StringUtil.isEmpty(request.getParameter("productName"))){//商品名字
				String productName = request.getParameter("productName");
				map.put("productName","%"+productName+"%");
				criteria.andNameLike(productName);
			}

			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){//商家序号
				String mchtCode = request.getParameter("mchtCode");
				map.put("mchtCode",mchtCode);
				criteria.andMchtCodesIn(mchtCode);
			}

			if(!StringUtil.isEmpty(request.getParameter("mchtName"))){//店铺名称
				String mchtName = request.getParameter("mchtName");
				map.put("mchtName","%"+mchtName+"%");
				criteria.andMchtNameIn(mchtName);
			}

			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))){//类目
				String productTypeId = request.getParameter("productTypeId");
				map.put("productTypeId",Integer.parseInt(productTypeId));
				criteria.andProductTypeIdEuqalTo(Integer.parseInt(productTypeId));
			}

			String statisticsType = request.getParameter("statisticsType");
			String beginDate = "";
			String endDate = "";
			if(statisticsType.equals("1")){
				beginDate = request.getParameter("nowBeginDate");
				endDate = request.getParameter("nowEndDate");
			}else{
				beginDate = request.getParameter("beforeBeginDate");
				endDate = request.getParameter("beforeEndDate");
			}
			map.put("beginDate", beginDate+" 00:00:00");
			map.put("endDate", endDate+" 23:59:59");

			Integer limitSize = page.getLimitSize();
			Integer limitStart = page.getLimitStart();
			if(limitSize!=null && limitStart!=null){
				map.put("limitSize",limitSize);
				map.put("limitStart",limitStart);
			}

			sourceNicheCustomExample.setLimitSize(limitSize);
			sourceNicheCustomExample.setLimitStart(limitStart);

			if("2".equals(statisticsType) || "".equals(statisticsType)){//昨天之后
				dataList = typeColumnPvStatisticalService.selectDataStatisticsList(map);
			}else if("1".equals(statisticsType)){//当前时间
				dataList = typeColumnPvStatisticalService.selectNowDataStatisticsList(map);
			}
			totalCount = sourceNicheService.sourceNichecountByExample(sourceNicheCustomExample);
		} catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		resMap.put("Total", totalCount);
        resMap.put("Rows", dataList);
       // resMap.put("Total", totalCount);
        return resMap;
    }

	/**
	 * 商品统计 类目
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/categoryProductDataStatistics.shtml")
	public ModelAndView dataOfOnlineGoods1(HttpServletRequest request, Integer activitybrandgroupid) {
		String pagetype = request.getParameter("pagetype");
		Map<String, Object> resMap = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,-1);
		String beginDate = new SimpleDateFormat( "yyyy-MM-dd").format(calendar.getTime());
		Map<String,String> map = new HashMap<>();
		map.put("begin_date",beginDate);
		map.put("end_date",beginDate);
		map.put("pagetype",pagetype);
		return new ModelAndView("/resourceLocationManagement/dataStatistics/categoryProductDataStatistics",map);
	}

	/**
	 * 商品统计 类目(数据)
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/getCategoryProductDataStatisticsList.shtml")
	@ResponseBody
	public Map<String,Object> getCategoryProductDataStatisticsList(HttpServletRequest request) {
		String pagetype = request.getParameter("pagetype");
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			String beginDate = request.getParameter("begin_date");
			String endDate = request.getParameter("end_date");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE,-1);
			String beginDates = new SimpleDateFormat( "yyyy-MM-dd ").format(calendar.getTime());
			if(!StringUtil.isEmpty(beginDate) ){
				paramMap.put("beginDate", beginDate);
			}else{
				paramMap.put("beginDate", beginDates);
			}
			if(!StringUtil.isEmpty(endDate) ){
				paramMap.put("endDate", endDate);
			}else{
				paramMap.put("endDate", beginDates);
			}

			//判断pageType类型，获取相应的栏目Type进行查询
			if(org.apache.commons.lang.StringUtils.equals(pagetype,"1102")){//有好货(类目)
				paramMap.put("columnType","15");
			}else if(org.apache.commons.lang.StringUtils.equals(pagetype,"1202")){//每日好店(类目)
				paramMap.put("columnType","16");
			}
			List<TypeColumnPvStatisticalCustom> typeColumnPvStatisticalCustomList = typeColumnPvStatisticalService.getCategoryProductDataStatisticsList(paramMap);

			//记录合计数据
			TypeColumnPvStatisticalCustom totalTypeColumnPvStatistics = getList(null);
			for (TypeColumnPvStatisticalCustom typeColumnPvStatistical:typeColumnPvStatisticalCustomList) {
				getTotal(typeColumnPvStatistical,totalTypeColumnPvStatistics);
			}

			Map<Integer, TypeColumnPvStatisticalCustom> map = new HashMap<>();
			for (TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom:typeColumnPvStatisticalCustomList) {
				map.put(typeColumnPvStatisticalCustom.getProductType1Id(),typeColumnPvStatisticalCustom);
			}
			//获取全部一级类目
			ProductTypeExample productTypeExample = new ProductTypeExample();
			ProductTypeExample.Criteria criteria = productTypeExample.createCriteria();
			criteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
			productTypeExample.setOrderByClause(" id asc");
			List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);

			for (ProductType productType:productTypeList) {
				if(!map.containsKey(productType.getId())){
					TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom = getList(productType);
					typeColumnPvStatisticalCustomList.add(typeColumnPvStatisticalCustom);
				}
			}
			typeColumnPvStatisticalCustomList.add(totalTypeColumnPvStatistics);
			Collections.sort(typeColumnPvStatisticalCustomList, new Comparator<TypeColumnPvStatisticalCustom>() {
				@Override
				public int compare(TypeColumnPvStatisticalCustom p1, TypeColumnPvStatisticalCustom p2) {
					//升序
					return p1.getProductType1Id().compareTo(p2.getProductType1Id());
				}
			});
			resMap.put("Rows", typeColumnPvStatisticalCustomList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	public void getTotal(TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom, TypeColumnPvStatisticalCustom totalTypeColumnPvStatistics){
		totalTypeColumnPvStatistics.setTotalVisitorCount(totalTypeColumnPvStatistics.getTotalVisitorCount()+typeColumnPvStatisticalCustom.getTotalVisitorCount());
		totalTypeColumnPvStatistics.setTotalVisitorCountTourist(totalTypeColumnPvStatistics.getTotalVisitorCountTourist()+typeColumnPvStatisticalCustom.getTotalVisitorCountTourist());
		totalTypeColumnPvStatistics.setTotalPvCount(totalTypeColumnPvStatistics.getTotalPvCount()+typeColumnPvStatisticalCustom.getTotalPvCount());
		totalTypeColumnPvStatistics.setTotalPvCountTourist(totalTypeColumnPvStatistics.getTotalPvCountTourist()+typeColumnPvStatisticalCustom.getTotalPvCountTourist());
		totalTypeColumnPvStatistics.setShoppingCartCount(totalTypeColumnPvStatistics.getShoppingCartCount()+typeColumnPvStatisticalCustom.getShoppingCartCount());

		DecimalFormat df = new DecimalFormat("#0.00");

		String totalAddProductRate = totalTypeColumnPvStatistics.getAddProductRate();
		String addProductRate = typeColumnPvStatisticalCustom.getAddProductRate();
		double total= new BigDecimal(totalAddProductRate.substring(0,totalAddProductRate.indexOf("%"))).doubleValue();
		double item = new BigDecimal(addProductRate.substring(0,addProductRate.indexOf("%"))).doubleValue();
		totalTypeColumnPvStatistics.setAddProductRate(df.format(total+item)+"%");

		totalTypeColumnPvStatistics.setSubProductCount(totalTypeColumnPvStatistics.getSubProductCount()+typeColumnPvStatisticalCustom.getSubProductCount());

		String totalSubmitOrderRate = totalTypeColumnPvStatistics.getSubmitOrderRate();
		String submitOrderRate = typeColumnPvStatisticalCustom.getSubmitOrderRate();
		double total1= new BigDecimal(totalSubmitOrderRate.substring(0,totalSubmitOrderRate.indexOf("%"))).doubleValue();
		double item1 = new BigDecimal(submitOrderRate.substring(0,submitOrderRate.indexOf("%"))).doubleValue();
		totalTypeColumnPvStatistics.setSubmitOrderRate(df.format(total1+item1)+"%");

		totalTypeColumnPvStatistics.setPayProductCount(totalTypeColumnPvStatistics.getPayProductCount()+typeColumnPvStatisticalCustom.getPayProductCount());

		String totalPaymentRate = totalTypeColumnPvStatistics.getPaymentRate();
		String paymentRate = typeColumnPvStatisticalCustom.getPaymentRate();
		double total2= new BigDecimal(totalPaymentRate.substring(0,totalPaymentRate.indexOf("%"))).doubleValue();
		double item2 = new BigDecimal(paymentRate.substring(0,paymentRate.indexOf("%"))).doubleValue();
		totalTypeColumnPvStatistics.setPaymentRate(df.format(total2+item2)+"%");
	}

	public void getTotal(ProductDataStatistics productDataStatistics, ProductDataStatistics totalProductDataStatistics){
		totalProductDataStatistics.setTotalVisitorCount(totalProductDataStatistics.getTotalVisitorCount()+productDataStatistics.getTotalVisitorCount());
		totalProductDataStatistics.setTotalVisitorCountTourist(totalProductDataStatistics.getTotalVisitorCountTourist()+productDataStatistics.getTotalVisitorCountTourist());
		totalProductDataStatistics.setTotalPvCount(totalProductDataStatistics.getTotalPvCount()+productDataStatistics.getTotalPvCount());
		totalProductDataStatistics.setTotalPvCountTourist(totalProductDataStatistics.getTotalPvCountTourist()+productDataStatistics.getTotalPvCountTourist());
		totalProductDataStatistics.setShoppingCartCount(totalProductDataStatistics.getShoppingCartCount()+productDataStatistics.getShoppingCartCount());

		DecimalFormat df = new DecimalFormat("#0.00");

		String totalAddProductRate = totalProductDataStatistics.getAddProductRate();
		String addProductRate = productDataStatistics.getAddProductRate();
		double total= new BigDecimal(totalAddProductRate.substring(0,totalAddProductRate.indexOf("%"))).doubleValue();
		double item = new BigDecimal(addProductRate.substring(0,addProductRate.indexOf("%"))).doubleValue();
		totalProductDataStatistics.setAddProductRate(df.format(total+item)+"%");

		totalProductDataStatistics.setSubProductCount(totalProductDataStatistics.getSubProductCount()+productDataStatistics.getSubProductCount());

		String totalSubmitOrderRate = totalProductDataStatistics.getSubmitOrderRate();
		String submitOrderRate = productDataStatistics.getSubmitOrderRate();
		double total1= new BigDecimal(totalSubmitOrderRate.substring(0,totalSubmitOrderRate.indexOf("%"))).doubleValue();
		double item1 = new BigDecimal(submitOrderRate.substring(0,submitOrderRate.indexOf("%"))).doubleValue();
		totalProductDataStatistics.setSubmitOrderRate(df.format(total1+item1)+"%");

		totalProductDataStatistics.setPayProductCount(totalProductDataStatistics.getPayProductCount()+productDataStatistics.getPayProductCount());

		String totalPaymentRate = totalProductDataStatistics.getPaymentRate();
		String paymentRate = productDataStatistics.getPaymentRate();
		double total2= new BigDecimal(totalPaymentRate.substring(0,totalPaymentRate.indexOf("%"))).doubleValue();
		double item2 = new BigDecimal(paymentRate.substring(0,paymentRate.indexOf("%"))).doubleValue();
		totalProductDataStatistics.setPaymentRate(df.format(total2+item2)+"%");
	}

	public ProductDataStatistics getList(){
		ProductDataStatistics productDataStatistics = new ProductDataStatistics();
		productDataStatistics.setName("合计");
		productDataStatistics.setTimeHour("9999");
		productDataStatistics.setTotalVisitorCount(0);
		productDataStatistics.setTotalVisitorCountTourist(0);
		productDataStatistics.setTotalPvCount(0);
		productDataStatistics.setTotalPvCountTourist(0);
		productDataStatistics.setShoppingCartCount(0);
		productDataStatistics.setAddProductRate("0.00%");
		productDataStatistics.setSubProductCount(0);
		productDataStatistics.setSubmitOrderRate("0.00%");
		productDataStatistics.setPayProductCount(0);
		productDataStatistics.setPaymentRate("0.00%");
		return productDataStatistics;
	}

	public TypeColumnPvStatisticalCustom getList(ProductType productType){
		TypeColumnPvStatisticalCustom typeColumnPvStatisticalCustom = new TypeColumnPvStatisticalCustom();
		if(productType != null){
			typeColumnPvStatisticalCustom.setName(productType.getName());
			typeColumnPvStatisticalCustom.setProductType1Id(productType.getId());
		}else {
			typeColumnPvStatisticalCustom.setName("合计");
			typeColumnPvStatisticalCustom.setProductType1Id(9999);
		}
		typeColumnPvStatisticalCustom.setTotalVisitorCount(0);
		typeColumnPvStatisticalCustom.setTotalVisitorCountTourist(0);
		typeColumnPvStatisticalCustom.setTotalPvCount(0);
		typeColumnPvStatisticalCustom.setTotalPvCountTourist(0);
		typeColumnPvStatisticalCustom.setShoppingCartCount(0);
		typeColumnPvStatisticalCustom.setAddProductRate("0.00%");
		typeColumnPvStatisticalCustom.setSubProductCount(0);
		typeColumnPvStatisticalCustom.setSubmitOrderRate("0.00%");
		typeColumnPvStatisticalCustom.setPayProductCount(0);
		typeColumnPvStatisticalCustom.setPaymentRate("0.00%");
		return typeColumnPvStatisticalCustom;
	}

	/**
	 * 商品统计 小时数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/hourProductDataStatistics.shtml")
	public ModelAndView dataOfOnlineGoods2(HttpServletRequest request, Integer activitybrandgroupid) {
		String pagetype = request.getParameter("pagetype");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,-1);
		String beginDate = new SimpleDateFormat( "yyyy-MM-dd").format(calendar.getTime());
		Map<String,String> map = new HashMap<>();
		map.put("begin_date",beginDate);
		map.put("pagetype",pagetype);
		return new ModelAndView("/resourceLocationManagement/dataStatistics/hourProductDataStatistics",map);//在线商品
	}

	/**
	 * 商品统计 小时数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/getHourProductDataStatisticsList.shtml")
	@ResponseBody
	public Map<String,Object> getHourProductDataStatisticsList(HttpServletRequest request) {
		String pagetype = request.getParameter("pagetype");
		String beginDate = request.getParameter("begin_date");
		Map<String, Object> resMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		try{
			if(!StringUtil.isEmpty(beginDate) ){
				paramMap.put("beginDate", beginDate);
			}else{
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE,-1);
				paramMap.put("beginDate", new SimpleDateFormat( "yyyy-MM-dd ").format(calendar.getTime()));
			}


			List<ProductDataStatistics> productDataStatisticsList = new ArrayList<>();
			//判断是实时的，还是昨天之前的
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
			Date now = sdf.parse(sdf.format(new Date()));
			if(sdf.parse((String) paramMap.get("beginDate")).getTime() == now.getTime()){//当天实时
				paramMap.put("beginDate",beginDate+" 00:00:00");
				paramMap.put("endDate",beginDate+" 23:59:59");
				//判断pageType类型，获取相应的栏目Type进行查询
				if(org.apache.commons.lang.StringUtils.equals(pagetype,"1103")){//有好货(小时)
					paramMap.put("columnType","1");
					productDataStatisticsList = memberPvService.getNowHourProductDataStatisticsList(paramMap);

				}else if(org.apache.commons.lang.StringUtils.equals(pagetype,"1203")){//每日好店(小时)
					paramMap.put("columnType","2");
					productDataStatisticsList = memberPvService.getNowHourMchtDataStatisticsList(paramMap);
				}
				productDataStatisticsList.remove(productDataStatisticsList.size()-1);

			}else {//昨天之前
				//判断pageType类型，获取相应的栏目Type进行查询
				if(org.apache.commons.lang.StringUtils.equals(pagetype,"1103")){//有好货(小时)
					paramMap.put("columnType","15");
				}else if(org.apache.commons.lang.StringUtils.equals(pagetype,"1203")){//每日好店(小时)
					paramMap.put("columnType","16");
				}
				productDataStatisticsList = memberPvService.getHourProductDataStatisticsList(paramMap);
			}

			//对查询的数据补全24小时数据
			Map<String, ProductDataStatistics> map = new HashMap<>();
			ProductDataStatistics totalProductDataStatistics = getList();
			for (ProductDataStatistics productDataStatistics: productDataStatisticsList) {
				map.put(productDataStatistics.getTimeHour(),productDataStatistics);
				//记录合计数据
				getTotal(productDataStatistics,totalProductDataStatistics);
			}

			for (int i = 0;i <= 23;i++){
				ProductDataStatistics productDataStatistics = new ProductDataStatistics();
				if(!map.containsKey(String.valueOf(i))){
					productDataStatistics = new ProductDataStatistics();
				}else{//修改statisticalDate值
					productDataStatistics = map.get(String.valueOf(i));
				}
				nameSet(productDataStatistics, i);
				map.put(String.valueOf(i), productDataStatistics);
			}

			List<ProductDataStatistics> list = new ArrayList<>();
			for (ProductDataStatistics productDataStatistics : map.values()){
				list.add(productDataStatistics);
			}
			//添加合计数据
			list.add(totalProductDataStatistics);
			Collections.sort(list, new Comparator<ProductDataStatistics>() {
				@Override
				public int compare(ProductDataStatistics p1, ProductDataStatistics p2) {
					//升序
					return Integer.valueOf(p1.getTimeHour()).compareTo(Integer.valueOf(p2.getTimeHour()));
				}
			});
			resMap.put("Rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 给name赋值
	 * @param nameSet
	 */
	public void nameSet(ProductDataStatistics productDataStatistics, Integer i){
		String timeHour = "";
		switch (i){
			case 0: timeHour = "00 - 01 时";break;
			case 1: timeHour = "01 - 02 时";break;
			case 2: timeHour = "02 - 03 时";break;
			case 3: timeHour = "03 - 04 时";break;
			case 4: timeHour = "04 - 05 时";break;
			case 5: timeHour = "05 - 06 时";break;
			case 6: timeHour = "06 - 07 时";break;
			case 7: timeHour = "07 - 08 时";break;
			case 8: timeHour = "08 - 09 时";break;
			case 9: timeHour = "09 - 10 时";break;
			case 10: timeHour = "10 - 11 时";break;
			case 11: timeHour = "11 - 12 时";break;
			case 12: timeHour = "12 - 13 时";break;
			case 13: timeHour = "13 - 14 时";break;
			case 14: timeHour = "14 - 15 时";break;
			case 15: timeHour = "15 - 16 时";break;
			case 16: timeHour = "16 - 17 时";break;
			case 17: timeHour = "17 - 18 时";break;
			case 18: timeHour = "18 - 19 时";break;
			case 19: timeHour = "19 - 20 时";break;
			case 20: timeHour = "20 - 21 时";break;
			case 21: timeHour = "21 - 22 时";break;
			case 22: timeHour = "22 - 23 时";break;
			case 23: timeHour = "23 - 24 时";break;
		}
		productDataStatistics.setName(timeHour);
		if(productDataStatistics.getId()==null){
			productDataStatistics.setTimeHour(String.valueOf(i));
			productDataStatistics.setTotalVisitorCount(0);
			productDataStatistics.setTotalVisitorCountTourist(0);
			productDataStatistics.setTotalPvCount(0);
			productDataStatistics.setTotalPvCountTourist(0);
			productDataStatistics.setShoppingCartCount(0);
			productDataStatistics.setAddProductRate("0.00%");
			productDataStatistics.setSubProductCount(0);
			productDataStatistics.setSubmitOrderRate("0.00%");
			productDataStatistics.setPayProductCount(0);
			productDataStatistics.setPaymentRate("0.00%");
		}
	}
	/**
	 * 商品和店铺统计 日数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/dayProductDataStatistics.shtml")
	public ModelAndView dataOfOnlineGoods3(HttpServletRequest request, Integer activitybrandgroupid) {
		String pagetype = request.getParameter("pagetype");
		ModelAndView m = new ModelAndView();
		m.addObject("pagetype",pagetype);
		m.setViewName("/resourceLocationManagement/dataStatistics/dayProductDataStatistics");

		//昨天日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,-1);
		Date yestertime = calendar.getTime();
		String yesterday = sdf.format(yestertime);
		m.addObject("yesterday",yesterday);

		//往前八天的日期
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,-7);
		Date eightDayAgoTime = calendar.getTime();
		String eightDayAgo= sdf.format(eightDayAgoTime);
		m.addObject("eightDayAgo",eightDayAgo);

		return m;
	}

	/**
	 * 商品和店铺统计 日数据的数据列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/dayProductDataStatisticsList.shtml")
	@ResponseBody
	public  Map<String, Object> dayProductDataStatisticsList(HttpServletRequest request  ) {
		Map<String , Object> resMap = new HashMap<String, Object>();
		List<ColumnPvStatisticalCustom> dataList = null;
		Integer totalCount = 0;

		try {
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String pagetype = request.getParameter("pagetype");//类型
			String statisticalBeginDate = request.getParameter("statisticalBeginDate");//开始日期
			String statisticalEndDate = request.getParameter("statisticalEndDate");//结束日期
			ColumnPvStatisticalCustomExample columnPvStatisticalCustomExample = new ColumnPvStatisticalCustomExample();
			ColumnPvStatisticalExample.Criteria criteria = columnPvStatisticalCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(pagetype)){
				if("1104".equals(pagetype)){
					criteria.andColumnTypeEqualTo("15");//栏目,有好货
				}else if("1204".equals(pagetype)){
					criteria.andColumnTypeEqualTo("16");//栏目,每日好店
				}
			}


			if(!StringUtil.isEmpty(statisticalBeginDate)){
				criteria.andStatisticalDateGreaterThanOrEqualTo(statisticalBeginDate);
			}
			if(!StringUtil.isEmpty(statisticalEndDate)){
				criteria.andStatisticalDateLessThanOrEqualTo(statisticalEndDate);
			}

			dataList = columnPvStatisticalService.selectByCustomExample(columnPvStatisticalCustomExample);
			//将有数据的天数添加进去
			List<String> containDays = new ArrayList<String>();
			for (ColumnPvStatisticalCustom cpsc : dataList) {
				containDays.add(cpsc.getStatisticalDate());
			}
			//获取开始结束之间的天数
			List<String> betweenDays = this.getBetweenDays(statisticalBeginDate, statisticalEndDate);
			//拼接完整的日期
			for (int i = 0; i < betweenDays.size(); i++) {
				if(!containDays.contains(betweenDays.get(i))){//不存在就都设为0
					ColumnPvStatisticalCustom columnPvStatisticalCustom = new ColumnPvStatisticalCustom();
					columnPvStatisticalCustom.setStatisticalDate(betweenDays.get(i));
					columnPvStatisticalCustom.setTotalPvCount(0);
					columnPvStatisticalCustom.setTotalPvCountTourist(0);
					columnPvStatisticalCustom.setTotalVisitorCount(0);
					columnPvStatisticalCustom.setTotalVisitorCountTourist(0);
					columnPvStatisticalCustom.setShoppingCartCount(0);
					columnPvStatisticalCustom.setSubProductCount(0);
					columnPvStatisticalCustom.setPayProductCount(0);
					columnPvStatisticalCustom.setAddConversion(new BigDecimal(0));//加购转化
					columnPvStatisticalCustom.setOrderConversion(new BigDecimal(0));//下单转化
					columnPvStatisticalCustom.setPaymentConversion(new BigDecimal(0));//支付转化
					dataList.add(columnPvStatisticalCustom);
				}
			}
			// 升序排序
			Collections.sort(dataList, new Comparator<ColumnPvStatisticalCustom>() {
				@Override
				public int compare(ColumnPvStatisticalCustom c1, ColumnPvStatisticalCustom c2) {
					return c1.getStatisticalDate().compareTo(c2.getStatisticalDate());
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 * 获取两天之间的时间
	 * @param request
	 * @return
	 */
	public List<String> getBetweenDays(String stime,String etime){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date sdate=null;
		Date eDate=null;
		try {
			sdate=df.parse(stime);
			eDate=df.parse(etime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		List<String> list=new ArrayList<String>();
		while (sdate.getTime()<=eDate.getTime()) {
			list.add(df.format(sdate));
			c.setTime(sdate);
			c.add(Calendar.DATE, 1); // 日期加1天
			sdate = c.getTime();
		}
		return list;
	}

	//  ==========================================
	/**
	 * 店铺统计 在线店铺
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/onlineShopDataStatistics.shtml")
	public ModelAndView dataStatistics1(HttpServletRequest request, Integer activitybrandgroupid) {
		String pagetype = request.getParameter("pagetype");
		Map<String, Object> resMap = new HashMap<String, Object>();
		ModelAndView m = new ModelAndView();
		m.setViewName("/resourceLocationManagement/dataStatistics/onlineShopDataStatistics");
		//九大类目
		ProductTypeExample example = new ProductTypeExample();
		example.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(example );
		m.addObject("productTypes", productTypes);

		//昨天的日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,-1);
		Date yestertime = calendar.getTime();
		String yesterday = sdf.format(yestertime);
		m.addObject("yesterday",yesterday);

		//今天日期
		Date nowDate = new Date();
		String today = sdf.format(nowDate);
		m.addObject("today",today);

		ResourceBundle resource = ResourceBundle.getBundle("base_config");
		String mUrl = resource.getString("mUrl");
		m.addObject("previewUrl", mUrl+"/xgbuy/views/index.html?redirect_url=seller/index.html?mchtId=");
		return m;
	}
	/**
	 * 店铺统计 在线店铺
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/onlineShopDataStatisticsList.shtml")
	public  Map<String, Object> onlineShopDataStatisticsList(HttpServletRequest request , Page page ) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<TypeColumnPvStatisticalCustom> dataList = null;
		Integer totalCount = 0;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			SourceNicheCustomExample sourceNicheCustomExample = new SourceNicheCustomExample();
			SourceNicheCustomCriteria criteria = sourceNicheCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andAuditStatusEqualTo("1");//审核通过
			criteria.andStatusEqualTo("0");//活动状态为添加


			String pagetype = request.getParameter("pagetype");
			if(!StringUtil.isEmpty(pagetype)){
				if("1201".equals(pagetype)){//每日好店
					map.put("columnType",16);
					map.put("type",2);
					criteria.andTypeEqualTo("2");
				}
				if("2001".equals(pagetype)){//大学生创业
					map.put("columnType",25);
					map.put("type",10);
					criteria.andTypeEqualTo("10");
				}
				map.put("statisticType","2");
			}

			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){//商家序号
				String mchtCode = request.getParameter("mchtCode");
				map.put("mchtCode",mchtCode);
				criteria.andmchtCodeEqualTo(mchtCode);
			}

			if(!StringUtil.isEmpty(request.getParameter("mchtName"))){//店铺名称
				String mchtName = request.getParameter("mchtName");
				map.put("mchtName","%"+mchtName+"%");
				criteria.andmchtNameLikeTo(mchtName);
			}

			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))){//类目
				String productTypeId = request.getParameter("productTypeId");
				map.put("productTypeId",Integer.parseInt(productTypeId));
				criteria.andproductTypeIdEqualTo(productTypeId);
			}

			String statisticsType = request.getParameter("statisticsType");
			String beginDate = "";
			String endDate = "";
			if(statisticsType.equals("1")){
				beginDate = request.getParameter("nowBeginDate");
				endDate = request.getParameter("nowEndDate");
			}else{
				beginDate = request.getParameter("beforeBeginDate");
				endDate = request.getParameter("beforeEndDate");
			}
			map.put("beginDate", beginDate+" 00:00:00");
			map.put("endDate", endDate+" 23:59:59");

			Integer limitSize = page.getLimitSize();
			Integer limitStart = page.getLimitStart();
			if(limitSize!=null && limitStart!=null){
				map.put("limitSize",limitSize);
				map.put("limitStart",limitStart);
			}

			sourceNicheCustomExample.setLimitSize(limitSize);
			sourceNicheCustomExample.setLimitStart(limitStart);

			if("2".equals(statisticsType) || "".equals(statisticsType)){//昨天之后
				dataList = typeColumnPvStatisticalService.selectShopDataStatisticsList(map);
			}else if("1".equals(statisticsType)){//当前时间
				dataList = typeColumnPvStatisticalService.selectNowShopDataStatisticsList(map);
			}

			totalCount = sourceNicheService.sourceNichecountByExample(sourceNicheCustomExample);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		// resMap.put("Total", totalCount);
		return resMap;
	}


	/**
	 * 店铺统计 类目
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/categoryShopDataStatistics.shtml")
	public ModelAndView dataOfOnlineGoods1a(HttpServletRequest request, Integer activitybrandgroupid) {
		String pagetype = request.getParameter("pagetype");
		Map<String, Object> resMap = new HashMap<String, Object>();
		ModelAndView m = new ModelAndView();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,-1);
		String beginDate = new SimpleDateFormat( "yyyy-MM-dd ").format(calendar.getTime());
		m.addObject("beginDate",beginDate);
		m.addObject("endDate",beginDate);
		m.addObject("pagetype",pagetype);
		m.setViewName("/resourceLocationManagement/dataStatistics/categoryShopDataStatistics");//在线商品
		return m;
	}
	/**
	 * 店铺统计 小时数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/hourShopDataStatistics.shtml")
	public ModelAndView dataOfOnlineGoodsb2(HttpServletRequest request, Integer activitybrandgroupid) {
		String pagetype = request.getParameter("pagetype");
		Map<String, Object> resMap = new HashMap<String, Object>();
		ModelAndView m = new ModelAndView();
		m.setViewName("/resourceLocationManagement/hourShopDataStatistics");//在线商品
		return m;
	}

	/**
	 * 积分抽奖路由
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/integralLotteryRoute.shtml")
	public ModelAndView integralLotteryRoute(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		String pagetype = request.getParameter("pagetype");
		if(StringUtils.isEmpty(pagetype)){
			throw new ArgException("参数异常!");
		}
		switch (pagetype){
			case "10000":
				m.setViewName("/resourceLocationManagement/integralLottery/commodityAudit");//商品审核
				break;
			case "10001":
				m.setViewName("/resourceLocationManagement/integralLottery/commodityPools");//商品池
				break;
			case "10002":
				m.setViewName("/resourceLocationManagement/coupon/commodityCouponsList");//优惠券
				break;
			case "10003":
				m.setViewName("/resourceLocationManagement/integralLottery/integralDrillPlate");//抽奖管理
				break;
			case "10004":
				m.setViewName("/resourceLocationManagement/integralLottery/memberLotteryView");//抽奖记录
				break;
			case "10005":
				m.setViewName("/resourceLocationManagement/coupon/commodityCouponsList");//优惠券
				m.addObject("moneyMin",request.getParameter("moneyMin"));
				m.addObject("moneyMax",request.getParameter("moneyMax"));
				break;
			default:
				break;
		}

		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		m.addObject("productTypes",productTypes);
		m.addObject("pagetype",pagetype);
		return m;
	}

	/**
	 *	批量审核积分抽奖商品
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/batchAuditIntegralProduct.shtml")
	public Map<String, Object> batchAuditIntegralProduct(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "0000";
		String message = "操作成功";
		try {
			String staffID = this.getSessionStaffBean(request).getStaffID();
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			ArrayList<String> failList = new ArrayList<>();
			String[] ids = request.getParameter("ids").split(",");
			String artificialAuditStatus = request.getParameter("artificialAuditStatus");
			String stock = request.getParameter("stock");
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("artificialAuditStatus", artificialAuditStatus);
			paramMap.put("auditBy", staffID);
			paramMap.put("auditDate", date);
			if("1".equals(artificialAuditStatus)){
				paramMap.put("stock", stock);
				int parseStock = Integer.parseInt(stock);
				int totalCount = this.countCommodityPools();
				for (int i = 0; i < ids.length; i++) {
					SourceNicheCustom sourceNicheCustom = sourceNicheService.selectIntegralProductCustomById(Integer.valueOf(ids[i]));
					if (sourceNicheCustom.getStockSum() < 5 || parseStock > sourceNicheCustom.getStockSum()) {
						failList.add(ids[i]);
						continue;
					}
					totalCount++;
					paramMap.put("id", ids[i]);
					paramMap.put("seqNo", totalCount);
					sourceNicheService.batchAuditIntegralProduct(paramMap);
				}
			}else {
				paramMap.put("ids", ids);
				sourceNicheService.batchAuditIntegralProduct(paramMap);
			}
			if(!CollectionUtils.isEmpty(failList)){
				statusCode = "4004";
				message = failList.size()+"条记录不符合条件!其余插入成功";
			}
		} catch (Exception e) {
			statusCode = "4004";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}

	//商品池数据
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/integralLotteryCommodityPoolsList.shtml")
	public Map<String, Object> integralLotteryCommodityPoolsList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<SourceNicheCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
			SourceNicheCustomExample.SourceNicheCustomCriteria sourceNicheCustomCriteria=sourceNicheCustomExample.createCriteria();
			sourceNicheCustomCriteria.andDelFlagEqualTo("0");
			sourceNicheCustomCriteria.andAuditStatusEqualTo("1");//审核通过
			sourceNicheCustomCriteria.andpStatusEqualTo("1");//商品处于上线状态
			sourceNicheCustomExample.setOrderByClause("IFNULL(sn.seq_no, 99999999999) asc");
			sourceNicheCustomCriteria.andTypeEqualTo("13");//积分抽奖
			sourceNicheCustomCriteria.andStockGreaterThan(0);

			if(!StringUtil.isEmpty(request.getParameter("recycleStatus"))){//回收状态
				sourceNicheCustomCriteria.andStatusEqualTo(request.getParameter("recycleStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))){//品类
				String productTypeId = request.getParameter("productTypeId");
				sourceNicheCustomCriteria.andsourceProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))){//店铺名
				sourceNicheCustomCriteria.andMchtNameLikeTo(request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){//商家序号
				sourceNicheCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){//品牌
				sourceNicheCustomCriteria.andproductbrandNameLike("%"+request.getParameter("productBrandName")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("whichProduct"))){
				String goodIds = "";
				if(!StringUtil.isEmpty(request.getParameter("goodIds"))){
					goodIds = request.getParameter("goodIds");
				}else if(!StringUtil.isEmpty(request.getParameter("artnos"))){
					goodIds = request.getParameter("artnos");
				}
				if(!StringUtil.isEmpty(goodIds)){
					String[] split = goodIds.split("\n");
					String aaaString = "";
					for (int i = 0; i < split.length; i++) {
						if(aaaString==""){
							aaaString+=split[i];
						}else{
							aaaString+=","+split[i];
						}
					}
					if("1".equals(request.getParameter("whichProduct"))){//商品id
						sourceNicheCustomCriteria.andCodesIn(aaaString);
					}else if("2".equals(request.getParameter("whichProduct"))){//货物id
						sourceNicheCustomCriteria.andArtNosIn(aaaString);
					}
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("lotteryCountMin"))){
				sourceNicheCustomCriteria.andLotteryCountMinLessThen(Integer.valueOf(request.getParameter("lotteryCountMin")));
			}
			if(!StringUtil.isEmpty(request.getParameter("lotteryCountMax"))){
				sourceNicheCustomCriteria.andLotteryCountMinGreaterThen(Integer.valueOf(request.getParameter("lotteryCountMax")));
			}

			sourceNicheCustomExample.setLimitStart(page.getLimitStart());
			sourceNicheCustomExample.setLimitSize(page.getLimitSize());
			totalCount=sourceNicheService.sourceNichecountByExample(sourceNicheCustomExample);
			dataList=sourceNicheService.selectIntegralLotteryCommodityPoolsList(sourceNicheCustomExample);

		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 *	积分商品回收和移除
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/updateStatusIntegralProduct.shtml")
	public Map<String, Object> updateStatusIntegralProduct(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "0000";
		String message = "操作成功";
		try {
			Integer staffID = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id = Integer.valueOf(request.getParameter("id"));
			String status = request.getParameter("status");
			if("0".equals(status)){
				SourceNicheCustom sourceNicheCustom = sourceNicheService.selectIntegralProductCustomById(id);
				if (sourceNicheCustom.getStockSum() < 5 || sourceNicheCustom.getStock() > sourceNicheCustom.getStockSum()) {
					map.put("statusCode", "4004");
					map.put("message", "商品不符合移出条件!");
					return map;
				}
				int totalCount = this.countCommodityPools();
				SourceNiche sourceNiche = new SourceNiche();
				sourceNiche.setId(id);
				sourceNiche.setStatus(status);
				sourceNiche.setSeqNo(++totalCount);
				sourceNiche.setUpdateBy(staffID);
				sourceNiche.setUpdateDate(new Date());
				sourceNicheService.updateByPrimaryKeySelective(sourceNiche);
			}else if("1".equals(status)){
				sourceNicheService.removeIntegralProduct(id, staffID);
			}
		} catch (Exception e) {
			statusCode = "4004";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}

	/**
	 *	修改积分
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/updateSourceNicheIntegralCount.shtml")
	public Map<String, Object> updateSourceNicheIntegralCount(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "0000";
		String message = "操作成功";
		try {
			String id = request.getParameter("id");
			String integralCount = request.getParameter("integralCount");
			SourceNiche sourceNiche = new SourceNiche();
			sourceNiche.setId(Integer.valueOf(id));
			sourceNiche.setIntegralCount(Integer.parseInt(integralCount));
			sourceNiche.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			sourceNiche.setUpdateDate(new Date());
			sourceNicheService.updateByPrimaryKeySelective(sourceNiche);
		} catch (Exception e) {
			statusCode = "4004";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}

	/**
	 *	修改排序
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/updateIntegralLotterySeqNo.shtml")
	public Map<String, Object> updateIntegralLotterySeqNo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "0000";
		String message = "操作成功";
		try {
			sourceNicheService.updateIntegralLotterySeqNo(request, this.countCommodityPools(), Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
		} catch (Exception e) {
			statusCode = "4004";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}

	/**
	 *	格式化排序
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/integralProductFormattingSeqNo.shtml")
	public Map<String, Object> integralProductFormattingSeqNo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "0000";
		String message = "操作成功";
		try {
			sourceNicheService.integralProductFormattingSeqNo();
		} catch (Exception e) {
			statusCode = "4004";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}

	/**
	 * 中奖记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/memberLotteryView.shtml")
	public ModelAndView memberLotteryView(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/resourceLocationManagement/integralLottery/memberLotteryView");
		m.addObject("type",request.getParameter("type"));
		m.addObject("integralProduct",request.getParameter("integralProduct"));
		m.addObject("couponId",request.getParameter("couponId"));
		return m;
	}

	/**
	 * 抽奖记录
	 * @param request
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/memberLotteryList.shtml")
	public Map<String, Object> memberLotteryList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<MemberLotteryCustom> dataList = null;
		Integer totalCount = 0;
		try {
			MemberLotteryCustomExample example = new MemberLotteryCustomExample();
			MemberLotteryCustomExample.MemberLotteryCustomCriteria criteria = example.createCriteria();
			example.setOrderByClause("id DESC");
			criteria.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(request.getParameter("type"))){
				criteria.andTypeEqualTo(request.getParameter("type"));
			}
			if(!StringUtils.isEmpty(request.getParameter("integralProduct"))){
				criteria.andProductIdEqualTo(Integer.valueOf(request.getParameter("integralProduct")));
			}
			if(!StringUtils.isEmpty(request.getParameter("couponId"))){
				criteria.andCouponIdEqualTo(Integer.valueOf(request.getParameter("couponId")));
			}
			if(!StringUtils.isEmpty(request.getParameter("memberMobile"))){
				criteria.andMemberMobileEqualTo(request.getParameter("memberMobile"));
			}
			if(!StringUtils.isEmpty(request.getParameter("memberNick"))){
				criteria.andMemberNickEqualTo(request.getParameter("memberNick"));
			}
			if(!StringUtils.isEmpty(request.getParameter("receiverName"))){
				criteria.andReceiverNameEqualTo(request.getParameter("receiverName"));
			}
			if(!StringUtils.isEmpty(request.getParameter("useStatus"))){
				criteria.andUseStatusEqualTo(request.getParameter("useStatus"));
			}
			if(!StringUtils.isEmpty(request.getParameter("memberId"))){
				criteria.andMemberIdEqualTo(Integer.valueOf(request.getParameter("memberId")));
			}
			if(!StringUtils.isEmpty(request.getParameter("createDate"))){
				criteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("createDate")+" 00:00:00"));
				criteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("createDate")+" 23:59:59"));
			}

			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			totalCount=memberLotteryService.countMemberLotteryCustomByExample(example);
			dataList=memberLotteryService.selectMemberLotteryCustomByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}

	/**
	 * 抽奖转盘设置
	 * @param request
	 * @return
	 */
	@RequestMapping("/resourceLocationManagement/editLotterySettingsView.shtml")
	public ModelAndView editLotterySettingsView(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		m.setViewName("/resourceLocationManagement/integralLottery/editLotterySettingsView");
		LotterySettings lotterySettings = lotterySettingsService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		m.addObject("lotterySettings",lotterySettings);
		return m;
	}

	/**
	 * 转盘数据
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/memberLotteryData.shtml")
	public Map<String, Object> memberLotteryData(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "0000";
		String message = "查询成功";
		try {
			//转盘设置
			SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
			sysParamCfgExample.createCriteria().andParamCodeEqualTo("INTEGRAL_LOTTERY_CONFIG");
			List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
			for (SysParamCfg sysParamCfg : sysParamCfgs) {
				map.put(sysParamCfg.getParamName(), sysParamCfg.getParamValue());
			}
			//转盘数据
			LotterySettingsExample lotterySettingsExample = new LotterySettingsExample();
			lotterySettingsExample.createCriteria().andDelFlagEqualTo("0");
			lotterySettingsExample.setLimitStart(0);
			lotterySettingsExample.setLimitSize(8);
			List<LotterySettings> lotterySettings = lotterySettingsService.selectByExample(lotterySettingsExample);
			int productSize = 0;
			HashMap<String, Object> couponMap = new HashMap<>();
			for (LotterySettings lotterySetting : lotterySettings) {
				if("3".equals(lotterySetting.getType())){
					productSize++;
				} else if ("2".equals(lotterySetting.getType())) {
					CouponExample example = new CouponExample();
					CouponExample.Criteria criteria = example.createCriteria();
					criteria.andDelFlagEqualTo("0");
					criteria.andRangEqualTo("4");
					criteria.andIsIntegralTurntableEqualTo("1");
					criteria.andRecBeginDateLessThan(new Date());
					criteria.andRecEndDateGreaterThan(new Date());
					criteria.andMoneyGreaterThanOrEqualTo(lotterySetting.getMinAmount());
					criteria.andMoneyLessThan(lotterySetting.getMaxAmount());
					int countCoupon = couponService.countByExample(example);
					lotterySetting.setRemarks(countCoupon+"");
				}
			}
			//存在抽奖商品
			if (productSize > 0) {
				SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
				SourceNicheCustomExample.SourceNicheCustomCriteria sourceNicheCustomCriteria=sourceNicheCustomExample.createCriteria();
				sourceNicheCustomCriteria.andDelFlagEqualTo("0");
				sourceNicheCustomCriteria.andAuditStatusEqualTo("1");//审核通过
				sourceNicheCustomCriteria.andpStatusEqualTo("1");//商品处于上线状态
				sourceNicheCustomCriteria.andTypeEqualTo("13");//积分抽奖
				sourceNicheCustomCriteria.andStockGreaterThan(0);
				sourceNicheCustomCriteria.andUpDateLessThanOrEqualTo(new Date());
				sourceNicheCustomCriteria.andStatusEqualTo("0");
				sourceNicheCustomExample.setOrderByClause("IFNULL(sn.seq_no, 99999999999) asc");
				sourceNicheCustomExample.setLimitStart(0);
				sourceNicheCustomExample.setLimitSize(productSize);
				List<SourceNicheCustom> sourceNicheCustoms = sourceNicheService.selectIntegralLotteryCommodityPoolsList(sourceNicheCustomExample);
				map.put("sourceNicheCustoms", sourceNicheCustoms);
			}else {
				map.put("sourceNicheCustoms", new ArrayList<SourceNicheCustom>());
			}
			map.put("lotterySettings", lotterySettings);
		} catch (Exception e) {
			statusCode = "4004";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}

	/**
	 *	保存抽奖转盘设置
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/saveTurntableSettings.shtml")
	public Map<String, Object> saveTurntableSettings(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "0000";
		String message = "操作成功";
		try {
			String perIntegralSpend = request.getParameter("perIntegralSpend");
			String shareForFreeTimes = request.getParameter("shareForFreeTimes");
			SysParamCfg sysParamCfg = new SysParamCfg();
			sysParamCfg.setParamValue(perIntegralSpend);
			SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
			SysParamCfgExample.Criteria criteria = sysParamCfgExample.createCriteria();
			criteria.andParamCodeEqualTo("INTEGRAL_LOTTERY_CONFIG");
			criteria.andParamNameEqualTo("PER_INTEGRAL_SPEND");
			sysParamCfgService.updateByExampleSelective(sysParamCfg, sysParamCfgExample);
			sysParamCfg.setParamValue(shareForFreeTimes);
			SysParamCfgExample sysParamCfgExample1 = new SysParamCfgExample();
			SysParamCfgExample.Criteria criteria1 = sysParamCfgExample1.createCriteria();
			criteria1.andParamCodeEqualTo("INTEGRAL_LOTTERY_CONFIG");
			criteria1.andParamNameEqualTo("SHARE_FOR_FREE_TIMES");
			sysParamCfgService.updateByExampleSelective(sysParamCfg, sysParamCfgExample1);
		} catch (Exception e) {
			statusCode = "4004";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}

	/**
	 *	修改抽奖转盘设置
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resourceLocationManagement/updateLotterySettings.shtml")
	public Map<String, Object> updateLotterySettings(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String statusCode = "0000";
		String message = "操作成功";
		try {
			String id = request.getParameter("id");
			String type = request.getParameter("type");
			LotterySettings lotterySettings = new LotterySettings();
			lotterySettings.setId(Integer.valueOf(id));
			lotterySettings.setType(type);
			if ("1".equals(type)) {
				lotterySettings.setIntegral(Integer.valueOf(request.getParameter("integral")));
				lotterySettings.setWinningProbability(new BigDecimal(request.getParameter("winningProbability")).divide(new BigDecimal("1000")));
			}else if("2".equals(type)){
				lotterySettings.setMinAmount(new BigDecimal(request.getParameter("minAmount")));
				lotterySettings.setMaxAmount(new BigDecimal(request.getParameter("maxAmount")));
				lotterySettings.setWinningProbability(new BigDecimal(request.getParameter("winningProbability")).divide(new BigDecimal("1000")));
			}
			lotterySettings.setUpdateBy(Integer.valueOf(this.getSessionStaffBean(request).getStaffID()));
			lotterySettings.setUpdateDate(new Date());
			lotterySettingsService.updateByPrimaryKeySelective(lotterySettings);
		} catch (Exception e) {
			statusCode = "4004";
			message = "系统错误";
		}
		map.put("statusCode", statusCode);
		map.put("message", message);
		return map;
	}

	/**
	 * 获取抽奖商品池中商品个数
	 * @return
	 */
	private int countCommodityPools(){
		SourceNicheCustomExample sourceNicheCustomExample=new SourceNicheCustomExample();
		SourceNicheCustomExample.SourceNicheCustomCriteria sourceNicheCustomCriteria=sourceNicheCustomExample.createCriteria();
		sourceNicheCustomCriteria.andDelFlagEqualTo("0");
		sourceNicheCustomCriteria.andAuditStatusEqualTo("1");//审核通过
		sourceNicheCustomCriteria.andpStatusEqualTo("1");//商品处于上线状态
		sourceNicheCustomCriteria.andTypeEqualTo("13");//积分抽奖
		sourceNicheCustomCriteria.andStockGreaterThan(0);
		sourceNicheCustomCriteria.andStatusEqualTo("0");
		return sourceNicheService.sourceNichecountByExample(sourceNicheCustomExample);
	}
}
