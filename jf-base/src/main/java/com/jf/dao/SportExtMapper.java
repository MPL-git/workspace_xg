package com.jf.dao;

import com.jf.entity.SportExt;
import com.jf.entity.SportExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SportExt findById(int id);

    SportExt find(SportExtExample example);

    List<SportExt> list(SportExtExample example);

    List<Integer> listId(SportExtExample example);

    int count(SportExtExample example);

    long sum(@Param("field") String field, @Param("example") SportExtExample example);

    int max(@Param("field") String field, @Param("example") SportExtExample example);

    int min(@Param("field") String field, @Param("example") SportExtExample example);

}

