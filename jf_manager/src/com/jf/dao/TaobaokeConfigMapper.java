package com.jf.dao;

import com.jf.entity.TaobaokeConfig;
import com.jf.entity.TaobaokeConfigExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface TaobaokeConfigMapper extends BaseDao<TaobaokeConfig, TaobaokeConfigExample>{
    int countByExample(TaobaokeConfigExample example);

    int deleteByExample(TaobaokeConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaobaokeConfig record);

    int insertSelective(TaobaokeConfig record);

    List<TaobaokeConfig> selectByExample(TaobaokeConfigExample example);

    TaobaokeConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaobaokeConfig record, @Param("example") TaobaokeConfigExample example);

    int updateByExample(@Param("record") TaobaokeConfig record, @Param("example") TaobaokeConfigExample example);

    int updateByPrimaryKeySelective(TaobaokeConfig record);

    int updateByPrimaryKey(TaobaokeConfig record);
}