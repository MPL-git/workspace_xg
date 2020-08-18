package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.CloudProductPropValueExt;
import com.jf.entity.CloudProductPropValueExtExample;

@Repository
public interface CloudProductPropValueExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CloudProductPropValueExt findById(int id);

    CloudProductPropValueExt find(CloudProductPropValueExtExample example);

    List<CloudProductPropValueExt> list(CloudProductPropValueExtExample example);

    List<Integer> listId(CloudProductPropValueExtExample example);

    int count(CloudProductPropValueExtExample example);

    long sum(@Param("field") String field, @Param("example") CloudProductPropValueExtExample example);

    int max(@Param("field") String field, @Param("example") CloudProductPropValueExtExample example);

    int min(@Param("field") String field, @Param("example") CloudProductPropValueExtExample example);

}

