package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.AndroidChannelGroup;
import com.jf.entity.AndroidChannelGroupExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AndroidChannelGroupMapper extends BaseDao<AndroidChannelGroup, AndroidChannelGroupExample> {
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
