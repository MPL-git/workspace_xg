package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年10月20日 上午10:13:53 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class HomeService{
	@Resource
	private SysParamCfgService sysParamCfgService;
	@Resource
	private AdInfoService adInfoService;
	@Resource
	private CustomAdPageService customAdPageService;
	@Resource 
	private MallCategoryMapService mallCategoryMapService;
	@Resource
	private IndexTopStyleService indexTopStyleService;
	@Resource
	private IndexTopTabService indexTopTabService;
	@Resource
	private MemberSignInSettingService memberSignInSettingService;
	@Resource
	private SourceProductTypeService sourceProductTypeService;
	@Resource
	private DecorateInfoService decorateInfoService;
	
	public Map<String, Object> getHomeCategory(HttpServletRequest request) {
		JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
		JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
		Integer version = reqPRM.getInt("version");
		String system = reqPRM.getString("system");
		Integer memberId = null;
		if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
			memberId = reqDataJson.getInt("memberId");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		//放置首页4张品类图
		List<Map<String,Object>> categoryList = new ArrayList<Map<String,Object>>();
		if((version <=46 && system.equals(Const.ANDROID)) || (version <= 49 && system.equals(Const.IOS))){
			//最新版本
			Map<String,Object> dataMap = new HashMap<String,Object>();
			String jumpType = "1";//1首页 2特卖一级 3商城一级4预告
			String planType = "1";
			dataMap.put("id", "");
			dataMap.put("name", "推荐");
			dataMap.put("pic", "");
			dataMap.put("adCatalog", "");
			dataMap.put("jumpType", "1");
			categoryList.add(dataMap);
			SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
			sysParamCfgExample.createCriteria().andParamCodeEqualTo("APP_HOME_TOP_CATALOG");
			sysParamCfgExample.setOrderByClause("param_id");
			List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
			if(CollectionUtils.isNotEmpty(sysParamCfgs)){
				//存放特卖一级类目的跳转id（这些一级类目是要跳转到商城一级类目的，所以下面还要吧）
				for (SysParamCfg sysParamCfg : sysParamCfgs) {
					dataMap = new HashMap<String,Object>();
					dataMap.put("id", sysParamCfg.getParamValue());
					dataMap.put("name", sysParamCfg.getParamName());
					dataMap.put("pic", StringUtil.getPic(sysParamCfg.getParamDesc(), ""));
					dataMap.put("adCatalog", sysParamCfg.getParamOrder());//paramOrder 该字段用来排序 现用来储存 广告推荐
					dataMap.put("jumpType", sysParamCfg.getParamDesc());
					categoryList.add(dataMap);
				}
			}
			//导航方案
			SysParamCfgExample navigationPlacEx = new SysParamCfgExample();
			navigationPlacEx.createCriteria().andParamCodeEqualTo("APP_HOME_NAVIGATION_PLAN");
			List<SysParamCfg> navigationPlacExs = sysParamCfgService.selectByExample(navigationPlacEx);
			if(CollectionUtils.isNotEmpty(navigationPlacExs)){
				planType = navigationPlacExs.get(0).getParamValue();
			}
			dataMap = new HashMap<String,Object>();
			dataMap.put("id", "");
			dataMap.put("name", "预告");
			dataMap.put("pic", "");
			dataMap.put("adCatalog", "");
			dataMap.put("jumpType", "4");
			categoryList.add(dataMap);
			Map<String,Object> adPageMap1 = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_1,null);
			Map<String,Object> adPageMap2 = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_2,null);
			String themePavilions = adPageMap1.get("customAdPageUrl").toString();
			String marketing = adPageMap2.get("customAdPageUrl").toString();
			map.put("themePavilions",themePavilions);
			map.put("marketing", marketing);
			map.put("categoryList", categoryList);//4
			map.put("planType", planType);
		}else if((version <=52 && system.equals(Const.ANDROID)) || (version <= 55 && system.equals(Const.IOS))){
			//导航方案
			String planType = "1";
			SysParamCfgExample navigationPlacEx = new SysParamCfgExample();
			navigationPlacEx.createCriteria().andParamCodeEqualTo("APP_HOME_NAVIGATION_PLAN");
			List<SysParamCfg> navigationPlacExs = sysParamCfgService.selectByExample(navigationPlacEx);
			if(CollectionUtils.isNotEmpty(navigationPlacExs)){
				planType = navigationPlacExs.get(0).getParamValue();
			}
			Map<String, Object> styleMap = indexTopStyleService.getTopStyles();
			List<Map<String, Object>> topTabList = indexTopTabService.getTopTabs();
			Map<String,Object> adPageMap1 = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_1,null);
			Map<String,Object> adPageMap2 = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_2,32);
			String themePavilions = adPageMap1.get("customAdPageUrl").toString();
			String marketing = adPageMap2.get("customAdPageUrl").toString();
			//签到
			String signInUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/newsign/index.html";
			boolean isRedDot = true;
			if(memberId != null){
				Integer memberSignInCount = memberSignInSettingService.getMemberSignInCount(memberId);
				if(memberSignInCount > 0){
					isRedDot = false;
				}
			}
			map.put("themePavilions",themePavilions);
			map.put("marketing", marketing);
			map.put("categoryList", topTabList);
			map.put("planType", planType);
			map.put("bgPic", styleMap.get("bgPic").toString());
			map.put("bgOtherPic", styleMap.get("bgOtherPic").toString());
			map.put("signInUrl", signInUrl);
			map.put("isRedDot", isRedDot);
			map.put("currentDate", new Date());
		}else if((version <=57 && system.equals(Const.ANDROID)) || (version <= 58 && system.equals(Const.IOS))){
			//导航方案
			String planType = "1";
			SysParamCfgExample navigationPlacEx = new SysParamCfgExample();
			navigationPlacEx.createCriteria().andParamCodeEqualTo("APP_HOME_NAVIGATION_PLAN");
			List<SysParamCfg> navigationPlacExs = sysParamCfgService.selectByExample(navigationPlacEx);
			if(CollectionUtils.isNotEmpty(navigationPlacExs)){
				planType = navigationPlacExs.get(0).getParamValue();
			}
			Map<String, Object> styleMap = indexTopStyleService.getTopStyles();
			List<Map<String, Object>> topTabList = sourceProductTypeService.getBrandGroupCategoryTop("1");
			Map<String,Object> adPageMap1 = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_1,null);
			Map<String,Object> adPageMap2 = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_2,null);//2.首页栏目装修
			String themePavilions = adPageMap1.get("customAdPageUrl").toString();
			String marketing = adPageMap2.get("customAdPageUrl").toString();
			//签到
			String signInUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/newsign/index.html";
			boolean isRedDot = true;
			if(memberId != null){
				Integer memberSignInCount = memberSignInSettingService.getMemberSignInCount(memberId);
				if(memberSignInCount > 0){
					isRedDot = false;
				}
			}
			map.put("themePavilions",themePavilions);
			map.put("marketing", marketing);
			map.put("categoryList", topTabList);
			map.put("planType", planType);
			map.put("bgPic", styleMap.get("bgPic").toString());
			map.put("bgOtherPic", styleMap.get("bgOtherPic").toString());
			map.put("signInUrl", signInUrl);
			map.put("isRedDot", isRedDot);
			map.put("currentDate", new Date());
		}else{
			//2019年7月4日13:47:33 STORY #1535 五粒导航，首页装修模块改为原生
			String planType = "1";//导航方案
			SysParamCfgExample navigationPlacEx = new SysParamCfgExample();
			navigationPlacEx.createCriteria().andParamCodeEqualTo("APP_HOME_NAVIGATION_PLAN");
			List<SysParamCfg> navigationPlacExs = sysParamCfgService.selectByExample(navigationPlacEx);
			if(CollectionUtils.isNotEmpty(navigationPlacExs)){
				planType = navigationPlacExs.get(0).getParamValue();
			}
			Map<String, Object> styleMap = indexTopStyleService.getTopStyles();
			List<Map<String, Object>> topTabList = sourceProductTypeService.getBrandGroupCategoryTop("1");
			Map<String,Object> adPageMap1 = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_1,null);
			Map<String,Object> adPageMap20 = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_20,null);//20.首页H5装修
			String themePavilions = adPageMap1.get("customAdPageUrl").toString();
			String marketing = adPageMap20.get("customAdPageUrl").toString();
			//签到
			String signInUrl = Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=activity/newsign/index.html";
			boolean isRedDot = true;
			if(memberId != null){
				Integer memberSignInCount = memberSignInSettingService.getMemberSignInCount(memberId);
				if(memberSignInCount > 0){
					isRedDot = false;
				}
			}
			map.put("themePavilions",themePavilions);
			map.put("marketing", marketing);
			map.put("categoryList", topTabList);
			map.put("planType", planType);
			map.put("bgPic", styleMap.get("bgPic").toString());
			map.put("bgOtherPic", styleMap.get("bgOtherPic").toString());
			map.put("fontType", styleMap.get("fontType").toString());
			map.put("signInUrl", signInUrl);
			map.put("isRedDot", isRedDot);
			map.put("currentDate", new Date());
			
			Map<String,Object> adPageMap2 = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_2,null);//2.首页栏目装修,五粒导航,图片模块，醒购秒杀模块，app原生处理
			String decorateInfoId = adPageMap2.get("decorateInfoId").toString();
			Map<String,Object> decorateInfoMap = decorateInfoService.getHomePageDecorateInfo(StringUtil.isBlank(decorateInfoId) ? null : Integer.valueOf(decorateInfoId),reqPRM,memberId);
			map.put("decorateInfo", decorateInfoMap);
		}
		return map;
	}

	public List<Map<String,Object>> getFourCategories() {
		List<Map<String,Object>> categoryList = new ArrayList<Map<String,Object>>();
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		sysParamCfgExample.createCriteria().andParamCodeEqualTo("APP_HOME_CATALOG");
		sysParamCfgExample.setOrderByClause("param_id");
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		if(CollectionUtils.isNotEmpty(sysParamCfgs)){
			for (SysParamCfg sysParamCfg : sysParamCfgs) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				dataMap.put("id", sysParamCfg.getParamValue());
				dataMap.put("name", sysParamCfg.getParamName());
				dataMap.put("pic", StringUtil.getPic(sysParamCfg.getParamDesc(), ""));
				//paramOrder 该字段用来排序 现用来储存 广告推荐
				dataMap.put("adCatalog", sysParamCfg.getParamOrder());
				categoryList.add(dataMap);
			}
		}
		return categoryList;
	}
}
