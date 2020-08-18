package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SvipPracticeInfo;
import com.jf.entity.SvipPracticeInfoExample;

@Repository
public interface SvipPracticeInfoMapper extends BaseDao<SvipPracticeInfo, SvipPracticeInfoExample> {
    int countByExample(SvipPracticeInfoExample example);

    int deleteByExample(SvipPracticeInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SvipPracticeInfo record);

    int insertSelective(SvipPracticeInfo record);

    List<SvipPracticeInfo> selectByExample(SvipPracticeInfoExample example);

    SvipPracticeInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SvipPracticeInfo record, @Param("example") SvipPracticeInfoExample example);

    int updateByExample(@Param("record") SvipPracticeInfo record, @Param("example") SvipPracticeInfoExample example);

    int updateByPrimaryKeySelective(SvipPracticeInfo record);

    int updateByPrimaryKey(SvipPracticeInfo record);
}
