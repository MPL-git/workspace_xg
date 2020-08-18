package com.jf.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.FavoritesCustomMapper;
import com.jf.dao.FavoritesMapper;
import com.jf.entity.Favorites;
import com.jf.entity.FavoritesCustom;
import com.jf.entity.FavoritesExample;

@Service
@Transactional
public class FavoritesService extends BaseService<Favorites,FavoritesExample> {
	@Autowired
	private FavoritesMapper favoritesMapper;
	@Autowired
	private FavoritesCustomMapper favoritesCustomMapper;
	
	@Autowired
	public void setFavoritesMapper(FavoritesMapper favoritesMapper) {
		super.setDao(favoritesMapper);
		this.favoritesMapper = favoritesMapper;
	}
	
	public int countFavoritesCustomByExample(FavoritesExample example){
		return favoritesCustomMapper.countByExample(example);
	}
    public List<FavoritesCustom> selectFavoritesCustomByExample(FavoritesExample example){
    	return favoritesCustomMapper.selectByExample(example);
    }
    public FavoritesCustom selectFavoritesCustomByPrimaryKey(Integer id){
    	return favoritesCustomMapper.selectByPrimaryKey(id);
    }
	public int updateFavoritesSeqNo(HashMap<String,Object> map){
		return favoritesCustomMapper.updateFavoritesSeqNo(map);
	}
}
