package com.jf.dao;

import com.jf.entity.MchtDepositExt;
import com.jf.entity.MchtDepositExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtDepositExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtDepositExt findById(int id);

    MchtDepositExt find(MchtDepositExtExample example);

    List<MchtDepositExt> list(MchtDepositExtExample example);

    List<Integer> listId(MchtDepositExtExample example);

    int count(MchtDepositExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtDepositExtExample example);

    int max(@Param("field") String field, @Param("example") MchtDepositExtExample example);

    int min(@Param("field") String field, @Param("example") MchtDepositExtExample example);

}

