package com.jf.dao;

import com.jf.entity.KdnWuliuInfoExt;
import com.jf.entity.KdnWuliuInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KdnWuliuInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    KdnWuliuInfoExt findById(int id);

    KdnWuliuInfoExt find(KdnWuliuInfoExtExample example);

    List<KdnWuliuInfoExt> list(KdnWuliuInfoExtExample example);

    List<Integer> listId(KdnWuliuInfoExtExample example);

    int count(KdnWuliuInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") KdnWuliuInfoExtExample example);

    int max(@Param("field") String field, @Param("example") KdnWuliuInfoExtExample example);

    int min(@Param("field") String field, @Param("example") KdnWuliuInfoExtExample example);

}

