package com.jf.dao;

import com.jf.entity.CustomerServiceStatusLogExt;
import com.jf.entity.CustomerServiceStatusLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceStatusLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CustomerServiceStatusLogExt findById(int id);

    CustomerServiceStatusLogExt find(CustomerServiceStatusLogExtExample example);

    List<CustomerServiceStatusLogExt> list(CustomerServiceStatusLogExtExample example);

    List<Integer> listId(CustomerServiceStatusLogExtExample example);

    int count(CustomerServiceStatusLogExtExample example);

    long sum(@Param("field") String field, @Param("example") CustomerServiceStatusLogExtExample example);

    int max(@Param("field") String field, @Param("example") CustomerServiceStatusLogExtExample example);

    int min(@Param("field") String field, @Param("example") CustomerServiceStatusLogExtExample example);

}

