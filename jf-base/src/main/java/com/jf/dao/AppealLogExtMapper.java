package com.jf.dao;

import com.jf.entity.AppealLogExt;
import com.jf.entity.AppealLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppealLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AppealLogExt findById(int id);

    AppealLogExt find(AppealLogExtExample example);

    List<AppealLogExt> list(AppealLogExtExample example);

    List<Integer> listId(AppealLogExtExample example);

    int count(AppealLogExtExample example);

    long sum(@Param("field") String field, @Param("example") AppealLogExtExample example);

    int max(@Param("field") String field, @Param("example") AppealLogExtExample example);

    int min(@Param("field") String field, @Param("example") AppealLogExtExample example);

}

