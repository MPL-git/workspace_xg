package com.jf.dao;

import com.jf.entity.ToutiaoAdvertiserInfo;
import com.jf.entity.ToutiaoAdvertiserInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToutiaoAdvertiserInfoMapper extends BaseDao<ToutiaoAdvertiserInfo, ToutiaoAdvertiserInfoExample> {
    int countByExample(ToutiaoAdvertiserInfoExample example);

    int deleteByExample(ToutiaoAdvertiserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ToutiaoAdvertiserInfo record);

    int insertSelective(ToutiaoAdvertiserInfo record);

    List<ToutiaoAdvertiserInfo> selectByExample(ToutiaoAdvertiserInfoExample example);

    ToutiaoAdvertiserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ToutiaoAdvertiserInfo record, @Param("example") ToutiaoAdvertiserInfoExample example);

    int updateByExample(@Param("record") ToutiaoAdvertiserInfo record, @Param("example") ToutiaoAdvertiserInfoExample example);

    int updateByPrimaryKeySelective(ToutiaoAdvertiserInfo record);

    int updateByPrimaryKey(ToutiaoAdvertiserInfo record);
}