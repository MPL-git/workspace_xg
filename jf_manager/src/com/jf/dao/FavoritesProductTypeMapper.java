package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.FavoritesProductType;
import com.jf.entity.FavoritesProductTypeExample;
@Repository
public interface FavoritesProductTypeMapper extends BaseDao<FavoritesProductType, FavoritesProductTypeExample>{
    int countByExample(FavoritesProductTypeExample example);

    int deleteByExample(FavoritesProductTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FavoritesProductType record);

    int insertSelective(FavoritesProductType record);

    List<FavoritesProductType> selectByExample(FavoritesProductTypeExample example);

    FavoritesProductType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FavoritesProductType record, @Param("example") FavoritesProductTypeExample example);

    int updateByExample(@Param("record") FavoritesProductType record, @Param("example") FavoritesProductTypeExample example);

    int updateByPrimaryKeySelective(FavoritesProductType record);

    int updateByPrimaryKey(FavoritesProductType record);
}