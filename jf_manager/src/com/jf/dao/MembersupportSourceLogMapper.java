package com.jf.dao;

import com.jf.entity.MembersupportSourceLog;
import com.jf.entity.MembersupportSourceLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MembersupportSourceLogMapper extends BaseDao<MembersupportSourceLog, MembersupportSourceLogExample>{
    int countByExample(MembersupportSourceLogExample example);

    int deleteByExample(MembersupportSourceLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MembersupportSourceLog record);

    int insertSelective(MembersupportSourceLog record);

    List<MembersupportSourceLog> selectByExample(MembersupportSourceLogExample example);

    MembersupportSourceLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MembersupportSourceLog record, @Param("example") MembersupportSourceLogExample example);

    int updateByExample(@Param("record") MembersupportSourceLog record, @Param("example") MembersupportSourceLogExample example);

    int updateByPrimaryKeySelective(MembersupportSourceLog record);

    int updateByPrimaryKey(MembersupportSourceLog record);
}