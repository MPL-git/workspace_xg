package com.jf.dao;

import com.jf.entity.IntegralDailyStatistics;
import com.jf.entity.IntegralDailyStatisticsExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegralDailyStatisticsMapper extends BaseDao<IntegralDailyStatistics, IntegralDailyStatisticsExample> {
    int countByExample(IntegralDailyStatisticsExample example);

    int deleteByExample(IntegralDailyStatisticsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IntegralDailyStatistics record);

    int insertSelective(IntegralDailyStatistics record);

    List<IntegralDailyStatistics> selectByExample(IntegralDailyStatisticsExample example);

    IntegralDailyStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IntegralDailyStatistics record, @Param("example") IntegralDailyStatisticsExample example);

    int updateByExample(@Param("record") IntegralDailyStatistics record, @Param("example") IntegralDailyStatisticsExample example);

    int updateByPrimaryKeySelective(IntegralDailyStatistics record);

    int updateByPrimaryKey(IntegralDailyStatistics record);
}