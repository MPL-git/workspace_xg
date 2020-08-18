package com.jf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.StringUtil;
import com.jf.dao.ShopDecorateInfoDraftCustomMapper;
import com.jf.dao.ShopDecorateInfoDraftMapper;
import com.jf.entity.ShopDecorateAreaDraft;
import com.jf.entity.ShopDecorateAreaDraftExample;
import com.jf.entity.ShopDecorateInfoDraft;
import com.jf.entity.ShopDecorateInfoDraftCustom;
import com.jf.entity.ShopDecorateInfoDraftExample;
import com.jf.entity.ShopModuleDraft;
import com.jf.entity.ShopModuleDraftExample;
import com.jf.entity.ShopModulePicMapDraft;
import com.jf.entity.ShopModulePicMapDraftExample;

@Service
@Transactional
public class ShopDecorateInfoDraftService extends BaseService<ShopDecorateInfoDraft, ShopDecorateInfoDraftExample> {
	@Autowired
	private ShopDecorateInfoDraftMapper shopDecorateInfoDraftMapper;
	@Autowired
	private ShopDecorateInfoDraftCustomMapper shopDecorateInfoDraftCustomMapper;
	@Autowired
	private ShopDecorateAreaDraftService shopDecorateAreaDraftService;
	@Autowired
	private ShopModuleDraftService shopModuleDraftService;
	@Autowired
	private ShopModulePicMapDraftService shopModulePicMapDraftService;
	@Autowired
	public void setShopDecorateInfoDraftMapper(ShopDecorateInfoDraftMapper shopDecorateInfoDraftMapper) {
		this.setDao(shopDecorateInfoDraftMapper);
		this.shopDecorateInfoDraftMapper = shopDecorateInfoDraftMapper;
	}
	/**商家端装修预览*/
	public Map<String, Object> getShopDecorateInfoDraft(Map<String, Object> resMap) {
		List<Map<String,Object>> decorateAreaList = new ArrayList<Map<String,Object>>();
		Integer mchtId = Integer.parseInt(resMap.get("mchtId").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		List<ShopDecorateInfoDraftCustom> infoCustoms = shopDecorateInfoDraftCustomMapper.getShopDecorateInfo(mchtId);
		if(CollectionUtils.isNotEmpty(infoCustoms)){
			//分区
			ShopDecorateInfoDraftCustom infoCustom = infoCustoms.get(0);
			Integer shopDecorateInfoId = infoCustom.getId();
			String banner = StringUtil.getPic(infoCustom.getBanner(), "");
			String shopLogo = StringUtil.getPic(infoCustom.getShopLogo(), "");
			map.put("decorateInfoName", "");
			map.put("decorateInfoId", shopDecorateInfoId);
			map.put("decorateInfoBanner", banner);
			map.put("shopLogo", shopLogo);
			ShopDecorateAreaDraftExample shopDecorateAreaDraftExample = new ShopDecorateAreaDraftExample();
			shopDecorateAreaDraftExample.createCriteria().andShopDecorateInfoIdEqualTo(shopDecorateInfoId).andDelFlagEqualTo("0");
			shopDecorateAreaDraftExample.setOrderByClause("ifnull(seq_no,99999),id desc");
			List<ShopDecorateAreaDraft> decorateAreaDrafts = shopDecorateAreaDraftService.selectByExample(shopDecorateAreaDraftExample);
			if(CollectionUtils.isNotEmpty(decorateAreaDrafts)){
				for (ShopDecorateAreaDraft area : decorateAreaDrafts) {
					//模块
					List<Map<String,Object>> decorateModuleList = new ArrayList<Map<String,Object>>();
					Integer shopDecorateAreaId = area.getId();
					String areaName = area.getAreaName();
					Map<String,Object> decorateAreaMap = new HashMap<String,Object>();
					decorateAreaMap.put("decorateAreaName", areaName);
					decorateAreaMap.put("decorateAreaId", shopDecorateAreaId);
					ShopModuleDraftExample shopModuleDraftExample = new ShopModuleDraftExample();
					shopModuleDraftExample.createCriteria().andShopDecorateAreaIdEqualTo(shopDecorateAreaId).andDelFlagEqualTo("0");
					shopModuleDraftExample.setOrderByClause("ifnull(seq_no,99999),id desc");
					List<ShopModuleDraft> shopModuleDrafts = shopModuleDraftService.selectByExample(shopModuleDraftExample);
					if(CollectionUtils.isNotEmpty(shopModuleDrafts)){
						for (ShopModuleDraft shopModule : shopModuleDrafts) {
							Integer shopModuleId = shopModule.getId();
							String shopModulePic = StringUtil.getPic(shopModule.getPic(), "");
							String shopModuleType = shopModule.getModuleType();
							List<Map<String,Object>> moduleMapList = new ArrayList<Map<String,Object>>();
							Map<String,Object> decorateModuleMap = new HashMap<String,Object>();
							decorateModuleMap.put("decorateModuleId", shopModuleId);
							decorateModuleMap.put("decorateModulePic", shopModulePic);
							decorateModuleMap.put("moduleType", shopModuleType);//1图片模块
							if("1".equals(shopModuleType)){
								//1图片模块 
								ShopModulePicMapDraftExample shopModulePicMapDraftExample = new ShopModulePicMapDraftExample();
								shopModulePicMapDraftExample.createCriteria().andShopModuleIdEqualTo(shopModuleId).andDelFlagEqualTo("0");
								List<ShopModulePicMapDraft> shopModulePicMapDrafts = shopModulePicMapDraftService.selectByExample(shopModulePicMapDraftExample);
								if(CollectionUtils.isNotEmpty(shopModulePicMapDrafts)){
									for (ShopModulePicMapDraft shopModulePicMap : shopModulePicMapDrafts) {
										Map<String,Object> moduleMapMap = new HashMap<String,Object>();
										Integer shopModulePicMapId = shopModulePicMap.getId();
										String coords = shopModulePicMap.getCoords();
										String linkType = shopModulePicMap.getLinkType();
										String linkValue = shopModulePicMap.getLinkValue();
										moduleMapMap.put("moduleMapId", shopModulePicMapId);
										moduleMapMap.put("corrds", coords);
										moduleMapMap.put("linkType", linkType);
										moduleMapMap.put("linkValue", linkValue);
										moduleMapList.add(moduleMapMap);
									}
								}
							}
							decorateModuleMap.put("moduleMapList", moduleMapList);//图片模块
							decorateModuleList.add(decorateModuleMap);
						}
					}
					decorateAreaMap.put("decorateModuleList", decorateModuleList);
					decorateAreaList.add(decorateAreaMap);
				}
			}
		}
		map.put("decorateAreaList", decorateAreaList);
		return map;
	}
	
	
}
