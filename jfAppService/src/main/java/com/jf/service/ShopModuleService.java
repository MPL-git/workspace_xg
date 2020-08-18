package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ShopModuleMapper;
import com.jf.entity.ShopModule;
import com.jf.entity.ShopModuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopModuleService extends BaseService<ShopModule, ShopModuleExample> {
	@Autowired
	private ShopModuleMapper shopModuleMapper;
	@Autowired
	public void setShopModuleMapper(ShopModuleMapper shopModuleMapper) {
		this.setDao(shopModuleMapper);
		this.shopModuleMapper = shopModuleMapper;
	}
	
	
}
