package com.jf.dao;

import com.jf.entity.WithdrawCnfExt;
import com.jf.entity.WithdrawCnfExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawCnfExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WithdrawCnfExt findById(int id);

    WithdrawCnfExt find(WithdrawCnfExtExample example);

    List<WithdrawCnfExt> list(WithdrawCnfExtExample example);

    List<Integer> listId(WithdrawCnfExtExample example);

    int count(WithdrawCnfExtExample example);

    long sum(@Param("field") String field, @Param("example") WithdrawCnfExtExample example);

    int max(@Param("field") String field, @Param("example") WithdrawCnfExtExample example);

    int min(@Param("field") String field, @Param("example") WithdrawCnfExtExample example);

}

