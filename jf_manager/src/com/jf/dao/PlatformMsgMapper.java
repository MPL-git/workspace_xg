package com.jf.dao;

import com.jf.entity.PlatformMsg;
import com.jf.entity.PlatformMsgExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 站内消息
 * @author luoyl
 * 创建时间：2017-3-27
 */
@Repository
public interface PlatformMsgMapper extends BaseDao<PlatformMsg, PlatformMsgExample>{
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