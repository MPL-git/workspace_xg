package com.jf.dao;

import com.jf.entity.WeixinGzhMemberBindExt;
import com.jf.entity.WeixinGzhMemberBindExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinGzhMemberBindExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WeixinGzhMemberBindExt findById(int id);

    WeixinGzhMemberBindExt find(WeixinGzhMemberBindExtExample example);

    List<WeixinGzhMemberBindExt> list(WeixinGzhMemberBindExtExample example);

    List<Integer> listId(WeixinGzhMemberBindExtExample example);

    int count(WeixinGzhMemberBindExtExample example);

    long sum(@Param("field") String field, @Param("example") WeixinGzhMemberBindExtExample example);

    int max(@Param("field") String field, @Param("example") WeixinGzhMemberBindExtExample example);

    int min(@Param("field") String field, @Param("example") WeixinGzhMemberBindExtExample example);

}

