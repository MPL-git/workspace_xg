package com.jf.dao;

import com.jf.entity.SupplementarySignInSettingExt;
import com.jf.entity.SupplementarySignInSettingExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplementarySignInSettingExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SupplementarySignInSettingExt findById(int id);

    SupplementarySignInSettingExt find(SupplementarySignInSettingExtExample example);

    List<SupplementarySignInSettingExt> list(SupplementarySignInSettingExtExample example);

    List<Integer> listId(SupplementarySignInSettingExtExample example);

    int count(SupplementarySignInSettingExtExample example);

    long sum(@Param("field") String field, @Param("example") SupplementarySignInSettingExtExample example);

    int max(@Param("field") String field, @Param("example") SupplementarySignInSettingExtExample example);

    int min(@Param("field") String field, @Param("example") SupplementarySignInSettingExtExample example);

}

