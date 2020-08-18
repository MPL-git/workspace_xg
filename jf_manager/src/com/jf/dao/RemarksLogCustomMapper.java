package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.RemarksLogCustom;
import com.jf.entity.RemarksLogExample;
@Repository
public interface RemarksLogCustomMapper{
    int countByExample(RemarksLogExample example);
    List<RemarksLogCustom> selectByExample(RemarksLogExample example);
    RemarksLogCustom selectByPrimaryKey(Integer id);
}