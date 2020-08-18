package com.jf.dao;

import com.jf.entity.PayToMchtLogExt;
import com.jf.entity.PayToMchtLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayToMchtLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PayToMchtLogExt findById(int id);

    PayToMchtLogExt find(PayToMchtLogExtExample example);

    List<PayToMchtLogExt> list(PayToMchtLogExtExample example);

    List<Integer> listId(PayToMchtLogExtExample example);

    int count(PayToMchtLogExtExample example);

    long sum(@Param("field") String field, @Param("example") PayToMchtLogExtExample example);

    int max(@Param("field") String field, @Param("example") PayToMchtLogExtExample example);

    int min(@Param("field") String field, @Param("example") PayToMchtLogExtExample example);

}

