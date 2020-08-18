package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.IntegralDailyStatistics;
import com.jf.entity.IntegralDailyStatisticsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
