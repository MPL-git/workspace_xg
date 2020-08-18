package com.jf.dao;

import com.jf.entity.MchtBankAccountExt;
import com.jf.entity.MchtBankAccountExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBankAccountExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBankAccountExt findById(int id);

    MchtBankAccountExt find(MchtBankAccountExtExample example);

    List<MchtBankAccountExt> list(MchtBankAccountExtExample example);

    List<Integer> listId(MchtBankAccountExtExample example);

    int count(MchtBankAccountExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBankAccountExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBankAccountExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBankAccountExtExample example);

}

