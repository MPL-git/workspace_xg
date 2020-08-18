package com.jf.dao;

import com.jf.entity.BusinessCirclesAppealOrderPicExt;
import com.jf.entity.BusinessCirclesAppealOrderPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessCirclesAppealOrderPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    BusinessCirclesAppealOrderPicExt findById(int id);

    BusinessCirclesAppealOrderPicExt find(BusinessCirclesAppealOrderPicExtExample example);

    List<BusinessCirclesAppealOrderPicExt> list(BusinessCirclesAppealOrderPicExtExample example);

    List<Integer> listId(BusinessCirclesAppealOrderPicExtExample example);

    int count(BusinessCirclesAppealOrderPicExtExample example);

    long sum(@Param("field") String field, @Param("example") BusinessCirclesAppealOrderPicExtExample example);

    int max(@Param("field") String field, @Param("example") BusinessCirclesAppealOrderPicExtExample example);

    int min(@Param("field") String field, @Param("example") BusinessCirclesAppealOrderPicExtExample example);

}

