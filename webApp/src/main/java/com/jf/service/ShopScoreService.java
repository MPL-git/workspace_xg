package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ShopScoreMapper;
import com.jf.entity.ShopScore;
import com.jf.entity.ShopScoreExample;

@Service
@Transactional
public class ShopScoreService extends BaseService<ShopScore,ShopScoreExample> {
	@Autowired
	private ShopScoreMapper shopScoreMapper;
	
	@Autowired
	public void setShopScoreMapper(ShopScoreMapper shopScoreMapper) {
		super.setDao(shopScoreMapper);
		this.shopScoreMapper = shopScoreMapper;
	}
}
