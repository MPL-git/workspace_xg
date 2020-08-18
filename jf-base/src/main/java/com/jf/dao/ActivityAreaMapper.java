package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ActivityArea;
import com.jf.entity.ActivityAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityAreaMapper extends BaseDao<ActivityArea,ActivityAreaExample> {
    int countByExample(ActivityAreaExample example);

    int deleteByExample(ActivityAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityArea record);

    int insertSelective(ActivityArea record);

    List<ActivityArea> selectByExample(ActivityAreaExample example);

    ActivityArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityArea record, @Param("example") ActivityAreaExample example);

    int updateByExample(@Param("record") ActivityArea record, @Param("example") ActivityAreaExample example);

    int updateByPrimaryKeySelective(ActivityArea record);

    int updateByPrimaryKey(ActivityArea record);
}