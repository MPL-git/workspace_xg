package com.jf.dao;

import com.jf.entity.ShopScore;
import com.jf.entity.ShopScoreExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopScoreMapper extends BaseDao<ShopScore, ShopScoreExample> {
    int countByExample(ShopScoreExample example);

    int deleteByExample(ShopScoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopScore record);

    int insertSelective(ShopScore record);

    List<ShopScore> selectByExample(ShopScoreExample example);

    ShopScore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopScore record, @Param("example") ShopScoreExample example);

    int updateByExample(@Param("record") ShopScore record, @Param("example") ShopScoreExample example);

    int updateByPrimaryKeySelective(ShopScore record);

    int updateByPrimaryKey(ShopScore record);
}