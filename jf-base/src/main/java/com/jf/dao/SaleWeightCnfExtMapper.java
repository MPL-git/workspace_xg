package com.jf.dao;

import com.jf.entity.SaleWeightCnfExt;
import com.jf.entity.SaleWeightCnfExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleWeightCnfExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SaleWeightCnfExt findById(int id);

    SaleWeightCnfExt find(SaleWeightCnfExtExample example);

    List<SaleWeightCnfExt> list(SaleWeightCnfExtExample example);

    List<Integer> listId(SaleWeightCnfExtExample example);

    int count(SaleWeightCnfExtExample example);

    long sum(@Param("field") String field, @Param("example") SaleWeightCnfExtExample example);

    int max(@Param("field") String field, @Param("example") SaleWeightCnfExtExample example);

    int min(@Param("field") String field, @Param("example") SaleWeightCnfExtExample example);

}

