package com.jf.dao;

import com.jf.entity.ActivityBrandGroupActivity;
import com.jf.entity.ActivityBrandGroupActivityExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ActivityBrandGroupActivityMapper extends BaseDao<ActivityBrandGroupActivity,ActivityBrandGroupActivityExample>{
    int countByExample(ActivityBrandGroupActivityExample example);

    int deleteByExample(ActivityBrandGroupActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityBrandGroupActivity record);

    int insertSelective(ActivityBrandGroupActivity record);

    List<ActivityBrandGroupActivity> selectByExample(ActivityBrandGroupActivityExample example);

    ActivityBrandGroupActivity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityBrandGroupActivity record, @Param("example") ActivityBrandGroupActivityExample example);

    int updateByExample(@Param("record") ActivityBrandGroupActivity record, @Param("example") ActivityBrandGroupActivityExample example);

    int updateByPrimaryKeySelective(ActivityBrandGroupActivity record);

    int updateByPrimaryKey(ActivityBrandGroupActivity record);
}