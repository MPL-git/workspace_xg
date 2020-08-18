package com.jf.dao;

import com.jf.entity.ObligeePicExt;
import com.jf.entity.ObligeePicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObligeePicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ObligeePicExt findById(int id);

    ObligeePicExt find(ObligeePicExtExample example);

    List<ObligeePicExt> list(ObligeePicExtExample example);

    List<Integer> listId(ObligeePicExtExample example);

    int count(ObligeePicExtExample example);

    long sum(@Param("field") String field, @Param("example") ObligeePicExtExample example);

    int max(@Param("field") String field, @Param("example") ObligeePicExtExample example);

    int min(@Param("field") String field, @Param("example") ObligeePicExtExample example);

}

