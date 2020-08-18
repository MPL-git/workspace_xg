package com.jf.dao;

import com.jf.entity.MchtBrandInspectionPicExt;
import com.jf.entity.MchtBrandInspectionPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandInspectionPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandInspectionPicExt findById(int id);

    MchtBrandInspectionPicExt find(MchtBrandInspectionPicExtExample example);

    List<MchtBrandInspectionPicExt> list(MchtBrandInspectionPicExtExample example);

    List<Integer> listId(MchtBrandInspectionPicExtExample example);

    int count(MchtBrandInspectionPicExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandInspectionPicExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandInspectionPicExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandInspectionPicExtExample example);

}

