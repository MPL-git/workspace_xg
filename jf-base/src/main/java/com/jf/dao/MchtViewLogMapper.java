package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtViewLog;
import com.jf.entity.MchtViewLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtViewLogMapper extends BaseDao<MchtViewLog, MchtViewLogExample> {
    int countByExample(MchtViewLogExample example);

    int deleteByExample(MchtViewLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtViewLog record);

    int insertSelective(MchtViewLog record);

    List<MchtViewLog> selectByExample(MchtViewLogExample example);

    MchtViewLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtViewLog record, @Param("example") MchtViewLogExample example);

    int updateByExample(@Param("record") MchtViewLog record, @Param("example") MchtViewLogExample example);

    int updateByPrimaryKeySelective(MchtViewLog record);

    int updateByPrimaryKey(MchtViewLog record);
}
