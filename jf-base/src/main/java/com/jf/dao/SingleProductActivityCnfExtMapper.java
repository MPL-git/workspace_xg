package com.jf.dao;

import com.jf.entity.SingleProductActivityCnfExt;
import com.jf.entity.SingleProductActivityCnfExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleProductActivityCnfExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SingleProductActivityCnfExt findById(int id);

    SingleProductActivityCnfExt find(SingleProductActivityCnfExtExample example);

    List<SingleProductActivityCnfExt> list(SingleProductActivityCnfExtExample example);

    List<Integer> listId(SingleProductActivityCnfExtExample example);

    int count(SingleProductActivityCnfExtExample example);

    long sum(@Param("field") String field, @Param("example") SingleProductActivityCnfExtExample example);

    int max(@Param("field") String field, @Param("example") SingleProductActivityCnfExtExample example);

    int min(@Param("field") String field, @Param("example") SingleProductActivityCnfExtExample example);

}

