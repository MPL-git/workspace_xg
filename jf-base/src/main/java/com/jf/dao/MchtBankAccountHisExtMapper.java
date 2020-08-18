package com.jf.dao;

import com.jf.entity.MchtBankAccountHisExt;
import com.jf.entity.MchtBankAccountHisExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBankAccountHisExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBankAccountHisExt findById(int id);

    MchtBankAccountHisExt find(MchtBankAccountHisExtExample example);

    List<MchtBankAccountHisExt> list(MchtBankAccountHisExtExample example);

    List<Integer> listId(MchtBankAccountHisExtExample example);

    int count(MchtBankAccountHisExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBankAccountHisExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBankAccountHisExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBankAccountHisExtExample example);

}

