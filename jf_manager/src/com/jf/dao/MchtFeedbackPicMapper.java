package com.jf.dao;

import com.jf.entity.MchtFeedbackPic;
import com.jf.entity.MchtFeedbackPicExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtFeedbackPicMapper extends BaseDao<MchtFeedbackPic, MchtFeedbackPicExample>{
    int countByExample(MchtFeedbackPicExample example);

    int deleteByExample(MchtFeedbackPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtFeedbackPic record);

    int insertSelective(MchtFeedbackPic record);

    List<MchtFeedbackPic> selectByExample(MchtFeedbackPicExample example);

    MchtFeedbackPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtFeedbackPic record, @Param("example") MchtFeedbackPicExample example);

    int updateByExample(@Param("record") MchtFeedbackPic record, @Param("example") MchtFeedbackPicExample example);

    int updateByPrimaryKeySelective(MchtFeedbackPic record);

    int updateByPrimaryKey(MchtFeedbackPic record);
}