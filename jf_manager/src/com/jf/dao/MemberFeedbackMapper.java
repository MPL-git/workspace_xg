package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberFeedback;
import com.jf.entity.MemberFeedbackExample;
@Repository
public interface MemberFeedbackMapper extends BaseDao<MemberFeedback, MemberFeedbackExample>{
    int countByExample(MemberFeedbackExample example);

    int deleteByExample(MemberFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberFeedback record);

    int insertSelective(MemberFeedback record);

    List<MemberFeedback> selectByExample(MemberFeedbackExample example);

    MemberFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberFeedback record, @Param("example") MemberFeedbackExample example);

    int updateByExample(@Param("record") MemberFeedback record, @Param("example") MemberFeedbackExample example);

    int updateByPrimaryKeySelective(MemberFeedback record);

    int updateByPrimaryKey(MemberFeedback record);
}