package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SvipPracticeInfoExt;
import com.jf.entity.SvipPracticeInfoExtExample;

@Repository
public interface SvipPracticeInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SvipPracticeInfoExt findById(int id);

    SvipPracticeInfoExt find(SvipPracticeInfoExtExample example);

    List<SvipPracticeInfoExt> list(SvipPracticeInfoExtExample example);

    List<Integer> listId(SvipPracticeInfoExtExample example);

    int count(SvipPracticeInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") SvipPracticeInfoExtExample example);

    int max(@Param("field") String field, @Param("example") SvipPracticeInfoExtExample example);

    int min(@Param("field") String field, @Param("example") SvipPracticeInfoExtExample example);

}

