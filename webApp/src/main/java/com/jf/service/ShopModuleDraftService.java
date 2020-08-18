package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ShopModuleDraftMapper;
import com.jf.entity.ShopModuleDraft;
import com.jf.entity.ShopModuleDraftExample;

@Service
@Transactional
public class ShopModuleDraftService extends BaseService<ShopModuleDraft, ShopModuleDraftExample> {
	@Autowired
	private ShopModuleDraftMapper shopModuleDraftMapper;
	@Autowired
	public void setShopModuleDraftMapper(ShopModuleDraftMapper shopModuleDraftMapper) {
		this.setDao(shopModuleDraftMapper);
		this.shopModuleDraftMapper = shopModuleDraftMapper;
	}
	
	
}
