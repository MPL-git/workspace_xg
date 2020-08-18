package com.jf.dao;

import com.jf.entity.BlackListExt;
import com.jf.entity.BlackListExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlackListExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    BlackListExt findById(int id);

    BlackListExt find(BlackListExtExample example);

    List<BlackListExt> list(BlackListExtExample example);

    List<Integer> listId(BlackListExtExample example);

    int count(BlackListExtExample example);

    long sum(@Param("field") String field, @Param("example") BlackListExtExample example);

    int max(@Param("field") String field, @Param("example") BlackListExtExample example);

    int min(@Param("field") String field, @Param("example") BlackListExtExample example);

}

