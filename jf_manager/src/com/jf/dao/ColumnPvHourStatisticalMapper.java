package com.jf.dao;

import com.jf.entity.ColumnPvHourStatistical;
import com.jf.entity.ColumnPvHourStatisticalExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ColumnPvHourStatisticalMapper extends BaseDao<ColumnPvHourStatistical,ColumnPvHourStatisticalExample>{
    int countByExample(ColumnPvHourStatisticalExample example);

    int deleteByExample(ColumnPvHourStatisticalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ColumnPvHourStatistical record);

    int insertSelective(ColumnPvHourStatistical record);

    List<ColumnPvHourStatistical> selectByExample(ColumnPvHourStatisticalExample example);

    ColumnPvHourStatistical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ColumnPvHourStatistical record, @Param("example") ColumnPvHourStatisticalExample example);

    int updateByExample(@Param("record") ColumnPvHourStatistical record, @Param("example") ColumnPvHourStatisticalExample example);

    int updateByPrimaryKeySelective(ColumnPvHourStatistical record);

    int updateByPrimaryKey(ColumnPvHourStatistical record);
}