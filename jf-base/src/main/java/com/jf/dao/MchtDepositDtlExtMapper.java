package com.jf.dao;

import com.jf.entity.MchtDepositDtlExt;
import com.jf.entity.MchtDepositDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtDepositDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtDepositDtlExt findById(int id);

    MchtDepositDtlExt find(MchtDepositDtlExtExample example);

    List<MchtDepositDtlExt> list(MchtDepositDtlExtExample example);

    List<Integer> listId(MchtDepositDtlExtExample example);

    int count(MchtDepositDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtDepositDtlExtExample example);

    int max(@Param("field") String field, @Param("example") MchtDepositDtlExtExample example);

    int min(@Param("field") String field, @Param("example") MchtDepositDtlExtExample example);

}

