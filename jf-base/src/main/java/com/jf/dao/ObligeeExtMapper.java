package com.jf.dao;

import com.jf.entity.ObligeeExt;
import com.jf.entity.ObligeeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObligeeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ObligeeExt findById(int id);

    ObligeeExt find(ObligeeExtExample example);

    List<ObligeeExt> list(ObligeeExtExample example);

    List<Integer> listId(ObligeeExtExample example);

    int count(ObligeeExtExample example);

    long sum(@Param("field") String field, @Param("example") ObligeeExtExample example);

    int max(@Param("field") String field, @Param("example") ObligeeExtExample example);

    int min(@Param("field") String field, @Param("example") ObligeeExtExample example);

}

