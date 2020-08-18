package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberBlackOperateLog;
import com.jf.entity.MemberBlackOperateLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberBlackOperateLogMapper extends BaseDao<MemberBlackOperateLog, MemberBlackOperateLogExample> {
    int countByExample(MemberBlackOperateLogExample example);

    int deleteByExample(MemberBlackOperateLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberBlackOperateLog record);

    int insertSelective(MemberBlackOperateLog record);

    List<MemberBlackOperateLog> selectByExample(MemberBlackOperateLogExample example);

    MemberBlackOperateLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberBlackOperateLog record, @Param("example") MemberBlackOperateLogExample example);

    int updateByExample(@Param("record") MemberBlackOperateLog record, @Param("example") MemberBlackOperateLogExample example);

    int updateByPrimaryKeySelective(MemberBlackOperateLog record);

    int updateByPrimaryKey(MemberBlackOperateLog record);
}
