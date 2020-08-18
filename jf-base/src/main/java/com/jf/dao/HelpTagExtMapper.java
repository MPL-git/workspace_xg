package com.jf.dao;

import com.jf.entity.HelpTagExt;
import com.jf.entity.HelpTagExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpTagExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    HelpTagExt findById(int id);

    HelpTagExt find(HelpTagExtExample example);

    List<HelpTagExt> list(HelpTagExtExample example);

    List<Integer> listId(HelpTagExtExample example);

    int count(HelpTagExtExample example);

    long sum(@Param("field") String field, @Param("example") HelpTagExtExample example);

    int max(@Param("field") String field, @Param("example") HelpTagExtExample example);

    int min(@Param("field") String field, @Param("example") HelpTagExtExample example);

}

