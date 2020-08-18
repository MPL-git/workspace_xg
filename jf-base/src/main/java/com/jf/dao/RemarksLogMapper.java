package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.RemarksLog;
import com.jf.entity.RemarksLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemarksLogMapper extends BaseDao<RemarksLog, RemarksLogExample> {
    int countByExample(RemarksLogExample example);

    int deleteByExample(RemarksLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RemarksLog record);

    int insertSelective(RemarksLog record);

    List<RemarksLog> selectByExample(RemarksLogExample example);

    RemarksLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RemarksLog record, @Param("example") RemarksLogExample example);

    int updateByExample(@Param("record") RemarksLog record, @Param("example") RemarksLogExample example);

    int updateByPrimaryKeySelective(RemarksLog record);

    int updateByPrimaryKey(RemarksLog record);
}
