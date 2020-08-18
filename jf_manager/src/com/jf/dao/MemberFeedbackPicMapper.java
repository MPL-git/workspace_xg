package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberFeedbackPic;
import com.jf.entity.MemberFeedbackPicExample;
@Repository
public interface MemberFeedbackPicMapper extends BaseDao<MemberFeedbackPic, MemberFeedbackPicExample>{
    int countByExample(MemberFeedbackPicExample example);

    int deleteByExample(MemberFeedbackPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberFeedbackPic record);

    int insertSelective(MemberFeedbackPic record);

    List<MemberFeedbackPic> selectByExample(MemberFeedbackPicExample example);

    MemberFeedbackPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberFeedbackPic record, @Param("example") MemberFeedbackPicExample example);

    int updateByExample(@Param("record") MemberFeedbackPic record, @Param("example") MemberFeedbackPicExample example);

    int updateByPrimaryKeySelective(MemberFeedbackPic record);

    int updateByPrimaryKey(MemberFeedbackPic record);
}