package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.FavoritesProductTypeMapper;
import com.jf.entity.FavoritesProductType;
import com.jf.entity.FavoritesProductTypeExample;

@Service
@Transactional
public class FavoritesProductTypeService extends BaseService<FavoritesProductType,FavoritesProductTypeExample> {
	@Autowired
	private FavoritesProductTypeMapper favoritesProductTypeMapper;
	
	@Autowired
	public void setFavoritesProductTypeMapper(FavoritesProductTypeMapper favoritesProductTypeMapper) {
		super.setDao(favoritesProductTypeMapper);
		this.favoritesProductTypeMapper = favoritesProductTypeMapper;
	}
}
