package com.jf.dao;

import com.jf.entity.MchtProductTypeExt;
import com.jf.entity.MchtProductTypeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtProductTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtProductTypeExt findById(int id);

    MchtProductTypeExt find(MchtProductTypeExtExample example);

    List<MchtProductTypeExt> list(MchtProductTypeExtExample example);

    List<Integer> listId(MchtProductTypeExtExample example);

    int count(MchtProductTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtProductTypeExtExample example);

    int max(@Param("field") String field, @Param("example") MchtProductTypeExtExample example);

    int min(@Param("field") String field, @Param("example") MchtProductTypeExtExample example);

}

