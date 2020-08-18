package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.FavoritesCustom;
import com.jf.entity.FavoritesExample;
@Repository
public interface FavoritesCustomMapper{
    int countByExample(FavoritesExample example);
    List<FavoritesCustom> selectByExample(FavoritesExample example);
    FavoritesCustom selectByPrimaryKey(Integer id);
	int updateFavoritesSeqNo(HashMap<String,Object> map);
}