package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.MobileVerificationCodeExt;
import com.jf.entity.MobileVerificationCodeExtExample;

@Repository
public interface MobileVerificationCodeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MobileVerificationCodeExt findById(int id);

    MobileVerificationCodeExt find(MobileVerificationCodeExtExample example);

    List<MobileVerificationCodeExt> list(MobileVerificationCodeExtExample example);

    List<Integer> listId(MobileVerificationCodeExtExample example);

    int count(MobileVerificationCodeExtExample example);

    long sum(@Param("field") String field, @Param("example") MobileVerificationCodeExtExample example);

    int max(@Param("field") String field, @Param("example") MobileVerificationCodeExtExample example);

    int min(@Param("field") String field, @Param("example") MobileVerificationCodeExtExample example);

}

