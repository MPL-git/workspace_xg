package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.BrandTeamType;
import com.jf.entity.BrandTeamTypeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandTeamTypeMapper extends BaseDao<BrandTeamType, BrandTeamTypeExample> {
    int countByExample(BrandTeamTypeExample example);

    int deleteByExample(BrandTeamTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BrandTeamType record);

    int insertSelective(BrandTeamType record);

    List<BrandTeamType> selectByExample(BrandTeamTypeExample example);

    BrandTeamType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BrandTeamType record, @Param("example") BrandTeamTypeExample example);

    int updateByExample(@Param("record") BrandTeamType record, @Param("example") BrandTeamTypeExample example);

    int updateByPrimaryKeySelective(BrandTeamType record);

    int updateByPrimaryKey(BrandTeamType record);
}
