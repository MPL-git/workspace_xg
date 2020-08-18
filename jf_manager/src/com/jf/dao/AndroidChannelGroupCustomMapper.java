package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.AndroidChannelGroupCustom;
import com.jf.entity.AndroidChannelGroupExample;
@Repository
public interface AndroidChannelGroupCustomMapper{
    int countByExample(AndroidChannelGroupExample example);
    List<AndroidChannelGroupCustom> selectByExample(AndroidChannelGroupExample example);
    AndroidChannelGroupCustom selectByPrimaryKey(Integer id);
}