package com.jf.dao;

import com.jf.entity.AndroidChannelGroupSet;
import com.jf.entity.AndroidChannelGroupSetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AndroidChannelGroupSetMapper extends BaseDao<AndroidChannelGroupSet, AndroidChannelGroupSetExample> {
    int countByExample(AndroidChannelGroupSetExample example);

    int deleteByExample(AndroidChannelGroupSetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AndroidChannelGroupSet record);

    int insertSelective(AndroidChannelGroupSet record);

    List<AndroidChannelGroupSet> selectByExample(AndroidChannelGroupSetExample example);

    AndroidChannelGroupSet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AndroidChannelGroupSet record, @Param("example") AndroidChannelGroupSetExample example);

    int updateByExample(@Param("record") AndroidChannelGroupSet record, @Param("example") AndroidChannelGroupSetExample example);

    int updateByPrimaryKeySelective(AndroidChannelGroupSet record);

    int updateByPrimaryKey(AndroidChannelGroupSet record);
}