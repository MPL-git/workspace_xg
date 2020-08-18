package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
@Repository
public interface SysParamCfgMapper extends BaseDao<SysParamCfg, SysParamCfgExample>{
    int countByExample(SysParamCfgExample example);

    int deleteByExample(SysParamCfgExample example);

    int deleteByPrimaryKey(Integer paramId);

    int insert(SysParamCfg record);

    int insertSelective(SysParamCfg record);

    List<SysParamCfg> selectByExample(SysParamCfgExample example);

    SysParamCfg selectByPrimaryKey(Integer paramId);

    int updateByExampleSelective(@Param("record") SysParamCfg record, @Param("example") SysParamCfgExample example);

    int updateByExample(@Param("record") SysParamCfg record, @Param("example") SysParamCfgExample example);

    int updateByPrimaryKeySelective(SysParamCfg record);

    int updateByPrimaryKey(SysParamCfg record);
}