package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.Favorites;
import com.jf.entity.FavoritesExample;
@Repository
public interface FavoritesMapper extends BaseDao<Favorites, FavoritesExample>{
    int countByExample(FavoritesExample example);

    int deleteByExample(FavoritesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Favorites record);

    int insertSelective(Favorites record);

    List<Favorites> selectByExample(FavoritesExample example);

    Favorites selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Favorites record, @Param("example") FavoritesExample example);

    int updateByExample(@Param("record") Favorites record, @Param("example") FavoritesExample example);

    int updateByPrimaryKeySelective(Favorites record);

    int updateByPrimaryKey(Favorites record);
}