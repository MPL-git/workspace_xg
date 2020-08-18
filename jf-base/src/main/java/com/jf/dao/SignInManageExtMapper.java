package com.jf.dao;

import com.jf.entity.SignInManageExt;
import com.jf.entity.SignInManageExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignInManageExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SignInManageExt findById(int id);

    SignInManageExt find(SignInManageExtExample example);

    List<SignInManageExt> list(SignInManageExtExample example);

    List<Integer> listId(SignInManageExtExample example);

    int count(SignInManageExtExample example);

    long sum(@Param("field") String field, @Param("example") SignInManageExtExample example);

    int max(@Param("field") String field, @Param("example") SignInManageExtExample example);

    int min(@Param("field") String field, @Param("example") SignInManageExtExample example);

}

