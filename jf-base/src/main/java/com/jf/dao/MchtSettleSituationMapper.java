package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtSettleSituation;
import com.jf.entity.MchtSettleSituationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtSettleSituationMapper extends BaseDao<MchtSettleSituation, MchtSettleSituationExample> {
    int countByExample(MchtSettleSituationExample example);

    int deleteByExample(MchtSettleSituationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtSettleSituation record);

    int insertSelective(MchtSettleSituation record);

    List<MchtSettleSituation> selectByExample(MchtSettleSituationExample example);

    MchtSettleSituation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtSettleSituation record, @Param("example") MchtSettleSituationExample example);

    int updateByExample(@Param("record") MchtSettleSituation record, @Param("example") MchtSettleSituationExample example);

    int updateByPrimaryKeySelective(MchtSettleSituation record);

    int updateByPrimaryKey(MchtSettleSituation record);
}
