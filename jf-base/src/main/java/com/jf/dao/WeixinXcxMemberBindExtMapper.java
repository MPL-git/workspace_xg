package com.jf.dao;

import com.jf.entity.WeixinXcxMemberBindExt;
import com.jf.entity.WeixinXcxMemberBindExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeixinXcxMemberBindExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    WeixinXcxMemberBindExt findById(int id);

    WeixinXcxMemberBindExt find(WeixinXcxMemberBindExtExample example);

    List<WeixinXcxMemberBindExt> list(WeixinXcxMemberBindExtExample example);

    List<Integer> listId(WeixinXcxMemberBindExtExample example);

    int count(WeixinXcxMemberBindExtExample example);

    long sum(@Param("field") String field, @Param("example") WeixinXcxMemberBindExtExample example);

    int max(@Param("field") String field, @Param("example") WeixinXcxMemberBindExtExample example);

    int min(@Param("field") String field, @Param("example") WeixinXcxMemberBindExtExample example);

}

