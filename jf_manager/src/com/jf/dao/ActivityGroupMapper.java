package com.jf.dao;

import com.jf.entity.ActivityGroup;
import com.jf.entity.ActivityGroupExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityGroupMapper extends BaseDao<ActivityGroup, ActivityGroupExample> {
    int countByExample(ActivityGroupExample example);

    int deleteByExample(ActivityGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityGroup record);

    int insertSelective(ActivityGroup record);

    List<ActivityGroup> selectByExample(ActivityGroupExample example);

    ActivityGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityGroup record, @Param("example") ActivityGroupExample example);

    int updateByExample(@Param("record") ActivityGroup record, @Param("example") ActivityGroupExample example);

    int updateByPrimaryKeySelective(ActivityGroup record);

    int updateByPrimaryKey(ActivityGroup record);
}