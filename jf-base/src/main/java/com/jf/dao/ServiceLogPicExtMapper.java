package com.jf.dao;

import com.jf.entity.ServiceLogPicExt;
import com.jf.entity.ServiceLogPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceLogPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ServiceLogPicExt findById(int id);

    ServiceLogPicExt find(ServiceLogPicExtExample example);

    List<ServiceLogPicExt> list(ServiceLogPicExtExample example);

    List<Integer> listId(ServiceLogPicExtExample example);

    int count(ServiceLogPicExtExample example);

    long sum(@Param("field") String field, @Param("example") ServiceLogPicExtExample example);

    int max(@Param("field") String field, @Param("example") ServiceLogPicExtExample example);

    int min(@Param("field") String field, @Param("example") ServiceLogPicExtExample example);

}

