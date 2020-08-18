package com.jf.dao;

import com.jf.entity.DeliveryOvertimeCnfExt;
import com.jf.entity.DeliveryOvertimeCnfExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryOvertimeCnfExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DeliveryOvertimeCnfExt findById(int id);

    DeliveryOvertimeCnfExt find(DeliveryOvertimeCnfExtExample example);

    List<DeliveryOvertimeCnfExt> list(DeliveryOvertimeCnfExtExample example);

    List<Integer> listId(DeliveryOvertimeCnfExtExample example);

    int count(DeliveryOvertimeCnfExtExample example);

    long sum(@Param("field") String field, @Param("example") DeliveryOvertimeCnfExtExample example);

    int max(@Param("field") String field, @Param("example") DeliveryOvertimeCnfExtExample example);

    int min(@Param("field") String field, @Param("example") DeliveryOvertimeCnfExtExample example);

}

