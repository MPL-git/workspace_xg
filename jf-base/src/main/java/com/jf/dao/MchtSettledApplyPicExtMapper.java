package com.jf.dao;

import com.jf.entity.MchtSettledApplyPicExt;
import com.jf.entity.MchtSettledApplyPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtSettledApplyPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtSettledApplyPicExt findById(int id);

    MchtSettledApplyPicExt find(MchtSettledApplyPicExtExample example);

    List<MchtSettledApplyPicExt> list(MchtSettledApplyPicExtExample example);

    List<Integer> listId(MchtSettledApplyPicExtExample example);

    int count(MchtSettledApplyPicExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtSettledApplyPicExtExample example);

    int max(@Param("field") String field, @Param("example") MchtSettledApplyPicExtExample example);

    int min(@Param("field") String field, @Param("example") MchtSettledApplyPicExtExample example);

}

