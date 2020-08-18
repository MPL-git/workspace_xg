package com.jf.dao;

import com.jf.entity.MchtContractPicExt;
import com.jf.entity.MchtContractPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtContractPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtContractPicExt findById(int id);

    MchtContractPicExt find(MchtContractPicExtExample example);

    List<MchtContractPicExt> list(MchtContractPicExtExample example);

    List<Integer> listId(MchtContractPicExtExample example);

    int count(MchtContractPicExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtContractPicExtExample example);

    int max(@Param("field") String field, @Param("example") MchtContractPicExtExample example);

    int min(@Param("field") String field, @Param("example") MchtContractPicExtExample example);

}

