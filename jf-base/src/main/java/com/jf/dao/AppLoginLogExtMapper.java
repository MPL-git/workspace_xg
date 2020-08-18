package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.AppLoginLogExt;
import com.jf.entity.AppLoginLogExtExample;

@Repository
public interface AppLoginLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AppLoginLogExt findById(int id);

    AppLoginLogExt find(AppLoginLogExtExample example);

    List<AppLoginLogExt> list(AppLoginLogExtExample example);

    List<Integer> listId(AppLoginLogExtExample example);

    int count(AppLoginLogExtExample example);

    long sum(@Param("field") String field, @Param("example") AppLoginLogExtExample example);

    int max(@Param("field") String field, @Param("example") AppLoginLogExtExample example);

    int min(@Param("field") String field, @Param("example") AppLoginLogExtExample example);

}

