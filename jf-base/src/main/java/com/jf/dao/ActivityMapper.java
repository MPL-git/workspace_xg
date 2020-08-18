package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.Activity;
import com.jf.entity.ActivityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityMapper extends BaseDao<Activity, ActivityExample> {
    int countByExample(ActivityExample example);

    int deleteByExample(ActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    List<Activity> selectByExample(ActivityExample example);

    Activity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByExample(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> selectByExampleWithBLOBs(ActivityExample example);
    int updateByPrimaryKeyWithBLOBs(Activity record);
    int updateByExampleWithBLOBs(@Param("record") Activity record, @Param("example") ActivityExample example);
}
