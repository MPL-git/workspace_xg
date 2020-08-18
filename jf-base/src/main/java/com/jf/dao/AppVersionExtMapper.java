package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.AppVersionExt;
import com.jf.entity.AppVersionExtExample;

@Repository
public interface AppVersionExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AppVersionExt findById(int id);

    AppVersionExt find(AppVersionExtExample example);

    List<AppVersionExt> list(AppVersionExtExample example);

    List<Integer> listId(AppVersionExtExample example);

    int count(AppVersionExtExample example);

    long sum(@Param("field") String field, @Param("example") AppVersionExtExample example);

    int max(@Param("field") String field, @Param("example") AppVersionExtExample example);

    int min(@Param("field") String field, @Param("example") AppVersionExtExample example);

}

