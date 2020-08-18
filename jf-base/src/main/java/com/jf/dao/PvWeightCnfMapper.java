package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.PvWeightCnf;
import com.jf.entity.PvWeightCnfExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PvWeightCnfMapper extends BaseDao<PvWeightCnf, PvWeightCnfExample> {
    int countByExample(PvWeightCnfExample example);

    int deleteByExample(PvWeightCnfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PvWeightCnf record);

    int insertSelective(PvWeightCnf record);

    List<PvWeightCnf> selectByExample(PvWeightCnfExample example);

    PvWeightCnf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PvWeightCnf record, @Param("example") PvWeightCnfExample example);

    int updateByExample(@Param("record") PvWeightCnf record, @Param("example") PvWeightCnfExample example);

    int updateByPrimaryKeySelective(PvWeightCnf record);

    int updateByPrimaryKey(PvWeightCnf record);
}
