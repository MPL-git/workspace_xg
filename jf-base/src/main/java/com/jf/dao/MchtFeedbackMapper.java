package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtFeedback;
import com.jf.entity.MchtFeedbackExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtFeedbackMapper extends BaseDao<MchtFeedback, MchtFeedbackExample> {
    int countByExample(MchtFeedbackExample example);

    int deleteByExample(MchtFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtFeedback record);

    int insertSelective(MchtFeedback record);

    List<MchtFeedback> selectByExample(MchtFeedbackExample example);

    MchtFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtFeedback record, @Param("example") MchtFeedbackExample example);

    int updateByExample(@Param("record") MchtFeedback record, @Param("example") MchtFeedbackExample example);

    int updateByPrimaryKeySelective(MchtFeedback record);

    int updateByPrimaryKey(MchtFeedback record);
}
