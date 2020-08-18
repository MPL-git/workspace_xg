package com.jf.dao;

import com.jf.entity.ReportGdtLogExt;
import com.jf.entity.ReportGdtLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportGdtLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ReportGdtLogExt findById(int id);

    ReportGdtLogExt find(ReportGdtLogExtExample example);

    List<ReportGdtLogExt> list(ReportGdtLogExtExample example);

    List<Integer> listId(ReportGdtLogExtExample example);

    int count(ReportGdtLogExtExample example);

    long sum(@Param("field") String field, @Param("example") ReportGdtLogExtExample example);

    int max(@Param("field") String field, @Param("example") ReportGdtLogExtExample example);

    int min(@Param("field") String field, @Param("example") ReportGdtLogExtExample example);

}

