package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberStatisticalInfo;
import com.jf.entity.MemberStatisticalInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberStatisticalInfoMapper extends BaseDao<MemberStatisticalInfo, MemberStatisticalInfoExample> {
    int countByExample(MemberStatisticalInfoExample example);

    int deleteByExample(MemberStatisticalInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberStatisticalInfo record);

    int insertSelective(MemberStatisticalInfo record);

    List<MemberStatisticalInfo> selectByExample(MemberStatisticalInfoExample example);

    MemberStatisticalInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberStatisticalInfo record, @Param("example") MemberStatisticalInfoExample example);

    int updateByExample(@Param("record") MemberStatisticalInfo record, @Param("example") MemberStatisticalInfoExample example);

    int updateByPrimaryKeySelective(MemberStatisticalInfo record);

    int updateByPrimaryKey(MemberStatisticalInfo record);
}
