package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ShopownerOrderMapper;
import com.jf.entity.ShopownerOrder;
import com.jf.entity.ShopownerOrderExample;

@Service
@Transactional
public class ShopownerOrderService extends BaseService<ShopownerOrder, ShopownerOrderExample> {
	@Autowired
	private ShopownerOrderMapper shopownerOrderMapper;
	@Autowired
	public void setShopownerOrderMapper(ShopownerOrderMapper shopownerOrderMapper) {
		this.setDao(shopownerOrderMapper);
		this.shopownerOrderMapper = shopownerOrderMapper;
	}
	
	
}
