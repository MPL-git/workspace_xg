package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ShopDecorateAreaMapper;
import com.jf.entity.ShopDecorateArea;
import com.jf.entity.ShopDecorateAreaExample;

@Service
@Transactional
public class ShopDecorateAreaService extends BaseService<ShopDecorateArea, ShopDecorateAreaExample> {
	@Autowired
	private ShopDecorateAreaMapper shopDecorateAreaMapper;
	@Autowired
	public void setShopDecorateAreaMapper(ShopDecorateAreaMapper shopDecorateAreaMapper) {
		this.setDao(shopDecorateAreaMapper);
		this.shopDecorateAreaMapper = shopDecorateAreaMapper;
	}
	
	
}
