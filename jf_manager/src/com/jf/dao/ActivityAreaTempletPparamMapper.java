package com.jf.dao;

import com.jf.entity.ActivityAreaTempletPparam;
import com.jf.entity.ActivityAreaTempletPparamExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityAreaTempletPparamMapper extends BaseDao<ActivityAreaTempletPparam, ActivityAreaTempletPparamExample> {
    int countByExample(ActivityAreaTempletPparamExample example);

    int deleteByExample(ActivityAreaTempletPparamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityAreaTempletPparam record);

    int insertSelective(ActivityAreaTempletPparam record);

    List<ActivityAreaTempletPparam> selectByExampleWithBLOBs(ActivityAreaTempletPparamExample example);

    List<ActivityAreaTempletPparam> selectByExample(ActivityAreaTempletPparamExample example);

    ActivityAreaTempletPparam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityAreaTempletPparam record, @Param("example") ActivityAreaTempletPparamExample example);

    int updateByExampleWithBLOBs(@Param("record") ActivityAreaTempletPparam record, @Param("example") ActivityAreaTempletPparamExample example);

    int updateByExample(@Param("record") ActivityAreaTempletPparam record, @Param("example") ActivityAreaTempletPparamExample example);

    int updateByPrimaryKeySelective(ActivityAreaTempletPparam record);

    int updateByPrimaryKeyWithBLOBs(ActivityAreaTempletPparam record);

    int updateByPrimaryKey(ActivityAreaTempletPparam record);
}