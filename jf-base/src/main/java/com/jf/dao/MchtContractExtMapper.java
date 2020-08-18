package com.jf.dao;

import com.jf.entity.MchtContractExt;
import com.jf.entity.MchtContractExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtContractExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtContractExt findById(int id);

    MchtContractExt find(MchtContractExtExample example);

    List<MchtContractExt> list(MchtContractExtExample example);

    List<Integer> listId(MchtContractExtExample example);

    int count(MchtContractExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtContractExtExample example);

    int max(@Param("field") String field, @Param("example") MchtContractExtExample example);

    int min(@Param("field") String field, @Param("example") MchtContractExtExample example);

}

