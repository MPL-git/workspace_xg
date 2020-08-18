package com.jf.service;

import com.google.common.collect.Lists;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.entity.AdInfo;
import com.jf.entity.AdInfoExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
//	@Resource
//	private MallCategoryMapService mallCategoryMapService;
	@Autowired
	private SourceProductTypeService sourceProductTypeService;
	
	public Map<String, Object> getHomeCategory(String clientType) {
		Map<String,Object> map = new HashMap<String,Object>();
//		List<Map<String, Object>> categoryList = getCategoryListByCfg();
		List<Map<String, Object>> categoryList = sourceProductTypeService.getBrandGroupCategoryTop("1");

		Map<String,Object> adPageMap1=null;
		Map<String,Object> adPageMap2=null;
		if(Const.WX_XCX.equals(clientType)){//小程序
			adPageMap2= customAdPageService.getCustomAdPage(Const.PAGE_TYPE_10,null);
			
		}else{
			if(Const.IOS.equals(clientType) || Const.ANDROID.equals(clientType)){//客户端为APP时（1:ios,2:安卓）
				adPageMap2= customAdPageService.getCustomAdPage(Const.PAGE_TYPE_20,null);//首页H5装修
			}else{
				adPageMap2= customAdPageService.getCustomAdPage(Const.PAGE_TYPE_11,null);
			}
		}
		adPageMap1= customAdPageService.getCustomAdPage(Const.PAGE_TYPE_1,null);
		map.put("themePavilions", adPageMap1.get("decorateInfoId"));
		map.put("marketing", adPageMap2.get("decorateInfoId"));
		map.put("categoryList", categoryList);//4
		map.put("planType", "2");
		return map;
	}

	/**
	 * 根据系统参数配置获取首页顶部栏目：APP_HOME_TOP_CATALOG
	 * jumpType: 1 首页 2 特卖一级 3 商城一级 4 预告
	 */
	private List<Map<String, Object>> getCategoryListByCfg() {
		List<Map<String, Object>> categoryList = Lists.newArrayList();
		Map<String,Object> dataMap = new HashMap<String,Object>();
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
		List<String> specialHideCategoryList = sysParamCfgService.getSpecialHideCategoryList();
		boolean needHide = CollectionUtils.isNotEmpty(specialHideCategoryList);
		if(CollectionUtils.isNotEmpty(sysParamCfgs)){
			//存放特卖一级类目的跳转id（这些一级类目是要跳转到商城一级类目的，所以下面还要吧）
			for (SysParamCfg sysParamCfg : sysParamCfgs) {
				if (needHide && specialHideCategoryList.contains(sysParamCfg.getParamName())) {
					continue; //小程序审核时要求隐藏
				}
				dataMap = new HashMap<String,Object>();
				dataMap.put("id", sysParamCfg.getParamValue());
				dataMap.put("name", sysParamCfg.getParamName());
				dataMap.put("pic", StringUtil.getPic(sysParamCfg.getParamDesc(), ""));
				dataMap.put("adCatalog", sysParamCfg.getParamOrder());//paramOrder 该字段用来排序 现用来储存 广告推荐
				dataMap.put("jumpType", sysParamCfg.getParamDesc());
				categoryList.add(dataMap);
			}
		}
		dataMap = new HashMap<String,Object>();
		dataMap.put("id", "");
		dataMap.put("name", "预告");
		dataMap.put("pic", "");
		dataMap.put("adCatalog", "");
		dataMap.put("jumpType", "4");
		categoryList.add(dataMap);
		return categoryList;
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

	public List<Map<String, Object>> getHomeThirdPlate() {
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		AdInfoExample adInfoExample = new AdInfoExample();
		adInfoExample.createCriteria().andPositionEqualTo("4").andStatusEqualTo(Const.AD_STATUS_UP)
		.andTypeEqualTo(Const.AD_TYPE_1).andCatalogEqualTo(Const.AD_CATALOG_HOME).andDelFlagEqualTo("0");
		adInfoExample.setLimitStart(0);
		adInfoExample.setLimitSize(1);
		adInfoExample.setOrderByClause("auto_up_date desc");
		List<AdInfo> adInfoList = adInfoService.selectByExample(adInfoExample);
		if(CollectionUtils.isNotEmpty(adInfoList)){
			AdInfo adInfo = adInfoList.get(0);
			map.put("pic", StringUtil.getPic(adInfo.getPic(), ""));
			map.put("type", "1");
			dataList.add(map);
		}
		SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
		sysParamCfgExample.createCriteria().andParamCodeEqualTo("APP_HOME_CATALOG");
		sysParamCfgExample.setOrderByClause("param_id");
		List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(sysParamCfgExample);
		if(CollectionUtils.isNotEmpty(sysParamCfgs)){
			for (SysParamCfg sysParamCfg : sysParamCfgs) {
				map = new HashMap<String,Object>();
				map.put("pic", StringUtil.getPic(sysParamCfg.getParamValue(), ""));
				map.put("type", sysParamCfg.getParamDesc());
				dataList.add(map);
			}
		}
		return dataList;
	}
}
