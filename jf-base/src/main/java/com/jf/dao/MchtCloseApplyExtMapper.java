package com.jf.dao;

import com.jf.entity.MchtCloseApplyExt;
import com.jf.entity.MchtCloseApplyExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtCloseApplyExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtCloseApplyExt findById(int id);

    MchtCloseApplyExt find(MchtCloseApplyExtExample example);

    List<MchtCloseApplyExt> list(MchtCloseApplyExtExample example);

    List<Integer> listId(MchtCloseApplyExtExample example);

    int count(MchtCloseApplyExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtCloseApplyExtExample example);

    int max(@Param("field") String field, @Param("example") MchtCloseApplyExtExample example);

    int min(@Param("field") String field, @Param("example") MchtCloseApplyExtExample example);

}

