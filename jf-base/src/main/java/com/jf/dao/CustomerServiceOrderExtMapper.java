package com.jf.dao;

import com.jf.entity.CustomerServiceOrderExt;
import com.jf.entity.CustomerServiceOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CustomerServiceOrderExt findById(int id);

    CustomerServiceOrderExt find(CustomerServiceOrderExtExample example);

    List<CustomerServiceOrderExt> list(CustomerServiceOrderExtExample example);

    List<Integer> listId(CustomerServiceOrderExtExample example);

    int count(CustomerServiceOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") CustomerServiceOrderExtExample example);

    int max(@Param("field") String field, @Param("example") CustomerServiceOrderExtExample example);

    int min(@Param("field") String field, @Param("example") CustomerServiceOrderExtExample example);

}

