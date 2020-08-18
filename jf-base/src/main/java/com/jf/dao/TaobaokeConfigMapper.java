package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.TaobaokeConfig;
import com.jf.entity.TaobaokeConfigExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaobaokeConfigMapper extends BaseDao<TaobaokeConfig, TaobaokeConfigExample> {
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
