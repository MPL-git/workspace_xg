package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.CloudProductPropExt;
import com.jf.entity.CloudProductPropExtExample;

@Repository
public interface CloudProductPropExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CloudProductPropExt findById(int id);

    CloudProductPropExt find(CloudProductPropExtExample example);

    List<CloudProductPropExt> list(CloudProductPropExtExample example);

    List<Integer> listId(CloudProductPropExtExample example);

    int count(CloudProductPropExtExample example);

    long sum(@Param("field") String field, @Param("example") CloudProductPropExtExample example);

    int max(@Param("field") String field, @Param("example") CloudProductPropExtExample example);

    int min(@Param("field") String field, @Param("example") CloudProductPropExtExample example);

}

