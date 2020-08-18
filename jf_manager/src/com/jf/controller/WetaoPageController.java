package com.jf.controller;
import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class WetaoPageController extends BaseController {
	
	@Resource
	private PayToMchtLogService payToMchtLogService;
	
	@Resource
	private MchtInfoService mchtInfoService;

	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private WetaoPageService wetaoPageService;
	
	@Autowired
	private ProductBrandService productBrandService;
	
	@Autowired
	private WetaoPageAdInfoService wetaoPageAdInfoService;
	
	@Autowired
	private ActivityAreaService activityAreaService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DecorateAreaService decorateAreaService;
	
	@Autowired
	private DecorateModuleService decorateModuleService;
	
	@Autowired
	private ModuleMapService moduleMapService;
	
	@Autowired
	private WeitaoChannelService  weitaoChannelService;
	
	@Resource
	private BrandteamTypeService brandteamTypeService;
	
	@Resource
	private MallCategoryService mallCategoryService;
	
	@Resource
	private CouponService couponService;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 微淘列表装修
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoPage/list.shtml")
	public ModelAndView wetaoPageList(HttpServletRequest request) {
		String rtPage = "wetao/wetaoPage_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andTLevelEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypeList", productTypeList); //1级类目
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 微淘列表装修数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoPage/data.shtml")
	@ResponseBody
	public Map<String, Object> wetaoPageData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			WetaoPageExample example = new WetaoPageExample();
			example.setOrderByClause("IFNULL(t.seq_no,999999999) asc,id desc");
			WetaoPageExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			String productTypeId = request.getParameter("productTypeId");
			String name = request.getParameter("name");
			String status = request.getParameter("status");
			
			if(!StringUtil.isEmpty(productTypeId) && !productTypeId.equals("-1")){
				criteria.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}else if(StringUtil.isEmpty(productTypeId)){
				criteria.andProductTypeIdIsNull();
			}
			if(!StringUtil.isEmpty(name)){
				criteria.andNameLike("%"+name+"%");
			}
		
			if(!StringUtil.isEmpty(status)){
				criteria.andStatusEqualTo(status);
			}
			totalCount = wetaoPageService.countByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<WetaoPageCustom> wetaoPageCustoms = wetaoPageService.selectCustomByExample(example);
			ResourceBundle resource = ResourceBundle.getBundle("base_config"); 
			String mUrl = resource.getString("mUrl");
			for(WetaoPageCustom wetaoPageCustom:wetaoPageCustoms){
				String previewUrl = mUrl+"/xgbuy/views/index.html?redirect_url=activity/templet/brand_decorate.html?infoId="+wetaoPageCustom.getDecorateInfoId();
				wetaoPageCustom.setPreviewUrl(previewUrl);
			}
			resMap.put("Rows", wetaoPageCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 编辑微淘页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoPage/toEdit.shtml")
	public ModelAndView toEdit(HttpServletRequest request) {
		String rtPage = "wetao/toEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andTLevelEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypeList", productTypeList); //1级类目
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			WetaoPage wetaoPage = wetaoPageService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("wetaoPage", wetaoPage);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoPage/save.shtml")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String productTypeId = request.getParameter("productTypeId");
			WetaoPageExample e = new WetaoPageExample();
			WetaoPageExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(productTypeId)){
				c.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}else{
				c.andProductTypeIdIsNull();
			}
			List<WetaoPage> wetaoPages = wetaoPageService.selectByExample(e);
			WetaoPage wetaoPage = new WetaoPage();
			if(!StringUtils.isEmpty(id)){
				if(wetaoPages!=null && wetaoPages.size()>0){
					if(!wetaoPages.get(0).getId().toString().equals(id)){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "一级分类已存在，不能重复添加");
						return resMap;
					}
				}
				wetaoPage = wetaoPageService.selectByPrimaryKey(Integer.parseInt(id));
			}else{
				if(wetaoPages!=null && wetaoPages.size()>0){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "一级分类已存在，不能重复添加");
					return resMap;
				}
				wetaoPage.setDelFlag("0");
				wetaoPage.setCreateDate(new Date());
				wetaoPage.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				wetaoPage.setStatus("0");
			}
			wetaoPage.setName(name);
			if(!StringUtils.isEmpty(productTypeId)){
				wetaoPage.setProductTypeId(Integer.parseInt(productTypeId));
			}
			wetaoPage.setUpdateDate(new Date());
			wetaoPage.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(!StringUtils.isEmpty(id)){
				wetaoPageService.update(wetaoPage);
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
				wetaoPageService.save(di,da,wetaoPage);
			}
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
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
	@RequestMapping(value = "/wetaoPage/changeStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			WetaoPage wetaoPage = wetaoPageService.selectByPrimaryKey(Integer.parseInt(id));
			if(wetaoPage.getStatus().equals("0")){
				wetaoPage.setStatus("1");
			}else{
				wetaoPage.setStatus("0");
			}
			wetaoPage.setUpdateDate(new Date());
			wetaoPage.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			wetaoPageService.updateByPrimaryKeySelective(wetaoPage);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 保存排序值
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoPage/saveSeqNo.shtml")
	@ResponseBody
	public Map<String, Object> saveSeqNo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			WetaoPage wetaoPage = wetaoPageService.selectByPrimaryKey(Integer.parseInt(id));
			if(!StringUtils.isEmpty(seqNo)){
				wetaoPage.setSeqNo(Integer.parseInt(seqNo));
			}
			wetaoPage.setUpdateDate(new Date());
			wetaoPage.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			wetaoPageService.updateByPrimaryKeySelective(wetaoPage);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 微淘管理广告
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoPage/wetaoPageAdInfo/list.shtml")
	public ModelAndView wetaoPageAdInfoList(HttpServletRequest request) {
		String rtPage = "wetao/wetaoPageAdInfo_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String wetaoPageId = request.getParameter("wetaoPageId");
		resMap.put("wetaoPageId", wetaoPageId);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 微淘管理广告数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoPage/wetaoPageAdInfo/data.shtml")
	@ResponseBody
	public Map<String, Object> wetaoPageAdInfoData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			WetaoPageAdInfoExample example = new WetaoPageAdInfoExample();
			example.setOrderByClause("IFNULL(t.seq_no,999999999) asc");
			WetaoPageAdInfoExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			String wetaoPageId = request.getParameter("wetaoPageId");
			if(!StringUtil.isEmpty(wetaoPageId)){
				criteria.andWetaoPageIdEqualTo(Integer.parseInt(wetaoPageId));
			}
			totalCount = wetaoPageAdInfoService.countByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<WetaoPageAdInfoCustom> wetaoPageAdInfoCustoms = wetaoPageAdInfoService.selectCustomByExample(example);
			resMap.put("Rows", wetaoPageAdInfoCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 编辑微淘广告页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoPage/toEditWetaoPageAdInfo.shtml")
	public ModelAndView toEditWetaoPageAdInfo(HttpServletRequest request) {
		String rtPage = "wetao/toEditWetaoPageAdInfo";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			WetaoPageAdInfo wetaoPageAdInfo = wetaoPageAdInfoService.selectByPrimaryKey(Integer.parseInt(id));
			if(wetaoPageAdInfo.getLinkType().equals("3")){
				Product product = productService.selectByPrimaryKey(Integer.parseInt(wetaoPageAdInfo.getLinkValue()));
				wetaoPageAdInfo.setLinkValue(product.getCode());
			}else if(wetaoPageAdInfo.getLinkType().equals("10")){
				MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(wetaoPageAdInfo.getLinkValue()));
				wetaoPageAdInfo.setLinkValue(mchtInfo.getMchtCode());
			}
			resMap.put("wetaoPageAdInfo", wetaoPageAdInfo);
		}
		String wetaoPageId = request.getParameter("wetaoPageId");
		resMap.put("wetaoPageId", wetaoPageId);
		/*resMap.put("linkTypeList", DataDicUtil.getStatusList("BU_WETAO_PAGE_AD_INFO", "LINK_TYPE"));*/
		
		BrandteamTypeExample e = new BrandteamTypeExample();//新品牌团
		e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(e);
		resMap.put("brandteamTypes", brandteamTypes);

		//商城一级分类
		MallCategoryExample mallCategoryExample = new MallCategoryExample();
		mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MallCategory> mallCategorys = mallCategoryService.selectByExample(mallCategoryExample );
		resMap.put("mallCategorys", mallCategorys);

		ProductTypeExample pte = new ProductTypeExample();//一级类目
		ProductTypeExample.Criteria ptec = pte.createCriteria();
		ptec.andDelFlagEqualTo("0");
		ptec.andTLevelEqualTo(1);
		ptec.andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes", productTypes);
		return new ModelAndView(rtPage,resMap);
	}
	
	
	/**
	 * 保存微淘广告
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoPage/saveWetaoPageAdInfo.shtml")
	@ResponseBody
	public Map<String, Object> saveWetaoPageAdInfo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			String wetaoPageId = request.getParameter("wetaoPageId");
			String pic = request.getParameter("pic");
			String linkType = request.getParameter("linkType");
			String linkValue = request.getParameter("linkValue");
			WetaoPageAdInfo wetaoPageAdInfo = new WetaoPageAdInfo();
			if(!StringUtils.isEmpty(id)){
				wetaoPageAdInfo = wetaoPageAdInfoService.selectByPrimaryKey(Integer.parseInt(id));
			}else{
				wetaoPageAdInfo.setWetaoPageId(Integer.parseInt(wetaoPageId));
				wetaoPageAdInfo.setDelFlag("0");
				wetaoPageAdInfo.setCreateDate(new Date());
				wetaoPageAdInfo.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				wetaoPageAdInfo.setStatus("0");
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("upDate")) ){
				wetaoPageAdInfo.setUpDate(dateFormat.parse(request.getParameter("upDate")+":00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("downDate")) ){
				wetaoPageAdInfo.setDownDate(dateFormat.parse(request.getParameter("downDate")+":59"));
			}
			wetaoPageAdInfo.setPic(pic);
			wetaoPageAdInfo.setLinkType(linkType);
			wetaoPageAdInfo.setLinkValue(linkValue);
			if ("1".equals(linkType)){
				ActivityAreaExample activityAreaExample =new ActivityAreaExample();
				ActivityAreaExample.Criteria activityAreaCriteria=activityAreaExample.createCriteria();
//				activityAreaCriteria.andDelFlagEqualTo("0").andSourceEqualTo("1").andStatusEqualTo("1").andIdEqualTo(Integer.parseInt(linkValue));
				activityAreaCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(linkValue));
				Integer totalCount=activityAreaService.countByExample(activityAreaExample);
				if (totalCount==0){
					resMap.put("returnCode", "4004");
//					resMap.put("returnMsg", "会场不存在或未启用！");
					resMap.put("returnMsg", "会场不存在");
					return resMap;
				}
			}else if ("2".equals(linkType)){
				ActivityExample activityExample =new ActivityExample();
				ActivityExample.Criteria activityCriteria=activityExample.createCriteria();
//				activityCriteria.andDelFlagEqualTo("0").andStatusEqualTo("6").andIdEqualTo(Integer.parseInt(linkValue));
				activityCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(linkValue));
				Integer totalCount=activityService.countByExample(activityExample);
				if (totalCount==0){
					resMap.put("returnCode", "4004");
//					resMap.put("returnMsg", "活动不存在或未审核通过！");
					resMap.put("returnMsg", "活动不存在");
					return resMap;
				}
			}else if ("3".equals(linkType)){//3.商品，存商品ID
				ProductExample e = new ProductExample();
				e.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(linkValue);
				List<Product> products = productService.selectByExample(e);
				if(products==null || products.size()==0){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "商品不存在！");
					return resMap;
				}else{
					wetaoPageAdInfo.setLinkValue(products.get(0).getId().toString());
				}
//				ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(Integer.parseInt(linkValue));
//				if(productCustom==null || !"0".equals(productCustom.getDelFlag())){
//					resMap.put("returnCode", "4004");
//					resMap.put("returnMsg", "商品不存在！");
//					return resMap;
//				}else{
//					String productActivityStatus=productCustom.getProductActivityStatus();
//					if (!"2".equals(productActivityStatus) && !"3".equals(productActivityStatus) && !"4".equals(productActivityStatus)){
//						resMap.put("returnCode", "4004");
//						resMap.put("returnMsg", "请检查商品的报名活动状态！");
//						return resMap;
//					}
//				}
			}else if(linkType.equals("10")){//10.商家店铺，存商家ID
				MchtInfoExample mie = new MchtInfoExample();
				mie.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(linkValue).andStatusEqualTo("1");
				List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mie);
				if(mchtInfos.size()<= 0){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "商家序号有误，请输入正确的商家序号");
					return resMap;
				}else{
					wetaoPageAdInfo.setLinkValue(mchtInfos.get(0).getId().toString());
				}
			}else if("12".equals(linkType)){//判断微淘频道
				WetaoChannelExample weitaoChannelExample = new WetaoChannelExample() ;
				weitaoChannelExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(linkValue));
				int totalCount = weitaoChannelService.countByExample(weitaoChannelExample);
				if (totalCount==0){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "微淘频道不存在或未启用！");
					return resMap;
				}
			}else if(linkType.equals("29")){//优惠券ID
				CouponExample example = new CouponExample();
				CouponExample.Criteria c = example.createCriteria();
				c.andDelFlagEqualTo("0");
				/*c.andStatusEqualTo("1");*/
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
							resMap.put("returnMsg", "优惠券已失效");
						}
					}else if(coupon.getExpiryType().equals("2")){//2.相对时间
						Date last = DateUtil.getDateAfter(coupon.getRecEndDate(), coupon.getExpiryDays());
						if(now.after(last)){
							resMap.put("returnCode", "4004");
							resMap.put("returnMsg", "优惠券已失效");
						}
					}
					
				}*/
			}
			wetaoPageAdInfo.setUpdateDate(new Date());
			wetaoPageAdInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(!StringUtils.isEmpty(id)){
				wetaoPageAdInfoService.updateByPrimaryKeySelective(wetaoPageAdInfo);
			}else{
				wetaoPageAdInfoService.insertSelective(wetaoPageAdInfo);
			}
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
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
	@RequestMapping(value = "/wetaoPage/changeAdInfoStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeAdInfoStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			WetaoPageAdInfo wetaoPageAdInfo = wetaoPageAdInfoService.selectByPrimaryKey(Integer.parseInt(id));
			if(wetaoPageAdInfo.getStatus().equals("0")){
				wetaoPageAdInfo.setStatus("1");
			}else{
				wetaoPageAdInfo.setStatus("0");
			}
			wetaoPageAdInfo.setUpdateDate(new Date());
			wetaoPageAdInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			wetaoPageAdInfoService.updateByPrimaryKeySelective(wetaoPageAdInfo);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 保存排序值
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoPage/saveAdInfoSeqNo.shtml")
	@ResponseBody
	public Map<String, Object> saveAdInfoSeqNo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			WetaoPageAdInfo wetaoPageAdInfo = wetaoPageAdInfoService.selectByPrimaryKey(Integer.parseInt(id));
			if(!StringUtils.isEmpty(seqNo)){
				wetaoPageAdInfo.setSeqNo(Integer.parseInt(seqNo));
			}
			wetaoPageAdInfo.setUpdateDate(new Date());
			wetaoPageAdInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			wetaoPageAdInfoService.updateByPrimaryKeySelective(wetaoPageAdInfo);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 删除微淘广告
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/wetaoPage/deleteAdInfo.shtml")
	@ResponseBody
	public Map<String, Object> deleteAdInfo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			WetaoPageAdInfo wetaoPageAdInfo = new WetaoPageAdInfo();
			wetaoPageAdInfo.setDelFlag("1");
			wetaoPageAdInfo.setUpdateDate(new Date());
			wetaoPageAdInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			WetaoPageAdInfoExample example = new WetaoPageAdInfoExample();
			example.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(id));
			wetaoPageAdInfoService.updateByExampleSelective(wetaoPageAdInfo, example);
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
	@RequestMapping(value = "/wetaoPage/toDesign.shtml")
	public ModelAndView toDesign(HttpServletRequest request) {
		String rtPage = "wetao/toDesign";
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
	@RequestMapping(value = "/wetaoPage/editDecorateModule.shtml")
	public ModelAndView editDecorateModule(HttpServletRequest request) {
		String rtPage = "wetao/editDecorateModule";
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
					}else if(moduleMap.getLinkType().equals("13")){//商家店铺
						MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(moduleMap.getLinkValue());
						if(mchtInfo!=null){
							jo.put("linkValue", mchtInfo.getMchtCode());
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
		BrandteamTypeExample e = new BrandteamTypeExample();//新品牌团
		e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(e);
		resMap.put("brandteamTypes", brandteamTypes);
		
		MallCategoryExample mallCategoryExample=new MallCategoryExample();
		mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MallCategory> mallCategories=mallCategoryService.selectByExample(mallCategoryExample);
		resMap.put("mallCategorys", mallCategories);
		
		resMap.put("decorateInfoId", decorateInfoId);
		resMap.put("decorateAreaId", decorateAreaId);
		resMap.put("decorateModuleId", decorateModuleId);
		resMap.put("moduleType", moduleType);
		resMap.put("seqNo", seqNo);
		return new ModelAndView(rtPage,resMap);
	}
	
	
}
