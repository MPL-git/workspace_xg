package com.jf.dao;

import com.jf.entity.SignInCnfDtlExt;
import com.jf.entity.SignInCnfDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignInCnfDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SignInCnfDtlExt findById(int id);

    SignInCnfDtlExt find(SignInCnfDtlExtExample example);

    List<SignInCnfDtlExt> list(SignInCnfDtlExtExample example);

    List<Integer> listId(SignInCnfDtlExtExample example);

    int count(SignInCnfDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") SignInCnfDtlExtExample example);

    int max(@Param("field") String field, @Param("example") SignInCnfDtlExtExample example);

    int min(@Param("field") String field, @Param("example") SignInCnfDtlExtExample example);

}

