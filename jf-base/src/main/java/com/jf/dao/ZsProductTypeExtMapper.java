package com.jf.dao;

import com.jf.entity.ZsProductTypeExt;
import com.jf.entity.ZsProductTypeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZsProductTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ZsProductTypeExt findById(int id);

    ZsProductTypeExt find(ZsProductTypeExtExample example);

    List<ZsProductTypeExt> list(ZsProductTypeExtExample example);

    List<Integer> listId(ZsProductTypeExtExample example);

    int count(ZsProductTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") ZsProductTypeExtExample example);

    int max(@Param("field") String field, @Param("example") ZsProductTypeExtExample example);

    int min(@Param("field") String field, @Param("example") ZsProductTypeExtExample example);

}

