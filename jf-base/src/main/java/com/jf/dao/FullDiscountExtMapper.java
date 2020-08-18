package com.jf.dao;

import com.jf.entity.FullDiscountExt;
import com.jf.entity.FullDiscountExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FullDiscountExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    FullDiscountExt findById(int id);

    FullDiscountExt find(FullDiscountExtExample example);

    List<FullDiscountExt> list(FullDiscountExtExample example);

    List<Integer> listId(FullDiscountExtExample example);

    int count(FullDiscountExtExample example);

    long sum(@Param("field") String field, @Param("example") FullDiscountExtExample example);

    int max(@Param("field") String field, @Param("example") FullDiscountExtExample example);

    int min(@Param("field") String field, @Param("example") FullDiscountExtExample example);

}

