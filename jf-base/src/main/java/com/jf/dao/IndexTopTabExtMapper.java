package com.jf.dao;

import com.jf.entity.IndexTopTabExt;
import com.jf.entity.IndexTopTabExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexTopTabExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    IndexTopTabExt findById(int id);

    IndexTopTabExt find(IndexTopTabExtExample example);

    List<IndexTopTabExt> list(IndexTopTabExtExample example);

    List<Integer> listId(IndexTopTabExtExample example);

    int count(IndexTopTabExtExample example);

    long sum(@Param("field") String field, @Param("example") IndexTopTabExtExample example);

    int max(@Param("field") String field, @Param("example") IndexTopTabExtExample example);

    int min(@Param("field") String field, @Param("example") IndexTopTabExtExample example);

}

