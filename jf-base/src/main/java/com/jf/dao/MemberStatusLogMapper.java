package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberStatusLog;
import com.jf.entity.MemberStatusLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberStatusLogMapper extends BaseDao<MemberStatusLog, MemberStatusLogExample> {
    int countByExample(MemberStatusLogExample example);

    int deleteByExample(MemberStatusLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberStatusLog record);

    int insertSelective(MemberStatusLog record);

    List<MemberStatusLog> selectByExample(MemberStatusLogExample example);

    MemberStatusLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberStatusLog record, @Param("example") MemberStatusLogExample example);

    int updateByExample(@Param("record") MemberStatusLog record, @Param("example") MemberStatusLogExample example);

    int updateByPrimaryKeySelective(MemberStatusLog record);

    int updateByPrimaryKey(MemberStatusLog record);
}
