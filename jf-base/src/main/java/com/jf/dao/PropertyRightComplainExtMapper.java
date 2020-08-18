package com.jf.dao;

import com.jf.entity.PropertyRightComplainExt;
import com.jf.entity.PropertyRightComplainExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRightComplainExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PropertyRightComplainExt findById(int id);

    PropertyRightComplainExt find(PropertyRightComplainExtExample example);

    List<PropertyRightComplainExt> list(PropertyRightComplainExtExample example);

    List<Integer> listId(PropertyRightComplainExtExample example);

    int count(PropertyRightComplainExtExample example);

    long sum(@Param("field") String field, @Param("example") PropertyRightComplainExtExample example);

    int max(@Param("field") String field, @Param("example") PropertyRightComplainExtExample example);

    int min(@Param("field") String field, @Param("example") PropertyRightComplainExtExample example);

}

