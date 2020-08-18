package com.jf.dao;

import com.jf.entity.PropertyRightAppealPicExt;
import com.jf.entity.PropertyRightAppealPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRightAppealPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PropertyRightAppealPicExt findById(int id);

    PropertyRightAppealPicExt find(PropertyRightAppealPicExtExample example);

    List<PropertyRightAppealPicExt> list(PropertyRightAppealPicExtExample example);

    List<Integer> listId(PropertyRightAppealPicExtExample example);

    int count(PropertyRightAppealPicExtExample example);

    long sum(@Param("field") String field, @Param("example") PropertyRightAppealPicExtExample example);

    int max(@Param("field") String field, @Param("example") PropertyRightAppealPicExtExample example);

    int min(@Param("field") String field, @Param("example") PropertyRightAppealPicExtExample example);

}

