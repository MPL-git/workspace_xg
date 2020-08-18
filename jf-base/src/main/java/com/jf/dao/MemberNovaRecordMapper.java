package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberNovaRecord;
import com.jf.entity.MemberNovaRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberNovaRecordMapper extends BaseDao<MemberNovaRecord, MemberNovaRecordExample> {
    int countByExample(MemberNovaRecordExample example);

    int deleteByExample(MemberNovaRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberNovaRecord record);

    int insertSelective(MemberNovaRecord record);

    List<MemberNovaRecord> selectByExample(MemberNovaRecordExample example);

    MemberNovaRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberNovaRecord record, @Param("example") MemberNovaRecordExample example);

    int updateByExample(@Param("record") MemberNovaRecord record, @Param("example") MemberNovaRecordExample example);

    int updateByPrimaryKeySelective(MemberNovaRecord record);

    int updateByPrimaryKey(MemberNovaRecord record);
}
