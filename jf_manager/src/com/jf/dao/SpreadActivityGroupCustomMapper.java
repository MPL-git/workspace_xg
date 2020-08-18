package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SpreadActivityGroupCustom;
import com.jf.entity.SpreadActivityGroupExample;
@Repository
public interface SpreadActivityGroupCustomMapper{
    int countByExample(SpreadActivityGroupExample example);

    List<SpreadActivityGroupCustom> selectByExample(SpreadActivityGroupExample example);

    SpreadActivityGroupCustom selectByPrimaryKey(Integer id);
}