package com.jf.dao;

import com.jf.entity.ViolatePlatformRemarksPicExt;
import com.jf.entity.ViolatePlatformRemarksPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolatePlatformRemarksPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ViolatePlatformRemarksPicExt findById(int id);

    ViolatePlatformRemarksPicExt find(ViolatePlatformRemarksPicExtExample example);

    List<ViolatePlatformRemarksPicExt> list(ViolatePlatformRemarksPicExtExample example);

    List<Integer> listId(ViolatePlatformRemarksPicExtExample example);

    int count(ViolatePlatformRemarksPicExtExample example);

    long sum(@Param("field") String field, @Param("example") ViolatePlatformRemarksPicExtExample example);

    int max(@Param("field") String field, @Param("example") ViolatePlatformRemarksPicExtExample example);

    int min(@Param("field") String field, @Param("example") ViolatePlatformRemarksPicExtExample example);

}

