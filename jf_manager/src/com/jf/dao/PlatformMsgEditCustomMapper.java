package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.PlatformMsgEditCustom;
import com.jf.entity.PlatformMsgEditExample;

@Repository
public interface PlatformMsgEditCustomMapper{
    int countByExample(PlatformMsgEditExample example);
    List<PlatformMsgEditCustom> selectByExample(PlatformMsgEditExample example);
    PlatformMsgEditCustom selectByPrimaryKey(Integer id);

}