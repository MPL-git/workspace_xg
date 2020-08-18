package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ShopProductCustomTypeMapper;
import com.jf.entity.ShopProductCustomType;
import com.jf.entity.ShopProductCustomTypeExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年6月2日 上午9:46:41 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ShopProductCustomTypeService extends BaseService<ShopProductCustomType, ShopProductCustomTypeExample> {
	@Autowired
	private ShopProductCustomTypeMapper shopProductCustomTypeMapper;

	@Autowired
	public void setShopProductCustomTypeMapper(ShopProductCustomTypeMapper shopProductCustomTypeMapper) {
		this.setDao(shopProductCustomTypeMapper);
		this.shopProductCustomTypeMapper = shopProductCustomTypeMapper;
	}
	
	
}
