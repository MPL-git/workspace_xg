package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SvipPracticeRecord;
import com.jf.entity.SvipPracticeRecordExample;

@Repository
public interface SvipPracticeRecordMapper extends BaseDao<SvipPracticeRecord, SvipPracticeRecordExample> {
    int countByExample(SvipPracticeRecordExample example);

    int deleteByExample(SvipPracticeRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SvipPracticeRecord record);

    int insertSelective(SvipPracticeRecord record);

    List<SvipPracticeRecord> selectByExample(SvipPracticeRecordExample example);

    SvipPracticeRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SvipPracticeRecord record, @Param("example") SvipPracticeRecordExample example);

    int updateByExample(@Param("record") SvipPracticeRecord record, @Param("example") SvipPracticeRecordExample example);

    int updateByPrimaryKeySelective(SvipPracticeRecord record);

    int updateByPrimaryKey(SvipPracticeRecord record);
}
