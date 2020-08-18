package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.FavoritesMapper;
import com.jf.entity.Favorites;
import com.jf.entity.FavoritesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月12日 下午3:04:16 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class FavoritesService extends BaseService<Favorites, FavoritesExample> {
	
	@Autowired
	private FavoritesMapper favoritesMapper;
	
	@Autowired
	public void setFavoritesMapper(FavoritesMapper favoritesMapper) {
		this.setDao(favoritesMapper);
		this.favoritesMapper = favoritesMapper;
	}
	
	
}
