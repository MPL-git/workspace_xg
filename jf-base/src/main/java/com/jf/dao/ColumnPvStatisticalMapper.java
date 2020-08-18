package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ColumnPvStatistical;
import com.jf.entity.ColumnPvStatisticalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnPvStatisticalMapper extends BaseDao<ColumnPvStatistical, ColumnPvStatisticalExample> {
    int countByExample(ColumnPvStatisticalExample example);

    int deleteByExample(ColumnPvStatisticalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ColumnPvStatistical record);

    int insertSelective(ColumnPvStatistical record);

    List<ColumnPvStatistical> selectByExample(ColumnPvStatisticalExample example);

    ColumnPvStatistical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ColumnPvStatistical record, @Param("example") ColumnPvStatisticalExample example);

    int updateByExample(@Param("record") ColumnPvStatistical record, @Param("example") ColumnPvStatisticalExample example);

    int updateByPrimaryKeySelective(ColumnPvStatistical record);

    int updateByPrimaryKey(ColumnPvStatistical record);
}