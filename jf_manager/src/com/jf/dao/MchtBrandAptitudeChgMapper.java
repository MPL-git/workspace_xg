package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBrandAptitudeChg;
import com.jf.entity.MchtBrandAptitudeChgExample;
@Repository
public interface MchtBrandAptitudeChgMapper extends BaseDao<MchtBrandAptitudeChg, MchtBrandAptitudeChgExample>{
    int countByExample(MchtBrandAptitudeChgExample example);

    int deleteByExample(MchtBrandAptitudeChgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandAptitudeChg record);

    int insertSelective(MchtBrandAptitudeChg record);

    List<MchtBrandAptitudeChg> selectByExample(MchtBrandAptitudeChgExample example);

    MchtBrandAptitudeChg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandAptitudeChg record, @Param("example") MchtBrandAptitudeChgExample example);

    int updateByExample(@Param("record") MchtBrandAptitudeChg record, @Param("example") MchtBrandAptitudeChgExample example);

    int updateByPrimaryKeySelective(MchtBrandAptitudeChg record);

    int updateByPrimaryKey(MchtBrandAptitudeChg record);
}