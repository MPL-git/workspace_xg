package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ColumnPvHourStatistical;
import com.jf.entity.ColumnPvHourStatisticalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnPvHourStatisticalMapper extends BaseDao<ColumnPvHourStatistical,ColumnPvHourStatisticalExample> {
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