package com.jf.dao;

import com.jf.entity.MchtUserExt;
import com.jf.entity.MchtUserExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtUserExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtUserExt findById(int id);

    MchtUserExt find(MchtUserExtExample example);

    List<MchtUserExt> list(MchtUserExtExample example);

    List<Integer> listId(MchtUserExtExample example);

    int count(MchtUserExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtUserExtExample example);

    int max(@Param("field") String field, @Param("example") MchtUserExtExample example);

    int min(@Param("field") String field, @Param("example") MchtUserExtExample example);

}

