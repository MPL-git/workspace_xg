package com.jf.dao;

import com.jf.entity.ActivityPvStatistical;
import com.jf.entity.ActivityPvStatisticalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityPvStatisticalMapper extends BaseDao<ActivityPvStatistical, ActivityPvStatisticalExample>{
    int countByExample(ActivityPvStatisticalExample example);

    int deleteByExample(ActivityPvStatisticalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityPvStatistical record);

    int insertSelective(ActivityPvStatistical record);

    List<ActivityPvStatistical> selectByExample(ActivityPvStatisticalExample example);

    ActivityPvStatistical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityPvStatistical record, @Param("example") ActivityPvStatisticalExample example);

    int updateByExample(@Param("record") ActivityPvStatistical record, @Param("example") ActivityPvStatisticalExample example);

    int updateByPrimaryKeySelective(ActivityPvStatistical record);

    int updateByPrimaryKey(ActivityPvStatistical record);
}