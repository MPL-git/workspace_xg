package com.jf.dao;

import com.jf.entity.DouyinSprDtlExt;
import com.jf.entity.DouyinSprDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DouyinSprDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DouyinSprDtlExt findById(int id);

    DouyinSprDtlExt find(DouyinSprDtlExtExample example);

    List<DouyinSprDtlExt> list(DouyinSprDtlExtExample example);

    List<Integer> listId(DouyinSprDtlExtExample example);

    int count(DouyinSprDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") DouyinSprDtlExtExample example);

    int max(@Param("field") String field, @Param("example") DouyinSprDtlExtExample example);

    int min(@Param("field") String field, @Param("example") DouyinSprDtlExtExample example);

}

