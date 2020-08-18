package com.jf.dao;

import com.jf.entity.DecorateInfoExt;
import com.jf.entity.DecorateInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorateInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DecorateInfoExt findById(int id);

    DecorateInfoExt find(DecorateInfoExtExample example);

    List<DecorateInfoExt> list(DecorateInfoExtExample example);

    List<Integer> listId(DecorateInfoExtExample example);

    int count(DecorateInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") DecorateInfoExtExample example);

    int max(@Param("field") String field, @Param("example") DecorateInfoExtExample example);

    int min(@Param("field") String field, @Param("example") DecorateInfoExtExample example);

}

