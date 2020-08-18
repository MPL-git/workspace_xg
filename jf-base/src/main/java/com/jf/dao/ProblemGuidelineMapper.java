package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ProblemGuideline;
import com.jf.entity.ProblemGuidelineExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemGuidelineMapper extends BaseDao<ProblemGuideline, ProblemGuidelineExample> {
    int countByExample(ProblemGuidelineExample example);

    int deleteByExample(ProblemGuidelineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProblemGuideline record);

    int insertSelective(ProblemGuideline record);

    List<ProblemGuideline> selectByExample(ProblemGuidelineExample example);

    ProblemGuideline selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProblemGuideline record, @Param("example") ProblemGuidelineExample example);

    int updateByExample(@Param("record") ProblemGuideline record, @Param("example") ProblemGuidelineExample example);

    int updateByPrimaryKeySelective(ProblemGuideline record);

    int updateByPrimaryKey(ProblemGuideline record);
}