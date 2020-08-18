package com.jf.dao;

import com.jf.entity.SportTypeExt;
import com.jf.entity.SportTypeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SportTypeExt findById(int id);

    SportTypeExt find(SportTypeExtExample example);

    List<SportTypeExt> list(SportTypeExtExample example);

    List<Integer> listId(SportTypeExtExample example);

    int count(SportTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") SportTypeExtExample example);

    int max(@Param("field") String field, @Param("example") SportTypeExtExample example);

    int min(@Param("field") String field, @Param("example") SportTypeExtExample example);

}

