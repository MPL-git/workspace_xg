package com.jf.dao;

import com.jf.entity.CooperationChangeApplyLog;
import com.jf.entity.CooperationChangeApplyLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CooperationChangeApplyLogMapper extends BaseDao<CooperationChangeApplyLog,CooperationChangeApplyLogExample> {
    int countByExample(CooperationChangeApplyLogExample example);

    int deleteByExample(CooperationChangeApplyLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CooperationChangeApplyLog record);

    int insertSelective(CooperationChangeApplyLog record);

    List<CooperationChangeApplyLog> selectByExample(CooperationChangeApplyLogExample example);

    CooperationChangeApplyLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CooperationChangeApplyLog record, @Param("example") CooperationChangeApplyLogExample example);

    int updateByExample(@Param("record") CooperationChangeApplyLog record, @Param("example") CooperationChangeApplyLogExample example);

    int updateByPrimaryKeySelective(CooperationChangeApplyLog record);

    int updateByPrimaryKey(CooperationChangeApplyLog record);
}