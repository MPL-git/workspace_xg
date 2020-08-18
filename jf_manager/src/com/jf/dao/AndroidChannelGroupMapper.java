package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.AndroidChannelGroup;
import com.jf.entity.AndroidChannelGroupExample;
@Repository
public interface AndroidChannelGroupMapper extends BaseDao<AndroidChannelGroup, AndroidChannelGroupExample>{
    int countByExample(AndroidChannelGroupExample example);

    int deleteByExample(AndroidChannelGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AndroidChannelGroup record);

    int insertSelective(AndroidChannelGroup record);

    List<AndroidChannelGroup> selectByExample(AndroidChannelGroupExample example);

    AndroidChannelGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AndroidChannelGroup record, @Param("example") AndroidChannelGroupExample example);

    int updateByExample(@Param("record") AndroidChannelGroup record, @Param("example") AndroidChannelGroupExample example);

    int updateByPrimaryKeySelective(AndroidChannelGroup record);

    int updateByPrimaryKey(AndroidChannelGroup record);
}