package com.jf.dao;

import com.jf.entity.AppealLogPicExt;
import com.jf.entity.AppealLogPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppealLogPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AppealLogPicExt findById(int id);

    AppealLogPicExt find(AppealLogPicExtExample example);

    List<AppealLogPicExt> list(AppealLogPicExtExample example);

    List<Integer> listId(AppealLogPicExtExample example);

    int count(AppealLogPicExtExample example);

    long sum(@Param("field") String field, @Param("example") AppealLogPicExtExample example);

    int max(@Param("field") String field, @Param("example") AppealLogPicExtExample example);

    int min(@Param("field") String field, @Param("example") AppealLogPicExtExample example);

}

