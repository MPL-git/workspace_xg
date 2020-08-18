package com.jf.dao;

import com.jf.entity.SportLogExt;
import com.jf.entity.SportLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SportLogExt findById(int id);

    SportLogExt find(SportLogExtExample example);

    List<SportLogExt> list(SportLogExtExample example);

    List<Integer> listId(SportLogExtExample example);

    int count(SportLogExtExample example);

    long sum(@Param("field") String field, @Param("example") SportLogExtExample example);

    int max(@Param("field") String field, @Param("example") SportLogExtExample example);

    int min(@Param("field") String field, @Param("example") SportLogExtExample example);

}

