package com.jf.dao;

import com.jf.entity.MchtCloseApplicationExt;
import com.jf.entity.MchtCloseApplicationExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtCloseApplicationExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtCloseApplicationExt findById(int id);

    MchtCloseApplicationExt find(MchtCloseApplicationExtExample example);

    List<MchtCloseApplicationExt> list(MchtCloseApplicationExtExample example);

    List<Integer> listId(MchtCloseApplicationExtExample example);

    int count(MchtCloseApplicationExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtCloseApplicationExtExample example);

    int max(@Param("field") String field, @Param("example") MchtCloseApplicationExtExample example);

    int min(@Param("field") String field, @Param("example") MchtCloseApplicationExtExample example);

}

