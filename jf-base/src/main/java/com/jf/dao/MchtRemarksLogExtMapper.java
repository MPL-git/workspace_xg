package com.jf.dao;

import com.jf.entity.MchtRemarksLogExt;
import com.jf.entity.MchtRemarksLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtRemarksLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtRemarksLogExt findById(int id);

    MchtRemarksLogExt find(MchtRemarksLogExtExample example);

    List<MchtRemarksLogExt> list(MchtRemarksLogExtExample example);

    List<Integer> listId(MchtRemarksLogExtExample example);

    int count(MchtRemarksLogExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtRemarksLogExtExample example);

    int max(@Param("field") String field, @Param("example") MchtRemarksLogExtExample example);

    int min(@Param("field") String field, @Param("example") MchtRemarksLogExtExample example);

}

