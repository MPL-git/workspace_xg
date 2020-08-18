package com.jf.dao;

import com.jf.entity.ActivityBrandGroup;
import com.jf.entity.ActivityBrandGroupExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ActivityBrandGroupMapper extends BaseDao<ActivityBrandGroup,ActivityBrandGroupExample>{
    int countByExample(ActivityBrandGroupExample example);

    int deleteByExample(ActivityBrandGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityBrandGroup record);

    int insertSelective(ActivityBrandGroup record);

    List<ActivityBrandGroup> selectByExample(ActivityBrandGroupExample example);

    ActivityBrandGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityBrandGroup record, @Param("example") ActivityBrandGroupExample example);

    int updateByExample(@Param("record") ActivityBrandGroup record, @Param("example") ActivityBrandGroupExample example);

    int updateByPrimaryKeySelective(ActivityBrandGroup record);

    int updateByPrimaryKey(ActivityBrandGroup record);
}