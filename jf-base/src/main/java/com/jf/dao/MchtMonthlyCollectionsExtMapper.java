package com.jf.dao;

import com.jf.entity.MchtMonthlyCollectionsExt;
import com.jf.entity.MchtMonthlyCollectionsExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtMonthlyCollectionsExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtMonthlyCollectionsExt findById(int id);

    MchtMonthlyCollectionsExt find(MchtMonthlyCollectionsExtExample example);

    List<MchtMonthlyCollectionsExt> list(MchtMonthlyCollectionsExtExample example);

    List<Integer> listId(MchtMonthlyCollectionsExtExample example);

    int count(MchtMonthlyCollectionsExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtMonthlyCollectionsExtExample example);

    int max(@Param("field") String field, @Param("example") MchtMonthlyCollectionsExtExample example);

    int min(@Param("field") String field, @Param("example") MchtMonthlyCollectionsExtExample example);

}

