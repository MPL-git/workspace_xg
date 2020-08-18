package com.jf.dao;

import com.jf.entity.RemarksLogExt;
import com.jf.entity.RemarksLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemarksLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    RemarksLogExt findById(int id);

    RemarksLogExt find(RemarksLogExtExample example);

    List<RemarksLogExt> list(RemarksLogExtExample example);

    List<Integer> listId(RemarksLogExtExample example);

    int count(RemarksLogExtExample example);

    long sum(@Param("field") String field, @Param("example") RemarksLogExtExample example);

    int max(@Param("field") String field, @Param("example") RemarksLogExtExample example);

    int min(@Param("field") String field, @Param("example") RemarksLogExtExample example);

}

