package com.jf.dao;

import com.jf.entity.MemberSignInSettingExt;
import com.jf.entity.MemberSignInSettingExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberSignInSettingExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberSignInSettingExt findById(int id);

    MemberSignInSettingExt find(MemberSignInSettingExtExample example);

    List<MemberSignInSettingExt> list(MemberSignInSettingExtExample example);

    List<Integer> listId(MemberSignInSettingExtExample example);

    int count(MemberSignInSettingExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberSignInSettingExtExample example);

    int max(@Param("field") String field, @Param("example") MemberSignInSettingExtExample example);

    int min(@Param("field") String field, @Param("example") MemberSignInSettingExtExample example);

}

