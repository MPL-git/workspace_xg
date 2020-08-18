package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberAllowanceUsage;
import com.jf.entity.MemberAllowanceUsageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAllowanceUsageMapper extends BaseDao<MemberAllowanceUsage, MemberAllowanceUsageExample> {
    int countByExample(MemberAllowanceUsageExample example);

    int deleteByExample(MemberAllowanceUsageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberAllowanceUsage record);

    int insertSelective(MemberAllowanceUsage record);

    List<MemberAllowanceUsage> selectByExample(MemberAllowanceUsageExample example);

    MemberAllowanceUsage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberAllowanceUsage record, @Param("example") MemberAllowanceUsageExample example);

    int updateByExample(@Param("record") MemberAllowanceUsage record, @Param("example") MemberAllowanceUsageExample example);

    int updateByPrimaryKeySelective(MemberAllowanceUsage record);

    int updateByPrimaryKey(MemberAllowanceUsage record);
}