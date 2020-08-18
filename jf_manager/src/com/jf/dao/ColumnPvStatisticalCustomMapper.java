package com.jf.dao;

import com.jf.entity.ColumnPvStatistical;
import com.jf.entity.ColumnPvStatisticalCustom;
import com.jf.entity.ColumnPvStatisticalCustomExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ColumnPvStatisticalCustomMapper {

    public int countByCustomExample(ColumnPvStatisticalCustomExample example);

    public List<ColumnPvStatisticalCustom> selectByCustomExample(ColumnPvStatisticalCustomExample example);

    public ColumnPvStatisticalCustom selectByCustomPrimaryKey(Integer id);

    public int updateByCustomExampleSelective(@Param("record") ColumnPvStatistical record, @Param("example") ColumnPvStatisticalCustomExample example);

    public List<Map<String, Object>> selectColumnPvStatisticalDayList(Map<String, Object> paramMap);

    public Map<String, Object> selectColumnPvStatisticalDaySum(Map<String, Object> paramMap);

    public Map<String, Object> selectColumnPvStatisticalWeekList(Map<String, Object> paramMap);

    public List<Map<String, Object>> selectColumnPvStatisticalMonthList(Map<String, Object> paramMap);

    public List<Map<String, Object>> selectColumnPvStatisticalColumnTypeList(Map<String, Object> paramMap);

    Map<String, Object> selectColumnPvStatisticalColumnTypeSum(Map<String, Object> paramMap);

}