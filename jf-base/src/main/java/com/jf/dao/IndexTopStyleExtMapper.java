package com.jf.dao;

import com.jf.entity.IndexTopStyleExt;
import com.jf.entity.IndexTopStyleExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexTopStyleExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    IndexTopStyleExt findById(int id);

    IndexTopStyleExt find(IndexTopStyleExtExample example);

    List<IndexTopStyleExt> list(IndexTopStyleExtExample example);

    List<Integer> listId(IndexTopStyleExtExample example);

    int count(IndexTopStyleExtExample example);

    long sum(@Param("field") String field, @Param("example") IndexTopStyleExtExample example);

    int max(@Param("field") String field, @Param("example") IndexTopStyleExtExample example);

    int min(@Param("field") String field, @Param("example") IndexTopStyleExtExample example);

}

