package com.jf.dao;

import com.jf.entity.MchtMonthTradeExt;
import com.jf.entity.MchtMonthTradeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtMonthTradeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtMonthTradeExt findById(int id);

    MchtMonthTradeExt find(MchtMonthTradeExtExample example);

    List<MchtMonthTradeExt> list(MchtMonthTradeExtExample example);

    List<Integer> listId(MchtMonthTradeExtExample example);

    int count(MchtMonthTradeExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtMonthTradeExtExample example);

    int max(@Param("field") String field, @Param("example") MchtMonthTradeExtExample example);

    int min(@Param("field") String field, @Param("example") MchtMonthTradeExtExample example);

}

