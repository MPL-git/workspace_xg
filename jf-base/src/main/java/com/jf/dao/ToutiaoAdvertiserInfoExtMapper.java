package com.jf.dao;

import com.jf.entity.ToutiaoAdvertiserInfoExt;
import com.jf.entity.ToutiaoAdvertiserInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToutiaoAdvertiserInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ToutiaoAdvertiserInfoExt findById(int id);

    ToutiaoAdvertiserInfoExt find(ToutiaoAdvertiserInfoExtExample example);

    List<ToutiaoAdvertiserInfoExt> list(ToutiaoAdvertiserInfoExtExample example);

    List<Integer> listId(ToutiaoAdvertiserInfoExtExample example);

    int count(ToutiaoAdvertiserInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") ToutiaoAdvertiserInfoExtExample example);

    int max(@Param("field") String field, @Param("example") ToutiaoAdvertiserInfoExtExample example);

    int min(@Param("field") String field, @Param("example") ToutiaoAdvertiserInfoExtExample example);

}

