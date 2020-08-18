package com.jf.dao;

import com.jf.entity.SvipOrderExt;
import com.jf.entity.SvipOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SvipOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SvipOrderExt findById(int id);

    SvipOrderExt find(SvipOrderExtExample example);

    List<SvipOrderExt> list(SvipOrderExtExample example);

    List<Integer> listId(SvipOrderExtExample example);

    int count(SvipOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") SvipOrderExtExample example);

    int max(@Param("field") String field, @Param("example") SvipOrderExtExample example);

    int min(@Param("field") String field, @Param("example") SvipOrderExtExample example);

}

