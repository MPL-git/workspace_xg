package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.WoRkHistoryCustom;
import com.jf.entity.WoRkHistoryCustomExample;

@Repository
public interface WoRkHistoryCustomMapper {
    int countByCustomExample(WoRkHistoryCustomExample example);

    List<WoRkHistoryCustom> selectByCustomExample(WoRkHistoryCustomExample example);

    WoRkHistoryCustom selectByPrimaryKeyCustom(Integer staffRoleId);

}