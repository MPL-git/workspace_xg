package com.jf.dao;

import com.jf.entity.ContractRenewLogExt;
import com.jf.entity.ContractRenewLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRenewLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ContractRenewLogExt findById(int id);

    ContractRenewLogExt find(ContractRenewLogExtExample example);

    List<ContractRenewLogExt> list(ContractRenewLogExtExample example);

    List<Integer> listId(ContractRenewLogExtExample example);

    int count(ContractRenewLogExtExample example);

    long sum(@Param("field") String field, @Param("example") ContractRenewLogExtExample example);

    int max(@Param("field") String field, @Param("example") ContractRenewLogExtExample example);

    int min(@Param("field") String field, @Param("example") ContractRenewLogExtExample example);

}

