package com.jf.dao;

import com.jf.entity.MchtSettleCompareExt;
import com.jf.entity.MchtSettleCompareExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtSettleCompareExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtSettleCompareExt findById(int id);

    MchtSettleCompareExt find(MchtSettleCompareExtExample example);

    List<MchtSettleCompareExt> list(MchtSettleCompareExtExample example);

    List<Integer> listId(MchtSettleCompareExtExample example);

    int count(MchtSettleCompareExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtSettleCompareExtExample example);

    int max(@Param("field") String field, @Param("example") MchtSettleCompareExtExample example);

    int min(@Param("field") String field, @Param("example") MchtSettleCompareExtExample example);

}

