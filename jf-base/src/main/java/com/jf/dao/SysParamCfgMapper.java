package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;

@Repository
public interface SysParamCfgMapper extends BaseDao<SysParamCfg, SysParamCfgExample> {
    int countByExample(SysParamCfgExample example);

    int deleteByExample(SysParamCfgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysParamCfg record);

    int insertSelective(SysParamCfg record);

    List<SysParamCfg> selectByExample(SysParamCfgExample example);

    SysParamCfg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysParamCfg record, @Param("example") SysParamCfgExample example);

    int updateByExample(@Param("record") SysParamCfg record, @Param("example") SysParamCfgExample example);

    int updateByPrimaryKeySelective(SysParamCfg record);

    int updateByPrimaryKey(SysParamCfg record);
}
