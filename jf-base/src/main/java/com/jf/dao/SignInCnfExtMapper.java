package com.jf.dao;

import com.jf.entity.SignInCnfExt;
import com.jf.entity.SignInCnfExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignInCnfExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SignInCnfExt findById(int id);

    SignInCnfExt find(SignInCnfExtExample example);

    List<SignInCnfExt> list(SignInCnfExtExample example);

    List<Integer> listId(SignInCnfExtExample example);

    int count(SignInCnfExtExample example);

    long sum(@Param("field") String field, @Param("example") SignInCnfExtExample example);

    int max(@Param("field") String field, @Param("example") SignInCnfExtExample example);

    int min(@Param("field") String field, @Param("example") SignInCnfExtExample example);

}

