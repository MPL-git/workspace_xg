package com.jf.dao;

import com.jf.entity.CustomerServiceRecordsExt;
import com.jf.entity.CustomerServiceRecordsExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceRecordsExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CustomerServiceRecordsExt findById(int id);

    CustomerServiceRecordsExt find(CustomerServiceRecordsExtExample example);

    List<CustomerServiceRecordsExt> list(CustomerServiceRecordsExtExample example);

    List<Integer> listId(CustomerServiceRecordsExtExample example);

    int count(CustomerServiceRecordsExtExample example);

    long sum(@Param("field") String field, @Param("example") CustomerServiceRecordsExtExample example);

    int max(@Param("field") String field, @Param("example") CustomerServiceRecordsExtExample example);

    int min(@Param("field") String field, @Param("example") CustomerServiceRecordsExtExample example);

}

