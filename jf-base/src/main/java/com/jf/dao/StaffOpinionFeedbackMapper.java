package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.StaffOpinionFeedback;
import com.jf.entity.StaffOpinionFeedbackExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffOpinionFeedbackMapper extends BaseDao<StaffOpinionFeedback, StaffOpinionFeedbackExample> {
    int countByExample(StaffOpinionFeedbackExample example);

    int deleteByExample(StaffOpinionFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StaffOpinionFeedback record);

    int insertSelective(StaffOpinionFeedback record);

    List<StaffOpinionFeedback> selectByExample(StaffOpinionFeedbackExample example);

    StaffOpinionFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StaffOpinionFeedback record, @Param("example") StaffOpinionFeedbackExample example);

    int updateByExample(@Param("record") StaffOpinionFeedback record, @Param("example") StaffOpinionFeedbackExample example);

    int updateByPrimaryKeySelective(StaffOpinionFeedback record);

    int updateByPrimaryKey(StaffOpinionFeedback record);
}
