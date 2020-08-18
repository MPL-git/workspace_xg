package com.jf.dao;

import com.jf.entity.GradeWeightCnfExt;
import com.jf.entity.GradeWeightCnfExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeWeightCnfExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    GradeWeightCnfExt findById(int id);

    GradeWeightCnfExt find(GradeWeightCnfExtExample example);

    List<GradeWeightCnfExt> list(GradeWeightCnfExtExample example);

    List<Integer> listId(GradeWeightCnfExtExample example);

    int count(GradeWeightCnfExtExample example);

    long sum(@Param("field") String field, @Param("example") GradeWeightCnfExtExample example);

    int max(@Param("field") String field, @Param("example") GradeWeightCnfExtExample example);

    int min(@Param("field") String field, @Param("example") GradeWeightCnfExtExample example);

}

