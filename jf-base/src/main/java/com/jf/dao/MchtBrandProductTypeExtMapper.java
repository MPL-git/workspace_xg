package com.jf.dao;

import com.jf.entity.MchtBrandProductTypeExt;
import com.jf.entity.MchtBrandProductTypeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandProductTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandProductTypeExt findById(int id);

    MchtBrandProductTypeExt find(MchtBrandProductTypeExtExample example);

    List<MchtBrandProductTypeExt> list(MchtBrandProductTypeExtExample example);

    List<Integer> listId(MchtBrandProductTypeExtExample example);

    int count(MchtBrandProductTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandProductTypeExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandProductTypeExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandProductTypeExtExample example);

}

