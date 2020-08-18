package com.jf.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ShopownerCustomMapper;
import com.jf.dao.ShopownerMapper;
import com.jf.entity.SalesmanCustom;
import com.jf.entity.Shopowner;
import com.jf.entity.ShopownerCustom;
import com.jf.entity.ShopownerCustomExample;
import com.jf.entity.ShopownerExample;

@Service
@Transactional
public class ShopownerService extends BaseService<Shopowner, ShopownerExample>{
	
	@Autowired
	private ShopownerMapper shopownerMapper;
	
	@Autowired
	private ShopownerCustomMapper shopownerCustomMapper;
	
	@Autowired
	public void setactivityAreaMapper(ShopownerMapper shopownerMapper) {
		super.setDao(shopownerMapper);
		this.shopownerMapper = shopownerMapper;
	}

	/**
	 * @MethodName countByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月12日 下午6:01:28
	 */
	public Integer countByCustomExample(
			ShopownerCustomExample shopownerCustomExample) {
		// TODO Auto-generated method stub
		return shopownerCustomMapper.countByCustomExample(shopownerCustomExample);
	}

	/**
	 * @MethodName selectByCustomExample
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月12日 下午6:01:34
	 */
	public List<ShopownerCustom> selectByCustomExample(
			ShopownerCustomExample shopownerCustomExample) {
		// TODO Auto-generated method stub
		return shopownerCustomMapper.selectByCustomExample(shopownerCustomExample);
	}
}
