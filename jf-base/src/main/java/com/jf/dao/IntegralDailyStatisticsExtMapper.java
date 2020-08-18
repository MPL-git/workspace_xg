package com.jf.dao;

import com.jf.entity.IntegralDailyStatisticsExt;
import com.jf.entity.IntegralDailyStatisticsExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegralDailyStatisticsExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    IntegralDailyStatisticsExt findById(int id);

    IntegralDailyStatisticsExt find(IntegralDailyStatisticsExtExample example);

    List<IntegralDailyStatisticsExt> list(IntegralDailyStatisticsExtExample example);

    List<Integer> listId(IntegralDailyStatisticsExtExample example);

    int count(IntegralDailyStatisticsExtExample example);

    long sum(@Param("field") String field, @Param("example") IntegralDailyStatisticsExtExample example);

    int max(@Param("field") String field, @Param("example") IntegralDailyStatisticsExtExample example);

    int min(@Param("field") String field, @Param("example") IntegralDailyStatisticsExtExample example);

}

