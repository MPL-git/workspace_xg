package com.jf.dao;

import com.jf.entity.PropertyRightComplainLogExt;
import com.jf.entity.PropertyRightComplainLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRightComplainLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PropertyRightComplainLogExt findById(int id);

    PropertyRightComplainLogExt find(PropertyRightComplainLogExtExample example);

    List<PropertyRightComplainLogExt> list(PropertyRightComplainLogExtExample example);

    List<Integer> listId(PropertyRightComplainLogExtExample example);

    int count(PropertyRightComplainLogExtExample example);

    long sum(@Param("field") String field, @Param("example") PropertyRightComplainLogExtExample example);

    int max(@Param("field") String field, @Param("example") PropertyRightComplainLogExtExample example);

    int min(@Param("field") String field, @Param("example") PropertyRightComplainLogExtExample example);

}

