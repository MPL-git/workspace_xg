package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ShopStoryDetailMapper;
import com.jf.entity.ShopStoryDetail;
import com.jf.entity.ShopStoryDetailExample;

@Service
@Transactional
public class ShopStoryDetailService extends BaseService<ShopStoryDetail, ShopStoryDetailExample> {
	
	@Autowired
	private ShopStoryDetailMapper shopStoryDetailMapper;
	
	
	@Autowired
	public void setShopStoryDetailMapper(ShopStoryDetailMapper shopStoryDetailMapper) {
		super.setDao(shopStoryDetailMapper);
		this.shopStoryDetailMapper = shopStoryDetailMapper;
	}
	

}
