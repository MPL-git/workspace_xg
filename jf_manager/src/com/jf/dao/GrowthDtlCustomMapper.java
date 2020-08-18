package com.jf.dao;

import com.jf.entity.GrowthDtlCustom;
import com.jf.entity.GrowthDtlExample;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GrowthDtlCustomMapper{
    int countByExample(GrowthDtlExample example);
    List<GrowthDtlCustom> selectByExample(GrowthDtlExample example);
    GrowthDtlCustom selectByPrimaryKey(Integer id);

    int sumValueByExample(GrowthDtlExample example);
    int selectTotalGValue();
}