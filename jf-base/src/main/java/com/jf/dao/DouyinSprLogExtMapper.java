package com.jf.dao;

import com.jf.entity.DouyinSprLogExt;
import com.jf.entity.DouyinSprLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DouyinSprLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DouyinSprLogExt findById(int id);

    DouyinSprLogExt find(DouyinSprLogExtExample example);

    List<DouyinSprLogExt> list(DouyinSprLogExtExample example);

    List<Integer> listId(DouyinSprLogExtExample example);

    int count(DouyinSprLogExtExample example);

    long sum(@Param("field") String field, @Param("example") DouyinSprLogExtExample example);

    int max(@Param("field") String field, @Param("example") DouyinSprLogExtExample example);

    int min(@Param("field") String field, @Param("example") DouyinSprLogExtExample example);

}

