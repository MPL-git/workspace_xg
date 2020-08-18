package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberInfoMapper extends BaseDao<MemberInfo, MemberInfoExample> {
    int countByExample(MemberInfoExample example);

    int deleteByExample(MemberInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberInfo record);

    int insertSelective(MemberInfo record);

    List<MemberInfo> selectByExample(MemberInfoExample example);

    MemberInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberInfo record, @Param("example") MemberInfoExample example);

    int updateByExample(@Param("record") MemberInfo record, @Param("example") MemberInfoExample example);

    int updateByPrimaryKeySelective(MemberInfo record);

    int updateByPrimaryKey(MemberInfo record);
}
