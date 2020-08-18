package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ShopModulePicMapMapper;
import com.jf.entity.ShopModulePicMap;
import com.jf.entity.ShopModulePicMapExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopModulePicMapService extends BaseService<ShopModulePicMap, ShopModulePicMapExample> {
	@Autowired
	private ShopModulePicMapMapper shopModulePicMapMapper;
	@Autowired
	public void setShopModulePicMapMapper(ShopModulePicMapMapper shopModulePicMapMapper) {
		this.setDao(shopModulePicMapMapper);
		this.shopModulePicMapMapper = shopModulePicMapMapper;
	}
	
	
}
