package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.AppAccessTokenExt;
import com.jf.entity.AppAccessTokenExtExample;

@Repository
public interface AppAccessTokenExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AppAccessTokenExt findById(int id);

    AppAccessTokenExt find(AppAccessTokenExtExample example);

    List<AppAccessTokenExt> list(AppAccessTokenExtExample example);

    List<Integer> listId(AppAccessTokenExtExample example);

    int count(AppAccessTokenExtExample example);

    long sum(@Param("field") String field, @Param("example") AppAccessTokenExtExample example);

    int max(@Param("field") String field, @Param("example") AppAccessTokenExtExample example);

    int min(@Param("field") String field, @Param("example") AppAccessTokenExtExample example);

}

