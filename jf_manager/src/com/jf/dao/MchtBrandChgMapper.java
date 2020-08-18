package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBrandChg;
import com.jf.entity.MchtBrandChgExample;
@Repository
public interface MchtBrandChgMapper extends BaseDao<MchtBrandChg, MchtBrandChgExample> {
    int countByExample(MchtBrandChgExample example);

    int deleteByExample(MchtBrandChgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandChg record);

    int insertSelective(MchtBrandChg record);

    List<MchtBrandChg> selectByExample(MchtBrandChgExample example);

    MchtBrandChg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandChg record, @Param("example") MchtBrandChgExample example);

    int updateByExample(@Param("record") MchtBrandChg record, @Param("example") MchtBrandChgExample example);

    int updateByPrimaryKeySelective(MchtBrandChg record);

    int updateByPrimaryKey(MchtBrandChg record);
}