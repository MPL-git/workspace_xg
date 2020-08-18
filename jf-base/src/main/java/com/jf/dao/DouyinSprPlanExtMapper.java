package com.jf.dao;

import com.jf.entity.DouyinSprPlanExt;
import com.jf.entity.DouyinSprPlanExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DouyinSprPlanExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DouyinSprPlanExt findById(int id);

    DouyinSprPlanExt find(DouyinSprPlanExtExample example);

    List<DouyinSprPlanExt> list(DouyinSprPlanExtExample example);

    List<Integer> listId(DouyinSprPlanExtExample example);

    int count(DouyinSprPlanExtExample example);

    long sum(@Param("field") String field, @Param("example") DouyinSprPlanExtExample example);

    int max(@Param("field") String field, @Param("example") DouyinSprPlanExtExample example);

    int min(@Param("field") String field, @Param("example") DouyinSprPlanExtExample example);

}

