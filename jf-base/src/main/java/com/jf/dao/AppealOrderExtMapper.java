package com.jf.dao;

import com.jf.entity.AppealOrderExt;
import com.jf.entity.AppealOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppealOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AppealOrderExt findById(int id);

    AppealOrderExt find(AppealOrderExtExample example);

    List<AppealOrderExt> list(AppealOrderExtExample example);

    List<Integer> listId(AppealOrderExtExample example);

    int count(AppealOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") AppealOrderExtExample example);

    int max(@Param("field") String field, @Param("example") AppealOrderExtExample example);

    int min(@Param("field") String field, @Param("example") AppealOrderExtExample example);

}

