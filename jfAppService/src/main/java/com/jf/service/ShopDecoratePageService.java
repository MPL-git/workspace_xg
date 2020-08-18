package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ShopDecoratePageMapper;
import com.jf.entity.ShopDecoratePage;
import com.jf.entity.ShopDecoratePageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopDecoratePageService extends BaseService<ShopDecoratePage, ShopDecoratePageExample> {
	@Autowired
	private ShopDecoratePageMapper shopDecoratePageMapper;
	@Autowired
	public void setShopDecoratePageMapper(ShopDecoratePageMapper shopDecoratePageMapper) {
		this.setDao(shopDecoratePageMapper);
		this.shopDecoratePageMapper = shopDecoratePageMapper;
	}
	
	
}
