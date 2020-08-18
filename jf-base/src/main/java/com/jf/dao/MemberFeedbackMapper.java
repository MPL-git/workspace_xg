package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberFeedback;
import com.jf.entity.MemberFeedbackExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberFeedbackMapper extends BaseDao<MemberFeedback, MemberFeedbackExample> {
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
