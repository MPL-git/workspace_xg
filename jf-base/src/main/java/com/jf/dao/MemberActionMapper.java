package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberAction;
import com.jf.entity.MemberActionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberActionMapper extends BaseDao<MemberAction, MemberActionExample> {
    int countByExample(MemberActionExample example);

    int deleteByExample(MemberActionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberAction record);

    int insertSelective(MemberAction record);

    List<MemberAction> selectByExample(MemberActionExample example);

    MemberAction selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberAction record, @Param("example") MemberActionExample example);

    int updateByExample(@Param("record") MemberAction record, @Param("example") MemberActionExample example);

    int updateByPrimaryKeySelective(MemberAction record);

    int updateByPrimaryKey(MemberAction record);
}