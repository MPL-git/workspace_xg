package com.jf.dao;

import com.jf.entity.BusinessCirclesAppealOrderExt;
import com.jf.entity.BusinessCirclesAppealOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessCirclesAppealOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    BusinessCirclesAppealOrderExt findById(int id);

    BusinessCirclesAppealOrderExt find(BusinessCirclesAppealOrderExtExample example);

    List<BusinessCirclesAppealOrderExt> list(BusinessCirclesAppealOrderExtExample example);

    List<Integer> listId(BusinessCirclesAppealOrderExtExample example);

    int count(BusinessCirclesAppealOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") BusinessCirclesAppealOrderExtExample example);

    int max(@Param("field") String field, @Param("example") BusinessCirclesAppealOrderExtExample example);

    int min(@Param("field") String field, @Param("example") BusinessCirclesAppealOrderExtExample example);

}

