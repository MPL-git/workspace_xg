package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.constant.Const;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.MyTool;
import com.jf.entity.Activity;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAreaExample;
import com.jf.entity.ActivityExample;
import com.jf.entity.AdInfo;
import com.jf.entity.AdInfoCustom;
import com.jf.entity.AdInfoCustomExample;
import com.jf.entity.AdInfoExample;
import com.jf.entity.AppVersion;
import com.jf.entity.AppVersionCustom;
import com.jf.entity.AppVersionExample;
import com.jf.entity.AppealOrder;
import com.jf.entity.AppealOrderExample;
import com.jf.entity.Attachment;
import com.jf.entity.AttachmentHistory;
import com.jf.entity.BrandteamType;
import com.jf.entity.BrandteamTypeExample;
import com.jf.entity.Coupon;
import com.jf.entity.CouponExample;
import com.jf.entity.CustomPage;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderExample;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateAreaCustom;
import com.jf.entity.DecorateAreaExample;
import com.jf.entity.DecorateModule;
import com.jf.entity.DecorateModuleCustom;
import com.jf.entity.DecorateModuleExample;
import com.jf.entity.Favorites;
import com.jf.entity.FavoritesCustom;
import com.jf.entity.FavoritesExample;
import com.jf.entity.FavoritesProductType;
import com.jf.entity.FavoritesProductTypeExample;
import com.jf.entity.InterventionOrderCustom;
import com.jf.entity.InterventionOrderCustomExample;
import com.jf.entity.MallCategory;
import com.jf.entity.MallCategoryExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MemberFavorItesCustom;
import com.jf.entity.MemberFavorItesCustomExample;
import com.jf.entity.MemberFavorItesCustomExample.MemberFavorItesCustomCriteria;
import com.jf.entity.MemberFeedback;
import com.jf.entity.MemberFeedbackCustom;
import com.jf.entity.MemberFeedbackExample;
import com.jf.entity.MemberFeedbackPic;
import com.jf.entity.MemberFeedbackPicExample;
import com.jf.entity.ModuleMap;
import com.jf.entity.ModuleMapExample;
import com.jf.entity.ModuleNavigation;
import com.jf.entity.ModuleNavigationExample;
import com.jf.entity.PlatformContact;
import com.jf.entity.PlatformContactExample;
import com.jf.entity.Product;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductCustomExample;
import com.jf.entity.ProductExample;
import com.jf.entity.ProductExample.Criteria;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.SourceProductType;
import com.jf.entity.SourceProductTypeExample;
import com.jf.entity.StaffBean;
import com.jf.entity.StateCode;
import com.jf.entity.Style;
import com.jf.entity.StyleExample;
import com.jf.entity.SubOrder;
import com.jf.entity.SubOrderExample;
import com.jf.entity.SysOrganization;
import com.jf.entity.SysOrganizationExample;
import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffInfoExample;
import com.jf.entity.SysStaffProductType;
import com.jf.entity.SysStaffProductTypeExample;
import com.jf.entity.SysStaffRole;
import com.jf.entity.SysStaffRoleExample;
import com.jf.entity.SysStatus;
import com.jf.entity.WoRk;
import com.jf.entity.WorkHistory;
import com.jf.entity.WorkRecord;
import com.jf.service.ActivityAreaService;
import com.jf.service.ActivityService;
import com.jf.service.AdInfoService;
import com.jf.service.AppVersionService;
import com.jf.service.AppealOrderService;
import com.jf.service.AttachmentHistoryService;
import com.jf.service.AttachmentService;
import com.jf.service.BrandteamTypeService;
import com.jf.service.CouponService;
import com.jf.service.CustomPageService;
import com.jf.service.CustomerServiceOrderService;
import com.jf.service.DecorateAreaService;
import com.jf.service.DecorateModuleService;
import com.jf.service.FavoritesProductTypeService;
import com.jf.service.FavoritesService;
import com.jf.service.InterventionOrderService;
import com.jf.service.MallCategoryService;
import com.jf.service.MchtInfoService;
import com.jf.service.MemberFavorItesService;
import com.jf.service.MemberFeedbackPicService;
import com.jf.service.MemberFeedbackService;
import com.jf.service.ModuleMapService;
import com.jf.service.ModuleNavigationService;
import com.jf.service.PlatformContactService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;
import com.jf.service.SourceProductTypeService;
import com.jf.service.StyleService;
import com.jf.service.SubOrderService;
import com.jf.service.SysOrganizationService;
import com.jf.service.SysStaffInfoService;
import com.jf.service.SysStaffProductTypeService;
import com.jf.service.SysStaffRoleService;
import com.jf.service.WoRkHistoryService;
import com.jf.service.WoRkService;
import com.jf.service.WorkRecordService;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

@Controller
public class AppMngController extends BaseController {

	@Value("${CONTEXTPATH}")
	private String apkFilePath;
	@Resource
	private AdInfoService adInfoService;
	
	@Resource
	private MemberFeedbackService memberFeedbackService;
	
	@Resource
	private MemberFeedbackPicService memberFeedbackPicService;

	@Resource
	private FavoritesService favoritesService;

	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private FavoritesProductTypeService favoritesProductTypeService;
	
	@Resource
	private ActivityAreaService activityAreaService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private AppVersionService appVersionService;
	
	@Resource
	private MemberFavorItesService memberfavoritesService;
	
	@Resource
	private DecorateAreaService decorateAreaService;
	
	@Resource
	private DecorateModuleService decorateModuleService;
	
	@Resource
	private ModuleMapService moduleMapService;
	
	@Resource
	private StyleService styleservice;
	
	@Resource
	private PlatformContactService platformContactService;
	
	@Autowired
	private SysOrganizationService sysOrganizationService;
	
	@Autowired
	private SysStaffInfoService sysStaffInfoService;
	
	@Autowired
	private SysStaffRoleService sysStaffRoleService;
	
	@Autowired
	private SysStaffProductTypeService sysStaffProductTypeService;
	
	@Resource
	private WoRkService woRkService;
	
	@Resource
	private WoRkHistoryService woRkHistoryService;
	
	@Resource
	private WorkRecordService workRecordService;
	
	@Resource
	private AttachmentHistoryService attachmentHistoryService;
	
	@Resource
	private AttachmentService attachmentService;
	
	@Resource
	private InterventionOrderService interventionOrderService;
	
	@Resource
	private SourceProductTypeService sourceProductTypeService;

	@Resource
	private BrandteamTypeService brandteamTypeService;
	@Resource
	private CouponService couponService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private ProductBrandService productBrandService;
	
	@Resource
	private MallCategoryService mallCategoryService;
	
	@Resource
	private CustomPageService customPageService;
	
	@Resource
	private ModuleNavigationService moduleNavigationService;

	@Resource
	private AppealOrderService appealOrderService;

	@Resource
	private SubOrderService subOrderService;

	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * APP版本管理列表
	 * 
	 * @param request
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/version/list.shtml")
	public ModelAndView appMngVersionIndex(HttpServletRequest request) {
		String rtPage = "/appMng/version/list";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("isEffectList", DataDicUtil.getStatusList("SYS_APP_VERSION", "IS_EFFECT"));
		resMap.put("isMustList", DataDicUtil.getStatusList("SYS_APP_VERSION", "IS_MUST"));
		resMap.put("sprChnls", DataDicUtil.getStatusList("BU_MEMBER_INFO", "SPR_CHNL"));
		resMap.put("chnlType", request.getParameter("chnlType"));
		
		return new ModelAndView(rtPage,resMap);
	}

	@RequestMapping(value = "/appMng/version/datalist.shtml")
	@ResponseBody
	public Map<String, Object> datalist(HttpServletRequest request, Page page) {

		Map<String, Object> resMap = new HashMap<String, Object>();
		List<AppVersionCustom> dataList = null;
		int totalCount = 0;
		try {
			AppVersionExample appVersionExample=new AppVersionExample();
			AppVersionExample.Criteria appVersionCriteria=appVersionExample.createCriteria();
			appVersionCriteria.andDelFlagEqualTo("0");
			appVersionExample.setOrderByClause("a.create_date desc, a.id desc");
			
			String[] strs={"1001","1002","9999"};
			List<String> sList = Arrays.asList(strs);
			if(!StringUtil.isEmpty(request.getParameter("chnlType")) ){
				String chnlType=request.getParameter("chnlType");
				if ("1".equals(chnlType)){
					appVersionCriteria.andAppTypeEqualTo("2");
					appVersionCriteria.andSprChnlEqualTo("1001");
				}else if ("3".equals(chnlType)){
					appVersionCriteria.andAppTypeEqualTo("1");
					appVersionCriteria.andSprChnlEqualTo("1002");
				}else{
					appVersionCriteria.andAppTypeEqualTo("2");
					appVersionCriteria.andSprChnlNotIn(sList);
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("appVersion")) ){
				Integer appVersion=Integer.valueOf(request.getParameter("appVersion"));
				appVersionCriteria.andAppVersionEqualTo(appVersion);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("appVersionNo")) ){
				String appVersionNo=request.getParameter("appVersionNo");
				appVersionCriteria.andAppVersionNoEqualTo(appVersionNo);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("isEffect")) ){
				String isEffect=request.getParameter("isEffect");
				appVersionCriteria.andIsEffectEqualTo(isEffect);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("isMust")) ){
				String isMust=request.getParameter("isMust");
				appVersionCriteria.andIsMustEqualTo(isMust);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("sprChnl")) ){
				String sprChnl=request.getParameter("sprChnl");
				appVersionCriteria.andSprChnlEqualTo(sprChnl);
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("dateBegin")) ){
				appVersionCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("dateBegin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("dateEnd")) ){
				appVersionCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("dateEnd")+" 23:59:59"));
			}
			
			totalCount = appVersionService.countCustomByExample(appVersionExample);
			appVersionExample.setLimitStart(page.getLimitStart());
			appVersionExample.setLimitSize(page.getLimitSize());
			dataList = appVersionService.selectCustomByExample(appVersionExample);
			for (AppVersionCustom v : dataList) {
				String url = MyTool.trimStr(v.getPackageUrl());
				if (!"".equals(url)) {
					String fileName = url.substring(url.lastIndexOf("/") + 1);
					v.setFileName(fileName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<AppVersionCustom>();
			totalCount = 0;
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * APP版本添加/修改
	 * 
	 * @param request
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/version/edit.shtml")
	public ModelAndView addVersion(HttpServletRequest request) {
		String rtPage = "/appMng/version/edit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		resMap.put("isEffectList", DataDicUtil.getStatusList("SYS_APP_VERSION", "IS_EFFECT"));
		resMap.put("isMustList", DataDicUtil.getStatusList("SYS_APP_VERSION", "IS_MUST"));
		resMap.put("sprChnls", DataDicUtil.getStatusList("BU_MEMBER_INFO", "SPR_CHNL"));
		resMap.put("chnlType", request.getParameter("chnlType"));
		
		if(!StringUtil.isEmpty(request.getParameter("id")) ){
			Integer id=Integer.valueOf(request.getParameter("id"));
			AppVersionCustom version = appVersionService.selectCustomByPrimaryKey(id);
			resMap.put("version", version);
		}
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 检查APP版本是否存在
	 * 
	 * @param request
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/version/check.shtml")
	@ResponseBody
	public Map<String, Object> checkAppVersion(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		Integer appVersion=Integer.valueOf(request.getParameter("appVersion"));
		AppVersionExample appVersionExample=new AppVersionExample();
		AppVersionExample.Criteria appVersionCriteria=appVersionExample.createCriteria();
		appVersionCriteria.andDelFlagEqualTo("0").andAppVersionEqualTo(appVersion);
		
		if(!StringUtil.isEmpty(request.getParameter("id")) ){
			Integer id=Integer.valueOf(request.getParameter("id"));
			appVersionCriteria.andIdNotEqualTo(id);
			
		}
		
		Integer i = appVersionService.countByExample(appVersionExample);
		if (i > 0) {
			resMap.put("returnCode", "4004");
		} else {
			resMap.put("returnCode", "0000");
		}
	
		return resMap;
	}
	
	/**
	 * 检查APP版本号是否存在
	 * 
	 * @param request
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/version/checkNo.shtml")
	@ResponseBody
	public Map<String, Object> checkVersionNo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		String appVersionNo=request.getParameter("appVersionNo");
		AppVersionExample appVersionExample=new AppVersionExample();
		AppVersionExample.Criteria appVersionCriteria=appVersionExample.createCriteria();
		appVersionCriteria.andDelFlagEqualTo("0").andAppVersionNoEqualTo(appVersionNo);
		
		if(!StringUtil.isEmpty(request.getParameter("id")) ){
			Integer id=Integer.valueOf(request.getParameter("id"));
			appVersionCriteria.andIdNotEqualTo(id);
			
		}
		
		Integer i = appVersionService.countByExample(appVersionExample);
		if (i > 0) {
			resMap.put("returnCode", "4004");
		} else {
			resMap.put("returnCode", "0000");
		}
		
		return resMap;
	}
	
	/**
	 * APP版本添加或修改保存
	 * 
	 * @param request
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/version/saveSubmit.shtml")
	public ModelAndView saveVersion(HttpServletRequest request, @RequestParam(value = "imageFile", required = false) MultipartFile file, AppVersion appVersion) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg = "";

		try {
			int staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			if(!StringUtil.isEmpty(request.getParameter("chnlType")) ){
				String chnlType=request.getParameter("chnlType");
				if(!"3".equals(chnlType)){
					int fileSize = (int) file.getSize();
					if (fileSize != 0) {
						String filePath = FileUtil.saveFile(file.getInputStream(), file.getOriginalFilename(), 5, 0);
						String url = apkFilePath + filePath;
						appVersion.setPackageUrl(url);
						appVersion.setPackageSize(fileSize);
					}
				}else{
					appVersion.setPackageUrl("");
					appVersion.setPackageSize(0);
				}
			}
			if (StringUtil.isEmpty(request.getParameter("id"))) {
				appVersion.setCreateBy(staffId);
				appVersion.setCreateDate(new Date());
				appVersionService.insertSelective(appVersion);
			} else {
				appVersion.setUpdateBy(staffId);
				appVersion.setUpdateDate(new Date());
				appVersionService.updateByPrimaryKeySelective(appVersion);
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
	
	/**
	 * APP版本删除
	 * 
	 * @param request
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/version/del.shtml")
	@ResponseBody
	public Map<String, Object> delVersion(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			int staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			String ids = request.getParameter("id");
//			Integer id=Integer.valueOf(request.getParameter("id"));
//			AppVersion appVersion=new AppVersion();
//			appVersion.setId(id);
//			appVersion.setDelFlag("1");
//			appVersion.setUpdateBy(staffId);
//			appVersion.setUpdateDate(new Date());
//			appVersionService.updateByPrimaryKeySelective(appVersion);
			appVersionService.bathcDelete(staffId, ids);
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return resMap;
	}
	
	/**
	 * APP版本下载
	 * 
	 * @param request
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/version/downloadIndex.shtml")
	public ModelAndView downloadUI(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam HashMap<String, Object> map) {
		String rtPage = "/appMng/version/download";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage, resMap);
	}

	private String getFileNameFromUrl(String url) {
		String name = new Long(System.currentTimeMillis()).toString() + ".X";
		int index = url.lastIndexOf("/");
		if (index > 0) {
			name = url.substring(index + 1);
			if (name.trim().length() > 0) {
				return name;
			}
		}
		return name;
	}

	@RequestMapping(value = "/appMng/version/download.shtml")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpURLConnection urlcon;
		String url="";
		String appType=request.getParameter("appType");
		AppVersionExample appVersionExample=new AppVersionExample();
		AppVersionExample.Criteria appVersionCriteria=appVersionExample.createCriteria();
		appVersionCriteria.andDelFlagEqualTo("0").andAppTypeEqualTo(appType).andIsEffectEqualTo("1");
		appVersionExample.setOrderByClause("app_version desc");
		appVersionExample.setLimitSize(1);
		
		List<AppVersion> appVersions=appVersionService.selectByExample(appVersionExample);
		if (appVersions.size()>0){
			AppVersion appVersion=appVersions.get(0);
			url = appVersion.getPackageUrl();
		}
	
		response.setCharacterEncoding("utf-8");
		String fileName = new String(getFileNameFromUrl(url).getBytes("UTF-8"),"iso-8859-1");// 为了解决中文名称乱码问题
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;fileName="+ fileName);

		// get url properties
		URL urlHttp = new URL(url);
		urlcon = (HttpURLConnection) urlHttp.openConnection();
		InputStream is = urlHttp.openStream();
		double filelength = urlcon.getContentLength();
		response.setContentLength((int) filelength);
		// File file=new File(path);
		HttpHeaders headers = new HttpHeaders();

		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		FileCopyUtils.copy(is, response.getOutputStream());// 将图片写到输出流中

		return;

	}

	/**
	 * 广告管理首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/index.shtml")
	public ModelAndView appMngAdMngIndex(HttpServletRequest request) {	
		String rtPage = "/appMng/adMng/index";// 重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value ="/appMng/adMng/list.shtml")
	public ModelAndView appMngAdMngList(HttpServletRequest request) {	
		String rtPage = "/appMng/adMng/list";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("mngPos", request.getParameter("mngPos"));
		resMap.put("catalogs", DataDicUtil.getStatusList("BU_AD_INFO", "CATALOG"));
		resMap.put("positions", DataDicUtil.getStatusList("BU_AD_INFO", "POSITION"));
		if(!StringUtil.isEmpty(request.getParameter("autoUpDate")) ){
			resMap.put("autoUpDate",request.getParameter("autoUpDate"));
		}
		if(!StringUtil.isEmpty(request.getParameter("autoDownDate")) ){
			resMap.put("autoDownDate",request.getParameter("autoDownDate"));
		}
		if (request.getParameter("mngPos").equals("1002")) {//好货类目管理
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			resMap.put("sourceProductTypes",sourceProductTypes);
			resMap.put("showType",14);
		}else if (request.getParameter("mngPos").equals("1004")) {//好店类目管理
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("2");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			resMap.put("sourceProductTypes",sourceProductTypes);
			
		}else if (request.getParameter("mngPos").equals("1007")) {//拉新分润好货类目
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("3");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			resMap.put("sourceProductTypes",sourceProductTypes);
		}else if (request.getParameter("mngPos").equals("1012")) {//潮鞋上新
			resMap.put("showType",16);
		}else if (request.getParameter("mngPos").equals("1022")) {//潮流男装
			resMap.put("showType",18);
		}else if (request.getParameter("mngPos").equals("1032")) {//断码特惠
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("6");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			resMap.put("sourceProductTypes",sourceProductTypes);
			resMap.put("showType",20);
		}else if (request.getParameter("mngPos").equals("1042")) {//运动鞋服
			resMap.put("showType",22);
		}else if (request.getParameter("mngPos").equals("1052")) {//潮流美妆
			resMap.put("showType",24);
		}else if (request.getParameter("mngPos").equals("1062")) {//食品超市
			resMap.put("showType",26);
		}else if (request.getParameter("mngPos").equals("1102")) {//爆款推荐
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("12");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			resMap.put("sourceProductTypes",sourceProductTypes);
//			resMap.put("showType",29);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/appMng/adMng/data.shtml")
	@ResponseBody
	public Map<String, Object> appMngAdMngData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount =0;
		try {
			AdInfoCustomExample adInfoExample = new AdInfoCustomExample();
			AdInfoCustomExample.AdInfoCustomCriteria adInfoCriteria = adInfoExample.createCriteria();
			adInfoCriteria.andDelFlagEqualTo("0").andTypeEqualTo("1");
			int mngPos = Integer.valueOf(request.getParameter("mngPos"));
			if (mngPos == 1){
				adInfoCriteria.andCatalogEqualTo("1");
				adInfoCriteria.andPositionEqualTo("1");
			}else if (mngPos == 2){
				String[] strs={"2","3","4","5","11","12","13","14","15"};
				List<String> sList = Arrays.asList(strs);
				adInfoCriteria.andCatalogIn(sList);
			}else if(mngPos == 9) { //SVIP会场广告列表
				adInfoCriteria.andCatalogEqualTo("16");
			}else if(mngPos == 10){//启动广告
				adInfoCriteria.andCatalogEqualTo("19");//19.启动广告
			}else if(mngPos == 11){//个人中心
				adInfoCriteria.andCatalogEqualTo("30");
				adInfoCriteria.andPositionEqualTo("2");
			}else if (mngPos == 1002) {
				adInfoCriteria.andCatalogEqualTo("17");
				adInfoCriteria.andPositionEqualTo("1");
			}else if (mngPos == 1004) {
				adInfoCriteria.andCatalogEqualTo("18");
				adInfoCriteria.andPositionEqualTo("1");
			}else if (mngPos == 1007) {
				adInfoCriteria.andCatalogEqualTo("20");
				adInfoCriteria.andPositionEqualTo("1");
			}else if (mngPos == 1012) {
				adInfoCriteria.andCatalogEqualTo("21");
				adInfoCriteria.andPositionEqualTo("1");
			}else if (mngPos == 1022) {
				adInfoCriteria.andCatalogEqualTo("22");
				adInfoCriteria.andPositionEqualTo("1");
			}else if (mngPos == 1032) {
				adInfoCriteria.andCatalogEqualTo("23");
				adInfoCriteria.andPositionEqualTo("1");
			}else if (mngPos == 1042) {
				adInfoCriteria.andCatalogEqualTo("24");
				adInfoCriteria.andPositionEqualTo("1");
			}else if (mngPos == 1052) {
				adInfoCriteria.andCatalogEqualTo("25");
				adInfoCriteria.andPositionEqualTo("1");
			}else if (mngPos == 1062) {
				adInfoCriteria.andCatalogEqualTo("26");
				adInfoCriteria.andPositionEqualTo("1");
			}else if (mngPos == 1082) {
				adInfoCriteria.andCatalogEqualTo("27");
				adInfoCriteria.andPositionEqualTo("1");
			}else if (mngPos == 1095) {
				adInfoCriteria.andCatalogEqualTo("28");
				adInfoCriteria.andPositionEqualTo("1");
			}else if (mngPos == 1102) {
				adInfoCriteria.andCatalogEqualTo("29");
				adInfoCriteria.andPositionEqualTo("1");
			}
			
			
			adInfoExample.setOrderByClause("IFNULL(a.seq_no, 999999999) asc,a.auto_up_date asc");
			if(!StringUtil.isEmpty(request.getParameter("catalog")) ){
				String catalog=request.getParameter("catalog");
				adInfoCriteria.andCatalogEqualTo(catalog);
			}
			if(!StringUtil.isEmpty(request.getParameter("position")) ){
				String position=request.getParameter("position");
				adInfoCriteria.andPositionEqualTo(position);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("autoUpDate")) ){
				adInfoCriteria.andAutoDownDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("autoUpDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("autoDownDate")) ){
				adInfoCriteria.andAutoUpDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("autoDownDate")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("onLineDate")) ){
				adInfoCriteria.andAutoUpDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("onLineDate")+" 23:59:59"));
				adInfoCriteria.andAutoDownDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("onLineDate")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("showChannel")) ){
				adInfoCriteria.andShowChannelFindInSet(request.getParameter("showChannel"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				adInfoCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("sourceProductTypeId")) ){
				String sourceProductTypeId=request.getParameter("sourceProductTypeId");
				adInfoCriteria.andSourceProductTypeIdEqualTo(Integer.valueOf(sourceProductTypeId));
			}
			if(!StringUtil.isEmpty(request.getParameter("linkType")) ){
				String linkType=request.getParameter("linkType");
				adInfoCriteria.andLinkTypeEqualTo(linkType);
			}
			totalCount = adInfoService.countAdInfoCustomByExample(adInfoExample);
			adInfoExample.setLimitStart(page.getLimitStart());
			adInfoExample.setLimitSize(page.getLimitSize());
			List<AdInfoCustom> adInfoCustoms=adInfoService.selectAdInfoCustomByExample(adInfoExample);
		
			resMap.put("Rows", adInfoCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	@RequestMapping(value = "/appMng/seqEdit/Submit.shtml")
	@ResponseBody
	public Map<String, Object> appMngAdMngSeqSubmit(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		HashMap<String, Object> paramMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			int staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			String seqData=request.getParameter("seqData");
			paramMap.put("seqData", seqData);
			paramMap.put("staffId", staffId);
			if ("1".equals(request.getParameter("type"))){
				adInfoService.updateAdInfoSeqNo(paramMap);
			}else if ("2".equals(request.getParameter("type"))){
				favoritesService.updateFavoritesSeqNo(paramMap);
			}
			
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 添加广告
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/add.shtml")
	public String appMngAdMngAdd(Model model,HttpServletRequest request) {
		String rtPage = "/appMng/adMng/add";// 重定向
		model.addAttribute("showType", request.getParameter("showType"));
		model.addAttribute("mngPos", request.getParameter("mngPos"));
		model.addAttribute("catalogs", DataDicUtil.getStatusList("BU_AD_INFO", "CATALOG"));
		/*model.addAttribute("linkTypes", DataDicUtil.getStatusList("BU_AD_INFO", "LINK_TYPE"));*/
		model.addAttribute("positions", DataDicUtil.getStatusList("BU_AD_INFO", "POSITION"));
		
		
		List<SysStatus> statusList = DataDicUtil.getStatusList("BU_AD_INFO", "LINK_TYPE");
		List<SysStatus> linkTypes  = new ArrayList<SysStatus>();
		
		//商城一级分类
		MallCategoryExample mallCategoryExample = new MallCategoryExample();
		mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MallCategory> mallCategorys = mallCategoryService.selectByExample(mallCategoryExample );
		model.addAttribute("mallCategorys", mallCategorys);
		
		//上架的品牌团名称
		BrandteamTypeExample brandteamTypeExample = new BrandteamTypeExample();
		brandteamTypeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(brandteamTypeExample );
		model.addAttribute("brandteamTypes", brandteamTypes);
		
		//九大类目
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample );
		model.addAttribute("productTypes", productTypes);
		
		
		for (int i = 0; i < 11; i++) {
			linkTypes.add(statusList.get(i));
		}
		
		
		if (request.getParameter("mngPos").equals("1002")) {
			linkTypes.add(statusList.get(11));
			linkTypes.add(statusList.get(12));
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			model.addAttribute("sourceProductTypes",sourceProductTypes);	
			model.addAttribute("linkTypes",linkTypes);
		}else if (request.getParameter("mngPos").equals("1004")) {

			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("2");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			model.addAttribute("sourceProductTypes",sourceProductTypes);
			model.addAttribute("linkTypes",linkTypes);
		}else if (request.getParameter("mngPos").equals("1007")) {

			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("3");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			model.addAttribute("sourceProductTypes",sourceProductTypes);
			model.addAttribute("linkTypes",linkTypes);
		}else if (request.getParameter("mngPos").equals("1012")) {	//潮鞋上新
			linkTypes.add(statusList.get(13));
			linkTypes.add(statusList.get(14));
			model.addAttribute("linkTypes",linkTypes);
		}else if (request.getParameter("mngPos").equals("1022")) {//潮流男装
			linkTypes.add(statusList.get(15));
			linkTypes.add(statusList.get(16));
			model.addAttribute("linkTypes",linkTypes);
		}else if (request.getParameter("mngPos").equals("1032")) {//断码特惠
			linkTypes.add(statusList.get(17));
			linkTypes.add(statusList.get(18));
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("6");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			model.addAttribute("sourceProductTypes",sourceProductTypes);
			model.addAttribute("linkTypes",linkTypes);
		}else if (request.getParameter("mngPos").equals("1042")) {	//运动鞋服
			linkTypes.add(statusList.get(19));
			linkTypes.add(statusList.get(20));
			model.addAttribute("linkTypes",linkTypes);
		}else if (request.getParameter("mngPos").equals("1052")) {//潮流美妆
			linkTypes.add(statusList.get(21));
			linkTypes.add(statusList.get(22));
			model.addAttribute("linkTypes",linkTypes);
		}else if (request.getParameter("mngPos").equals("1062")) {	//食品超市
			linkTypes.add(statusList.get(23));
			linkTypes.add(statusList.get(24));
			model.addAttribute("linkTypes",linkTypes);
		}else if (request.getParameter("mngPos").equals("1095")) {	//领劵中心
			linkTypes.add(statusList.get(14));
			linkTypes.add(statusList.get(15));
			model.addAttribute("linkTypes",linkTypes);
		}else if (request.getParameter("mngPos").equals("1102")) {	//爆款推荐
			//linkTypes.add(statusList.get(11));
			//linkTypes.add(statusList.get(12));
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("12");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			model.addAttribute("sourceProductTypes",sourceProductTypes);
			model.addAttribute("linkTypes",linkTypes);
		}
		/*else if (request.getParameter("mngPos").equals("1082")) {	//大学生创业
			linkTypes.add(statusList.get(23));
			linkTypes.add(statusList.get(24));
			model.addAttribute("linkTypes",linkTypes);
		}*/
		return rtPage;
	}
	
	
	/**
	 * 修改广告
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/edit.shtml")
	public String appMngAdMngEdit(Model model,HttpServletRequest request) {
		String rtPage = "/appMng/adMng/edit";// 重定向
		AdInfoCustom adInfoCustom=new AdInfoCustom();
		adInfoCustom = adInfoService.selectAdInfoCustomByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("adInfoCustom", adInfoCustom);
		model.addAttribute("showType", request.getParameter("showType"));
		model.addAttribute("mngPos", request.getParameter("mngPos"));
		model.addAttribute("catalogs", DataDicUtil.getStatusList("BU_AD_INFO", "CATALOG"));
		model.addAttribute("linkTypes", DataDicUtil.getStatusList("BU_AD_INFO", "LINK_TYPE"));
		model.addAttribute("positions", DataDicUtil.getStatusList("BU_AD_INFO", "POSITION"));
		
		//商城一级分类
		MallCategoryExample mallCategoryExample = new MallCategoryExample();
		mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MallCategory> mallCategorys = mallCategoryService.selectByExample(mallCategoryExample );
		model.addAttribute("mallCategorys", mallCategorys);
		
		//上架的品牌团名称
		BrandteamTypeExample brandteamTypeExample = new BrandteamTypeExample();
		brandteamTypeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(brandteamTypeExample );
		model.addAttribute("brandteamTypes", brandteamTypes);
		
		//九大类目
		ProductTypeExample productTypeExample2 = new ProductTypeExample();
		productTypeExample2.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypess = productTypeService.selectByExample(productTypeExample2 );
		model.addAttribute("productTypes", productTypess);
		
		//二级分类下的一级类目和二级类目
		if("14".equals(adInfoCustom.getLinkType()) || "16".equals(adInfoCustom.getLinkType()) || "18".equals(adInfoCustom.getLinkType()) ||
				"20".equals(adInfoCustom.getLinkType()) || "22".equals(adInfoCustom.getLinkType()) || "24".equals(adInfoCustom.getLinkType()) ||
				"26".equals(adInfoCustom.getLinkType())  ){
			ProductTypeExample productTypeExample = new ProductTypeExample();
			productTypeExample.createCriteria().andDelFlagEqualTo("0").andParentIdEqualTo(1).andStatusEqualTo("1");
			List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample );
			model.addAttribute("productTypes", productTypes);
			String sonIds = adInfoCustom.getLinkUrl();
			String[] split = sonIds.split(",");
			if(split.length>0&&split!=null){
			ProductType partenType = productTypeService.selectByPrimaryKey(Integer.parseInt(split[0]));
			model.addAttribute("partenId", partenType.getParentId());
			model.addAttribute("sonIds", sonIds);
			}
		}
		
		if (request.getParameter("mngPos").equals("1002")) {
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			model.addAttribute("sourceProductTypes",sourceProductTypes);	
		}else if (request.getParameter("mngPos").equals("1004")) {
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("2");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			model.addAttribute("sourceProductTypes",sourceProductTypes);
		}else if (request.getParameter("mngPos").equals("1007")) {
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("3");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			model.addAttribute("sourceProductTypes",sourceProductTypes);
		}else if (request.getParameter("mngPos").equals("1032")) {
			SourceProductTypeExample suCustomExample=new SourceProductTypeExample();
			suCustomExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("6");
			List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(suCustomExample);
			model.addAttribute("sourceProductTypes",sourceProductTypes);
		}
		return rtPage;
	}
	
	
	/**
	 * 广告保存前的检查
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	public  Map<String, String> MngAdMngCheckSubmit( AdInfo adInfo) {
		Map<String, String> resMap = new HashMap<String, String>();
		if ("1".equals(adInfo.getLinkType())){
			ActivityAreaExample activityAreaExample =new ActivityAreaExample();
			ActivityAreaExample.Criteria activityAreaCriteria=activityAreaExample.createCriteria();
			/*activityAreaCriteria.andDelFlagEqualTo("0").andSourceEqualTo("1").andStatusEqualTo("1").andIdEqualTo(adInfo.getLinkId());*/
			activityAreaCriteria.andDelFlagEqualTo("0").andIdEqualTo(adInfo.getLinkId());
			Integer totalCount=activityAreaService.countByExample(activityAreaExample);
			if (totalCount==0){
				resMap.put("YN", "0");
				resMap.put("msg", "会场不存在！");
				return resMap;
			}
		}
		if ("2".equals(adInfo.getLinkType())){
			ActivityExample activityExample =new ActivityExample();
			ActivityExample.Criteria activityCriteria=activityExample.createCriteria();
			/*activityCriteria.andDelFlagEqualTo("0").andStatusEqualTo("6").andIdEqualTo(adInfo.getLinkId());*/
			activityCriteria.andDelFlagEqualTo("0").andIdEqualTo(adInfo.getLinkId());
			Integer totalCount=activityService.countByExample(activityExample);
			if (totalCount==0){
				resMap.put("YN", "0");
				resMap.put("msg", "活动不存在或未审核通过！");
				return resMap;
			}
		}
		if ("3".equals(adInfo.getLinkType())){
/*			ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(adInfo.getLinkId());*/
			ProductCustomExample productCustomExample = new ProductCustomExample();
			productCustomExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(adInfo.getLinkUrl());
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
		if ("7".equals(adInfo.getLinkType())){//自建页面
			/*			ProductCustom productCustom=productService.selectProductCustomByPrimaryKey(adInfo.getLinkId());*/
						CustomPage customPage = customPageService.selectByPrimaryKey(adInfo.getLinkId());
						if(customPage==null || !"0".equals(customPage.getDelFlag())){
							resMap.put("YN", "0");
							resMap.put("msg", "自建页面不存在");
							return resMap;

						}
					}
		
		if ("29".equals(adInfo.getLinkType())){
			CouponExample example = new CouponExample();
			CouponExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andIdEqualTo(adInfo.getLinkId());
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
		if ("10".equals(adInfo.getLinkType())){
			MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
			mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(adInfo.getLinkUrl());
			Integer totalCount = mchtInfoService.countByExample(mchtInfoExample );
			if (totalCount==0){
				resMap.put("YN", "0");
				resMap.put("msg", "商家店铺不存在！");
				return resMap;

			}
		}
		if ("15".equals(adInfo.getLinkType()) || "17".equals(adInfo.getLinkType()) || "19".equals(adInfo.getLinkType()) || "21".equals(adInfo.getLinkType()) || "25".equals(adInfo.getLinkType()) || "27".equals(adInfo.getLinkType())){
			String linkUrl = adInfo.getLinkUrl();
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
	
	
	
	
	
	/**
	 * 广告保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/saveSubmit.shtml")
	@ResponseBody
	public Map<String, Object> appMngAdMngSaveSubmit(HttpServletRequest request, AdInfo adInfo) {
/*		String rtPage = "/success/success";*/
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		String code = null;
 		String msg =null;
		
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			
			if("4".equals(adInfo.getLinkType()) || "5".equals(adInfo.getLinkType())  || "14".equals(adInfo.getLinkType()) || "15".equals(adInfo.getLinkType())
					|| "16".equals(adInfo.getLinkType()) || "17".equals(adInfo.getLinkType()) || "18".equals(adInfo.getLinkType()) || "19".equals(adInfo.getLinkType())
					|| "20".equals(adInfo.getLinkType()) || "21".equals(adInfo.getLinkType()) || "22".equals(adInfo.getLinkType()) || "23".equals(adInfo.getLinkType())
					|| "24".equals(adInfo.getLinkType()) || "25".equals(adInfo.getLinkType()) || "26".equals(adInfo.getLinkType()) || "27".equals(adInfo.getLinkType()) )
			{
				adInfo.setLinkId(0);
			}else if("3".equals(adInfo.getLinkType())){
					String pcode = adInfo.getLinkUrl();
					ProductExample productExample = new ProductExample();
					productExample.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(pcode+"");
					List<Product> products = productService.selectByExample(productExample);
					if(products.size()>0&&products!=null){
						Product product = products.get(0);
						adInfo.setLinkId(product.getId());
					}
				
				}else if ("10".equals(adInfo.getLinkType())){//将店铺code转成主键ID 存入
					String mcode = adInfo.getLinkUrl();
					MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
					mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(mcode+"");
					 List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.selectByExample(mchtInfoExample);
					if(mchtInfoCustoms.size()>0&&mchtInfoCustoms!=null){
						MchtInfoCustom mchtInfoCustom = mchtInfoCustoms.get(0);
						adInfo.setLinkId(mchtInfoCustom.getId());
					}
				}else{
					adInfo.setLinkUrl("");
				}
			
			
				
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("autoUpDate")) ){
				adInfo.setAutoUpDate(dateFormat.parse(request.getParameter("autoUpDate")+":00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("autoDownDate")) ){
				adInfo.setAutoDownDate(dateFormat.parse(request.getParameter("autoDownDate")+":59"));
			}

			if (StringUtil.isEmpty(request.getParameter("id"))) {
				
				Map<String, String> mngAdMngCheckSubmit = MngAdMngCheckSubmit(adInfo);
				if("0".equals(mngAdMngCheckSubmit.get("YN"))){
					resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
					resMap.put(this.JSON_RESULT_MESSAGE,mngAdMngCheckSubmit.get("msg") );
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", mngAdMngCheckSubmit.get("msg"));
				}else{
					adInfo.setStatus("0");
					adInfo.setCreateBy(staffId);
					adInfo.setCreateDate(new Date());
					adInfoService.insertSelective(adInfo);
				}
			}else{	
				Map<String, String> mngAdMngCheckSubmit = MngAdMngCheckSubmit(adInfo);
				if("0".equals(mngAdMngCheckSubmit.get("YN"))){
					resMap.put(this.JSON_RESULT_CODE, StateCode.JSON_AJAX_ERROR.getStateCode());
					resMap.put(this.JSON_RESULT_MESSAGE,mngAdMngCheckSubmit.get("msg") );
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", mngAdMngCheckSubmit.get("msg"));
				}else{
					adInfo.setUpdateBy(staffId);
					adInfo.setUpdateDate(new Date());
					adInfoService.updateByPrimaryKeySelective(adInfo);
				}

			}
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
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
	 * 删除广告/推荐
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/del.shtml")
	@ResponseBody
	public Map<String, Object> appMngAdMngDel(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id=Integer.valueOf(request.getParameter("id"));
			
			AdInfo adInfo=new AdInfo();
			adInfo.setId(id);
			adInfo.setUpdateBy(staffId);
			adInfo.setUpdateDate(new Date());
			adInfo.setDelFlag("1");
			adInfoService.updateByPrimaryKeySelective(adInfo);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}

		return resMap;
	}
	
	/**
	 * 上下架广告/推荐
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/chgStatus.shtml")
	@ResponseBody
	public Map<String, Object> appMngAdMngChgStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id=Integer.valueOf(request.getParameter("id"));
			String status=request.getParameter("status");
			
			AdInfo adInfo=new AdInfo();
			adInfo.setId(id);
			adInfo.setUpdateBy(staffId);
			adInfo.setUpdateDate(new Date());
			adInfo.setStatus(status);
			adInfoService.updateByPrimaryKeySelective(adInfo);
			
			resMap.put("status", request.getParameter("status"));
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}

		return resMap;
	}
	
	/**
	 * 推荐管理首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/appMng/recommend/index.shtml")
	public ModelAndView appMngRecommendIndex(HttpServletRequest request) {	
		String rtPage = "/appMng/recommend/index";// 重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value ="/appMng/recommend/list.shtml")
	public ModelAndView appMngRecommendList(HttpServletRequest request) {	
		String rtPage = "/appMng/recommend/list";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		String catalog = request.getParameter("catalog");
		resMap.put("catalog", catalog);
		if(!StringUtil.isEmpty(request.getParameter("autoUpDate")) ){
			resMap.put("autoUpDate",request.getParameter("autoUpDate"));
		}
		if(!StringUtil.isEmpty(request.getParameter("autoDownDate")) ){
			resMap.put("autoDownDate",request.getParameter("autoDownDate"));
		}
		
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andDelFlagEqualTo("0").andTLevelEqualTo(1);
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypeList", productTypeList);
		
		boolean showFlag = false;
		String staffId = this.getSessionStaffBean(request).getStaffID();
		SysStaffRoleExample staffRoleExample = new SysStaffRoleExample();
		staffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(staffId)).andRoleIdEqualTo(Const.ROLE_ID_80);
		List<SysStaffRole> staffRoleList = sysStaffRoleService.selectByExample(staffRoleExample);
		if("1".equals(staffId) || (staffRoleList != null && staffRoleList.size() > 0) ) {
			showFlag = true;
		}else {
			SysStaffProductTypeExample staffProductTypeExample = new SysStaffProductTypeExample();
			staffProductTypeExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffId));
			List<SysStaffProductType> staffProductTypeList = sysStaffProductTypeService.selectByExample(staffProductTypeExample);
			for(SysStaffProductType sysStaffProductType : staffProductTypeList) {
				if(Const.TYPE_ID_2 == sysStaffProductType.getProductTypeId() && "2".equals(catalog)) { // 运动户外
					showFlag = true;
				}else if(Const.TYPE_ID_3 == sysStaffProductType.getProductTypeId() && "3".equals(catalog)) { // 鞋靴箱包
					showFlag = true;
				}else if(Const.TYPE_ID_4 == sysStaffProductType.getProductTypeId() && "4".equals(catalog)) { // 服装配饰
					showFlag = true;
				}else if(Const.TYPE_ID_5 == sysStaffProductType.getProductTypeId() && "5".equals(catalog)) { // 生活家居
					showFlag = true;
				}else if(Const.TYPE_ID_430 == sysStaffProductType.getProductTypeId() && "11".equals(catalog)) { // 钟表珠宝
					showFlag = true;
				}else if(Const.TYPE_ID_705 == sysStaffProductType.getProductTypeId() && "12".equals(catalog)) { // 数码家电
					showFlag = true;
				}else if(Const.TYPE_ID_858 == sysStaffProductType.getProductTypeId() && "13".equals(catalog)) { // 美妆个护
					showFlag = true;
				}else if(Const.TYPE_ID_954 == sysStaffProductType.getProductTypeId() && "14".equals(catalog)) { // 母婴童装
					showFlag = true;
				}else if(Const.TYPE_ID_1047 == sysStaffProductType.getProductTypeId() && "15".equals(catalog)) { // 食品超市
					showFlag = true;
				}
			}
		}
		resMap.put("showFlag", showFlag);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/appMng/recommend/data.shtml")
	@ResponseBody
	public Map<String, Object> appMngRecommendData(HttpServletRequest request,Page page) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount =0;
		try {
			AdInfoCustomExample adInfoCustomExample=new AdInfoCustomExample();
			AdInfoCustomExample.AdInfoCustomCriteria adInfoCustomCriteria=adInfoCustomExample.createCriteria();
			adInfoCustomCriteria.andDelFlagEqualTo("0").andTypeEqualTo("2").andPositionEqualTo("9");
			adInfoCustomCriteria.andActivityEndTime(); //过滤掉 已结束的活动
			adInfoCustomExample.setOrderByClause("IFNULL(a.seq_no, 999999999),IFNULL(activity_begin_time,''),IFNULL(activity_end_time,''),a.id");
			
			if(!StringUtil.isEmpty(request.getParameter("catalog")) ){
				String catalog=request.getParameter("catalog");
				adInfoCustomCriteria.andCatalogEqualTo(catalog);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("activityName")) ){
				String activityName=request.getParameter("activityName");
				adInfoCustomCriteria.andActivityNameLikeTo("%"+activityName+"%");
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("autoUpDate")) ){
				adInfoCustomCriteria.andAutoDownDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("autoUpDate")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("autoDownDate")) ){
				adInfoCustomCriteria.andAutoUpDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("autoDownDate")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("productTypeId")) ) {
				adInfoCustomCriteria.andLinkTypeEqualTo("2");
				adInfoCustomCriteria.andActivityStatus(Integer.parseInt(request.getParameter("productTypeId")));
			}
			
			totalCount = adInfoService.countAdInfoCustomByExample(adInfoCustomExample);
			
			adInfoCustomExample.setLimitStart(page.getLimitStart());
			adInfoCustomExample.setLimitSize(page.getLimitSize());
			List<AdInfoCustom> adInfoCustoms=adInfoService.selectAdInfoCustomByExample(adInfoCustomExample);
		
			resMap.put("Rows", adInfoCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 添加推荐
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/recommend/add.shtml")
	public String appMngRecommendAdd(Model model,HttpServletRequest request) {
		String rtPage = "/appMng/recommend/add";// 重定向
		
		model.addAttribute("catalog", request.getParameter("catalog"));
	
		return rtPage;
	}
	
	/**
	 * 修改推荐
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/recommend/edit.shtml")
	public String appMngRecommendEdit(Model model,HttpServletRequest request) {
		String rtPage = "/appMng/recommend/edit";// 重定向
		
		AdInfoCustom adInfoCustom=new AdInfoCustom();
		adInfoCustom=adInfoService.selectAdInfoCustomByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		model.addAttribute("adInfoCustom", adInfoCustom);
		model.addAttribute("linkTypes", DataDicUtil.getStatusList("BU_AD_INFO", "LINK_TYPE"));
	
		return rtPage;
	}
	
	/**
	 * 推荐保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/recommend/saveSubmit.shtml")
	public ModelAndView appMngRecommendSaveSubmit(HttpServletRequest request, AdInfo adInfo) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			String linkIdStr=request.getParameter("linkIdStr");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("autoUpDate")) ){
				adInfo.setAutoUpDate(dateFormat.parse(request.getParameter("autoUpDate")+":00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("autoDownDate")) ){
				adInfo.setAutoDownDate(dateFormat.parse(request.getParameter("autoDownDate")+":59"));
			}
			
			if (StringUtil.isEmpty(request.getParameter("id"))) {
				String[] linkIdArray=linkIdStr.split(",");
				for(int i = 0; i < linkIdArray.length; i++) {
					if(linkIdArray[i]!=null && linkIdArray[i]!=""){
						adInfo.setLinkId(Integer.valueOf(linkIdArray[i]));
						adInfo.setStatus("0");
						adInfo.setCreateBy(staffId);
						adInfo.setCreateDate(new Date());
						adInfoService.insertSelective(adInfo);
					}
				}
			}else{
				adInfo.setUpdateBy(staffId);
				adInfo.setUpdateDate(new Date());
				adInfoService.updateByPrimaryKeySelective(adInfo);
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
	
	/**
	 * 推荐中过滤不合法的会场/活动ID
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/recommend/filterId.shtml")
	@ResponseBody
	public Map<String, Object> appMngArecommendFilterId(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 

		try {
			String type=request.getParameter("type");
			String arrayIdStr=request.getParameter("arrayIdStr");
			String catalog=request.getParameter("catalog");
			List<Integer> ids = new ArrayList<Integer>();
			String[] arrayIdStrs = arrayIdStr.split(",");
			for(String arrayIdString : arrayIdStrs) {
				AdInfoExample adInfoExample = new AdInfoExample();
				AdInfoExample.Criteria adInfoCriteria = adInfoExample.createCriteria();
				adInfoCriteria.andTypeEqualTo("2").andCatalogEqualTo(catalog).andPositionEqualTo("9")
					.andDelFlagEqualTo("0").andLinkTypeEqualTo(type).andLinkIdEqualTo(Integer.parseInt(arrayIdString));
				Integer adInfoCount = adInfoService.countByExample(adInfoExample);
				if(adInfoCount == 0) {
					ids.add(Integer.parseInt(arrayIdString));
				}
			}
			List <String> returnIds = new ArrayList<String>();
			if(ids.size() > 0) {
				if ("1".equals(type)){
					ActivityAreaExample activityAreaExample =new ActivityAreaExample();
					ActivityAreaExample.Criteria activityAreaCriteria=activityAreaExample.createCriteria();
					activityAreaCriteria.andDelFlagEqualTo("0").andSourceEqualTo("1").andStatusEqualTo("1").andIdIn(ids);
					List <ActivityArea> activityAreas=activityAreaService.selectByExample(activityAreaExample);
					for (int i = 0; i < activityAreas.size(); i++) {
						returnIds.add(activityAreas.get(i).getId().toString());
					}	
				}
				
				if ("2".equals(type)){
					ActivityExample activityExample =new ActivityExample();
					ActivityExample.Criteria activityCriteria=activityExample.createCriteria();
					activityCriteria.andDelFlagEqualTo("0").andStatusEqualTo("6").andIdIn(ids);
					List <Activity> activitys=activityService.selectByExample(activityExample);
					for (int i = 0; i < activitys.size(); i++) {
						returnIds.add(activitys.get(i).getId().toString());
					}	
				}
			}
			String returnStr = StringUtils.join(returnIds, ",");
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", returnStr);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}

		return resMap;
	}
	
	/**
	 * 喜好管理列表
	 * 
	 * @return
	 */
	@RequestMapping(value ="/appMng/favorites/list.shtml")
	public ModelAndView appMngFavoritesList(HttpServletRequest request) {	
		String rtPage = "/appMng/favorites/list";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();

		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/appMng/favorites/data.shtml")
	@ResponseBody
	public Map<String, Object> appMngFavoritesData(HttpServletRequest request,Page page) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount =0;
		try {
			FavoritesExample favoritesExample=new FavoritesExample();
			FavoritesExample.Criteria favoritesCriteria=favoritesExample.createCriteria();
			favoritesCriteria.andDelFlagEqualTo("0");
			favoritesExample.setOrderByClause("a.status desc,IFNULL(a.seq_no, 999999999),a.id");
			
			totalCount = favoritesService.countFavoritesCustomByExample(favoritesExample);
			
			favoritesExample.setLimitStart(page.getLimitStart());
			favoritesExample.setLimitSize(page.getLimitSize());
			List<FavoritesCustom> favorites=favoritesService.selectFavoritesCustomByExample(favoritesExample);
		
			resMap.put("Rows", favorites);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 上下架喜爱
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/favorites/chgStatus.shtml")
	@ResponseBody
	public Map<String, Object> appMngFavoritesChgStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id=Integer.valueOf(request.getParameter("id"));
			String status=request.getParameter("status");
			
			Favorites favorites=new Favorites();
			favorites.setId(id);
			favorites.setUpdateBy(staffId);
			favorites.setUpdateDate(new Date());
			favorites.setStatus(status);
			favoritesService.updateByPrimaryKeySelective(favorites);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}

		return resMap;
	}
	
	/**
	 * 删除喜好
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/favorites/del.shtml")
	@ResponseBody
	public Map<String, Object> appMngFavoritesDel(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id=Integer.valueOf(request.getParameter("id"));
			
			Favorites favorites=new Favorites();
			favorites.setId(id);
			favorites.setUpdateBy(staffId);
			favorites.setUpdateDate(new Date());
			favorites.setDelFlag("1");
			favoritesService.updateByPrimaryKeySelective(favorites);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}

		return resMap;
	}
	
	/**
	 * 添加喜好
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/favorites/edit.shtml")
	public ModelAndView appMngFavoritesEdit(Model model,HttpServletRequest request) {
		String rtPage = "/appMng/favorites/edit";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		if (!StringUtil.isEmpty(request.getParameter("id"))) {
			FavoritesCustom favoritesCustom = favoritesService.selectFavoritesCustomByPrimaryKey(Integer.valueOf(request.getParameter("id")));
			resMap.put("favoritesCustom", favoritesCustom);
			
			if (favoritesCustom.getFirstProductTypeId()!=null){
				ProductTypeExample productTypeExample2=new ProductTypeExample();
				ProductTypeExample.Criteria productTypeCriteria2=productTypeExample2.createCriteria();
				productTypeCriteria2.andDelFlagEqualTo("0").andStatusEqualTo("1").andParentIdEqualTo(favoritesCustom.getFirstProductTypeId());
				
				List<ProductType> productType2s=productTypeService.selectByExample(productTypeExample2);
				model.addAttribute("productType2s", productType2s);
			}
		}
		
		ProductTypeExample productTypeExample1=new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria1=productTypeExample1.createCriteria();
		productTypeCriteria1.andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		
		List<ProductType> productTypes=productTypeService.selectByExample(productTypeExample1);
		model.addAttribute("productTypes", productTypes);
		
		return new ModelAndView(rtPage, resMap);
	}

	/**
	 * 喜好保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/favorites/saveSubmit.shtml")
	public ModelAndView appMngFavoritesSaveSubmit(HttpServletRequest request, Favorites favorites) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String code = null;
		String msg =null;
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer firstProductTypeId=Integer.valueOf(request.getParameter("firstProductTypeId"));
			String secondType=request.getParameter("secondType");
			
			if (StringUtil.isEmpty(request.getParameter("id"))) {
				favorites.setStatus("0");
				favorites.setUpdateBy(staffId);
				favorites.setCreateDate(new Date());
				favoritesService.insertSelective(favorites);
			}else{
				favorites.setUpdateBy(staffId);
				favorites.setUpdateDate(new Date());
				favoritesService.updateByPrimaryKeySelective(favorites);
				
				FavoritesProductTypeExample favoritesProductTypeExample=new FavoritesProductTypeExample();
				favoritesProductTypeExample.createCriteria().andFavoritesIdEqualTo(favorites.getId()).andDelFlagEqualTo("0");
				FavoritesProductType FavoritesProductTypeUpdate=new FavoritesProductType();
				FavoritesProductTypeUpdate.setDelFlag("1");
				favoritesProductTypeService.updateByExampleSelective(FavoritesProductTypeUpdate, favoritesProductTypeExample);
			}
			
			String[] typeArray=secondType.split(",");
			for(int i = 0; i < typeArray.length; i++) {
				if(typeArray[i]!=null && typeArray[i]!=""){
					FavoritesProductTypeExample mchtBlPicExample2=new FavoritesProductTypeExample();
					mchtBlPicExample2.createCriteria().andFavoritesIdEqualTo(favorites.getId()).andFavoritesIdEqualTo(firstProductTypeId).andSecondProductTypeIdEqualTo(Integer.valueOf(typeArray[i]));
					FavoritesProductType FavoritesProductTypeUpdate=new FavoritesProductType();
					FavoritesProductTypeUpdate.setDelFlag("0");
					int updateCount=favoritesProductTypeService.updateByExampleSelective(FavoritesProductTypeUpdate, mchtBlPicExample2);
					if(updateCount>0){
						continue;
					}
					FavoritesProductType favoritesProductType=new FavoritesProductType();
					favoritesProductType.setFavoritesId(favorites.getId());
					favoritesProductType.setFirstProductTypeId(firstProductTypeId);
					favoritesProductType.setSecondProductTypeId(Integer.valueOf(typeArray[i]));
					favoritesProductType.setCreateBy(staffId);
					favoritesProductType.setCreateDate(new Date());
					favoritesProductTypeService.insertSelective(favoritesProductType);
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

	
	/**
	 * 会员意见反馈列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/feedback/list.shtml")
	public ModelAndView appMngFeedbackList(HttpServletRequest request) {
		String rtPage = "/appMng/feedback/list";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		resMap.put("types", DataDicUtil.getStatusList("BU_MEMBER_FEEDBACK", "TYPE"));
		resMap.put("phoneSystems", DataDicUtil.getStatusList("BU_MEMBER_FEEDBACK", "PHONE_SYSTEM"));
		resMap.put("MemberfeedbackList", memberFeedbackService.getMemberfeedbackList());
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 会员意见反馈数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/feedback/data.shtml")
	@ResponseBody
	public Map<String, Object> appMngFeedbackData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MemberFeedbackExample memberFeedbackExample=new MemberFeedbackExample();
			MemberFeedbackExample.Criteria memberFeedbackCriteria=memberFeedbackExample.createCriteria();
			memberFeedbackCriteria.andDelFlagEqualTo("0");
			/*memberFeedbackExample.setOrderByClause(" a.process_date is not NULL asc ");*/
			memberFeedbackExample.setOrderByClause(" IFNULL(a.deal_date,DATE('2999-01-01')) desc,IFNULL(a.process_date,DATE('1970-01-01')) asc,a.create_date asc ");
			if(!StringUtil.isEmpty(request.getParameter("type")) ){
				String type=request.getParameter("type");
				memberFeedbackCriteria.andTypeEqualTo(type);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("phoneSystem")) ){
				String phoneSystem=request.getParameter("phoneSystem");
				memberFeedbackCriteria.andPhoneSystemEqualTo(phoneSystem);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("appVersion")) ){
				String appVersion=request.getParameter("appVersion");
				memberFeedbackCriteria.andAppVersionLike("%"+appVersion+"%");
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
				memberFeedbackCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
				memberFeedbackCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
			}
			
			SimpleDateFormat processdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("process_date_begin")) ){
				memberFeedbackCriteria.andProcessDateGreaterThanOrEqualTo(processdateFormat.parse(request.getParameter("process_date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("process_date_end")) ){
				memberFeedbackCriteria.andProcessDateLessThanOrEqualTo(processdateFormat.parse(request.getParameter("process_date_end")+" 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("procesBy"))) {
				/*if ("unclaimed".equals(request.getParameter("procesBy"))) {*/
			/*		memberFeedbackCriteria.andProcesByIsNull();*/
				/*}else {*/
					memberFeedbackCriteria.andProcesByEqualTo(Integer.valueOf(request.getParameter("procesBy")));
				
			}
		    if (request.getParameter("dealstatus").equals("0")) {
		    	memberFeedbackCriteria.andDealStatusEqualTo("0");
			}
		    if (request.getParameter("dealstatus").equals("1")) {
		    	memberFeedbackCriteria.andDealStatusEqualTo("1");
			}
		    if (request.getParameter("dealstatus").equals("2")) {
		    	memberFeedbackCriteria.andDealStatusEqualTo("2");
			}
		    if (request.getParameter("dealstatus").equals("3")) {
		    	memberFeedbackCriteria.andProcesByIsNull();
			}
		    if(!StringUtil.isEmpty(request.getParameter("memberId"))) {
		    	memberFeedbackCriteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
		    }
		    if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
		    	memberFeedbackCriteria.andmchtCodeEqualTo(request.getParameter("mchtCode"));
		    }
		    if(!StringUtil.isEmpty(request.getParameter("mchtcompanyName"))) {
		    	String mchtcompanyName=request.getParameter("mchtcompanyName");
		    	memberFeedbackCriteria.andmchtcompanyNameLike("%"+mchtcompanyName+"%");
		    }
			totalCount = memberFeedbackService.countMemberFeedbackCustomByExample(memberFeedbackExample);
			
			memberFeedbackExample.setLimitStart(page.getLimitStart());
			memberFeedbackExample.setLimitSize(page.getLimitSize());
			List<MemberFeedbackCustom> memberFeedbackCustoms=memberFeedbackService.selectMemberFeedbackCustomByExample(memberFeedbackExample);
			
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			 
			    //本领取人的协助人可以操作修改处理
            	for (MemberFeedbackCustom memberFeedbackCustom : memberFeedbackCustoms) {
            		if (memberFeedbackCustom.getProcesby()!=null) {
            			Integer memberFeedbackCustomProcesby=memberFeedbackCustom.getProcesby();
            			PlatformContactExample platformContactExamples=new PlatformContactExample();
            			platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(memberFeedbackCustomProcesby);	            	
            			List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
            			
            			if (platformContacts !=null && platformContacts.size()>0) {
            				for (PlatformContact platformContact : platformContacts) {
            					Integer assistantId=platformContact.getId();
            					PlatformContactExample platformContactExample=new PlatformContactExample();
            					platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(assistantId);
            					List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
            					
            					for (PlatformContact platformcontacts : platformAssistantContact) {
            						if (platformcontacts.getStaffId().equals(staffId)) {				    						 
            							memberFeedbackCustom.setAssistantContact("1");		    						 
            						}
            					}
            					
            				}
            				
            			}							
						
					}
                	
                }
   
            		
		
			resMap.put("Rows", memberFeedbackCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//保存领取人
	@RequestMapping(value = "/appMng/saveMemberFeedback.shtml")
	@ResponseBody
	public Map<String, Object> savePlatformProcessor(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			MemberFeedback memberFeedback = memberFeedbackService.selectByPrimaryKey(Integer.parseInt(id));
			memberFeedback.setProcesby(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			memberFeedback.setProcessdate(new Date());
			memberFeedbackService.updateByPrimaryKey(memberFeedback);
			resMap.put("staffName", this.getSessionStaffBean(request).getStaffName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	//批量领取
	@RequestMapping(value = "/appMng/feedback/procesbydata.shtml")
	@ResponseBody
	public ModelAndView changeStatu(HttpServletRequest request, 
			HttpServletResponse response,@RequestParam HashMap<String, Object> paramMap,String id) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg = ""; 
		try {    
			String[] split = id.split(",");
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			for (int i = 0; i < split.length; i++) {
				int procesbyid=Integer.valueOf(split[i]);
				MemberFeedbackExample memberFeedbackExample=new MemberFeedbackExample();
				MemberFeedbackExample.Criteria memberFeedbackCriteria=memberFeedbackExample.createCriteria();
				memberFeedbackCriteria.andDelFlagEqualTo("0").andIdEqualTo(procesbyid);
				MemberFeedback memberFeedback = memberFeedbackService.selectByPrimaryKey(procesbyid);
				memberFeedback.setProcesby(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				memberFeedback.setProcessdate(new Date());
				memberFeedback.setUpdateBy(staffId);
				memberFeedback.setUpdateDate(new Date());
				memberFeedbackService.updateByExampleSelective(memberFeedback,memberFeedbackExample);
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
	
	//根据id获取处理数据信息
	@RequestMapping(value = "/appMng/feedback/edit.shtml")
	public ModelAndView memberFeedbackProcesBy(HttpServletRequest request) {
		String rtPage = "/appMng/feedback/edit";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			if(!StringUtil.isEmpty(request.getParameter("id"))){
				MemberFeedback memberFeedback=memberFeedbackService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id"))); 
				resMap.put("memberFeedback", memberFeedback);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(rtPage, resMap);
	}
	
	//修改处理数据
	@ResponseBody
	@RequestMapping(value = "/appMng/editsales.shtml")
	public ModelAndView memberFeedbackProcesBylist(HttpServletRequest request, String id, String dealstatus, String dealopinion, String relatedorder,String mchtCode, String mchtcompanyName) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg =null;
		try {
			StaffBean staffBean = this.getSessionStaffBean(request);
			int staffId=Integer.valueOf(staffBean.getStaffID());
			MemberFeedbackExample memberFeedbackExample = new MemberFeedbackExample();
			MemberFeedbackExample.Criteria memberFeedbackExampleCriteria = memberFeedbackExample.createCriteria();
			memberFeedbackExampleCriteria.andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(id));
			MemberFeedback MemberFeedback = new MemberFeedback();
			MemberFeedback.setDealopinion(dealopinion);
			MemberFeedback.setRelatedorder(relatedorder);
		    MemberFeedback.setDealstatus(dealstatus);
		    MemberFeedback.setMchtCode(mchtCode);
		    MemberFeedback.setMchtcompanyName(mchtcompanyName);
		    MemberFeedback.setDealdate(new Date());
			MemberFeedback.setUpdateBy(staffId);
			MemberFeedback.setUpdateDate(new Date());
			
			memberFeedbackService.updateByExampleSelective(MemberFeedback, memberFeedbackExample);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 获取会员意见反馈图片
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/feedback/getPics.shtml")
	@ResponseBody
	public List<MemberFeedbackPic> appMngFeedbackGetPics(HttpServletRequest request) {
		MemberFeedbackPicExample memberFeedbackPicExample = new MemberFeedbackPicExample();
		MemberFeedbackPicExample.Criteria criteria = memberFeedbackPicExample.createCriteria();
		criteria.andDelFlagEqualTo("0").andFeedbackIdEqualTo(Integer.valueOf(request.getParameter("id")));
		List<MemberFeedbackPic> memberFeedbackPics = memberFeedbackPicService.selectByExample(memberFeedbackPicExample);
		return memberFeedbackPics;
	}
	
	@RequestMapping(value = "/appMng/feedback/downloadList.shtml")
	public void appMngFeedbackDownloadList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
		// 查询
		MemberFeedbackExample memberFeedbackExample=new MemberFeedbackExample();
		MemberFeedbackExample.Criteria memberFeedbackCriteria=memberFeedbackExample.createCriteria();
		memberFeedbackCriteria.andDelFlagEqualTo("0");
		memberFeedbackExample.setOrderByClause("a.id desc");
		
		if(!StringUtil.isEmpty(request.getParameter("type")) ){
			String type=request.getParameter("type");
			memberFeedbackCriteria.andTypeEqualTo(type);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("phoneSystem")) ){
			String phoneSystem=request.getParameter("phoneSystem");
			memberFeedbackCriteria.andPhoneSystemEqualTo(phoneSystem);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("appVersion")) ){
			String appVersion=request.getParameter("appVersion");
			memberFeedbackCriteria.andAppVersionLike("%"+appVersion+"%");
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
			memberFeedbackCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
		}
		
		if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
			memberFeedbackCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
		}
		if(!StringUtil.isEmpty(request.getParameter("memberId"))) {
	    	memberFeedbackCriteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
	    }
		 if (request.getParameter("dealstatus").equals("0")) {
		    	memberFeedbackCriteria.andDealStatusEqualTo("0");
			}
		    if (request.getParameter("dealstatus").equals("1")) {
		    	memberFeedbackCriteria.andDealStatusEqualTo("1");
			}
		    if (request.getParameter("dealstatus").equals("2")) {
		    	memberFeedbackCriteria.andDealStatusEqualTo("2");
			}
		    if (request.getParameter("dealstatus").equals("3")) {
		    	memberFeedbackCriteria.andProcesByIsNull();
			}
		    SimpleDateFormat processdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("process_date_begin")) ){
				memberFeedbackCriteria.andProcessDateGreaterThanOrEqualTo(processdateFormat.parse(request.getParameter("process_date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("process_date_end")) ){
				memberFeedbackCriteria.andProcessDateLessThanOrEqualTo(processdateFormat.parse(request.getParameter("process_date_end")+" 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("procesBy"))) {
			   memberFeedbackCriteria.andProcesByEqualTo(Integer.valueOf(request.getParameter("procesBy")));
				
			}
			 if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
			    memberFeedbackCriteria.andmchtCodeEqualTo(request.getParameter("mchtCode"));
			}
		List<MemberFeedbackCustom> memberFeedbackCustoms=memberFeedbackService.selectMemberFeedbackCustomByExample(memberFeedbackExample);
		
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String[] titles = { "会员ID", "会员昵称", "手机号码", "反馈类型", "反馈内容", "反馈时间","系统","手机系统版本","手机型号","APP版本","领取人","领取时间","处理状态","处理意见","相关订单","商家序号/商家名称","处理时间  "};
		ExcelBean excelBean = new ExcelBean(dateFormat2.format(new Date())+"导出会员意见反馈表.xls", "会员意见反馈表", titles);
		List<String[]> datas = new ArrayList<String[]>();
		for(MemberFeedbackCustom memberFeedbackCustom:memberFeedbackCustoms){
			String dealstatus = "";
			if (memberFeedbackCustom.getDealstatus().equals("0")) {
				dealstatus="待处理";
			}
		    if (memberFeedbackCustom.getDealstatus().equals("1")) {
		    	dealstatus="已处理";
			}
		    if (memberFeedbackCustom.getDealstatus().equals("2")) {
		    	dealstatus="不需要处理";
			}	    
			String[] data = {
					memberFeedbackCustom.getMemberId().toString(),
					memberFeedbackCustom.getNick()==null?"":memberFeedbackCustom.getNick(),
					memberFeedbackCustom.getMobile()==null?"":memberFeedbackCustom.getMobile(),
					memberFeedbackCustom.getTypeDesc()==null?"":memberFeedbackCustom.getTypeDesc(),
					memberFeedbackCustom.getContent()==null?"":memberFeedbackCustom.getContent(),
					dateFormat.format(memberFeedbackCustom.getCreateDate()),
					memberFeedbackCustom.getPhoneSystemName()==null?"":memberFeedbackCustom.getPhoneSystemName(),
				    memberFeedbackCustom.getSystemversion()==null?"":memberFeedbackCustom.getSystemversion(),		
					memberFeedbackCustom.getPhoneModel()==null?"":memberFeedbackCustom.getPhoneModel(),
					memberFeedbackCustom.getPhoneSystemName()+'V'+memberFeedbackCustom.getAppVersion(),
					memberFeedbackCustom.getStaffName()==null?"":memberFeedbackCustom.getStaffName(),
				    memberFeedbackCustom.getProcessdate()==null?"":dateFormat2.format(memberFeedbackCustom.getProcessdate()),	
					dealstatus,
				    memberFeedbackCustom.getDealopinion()==null?"":memberFeedbackCustom.getDealopinion(),
				    memberFeedbackCustom.getRelatedorder()==null?"":memberFeedbackCustom.getRelatedorder(),
				    memberFeedbackCustom.getMchtCode()==null?"":memberFeedbackCustom.getMchtCode()+memberFeedbackCustom.getMchtcompanyName(),
				    memberFeedbackCustom.getDealdate()==null?"":dateFormat2.format(memberFeedbackCustom.getDealdate())
			
			};
			datas.add(data);
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
	    } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//选择批量导出
	@RequestMapping(value = "/appMng/feedback/downloadListCheckout.shtml")
	public void appMngFeedbackdownloadListCheckout(HttpServletRequest request,String id, HttpServletResponse response) throws Exception {
		try {
		String[] split=id.split(",");
		List<String[]> datas = new ArrayList<String[]>();
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for (int i = 0; i < split.length; i++) {			
			 int memberid=Integer.valueOf(split[i]);	
			 
		MemberFeedbackExample memberFeedbackExample=new MemberFeedbackExample();
		MemberFeedbackExample.Criteria memberFeedbackCriteria=memberFeedbackExample.createCriteria();
		memberFeedbackCriteria.andDelFlagEqualTo("0").andIdEqualTo(memberid);
		memberFeedbackExample.setOrderByClause("a.id desc");
		
		if(!StringUtil.isEmpty(request.getParameter("type")) ){
			String type=request.getParameter("type");
			memberFeedbackCriteria.andTypeEqualTo(type);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("phoneSystem")) ){
			String phoneSystem=request.getParameter("phoneSystem");
			memberFeedbackCriteria.andPhoneSystemEqualTo(phoneSystem);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("appVersion")) ){
			String appVersion=request.getParameter("appVersion");
			memberFeedbackCriteria.andAppVersionLike("%"+appVersion+"%");
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
			memberFeedbackCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
		}
		
		if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
			memberFeedbackCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
		}
		if(!StringUtil.isEmpty(request.getParameter("memberId"))) {
	    	memberFeedbackCriteria.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
	    }
		 if (request.getParameter("dealstatus").equals("0")) {
		    	memberFeedbackCriteria.andDealStatusEqualTo("0");
			}
		    if (request.getParameter("dealstatus").equals("1")) {
		    	memberFeedbackCriteria.andDealStatusEqualTo("1");
			}
		    if (request.getParameter("dealstatus").equals("2")) {
		    	memberFeedbackCriteria.andDealStatusEqualTo("2");
			}
		    if (request.getParameter("dealstatus").equals("3")) {
		    	memberFeedbackCriteria.andProcesByIsNull();
			}
		    SimpleDateFormat processdateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("process_date_begin")) ){
				memberFeedbackCriteria.andProcessDateGreaterThanOrEqualTo(processdateFormat.parse(request.getParameter("process_date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("process_date_end")) ){
				memberFeedbackCriteria.andProcessDateLessThanOrEqualTo(processdateFormat.parse(request.getParameter("process_date_end")+" 23:59:59"));
			}
			if (!StringUtil.isEmpty(request.getParameter("procesBy"))) {
			   memberFeedbackCriteria.andProcesByEqualTo(Integer.valueOf(request.getParameter("procesBy")));
				
			}
			 if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
			    memberFeedbackCriteria.andmchtCodeEqualTo(request.getParameter("mchtCode"));
			}
		List<MemberFeedbackCustom> memberFeedbackCustoms=memberFeedbackService.selectMemberFeedbackCustomByExample(memberFeedbackExample);
		
		for(MemberFeedbackCustom memberFeedbackCustom:memberFeedbackCustoms){
			String dealstatus = "";
			if (memberFeedbackCustom.getDealstatus().equals("0")) {
				dealstatus="待处理";
			}
		    if (memberFeedbackCustom.getDealstatus().equals("1")) {
		    	dealstatus="已处理";
			}
		    if (memberFeedbackCustom.getDealstatus().equals("2")) {
		    	dealstatus="不需要处理";
			}
		    
			String[] data = {
					memberFeedbackCustom.getMemberId().toString(),
					memberFeedbackCustom.getNick()==null?"":memberFeedbackCustom.getNick(),
					memberFeedbackCustom.getMobile()==null?"":memberFeedbackCustom.getMobile(),
					memberFeedbackCustom.getTypeDesc()==null?"":memberFeedbackCustom.getTypeDesc(),
					memberFeedbackCustom.getContent()==null?"":memberFeedbackCustom.getContent(),
					dateFormat.format(memberFeedbackCustom.getCreateDate()),
					memberFeedbackCustom.getPhoneSystemName()==null?"":memberFeedbackCustom.getPhoneSystemName(),
				    memberFeedbackCustom.getSystemversion()==null?"":memberFeedbackCustom.getSystemversion(),		
					memberFeedbackCustom.getPhoneModel()==null?"":memberFeedbackCustom.getPhoneModel(),
					memberFeedbackCustom.getPhoneSystemName()+'V'+memberFeedbackCustom.getAppVersion(),
					memberFeedbackCustom.getStaffName()==null?"":memberFeedbackCustom.getStaffName(),
				    memberFeedbackCustom.getProcessdate()==null?"":dateFormat2.format(memberFeedbackCustom.getProcessdate()),	
					dealstatus,
				    memberFeedbackCustom.getDealopinion()==null?"":memberFeedbackCustom.getDealopinion(),
				    memberFeedbackCustom.getRelatedorder()==null?"":memberFeedbackCustom.getRelatedorder(),
				    memberFeedbackCustom.getMchtCode()==null?"":memberFeedbackCustom.getMchtCode()+memberFeedbackCustom.getMchtcompanyName(),
				    memberFeedbackCustom.getDealdate()==null?"":dateFormat2.format(memberFeedbackCustom.getDealdate())
			
			};
			datas.add(data);
		}
	 }
		String[] titles = { "会员ID", "会员昵称", "手机号码", "反馈类型", "反馈内容", "反馈时间","系统","手机系统版本","手机型号","APP版本","领取人","领取时间","处理状态","处理意见","相关订单","商家序号/商家名称","处理时间  "};
		ExcelBean excelBean = new ExcelBean(dateFormat2.format(new Date())+"导出会员意见反馈表.xls", "会员意见反馈表", titles);
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);			
	    } catch (Exception e) {
		  e.printStackTrace();
	  }
	}
	
	
	
	
	@RequestMapping(value ="/appMng/adMng/countList.shtml")
	public ModelAndView countList(HttpServletRequest request) {	
		String rtPage = "/appMng/adMng/countList";// 首页,品类
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("mngPos", request.getParameter("mngPos"));
		String dateBegin = "";
		String dateEnd = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtil.isEmpty(request.getParameter("dateBegin")) && !StringUtil.isEmpty(request.getParameter("dateEnd"))){
			dateBegin = df.format(request.getParameter("dateBegin"));
			dateEnd = df.format(request.getParameter("dateEnd"));
		}else{
			dateBegin = df.format(DateUtil.getDateAfter(new Date(), -6));
			dateEnd = df.format(DateUtil.getDateAfter(new Date(), 6));
		}
		resMap.put("dateBegin", dateBegin);
		resMap.put("dateEnd", dateEnd);
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/appMng/adMng/countData.shtml")
	@ResponseBody
	public Map<String, Object> countData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			int mngPos=Integer.valueOf(request.getParameter("mngPos"));
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String dateBegin = "";
			String dateEnd = "";
			if(!StringUtil.isEmpty(request.getParameter("dateBegin")) && !StringUtil.isEmpty(request.getParameter("dateEnd"))){
				String dateBeginStr = request.getParameter("dateBegin");
				String[] dateBeginArray = dateBeginStr.split("-");
				dateBegin = dateBeginArray[0]+dateBeginArray[1]+dateBeginArray[2];
				String dateEndStr = request.getParameter("dateEnd");
				String[] dateEndArray = dateEndStr.split("-");
				dateEnd = dateEndArray[0]+dateEndArray[1]+dateEndArray[2];
			}else{
				dateBegin = df.format(DateUtil.getDateAfter(new Date(), -6));
				dateEnd = df.format(DateUtil.getDateAfter(new Date(), 6));
			}
			paramMap.put("dateBegin", dateBegin);
			paramMap.put("dateEnd", dateEnd);
			paramMap.put("type", "1");
			List<AdInfoCustom> adInfoCustoms = new ArrayList<AdInfoCustom>();
			if(mngPos==101){//首页统计
				paramMap.put("catalog", "1");
				String resultStr = adInfoService.countIndexAdInfo(paramMap);
				String[] resultArray = resultStr.split("\\|");
				for(int i=0;i<resultArray.length;i++){
					String adInfoCountStr = resultArray[i];
					String[] adInfoCountArray = adInfoCountStr.split(",");
					String eachDay = adInfoCountArray[0];
					String todayCount = adInfoCountArray[1];
					String upCount = adInfoCountArray[2];
					String downCount = adInfoCountArray[3];
					AdInfoCustom adInfoCustom = new AdInfoCustom();
					adInfoCustom.setEachDay(eachDay);
					adInfoCustom.setTodayCount(Integer.parseInt(todayCount));
					adInfoCustom.setUpCount(Integer.parseInt(upCount));
					adInfoCustom.setDownCount(Integer.parseInt(downCount));
					adInfoCustoms.add(adInfoCustom);
				}
			}else if(mngPos==102){//品类统计
				paramMap.put("catalog", "0");
				String resultStr = adInfoService.countTypeAdInfo(paramMap);
				String[] resultArray = resultStr.split("\\|");
				for(int i=0;i<resultArray.length;i++){
					String adInfoCountStr = resultArray[i];
					String[] adInfoCountArray = adInfoCountStr.split(",");
					String eachDay = adInfoCountArray[0];
					String recommendCount = adInfoCountArray[1];
					String upCount = adInfoCountArray[2];
					String downCount = adInfoCountArray[3];
					AdInfoCustom adInfoCustom = new AdInfoCustom();
					adInfoCustom.setEachDay(eachDay);
					adInfoCustom.setRecommendCount(Integer.parseInt(recommendCount));
					adInfoCustom.setUpCount(Integer.parseInt(upCount));
					adInfoCustom.setDownCount(Integer.parseInt(downCount));
					adInfoCustoms.add(adInfoCustom);
				}
			}
			HashMap<String, AdInfoCustom> map = new HashMap<String, AdInfoCustom>();
			List<String> containDays = new ArrayList<String>();
			for(AdInfoCustom adInfoCustom:adInfoCustoms){
				containDays.add(adInfoCustom.getEachDay());
				map.put(adInfoCustom.getEachDay(), adInfoCustom);
			}
			String beginDate = dateBegin.substring(0, 4)+"-"+dateBegin.substring(4, 6)+"-"+dateBegin.substring(6, 8);
			String endDate = dateEnd.substring(0, 4)+"-"+dateEnd.substring(4, 6)+"-"+dateEnd.substring(6, 8);
			List<String> betweenDays = this.getBetweenDays(beginDate, endDate);
			for(int i= 0;i<betweenDays.size();i++){
				if(!containDays.contains(betweenDays.get(i))){
					paramMap.put("eachDay", betweenDays.get(i));
					AdInfoCustom adInfoCustom = new AdInfoCustom();
					adInfoCustom.setEachDay(betweenDays.get(i));
					if(mngPos==101){
						adInfoCustom.setTodayCount(0);
						adInfoCustom.setUpCount(0);
						adInfoCustom.setDownCount(0);
					}else if(mngPos==102){
						adInfoCustom.setRecommendCount(0);
						adInfoCustom.setUpCount(0);
						adInfoCustom.setDownCount(0);
					}
					adInfoCustoms.add(adInfoCustom);
					map.put(betweenDays.get(i), adInfoCustom);
				}
			}
			Collections.sort(adInfoCustoms, new Comparator<AdInfoCustom>() {
	            @Override
	            public int compare(AdInfoCustom c1, AdInfoCustom c2) {
	                //升序
	                return c1.getEachDay().compareTo(c2.getEachDay());
	            }
	        });
			resMap.put("Rows", adInfoCustoms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
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
	
	@RequestMapping(value ="/appMng/recommend/countList.shtml")
	public ModelAndView recommendCountList(HttpServletRequest request) {	
		String rtPage = "/appMng/recommend/countList";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		String catalog = request.getParameter("catalog");
		resMap.put("catalog", catalog);
		String dateBegin = "";
		String dateEnd = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtil.isEmpty(request.getParameter("dateBegin")) && !StringUtil.isEmpty(request.getParameter("dateEnd"))){
			dateBegin = df.format(request.getParameter("dateBegin"));
			dateEnd = df.format(request.getParameter("dateEnd"));
		}else{
			dateBegin = df.format(DateUtil.getDateAfter(new Date(), -6));
			dateEnd = df.format(DateUtil.getDateAfter(new Date(), 6));
		}
		resMap.put("dateBegin", dateBegin);
		resMap.put("dateEnd", dateEnd);
		
		boolean showFlag = false;
		String staffId = this.getSessionStaffBean(request).getStaffID();
		SysStaffRoleExample staffRoleExample = new SysStaffRoleExample();
		staffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(staffId)).andRoleIdEqualTo(Const.ROLE_ID_80);
		List<SysStaffRole> staffRoleList = sysStaffRoleService.selectByExample(staffRoleExample);
		if("1".equals(staffId) || (staffRoleList != null && staffRoleList.size() > 0) ) {
			showFlag = true;
		}else {
			SysStaffProductTypeExample staffProductTypeExample = new SysStaffProductTypeExample();
			staffProductTypeExample.createCriteria().andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.parseInt(staffId));
			List<SysStaffProductType> staffProductTypeList = sysStaffProductTypeService.selectByExample(staffProductTypeExample);
			for(SysStaffProductType sysStaffProductType : staffProductTypeList) {
				if(Const.TYPE_ID_2 == sysStaffProductType.getProductTypeId() && "2".equals(catalog)) { // 运动户外
					showFlag = true;
				}else if(Const.TYPE_ID_3 == sysStaffProductType.getProductTypeId() && "3".equals(catalog)) { // 鞋靴箱包
					showFlag = true;
				}else if(Const.TYPE_ID_4 == sysStaffProductType.getProductTypeId() && "4".equals(catalog)) { // 服装配饰
					showFlag = true;
				}else if(Const.TYPE_ID_5 == sysStaffProductType.getProductTypeId() && "5".equals(catalog)) { // 生活家居
					showFlag = true;
				}else if(Const.TYPE_ID_430 == sysStaffProductType.getProductTypeId() && "11".equals(catalog)) { // 钟表珠宝
					showFlag = true;
				}else if(Const.TYPE_ID_705 == sysStaffProductType.getProductTypeId() && "12".equals(catalog)) { // 数码家电
					showFlag = true;
				}else if(Const.TYPE_ID_858 == sysStaffProductType.getProductTypeId() && "13".equals(catalog)) { // 美妆个护
					showFlag = true;
				}else if(Const.TYPE_ID_954 == sysStaffProductType.getProductTypeId() && "14".equals(catalog)) { // 母婴童装
					showFlag = true;
				}else if(Const.TYPE_ID_1047 == sysStaffProductType.getProductTypeId() && "15".equals(catalog)) { // 食品超市
					showFlag = true;
				}
			}
		}
		resMap.put("showFlag", showFlag);
		
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/appMng/recommend/countData.shtml")
	@ResponseBody
	public Map<String, Object> recommendCountData(HttpServletRequest request) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String catalog=request.getParameter("catalog");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String dateBegin = "";
			String dateEnd = "";
			if(!StringUtil.isEmpty(request.getParameter("dateBegin")) && !StringUtil.isEmpty(request.getParameter("dateEnd"))){
				String dateBeginStr = request.getParameter("dateBegin");
				String[] dateBeginArray = dateBeginStr.split("-");
				dateBegin = dateBeginArray[0]+dateBeginArray[1]+dateBeginArray[2];
				String dateEndStr = request.getParameter("dateEnd");
				String[] dateEndArray = dateEndStr.split("-");
				dateEnd = dateEndArray[0]+dateEndArray[1]+dateEndArray[2];
			}else{
				dateBegin = df.format(DateUtil.getDateAfter(new Date(), -6));
				dateEnd = df.format(DateUtil.getDateAfter(new Date(), 6));
			}
			paramMap.put("dateBegin", dateBegin);
			paramMap.put("dateEnd", dateEnd);
			paramMap.put("type", "2");
			paramMap.put("catalog", catalog);
			List<AdInfoCustom> adInfoCustoms = new ArrayList<AdInfoCustom>();
			String resultStr = adInfoService.countIndexAdInfo(paramMap);
			String[] resultArray = resultStr.split("\\|");
			for(int i=0;i<resultArray.length;i++){
				String adInfoCountStr = resultArray[i];
				String[] adInfoCountArray = adInfoCountStr.split(",");
				String eachDay = adInfoCountArray[0];
				String recommendCount = adInfoCountArray[1];
				String upCount = adInfoCountArray[2];
				String downCount = adInfoCountArray[3];
				AdInfoCustom adInfoCustom = new AdInfoCustom();
				adInfoCustom.setEachDay(eachDay);
				adInfoCustom.setRecommendCount(Integer.parseInt(recommendCount));
				adInfoCustom.setUpCount(Integer.parseInt(upCount));
				adInfoCustom.setDownCount(Integer.parseInt(downCount));
				adInfoCustoms.add(adInfoCustom);
			}
			HashMap<String, AdInfoCustom> map = new HashMap<String, AdInfoCustom>();
			List<String> containDays = new ArrayList<String>();
			for(AdInfoCustom adInfoCustom:adInfoCustoms){
				containDays.add(adInfoCustom.getEachDay());
				map.put(adInfoCustom.getEachDay(), adInfoCustom);
			}
			String beginDate = dateBegin.substring(0, 4)+"-"+dateBegin.substring(4, 6)+"-"+dateBegin.substring(6, 8);
			String endDate = dateEnd.substring(0, 4)+"-"+dateEnd.substring(4, 6)+"-"+dateEnd.substring(6, 8);
			List<String> betweenDays = this.getBetweenDays(beginDate, endDate);
			for(int i= 0;i<betweenDays.size();i++){
				if(!containDays.contains(betweenDays.get(i))){
					paramMap.put("eachDay", betweenDays.get(i));
					AdInfoCustom adInfoCustom = new AdInfoCustom();
					adInfoCustom.setEachDay(betweenDays.get(i));
					adInfoCustom.setRecommendCount(0);
					adInfoCustom.setUpCount(0);
					adInfoCustom.setDownCount(0);
					adInfoCustoms.add(adInfoCustom);
					map.put(betweenDays.get(i), adInfoCustom);
				}
			}
			Collections.sort(adInfoCustoms, new Comparator<AdInfoCustom>() {
	            @Override
	            public int compare(AdInfoCustom c1, AdInfoCustom c2) {
	                //升序
	                return c1.getEachDay().compareTo(c2.getEachDay());
	            }
	        });
			resMap.put("Rows", adInfoCustoms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title homePageManager   
	 * @Description    
	 * @author pengl
	 * @date 2017年10月17日 下午2:29:40
	 */
	@RequestMapping(value = "/appMng/homePageManager.shtml")
	public ModelAndView homePageManager(HttpServletRequest request, String flag) {
		ModelAndView m = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(flag.equals("3") || flag.equals("5")) {// 首页专区广告   或    频道广告
			m.setViewName("/appMng/adMng/homePage/prefectureImageList");
			m.addObject("flag", flag);
		}else if(flag.equals("4")) {// 首页品牌推荐
			m.setViewName("/appMng/adMng/homePage/brandRecommendList");
		}else if(flag.equals("6")){//首页主题馆装修
			AdInfoExample adInfoExample = new AdInfoExample();
			AdInfoExample.Criteria adInfoCriteria = adInfoExample.createCriteria();
			adInfoCriteria.andDelFlagEqualTo("0")
				.andTypeEqualTo("3")//装修页
				.andCatalogEqualTo("1")//首页
				.andPositionEqualTo("12")//首页主题馆
				.andStatusEqualTo("1")
				.andAutoUpDateLessThanOrEqualTo(new Date())
				.andAutoDownDateGreaterThan(new Date());
			adInfoExample.setOrderByClause("auto_up_date desc,id desc");
			List<AdInfo> adInfos = adInfoService.selectByExample(adInfoExample);
			if(adInfos!=null && adInfos.size()>0){
				m.addObject("id", adInfos.get(0).getId());
			}
			m.addObject("positionFlag", "12");
			m.setViewName("/appMng/adMng/homePage/themePavilionsList");
		}else if(flag.equals("7")){//日常营销入口装修
			AdInfoExample adInfoExample = new AdInfoExample();
			AdInfoExample.Criteria adInfoCriteria = adInfoExample.createCriteria();
			adInfoCriteria.andDelFlagEqualTo("0")
				.andTypeEqualTo("3")//装修页
				.andCatalogEqualTo("1")//首页
				.andPositionEqualTo("13")//日常营销
				.andStatusEqualTo("1")
				.andAutoUpDateLessThanOrEqualTo(new Date())
				.andAutoDownDateGreaterThan(new Date());
			adInfoExample.setOrderByClause("auto_up_date desc,id desc");
			List<AdInfo> adInfos = adInfoService.selectByExample(adInfoExample);
			if(adInfos!=null && adInfos.size()>0){
				m.addObject("id", adInfos.get(0).getId());
			}
			m.addObject("positionFlag", "13");
			m.setViewName("/appMng/adMng/homePage/dailyMarketingList");
		}else if(flag.equals("8")){//商品主题馆装修
			AdInfoExample adInfoExample = new AdInfoExample();
			AdInfoExample.Criteria adInfoCriteria = adInfoExample.createCriteria();
			adInfoCriteria.andDelFlagEqualTo("0")
				.andTypeEqualTo("3")//装修页
				.andCatalogEqualTo("1")//首页
				.andPositionEqualTo("14")//商品主题馆
				.andStatusEqualTo("1")
				.andAutoUpDateLessThanOrEqualTo(new Date())
				.andAutoDownDateGreaterThan(new Date());
			adInfoExample.setOrderByClause("auto_up_date desc,id desc");
			List<AdInfo> adInfos = adInfoService.selectByExample(adInfoExample);
			if(adInfos!=null && adInfos.size()>0){
				m.addObject("id", adInfos.get(0).getId());
			}
			m.addObject("positionFlag", "14");
			m.setViewName("/appMng/adMng/homePage/productThemePavilionsList");
		}
		m.addObject("autoUpDate", sdf.format(new Date()));
		m.addObject("adInfoStatusList", DataDicUtil.getStatusList("BU_AD_INFO", "STATUS"));
		return m;
	}
	
	/**
	 * 
	 * @Title PrefectureImageList   
	 * @Description    
	 * @author pengl
	 * @date 2017年10月17日 下午4:13:32
	 */
	@ResponseBody
	@RequestMapping(value = "/appMng/prefectureImageList.shtml")
	public Map<String, Object> prefectureImageList(HttpServletRequest request, Page page, String position, 
			String catalog, String adInfoStatus, String autoUpDate, String flag) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			AdInfoCustomExample adInfoCustomExample = new AdInfoCustomExample();
			AdInfoCustomExample.AdInfoCustomCriteria adInfoCustomCriteria = adInfoCustomExample.createCriteria();
			adInfoCustomCriteria.andDelFlagEqualTo("0").andTypeEqualTo("1");
			adInfoCustomExample.setOrderByClause(" a.auto_up_date desc");
			if(flag.equals("3")) {
				adInfoCustomCriteria.andCatalogEqualTo("1");
				if(!StringUtil.isEmpty(position)) {
					adInfoCustomCriteria.andPositionEqualTo(position);
				}else{
					List<String> list = new ArrayList<String>();
					list.add("4");
					list.add("5");
					list.add("6");
					list.add("10");
					list.add("11");
					list.add("2");
					list.add("3");
					adInfoCustomCriteria.andPositionIn(list);
				}
			}else if(flag.equals("5")) {
				adInfoCustomCriteria.andPositionEqualTo("1");
				if(!StringUtil.isEmpty(catalog)) {
					adInfoCustomCriteria.andCatalogEqualTo(catalog);
				}else{
					List<String> list = new ArrayList<String>();
					list.add("6");
					list.add("7");
					list.add("8");
					list.add("9");
					list.add("10");
					adInfoCustomCriteria.andCatalogIn(list);
				}
			}
			if(!StringUtil.isEmpty(adInfoStatus))
				adInfoCustomCriteria.andStatusEqualTo(adInfoStatus);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(autoUpDate)) {
				adInfoCustomCriteria.andAutoUpDateLessThanOrEqualTo(dateFormat.parse(autoUpDate+":00"));
			}/*else{
				adInfoCustomCriteria.andAutoUpDateLessThanOrEqualTo(dateFormat.parse(dateFormat.format(new Date())));
			}*/
			totalCount = adInfoService.countAdInfoCustomByExample(adInfoCustomExample);
			adInfoCustomExample.setLimitStart(page.getLimitStart());
			adInfoCustomExample.setLimitSize(page.getLimitSize());
			List<AdInfoCustom> adInfoCustoms = adInfoService.selectAdInfoCustomByExample(adInfoCustomExample);
			resMap.put("Rows", adInfoCustoms);
			resMap.put("Total", totalCount);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title aouPrefectureImage   
	 * @Description    
	 * @author pengl
	 * @date 2017年10月20日 上午11:35:01
	 */
	@RequestMapping(value = "/appMng/aouPrefectureImage.shtml")
	public ModelAndView aouPrefectureImage(HttpServletRequest request, String statusFlag,String linkUrl) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg = "";
		AdInfo newAdInfo = new AdInfo();
		Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
		String linkType = request.getParameter("linkType");
		String linkId = request.getParameter("linkId");
		try {
			if(!StringUtil.isEmpty(request.getParameter("id")))
				newAdInfo.setId(Integer.parseInt(request.getParameter("id")));
			if(!StringUtil.isEmpty(request.getParameter("position"))) 
				newAdInfo.setPosition(request.getParameter("position"));
			if(!StringUtil.isEmpty(request.getParameter("catalog"))) 
				newAdInfo.setCatalog(request.getParameter("catalog"));
			if(!StringUtil.isEmpty(request.getParameter("remarks")))
				newAdInfo.setRemarks(request.getParameter("remarks"));
			if(!StringUtil.isEmpty(request.getParameter("autoUpDate"))) {
				String autoUpDate = request.getParameter("autoUpDate");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				newAdInfo.setAutoUpDate(sdf.parse(autoUpDate));
			}
			if(!StringUtil.isEmpty(request.getParameter("pic")))
				newAdInfo.setPic(request.getParameter("pic"));
			if(statusFlag.equals("3")) {
				if(newAdInfo.getPosition().equals("2") || newAdInfo.getPosition().equals("3") || newAdInfo.getPosition().equals("10") || newAdInfo.getPosition().equals("11") ) {
					if(!StringUtil.isEmpty(linkType))
						newAdInfo.setLinkType(linkType);
					if(!StringUtil.isEmpty(linkId))
						if (linkType.equals("15")) {
							newAdInfo.setLinkUrl(linkId);
						}else {
							newAdInfo.setLinkId(Integer.parseInt(linkId));
						}
					if(!StringUtil.isEmpty(linkUrl))					
						newAdInfo.setLinkUrl(linkUrl);
				}
			}else if(statusFlag.equals("5")) {
				if(newAdInfo.getCatalog().equals("7") || newAdInfo.getCatalog().equals("9") ) {
					if(!StringUtil.isEmpty(linkType)){
						newAdInfo.setLinkType(linkType);
					}
					if(!StringUtil.isEmpty(linkId)){
						if("3".equals(linkType)){//3.商品，此时的linkId为商品code
							ProductExample e = new ProductExample();
							e.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(linkId);
							List<Product> products = productService.selectByExample(e);
							if(products!=null && products.size()>0){
								newAdInfo.setLinkId(products.get(0).getId());
								newAdInfo.setLinkUrl(linkId);
							}else{
								code = StateCode.JSON_AJAX_ERROR.getStateCode();
								msg = "商品id输入有误，商品id为12位数字，请检查。";
								return new ModelAndView(rtPage, resMap);
							}
						}else{
							if(!"4".equals(linkType) && !"5".equals(linkType)){
								newAdInfo.setLinkId(Integer.parseInt(linkId));
							}else{
								newAdInfo.setLinkId(0);
							}
						}
					}
					if(!StringUtil.isEmpty(linkUrl)){
						newAdInfo.setLinkUrl(linkUrl);
						if("10".equals(linkType)){//10.商家店铺
							MchtInfoExample e = new MchtInfoExample();
//							e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtCodeEqualTo(linkUrl);
							e.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(linkUrl);
							List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(e);
							if(mchtInfos!=null && mchtInfos.size()>0){
								newAdInfo.setLinkId(mchtInfos.get(0).getId());
							}else{
								code = StateCode.JSON_AJAX_ERROR.getStateCode();
								msg = "商品店铺序号输入有误，请检查。";
								resMap.put(this.JSON_RESULT_CODE, code);
								resMap.put(this.JSON_RESULT_MESSAGE, msg);
								return new ModelAndView(rtPage, resMap);
							}
						}
					}
				}
			}
			if(newAdInfo.getId() == null) {
				newAdInfo.setType("1");
				if(statusFlag.equals("3")) {
					newAdInfo.setCatalog("1");
				}else if(statusFlag.equals("5")) {
					newAdInfo.setPosition("1");
				}
				if(StringUtil.isEmpty(newAdInfo.getLinkType()))
					newAdInfo.setLinkType("5");
				newAdInfo.setStatus("0");
				newAdInfo.setCreateBy(staffId);
				newAdInfo.setCreateDate(new Date());
				newAdInfo.setDelFlag("0");
				adInfoService.insertSelective(newAdInfo);
			}else{
				newAdInfo.setUpdateBy(staffId);
				newAdInfo.setUpdateDate(new Date());
				AdInfoExample adInfoExample = new AdInfoExample();
				AdInfoExample.Criteria adInfoCriteria = adInfoExample.createCriteria();
				adInfoCriteria.andDelFlagEqualTo("0")
					.andIdEqualTo(newAdInfo.getId());
				adInfoService.updateByExampleSelective(newAdInfo, adInfoExample);
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
	
	/**
	 * 
	 * @Title BrandRecommendList   
	 * @Description    
	 * @author pengl
	 * @date 2017年10月17日 下午4:11:07
	 */
	@ResponseBody
	@RequestMapping(value = "/appMng/brandRecommendList.shtml")
	public Map<String, Object> brandRecommendList (HttpServletRequest request, Page page, 
			String adInfoStatus, String autoUpDate ){
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount =0;
		try {
			AdInfoCustomExample adInfoCustomExample = new AdInfoCustomExample();
			AdInfoCustomExample.AdInfoCustomCriteria adInfoCustomCriteria = adInfoCustomExample.createCriteria();
			adInfoCustomCriteria.andDelFlagEqualTo("0")
				.andTypeEqualTo("1")
				.andCatalogEqualTo("1")
				.andPositionEqualTo("7"); // 首页品牌专区--位置
			adInfoCustomExample.setOrderByClause(" a.auto_up_date desc");
			if(!StringUtil.isEmpty(adInfoStatus))
				adInfoCustomCriteria.andStatusEqualTo(adInfoStatus);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(autoUpDate)) {
				adInfoCustomCriteria.andAutoUpDateLessThanOrEqualTo(dateFormat.parse(autoUpDate+":00"));
			}/*else{
				adInfoCustomCriteria.andAutoUpDateLessThanOrEqualTo(dateFormat.parse(dateFormat.format(new Date())));
			}*/
			totalCount = adInfoService.countAdInfoCustomByExample(adInfoCustomExample);
			
			adInfoCustomExample.setLimitStart(page.getLimitStart());
			adInfoCustomExample.setLimitSize(page.getLimitSize());
			List<AdInfoCustom> adInfoCustoms = adInfoService.selectAdInfoCustomByExample(adInfoCustomExample);
			resMap.put("Rows", adInfoCustoms);
			resMap.put("Total", totalCount);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title themePavilionsList   
	 * @Description    
	 * @author yjc
	 * @date 2018年3月9日 
	 */
	@ResponseBody
	@RequestMapping(value = "/appMng/adMng/themePavilionsList.shtml")
	public Map<String, Object> themePavilionsList (HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount =0;
		try {
			AdInfoExample adInfoExample = new AdInfoExample();
			AdInfoExample.Criteria criteria = adInfoExample.createCriteria();
			criteria.andDelFlagEqualTo("0")
				.andTypeEqualTo("3")//装修页
				.andCatalogEqualTo("1")//首页
				.andPositionEqualTo("12"); //首页主题馆
			adInfoExample.setOrderByClause("id desc");
			String status = request.getParameter("status");
			if(!StringUtil.isEmpty(status)){
				criteria.andStatusEqualTo(status);
			}
			totalCount = adInfoService.countByExample(adInfoExample);
			adInfoExample.setLimitStart(page.getLimitStart());
			adInfoExample.setLimitSize(page.getLimitSize());
			List<AdInfo> adInfos = adInfoService.selectByExample(adInfoExample);
			resMap.put("Rows", adInfos);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title dailyMarketingList   
	 * @Description TODO(日常营销入口装修)   
	 * @author pengl
	 * @date 2018年5月28日 下午9:06:58
	 */
	@ResponseBody
	@RequestMapping(value = "/appMng/adMng/dailyMarketingList.shtml")
	public Map<String, Object> dailyMarketingList (HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount =0;
		try {
			AdInfoExample adInfoExample = new AdInfoExample();
			AdInfoExample.Criteria criteria = adInfoExample.createCriteria();
			criteria.andDelFlagEqualTo("0")
			.andTypeEqualTo("3")//装修页
			.andCatalogEqualTo("1")//首页
			.andPositionEqualTo("13"); //日常营销
			adInfoExample.setOrderByClause("id desc");
			String status = request.getParameter("status");
			if(!StringUtil.isEmpty(status)){
				criteria.andStatusEqualTo(status);
			}
			totalCount = adInfoService.countByExample(adInfoExample);
			adInfoExample.setLimitStart(page.getLimitStart());
			adInfoExample.setLimitSize(page.getLimitSize());
			List<AdInfo> adInfos = adInfoService.selectByExample(adInfoExample);
			resMap.put("Rows", adInfos);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title themePavilionsList   
	 * @Description    
	 * @author yjc
	 * @date 2018年6月13日 
	 */
	@ResponseBody
	@RequestMapping(value = "/appMng/adMng/productThemePavilionsList.shtml")
	public Map<String, Object> productThemePavilionsList (HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount =0;
		try {
			AdInfoExample adInfoExample = new AdInfoExample();
			AdInfoExample.Criteria criteria = adInfoExample.createCriteria();
			criteria.andDelFlagEqualTo("0")
				.andTypeEqualTo("3")//装修页
				.andCatalogEqualTo("1")//首页
				.andPositionEqualTo("14"); //商品主题馆装修
			adInfoExample.setOrderByClause("id desc");
			String status = request.getParameter("status");
			if(!StringUtil.isEmpty(status)){
				criteria.andStatusEqualTo(status);
			}
			totalCount = adInfoService.countByExample(adInfoExample);
			adInfoExample.setLimitStart(page.getLimitStart());
			adInfoExample.setLimitSize(page.getLimitSize());
			List<AdInfo> adInfos = adInfoService.selectByExample(adInfoExample);
			resMap.put("Rows", adInfos);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 编辑主题馆
	 * @Title    
	 * @Description    
	 */
	@RequestMapping(value = "/appMng/adMng/editThemePavilions.shtml")
	public ModelAndView editThemePavilions(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		String id = request.getParameter("id");
		String positionFlag = request.getParameter("positionFlag");
		if(!StringUtil.isEmpty(id)) {
			AdInfoExample adInfoExample = new AdInfoExample();
			AdInfoExample.Criteria adInfoCriteria = adInfoExample.createCriteria();
			adInfoCriteria.andDelFlagEqualTo("0")
				.andIdEqualTo(Integer.parseInt(id));
			List<AdInfo> adInfoList = adInfoService.selectByExample(adInfoExample);
			if(adInfoList != null && adInfoList.size() > 0) {
				m.addObject("adInfo", adInfoList.get(0));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				m.addObject("autoUpDate", sdf.format(adInfoList.get(0).getAutoUpDate()));
				m.addObject("autoDownDate", sdf.format(adInfoList.get(0).getAutoDownDate()));
			}
		}
		m.addObject("id", id);
		m.addObject("positionFlag", positionFlag);
		m.setViewName("/appMng/adMng/homePage/editThemePavilions");
		return m;
	}
	
	/**
	 * 保存首页主题馆
	 * @Title    
	 * @Description    
	 */
	@RequestMapping(value = "/appMng/adMng/saveThemePavilions.shtml")
	@ResponseBody
	public Map<String, Object> saveThemePavilions(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String autoUpDate = request.getParameter("autoUpDate");
			String autoDownDate = request.getParameter("autoDownDate");
			String positionFlag = request.getParameter("positionFlag");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			AdInfo adInfo = new AdInfo();
			if(!StringUtil.isEmpty(id)){
				adInfo = adInfoService.selectByPrimaryKey(Integer.parseInt(id));
				adInfo.setUpdateDate(new Date());
				adInfo.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			}else{
				adInfo.setDelFlag("0");
				adInfo.setCreateDate(new Date());
				adInfo.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				adInfo.setType("3");//装修页
				adInfo.setCatalog("1");//首页
				if(!StringUtil.isEmpty(positionFlag)) {
					adInfo.setPosition(positionFlag);//首页主题馆
				}
				adInfo.setStatus("0");//0.下架
			}
			adInfo.setRemarks(request.getParameter("remarks"));
			adInfo.setAutoUpDate(sdf.parse(autoUpDate));
			adInfo.setAutoDownDate(sdf.parse(autoDownDate));
			adInfoService.saveThemePavilions(adInfo);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 预览主题馆
	 * @Title    
	 * @Description    
	 */
	@RequestMapping(value = "/appMng/adMng/previewThemePavilions.shtml")
	public ModelAndView previewThemePavilions(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		String decorateInfoId = request.getParameter("decorateInfoId");
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
					if(decorateModuleCustom.getModuleType().equals("1") || decorateModuleCustom.getModuleType().equals("9") || decorateModuleCustom.getModuleType().equals("8")){//图片
						pics.add(decorateModuleCustom.getPic());
						decorateModuleCustom.setPics(pics);
					}
				}
			}
			decorateAreaCustom.setDecorateModuleCustoms(decorateModuleCustoms);
		}
		m.addObject("decorateAreaCustoms", decorateAreaCustoms);
		m.setViewName("/appMng/adMng/homePage/previewThemePavilions");
		return m;
	}
	
	/**
	 * 装修页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/design.shtml")
	public ModelAndView design(HttpServletRequest request) {
		String rtPage = "/appMng/adMng/homePage/design";
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
	@RequestMapping(value = "/appMng/adMng/editDecorateModule.shtml")
	public ModelAndView editDecorateModule(HttpServletRequest request) {
		String rtPage = "/appMng/adMng/homePage/editDecorateModule";
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
						jo.put("linkValue", mchtInfo.getMchtCode());
					}else{//非商品,非商家店铺
						if(moduleMap.getLinkType().equals("9") || moduleMap.getLinkType().equals("14") ){
							jo.put("linkValue", moduleMap.getLinkUrl());
						}else if(moduleMap.getLinkType().equals("16") || moduleMap.getLinkType().equals("18") 
								|| moduleMap.getLinkType().equals("20") || moduleMap.getLinkType().equals("22") 
								|| moduleMap.getLinkType().equals("24") || moduleMap.getLinkType().equals("26") 
								|| moduleMap.getLinkType().equals("28") || moduleMap.getLinkType().equals("30")){//二级分类
							jo.put("linkValue", moduleMap.getLinkUrl());
							String[] productTypeIdArray = moduleMap.getLinkUrl().split(",");
							ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(productTypeIdArray[0]));
							if(productType!=null){
								jo.put("firstProductTypeId", productType.getParentId());
							}
						}else if (moduleMap.getLinkType().equals("19") || moduleMap.getLinkType().equals("21") || moduleMap.getLinkType().equals("23") || moduleMap.getLinkType().equals("25") || moduleMap.getLinkType().equals("27") || moduleMap.getLinkType().equals("29") || moduleMap.getLinkType().equals("31")) {//品牌ID
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
			}else if(decorateModule.getModuleType().equals("12")){
				 rtPage = "/appMng/adMng/homePage/editFiveGranuleNevigateModule";
				 ModuleNavigationExample example = new ModuleNavigationExample();
				 example.createCriteria().andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId)).andDelFlagEqualTo("0");
				 List<ModuleNavigation> ModuleNavigationList = moduleNavigationService.selectByExample(example);
				 Object[] ModuleNavigationListOne= new Object[12];
				 Object[] ModuleNavigationListTwo= new Object[12];
				 Object[] ModuleNavigationListThree= new Object[12];
				 for (int j = 0; j < ModuleNavigationList.size(); j++) {
					Integer row = ModuleNavigationList.get(j).getRow();
					if(row.equals(1)){
						ModuleNavigationListOne[ModuleNavigationList.get(j).getCol()-1]=ModuleNavigationList.get(j);
					}else if(row.equals(2)){
						ModuleNavigationListTwo[ModuleNavigationList.get(j).getCol()-1]=ModuleNavigationList.get(j);
					}else if(row.equals(3)){
						ModuleNavigationListThree[ModuleNavigationList.get(j).getCol()-1]=ModuleNavigationList.get(j);
					}else {
						continue;
					}
				}
				 resMap.put("ModuleNavigationListOne", Arrays.asList(ModuleNavigationListOne));
				 resMap.put("ModuleNavigationListTwo", Arrays.asList(ModuleNavigationListTwo));
				 resMap.put("ModuleNavigationListThree", Arrays.asList(ModuleNavigationListThree));
			}
		}else{
			if(moduleType.equals("1") || moduleType.equals("9")){//1.图片 9.滑动栏目
				ProductTypeExample pte = new ProductTypeExample();
				ProductTypeExample.Criteria ptec = pte.createCriteria();
				ptec.andDelFlagEqualTo("0");
				ptec.andTLevelEqualTo(1);
				ptec.andStatusEqualTo("1");
				List<ProductType> productTypes = productTypeService.selectByExample(pte);
				resMap.put("productTypes", productTypes);
			}
			if(moduleType.equals("12")){
				rtPage = "/appMng/adMng/homePage/editFiveGranuleNevigateModule";
			}
		}
		
		BrandteamTypeExample e = new BrandteamTypeExample();//新品牌团
		e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(e);
		resMap.put("brandteamTypes", brandteamTypes);
		
		MallCategoryExample mcCategoryExample=new MallCategoryExample();//商城一级分类
		mcCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MallCategory> mallCategories=mallCategoryService.selectByExample(mcCategoryExample);
		resMap.put("mallCategories", mallCategories);

		resMap.put("decorateInfoId", decorateInfoId);
		resMap.put("decorateAreaId", decorateAreaId);
		resMap.put("decorateModuleId", decorateModuleId);
		resMap.put("moduleType", moduleType);
		resMap.put("seqNo", seqNo);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 添加/编辑五粒导航模块
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/editModuleNavigation.shtml")
	public ModelAndView editModuleNavigation(HttpServletRequest request) {
		String rtPage = "/appMng/adMng/homePage/editModuleNavigation";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateModuleId = request.getParameter("decorateModuleId");
		String moduleNavigationId = request.getParameter("ModuleNavigationId");
		if(!StringUtils.isEmpty(moduleNavigationId)){
			ModuleNavigation moduleNavigation = moduleNavigationService.selectByPrimaryKey(Integer.parseInt(moduleNavigationId));
			if(moduleNavigation.getLinkType().equals("16") || moduleNavigation.getLinkType().equals("18")|| moduleNavigation.getLinkType().equals("20")|| moduleNavigation.getLinkType().equals("22")
					|| moduleNavigation.getLinkType().equals("24")|| moduleNavigation.getLinkType().equals("26")|| moduleNavigation.getLinkType().equals("28")|| moduleNavigation.getLinkType().equals("30")){
				String productTypeId = moduleNavigation.getLinkValue().split(",")[0];
				ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(productTypeId));
				Integer firstProductTypeId = productType.getParentId();
				resMap.put("firstProductTypeId", firstProductTypeId);
			}
			if(moduleNavigation.getLinkType().equals("3")){//商品
				Product product = productService.selectByPrimaryKey(Integer.parseInt(moduleNavigation.getLinkValue()));
				moduleNavigation.setLinkValue(product.getCode());
			}
			resMap.put("moduleNavigation", moduleNavigation);
		}else{
			ModuleNavigation moduleNavigation = new ModuleNavigation();
			moduleNavigation.setLinkType("0");
			moduleNavigation.setLinkValue("0");
			resMap.put("moduleNavigation", moduleNavigation);
		}
		ProductTypeExample pte = new ProductTypeExample();//旧品牌特卖一级目录
		ProductTypeExample.Criteria ptec = pte.createCriteria();
		ptec.andDelFlagEqualTo("0");
		ptec.andTLevelEqualTo(1);
		ptec.andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes", productTypes);
		
		BrandteamTypeExample e = new BrandteamTypeExample();//新品牌团
		e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(e);
		resMap.put("brandteamTypes", brandteamTypes);
		
		MallCategoryExample mcCategoryExample=new MallCategoryExample();//商城一级分类
		mcCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MallCategory> mallCategories=mallCategoryService.selectByExample(mcCategoryExample);
		resMap.put("mallCategories", mallCategories);
		resMap.put("decorateModuleId", decorateModuleId);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存/修改五粒导航
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/insertModuleNavigation.shtml")
	@ResponseBody
	public Map<String, Object> insertModuleNavigation(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try{
			String moduleNavigationId = request.getParameter("moduleNavigationId");
			String decorateModuleId = request.getParameter("decorateModuleId");
			String pic = request.getParameter("pic");
			String linkType = request.getParameter("linkType");
			String linkValue = request.getParameter("linkValue");
			String col = request.getParameter("col");
			String row = request.getParameter("row");
			
			if(linkType.equals("3")){
				ProductExample example = new ProductExample();
				Criteria criteria = example.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(linkValue);
				List<Product> products = productService.selectByExample(example);
				if(products!=null && products.size()>0){
					linkValue=products.get(0).getId()+"";
				}else{
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "保存失败，商品ID不存在");
					return resMap;
				}
			}
			if(linkType.equals("13")){
				MchtInfoExample e = new MchtInfoExample();
				e.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(linkValue);
				List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(e);
				if(mchtInfos!=null && mchtInfos.size()>0){
					linkValue=mchtInfos.get(0).getId()+"";
				}else{
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "保存失败，商家编号不存在");
					return resMap;
				}
			}
			if(!StringUtils.isEmpty(moduleNavigationId)){
				ModuleNavigationExample example = new ModuleNavigationExample();
				example.createCriteria().andDelFlagEqualTo("0").andColEqualTo(Integer.parseInt(col)).andRowEqualTo(Integer.parseInt(row)).andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
				List<ModuleNavigation> list = moduleNavigationService.selectByExample(example);
				if(list!=null && list.size()>0 && list.get(0).getId()!=Integer.parseInt(moduleNavigationId)){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "该行该列已有数据,请检查后再次修改");
					return resMap;
				}
				ModuleNavigation moduleNavigation = new ModuleNavigation();
				moduleNavigation.setId(Integer.parseInt(moduleNavigationId));
				moduleNavigation.setDecorateModuleId(Integer.parseInt(decorateModuleId));
				moduleNavigation.setPic(pic);
				moduleNavigation.setLinkType(linkType);
				moduleNavigation.setLinkValue(linkValue);
				moduleNavigation.setCol(Integer.parseInt(col));
				moduleNavigation.setRow(Integer.parseInt(row));
				moduleNavigation.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				moduleNavigation.setUpdateDate(new Date());
				moduleNavigationService.updateByPrimaryKeySelective(moduleNavigation);
			}else {
				ModuleNavigationExample example = new ModuleNavigationExample();
				example.createCriteria().andDelFlagEqualTo("0").andColEqualTo(Integer.parseInt(col)).andRowEqualTo(Integer.parseInt(row)).andDecorateModuleIdEqualTo(Integer.parseInt(decorateModuleId));
				List<ModuleNavigation> list = moduleNavigationService.selectByExample(example);
				if(list!=null && list.size()>0){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "该行该列已有数据,请检查后再次添加");
					return resMap;
				}
				ModuleNavigation moduleNavigation = new ModuleNavigation();
				moduleNavigation.setDecorateModuleId(Integer.parseInt(decorateModuleId));
				moduleNavigation.setPic(pic);
				moduleNavigation.setLinkType(linkType);
				moduleNavigation.setLinkValue(linkValue);
				moduleNavigation.setCol(Integer.parseInt(col));
				moduleNavigation.setRow(Integer.parseInt(row));
				moduleNavigation.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				moduleNavigation.setCreateDate(new Date());
				moduleNavigation.setDelFlag("0");
				moduleNavigationService.insertSelective(moduleNavigation);
			}
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "失败");
		}
		return resMap;
	}
	
	/**
	 * 删除五粒导航
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/delModuleNavigation.shtml")
	@ResponseBody
	public Map<String, Object> delModuleNavigation(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("moduleNavigationId");
			ModuleNavigation moduleNavigation = new ModuleNavigation();
			moduleNavigation.setId(Integer.parseInt(id));
			moduleNavigation.setDelFlag("1");
			moduleNavigation.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			moduleNavigation.setUpdateDate(new Date());
			moduleNavigationService.updateByPrimaryKeySelective(moduleNavigation);
		} catch (Exception e) {
			// TODO: handle exception
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "删除失败");
		}
		return resMap;
	}
	
	/**
	 * 保存图片模块
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/saveDecorateModule.shtml")
	@ResponseBody
	public Map<String, Object> saveDecorateModule(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		try {
			String decorateModuleId = request.getParameter("decorateModuleId");
			String decorateAreaId = request.getParameter("decorateAreaId");
			String moduleType = request.getParameter("moduleType");
			String seqNo = request.getParameter("seqNo");
			DecorateModule decorateModule = new DecorateModule();
			if(!StringUtils.isEmpty(decorateModuleId)){
				decorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(decorateModuleId));
				moduleType = decorateModule.getModuleType();
			}else{
				decorateModule.setDelFlag("0");
				decorateModule.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				decorateModule.setCreateDate(new Date());
				decorateModule.setDecorateAreaId(Integer.parseInt(decorateAreaId));
				decorateModule.setModuleType(moduleType);
			}
			if(!StringUtils.isEmpty(seqNo)){
				decorateModule.setSeqNo(Integer.parseInt(seqNo));
			}
			if(moduleType.equals("1")){//1.图片
				String pic = request.getParameter("pic");
				decorateModule.setPic(pic);
				String moduleMapJsonStr = request.getParameter("moduleMapJsonStr");
				List<ModuleMap> moduleMaps = new ArrayList<ModuleMap>();
				if(!StringUtils.isEmpty(moduleMapJsonStr)){
					JSONArray ja = JSONArray.fromObject(moduleMapJsonStr);
					for(int i=0;i<ja.size();i++){
						ModuleMap moduleMap = new ModuleMap();
						JSONObject jo = (JSONObject)ja.get(i);
						String x1 = jo.getString("x1");
						String y1 = jo.getString("y1");
						String x2 = jo.getString("x2");
						String y2 = jo.getString("y2");
						String coords = x1+","+y1+","+x2+","+y2;
						String linkType = jo.getString("linkType");
						if(!linkType.equals("3")){//非商品
							int linkValue = jo.getInt("linkValue");
							moduleMap.setLinkValue(linkValue);
						}else{//商品
							String linkValue = jo.getString("linkValue");
							ProductExample pe = new ProductExample();
							ProductExample.Criteria pec = pe.createCriteria();
							pec.andDelFlagEqualTo("0");
							pec.andCodeEqualTo(linkValue);
							List<Product> products = productService.selectByExample(pe);
							if(products == null || products.size() == 0){
								continue;
							}else{
								moduleMap.setLinkValue(products.get(0).getId());
							}
						}
						moduleMap.setDelFlag("0");
						moduleMap.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
						moduleMap.setCreateDate(new Date());
						moduleMap.setCoords(coords);
						moduleMap.setLinkType(linkType);
						moduleMaps.add(moduleMap);
					}
				}
				decorateModuleService.savePicModule(decorateModule,moduleMaps);
			}
			resMap.put("decorateModuleId", decorateModule.getId());
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 上移下移图片模块
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/moveDecorateModule.shtml")
	@ResponseBody
	public Map<String, Object> moveDecorateModule(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String prevId = request.getParameter("prevId");
			String nextId = request.getParameter("nextId");
			if(!StringUtil.isEmpty(prevId) && !StringUtil.isEmpty(nextId)){
				DecorateModule prevDecorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(prevId));
				DecorateModule nextDecorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(nextId));
				if(prevDecorateModule.getSeqNo()!=null && nextDecorateModule.getSeqNo()!=null){
					int prevSeqNo = prevDecorateModule.getSeqNo();
					int nextSeqNo = nextDecorateModule.getSeqNo();
					prevDecorateModule.setSeqNo(nextSeqNo);
					nextDecorateModule.setSeqNo(prevSeqNo);
				}else{
					prevDecorateModule.setSeqNo(Integer.parseInt(request.getParameter("prevSeqNo")));
					nextDecorateModule.setSeqNo(Integer.parseInt(request.getParameter("nextSeqNo")));
				}
				decorateModuleService.update(prevDecorateModule,nextDecorateModule);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "操作失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	/**
	 * 删除图片模块
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/appMng/adMng/deleteDecorateModule.shtml")
	@ResponseBody
	public Map<String, Object> deleteDecorateModule(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "删除成功");
		try {
			String id = request.getParameter("id");
			DecorateModule decorateModule = decorateModuleService.selectByPrimaryKey(Integer.parseInt(id));
			decorateModule.setDelFlag("1");
			decorateModule.setUpdateDate(new Date());
			decorateModule.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			decorateModuleService.delete(decorateModule);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "删除失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}
	
	
	
	/**
	 * 
	 * @Title updateAdInfoSeqNo   
	 * @Description    
	 * @author pengl
	 * @date 2017年10月18日 上午9:32:59
	 */
	@ResponseBody
	@RequestMapping(value = "/appMng/updateAdInfoSeqNo.shtml")
	public Map<String, Object> updateAdInfoSeqNo(HttpServletRequest request, Integer id, Integer seqNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			AdInfoExample adInfoExample = new AdInfoExample();
			AdInfoExample.Criteria adInfoCriteria = adInfoExample.createCriteria();
			adInfoCriteria.andDelFlagEqualTo("0")
				.andIdEqualTo(id);
			AdInfo adInfo = new AdInfo();
			adInfo.setSeqNo(seqNo);
			adInfoService.updateByExampleSelective(adInfo, adInfoExample);
			map.put("statusCode", 200);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("statusCode", 9999);
		}
		return map;
	}
	
	/**
	 * 
	 * @Title addAdinfo   
	 * @Description    
	 * @author pengl
	 * @date 2017年10月18日 上午11:12:13
	 */
	@RequestMapping(value = "/appMng/aouAdinfoManager.shtml")
	public ModelAndView aouAdinfoManager(HttpServletRequest request, Integer id, String flag, String statusFlag) {
		ModelAndView m = new ModelAndView();
		if(id != null) {
			m.addObject("id", id);
			AdInfoExample adInfoExample = new AdInfoExample();
			AdInfoExample.Criteria adInfoCriteria = adInfoExample.createCriteria();
			adInfoCriteria.andDelFlagEqualTo("0")
				.andIdEqualTo(id);
			List<AdInfo> adInfoList = adInfoService.selectByExample(adInfoExample);
			if(adInfoList != null && adInfoList.size() > 0) {
				m.addObject("adInfo", adInfoList.get(0));
		
			}
		}
		if(flag.equals("1")) {// 首页专区图片管理 添加、修改		
			m.setViewName("/appMng/adMng/homePage/aouPrefectureImage");
			m.addObject("statusFlag", statusFlag);
			
			
		}else if(flag.equals("2")) {// 首页品牌推荐 添加、修改		
			m.setViewName("/appMng/adMng/homePage/aouBrandRecommend");
		}
		
		BrandteamTypeExample e = new BrandteamTypeExample();//新品牌团
		e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(e);
		m.addObject("brandteamTypes", brandteamTypes);
		
		ProductTypeExample pte = new ProductTypeExample();//一级类目
		ProductTypeExample.Criteria ptec = pte.createCriteria();
		ptec.andDelFlagEqualTo("0");
		ptec.andTLevelEqualTo(1);
		ptec.andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		m.addObject("productTypes", productTypes);
		
		MallCategoryExample mce = new MallCategoryExample();
		mce.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MallCategory> mallCategorys = mallCategoryService.selectByExample(mce);
		m.addObject("mallCategorys", mallCategorys);
		return m;
	}
	
	/**
	 * 
	 * @Title updateLinkId   
	 * @Description    
	 * @author pengl
	 * @date 2017年10月19日 下午2:57:21
	 */
	@ResponseBody
	@RequestMapping(value = "/appMng/updateLinkId.shtml")
	public Map<String, Object> updateLinkId(HttpServletRequest request, String linkId, String linkType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusCode", 200);
		if(linkType.equals("1")) {
			ActivityAreaExample activityAreaExample = new ActivityAreaExample();
			ActivityAreaExample.Criteria activityAreaCriteria = activityAreaExample.createCriteria();
			activityAreaCriteria.andDelFlagEqualTo("0")
//				.andSourceEqualTo("1")
//				.andStatusEqualTo("1")
				.andIdEqualTo(Integer.parseInt(linkId));
			Integer totalCount = activityAreaService.countByExample(activityAreaExample);
			if (totalCount == 0){
//				map.put("statusCode", "会场不存在或未启用！");
				map.put("statusCode", "会场不存在");
			}
		}else if(linkType.equals("2")) {
			ActivityExample activityExample = new ActivityExample();
			ActivityExample.Criteria activityCriteria = activityExample.createCriteria();
			activityCriteria.andDelFlagEqualTo("0")
//				.andStatusEqualTo("6")
				.andIdEqualTo(Integer.parseInt(linkId));
			Integer totalCount = activityService.countByExample(activityExample);
			if (totalCount == 0){
//				map.put("statusCode", "活动不存在或未审核通过！");
				map.put("statusCode", "活动不存在");
			}
		}else if(linkType.equals("3")) {
			ProductExample pe = new ProductExample();
			ProductExample.Criteria pec = pe.createCriteria();
			pec.andDelFlagEqualTo("0");
			pec.andCodeEqualTo(linkId.toString());
			List<Product> products = productService.selectByExample(pe);
			if(products == null || products.size() == 0){
				map.put("statusCode", "商品不存在！");
			}else {
//				ProductCustom productCustom = productService.selectProductCustomByPrimaryKey(products.get(0).getId());
//				String productActivityStatus = productCustom.getProductActivityStatus();
//				if (!"2".equals(productActivityStatus) && !"3".equals(productActivityStatus) && !"4".equals(productActivityStatus)){
//					map.put("statusCode", "请检查商品的报名活动状态！");
//				}else{
//					map.put("statusCode", 200);
//				}
			}
			 
			/*ProductCustom productCustom = productService.selectProductCustomByPrimaryKey(linkId);
			if(productCustom == null || !"0".equals(productCustom.getDelFlag())) {
				map.put("statusCode", "商品不存在！");
			}else{
				String productActivityStatus = productCustom.getProductActivityStatus();
				if (!"2".equals(productActivityStatus) && !"3".equals(productActivityStatus) && !"4".equals(productActivityStatus)){
					map.put("statusCode", "请检查商品的报名活动状态！");
				}else{
					map.put("statusCode", 200);
				}
			}*/
		}else if(linkType.equals("7")){//自建页面
			CustomPage customPage = customPageService.selectByPrimaryKey(Integer.parseInt(linkId));
			if(customPage==null || !"0".equals(customPage.getDelFlag())){
				map.put("statusCode", "自建页面不存在！");
			}
		}else if(linkType.equals("10")){
			MchtInfoCustomExample mchtInfoExample = new MchtInfoCustomExample();
//			mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(linkId).andStatusEqualTo("1");
			mchtInfoExample.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(linkId);
			Integer totalCount = mchtInfoService.countByExample(mchtInfoExample);
			if(totalCount == 0){
				map.put("statusCode", "商家序号不存在！");
			}
		}else if(linkType.equals("29")){
			CouponExample example = new CouponExample();
			CouponExample.Criteria c = example.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andIdEqualTo(Integer.parseInt(linkId));
			Integer totalCount = couponService.countByExample(example);
			if(totalCount == 0){
				map.put("statusCode", "优惠券ID不存在！");
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @Title aouBrandRecommend   
	 * @Description    
	 * @author pengl
	 * @date 2017年10月19日 下午5:06:43
	 */
	@RequestMapping(value = "/appMng/aouBrandRecommend.shtml")
	public ModelAndView aouBrandRecommend(HttpServletRequest request, AdInfo adInfo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String rtPage = "/success/success";
		String code = null;
		String msg = "";
		Integer staffId = Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
		try {
			String autoUpDate = request.getParameter("autoUpDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			adInfo.setAutoUpDate(sdf.parse(autoUpDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			if("3".equals(adInfo.getLinkType())){
				ProductExample e = new ProductExample();
				e.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(adInfo.getLinkUrl());
				List<Product> products = productService.selectByExample(e);
				if(products!=null && products.size()>0){
					adInfo.setLinkId(products.get(0).getId());
					adInfo.setLinkUrl(adInfo.getLinkUrl());
				}else{
					code = StateCode.JSON_AJAX_ERROR.getStateCode();
					msg = "商品id输入有误，商品id为12位数字，请检查。";
					return new ModelAndView(rtPage, resMap);
				}
			}
			if("10".equals(adInfo.getLinkType())){//10.商家店铺
				MchtInfoExample e = new MchtInfoExample();
//				e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtCodeEqualTo(linkUrl);
				e.createCriteria().andDelFlagEqualTo("0").andMchtCodeEqualTo(adInfo.getLinkUrl());
				List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(e);
				if(mchtInfos!=null && mchtInfos.size()>0){
					adInfo.setLinkId(mchtInfos.get(0).getId());
				}else{
					code = StateCode.JSON_AJAX_ERROR.getStateCode();
					msg = "商品店铺序号输入有误，请检查。";
					resMap.put(this.JSON_RESULT_CODE, code);
					resMap.put(this.JSON_RESULT_MESSAGE, msg);
					return new ModelAndView(rtPage, resMap);
				}
			}
			
			
			if(adInfo.getId() == null) {
				adInfo.setType("1");
				adInfo.setCatalog("1");
				adInfo.setPosition("7");
				adInfo.setStatus("0");
				adInfo.setCreateBy(staffId);
				adInfo.setCreateDate(new Date());
				adInfo.setDelFlag("0");
				adInfoService.insertSelective(adInfo);
			}else{
				adInfo.setUpdateBy(staffId);
				adInfo.setUpdateDate(new Date());
				AdInfoExample adInfoExample = new AdInfoExample();
				AdInfoExample.Criteria adInfoCriteria = adInfoExample.createCriteria();
				adInfoCriteria.andDelFlagEqualTo("0")
					.andIdEqualTo(adInfo.getId());
				adInfoService.updateByExampleSelective(adInfo, adInfoExample);
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
	
	/**
	 * 
	 * @Title previewAd   
	 * @Description    
	 * @author pengl
	 * @date 2017年10月20日 下午3:48:52
	 */
	@RequestMapping(value = "/appMng/previewAd.shtml")
	public ModelAndView previewAd(HttpServletRequest request, String adInfoStatus, String autoUpDate ) {
		ModelAndView m = new ModelAndView();
		try {
			m.setViewName("/appMng/adMng/homePage/previewHomePage");
			for(int i=0; i<6; i++) {
				if(i != 5) {
					AdInfoExample adInfoExample = new AdInfoExample();
					AdInfoExample.Criteria adCriteria = adInfoExample.createCriteria();
					adCriteria.andDelFlagEqualTo("0").andTypeEqualTo("1").andCatalogEqualTo("1").andStatusEqualTo("1");
					if(!StringUtil.isEmpty(adInfoStatus))
						adCriteria.andStatusEqualTo(adInfoStatus);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(!StringUtil.isEmpty(autoUpDate)) {
						adCriteria.andAutoUpDateLessThanOrEqualTo(dateFormat.parse(autoUpDate+":00"));
					}else{
						adCriteria.andAutoUpDateLessThanOrEqualTo(dateFormat.parse(dateFormat.format(new Date())));
					}
					String position = "";
					if(i == 0) {
						position = "4";// 新用户专区
					}else if(i == 1) {
						position = "5";// 秒杀专区
					}else if(i == 2) {
						position = "6";// 爆款专区
					}else if(i == 3) {
						position = "2";// 专区小广告1
					}else if(i == 4) {
						position = "3";// 专区小广告2
					}
					adCriteria.andPositionEqualTo(position);
					adInfoExample.setLimitSize(1);
					adInfoExample.setLimitStart(0);
					adInfoExample.setOrderByClause(" auto_up_date desc");
					List<AdInfo> adInfoList = adInfoService.selectByExample(adInfoExample);
					if(adInfoList != null && adInfoList.size() > 0) {
						m.addObject("pic_"+i, adInfoList.get(0));
					}
				}else if(i == 5) {
					AdInfoExample adInfoExample = new AdInfoExample();
					AdInfoExample.Criteria adCriteria = adInfoExample.createCriteria();
					adCriteria.andDelFlagEqualTo("0").andTypeEqualTo("1").andStatusEqualTo("1");
					if(!StringUtil.isEmpty(adInfoStatus))
						adCriteria.andStatusEqualTo(adInfoStatus);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(!StringUtil.isEmpty(autoUpDate)) {
						adCriteria.andAutoUpDateLessThanOrEqualTo(dateFormat.parse(autoUpDate+":00"));
					}else{
						adCriteria.andAutoUpDateLessThanOrEqualTo(dateFormat.parse(dateFormat.format(new Date())));
					}
					adCriteria.andPositionEqualTo("7");
					adInfoExample.setLimitSize(8);
					adInfoExample.setLimitStart(0);
					adInfoExample.setOrderByClause(" auto_up_date desc");
					List<AdInfo> adInfoList = adInfoService.selectByExample(adInfoExample);
					Collections.sort(adInfoList, new Comparator<AdInfo>() {
			            @Override
			            public int compare(AdInfo c1, AdInfo c2) {
			            	if(c1.getSeqNo() == null || c1.getSeqNo() == 0) {
			            		if(c2.getSeqNo() == null || c2.getSeqNo() == 0) 
			            			return 0;
			            		else
			            			return 1;
			            	}else {
			            		if(c2.getSeqNo() == null || c2.getSeqNo() == 0) {
			            			return -1;
			            		} else {
			            			//升序
					                return c1.getSeqNo().compareTo(c2.getSeqNo());
			            		}
			            	}
			            }
			        });
					m.addObject("picBrand", adInfoList);// 品牌推荐
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	/**
	 * 用户信息统计首页
	 * 
	 * @return
	 */
	@RequestMapping(value ="/appMng/InformationTatistics/list.shtml")
	public ModelAndView appMngList(HttpServletRequest request) {	
		String rtPage = "/appMng/InformationTatistics/list";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();		
		return new ModelAndView(rtPage,resMap);
	}
	
	//用户信息统计列表
	@RequestMapping(value = "/appMng/InformationTatistics/data.shtml ")
	@ResponseBody
	public Map<String, Object> SpecificationManagemen(HttpServletRequest request,Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;
		List<MemberFavorItesCustom> dataList=new ArrayList<MemberFavorItesCustom>();

		try {
			MemberFavorItesCustomExample  memberFavoritesCustomExample=new MemberFavorItesCustomExample();
			MemberFavorItesCustomCriteria criteria=memberFavoritesCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			
            if(request.getParameter("sex").equals("1")){
            	criteria.andSexEqualTo("1");           	
            }
            
            if(request.getParameter("sex").equals("2")){
            	criteria.andSexEqualTo("2");           	
            }
            		
			if(!StringUtil.isEmpty(request.getParameter("age"))) {
				if(request.getParameter("age").equals("between")){
					criteria.andAgeBetween("15", "20");
					
				}
				if(request.getParameter("age").equals("before")){
					criteria.andAgeBetween("20", "35");
					
				}
				
				if(request.getParameter("age").equals("greater")){
					criteria.andAgeGreaterThan("35");
					
				}
				
			}
                                   
			if(!StringUtil.isEmpty(request.getParameter("occupation"))){
				if (request.getParameter("occupation").equals("1")) {
					criteria.andOccupationEqualTo("学生");
				}else if (request.getParameter("occupation").equals("2")) {
					criteria.andOccupationEqualTo("职场新人");
				}else if (request.getParameter("occupation").equals("3")) {
					criteria.andOccupationEqualTo("职场精英");
				}else if (request.getParameter("occupation").equals("4")) {
					criteria.andOccupationEqualTo("其他");
				}
			}
		                                    
            if(!StringUtil.isEmpty(request.getParameter("stylename"))) {
				
				criteria.andstylenameEqualTo(request.getParameter("stylename"));	
			}     
            
			totalCount=memberfavoritesService.countByCustomExample(memberFavoritesCustomExample);
			memberFavoritesCustomExample.setLimitStart(page.getLimitStart());
			memberFavoritesCustomExample.setLimitSize(page.getLimitSize());
			dataList=memberfavoritesService.selectByCustomExample(memberFavoritesCustomExample);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;	
	}
	
	
	/**
	 * 导出用户信息统计列表excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/appMng/InformationTatistics/export.shtml")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			MemberFavorItesCustomExample  memberFavoritesCustomExample=new MemberFavorItesCustomExample();
			MemberFavorItesCustomCriteria criteria=memberFavoritesCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			
			 if(request.getParameter("sex").equals("1")){
	            	criteria.andSexEqualTo("1");           	
	            }
	            
	         if(request.getParameter("sex").equals("2")){
	            	criteria.andSexEqualTo("2");           	
	            }
            		
			if(!StringUtil.isEmpty(request.getParameter("age"))) {
				if(request.getParameter("age").equals("between")){
					criteria.andAgeBetween("15", "20");
					
				}
				if(request.getParameter("age").equals("before")){
					criteria.andAgeBetween("20", "35");
					
				}
				
				if(request.getParameter("age").equals("greater")){
					criteria.andAgeGreaterThan("35");
					
				}
				
			}
                                   
			/*if(!StringUtil.isEmpty(request.getParameter("occupation"))) {
				
				criteria.andOccupationEqualTo(request.getParameter("occupation"));	
			}*/
			if(!StringUtil.isEmpty(request.getParameter("occupation"))){
				if (request.getParameter("occupation").equals("1")) {
					criteria.andOccupationEqualTo("学生");
				}else if (request.getParameter("occupation").equals("2")) {
					criteria.andOccupationEqualTo("职场新人");
				}else if (request.getParameter("occupation").equals("3")) {
					criteria.andOccupationEqualTo("职场精英");
				}else if (request.getParameter("occupation").equals("4")) {
					criteria.andOccupationEqualTo("其他");
				}
			}
		                                    
            if(!StringUtil.isEmpty(request.getParameter("stylename"))) {
				
				criteria.andstylenameEqualTo(request.getParameter("stylename"));	
			}    
			
            List<MemberFavorItesCustom>  memberfavoritesServiceCustoms=memberfavoritesService.selectByCustomExample(memberFavoritesCustomExample);
			
			String[] titles = { "序号","手机序列号","用户ID","性别","年龄","职业","风格"};
			ExcelBean excelBean = new ExcelBean("用户信息统计列表.xls",
					"用户信息统计列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MemberFavorItesCustom memberfavoritesServiceCustom:memberfavoritesServiceCustoms){
				String[] data = {
					memberfavoritesServiceCustom.getId()==null?"":memberfavoritesServiceCustom.getId().toString(),
					memberfavoritesServiceCustom.getReqimei()==null?"":memberfavoritesServiceCustom.getReqimei(),
					memberfavoritesServiceCustom.getMemberId()==null?"":memberfavoritesServiceCustom.getMemberId().toString(),
					memberfavoritesServiceCustom.getSex().equals("2")?"女":"男",
					memberfavoritesServiceCustom.getAge()==null?"":memberfavoritesServiceCustom.getAge(),		
					memberfavoritesServiceCustom.getOccupation()==null?"":memberfavoritesServiceCustom.getOccupation(),
					memberfavoritesServiceCustom.getStylename()==null?"":memberfavoritesServiceCustom.getStylename()
					
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
	 * 风格管理列表
	 * 
	 * @return
	 */
	@RequestMapping(value ="/appMng/styleAdministration/list.shtml")
	public ModelAndView styleAdministrationlist(HttpServletRequest request) {	
		String rtPage = "/appMng/styleAdministration/list";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();

		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/appMng/styleAdministration/data.shtml")
	@ResponseBody
	public Map<String, Object> styleAdministrationdata(HttpServletRequest request,Page page) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount =0;
		try {
			StyleExample styleExample=new StyleExample();
			StyleExample.Criteria styleCriteria=styleExample.createCriteria();
			styleCriteria.andDelFlagEqualTo("0");
			styleExample.setOrderByClause("status desc");
			
			totalCount = styleservice.countStylesByExample(styleExample);
			
			styleExample.setLimitStart(page.getLimitStart());
			styleExample.setLimitSize(page.getLimitSize());
			List<Style> favorites=styleservice.selectStyleByExample(styleExample);
		
			resMap.put("Rows", favorites);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 上下架风格
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/styleadministraion/chgStatus.shtml")
	public Map<String, Object> appMngstyleAdministrationStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id=Integer.valueOf(request.getParameter("id"));
			String status=request.getParameter("status");
			
			Style styLe=new Style();
			styLe.setId(id);
			styLe.setUpdateBy(staffId);
			styLe.setUpdateDate(new Date());
			styLe.setStatus(status);
			styleservice.updateByPrimaryKeySelective(styLe);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}

		return resMap;
	}
	
	
	//根据id获取风格数据信息
		@RequestMapping(value = "/appMng/styleAdministration/edit.shtml")
		public ModelAndView edit(HttpServletRequest request) {
			String rtPage = "/appMng/styleAdministration/edit";// 重定向
			Map<String, Object> resMap = new HashMap<String, Object>();
			try {
				if(!StringUtil.isEmpty(request.getParameter("id"))){
					Style stylesegroup=styleservice.selectByPrimaryKey(Integer.valueOf(request.getParameter("id"))); 
					resMap.put("stylesegroup", stylesegroup);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView(rtPage, resMap);
		}
	
		//风格添加数据,更新数据
		@ResponseBody
		@RequestMapping(value = "/styleAdministration/editsales.shtml")
		public ModelAndView editbrandsave(HttpServletRequest request,Style style){
			String rtPage = "/success/success";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = null;
			String msg =null;
			try {
				if(style.getId() == null){
					//添加数据				
					styleservice.insertsale(style);

				}else{
					//更新数据
					styleservice.updatesale(style);

				}
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return new ModelAndView(rtPage, resMap);
		}
	
	
	/**
	 * 删除风格
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/styleAdministration/styledellist.shtml")
	public Map<String, Object> appMngstyleAdministrationDel(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id=Integer.valueOf(request.getParameter("id"));
			
			Style styledel=new Style();
			styledel.setId(id);
			styledel.setUpdateBy(staffId);
			styledel.setUpdateDate(new Date());
			styledel.setDelFlag("1");
			styleservice.updateByPrimaryKeySelective(styledel);
		}catch (Exception e) {
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", e.getMessage());
			e.printStackTrace();
		}

		return resMap;
	}
	
	   //修改风格排序值
		@ResponseBody
		@RequestMapping(value = "/appMng/styleAdministration/submit.shtml")
		public Map<String, Object> updatestyleAdministrationdatalist(HttpServletRequest request, Integer id, Integer seqNo) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = null;
			String msg =null;
			try {
				StyleExample styleExample = new StyleExample();
				StyleExample.Criteria styleExampleCriteria = styleExample.createCriteria();
				styleExampleCriteria.andDelFlagEqualTo("0").andIdEqualTo(id);
				Style style = new Style();
				style.setSeqNo(seqNo);
				styleservice.updateByExampleSelective(style, styleExample);
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
		
		
		/**
		 * 
		 * @Title horologeManager   
		 * @Description TODO(钟表广告管理)   
		 * @author pengl
		 * @date 2018年5月11日 上午11:01:35
		 */
		@RequestMapping("/appMng/adMng/horologePage/horologeManager.shtml")
		public ModelAndView horologeManager(HttpServletRequest request) {	
			ModelAndView m = new ModelAndView("/appMng/adMng/horologePage/horologeList");
			m.addObject("positions", DataDicUtil.getStatusList("BU_AD_INFO", "POSITION"));
			if(!StringUtil.isEmpty(request.getParameter("autoUpDate")) ){
				m.addObject("autoUpDate",request.getParameter("autoUpDate"));
			}
			if(!StringUtil.isEmpty(request.getParameter("autoDownDate")) ){
				m.addObject("autoDownDate",request.getParameter("autoDownDate"));
			}
			return m;
		}
		
		/**
		 * 
		 * @Title horologeList   
		 * @Description TODO(钟表广告管理)   
		 * @author pengl
		 * @date 2018年5月11日 上午11:08:36
		 */
		@ResponseBody
		@RequestMapping("/appMng/adMng/horologePage/horologeList.shtml")
		public Map<String, Object> horologeList(HttpServletRequest request, Page page) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			List<AdInfoCustom> dataList = null;
			Integer totalCount = 0;
			try {
				AdInfoExample adInfoExample=new AdInfoExample();
				AdInfoExample.Criteria adInfoCriteria = adInfoExample.createCriteria();
				adInfoCriteria.andDelFlagEqualTo("0").andTypeEqualTo("1").andCatalogEqualTo("12"); //广告类目  	钟表
				adInfoExample.setOrderByClause("IFNULL(a.seq_no, 999999999),a.auto_up_date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(!StringUtil.isEmpty(request.getParameter("autoUpDate")) ){
					adInfoCriteria.andAutoDownDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("autoUpDate")+" 00:00:00"));
				}
				if(!StringUtil.isEmpty(request.getParameter("autoDownDate")) ){
					adInfoCriteria.andAutoUpDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("autoDownDate")+" 23:59:59"));
				}
				totalCount = adInfoService.countAdInfoCustomByExample(adInfoExample);
				adInfoExample.setLimitStart(page.getLimitStart());
				adInfoExample.setLimitSize(page.getLimitSize());
				dataList = adInfoService.selectAdInfoCustomByExample(adInfoExample);
				resMap.put("Rows", dataList);
				resMap.put("Total", totalCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resMap;
		}
		
		/**
		 * 
		 * @Title addHorologe   
		 * @Description TODO(钟表广告管理-->添加)   
		 * @author pengl
		 * @date 2018年5月11日 上午11:54:54
		 */
		@RequestMapping("/appMng/adMng/horologePage/addHorologe.shtml")
		public ModelAndView addHorologe(HttpServletRequest request) {
			ModelAndView m = new ModelAndView("/appMng/adMng/horologePage/addHorologe");
			m.addObject("linkTypeList", DataDicUtil.getStatusList("BU_AD_INFO", "LINK_TYPE"));
			m.addObject("positionList", DataDicUtil.getStatusList("BU_AD_INFO", "POSITION"));
			m.addObject("catalog", "12"); //钟表类目
			return m;
		}
		
		/**
		 * 
		 * @Title editHorologe   
		 * @Description TODO(钟表广告管理-->修改)   
		 * @author pengl
		 * @date 2018年5月11日 下午1:57:34
		 */
		@RequestMapping("/appMng/adMng/horologePage/editHorologe.shtml")
		public ModelAndView editHorologe(HttpServletRequest request) {
			ModelAndView m = new ModelAndView("/appMng/adMng/horologePage/editHorologe");
			AdInfoCustom adInfoCustom = new AdInfoCustom();
			adInfoCustom = adInfoService.selectAdInfoCustomByPrimaryKey(Integer.valueOf(request.getParameter("id")));
			m.addObject("adInfoCustom", adInfoCustom);
			m.addObject("linkTypeList", DataDicUtil.getStatusList("BU_AD_INFO", "LINK_TYPE"));
			m.addObject("positionList", DataDicUtil.getStatusList("BU_AD_INFO", "POSITION"));
			return m;
		}
		
		/**
		 * 批量添加
		 * 
		 * @param request
		 * @param paramMap
		 * @return
		 */
		@RequestMapping(value = "/appMng/version/batchAdd.shtml")
		public ModelAndView batchAddVersion(HttpServletRequest request) {
			String rtPage = "/appMng/version/batchAdd";
			Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put("isMustList", DataDicUtil.getStatusList("SYS_APP_VERSION", "IS_MUST"));
			resMap.put("chnlType", request.getParameter("chnlType"));
			List<SysStatus> sprChnls = DataDicUtil.getStatusList("BU_MEMBER_INFO", "SPR_CHNL");
			resMap.put("sprChnlsJson", JSONArray.fromObject(sprChnls));
			return new ModelAndView(rtPage, resMap);
		}
		
		/**
		 * 批量添加保存
		 * 
		 * @param request
		 * @param paramMap
		 * @return
		 */
		@RequestMapping(value = "/appMng/version/batchSave.shtml")
		public ModelAndView batchSave(HttpServletRequest request, @RequestParam("file") MultipartFile[] files) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			String rtPage = "/success/success";
			String code = null;
			String msg = "";
			try {
				int staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
				String appVersion = request.getParameter("appVersion");
				String appVersionNo = request.getParameter("appVersionNo");
				String appType = request.getParameter("appType");
				String isEffect = request.getParameter("isEffect");
				String isMust = request.getParameter("isMust");
				String launchContent = request.getParameter("launchContent");
				String sprChnls = request.getParameter("sprChnls");
				String[] sprChnlsArray = sprChnls.split(",");
				if(!StringUtil.isEmpty(request.getParameter("chnlType")) ){
					String chnlType=request.getParameter("chnlType");
					if(!"3".equals(chnlType)){
						List<AppVersion> appVersions = new ArrayList<AppVersion>();
						for(int i=0;i<files.length;i++){
							int fileSize = (int) files[i].getSize();
							if (fileSize != 0) {
								String filePath = FileUtil.saveFile(files[i].getInputStream(), files[i].getOriginalFilename(), 5, 0);
								String url = apkFilePath + filePath;
								AppVersion av = new AppVersion();
								av.setDelFlag("0");
								av.setCreateBy(staffId);
								av.setCreateDate(new Date());
								av.setAppVersion(Integer.parseInt(appVersion));
								av.setAppVersionNo(appVersionNo);
								av.setAppType(appType);
								av.setIsEffect(isEffect);
								av.setIsMust(isMust);
								av.setLaunchContent(launchContent);
								av.setSprChnl(sprChnlsArray[i]);
								av.setPackageUrl(url);
								av.setPackageSize(fileSize);
								appVersions.add(av);
							}
						}
						if(appVersions!=null && appVersions.size()>0){
							appVersionService.batchInsert(appVersions);
						}
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
		
		/**
		 * 批量生效
		 * @param request
		 * @param response
		 * @param paramMap
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/appMng/version/batchEffect.shtml")
		public Map<String, Object> batchEffect(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>(); 
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "成功");
			try {
				Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
				String ids=request.getParameter("ids");
				List<Integer> idsList = new ArrayList<Integer>();
				if(!StringUtil.isEmpty(ids)){
					String[] idsArray = ids.split(",");
					for(String idStr:idsArray){
						if(!StringUtil.isEmpty(idStr) && !idsList.contains(Integer.parseInt(idStr))){
							idsList.add(Integer.parseInt(idStr));
						}
					}
				}
				if(idsList!=null && idsList.size()>0){
					AppVersionExample e = new AppVersionExample();
					AppVersionExample.Criteria c = e.createCriteria();
					c.andDelFlagEqualTo("0");
					c.andIdIn(idsList);
					
					AppVersion appVersion = new AppVersion();
					appVersion.setIsEffect("1");
					appVersion.setUpdateBy(staffId);
					appVersion.setUpdateDate(new Date());
					appVersionService.updateByExampleSelective(appVersion, e);
				}
				
			}catch (Exception e) {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", e.getMessage());
				e.printStackTrace();
			}
			return resMap;
		}
		
		/**
		 * 根据版本获取推广渠道
		 * @param request
		 * @param response
		 * @param paramMap
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/appMng/version/getSprChnlByAppVersion.shtml")
		public Map<String, Object> getSprChnlByAppVersion(HttpServletRequest request) {
			Map<String, Object> resMap = new HashMap<String, Object>(); 
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "成功");
			try {
				String appVersion=request.getParameter("appVersion");
				List<String> sprChnls = appVersionService.getSprChnlsByAppVersion(Integer.parseInt(appVersion));
				resMap.put("sprChnls", sprChnls);
			}catch (Exception e) {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", e.getMessage());
				e.printStackTrace();
			}
			return resMap;
		}
		
		/**
		 * 
		 * @Title editUpdateByManager   
		 * @Description TODO(批量变更领取人)   
		 * @author pengl
		 * @date 2018年9月25日 下午2:27:16
		 */
		@RequestMapping("/appMng/version/editUpdateByManager.shtml")
		public ModelAndView editUpdateByManager(HttpServletRequest request) {
			ModelAndView m = new ModelAndView("/appMng/feedback/editUpdateBy");
			SysOrganizationExample sysOrganizationExample = new SysOrganizationExample();
			SysOrganizationExample.Criteria sysOrganizationCriteria = sysOrganizationExample.createCriteria();
			sysOrganizationCriteria.andOrgNameLike("%客服%");
			List<SysOrganization> sysOrganizationList = sysOrganizationService.selectByExample(sysOrganizationExample);
			List<Integer> orgIdList = new ArrayList<Integer>();
			for(SysOrganization sysOrganization : sysOrganizationList) {
				orgIdList.add(sysOrganization.getOrgId());
			}
			SysStaffInfoExample sysStaffInfoExample = new SysStaffInfoExample();
			SysStaffInfoExample.Criteria sysStaffInfoCriteria = sysStaffInfoExample.createCriteria();
			sysStaffInfoCriteria.andStatusEqualTo("A").andOrgIdIn(orgIdList);
			List<SysStaffInfo> sysStaffInfoList = sysStaffInfoService.selectByExample(sysStaffInfoExample);
			m.addObject("sysStaffInfoList", sysStaffInfoList);
			m.addObject("ids", request.getParameter("ids"));
			return m;
		}
		
		/**
		 * 
		 * @Title editUpdateBy   
		 * @Description TODO(批量变更领取人)   
		 * @author pengl
		 * @date 2018年9月25日 下午2:27:20
		 */
		@RequestMapping("/appMng/version/editUpdateBy.shtml")
		public ModelAndView editUpdateBy(HttpServletRequest request) {
			String rtPage = "/success/success";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = "";
			String msg = "";
			try {
				StaffBean staffBean = this.getSessionStaffBean(request);
				Integer staffId = Integer.valueOf(staffBean.getStaffID());
				Date date = new Date();
				String ids = request.getParameter("ids");
				if(!StringUtil.isEmpty(ids)) {
					List<Integer> idList = new ArrayList<Integer>();
					String[] idStr = ids.split(",");
					for(String id : idStr) {
						idList.add(Integer.parseInt(id));
					}
					MemberFeedbackExample memberFeedbackExample = new MemberFeedbackExample();
					memberFeedbackExample.createCriteria().andIdIn(idList);
					String procesby = request.getParameter("procesby");
					MemberFeedback memberFeedback = new MemberFeedback();
					memberFeedback.setProcesby(Integer.parseInt(procesby));
					memberFeedback.setUpdateBy(staffId);
					memberFeedback.setUpdateDate(date);
					memberFeedbackService.updateByExampleSelective(memberFeedback, memberFeedbackExample);
				}
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			} catch (Exception e) {
				e.printStackTrace();
				code = StateCode.JSON_AJAX_ERROR.getStateCode();
				msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
			}
			resMap.put(this.JSON_RESULT_CODE, code);
			resMap.put(this.JSON_RESULT_MESSAGE, msg);
			return new ModelAndView(rtPage, resMap); 
		}
		
		
		//创建意见反馈工单
		@RequestMapping(value = "/appMng/addfeedbackwork.shtml")
		public ModelAndView addfeedbackwork(HttpServletRequest request) {
			String rtPage = "/appMng/feedback/addfeedbackwork";
			Map<String, Object> resMap = new HashMap<String, Object>();
						
			SysOrganizationExample sysOrganizationExample=new SysOrganizationExample();
			sysOrganizationExample.createCriteria().andStatusEqualTo("A");
			List<SysOrganization> sysOrganizationlist=sysOrganizationService.selectByExample(sysOrganizationExample);			
			resMap.put("sysOrganizationlist",sysOrganizationlist);
			return new ModelAndView(rtPage,resMap);
		}
		
		
		
        //添加意见反馈工单
		@ResponseBody
		@RequestMapping("/appMng/feedback/addfeedbackwork.shtml")
		public ModelAndView addcustomerServiceOrderWorkList(HttpServletRequest request, WoRk work,String attachmentName, String attachmentPath) {
			String rtPage = "/success/success";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String code = "";
			String msg = "";
			try {
				StaffBean staffBean = this.getSessionStaffBean(request);
				Integer staffId = Integer.valueOf(staffBean.getStaffID());
				Date date = new Date();
				
				   work.setStatus("0");
				   work.setStatusBehavior("1");
				   work.setDelFlag("0");	
				   work.setCreateBy(staffId);
				   work.setCreateDate(date);
					if (work.getRelevantType().equals("1")) {
						InterventionOrderCustomExample interventionOrderCustomExample=new InterventionOrderCustomExample();
						interventionOrderCustomExample.createCriteria().andDelFlagEqualTo("0").andInterventionCodeEqualTo(work.getRelevantCode());
						List<InterventionOrderCustom> interventionOrderCustom=interventionOrderService.selectByCustomExample(interventionOrderCustomExample);
						work.setRelevantId(interventionOrderCustom.get(0).getId());

					}
					if (work.getRelevantType().equals("2")) {
						AppealOrderExample appealOrderExample=new AppealOrderExample();
						appealOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderCodeEqualTo(work.getRelevantCode());
						List<AppealOrder> appealOrder=appealOrderService.selectByExample(appealOrderExample);
						work.setRelevantId(appealOrder.get(0).getId());
					}
					if (work.getRelevantType().equals("4")) {
						SubOrderExample subOrderExample=new SubOrderExample();
						subOrderExample.createCriteria().andDelFlagEqualTo("0").andSubOrderCodeEqualTo(work.getRelevantCode());
						List<SubOrder> subOrder=subOrderService.selectByExample(subOrderExample);
						work.setRelevantId(subOrder.get(0).getId());
					}
					if (work.getRelevantType().equals("5")) {
						CustomerServiceOrderExample customerServiceOrderExample=new CustomerServiceOrderExample();
						customerServiceOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderCodeEqualTo(work.getRelevantCode());
						List<CustomerServiceOrder> customerServiceOrder=customerServiceOrderService.selectByExample(customerServiceOrderExample);
						work.setRelevantId(customerServiceOrder.get(0).getId());
					}
				   woRkService.insertSelective(work);
				  	   		   
				   Attachment attachment=new Attachment();
				   attachment.setWorkId(work.getId());
				   attachment.setAttachmentName(attachmentName);
				   attachment.setAttachmentPath(attachmentPath);
				   attachment.setDelFlag("0");
				   attachment.setCreateBy(staffId);
				   attachment.setCreateDate(date);
				   attachmentService.insertSelective(attachment);
				   						   
				   
				   WorkHistory workHistory=new WorkHistory();
				   workHistory.setWorkId(work.getId());
				   workHistory.setOrgId(work.getOrgId());
				   workHistory.setStaffId(work.getStaffId());
				   workHistory.setWorkType(work.getWorkType());
				   workHistory.setStatus(work.getStatus());
				   workHistory.setStatusBehavior(work.getStatusBehavior());
				   workHistory.setUrgentDegree(work.getUrgentDegree());
				   workHistory.setCloseReason(work.getCloseReason());
				   workHistory.setTitleContent(work.getTitleContent());
				   workHistory.setRelevantType(work.getRelevantType());
				   workHistory.setRelevantCode(work.getRelevantCode());
				   workHistory.setRelevantId(work.getRelevantId());
				   workHistory.setDescribeContent(work.getDescribeContent());
				   workHistory.setCreateBy(work.getCreateBy());
				   workHistory.setCreateDate(work.getCreateDate());
				   workHistory.setDelFlag("0");
				   woRkHistoryService.insertSelective(workHistory);
				   
				   SysStaffInfo sysStaffInfo=sysStaffInfoService.selectByPrimaryKey(staffId);
				   SysStaffInfo sysStaffInfos=sysStaffInfoService.selectByPrimaryKey(work.getStaffId());
				   
				   WorkRecord workRecord=new WorkRecord();
				   workRecord.setWorkHistoryId(workHistory.getId());
				   workRecord.setWorkId(work.getId());
				   workRecord.setOrgId(work.getOrgId());
				   workRecord.setStaffId(work.getStaffId());
				   workRecord.setRecordStatus("1");
				   workRecord.setRecordTitle("由"+sysStaffInfo.getStaffName()+"创建工单并指派给"+sysStaffInfos.getStaffName());
				   workRecord.setCreateBy(staffId);
				   workRecord.setCreateDate(date);
				   workRecord.setDelFlag("0");
				   workRecordService.insertSelective(workRecord);
				   
				   
				   AttachmentHistory attachmentHistory=new AttachmentHistory();
				   attachmentHistory.setWorkHistoryId(workHistory.getId());
				   attachmentHistory.setAttachmentName(attachmentName);
				   attachmentHistory.setAttachmentPath(attachmentPath);
				   attachmentHistory.setCreateBy(staffId);
				   attachmentHistory.setCreateDate(date);
				   attachmentHistory.setDelFlag("0");
				   attachmentHistoryService.insertSelective(attachmentHistory);
				   						   					   
				code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
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
		 * 
		 * @MethodName: editSourceNicheDecorateModule
		 * @Description: (编辑资源位图片热区)
		 * @author Pengl
		 * @date 2019年4月18日 上午9:49:48
		 */
		@RequestMapping(value = "/appMng/adMng/editSourceNicheDecorateModule.shtml")
		public ModelAndView editSourceNicheDecorateModule(HttpServletRequest request) {
			String rtPage = "/appMng/adMng/homePage/editSourceNicheDecorateModule";
			Map<String, Object> resMap = new HashMap<String, Object>();
			String decorateInfoId = request.getParameter("decorateInfoId");
			String decorateAreaId = request.getParameter("decorateAreaId");
			String moduleType = request.getParameter("moduleType");
			String seqNo = request.getParameter("seqNo");
			String decorateModuleId = request.getParameter("decorateModuleId");
			String showType = request.getParameter("showType");
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
						}else if(moduleMap.getLinkType().equals("13")){//商家序号
							MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(moduleMap.getLinkValue());
							if(mchtInfo!=null){
								jo.put("linkValue", mchtInfo.getMchtCode());
							}
						}else{//非商品
							/*if(moduleMap.getLinkType().equals("9")){
								jo.put("linkValue", moduleMap.getLinkUrl());
							}else{
								jo.put("linkValue", moduleMap.getLinkValue());
							}*/
							if(moduleMap.getLinkType().equals("9")){
								jo.put("linkValue", moduleMap.getLinkUrl());
							}else if(moduleMap.getLinkType().equals("18") || moduleMap.getLinkType().equals("19")|| moduleMap.getLinkType().equals("20")|| moduleMap.getLinkType().equals("21")|| moduleMap.getLinkType().equals("22") || moduleMap.getLinkType().equals("23")
									||moduleMap.getLinkType().equals("24")|| moduleMap.getLinkType().equals("25")||moduleMap.getLinkType().equals("26")|| moduleMap.getLinkType().equals("27")|| moduleMap.getLinkType().equals("28") || moduleMap.getLinkType().equals("29")
									||moduleMap.getLinkType().equals("30") || moduleMap.getLinkType().equals("31") ){//二级分类和品牌团ID
								jo.put("linkValue", moduleMap.getLinkUrl());
								
								if(moduleMap!=null){
								String[] productTypeIdArray = moduleMap.getLinkUrl().split(",");
								ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(productTypeIdArray[0]));
								if(productType!=null){
									jo.put("firstProductTypeId", productType.getParentId());
								}
								}
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
					
					//上架的品牌团名称
					BrandteamTypeExample brandteamTypeExample = new BrandteamTypeExample();
					brandteamTypeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
					List<BrandteamType> brandteamTypes = brandteamTypeService.selectByExample(brandteamTypeExample );
					resMap.put("brandteamTypes", brandteamTypes);
				}
			}else{
				if(moduleType.equals("1") || moduleType.equals("9")){//1.图片 9.滑动栏目
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
			resMap.put("showType", showType);
			
			//商城一级分类
			MallCategoryExample mallCategoryExample = new MallCategoryExample();
			mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
			List<MallCategory> mallCategorys = mallCategoryService.selectByExample(mallCategoryExample );
			resMap.put("mallCategorys", mallCategorys);
			
			return new ModelAndView(rtPage,resMap);
		}
		
		
		/**
		 * 
		 * @MethodName: editBrandTeamDecorateModule
		 * @Description: (编辑品牌团图片热区)
		 * @author Pengl
		 * @date 2019年4月25日 下午2:43:27
		 */
		@RequestMapping(value = "/appMng/adMng/editBrandTeamDecorateModule.shtml")
		public ModelAndView editBrandTeamDecorateModule(HttpServletRequest request) {
			String rtPage = "/appMng/adMng/homePage/editBrandTeamDecorateModule";
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
							if(moduleMap.getLinkType().equals("9")){
								jo.put("linkValue", moduleMap.getLinkUrl());
							}else if(moduleMap.getLinkType().equals("16") || moduleMap.equals("18")){//二级分类
								jo.put("linkValue", moduleMap.getLinkUrl());
								String[] productTypeIdArray = moduleMap.getLinkUrl().split(",");
								ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(productTypeIdArray[0]));
								if(productType!=null){
									jo.put("firstProductTypeId", productType.getParentId());
								}
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
				if(moduleType.equals("1") || moduleType.equals("9")){//1.图片 9.滑动栏目
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
			resMap.put("showType", 16);
			
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
		
		
}

