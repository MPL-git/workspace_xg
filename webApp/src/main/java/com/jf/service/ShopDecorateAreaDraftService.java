package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ShopDecorateAreaDraftMapper;
import com.jf.entity.ShopDecorateAreaDraft;
import com.jf.entity.ShopDecorateAreaDraftExample;

@Service
@Transactional
public class ShopDecorateAreaDraftService extends BaseService<ShopDecorateAreaDraft, ShopDecorateAreaDraftExample> {
	@Autowired
	private ShopDecorateAreaDraftMapper shopDecorateAreaDraftMapper;
	@Autowired
	public void setShopDecorateAreaDraftMapper(ShopDecorateAreaDraftMapper shopDecorateAreaDraftMapper) {
		this.setDao(shopDecorateAreaDraftMapper);
		this.shopDecorateAreaDraftMapper = shopDecorateAreaDraftMapper;
	}
	
	
}
