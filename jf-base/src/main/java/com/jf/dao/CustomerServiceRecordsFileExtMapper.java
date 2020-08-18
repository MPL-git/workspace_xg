package com.jf.dao;

import com.jf.entity.CustomerServiceRecordsFileExt;
import com.jf.entity.CustomerServiceRecordsFileExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceRecordsFileExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CustomerServiceRecordsFileExt findById(int id);

    CustomerServiceRecordsFileExt find(CustomerServiceRecordsFileExtExample example);

    List<CustomerServiceRecordsFileExt> list(CustomerServiceRecordsFileExtExample example);

    List<Integer> listId(CustomerServiceRecordsFileExtExample example);

    int count(CustomerServiceRecordsFileExtExample example);

    long sum(@Param("field") String field, @Param("example") CustomerServiceRecordsFileExtExample example);

    int max(@Param("field") String field, @Param("example") CustomerServiceRecordsFileExtExample example);

    int min(@Param("field") String field, @Param("example") CustomerServiceRecordsFileExtExample example);

}

