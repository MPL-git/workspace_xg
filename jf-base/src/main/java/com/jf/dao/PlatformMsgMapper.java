package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.PlatformMsg;
import com.jf.entity.PlatformMsgExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformMsgMapper extends BaseDao<PlatformMsg, PlatformMsgExample> {
    int countByExample(PlatformMsgExample example);

    int deleteByExample(PlatformMsgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlatformMsg record);

    int insertSelective(PlatformMsg record);

    List<PlatformMsg> selectByExample(PlatformMsgExample example);

    PlatformMsg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlatformMsg record, @Param("example") PlatformMsgExample example);

    int updateByExample(@Param("record") PlatformMsg record, @Param("example") PlatformMsgExample example);

    int updateByPrimaryKeySelective(PlatformMsg record);

    int updateByPrimaryKey(PlatformMsg record);
}
