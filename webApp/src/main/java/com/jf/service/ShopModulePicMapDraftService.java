package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ShopModulePicMapDraftMapper;
import com.jf.entity.ShopModulePicMapDraft;
import com.jf.entity.ShopModulePicMapDraftExample;

@Service
@Transactional
public class ShopModulePicMapDraftService extends BaseService<ShopModulePicMapDraft, ShopModulePicMapDraftExample> {
	@Autowired
	private ShopModulePicMapDraftMapper shopModulePicMapDraftMapper;
	@Autowired
	public void setShopModulePicMapDraftMapper(ShopModulePicMapDraftMapper shopModulePicMapDraftMapper) {
		this.setDao(shopModulePicMapDraftMapper);
		this.shopModulePicMapDraftMapper = shopModulePicMapDraftMapper;
	}
	
	
}
