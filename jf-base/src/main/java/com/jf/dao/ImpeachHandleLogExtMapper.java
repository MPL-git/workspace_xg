package com.jf.dao;

import com.jf.entity.ImpeachHandleLogExt;
import com.jf.entity.ImpeachHandleLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpeachHandleLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ImpeachHandleLogExt findById(int id);

    ImpeachHandleLogExt find(ImpeachHandleLogExtExample example);

    List<ImpeachHandleLogExt> list(ImpeachHandleLogExtExample example);

    List<Integer> listId(ImpeachHandleLogExtExample example);

    int count(ImpeachHandleLogExtExample example);

    long sum(@Param("field") String field, @Param("example") ImpeachHandleLogExtExample example);

    int max(@Param("field") String field, @Param("example") ImpeachHandleLogExtExample example);

    int min(@Param("field") String field, @Param("example") ImpeachHandleLogExtExample example);

}

