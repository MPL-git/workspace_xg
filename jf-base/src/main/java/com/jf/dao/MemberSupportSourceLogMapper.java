package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberSupportSourceLog;
import com.jf.entity.MemberSupportSourceLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberSupportSourceLogMapper extends BaseDao<MemberSupportSourceLog, MemberSupportSourceLogExample> {
    int countByExample(MemberSupportSourceLogExample example);

    int deleteByExample(MemberSupportSourceLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberSupportSourceLog record);

    int insertSelective(MemberSupportSourceLog record);

    List<MemberSupportSourceLog> selectByExample(MemberSupportSourceLogExample example);

    MemberSupportSourceLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberSupportSourceLog record, @Param("example") MemberSupportSourceLogExample example);

    int updateByExample(@Param("record") MemberSupportSourceLog record, @Param("example") MemberSupportSourceLogExample example);

    int updateByPrimaryKeySelective(MemberSupportSourceLog record);

    int updateByPrimaryKey(MemberSupportSourceLog record);
}
