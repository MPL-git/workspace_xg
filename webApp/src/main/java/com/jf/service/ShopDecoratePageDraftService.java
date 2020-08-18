package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ShopDecoratePageDraftMapper;
import com.jf.entity.ShopDecoratePageDraft;
import com.jf.entity.ShopDecoratePageDraftExample;

@Service
@Transactional
public class ShopDecoratePageDraftService extends BaseService<ShopDecoratePageDraft, ShopDecoratePageDraftExample> {
	@Autowired
	private ShopDecoratePageDraftMapper shopDecoratePageDraftMapper;
	@Autowired
	public void setShopDecoratePageDraftMapper(ShopDecoratePageDraftMapper shopDecoratePageDraftMapper) {
		this.setDao(shopDecoratePageDraftMapper);
		this.shopDecoratePageDraftMapper = shopDecoratePageDraftMapper;
	}
	
	
}
