package com.jf.dao;

import com.jf.entity.SportGuessExt;
import com.jf.entity.SportGuessExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportGuessExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SportGuessExt findById(int id);

    SportGuessExt find(SportGuessExtExample example);

    List<SportGuessExt> list(SportGuessExtExample example);

    List<Integer> listId(SportGuessExtExample example);

    int count(SportGuessExtExample example);

    long sum(@Param("field") String field, @Param("example") SportGuessExtExample example);

    int max(@Param("field") String field, @Param("example") SportGuessExtExample example);

    int min(@Param("field") String field, @Param("example") SportGuessExtExample example);

}

