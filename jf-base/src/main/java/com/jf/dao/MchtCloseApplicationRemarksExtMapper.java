package com.jf.dao;

import com.jf.entity.MchtCloseApplicationRemarksExt;
import com.jf.entity.MchtCloseApplicationRemarksExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtCloseApplicationRemarksExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtCloseApplicationRemarksExt findById(int id);

    MchtCloseApplicationRemarksExt find(MchtCloseApplicationRemarksExtExample example);

    List<MchtCloseApplicationRemarksExt> list(MchtCloseApplicationRemarksExtExample example);

    List<Integer> listId(MchtCloseApplicationRemarksExtExample example);

    int count(MchtCloseApplicationRemarksExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtCloseApplicationRemarksExtExample example);

    int max(@Param("field") String field, @Param("example") MchtCloseApplicationRemarksExtExample example);

    int min(@Param("field") String field, @Param("example") MchtCloseApplicationRemarksExtExample example);

}

