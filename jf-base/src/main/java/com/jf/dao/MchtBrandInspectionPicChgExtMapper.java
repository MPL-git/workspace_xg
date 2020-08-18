package com.jf.dao;

import com.jf.entity.MchtBrandInspectionPicChgExt;
import com.jf.entity.MchtBrandInspectionPicChgExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandInspectionPicChgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandInspectionPicChgExt findById(int id);

    MchtBrandInspectionPicChgExt find(MchtBrandInspectionPicChgExtExample example);

    List<MchtBrandInspectionPicChgExt> list(MchtBrandInspectionPicChgExtExample example);

    List<Integer> listId(MchtBrandInspectionPicChgExtExample example);

    int count(MchtBrandInspectionPicChgExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandInspectionPicChgExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandInspectionPicChgExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandInspectionPicChgExtExample example);

}

