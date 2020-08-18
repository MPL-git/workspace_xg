package com.jf.dao;

import com.jf.entity.DouyinMemberBindExt;
import com.jf.entity.DouyinMemberBindExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DouyinMemberBindExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    DouyinMemberBindExt findById(int id);

    DouyinMemberBindExt find(DouyinMemberBindExtExample example);

    List<DouyinMemberBindExt> list(DouyinMemberBindExtExample example);

    List<Integer> listId(DouyinMemberBindExtExample example);

    int count(DouyinMemberBindExtExample example);

    long sum(@Param("field") String field, @Param("example") DouyinMemberBindExtExample example);

    int max(@Param("field") String field, @Param("example") DouyinMemberBindExtExample example);

    int min(@Param("field") String field, @Param("example") DouyinMemberBindExtExample example);

}

