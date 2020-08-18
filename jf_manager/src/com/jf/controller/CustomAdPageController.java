package com.jf.controller;
import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.CustomAdPageMapper;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ActivityExample;
import com.jf.entity.CustomAdPage;
import com.jf.entity.CustomAdPageCustom;
import com.jf.entity.CustomAdPageCustomExample;
import com.jf.entity.CustomAdPageExample;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateAreaCustom;
import com.jf.entity.DecorateAreaExample;
import com.jf.entity.DecorateModule;
import com.jf.entity.DecorateModuleCustom;
import com.jf.entity.DecorateModuleExample;
import com.jf.entity.ModuleItemCustom;
import com.jf.entity.ModuleItemCustomExample;
import com.jf.entity.ModuleItemExample;
import com.jf.entity.ModuleNavigation;
import com.jf.entity.ModuleNavigationExample;
import com.jf.entity.ModuleNavigationExample.Criteria;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductCustomExample;
import com.jf.entity.SeckillTime;
import com.jf.entity.SeckillTimeExample;
import com.jf.entity.SourceProductType;
import com.jf.entity.SourceProductTypeExample;
import com.jf.service.ActivityService;
import com.jf.service.CustomAdPageService;
import com.jf.service.DecorateAreaService;
import com.jf.service.DecorateInfoService;
import com.jf.service.DecorateModuleService;
import com.jf.service.MchtInfoService;
import com.jf.service.ModuleItemService;
import com.jf.service.ModuleNavigationService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductService;
import com.jf.service.ProductTypeService;
import com.jf.service.SeckillTimeService;
import com.jf.service.SourceProductTypeService;
import com.jf.vo.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
public class CustomAdPageController extends BaseController {
	
	private static final Log logger = LogFactory.getLog(MchtInfoService.class);
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private CustomAdPageMapper customAdPageMapper;
	
	@Resource
	private CustomAdPageService customAdPageService;
	
	@Resource
	private DecorateAreaService decorateAreaService;
	
	@Resource
	private DecorateModuleService decorateModuleService;
	
	@Resource
	private ModuleItemService moduleItemService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private ProductBrandService productBrandService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private SeckillTimeService seckillTimeService;
	
	@Resource
	private SourceProductTypeService sourceProductTypeService;
	
	@Resource
	private DecorateInfoService decorateInfoService;
	
	@Resource
	private ModuleNavigationService moduleNavigationService;

	/**
	 * 固定页面装修首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/customAdPage/index.shtml")
	public ModelAndView appMngAdMngIndex(HttpServletRequest request) {	
		String rtPage = "/marketing/customAdPage/index";// 重定向
		Map<String,Object> resMap = new HashMap<String,Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/customAdPage/keyPageManager.shtml")
	public ModelAndView keyPageManager(HttpServletRequest request, String pageType) {
		ModelAndView m = new ModelAndView();
		CustomAdPageExample cape = new CustomAdPageExample();
		cape.setOrderByClause(" id desc");
		CustomAdPageExample.Criteria capec = cape.createCriteria();
		capec.andDelFlagEqualTo("0");
		capec.andPageTypeEqualTo(pageType);//1.首页主题管 2.日常营销入口3.商品主题馆10.小程序页面--首页栏目装修11.H5页面装修-首页栏目装修20.首页H5装修22.大学生创业24.领劵中心25.爆款推荐
		capec.andIsEffectEqualTo("1");
		capec.andAutoUpDateLessThanOrEqualTo(new Date());
		capec.andAutoDownDateGreaterThan(new Date());
		List<CustomAdPage> customAdPages = customAdPageMapper.selectByExample(cape);
		if(customAdPages!=null && customAdPages.size()>0){
			m.addObject("id", customAdPages.get(0).getId());
		}
		m.addObject("pageType", pageType);
		if (request.getParameter("status").equals("1")) {
			if (pageType.equals("12")) {
				SourceProductTypeExample sourceProductTypeExample=new SourceProductTypeExample();
				sourceProductTypeExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1");
				List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(sourceProductTypeExample);
				m.addObject("sourceProductTypes", sourceProductTypes);		
				m.addObject("showType",18);//showType
			}else if (pageType.equals("13")) {
				SourceProductTypeExample sourceProductTypeExample=new SourceProductTypeExample();
				sourceProductTypeExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("2");
				List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(sourceProductTypeExample);
				m.addObject("sourceProductTypes", sourceProductTypes);
			}else if (pageType.equals("14")) {
				m.addObject("showType",20);//showType
			}else if (pageType.equals("15")) {
				m.addObject("showType",22);//showType
			}else if (pageType.equals("16")) {
				SourceProductTypeExample sourceProductTypeExample=new SourceProductTypeExample();
				sourceProductTypeExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("6");
				List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(sourceProductTypeExample);
				m.addObject("sourceProductTypes", sourceProductTypes);
				m.addObject("showType",24);//showType
			}else if (pageType.equals("17")) {
				m.addObject("showType",26);//showType
			}else if (pageType.equals("18")) {
				m.addObject("showType",28);//showType
			}else if (pageType.equals("19")) {
				m.addObject("showType",30);//showType
			}else if (pageType.equals("25")) {
				SourceProductTypeExample sourceProductTypeExample=new SourceProductTypeExample();
				sourceProductTypeExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("12");
				List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(sourceProductTypeExample);
				m.addObject("sourceProductTypes", sourceProductTypes);
			}
			m.setViewName("/resourceLocationManagement/themePavilionkeyPageList");
		}else {
			
			m.setViewName("/marketing/customAdPage/keyPage/keyPageList");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		m.addObject("autoUpDate", sdf.format(new Date()));
		m.addObject("adInfoStatusList", DataDicUtil.getStatusList("BU_AD_INFO", "STATUS"));
		ResourceBundle resource = ResourceBundle.getBundle("base_config");
		String mUrl = resource.getString("mUrl");
		m.addObject("previewUrl", mUrl+"/xgbuy/views/index.html?redirect_url=activity/templet/brand_decorate.html?infoId=");
		return m;
	}
	
	@RequestMapping(value = "/customAdPage/marketingPageManager.shtml")
	public ModelAndView marketingPageManager(HttpServletRequest request, String pageType) {
		ModelAndView m = new ModelAndView();
		CustomAdPageExample cape = new CustomAdPageExample();
		cape.setOrderByClause("auto_up_date desc,id desc");
		CustomAdPageExample.Criteria capec = cape.createCriteria();
		capec.andDelFlagEqualTo("0");
		capec.andPageTypeEqualTo(pageType);//4 现金签到	5 砍价免费拿 6 购物车 7 消息 8 商品未上架9.邀请享免单21.醒购店长权益
		capec.andIsEffectEqualTo("1");
		capec.andAutoUpDateLessThanOrEqualTo(new Date());
		capec.andAutoDownDateGreaterThan(new Date());
		List<CustomAdPage> customAdPages = customAdPageMapper.selectByExample(cape);
		if(customAdPages!=null && customAdPages.size()>0){
			m.addObject("id", customAdPages.get(0).getId());
		}
		m.addObject("pageType", pageType);
		ResourceBundle resource = ResourceBundle.getBundle("base_config");
		String mUrl = resource.getString("mUrl");
		m.addObject("previewUrl", mUrl+"/xgbuy/views/index.html?redirect_url=activity/shopowner/pages/salesman/index.html?preview=1&infoId=");
		m.setViewName("/marketing/customAdPage/marketingPage/marketingPageList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		m.addObject("autoUpDate", sdf.format(new Date()));
		m.addObject("adInfoStatusList", DataDicUtil.getStatusList("BU_AD_INFO", "STATUS"));
		return m;
	}
	
	/**
	 * 
	 * @Title 获取列表数据   
	 * @Description    
	 * @author yjc
	 * @date 2018年6月14日 
	 */
	@ResponseBody
	@RequestMapping(value = "/customAdPage/keyPage/getDataList.shtml")
	public Map<String, Object> getDataList (HttpServletRequest request, Page page){
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount =0;
		try {
			CustomAdPageCustomExample cape = new CustomAdPageCustomExample();
			cape.setOrderByClause("id desc");
			CustomAdPageCustomExample.Criteria capec = cape.createCriteria();
			capec.andDelFlagEqualTo("0");
			String pageType = request.getParameter("pageType");
			String isEffect = request.getParameter("isEffect");
			String autoUpDateBegin = request.getParameter("autoUpDateBegin");
			String autoUpDateEnd = request.getParameter("autoUpDateEnd");
			String sourceProductTypeId = request.getParameter("sourceProductTypeId");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			capec.andPageTypeEqualTo(pageType);
			if (!StringUtil.isEmpty(sourceProductTypeId)) {
				capec.andSourceProductTypeIdEqualTo(Integer.valueOf(sourceProductTypeId));
			}
			if (!StringUtil.isEmpty(isEffect)) {
				capec.andIsEffectEqualTo(isEffect);
			}
			if (!StringUtil.isEmpty(autoUpDateBegin)) {
				capec.andAutoUpDateGreaterThan(sdf.parse(autoUpDateBegin+" 00:00:00"));
			}
			if (!StringUtil.isEmpty(autoUpDateEnd)) {
				capec.andAutoUpDateLessThan(sdf.parse(autoUpDateEnd+" 23:59:59"));
			}
			totalCount = customAdPageService.countByCustomExample(cape);
			cape.setLimitStart(page.getLimitStart());
			cape.setLimitSize(page.getLimitSize());
			List<CustomAdPageCustom> customAdPages = customAdPageService.selectByCustomExample(cape);
			resMap.put("Rows", customAdPages);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 首页栏目装修复制功能
	 * @Title    
	 * @Description    
	 */
	@ResponseBody
	@RequestMapping(value="/customAdPage/keyPage/copy.shtml")
	public Map<String, Object> copy(HttpServletRequest request){
		String decorateInfoId = request.getParameter("decorateInfoId");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap = customAdPageService.copy(decorateInfoId);
		return msgMap;
	}

	/**
	 * 编辑主题馆
	 * @Title    
	 * @Description    
	 */
	@RequestMapping(value = "/customAdPage/keyPage/edit.shtml")
	public ModelAndView edit(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		String id = request.getParameter("id");
		String pageType = request.getParameter("pageType");
		if(!StringUtil.isEmpty(pageType) ) {
			if (pageType.equals("12") ) {
				SourceProductTypeExample sourceProductTypeExample=new SourceProductTypeExample();
				sourceProductTypeExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("1").andStatusEqualTo("1");
				List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(sourceProductTypeExample);
				m.addObject("sourceProductTypes", sourceProductTypes);
			}else if(pageType.equals("13") ){
				SourceProductTypeExample sourceProductTypeExample=new SourceProductTypeExample();
				sourceProductTypeExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("2").andStatusEqualTo("1");
				List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(sourceProductTypeExample);
				m.addObject("sourceProductTypes", sourceProductTypes);
			}else if(pageType.equals("16") ){
				SourceProductTypeExample sourceProductTypeExample=new SourceProductTypeExample();
				sourceProductTypeExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("6").andStatusEqualTo("1");
				List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(sourceProductTypeExample);
				m.addObject("sourceProductTypes", sourceProductTypes);
			}else if(pageType.equals("25") ){
				SourceProductTypeExample sourceProductTypeExample=new SourceProductTypeExample();
				sourceProductTypeExample.createCriteria().andDelFlagEqualTo("0").andTypeEqualTo("12").andStatusEqualTo("1");
				List<SourceProductType> sourceProductTypes=sourceProductTypeService.selectByExample(sourceProductTypeExample);
				m.addObject("sourceProductTypes", sourceProductTypes);
			}
		}
		if(!StringUtil.isEmpty(id)) {
			CustomAdPage customAdPage = customAdPageMapper.selectByPrimaryKey(Integer.parseInt(id));
			m.addObject("customAdPage", customAdPage);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			m.addObject("autoUpDate", sdf.format(customAdPage.getAutoUpDate()));
			m.addObject("autoDownDate", sdf.format(customAdPage.getAutoDownDate()));
		}
		m.addObject("id", id);
		m.addObject("pageType", pageType);
		m.setViewName("/marketing/customAdPage/keyPage/edit");
		return m;
	}
	
	/**
	 * 保存首页主题馆
	 * @Title    
	 * @Description    
	 */
	@RequestMapping(value = "/customAdPage/keyPage/save.shtml")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request) {
		Map<String, Object> resMap=new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String autoUpDate = request.getParameter("autoUpDate");
			String autoDownDate = request.getParameter("autoDownDate");
			String pageType = request.getParameter("pageType");
			String pageName = request.getParameter("pageName");
			String sourceProductTypeId = request.getParameter("sourceProductTypeId");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CustomAdPage customAdPage = new CustomAdPage();
			if(!StringUtil.isEmpty(id)){
				customAdPage = customAdPageMapper.selectByPrimaryKey(Integer.parseInt(id));
				customAdPage.setUpdateDate(new Date());
				customAdPage.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			}else{
				customAdPage.setDelFlag("0");
				customAdPage.setCreateDate(new Date());
				customAdPage.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				customAdPage.setPageType(pageType);
				customAdPage.setIsEffect("0");//0.下架
			}
			customAdPage.setPageName(pageName);
			customAdPage.setAutoUpDate(sdf.parse(autoUpDate));
			customAdPage.setAutoDownDate(sdf.parse(autoDownDate));
			if(!StringUtil.isEmpty(sourceProductTypeId) ) {
				customAdPage.setSourceProductTypeId(Integer.valueOf(sourceProductTypeId));
			}
			customAdPageService.save(customAdPage);
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
	@RequestMapping(value = "/customAdPage/keyPage/changeIsEffect.shtml")
	@ResponseBody
	public Map<String, Object> changeIsEffect(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			Integer id=Integer.valueOf(request.getParameter("id"));
			String isEffect=request.getParameter("isEffect");
			CustomAdPage customAdPage=new CustomAdPage();
			customAdPage.setId(id);
			customAdPage.setUpdateBy(staffId);
			customAdPage.setUpdateDate(new Date());
			customAdPage.setIsEffect(isEffect);
			customAdPageService.updateByPrimaryKeySelective(customAdPage);
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
	@RequestMapping(value = "/customAdPage/keyPage/previewThemePavilions.shtml")
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
		m.setViewName("/marketing/customAdPage/keyPage/previewThemePavilions");
		return m;
	}
	
	/**
	 * 装修页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/customAdPage/keyPage/design.shtml")
	public ModelAndView design(HttpServletRequest request) {
		ModelAndView m = new ModelAndView();
		String pageType = request.getParameter("pageType");
		if(!StringUtils.isEmpty(pageType)){
			m.addObject("pageType", pageType);
		}
		if (request.getParameter("status").equals("1")) {
			String showType = request.getParameter("showType");
			m.addObject("showType", showType);
			m.setViewName("/resourceLocationManagement/design");
		}else {
			m.setViewName("/marketing/customAdPage/keyPage/design");
			/*String rtPage = "/marketing/customAdPage/keyPage/design";*/
		}
		/*Map<String, Object> resMap = new HashMap<String, Object>();*/
		String decorateInfoId = request.getParameter("decorateInfoId");
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		if(decorateAreas!=null && decorateAreas.size()>0){
			/*resMap.put("decorateAreaId", decorateAreas.get(0).getId());*/
			m.addObject("decorateAreaId", decorateAreas.get(0).getId());
			DecorateModuleExample dme = new DecorateModuleExample();
			dme.setOrderByClause("ifnull(t.seq_no,99999) asc,t.id desc");
			DecorateModuleExample.Criteria dmec = dme.createCriteria();
			dmec.andDelFlagEqualTo("0");
			dmec.andDecorateAreaIdEqualTo(decorateAreas.get(0).getId());
			List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
			for(DecorateModuleCustom decorateModuleCustom:decorateModuleCustoms){
				if(decorateModuleCustom.getModuleType().equals("11")){//醒购秒杀
					SeckillTimeExample ste = new SeckillTimeExample();
					SeckillTimeExample.Criteria stec = ste.createCriteria();
					stec.andDelFlagEqualTo("0");
					stec.andSeckillTypeEqualTo("1");//限时秒杀
					stec.andStatusEqualTo("1");
					ste.setOrderByClause("start_hours,start_min asc");
					List<SeckillTime> seckillTimes = seckillTimeService.selectByExample(ste);
					decorateModuleCustom.setSeckillTimes(seckillTimes);
				}else if(decorateModuleCustom.getModuleType().equals("12")){//五粒导航
					ModuleNavigationExample mne = new ModuleNavigationExample();
					Criteria mnec = mne.createCriteria();
					mnec.andDelFlagEqualTo("0");
					mnec.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
					mne.setOrderByClause("col");
					List<ModuleNavigation> moduleNavigations = moduleNavigationService.selectByExample(mne);
					ArrayList<ModuleNavigation> firstLine=new ArrayList<ModuleNavigation>();
					ArrayList<ModuleNavigation> secondLine=new ArrayList<ModuleNavigation>();
					ArrayList<ModuleNavigation> thirdLine=new ArrayList<ModuleNavigation>();
					for (ModuleNavigation moduleNavigation : moduleNavigations) {
						if(moduleNavigation.getRow()==1){
							firstLine.add(moduleNavigation);
						}else if(moduleNavigation.getRow()==2){
							secondLine.add(moduleNavigation);
						}else if(moduleNavigation.getRow()==3){
							thirdLine.add(moduleNavigation);
						}
					}
					decorateModuleCustom.setModuleNavigationFirstLine(firstLine);
					decorateModuleCustom.setModuleNavigationSecondLine(secondLine);
					decorateModuleCustom.setModuleNavigationThirdLine(thirdLine);
				}
			}
			/*resMap.put("decorateModuleCustoms", decorateModuleCustoms);*/
			m.addObject("decorateModuleCustoms", decorateModuleCustoms);
		}
		ResourceBundle resource = ResourceBundle.getBundle("base_config"); 
		String mUrl = resource.getString("mUrl");
		/*resMap.put("previewUrl", mUrl+"/xgbuy/views/index.html?redirect_url=activity/templet/brand_decorate.html?infoId="+decorateInfoId);*/
		m.addObject("previewUrl", mUrl+"/xgbuy/views/index.html?redirect_url=activity/templet/brand_decorate.html?infoId="+decorateInfoId);
		/*return new ModelAndView(rtPage,resMap);*/
		return m;
	}
	
	/**
	 * 营销页面装修页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/customAdPage/marketingPage/design.shtml")
	public ModelAndView marketingPageDesign(HttpServletRequest request) {
		String rtPage = "/marketing/customAdPage/marketingPage/design";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		String pageType = request.getParameter("pageType");
		resMap.put("pageType", pageType);
		DecorateAreaExample dae = new DecorateAreaExample();
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateArea> decorateAreas = decorateAreaService.selectByExample(dae);
		if(decorateAreas!=null && decorateAreas.size()>0){
			resMap.put("decorateAreaId", decorateAreas.get(0).getId());
			DecorateModuleExample dme = new DecorateModuleExample();
			dme.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
			DecorateModuleExample.Criteria dmec = dme.createCriteria();
			dmec.andDelFlagEqualTo("0");
			dmec.andDecorateAreaIdEqualTo(decorateAreas.get(0).getId());
			List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
			resMap.put("decorateModuleCustoms", decorateModuleCustoms);
			for(DecorateModuleCustom decorateModuleCustom:decorateModuleCustoms){
				if(decorateModuleCustom.getModuleType().equals("2")){//商品
					ModuleItemExample mie = new ModuleItemExample();
					mie.setOrderByClause("IFNULL(seq_no,99999) ASC,id desc");
					ModuleItemExample.Criteria miec = mie.createCriteria();
					miec.andDelFlagEqualTo("0");
					miec.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
					miec.andItemTypeEqualTo("1");//1.商品 2.特卖
					int count = moduleItemService.countByExample(mie);
					decorateModuleCustom.setCount(count);
				}else if(decorateModuleCustom.getModuleType().equals("3")){//特卖
					ModuleItemExample mie = new ModuleItemExample();
					mie.setOrderByClause("IFNULL(seq_no,99999) ASC,id desc");
					ModuleItemExample.Criteria miec = mie.createCriteria();
					miec.andDelFlagEqualTo("0");
					miec.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
					miec.andItemTypeEqualTo("2");//1.商品 2.特卖
					int count = moduleItemService.countByExample(mie);
					decorateModuleCustom.setCount(count);
				}else if(decorateModuleCustom.getModuleType().equals("4") || decorateModuleCustom.getModuleType().equals("5")){//商品列表,特卖列表
					if(!StringUtils.isEmpty(decorateModuleCustom.getProductType1Ids())){
						String firstLevelName = productTypeService.getNamesByIds(decorateModuleCustom.getProductType1Ids());
						decorateModuleCustom.setFirstLevelName(firstLevelName);
					}else{
						decorateModuleCustom.setFirstLevelName("不限");
					}
					if(!StringUtils.isEmpty(decorateModuleCustom.getProductType2Ids())){
						String secondLevelName = productTypeService.getNamesByIds(decorateModuleCustom.getProductType2Ids());
						decorateModuleCustom.setSecondLevelName(secondLevelName);
					}else{
						decorateModuleCustom.setSecondLevelName("不限");
					}
					if(!StringUtils.isEmpty(decorateModuleCustom.getProductBrandIds())){
						String brandNames = productBrandService.getNamesByIds(decorateModuleCustom.getProductBrandIds());
						decorateModuleCustom.setBrandNames(brandNames);
					}else{
						decorateModuleCustom.setBrandNames("不限");
					}
				}
			}
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 自建装修预览页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/customAdPage/marketingPage/preview.shtml")
	public ModelAndView preview(HttpServletRequest request) {
		String rtPage = "/marketing/customAdPage/marketingPage/preview";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String decorateInfoId = request.getParameter("decorateInfoId");
		resMap.put("decorateInfoId", decorateInfoId);
		DecorateAreaExample dae = new DecorateAreaExample();
		dae.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
		DecorateAreaExample.Criteria daec = dae.createCriteria();
		daec.andDelFlagEqualTo("0");
		daec.andDecorateInfoIdEqualTo(Integer.parseInt(decorateInfoId));
		List<DecorateAreaCustom> decorateAreaCustoms = decorateAreaService.selectDecorateAreaCustomByExample(dae);
		for(DecorateAreaCustom decorateAreaCustom:decorateAreaCustoms){
			DecorateModuleExample dme = new DecorateModuleExample();
			dme.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
			DecorateModuleExample.Criteria dmec = dme.createCriteria();
			dmec.andDelFlagEqualTo("0");
			dmec.andDecorateAreaIdEqualTo(decorateAreaCustom.getId());
			List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
			if(decorateModuleCustoms!=null && decorateModuleCustoms.size()>0){
				for(DecorateModuleCustom decorateModuleCustom:decorateModuleCustoms){
					List<String> pics = new ArrayList<String>();
					HashMap<String,Object> paramMap = new HashMap<String,Object>();
					if(decorateModuleCustom.getModuleType().equals("1") || decorateModuleCustom.getModuleType().equals("9") || decorateModuleCustom.getModuleType().equals("8")){//图片
						pics.add(decorateModuleCustom.getPic());
						decorateModuleCustom.setPics(pics);
					}else if(decorateModuleCustom.getModuleType().equals("2")){//商品
						ModuleItemCustomExample mice = new ModuleItemCustomExample();
						mice.setOrderByClause("IFNULL(t.seq_no,99999) ASC,t.id desc");
						ModuleItemCustomExample.ModuleItemCustomCriteria micc = mice.createCriteria();
						micc.andDelFlagEqualTo("0");
						micc.andDecorateModuleIdEqualTo(decorateModuleCustom.getId());
						micc.andItemTypeEqualTo("1");
						micc.andProductActivityStatusEqualTo("4");//活动中
						List<ModuleItemCustom> moduleItemCustoms = moduleItemService.selectModuleItemCustomByExample(mice);
						decorateModuleCustom.setModuleItemCustoms(moduleItemCustoms);
					}else if(decorateModuleCustom.getModuleType().equals("3")){//特卖
						List<Integer> activityIds = moduleItemService.getIdsByModuleId(decorateModuleCustom.getId());
						ActivityExample ae = new ActivityExample();
						ActivityExample.Criteria aec = ae.createCriteria();
						aec.andDelFlagEqualTo("0");
						aec.andIdIn(activityIds);
						aec.andStatusEqualTo("6");
						List<ActivityCustom> activityCustoms = activityService.selectActivityCustomByExample(ae);
						decorateModuleCustom.setActivityCustoms(activityCustoms);
					}else if(decorateModuleCustom.getModuleType().equals("4")){//商品列表
						List<Integer> productType1Ids = new ArrayList<Integer>();
						if(!StringUtils.isEmpty(decorateModuleCustom.getProductType1Ids())){
							String[] productType1IdsArray = decorateModuleCustom.getProductType1Ids().trim().split(",");
							for(int i=0;i<productType1IdsArray.length;i++){
								productType1Ids.add(Integer.parseInt(productType1IdsArray[i]));
							}
						}
						List<Integer> productType2Ids = new ArrayList<Integer>();
						if(!StringUtils.isEmpty(decorateModuleCustom.getProductType2Ids())){
							String[] productType2IdsArray = decorateModuleCustom.getProductType2Ids().trim().split(",");
							for(int i=0;i<productType2IdsArray.length;i++){
								productType2Ids.add(Integer.parseInt(productType2IdsArray[i]));
							}
						}
						if(!StringUtils.isEmpty(decorateModuleCustom.getProductBrandIds())){
							List<Integer> productBrandIds = new ArrayList<Integer>();
							String[] productBrandIdsArray = decorateModuleCustom.getProductBrandIds().trim().split(",");
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
						decorateModuleCustom.setProductCustoms(productCustoms);
					}else if(decorateModuleCustom.getModuleType().equals("5")){//特卖列表
						List<Integer> productType1Ids = new ArrayList<Integer>();
						if(!StringUtils.isEmpty(decorateModuleCustom.getProductType1Ids())){
							String[] productType1IdsArray = decorateModuleCustom.getProductType1Ids().trim().split(",");
							for(int i=0;i<productType1IdsArray.length;i++){
								productType1Ids.add(Integer.parseInt(productType1IdsArray[i]));
							}
						}
						List<Integer> productType2Ids = new ArrayList<Integer>();
						if(!StringUtils.isEmpty(decorateModuleCustom.getProductType2Ids())){
							String[] productType2IdsArray = decorateModuleCustom.getProductType2Ids().trim().split(",");
							for(int i=0;i<productType2IdsArray.length;i++){
								productType2Ids.add(Integer.parseInt(productType2IdsArray[i]));
							}
						}
						ActivityExample ae = new ActivityExample();
						ActivityExample.Criteria aec = ae.createCriteria();
						aec.andDelFlagEqualTo("0");
						if(productType1Ids!=null && productType1Ids.size()>0){
							aec.andProductTypeIdIn(productType1Ids);
						}
						if(productType2Ids!=null && productType2Ids.size()>0){
							aec.andProductTypeSecondIdIn(productType2Ids);
						}
						if(!StringUtils.isEmpty(decorateModuleCustom.getProductBrandIds())){
							List<Integer> productBrandIds = new ArrayList<Integer>();
							String[] productBrandIdsArray = decorateModuleCustom.getProductBrandIds().trim().split(",");
							for(int i=0;i<productBrandIdsArray.length;i++){
								productBrandIds.add(Integer.parseInt(productBrandIdsArray[i]));
							}
							aec.andProductBrandIdIn(productBrandIds);
						}
						aec.andStatusEqualTo("6");
						List<ActivityCustom> activityCustoms = activityService.selectActivityCustomByExample(ae);
						decorateModuleCustom.setActivityCustoms(activityCustoms);
					}
				}
			}
			decorateAreaCustom.setDecorateModuleCustoms(decorateModuleCustoms);
		}
		resMap.put("decorateAreaCustoms", decorateAreaCustoms);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 插入醒购秒杀  
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/customAdPage/keyPage/insertXgSeckillModule.shtml")
	@ResponseBody
	public Map<String, Object> insertXgSeckillModule(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		try{
		String decorateAreaId = request.getParameter("decorateAreaId");
		DecorateModule dm = new DecorateModule();
		dm.setDelFlag("0");
		dm.setCreateDate(new Date());
		dm.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		dm.setModuleType(request.getParameter("moduleType"));
		dm.setDecorateAreaId(Integer.parseInt(decorateAreaId));

		DecorateModuleExample dme = new DecorateModuleExample();
		DecorateModuleExample.Criteria dmec = dme.createCriteria();
		dmec.andDelFlagEqualTo("0");
		dmec.andDecorateAreaIdEqualTo(Integer.parseInt(decorateAreaId));
		List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
		decorateModuleService.createTopModule(dm,decorateModuleCustoms);
		}catch (Exception e) {
			// TODO: handle exception
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败");
		}
		return resMap;
	}
	
	/**
	 * 插入五粒导航  
	 * @Title   
	 * @Description 
	 */
	@RequestMapping(value="/customAdPage/keyPage/insertFiveGranuleNevigateModule.shtml")
	@ResponseBody
	public Map<String, Object> insertFiveGranuleNevigateModule(HttpServletRequest request){
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "保存成功");
		try{
		String decorateAreaId = request.getParameter("decorateAreaId");
		DecorateModule dm = new DecorateModule();
		dm.setDelFlag("0");
		dm.setCreateDate(new Date());
		dm.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
		dm.setModuleType(request.getParameter("moduleType"));
		dm.setDecorateAreaId(Integer.parseInt(decorateAreaId));
		
		DecorateModuleExample dme = new DecorateModuleExample();
		DecorateModuleExample.Criteria dmec = dme.createCriteria();
		dmec.andDelFlagEqualTo("0");
		dmec.andDecorateAreaIdEqualTo(Integer.parseInt(decorateAreaId));
		List<DecorateModuleCustom> decorateModuleCustoms = decorateModuleService.selectDecorateModuleCustomByExample(dme);
		decorateModuleService.createTopModule(dm,decorateModuleCustoms);
		}catch (Exception e) {
			// TODO: handle exception
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败");
		}

		return resMap;
	}


	/**
	 * 删除装修
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/customAdPage/keyPage/delInfo.shtml")
	public Map<String, Object> delInfo(HttpServletRequest request){
		String customAdPageId = request.getParameter("customAdPageId");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("returnCode", "200");
		msgMap.put("returnMsg", "修改成功");
		try {
			CustomAdPageExample customAdPageExample = new CustomAdPageExample();
			customAdPageExample.createCriteria().andDelFlagEqualTo("0").andIsEffectEqualTo("0").andIdEqualTo(Integer.parseInt(customAdPageId));
			CustomAdPage customAdPage = new CustomAdPage();
			customAdPage.setDelFlag("1");
			customAdPageService.updateByExampleSelective(customAdPage,customAdPageExample);
		}catch (Exception e){
			msgMap.put("returnCode", "4004");
			msgMap.put("returnMsg", e.getMessage());
			return msgMap;
		}
		return msgMap;
	}
}
