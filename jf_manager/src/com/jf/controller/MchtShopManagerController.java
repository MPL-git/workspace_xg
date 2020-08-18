package com.jf.controller;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.ShopStatusLogMapper;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.CustomPage;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateAreaCustom;
import com.jf.entity.DecorateAreaExample;
import com.jf.entity.DecorateModule;
import com.jf.entity.DecorateModuleCustom;
import com.jf.entity.DecorateModuleExample;
import com.jf.entity.MallCategorySub;
import com.jf.entity.MallCategorySubCustom;
import com.jf.entity.MallCategorySubExample;
import com.jf.entity.MallCategoryTop;
import com.jf.entity.MallCategoryTopCustom;
import com.jf.entity.MallCategoryTopExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.MchtProductType;
import com.jf.entity.MchtProductTypeExample;
import com.jf.entity.MchtSupplierUser;
import com.jf.entity.MchtSupplierUserCustom;
import com.jf.entity.MchtSupplierUserExample;
import com.jf.entity.ModuleMap;
import com.jf.entity.ModuleMapExample;
import com.jf.entity.Product;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductCustomExample;
import com.jf.entity.ProductExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.ShopStatusLog;
import com.jf.entity.ShopStatusLogExample;
import com.jf.entity.SpOfficeCustom;
import com.jf.entity.SpOfficeCustomExample;
import com.jf.entity.SysStaffInfoCustom;
import com.jf.entity.SysStaffInfoCustomExample;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityService;
import com.jf.service.CustomPageService;
import com.jf.service.DecorateAreaService;
import com.jf.service.DecorateModuleService;
import com.jf.service.MallCategorySubService;
import com.jf.service.MallCategoryTopService;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtProductTypeService;
import com.jf.service.MchtSupplierUserService;
import com.jf.service.ModuleMapService;
import com.jf.service.PlatformContactService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;
import com.jf.service.SpOfficeService;
import com.jf.service.SpUserService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.SysStaffProductTypeService;
import com.jf.vo.Page;

@Controller
public class MchtShopManagerController extends BaseController {
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ProductBrandService productBrandService;
	
	@Resource
	private MallCategoryTopService mallCategoryTopService;
	
	@Resource
	private MallCategorySubService mallCategorySubService;
	
	@Resource
	private DecorateAreaService decorateAreaService;
	
	@Resource
	private DecorateModuleService decorateModuleService;
	
	@Resource
	private ShopStatusLogMapper shopStatusLogMapper;
	
	@Resource
	private ModuleMapService moduleMapService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private ActivityAreaService activityAreaService;
	
	@Resource
	private CustomPageService customPageService;
	
	@Resource
	private SpUserService spUserService;
	
	@Resource
	private MchtSupplierUserService mchtSupplierUserService;
	
	@Resource
	private SpOfficeService spOfficeService;
	
	@Resource
	private PlatformContactService platformContactService;
	
	@Resource
	private SysStaffProductTypeService sysStaffProductTypeService;
	
	@Resource
	private SysStaffInfoService sysStaffInfoService;
	
	@Resource
	private MchtProductTypeService mchtProductTypeService;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 商家店铺管理列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/list.shtml")
	public ModelAndView mchtShopManagerList(HttpServletRequest request) {
		String rtPage = "/mchtShopManager/mchtShopManagerList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		Integer staffId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		
		resMap.put("productTypes", sysStaffProductTypeService.selectByStaffId(staffId));
		
		//对接人的下拉选项：（我可查看的人员，及他们的下级人员 比如：我可查看小李，而小李可以查看小王和小陈）
		String isManagement = this.getSessionStaffBean(request).getIsManagement();//管理层
		resMap.put("isManagement", isManagement);
		String staffID = this.getSessionStaffBean(request).getStaffID();
		resMap.put("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		resMap.put("sysStaffInfoCustomList", sysStaffInfoCustomList);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 商家店铺管理列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/data.shtml")
	@ResponseBody
	public Map<String, Object> mchtShopManagerData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MchtInfoCustomExample example = new MchtInfoCustomExample();
			example.setOrderByClause("id desc");
			MchtInfoCustomExample.MchtInfoCustomCriteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andStatusEqualTo("1");
//			criteria.andShopStatusEqualTo("1");
			String mchtCode = request.getParameter("mchtCode");
			String name = request.getParameter("name");
			String productTypeId = request.getParameter("productTypeId");
			String shopStatus = request.getParameter("shopStatus");
			String activityAuthStatus = request.getParameter("activityAuthStatus");
			if(!StringUtil.isEmpty(mchtCode)){
				criteria.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtil.isEmpty(name)){
				criteria.andMchtNameLike(name);
			}
			if(!StringUtil.isEmpty(productTypeId)){
				criteria.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}
			if(!StringUtil.isEmpty(shopStatus)){
				criteria.andShopStatusEqualTo(shopStatus);
			}
			if(!StringUtil.isEmpty(activityAuthStatus)){
				criteria.andActivityAuthStatusEqualTo(activityAuthStatus);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("platformContactId"))) {
				Integer platformContactId = Integer.valueOf(request.getParameter("platformContactId"));
				//我对接的商家/我协助的商家
				criteria.andPlatformContactIdEqualTo(platformContactId);
			}
			
			totalCount = mchtInfoService.countMchtShopManagerList(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<HashMap<String, Object>> list = mchtInfoService.selectMchtShopManagerList(example);
			resMap.put("Rows", list);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 开通店铺页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/toAddMchtShop.shtml")
	public ModelAndView toAddMchtShop(HttpServletRequest request) {
		String rtPage = "/mchtShopManager/toAddMchtShop";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 开通活动权限页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/toAddActivityAuth.shtml")
	public ModelAndView toAddActivityAuth(HttpServletRequest request) {
		String rtPage = "/mchtShopManager/toAddActivityAuth";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 开通/关闭店铺
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/getMchtInfoByMchtCode.shtml")
	@ResponseBody
	public Map<String, Object> getMchtInfoByMchtCode(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtCode = request.getParameter("mchtCode");
			String type = request.getParameter("type");
			List<HashMap<String, Object>> list = mchtInfoService.getMchtInfoByMchtCode(mchtCode.trim());
			if(list!=null && list.size()>0){
				HashMap<String, Object> mchtInfo = list.get(0);
				if(mchtInfo.get("id")==null){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "对不起，您填写的商家序号有误。 或商家业务状态异常");
					return resMap;
				}
				if(StringUtils.isEmpty(type)){
					String shopStatus = mchtInfo.get("shop_status").toString();
					if(shopStatus.equals("1")){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "对不起，该商家已开通日常销售。");
						return resMap;
					}
				}else{
					String activityAuthStatus = mchtInfo.get("activity_auth_status").toString();
					if(activityAuthStatus.equals("1")){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "该商家已开通活动权限");
						return resMap;
					}
				}
				resMap.put("mchtCode", mchtCode);
				resMap.put("id", mchtInfo.get("id").toString());
				resMap.put("companyName", mchtInfo.get("company_name").toString());
				resMap.put("shopName", mchtInfo.get("shop_name").toString());
				resMap.put("mchtProductType",mchtInfo.get("mcht_product_type")==null?"":mchtInfo.get("mcht_product_type").toString());
			}else{
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "对不起，您填写的商家序号有误。");
				return resMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 开通/关闭店铺
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/changeMchtShopStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeMchtShopStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtId = request.getParameter("mchtId");
			String type = request.getParameter("type");
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(mchtId));
			Date now = new Date();
			ShopStatusLog ssl = new ShopStatusLog();
			ssl.setDelFlag("0");
			ssl.setCreateDate(now);
			ssl.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			ssl.setMchtId(mchtInfo.getId());
			if(StringUtils.isEmpty(type)){
				if(StringUtils.isEmpty(mchtInfo.getShopStatus())){
					mchtInfo.setShopStatus("1");//开通
				}else{
					if(mchtInfo.getShopStatus().equals("0")){
						mchtInfo.setShopStatus("1");
					}else if(mchtInfo.getShopStatus().equals("1")){
						mchtInfo.setShopStatus("0");
					}
				}
				mchtInfo.setShopStatusDate(now);
				ssl.setStatus(mchtInfo.getShopStatus());
				ssl.setType("0");//0.店铺
			}else{
				if(StringUtils.isEmpty(mchtInfo.getActivityAuthStatus())){
					mchtInfo.setActivityAuthStatus("1");//开通
				}else{
					if(mchtInfo.getActivityAuthStatus().equals("0")){
						mchtInfo.setActivityAuthStatus("1");
					}else if(mchtInfo.getActivityAuthStatus().equals("1")){
						mchtInfo.setActivityAuthStatus("0");
					}
				}
				ssl.setStatus(mchtInfo.getActivityAuthStatus());
				ssl.setType("1");//1.活动
			}
			mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo.setUpdateDate(now);
			mchtInfoService.update(mchtInfo,ssl);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 店铺状态列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/shopStatusLogList.shtml")
	public ModelAndView shopStatusLogList(HttpServletRequest request) {
		String rtPage = "/mchtShopManager/shopStatusLogList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtId = request.getParameter("mchtId");
		resMap.put("mchtId", mchtId);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 店铺状态列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/shopStatusLogData.shtml")
	@ResponseBody
	public Map<String, Object> shopStatusLogData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String mchtId = request.getParameter("mchtId");
			ShopStatusLogExample e = new ShopStatusLogExample();
			e.setOrderByClause("id desc");
			ShopStatusLogExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andMchtIdEqualTo(Integer.parseInt(mchtId));
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			totalCount = shopStatusLogMapper.countByExample(e);
			List<ShopStatusLog> list = shopStatusLogMapper.selectByExample(e);
			resMap.put("Rows", list);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 一级列表装修
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategoryTop/list.shtml")
	public ModelAndView mallCategoryTopList(HttpServletRequest request) {
		String rtPage = "/mchtShopManager/mallCategoryTopList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample pte = new ProductTypeExample();
		ProductTypeExample.Criteria c = pte.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andStatusEqualTo("1");
		c.andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes", productTypes);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 一级列表装修数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategoryTop/data.shtml")
	@ResponseBody
	public Map<String, Object> mallCategoryTopData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MallCategoryTopExample e = new MallCategoryTopExample();
			MallCategoryTopExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			String productTypeId = request.getParameter("productTypeId");
			String remarks = request.getParameter("remarks");
			String status = request.getParameter("status");
			if(!StringUtils.isEmpty(productTypeId)){
				c.andProductTypeIdEqualTo(Integer.parseInt(productTypeId));
			}
			if(!StringUtils.isEmpty(remarks)){
				c.andRemarksEqualTo(remarks);
			}
			if(!StringUtils.isEmpty(status)){
				c.andStatusEqualTo(status);
			}
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			totalCount = mallCategoryTopService.countMallCategoryTopCustomByExample(e);
			List<MallCategoryTopCustom> list = mallCategoryTopService.selectMallCategoryTopCustomByExample(e);
			resMap.put("Rows", list);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 添加/编辑一级列表装修
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategoryTop/toEdit.shtml")
	public ModelAndView toEditMallCategoryTop(HttpServletRequest request) {
		String rtPage = "/mchtShopManager/editMallCategoryTop";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		ProductTypeExample pte = new ProductTypeExample();
		ProductTypeExample.Criteria c = pte.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andStatusEqualTo("1");
		c.andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes", productTypes);
		if(!StringUtils.isEmpty(id)){
			MallCategoryTop mallCategoryTop = mallCategoryTopService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("mallCategoryTop", mallCategoryTop);
		}
		resMap.put("mallCategoryTopId", id);
		return new ModelAndView(rtPage,resMap);
	}

	/**
	 * 保存一级列表装修
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategoryTop/save.shtml")
	@ResponseBody
	public Map<String, Object> mallCategoryTopSave(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String productTypeId = request.getParameter("productTypeId");
			String remarks = request.getParameter("remarks");
			String upDate = request.getParameter("upDate");
			String downDate = request.getParameter("downDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			MallCategoryTop mallCategoryTop = new MallCategoryTop();
			if(StringUtils.isEmpty(id)){
				mallCategoryTop.setDelFlag("0");
				mallCategoryTop.setStatus("0");//默认0.下架
				mallCategoryTop.setCreateDate(new Date());
				mallCategoryTop.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			}else{
				mallCategoryTop = mallCategoryTopService.selectByPrimaryKey(Integer.parseInt(id));
				mallCategoryTop.setUpDate(new Date());
				mallCategoryTop.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			}
			mallCategoryTop.setProductTypeId(Integer.parseInt(productTypeId));
			mallCategoryTop.setRemarks(remarks);
			mallCategoryTop.setUpDate(sdf.parse(upDate));
			mallCategoryTop.setDownDate(sdf.parse(downDate));
			mallCategoryTopService.save(mallCategoryTop);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 一级列表上架/下架
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategoryTop/changeStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			MallCategoryTop mallCategoryTop = mallCategoryTopService.selectByPrimaryKey(Integer.parseInt(id));
			if(mallCategoryTop.getStatus().equals("0")){
				mallCategoryTop.setStatus("1");
			}else{
				mallCategoryTop.setStatus("0");
			}
			mallCategoryTop.setUpdateDate(new Date());
			mallCategoryTop.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mallCategoryTopService.updateByPrimaryKeySelective(mallCategoryTop);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 二级列表配置
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategorySub/list.shtml")
	public ModelAndView mallCategorySubList(HttpServletRequest request) {
		String rtPage = "/mchtShopManager/mallCategorySubList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		ProductTypeExample pte = new ProductTypeExample();
		ProductTypeExample.Criteria c = pte.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andStatusEqualTo("1");
		c.andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes", productTypes);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 二级列表配置数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategorySub/data.shtml")
	@ResponseBody
	public Map<String, Object> mallCategorySubData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MallCategorySubExample e = new MallCategorySubExample();
			e.setOrderByClause("t.seq_no asc,t.id desc");
			MallCategorySubExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			String productTypeId = request.getParameter("productTypeId");
			String name = request.getParameter("name");
			String status = request.getParameter("status");
			if(!StringUtils.isEmpty(productTypeId)){
				c.andProductType1EqualTo(Integer.parseInt(productTypeId));
			}
			if(!StringUtils.isEmpty(name)){
				c.andNameEqualTo(name);
			}
			if(!StringUtils.isEmpty(status)){
				c.andStatusEqualTo(status);
			}
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			totalCount = mallCategorySubService.countMallCategorySubCustomByExample(e);
			List<MallCategorySubCustom> list = mallCategorySubService.selectMallCategorySubCustomByExample(e);
			resMap.put("Rows", list);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 添加/编辑二级列表配置
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategorySub/toEdit.shtml")
	public ModelAndView toEditMallCategorySub(HttpServletRequest request) {
		String rtPage = "/mchtShopManager/editMallCategorySub";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		ProductTypeExample pte = new ProductTypeExample();
		ProductTypeExample.Criteria c = pte.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andStatusEqualTo("1");
		c.andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes", productTypes);
		if(!StringUtils.isEmpty(id)){
			MallCategorySub mallCategorySub = mallCategorySubService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("mallCategorySub", mallCategorySub);
			if(!StringUtils.isEmpty(mallCategorySub.getProductType1())){
				ProductTypeExample secondPte = new ProductTypeExample();
				ProductTypeExample.Criteria secondPtec = secondPte.createCriteria();
				secondPtec.andDelFlagEqualTo("0");
				secondPtec.andParentIdEqualTo(mallCategorySub.getProductType1());
				secondPtec.andStatusEqualTo("1");
				List<ProductType> secondProductTypes = productTypeService.selectByExample(secondPte);
				resMap.put("secondProductTypes", secondProductTypes);
			}
			if(!StringUtils.isEmpty(mallCategorySub.getProductType2Ids())){
				List<Integer> ids = new ArrayList<Integer>();
				String[] productType2IdsArray = mallCategorySub.getProductType2Ids().split(",");
				for(int i=0;i<productType2IdsArray.length;i++){
					if(!ids.contains(Integer.parseInt(productType2IdsArray[i]))){
						ids.add(Integer.parseInt(productType2IdsArray[i]));
					}
				}
				ProductTypeExample thirdPte = new ProductTypeExample();
				ProductTypeExample.Criteria thirdPtec = thirdPte.createCriteria();
				thirdPtec.andDelFlagEqualTo("0");
				thirdPtec.andParentIdIn(ids);
				thirdPtec.andStatusEqualTo("1");
				List<ProductType> thirdProductTypes = productTypeService.selectByExample(thirdPte);
				resMap.put("thirdProductTypes", thirdProductTypes);
			}
			if(!StringUtils.isEmpty(mallCategorySub.getProductBrandIds())){
				String[] productBrandIdsArray = mallCategorySub.getProductBrandIds().split(",");
				ArrayList<Integer> productBrandIdList = new ArrayList<Integer>();
				for(String productBrandIdStr:productBrandIdsArray){
					productBrandIdList.add(Integer.parseInt(productBrandIdStr));
				}
				ProductBrandExample pbe = new ProductBrandExample();
				ProductBrandExample.Criteria pbc = pbe.createCriteria();
				pbc.andDelFlagEqualTo("0");
				pbc.andIdIn(productBrandIdList);
				List<ProductBrand> productBrands = productBrandService.selectByExample(pbe);
				resMap.put("productBrands", productBrands);
			}
		}
		resMap.put("mallCategorySubId", id);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存二级列表配置
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategorySub/save.shtml")
	@ResponseBody
	public Map<String, Object> mallCategorySubSave(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String productType1 = request.getParameter("productType1");
			String productType2Ids = request.getParameter("productType2Ids");
			String productType3Ids = request.getParameter("productType3Ids");
			String productBrandIds = request.getParameter("productBrandIds");
			String suitSex = request.getParameter("suitSex");
			MallCategorySub mallCategorySub = new MallCategorySub();
			if(!StringUtils.isEmpty(id)){
				mallCategorySub = mallCategorySubService.selectByPrimaryKey(Integer.parseInt(id));
				mallCategorySub.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mallCategorySub.setUpdateDate(new Date());
			}else{
				mallCategorySub.setDelFlag("0");
				mallCategorySub.setStatus("0");
				mallCategorySub.setCreateDate(new Date());
				mallCategorySub.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				MallCategorySubExample e = new MallCategorySubExample();
				MallCategorySubExample.Criteria c = e.createCriteria();
				c.andDelFlagEqualTo("0");
				int totalCount = mallCategorySubService.countByExample(e);
				if(totalCount == 0){
					mallCategorySub.setSeqNo(1);
				}else{
					int maxSeqNo = mallCategorySubService.getMaxSeqNo();
					mallCategorySub.setSeqNo(maxSeqNo+1);
				}
			}
			mallCategorySub.setName(name);
			mallCategorySub.setProductType1(Integer.parseInt(productType1));
			mallCategorySub.setProductType2Ids(productType2Ids);
			mallCategorySub.setProductType3Ids(productType3Ids);
			mallCategorySub.setProductBrandIds(productBrandIds);
			mallCategorySub.setSuitSex(suitSex);
			mallCategorySubService.save(mallCategorySub);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 二级列表上架/下架
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategorySub/changeStatus.shtml")
	@ResponseBody
	public Map<String, Object> mallCategorySubChangeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			MallCategorySub mallCategorySub = mallCategorySubService.selectByPrimaryKey(Integer.parseInt(id));
			if(mallCategorySub.getStatus().equals("0")){
				mallCategorySub.setStatus("1");
			}else{
				mallCategorySub.setStatus("0");
			}
			mallCategorySub.setUpdateDate(new Date());
			mallCategorySub.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mallCategorySubService.updateByPrimaryKeySelective(mallCategorySub);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 二级列表排序保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategorySub/saveSeqNo.shtml")
	@ResponseBody
	public Map<String, Object> mallCategorySubSaveSeqNo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			MallCategorySub mallCategorySub = mallCategorySubService.selectByPrimaryKey(Integer.parseInt(id));
			mallCategorySub.setUpdateDate(new Date());
			mallCategorySub.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mallCategorySubService.saveSeqNo(mallCategorySub,Integer.parseInt(seqNo));
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 一二级装修页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategory/design.shtml")
	public ModelAndView design(HttpServletRequest request) {
		String rtPage = "/mchtShopManager/designMallCategory";
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
			dme.setOrderByClause("t.seq_no asc");
			DecorateModuleExample.Criteria dmec = dme.createCriteria();
			dmec.andDelFlagEqualTo("0");
			dmec.andDecorateAreaIdEqualTo(decorateAreas.get(0).getId());
			List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
			resMap.put("decorateModuleCustoms", decorateModuleCustoms);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 添加/编辑图片模块
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategory/editDecorateModule.shtml")
	public ModelAndView editDecorateModule(HttpServletRequest request) {
		String rtPage = "/mchtShopManager/editDecorateModule";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		String decorateAreaId = request.getParameter("decorateAreaId");
		String moduleType = request.getParameter("moduleType");
		String seqNo = request.getParameter("seqNo");
		String decorateModuleId = request.getParameter("decorateModuleId");
		if(!StringUtils.isEmpty(decorateModuleId)){
			DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(decorateModuleId));
			resMap.put("decorateModule", decorateModule);
			if(decorateModule.getModuleType().equals("1") || decorateModule.getModuleType().equals("8") || decorateModule.getModuleType().equals("9")){//1.图片
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
						jo.put("linkValue", moduleMap.getLinkValue());
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
			if(moduleType.equals("1") || moduleType.equals("9")){
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
	
	/**
	 * 检验热区id是否存在
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategory/checkExists.shtml")
	@ResponseBody
	public Map<String, Object> checkExists(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String linkType = request.getParameter("linkType");
			String linkValue = request.getParameter("linkValue");
			if(linkType.equals("1")){//1.会场
				ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(linkValue));
				if(activityArea == null){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "ID不存在，保存失败，请稍后重试");
				}
			}else if(linkType.equals("2")){//2.特卖
				Activity activity = activityService.selectByPrimaryKey(Integer.parseInt(linkValue));
				if(activity == null){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "ID不存在，保存失败，请稍后重试");
				}
			}else if(linkType.equals("3")){//3.商品
				ProductExample pe = new ProductExample();
				ProductExample.Criteria pec = pe.createCriteria();
				pec.andDelFlagEqualTo("0");
				pec.andCodeEqualTo(linkValue);
				List<Product> products = productService.selectByExample(pe);
				if(products == null || products.size() == 0){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "ID不存在，保存失败，请稍后重试");
				}
			}else if(linkType.equals("4")){//4.自建页面
				CustomPage customPage = customPageService.selectByPrimaryKey(Integer.parseInt(linkValue));
				if(customPage == null){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "ID不存在，保存失败，请稍后重试");
				}
			}else if(linkType.equals("6")){//6.商城二级列表ID
				MallCategorySub mallCategorySub = mallCategorySubService.selectByPrimaryKey(Integer.parseInt(linkValue));
				if(mallCategorySub == null){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "ID不存在，保存失败，请稍后重试");
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
	
	/**
	 * 一二级列表预览
	 * @Title    
	 * @Description    
	 */
	@RequestMapping(value = "/mchtShopManager/mallCategory/preview.shtml")
	public ModelAndView previewMallCategory(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		String decorateInfoId = request.getParameter("decorateInfoId");
		String mallCategorySubId = request.getParameter("mallCategorySubId");
		if(!StringUtils.isEmpty(mallCategorySubId)){
			MallCategorySub mallCategorySub = mallCategorySubService.selectByPrimaryKey(Integer.parseInt(mallCategorySubId));
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			if(mallCategorySub.getId()!=null){
				List<Integer> productType1Ids = new ArrayList<Integer>();
				productType1Ids.add(mallCategorySub.getProductType1());
				List<Integer> productType2Ids = new ArrayList<Integer>();
				if(!StringUtils.isEmpty(mallCategorySub.getProductType2Ids())){
					String[] productType2IdsArray = mallCategorySub.getProductType2Ids().trim().split(",");
					for(int i=0;i<productType2IdsArray.length;i++){
						productType2Ids.add(Integer.parseInt(productType2IdsArray[i]));
					}
				}
				if(!StringUtils.isEmpty(mallCategorySub.getProductBrandIds())){
					List<Integer> productBrandIds = new ArrayList<Integer>();
					String[] productBrandIdsArray = mallCategorySub.getProductBrandIds().trim().split(",");
					for(int i=0;i<productBrandIdsArray.length;i++){
						productBrandIds.add(Integer.parseInt(productBrandIdsArray[i]));
					}
					paramMap.put("productBrandIds", productBrandIds);
				}
				if(productType1Ids!=null && productType1Ids.size()>0){
					paramMap.put("productType1Ids", productType1Ids);
				}
				if(productType2Ids!=null && productType2Ids.size()>0){
					paramMap.put("productType2Ids", productType2Ids);
				}
				List<Integer> productIds = productService.getProductIds(paramMap);
				ProductCustomExample pce = new ProductCustomExample();
				ProductCustomExample.ProductCustomCriteria pcec = pce.createCriteria();
				pcec.andDelFlagEqualTo("0");
				if(productIds!=null && productIds.size()>0){
					pcec.andIdIn(productIds);
				}
				pcec.andStatusEqualTo("1");
				pcec.andProductActivityStatusEqualTo("4");//活动中
				List<ProductCustom> productCustoms = productService.selectProductCustomByExample(pce);
				m.addObject("productCustoms", productCustoms);
			}
		}
		DecorateAreaExample dae = new DecorateAreaExample();
		dae.setOrderByClause("t.seq_no asc");
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateAreaCustom> decorateAreaCustoms = decorateAreaService.selectDecorateAreaCustomByExample(dae);
		for(DecorateAreaCustom decorateAreaCustom:decorateAreaCustoms){
			DecorateModuleExample dme = new DecorateModuleExample();
			dme.setOrderByClause("t.seq_no asc");
			DecorateModuleExample.Criteria dmec = dme.createCriteria();
			dmec.andDelFlagEqualTo("0");
			dmec.andDecorateAreaIdEqualTo(decorateAreaCustom.getId());
			List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
			if(decorateModuleCustoms!=null && decorateModuleCustoms.size()>0){
				for(DecorateModuleCustom decorateModuleCustom:decorateModuleCustoms){
					List<String> pics = new ArrayList<String>();
					if(decorateModuleCustom.getModuleType().equals("1") || decorateModuleCustom.getModuleType().equals("8") || decorateModuleCustom.getModuleType().equals("9")){//图片
						pics.add(decorateModuleCustom.getPic());
						decorateModuleCustom.setPics(pics);
					}
				}
			}
			decorateAreaCustom.setDecorateModuleCustoms(decorateModuleCustoms);
		}
		m.addObject("decorateAreaCustoms", decorateAreaCustoms);
		m.setViewName("/mchtShopManager/previewMallCategory");
		return m;
	}
	
	/**
	 * 商品排序列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/productSort/list.shtml")
	public ModelAndView productSortList(HttpServletRequest request) {
		String rtPage = "/mchtShopManager/productSortList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 商品排序列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/productSort/data.shtml")
	@ResponseBody
	public Map<String, Object> productSortData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			ProductCustomExample pce = new ProductCustomExample();
			pce.setOrderByClause("t.weights desc,t.id desc");
			ProductCustomExample.ProductCustomCriteria pcc = pce.createCriteria();
			pcc.andDelFlagEqualTo("0");
//			pcc.andIsActivityEqualTo("0");
			pcc.andAuditStatusEqualTo("2");
			pcc.andMchtInfoStatusEqualTo("1");
			pcc.andSaleTypeEqualTo("1");
			String suitSex = request.getParameter("suitSex");
			String suitGroup = request.getParameter("suitGroup");
			String status = request.getParameter("status");
			String isShow = request.getParameter("isShow");
			String mallPriceMin = request.getParameter("mallPriceBegin");
			String mallPriceMax = request.getParameter("mallPriceEnd");
			String productName = request.getParameter("name");
			String productCode = request.getParameter("code");
			String productId = request.getParameter("id");
			String mchtCode = request.getParameter("mchtCode");
			String mchtName = request.getParameter("mchtName");
			String brandName = request.getParameter("productBrandName");
			String artNo = request.getParameter("artNo");
			String weights = request.getParameter("weights");
			String seasonWeight = request.getParameter("seasonWeight");
			String saleWeight = request.getParameter("saleWeight");
			String pvWeight = request.getParameter("pvWeight");
		 	String mchtGradeWeight = request.getParameter("mchtGradeWeight");
			String brandGradeWeight = request.getParameter("brandGradeWeight");
			String manualWeight = request.getParameter("manualWeight");
			String saleAmountWeight = request.getParameter("saleAmountWeight");
			String commentWeight = request.getParameter("commentWeight");
			String productType = request.getParameter("productType");
			if(!StringUtils.isEmpty(suitSex)){
				if(suitSex.equals("10")){//男
					pcc.andSuitSexEqualToManOrWomen(suitSex);
				}else if(suitSex.equals("01")){//女
					pcc.andSuitSexEqualToManOrWomen(suitSex);
				}else{//男丶女
					pcc.andSuitSexEqualTo(suitSex);
				}
			}
			if(!StringUtils.isEmpty(suitGroup)){
				if(suitGroup.equals("100")){//青年
					pcc.andSuitGroupLike("1%");
				}else if(suitGroup.equals("010") || suitGroup.equals("001")){//儿童，中老年
					pcc.andSuitGroupLikeTo(suitGroup);
				}
			}
			if(!StringUtils.isEmpty(status)){
				pcc.andStatusEqualTo(status);
			}
			if(!StringUtils.isEmpty(isShow)){
				pcc.andIsShowEqualTo(isShow);
			}
			if(!StringUtils.isEmpty(mallPriceMin)){
				pcc.andMallPriceGreaterThanOrEqualTo(new BigDecimal(mallPriceMin));
			}
			if(!StringUtils.isEmpty(mallPriceMax)){
				pcc.andMallPriceLessThanOrEqualTo(new BigDecimal(mallPriceMax));
			}
			if(!StringUtils.isEmpty(productName)){
				pcc.andNameLike("%"+productName+"%");
			}
			if(!StringUtils.isEmpty(productCode)){
				pcc.andCodeEqualTo(productCode);
			}
			if(!StringUtils.isEmpty(productId)){
				pcc.andIdEqualTo(Integer.parseInt(productId));
			}
			if(!StringUtils.isEmpty(mchtCode)){
				pcc.andMchtCodeEqualTo(mchtCode);
			}
			if(!StringUtils.isEmpty(mchtName)){
				pcc.andMchtNameLikeTo("%"+mchtName+"%");
			}
			if(!StringUtils.isEmpty(brandName)){
				pcc.andBrandNameEqualTo(brandName);
			}
			if(!StringUtils.isEmpty(artNo)){
				pcc.andArtNoLike("%"+artNo+"%");
			}
			if(!StringUtils.isEmpty(weights)){
				pcc.andWeightsEqualTo(Integer.parseInt(weights));
			}
			if(!StringUtils.isEmpty(seasonWeight)){
				pcc.andSeasonWeightEqualTo(Integer.parseInt(seasonWeight));
			}
			if(!StringUtils.isEmpty(saleWeight)){
				pcc.andSaleWeightEqualTo(Integer.parseInt(saleWeight));
			}
			if(!StringUtils.isEmpty(pvWeight)){
				pcc.andPvWeightEqualTo(Integer.parseInt(pvWeight));
			}
			if(!StringUtils.isEmpty(mchtGradeWeight)){
				pcc.andMchtGradeWeightEqualTo(Integer.parseInt(mchtGradeWeight));
			}
			if(!StringUtils.isEmpty(brandGradeWeight)){
				pcc.andBrandGradeWeightEqualTo(Integer.parseInt(brandGradeWeight));
			}
			if(!StringUtils.isEmpty(manualWeight)){
				pcc.andManualWeightEqualTo(Integer.parseInt(manualWeight));
			}
			if(!StringUtils.isEmpty(saleAmountWeight)){
				pcc.andSaleAmountWeightEqualTo(Integer.parseInt(saleAmountWeight));
			}
			if(!StringUtils.isEmpty(commentWeight)){
				pcc.andCommentWeightEqualTo(Integer.parseInt(commentWeight));
			}
			if(!StringUtil.isEmpty(productType)){
				pcc.andProductTypeAll(productService.getProductTypeAllChild(Integer.valueOf(request.getParameter("productType"))));
			}
			totalCount = productService.countProductCustomByExample(pce);
			pce.setLimitStart(page.getLimitStart());
			pce.setLimitSize(page.getLimitSize());
			List<HashMap<String, Object>> list = productService.getProductSortList(pce);
			resMap.put("Rows", list);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 获取三级商品分类
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/getThirdProductTypes.shtml")
	@ResponseBody
	public Map<String, Object> getThirdProductTypes(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String productTypeIds = request.getParameter("productTypeIds");
			List<Integer> productTypeIdsList = new ArrayList<Integer>();
			if(!StringUtils.isEmpty(productTypeIds)){
				String[] productTypeIdsArray = productTypeIds.split(",");
				for(int i=0;i<productTypeIdsArray.length;i++){
					if(!productTypeIdsList.contains(Integer.parseInt(productTypeIdsArray[i]))){
						productTypeIdsList.add(Integer.parseInt(productTypeIdsArray[i]));
					}
				}
			}
			ProductTypeExample pte = new ProductTypeExample();
			ProductTypeExample.Criteria ptec = pte.createCriteria();
			ptec.andDelFlagEqualTo("0");
			ptec.andParentIdIn(productTypeIdsList);
			ptec.andStatusEqualTo("1");
			List<ProductType> thirdProductTypes = productTypeService.selectByExample(pte);
			resMap.put("thirdProductTypes", thirdProductTypes);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 开通/关闭供应链
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/changeSupplyChainStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeSupplyChainStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtId = request.getParameter("mchtId");
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(mchtId));
			Date now = new Date();
			ShopStatusLog ssl = new ShopStatusLog();
			ssl.setDelFlag("0");
			ssl.setCreateDate(now);
			ssl.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			ssl.setMchtId(mchtInfo.getId());
			if(StringUtils.isEmpty(mchtInfo.getSupplyChainStatus())){
				mchtInfo.setSupplyChainStatus("1");//开通
			}else{
				if(mchtInfo.getSupplyChainStatus().equals("0")){
					mchtInfo.setSupplyChainStatus("1");
				}else if(mchtInfo.getSupplyChainStatus().equals("1")){
					mchtInfo.setSupplyChainStatus("0");
				}
			}
			ssl.setStatus(mchtInfo.getSupplyChainStatus());
			ssl.setType("2");//2.供应链
			mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo.setUpdateDate(now);
			mchtInfoService.update(mchtInfo,ssl);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 修改商家的供应商列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/toViewSupplyList.shtml")
	public ModelAndView toViewSupplyList(HttpServletRequest request) {
		String rtPage = "mchtShopManager/toViewSupplyList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtId = request.getParameter("mchtId");
		resMap.put("mchtId", mchtId);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 修改商家的供应商列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/toViewSupplyData.shtml")
	@ResponseBody
	public Map<String, Object> toViewSupplyData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String mchtId = request.getParameter("mchtId");
			MchtSupplierUserExample e = new MchtSupplierUserExample();
			MchtSupplierUserExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andMchtIdEqualTo(Integer.parseInt(mchtId));
			totalCount = mchtSupplierUserService.countMchtSupplierUserCustomByExample(e);
			e.setOrderByClause("t.status asc,t.id desc");
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getPagesize());
			List<MchtSupplierUserCustom> mchtSupplierUserCustoms = mchtSupplierUserService.selectMchtSupplierUserCustomByExample(e);
			resMap.put("Rows", mchtSupplierUserCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 修改供应商合作状态页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/toEditStatus.shtml")
	public ModelAndView toEditStatus(HttpServletRequest request) {
		String rtPage = "mchtShopManager/toEditStatus";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtSupplierUserId = request.getParameter("mchtSupplierUserId");
		MchtSupplierUser mchtSupplierUser = mchtSupplierUserService.selectByPrimaryKey(Integer.parseInt(mchtSupplierUserId));
		resMap.put("mchtSupplierUser", mchtSupplierUser);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 修改供应商合作状态
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/editStatus.shtml")
	@ResponseBody
	public Map<String, Object> editStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtSupplierUserId = request.getParameter("mchtSupplierUserId");
			String status = request.getParameter("status");
			MchtSupplierUser mchtSupplierUser = mchtSupplierUserService.selectByPrimaryKey(Integer.parseInt(mchtSupplierUserId));
			mchtSupplierUser.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtSupplierUser.setUpdateDate(new Date());
			mchtSupplierUser.setStatus(status);
			mchtSupplierUserService.updateByPrimaryKeySelective(mchtSupplierUser);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 添加供应商列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/toAddSupplyList.shtml")
	public ModelAndView toAddSupplyList(HttpServletRequest request) {
		String rtPage = "mchtShopManager/toAddSupplyList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtId = request.getParameter("mchtId");
		resMap.put("mchtId", mchtId);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 添加供应商列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/toAddSupplyData.shtml")
	@ResponseBody
	public Map<String, Object> toAddSupplyData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String companyName = request.getParameter("companyName");
			if(StringUtils.isEmpty(companyName)){
				return resMap;
			}
			SpOfficeCustomExample e = new SpOfficeCustomExample();
			SpOfficeCustomExample.SpOfficeCustomCriteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andStatusEqualTo("1");
			c.andNameLike("%"+companyName+"%");
			e.setOrderByClause("t.id asc");
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<SpOfficeCustom> spOfficeCustoms = spOfficeService.selectByCustomExample(e);
			resMap.put("Rows", spOfficeCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 添加供应商
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/addSupply.shtml")
	@ResponseBody
	public Map<String, Object> addSupply(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtId = request.getParameter("mchtId");
			String supplierUserId = request.getParameter("supplierUserId");
			MchtSupplierUserExample e = new MchtSupplierUserExample();
			e.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(Integer.parseInt(mchtId)).andSpOfficeIdEqualTo(Integer.parseInt(supplierUserId));
			List<MchtSupplierUser> mchtSupplierUsers = mchtSupplierUserService.selectByExample(e);
			if(mchtSupplierUsers!=null && mchtSupplierUsers.size()>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "该供应商已添加，不可重复添加。");
			}else{
				MchtSupplierUser mchtSupplierUser = new MchtSupplierUser();
				mchtSupplierUser.setDelFlag("0");
				mchtSupplierUser.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtSupplierUser.setCreateDate(new Date());
				mchtSupplierUser.setMchtId(Integer.parseInt(mchtId));
				mchtSupplierUser.setSpOfficeId(Integer.parseInt(supplierUserId));
				mchtSupplierUser.setStatus("1");
				mchtSupplierUserService.insertSelective(mchtSupplierUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 开通关闭活动页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/toEditActivityStatus.shtml")
	public ModelAndView toEditActivityStatus(HttpServletRequest request) {
		String rtPage = "/mchtShopManager/toEditActivityStatus";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String mchtId = request.getParameter("mchtId");
		if (!StringUtils.isEmpty(request.getParameter("isClose"))) {
			resMap.put("isClose", request.getParameter("isClose"));
		}
		resMap.put("mchtInfo", mchtInfoService.selectByPrimaryKey(Integer.parseInt(mchtId)));
		MchtProductTypeExample mpte = new MchtProductTypeExample();
		mpte.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(Integer.parseInt(mchtId)).andIsMainEqualTo("1").andStatusEqualTo("1");
		List<MchtProductType> mchtProductTypes = mchtProductTypeService.selectByExample(mpte);
		if(mchtProductTypes!=null && mchtProductTypes.size()>0){
			ProductType productType = productTypeService.selectByPrimaryKey(mchtProductTypes.get(0).getProductTypeId());
			resMap.put("productType", productType);
		}
		MchtInfoCustomExample example = new MchtInfoCustomExample();
		example.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(mchtId));
		List<HashMap<String, Object>> list = mchtInfoService.selectMchtShopManagerList(example);
		resMap.put("map", list.get(0));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 开通/关闭活动
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtShopManager/changeActivityStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeActivityStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String mchtId = request.getParameter("mchtId");
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(Integer.parseInt(mchtId));
			Date now = new Date();
			ShopStatusLog ssl = new ShopStatusLog();
			ssl.setDelFlag("0");
			ssl.setCreateDate(now);
			ssl.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			ssl.setMchtId(mchtInfo.getId());
			String speciallyApprovedStatus = request.getParameter("speciallyApprovedStatus");
			String speciallyApprovedRemarks = request.getParameter("speciallyApprovedRemarks");
			String activityAuthStatus = request.getParameter("activityAuthStatus");
			mchtInfo.setSpeciallyApprovedStatus(speciallyApprovedStatus);
			mchtInfo.setSpeciallyApprovedRemarks(speciallyApprovedRemarks);
			mchtInfo.setActivityAuthStatus(activityAuthStatus);
			ssl.setStatus(mchtInfo.getActivityAuthStatus());
			ssl.setType("1");//1.活动
			mchtInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			mchtInfo.setUpdateDate(now);
			mchtInfoService.update(mchtInfo,ssl);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
}
