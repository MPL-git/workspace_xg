package com.jf.dao;

import com.jf.entity.PropertyRightProsecutionExt;
import com.jf.entity.PropertyRightProsecutionExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRightProsecutionExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PropertyRightProsecutionExt findById(int id);

    PropertyRightProsecutionExt find(PropertyRightProsecutionExtExample example);

    List<PropertyRightProsecutionExt> list(PropertyRightProsecutionExtExample example);

    List<Integer> listId(PropertyRightProsecutionExtExample example);

    int count(PropertyRightProsecutionExtExample example);

    long sum(@Param("field") String field, @Param("example") PropertyRightProsecutionExtExample example);

    int max(@Param("field") String field, @Param("example") PropertyRightProsecutionExtExample example);

    int min(@Param("field") String field, @Param("example") PropertyRightProsecutionExtExample example);

}

