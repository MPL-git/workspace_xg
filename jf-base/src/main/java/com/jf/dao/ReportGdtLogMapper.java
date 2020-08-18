package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ReportGdtLog;
import com.jf.entity.ReportGdtLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportGdtLogMapper extends BaseDao<ReportGdtLog, ReportGdtLogExample> {
    int countByExample(ReportGdtLogExample example);

    int deleteByExample(ReportGdtLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportGdtLog record);

    int insertSelective(ReportGdtLog record);

    List<ReportGdtLog> selectByExample(ReportGdtLogExample example);

    ReportGdtLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportGdtLog record, @Param("example") ReportGdtLogExample example);

    int updateByExample(@Param("record") ReportGdtLog record, @Param("example") ReportGdtLogExample example);

    int updateByPrimaryKeySelective(ReportGdtLog record);

    int updateByPrimaryKey(ReportGdtLog record);
}
