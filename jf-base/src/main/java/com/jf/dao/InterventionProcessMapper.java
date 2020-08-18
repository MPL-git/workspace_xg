package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.InterventionProcess;
import com.jf.entity.InterventionProcessExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionProcessMapper extends BaseDao<InterventionProcess, InterventionProcessExample> {
    int countByExample(InterventionProcessExample example);

    int deleteByExample(InterventionProcessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InterventionProcess record);

    int insertSelective(InterventionProcess record);

    List<InterventionProcess> selectByExample(InterventionProcessExample example);

    InterventionProcess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InterventionProcess record, @Param("example") InterventionProcessExample example);

    int updateByExample(@Param("record") InterventionProcess record, @Param("example") InterventionProcessExample example);

    int updateByPrimaryKeySelective(InterventionProcess record);

    int updateByPrimaryKey(InterventionProcess record);
}
