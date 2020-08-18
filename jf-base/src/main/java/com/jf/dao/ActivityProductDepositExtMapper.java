package com.jf.dao;

import com.jf.entity.ActivityProductDepositExt;
import com.jf.entity.ActivityProductDepositExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityProductDepositExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityProductDepositExt findById(int id);

    ActivityProductDepositExt find(ActivityProductDepositExtExample example);

    List<ActivityProductDepositExt> list(ActivityProductDepositExtExample example);

    List<Integer> listId(ActivityProductDepositExtExample example);

    int count(ActivityProductDepositExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityProductDepositExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityProductDepositExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityProductDepositExtExample example);

}

