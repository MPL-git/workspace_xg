package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.FullCutMapper;
import com.jf.dao.ShopPreferentialInfoCustomMapper;
import com.jf.dao.ShopPreferentialInfoMapper;
import com.jf.entity.FullCut;
import com.jf.entity.ShopPreferentialInfo;
import com.jf.entity.ShopPreferentialInfoCustom;
import com.jf.entity.ShopPreferentialInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopPreferentialInfoService extends BaseService<ShopPreferentialInfo,ShopPreferentialInfoExample> {
	@Autowired
	private ShopPreferentialInfoMapper dao;
	
	@Autowired
	private ShopPreferentialInfoCustomMapper shopPreferentialInfoCustomMapper;
	
	@Autowired
	private FullCutMapper fullCutMapper;
	
	@Autowired
	public void setShopPreferentialInfoMapper(ShopPreferentialInfoMapper shopPreferentialInfoMapper) {
		super.setDao(shopPreferentialInfoMapper);
		this.dao = shopPreferentialInfoMapper;
	}

	public List<ShopPreferentialInfoCustom> selectCustomByExample(ShopPreferentialInfoExample e) {
		return shopPreferentialInfoCustomMapper.selectByExample(e);
	}

	public void save(ShopPreferentialInfo shopPreferentialInfo, FullCut fc) {
		if(shopPreferentialInfo.getId()!=null){
			fullCutMapper.updateByPrimaryKeySelective(fc);
			this.updateByPrimaryKeySelective(shopPreferentialInfo);
		}else{
			fullCutMapper.insertSelective(fc);
			shopPreferentialInfo.setPreferentialId(fc.getId());
			this.insertSelective(shopPreferentialInfo);
		}
	}

	public int countUpProductCount(Integer mchtId) {
		return shopPreferentialInfoCustomMapper.countUpProductCount(mchtId);
	}

}
