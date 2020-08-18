package com.jf.dao;

import com.jf.entity.ViolateComplainOrderExt;
import com.jf.entity.ViolateComplainOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolateComplainOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ViolateComplainOrderExt findById(int id);

    ViolateComplainOrderExt find(ViolateComplainOrderExtExample example);

    List<ViolateComplainOrderExt> list(ViolateComplainOrderExtExample example);

    List<Integer> listId(ViolateComplainOrderExtExample example);

    int count(ViolateComplainOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") ViolateComplainOrderExtExample example);

    int max(@Param("field") String field, @Param("example") ViolateComplainOrderExtExample example);

    int min(@Param("field") String field, @Param("example") ViolateComplainOrderExtExample example);

}

