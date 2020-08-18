package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ShopScoreCustomMapper;
import com.jf.dao.ShopScoreMapper;
import com.jf.entity.ShopScore;
import com.jf.entity.ShopScoreExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ShopScoreService extends BaseService<ShopScore,ShopScoreExample> {
	@Autowired
	private ShopScoreMapper dao;
	
	@Autowired
	private ShopScoreCustomMapper shopScoreCustomMapper;
	
	@Autowired
	public void setShopScoreMapper(ShopScoreMapper shopScoreMapper) {
		super.setDao(shopScoreMapper);
		this.dao = shopScoreMapper;
	}
	
	public List<HashMap<String,Object>> getTotalShopScoreByMchtId(Integer mchtId){
		return shopScoreCustomMapper.getTotalShopScoreByMchtId(mchtId);
	}

	public List<HashMap<String, Object>> countShopScoreByMhctId(Integer mchtId) {
		return shopScoreCustomMapper.countShopScoreByMhctId(mchtId);
	}
}
