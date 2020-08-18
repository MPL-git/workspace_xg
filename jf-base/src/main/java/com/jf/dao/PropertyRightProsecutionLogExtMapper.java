package com.jf.dao;

import com.jf.entity.PropertyRightProsecutionLogExt;
import com.jf.entity.PropertyRightProsecutionLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRightProsecutionLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PropertyRightProsecutionLogExt findById(int id);

    PropertyRightProsecutionLogExt find(PropertyRightProsecutionLogExtExample example);

    List<PropertyRightProsecutionLogExt> list(PropertyRightProsecutionLogExtExample example);

    List<Integer> listId(PropertyRightProsecutionLogExtExample example);

    int count(PropertyRightProsecutionLogExtExample example);

    long sum(@Param("field") String field, @Param("example") PropertyRightProsecutionLogExtExample example);

    int max(@Param("field") String field, @Param("example") PropertyRightProsecutionLogExtExample example);

    int min(@Param("field") String field, @Param("example") PropertyRightProsecutionLogExtExample example);

}

