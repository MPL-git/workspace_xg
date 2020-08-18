package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ActivityAreaDecorate;
import com.jf.entity.ActivityAreaDecorateExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityAreaDecorateMapper extends BaseDao<ActivityAreaDecorate, ActivityAreaDecorateExample> {
    int countByExample(ActivityAreaDecorateExample example);

    int deleteByExample(ActivityAreaDecorateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityAreaDecorate record);

    int insertSelective(ActivityAreaDecorate record);

    List<ActivityAreaDecorate> selectByExample(ActivityAreaDecorateExample example);

    ActivityAreaDecorate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityAreaDecorate record, @Param("example") ActivityAreaDecorateExample example);

    int updateByExample(@Param("record") ActivityAreaDecorate record, @Param("example") ActivityAreaDecorateExample example);

    int updateByPrimaryKeySelective(ActivityAreaDecorate record);

    int updateByPrimaryKey(ActivityAreaDecorate record);
}
