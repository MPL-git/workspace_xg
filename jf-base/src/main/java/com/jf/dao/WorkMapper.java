package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.Work;
import com.jf.entity.WorkExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkMapper extends BaseDao<Work, WorkExample> {
    int countByExample(WorkExample example);

    int deleteByExample(WorkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Work record);

    int insertSelective(Work record);

    List<Work> selectByExample(WorkExample example);

    Work selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByExample(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKey(Work record);

    List<Work> selectByExampleWithBLOBs(WorkExample example);
    int updateByPrimaryKeyWithBLOBs(Work record);
    int updateByExampleWithBLOBs(@Param("record") Work record, @Param("example") WorkExample example);
}
