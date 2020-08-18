package com.jf.dao;

import com.jf.entity.CumulativeSignInSettingExt;
import com.jf.entity.CumulativeSignInSettingExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CumulativeSignInSettingExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    CumulativeSignInSettingExt findById(int id);

    CumulativeSignInSettingExt find(CumulativeSignInSettingExtExample example);

    List<CumulativeSignInSettingExt> list(CumulativeSignInSettingExtExample example);

    List<Integer> listId(CumulativeSignInSettingExtExample example);

    int count(CumulativeSignInSettingExtExample example);

    long sum(@Param("field") String field, @Param("example") CumulativeSignInSettingExtExample example);

    int max(@Param("field") String field, @Param("example") CumulativeSignInSettingExtExample example);

    int min(@Param("field") String field, @Param("example") CumulativeSignInSettingExtExample example);

}

