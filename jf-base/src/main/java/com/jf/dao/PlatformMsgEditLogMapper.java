package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.PlatformMsgEditLog;
import com.jf.entity.PlatformMsgEditLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformMsgEditLogMapper extends BaseDao<PlatformMsgEditLog, PlatformMsgEditLogExample> {
    int countByExample(PlatformMsgEditLogExample example);

    int deleteByExample(PlatformMsgEditLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlatformMsgEditLog record);

    int insertSelective(PlatformMsgEditLog record);

    List<PlatformMsgEditLog> selectByExample(PlatformMsgEditLogExample example);

    PlatformMsgEditLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlatformMsgEditLog record, @Param("example") PlatformMsgEditLogExample example);

    int updateByExample(@Param("record") PlatformMsgEditLog record, @Param("example") PlatformMsgEditLogExample example);

    int updateByPrimaryKeySelective(PlatformMsgEditLog record);

    int updateByPrimaryKey(PlatformMsgEditLog record);
}
