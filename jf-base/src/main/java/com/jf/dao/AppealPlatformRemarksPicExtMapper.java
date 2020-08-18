package com.jf.dao;

import com.jf.entity.AppealPlatformRemarksPicExt;
import com.jf.entity.AppealPlatformRemarksPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppealPlatformRemarksPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AppealPlatformRemarksPicExt findById(int id);

    AppealPlatformRemarksPicExt find(AppealPlatformRemarksPicExtExample example);

    List<AppealPlatformRemarksPicExt> list(AppealPlatformRemarksPicExtExample example);

    List<Integer> listId(AppealPlatformRemarksPicExtExample example);

    int count(AppealPlatformRemarksPicExtExample example);

    long sum(@Param("field") String field, @Param("example") AppealPlatformRemarksPicExtExample example);

    int max(@Param("field") String field, @Param("example") AppealPlatformRemarksPicExtExample example);

    int min(@Param("field") String field, @Param("example") AppealPlatformRemarksPicExtExample example);

}

