package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.CloudProductItemExt;
import com.jf.entity.CloudProductItemExtExample;

@Repository
public interface CloudProductItemExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CloudProductItemExt findById(int id);

    CloudProductItemExt find(CloudProductItemExtExample example);

    List<CloudProductItemExt> list(CloudProductItemExtExample example);

    List<Integer> listId(CloudProductItemExtExample example);

    int count(CloudProductItemExtExample example);

    long sum(@Param("field") String field, @Param("example") CloudProductItemExtExample example);

    int max(@Param("field") String field, @Param("example") CloudProductItemExtExample example);

    int min(@Param("field") String field, @Param("example") CloudProductItemExtExample example);

}

