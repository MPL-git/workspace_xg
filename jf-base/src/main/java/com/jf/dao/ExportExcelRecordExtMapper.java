package com.jf.dao;

import com.jf.entity.ExportExcelRecordExt;
import com.jf.entity.ExportExcelRecordExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportExcelRecordExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ExportExcelRecordExt findById(int id);

    ExportExcelRecordExt find(ExportExcelRecordExtExample example);

    List<ExportExcelRecordExt> list(ExportExcelRecordExtExample example);

    List<Integer> listId(ExportExcelRecordExtExample example);

    int count(ExportExcelRecordExtExample example);

    long sum(@Param("field") String field, @Param("example") ExportExcelRecordExtExample example);

    int max(@Param("field") String field, @Param("example") ExportExcelRecordExtExample example);

    int min(@Param("field") String field, @Param("example") ExportExcelRecordExtExample example);

}

