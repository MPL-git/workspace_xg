package com.jf.dao;

import com.jf.entity.PropertyRightAppealLogExt;
import com.jf.entity.PropertyRightAppealLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRightAppealLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PropertyRightAppealLogExt findById(int id);

    PropertyRightAppealLogExt find(PropertyRightAppealLogExtExample example);

    List<PropertyRightAppealLogExt> list(PropertyRightAppealLogExtExample example);

    List<Integer> listId(PropertyRightAppealLogExtExample example);

    int count(PropertyRightAppealLogExtExample example);

    long sum(@Param("field") String field, @Param("example") PropertyRightAppealLogExtExample example);

    int max(@Param("field") String field, @Param("example") PropertyRightAppealLogExtExample example);

    int min(@Param("field") String field, @Param("example") PropertyRightAppealLogExtExample example);

}

