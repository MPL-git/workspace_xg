package com.jf.dao;

import com.jf.entity.BankBranchExt;
import com.jf.entity.BankBranchExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankBranchExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    BankBranchExt findById(int id);

    BankBranchExt find(BankBranchExtExample example);

    List<BankBranchExt> list(BankBranchExtExample example);

    List<Integer> listId(BankBranchExtExample example);

    int count(BankBranchExtExample example);

    long sum(@Param("field") String field, @Param("example") BankBranchExtExample example);

    int max(@Param("field") String field, @Param("example") BankBranchExtExample example);

    int min(@Param("field") String field, @Param("example") BankBranchExtExample example);

}

