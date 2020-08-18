package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ShopDecorateInfoCustomMapper;
import com.jf.dao.ShopDecorateInfoMapper;
import com.jf.entity.MchtInfo;
import com.jf.entity.Product;
import com.jf.entity.ShopDecorateArea;
import com.jf.entity.ShopDecorateAreaExample;
import com.jf.entity.ShopDecorateInfo;
import com.jf.entity.ShopDecorateInfoCustom;
import com.jf.entity.ShopDecorateInfoExample;
import com.jf.entity.ShopModule;
import com.jf.entity.ShopModuleExample;
import com.jf.entity.ShopModulePicMap;
import com.jf.entity.ShopModulePicMapExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopDecorateInfoService extends BaseService<ShopDecorateInfo, ShopDecorateInfoExample> {
	@Autowired
	private ShopDecorateInfoMapper shopDecorateInfoMapper;
	@Autowired
	private ShopDecorateInfoCustomMapper shopDecorateInfoCustomMapper;
	@Autowired
	private ShopDecorateAreaService shopDecorateAreaService;
	@Autowired
	private ShopModuleService shopModuleService;
	@Autowired
	private ShopModulePicMapService shopModulePicMapService;
	@Autowired
	private ProductService productService;
	@Autowired
	private MchtInfoService mchtInfoService;
	@Autowired
	public void setShopDecorateInfoMapper(ShopDecorateInfoMapper shopDecorateInfoMapper) {
		this.setDao(shopDecorateInfoMapper);
		this.shopDecorateInfoMapper = shopDecorateInfoMapper;
	}
	
	public Map<String, Object> getShopDecorateInfo(Map<String, Object> resMap) {
		List<Map<String,Object>> decorateAreaList = new ArrayList<Map<String,Object>>();
		Integer mchtId = Integer.parseInt(resMap.get("mchtId").toString());
		Map<String, Object> map = new HashMap<String, Object>();
		List<ShopDecorateInfoCustom> infoCustoms = shopDecorateInfoCustomMapper.getShopDecorateInfo(mchtId);
		if(CollectionUtils.isNotEmpty(infoCustoms)){
			//分区
			ShopDecorateInfoCustom infoCustom = infoCustoms.get(0);
			Integer shopDecorateInfoId = infoCustom.getId();
			String banner = StringUtil.getPic(infoCustom.getBanner(), "");
			map.put("decorateInfoName", "");
			map.put("decorateInfoId", shopDecorateInfoId);
			map.put("decorateInfoBanner", banner);
			ShopDecorateAreaExample shopDecorateAreaExample = new ShopDecorateAreaExample();
			shopDecorateAreaExample.createCriteria().andShopDecorateInfoIdEqualTo(shopDecorateInfoId).andDelFlagEqualTo("0");
			shopDecorateAreaExample.setOrderByClause("ifnull(seq_no,99999),id desc");
			List<ShopDecorateArea> decorateAreas = shopDecorateAreaService.selectByExample(shopDecorateAreaExample);
			if(CollectionUtils.isNotEmpty(decorateAreas)){
				for (ShopDecorateArea area : decorateAreas) {
					//模块
					List<Map<String,Object>> decorateModuleList = new ArrayList<Map<String,Object>>();
					Integer shopDecorateAreaId = area.getId();
					String areaName = area.getAreaName();
					Map<String,Object> decorateAreaMap = new HashMap<String,Object>();
					decorateAreaMap.put("decorateAreaName", areaName);
					decorateAreaMap.put("decorateAreaId", shopDecorateAreaId);
					ShopModuleExample shopModuleExample = new ShopModuleExample();
					shopModuleExample.createCriteria().andShopDecorateAreaIdEqualTo(shopDecorateAreaId).andDelFlagEqualTo("0");
					shopModuleExample.setOrderByClause("ifnull(seq_no,99999),id desc");
					List<ShopModule> shopModules = shopModuleService.selectByExample(shopModuleExample);
					if(CollectionUtils.isNotEmpty(shopModules)){
						for (ShopModule shopModule : shopModules) {
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
								ShopModulePicMapExample shopModulePicMapExample = new ShopModulePicMapExample();
								shopModulePicMapExample.createCriteria().andShopModuleIdEqualTo(shopModuleId).andDelFlagEqualTo("0");
								List<ShopModulePicMap> shopModulePicMaps = shopModulePicMapService.selectByExample(shopModulePicMapExample);
								if(CollectionUtils.isNotEmpty(shopModulePicMaps)){
									for (ShopModulePicMap shopModulePicMap : shopModulePicMaps) {
										Map<String,Object> moduleMapMap = new HashMap<String,Object>();
										Integer shopModulePicMapId = shopModulePicMap.getId();
										String coords = shopModulePicMap.getCoords();
										String linkType = shopModulePicMap.getLinkType();
										String linkValue = shopModulePicMap.getLinkValue();
										if(StringUtil.isBlank(linkValue)){
											continue;
										}
										if("1".equals(linkType)){
											//商品
											Product p = productService.findModelByCode(linkValue);
											if(p != null){
												linkValue = p.getId().toString();
											}else{
												continue;
											}
										}else if("3".equals(linkType)){
											//店铺
											MchtInfo m = mchtInfoService.findModelByCode(linkValue);
											if(m != null){
												linkValue = m.getId().toString();
											}else{
												continue;
											}
										}
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
