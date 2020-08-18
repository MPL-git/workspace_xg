package com.jf.dao;

import com.jf.entity.SobotCustomerServiceExt;
import com.jf.entity.SobotCustomerServiceExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SobotCustomerServiceExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SobotCustomerServiceExt findById(int id);

    SobotCustomerServiceExt find(SobotCustomerServiceExtExample example);

    List<SobotCustomerServiceExt> list(SobotCustomerServiceExtExample example);

    List<Integer> listId(SobotCustomerServiceExtExample example);

    int count(SobotCustomerServiceExtExample example);

    long sum(@Param("field") String field, @Param("example") SobotCustomerServiceExtExample example);

    int max(@Param("field") String field, @Param("example") SobotCustomerServiceExtExample example);

    int min(@Param("field") String field, @Param("example") SobotCustomerServiceExtExample example);

}

