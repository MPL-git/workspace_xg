package com.jf.dao;

import com.jf.entity.CustomerServicePicExt;
import com.jf.entity.CustomerServicePicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServicePicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CustomerServicePicExt findById(int id);

    CustomerServicePicExt find(CustomerServicePicExtExample example);

    List<CustomerServicePicExt> list(CustomerServicePicExtExample example);

    List<Integer> listId(CustomerServicePicExtExample example);

    int count(CustomerServicePicExtExample example);

    long sum(@Param("field") String field, @Param("example") CustomerServicePicExtExample example);

    int max(@Param("field") String field, @Param("example") CustomerServicePicExtExample example);

    int min(@Param("field") String field, @Param("example") CustomerServicePicExtExample example);

}

