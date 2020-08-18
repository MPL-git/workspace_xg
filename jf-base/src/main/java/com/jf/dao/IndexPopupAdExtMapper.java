package com.jf.dao;

import com.jf.entity.IndexPopupAdExt;
import com.jf.entity.IndexPopupAdExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexPopupAdExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    IndexPopupAdExt findById(int id);

    IndexPopupAdExt find(IndexPopupAdExtExample example);

    List<IndexPopupAdExt> list(IndexPopupAdExtExample example);

    List<Integer> listId(IndexPopupAdExtExample example);

    int count(IndexPopupAdExtExample example);

    long sum(@Param("field") String field, @Param("example") IndexPopupAdExtExample example);

    int max(@Param("field") String field, @Param("example") IndexPopupAdExtExample example);

    int min(@Param("field") String field, @Param("example") IndexPopupAdExtExample example);

}

