package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ActivityAreaTempletParam;
import com.jf.entity.ActivityAreaTempletParamExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityAreaTempletParamMapper extends BaseDao<ActivityAreaTempletParam, ActivityAreaTempletParamExample> {
    int countByExample(ActivityAreaTempletParamExample example);

    int deleteByExample(ActivityAreaTempletParamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityAreaTempletParam record);

    int insertSelective(ActivityAreaTempletParam record);

    List<ActivityAreaTempletParam> selectByExample(ActivityAreaTempletParamExample example);

    ActivityAreaTempletParam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityAreaTempletParam record, @Param("example") ActivityAreaTempletParamExample example);

    int updateByExample(@Param("record") ActivityAreaTempletParam record, @Param("example") ActivityAreaTempletParamExample example);

    int updateByPrimaryKeySelective(ActivityAreaTempletParam record);

    int updateByPrimaryKey(ActivityAreaTempletParam record);

    List<ActivityAreaTempletParam> selectByExampleWithBLOBs(ActivityAreaTempletParamExample example);
    int updateByPrimaryKeyWithBLOBs(ActivityAreaTempletParam record);
    int updateByExampleWithBLOBs(@Param("record") ActivityAreaTempletParam record, @Param("example") ActivityAreaTempletParamExample example);
}
