package com.jf.dao;

import com.jf.entity.MemberMobileWeixinMapExt;
import com.jf.entity.MemberMobileWeixinMapExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMobileWeixinMapExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberMobileWeixinMapExt findById(int id);

    MemberMobileWeixinMapExt find(MemberMobileWeixinMapExtExample example);

    List<MemberMobileWeixinMapExt> list(MemberMobileWeixinMapExtExample example);

    List<Integer> listId(MemberMobileWeixinMapExtExample example);

    int count(MemberMobileWeixinMapExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberMobileWeixinMapExtExample example);

    int max(@Param("field") String field, @Param("example") MemberMobileWeixinMapExtExample example);

    int min(@Param("field") String field, @Param("example") MemberMobileWeixinMapExtExample example);

}

