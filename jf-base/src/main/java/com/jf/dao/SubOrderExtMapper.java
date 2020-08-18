package com.jf.dao;

import com.jf.entity.SubOrderExt;
import com.jf.entity.SubOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SubOrderExt findById(int id);

    SubOrderExt find(SubOrderExtExample example);

    List<SubOrderExt> list(SubOrderExtExample example);

    List<Integer> listId(SubOrderExtExample example);

    int count(SubOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") SubOrderExtExample example);

    int max(@Param("field") String field, @Param("example") SubOrderExtExample example);

    int min(@Param("field") String field, @Param("example") SubOrderExtExample example);

}

