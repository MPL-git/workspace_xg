package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ActivityBrandGroupActivity;
import com.jf.entity.ActivityBrandGroupActivityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityBrandGroupActivityMapper extends BaseDao<ActivityBrandGroupActivity, ActivityBrandGroupActivityExample> {
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
