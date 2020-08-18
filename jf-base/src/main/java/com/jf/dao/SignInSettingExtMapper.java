package com.jf.dao;

import com.jf.entity.SignInSettingExt;
import com.jf.entity.SignInSettingExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignInSettingExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SignInSettingExt findById(int id);

    SignInSettingExt find(SignInSettingExtExample example);

    List<SignInSettingExt> list(SignInSettingExtExample example);

    List<Integer> listId(SignInSettingExtExample example);

    int count(SignInSettingExtExample example);

    long sum(@Param("field") String field, @Param("example") SignInSettingExtExample example);

    int max(@Param("field") String field, @Param("example") SignInSettingExtExample example);

    int min(@Param("field") String field, @Param("example") SignInSettingExtExample example);

}

