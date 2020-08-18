package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.CloudProductAfterTempletExt;
import com.jf.entity.CloudProductAfterTempletExtExample;

@Repository
public interface CloudProductAfterTempletExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CloudProductAfterTempletExt findById(int id);

    CloudProductAfterTempletExt find(CloudProductAfterTempletExtExample example);

    List<CloudProductAfterTempletExt> list(CloudProductAfterTempletExtExample example);

    List<Integer> listId(CloudProductAfterTempletExtExample example);

    int count(CloudProductAfterTempletExtExample example);

    long sum(@Param("field") String field, @Param("example") CloudProductAfterTempletExtExample example);

    int max(@Param("field") String field, @Param("example") CloudProductAfterTempletExtExample example);

    int min(@Param("field") String field, @Param("example") CloudProductAfterTempletExtExample example);

}

