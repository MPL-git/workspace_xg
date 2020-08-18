package com.jf.dao;

import com.jf.entity.FreightTemplateExt;
import com.jf.entity.FreightTemplateExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreightTemplateExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    FreightTemplateExt findById(int id);

    FreightTemplateExt find(FreightTemplateExtExample example);

    List<FreightTemplateExt> list(FreightTemplateExtExample example);

    List<Integer> listId(FreightTemplateExtExample example);

    int count(FreightTemplateExtExample example);

    long sum(@Param("field") String field, @Param("example") FreightTemplateExtExample example);

    int max(@Param("field") String field, @Param("example") FreightTemplateExtExample example);

    int min(@Param("field") String field, @Param("example") FreightTemplateExtExample example);

}

