package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtViewlogCustom;
import com.jf.entity.MchtViewlogCustomExample;

@Repository
public interface MchtViewlogCustomMapper {
    int mchtViewlogExamplecountByExample(MchtViewlogCustomExample example);

    List<MchtViewlogCustom> mchtViewlogCustomselectByExample(MchtViewlogCustomExample example);

    MchtViewlogCustom mchtViewlogCustomselectByPrimaryKey(Integer id);
}