package com.jf.dao;

import com.jf.entity.PropertyRightProsecutionPicExt;
import com.jf.entity.PropertyRightProsecutionPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRightProsecutionPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PropertyRightProsecutionPicExt findById(int id);

    PropertyRightProsecutionPicExt find(PropertyRightProsecutionPicExtExample example);

    List<PropertyRightProsecutionPicExt> list(PropertyRightProsecutionPicExtExample example);

    List<Integer> listId(PropertyRightProsecutionPicExtExample example);

    int count(PropertyRightProsecutionPicExtExample example);

    long sum(@Param("field") String field, @Param("example") PropertyRightProsecutionPicExtExample example);

    int max(@Param("field") String field, @Param("example") PropertyRightProsecutionPicExtExample example);

    int min(@Param("field") String field, @Param("example") PropertyRightProsecutionPicExtExample example);

}

