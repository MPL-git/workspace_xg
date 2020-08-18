package com.jf.dao;

import com.jf.entity.ActivityAreaPvStatistical;
import com.jf.entity.ActivityAreaPvStatisticalExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityAreaPvStatisticalMapper extends BaseDao<ActivityAreaPvStatistical, ActivityAreaPvStatisticalExample>{
    int countByExample(ActivityAreaPvStatisticalExample example);

    int deleteByExample(ActivityAreaPvStatisticalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityAreaPvStatistical record);

    int insertSelective(ActivityAreaPvStatistical record);

    List<ActivityAreaPvStatistical> selectByExample(ActivityAreaPvStatisticalExample example);

    ActivityAreaPvStatistical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityAreaPvStatistical record, @Param("example") ActivityAreaPvStatisticalExample example);

    int updateByExample(@Param("record") ActivityAreaPvStatistical record, @Param("example") ActivityAreaPvStatisticalExample example);

    int updateByPrimaryKeySelective(ActivityAreaPvStatistical record);

    int updateByPrimaryKey(ActivityAreaPvStatistical record);
}