package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.CloudProductExt;
import com.jf.entity.CloudProductExtExample;

@Repository
public interface CloudProductExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CloudProductExt findById(int id);

    CloudProductExt find(CloudProductExtExample example);

    List<CloudProductExt> list(CloudProductExtExample example);

    List<Integer> listId(CloudProductExtExample example);

    int count(CloudProductExtExample example);

    long sum(@Param("field") String field, @Param("example") CloudProductExtExample example);

    int max(@Param("field") String field, @Param("example") CloudProductExtExample example);

    int min(@Param("field") String field, @Param("example") CloudProductExtExample example);

}

