package com.jf.dao;

import com.jf.entity.XiaonengCustomerServiceExt;
import com.jf.entity.XiaonengCustomerServiceExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XiaonengCustomerServiceExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    XiaonengCustomerServiceExt findById(int id);

    XiaonengCustomerServiceExt find(XiaonengCustomerServiceExtExample example);

    List<XiaonengCustomerServiceExt> list(XiaonengCustomerServiceExtExample example);

    List<Integer> listId(XiaonengCustomerServiceExtExample example);

    int count(XiaonengCustomerServiceExtExample example);

    long sum(@Param("field") String field, @Param("example") XiaonengCustomerServiceExtExample example);

    int max(@Param("field") String field, @Param("example") XiaonengCustomerServiceExtExample example);

    int min(@Param("field") String field, @Param("example") XiaonengCustomerServiceExtExample example);

}

