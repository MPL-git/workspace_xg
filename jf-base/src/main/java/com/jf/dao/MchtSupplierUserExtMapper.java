package com.jf.dao;

import com.jf.entity.MchtSupplierUserExt;
import com.jf.entity.MchtSupplierUserExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtSupplierUserExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtSupplierUserExt findById(int id);

    MchtSupplierUserExt find(MchtSupplierUserExtExample example);

    List<MchtSupplierUserExt> list(MchtSupplierUserExtExample example);

    List<Integer> listId(MchtSupplierUserExtExample example);

    int count(MchtSupplierUserExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtSupplierUserExtExample example);

    int max(@Param("field") String field, @Param("example") MchtSupplierUserExtExample example);

    int min(@Param("field") String field, @Param("example") MchtSupplierUserExtExample example);

}

