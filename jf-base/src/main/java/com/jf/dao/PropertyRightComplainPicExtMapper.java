package com.jf.dao;

import com.jf.entity.PropertyRightComplainPicExt;
import com.jf.entity.PropertyRightComplainPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRightComplainPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PropertyRightComplainPicExt findById(int id);

    PropertyRightComplainPicExt find(PropertyRightComplainPicExtExample example);

    List<PropertyRightComplainPicExt> list(PropertyRightComplainPicExtExample example);

    List<Integer> listId(PropertyRightComplainPicExtExample example);

    int count(PropertyRightComplainPicExtExample example);

    long sum(@Param("field") String field, @Param("example") PropertyRightComplainPicExtExample example);

    int max(@Param("field") String field, @Param("example") PropertyRightComplainPicExtExample example);

    int min(@Param("field") String field, @Param("example") PropertyRightComplainPicExtExample example);

}

