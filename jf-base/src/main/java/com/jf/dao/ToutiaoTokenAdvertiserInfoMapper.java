package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ToutiaoTokenAdvertiserInfo;
import com.jf.entity.ToutiaoTokenAdvertiserInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToutiaoTokenAdvertiserInfoMapper extends BaseDao<ToutiaoTokenAdvertiserInfo, ToutiaoTokenAdvertiserInfoExample> {
    int countByExample(ToutiaoTokenAdvertiserInfoExample example);

    int deleteByExample(ToutiaoTokenAdvertiserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ToutiaoTokenAdvertiserInfo record);

    int insertSelective(ToutiaoTokenAdvertiserInfo record);

    List<ToutiaoTokenAdvertiserInfo> selectByExample(ToutiaoTokenAdvertiserInfoExample example);

    ToutiaoTokenAdvertiserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ToutiaoTokenAdvertiserInfo record, @Param("example") ToutiaoTokenAdvertiserInfoExample example);

    int updateByExample(@Param("record") ToutiaoTokenAdvertiserInfo record, @Param("example") ToutiaoTokenAdvertiserInfoExample example);

    int updateByPrimaryKeySelective(ToutiaoTokenAdvertiserInfo record);

    int updateByPrimaryKey(ToutiaoTokenAdvertiserInfo record);
}
