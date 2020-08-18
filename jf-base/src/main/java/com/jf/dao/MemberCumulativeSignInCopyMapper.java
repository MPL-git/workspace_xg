package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberCumulativeSignInCopy;
import com.jf.entity.MemberCumulativeSignInCopyExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCumulativeSignInCopyMapper extends BaseDao<MemberCumulativeSignInCopy, MemberCumulativeSignInCopyExample> {
    int countByExample(MemberCumulativeSignInCopyExample example);

    int deleteByExample(MemberCumulativeSignInCopyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberCumulativeSignInCopy record);

    int insertSelective(MemberCumulativeSignInCopy record);

    List<MemberCumulativeSignInCopy> selectByExample(MemberCumulativeSignInCopyExample example);

    MemberCumulativeSignInCopy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberCumulativeSignInCopy record, @Param("example") MemberCumulativeSignInCopyExample example);

    int updateByExample(@Param("record") MemberCumulativeSignInCopy record, @Param("example") MemberCumulativeSignInCopyExample example);

    int updateByPrimaryKeySelective(MemberCumulativeSignInCopy record);

    int updateByPrimaryKey(MemberCumulativeSignInCopy record);
}
