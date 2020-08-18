package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ExportExcelRecord;
import com.jf.entity.ExportExcelRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportExcelRecordMapper extends BaseDao<ExportExcelRecord, ExportExcelRecordExample> {
    int countByExample(ExportExcelRecordExample example);

    int deleteByExample(ExportExcelRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExportExcelRecord record);

    int insertSelective(ExportExcelRecord record);

    List<ExportExcelRecord> selectByExample(ExportExcelRecordExample example);

    ExportExcelRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExportExcelRecord record, @Param("example") ExportExcelRecordExample example);

    int updateByExample(@Param("record") ExportExcelRecord record, @Param("example") ExportExcelRecordExample example);

    int updateByPrimaryKeySelective(ExportExcelRecord record);

    int updateByPrimaryKey(ExportExcelRecord record);
}
