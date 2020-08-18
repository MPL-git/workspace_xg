package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MsgTplCustom;
import com.jf.entity.MsgTplExample;
@Repository
public interface MsgTplCustomMapper{
    int countByExample(MsgTplExample example);
    List<MsgTplCustom> selectByExample(MsgTplExample example);
    MsgTplCustom selectByPrimaryKey(Integer id);
}