package com.jf.dao;

import com.jf.entity.MchtSettleOrderExt;
import com.jf.entity.MchtSettleOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtSettleOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtSettleOrderExt findById(int id);

    MchtSettleOrderExt find(MchtSettleOrderExtExample example);

    List<MchtSettleOrderExt> list(MchtSettleOrderExtExample example);

    List<Integer> listId(MchtSettleOrderExtExample example);

    int count(MchtSettleOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtSettleOrderExtExample example);

    int max(@Param("field") String field, @Param("example") MchtSettleOrderExtExample example);

    int min(@Param("field") String field, @Param("example") MchtSettleOrderExtExample example);

}

