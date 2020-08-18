package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.StaffOpinionFeedbackPic;
import com.jf.entity.StaffOpinionFeedbackPicExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffOpinionFeedbackPicMapper extends BaseDao<StaffOpinionFeedbackPic, StaffOpinionFeedbackPicExample> {
    int countByExample(StaffOpinionFeedbackPicExample example);

    int deleteByExample(StaffOpinionFeedbackPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StaffOpinionFeedbackPic record);

    int insertSelective(StaffOpinionFeedbackPic record);

    List<StaffOpinionFeedbackPic> selectByExample(StaffOpinionFeedbackPicExample example);

    StaffOpinionFeedbackPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StaffOpinionFeedbackPic record, @Param("example") StaffOpinionFeedbackPicExample example);

    int updateByExample(@Param("record") StaffOpinionFeedbackPic record, @Param("example") StaffOpinionFeedbackPicExample example);

    int updateByPrimaryKeySelective(StaffOpinionFeedbackPic record);

    int updateByPrimaryKey(StaffOpinionFeedbackPic record);
}
