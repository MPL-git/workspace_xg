package com.jf.dao;

import com.jf.entity.CustomerServiceLogExt;
import com.jf.entity.CustomerServiceLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CustomerServiceLogExt findById(int id);

    CustomerServiceLogExt find(CustomerServiceLogExtExample example);

    List<CustomerServiceLogExt> list(CustomerServiceLogExtExample example);

    List<Integer> listId(CustomerServiceLogExtExample example);

    int count(CustomerServiceLogExtExample example);

    long sum(@Param("field") String field, @Param("example") CustomerServiceLogExtExample example);

    int max(@Param("field") String field, @Param("example") CustomerServiceLogExtExample example);

    int min(@Param("field") String field, @Param("example") CustomerServiceLogExtExample example);

}

