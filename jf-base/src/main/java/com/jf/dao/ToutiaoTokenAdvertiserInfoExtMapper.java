package com.jf.dao;

import com.jf.entity.ToutiaoTokenAdvertiserInfoExt;
import com.jf.entity.ToutiaoTokenAdvertiserInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToutiaoTokenAdvertiserInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ToutiaoTokenAdvertiserInfoExt findById(int id);

    ToutiaoTokenAdvertiserInfoExt find(ToutiaoTokenAdvertiserInfoExtExample example);

    List<ToutiaoTokenAdvertiserInfoExt> list(ToutiaoTokenAdvertiserInfoExtExample example);

    List<Integer> listId(ToutiaoTokenAdvertiserInfoExtExample example);

    int count(ToutiaoTokenAdvertiserInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") ToutiaoTokenAdvertiserInfoExtExample example);

    int max(@Param("field") String field, @Param("example") ToutiaoTokenAdvertiserInfoExtExample example);

    int min(@Param("field") String field, @Param("example") ToutiaoTokenAdvertiserInfoExtExample example);

}

