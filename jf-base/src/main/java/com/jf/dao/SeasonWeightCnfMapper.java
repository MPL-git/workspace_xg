package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SeasonWeightCnf;
import com.jf.entity.SeasonWeightCnfExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonWeightCnfMapper extends BaseDao<SeasonWeightCnf, SeasonWeightCnfExample> {
    int countByExample(SeasonWeightCnfExample example);

    int deleteByExample(SeasonWeightCnfExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SeasonWeightCnf record);

    int insertSelective(SeasonWeightCnf record);

    List<SeasonWeightCnf> selectByExample(SeasonWeightCnfExample example);

    SeasonWeightCnf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SeasonWeightCnf record, @Param("example") SeasonWeightCnfExample example);

    int updateByExample(@Param("record") SeasonWeightCnf record, @Param("example") SeasonWeightCnfExample example);

    int updateByPrimaryKeySelective(SeasonWeightCnf record);

    int updateByPrimaryKey(SeasonWeightCnf record);
}
