package com.jf.dao;

import com.jf.entity.SourceProductTypeExt;
import com.jf.entity.SourceProductTypeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceProductTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SourceProductTypeExt findById(int id);

    SourceProductTypeExt find(SourceProductTypeExtExample example);

    List<SourceProductTypeExt> list(SourceProductTypeExtExample example);

    List<Integer> listId(SourceProductTypeExtExample example);

    int count(SourceProductTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") SourceProductTypeExtExample example);

    int max(@Param("field") String field, @Param("example") SourceProductTypeExtExample example);

    int min(@Param("field") String field, @Param("example") SourceProductTypeExtExample example);

}

