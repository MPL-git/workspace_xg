package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SpUserExt;
import com.jf.entity.SpUserExtExample;

@Repository
public interface SpUserExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SpUserExt findById(int id);

    SpUserExt find(SpUserExtExample example);

    List<SpUserExt> list(SpUserExtExample example);

    List<Integer> listId(SpUserExtExample example);

    int count(SpUserExtExample example);

    long sum(@Param("field") String field, @Param("example") SpUserExtExample example);

    int max(@Param("field") String field, @Param("example") SpUserExtExample example);

    int min(@Param("field") String field, @Param("example") SpUserExtExample example);

}

