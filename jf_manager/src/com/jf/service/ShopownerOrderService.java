package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ShopownerOrderCustomMapper;
import com.jf.dao.ShopownerOrderMapper;
import com.jf.entity.ShopownerOrder;
import com.jf.entity.ShopownerOrderCustom;
import com.jf.entity.ShopownerOrderCustomExample;
import com.jf.entity.ShopownerOrderExample;

@Service
@Transactional
public class ShopownerOrderService extends BaseService<ShopownerOrder,ShopownerOrderExample> {
	@Autowired
	private ShopownerOrderMapper shopownerOrderMapper;
	
	@Autowired
	private ShopownerOrderCustomMapper shopownerOrderCustomMapper;

	@Autowired
	public void setActivityAreaDecorateMapper(ShopownerOrderMapper shopownerOrderMapper) {
		super.setDao(shopownerOrderMapper);
		this.shopownerOrderMapper = shopownerOrderMapper;
	}
	
	public int countByCustomExample(ShopownerOrderCustomExample example){
		return shopownerOrderCustomMapper.countByCustomExample(example);
	};

    public List<ShopownerOrderCustom> selectByCustomExample(ShopownerOrderCustomExample example){
    	return shopownerOrderCustomMapper.selectByCustomExample(example);
    };
	
	public List<Map<String, Object>> statisticsShopownerOrder(Map<String, Object> paramMap) {
		return shopownerOrderCustomMapper.statisticsShopownerOrder(paramMap);
	}
}
